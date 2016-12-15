package com.yiniu.classify;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ClassResult implements Serializable {

	private static final long serialVersionUID = -5454824057721686468L;

	private Map<String, List<List<?>>> classResultMap;

	public ClassResult() {
		super();
	}

	public ClassResult(Map<String, List<List<?>>> classResultMap) {
		super();
		this.classResultMap = classResultMap;
	}

	public Map<String, List<List<?>>> getClassResultMap() {
		return classResultMap;
	}

	public void setClassResultMap(Map<String, List<List<?>>> classResultMap) {
		this.classResultMap = classResultMap;
	}

}
