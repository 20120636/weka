package com.yiniu.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BiDataUilt<T> {

	/**
	 * 把对象转换成list
	 * @param instance
	 * @return
	 */
	public List<Object> instanceToList(T instance){
		List<Object> list = new ArrayList<Object>();
		Field[] fields = instance.getClass().getDeclaredFields();
		for(Field f : fields){
			try {
				String firstLetter = f.getName().substring(0, 1).toUpperCase();
				String getter = "get" + firstLetter + f.getName().substring(1);
				Method method = instance.getClass().getMethod(getter, new Class[] {});
				Object value = method.invoke(instance, new Object[] {});
				list.add(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	/**
	 * 把对象转换成list<String>
	 * @param instance
	 * @return
	 */
	public List<String> instanceToStringList(T instance){
		List<String> list = new ArrayList<String>();
		Field[] fields = instance.getClass().getDeclaredFields();
		for(Field f : fields){
			try {
				String firstLetter = f.getName().substring(0, 1).toUpperCase();
				String getter = "get" + firstLetter + f.getName().substring(1);
				Method method = instance.getClass().getMethod(getter, new Class[] {});
				String value =  method.invoke(instance, new Object[] {}).toString();
				list.add(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 把list<Object>封装成list<list<?>>
	 * @param instances
	 * @return
	 */
	public List<List<Object>> instancesToArrayList(List<T> instances){
		List<List<Object>> list = new ArrayList<List<Object>>();
		for(T instance : instances){
			List<Object> iList = instanceToList(instance);
			list.add(iList);
		}
		return list;
	}
	
	
	/**
	 * 把list<Object>封装成list<list<String>>
	 * @param instances
	 * @return
	 */
	public List<List<String>> instancesToStringList(List<T> instances){
		List<List<String>> list = new ArrayList<List<String>>();
		for(T instance : instances){
			List<String> iList = instanceToStringList(instance);
			list.add(iList);
		}
		return list;
	}
	
	/**
	 * 获取实例的属性名称
	 * @param instance
	 * @return
	 */
	public List<String> getInstanceFieldsName(T instance){
		List<String> list = new ArrayList<String>();
		Field[] fields = instance.getClass().getDeclaredFields();
		for (Field f : fields) {
			list.add(f.getName());
		}
		return list;
	}
	
	
	/**
	 * 获取实例的属性类型名称
	 * @param instance
	 * @return
	 */
	public List<String> getInstanceFieldType(T instance){
		List<String> list = new ArrayList<String>();
		Field[] fields = instance.getClass().getDeclaredFields();
		for(Field f : fields){
			try {
				String firstLetter = f.getName().substring(0, 1).toUpperCase();
				String getter = "get" + firstLetter + f.getName().substring(1);
				Method method = instance.getClass().getMethod(getter, new Class[] {});
				String value =  method.invoke(instance, new Object[] {}).getClass().getName();
				list.add(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
