package com.project.dao.controller;

import java.util.List;
import java.util.Map;

import com.project.bean.member.MemberBean;
import com.project.bean.order.OrderBean;
import com.project.bean.system.SystemAccountBean;
import com.project.bean.workOrder.PingHistoryBean;
import com.project.bean.workOrder.ServiceClassBean;
import com.project.bean.workOrder.WorkOrderBean;
import com.project.page.PageBean;

public interface WorkOrderDaoC {
	public List<WorkOrderBean> getRefundOrderList(PageBean pageBean);
	/**
	 * 创建退单记录
	 * @param workOrderBean
	 * @return
	 */
	public int createRefundWorkOrder(WorkOrderBean workOrderBean);
	/**
	 * 工单预约审核
	 * @param workOrderBean
	 * @return
	 */
	public int subscribeAudit(WorkOrderBean workOrderBean);

	/**
	 * 获取工单列表
	 * @return
	 */
	public List<WorkOrderBean> getOrderList(WorkOrderBean workOrderBean,PageBean pageBean);
	/**
	 * 更新工单信息
	 * @param workOrderBean
	 * @return
	 */
	public int updateOrder(WorkOrderBean workOrderBean);
	/**
	 * 删除工单
	 * @param ids
	 * @return
	 */
	public int deleteOrder(String ids);
	/**
	 * 获取工单详情
	 * @param workOrderBean
	 * @return
	 */
	public WorkOrderBean getWorkOrderDetail(WorkOrderBean workOrderBean);
	/**
	 * 获取服务类别列表
	 * @param pageBean
	 * @return
	 */
	public List<ServiceClassBean> getServiceClasss(PageBean pageBean);
	/**
	 * 获取服务类别详情
	 * @param serviceClassBean
	 * @return
	 */
	public ServiceClassBean getServiceClassDetail(ServiceClassBean serviceClassBean);
	/**
	 * 添加服务类别
	 * @param serviceClassBean
	 * @return
	 */
	public int insertServiceClass(ServiceClassBean serviceClassBean);
	/**
	 * 更新服务详情
	 * @param serviceClassBean
	 * @return
	 */
	public int updateServiceClass(ServiceClassBean serviceClassBean);
	/**
	 * 删除服务类别
	 * @param serviceClassBean
	 * @return
	 */
	public int deleteServiceClass(ServiceClassBean serviceClassBean);
	public List<ServiceClassBean> getServiceClassList(ServiceClassBean serviceClassBean);
	/**
	 * 创建工单
	 * @param workOrderBean
	 * @return
	 */
	public int createWorkOrder(WorkOrderBean workOrderBean);
	/**
	 * 工单各个状态统计
	 * @return
	 */
	public Map<String, Integer> getWorkOrderStateCount(SystemAccountBean systemAccountBean);
	/**
	 * 支付记录
	 * @param pingHistoryBean
	 * @param pageBean
	 * @return
	 */
	public List<PingHistoryBean> getPingHistoryList(PingHistoryBean pingHistoryBean, PageBean pageBean);
	/**
	 * 押金统计
	 * @param orderBean
	 * @return
	 */
	public Map<String, Object> getFinanceStatistics(OrderBean orderBean);
	/**
	 * 派单师傅列表
	 * @param workOrderBean
	 * @param pageBean
	 * @return
	 */
	public List<MemberBean> getSendWorkOrderWorkerList(Map<String, String> params, PageBean pageBean);
	/**
	 * 导出押金记录excel
	 * @param pingHistoryBean
	 * @return
	 */
	public List<Object> exportPingHistoryExcel(PingHistoryBean pingHistoryBean);
	/**
	 * 导出工单Excel
	 * @param workOrderBean
	 * @return
	 */
	public List<Object> exportWorkOrderExcel(WorkOrderBean workOrderBean);
	/**
	 * 导出退单excel
	 * @return
	 */
	public List<Object> exportRefundOrderExcel();
	/**
	 * 获取退单详情
	 * @param workOrderBean
	 * @return
	 */
	public WorkOrderBean getRefundWorkOrderDetail(WorkOrderBean workOrderBean);
}
