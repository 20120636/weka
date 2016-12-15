package com.yiniu.classify;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yiniu.Instance;
import com.yiniu.Instances;

/**
 * 朴素贝叶斯分类器
 */
public class NBClassifier implements Classifier, Serializable {

	private static final long serialVersionUID = 3386342682434404630L;

	/**
	 * 命名常量存储算法需要的数据信息，便于算法后边的调用，加快算法参数的数据读取数据。该数据同时需要存入数据库或者文本文件里。
	 * 防止内存数据消失所带来的重新训练算法
	 */

	/** 训练样本中每个类别的频率 */
	public static Map<String, Double> classFrequencyMap;

	/** 每个类别下各个特征的频率 */
	public static Map<String, List<Map<String, Double>>> featureFrequencyMap = new HashMap<String, List<Map<String, Double>>>();

	public static Map<String, Double> getClassFrequencyMap() {
		return classFrequencyMap;
	}

	public static Map<String, List<Map<String, Double>>> getFeatureFrequencyMap() {
		return featureFrequencyMap;
	}

	public void buildClassifier(Instances instances) throws Exception {
		/** 判断最后一列是否为类别，如果是就行算法训练 */
		List<Instance> instanceList = instances.getInstances();
		Integer featureNum = instanceList.get(0).getAttributeNum() - 1;
		// 对训练数据进行按类存储
		Map<String, List<List<String>>> clazzData = new HashMap<String, List<List<String>>>();
		for (Instance instance : instanceList) {
			List<String> attributeValueS = (List<String>) instance.getAttributeValueS();
			String key = attributeValueS.get(attributeValueS.size() - 1);
			if (clazzData.containsKey(key)) {
				clazzData.get(key).add(attributeValueS);
			} else {
				List<List<String>> clazz = new ArrayList<List<String>>();
				clazz.add(attributeValueS);
				clazzData.put(key, clazz);
			}
		}
		// 获取每个类别的频率
		classFrequencyMap = calClassFrequency(clazzData);

		// 每个类别下各个特征的频率
		featureFrequencyMap = calFeatureFrequency(clazzData, featureNum);
	}

	/**
	 * 计算每个类的频率
	 * 
	 * @return
	 */
	public Map<String, Double> calClassFrequency(Map<String, List<List<String>>> clazzData) {
		Map<String, Double> classFrequencyMap = new HashMap<String, Double>();
		Double count = 0.0;
		Set<String> keySet = clazzData.keySet();
		// 获取每个类别的频数
		for (String key : keySet) {
			Double clazzNum = (double) clazzData.get(key).size();
			classFrequencyMap.put(key, clazzNum);
			count += clazzNum;
		}

		// 计算每个类别的频率
		for (String key : keySet) {
			classFrequencyMap.put(key, classFrequencyMap.get(key) / count);
		}
		return classFrequencyMap;
	}

	/**
	 * 计算每个类别下各个特征的频率
	 * 
	 * @param clazzData
	 *            训练数据
	 * @param featureNum
	 *            特征数量
	 * @return
	 */
	public Map<String, List<Map<String, Double>>> calFeatureFrequency(Map<String, List<List<String>>> clazzData,
			Integer featureNum) {
		Map<String, List<Map<String, Double>>> featureFrequencyMap = new HashMap<String, List<Map<String, Double>>>();
		Set<String> keySet = clazzData.keySet();
		for (String key : keySet) {
			List<List<String>> cDataList = clazzData.get(key);
			Integer cDataNum = cDataList.size();
			List<Map<String, Double>> listMap = new ArrayList<Map<String,Double>>();
			for(int i = 0; i < featureNum; i++){
				//统计第i个特征的频数，并计算其概率。存入listMap
				Map<String, Double> featureMap = new HashMap<String, Double>();
				for(List<String> list : cDataList){
					if(featureMap.containsKey(list.get(i))){
						featureMap.put(list.get(i), featureMap.get(list.get(i))+1);
					}else {
						featureMap.put(list.get(i), 1.0);
					}
				}
				
				//计算第i个特征每种取值的概率
				Set<String> fKeySet = featureMap.keySet();
				for(String fKey : fKeySet){
					featureMap.put(fKey, featureMap.get(fKey)/cDataNum);
				}
				listMap.add(featureMap);
			}
			featureFrequencyMap.put(key, listMap);
		}
		return featureFrequencyMap;
	}


	/**
	 * 利用训练好的分类器进行数据分类处理
	 */
	public ClassResult classifyInstance(Instances instances) throws Exception {
		//分类结果
		Map<String, List<List<?>>> classResultMap = new HashMap<String, List<List<?>>>();
		
		List<Instance> instanceList = instances.getInstances();
		for(Instance instance : instanceList){
			//用于存储样本对于各个类别的概率
			Map<String, Double> clazzScoreMap = new HashMap<String, Double>();
			
			Set<String> keySet = featureFrequencyMap.keySet();
			List<String> attributeValueS = (List<String>) instance.getAttributeValueS();
			for(String key : keySet){
				//计算该样本属于每个类别的概率
				 List<Map<String, Double>> listMap = featureFrequencyMap.get(key);
				 Double clazzScore = 1.0;
				 for(int i = 0; i < instance.getAttributeNum()-1; i++){
					 
					 /**  当该属性在该类的不存在时，这里默认概率为0。当该情况出现时，贝叶斯分类的效果就会大大打折，所以后期需要进行处理
					  *   可以利用Laplace校准  http://www.cnblogs.com/leoo2sk/archive/2010/09/17/naive-bayesian-classifier.html*/
					 if(listMap.get(i).get(attributeValueS.get(i))== null){
						 clazzScore *= 0;
					 }else {
						 clazzScore *= listMap.get(i).get(attributeValueS.get(i));
					}
				 }
				 clazzScore *= classFrequencyMap.get(key);
				 clazzScoreMap.put(key, clazzScore);
			}
			String cKey = getKeyOfMapMaxValue(clazzScoreMap);
			if(classResultMap.containsKey(cKey)){
				classResultMap.get(cKey).add(attributeValueS);
			}else {
				List<List<?>> lList = new ArrayList<List<?>>();
				lList.add(attributeValueS);
				classResultMap.put(cKey, lList);
			}
			
			
		}
		return new ClassResult(classResultMap);
	}

	/**
	 * 获取map存储最大值的key
	 * @param map
	 * @return
	 */
	public String getKeyOfMapMaxValue(Map<String, Double> map){
		Set<String> keySet = map.keySet();
		String clazzKey = null;
		Double counter = -1.0;
		for(String key : keySet){
			if(map.get(key)>= counter){
				clazzKey = key;
				counter = map.get(key);
			}
		}
		return clazzKey;
	}
}
