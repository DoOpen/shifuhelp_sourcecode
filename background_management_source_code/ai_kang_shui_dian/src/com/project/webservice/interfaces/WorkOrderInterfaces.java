package com.project.webservice.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.bean.member.MemberBean;
import com.project.bean.workOrder.ServiceClassBean;
import com.project.bean.workOrder.WorkOrderBean;
import com.project.others.NotToken;
import com.project.page.PageBean;
import com.project.service.interfaces.MemberServiceI;
import com.project.service.interfaces.WorkOrderServiceI;
import com.project.webservice.BaseController;

@Controller
@RequestMapping("/workOrderInterfaces.api")
public class WorkOrderInterfaces extends BaseController {

	@Autowired
	WorkOrderServiceI workOrderService;
	@Autowired
	MemberServiceI memberService;
	/**
	 * 工单投诉
	 * @param complaintsBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "workOrderComplaints", method = RequestMethod.POST)
	public void workOrderComplaints(WorkOrderBean workOrderBean,MemberBean memberBean) throws Exception {
		WorkOrderBean workOrderBean2=workOrderService.getWorkOrderDetail(workOrderBean);
		if(workOrderBean2==null) {
			WriteError("工单不存在");
			return;
		}else if(!"8".equals(workOrderBean2.getOrder_state())){
			WriteError("该状态不允许投诉");
			return;
		}
		int num=workOrderService.workOrderComplaints(workOrderBean);
		if(num>0){
			WriteMsg("操作成功");
		}else{
			WriteError("操作失败");
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
	public void getMemberRefundCount(MemberBean memberBean) throws Exception {
		WriteObject(workOrderService.getWorkOrderStateCount(memberBean));
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
	 *获取服务类别详情
	 * @param memberBean
	 * @param serviceClassBean
	 * @param request
	 * @param response
	 */
	@NotToken
	@RequestMapping(params = "getServiceCLassDetail", method = RequestMethod.POST)
	public void getServiceCLassDetail(MemberBean memberBean,ServiceClassBean serviceClassBean,HttpServletRequest request, HttpServletResponse response) {
		WriteObject(workOrderService.getServiceCLassDetail(serviceClassBean));
	}
	/**
	 * 根据服务类别拼接服务内容
	 * @param memberBean
	 * @param serviceClassBean
	 * @param request
	 * @param response
	 */
	@NotToken
	@RequestMapping(params = "getContentByServiceClass", method = RequestMethod.POST)
	public void getContentByServiceClass(ServiceClassBean serviceClassBean) {
		WriteObject(workOrderService.getContentByServiceClass(serviceClassBean));
	}
	/**
	 * 获取最近可抢单
	 * @param memberBean
	 * @param request
	 * @param response
	 */
	@NotToken
	@RequestMapping(params = "getWorkOrderByDistance", method = RequestMethod.POST)
	public void getWorkOrderByDistance(MemberBean memberBean) {
		WriteObject(workOrderService.getWorkOrderByDistance(memberBean));
	}
	/**
	 * 自定义多级服务分类
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getServiceClassList", method = RequestMethod.POST)
	public void getServiceClassList(ServiceClassBean serviceClassBean,String level) {
		WriteObject(workOrderService.getServiceClassList(serviceClassBean,level==null?1:Integer.valueOf(level)));
	}
	/**
	 * 创建工单
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "createWorkOrder", method = RequestMethod.POST)
	public void createWorkOrder(WorkOrderBean workOrderBean,MemberBean memberBean) throws Exception {
		workOrderBean.setOrder_member_id(memberBean.getMember_id());
		int order_id=workOrderService.createWorkOrder(workOrderBean);
		if(order_id>0){
			WriteObject(order_id);
		}else{
			WriteError("预约失败");
		}
	}
	/**
	 * 获取工单（预约）详情
	 * @param workOrderBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getWorkOrderDetail", method = RequestMethod.POST)
	public void getWorkOrderDetail(WorkOrderBean workOrderBean) throws Exception {
		WriteObject(workOrderService.getWorkOrderDetail(workOrderBean));
	}
	/**
	 * 获取工单信息列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getOrderListByState", method = RequestMethod.POST)
	public void getOrderListByState(PageBean pageBean,String type,MemberBean memberBean,WorkOrderBean workOrderBean) throws Exception {
		WriteObject(workOrderService.getOrderListByState(pageBean,type,workOrderBean,memberBean),pageBean.getTotal());
	}
	/**
	 * 申请退单
	 * @param workOrderBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "cancleOrder", method = RequestMethod.POST)
	public void cancleOrder(WorkOrderBean workOrderBean) throws Exception {
		int num=workOrderService.cancleOrder(workOrderBean);
		if(num>0){
			WriteMsg("退单成功");
		}else{
			WriteError("退单失败");
		}
	}
	/**
	 * 取消退单
	 * @param workOrderBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "cancleCancleOrder", method = RequestMethod.POST)
	public void cancleCancleOrder(WorkOrderBean workOrderBean) throws Exception {
		int num=workOrderService.cancleCancleOrder(workOrderBean);
		if(num>0){
			WriteMsg("取消退单成功");
		}else{
			WriteError("取消退单失败");
		}
	}
	/**
	 * 更改工单状态
	 * @param workOrderBean
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "updateOrderState", method = RequestMethod.POST)
	public void updateOrderState(WorkOrderBean workOrderBean,String type,MemberBean memberBean) throws Exception{
		int num=workOrderService.updateOrderState(workOrderBean,memberBean,type);
		if(num>0){
			WriteMsg("操作成功");
		}else{
			WriteError("操作失败");
		}
	}
	/**
	 * 修改工单信息
	 * @param workOrderBean
	 * @param type
	 * @param memberBean
	 * @throws Exception
	 */
	@RequestMapping(params = "updateWorkOrder", method = RequestMethod.POST)
	public void updateWorkOrder(WorkOrderBean workOrderBean) throws Exception{
		int num=workOrderService.updateWorkOrder(workOrderBean);
		if(num>0){
			WriteMsg("操作成功");
		}else{
			WriteError("操作失败");
		}
	}
	/**
	 * 师傅抢单
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "robOrder", method = RequestMethod.POST)
	public synchronized void robOrder(MemberBean memberBean,WorkOrderBean workOrderBean)throws Exception {
		WorkOrderBean order=workOrderService.getWorkOrderDetail(workOrderBean);
		if(!"2".equals(order.getOrder_state())){
			WriteError("工单当前不可抢!");
			return;
		}
		int num=workOrderService.robOrder(workOrderBean,memberBean);
		if (num>0) {
			WriteMsg("抢单成功");
		} else {
			WriteError("抢单失败");
		}
	}
}
