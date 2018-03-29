package com.project.webservice.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import com.project.bean.goods.GoodsSpecificationBean;
import com.project.bean.member.BalanceHistoryBean;
import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberGroupBean;
import com.project.bean.member.MemberIntegralBean;
import com.project.bean.order.AssessmentBean;
import com.project.bean.order.KuaidiniaoResultBean;
import com.project.bean.order.OrderBean;
import com.project.bean.order.OrderGoodsBean;
import com.project.bean.order.OrderLogisticsBean;
import com.project.bean.order.CreateOrderInfoBean;
import com.project.bean.order.RefundBean;
import com.project.bean.order.RefundReasonBean;
import com.project.bean.others.PingSettingBean;
import com.project.bean.pay.PingChargeBean;
import com.project.bean.system.SettingBean;
import com.project.others.NotToken;
import com.project.page.PageBean;
import com.project.service.interfaces.GoodsServiceI;
import com.project.service.interfaces.MemberServiceI;
import com.project.service.interfaces.OrderServiceI;
import com.project.service.interfaces.SettingServiceI;
import com.project.utils.EncodeUtils;
import com.project.utils.NumberUtils;
import com.project.utils.QRCodeUtils;
import com.project.utils.TimeUtils;
import com.project.utils.UUIDUtils;
import com.project.webservice.BaseController;

