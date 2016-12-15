package com.yiniu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netlib.util.doubleW;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.matrix.Matrix;

/**
 * @author Con Zhang
 * @date 2016年9月18日
 */
public class MathUtil {

	/**
	 * 
	 * @Description 冒泡排序
	 * @para @param numbers
	 * @return int[]
	 */
	public static double[] bubbleSort(double[] numbers) {
		double temp; // 记录临时中间值
		int size = numbers.length; // 数组大小
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				if (numbers[i] < numbers[j]) { // 交换两数的位置
					temp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = temp;
				}
			}
		}
		return numbers;
	}

	/**
	 * 
	 * @Description 冒泡排序 并把数据的原始位置也进行存储
	 */
	public static double[][] bubbleSort2(double[] numbers) {
		double[][] data = new double[2][numbers.length];
		double temp;
		double index;
		// 记录临时中间值
		double[] indexs = new double[numbers.length];
		for(int i=0; i<numbers.length; i++){
			indexs[i] = i;
		}
		
		int size = numbers.length; // 数组大小
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				if (numbers[i] < numbers[j]) { // 交换两数的位置
					temp = numbers[i];
					index = indexs[i];
					
					numbers[i] = numbers[j];
					indexs[i] = indexs[j];
					
					numbers[j] = temp;
					indexs[j] = index;
				}
			}
		}
		
		for(int i=0; i<numbers.length; i++){
			data[0][i] = indexs[i];
			data[1][i] = numbers[i];
		}
		
		return data;
	}
	
	
	/**
	 * @Description 最大值
	 * @para @param numbers
	 * @return float
	 */
	public static double maxValue(double[] numbers) {
		double temp = numbers[0];
		for(int i=0; i<numbers.length; i++){
			if(numbers[i]>temp){
				temp = numbers[i];
			}
		}
		return temp;
	}

	/**
	 * @Description 最小值
	 * @para @param numbers
	 * @return float
	 */
	public static double minValue(double[] numbers) {
		double temp = numbers[0];
		for(int i=0; i<numbers.length; i++){
			if(numbers[i]<temp){
				temp = numbers[i];
			}
		}
		return temp;
	}

	/**
	 * 
	 * @Description 对数字数据进行分组离散化处理
	 * @para @param numbers
	 * @para @param list
	 * @return float[]
	 */
	public static float[] discreteFilter(float[] numbers, List<Float[]> list) {
		int groupNum = list.size();
		List<String> groupList = new ArrayList<String>();
		for (int i = 0; i < numbers.length; i++) {
			int flag = 0;
			for (Float[] l : list) {
				if (numbers[i] > l[0] && numbers[i] <= l[1]) {
					numbers[i] = flag;
					break;
				}
				flag++;
			}
		}
		return numbers;
	}

	/**
	 * 
	 * @Description 把数字数组转化为字符串数组，便于分类算法的使用
	 * @para @param t
	 * @return String[]
	 */
	public static String[] floatToString(float[] t) {
		String s = Arrays.toString(t);
		s = s.substring(1, s.length() - 1);
		String[] str = s.split(",");
		return str;
	}

	public static String[] intToString(int[] t) {
		String s = Arrays.toString(t);
		s = s.substring(1, s.length() - 1);
		String[] str = s.split(",");
		System.out.println(str[0] + str[1]);
		return str;
	}

	/**
	 * 
	 * @Description 把int数组转化为float数组
	 * @para @param data
	 * @para @return
	 * @return Float[]
	 */
	public static float[] intToFloat(int[] data) {
		float[] floatData = new float[data.length];
		for (int i = 0; i < data.length; i++) {
			floatData[i] = (float) data[i];
		}
		return floatData;
	}

	/**
	 * @Description 获取商
	 */
	public int getDiscuss(float dividend, float divisor) {
		return (int) (dividend / divisor);
	}

	/**
	 * 
	 * @Description 获取余数
	 */
	public int getemainder(int dividend, int divisor) {
		return dividend % divisor;
	}

	/**
	 * 
	 * @Description 数组求和
	 */
	public static double arrSum(double[] data) {
		double sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}

	/**
	 * 
	 * @Description 样本均值
	 */
	public static double arrAvg(double[] data) {
		return MathUtil.arrSum(data) / data.length;
	}

	/**
	 * 
	 * @Description 样本方差
	 */
	public static double arrSd(double[] data) {
		double sd = 0;
		double avg = MathUtil.arrAvg(data);
		for (int i = 0; i < data.length; i++) {
			sd += (data[i] - avg) * (data[i] - avg);
		}
		return sd / (data.length - 1);
	}

	/**
	 * 
	 * @Description 数据标准化处理
	 */
	public static double[][] dataStandardization(double[][] data) {
		for (int i = 0; i < data.length; i++) {
			double avg = MathUtil.arrAvg(data[i]);
			double sd = MathUtil.arrSd(data[i]);
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = (data[i][j] - avg) / Math.sqrt(sd);
			}
		}
		return data;
	}
	
	public static double[][] minMaxStandardization(double[][] data){
		for (int i = 0; i < data.length; i++) {
			double maxValue = MathUtil.maxValue(data[i]);
			double minValue = MathUtil.minValue(data[i]);
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = (data[i][j] - minValue) / (maxValue-minValue);
			}
		}
		return data;
	}
	
	/**
	 * 
	 * @Description 数据标准化处理
	 */
	public static double[][] dataStandardization2(double[][] data) {
		for (int i = 0; i < data.length; i++) {
			double avg = MathUtil.arrAvg(data[i]);
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = (data[i][j] - avg);
			}
		}
		return data;
	}
	
	

	/**
	 * 
	 * @Description 返回一个相关系数二维数组
	 */
	public static double[][] correlationArrMatrix(double[][] data) {
		double[][] correlateArr = new double[data[0].length][data[0].length];
		for (int i = 0; i < data[0].length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				double temp = 0;

				for (int k = 0; k < data.length; k++) {
					temp += (data[k][i] * data[k][j]);
				}
				correlateArr[i][j] = temp / (data.length - 1);
			}

		}
		return correlateArr;
	}
	
	
	/**
	 * 
	 * @Description 左右翻转数组
	 * @para @param data
	 * @return double[][]
	 */
	public static double[][] retroflexion(double[][] data){
		for(int i=0; i<data.length; i++){
			int left = 0;
			int right = data[0].length-1;
			double temp = 0;
			while(left<right){
				temp = data[i][left];
				data[i][left] = data[i][right];
				data[i][right] = temp;
				left++;
				right--;
			}
		}
		
		return data;
	}

	
	/**
	 * 
	 * @Description 按列求和
	 * @return double[]
	 */
	public static double[] sumCol(double[][] data){
		double[] result = new double[data[0].length];
		data = new Matrix(data).transpose().getArray();
		for(int i=0; i<data.length; i++){
			result[i] = MathUtil.arrSum(data[i]);
		}
		return result;
	}
	
	/**
	 * 
	 * @Description 按列求和
	 * @return double[]
	 */
	public static double[] sumRow(double[][] data){
		double[] result = new double[data.length];
		for(int i=0; i<data.length; i++){
			result[i] = MathUtil.arrSum(data[i]);
		}
		return result;
	}
	
	/**
	 * 
	 * @Description 对数组每个值乘以一个数
	 * @return double[]
	 */
	public static double[] times(double[] data, double num){
		for(int i=0; i<data.length; i++){
			data[i] = data[i]*num;
		}
		return data;
	}
	
	/**
	 * 
	 * @Description 计算高斯分布对应的概率
	 * @para @param avg
	 * @para @param sd
	 * @para @param num
	 * @return double
	 */
	public static double getGaussianDP(double avg, double sd, double num){
		return (1/(Math.sqrt(2*Math.PI)*sd)) * Math.exp(-(num-avg)*(num-avg)/(2*sd*sd));
	}
	
	/**
	 * 
	 * @Description 计算两个向量的欧式距离
	 * @para @param data0
	 * @para @param data1
	 * @return double
	 */
	public static double getEulerDistance(double[] dataX,double[] dataY){
	
		double temp = 0;
		for(int i=0; i<dataX.length;i++){
			temp += Math.pow(dataX[i]-dataY[i], 2);
		}
		return Math.sqrt(temp);
	}
	
	/**
	 * 
	 * @Description 基于欧拉距离的相似度计算，
	 * @para @param EulerDistance 欧拉距离，该距离是数据标准化过后计算的欧拉距离，所以EulerDistance应该是在0-1之间
	 * @para @return
	 * @return double
	 */
	public static double SimilarityByEulerDistance(double EulerDistance){
		return 1/EulerDistance;
	}
	
	/**
	 * 
	 * @Description 获取一组随机数
	 * @para @param num  随机数数量
	 * @return int[]
	 */
	public static int[] getRandomArr(int num, int range){
		int[] randomArr = new int[num];
		for(int i=0; i<num; i++){
			randomArr[i] = (int) (Math.random()*range);
		}
		return randomArr;
		
	}
	
	/**
	 * 获取最大值的位置
	 */
	public static int getMaxvalueValueIndex(double[] data){
		double temp = data[0];
		int index = 0;
		for(int i=0; i<data.length; i++){
			if(data[i]>temp){
				temp = data[i];
				index = i;
			}
		}
		return index;
	}
	
	
	public static boolean isExist(int num, int[] nums){
		for(int i=0; i<nums.length; i++){
			if(nums[i]==num){
				return true;
			}
		}
		return false;
		
	}
	
	public static void main(String[] args) {
		/*double[][] d = new double[][] { { 47,30,2 }, { 11,39,17 },{30,46,40},{24,36,0} ,{44,8,6},{38,20,10},{22,46,9},{0,45,30},
		{41,20,13},{22,44,9}};
		double[][] data = MathUtil.correlationArrMatrix(d);
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.println(data[i][j]);
			}
		}*/
		
	
		/*double[] x = new double[]{1,2,2342,34324,0.3,324};
		System.out.println(getMinvalueValueIndex(x));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("1", 1);
		map.put("2", 1);*/
		
		
		double[][] x1 = new double[][]{{0,0,110},{4,5,6}};
		double[][] x2 = new double[][]{{1,2,3},{4,5,6}};
		x2[1] = x1[0];
		System.out.println(x2[1][2]);
	
	}
}
