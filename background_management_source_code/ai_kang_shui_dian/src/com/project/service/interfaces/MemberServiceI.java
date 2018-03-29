package com.project.service.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import com.project.bean.member.BalanceHistoryBean;
import com.project.bean.member.IntegralBean;
import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberIntegralBean;
import com.project.bean.member.MemberMsgBean;
import com.project.bean.member.MemberReportedBean;
import com.project.bean.member.MessageBean;
import com.project.bean.member.SignBean;
import com.project.bean.member.WithdrawalBean;
import com.project.bean.member.WorkTypeBean;
import com.project.bean.pay.PingChargeBean;
import com.project.bean.others.CodeBean;
import com.project.bean.others.LocationBean;
import com.project.bean.others.AdviceBean;
import com.project.bean.system.SettingBean;
import com.project.bean.workOrder.PingHistoryBean;
import com.project.bean.workOrder.WorkOrderBean;
import com.project.dao.interfaces.MemberDaoI;
import com.project.dao.interfaces.SettingDaoI;
import com.project.page.PageBean;
import com.project.utils.EncodeUtils;
import com.project.utils.GaoDeUtils;
import com.project.utils.NumberUtils;
import com.project.utils.TimeUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceI {
	@Autowired
	MemberDaoI memberDao;
	@Autowired
	SettingDaoI settingDao;
	@Autowired
	SignServiceI signService;
	@Autowired
	OrderServiceI orderService;
	@Autowired
	WorkOrderServiceI workOrderService;

	/**
	 * 添加用户消息
	 * 
	 * @param messageBean
	 * @return
	 */
	public int insertMemberMsg(MemberMsgBean memberMsgBean) {
		return memberDao.insertMemberMsg(memberMsgBean);
	}

	/**
	 * 删除用户消息
	 * 
	 * @return
	 */
	public int deleteMemberMsg(MessageBean messageBase) {
		return memberDao.deleteMemberMsg(messageBase);
	}

	/**
	 * 用户消息
	 * 
	 * @param memberBean
	 * @return
	 */
	public List<MessageBean> getMemberMsgList(MessageBean messageBean, PageBean pageBean) {
		List<MessageBean> messageBeans = memberDao.getMemberMsgList(messageBean, pageBean);
//		if (messageBeans != null) {
//			for (MessageBean messageBean1 : messageBeans) {
//				if ("order".equals(messageBean1.getMsg_type())) {
//					OrderBean orderBean = orderService.getOrderDetail(
//							new OrderBean().setOrder_id(Integer.valueOf(messageBean1.getOrder_id())));
//					if (orderBean != null) {
//						LogisticsDetailBean logisticsDetailBean = orderService.getLastLogistics(
//								new LogisticsDetailBean().setLogistics_no(orderBean.getLogistics_no()));
//						if (logisticsDetailBean != null) {
//							messageBean1.setLogistics_state(logisticsDetailBean.getLogistics_state());
//						}
//						messageBean1.setLogistics_no(orderBean.getLogistics_no())
//								.setGoods_name(orderBean.getOrderGoodsBeans() == null ? ""
//										: orderBean.getOrderGoodsBeans().get(0).getGoods_name())
//								.setGoods_img(orderBean.getOrderGoodsBeans() == null ? ""
//										: orderBean.getOrderGoodsBeans().get(0).getGoods_img());
//					}
//
//				}
//			}
//		}
		return messageBeans;
	}

	/**
	 * 通过用户ID 获得用户信息
	 * 
	 * @param memberBean
	 * @return
	 */
	public MemberBean getMemberDetail(MemberBean memberBean) {
		MemberBean memberBean1 = memberDao.getMemberDetail(memberBean);
		if (memberBean1 != null) {
			SettingBean settingBean = settingDao.getSystemSettingDetail(new SettingBean().setSetting_name("nead_deposit"));
			memberBean1.setMember_good_rate(memberDao.getMemberGoodsRate(memberBean1))
					.setNead_deposit(settingBean == null ? "系统未设置" : settingBean.getSetting_value());
			SignBean signBean = signService
					.getSignToday(new SignBean().setCreate_time(TimeUtils.getCurrentTime("yyyy-MM-dd"))
							.setMember_id(memberBean.getMember_id()));
			if (signBean != null) {
				memberBean1.setIs_sign("1");
			} else {
				memberBean1.setIs_sign("0");
			}
		}
		return memberBean1;
	}

	/**
	 * 用户登录
	 * 
	 * @param memberBean
	 * @return
	 * @throws Exception
	 */
	public MemberBean memberLogin(MemberBean memberBean) {
		MemberBean memberBean1 = null;
		memberBean.setMember_password(EncodeUtils.MD5Encode(memberBean.getMember_password()));
		int num = memberDao.updateMemberToken(memberBean);
		if (num > 0) {
			memberBean1 = memberDao.memberLogin(memberBean);
			if (memberBean1 != null) {
				SettingBean settingBean = settingDao.getSystemSettingDetail(new SettingBean().setSetting_name("nead_deposit"));
				memberBean1.setMember_good_rate(memberDao.getMemberGoodsRate(memberBean1))
						.setNead_deposit(settingBean == null ? "系统未设置" : settingBean.getSetting_value());
				SignBean signBean = signService
						.getSignToday(new SignBean().setCreate_time(TimeUtils.getCurrentTime("yyyy-MM-dd"))
								.setMember_id(memberBean.getMember_id()));
				if (signBean != null) {
					memberBean1.setIs_sign("1");
				} else {
					memberBean1.setIs_sign("0");
				}
			}
		} else {
			return null;
		}
		return memberBean1;
	}

	/**
	 * 微信公众号登录
	 * 
	 * @return
	 */
	public MemberBean wxPubMemberLogin(MemberBean memberBean) {
		return memberDao.wxPubMemberLogin(memberBean);
	}

	/**
	 * 微信公众号 更新
	 * 
	 * @param memberBean
	 * @return
	 */
	public int wxPubMemberUpdate(MemberBean memberBean) {
		return memberDao.wxPubMemberUpdate(memberBean);
	}

	/**
	 * 微信公众号注册
	 * 
	 * @param memberBean
	 * @return
	 */
	public int wxPubMemberRegister(MemberBean memberBean) {
		return memberDao.wxPubMemberRegister(memberBean);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param memberBean
	 * @return
	 */
	public int updateMemberDetail(MemberBean memberBean) {
		MemberBean memberBean1 = memberDao.getMemberDetail(memberBean);
		if ("-1".equals(memberBean1.getMember_state())) {
			memberBean.setMember_state("0");
		}
		if (memberBean.getMember_service_detail() != null) {
			LocationBean locationBean = GaoDeUtils.addressToLocation(memberBean.getMember_service_province() + memberBean.getMember_service_city()
							+ memberBean.getMember_service_country() + memberBean.getMember_service_detail());
			if (locationBean.getLongitude() == null || locationBean.getLatitude() == null) {
				return 0;
			} else {
				memberBean.setMember_service_longitude(locationBean.getLongitude())
						.setMember_service_latitude(locationBean.getLatitude());
			}
		}
		memberBean.setMember_update_time(TimeUtils.getCurrentTime());
		return memberDao.updateMemberDetail(memberBean);
	}

	/**
	 * 用户注册
	 * 
	 * @return
	 * @throws Exception
	 */
	public int memberRegister(MemberBean memberBean, CodeBean codeBean) throws Exception {
		if(memberBean.getRecommend_phone()!=null) {
			MemberBean memberBean2=memberDao.getMemberDetail(new MemberBean().setMember_account(memberBean.getRecommend_phone()).setMember_type("1"));
			if(memberBean2==null) {
				throw new Exception("推荐人不存在");
			}else {
				int num=memberDao.updateMemberBalance(new MemberBean().setMember_id(memberBean2.getMember_id()).setMember_integral(memberBean2.getMember_integral()+200));
				if(num==0) {
					throw new Exception("修改用户积分出错");
				}
				num=memberDao.insertMemberIntegral(new MemberIntegralBean().setIntegral_type("register_recommend")
						.setIntegral_value(200)
						.setMember_id(memberBean2.getMember_id())
						.setState("add"));
				if(num==0) {
					throw new Exception("积分记录添加失败");
				}
			}
		}
		memberBean.setMember_password(EncodeUtils.MD5Encode(memberBean.getMember_password()))
		.setMember_create_time(TimeUtils.getCurrentTime()).setMember_update_time(TimeUtils.getCurrentTime())
		.setMember_type("1").setMember_state("-1").setMember_integral(100);
		int num = memberDao.memberRegister(memberBean);
		if (num==0) {
			throw new Exception("注册失败");
		}
		num=memberDao.insertMemberIntegral(new MemberIntegralBean().setIntegral_type("register_recommend")
				.setIntegral_value(100)
				.setMember_id(memberBean.getMember_id())
				.setState("add"));
		if(num==0) {
			throw new Exception("积分记录添加失败");
		}
		settingDao.deleteCodeByMobileAndCode(codeBean);
		MemberMsgBean memberMsgBean = new MemberMsgBean().setMsg_desc("欢迎您加入师傅上门服务平台的大家庭，祝您使用愉快！")
				.setMember_id(memberBean.getMember_id()).setMsg_type("system");
		this.insertMemberMsg(memberMsgBean);
		
		return num;
	}

	/**
	 * 忘记密码
	 * 
	 * @return
	 */
	public int memberForgetPassword(MemberBean memberBean, CodeBean codeBean) {
		try {
			memberBean.setMember_password(EncodeUtils.MD5Encode(memberBean.getMember_password()))
					.setMember_update_time(TimeUtils.getCurrentTime());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		int num = memberDao.memberForgetPassword(memberBean);
		if (num > 0) {
			settingDao.deleteCodeByMobileAndCode(codeBean);
			MemberMsgBean memberMsgBean = new MemberMsgBean().setMsg_desc("您的密码已修改，如非本人操作，请联系客服")
					.setMember_id(memberBean.getMember_id()).setMsg_type("system");
			this.insertMemberMsg(memberMsgBean);
		}
		return num;
	}

	/**
	 * 提交审核
	 * 
	 * @param memberBean
	 * @return
	 */
	public int submitAudit(MemberBean memberBean) {
		return memberDao.submitAudit(memberBean);
	}

	/**
	 * 添加用户积分详情
	 * 
	 * @param integralBean
	 * @return
	 */
	public int insertMemberIntegral(MemberIntegralBean memberIntegralBean) {
		if (Integer.valueOf(NumberUtils.KeepDecimal(0)) > 0) {
			memberIntegralBean.setState("add");
		} else {
			memberIntegralBean.setState("use");
		}
		return memberDao.insertMemberIntegral(memberIntegralBean);
	}

	/**
	 * 用户信息报备
	 * 
	 * @param memberReportedBean
	 * @return
	 * @throws Exception 
	 */
	public int insertReported(MemberReportedBean memberReportedBean) throws Exception {
		memberReportedBean.setIs_delete(0).setCreate_time(TimeUtils.getCurrentTime())
				.setUpdate_time(TimeUtils.getCurrentTime());
		int num=memberDao.insertReported(memberReportedBean);
		if(num==0) {
			throw new Exception("报备入库失败");
		}
		MemberBean memberBean=memberDao.getMemberDetail(new MemberBean().setMember_id(memberReportedBean.getMember_id()));
		memberDao.updateMemberBalance(new MemberBean().setMember_id(memberBean.getMember_id())
				.setMember_integral(NumberUtils.KeepDecimal(memberBean.getMember_integral()+100)));
		if(num==0) {
			throw new Exception("更新用户积分失败");
		}
		num=memberDao.insertMemberIntegral(new MemberIntegralBean().setIntegral_type("reported")
				.setIntegral_value(100)
				.setMember_id(memberBean.getMember_id())
				.setState("add"));
		if(num==0) {
			throw new Exception("积分记录添加失败");
		}
		return num;
	}

	/**
	 * 通过用户id获取报备信息列表
	 * 
	 * @param memberReportedBean
	 * @param pageBean
	 * @return
	 */
	public List<MemberReportedBean> getReportedList(MemberReportedBean memberReportedBean,PageBean pageBean) {
		return memberDao.getReportedList(memberReportedBean, pageBean);
	}

	/**
	 * 通过手机号获取备案信息
	 * 
	 * @param memberReportedBean
	 * @return
	 */
	public List<MemberReportedBean> getReportedByPhone(MemberReportedBean memberReportedBean) {
		return memberDao.getReportedByPhone(memberReportedBean);
	}

	/**
	 * 申请提现列表
	 * 
	 * @return
	 */
	public List<WithdrawalBean> getWithdrawalList(WithdrawalBean withdrawalBean, PageBean pageBean) {
		return memberDao.getWithdrawalList(withdrawalBean, pageBean);
	}

	/**
	 * 最近的一次申请提现
	 * 
	 * @return
	 */
	public WithdrawalBean getLastApplyCash(WithdrawalBean withdrawalBean) {
		return memberDao.getLastApplyCash(withdrawalBean);
	}

	/**
	 * 申请提现
	 * 
	 * @param memberWithdrawalsBean
	 * @return
	 * @throws Exception
	 */
	public int applyWithdrawal(WithdrawalBean withdrawalBean,String member_pay_password)throws Exception {
		MemberBean memberBean=memberDao.getMemberDetail(new MemberBean().setMember_id(withdrawalBean.getMember_id()));
		if(withdrawalBean.getWithdrawal_price()<100) {
			throw new Exception("提现金额必须大于100元");
		}
		if(withdrawalBean.getWithdrawal_price()>memberBean.getMember_extract_money()){
			throw new Exception("提现金额大于可提现金额");
		}
		if (memberBean.getMember_pay_password() == null || "".equals(memberBean.getMember_pay_password())) {
			throw new Exception("请先设置支付密码");
		}

		if (!memberBean.getMember_pay_password().equals(EncodeUtils.MD5Encode(member_pay_password))) {
			throw new Exception("支付密码错误");
		}
		if ("bank".equals(withdrawalBean.getWithdrawal_way())) {
			if (memberBean.getMember_bank_code() == null||"".equals(memberBean.getMember_bank_code())) {
				throw new Exception("未绑定银行卡");
			}
			withdrawalBean.setMember_id(memberBean.getMember_id()).setWithdrawal_way("bank")
			.setWithdrawal_no(memberBean.getMember_bank_code()).setBank_name(memberBean.getMember_bank_name())
			.setBank_open_mobile(memberBean.getMember_bank_phone())
			.setBank_user_name(memberBean.getMember_bank__user_name())
			.setBank_open_name(memberBean.getMember_bank_open_name());
		}else if ("alipay".equals(withdrawalBean.getWithdrawal_way())){
			if (memberBean.getMember_alipay() == null||"".equals(memberBean.getMember_alipay())) {
				throw new Exception( "未绑定支付宝");
			}
			withdrawalBean.setMember_id(memberBean.getMember_id()).setWithdrawal_way("alipay").setWithdrawal_no(memberBean.getMember_alipay());
		}else if ("we_chat".equals(withdrawalBean.getWithdrawal_way())){
			if (memberBean.getMember_we_chat() == null||"".equals(memberBean.getMember_we_chat())) {
				throw new Exception("未绑定微信");
			}
			withdrawalBean.setMember_id(memberBean.getMember_id()).setWithdrawal_way("we_chat")
			.setWithdrawal_no(memberBean.getMember_we_chat());
		}
		int num=memberDao.updateMemberBalance(new MemberBean().setMember_id(withdrawalBean.getMember_id()).setMember_extract_money(memberBean.getMember_extract_money()-withdrawalBean.getWithdrawal_price()));
		if(num==0) {
			throw new Exception("用户余额更新失败");
		}
		num=memberDao.insertBalanceHistory(new BalanceHistoryBean().setMember_id(withdrawalBean.getMember_id())
				.setPrice(withdrawalBean.getWithdrawal_price())
				.setType("use")
				.setTitle("余额提现"));
		if(num==0) {
			throw new Exception("余额使用记录添加失败");
		}
		return memberDao.applyWithdrawal(withdrawalBean);
	}

	/**
	 * 获取明星师傅列表
	 * 
	 * @param size
	 * @return
	 */
	public List<MemberBean> getStarMemberList(PageBean pageBean) {
		List<MemberBean> memberBeans = memberDao.getStarMemberList(pageBean);
		for (MemberBean memberBean : memberBeans) {
			memberBean.setMember_good_rate(memberDao.getMemberGoodsRate(memberBean));
		}
		return memberBeans;
	}

	/**
	 * 提交建议
	 * 
	 * @param adviceBean
	 * @return
	 */
	public int addAdvice(AdviceBean adviceBean) {
		adviceBean.setCreate_time(TimeUtils.getCurrentTime()).setUpdate_time(TimeUtils.getCurrentTime())
				.setIs_delete(0);
		return memberDao.addAdvice(adviceBean);
	}

	/**
	 * 获取报备信息详情
	 * 
	 * @param memberReportedBean
	 * @return
	 */
	public MemberReportedBean getReportedDetail(MemberReportedBean memberReportedBean) {
		return memberDao.getReportedDetail(memberReportedBean);
	}

	// 绑定手机号
	public int bindMobile(MemberBean memberBean, CodeBean codeBean) {
		MemberBean memberBean1 = memberDao.getMemberDetail(memberBean);
		if (memberBean1 != null) {
			return 0;
		} else {
			Integer num = memberDao.updateMemberDetail(memberBean.setMember_account(memberBean.getMember_phone()));
			if (num > 0) {
				settingDao.deleteCodeByMobileAndCode(codeBean);
				return num;
			}
			return num;
		}
	}

	/**
	 * 修改用户手机号
	 * 
	 * @param memberBean
	 * @param codeBean
	 * @return
	 */
	public int updateMobile(MemberBean memberBean, CodeBean codeBean) {
		Integer num = memberDao.updateMemberDetail(memberBean.setMember_account(memberBean.getMember_phone()));
		if (num > 0) {
			settingDao.deleteCodeByMobileAndCode(codeBean);
			MemberMsgBean memberMsgBean = new MemberMsgBean().setMsg_desc("您的手机号已重新绑定，如非本人操作，请联系客服")
					.setMember_id(memberBean.getMember_id()).setMsg_type("system");
			this.insertMemberMsg(memberMsgBean);
		}
		return num;
	}

	/**
	 * 获取用户积分获取记录
	 * 
	 * @param memberBean
	 * @return
	 */
	public List<IntegralBean> getIntegralGetRecord(MemberBean memberBean, PageBean pageBean) {
		return memberDao.getIntegralGetRecord(memberBean, pageBean);
	}

	/**
	 * 修改用户报备信息
	 * 
	 * @param memberReportedBean
	 * @return
	 */
	public int updateReported(MemberReportedBean memberReportedBean) {
		return memberDao.updateReported(memberReportedBean);
	}

	/**
	 * 绑定第三方账号
	 * 
	 * @param memberBean
	 * @param codeBean
	 * @return
	 */
	public int bindOtherNo(MemberBean memberBean, CodeBean codeBean) {
		Integer num = memberDao.updateMemberDetail(memberBean);
		if (num > 0) {
			settingDao.deleteCodeByMobileAndCode(codeBean);
			MemberMsgBean memberMsgBean = new MemberMsgBean().setMsg_desc("您的账号已绑定第三方账号，如非本人操作，请联系客服")
					.setMember_id(memberBean.getMember_id()).setMsg_type("system");
			this.insertMemberMsg(memberMsgBean);
		}
		return num;
	}

	/**
	 * 师傅注册押金缴纳成功
	 * 
	 * @param pingChargeBean
	 * @return
	 */
	public int payDepositSuccess(PingChargeBean pingChargeBean) {
		Float amount = NumberUtils.KeepDecimal(pingChargeBean.getData().getObject().getAmount() / 100f, 2);
		String body = pingChargeBean.getData().getObject().getBody();
		MemberBean memberBean = new MemberBean().setMember_id(Integer.valueOf(body)).setMember_deposit_money(amount);
		MemberMsgBean memberMsgBean = new MemberMsgBean().setMsg_desc("您的押金已经缴纳成功，可以开始服务了")
				.setMember_id(memberBean.getMember_id()).setMsg_type("system");
		this.insertMemberMsg(memberMsgBean);
		PingHistoryBean pingHistoryBean = new PingHistoryBean().setCreate_time(TimeUtils.getCurrentTime())
				.setMember_id(Integer.valueOf(body)).setOrder_no(pingChargeBean.getData().getObject().getOrder_no())
				.setPing_type("workerDeposit").setPrice(amount).setChannel(new Gson().toJson(pingChargeBean));
		memberDao.insertPingHistory(pingHistoryBean);
		return memberDao.updateMemberDetail(memberBean);
	}

	/**
	 * 删除用户报备信息
	 * 
	 * @param memberReportedBean
	 * @return
	 */
	public int deleteReported(MemberReportedBean memberReportedBean) {
		return memberDao.deleteReported(memberReportedBean);
	}

	/**
	 * 设置支付密码
	 * 
	 * @param memberBean
	 * @param codeBean
	 * @return
	 * @throws Exception
	 */
	public int setPayPassword(MemberBean memberBean, CodeBean codeBean) throws Exception {
		memberBean.setMember_pay_password(EncodeUtils.MD5Encode(memberBean.getMember_pay_password()));
		int num = memberDao.updateMemberDetail(memberBean);
		if (num > 0) {
			settingDao.deleteCodeByMobileAndCode(codeBean);
			MemberMsgBean memberMsgBean = new MemberMsgBean().setMsg_desc("您的支付密码已修改，请注意账号安全")
					.setMember_id(memberBean.getMember_id()).setMsg_type("system");
			this.insertMemberMsg(memberMsgBean);
		}
		return num;
	}

	/**
	 * 师傅工种列表
	 * 
	 * @param pageBean
	 * @return
	 */
	public List<WorkTypeBean> getWorkTypeList(PageBean pageBean) {
		return memberDao.getWorkTypeList(pageBean);
	}

	/**
	 * 工单预约押金缴纳
	 * 
	 * @param pingChargeBean
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public int payWorkOrderDepositSuccess(PingChargeBean pingChargeBean) throws NumberFormatException, Exception {
		Float amount = NumberUtils.KeepDecimal(pingChargeBean.getData().getObject().getAmount() / 100f, 2);
		String body = pingChargeBean.getData().getObject().getBody();
		PingHistoryBean pingHistoryBean = new PingHistoryBean().setCreate_time(TimeUtils.getCurrentTime())
				.setMember_id(Integer.valueOf(Integer.valueOf(body.split("#")[0]))).setOrder_no(pingChargeBean.getData().getObject().getOrder_no())
				.setPing_type("workOrderDeposit").setPrice(amount).setOrder_id(Integer.valueOf(body.split("#")[1]))
				.setChannel(new Gson().toJson(pingChargeBean));
		int num = memberDao.insertPingHistory(pingHistoryBean);
		if (num > 0) {
			WorkOrderBean workOrderBean = new WorkOrderBean().setOrder_id(Integer.valueOf(body.split("#")[1])).setDeposit_price(amount);
			workOrderService.updateOrderState(workOrderBean,
					new MemberBean().setMember_id(Integer.valueOf(body.split("#")[0])), "pay_over");
		}
		return num;
	}

	/**
	 * 工单结算支付成功
	 * 
	 * @param pingChargeBean
	 * @return
	 * @throws Exception 
	 */
	public int payWorkOrderCompleteSuccess(PingChargeBean pingChargeBean) throws Exception {
		Float amount = NumberUtils.KeepDecimal(pingChargeBean.getData().getObject().getAmount() / 100f, 2);
		String body = pingChargeBean.getData().getObject().getBody();
		PingHistoryBean pingHistoryBean = new PingHistoryBean().setCreate_time(TimeUtils.getCurrentTime())
				.setMember_id(Integer.valueOf(body.split("#")[0])).setOrder_no(pingChargeBean.getData().getObject().getOrder_no())
				.setPing_type("workOrderComplete").setPrice(amount).setOrder_id(Integer.valueOf(body.split("#")[1]))
				.setChannel(new Gson().toJson(pingChargeBean));
		int num = memberDao.insertPingHistory(pingHistoryBean);
		if (num > 0) {
			WorkOrderBean workOrderBean1=workOrderService.getWorkOrderDetail( new WorkOrderBean().setOrder_id(Integer.valueOf(body.split("#")[1])));
			WorkOrderBean workOrderBean = new WorkOrderBean().setOrder_id(Integer.valueOf(body.split("#")[1]))
					.setOrder_price(Float.valueOf(amount)+Float.valueOf(workOrderBean1.getDeposit_price()));
			workOrderService.updateOrderState(workOrderBean,
					new MemberBean().setMember_id(Integer.valueOf(body.split("#")[0])), "member_complete");
			MemberBean memberBean=memberDao.getMemberDetail(new MemberBean().setMember_id(workOrderBean1.getOrder_accept_id()));
			Float balance=Float.valueOf(memberBean.getMember_extract_money())+Float.valueOf(amount)+Float.valueOf(workOrderBean1.getDeposit_price());
			num=memberDao.updateMemberDetail(new MemberBean().setMember_extract_money(balance).setMember_id(workOrderBean1.getOrder_accept_id()));
			if(num==0) {
				throw new Exception("师傅余额更新失败");
			}
		}
		return num;
	}

	/**
	 * 验证用户token
	 * 
	 * @param memberBean
	 * @return
	 * @throws Exception
	 */
	public boolean validationToken(MemberBean memberBean) throws Exception {
		MemberBean memberBean2 = memberDao.validationToken(memberBean);
		if (memberBean2 != null) {
			if ("1".equals(memberBean2.getIs_disable())) {
				throw new Exception("您账号已被冻结!");
			}else {
				return true;
			}
		}else {
			return false;
		}
	}
	/**
	 * 添加余额使用记录
	 * @param balanceHistoryBean
	 */
	public int insertBalanceHistory(BalanceHistoryBean balanceHistoryBean) {
		return memberDao.insertBalanceHistory(balanceHistoryBean);
	}
	/**
	 * 修改用户余额
	 * @param setMember_extract_money
	 */
	public int updateMemberBalance(MemberBean memberBean) {
		return memberDao.updateMemberBalance(memberBean);
	}
}