@RestController
@RequestMapping("/orderInterfaces.api")
public class OrderInterfaces extends BaseController{
	@Autowired
	OrderServiceI orderService;
	@Autowired
	MemberServiceI memberService;
	@Autowired
	GoodsServiceI goodsService;
	@Autowired
	SettingServiceI settingService;
	/**
	 * 删除订单
	 * @param orderBean
	 */
	@RequestMapping(params = "deleteOrder", method = RequestMethod.POST)
	public void deleteOrder(OrderBean orderBean) {
		if(orderService.deleteOrder(orderBean)>0) {
			WriteObject("删除成功");
		}else {
			WriteError("删除失败");
		}
	}
	/**
	 * 物流列表
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getOrderLogisticsList", method = RequestMethod.POST)
	public void getOrderLogisticsList(OrderLogisticsBean orderLogisticsBean) throws Exception {
		WriteObject(orderService.getOrderLogisticsList(orderLogisticsBean));
	}
	
	/**
	 * 快递鸟订阅回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "kuaidiniaoCallback", method = RequestMethod.POST)
	public void kuaidiniaoCallback(String RequestData) throws Exception {
		KuaidiniaoResultBean kuaidiniaoResultBean=new Gson().fromJson(RequestData, KuaidiniaoResultBean.class);
		WriteOnlyMsg("{\"EBusinessID\": \""+kuaidiniaoResultBean.getEBusinessID()+"\", \"UpdateTime\": \""+TimeUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss")+"\", \"Success\": true,\"Reason\": \"\"}");
		orderService.insertOrderLogisticsDetail(kuaidiniaoResultBean);
	}
	
	
	/**
	 * 获得发票列表
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getOrderInvoiceList", method = RequestMethod.POST)
	public void getOrderInvoiceList(OrderBean orderBean,PageBean pageBean) throws Exception {
		WriteObject(orderService.getOrderList(orderBean.setOrder_state("close"),pageBean),pageBean.getTotal());
	}
	
	/**
	 * 申请开发票
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "applyOrderInvoice", method = RequestMethod.POST)
	public void applyOrderInvoice(OrderBean orderBean,String order_ids) throws Exception {
		int num=orderService.applyOrderInvoice(orderBean,order_ids.split(","));
		if(num>0){
			WriteMsg("申请成功");
		}else{
			WriteError("申请失败");
		}
	}
	/**
	 * 获得退款详情
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getRefundOrderDetail", method = RequestMethod.POST)
	public void getRefundOrderDetail(RefundBean refundBean) throws Exception {
		WriteObject(orderService.getRefundOrderDetail(refundBean));
	}
	
	/**
	 * 获得退款列表
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getRefundOrderList", method = RequestMethod.POST)
	public void getRefundOrderList(MemberBean memberBean,RefundBean refundBean, PageBean pageBean) throws Exception {
		WriteObject(orderService.getRefundOrderList(refundBean, pageBean),pageBean.getTotal());
	}
	/**
	 * 取消退款订单
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "cancelRefundOrder", method = RequestMethod.POST)
	public void cancelRefundOrder(MemberBean memberBean,RefundBean refundBean) throws Exception {
		int num=orderService.cancelRefundOrder(refundBean);
		if(num>0){
			WriteMsg("申请成功");
		}else{
			WriteError("申请失败");
		}
	}
	
	/**
	 * 退款订单
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "refundOrder", method = RequestMethod.POST)
	public void refundOrder(RefundBean refundBean,String refund_imgs) throws Exception {
		int num=orderService.refundOrder(refundBean,refund_imgs == null||"".equals(refund_imgs)? null :  refund_imgs.split(","));
		if(num>0){
			WriteMsg("申请成功");
		}else{
			WriteError("申请失败");
		}
	}
	
	/**
	 * 获得订单原因列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getRefundReasonList", method = RequestMethod.POST)
	public void getRefundReasonList(MemberBean memberBean, RefundReasonBean refundReasonBean, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WriteObject(orderService.getRefundReasonList(refundReasonBean));
	}
	
	/**
	 * 获得商品的开团列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getGoodsGroupList", method = RequestMethod.POST)
	public void getGoodsGroupList(MemberGroupBean memberGroupBean,PageBean pageBean) throws Exception {
		WriteObject(orderService.getGoodsGroupList(memberGroupBean,pageBean),pageBean.getTotal());
	}
	
	/**
	 * 获得用户的开团详情
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getMemberGroupDetail", method = RequestMethod.POST)
	public void getMemberGroup(MemberGroupBean memberGroupBean) throws Exception {
		WriteObject(orderService.getMemberGroupDetail(memberGroupBean));
	}
	
	/**
	 * 评价订单
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "assessmentOrder", method = RequestMethod.POST)
	public void assessmentOrder(OrderBean orderBean,String json) throws Exception {
		@SuppressWarnings("unchecked")
		List<AssessmentBean> assessmentBeans = (List<AssessmentBean>) jsonToObject(json, List.class, AssessmentBean.class);
		int num=orderService.assessmentOrder(assessmentBeans);
		if(num>0){
			WriteMsg("评价成功");
		}else{
			WriteError("评价失败");
		}
	}
	/**
	 * 确认接收订单
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "receiveOrder", method = RequestMethod.POST)
	public void receiveOrder(OrderBean orderBean) throws Exception {
		int num=orderService.receiveOrder(orderBean);
		if(num>0){
			WriteMsg("确认成功");
		}else{
			WriteError("确认失败");
		}
	}
	/**
	 * 取消订单(待付款状态)
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "cancelPayOrder", method = RequestMethod.POST)
	public void cancelPayOrder(OrderBean orderBean) throws Exception {
		int num=orderService.cancelPayOrder(orderBean);
		if(num>0){
			WriteMsg("取消成功");
		}else{
			WriteError("取消失败");
		}
	}
	/**
	 * 取消订单(支付之前)
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "cancelOrder", method = RequestMethod.POST)
	public void cancelOrder(OrderBean orderBean) throws Exception {
		int num=orderService.cancelOrder(orderBean);
		if(num>0){
			WriteMsg("取消成功");
		}else{
			WriteError("取消失败");
		}
		
	}
	/**
	 * 获得订单详情
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getOrderDetail", method = RequestMethod.POST)
	public void getOrderDetail(OrderBean orderBean) throws Exception {
		WriteObject(orderService.getOrderDetail(orderBean));
	}
	
	/**
	 * 获得订单列表
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getOrderList", method = RequestMethod.POST)
	public void getOrderList(OrderBean orderBean,PageBean pageBean) throws Exception {
		WriteObject(orderService.getOrderList(orderBean,pageBean),pageBean.getTotal());
	}
	
	/**
	 * 获得订单各个状态数量
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getOrderStateCount", method = RequestMethod.POST)
	public void getOrderStateCount(OrderBean orderBean) throws Exception {
		WriteObject(orderService.getOrderStateCount(orderBean));
	}
	
	/**
	 * ping++支付成功回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "paySuccessOrder", method = RequestMethod.POST)
	public void paySuccessOrder() throws Exception {
		String json = readJSONString();
		PingChargeBean pingChargeBean =(PingChargeBean) jsonToObject(json, PingChargeBean.class);
		System.out.println(json);
		String subject=pingChargeBean.getData().getObject().getSubject();
		if("师傅缴纳押金".equals(subject)){
			memberService.payDepositSuccess(pingChargeBean);
			return;
		}else if("工单预约押金".equals(subject)){
			memberService.payWorkOrderDepositSuccess(pingChargeBean);
			return;
		}else if("工单结算金额".equals(subject)){
			try {
				memberService.payWorkOrderCompleteSuccess(pingChargeBean);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		if(pingChargeBean.getData().getObject().getBody().equals("用户余额充值")) {
			Integer member_id=Integer.valueOf(pingChargeBean.getData().getObject().getMetadata().get("member_id"));
			Integer amount=pingChargeBean.getData().getObject().getAmount();
			String channel=pingChargeBean.getData().getObject().getChannel().equals("alipay_wap")?"支付宝充值":pingChargeBean.getData().getObject().getChannel().equals("wx_wap")?"微信充值":"未知";
			memberService.insertBalanceHistory(new BalanceHistoryBean().setMember_id(member_id)
					.setOrder_no(pingChargeBean.getData().getObject().getOrder_no())
					.setPrice(amount/100f)
					.setTitle(channel)
					.setType("add"));
			MemberBean memberBean=memberService.getMemberDetail(new MemberBean().setMember_id(member_id));
			memberService.updateMemberBalance(new MemberBean().setMember_id(member_id)
					.setMember_extract_money(memberBean.getMember_extract_money()+amount/100f));
		}else if(pingChargeBean.getData().getObject().getBody().equals("商品购买")) {
			String order_ids = pingChargeBean.getData().getObject().getMetadata().get("order_ids");
			orderService.paySuccessOrder(order_ids, pingChargeBean.getData().getObject().getChannel());
		}
	}
	/**
	 * 支付订单----真实支付
	 * 
	 * @param memberBean
	 * @param orderBean
	 * @param pageBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "payRealOrderList", method = RequestMethod.POST)
	public void payRealOrderList(OrderBean orderBean,String channel,String order_ids,String type,String member_pay_password) throws Exception {
		MemberBean memberBean=memberService.getMemberDetail(new MemberBean().setMember_id(orderBean.getMember_id()));
		float total_price = 0;
		UUIDUtils uuidUtils=new UUIDUtils(1);
		String pay_no=uuidUtils.nextId(false, 1)+"";
		String body="商品购买";
		for (String order_id:order_ids.split(",")) {
			OrderBean orderBean2 = orderService.getOrderDetail(new OrderBean().setOrder_id(Integer.valueOf(order_id)));
			if(orderBean2==null){
				WriteError("订单不存在!");
				return;
			}
			if(!"wait_pay".equals(orderBean2.getOrder_state())){
				WriteError("非待付款订单!");
				return;
			}
			total_price += orderBean2.getOrder_actual_price();
			for (OrderGoodsBean orderGoodsBean:orderBean2.getOrderGoodsBeans()) {
				GoodsSpecificationBean goodsSpecificationBean=goodsService.getGoodsSpecificationDetail(new GoodsSpecificationBean()
														.setSpecification_id(orderGoodsBean.getSpecification_id()));
				if(goodsSpecificationBean==null){
					WriteError("此规格商品已不存在!");
					return;
				}
				if(!"1".equals(goodsSpecificationBean.getSpecification_state())){
					WriteError("此规格商品已下架!");
					return;
				}
				if(orderGoodsBean.getGoods_num()>goodsSpecificationBean.getSpecification_stock()){
					WriteError("此规格商品库存不足!");
					return;
				}
				if(orderGoodsBean.getShow_type().equals("integral")) {
					channel="member_integral";
				}
			}
		}
		if (total_price == 0) {// 如果订单已用积分付款成功 则不需要真实支付
			WriteObject("pay_success");
			return;
		}
		if("member_balance".equals(channel)) {
			if(!EncodeUtils.MD5Encode(member_pay_password).equals(memberBean.getMember_pay_password())) {
				WriteError("支付密码错误");
				return;
			}
			if(memberBean.getMember_extract_money()<total_price) {
				WriteError("余额不足");
				return;
			}else {
				int num=memberService.updateMemberBalance(new MemberBean().setMember_extract_money(memberBean.getMember_extract_money()-total_price)
						.setMember_id(memberBean.getMember_id()));
				if(num==0) {
					throw new Exception("用户余额更新失败");
				}
				num=orderService.paySuccessOrder(request.getParameter("order_ids"), "member_balance");
				if(num==0) {
					throw new Exception("支付失败");
				}else {
					memberService.insertBalanceHistory(new BalanceHistoryBean().setMember_id(memberBean.getMember_id())
							.setOrder_no(new UUIDUtils(1).nextId(false, 1) + "")
							.setPrice(total_price)
							.setTitle("商品购买")
							.setType("use"));
					WritePending("支付成功");
					return;
				}
			}
		}else if("member_integral".equals(channel)) {
			if(!EncodeUtils.MD5Encode(member_pay_password).equals(memberBean.getMember_pay_password())) {
				WriteError("支付密码错误");
				return;
			}
			if(memberBean.getMember_integral()<total_price) {
				WriteError("积分不足");
				return;
			}else {
				int num=memberService.updateMemberBalance(new MemberBean().setMember_integral(NumberUtils.KeepDecimal(memberBean.getMember_integral()-total_price))
						.setMember_id(memberBean.getMember_id()));
				if(num==0) {
					throw new Exception("用户积分更新失败");
				}
				num=orderService.paySuccessOrder(request.getParameter("order_ids"), "member_integral");
				if(num==0) {
					throw new Exception("支付失败");
				}else {
					num=memberService.insertMemberIntegral(new MemberIntegralBean().setIntegral_type("goods_buy")
							.setIntegral_value(NumberUtils.KeepDecimal(total_price))
							.setState("use")
							.setMember_id(memberBean.getMember_id()));
					WritePending("支付成功");
					return;
				}
			}
		}else {
			PingSettingBean pingSettingBean = settingService.getPingSetting();
			SettingBean settingBean=settingService.getSystemSettingDetail(new SettingBean().setSetting_name("server_url"));
			if(settingBean==null) {
				WriteError("服务器地址未配置");
				return;
			}
			Pingpp.apiKey = pingSettingBean.getApp_key();
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			chargeParams.put("order_no", pay_no);
			Map<String, String> metadata=new HashMap<String,String>();
			metadata.put("order_ids", order_ids);
			chargeParams.put("metadata", metadata);
			//chargeParams.put("amount", NumberUtils.KeepDecimal(total_price*100,0));// total_price*100
			chargeParams.put("amount",1);// total_price*100
			Map<String, String> app = new HashMap<String, String>();
			app.put("id", pingSettingBean.getApp_id());
			chargeParams.put("app", app);
			chargeParams.put("channel", channel);
			chargeParams.put("currency", "cny");
			chargeParams.put("client_ip", request.getRemoteAddr());
			chargeParams.put("subject", "商品购买");
			chargeParams.put("body", body.length()>20?body.substring(0,20):body);
			if (channel.equals("wx_pub")) {
				Map<String, String> extra = new HashMap<String, String>();
				extra.put("open_id", memberBean.getMember_open_id());// 用户在商户微信公众号下的唯一标识，获取方式可参考
				chargeParams.put("extra", extra);
			} else if (channel.equals("alipay_pc_direct")) {
				Map<String, String> extra = new HashMap<String, String>();
				extra.put("success_url", settingBean.getSetting_value() +"/#/pay_success/"+order_ids);
				chargeParams.put("extra", extra);
			} else if (channel.equals("wx_pub_qr")) {
				Map<String, String> extra = new HashMap<String, String>();
				extra.put("product_id", NumberUtils.createRandom(false, 20));// 用户在商户微信公众号下的唯一标识，获取方式可参考
				chargeParams.put("extra", extra);
			}else if(channel.equals("alipay_wap")){
				Map<String, String> extra = new HashMap<String, String>();
				extra.put("success_url", settingBean.getSetting_value() + "/#/pay_success/"+order_ids);
				chargeParams.put("extra", extra);
//				WriteObject("支付方式未开通");
			}else if(channel.equals("wx_wap")){
				Map<String, String> extra = new HashMap<String, String>();
				extra.put("result_url", settingBean.getSetting_value() + "/#/pay_success/"+order_ids);
				chargeParams.put("extra", extra);
//				WriteObject("支付方式未开通");
			}

			Charge charge = Charge.create(chargeParams);
			for (String order_id:order_ids.split(",")) {
				orderService.updateOrderDetail(new OrderBean().setOrder_id(Integer.valueOf(order_id))
															  .setPay_charge(new Gson().toJson(charge))
															  .setPing_no(charge.getId())
															  .setPay_no(pay_no));
			}
			if (channel.equals("wx_pub_qr")) {
				String file_name = TimeUtils.getCurrentTime("yyyyMMddHHmmss") + NumberUtils.createRandom(true, 6)+ ".png";
				QRCodeUtils.CreateQrcode(request, "/images/qrcode/wx_pub_qr/" ,file_name,
						charge.getCredential().get("wx_pub_qr").toString());
				WriteMsg("/images/qrcode/wx_pub_qr/" + file_name);
			} else {
				if("android".equals(type)){
					WriteMsg(new Gson().toJson(charge));
					System.out.println(new ObjectMapper().writeValueAsString(charge));
				}else{
					WriteObject(charge);
				}
			}
		}
	}
	/**
	 * 获得订单的价格详情
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getOrderPrice", method = RequestMethod.POST)
	public void getOrderPrice(String json) throws Exception {
		CreateOrderInfoBean createOrderInfoBean =(CreateOrderInfoBean) jsonToObject(json, CreateOrderInfoBean.class);
		if(createOrderInfoBean==null) {
			WriteError("json反序列化异常");
			return;
		}
		WriteObject(orderService.getOrderPrice(createOrderInfoBean));
	}
	/**
	 * 下单
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "insertOrder", method = RequestMethod.POST)
	public void insertOrder(String json) throws Exception {
		CreateOrderInfoBean createOrderInfoBean =(CreateOrderInfoBean) jsonToObject(json, CreateOrderInfoBean.class);
		if(createOrderInfoBean==null) {
			WriteError("json反序列化异常");
			return;
		}
		WriteObject(orderService.insertOrder(createOrderInfoBean));
	}
}
