package com.yiniu.tree;

/**
 * 二叉树
 * @author ConZhang
 *
 * 2016年11月21日
 */
public class BinTree extends Tree{

	private Node root;
	
	
	public BinTree() {
		this.root = null;
	}

	
	public Node getRoot() {
		return root;
	}


	public void setRoot(Node root) {
		this.root = root;
	}


	@Override
	public void treeInit(Node node, Object data) {
	}

	@Override
	public void preTreeInit(Node node, Object data) {
	}

	@Override
	public void midTreeInit(Node node, Object data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postTreeInit(Node node, Object data) {
		// TODO Auto-generated method stub
		
	}

}
