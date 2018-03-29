package com.project.bean.workOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.project.bean.member.MemberBean;

public class WorkOrderBean {
	private Integer order_id;
	private Integer order_member_id;
	private String order_name;
	private String order_phone;
	private String order_address_country;
	private String order_address_province;
	private String order_address_city;
	private String order_address_district;
	private String order_address_detail;
	private String order_subscribe_content;
	private String order_subscribe_note;
	private String order_subscribe_img1;
	private String order_subscribe_img2;
	private String order_subscribe_img3;
	private List<String> orderSubscribeImgBeans;
	private String order_hope_service_time;
	private String order_create_time;
	private String order_update_time;
	private String order_audit_pass_time;
	private String order_state;
	private Integer order_accept_id;
	private String order_accept_time;
	private String order_cancle_why;
	private String order_cancle_time;
	private String order_cancle_pass_time;
	private String order_reality_content;
	private List<String> orderCompleteImgBeans;
	private String order_complete_img1;
	private String order_complete_img2;
	private String order_complete_img3;
	private String order_complete_note;
	private String order_service_time;
	private String order_complete_time;
	private String order_complete_pass_time;
	private String order_is_delete;
	private Integer order_class_id;
	private MemberBean order_accept;
	private Integer order_is_cancle;
	private Integer order_service_attitude;
	private Integer order_service_aging;
	private Integer order_service_quality;
	private String order_evaluate_content;
	private String stateList;
	private String order_address_longitude;
	private String order_address_latitude;
	private String distance;
	private Float order_price;
	private String order_state_show;
	private String is_today_order;
	private String complaints_content;
	private String complaints_time;
	private String is_complaints;
	private String district;
	private Float deposit_price;
	private String is_lock;
	private Integer lock_id;
	private String worker_name;
	private Float work_area;
	private String hope_complete_time;
	private String work_requirements;
	private String recommend_phone;
	private String work_way;//施工方式
	private Float others_price;//其他价格
	private Float service_class_price;//服务分类单价
	private String others_service_content;//其他服务内容
	private Float order_final_price;//最终支付价格
	private String order_type;//工单类型 install:安装 maintenance:维修
	public Float getService_class_price() {
		return service_class_price;
	}
	public WorkOrderBean setService_class_price(Float service_class_price) {
		this.service_class_price = service_class_price;
		return this;
	}
	public String getOrder_type() {
		return order_type;
	}
	public WorkOrderBean setOrder_type(String order_type) {
		this.order_type = order_type;
		return this;
	}
	public Float getOrder_final_price() {
		return order_final_price;
	}
	public WorkOrderBean setOrder_final_price(Float order_final_price) {
		this.order_final_price = order_final_price;
		return this;
	}
	public Float getOthers_price() {
		return others_price;
	}
	public WorkOrderBean setOthers_price(Float others_price) {
		this.others_price = others_price;
		return this;
	}
	public String getOthers_service_content() {
		return others_service_content;
	}
	public WorkOrderBean setOthers_service_content(String others_service_content) {
		this.others_service_content = others_service_content;
		return this;
	}
	public String getWork_way() {
		return work_way;
	}
	public WorkOrderBean setWork_way(String work_way) {
		this.work_way = work_way;
		return this;
	}
	public Float getWork_area() {
		return work_area;
	}
	public WorkOrderBean setWork_area(Float work_area) {
		this.work_area = work_area;
		return this;
	}
	public String getHope_complete_time() {
		return hope_complete_time;
	}
	public WorkOrderBean setHope_complete_time(String hope_complete_time) {
		this.hope_complete_time = hope_complete_time;
		return this;
	}
	public String getWork_requirements() {
		return work_requirements;
	}
	public WorkOrderBean setWork_requirements(String work_requirements) {
		this.work_requirements = work_requirements;
		return this;
	}
	public String getRecommend_phone() {
		return recommend_phone;
	}
	public WorkOrderBean setRecommend_phone(String recommend_phone) {
		this.recommend_phone = recommend_phone;
		return this;
	}
	public List<String> getOrderSubscribeImgBeans() {
		return orderSubscribeImgBeans;
	}
	public WorkOrderBean setOrderSubscribeImgBeans(List<String> orderSubscribeImgBeans) {
		this.orderSubscribeImgBeans = orderSubscribeImgBeans;
		return this;
	}
	public List<String> getOrderCompleteImgBeans() {
		return orderCompleteImgBeans;
	}
	public WorkOrderBean setOrderCompleteImgBeans(List<String> orderCompleteImgBeans) {
		this.orderCompleteImgBeans = orderCompleteImgBeans;
		return this;
	}
	public String getWorker_name() {
		return worker_name;
	}
	public WorkOrderBean setWorker_name(String worker_name) {
		this.worker_name = worker_name;
		return this;
	}
	public String getComplaints_content() {
		return complaints_content;
	}
	public WorkOrderBean setComplaints_content(String complaints_content) {
		this.complaints_content = complaints_content;
		return this;
	}
	public String getComplaints_time() {
		return complaints_time;
	}
	public WorkOrderBean setComplaints_time(String complaints_time) {
		this.complaints_time = complaints_time;
		return this;
	}
	public String getIs_complaints() {
		return is_complaints;
	}
	public WorkOrderBean setIs_complaints(String is_complaints) {
		this.is_complaints = is_complaints;
		return this;
	}
	public String getIs_today_order() {
		return is_today_order;
	}
	public WorkOrderBean setIs_today_order(String is_today_order) {
		this.is_today_order = is_today_order;
		return this;
	}
	public String getOrder_state_show() {
		return order_state_show;
	}
	public WorkOrderBean setOrder_state_show(String order_state_show) {
		this.order_state_show = order_state_show;
		return this;
	}
	public Float getOrder_price() {
		return order_price;
	}
	public WorkOrderBean setOrder_price(Float order_price) {
		this.order_price = order_price;
		return this;
	}
	public String getDistance() {
		return distance;
	}
	public WorkOrderBean setDistance(String distance) {
		this.distance = distance;
		return this;
	}
	public String getOrder_address_longitude() {
		return order_address_longitude;
	}
	public WorkOrderBean setOrder_address_longitude(String order_address_longitude) {
		this.order_address_longitude = order_address_longitude;
		return this;
	}
	public String getOrder_address_latitude() {
		return order_address_latitude;
	}
	public WorkOrderBean setOrder_address_latitude(String order_address_latitude) {
		this.order_address_latitude = order_address_latitude;
		return this;
	}
	public String getStateList() {
		return stateList;
	}
	public WorkOrderBean setStateList(String stateList) {
		this.stateList = stateList;
		return this;
	}
	public Integer getOrder_service_attitude() {
		return order_service_attitude;
	}
	public WorkOrderBean setOrder_service_attitude(Integer order_service_attitude) {
		this.order_service_attitude = order_service_attitude;
		return this;
	}
	public Integer getOrder_service_aging() {
		return order_service_aging;
	}
	public WorkOrderBean setOrder_service_aging(Integer order_service_aging) {
		this.order_service_aging = order_service_aging;
		return this;
	}
	public Integer getOrder_service_quality() {
		return order_service_quality;
	}
	public WorkOrderBean setOrder_service_quality(Integer order_service_quality) {
		this.order_service_quality = order_service_quality;
		return this;
	}
	public String getOrder_evaluate_content() {
		return order_evaluate_content;
	}
	public WorkOrderBean setOrder_evaluate_content(String order_evaluate_content) {
		this.order_evaluate_content = order_evaluate_content;
		return this;
	}
	public Integer getOrder_is_cancle() {
		return order_is_cancle;
	}
	public WorkOrderBean setOrder_is_cancle(Integer order_is_cancle) {
		this.order_is_cancle = order_is_cancle;
		return this;
	}
	public MemberBean getOrder_accept() {
		return order_accept;
	}
	public WorkOrderBean setOrder_accept(MemberBean order_accept) {
		this.order_accept = order_accept;
		return this;
	}
	public Integer getOrder_class_id() {
		return order_class_id;
	}
	public WorkOrderBean setOrder_class_id(Integer order_class_id) {
		this.order_class_id = order_class_id;
		return this;
	}
	public String getOrder_address_country() {
		return order_address_country;
	}
	public WorkOrderBean setOrder_address_country(String order_address_country) {
		this.order_address_country = order_address_country;
		return this;
	}
	public String getOrder_address_province() {
		return order_address_province;
	}
	public WorkOrderBean setOrder_address_province(String order_address_province) {
		this.order_address_province = order_address_province;
		return this;
	}
	public String getOrder_address_city() {
		return order_address_city;
	}
	public WorkOrderBean setOrder_address_city(String order_address_city) {
		this.order_address_city = order_address_city;
		return this;
	}
	public String getOrder_address_district() {
		return order_address_district;
	}
	public WorkOrderBean setOrder_address_district(String order_address_district) {
		this.order_address_district = order_address_district;
		return this;
	}
	public String getOrder_address_detail() {
		return order_address_detail;
	}
	public WorkOrderBean setOrder_address_detail(String order_address_detail) {
		this.order_address_detail = order_address_detail;
		return this;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public WorkOrderBean setOrder_id(Integer order_id) {
		this.order_id = order_id;
		return this;
	}
	public Integer getOrder_member_id() {
		return order_member_id;
	}
	public WorkOrderBean setOrder_member_id(Integer order_member_id) {
		this.order_member_id = order_member_id;
		return this;
	}
	public String getOrder_name() {
		return order_name;
	}
	public WorkOrderBean setOrder_name(String order_name) {
		this.order_name = order_name;
		return this;
	}
	public String getOrder_phone() {
		return order_phone;
	}
	public WorkOrderBean setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
		return this;
	}
	public String getOrder_subscribe_content() {
		return order_subscribe_content;
	}
	public WorkOrderBean setOrder_subscribe_content(String order_subscribe_content) {
		this.order_subscribe_content = order_subscribe_content;
		return this;
	}
	public String getOrder_subscribe_note() {
		return order_subscribe_note;
	}
	public WorkOrderBean setOrder_subscribe_note(String order_subscribe_note) {
		this.order_subscribe_note = order_subscribe_note;
		return this;
	}
	public String getOrder_subscribe_img1() {
		return order_subscribe_img1;
	}
	public WorkOrderBean setOrder_subscribe_img1(String order_subscribe_img1) {
		this.order_subscribe_img1 = order_subscribe_img1;
		return this;
	}
	public String getOrder_subscribe_img2() {
		return order_subscribe_img2;
	}
	public WorkOrderBean setOrder_subscribe_img2(String order_subscribe_img2) {
		this.order_subscribe_img2 = order_subscribe_img2;
		return this;
	}
	public String getOrder_subscribe_img3() {
		return order_subscribe_img3;
	}
	public WorkOrderBean setOrder_subscribe_img3(String order_subscribe_img3) {
		this.order_subscribe_img3 = order_subscribe_img3;
		return this;
	}
	public String getOrder_hope_service_time() {
		return order_hope_service_time;
	}
	public WorkOrderBean setOrder_hope_service_time(String order_hope_service_time) {
		this.order_hope_service_time = order_hope_service_time;
		return this;
	}
	public String getOrder_create_time() {
		return order_create_time;
	}
	public WorkOrderBean setOrder_create_time(String order_create_time) {
		this.order_create_time = order_create_time;
		return this;
	}
	public String getOrder_update_time() {
		return order_update_time;
	}
	public WorkOrderBean setOrder_update_time(String order_update_time) {
		this.order_update_time = order_update_time;
		return this;
	}
	public String getOrder_audit_pass_time() {
		return order_audit_pass_time;
	}
	public WorkOrderBean setOrder_audit_pass_time(String order_audit_pass_time) {
		this.order_audit_pass_time = order_audit_pass_time;
		return this;
	}
	public String getOrder_state() {
		return order_state;
	}
	public WorkOrderBean setOrder_state(String order_state) {
		this.order_state = order_state;
		switch(order_state){
		case "0":
			this.setOrder_state_show("预约待审核");
			break;
		case "1":
			this.setOrder_state_show("预约审核未通过");
			break;
		case "2":
			this.setOrder_state_show("已发布（待抢单）");
			break;
		case "3":
			this.setOrder_state_show("未服务");
			break;
		case "4":
			this.setOrder_state_show("未服务退单审核");
			break;
		case "5":
			this.setOrder_state_show("服务中");
			break;
		case "6":
			this.setOrder_state_show("服务中退单审核");
			break;
		case "7":
			this.setOrder_state_show("完工待审核");
			break;
		case "8":
			this.setOrder_state_show("已完成(待评价)");
			break;
		case "9":
			this.setOrder_state_show("已完成(已评价)");
			break;
		case "10":
			this.setOrder_state_show("超时待派单");
			break;
		case "11":
			this.setOrder_state_show("已派单待接单");
			break;
		case "12":
			this.setOrder_state_show("师傅已完工");
			break;
		case "13":
			this.setOrder_state_show("未服务退单");
			break;
		case "14":
			this.setOrder_state_show("服务中退单");
			break;
		case "15":
			this.setOrder_state_show("异常");
			break;
		case "16":
			this.setOrder_state_show("待付款");
			break;
		}
		return this;
	}
	public Integer getOrder_accept_id() {
		return order_accept_id;
	}
	public WorkOrderBean setOrder_accept_id(Integer order_accept_id) {
		this.order_accept_id = order_accept_id;
		return this;
	}
	public String getOrder_accept_time() {
		return order_accept_time;
	}
	public WorkOrderBean setOrder_accept_time(String order_accept_time) {
		this.order_accept_time = order_accept_time;
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(order_accept_time);
			if(date.getDay()==new Date().getDay()){
				this.is_today_order="1";
			}else{
				this.is_today_order="0";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	public String getOrder_cancle_why() {
		return order_cancle_why;
	}
	public WorkOrderBean setOrder_cancle_why(String order_cancle_why) {
		this.order_cancle_why = order_cancle_why;
		return this;
	}
	public String getOrder_cancle_time() {
		return order_cancle_time;
	}
	public WorkOrderBean setOrder_cancle_time(String order_cancle_time) {
		this.order_cancle_time = order_cancle_time;
		return this;
	}
	public String getOrder_cancle_pass_time() {
		return order_cancle_pass_time;
	}
	public WorkOrderBean setOrder_cancle_pass_time(String order_cancle_pass_time) {
		this.order_cancle_pass_time = order_cancle_pass_time;
		return this;
	}
	public String getOrder_reality_content() {
		return order_reality_content;
	}
	public WorkOrderBean setOrder_reality_content(String order_reality_content) {
		this.order_reality_content = order_reality_content;
		return this;
	}
	public String getOrder_complete_img1() {
		return order_complete_img1;
	}
	public WorkOrderBean setOrder_complete_img1(String order_complete_img1) {
		this.order_complete_img1 = order_complete_img1;
		return this;
	}
	public String getOrder_complete_img2() {
		return order_complete_img2;
	}
	public WorkOrderBean setOrder_complete_img2(String order_complete_img2) {
		this.order_complete_img2 = order_complete_img2;
		return this;
	}
	public String getOrder_complete_img3() {
		return order_complete_img3;
	}
	public WorkOrderBean setOrder_complete_img3(String order_complete_img3) {
		this.order_complete_img3 = order_complete_img3;
		return this;
	}
	public String getOrder_complete_note() {
		return order_complete_note;
	}
	public WorkOrderBean setOrder_complete_note(String order_complete_note) {
		this.order_complete_note = order_complete_note;
		return this;
	}
	public String getOrder_service_time() {
		return order_service_time;
	}
	public WorkOrderBean setOrder_service_time(String order_service_time) {
		this.order_service_time = order_service_time;
		return this;
	}
	public String getOrder_complete_time() {
		return order_complete_time;
	}
	public WorkOrderBean setOrder_complete_time(String order_complete_time) {
		this.order_complete_time = order_complete_time;
		return this;
	}
	public String getOrder_complete_pass_time() {
		return order_complete_pass_time;
	}
	public WorkOrderBean setOrder_complete_pass_time(String order_complete_pass_time) {
		this.order_complete_pass_time = order_complete_pass_time;
		return this;
	}
	public String getOrder_is_delete() {
		return order_is_delete;
	}
	public WorkOrderBean setOrder_is_delete(String order_is_delete) {
		this.order_is_delete = order_is_delete;
		return this;
	}
	public String getDistrict() {
		return district;
	}
	public WorkOrderBean setDistrict(String district) {
		this.district = district;
		return this;
	}
	public Float getDeposit_price() {
		return deposit_price;
	}
	public WorkOrderBean setDeposit_price(Float deposit_price) {
		this.deposit_price = deposit_price;
		return this;
	}
	public String getIs_lock() {
		return is_lock;
	}
	public WorkOrderBean setIs_lock(String is_lock) {
		this.is_lock = is_lock;
		return this;
	}
	public Integer getLock_id() {
		return lock_id;
	}
	public WorkOrderBean setLock_id(Integer lock_id) {
		this.lock_id = lock_id;
		return this;
	}
	
}
