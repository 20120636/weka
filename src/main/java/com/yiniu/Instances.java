package com.yiniu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Instances implements Serializable {

	private static final long serialVersionUID = -2974291490578938923L;

	private List<Instance> instances;

	private List clazzFlagList = new ArrayList();

	
	private Instances(List<Instance> instances, List clazzFlagList) {
		super();
		this.instances = instances;
		this.clazzFlagList = clazzFlagList;
	}

	public List<Instance> getInstances() {
		return instances;
	}

	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}

	public List getClazzFlagList() {
		return clazzFlagList;
	}

	public void setClazzFlagList(List clazzFlagList) {
		this.clazzFlagList = clazzFlagList;
	}

	/** 把对象list封装成Instance列表 */
	public static Instances initInstances(List<?> list, List<?> clazzFlagList) {
		List<Instance> instances = new ArrayList<Instance>();
		for (Object obj : list) {
			instances.add(Instance.initInstance(obj));
		}
		
		return new Instances(instances, clazzFlagList);
	}

}
