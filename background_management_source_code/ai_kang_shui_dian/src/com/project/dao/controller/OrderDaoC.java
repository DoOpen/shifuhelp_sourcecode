package com.project.dao.controller;

import java.util.List;
import java.util.Map;

import com.project.bean.order.LogisticsBean;
import com.project.bean.order.OrderBean;
import com.project.bean.order.OrderGoodsBean;
import com.project.bean.order.RefundBean;
import com.project.bean.order.RefundReasonBean;
import com.project.page.PageBean;

public interface OrderDaoC {
	/**
	 * 订单列表
	 * @param orderBean
	 * @param pageBean
	 * @return
	 */
	public List<OrderBean> getOrderList(OrderBean orderBean,PageBean pageBean);
	/**
	 * 订单详情
	 * @param orderBean
	 * @return
	 */
	public OrderBean getOrderDetail(OrderBean orderBean);
	/**
	 * 删除订单
	 * @param orderBean
	 * @return
	 */
	public int deleteOrder(OrderBean orderBean);
	/**
	 * 修改订单
	 * @param orderBean
	 * @return
	 */
	public int updateOrder(OrderBean orderBean);
	/**
	 * 获取订单商品列表
	 * @param orderGoodsBean
	 * @return
	 */
	public List<OrderGoodsBean> getOrderGoodsList(OrderGoodsBean orderGoodsBean);
	/**
	 * 售后订单列表
	 * @param orderRefundBean
	 * @param pageBean
	 * @return
	 */
	public List<RefundBean> getRefundList(RefundBean refundBean, PageBean pageBean);
	/**
	 * 售后订单详情
	 * @param orderRefundBean
	 * @return
	 */
	public RefundBean getRefundDetail(RefundBean refundBean);
	/**
	 * 修改售后订单
	 * @param orderRefundBean
	 * @return
	 */
	public int updateRefund(RefundBean refundBean);
	/**
	 * 添加退款原因
	 * @param refundReasonBean
	 * @return
	 */
	public int insertRefundReason(RefundReasonBean refundReasonBean);
	/**
	 * 删除退款原因
	 * @param refundReasonBean
	 * @return
	 */
	public int deleteRefundReason(RefundReasonBean refundReasonBean);
	/**
	 * 修改退款原因
	 * @param refundReasonBean
	 * @return
	 */
	public int updateRefundReason(RefundReasonBean refundReasonBean);
	/**
	 * 退款原因详情
	 * @param refundReasonBean
	 * @return
	 */
	public RefundReasonBean getRefundReasonDetail(RefundReasonBean refundReasonBean);
	/**
	 * 退款原因列表
	 * @param refundReasonBean
	 * @param pageBean
	 * @return
	 */
	public List<RefundReasonBean> getRefundReasonList(RefundReasonBean refundReasonBean, PageBean pageBean);
	/**
	 * 获取订单收益财务统计
	 * @param orderBean
	 * @return
	 */
	public Map<String, String> getFinanceStatistics(OrderBean orderBean);
	/**
	 * 添加物流公司
	 * @param logisticsBean
	 * @return
	 */
	public int insertLogistics(LogisticsBean logisticsBean);
	/**
	 * 修改物流公司
	 * @param logisticsBean
	 * @return
	 */
	public int updateLogistics(LogisticsBean logisticsBean);
	/**
	 * 删除物流公司
	 * @param logisticsBean
	 * @return
	 */
	public int deleteLogistics(LogisticsBean logisticsBean);
	/**
	 * 物流公司详情
	 * @param logisticsBean
	 * @return
	 */
	public LogisticsBean getLogisticsDetail(LogisticsBean logisticsBean);
	/**
	 * 物流公司列表
	 * @param logisticsBean
	 * @param pageBean
	 * @return
	 */
	public List<LogisticsBean> getLogisticsList(LogisticsBean logisticsBean, PageBean pageBean);
	/**
	 * 订单商品详情
	 * @param orderGoodsBean
	 * @return
	 */
	public OrderGoodsBean getOrderGoodsDetail(OrderGoodsBean orderGoodsBean);
	/**
	 * 导出订单列表
	 * @param orderBean
	 * @return
	 */
	public List<Object> exportOrderExcel(OrderBean orderBean);
}
