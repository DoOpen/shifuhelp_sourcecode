package com.project.service.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.goods.GoodsBean;
import com.project.bean.goods.GoodsSpecificationBean;
import com.project.bean.member.AddressBean;
import com.project.bean.member.CouponBean;
import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberGroupBean;
import com.project.bean.member.MemberMsgBean;
import com.project.bean.order.AssessmentBean;
import com.project.bean.order.CreateOrderInfoBean;
import com.project.bean.order.KuaidiniaoResultBean;
import com.project.bean.order.OrderBean;
import com.project.bean.order.OrderGoodsBean;
import com.project.bean.order.OrderLogisticsBean;
import com.project.bean.order.RefundBean;
import com.project.bean.order.RefundReasonBean;
import com.project.bean.others.MsgTypeBean;
import com.project.bean.system.SettingBean;
import com.project.dao.interfaces.MemberDaoI;
import com.project.dao.interfaces.OrderDaoI;
import com.project.dao.interfaces.SettingDaoI;
import com.project.others.NotToken;
import com.project.page.PageBean;
import com.project.utils.NumberUtils;
import com.project.utils.TimeUtils;
import com.project.utils.UUIDUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceI {	
	@Autowired
	OrderDaoI orderDao;
	
	@Autowired
	AddressServiceI addressService;
	
	@Autowired
	ShopCarServiceI shopCarService;
	
	@Autowired
	CouponServiceI couponService;
	
	@Autowired
	MemberServiceI memberService;
	
	@Autowired
	GoodsServiceI goodsService;
	
	@Autowired
	SettingDaoI settingDao;
	
	@Autowired
	MemberDaoI memberDao;
	
	/**
	 * 物流回调  
	 * @param kuaidiniaoResultBean
	 * @return
	 * @throws Exception
	 */
	public int insertOrderLogisticsDetail(KuaidiniaoResultBean kuaidiniaoResultBean) throws Exception{
		List<KuaidiniaoResultBean.DataBean> resultBeans=kuaidiniaoResultBean.getData();
		for (int i = 0; i < resultBeans.size(); i++) {
			KuaidiniaoResultBean.DataBean dataBean=resultBeans.get(i);
			List<OrderLogisticsBean> orderLogisticsBeans=orderDao
					.getOrderLogisticsList(new OrderLogisticsBean().setLogistics_no(dataBean.getLogisticCode()));
			
			List<KuaidiniaoResultBean.DataBean.TracesBean> tracesBeans=dataBean.getTraces();
			for (int j =orderLogisticsBeans!=null?orderLogisticsBeans.size():0; j < tracesBeans.size(); j++) {
				KuaidiniaoResultBean.DataBean.TracesBean tracesBean=tracesBeans.get(j);
				int num=orderDao.insertOrderLogistics(new OrderLogisticsBean()
						.setLogistics_context(tracesBean.getAcceptStation())
						.setLogistics_time(tracesBean.getAcceptTime())
						.setLogistics_no(dataBean.getLogisticCode())
						.setLogistics_state(dataBean.getState()));
				if(num<=0){
					throw new Exception("物流添加失败");
				}
			}
			int num=0;
			OrderBean orderBean=new OrderBean()
					.setLogistics_state(dataBean.getState()).setLogistics_no(dataBean.getLogisticCode());
			if("3".equals(dataBean.getState())){
				orderBean.setOrder_state("end");
				orderBean.setReceive_time(TimeUtils.getCurrentTime());
				List<OrderBean> orderBeans=orderDao.getOrders(new OrderBean().setLogistics_no(dataBean.getLogisticCode()));
				if(orderBeans!=null){
					for (int j = 0; j < orderBeans.size(); j++) {
						OrderBean orderBean2=orderBeans.get(j);
						MsgTypeBean msgTypeBean=settingDao.getMsgType(new MsgTypeBean().setMsg_type("receive"));
						if(msgTypeBean!=null){
							num=memberDao.insertMemberMsg(new MemberMsgBean()
									.setMember_id(orderBean2.getMember_id())
									.setMsg_type("order")
									.setMsg_title("已签收")
									.setMsg_desc(msgTypeBean.getMsg_desc().replace("[content]", orderBean2.getOrder_no()))
									.setOrder_id(orderBean2.getOrder_id())
									.setOrder_no(orderBean2.getOrder_no()));
							if(num<=0){
								throw new Exception("消息发送失败!");
							}
						}
					}
				}	
			}
			num=orderDao.updateOrderDetail(orderBean);
			if(num<=0){
				throw new Exception("订单物流状态失败");
			}
		}
		return 1;
	}
	
	/**
	 * 物流列表
	 * @param orderLogisticsBean
	 * @return
	 */
	public List<OrderLogisticsBean> getOrderLogisticsList(OrderLogisticsBean orderLogisticsBean){
		return orderDao.getOrderLogisticsList(orderLogisticsBean);
	}
	
	/**
	 * 申请开发票
	 * @param orderBean
	 * @return
	 * @throws Exception 
	 */
	public int applyOrderInvoice(OrderBean orderBean,String[] order_ids) throws Exception{
		int num=0;
		for (int i = 0; i < order_ids.length; i++) {
			OrderBean orderBean2=orderDao.getOrderDetail(new OrderBean().setOrder_id(NumberUtils.Integer(order_ids[i])));
			if(orderBean2==null){
				throw new Exception("订单不存在");
			}
			if(!"close".equals(orderBean2.getOrder_state())){
				throw new Exception("此订单不可申请开票!");
			}
			
			if(!("wait_apply".equals(orderBean2.getInvoice_state())||"refuse".equals(orderBean2.getInvoice_state()))){
				throw new Exception("已申请开票");
			}
			num=orderDao.applyOrderInvoice(orderBean.setOrder_id(NumberUtils.Integer(order_ids[i]))
					.setInvoice_price(orderBean2.getOrder_actual_price()+""));
			if(num<=0){
				throw new Exception("开票失败");
			}
		}
		return num;
	}
	
	
	/**
	 * 获得退款列表
	 * @param refundBean
	 * @param pageBean
	 * @return
	 */
	public List<RefundBean> getRefundOrderList(RefundBean refundBean,PageBean pageBean){
		List<RefundBean> refundBeans=orderDao.getRefundOrderList(refundBean, pageBean);
		if(refundBeans!=null){
			for(RefundBean refundBean2:refundBeans) {
				OrderBean orderBean=getOrderDetail(new OrderBean().setOrder_id(refundBean2.getOrder_id()));
				if(orderBean!=null&&orderBean.getOrderGoodsBeans()!=null) {
					for(OrderGoodsBean orderGoodsBean:orderBean.getOrderGoodsBeans()) {
						if(orderGoodsBean.getOrder_goods_id()==refundBean2.getOrder_goods_id()) {
							refundBean2.setOrderGoodsBean(orderGoodsBean);
						}
					}
				}
				refundBean2.setOrderBean(orderBean);
			}
		}
		return refundBeans;
	}
	/**
	 * 退款订单详情
	 * @param refundBean
	 * @return
	 */
	public RefundBean getRefundOrderDetail(RefundBean refundBean){
		RefundBean refundBean2=orderDao.getRefundOrderDetail(refundBean);
		if(refundBean2!=null){
			OrderBean orderBean=getOrderDetail(new OrderBean().setOrder_id(refundBean2.getOrder_id()));
			if(orderBean!=null&&orderBean.getOrderGoodsBeans()!=null) {
				for(OrderGoodsBean orderGoodsBean:orderBean.getOrderGoodsBeans()) {
					if(orderGoodsBean.getOrder_goods_id()==refundBean2.getOrder_goods_id()) {
						refundBean2.setOrderGoodsBean(orderGoodsBean);
					}
				}
			}
			refundBean2.setOrderBean(orderBean);
		}
		return refundBean2;
	}
	
	/**
	 * 取消退款订单
	 * @param refundBean
	 * @return
	 */
	public int cancelRefundOrder(RefundBean refundBean){
		return orderDao.cancelRefundOrder(refundBean);
	}
	
	/**
	 * 退款订单
	 * @param refundBean
	 * @return
	 * @throws Exception 
	 */
	public int refundOrder(RefundBean refundBean,String[] refund_imgs) throws Exception{
		RefundBean refundBean2=orderDao
				.getRefundOrderDetail(new RefundBean().setOrder_id(refundBean.getOrder_id())
														.setOrder_goods_id(refundBean.getOrder_goods_id())
														.setMember_id(refundBean.getMember_id()));
		if(refundBean2!=null){
			throw new Exception("此商品已申请退款");
		}
		OrderBean orderBean=orderDao.getOrderDetail(new OrderBean()
				.setOrder_id(refundBean.getOrder_id()));
		if(orderBean==null){
			throw new Exception("此订单不存在!");
		}
		
		if("only_money".equals(refundBean.getRefund_type())){//待发货 退款订单
			if("end".equals(orderBean.getOrder_state())){
				throw new Exception("此订单不可以申请退款!");
			}
		}
		OrderGoodsBean orderGoodsBean=orderDao.getOrderGoodsDetail(new OrderGoodsBean()
								.setOrder_goods_id(refundBean.getOrder_goods_id()));
		if(orderGoodsBean.getGoods_num()<refundBean.getRefund_count()){
			throw new Exception("退款数量大于购买数量了");
		}
		
		List<OrderGoodsBean> orderGoodsBeans=orderDao.getOrderGoodsList(new OrderGoodsBean().setOrder_id(orderBean.getOrder_id()));
		float total_price=0;
		for (OrderGoodsBean orderGoodsBean2:orderGoodsBeans) {
			total_price += orderGoodsBean2.getSpecification_price() * orderGoodsBean2.getGoods_num();
		}
		float base_price= orderGoodsBean.getSpecification_price()* refundBean.getRefund_count();
		float refund_price =orderBean.getOrder_actual_price()*base_price/total_price;// 需要返回用户金钱
		String refund_no=new UUIDUtils(1).nextId(false, 1)+"";
		int num=orderDao.refundOrder(refundBean
				.setRefund_price(refund_price)
				.setRefund_no(refund_no)
				.setOrder_no(orderBean.getOrder_no())
				.setRefund_state("wait_review"));
		return num;
	}
	/**
	 * 获得订单原因列表
	 * 
	 * @param refundReasonBean
	 * @return
	 */
	@NotToken
	public List<RefundReasonBean> getRefundReasonList(RefundReasonBean refundReasonBean) {
		return orderDao.getRefundReasonList(refundReasonBean);
	}
	/**
	 * 获得用户的开团详情信息
	 * @param memberGroupBean
	 * @return
	 */
	public MemberGroupBean getMemberGroupDetail(MemberGroupBean memberGroupBean){
		MemberGroupBean memberGroupBean2=orderDao.getMemberGroupDetail(memberGroupBean);
		if(memberGroupBean2!=null){
			MemberBean memberBean=memberService
					.getMemberDetail(new MemberBean().setMember_id(memberGroupBean2.getMember_id()));
			memberGroupBean2.setMemberBean(memberBean);
			
			List<MemberBean> memberBeans=orderDao.getGroupMemberList(memberGroupBean2);
			memberGroupBean2.setMemberBeans(memberBeans);
			
			OrderBean orderBean=orderDao.getGroupOrder(memberGroupBean);
			memberGroupBean2.setOrderBean(orderBean);
			
			if(memberGroupBean2.getEnd_time()!=null&&!"".equals(memberGroupBean2.getEnd_time())){
				String end_time_mill=TimeUtils.getMsecFromDate("yyyy-MM-dd hh:mm:ss",memberGroupBean2.getEnd_time())+"";
				memberGroupBean2.setEnd_time_mill(end_time_mill);
			}
		}
		return memberGroupBean2;
	}
	
	/**
	 *  获得商品的开团列表
	 * @param memberGroupBean
	 * @param pageBean
	 * @return
	 */
	public List<MemberGroupBean> getGoodsGroupList(MemberGroupBean memberGroupBean,PageBean pageBean){
		List<MemberGroupBean> memberGroupBeans= orderDao.getGoodsGroupList(memberGroupBean, pageBean);
		if(memberGroupBeans!=null){
			for (int i = 0; i < memberGroupBeans.size(); i++) {
				MemberGroupBean memberGroupBean2=memberGroupBeans.get(i);
				MemberBean memberBean=memberService
						.getMemberDetail(new MemberBean().setMember_id(memberGroupBean2.getMember_id()));
				memberGroupBean2.setMemberBean(memberBean);
				
				if(memberGroupBean2.getEnd_time()!=null&&!"".equals(memberGroupBean2.getEnd_time())){
					String end_time_mill=TimeUtils.getMsecFromDate("yyyy-MM-dd hh:mm:ss",memberGroupBean2.getEnd_time())+"";
					memberGroupBean2.setEnd_time_mill(end_time_mill);
				}
			}
		}
		return memberGroupBeans;
	}
	
	/**
	 * 修改订单信息
	 * @param assessmentBeans
	 * @return
	 * @throws Exception 
	 */
	public int updateOrderDetail(OrderBean orderBean){
		return orderDao.updateOrderDetail(orderBean);
	}
	/**
	 * 评价订单
	 * @param assessmentBeans
	 * @return
	 * @throws Exception 
	 */
	public int assessmentOrder(List<AssessmentBean> assessmentBeans) throws Exception{
		int num=0;
		if(assessmentBeans!=null){
			Integer order_id=null;
			for (AssessmentBean assessmentBean:assessmentBeans) {
				order_id=assessmentBean.getOrder_id();
				OrderBean orderBean = orderDao.getOrderDetail(new OrderBean().setOrder_id(assessmentBean.getOrder_id()));
				if (orderBean == null) {
					throw new Exception("订单不存在");
				}
				if(!"wait_assessment".equals(orderBean.getOrder_state())){
					throw new Exception("非评价订单");
				}
				num=orderDao.assessmentOrder(assessmentBean.setOrder_no(orderBean.getOrder_no()));
				// 则更新商品的星级
				GoodsBean goodsBean = goodsService.getGoodsDetail(new GoodsBean().setGoods_id(assessmentBean.getGoods_id()));
				if(goodsBean==null){
					throw new Exception("此商品已下架!");
				}
				int assessment_count = goodsBean.getAssessment_count() + 1;
				float assessment_star1= assessmentBean.getAssessment_star1()==null?5.0f:assessmentBean.getAssessment_star1();
				float assessment_star2= assessmentBean.getAssessment_star2()==null?5.0f:assessmentBean.getAssessment_star2();
				float assessment_star3= assessmentBean.getAssessment_star3()==null?5.0f:assessmentBean.getAssessment_star3();
				
				float star1 = NumberUtils.KeepDecimal((goodsBean.getGoods_star1()*assessment_count+assessment_star1)/(assessment_count+1),2);
				float star2 = NumberUtils.KeepDecimal((goodsBean.getGoods_star2()*assessment_count+assessment_star2)/(assessment_count+1),2);
				float star3 = NumberUtils.KeepDecimal((goodsBean.getGoods_star3()*assessment_count+assessment_star3)/(assessment_count+1),2);
				int h = goodsService.updateGoodsDetail(new GoodsBean()
						.setGoods_id(assessmentBean.getGoods_id())
						.setGoods_star1(star1)
						.setGoods_star2(star2)
						.setGoods_star3(star3)
						.setAssessment_count(assessment_count));
				if (h <= 0) {
					throw new Exception("更新商品星级失败!");
				}
			}
			num = orderDao.updateOrderDetail(new OrderBean().setOrder_id(order_id).setOrder_state("end").setAssessment_time(TimeUtils.getCurrentTime()));
			if (num <= 0) {
				throw new Exception("订单状态改变失败!");
			}
		}
		return num;
	}
	
	/**
	 * 确认收货
	 * @param orderBean
	 * @return
	 * @throws Exception
	 */
	public int receiveOrder(OrderBean orderBean) throws Exception{
		OrderBean orderBean2=orderDao.getOrderDetail(orderBean);
		if(orderBean2==null){
			throw new Exception("订单不存在");
		}
		if(!"wait_receive".equals(orderBean2.getOrder_state())){
			throw new Exception("此订单不可确认收货");
		}
		
		int num=orderDao.updateOrderDetail(new OrderBean()
				.setOrder_id(orderBean2.getOrder_id())
				.setOrder_state("wait_assessment")
				.setReceive_time(TimeUtils.getCurrentTime()));
		
		return num;
	}
	/**
	 * 取消订单(支付之后)
	 * @param orderBean
	 * @return
	 * @throws Exception 
	 */
	public int cancelPayOrder(OrderBean orderBean) throws Exception{
		OrderBean orderBean2=orderDao.getOrderDetail(orderBean);
		if(orderBean2==null){
			throw new Exception("订单不存在");
		}
		if(!"wait_send".equals(orderBean2.getOrder_state())){
			throw new Exception("此订单不可取消");
		}
		int num=orderDao.updateOrderDetail(new OrderBean()
				.setOrder_id(orderBean2.getOrder_id())
				.setOrder_state("cancel")
				.setCancel_time(TimeUtils.getCurrentTime()));
		
		MsgTypeBean msgTypeBean=settingDao.getMsgType(new MsgTypeBean().setMsg_type("cancel_order"));
		if(msgTypeBean!=null){
			num=memberService.insertMemberMsg(new MemberMsgBean()
					.setMember_id(orderBean2.getMember_id())
					.setMsg_type("order")
					.setMsg_title("取消订单成功")
					.setMsg_desc(msgTypeBean.getMsg_desc().replace("[content]", orderBean2.getOrder_no()))
					.setOrder_id(orderBean2.getOrder_id())
					.setOrder_no(orderBean2.getOrder_no()));
			if(num<=0){
				throw new Exception("消息发送失败!");
			}
		}
		if(num>0){
			List<OrderGoodsBean> orderGoodsBeans=orderDao
					.getOrderGoodsList(new OrderGoodsBean().setOrder_id(orderBean2.getOrder_id()));
			if(orderGoodsBeans!=null){
				for (OrderGoodsBean orderGoodsBean:orderGoodsBeans) {
					GoodsSpecificationBean goodsSpecificationBean=goodsService
							.getGoodsSpecificationDetail(new GoodsSpecificationBean().setSpecification_id(orderGoodsBean.getSpecification_id()));
					if(goodsSpecificationBean!=null){
						int specification_stock=goodsSpecificationBean.getSpecification_stock()+orderGoodsBean.getGoods_num();
						int specification_sales=goodsSpecificationBean.getSpecification_sales()-orderGoodsBean.getGoods_num();
						num=goodsService.updateGoodsSpecificationDetail(new GoodsSpecificationBean()
													.setSpecification_id(orderGoodsBean.getSpecification_id())
													.setSpecification_stock(specification_stock)
													.setSpecification_sales(specification_sales));
						if(num<=0){
							throw new Exception("库存更新失败!");
						}
					}
					GoodsBean goodsBean=goodsService
							.getGoodsDetail(new GoodsBean().setGoods_id(orderGoodsBean.getGoods_id()));
					if(goodsBean!=null){
						int actual_sales=goodsBean.getActual_sales()-orderGoodsBean.getGoods_num();
						int day_sales=goodsBean.getDay_sales()-orderGoodsBean.getGoods_num();
						int month_sales=goodsBean.getMonth_sales()-orderGoodsBean.getGoods_num();
						int total_sales=goodsBean.getTotal_sales()-orderGoodsBean.getGoods_num();
						int goods_stock=goodsBean.getGoods_stock()+orderGoodsBean.getGoods_num();
						num=goodsService.updateGoodsDetail(new GoodsBean()
								.setGoods_id(goodsBean.getGoods_id())
								.setGoods_stock(goods_stock)
								.setDay_sales(day_sales)
								.setMonth_sales(month_sales)
								.setActual_sales(actual_sales)
								.setTotal_sales(total_sales));
						if(num<=0){
							throw new Exception("商品库存更新失败!");
						}
					}	
				}
			}
		}
		
		return num;
	}
	
	/**
	 * 取消订单(支付之前)
	 * @param orderBean
	 * @return
	 * @throws Exception 
	 */
	public int cancelOrder(OrderBean orderBean) throws Exception{
		OrderBean orderBean2=orderDao.getOrderDetail(orderBean);
		if(orderBean2==null){
			throw new Exception("订单不存在");
		}
		if(!"wait_pay".equals(orderBean2.getOrder_state())){
			throw new Exception("此订单不可取消");
		}
		int num=orderDao.updateOrderDetail(new OrderBean()
				.setOrder_id(orderBean2.getOrder_id())
				.setOrder_state("cancel")
				.setCancel_time(TimeUtils.getCurrentTime()));
		MsgTypeBean msgTypeBean=settingDao.getMsgType(new MsgTypeBean().setMsg_type("cancel_order"));
		if(msgTypeBean!=null){
			num=memberService.insertMemberMsg(new MemberMsgBean()
					.setMember_id(orderBean2.getMember_id())
					.setMsg_type("order")
					.setMsg_title("取消订单成功")
					.setMsg_desc(msgTypeBean.getMsg_desc().replace("[content]", orderBean2.getOrder_no()))
					.setOrder_id(orderBean2.getOrder_id())
					.setOrder_no(orderBean2.getOrder_no()));
			if(num<=0){
				throw new Exception("消息发送失败!");
			}
		}
		if(num>0){
			List<OrderGoodsBean> orderGoodsBeans=orderDao
					.getOrderGoodsList(new OrderGoodsBean().setOrder_id(orderBean2.getOrder_id()));
			if(orderGoodsBeans!=null){
				for (OrderGoodsBean orderGoodsBean:orderGoodsBeans) {
					GoodsSpecificationBean goodsSpecificationBean=goodsService
							.getGoodsSpecificationDetail(new GoodsSpecificationBean().setSpecification_id(orderGoodsBean.getSpecification_id()));
					if(goodsSpecificationBean!=null){
						int specification_stock=goodsSpecificationBean.getSpecification_stock()+orderGoodsBean.getGoods_num();
						num=goodsService.updateGoodsSpecificationDetail(new GoodsSpecificationBean()
													.setSpecification_id(orderGoodsBean.getSpecification_id())
													.setSpecification_stock(specification_stock));
						if(num<=0){
							throw new Exception("规格库存更新失败!");
						}
					}
					GoodsBean goodsBean=goodsService
							.getGoodsDetail(new GoodsBean().setGoods_id(orderGoodsBean.getGoods_id()));
					if(goodsBean!=null){
						int goods_stock=goodsBean.getGoods_stock()+orderGoodsBean.getGoods_num();
						num=goodsService.updateGoodsDetail(new GoodsBean()
								.setGoods_id(goodsBean.getGoods_id())
								.setGoods_stock(goods_stock));
						if(num<=0){
							throw new Exception("商品库存更新失败!");
						}
					}	
				}
			}
		}
		return num;
	}
	
	/**
	 * 订单详情
	 * @param orderBean
	 * @return
	 */
	public OrderBean getOrderDetail(OrderBean orderBean){
		OrderBean orderBean2=orderDao.getOrderDetail(orderBean);
		if(orderBean2!=null){
			List<OrderGoodsBean> orderGoodsBeans=getOrderGoodsList(new OrderGoodsBean().setOrder_id(orderBean2.getOrder_id()));
			orderBean2.setOrderGoodsBeans(orderGoodsBeans);
			List<OrderLogisticsBean> orderLogisticsBeans=orderDao.getOrderLogisticsList(new OrderLogisticsBean().setLogistics_no(orderBean2.getLogistics_no()));
			orderBean2.setOrderLogisticsBeans(orderLogisticsBeans);
		}
		return orderBean2;
	}
	
	/**
	 * 订单商品列表
	 * @param orderGoodsBean
	 * @return
	 */
	public List<OrderGoodsBean> getOrderGoodsList(OrderGoodsBean orderGoodsBean){
		return orderDao.getOrderGoodsList(orderGoodsBean);
	}
	/**
	 * 订单列表
	 * @param orderBean
	 * @param pageBean
	 * @return
	 */
	public List<OrderBean> getOrderList(OrderBean orderBean,PageBean pageBean){
		List<OrderBean> orderBeans=orderDao.getOrderList(orderBean, pageBean);
		if(orderBeans!=null){
			for (int i = 0; i < orderBeans.size(); i++) {
				OrderBean orderBean2=orderBeans.get(i);
				List<OrderGoodsBean> orderGoodsBeans=getOrderGoodsList(new OrderGoodsBean().setOrder_id(orderBean2.getOrder_id()));
				orderBean2.setOrderGoodsBeans(orderGoodsBeans);
			}
		}
		return orderBeans;
	}
	
	/**
	 * 获得订单各个状态数量
	 * @param orderBean
	 * @return
	 */
	public Map<String,String> getOrderStateCount(OrderBean orderBean){
		return orderDao.getOrderStateCount(orderBean);
	}
	
	/**
	 * 用户付款成功回调
	 * @param order_ids
	 * @param pay_way  支付方式
	 * @return
	 * @throws Exception 
	 */
	public int paySuccessOrder(String order_ids,String pay_way) throws Exception{
		int num=0;
		List<OrderBean> orderBeans=orderDao.getOrderList(new OrderBean().setOrder_ids(order_ids),new PageBean().setLimit(Integer.MAX_VALUE));
		for (OrderBean orderBean:orderBeans) {
			String order_state="wait_send";
			if(orderBean==null){
				throw new Exception("订单不存在!");
			}
			if(!"wait_pay".equals(orderBean.getOrder_state())){
				throw new Exception("非待付款订单!");
			}
			List<OrderGoodsBean> orderGoodsBeans=orderDao.getOrderGoodsList(new OrderGoodsBean().setOrder_id(orderBean.getOrder_id()));
			if(orderGoodsBeans!=null){
				for (OrderGoodsBean orderGoodsBean:orderGoodsBeans) {
					GoodsSpecificationBean goodsSpecificationBean=goodsService
							.getGoodsSpecificationDetail(new GoodsSpecificationBean().setSpecification_id(orderGoodsBean.getSpecification_id()));
					if(goodsSpecificationBean!=null){
						int specification_sales=goodsSpecificationBean.getSpecification_sales()+orderGoodsBean.getGoods_num();
						num=goodsService.updateGoodsSpecificationDetail(new GoodsSpecificationBean()
														.setSpecification_id(goodsSpecificationBean.getSpecification_id())
														.setSpecification_sales(specification_sales));
						if(num<=0){
							throw new Exception("规格销量更新失败");
						}
					}
					GoodsBean goodsBean=goodsService.getGoodsDetail(new GoodsBean()
							.setGoods_id(orderGoodsBean.getGoods_id()));
					int goods_num=orderGoodsBean.getGoods_num();
					int total_sales=goodsBean.getTotal_sales()+goods_num;
					int actual_sales=goodsBean.getActual_sales()+goods_num;
					num=goodsService.updateGoodsDetail(new GoodsBean()
							.setGoods_id(orderGoodsBean.getGoods_id())
							.setActual_sales(actual_sales)
							.setTotal_sales(total_sales)
							.setDay_sales(goodsBean.getDay_sales()+goods_num)
							.setMonth_sales(goodsBean.getMonth_sales()+goods_num));
					if(num==0) {
						throw new Exception("商品销量更新失败");
					}
			}
			}
			if("wait_send".equals(order_state)){
				MsgTypeBean msgTypeBean=settingDao.getMsgType(new MsgTypeBean().setMsg_type("wait_send"));
				if(msgTypeBean!=null){
					num=memberService.insertMemberMsg(new MemberMsgBean()
							.setMember_id(orderBean.getMember_id())
							.setMsg_type("order")
							.setMsg_title("待发货")
							.setMsg_desc(msgTypeBean.getMsg_desc().replace("[content]", orderBean.getOrder_no()))
							.setOrder_id(orderBean.getOrder_id())
							.setOrder_no(orderBean.getOrder_no()));
					if(num<=0){
						throw new Exception("消息发送失败!");
					}
				}
			}
			num=orderDao.updateOrderDetail(new OrderBean().setOrder_id(orderBean.getOrder_id())
													 .setOrder_state(order_state)
													 .setPay_way(pay_way)
													 .setPay_time(TimeUtils.getCurrentTime()));
			if(num<=0){
				throw new Exception("订单状态更新失败!");
			}
		}
		return num;
	}
	/**
	 * 获得订单的价格详情
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getOrderPrice(CreateOrderInfoBean createOrderInfoBean) throws Exception{
		List<Map<String, Object>> priceMaps = new ArrayList<Map<String, Object>>();
		List<OrderBean> orderBeans = createOrderInfoBean.getOrderBeans();
		MemberBean memberBean = memberService
				.getMemberDetail(new MemberBean().setMember_id(createOrderInfoBean.getMember_id()));
		if (memberBean == null) {
			throw new Exception("用户不存在");
		}
		if (orderBeans != null && orderBeans.size() > 0) {// 订单列表不为null
			boolean is_used_coupon = false;// 判断优惠卷是否已经用在某个商家店铺了
			for (OrderBean orderBean:orderBeans) {// 订单 按商家 入库
				float order_total_price = 0;// 订单总价
				float order_actual_price = 0;// 实际支付金额
				float express_price = 0;// 订单总邮费
				Map<String, Object> priceMap = new HashMap<String, Object>();
				for (OrderGoodsBean orderGoodsBean:orderBean.getOrderGoodsBeans()) {// 订单商品入库
					GoodsBean goodsBean = goodsService.getGoodsDetail(
							new GoodsBean().setGoods_id(orderGoodsBean.getGoods_id()));// 获得商品详情
					GoodsSpecificationBean goodsSpecificationBean = goodsService.getGoodsSpecificationDetail(
							new GoodsSpecificationBean().setSpecification_id(orderGoodsBean.getSpecification_id()));
					if (goodsBean == null ||"0".equals(goodsBean.getGoods_state())) {
						throw new Exception("此商品下架或者已删除");
					}
					order_total_price += orderGoodsBean.getGoods_num()*goodsSpecificationBean.getSpecification_price();
					order_actual_price += orderGoodsBean.getGoods_num()*goodsSpecificationBean.getSpecification_price();
					express_price+=goodsBean.getExpress_price()==null?0:goodsBean.getExpress_price();
				}
				if("money".equals(createOrderInfoBean.getBuy_type())) {
					/*
					 * 邮费计算
					 */
					SettingBean settingBean2=settingDao.getSystemSettingDetail(new SettingBean().setSetting_name("express_free_price"));
					float express_free_price =settingBean2==null?0:Float.valueOf(settingBean2.getSetting_value());// 商家设置的订单满多少免邮
					//订单总价小于免邮价格，需要计算邮费
					if (order_total_price<express_free_price) {
						order_total_price += express_price;// 订单总价 加上邮费
						order_actual_price += express_price;
						priceMap.put("is_free_express", "0");
					} else {
						priceMap.put("is_free_express", "1");
					}
					priceMap.put("express_free_price", express_free_price);
					priceMap.put("express_price", express_price);
					//折扣之前的价钱
					priceMap.put("discount_brfore_price", NumberUtils.KeepDecimal(order_actual_price, 2));
					priceMap.put("member_discount", "1");
					/*
					 * 计算优惠券 需放在邮费和折扣计算后面
					 */
					// 此次下单 优惠卷未用过
					if (!is_used_coupon) {
						// 用户选择优惠卷了
						if (createOrderInfoBean.getMember_coupon_id() != null) {
							CouponBean couponBean = couponService.getCouponDetailByMemberCouponId(
									new CouponBean().setMember_coupon_id(createOrderInfoBean.getMember_coupon_id()).setCoupon_state("not_used"));
							if (couponBean == null) {
								throw new Exception("此优惠券不可用状态");
							}
							// 满多少可用优惠券
							float coupon_full_price = Float.valueOf(couponBean.getCoupon_full_price());
							// 满足优惠券 满额条件
							if (coupon_full_price <= order_total_price) {
								// 订单实际支付价格 减去优惠卷的钱
								order_actual_price -= couponBean.getCoupon_price();
								is_used_coupon = true;
							}
							priceMap.put("coupon_full_price", coupon_full_price);
							priceMap.put("coupon_price", couponBean.getCoupon_price());
						}
					}
				}
				priceMap.put("order_actual_price", NumberUtils.KeepDecimal(order_actual_price, 2));
				priceMaps.add(priceMap);
			}
			if (createOrderInfoBean.getMember_coupon_id() != null) {// 用户选择优惠卷了
				if (!is_used_coupon) {// 优惠卷满额要求 没有订单满足条件
					throw new Exception("订单金额未满足优惠卷满额要求");
				}
			}
		} else {
			throw new Exception("无任何订单信息");
		}
		return priceMaps;
	}
	/**
	 * 用户添加订单
	 * @return
	 * @throws Exception 
	 */
	public Map<String,Object> insertOrder(CreateOrderInfoBean createOrderInfoBean) throws Exception{
		AddressBean addressBean = addressService
				.getAddressDetail(new AddressBean().setAddress_id(createOrderInfoBean.getAddress_id()));// 地址详情
		if (addressBean == null) {
			throw new Exception("地址不存在");
		}
		List<OrderBean> orderBeans = createOrderInfoBean.getOrderBeans();
		int num = -1;
		String car_ids = createOrderInfoBean.getCar_ids();
		if (car_ids != null&&!"".equals(car_ids)) {
			int m = shopCarService.deleteShopCar(car_ids);
			if (m <= 0) {
				throw new Exception("购物车删除失败");
			}
		}
		MemberBean memberBean = memberService
				.getMemberDetail(new MemberBean().setMember_id(createOrderInfoBean.getMember_id()));
		if (memberBean == null) {
			throw new Exception("用户不存在");
		}
		float order_total_actual_price = 0;// 多个订单的总金额
		if (orderBeans != null) {// 订单列表不为null
			List<Integer> order_ids=new LinkedList<Integer>();
			boolean is_used_coupon = false;// 判断优惠卷是否已经用在某个商家店铺了
			for (OrderBean orderBean:orderBeans) {// 订单 按商家 入库
				float order_total_price = 0;// 订单总价
				float order_actual_price = 0;// 实际支付金额
				float express_price = 0;// 订单总邮费
				orderBean.setAddressBean(addressBean);
				String order_no =  new UUIDUtils(1).nextId(false, 1) + "";
				orderBean.setOrder_state("wait_pay");// 订单状态
				orderBean.setOrder_no(order_no);// 订单号
				orderBean.setMember_id(createOrderInfoBean.getMember_id());// 用户id
				orderBean.setAddress_id(createOrderInfoBean.getAddress_id());// 地址id
				orderBean.setOrder_type(createOrderInfoBean.getOrder_type()==null?"goods":createOrderInfoBean.getOrder_type());// 订单类型
				orderBean.setBuy_type(createOrderInfoBean.getBuy_type());
				num = orderDao.insertOrder(orderBean);// 订单入库
				if(num==0) {
					throw new Exception("订单入库失败");
				}else {
					order_ids.add(orderBean.getOrder_id());
				}
				List<OrderGoodsBean> orderGoodsBeans = orderBean.getOrderGoodsBeans();
				if (orderGoodsBeans == null || orderGoodsBeans.size() <= 0) {
					throw new Exception("商品不可为空!");
				}
				Set<String> buy_types=new LinkedHashSet<String>();
				// 订单商品入库
				for (OrderGoodsBean orderGoodsBean:orderBean.getOrderGoodsBeans()) {
					GoodsBean goodsBean = goodsService.getGoodsDetail(new GoodsBean().setGoods_id(orderGoodsBean.getGoods_id()));
					buy_types.add(goodsBean.getBuy_type());
					GoodsSpecificationBean goodsSpecificationBean = goodsService.getGoodsSpecificationDetail(
							new GoodsSpecificationBean().setSpecification_id(orderGoodsBean.getSpecification_id()));
					if (goodsBean == null ||"0".equals(goodsBean.getGoods_state())) {
						throw new Exception("此商品下架或者已删除");
					}
					if (goodsSpecificationBean.getSpecification_stock() < orderGoodsBean.getGoods_num()) {
						throw new Exception(goodsSpecificationBean.getSpecification_names() + "库存不足!  现有库存:"
								+ goodsSpecificationBean.getSpecification_stock());
					} else {
						int goods_stock = goodsBean.getGoods_stock() - orderGoodsBean.getGoods_num();
						int goods_num=orderGoodsBean.getGoods_num();
						int k = goodsService.updateGoodsDetail(new GoodsBean()
								.setGoods_id(goodsBean.getGoods_id()).setGoods_stock(goods_stock));
						if(k==0) {
							throw new Exception("更新商品信息失败");
						}
						goods_stock = goodsSpecificationBean.getSpecification_stock()- orderGoodsBean.getGoods_num();
						goodsSpecificationBean.setSpecification_stock(goodsSpecificationBean.getSpecification_stock()-goods_num);
						goodsService.updateGoodsSpecificationDetail(goodsSpecificationBean);
						if (k <= 0) {
							throw new Exception("更新规格信息失败");
						}
					}
					orderGoodsBean.setGoodsBean(goodsBean);
					float total_price = 0;
					orderGoodsBean.setGoodsSpecificationBean(goodsSpecificationBean);
					int k = orderDao.insertOrderGoods(orderGoodsBean.setOrder_id(orderBean.getOrder_id()));
					if (k <= 0) {
						throw new Exception("商品入库失败");
					}
					if (goodsBean.getExpress_price()!=null&&goodsBean.getExpress_price()!=0) {// 计算邮费
						express_price += goodsBean.getExpress_price();
					}
					// 正常商品下单
					if (createOrderInfoBean.getOrder_type()==null||createOrderInfoBean.getOrder_type().equals("goods")) {
						// 总价加上商品的钱
						order_total_price += goodsSpecificationBean.getSpecification_price()*orderGoodsBean.getGoods_num();
						total_price=order_actual_price=order_total_price;
					}
					num = orderDao.updateOrderGoods(new OrderGoodsBean()
							.setOrder_goods_id(orderGoodsBean.getOrder_goods_id()).setTotal_price(total_price+""));
					if (num <= 0) {
						throw new Exception("订单商品更新失败");
					}
				}
				if(buy_types.size()>1) {
					throw new Exception("积分商品和普通商品不能同时下单");
				}
				if("money".equals(createOrderInfoBean.getBuy_type())) {
					/*
					 * 邮费计算
					 */
					SettingBean settingBean2=settingDao.getSystemSettingDetail(new SettingBean().setSetting_name("express_free_price"));
					float express_free_price =settingBean2==null?0:Float.valueOf(settingBean2.getSetting_value());// 商家设置的订单满多少免邮
					// 订单金额小于满邮要求
					if (order_total_price<express_free_price) {
						order_total_price += express_price;// 订单总价 加上邮费
						order_actual_price += express_price;
					}
					orderBean.setExpress_free_price(NumberUtils.KeepDecimal(express_free_price, 2) + "");// 满多少包邮
					orderBean.setExpress_price(NumberUtils.KeepDecimal(express_price, 2) + "");// 邮费
					/*
					 * 计算优惠券 需放在邮费和折扣计算后面
					 */
					if (!is_used_coupon) {// 此次下单 优惠卷未用过
						if (createOrderInfoBean.getMember_coupon_id() != null) {// 用户选择优惠卷了
							CouponBean couponBean = couponService.getCouponDetailByMemberCouponId(
									new CouponBean().setMember_coupon_id(createOrderInfoBean.getMember_coupon_id()).setCoupon_state("not_used"));
							if (couponBean == null) {
								throw new Exception("此优惠券不可用状态");
							}
							// 满多少可用优惠券
							float coupon_full_price = Float.valueOf(couponBean.getCoupon_full_price());
							// 满足优惠券满额条件
							if (coupon_full_price <= order_total_price) {
								order_actual_price -= couponBean.getCoupon_price();// 订单总价
								is_used_coupon = true;
							}
							orderBean.setMember_coupon_id(createOrderInfoBean.getMember_coupon_id());
							orderBean.setCoupon_full_price(coupon_full_price + "");
							orderBean.setCoupon_price(couponBean.getCoupon_price()+"");
							// 优惠券已使用
							int k = couponService.updateCouponState(couponBean.setCoupon_state("already_used").setMember_coupon_id(createOrderInfoBean.getMember_coupon_id()));
							if (k <= 0) {
								throw new Exception("优惠卷使用失败");
							}
						}
					}
				}
				order_total_actual_price += order_actual_price;
				int m = updateOrderDetail(
						orderBean.setOrder_total_price(NumberUtils.KeepDecimal(order_total_price, 2))
								.setOrder_actual_price(order_actual_price));
				if (m <= 0) {
					throw new Exception("订单更新失败");
				}
			}

			if (createOrderInfoBean.getMember_coupon_id() != null) {// 用户选择优惠卷了
				if (!is_used_coupon) {// 优惠卷满额要求 没有订单满足条件
					throw new Exception("订单金额未满足优惠卷满额要求");
				}
			}
			Map<String,Object> map = new HashMap<String,Object>();
			StringBuffer sb=new StringBuffer();
			for(Integer order_id:order_ids) {
				sb.append(order_id).append(",");
			}
			if(sb.length()>0) {
				sb.deleteCharAt(sb.length()-1);
			}
			map.put("order_ids", sb.toString());
			map.put("order_actual_price", NumberUtils.KeepDecimal(order_total_actual_price, 2));
			return map;
		} else {
			// 未选择商品
			return null;
		}
	}
	/**
	 * 删除订单
	 * @param orderBean
	 * @return
	 */
	public int deleteOrder(OrderBean orderBean) {
		return orderDao.deleteOrder(orderBean);
	}
}
