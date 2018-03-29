package com.project.bean.member;

public class MessageBean {
	private Integer msg_id;
	private String member_id;
	private String msg_title;
	private String msg_desc;
	private String msg_type;
	private String order_id;
	private String order_no;
	private String create_time;
	private String is_delete;
	private String is_read;
	private String logistics_state;
	private String logistics_no;
	private String goods_img;
	private String goods_name;
	public String getIs_read() {
		return is_read;
	}
	public MessageBean setIs_read(String is_read) {
		this.is_read = is_read;
		return this;
	}
	public String getLogistics_state() {
		return logistics_state;
	}
	public MessageBean setLogistics_state(String logistics_state) {
		this.logistics_state = logistics_state;
		return this;
	}
	public String getLogistics_no() {
		return logistics_no;
	}
	public MessageBean setLogistics_no(String logistics_no) {
		this.logistics_no = logistics_no;
		return this;
	}
	public String getGoods_img() {
		return goods_img;
	}
	public MessageBean setGoods_img(String goods_img) {
		this.goods_img = goods_img;
		return this;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public MessageBean setGoods_name(String goods_name) {
		this.goods_name = goods_name;
		return this;
	}
	public String getMsg_title() {
		return msg_title;
	}
	public MessageBean setMsg_title(String msg_title) {
		this.msg_title = msg_title;
		return this;
	}
	public Integer getMsg_id() {
		return msg_id;
	}
	public MessageBean setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
		return this;
	}
	public String getMember_id() {
		return member_id;
	}
	public MessageBean setMember_id(String member_id) {
		this.member_id = member_id;
		return this;
	}
	public String getMsg_desc() {
		return msg_desc;
	}
	public MessageBean setMsg_desc(String msg_desc) {
		this.msg_desc = msg_desc;
		return this;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public MessageBean setMsg_type(String msg_type) {
		this.msg_type = msg_type;
		return this;
	}
	public String getOrder_id() {
		return order_id;
	}
	public MessageBean setOrder_id(String order_id) {
		this.order_id = order_id;
		return this;
	}
	public String getOrder_no() {
		return order_no;
	}
	public MessageBean setOrder_no(String order_no) {
		this.order_no = order_no;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public MessageBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public MessageBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}

}
