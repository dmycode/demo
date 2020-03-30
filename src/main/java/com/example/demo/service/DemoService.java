
/**  
* @Title: DemoService.java
* @Package com.example.demo.service
* @Description: TODO(用一句话描述该文件做什么)
* @author dy
* @date 2020年3月27日
* @version V1.0  
*/

package com.example.demo.service;

/**
* @ClassName: DemoService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author dy
* @date 2020年3月27日
*/

public interface DemoService {

	/**
	 * 
	    * @Title: judageScore
	    * @Description:判断成绩
	    * @param 成绩
	    * @return 等级
	    * @throws Exception 
	    * String 返回类型
	    * @author dy
		* @date 2020年3月27日
	    * @throws
	 */
	String judageScore(Integer score) throws Exception;
}
