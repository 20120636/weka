package com.yiniu.bean;

import java.util.List;

public class Demo {

	public static void main(String[] args) {
		User u = new User(1, "2", 3);
		BiDataUilt<User> biDataUilt = new BiDataUilt<User>();
		//List<String> list = biDataUilt.getInstanceFieldsName(new User());
		List<String> list = biDataUilt.getInstanceFieldType(u);
		for(String l : list){
			System.out.println(l);
		}
	}
}
