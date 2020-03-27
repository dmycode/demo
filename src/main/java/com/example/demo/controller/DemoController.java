
/**  
* @Title: DemoController.java
* @Package com.example.demo.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author dy
* @date 2020年3月27日
* @version V1.0  
*/

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DemoService;
import com.example.demo.vo.web.ServiceResult;

/**
    * @ClassName: DemoController
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author dy
    * @date 2020年3月27日
    */
@RestController
public class DemoController {

	@Autowired
	private DemoService demoService;

	@RequestMapping(value = "/judageScore", method = RequestMethod.GET)
	public ServiceResult judageScore(Integer score) {
		ServiceResult serviceResult = new ServiceResult(
		        ServiceResult.STATE_SUCCESS);

		try {
			String result = demoService.judageScore(score);
			serviceResult.setResult(result);
		} catch (Exception e) {
			serviceResult.setFail(e.getMessage());
		}
		return serviceResult;
	}
}
