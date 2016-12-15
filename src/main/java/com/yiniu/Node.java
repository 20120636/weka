package com.yiniu;

/**
 * @author Con Zhang
 * @date 2016年9月29日
 */
public class Node {

	private double numerialData;
	private String strData;
	private Node leftChild;
	private Node rightChild;

	public Node() {
		super();
	}


	public double getNumerialData() {
		return numerialData;
	}


	public void setNumerialData(double numerialData) {
		this.numerialData = numerialData;
	}


	public String getStrData() {
		return strData;
	}


	public void setStrData(String strData) {
		this.strData = strData;
	}


	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

}
