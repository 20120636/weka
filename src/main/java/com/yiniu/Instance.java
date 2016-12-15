package com.yiniu;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 对象类
 * 
 * @author ConZhang
 *
 *         2016年11月7日
 */
public class Instance implements Serializable {

	private static final long serialVersionUID = -151482065680671045L;

	/** 属性 */
	private  List<Attribute> attributeS;

	/** 属性值 */
	private  List attributeValueS;
	
	/** 属性取值范围*/
	private List<List<?>> attributeRangeList;
	
	public Instance(List<Attribute> attributeS, List<?> attributeValueS) {
		super();
		this.attributeS = attributeS;
		this.attributeValueS = attributeValueS;
	}

	public List<Attribute> getAttributeS() {
		return attributeS;
	}

	public void setAttributeS(List<Attribute> attributeS) {
		this.attributeS = attributeS;
	}

	public List<?> getAttributeValueS() {
		return attributeValueS;

	}

	public void setAttributeValueS(List<?> attributeValueS) {
		this.attributeValueS = attributeValueS;
	}

	/** 获取属性的个数 */
	public Integer getAttributeNum() {
		return attributeS.size();
	}

	/** 根据属性序号获取属性对象 */
	public Attribute getAttributeByIndex(int index) {
		return attributeS.get(index);
	}

	/** 根据属性名称获取属性对象 */
	public Attribute getAttributeByName(String attributeName) {
		for (Attribute att : attributeS) {
			if (att.getAttributeName() == attributeName || att.getAttributeName().equals(attributeName)) {
				return att;
			}
		}
		return null;
	}

	/** 通过属性名称获取属性位置 */
	public static Integer getAttIndexByName(String attributeName,List<Attribute> attributeS) {
		Integer index = -1;
		for (Attribute att : attributeS) {
			index++;
			if (att.getAttributeName() == attributeName || att.getAttributeName().equals(attributeName)) {
				return index;
			}
		}
		return null;
	}

	/** 根据obj去实例化一个Instance */
	public static Instance initInstance(Object obj) {
		List attributeValueList =  new ArrayList();
		Field[] fields = obj.getClass().getDeclaredFields();
		List<Attribute> attributeList = new ArrayList<Attribute>();
		for (Field f : fields) {
			try {
				String firstLetter = f.getName().substring(0, 1).toUpperCase();
				String getter = "get" + firstLetter + f.getName().substring(1);
				Method method = obj.getClass().getMethod(getter, new Class[] {});
				Object value = method.invoke(obj, new Object[] {});
				attributeList.add(new Attribute(f.getName(), f.getType()));
				attributeValueList.add(value);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return new Instance(attributeList, attributeValueList);
	}
}
