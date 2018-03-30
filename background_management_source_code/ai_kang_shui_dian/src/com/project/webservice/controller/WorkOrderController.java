package com.project.webservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.bean.order.OrderBean;
import com.project.bean.others.ExcelBean;
import com.project.bean.system.SystemAccountBean;
import com.project.bean.workOrder.PingHistoryBean;
import com.project.bean.workOrder.ServiceClassBean;
import com.project.bean.workOrder.WorkOrderBean;
import com.project.page.PageBean;
import com.project.service.controller.SystemServiceC;
import com.project.service.controller.WorkOrderServiceC;
import com.project.utils.ExcelUtils;
import com.project.utils.TimeUtils;
import com.project.webservice.BaseController;

/**
 * 工单管理模块
 * @author 方林
 *
 */
@Controller
@RequestMapping("/workOrderController.api")
public class WorkOrderController extends BaseController{
	@Autowired
	SystemServiceC systemService;
	
	@Autowired
	WorkOrderServiceC workOrderService;
	
	/**
	 * 派单师傅列表
	 * @param orderBean
	 * @throws Exception 
	 */
	@RequestMapping(params="getSendWorkOrderWorkerList")
	public void getSendWorkOrderWorkerList(@RequestParam Map<String, String> params,PageBean pageBean) throws Exception {
		WriteObject(workOrderService.getSendWorkOrderWorkerList(params,pageBean),pageBean.getTotal());
	}
	/**
	 * 押金统计
	 * @param orderBean
	 */
	@RequestMapping(params="getFinanceStatistics")
	public void getFinanceStatistics(OrderBean orderBean) {
		WriteObject(workOrderService.getFinanceStatistics(orderBean));
	}
	/**
	 * 押金缴纳记录
	 * @param pingHistoryBean
	 * @param pageBean
	 * @param response
	 */
	@RequestMapping(params="getPingHistoryList")
	public void getPingHistoryList(PingHistoryBean pingHistoryBean,PageBean pageBean) {
		WriteObject(workOrderService.getPingHistoryList(pingHistoryBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 导出押金记录excel
	 * @param pingHistoryBean
	 */
	@RequestMapping(params="exportPingHistoryExcel")
	public void exportPingHistoryExcel(PingHistoryBean pingHistoryBean) {
		String fileName="/excel/pingHistory/"+TimeUtils.getCurrentTime("yyyyMMddHHmmss")+".xls";
		List<ExcelBean> excelBeans=new ArrayList<ExcelBean>();
		excelBeans.add(new ExcelBean().setName("用户ID").setType("member_id"));
		excelBeans.add(new ExcelBean().setName("师傅姓名").setType("member_real_name"));
		excelBeans.add(new ExcelBean().setName("师傅账号").setType("member_account"));
		excelBeans.add(new ExcelBean().setName("用户昵称").setType("member_nick_name"));
		excelBeans.add(new ExcelBean().setName("支付金额").setType("price"));
		excelBeans.add(new ExcelBean().setName("支付时间").setType("create_time"));
		excelBeans.add(new ExcelBean().setName("押金类型").setType("ping_type_show"));
		List<Object> pingHistoryBeans=workOrderService.exportPingHistoryExcel(pingHistoryBean);
		boolean is_success=ExcelUtils.exportExcel(fileName, excelBeans,pingHistoryBeans);
		if(is_success){
			WriteObject(fileName);
		}else{
			WriteError("导出失败");
		}
	}
	/**
	 * 导出工单excel
	 * @param type
	 * @param workOrderBean
	 */
	@RequestMapping(params="exportWorkOrderExcel")
	public void exportWorkOrderExcel(String type,WorkOrderBean workOrderBean) {
		String fileName="/excel/workOrder/"+TimeUtils.getCurrentTime("yyyyMMddHHmmss")+".xls";
		List<ExcelBean> excelBeans=new ArrayList<ExcelBean>();
		excelBeans.add(new ExcelBean().setName("工单ID").setType("order_id"));
		excelBeans.add(new ExcelBean().setName("用户ID").setType("order_member_id"));
		excelBeans.add(new ExcelBean().setName("预约姓名").setType("order_name"));
		excelBeans.add(new ExcelBean().setName("预约电话").setType("order_phone"));
		excelBeans.add(new ExcelBean().setName("预约地址省").setType("order_address_province"));
		excelBeans.add(new ExcelBean().setName("预约地址市").setType("order_address_city"));
		excelBeans.add(new ExcelBean().setName("预约地址县").setType("order_address_district"));
		excelBeans.add(new ExcelBean().setName("预约详细地址").setType("order_address_detail"));
		excelBeans.add(new ExcelBean().setName("服务内容").setType("order_subscribe_content"));
		excelBeans.add(new ExcelBean().setName("预约备注").setType("order_subscribe_note"));
		excelBeans.add(new ExcelBean().setName("期望服务时间").setType("order_hope_service_time"));
		excelBeans.add(new ExcelBean().setName("预约时间").setType("order_create_time"));
		excelBeans.add(new ExcelBean().setName("上次操作时间").setType("order_update_time"));
		excelBeans.add(new ExcelBean().setName("预约审核通过时间").setType("order_audit_pass_time"));
		excelBeans.add(new ExcelBean().setName("工单状态").setType("order_state"));
		excelBeans.add(new ExcelBean().setName("接单师傅ID").setType("order_accept_id"));
		excelBeans.add(new ExcelBean().setName("接单时间").setType("order_accept_time"));
		excelBeans.add(new ExcelBean().setName("退单原因").setType("order_cancle_why"));
		excelBeans.add(new ExcelBean().setName("退单时间").setType("order_cancle_time"));
		excelBeans.add(new ExcelBean().setName("退单审核通过时间").setType("order_cancle_pass_time"));
		excelBeans.add(new ExcelBean().setName("实际服务内容").setType("order_reality_content"));
		excelBeans.add(new ExcelBean().setName("完工备注").setType("order_complete_note"));
		excelBeans.add(new ExcelBean().setName("开始服务时间").setType("order_service_time"));
		excelBeans.add(new ExcelBean().setName("完工时间").setType("order_complete_time"));
		excelBeans.add(new ExcelBean().setName("完工审核通过时间").setType("order_complete_pass_time"));
		excelBeans.add(new ExcelBean().setName("是否删除").setType("order_is_delete"));
		excelBeans.add(new ExcelBean().setName("分类id").setType("order_class_id"));
		excelBeans.add(new ExcelBean().setName("是否退单").setType("order_is_cancle"));
		excelBeans.add(new ExcelBean().setName("服务态度").setType("order_service_attitude"));
		excelBeans.add(new ExcelBean().setName("服务时效").setType("order_service_aging"));
		excelBeans.add(new ExcelBean().setName("服务质量").setType("order_service_quality"));
		excelBeans.add(new ExcelBean().setName("评价内容").setType("order_evaluate_content"));
		excelBeans.add(new ExcelBean().setName("经度").setType("order_address_longitude"));
		excelBeans.add(new ExcelBean().setName("维度").setType("order_address_latitude"));
		excelBeans.add(new ExcelBean().setName("订单金额").setType("order_price"));
		excelBeans.add(new ExcelBean().setName("工单状态").setType("order_state_show"));
		excelBeans.add(new ExcelBean().setName("投诉内容").setType("complaints_content"));
		excelBeans.add(new ExcelBean().setName("投诉时间").setType("complaints_time"));
		excelBeans.add(new ExcelBean().setName("是否投诉").setType("is_complaints"));
		excelBeans.add(new ExcelBean().setName("押金").setType("deposit_price"));
		excelBeans.add(new ExcelBean().setName("师傅姓名").setType("worker_name"));
		excelBeans.add(new ExcelBean().setName("施工面积").setType("work_area"));
		excelBeans.add(new ExcelBean().setName("期望完工时间").setType("hope_complete_time"));
		excelBeans.add(new ExcelBean().setName("施工要求").setType("work_requirements"));
		excelBeans.add(new ExcelBean().setName("推荐人手机号").setType("recommend_phone"));
		excelBeans.add(new ExcelBean().setName("施工方式").setType("work_way"));
		excelBeans.add(new ExcelBean().setName("其他服务价格").setType("others_price"));
		excelBeans.add(new ExcelBean().setName("服务分类单价").setType("service_class_price"));
		excelBeans.add(new ExcelBean().setName("其他服务内容").setType("others_service_content"));
		excelBeans.add(new ExcelBean().setName("最终支付价格").setType("order_final_price"));
		excelBeans.add(new ExcelBean().setName("工单类型").setType("order_type"));
		List<Object> orderBeans=workOrderService.exportWorkOrderExcel(workOrderBean,type);
		boolean is_success=ExcelUtils.exportExcel(fileName, excelBeans,orderBeans);
		if(is_success){
			WriteObject(fileName);
		}else{
			WriteError("导出失败");
		}
	}
	/**
	 * 工单加锁解锁
	 * @param workOrderBean
	 * @param systemAccountBean
	 * @throws Exception
	 */
	@RequestMapping(params="changeLockState")
	public void changeLockState(WorkOrderBean workOrderBean,SystemAccountBean systemAccountBean) throws Exception {
		WorkOrderBean workOrderBean2=workOrderService.getWorkOrderDetail(workOrderBean);
		if("1".equals(workOrderBean2.getIs_lock())) {
			if(workOrderBean2.getLock_id()==systemAccountBean.getAccount_login_id()) {
				workOrderBean.setIs_lock("0");
			}else {
				WriteError("工单已被ID【"+workOrderBean2.getLock_id()+"】客服锁住");
				return;
			}
		}else {
			workOrderBean.setIs_lock("1").setLock_id(systemAccountBean.getAccount_login_id());
		}
		int num=workOrderService.updateOrder(workOrderBean);
		if(num>0){
			WriteObject("0".equals(workOrderBean.getIs_lock())?"解锁成功":"锁定成功");
		}
	}
	/**
	 * 工单各个状态统计
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getWorkOrderStateCount", method = RequestMethod.POST)
	public void getMemberRefundCount(SystemAccountBean systemAccountBean) throws Exception {
		WriteObject(workOrderService.getWorkOrderStateCount(systemAccountBean));
	}
	/**
	 * 创建工单
	 * @param workOrderBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "createWorkOrder", method = RequestMethod.POST)
	public void createWorkOrder(WorkOrderBean workOrderBean) throws Exception {
		int num=workOrderService.createWorkOrder(workOrderBean);
		if(num>0){
			WriteMsg("预约成功");
		}else{
			WriteError("预约失败");
		}
	}
	/**
	 * 删除服务信息
	 * @param serviceClassBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteServiceClass", method = RequestMethod.POST)
	public void deleteServiceClass(ServiceClassBean serviceClassBean) throws Exception {
		int num=workOrderService.deleteServiceClass(serviceClassBean);
		if(num>0){
			WriteMsg("操作成功");
		}else{
			WriteError("操作失败");
		}
	}
	/**
	 * 更新服务信息
	 * @param serviceClassBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "updateServiceClass", method = RequestMethod.POST)
	public void updateServiceClass(ServiceClassBean serviceClassBean) throws Exception {
		int num=workOrderService.updateServiceClass(serviceClassBean);
		if(num>0){
			WriteMsg("操作成功");
		}else{
			WriteError("操作失败");
		}
	}
	/**
	 * 添加服务类别
	 * @param serviceClassBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "insertServiceClass", method = RequestMethod.POST)
	public void insertServiceClass(ServiceClassBean serviceClassBean) throws Exception {
		int num=workOrderService.insertServiceClass(serviceClassBean);
		if(num>0){
			WriteMsg("操作成功");
		}else{
			WriteError("操作失败");
		}
	}
	/**
	 * 获取服务类别详情
	 
	 * @param serviceClassBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getServiceClassDetail", method = RequestMethod.POST)
	public void getServiceClassDetail(ServiceClassBean serviceClassBean) throws Exception {
		WriteObject(workOrderService.getServiceClassDetail(serviceClassBean));
	}
	/**
	 * 获取服务类别列表
	 
	 * @param pageBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getServiceClassList", method = RequestMethod.POST)
	public void getServiceClassList(ServiceClassBean serviceClassBean,String level) {
		WriteObject(workOrderService.getServiceClassList(serviceClassBean,level==null?1:Integer.valueOf(level)));
	}
	/**
	 * 获取工单（预约）详情
	 * @param workOrderBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getWorkOrderDetail", method = RequestMethod.POST)
	public void getWorkOrderDetail(WorkOrderBean workOrderBean) throws Exception {
		WriteObject(workOrderService.getWorkOrderDetail(workOrderBean));
	}
	/**
	 * 获取退单详情
	 * @param workOrderBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getRefundWorkOrderDetail", method = RequestMethod.POST)
	public void getRefundWorkOrderDetail(WorkOrderBean workOrderBean) throws Exception {
		WriteObject(workOrderService.getRefundWorkOrderDetail(workOrderBean));
	}
	/**
	 * 预约审核
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "updateOrderAuditState", method = RequestMethod.POST)
	public void updateOrderAuditState(String type,WorkOrderBean workOrderBean) throws Exception{
		int num=workOrderService.updateOrderAuditState(workOrderBean,type);
		if(num>0){
			WriteMsg("审核成功");
		}else{
			WriteError("审核失败");
		}
	}
	/**
	 * 获取工单列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getOrderList", method = RequestMethod.POST)
	public void getOrderList(PageBean pageBean,String type,WorkOrderBean workOrderBean) throws Exception {
		WriteObject(workOrderService.getOrderList(workOrderBean,pageBean,type),pageBean.getTotal());
	}
	/**
	 * 删除工单
	 
	 * @param ids
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteOrder", method = RequestMethod.POST)
	public void deleteOrder(String ids) throws Exception{
		int num=workOrderService.deleteOrder(ids);
		if(num>0){
			WriteMsg("删除成功");
		}else{
			WriteError("删除失败");
		}
	}
	/**
	 * 更新工单信息
	 
	 * @param ids
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "updateOrder", method = RequestMethod.POST)
	public void updateOrder(WorkOrderBean workOrderBean) throws Exception{
		int num=workOrderService.updateOrder(workOrderBean);
		if(num>0){
			WriteMsg("更新成功");
		}else{
			WriteError("更新失败");
		}
	}
	/**
	 * 后台手动派单
	 
	 * @param workOrderBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "sendWorkOrder", method = RequestMethod.POST)
	public void sendWorkOrder(WorkOrderBean workOrderBean) throws Exception{
		int num=workOrderService.sendWorkOrder(workOrderBean);
		if(num>0){
			WriteMsg("派单成功");
		}else{
			WriteError("派单失败");
		}
	}
}
