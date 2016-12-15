package com.yiniu.classify;

import java.util.ArrayList;
import java.util.List;

import com.yiniu.Instance;
import com.yiniu.Instances;

public class NBTest {

	private String outLook;
	private String temperature;
	private String humidity;
	private String wind;
	private String playTennis;

	public NBTest(String outLook, String temperature, String humidity, String wind, String playTennis) {
		super();
		this.outLook = outLook;
		this.temperature = temperature;
		this.humidity = humidity;
		this.wind = wind;
		this.playTennis = playTennis;
	}

	public String getOutLook() {
		return outLook;
	}

	public void setOutLook(String outLook) {
		this.outLook = outLook;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getPlayTennis() {
		return playTennis;
	}

	public void setPlayTennis(String playTennis) {
		this.playTennis = playTennis;
	}

	public static void main(String[] args) throws Exception {
		NBTest n1 = new NBTest("Sunny", "Hot", "High", "Weak", "No");
		NBTest n2 = new NBTest("Sunny", "Hot", "High", "Strong", "No");
		NBTest n3 = new NBTest("Overcast", "Hot", "High", "Weak", "Yes");
		NBTest n4 = new NBTest("Rain", "Mild", "High", "Weak", "Yes");
		NBTest n5 = new NBTest("Rain", "Cool", "Normal", "Weak", "Yes");
		NBTest n6 = new NBTest("Rain", "Cool", "Normal", "Strong", "No");
		NBTest n7 = new NBTest("Overcast", "Cool", "Normal", "Strong", "Yes");
		NBTest n8 = new NBTest("Sunny", "Mild", "High", "Weak", "No");
		NBTest n9 = new NBTest("Sunny", "Cool", "Normal", "Weak", "Yes");
		NBTest n10 = new NBTest("Rain", "Mild", "Normal", "Weak", "Yes");
		
		List<NBTest> list = new ArrayList<NBTest>();
		list.add(n1);
		list.add(n2);
		list.add(n3);
		list.add(n4);
		list.add(n5);
		list.add(n6);
		list.add(n7);
		list.add(n8);
		list.add(n9);
		list.add(n10);
		Instances instances = Instances.initInstances(list, null);
		NBClassifier bncf = new NBClassifier();
		bncf.buildClassifier(instances);
		System.out.println(bncf.getClassFrequencyMap()+"--"+bncf.getFeatureFrequencyMap());
		NBTest t1 = new NBTest("Sunny", "Mild", "Normal", "Strong", "Yes");
		NBTest t2 = new NBTest("Overcast", "Mild", "High", "Strong", "Yes");
		NBTest t3 = new NBTest("Overcast", "Hot", "Normal", "Weak", "Yes");
		NBTest t4 = new NBTest("Rain", "Mild ", "High", "Strong", "No");
		
		List<NBTest> testList = new ArrayList<NBTest>();
		testList.add(t1);
		testList.add(t2);
		testList.add(t3);
		testList.add(t4);
		Instances instancesTest = Instances.initInstances(testList, null);
		ClassResult classResult = bncf.classifyInstance(instancesTest);
		System.out.println(classResult.getClassResultMap());
	}
}
