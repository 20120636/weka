package com.yiniu.tree;

/**
 * 
 * @author ConZhang
 *
 * 2016年11月21日
 */
public class TreeUlit {

	/**
	 * 先序遍历
	 * @param node
	 */
	public static void preOrder(Node node){
		if(node != null){
			System.out.println(node.getData());
			preOrder(node.getLeftNode());
			preOrder(node.getRightNode());
		}
	}
	
	/**
	 * 中序遍历
	 * @param node
	 */
	public static void midOrder(Node node){
		if(node != null){
			midOrder(node.getLeftNode());
			System.out.println(node.getData());
			midOrder(node.getRightNode());
		}
	}
	
	
	/**
	 * 后续遍历
	 * @param node
	 */
	public static void postOrder(Node node){
		if(node != null){
			postOrder(node.getLeftNode());
			postOrder(node.getRightNode());
			System.out.println(node.getData());
		}
	}
}
