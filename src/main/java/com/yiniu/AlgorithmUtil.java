package com.yiniu;

import weka.core.matrix.EigenvalueDecomposition;
import weka.core.matrix.Matrix;

/**
 * 基于主成份分析的综合评分
 * 
 * @author Con Zhang
 * @date 2016年9月19日
 */
public class AlgorithmUtil {

	public static double[][] algorithm_PCA(double[][] data, double Cumulative) {
		Matrix matrix = new Matrix(data);
		// 获取相关系数矩阵
		Matrix crltCftMatrix = MatrixUtil.getCorrelationArrMatrix2(matrix);
		System.out.println(crltCftMatrix);
		// 获取矩阵的特征值和特征向量
		EigenvalueDecomposition edp = crltCftMatrix.eig();
		Matrix eigenvalueMatrix = edp.getD();
		eigenvalueMatrix.print(4, 2);
		Matrix eigenvectorMatrix = edp.getV();
		eigenvectorMatrix.print(4, 2);
		eigenvectorMatrix = new Matrix(MathUtil.retroflexion(eigenvectorMatrix.getArray()));
		// 把特征值矩阵转为数组
		double[] eigenvalues = new double[eigenvalueMatrix.getRowDimension()];
		for (int i = 0; i < eigenvalueMatrix.getRowDimension(); i++) {
			eigenvalues[i] = eigenvalueMatrix.get(i, i);
		}

		// 对特征值进行排序
		double[][] eigenvaluesArr = MathUtil.bubbleSort2(eigenvalues);

		// 特征值原始位置、特征值、特征值贡献率、累计贡献率
		double[][] contributionArr = getContributionRate(eigenvaluesArr);

		int varNum = getVarNumByContribution(Cumulative, contributionArr);

		int[] indexs = new int[varNum];
		double[] subeigenvaluesArr = new double[varNum];
		for (int k = 0; k < varNum; k++) {
			indexs[k] = (int) contributionArr[k][0];
			subeigenvaluesArr[k] = contributionArr[k][2];
		}

		Matrix subEigenvectorMatrix = eigenvectorMatrix.getMatrix(0, eigenvectorMatrix.getRowDimension() - 1, 0,
				varNum - 1);

		// 修改特征向量的正负号，使得每个特征向量的分量和为正
		double[][] subEigenvectorArr = subEigenvectorMatrix.transpose().getArray();
		for (int i = 0; i < subEigenvectorArr.length; i++) {
			if (MathUtil.arrSum(subEigenvectorArr[i]) < 0) {
				subEigenvectorArr[i] = MathUtil.times(subEigenvectorArr[i], -1);
			}
		}
		subEigenvectorMatrix = new Matrix(subEigenvectorArr).transpose();
		return compositScore(data, subEigenvectorMatrix, subeigenvaluesArr);
	}

	/**
	 * 
	 * @Description 综合得分
	 */
	public static double[][] compositScore(double[][] data, Matrix subEigenvectorMatrix, double[] subeigenvaluesArr) {
		Matrix data1 = MatrixUtil.dataStandardization(new Matrix(data));
		// Matrix data1 = new Matrix(data);
		// double[] score = new double[data.length];
		Matrix subeigenvaluesMatrix = new Matrix(subeigenvaluesArr, subeigenvaluesArr.length);
		/*
		 * for(int i=0; i<data.length; i++){ Matrix matrix = new
		 * Matrix(data[i],data[i].length); matrix =
		 * subEigenvectorMatrix.times(matrix); score[i] =
		 * matrix.times(subeigenvaluesMatrix.transpose()).get(0, 0); }
		 */
		Matrix df = data1.times(subEigenvectorMatrix);
		double[][] socre = df.times(subeigenvaluesMatrix).getArray();
		return socre;

	}

	public static double[][] getContributionRate(double[][] eigenvaluesArr) {
		double[][] data = new double[eigenvaluesArr[0].length][4];
		double sum = 0;
		for (int i = 0; i < eigenvaluesArr[0].length; i++) {
			sum += eigenvaluesArr[1][i];
		}

		// 定义累计贡献率 cltCtbRate
		double cltCtbRate = 0;
		for (int j = 0; j < eigenvaluesArr[0].length; j++) {
			data[j][0] = eigenvaluesArr[0][j];
			data[j][1] = eigenvaluesArr[1][j];
			data[j][2] = eigenvaluesArr[1][j] / sum;
			cltCtbRate += data[j][2];
			data[j][3] = cltCtbRate;
		}

		return data;
	}

	/**
	 * 
	 * @Description 根据累计贡献率求指标变量的个数
	 * @return Integer
	 */
	public static Integer getVarNumByContribution(double cumulative, double[][] contributionArr) {
		for (int i = 0; i < contributionArr.length; i++) {
			if (contributionArr[i][3] >= cumulative) {
				return i + 1;
			}
		}

		return contributionArr.length;
	}

	/**
	 * 
	 * @Description KMean聚类
	 * @para @param data 聚类数据
	 * @para @param clusterNum 类别个数
	 * @return List<Double>
	 * 
	 * 
	 */
	public static double[][] KMeanCluster2(double[][] data, Integer clusterNum, double convergence) {

		double jMeasureFlag = Float.NaN;
		// (1)标准化数据
		data = MathUtil.dataStandardization(data);

		// (2)从训练数据data中选取clusterNum数据作为聚点
		int[] randomIndexs = MathUtil.getRandomArr(clusterNum, data.length);
		double[][] clusterS = new Matrix(data).getMatrix(randomIndexs, 0, data[0].length).getArray();

		return clusterS;

	}

