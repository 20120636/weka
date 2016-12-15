package com.yiniu;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * 属性/特征 类
 * 
 * @author ConZhang
 *
 *         2016年11月7日
 */
public class Attribute implements Serializable {

	private static final long serialVersionUID = -6512766139598228610L;

	/** 属性名称 */
	private String attributeName;

	/** 只定义两种数据类型:数值性和字符串 */
	public final static String ARFF_ATTRIBUTE_NUMERIC = "numeric";

	public final static String ARFF_ATTRIBUTE_STRING = "string";

	/** 属性类型 */
	private Type attributeType;

	public Attribute() {
		super();
	}

	public Attribute(String attributeName,Type attributeType) {
		super();
		this.attributeName = attributeName;
		this.attributeType = attributeType;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}


	public Type getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(Type attributeType) {
		this.attributeType = attributeType;
	}

}
