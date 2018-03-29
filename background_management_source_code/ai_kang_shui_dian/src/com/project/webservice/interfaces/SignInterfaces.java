package com.project.webservice.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.member.SignBean;
import com.project.page.PageBean;
import com.project.service.interfaces.MemberServiceI;
import com.project.service.interfaces.SettingServiceI;
import com.project.service.interfaces.SignServiceI;
import com.project.webservice.BaseController;

/**
 * 签到系统
 * @author shenjiabo
 *
 */

@RestController
@RequestMapping("/signInterfaces.api")
public class SignInterfaces extends BaseController{
	
	@Autowired
	SignServiceI signService;
	
	@Autowired
	MemberServiceI memberService;
	
	@Autowired
	SettingServiceI settingService;
	/**
	 * 签到获取积分
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "insertSign", method = RequestMethod.POST)
	public void insertSign(SignBean signBean) throws Exception {	
		if(signService.insertSign(signBean)>0) {
			WriteObject(signBean.getSign_integral());
		}else {
			WriteError("签到失败");
		}
	}

	/**
	 * 获得今天签到信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getSignToday", method = RequestMethod.POST)
	public void getSignToday(SignBean signBean) throws Exception {		
		WriteObject(signService.getSignToday(signBean));
	}

	/**
	 * 当月签到统计
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getMonthSignStatistics", method = RequestMethod.POST)
	public void getMonthSignStatistics(SignBean signBean) throws Exception {
		WriteObject(signService.getMonthSignStatistics(signBean));
	}
	/**
	 * 当月签到列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getMonthSignList", method = RequestMethod.POST)
	public void getMonthSignList(SignBean signBean) throws Exception {
		WriteObject(signService.getMonthSignList(signBean));
	}
	
	/**
	 * 签到列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getSignList", method = RequestMethod.POST)
	public void getSignList(SignBean signBean,PageBean pageBean) throws Exception {			
		WriteObject(signService.getSignList(signBean,pageBean),pageBean.getTotal());
	}
}
