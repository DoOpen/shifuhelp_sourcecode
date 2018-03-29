package com.project.webservice.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.bean.member.CouponBean;
import com.project.page.PageBean;
import com.project.service.interfaces.CouponServiceI;
import com.project.service.interfaces.MemberServiceI;
import com.project.utils.TimeUtils;
import com.project.webservice.BaseController;

@Controller
@RequestMapping("/couponInterfaces.api")
public class CouponInterfaces extends BaseController{
	@Autowired
	MemberServiceI memberService;

	@Autowired
	CouponServiceI couponService;
	
	
	/**
	 * 可领取优惠卷列表
	 * @param memberBean
	 * @param couponBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getReceiceCouponList", method = RequestMethod.POST)
	public void getReceiceCouponList(CouponBean couponBean,PageBean pageBean)throws Exception {
		WriteObject(couponService.getReceiceCouponList(couponBean,pageBean),pageBean.getTotal());
	}
	
	
	/**
	 * 用户领取优惠卷
	 * @param memberBean
	 * @param couponBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "memberReceiveCoupon", method = RequestMethod.POST)
	public void memberReceiveCoupon(CouponBean couponBean)throws Exception {
		CouponBean couponBean3=couponService.getCouponDetail(couponBean);
		if(couponBean3==null){
			WriteError("此优惠卷已不存在！");
			return;
		}
		CouponBean couponBean2=couponService.getCouponDetailByMemberCouponId(couponBean);
		if(couponBean2!=null){
			WriteError("已经领取过了");
			return;
		}
		int day=couponBean3.getValid_day();
		if(day<=0){
			WriteError("此优惠卷数据异常!");
			return;
		}
		String start_time=TimeUtils.getCurrentTime();
		String end_time=TimeUtils.getTimeDayAfter("yyyy-MM-dd HH:mm:ss", start_time, day);
		int num=couponService.memberReceiveCoupon(couponBean.setStart_time(start_time).setEnd_time(end_time));
		if(num>0){
			WriteMsg("领取成功");
		}else{
			WriteError("领取失败");
		}
	}
	
	/**
	 * 用户的优惠卷列表
	 * @param memberBean
	 * @param couponBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getCouponList", method = RequestMethod.POST)
	public void getCouponList(CouponBean couponBean,PageBean pageBean)throws Exception {
		WriteObject(couponService.getCouponList(couponBean,pageBean),pageBean.getTotal());
	}
	
	
	/**
	 * 用户的优惠卷列表 各个状态总数
	 * @param memberBean
	 * @param couponBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getCouponsCount", method = RequestMethod.POST)
	public void getCouponsCount(CouponBean couponBean)throws Exception {
		WriteObject(couponService.getCouponsCount(couponBean));
	}
}
