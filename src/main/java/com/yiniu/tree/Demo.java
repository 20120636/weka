package com.yiniu.tree;

import java.util.ArrayList;
import java.util.List;

public class Demo {

	public static void main(String[] args) {
		List<Object> elements = new ArrayList<Object>();
		elements.add(23);
		elements.add(43);
		elements.add("#");
		elements.add("#");
		elements.add(100);
		elements.add("#");
		elements.add("#");
		BinTree binTree = new BinTree();
		for(Object ob : elements){
			binTree.preTreeInit(binTree.getRoot(), ob);
		}
		
		TreeUlit.preOrder(binTree.getRoot());
		//reeUlit.midOrder(binTree.getRoot());
		//TreeUlit.postOrder(binTree.getRoot());
	}
}
