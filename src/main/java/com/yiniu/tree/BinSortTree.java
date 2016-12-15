package com.yiniu.tree;


/**
 * 
 * @author ConZhang
 *
 * 2016年11月21日
 */
public class BinSortTree extends Tree{
	
	private Node root;
	
	
	public BinSortTree() {
		root = null;
	}

	
	public Node getRoot() {
		return root;
	}


	public void setRoot(Node root) {
		this.root = root;
	}


	@Override
	public void treeInit(Node node,Object data) {
		//进行数据类型强转
		Double element = (Double) data;
 		if(root == null){
			root = new Node(element);
		}else {
			if((Double)node.getData() > element){
				if(node.getLeftNode() == null){
					node.setLeftNode(new Node(element));
				}else {
					treeInit(node.getLeftNode(), element);
				}
			}else {
				if(node.getRightNode() == null){
					node.setRightNode(new Node(element));
				}else {
					treeInit(node.getRightNode(), element);
				}
			}
		}
	}

	
	@Override
	public void preTreeInit(Node node, Object data) {
		
	}

	@Override
	public void midTreeInit(Node node, Object data) {
		
	}

	@Override
	public void postTreeInit(Node node, Object data) {
		
	}
	

}
