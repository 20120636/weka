package com.yiniu.bean;

import java.util.List;

/**
 * 
 * @author ConZhang
 *
 * 2016年11月22日
 */
public class BiInstances<T> {

	private List<T> instances;
	private List<String> fieldsName;
	private List<String> feildsType;

	public BiInstances() {
		super();
	}

	public BiInstances(List<T> instances) {
		this.instances = instances;
	}

	public List<T> getInstances() {
		return instances;
	}

	public void setInstances(List<T> instances) {
		this.instances = instances;
	}

	public List<String> getFieldsName() {
		return fieldsName;
	}

	public void setFieldsName(List<String> fieldsName) {
		this.fieldsName = fieldsName;
	}

	public List<String> getFeildsType() {
		return feildsType;
	}

	public void setFeildsType(List<String> feildsType) {
		this.feildsType = feildsType;
	}

	
}
