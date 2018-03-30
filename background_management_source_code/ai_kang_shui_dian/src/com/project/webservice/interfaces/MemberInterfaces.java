package com.project.webservice.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;

import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberReportedBean;
import com.project.bean.member.MessageBean;
import com.project.bean.member.WithdrawalBean;
import com.project.bean.others.PingSettingBean;
import com.project.bean.others.CodeBean;
import com.project.bean.others.HtmlStyleBean;
import com.project.bean.others.AdviceBean;
import com.project.bean.system.SettingBean;
import com.project.bean.workOrder.WorkOrderBean;
import com.project.bean.wx.WXPubBean;
import com.project.bean.wx.WXSettingBean;
import com.project.others.NotToken;
import com.project.page.PageBean;
import com.project.service.interfaces.MemberServiceI;
import com.project.service.interfaces.SettingServiceI;
import com.project.service.interfaces.WorkOrderServiceI;
import com.project.utils.EncodeUtils;
import com.project.utils.NumberUtils;
import com.project.utils.TimeUtils;
import com.project.utils.UUIDUtils;
import com.project.utils.WXUtils;
import com.project.webservice.BaseController;

@Controller
@RequestMapping("/memberInterfaces.api")
public class MemberInterfaces extends BaseController {
	@Autowired
	MemberServiceI memberService;
	@Autowired
	SettingServiceI settingService;
	@Autowired
	WorkOrderServiceI workOrderService;
	/**
	 * 工种列表
	 * @param pageBean
	 */
	@RequestMapping(params="getWorkTypeList")
	public void getWorkTypeList(PageBean pageBean) {
		WriteObject(memberService.getWorkTypeList(pageBean),pageBean.getTotal());
	}