	/**
	 * 
	 * @Description KMean聚类
	 * @para @param data 训练数据 需要进行归一化处理 使其值在0-1之间
	 * @para @param clusterNum 聚类数量
	 * @para @param convergence 收敛阙值 0.5
	 * @para @param clusterS 初始化聚点 可以从data数据随机选取clusterNum数据作为聚点
	 * @para @param jMeasureFlag
	 *       http://cs229.stanford.edu/notes/cs229-notes7a.pdf 所有点到其聚点距离的平方和
	 * @para @return
	 * @return double[][] 聚点信息
	 * 
	 * 
	 * 存在的问题，由于新簇点是该簇的中心，所以可能利用新簇点进行重新聚类时存在某一簇为空，程序会抛出数组越界
	 */
	public static double[][] KMeanCluster(double[][] data, Integer clusterNum, double convergence, double[][] clusterS,
			double jMeasureFlag) {

		// 计算相似度，根据相似度进行聚类
		double[][] similarity = new double[data.length][clusterNum];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < clusterNum; j++) {
				similarity[i][j] = MathUtil.SimilarityByEulerDistance(MathUtil.getEulerDistance(data[i], clusterS[j]));
			}
		}

		int[] dataClusterFlag = new int[data.length];

		// 给数据标志类别
		for (int i = 0; i < data.length; i++) {
			dataClusterFlag[i] = MathUtil.getMaxvalueValueIndex(similarity[i]);
		}

		// 计算聚类效果,并选取新的簇点
		double[][] newClusterS = new double[clusterNum][];
		double[] jMeasures = new double[clusterNum];

		// 更新每个簇的聚点，并计算其该簇所有点到其聚点距离的平方和
		for (int i = 0; i < clusterNum; i++) {
			double[][] clusterData = getClusterData(i, data, dataClusterFlag);
			jMeasures[i] = getJMeasures(clusterS[i], clusterData);
			newClusterS[i] = getNewCluster(clusterData);
		}
		if (Math.abs((MathUtil.arrSum(jMeasures) - jMeasureFlag)) < convergence) {
			return clusterS;
		} else {
			// 继续训练
			jMeasureFlag = MathUtil.arrSum(jMeasures);
			return KMeanCluster(data, clusterNum, convergence, newClusterS, jMeasureFlag);
		}

	}

	/**
	 * 
	 * @Description 计算每个簇的所以点到簇点的距离和,计算聚类效果
	 *              http://cs229.stanford.edu/notes/cs229-notes7a.pdf
	 * @return double
	 */
	public static double getJMeasures(double cluster[], double[][] data) {
		double jMeasures = 0;
		for (int i = 0; i < data.length; i++) {
			jMeasures += MathUtil.getEulerDistance(cluster, data[i]);
		}
		return jMeasures;

	}

	/**
	 * 根据簇数据选取新的簇点，用于从新聚类
	 */
	public static double[] getNewCluster(double[][] data) {
		double[] newCluster = new double[data[0].length];
		double[][] data1 = new Matrix(data).transpose().getArray();
		for (int i = 0; i < data[0].length; i++) {
			newCluster[i] = MathUtil.arrAvg(data1[i]);
		}
		return newCluster;

	}

	/**
	 * 根据簇点获取该簇的数据
	 */
	public static double[][] getClusterData(int clusterIndex, double[][] data, int[] dataClusterFlag) {
		double[][] clusterData = new double[data.length][];

		int j = 0;
		for (int i = 0; i < dataClusterFlag.length; i++) {
			if (dataClusterFlag[i] == clusterIndex) {
				clusterData[j++] = data[i];
			}
		}
		double[][] newClusterData = new double[j][];
		for (int i = 0; i < j; i++) {
			newClusterData[i] = clusterData[i];
		}

		return newClusterData;
	}

	/**
	 * 簇点的选取:1、随机选取一个点作为。2、计算其他点到该点的距离，取距离最大的为第二簇点。3、计算其他点到两点的距离，选取最大距离对应的点，
	 * 依次递推直到选取k个簇点
	 */

	public static double[][] selectCluster(double[][] data, double[][] clusters, int k, int clustersFlag[]) {
		double[][] newCluster;
		int[] newClustersFlag;
		if (clusters != null && clusters.length == k ) {
			return clusters;
		} else {
			if (clusters == null) {
				newCluster = new double[1][];
				newClustersFlag = new int[1];
				int index = (int)(Math.random()*data.length);
				newClustersFlag[0] = index;
				newCluster[0] = data[index];
			} else {
				newCluster = new double[clusters.length + 1][];
				newClustersFlag = new int[clustersFlag.length+1];
				double eulerDis;
				double maxDis = 0;
				int indexCluter = 0;
				for (int i = 0; i < data.length; i++) {
					eulerDis = 0;
					for (int j = 0; j < clusters.length; j++) {
						eulerDis += MathUtil.getEulerDistance(data[i], clusters[j]);
					}
					if (eulerDis > maxDis && !MathUtil.isExist(i, clustersFlag)) {
						maxDis = eulerDis;
						indexCluter = i;
					}
				}
				for (int j = 0; j < clusters.length; j++){
					newCluster[j] = clusters[j];
					newClustersFlag[j] = clustersFlag[j];
				}
				newCluster[newCluster.length - 1] = data[indexCluter];
				newClustersFlag[clustersFlag.length-1] = indexCluter;
			}
			
			return selectCluster(data, newCluster, k, newClustersFlag);
		}

	}

}
