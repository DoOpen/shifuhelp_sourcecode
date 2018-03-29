package com.project.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.project.bean.member.MemberBean;
import com.project.bean.workOrder.ServiceClassBean;
import com.project.bean.workOrder.WorkOrderBean;
import com.project.page.PageBean;

public interface WorkOrderDaoI {
	/**
	 * 获取师傅退单列表
	 * @param memberBean
	 * @return
	 */
	public List<WorkOrderBean> getRefundOrderList(WorkOrderBean workOrderBean,PageBean pageBean);
	/**
	 * 创建退单记录
	 * @param workOrderBean
	 * @return
	 */
	public int createRefundWorkOrder(WorkOrderBean workOrderBean);
	/**
	 * 获取商品类别
	 * @param goodsClassBean
	 * @return
	 */
	public List<ServiceClassBean> getServiceClassList(ServiceClassBean serviceClassBean);
	/**
	 * 创建工单
	 * @param workOrderBean
	 * @return
	 */
	public int createWorkOrder(WorkOrderBean workOrderBean);
	/**
	 * 获取预约待审核工单列表
	 * @param pageBean
	 * @return
	 */
	public List<WorkOrderBean> getOrderListByState(PageBean pageBean,WorkOrderBean workOrderBean);
	/**
	 * 更新工单信息
	 * @param workOrderBean
	 * @return
	 */
	public int updateWorkOrder(WorkOrderBean workOrderBean);
	/**
	 * 获取工单信息
	 * @param workOrderBean
	 * @return
	 */
	public WorkOrderBean getWorkOrderDetail(WorkOrderBean workOrderBean);
	/**
	 * 通过师傅地址获取最近范围的预约信息
	 * @param memberBean
	 * @return
	 */
	public WorkOrderBean getWorkOrderByDistance(MemberBean memberBean);
	/**
	 * 获取工单状态列表
	 * @return
	 */
	public List<String> getOrderStateList();
	/**
	 * 通过父类别id获取父类别信息
	 * @param serviceClassBean
	 * @return
	 */
	public ServiceClassBean getParentServiceClass(ServiceClassBean serviceClassBean);
	/**
	 * 通过id获取服务类别信息
	 * @param serviceClassBean
	 * @return
	 */
	public ServiceClassBean getServiceClassById(ServiceClassBean serviceClassBean);
	/**
	 * 获取退单详情
	 * @param workOrderBean
	 * @return
	 */
	public WorkOrderBean getRefundWorkOrderDetail(WorkOrderBean workOrderBean);
	/**
	 * 统计工单状态
	 * @return
	 */
	public Map<String, Integer> getWorkOrderStateCount(MemberBean memberBean);
	/**
	 * 工单投诉
	 * @param complaintsBean
	 * @return
	 */
	public int workOrderComplaints(WorkOrderBean workOrderBean);
}
