package com.yiniu.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ConZhang
 *
 * 2016年11月22日
 */
public class BiInstance<T> {

	private T instance;
	@SuppressWarnings("unused")
	private List<String> fieldsName;
	@SuppressWarnings("unused")
	private List<String> fieldType;

	public BiInstance() {
		super();
	}

	public BiInstance(T instance) {
		super();
		this.instance = instance;
	}

	public T getInstance() {
		return instance;
	}

	public void setInstance(T instance) {
		this.instance = instance;
	}

	public List<String> getFieldsName() {
		List<String> list = new ArrayList<String>();
		Field[] fields = instance.getClass().getDeclaredFields();
		for (Field f : fields) {
			list.add(f.getName());
		}
		return list;
	}

	public List<String> getFieldType() {
		List<String> list = new ArrayList<String>();
		Field[] fields = instance.getClass().getDeclaredFields();
		for (Field f : fields) {
			list.add(f.getClass().getName());
		}
		return list;
	}

}
