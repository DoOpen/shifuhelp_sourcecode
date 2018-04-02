package com.project.bean.order;

/**
 * 订单物流信息
 * @author 彭方林
 * @date 2018年4月2日
 */
public class OrderLogisticsBean {
	private Integer logistics_id;
	private String logistics_time;//物流信息产生时间
	private String logistics_context;//物流内容
	private String logistics_no;//物流单号
	private String logistics_state;//2-在途中 3-签收 4-问题件
	private String cretate_time;//创建时间
	private String is_delete;//1删除
	public Integer getLogistics_id() {
		return logistics_id;
	}
	public OrderLogisticsBean setLogistics_id(Integer logistics_id) {
		this.logistics_id = logistics_id;
		return this;
	}
	public String getLogistics_time() {
		return logistics_time;
	}
	public OrderLogisticsBean setLogistics_time(String logistics_time) {
		this.logistics_time = logistics_time;
		return this;
	}
	public String getLogistics_context() {
		return logistics_context;
	}
	public OrderLogisticsBean setLogistics_context(String logistics_context) {
		this.logistics_context = logistics_context;
		return this;
	}
	public String getLogistics_no() {
		return logistics_no;
	}
	public OrderLogisticsBean setLogistics_no(String logistics_no) {
		this.logistics_no = logistics_no;
		return this;
	}
	public String getLogistics_state() {
		return logistics_state;
	}
	public OrderLogisticsBean setLogistics_state(String logistics_state) {
		this.logistics_state = logistics_state;
		return this;
	}
	public String getCretate_time() {
		return cretate_time;
	}
	public OrderLogisticsBean setCretate_time(String cretate_time) {
		this.cretate_time = cretate_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public OrderLogisticsBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	
	
}
