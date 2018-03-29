package com.project.service.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberIntegralBean;
import com.project.bean.member.SignBean;
import com.project.bean.member.SignStatisticsBean;
import com.project.bean.system.SettingBean;
import com.project.dao.interfaces.SignDaoI;
import com.project.page.PageBean;
import com.project.utils.NumberUtils;
import com.project.utils.TimeUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class SignServiceI {
	@Autowired
	SignDaoI signDao;
	
	@Autowired 
	MemberServiceI memberService;
	
	@Autowired
	SettingServiceI settingService;
	/**
	 * 当月签到列表
	 * @param signBean
	 * @return
	 */
	public List<SignBean> getMonthSignList(SignBean signBean){
		String time=TimeUtils.getCurrentTime("yyyy-MM");
		return signDao.getMonthSignList(signBean.setCreate_time(time));
	}
	/**
	 * 签到列表
	 * @param signBean
	 * @return
	 */
	public List<SignBean> getSignList(SignBean signBean,PageBean pageBean){
		return signDao.getSignList(signBean,pageBean);
	}
	/**
	 * 用户签到
	 * @param signBean
	 * @return
	 * @throws Exception 
	 */
	public int insertSign(SignBean signBean) throws Exception{
		String today_time=TimeUtils.getCurrentTime("yyyy-MM-dd");
		String yesterday_time=TimeUtils.getTimeDayAfter("yyyy-MM-dd", TimeUtils.getDateFromTime("yyyy-MM-dd", today_time), -1);
		SignBean signBean2=signDao.getSignToday(signBean.setCreate_time(today_time));
		if(signBean2!=null){
			throw new Exception("今日已签到");
		}
		SettingBean settingBean=settingService.getSystemSettingDetail(new SettingBean().setSetting_name("sign_integral"));
		SettingBean settingBean2=settingService.getSystemSettingDetail(new SettingBean().setSetting_name("continuity_sign_integral"));
		//签到的基础分	
		int base_integral=NumberUtils.Integer(settingBean.getSetting_value());
		List<SignBean> signBeans=signDao.getMonthSignList(signBean);
		//一个月内 连续签到次数
		int sign_continuity_count_month=0;
		//一个月内 总共签到次数
		int sign_total_count_month=0;
		//签到赠送积分
		int sign_integral=0;
		//当前月之前签到过
		if(signBeans!=null && signBeans.size()>0){
			SignBean signBean3=signBeans.get(signBeans.size()-1);
			//昨天签到了
			if((yesterday_time).equals(signBean3.getCreate_time())){
				sign_integral=base_integral+NumberUtils.Integer(settingBean2.getSetting_value());
				sign_continuity_count_month=signBean3.getSign_continuity_count_month()+1;
			}else{
				sign_integral=base_integral;
				sign_continuity_count_month=1;
			}
			sign_total_count_month=signBean3.getSign_total_count_month()+1;
		}else{
			//当前月从未签到过
			sign_continuity_count_month=1;
			sign_total_count_month=1;
			sign_integral=base_integral;
		}
		int num=signDao.insertSign(signBean
				.setCreate_time(today_time)
				.setSign_integral(sign_integral)
				.setSign_continuity_count_month(sign_continuity_count_month)
				.setSign_total_count_month(sign_total_count_month));
		if(num==0){
			throw new Exception("签到失败");
		}
		//积分累计最大值
		SettingBean settingBean3=settingService.getSystemSettingDetail(new SettingBean().setSetting_name("max_integral"));
		MemberBean memberBean=memberService.getMemberDetail(new MemberBean().setMember_id(signBean.getMember_id()));
		//是否更新用户信息
		int total_integral=memberBean.getMember_integral()+sign_integral;
		if(total_integral>NumberUtils.Integer(settingBean3.getSetting_value())) {
			total_integral=NumberUtils.Integer(settingBean3.getSetting_value());
		}
		num=memberService.insertMemberIntegral(new MemberIntegralBean()
				.setMember_id(memberBean.getMember_id())
				.setIntegral_type("sign")
				.setIntegral_value(signBean.getSign_integral())
				.setRelation_id(signBean.getSign_id()));
		if(num==0) {
			throw new Exception("积分记录入库失败");
		}
		num=memberService.updateMemberDetail(new MemberBean()
				.setMember_id(signBean.getMember_id())
				.setMember_integral(total_integral));
		if(num==0){
			throw new Exception("更新用户积分失败");
		}	
		return num;
	}
	
	/**
	 * 当月签到统计
	 * @param signBean
	 * @return
	 */
	public SignStatisticsBean getMonthSignStatistics(SignBean signBean){
		String time=TimeUtils.getCurrentTime("yyyy-MM");
		return signDao.getMonthSignStatistics(signBean.setCreate_time(time));
	}
	
	/**
	 * 当天的签到信息
	 * @param signBean
	 * @return
	 */
	public SignBean getSignToday(SignBean signBean){
		return signDao.getSignToday(signBean);
	}
}
