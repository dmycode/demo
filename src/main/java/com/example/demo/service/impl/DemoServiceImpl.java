
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

<<<<<<< HEAD
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
        Assert.notNull(score, "成绩不能为空"); // 校验输入的分数是否为空，如果为空抛出非法参数异常（IllegalArgumentException），提示信息："成绩不能为空"
        String result = "成绩无效";// 输入错误提示
        // （定义一个String的result的变量，结果赋值给result），返回结果提前初始化。
        if (score >= 90) {
            result = "A"; // 输入数据满足条件时，终止程序并输出结果
        } else if (score >= 80) {// 不满足条件时，继续向下运行程序
            result = "B"; // 以此类推
        } else if (score >= 60) {
            result = "C";
        } else if (score >= 0) {
            result = "D";
        }
        return result; // 终止程序。
    }
=======
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
>>>>>>> 80088b94c811e14e88a690b1856044424a2bc31b

}
