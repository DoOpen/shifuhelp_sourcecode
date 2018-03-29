package com.project.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberGroupBean;
import com.project.bean.order.AssessmentBean;
import com.project.bean.order.AssessmentImgBean;
import com.project.bean.order.OrderBean;
import com.project.bean.order.OrderGoodsBean;
import com.project.bean.order.OrderLogisticsBean;
import com.project.bean.order.RefundBean;
import com.project.bean.order.RefundReasonBean;
import com.project.page.PageBean;

public interface OrderDaoI {
	
	/**
	 * 添加物流信息
	 * @param orderLogisticsBean
	 * @return
	 */
	public int insertOrderLogistics(OrderLogisticsBean orderLogisticsBean);
	/**
	 * 物流列表
	 * @param orderLogisticsBean
	 * @return
	 */
	public List<OrderLogisticsBean> getOrderLogisticsList(OrderLogisticsBean orderLogisticsBean);
	/**
	 * 申请开发票
	 * @param orderBean
	 * @return
	 */
	public int applyOrderInvoice(OrderBean orderBean);
	
	/**
	 * 获得退款列表
	 * @param refundBean
	 * @param pageBean
	 * @return
	 */
	public List<RefundBean> getRefundOrderList(RefundBean refundBean,PageBean pageBean);
	
	/**
	 * 取消退款订单
	 * @param refundBean
	 * @return
	 */
	public int cancelRefundOrder(RefundBean refundBean);
	
	/**
	 * 退款订单
	 * @param refundBean
	 * @return
	 */
	public int refundOrder(RefundBean refundBean);
	
	/**
	 * 退款订单详情
	 * @param refundBean
	 * @return
	 */
	public RefundBean getRefundOrderDetail(RefundBean refundBean);
	
	/**
	 * 获得订单原因列表
	 * 
	 * @param refundReasonBean
	 * @return
	 */
	public List<RefundReasonBean> getRefundReasonList(RefundReasonBean refundReasonBean);
	/**
	 * 获得用户的开团信息
	 * @param memberGroupBean
	 * @return
	 */
	public MemberGroupBean getMemberGroupDetail(MemberGroupBean memberGroupBean);
	
	/**
	 * 参团人信息
	 * @param addressBean
	 * @return
	 */
	public OrderBean getGroupOrder(MemberGroupBean memberGroupBean);
	
	/**
	 * 参加团购的用户列表
	 * @param memberGroupBean
	 * @return
	 */
	public List<MemberBean> getGroupMemberList(MemberGroupBean memberGroupBean);
	
	/**
	 * 修改用户的开团信息
	 * @param memberGroupBean
	 * @return
	 */
	public int updateMemberGroup(MemberGroupBean memberGroupBean);
	/**
	 *  获得商品的开团列表
	 * @param memberGroupBean
	 * @param pageBean
	 * @return
	 */
	public List<MemberGroupBean> getGoodsGroupList(MemberGroupBean memberGroupBean,PageBean pageBean);
	/**
	 * 用户开团录入信息
	 * @param memberGroupBean
	 * @return
	 */
	public int insertMemberGroup(MemberGroupBean memberGroupBean);
	
	/**
	 * 评价订单
	 * @param assessmentBean
	 * @return
	 */
	public int assessmentOrder(AssessmentBean assessmentBean);
	
	/**
	 * 评价订单图片信息
	 * @param assessmentImgBean
	 * @return
	 */
	public int insertAssessmentImg(AssessmentImgBean assessmentImgBean);
	/**
	 * 订单列表
	 * @param orderBean
	 * @param pageBean
	 * @return
	 */
	public List<OrderBean> getOrderList(OrderBean orderBean,PageBean pageBean);
	
	/**getOrderList
	 * 获得订单各个状态数量
	 * @param orderBean
	 * @return
	 */
	public Map<String,String> getOrderStateCount(OrderBean orderBean);
	/**
	 * 订单列表
	 * @param orderBean
	 * @param pageBean
	 * @return
	 */
	public List<OrderBean> getOrders(OrderBean orderBean);
	
	/**
	 * 订单商品列表
	 * @param orderGoodsBean
	 * @return
	 */
	public List<OrderGoodsBean> getOrderGoodsList(OrderGoodsBean orderGoodsBean);
	
	/**
	 * 订单商品详情
	 * @param orderGoodsBean
	 * @return
	 */
	public OrderGoodsBean getOrderGoodsDetail(OrderGoodsBean orderGoodsBean);
	
	/**
	 * 获得订单详情
	 * @param orderBean
	 * @return
	 */
	public OrderBean getOrderDetail(OrderBean orderBean);
	
	/**
	 * 修改订单详情
	 * @return
	 */
	public int updateOrderDetail(OrderBean orderBean);
	
	/**
	 * 下单订单入库
	 * @return
	 */
	public int insertOrder(OrderBean orderBean);
	
	/**
	 * 下单商品入库
	 * @return
	 */
	public int insertOrderGoods(OrderGoodsBean orderGoodsBean);
	/**
	 * 修改订单商品
	 * @return
	 */
	public int updateOrderGoods(OrderGoodsBean orderGoodsBean);
	/**
	 * 删除订单
	 * @param orderBean
	 * @return
	 */
	public int deleteOrder(OrderBean orderBean);
}
