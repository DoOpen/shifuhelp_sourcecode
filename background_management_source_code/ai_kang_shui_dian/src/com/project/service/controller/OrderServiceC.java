package com.project.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Refund;
import com.project.bean.goods.GoodsBean;
import com.project.bean.goods.GoodsSpecificationBean;
import com.project.bean.member.BalanceHistoryBean;
import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberMsgBean;
import com.project.bean.order.LogisticsBean;
import com.project.bean.order.OrderBean;
import com.project.bean.order.OrderGoodsBean;
import com.project.bean.order.RefundBean;
import com.project.bean.order.RefundReasonBean;
import com.project.bean.others.PingSettingBean;
import com.project.bean.others.MsgTypeBean;
import com.project.dao.controller.GoodsDaoC;
import com.project.dao.controller.MemberDaoC;
import com.project.dao.controller.OrderDaoC;
import com.project.dao.controller.SettingDaoC;
import com.project.page.PageBean;
import com.project.utils.NumberUtils;
import com.project.utils.TimeUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceC {
	
	@Autowired
	OrderDaoC orderDao;
	@Autowired
	SettingDaoC settingDao;
	@Autowired
	MemberDaoC memberDao;
	@Autowired
	GoodsDaoC goodsDao;
	/**
	 * 订单列表
	 * @param orderBean
	 * @param pageBean
	 * @return
	 */
	public List<OrderBean> getOrderList(OrderBean orderBean, PageBean pageBean) {
		return orderDao.getOrderList(orderBean, pageBean);
	}
	/**
	 * 订单详情
	 * @param orderBean
	 * @return
	 */
	public OrderBean getOrderDetail(OrderBean orderBean) {
		OrderBean orderBean2=orderDao.getOrderDetail(orderBean);
		if(orderBean2!=null) {
			List<OrderGoodsBean> orderGoodsBeans=orderDao.getOrderGoodsList(new OrderGoodsBean().setOrder_id(orderBean2.getOrder_id()));
			orderBean2.setOrderGoodsBeans(orderGoodsBeans);
		}
		return orderBean2;
	}
	/**
	 * 删除订单
	 * @param orderBean
	 * @return
	 */
	public int deleteOrder(OrderBean orderBean) {
		return orderDao.deleteOrder(orderBean);
	}
	/**
	 * 修改订单信息
	 * @param orderBean
	 * @return
	 */
	public int updateOrder(OrderBean orderBean) {
		return orderDao.updateOrder(orderBean);
	}
	/**
	 * 订单发货
	 * @param orderBean
	 * @return
	 */
	public int sendOrder(OrderBean orderBean) {
		LogisticsBean logisticsBean=orderDao.getLogisticsDetail(new LogisticsBean().setLogistics_id(orderBean.getLogistics_id()));
		if(logisticsBean!=null) {
			orderBean.setLogistics_name(logisticsBean.getLogistics_name()).setLogistics_code(logisticsBean.getLogistics_code())
			.setLogistics_id(logisticsBean.getLogistics_id());
		}
		orderBean.setOrder_state("wait_receive").setSend_time(TimeUtils.getCurrentTime()).setLogistics_state("not");
		return orderDao.updateOrder(orderBean);
	}
	/**
	 * 售后订单列表
	 * @param orderRefundBean
	 * @param pageBean
	 * @return
	 */
	public List<RefundBean> getRefundList(RefundBean refundBean, PageBean pageBean) {
		return orderDao.getRefundList(refundBean,pageBean);
	}
	/**
	 * 售后订单详情
	 * @param orderRefundBean
	 * @return
	 */
	public RefundBean getRefundDetail(RefundBean refundBean) {
		return orderDao.getRefundDetail(refundBean);
	}
	/**
	 * 修改售后订单
	 * @param orderRefundBean
	 * @return
	 */
	public int updateRefund(RefundBean refundBean) {
		return orderDao.updateRefund(refundBean);
	}
	/**
	 * 添加退款原因
	 * @param refundReasonBean
	 * @return
	 */
	public int insertRefundReason(RefundReasonBean refundReasonBean) {
		return orderDao.insertRefundReason(refundReasonBean);
	}
	/**
	 * 删除退款原因
	 * @param refundReasonBean
	 * @return
	 */
	public int deleteRefundReason(RefundReasonBean refundReasonBean) {
		return orderDao.deleteRefundReason(refundReasonBean);
	}
	/**
	 * 修改退款原因
	 * @param refundReasonBean
	 * @return
	 */
	public int updateRefundReason(RefundReasonBean refundReasonBean) {
		return orderDao.updateRefundReason(refundReasonBean);
	}
	/**
	 * 退款原因详情
	 * @param refundReasonBean
	 * @return
	 */
	public RefundReasonBean getRefundReasonDetail(RefundReasonBean refundReasonBean) {
		return orderDao.getRefundReasonDetail(refundReasonBean);
	}
	/**
	 * 退款原因列表
	 * @param refundReasonBean
	 * @param pageBean
	 * @return
	 */
	public List<RefundReasonBean> getRefundReasonList(RefundReasonBean refundReasonBean, PageBean pageBean) {
		return orderDao.getRefundReasonList(refundReasonBean,pageBean);
	}
	/**
	 * 获取订单收益财务统计
	 * @param orderBean
	 * @return
	 */
	public Map<String, String> getFinanceStatistics(OrderBean orderBean) {
		return orderDao.getFinanceStatistics(orderBean);
	}
	/**
	 * 物流公司列表
	 * @param logisticsBean
	 * @param pageBean
	 * @return
	 */
	public List<LogisticsBean> getLogisticsList(LogisticsBean logisticsBean, PageBean pageBean) {
		return orderDao.getLogisticsList(logisticsBean,pageBean);
	}
	/**
	 * 物流公司详情
	 * @param logisticsBean
	 * @return
	 */
	public LogisticsBean getLogisticsDetail(LogisticsBean logisticsBean) {
		return orderDao.getLogisticsDetail(logisticsBean);
	}
	/**
	 * 删除物流公司
	 * @param logisticsBean
	 * @return
	 */
	public int deleteLogistics(LogisticsBean logisticsBean) {
		return orderDao.deleteLogistics(logisticsBean);
	}
	/**
	 * 修改物流公司
	 * @param logisticsBean
	 * @return
	 */
	public int updateLogistics(LogisticsBean logisticsBean) {
		return orderDao.updateLogistics(logisticsBean);
	}
	/**
	 * 添加物流公司
	 * @param logisticsBean
	 * @return
	 */
	public int insertLogistics(LogisticsBean logisticsBean) {
		return orderDao.insertLogistics(logisticsBean);
	}
	/**
	 * 退款成功回调
	 * @param refundBean
	 * @return
	 * @throws Exception
	 */
	public int refundSuccessOrder(RefundBean refundBean) throws Exception{
		int num=0;
		RefundBean refundBean2=orderDao.getRefundDetail(refundBean);
		if(refundBean2!=null){
			if(!"end".equals(refundBean.getRefund_state())){
				num=orderDao.updateRefund(refundBean.setRefund_id(refundBean2.getRefund_id()));
			}else{
				MsgTypeBean msgTypeBean=settingDao.getMsgType(new MsgTypeBean().setMsg_type("refund"));
				if(msgTypeBean!=null){
					num=memberDao.insertMemberMsg(new MemberMsgBean()
							.setMember_id(refundBean2.getMember_id())
							.setMsg_type("order")
							.setMsg_title("订单退款成功")
							.setMsg_desc(msgTypeBean.getMsg_desc().replace("[content]", refundBean2.getOrder_no()))
							.setOrder_id(refundBean2.getOrder_id())
							.setOrder_no(refundBean2.getOrder_no()));
					if(num<=0){
						throw new Exception("消息发送失败!");
					}
				}
				num=orderDao.updateRefund(refundBean.setRefund_id(refundBean2.getRefund_id()));
				if(num<=0){
					throw new Exception("退单更新失败");
				}
				OrderBean orderBean=orderDao.getOrderDetail(new OrderBean().setOrder_id(refundBean2.getOrder_id()));
				OrderGoodsBean orderGoodsBean=orderDao
						.getOrderGoodsDetail(new OrderGoodsBean().setOrder_goods_id(refundBean2.getOrder_goods_id()));
				//用户返利退回
				MemberBean memberBean2=memberDao.getMemberDetail(new MemberBean().setMember_id(refundBean2.getMember_id()));
				if(memberBean2!=null){
					//退回退款
					Float cash_balance=memberBean2.getMember_extract_money();
					Float refund_value=NumberUtils.KeepDecimal(orderBean.getOrder_actual_price()
							*(refundBean2.getRefund_price()/orderBean.getOrder_actual_price()),2);
					cash_balance+=refund_value;
					num=memberDao.updateMember(new MemberBean()
							.setMember_id(refundBean2.getMember_id()).setMember_extract_money(cash_balance));
					if(num<=0){
						throw new Exception("用户余额更新失败!");
					}
				}
				GoodsBean goodsBean=goodsDao.getGoodsDetail(new GoodsBean().setGoods_id(orderGoodsBean.getGoods_id()));
				GoodsSpecificationBean goodsSpecificationBean=goodsDao.
						getGoodsSpecificationDetail(new GoodsSpecificationBean()
								.setSpecification_id(orderGoodsBean.getSpecification_id()));
				
				int goods_stock=goodsBean.getGoods_stock()+refundBean2.getRefund_count();
				int total_sales=goodsBean.getTotal_sales()+refundBean2.getRefund_count();
				int actual_sales=goodsBean.getActual_sales()+refundBean2.getRefund_count();
				num=goodsDao.updateGoods(new GoodsBean()
						.setGoods_id(goodsBean.getGoods_id())
						.setGoods_stock(goods_stock)
						.setTotal_sales(total_sales)
						.setActual_sales(actual_sales));
				if(num<=0){
					throw new Exception("商品信息更新失败");
				}
				
				int specification_stock=goodsSpecificationBean.getSpecification_stock()+refundBean2.getRefund_count();
				num=goodsDao.updateGoodsSpecification(new GoodsSpecificationBean()
						.setSpecification_id(orderGoodsBean.getSpecification_id())
						.setSpecification_stock(specification_stock));
				if(num<=0){
					throw new Exception("商品规格信息更新失败");
				}
			}
		}
		return num;
	}
	/**
	 * 退款订单
	 * @param refundBean
	 * @return
	 * @throws Exception 
	 */
	public int refundOrder(RefundBean refundBean) throws Exception{
		int num=0;
		RefundBean refundBean2=orderDao.getRefundDetail(refundBean);
		if(refundBean2==null){
			throw new Exception("退款订单不存在!");
		}
		
		if(!("wait_review".equals(refundBean2.getRefund_state()))){
			throw new Exception("此退款订单不可退款!");
		}
		OrderBean orderBean=orderDao.getOrderDetail(new OrderBean().setOrder_id(refundBean2.getOrder_id()));
		if(orderBean==null) {
			throw new Exception("订单不存在");
		}
		if(refundBean2.getRefund_price()>orderBean.getOrder_actual_price()){
			throw new Exception("退款金额大于付款金额");
		}	
		if(orderBean.getPay_way().equals("member_balance")) {
			MemberBean memberBean=memberDao.getMemberDetail(new MemberBean().setMember_id(refundBean2.getMember_id()));
			num=memberDao.updateMemberBalance(memberBean.setMember_extract_money(memberBean.getMember_extract_money()+refundBean2.getRefund_price()));
			if(num==0) {
				throw new Exception("用户余额更新失败");
			}
			MsgTypeBean msgTypeBean=settingDao.getMsgType(new MsgTypeBean().setMsg_type("refund"));
			if(msgTypeBean!=null){
				num=memberDao.insertMemberMsg(new MemberMsgBean()
						.setMember_id(refundBean2.getMember_id())
						.setMsg_type("order")
						.setMsg_title("订单退款成功")
						.setMsg_desc(msgTypeBean.getMsg_desc().replace("[content]", refundBean2.getOrder_no()))
						.setOrder_id(refundBean2.getOrder_id())
						.setOrder_no(refundBean2.getOrder_no()));
				if(num<=0){
					throw new Exception("消息发送失败!");
				}
			}
			num=memberDao.insertBalanceHistory(new BalanceHistoryBean().setMember_id(memberBean.getMember_id())
					.setOrder_no(orderBean.getOrder_no())
					.setPrice(refundBean2.getRefund_price())
					.setType("add")
					.setTitle("订单退款"));
			if(num==0) {
				throw new Exception("余额记录添加失败");
			}
			num=orderDao.updateRefund(refundBean.setRefund_state("end"));
		    if(num<=0){
				throw new Exception("售后状态修改失败!");
			}
		}else {
			PingSettingBean pingSettingBean = settingDao.getPingSetting();
			Pingpp.apiKey = pingSettingBean.getApp_key();
			Charge ch = Charge.retrieve(orderBean.getPing_no());//ch_id 是已付款的订单号
		    Map<String, Object> refundMap = new HashMap<String, Object>();
		    refundMap.put("amount", NumberUtils.KeepDecimal(refundBean.getRefund_price(),2)*100);
		    refundMap.put("description", "退款");
		    Refund re = ch.getRefunds().create(refundMap);	
		    num=orderDao.updateRefund(refundBean.setRefund_ping_no(re.getId()).setRefund_state("refund_ing"));
		    if(num<=0){
				throw new Exception("修改失败!");
			}
		}
	    OrderGoodsBean orderGoodsBean=orderDao
				.getOrderGoodsDetail(new OrderGoodsBean().setOrder_goods_id(refundBean2.getOrder_goods_id()));
		if(orderGoodsBean.getGoods_num()==refundBean2.getRefund_count()){
			num=orderDao.updateOrder(new OrderBean()
					.setOrder_id(refundBean2.getOrder_id())
					.setOrder_state("refund"));
			if(num<=0){
				throw new Exception("修改失败!");
			}
		}
		return num;
	}
	/**
	 * 导出订单列表
	 * @param orderBean
	 * @return
	 */
	public List<Object> exportOrderExcel(OrderBean orderBean) {
		return orderDao.exportOrderExcel(orderBean);
	}
}
