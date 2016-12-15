package com.yiniu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


import weka.classifiers.AbstractClassifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * @author Con Zhang
 * @date 2016年9月18日
 */
public class WekaUtil {

	/**
	 * 
	 * @Description 线性回归
	 * @para @param insTrain
	 * @para @param classIndex
	 * @para @throws Exception
	 * @return AbstractClassifier
	 */
	public static AbstractClassifier trainModel(Instances insTrain, int classIndex) throws Exception {

		insTrain.setClassIndex(classIndex);

		LinearRegression linear = new LinearRegression();
		linear.buildClassifier(insTrain);
		return linear;
	}

	/**
	 * 
	 * @Description 设置wekaweka对象属性
	 * @return ArrayList<Attribute>
	 */
	public static ArrayList<Attribute> setAttribute(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
		for (int count = 0; count < fields.length; count++) {
			attributeList.add(new Attribute(fields[count].getName()));
		}

		return attributeList;
	}

	/**
	 * 根据属性名获取属性值 参考：http://blog.csdn.net/linshutao/article/details/7693625
	 */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Description 获取weka对象 只处理数值属性
	 * @para @param objList 对象传入的值
	 * @para @return
	 * @return Instances
	 */
	public static Instances getInstances(List<?> objList) {
		ArrayList<Attribute> attList = WekaUtil.setAttribute(objList.get(0));
		Field[] fields = objList.get(0).getClass().getDeclaredFields();
		String dataName = objList.get(0).getClass().getName();
		Instances ins = new Instances(dataName, attList, 0);
		for (Object obj : objList) {
			Instance inst = new DenseInstance(attList.size());
			for (int i = 0; i < attList.size(); i++) {
				inst.setValue(ins.attribute(i), (Double) WekaUtil.getFieldValueByName(fields[i].getName(), obj));
				WekaUtil.getFieldValueByName(fields[i].getName(), obj);
			}
			ins.add(inst);
		}
		return ins;
	}

}
