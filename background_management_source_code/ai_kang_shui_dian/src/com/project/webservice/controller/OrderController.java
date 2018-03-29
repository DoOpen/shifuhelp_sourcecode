package com.project.webservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.order.LogisticsBean;
import com.project.bean.order.OrderBean;
import com.project.bean.order.RefundBean;
import com.project.bean.order.RefundReasonBean;
import com.project.bean.others.ExcelBean;
import com.project.bean.pay.PingRefundBean;
import com.project.service.controller.OrderServiceC;
import com.project.service.controller.SystemServiceC;
import com.project.utils.ExcelUtils;
import com.project.utils.TimeUtils;
import com.project.webservice.BaseController;

import com.project.page.PageBean;

@RestController
@RequestMapping("/orderController.api")
public class OrderController extends BaseController{
	@Autowired
	OrderServiceC orderService;
	@Autowired
	SystemServiceC systemService;
	
	/**
	 * 导出订单列表
	 * @param orderBean
	 */
	@RequestMapping(params = "exportOrderExcel", method = RequestMethod.POST)
	public void exportOrderExcel(OrderBean orderBean) {
		String fileName="/excel/order/"+TimeUtils.getCurrentTime("yyyyMMddHHmmss")+".xls";
		List<ExcelBean> excelBeans=new ArrayList<ExcelBean>();
		excelBeans.add(new ExcelBean().setName("用户ID").setType("member_id"));
		excelBeans.add(new ExcelBean().setName("订单号").setType("order_no"));
		excelBeans.add(new ExcelBean().setName("订单总价").setType("order_total_price"));
		excelBeans.add(new ExcelBean().setName("实收金额").setType("order_actual_price"));
		excelBeans.add(new ExcelBean().setName("订单状态").setType("order_state"));
		excelBeans.add(new ExcelBean().setName("邮费").setType("express_price"));
		excelBeans.add(new ExcelBean().setName("收件地址-省").setType("address_province"));
		excelBeans.add(new ExcelBean().setName("收件地址-市").setType("address_city"));
		excelBeans.add(new ExcelBean().setName("收件地址-区").setType("address_district"));
		excelBeans.add(new ExcelBean().setName("收件地址-详细地址").setType("address_detail"));
		excelBeans.add(new ExcelBean().setName("收件人").setType("address_name"));
		excelBeans.add(new ExcelBean().setName("电话").setType("address_mobile"));
		excelBeans.add(new ExcelBean().setName("快递公司").setType("logistics_name"));
		excelBeans.add(new ExcelBean().setName("备注").setType("custom_remark"));
		List<Object> orderBeans=orderService.exportOrderExcel(orderBean);
		boolean is_success=ExcelUtils.exportExcel(fileName, excelBeans,orderBeans);
		if(is_success){
			WriteObject(fileName);
		}else{
			WriteError("导出失败");
		}
	}
	/**
	 * 退款订单
	 * 
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "refundOrder", method = RequestMethod.POST)
	public void refundOrder(RefundBean refundBean) throws Exception {
		int num=orderService.refundOrder(refundBean);
		if(num>0){
			WriteMsg("退款成功");
		}else{
			WriteError("退款失败");
		}
	}
	/**
	 * ping++退款成功回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "refundSuccessOrder", method = RequestMethod.POST)
	public void refundSuccessOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = readJSONString();
		PingRefundBean pingRefundBean=(PingRefundBean) jsonToObject(json, PingRefundBean.class);
		orderService.refundSuccessOrder(new RefundBean()
				.setRefund_ping_no(pingRefundBean.getOrder_no())
				.setRefund_state(pingRefundBean.getSucceed()?"end":"refund_fail"));
	}
	/**
	 * 获取订单收益财务统计
	 * @param orderBean
	 */
	@RequestMapping(params="getFinanceStatistics")
	public void getFinanceStatistics(OrderBean orderBean) {
		WriteObject(orderService.getFinanceStatistics(orderBean));
	}
	/**
	 * 订单列表
	 * @param orderBean
	 * @param pageBean
	 */
	@RequestMapping(params="getOrderList")
	public void getOrderList( OrderBean orderBean,PageBean pageBean){
		WriteObject(orderService.getOrderList(orderBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 订单详情
	 * @param orderBean
	 * @throws Exception
	 */
	@RequestMapping(params="getOrderDetail")
	public void getOrderDetail( OrderBean orderBean){
		WriteObject(orderService.getOrderDetail(orderBean));
	} 
	/**
	 * 删除订单
	 * @param orderBean
	 * @throws Exception
	 */
	@RequestMapping(params="deleteOrder")
	public void deleteOrder( OrderBean orderBean){
		int num=orderService.deleteOrder(orderBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改订单信息
	 * @param orderBean
	 * @throws IOException 
	 */
	@RequestMapping(params="updateOrder")
	public void updateOrder(OrderBean orderBean ){
		int num=orderService.updateOrder(orderBean);
		if(num>0) {
			WriteMsg("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 订单发货
	 * @param orderBean
	 */
	@RequestMapping(params="sendOrder")
	public void sendOrder(OrderBean orderBean) {
		OrderBean orderBean2=orderService.getOrderDetail(orderBean);
		if(!"wait_send".equals(orderBean2.getOrder_state())) {
			WriteError("当前状态不可发货");
			return;
		}
		int num=orderService.sendOrder(orderBean);
		if(num>0) {
			WriteMsg("发货成功");
		}else {
			WriteError("发货失败");
		}
	}
	/**
	 * 售后订单列表
	 * @param orderRefundBean
	 * @param pageBean
	 */
	@RequestMapping(params="getRefundList")
	public void getRefundList(RefundBean refundBean,PageBean pageBean) {
		WriteObject(orderService.getRefundList(refundBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 售后订单详情
	 * @param orderRefundBean
	 */
	@RequestMapping(params="getRefundDetail")
	public void getRefundDetail(RefundBean refundBean) {
		WriteObject(orderService.getRefundDetail(refundBean));
	}
	/**
	 * 修改售后订单
	 * @param orderRefundBean
	 */
	@RequestMapping(params="updateRefund")
	public void updateRefund(RefundBean refundBean) {
		RefundBean refundBean2=orderService.getRefundDetail(refundBean);
		if("end".equals(refundBean2.getRefund_state())) {
			WriteError("售后已结束，无法修改售后状态!");
			return;
		}
		int num=orderService.updateRefund(refundBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 添加退款原因
	 * @param refundReasonBean
	 */
	@RequestMapping(params="insertRefundReason")
	public void insertRefundReason(RefundReasonBean refundReasonBean) {
		int num=orderService.insertRefundReason(refundReasonBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 删除退款原因
	 * @param refundReasonBean
	 */
	@RequestMapping(params="deleteRefundReason")
	public void deleteRefundReason(RefundReasonBean refundReasonBean) {
		int num=orderService.deleteRefundReason(refundReasonBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改退款原因
	 * @param refundReasonBean
	 */
	@RequestMapping(params="updateRefundReason")
	public void updateRefundReason(RefundReasonBean refundReasonBean) {
		int num=orderService.updateRefundReason(refundReasonBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 退款原因详情
	 * @param refundReasonBean
	 */
	@RequestMapping(params="getRefundReasonDetail")
	public void getRefundReasonDetail(RefundReasonBean refundReasonBean) {
		WriteObject(orderService.getRefundReasonDetail(refundReasonBean));
	}
	/**
	 * 退款原因列表
	 * @param refundReasonBean
	 * @param pageBean
	 */
	@RequestMapping(params="getRefundReasonList")
	public void getRefundReasonList(RefundReasonBean refundReasonBean,PageBean pageBean) {
		WriteObject(orderService.getRefundReasonList(refundReasonBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 物流公司列表
	 * @param logisticsBean
	 * @param pageBean
	 */
	@RequestMapping(params="getLogisticsList")
	public void getLogisticsList( LogisticsBean logisticsBean,PageBean pageBean){
		WriteObject(orderService.getLogisticsList(logisticsBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 物流公司详情
	 * @param logisticsBean
	 * @throws Exception
	 */
	@RequestMapping(params="getLogisticsDetail")
	public void getLogisticsDetail( LogisticsBean logisticsBean){
		WriteObject(orderService.getLogisticsDetail(logisticsBean));
	} 
	/**
	 * 删除物流公司
	 * @param logisticsBean
	 * @throws Exception
	 */
	@RequestMapping(params="deleteLogistics")
	public void deleteLogistics( LogisticsBean logisticsBean){
		int num=orderService.deleteLogistics(logisticsBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改物流公司信息
	 * @param logisticsBean
	 * @throws IOException 
	 */
	@RequestMapping(params="updateLogistics")
	public void updateLogistics(LogisticsBean logisticsBean ){
		int num=orderService.updateLogistics(logisticsBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 添加物流公司
	 * @param logisticsBean
	 */
	@RequestMapping(params="insertLogistics")
	public void insertLogistics(LogisticsBean logisticsBean) {
		int num=orderService.insertLogistics(logisticsBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
}