	/**
	 * 获取积分获取记录
	 * 
	 * @param pageBean
	 * @param memberReportedBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getIntegralGetRecord", method = RequestMethod.POST)
	public void getIntegralGetRecord(MemberBean memberBean, String state, PageBean pageBean) throws Exception {
		memberBean.setMember_state(state);
		WriteObject(memberService.getIntegralGetRecord(memberBean, pageBean), pageBean.getTotal());
	}

	/**
	 * 师傅缴纳押金
	 * 
	 * @param memberBean
	 * @param messageBean
	 * @param pageBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "payDeposit", method = RequestMethod.POST)
	public void payDeposit(MemberBean memberBean,String channel) throws Exception {
		MemberBean memberBean2=memberService.getMemberDetail(memberBean);
		PingSettingBean pingSettingBean = settingService.getPingSetting();
		Pingpp.apiKey = pingSettingBean.getApp_key();
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		String order_no = new UUIDUtils(1).nextId(false, 1) + "";
		String order_pay_no = order_no.substring(0, order_no.length() - 1) + TimeUtils.getCurrentTime("HHmmss");
		chargeParams.put("order_no", order_pay_no);
		chargeParams.put("amount",NumberUtils.KeepDecimal(settingService.getSystemSettingDetail(new SettingBean().setSetting_name("nead_deposit")).getSetting_value(), 2)*100);
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", pingSettingBean.getApp_id());
		chargeParams.put("app", app);
		chargeParams.put("channel", channel);
		chargeParams.put("currency", "cny");
		chargeParams.put("client_ip", "127.0.0.1");
		chargeParams.put("subject", "师傅缴纳押金");
		String body = String.valueOf(memberBean.getMember_id());
		chargeParams.put("body", body.substring(0, body.length() > 20 ? 20 : body.length()));

		if (channel.equals("wx_pub")) {
			Map<String, Object> extra = new HashMap<String, Object>();
			extra.put("open_id", memberBean2.getMember_open_id());// 用户在商户微信公众号下的唯一标识，获取方式可参考
			chargeParams.put("extra", extra);
		} else if (channel.equals("wx_pub_qr")) {
			Map<String, Object> extra = new HashMap<String, Object>();
			extra.put("product_id", NumberUtils.createRandom(false, 20));// 用户在商户微信公众号下的唯一标识，获取方式可参考
			// WxPubOAuthExample.java
			chargeParams.put("extra", extra);
		}
		Charge charge = Charge.create(chargeParams);
		WriteObject(new Gson().toJson(charge));
	}
	/**
	 * 缴纳工单预约押金
	 * @param memberBean
	 * @param order_id
	 * @param channel
	 * @throws Exception
	 */
	@RequestMapping(params = "payWorkOrderDeposit", method = RequestMethod.POST)
	public void payWorkOrderDeposit(MemberBean memberBean,String order_id,String channel) throws Exception {
		MemberBean memberBean2 = memberService.getMemberDetail(memberBean);
		WorkOrderBean workOrderBean=workOrderService.getWorkOrderDetail(new WorkOrderBean().setOrder_id(Integer.valueOf(order_id)));
		if(workOrderBean==null) {
			WriteError("工单不存在");
			return;
		}
		String deposit_type=workOrderBean.getWork_way()==null?"member_subscribe_deposit":workOrderBean.getWork_way().equals("清工")?"qing_work_deposit":"contractor_deposit";
		SettingBean settingBean=settingService.getSystemSettingDetail(new SettingBean().setSetting_name(deposit_type));
		PingSettingBean pingSettingBean = settingService.getPingSetting();
		Pingpp.apiKey = pingSettingBean.getApp_key();
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		String order_no = new UUIDUtils(1).nextId(false, 1) + "";
		String order_pay_no = order_no.substring(0, order_no.length() - 1) + TimeUtils.getCurrentTime("HHmmss");
		chargeParams.put("order_no", order_pay_no);
		chargeParams.put("amount",NumberUtils.KeepDecimal(settingBean.getSetting_value(), 2)*100);
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", pingSettingBean.getApp_id());
		chargeParams.put("app", app);
		chargeParams.put("channel", channel);
		chargeParams.put("currency", "cny");
		chargeParams.put("client_ip", request.getRemoteAddr());
		chargeParams.put("subject", "工单预约押金");
		String body = String.valueOf(memberBean.getMember_id())+"#"+order_id;
		chargeParams.put("body", body);

		if (channel.equals("wx_pub")) {
			Map<String, Object> extra = new HashMap<String, Object>();
			extra.put("open_id", memberBean2.getMember_open_id());// 用户在商户微信公众号下的唯一标识，获取方式可参考
			chargeParams.put("extra", extra);
		} else if (channel.equals("wx_pub_qr")) {
			Map<String, Object> extra = new HashMap<String, Object>();
			extra.put("product_id", NumberUtils.createRandom(false, 20));// 用户在商户微信公众号下的唯一标识，获取方式可参考
			// WxPubOAuthExample.java
			chargeParams.put("extra", extra);
		}
		Charge charge = Charge.create(chargeParams);
		WriteObject(new Gson().toJson(charge));
	}
	/**
	 * 工单完工支付
	 * @param memberBean
	 * @param order_id
	 * @param payBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "payWorkOrderComplete", method = RequestMethod.POST)
	public void payWorkOrderComplete(MemberBean memberBean,String price,String order_id,String channel) throws Exception {
		MemberBean memberBean2 = memberService.getMemberDetail(memberBean);
		PingSettingBean pingSettingBean = settingService.getPingSetting();
		Pingpp.apiKey = pingSettingBean.getApp_key();
		WorkOrderBean workOrderBean=workOrderService.getWorkOrderDetail(new WorkOrderBean().setOrder_id(Integer.valueOf(order_id)));
		if(workOrderBean==null) {
			WriteError("工单不存在");
			return;
		}
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		String order_no = new UUIDUtils(1).nextId(false, 1) + "";
		String order_pay_no = order_no.substring(0, order_no.length() - 1) + TimeUtils.getCurrentTime("HHmmss");
		chargeParams.put("order_no", order_pay_no);
//		chargeParams.put("amount",(workOrderBean.getOrder_final_price()-workOrderBean.getDeposit_price())*100);
		chargeParams.put("amount",1);
		Map<String, String> app = new HashMap<String, String>();

		app.put("id", pingSettingBean.getApp_id());

		chargeParams.put("app", app);
		chargeParams.put("channel", channel);
		chargeParams.put("currency", "cny");
		chargeParams.put("client_ip", request.getRemoteAddr());
		chargeParams.put("subject", "工单结算金额");
		String body = String.valueOf(memberBean.getMember_id())+"#"+order_id;
		chargeParams.put("body", body);
		if (channel.equals("wx_pub")) {
			Map<String, Object> extra = new HashMap<String, Object>();
			extra.put("open_id", memberBean2.getMember_open_id());// 用户在商户微信公众号下的唯一标识，获取方式可参考
			chargeParams.put("extra", extra);
		} else if (channel.equals("wx_pub_qr")) {
			Map<String, Object> extra = new HashMap<String, Object>();
			extra.put("product_id", NumberUtils.createRandom(false, 20));// 用户在商户微信公众号下的唯一标识，获取方式可参考
			// WxPubOAuthExample.java
			chargeParams.put("extra", extra);
		}
		Charge charge = Charge.create(chargeParams);
		WriteObject(new Gson().toJson(charge));
	}
	/**
	 * 获取报备详细信息
	 * 
	 * @param pageBean
	 * @param memberReportedBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getReportedDetail", method = RequestMethod.POST)
	public void getReportedDetail(MemberReportedBean memberReportedBean) throws Exception {
		WriteObject(memberService.getReportedDetail(memberReportedBean));
	}

	/**
	 * 删除系统给用户的消息列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteMemberMsg", method = RequestMethod.POST)
	public void deleteMemberMsg(MessageBean messageBean, PageBean pageBean) throws Exception {
		int num = memberService.deleteMemberMsg(messageBean);
		if (num > 0) {
			WriteMsg("删除成功");
		} else {
			WriteError("删除失败");
		}
	}

	/**
	 * 获得系统给用户的消息列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getMemberMsgList", method = RequestMethod.POST)
	public void getMemberMsgList(MessageBean messageBean, PageBean pageBean) throws Exception {
		WriteObject(memberService.getMemberMsgList(messageBean, pageBean), pageBean.getTotal());
	}

	/**
	 * 申请提现列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getWithdrawalList", method = RequestMethod.POST)
	public void getWithdrawalList(WithdrawalBean withdrawalBean, PageBean pageBean) throws Exception {
		WriteObject(memberService.getWithdrawalList(withdrawalBean, pageBean),pageBean.getTotal());
	}

	/**
	 * 用户积分
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getUserIntegral", method = RequestMethod.POST)
	public void getUserIntegral(MemberBean memberBean) throws Exception {
		WriteObject(memberService.getMemberDetail(memberBean).getMember_integral());
	}

	/**
	 * 申请提现
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "applyWithdrawal", method = RequestMethod.POST)
	public void applyWithdrawal(String member_pay_password,WithdrawalBean withdrawalBean) throws Exception {
		int num=memberService.applyWithdrawal(withdrawalBean,member_pay_password);
		if(num>0){
			WriteMsg("申请成功");
		}else{
			WriteError("申请失败");
		}
	}

	/**
	 * 用户信息报备
	 * 
	 * @param memberReportedBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "insertReported", method = RequestMethod.POST)
	public void insertReported(MemberReportedBean memberReportedBean) throws Exception {
		List<MemberReportedBean> reportedBeans=memberService.getReportedByPhone(memberReportedBean);
		if(reportedBeans.size()>0){
			WriteError("该报备用户已存在，不能重复报备");
			return;
		}
		int num = memberService.insertReported(memberReportedBean);
		if (num > 0) {
			WriteMsg("操作成功");
		} else {
			WriteError("操作失败");
		}
	}
	/**
	 * 用户信息报备修改
	 * 
	 * @param memberReportedBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "updateReported", method = RequestMethod.POST)
	public void updateReported(MemberReportedBean memberReportedBean) throws Exception {
		List<MemberReportedBean> reportedBeans=memberService.getReportedByPhone(memberReportedBean);
		if(reportedBeans.size()>0){
			if(!reportedBeans.get(0).getReported_phone().equals(memberReportedBean.getReported_phone())){
				WriteError("该手机号已报备");
				return;
			}
		}
		int num = memberService.updateReported(memberReportedBean);
		if (num > 0) {
			WriteMsg("操作成功");
		} else {
			WriteError("操作失败");
		}
	}
	/**
	 * 删除用户报备信息
	 * @param memberReportedBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteReported", method = RequestMethod.POST)
	public void deleteReported(MemberReportedBean memberReportedBean) throws Exception {
		int num = memberService.deleteReported(memberReportedBean);
		if (num > 0) {
			WriteMsg("操作成功");
		} else {
			WriteError("操作失败");
		}
	}
	/**
	 * 通过用户id获取报备信息列表
	 * 
	 * @param memberReportedBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getReportedList", method = RequestMethod.POST)
	public void getReportedList(PageBean pageBean, MemberReportedBean memberReportedBean) throws Exception {
		WriteObject(memberService.getReportedList(memberReportedBean, pageBean),pageBean.getTotal());
	}

	/**
	 * 获得用户信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getMemberDetail", method = RequestMethod.POST)
	public void getMemberDetail(MemberBean memberBean) throws Exception {
		WriteObject(memberService.getMemberDetail(memberBean));
	}

	/**
	 * 手机号登录
	 * 
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "memberLogin", method = RequestMethod.POST)
	public void memberLogin(MemberBean memberBean)throws Exception {
		MemberBean memberBean2=memberService.getMemberDetail(memberBean);
		if(memberBean2==null) {
			WriteError("账号不存在");
			return;
		}
		UUID uuid = UUID.randomUUID();
		MemberBean memberBean1 = memberService.memberLogin(memberBean.setMember_token(uuid.toString()));
		if (memberBean1 != null) {
			if(memberBean1.getIs_disable().equals("1")) {
				WriteError("您的账号已被冻结，请联系客服");
			}else {
				WriteObject(memberBean1);
			}
		} else {
			WriteError("用户名或者密码错误");
		}
	}
	/**
	 * 根据手机号获取用户信息
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getMemberByMobile", method = RequestMethod.POST)
	public void getMemberByMobile(MemberBean memberBean)throws Exception {
		MemberBean memberBean2=memberService.getMemberDetail(memberBean);
		if(memberBean2!=null) {
			WriteObject(memberBean2);
		}else {
			WriteError("账号不存在");
		}
	}
	/**
	 * 微信公众号登录
	 * 
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "wxPubMemberLogin", method = RequestMethod.POST)
	public void wxPubMemberLogin(String code,String fill_invitation_code) throws Exception {
		WXSettingBean wxSetingBean = settingService.getWXSetting(new WXSettingBean().setWeixin_type("1"));

		WXPubBean wxPubBean = WXUtils.getWXUserInfo(wxSetingBean.getWeixin_appid(), wxSetingBean.getWeixin_secret(),
				code, request);
		if (wxPubBean == null) {
			WriteError("微信code有误");
			return;
		}
		UUID uuid = UUID.randomUUID();
		MemberBean memberBean = new MemberBean().setMember_open_id(wxPubBean.getOpenid())
				.setMember_nick_name(wxPubBean.getNickname()).setMember_head_image(wxPubBean.getHeadimgurl())
				.setMember_token(uuid.toString());

		MemberBean memberBean1 = memberService.wxPubMemberLogin(memberBean);
		if (memberBean1 != null) {
			if(memberBean1.getIs_disable().equals("1")) {
				WriteError("您的账号已被冻结，请联系客服");
			}else {
				WriteObject(memberBean1);
			}
		} else {
			String qrcode_img = TimeUtils.getCurrentTime("yyyyMMddHHmmss") + ".png";
			int num = memberService
					.wxPubMemberRegister(memberBean.setMember_token(uuid.toString())
							.setMember_qrcode_img("/images/qrcode/business_goods/" + qrcode_img).setMember_type("0")
							.setMember_state("1"));
			if (num > 0) {
				WriteObject(memberService.wxPubMemberLogin(memberBean.setMember_token(uuid.toString())));
			} else {
				WriteError("注册失败");
			}
		}
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "memberRegister", method = RequestMethod.POST)
	public void memberRegister(MemberBean memberBean, CodeBean codeBean) throws Exception {
		if (settingService.getCodeBeanByMobileAndCode(
				codeBean.setMobile(memberBean.getMember_account()).setCode_type("register")
						.setCreate_time(TimeUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"))) == null) {
			WriteError("此验证码已过期");
			return;
		}
		MemberBean memberBean1=memberService.getMemberDetail(memberBean);
		if (memberBean1 != null) {
			if(memberBean1.getIs_disable().equals("1")) {
				WriteError("账号已被冻结，请联系客服");
			}else {
				WriteError("该账号已注册过");
			}
			return;
		}
		memberBean.setMember_phone(memberBean.getMember_account()).setMember_token(UUID.randomUUID().toString());
		SettingBean settingBean = settingService.getSystemSettingDetail(new SettingBean().setSetting_name("company_name_english"));

		memberBean.setMember_nick_name(settingBean.getSetting_value() + NumberUtils.createRandom(true, 6));
		//生成明星师傅html路径
		String fileName=TimeUtils.getCurrentTime("yyyyMMddHHmmss")+new Random().nextInt(Integer.MAX_VALUE)+".html";	
		String path="/html/member/starWorker/";
		HtmlStyleBean htmlStyleBean=settingService.getHtmlStyleDetail(new HtmlStyleBean().setStyle_type("common"));
		writeHtml(path+fileName,"明星师傅风采展示",htmlStyleBean);
		memberBean.setStar_worker_info(path+fileName);
		//生成师傅证书html路径
		fileName=TimeUtils.getCurrentTime("yyyyMMddHHmmss")+new Random().nextInt(Integer.MAX_VALUE)+".html";	
		path="/html/member/";
		writeHtml(path+fileName,"师傅证书",htmlStyleBean);
		memberBean.setMember_certificate(path+fileName);
		
		int num = memberService.memberRegister(memberBean, codeBean);
		if (num > 0) {
			MemberBean memberBean2 = memberService.getMemberDetail(memberBean);
			WriteObject(memberBean2);
		} else {
			WriteError("注册失败");
		}
	}

	/**
	 * 修改用户基本信息
	 * 
	 * @param memberBean
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "updateMemberDetail", method = RequestMethod.POST)
	public void updateMemberDetail(MemberBean memberBean) throws Exception {
		int num = memberService.updateMemberDetail(memberBean);
		if (num > 0) {
			WriteMsg("更改成功");
		} else {
			WriteMsg("更改失败");
		}
	}

	/**
	 * 忘记密码(手机号密码注册)
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "memberForgetPassword", method = RequestMethod.POST)
	public void memberForgetPassword(MemberBean memberBean, CodeBean codeBean)throws Exception {
		MemberBean memberBean2 = memberService.getMemberDetail(memberBean);
		if (memberBean2 == null) {
			WriteError("亲！您还未注册哦!");
			return;
		}

		if (EncodeUtils.MD5Encode(memberBean.getMember_password()).equals(memberBean2.getMember_password())) {
			WriteError("更改的密码!不可以和最近的密码相同哦！");
			return;
		}

		if (settingService.getCodeBeanByMobileAndCode(
				codeBean.setMobile(memberBean.getMember_account()).setCode_type("forget_password")
						.setCreate_time(TimeUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"))) == null) {
			WriteError("此验证码已过期");
			return;
		}

		int num = memberService.memberForgetPassword(memberBean, codeBean);
		if (num > 0) {
			WriteMsg("修改成功");
		} else {
			WriteError("修改失败");
		}
	}

	/**
	 * 修改密码(手机号密码注册)
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "memberUpdatePassword", method = RequestMethod.POST)
	public void memberUpdatePassword(MemberBean memberBean, CodeBean codeBean)throws Exception {
		MemberBean memberBean2 = memberService.getMemberDetail(memberBean);
		if (memberBean2 == null) {
			WriteError("亲！您还未注册哦!");
			return;
		}
		if (EncodeUtils.MD5Encode(memberBean.getMember_password()).equals(memberBean2.getMember_password())) {
			WriteError("更改的密码!不可以和最近的密码相同哦！");
			return;
		}
		if (memberBean2.getMember_password() == null || "".equals(memberBean2.getMember_password())) {

		}
		System.out.println(EncodeUtils.MD5Encode(memberBean.getMember_old_password())+" "+memberBean2.getMember_password());
		if (!EncodeUtils.MD5Encode(memberBean.getMember_old_password()).equals(memberBean2.getMember_password())) {
			WriteError("旧密码输入错误！");
			return;
		}
		if (settingService.getCodeBeanByMobileAndCode(
				codeBean.setMobile(memberBean.getMember_account()).setCode_type("update_password")
						.setCreate_time(TimeUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss"))) == null) {
			WriteError("此验证码已过期");
			return;
		}
		int num = memberService.memberForgetPassword(memberBean.setMember_id(memberBean2.getMember_id()), codeBean);
		if (num > 0) {
			WriteMsg("修改成功");
		} else {
			WriteError("修改失败");
		}
	}

	/**
	 * 提交审核
	 * 
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "submitAudit", method = RequestMethod.POST)
	public void submitAudit(MemberBean memberBean)throws Exception {
		int num = memberService.submitAudit(memberBean);
		if (num > 0) {
			WriteMsg("提交审核成功");
		} else {
			WriteError("提交审核失败");
		}
	}
	
	/**
	 * 获取明星师傅列表
	 * 
	 * @param size
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getStarMemberList", method = RequestMethod.POST)
	public void getStarMemberList(PageBean pageBean) throws Exception {
		WriteObject(memberService.getStarMemberList(pageBean));
	}

	/**
	 * 提交意见
	 * 
	 * @param memberBean
	 * @param adviceBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "addAdvice", method = RequestMethod.POST)
	public void addAdvice(AdviceBean adviceBean) throws Exception {
		int num = memberService.addAdvice(adviceBean);
		if (num > 0) {
			WriteMsg("提交建议成功");
		} else {
			WriteError("提交建议失败");
		}
	}

	/**
	 *  绑定手机号
	 * @param memberBean
	 * @param codeBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "updateMemberMobile", method = RequestMethod.POST)
	public void updateMemberMobile(MemberBean memberBean, CodeBean codeBean) throws Exception {
		if (settingService.getCodeBeanByMobileAndCode(
				codeBean.setMobile(memberBean.getMember_phone()).setCreate_time(TimeUtils.getCurrentTime())) == null) {
			WriteError("此验证码已过期");
			return;
		}
		if ("bind_mobile".equals(codeBean.getCode_type())) {
			int num = memberService.bindMobile(memberBean, codeBean);
			if (num > 0) {
				WriteMsg("绑定成功");
			} else {
				WriteError("绑定失败");
			}
		} else if ("update_mobile".equals(codeBean.getCode_type())) {
			int num = memberService.updateMobile(memberBean, codeBean);
			if (num > 0) {
				WriteMsg("修改成功");
			} else {
				WriteError("修改失败");
			}
		}
	}
	/**
	 * 绑定第三方账号
	 * @param memberBean
	 * @param codeBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "bindOtherNo", method = RequestMethod.POST)
	public void bindOtherNo(MemberBean memberBean, CodeBean codeBean) throws Exception {
		if ("bind_alipay".equals(codeBean.getCode_type())) {
			if (settingService.getCodeBeanByMobileAndCode(
					codeBean.setMobile(memberBean.getMember_alipay()).setCreate_time(TimeUtils.getCurrentTime())) == null) {
				WriteError("此验证码已过期");
				return;
			}
			int num = memberService.bindOtherNo(memberBean, codeBean);
			if (num > 0) {
				WriteMsg("绑定成功");
			} else {
				WriteError("绑定失败");
			}
		} else if ("bind_we_chat".equals(codeBean.getCode_type())) {
			if (settingService.getCodeBeanByMobileAndCode(
					codeBean.setMobile(memberBean.getMember_we_chat()).setCreate_time(TimeUtils.getCurrentTime())) == null) {
				WriteError("此验证码已过期");
				return;
			}
			int num = memberService.bindOtherNo(memberBean, codeBean);
			if (num > 0) {
				WriteMsg("绑定成功");
			} else {
				WriteError("绑定失败");
			}
		} else if ("bind_bank".equals(codeBean.getCode_type())) {
			if (settingService.getCodeBeanByMobileAndCode(
					codeBean.setMobile(memberBean.getMember_bank_phone()).setCreate_time(TimeUtils.getCurrentTime())) == null) {
				WriteError("此验证码已过期");
				return;
			}
			int num = memberService.bindOtherNo(memberBean, codeBean);
			if (num > 0) {
				WriteMsg("绑定成功");
			} else {
				WriteError("绑定失败");
			}
		}
	}
	/**
	 * 修改用户支付密码
	 * @param request
	 * @param response
	 * @param memberBean
	 * 
	 * @param codeBean
	 * @throws Exception
	 */
	@RequestMapping(params="setPayPassword",method=RequestMethod.POST)
	public void setPayPassword(MemberBean memberBean,CodeBean codeBean) throws Exception {
		if (settingService.getCodeBeanByMobileAndCode(
				codeBean.setMobile(memberBean.getMember_phone()).setCreate_time(TimeUtils.getCurrentTime()).setCode_type("balance_passwrod")) == null) {
			WriteError("此验证码已过期");
			return;
		}
		int num=memberService.setPayPassword(memberBean,codeBean);
		if(num>0) {
			WriteMsg("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
}
