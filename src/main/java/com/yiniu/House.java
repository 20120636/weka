package com.yiniu;

import java.util.ArrayList;
import java.util.List;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.SparseInstance;

/**
 * @author Con Zhang
 * @date 2016年9月18日
 */
public class House {

	private Double houseSize;
	private Double lotSize;
	private Double bedrooms;
	private Double granite;
	private Double bathroom;
	private Double sellingPrice;

	public House() {
		super();
		// TODO Auto-generated constructor stub
	}

	public House(Double houseSize, Double lotSize, Double bedrooms, Double granite, Double bathroom,
			Double sellingPrice) {
		super();
		this.houseSize = houseSize;
		this.lotSize = lotSize;
		this.bedrooms = bedrooms;
		this.granite = granite;
		this.bathroom = bathroom;
		this.sellingPrice = sellingPrice;
	}

	public Double getHouseSize() {
		return houseSize;
	}

	public void setHouseSize(Double houseSize) {
		this.houseSize = houseSize;
	}

	public Double getLotSize() {
		return lotSize;
	}

	public void setLotSize(Double lotSize) {
		this.lotSize = lotSize;
	}

	public Double getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(Double bedrooms) {
		this.bedrooms = bedrooms;
	}

	public Double getGranite() {
		return granite;
	}

	public void setGranite(Double granite) {
		this.granite = granite;
	}

	public Double getBathroom() {
		return bathroom;
	}

	public void setBathroom(Double bathroom) {
		this.bathroom = bathroom;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public static void main(String[] args) throws Exception {

		List<House> list = new ArrayList<House>();
		list.add(new House(3529.0, 9191.0, 6.0, 0.0, 0.0, 205000.0));
		list.add(new House(3247.0, 10061.0, 5.0, 1.0, 1.0, 224900.0));
		list.add(new House(4032.0, 10150.0, 5.0, 0.0, 1.0, 197900.0));
		list.add(new House(2397.0, 14156.0, 4.0, 1.0, 0.0, 189900.0));
		list.add(new House(2200.0, 9600.0, 4.0, 0.0, 1.0, 195000.0));
		list.add(new House(3536.0, 19994.0, 6.0, 1.0, 1.0, 325000.0));
		list.add(new House(2983.0, 9365.0, 5.0, 0.0, 1.0, 230000.0));
		Instances insTrain = WekaUtil.getInstances(list);

		System.out.println(insTrain.toString());
		// 训练模型
		AbstractClassifier classifier = WekaUtil.trainModel(insTrain, 5);

		// 检验模型
		SparseInstance ins = new weka.core.SparseInstance(5);
		ins.setValue(0, 990.8);
		ins.setValue(1, 1080.8);
		ins.setValue(2, 3);
		ins.setValue(3, 0);
		ins.setValue(4, 1);
		double price = classifier.classifyInstance(ins);
		System.out.println("Price: " + price);
		for (double coef : ((LinearRegression) classifier).coefficients()) {
		    System.out.println(coef);
		}
		
	}

}
