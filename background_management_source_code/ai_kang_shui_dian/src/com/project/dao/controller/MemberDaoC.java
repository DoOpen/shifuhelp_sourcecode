package com.project.dao.controller;

import java.util.List;

import com.project.bean.member.CouponBean;
import com.project.bean.member.AddressBean;
import com.project.bean.member.BalanceHistoryBean;
import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberIntegralBean;
import com.project.bean.member.MemberMsgBean;
import com.project.bean.member.MemberReportedBean;
import com.project.bean.member.WithdrawalBean;
import com.project.bean.member.WorkTypeBean;
import com.project.page.PageBean;

public interface MemberDaoC {
	/**
	 * 添加钱包记录
	 * @param balanceHistoryBean
	 * @return
	 */
	int insertBalanceHistory(BalanceHistoryBean balanceHistoryBean);
	/**
	 * 用户列表
	 * @param memberBean
	 * @param pageBean
	 * @return
	 */
	public List<MemberBean> getMemberList(MemberBean memberBean,PageBean pageBean);
	/**
	 * 用户详情
	 * @param memberBean
	 * @return
	 */
	public MemberBean getMemberDetail(MemberBean memberBean);
	/**
	 * 删除用户
	 * @param memberBean
	 * @return
	 */
	public int deleteMember(MemberBean memberBean);
	/**
	 * 修改用户信息
	 * @param memberBean
	 * @return
	 */
	public int updateMember(MemberBean memberBean);
	/**
	 * 获取用户收货地址列表
	 * @param addressBean
	 * @return
	 */
	public List<AddressBean> getMemberAddressList(AddressBean addressBean);
	/**
	 * 优惠券列表
	 * @param couponBean
	 * @param pageBean
	 * @return
	 */
	public List<CouponBean> getCouponList(CouponBean couponBean, PageBean pageBean);
	/**
	 * 优惠券详情
	 * @param couponBean
	 * @return
	 */
	public CouponBean getCouponDetail(CouponBean couponBean);
	/**
	 * 删除优惠券
	 * @param couponBean
	 * @return
	 */
	public int deleteCoupon(CouponBean couponBean);
	/**
	 * 修改优惠券
	 * @param couponBean
	 * @return
	 */
	public int updateCoupon(CouponBean couponBean);
	/**
	 * 添加优惠券
	 * @param couponBean
	 * @return
	 */
	public int insertCoupon(CouponBean couponBean);
	 /**
	  * 单一用户分配优惠券
	  * @param couponBean
	  * @return
	  */
	public int allocationCoupon(CouponBean couponBean);
	/**
	 * 为所有用户分配优惠券
	 * @param couponBean
	 * @return
	 */
	public int allocationAllMemberCoupon(CouponBean couponBean);
	/**
	 * 判断用户是否拥有此优惠券
	 * @param setMember_id
	 * @return
	 */
	public CouponBean getCouponByCouponIdAndMemberId(CouponBean couponBean);
	/**
	 * 用户提现列表
	 * @param withdrawalBean
	 * @return
	 */
	public List<WithdrawalBean> getWithdrawalList(WithdrawalBean withdrawalBean,PageBean pageBean);
	/**
	 * 修改提现信息
	 * @param withdrawalBean
	 * @return
	 */
	public int updateWithdrawal(WithdrawalBean withdrawalBean);
	/**
	 * 提现信息详情
	 * @param withdrawalBean
	 * @return
	 */
	public WithdrawalBean getWithdrawalDetail(WithdrawalBean withdrawalBean);
	/**
	 * 添加用户消息
	 * @param memberMsgBean
	 * @return
	 */
	public int insertMemberMsg(MemberMsgBean memberMsgBean);
	/**
	 * 更新用户余额
	 * @param setCash_balance
	 * @return
	 */
	public int updateMemberBalance(MemberBean memberBean);
	/**
	 * 报备信息列表
	 * @param memberReportedBean
	 * @param pageBean
	 * @return
	 */
	List<MemberReportedBean> getReportedList(MemberReportedBean memberReportedBean, PageBean pageBean);
	/**
	 * 报备信息详情
	 * @param memberReportedBean
	 * @return
	 */
	MemberReportedBean getReportedDetail(MemberReportedBean memberReportedBean);
	/**
	 * 修改报备信息
	 * @param memberReportedBean
	 * @return
	 */
	int updateReported(MemberReportedBean memberReportedBean);
	/**
	 * 工种列表
	 * @param workTypeBean
	 * @param pageBean
	 * @return
	 */
	List<WorkTypeBean> getWorkTypeList(WorkTypeBean workTypeBean, PageBean pageBean);
	/**
	 * 工种详情
	 * @param workTypeBean
	 * @return
	 */
	WorkTypeBean getWorkTypeDetail(WorkTypeBean workTypeBean);
	/**
	 * 修改工种
	 * @param workTypeBean
	 * @return
	 */
	int updateWorkType(WorkTypeBean workTypeBean);
	/**
	 * 添加工种
	 * @param workTypeBean
	 * @return
	 */
	int insertWorkType(WorkTypeBean workTypeBean);
	/**
	 * 删除工种
	 * @param workTypeBean
	 * @return
	 */
	int deleteWorkType(WorkTypeBean workTypeBean);
	/**
	 * 导出提现excel
	 * @param withdrawalBean
	 * @return
	 */
	List<Object> exportWithdrawalExcel(WithdrawalBean withdrawalBean);
	/**
	 * 添加用户积分记录
	 * @param memberIntegralBean
	 * @return
	 */
	int insertMemberIntegral(MemberIntegralBean memberIntegralBean);
}
