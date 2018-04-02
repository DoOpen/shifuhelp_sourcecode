package com.project.service.interfaces;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberIntegralBean;
import com.project.bean.others.LocationBean;
import com.project.bean.workOrder.ServiceClassBean;
import com.project.bean.workOrder.WorkOrderBean;
import com.project.dao.interfaces.SettingDaoI;
import com.project.dao.interfaces.WorkOrderDaoI;
import com.project.page.PageBean;
import com.project.utils.GaoDeUtils;
import com.project.utils.NumberUtils;
import com.project.utils.TimeUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class WorkOrderServiceI {
	@Autowired
	WorkOrderDaoI workOrderDao;
	@Autowired
	MemberServiceI memberService;
	@Autowired
	SettingDaoI settingDao;
	/**
	 * 通过师傅地址获取最近范围的预约信息
	 * @param memberBean
	 * @return
	 */
	public WorkOrderBean getWorkOrderByDistance(MemberBean memberBean){
		MemberBean memberBean2=memberService.getMemberDetail(memberBean);
		memberBean=memberService.getMemberDetail(memberBean2);
		return workOrderDao.getWorkOrderByDistance(memberBean);
	}
	
	/**
	 * 自定义多级分类
	 */
	public List<ServiceClassBean> getServiceClassList(ServiceClassBean serviceClassBean, int level) {
		List<ServiceClassBean> serviceClassBeans = getServiceClasssByParent(serviceClassBean);
		if (level < 2) {// 只取一级
			return serviceClassBeans;
		}
		return getServiceClassLevel(serviceClassBeans, level, 2);
	}
	/**
	 * 分类列表
	 * 
	 * @param ServiceClassBean
	 * @return
	 */
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
	 * 创建工单
	 * @param workOrderBean
	 * @return
	 * @throws Exception 
	 */
	public int createWorkOrder(WorkOrderBean workOrderBean) throws Exception{
		if(workOrderBean.getRecommend_phone()!=null) {
			MemberBean memberBean=memberService.getMemberDetail(new MemberBean().setMember_account(workOrderBean.getRecommend_phone()).setMember_type("1"));
			if(memberBean==null) {
				throw new Exception("推荐人不存在");
			}else {
				int num=memberService.updateMemberBalance(new MemberBean().setMember_id(memberBean.getMember_id())
						.setMember_integral(NumberUtils.KeepDecimal(memberBean.getMember_integral()+100)));
				if(num==0) {
					throw new Exception("更新用户积分失败");
				}
				num=memberService.insertMemberIntegral(new MemberIntegralBean().setIntegral_type("work_order_recommend")
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
			throw new Exception("地址转经纬度失败");
		}else{
			workOrderBean.setOrder_address_longitude(locationBean.getLongitude()).setOrder_address_latitude(locationBean.getLatitude());
		}
		ServiceClassBean serviceClassBean=workOrderDao.getServiceClassById(new ServiceClassBean().setClass_id(workOrderBean.getOrder_class_id()));
		if(serviceClassBean==null) {
			throw new Exception("服务分类不存在");
		}
		workOrderBean.setOrder_create_time(TimeUtils.getCurrentTime()).setOrder_update_time(TimeUtils.getCurrentTime()).setOrder_state("16")
		.setService_class_price(Float.valueOf(serviceClassBean.getClass_price()));
//		//跳过微信支付
//		workOrderBean.setOrder_state("0").setDeposit_price(0.01f);
		if("0".equals(workOrderBean.getOrder_type())) {
			workOrderBean.setOrder_final_price(Float.valueOf(serviceClassBean.getClass_price())*workOrderBean.getWork_area());
		}else {
			workOrderBean.setOrder_final_price(Float.valueOf(serviceClassBean.getClass_price()));
		}
		workOrderDao.createWorkOrder(workOrderBean);
		return workOrderBean.getOrder_id();
	}
	/**
	 * 获取工单列表
	 * @param pageBean
	 * @return
	 */
	public List<WorkOrderBean> getOrderListByState(PageBean pageBean,String type,WorkOrderBean workOrderBean,MemberBean memberBean) {
		List<Integer> stateList=new LinkedList<Integer>();
		if(type.equals("member_subscribe_wait_audit")){
			//用户-预约待审核
			stateList.add(0);
			workOrderBean.setOrder_member_id(memberBean.getMember_id());
		}else if(type.equals("member_subscribe_wait_pay")){
			//用户-预约待审核
			stateList.add(16);
			workOrderBean.setOrder_member_id(memberBean.getMember_id());
		}else if(type.equals("member_subscribe_audit_not_pass")){
			//用户-预约审核未通过
			stateList.add(1);
			workOrderBean.setOrder_member_id(memberBean.getMember_id());
		}else if(type.equals("worker_wait_accept")){
			//师傅-待接单
			stateList.add(11);
			workOrderBean.setOrder_member_id(null);
			workOrderBean.setOrder_accept_id(memberBean.getMember_id());
		}else if(type.equals("worker_accept_not_service")){
			//师傅-已接单（未服务）
			stateList.add(3);
			workOrderBean.setOrder_member_id(null);
			workOrderBean.setOrder_accept_id(memberBean.getMember_id());
		}else if(type.equals("worker_accept_service")){
			//师傅-已接单（服务中）
			stateList.add(5);
			workOrderBean.setOrder_member_id(null);
			workOrderBean.setOrder_accept_id(memberBean.getMember_id());
		}else if(type.equals("worker_accept_wait_audit")){
			//师傅-已接单（待审核）
			stateList.add(6);
			stateList.add(4);
			stateList.add(7);
			stateList.add(12);
			workOrderBean.setOrder_member_id(null);
			workOrderBean.setOrder_accept_id(memberBean.getMember_id());
		}else if(type.equals("worker_accept_complete")){
			//师傅-已接单（已完成
			stateList.add(8);
			stateList.add(9);
			workOrderBean.setOrder_member_id(null);
			workOrderBean.setOrder_accept_id(memberBean.getMember_id());
		}else if(type.equals("worker_cancle")){
			//师傅-已退单
			workOrderBean.setOrder_accept_id(memberBean.getMember_id());
			List<WorkOrderBean> workOrderBeans=workOrderDao.getRefundOrderList(workOrderBean,pageBean);
			for(WorkOrderBean workOrderBean1:workOrderBeans){
				if(workOrderBean1.getOrder_accept_id()!=null){
					MemberBean memberBean1=new MemberBean();
					memberBean1.setMember_id(workOrderBean1.getOrder_accept_id());
					memberBean1=memberService.getMemberDetail(memberBean);
					workOrderBean1.setOrder_accept(memberBean1);
				}
			}
			return workOrderBeans;
		}else if(type.equals("member_release")){
			//用户-已发布
			stateList.add(2);
			stateList.add(10);
			stateList.add(11);
			workOrderBean.setOrder_member_id(memberBean.getMember_id());
		}else if(type.equals("member_wait_service")){
			//用户-待服务
			stateList.add(3);
			stateList.add(4);
			stateList.add(5);
			stateList.add(6);
			stateList.add(8);
			stateList.add(12);
			workOrderBean.setOrder_member_id(memberBean.getMember_id());
		}else if(type.equals("member_complete")){
			//用户-已完成
			stateList.add(7);
			stateList.add(9);
			workOrderBean.setOrder_member_id(memberBean.getMember_id());
		}else if(type.equals("all")){
			List<String> list=workOrderDao.getOrderStateList();
			for(String s:list){
				stateList.add(Integer.valueOf(s));
			}
		}else{
			return null;
		}
		String s = StringUtils.collectionToDelimitedString(stateList, ",");
		workOrderBean.setStateList(s);
		List<WorkOrderBean> orderList=workOrderDao.getOrderListByState(pageBean,workOrderBean);
		for(WorkOrderBean workOrderBean1:orderList){
			if(workOrderBean1.getOrder_accept_id()!=null){
				MemberBean memberBean1=new MemberBean();
				memberBean1.setMember_id(workOrderBean1.getOrder_accept_id());
				memberBean1=memberService.getMemberDetail(memberBean);
				workOrderBean1.setOrder_accept(memberBean1);
			}
		}
		return orderList;
	}
	/**
	 * 申请退单
	 * @param workOrderBean
	 * @return
	 */
	public int cancleOrder(WorkOrderBean workOrderBean) {
		WorkOrderBean order=workOrderDao.getWorkOrderDetail(workOrderBean);
		if("3".equals(order.getOrder_state())){
			workOrderBean.setOrder_state("4");
		}else if("5".equals(order.getOrder_state())){
			workOrderBean.setOrder_state("6");
		}else{
			return 0;
		}
		workOrderBean.setOrder_update_time(TimeUtils.getCurrentTime()).setOrder_cancle_time(TimeUtils.getCurrentTime()).setOrder_audit_pass_time(TimeUtils.getCurrentTime());
		return workOrderDao.updateWorkOrder(workOrderBean);
	}
	/**
	 * 取消退单
	 * @param workOrderBean
	 * @return
	 */
	public int cancleCancleOrder(WorkOrderBean workOrderBean) {
		WorkOrderBean order=workOrderDao.getWorkOrderDetail(workOrderBean);
		if("4".equals(order.getOrder_state())){
			workOrderBean.setOrder_state("3");
		}else if("6".equals(order.getOrder_state())){
			workOrderBean.setOrder_state("5");
		}
		workOrderBean.setOrder_update_time(TimeUtils.getCurrentTime());
		return workOrderDao.updateWorkOrder(workOrderBean);
	}
	/**
	 * 更改工单状态
	 * @param workOrderBean
	 * @return
	 * @throws Exception 
	 */
	public int updateOrderState(WorkOrderBean workOrderBean,MemberBean memberBean,String type) throws Exception{
		if(type.equals("worker_accept")){
			//师傅接单
			workOrderBean.setOrder_accept_time(TimeUtils.getCurrentTime()).setOrder_state("3").setOrder_accept_id(memberBean.getMember_id());
		}else if(type.equals("worker_service")){
			//师傅开始服务
			workOrderBean.setOrder_service_time(TimeUtils.getCurrentTime()).setOrder_state("5");
		}else if(type.equals("worker_complete")){
			WorkOrderBean workOrderBean2=workOrderDao.getWorkOrderDetail(workOrderBean);
			if(workOrderBean2==null) {
				throw new Exception("工单不存在");
			}
			if(workOrderBean.getOthers_price()==null) {
				workOrderBean.setOthers_price(0f);
			}else if(workOrderBean.getOthers_price()<0) {
				throw new Exception("其他价格不能小于0");
			}
			if(workOrderBean2.getOrder_price()<0) {
				throw new Exception("工单价格不能小于0");
			}
			Float final_price=0f;
			if(workOrderBean2.getOrder_type().equals("0")) {
				final_price+=workOrderBean2.getService_class_price()*workOrderBean2.getWork_area()+workOrderBean.getOthers_price();
				workOrderBean.setOrder_price(workOrderBean2.getService_class_price()*workOrderBean2.getWork_area());
			}else {
				final_price+=workOrderBean2.getService_class_price()+workOrderBean.getOthers_price();
				workOrderBean.setOrder_price(workOrderBean2.getService_class_price());
			}
			if(final_price<=(Float.valueOf(workOrderBean2.getDeposit_price()==null?0:workOrderBean2.getDeposit_price()))) {
				throw new Exception("工单金额必须大于押金");
			}
			workOrderBean.setOrder_final_price(final_price);
			//师傅完工
			workOrderBean.setOrder_complete_time(TimeUtils.getCurrentTime()).setOrder_state("12");
//			//用户完工测试
//			workOrderBean.setOrder_complete_time(TimeUtils.getCurrentTime()).setOrder_state("7");
			int num=workOrderDao.updateWorkOrder(workOrderBean);
			if(num==0) {
				throw new Exception("更新工单信息失败");
			}
			
		}else if(type.equals("member_evaluate")){
			//工单评价
			workOrderBean.setOrder_state("9");
			WorkOrderBean workOrderBean2=workOrderDao.getWorkOrderDetail(workOrderBean);
			MemberBean memberBean2=memberService.getMemberDetail(new MemberBean().setMember_id(workOrderBean2.getOrder_accept_id()));
			if(memberBean2!=null) {
				Float assessment_star1=(memberBean2.getAssessment_star1()*memberBean2.getMember_service_number()+workOrderBean.getOrder_service_attitude())/(memberBean2.getMember_service_number()+1);
				Float assessment_star2=(memberBean2.getAssessment_star2()*memberBean2.getMember_service_number()+workOrderBean.getOrder_service_aging())/(memberBean2.getMember_service_number()+1);
				Float assessment_star3=(memberBean2.getAssessment_star3()*memberBean2.getMember_service_number()+workOrderBean.getOrder_service_quality())/(memberBean2.getMember_service_number()+1);
				Float good_rate=(assessment_star1+assessment_star2+assessment_star3)/15;
				memberService.updateMemberDetail(new MemberBean().setMember_id(memberBean2.getMember_id())
						.setMember_service_number(memberBean2.getMember_service_number()+1)
						.setMember_good_rate(NumberUtils.KeepDecimal(good_rate,2)+"")
						.setAssessment_star1(assessment_star1)
						.setAssessment_star2(assessment_star2)
						.setAssessment_star3(assessment_star3));
			}
		}else if(type.equals("member_complete")){
			//用户完工
			workOrderBean.setOrder_complete_time(TimeUtils.getCurrentTime()).setOrder_state("7");
		}else if(type.equals("pay_over")){
			//押金缴纳成功
			workOrderBean.setOrder_state("0");
		}
		return workOrderDao.updateWorkOrder(workOrderBean);
	}
	/**
	 * 获取工单详情
	 * @param workOrderBean
	 * @return
	 */
	public WorkOrderBean getWorkOrderDetail(WorkOrderBean workOrderBean){
		WorkOrderBean workOrderBean1=workOrderDao.getWorkOrderDetail(workOrderBean);
		if(workOrderBean1!=null){
			MemberBean memberBean=memberService.getMemberDetail(new MemberBean().setMember_id(workOrderBean1.getOrder_accept_id()));
			workOrderBean1.setOrder_accept(memberBean);
			this.buildWorkOrderImgs(workOrderBean1);
		}
		return workOrderBean1;
	}
	/**
	 * 构建图片集合
	 * @param workOrderBean
	 * @return
	 */
	private WorkOrderBean buildWorkOrderImgs(WorkOrderBean workOrderBean) {
		List<String> imgList1=new LinkedList<String>();
		List<String> imgList2=new LinkedList<String>();
		if(workOrderBean.getOrder_subscribe_img1()!=null&&!workOrderBean.getOrder_subscribe_img1().equals("")) {
			imgList1.add(workOrderBean.getOrder_subscribe_img1());
		}
		if(workOrderBean.getOrder_subscribe_img2()!=null&&!workOrderBean.getOrder_subscribe_img2().equals("")) {
			imgList1.add(workOrderBean.getOrder_subscribe_img2());
		}
		if(workOrderBean.getOrder_subscribe_img3()!=null&&!workOrderBean.getOrder_subscribe_img3().equals("")) {
			imgList1.add(workOrderBean.getOrder_subscribe_img3());
		}
		if(workOrderBean.getOrder_complete_img1()!=null&&!workOrderBean.getOrder_complete_img1().equals("")) {
			imgList2.add(workOrderBean.getOrder_complete_img1());
		}
		if(workOrderBean.getOrder_complete_img2()!=null&&!workOrderBean.getOrder_complete_img2().equals("")) {
			imgList2.add(workOrderBean.getOrder_complete_img2());
		}
		if(workOrderBean.getOrder_complete_img3()!=null&&!workOrderBean.getOrder_complete_img3().equals("")) {
			imgList2.add(workOrderBean.getOrder_complete_img3());
		}
		return workOrderBean.setOrderSubscribeImgBeans(imgList1).setOrderCompleteImgBeans(imgList2);
	}
	/**
	 * 抢单
	 * @param workOrderBean
	 * @return
	 * @throws Exception 
	 */
	public int robOrder(WorkOrderBean workOrderBean,MemberBean memberBean) throws Exception{
		return this.updateOrderState(workOrderBean, memberBean, "worker_accept");
	}
	/**
	 * 根据服务类别拼接服务内容
	 * @param serviceClassBean
	 * @return
	 */
	public String getContentByServiceClass(ServiceClassBean serviceClassBean) {
		serviceClassBean=workOrderDao.getServiceClassById(serviceClassBean);
		StringBuffer sb=new StringBuffer();
		String price=serviceClassBean.getClass_price();
		Stack<String> stack=new Stack<String>();
		stack.push(serviceClassBean.getClass_name());
		while(true){
			serviceClassBean=workOrderDao.getParentServiceClass(serviceClassBean);
			if(serviceClassBean!=null){
				stack.push(serviceClassBean.getClass_name());
			}else{
				break;
			}
		}
		while(!stack.isEmpty()){
			sb.append(stack.pop()).append("-");
		}
		sb.append(price);
		return sb.toString();
	}
	public ServiceClassBean getServiceCLassDetail(ServiceClassBean serviceClassBean) {
		return workOrderDao.getServiceClassById(serviceClassBean);
	}

	public WorkOrderBean getRefundWorkOrderDetail(WorkOrderBean workOrderBean) {
		WorkOrderBean workOrderBean1=workOrderDao.getRefundWorkOrderDetail(workOrderBean);
		if(workOrderBean1!=null){
			MemberBean memberBean=memberService.getMemberDetail(new MemberBean().setMember_id(workOrderBean1.getOrder_accept_id()));
			workOrderBean1.setOrder_accept(memberBean);
		}
		return workOrderBean1;
	}
	/**
	 * 工单各个状态统计
	 * @return
	 */
	public Map<String, Integer> getWorkOrderStateCount(MemberBean memberBean) {
		return workOrderDao.getWorkOrderStateCount(memberBean);
	}
	/**
	 * 工单投诉
	 * @param complaintsBean
	 * @return
	 */
	public int workOrderComplaints(WorkOrderBean workOrderBean) {
		return workOrderDao.updateWorkOrder(workOrderBean.setIs_complaints("1").setComplaints_time(TimeUtils.getCurrentTime()));
	}
	/**
	 * 修改工单信息
	 * @param workOrderBean
	 * @return
	 */
	public int updateWorkOrder(WorkOrderBean workOrderBean) {
		return workOrderDao.updateWorkOrder(workOrderBean);
	}
}
