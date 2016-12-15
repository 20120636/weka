package com.yiniu;

import weka.core.matrix.Matrix;

/**
 * @author Con Zhang
 * @date 2016年9月18日
 */
public class MatrixUtil {

	/**
	 * 
	 * @Description 获取矩阵每列的最大值
	 * @return double[]
	 */
	public static double[] getColMaxValue(Matrix matrix) {
		Matrix transMatrix = matrix.transpose();
		double[] maxValues = new double[transMatrix.getRowDimension()];
		double[][] arrMatrix = transMatrix.getArray();
		for (int i = 0; i < arrMatrix.length; i++) {
			maxValues[i] = MathUtil.maxValue(arrMatrix[i]);
		}
		return maxValues;
	}

	/**
	 * 
	 * @Description 获取矩阵每列的最小值
	 * @return double[]
	 */
	public static double[] getColMinValue(Matrix matrix) {
		Matrix transMatrix = matrix.transpose();
		double[] minValues = new double[transMatrix.getRowDimension()];
		double[][] arrMatrix = transMatrix.getArray();
		for (int i = 0; i < arrMatrix.length; i++) {
			minValues[i] = MathUtil.minValue(arrMatrix[i]);
		}
		return minValues;
	}

	/**
	 * 
	 * @Description 获取矩阵每行的最大值
	 * @return double[]
	 */
	public static double[] getRowMaxValue(Matrix matrix) {
		double[] maxValues = new double[matrix.getRowDimension()];
		double[][] arrMatrix = matrix.getArray();
		for (int i = 0; i < arrMatrix.length; i++) {
			maxValues[i] = MathUtil.maxValue(arrMatrix[i]);
		}
		return maxValues;
	}

	/**
	 * 
	 * @Description 获取矩阵每行的最小值
	 * @return double[]
	 */
	public static double[] getRowMinValue(Matrix matrix) {
		double[] minValues = new double[matrix.getRowDimension()];
		double[][] arrMatrix = matrix.getArray();
		for (int i = 0; i < arrMatrix.length; i++) {
			minValues[i] = MathUtil.minValue(arrMatrix[i]);
		}
		return minValues;
	}

	/**
	 * 
	 * @Description 获取矩阵每行的均值
	 * @return double[]
	 */
	public static double[] getRowAvgValue(Matrix matrix) {
		double[] minValues = new double[matrix.getRowDimension()];
		double[][] arrMatrix = matrix.getArray();
		for (int i = 0; i < arrMatrix.length; i++) {
			minValues[i] = MathUtil.minValue(arrMatrix[i]);
		}
		return minValues;
	}

	/**
	 * 
	 * @Description 矩阵按列标准化处理
	 * @return Matrix
	 */
	public static Matrix dataStandardization(Matrix matrix) {
		matrix = matrix.transpose();
		double[][] arrMatrix = matrix.getArray();
		return (new Matrix(MathUtil.dataStandardization(arrMatrix)).transpose());
	}

	/**
	 * 
	 * @Description 样本标准差
	 */
	public static double[] arrSd(Matrix matrix) {
		double[] sds = new double[matrix.getColumnDimension()];
		matrix = matrix.transpose();
		double[][] arrMatrix = matrix.getArray();
		for (int i = 0; i < matrix.getRowDimension(); i++) {
			sds[i] = MathUtil.arrSd(arrMatrix[i]);
		}
		return sds;
	}

	/**
	 * 
	 * @Description 矩阵按列标准化处理
	 * @return Matrix
	 */
	public static Matrix dataStandardization2(Matrix matrix) {
		matrix = matrix.transpose();
		double[][] arrMatrix = matrix.getArray();
		return (new Matrix(MathUtil.dataStandardization2(arrMatrix)).transpose());
	}

	/**
	 * 
	 * @Description 相关系数矩阵 存在问题
	 */
	public static Matrix getCorrelationArrMatrix(Matrix matrix) {
		double[][] arrMatrix = matrix.getArray();
		arrMatrix = MathUtil.correlationArrMatrix(arrMatrix);
		return new Matrix(arrMatrix);
	}

	/**
	 * 
	 * @Description 相关系数矩阵
	 */
	public static Matrix getCorrelationArrMatrix2(Matrix matrix) {
		// 获取每个维度的方差
		double[] sds = MatrixUtil.arrSd(matrix);
		matrix = MatrixUtil.dataStandardization2(matrix);
		Matrix matrix2 = matrix.transpose();
		matrix = matrix2.times(matrix).times(1.0 / (matrix.getRowDimension() - 1));
		double[][] arrMatrix = matrix.getArray();
		for (int i = 0; i < arrMatrix.length; i++) {
			for (int j = 0; j < arrMatrix[0].length; j++) {
				arrMatrix[i][j] = arrMatrix[i][j] / (Math.sqrt(sds[i]) * Math.sqrt(sds[j]));
			}
		}
		return new Matrix(arrMatrix);
	}

