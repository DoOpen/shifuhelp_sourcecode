package com.project.dao.interfaces;

import java.util.List;

import com.project.bean.member.SignBean;
import com.project.bean.member.SignStatisticsBean;
import com.project.page.PageBean;

public interface SignDaoI {
	/**
	 * 当月签到列表
	 * @param signBean
	 * @return
	 */
	public List<SignBean> getMonthSignList(SignBean signBean);
	/**
	 * 签到列表
	 * @param signBean
	 * @return
	 */
	public List<SignBean> getSignList(SignBean signBean,PageBean pageBean);
	/**
	 * 用户签到
	 * @param signBean
	 * @return
	 */
	public int insertSign(SignBean signBean);
	/**
	 * 当月签到统计
	 * @param signBean
	 * @return
	 */
	public SignStatisticsBean getMonthSignStatistics(SignBean signBean);
	/**
	 * 当天的签到信息
	 * @param signBean
	 * @return
	 */
	public SignBean getSignToday(SignBean signBean);
}
