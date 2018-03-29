package com.project.dao.interfaces;

import java.util.List;

import com.project.bean.member.BalanceHistoryBean;
import com.project.bean.member.IntegralBean;
import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberIntegralBean;
import com.project.bean.member.MemberMsgBean;
import com.project.bean.member.MemberReportedBean;
import com.project.bean.member.MessageBean;
import com.project.bean.member.WithdrawalBean;
import com.project.bean.member.WorkTypeBean;
import com.project.bean.others.AdviceBean;
import com.project.bean.workOrder.PingHistoryBean;
import com.project.page.PageBean;

public interface MemberDaoI {
	/**
	 * 获取师傅好评率
	 * @param memberBean
	 * @return
	 */
	public String getMemberGoodsRate(MemberBean memberBean);
	
	/**
	 * 删除用户消息
	 * @return
	 */
	public int deleteMemberMsg(MessageBean messageBase);
	/**
	 * 用户消息
	 * @param memberBean
	 * @return
	 */
	public List<MessageBean> getMemberMsgList(MessageBean messageBean,PageBean pageBean);
	/**
	 * 申请提现列表
	 * @return
	 */
	public List<WithdrawalBean> getWithdrawalList(WithdrawalBean withdrawalBean,PageBean pageBean);
	/**
	 * 最近的一次申请提现
	 * @return
	 */
	public WithdrawalBean getLastApplyCash(WithdrawalBean withdrawalBean);
	/**
	 * 申请提现
	 * @return
	 */
	public int applyWithdrawal(WithdrawalBean withdrawalBean);
	/**
	 * 通过用户ID 获得用户信息
	 * 
	 * @param memberBean
	 * @return
	 */
	public MemberBean getMemberDetail(MemberBean memberBean) ;
	/**
	 * 用户登录需要更新token
	 * @param memberBean
	 * @return
	 */
	public int updateMemberToken(MemberBean memberBean);
	/**
	 * 用户登录
	 * @param memberBean
	 * @return
	 */
	public MemberBean memberLogin(MemberBean memberBean);
	/**
	 * 微信公众号登录
	 * @return
	 */
	public MemberBean wxPubMemberLogin(MemberBean memberBean);
	/**
	 * 微信公众号 更新
	 * @param memberBean
	 * @return
	 */
	public int wxPubMemberUpdate(MemberBean memberBean);
	/**
	 * 微信公众号注册
	 * @param memberBean
	 * @return
	 */
	public int wxPubMemberRegister(MemberBean memberBean);
	/**
	 * 修改用户信息
	 * @param memberBean
	 * @return
	 */
	public int updateMemberDetail(MemberBean memberBean);
	/**
	 * 用户注册
	 * @return
	 */
	public int memberRegister(MemberBean memberBean);
	/**
	 * 忘记密码
	 * @return
	 */
	public int memberForgetPassword(MemberBean memberBean);
	/**
	 * 提交审核
	 * @param memberBean
	 * @return
	 */
	public int submitAudit(MemberBean memberBean);
	/**
	 * 添加用户积分详情
	 * @param integralBean
	 * @return
	 */
	public int insertMemberIntegral(MemberIntegralBean memberIntegralBean);
	/**
	 * 用户信息报备
	 * @param memberReportedBean
	 * @return
	 */
	public int insertReported(MemberReportedBean memberReportedBean);
	/**
	 * 通过用户id获取报备信息列表
	 * @param memberReportedBean
	 * @param pageBean
	 * @return
	 */
	public List<MemberReportedBean> getReportedList(MemberReportedBean memberReportedBean, PageBean pageBean);
	/**
	 * 通过手机号获取报备信息
	 * @param memberReportedBean
	 * @return
	 */
	public List<MemberReportedBean> getReportedByPhone(MemberReportedBean memberReportedBean);
	/**
	 * 获取明星师傅列表
	 * @param size
	 * @return
	 */
	public List<MemberBean> getStarMemberList(PageBean pageBean);
	/**
	 * 提交意见反馈
	 * @param adviceBean
	 * @return
	 */
	public int addAdvice(AdviceBean adviceBean);
	/**
	 * 获取报备信息详情
	 * @param memberReportedBean
	 * @return
	 */
	public MemberReportedBean getReportedDetail(MemberReportedBean memberReportedBean);
	/**
	 * 获取用户积分获取记录
	 * @param memberBean
	 * @return
	 */
	public List<IntegralBean> getIntegralGetRecord(MemberBean memberBean,PageBean pageBean);
	/**
	 * 用户报备信息修改
	 * @param memberReportedBean
	 * @return
	 */
	public int updateReported(MemberReportedBean memberReportedBean);
	/**
	 * 删除用户报备信息
	 * @param memberReportedBean
	 * @return
	 */
	public int deleteReported(MemberReportedBean memberReportedBean);
	/**
	 * 添加用户消息
	 * @param messageBean
	 * @return
	 */
	public int insertMemberMsg(MemberMsgBean memberMsgBean);
	/**
	 * 工种列表
	 * @param pageBean
	 * @return
	 */
	public List<WorkTypeBean> getWorkTypeList(PageBean pageBean);

	public int insertPingHistory(PingHistoryBean pingHistoryBean);
	/**
	 * 用户token验证
	 * @param memberBean
	 * @return
	 */
	public MemberBean validationToken(MemberBean memberBean);
	/**
	 * 添加余额使用记录
	 * @param balanceHistoryBean
	 * @return
	 */
	public int insertBalanceHistory(BalanceHistoryBean balanceHistoryBean);
	/**
	 * 修改用户余额
	 * @param memberBean
	 * @return
	 */
	public int updateMemberBalance(MemberBean memberBean);
}