	public static void main(String[] args) {

		double[][] d = new double[][] { { 5.96, 310, 461, 1557, 931, 319, 44.36, 2615, 2.20, 13631 },
				{ 3.39, 234, 308, 1035, 498, 161, 35.02, 3052, 0.90, 12665 },
				{ 2.35, 157, 229, 713, 295, 109, 38.40, 3031, 0.86, 9385 },
				{ 1.35, 81, 111, 364, 150, 58, 30.45, 2699, 1.22, 7881 },
				{ 1.50, 88, 128, 421, 144, 58, 34.30, 2808, 0.54, 7733 },
				{ 1.67, 86, 120, 370, 153, 58, 33.53, 2215, 0.76, 7480 },
				{ 1.17, 63, 93, 296, 117, 44, 35.22, 2528, 0.58, 8570 },
				{ 1.05, 67, 92, 297, 115, 43, 32.89, 2835, 0.66, 7262 },
				{ 0.95, 64, 94, 287, 102, 39, 31.54, 3008, 0.39, 7786 },
				{ 0.69, 39, 71, 205, 61, 24, 34.50, 2988, 0.37, 11355 },
				{ 0.56, 40, 57, 177, 61, 23, 32.62, 3149, 0.55, 7693 },
				{ 0.57, 58, 64, 181, 57, 22, 32.95, 3202, 0.28, 6805 },
				{ 0.71, 42, 62, 190, 66, 26, 28.13, 2657, 0.73, 7282 },
				{ 0.74, 42, 61, 194, 61, 24, 33.06, 2618, 0.47, 6477 },
				{ 0.86, 42, 71, 204, 66, 26, 29.94, 2363, 0.25, 7704 },
				{ 1.29, 47, 73, 265, 114, 46, 25.93, 2060, 0.37, 5719 },
				{ 1.04, 53, 71, 218, 63, 26, 29.01, 2099, 0.29, 7106 },
				{ 0.85, 53, 65, 218, 76, 30, 25.63, 2555, 0.43, 5580 },
				{ 0.81, 43, 66, 188, 61, 23, 29.82, 2313, 0.31, 5704 },
				{ 0.59, 35, 47, 146, 46, 20, 32.83, 2488, 0.33, 5628 },
				{ 0.66, 36, 40, 130, 44, 19, 28.55, 1974, 0.48, 9106 },
				{ 0.77, 43, 63, 194, 67, 23, 28.81, 2515, 0.34, 4085 },
				{ 0.70, 33, 51, 165, 47, 18, 27.34, 2344, 0.28, 7928 },
				{ 0.84, 43, 48, 171, 65, 29, 27.65, 2032, 0.32, 5581 },
				{ 1.69, 26, 45, 137, 75, 33, 12.10, 810, 1.00, 14199 },
				{ 0.55, 32, 46, 130, 44, 17, 28.41, 2341, 0.30, 5714 },
				{ 0.60, 28, 43, 129, 39, 17, 31.93, 2146, 0.24, 5139 },
				{ 1.39, 48, 62, 208, 77, 34, 22.70, 1500, 0.42, 5377 },
				{ 0.64, 23, 32, 93, 37, 16, 28.12, 1469, 0.34, 5415 },
				{ 1.48, 38, 46, 151, 63, 30, 17.87, 1024, 0.38, 7368 } };
		

	/*	double[][] score = AlgorithmUtil.algorithm_PCA(d, 0.98);
		for (int i = 0; i < score.length; i++) {
			System.out.println(score[i][0]);
		}*/

		/*double[][] d = new double[][]{{1,2,3},{4,0,-1},{1,3,9}};
		MatrixUtil.getCorrelationArrMatrix(MatrixUtil.dataStandardization(new Matrix(d))).eig().getD().print(4, 2);*/
				
		//聚类分析
		//d = new Matrix(MathUtil.minMaxStandardization(new Matrix(d).transpose().getArray())).transpose().getArray();
		new Matrix(d).print(4, 2);
		double[][] clusterS = AlgorithmUtil.selectCluster(d, null, 5, null);
		new Matrix(clusterS).print(4, 2);
		double[][] clusterSs = AlgorithmUtil.KMeanCluster(d, 5, 0.000000005, clusterS, 0);
		new Matrix(clusterSs).print(4, 2);
		
	}
}
