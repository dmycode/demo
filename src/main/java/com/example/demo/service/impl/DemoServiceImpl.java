
/**  
* @Title: DemoServiceImpl.java
* @Package com.example.demo.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author dy
* @date 2020年3月27日
* @version V1.0  
*/

package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.demo.service.DemoService;

/**
* @ClassName: DemoServiceImpl
* @Description: TODO(这里用一句话描述这个类的作用)
* @author dy
* @date 2020年3月27日
*/
@Service
public class DemoServiceImpl implements DemoService {

	/**
	* @Title:judageScore
	* @Description:TODO(这里用一句话描述这个方法的作用)
	* @param score
	* @return
	* @throws Exception
	* @see com.example.demo.service.DemoService#judageScore(java.lang.Integer)
	*/

	@Override
	public String judageScore(Integer score) throws Exception {
		Assert.notNull(score, "成绩不能为空");
		String result = "成绩无效";
		if (score >= 90) {
			result = "A";
		} else if (score >= 80) {
			result = "B";
		} else if (score >= 60) {
			result = "C";
		} else if (score >= 0) {
			result = "D";
		}
		return result;
	}

}
