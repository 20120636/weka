package com.yiniu.tree;

/**
 * 
 * @author ConZhang
 *
 * 2016年11月21日
 */
public abstract class Tree {
	
	/**
	 * 树的创建
	 * @param node
	 * @param data
	 */
	public abstract void treeInit(Node node,Object data);

	/**
	 * 先序遍历创建树
	 * @param root
	 * @param data
	 */
	public abstract void preTreeInit(Node node,Object data);
	
	/**
	 * 中序遍历创建树
	 * @param root
	 * @param data
	 */
	public abstract void midTreeInit(Node node,Object data);
	
	/**
	 * 后序遍历创建树
	 * @param root
	 * @param data
	 */
	public abstract void postTreeInit(Node node,Object data);
	
	/**
	 * 判断节点是否为叶子节点
	 * @param node
	 * @return
	 */
	public boolean isLeaf(Node node){
		return (node.getLeftNode() == null) && (node.getRightNode() == null);
	}
}
