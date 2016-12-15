package com.yiniu.tree;

import java.io.Serializable;

/**
 * 节点
 * @author ConZhang
 *
 * 2016年11月21日
 */
public class Node implements Serializable{

	private static final long serialVersionUID = 8232524841056648424L;
	
	private Object data;
	
	private Node leftNode;
	
	private Node rightNode;

	
	public Node() {
		super();
	}

	
	public Node(Object data) {
		super();
		this.data = data;
		this.leftNode = null;
		this.rightNode = null;
	}


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Node getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public Node getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

}
