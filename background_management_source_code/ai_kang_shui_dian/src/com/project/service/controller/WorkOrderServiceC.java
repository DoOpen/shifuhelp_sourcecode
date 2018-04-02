package com.project.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberIntegralBean;
import com.project.bean.order.OrderBean;
import com.project.bean.others.LocationBean;
import com.project.bean.system.SystemAccountBean;
import com.project.bean.workOrder.PingHistoryBean;
import com.project.bean.workOrder.ServiceClassBean;
import com.project.bean.workOrder.WorkOrderBean;
import com.project.dao.controller.MemberDaoC;
import com.project.dao.controller.WorkOrderDaoC;
import com.project.page.PageBean;
import com.project.utils.GaoDeUtils;
import com.project.utils.JPushUtils;
import com.project.utils.NumberUtils;
import com.project.utils.TimeUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class WorkOrderServiceC {

	@Autowired
	WorkOrderDaoC workOrderDao;
	@Autowired
	MemberDaoC memberDao;

	/**
	 * 获取工单详情
	 * 
	 * @param workOrderBean
	 * @return
	 */
	public WorkOrderBean getWorkOrderDetail(WorkOrderBean workOrderBean) {
		return workOrderDao.getWorkOrderDetail(workOrderBean);
	}

	/**
	 * 更改工单审核状态
	 * 
	 * @param workOrderBean
	 * @return
	 * @throws Exception 
	 */
	public int updateOrderAuditState(WorkOrderBean workOrderBean, String type) throws Exception {
		WorkOrderBean workOrderBean2=workOrderDao.getWorkOrderDetail(workOrderBean);
		if(workOrderBean2==null) {
			throw new Exception("工单不存在");
		}
		if ("pass".equals(type)) {
			if ("0".equals(workOrderBean2.getOrder_state())) {
				// 预约审核通过
				workOrderBean.setOrder_audit_pass_time(TimeUtils.getCurrentTime()).setOrder_state("2");
			}else if ("4".equals(workOrderBean2.getOrder_state())) {
				// 未服务退单审核通过
				workOrderBean.setOrder_cancle_pass_time(TimeUtils.getCurrentTime()).setOrder_state("2");
				WorkOrderBean workOrderBean1 = workOrderDao.getWorkOrderDetail(workOrderBean);
				workOrderDao.createRefundWorkOrder(workOrderBean1.setOrder_state("13"));
			}else if ("6".equals(workOrderBean2.getOrder_state())) {
				// 服务中退单审核通过
				workOrderBean.setOrder_cancle_pass_time(TimeUtils.getCurrentTime()).setOrder_state("2")
						.setOrder_is_cancle(1);
				WorkOrderBean workOrderBean1 = workOrderDao.getWorkOrderDetail(workOrderBean);
				workOrderDao.createRefundWorkOrder(workOrderBean1.setOrder_state("14"));
			}else if ("12".equals(workOrderBean2.getOrder_state())) {
				// 已完工审核通过
				workOrderBean.setOrder_complete_pass_time(TimeUtils.getCurrentTime()).setOrder_state("8");
			}
		} else {
			if ("0".equals(workOrderBean2.getOrder_state())) {
				// 预约审核未通过
				workOrderBean.setOrder_audit_pass_time(TimeUtils.getCurrentTime()).setOrder_state("1").setOrder_is_delete("1");
			}else if ("4".equals(workOrderBean2.getOrder_state())) {
				// 未服务退单审核未通过
				workOrderBean.setOrder_cancle_pass_time(TimeUtils.getCurrentTime()).setOrder_state("3");
			}else if ("6".equals(workOrderBean2.getOrder_state())) {
				// 服务中退单审核未通过
				workOrderBean.setOrder_cancle_pass_time(TimeUtils.getCurrentTime()).setOrder_state("5");
			}else if ("12".equals(workOrderBean2.getOrder_state())) {
				// 已完工审核未通过
				workOrderBean.setOrder_cancle_pass_time(TimeUtils.getCurrentTime()).setOrder_state("5");
			}
		}
		workOrderBean.setOrder_update_time(TimeUtils.getCurrentTime()).setIs_lock("0");
		int num= workOrderDao.updateOrder(workOrderBean);
		if(num>0&&type.equals("pass")&&"0".equals(workOrderBean2.getOrder_state())) {
			//群发抢单信息
			this.massRobberyWorkOrder(workOrderBean2);
		}
		return num;
	}

	/**
	 * 群发抢单信息
	 * @param workOrderBean
	 */
	private void massRobberyWorkOrder(WorkOrderBean workOrderBean) {
		WorkOrderBean workOrderBean2=workOrderDao.getWorkOrderDetail(workOrderBean);
		List<MemberBean> memberBeans=memberDao.getWorkerListByOrderAddress(workOrderBean2);
		if(memberBeans!=null) {
			for(MemberBean memberBean:memberBeans) {
				System.out.println("抢单推送成功，推送用户id"+memberBean.getMember_id());
				JPushUtils.myJPushClient("你附近有新工单来了，赶紧去抢单吧!", memberBean.getMember_id()+"", "grab_work_order");
			}
		}
	}

	/**
	 * 获取工单列表
	 * 
	 * @param pageBean
	 * @param type
	 * @return
	 */
	public List<WorkOrderBean> getOrderList(WorkOrderBean workOrderBean, PageBean pageBean, String type) {
		StringBuffer sb = new StringBuffer();
		if ("wait_accept".equals(type)) {
			// 待接单
			sb.append("2,11,");
		} else if ("complete_accept".equals(type)) {
			// 已接单
			sb.append("3,5,8,");
		} else if ("cancel".equals(type)) {
			//已退单
			return workOrderDao.getRefundOrderList(pageBean);
		} else if ("wait_audit".equals(type)) {
			// 待审核
			sb.append("0,4,6,12,");
		} else if ("complete".equals(type)) {
			// 已完成
			sb.append("7,9,");
		} else if ("loss".equals(type)) {
			// 超时
			sb.append("10,");
		} else if ("exception".equals(type)) {
			// 异常
			sb.append("1,15,");
			workOrderBean.setIs_complaints("1");
		} 
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
			workOrderBean.setStateList(sb.toString());
		}
		List<WorkOrderBean> workOrderBeans=workOrderDao.getOrderList(workOrderBean, pageBean);
		if(workOrderBeans!=null) {
			for(WorkOrderBean workOrderBean2:workOrderBeans) {
				MemberBean memberBean=memberDao.getMemberDetail(new MemberBean().setMember_id(workOrderBean2.getOrder_accept_id()));
				workOrderBean2.setOrder_accept(memberBean);
			}
		}
		return workOrderBeans;
	}

	/**
	 * 删除工单
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteOrder(String ids) {
		return workOrderDao.deleteOrder(ids);
	}

	/**
	 * 更新工单信息
	 * 
	 * @param workOrderBean
	 * @return
	 * @throws Exception 
	 */
	public int updateOrder(WorkOrderBean workOrderBean) throws Exception {
		LocationBean locationBean = GaoDeUtils.addressToLocation(workOrderBean.getOrder_address_province()+workOrderBean.getOrder_address_city()+workOrderBean.getOrder_address_country()+workOrderBean.getOrder_address_detail());
		if(locationBean.getLongitude()==null||locationBean.getLatitude()==null){
			throw new Exception("地址转经纬度失败");
		}else{
			workOrderBean.setOrder_address_longitude(locationBean.getLongitude()).setOrder_address_latitude(locationBean.getLatitude());
		}
		ServiceClassBean serviceClassBean=workOrderDao.getServiceClassDetail(new ServiceClassBean().setClass_id(workOrderBean.getOrder_class_id()));
		if(serviceClassBean==null) {
			throw new Exception("服务分类不存在");
		}else {
			workOrderBean.setService_class_price(Float.valueOf(serviceClassBean.getClass_price()));
		}
		return workOrderDao.updateOrder(workOrderBean);
	}

	/**
	 * 后台手动派单
	 * 
	 * @param workOrderBean
	 * @return
	 */
	public int sendWorkOrder(WorkOrderBean workOrderBean) {
		workOrderBean.setOrder_update_time(TimeUtils.getCurrentTime()).setOrder_accept_time(TimeUtils.getCurrentTime())
				.setOrder_state("11").setOrder_audit_pass_time(TimeUtils.getCurrentTime());
		int num=workOrderDao.updateOrder(workOrderBean);
		if(num>0) {
			JPushUtils.myJPushClient("收到新得派单，请及时接受!", workOrderBean.getOrder_accept_id()+"", "send_work_order");
		}
		return num;
	}

	/**
	 * 获取服务类别列表
	 * 
	 * @param pageBean
	 * @return
	 */
	public List<ServiceClassBean> getServiceClassList(ServiceClassBean serviceClassBean, int level) {
		List<ServiceClassBean> serviceClassBeans = getServiceClasssByParent(serviceClassBean);
		if (level < 2) {// 只取一级
			return serviceClassBeans;
		}
		return getServiceClassLevel(serviceClassBeans, level, 2);
	}
	public List<ServiceClassBean> getServiceClasssByParent(ServiceClassBean ServiceClassBean) {
		return workOrderDao.getServiceClassList(ServiceClassBean);
	}
	/**
	 * 
	 * @param goodsBeans
	 * @param level
	 *            总共需要取层级
	 * @param start
	 *            目前取到的层级
	 * @return
	 */
	public List<ServiceClassBean> getServiceClassLevel(List<ServiceClassBean> serviceClassBeans, int level, int start) {
		if (serviceClassBeans != null) {
			for (int i = 0; i < serviceClassBeans.size(); i++) {
				List<ServiceClassBean> serviceClassBeans2 = getServiceClasssByParent(
						new ServiceClassBean().setClass_parent_id(serviceClassBeans.get(i).getClass_id()));
				if (start < level && serviceClassBeans2 != null) {// 目前取到的层级 小于
					// 总共需要取层级时 向下取
					serviceClassBeans2 = getServiceClassLevel(serviceClassBeans2, level, start + 1);
				}
				serviceClassBeans.get(i).setServiceClassBeans(serviceClassBeans2);
			}
		}
		return serviceClassBeans;
	}
	/**
	 * 获取服务类别详情
	 * 
	 * @param serviceClassBean
	 * @return
	 */
	public ServiceClassBean getServiceClassDetail(ServiceClassBean serviceClassBean) {
		return workOrderDao.getServiceClassDetail(serviceClassBean);
	}

	/**
	 * 添加服务类别
	 * 
	 * @param serviceClassBean
	 * @return
	 */
	public int insertServiceClass(ServiceClassBean serviceClassBean) {
		return workOrderDao.insertServiceClass(serviceClassBean);
	}

	/**
	 * 更爱服务详情
	 * 
	 * @param serviceClassBean
	 * @return
	 */
	public int updateServiceClass(ServiceClassBean serviceClassBean) {
		return workOrderDao.updateServiceClass(serviceClassBean);
	}

	/**
	 * 删除服务信息
	 * 
	 * @param serviceClassBean
	 * @return
	 */
	public int deleteServiceClass(ServiceClassBean serviceClassBean) {
		return workOrderDao.deleteServiceClass(serviceClassBean);
	}

	/**
	 * 创建工单
	 * @param workOrderBean
	 * @return
	 * @throws Exception 
	 */
	public int createWorkOrder(WorkOrderBean workOrderBean) throws Exception{
		if(workOrderBean.getRecommend_phone()!=null) {
			MemberBean memberBean=memberDao.getMemberDetail(new MemberBean().setMember_account(workOrderBean.getRecommend_phone()).setMember_type("1"));
			if(memberBean==null) {
				throw new Exception("邀请人不存在");
			}else {
				int num=memberDao.updateMemberBalance(new MemberBean().setMember_id(memberBean.getMember_id())
						.setMember_integral(NumberUtils.KeepDecimal(memberBean.getMember_integral()+100)));
				if(num==0) {
					throw new Exception("更新用户积分失败");
				}
				num=memberDao.insertMemberIntegral(new MemberIntegralBean().setIntegral_type("work_order_recommend")
						.setIntegral_value(100)
						.setMember_id(memberBean.getMember_id())
						.setState("add"));
				if(num==0) {
					throw new Exception("积分记录添加失败");
				}
			}
		}
		LocationBean locationBean = GaoDeUtils.addressToLocation(workOrderBean.getOrder_address_province()+workOrderBean.getOrder_address_city()+workOrderBean.getOrder_address_country()+workOrderBean.getOrder_address_detail());
		if(locationBean.getLongitude()==null||locationBean.getLatitude()==null){
			return 0;
		}else{
			workOrderBean.setOrder_address_longitude(locationBean.getLongitude()).setOrder_address_latitude(locationBean.getLatitude());
		}
		if(workOrderBean.getOrder_accept_id()!=null) {
			workOrderBean.setOrder_state("11").setOrder_accept_time(TimeUtils.getCurrentTime());
		}else {
			workOrderBean.setOrder_state("2");
		}
		ServiceClassBean serviceClassBean=workOrderDao.getServiceClassDetail(new ServiceClassBean().setClass_id(workOrderBean.getOrder_class_id()));
		workOrderBean.setOrder_subscribe_content(workOrderBean.getOrder_subscribe_content()+serviceClassBean.getClass_price()+serviceClassBean.getClass_unit())
		.setService_class_price(Float.valueOf(serviceClassBean.getClass_price()))
		.setOrder_create_time(TimeUtils.getCurrentTime())
		.setOrder_update_time(TimeUtils.getCurrentTime())
		.setOrder_audit_pass_time(TimeUtils.getCurrentTime());
		if("0".equals(workOrderBean.getOrder_type())) {
			workOrderBean.setOrder_final_price(Float.valueOf(serviceClassBean.getClass_price())*workOrderBean.getWork_area());
		}else {
			workOrderBean.setOrder_final_price(Float.valueOf(serviceClassBean.getClass_price()));
		}
		return workOrderDao.createWorkOrder(workOrderBean);
	}
	/**
	 * 工单各个状态统计
	 * @return
	 */
	public Map<String, Integer> getWorkOrderStateCount(SystemAccountBean systemAccountBean) {
		return workOrderDao.getWorkOrderStateCount(systemAccountBean);
	}
	/**
	 * 支付记录
	 * @param pingHistoryBean
	 * @param pageBean
	 * @return
//	 */
	public List<PingHistoryBean> getPingHistoryList(PingHistoryBean pingHistoryBean, PageBean pageBean) {
		List<PingHistoryBean> pingHistoryBeans=workOrderDao.getPingHistoryList(pingHistoryBean,pageBean);
		for(PingHistoryBean pingHistoryBean2:pingHistoryBeans) {
			MemberBean memberBean=memberDao.getMemberDetail(new MemberBean().setMember_id(pingHistoryBean2.getMember_id()));
			pingHistoryBean2.setMemberBean(memberBean);
		}
		return pingHistoryBeans;
	}

	/**
	 * 押金统计
	 * @param orderBean
	 * @return
	 */
	public Map<String, Object> getFinanceStatistics(OrderBean orderBean) {
		Map<String, Object> map= workOrderDao.getFinanceStatistics(orderBean);
		for(Map.Entry<String, Object> entry:map.entrySet()) {
			map.put(entry.getKey(), NumberUtils.KeepDecimal(entry.getValue()+"",2));
		}
		return map;
	}
	/**
	 * 派单师傅列表
	 * @param workOrderBean
	 * @param pageBean
	 * @return
	 * @throws Exception 
	 */
	public List<MemberBean> getSendWorkOrderWorkerList(Map<String, String> params, PageBean pageBean) throws Exception {
		WorkOrderBean workOrderBean2=workOrderDao.getWorkOrderDetail(new WorkOrderBean().setOrder_id(Integer.valueOf(params.get("order_id"))));
		if(workOrderBean2==null) {
			throw new Exception("工单不存在");
		}
		params.put("order_address_longitude", workOrderBean2.getOrder_address_longitude());
		params.put("order_address_latitude", workOrderBean2.getOrder_address_latitude());
		for(Map.Entry<String, String> entry:params.entrySet()) {
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
		return workOrderDao.getSendWorkOrderWorkerList(params,pageBean);
	}
	/**
	 * 导出押金记录excel
	 * @param pingHistoryBean
	 * @return
	 */
	public List<Object> exportPingHistoryExcel(PingHistoryBean pingHistoryBean) {
		return workOrderDao.exportPingHistoryExcel(pingHistoryBean);
	}
	/**
	 * 导出工单excel
	 * @param workOrderBean
	 * @param type
	 * @return
	 */
	public List<Object> exportWorkOrderExcel(WorkOrderBean workOrderBean, String type) {
		StringBuffer sb = new StringBuffer();
		if ("wait_accept".equals(type)) {
			// 待接单
			sb.append("2,11,");
		} else if ("complete_accept".equals(type)) {
			// 已接单
			sb.append("3,5,");
		} else if ("cancl".equals(type)) {
			return workOrderDao.exportRefundOrderExcel();
		} else if ("wait_audit".equals(type)) {
			// 待审核
			sb.append("0,7,4,6,12,");
		} else if ("complete".equals(type)) {
			// 已完成
			sb.append("8,9,");
		} else if ("loss".equals(type)) {
			// 超时
			sb.append("10,");
		} else if ("exception".equals(type)) {
			// 异常
			sb.append("1,15,");
			workOrderBean.setIs_complaints("1");
		} 
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
			workOrderBean.setStateList(sb.toString());
		}
		List<Object> workOrderBeans=workOrderDao.exportWorkOrderExcel(workOrderBean);
		return workOrderBeans;
	}
	/**
	 * 获取退单详情
	 * @param workOrderBean
	 * @return
	 */
	public WorkOrderBean getRefundWorkOrderDetail(WorkOrderBean workOrderBean) {
		return workOrderDao.getRefundWorkOrderDetail(workOrderBean);
	}
}
