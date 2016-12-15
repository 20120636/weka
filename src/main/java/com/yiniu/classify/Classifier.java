package com.yiniu.classify;

import com.yiniu.Instances;

public interface Classifier {

	/** 创建分类器 */
	public void buildClassifier(Instances instances) throws Exception;

	/** 调用分类器进行数据分类 */
	public ClassResult classifyInstance(Instances instances) throws Exception;

}
