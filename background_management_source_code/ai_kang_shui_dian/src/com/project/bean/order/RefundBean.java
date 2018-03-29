package com.project.bean.order;

public class RefundBean {
	private Integer refund_id;
	private Integer member_id;
	private Integer order_id;
	private String member_nick_name;
	private String goods_name;
	private String order_no;
	private Integer order_goods_id;
	private String refund_type;
	private String refund_type_show;
	private String refund_no;
	private String refund_ping_no;
	private Integer refund_count;
	private String refund_desc;
	private String refund_state;
	private String refund_state_show;
	private Float refund_price;
	private Integer refund_reason_id;
	private String reason_name;
	private String refuse_note;
	private String create_time;
	private String update_time;
	private String is_delete;
	private String logistics_no;
	private String logistics_name;
	private String start_time;
	private String end_time;
	private String refund_img1;
	private String refund_img2;
	private String refund_img3;
	private OrderBean orderBean;
	private OrderGoodsBean orderGoodsBean;
	public String getMember_nick_name() {
		return member_nick_name;
	}
	public RefundBean setMember_nick_name(String member_nick_name) {
		this.member_nick_name = member_nick_name;
		return this;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public RefundBean setGoods_name(String goods_name) {
		this.goods_name = goods_name;
		return this;
	}
	public OrderGoodsBean getOrderGoodsBean() {
		return orderGoodsBean;
	}
	public RefundBean setOrderGoodsBean(OrderGoodsBean orderGoodsBean) {
		this.orderGoodsBean = orderGoodsBean;
		return this;
	}
	public String getRefund_ping_no() {
		return refund_ping_no;
	}
	public RefundBean setRefund_ping_no(String refund_ping_no) {
		this.refund_ping_no = refund_ping_no;
		return this;
	}
	public OrderBean getOrderBean() {
		return orderBean;
	}
	public RefundBean setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
		return this;
	}
	public Integer getRefund_id() {
		return refund_id;
	}
	public RefundBean setRefund_id(Integer refund_id) {
		this.refund_id = refund_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public RefundBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public RefundBean setOrder_id(Integer order_id) {
		this.order_id = order_id;
		return this;
	}
	public String getOrder_no() {
		return order_no;
	}
	public RefundBean setOrder_no(String order_no) {
		this.order_no = order_no;
		return this;
	}
	public Integer getOrder_goods_id() {
		return order_goods_id;
	}
	public RefundBean setOrder_goods_id(Integer order_goods_id) {
		this.order_goods_id = order_goods_id;
		return this;
	}
	public String getRefund_type() {
		return refund_type;
	}
	public RefundBean setRefund_type(String refund_type) {
		this.refund_type = refund_type;
		this.refund_type_show="only_money".equals(refund_type)?"仅退款":
			"refund_goods".equals(refund_type)?"退货退款":"未知";
		return this;
	}
	public String getRefund_type_show() {
		return refund_type_show;
	}
	public RefundBean setRefund_type_show(String refund_type_show) {
		this.refund_type_show = refund_type_show;
		return this;
	}
	public String getRefund_no() {
		return refund_no;
	}
	public RefundBean setRefund_no(String refund_no) {
		this.refund_no = refund_no;
		return this;
	}
	public Integer getRefund_count() {
		return refund_count;
	}
	public RefundBean setRefund_count(Integer refund_count) {
		this.refund_count = refund_count;
		return this;
	}
	public String getRefund_desc() {
		return refund_desc;
	}
	public RefundBean setRefund_desc(String refund_desc) {
		this.refund_desc = refund_desc;
		return this;
	}
	public String getRefund_state() {
		return refund_state;
	}
	public RefundBean setRefund_state(String refund_state) {
		this.refund_state = refund_state;
		this.refund_state_show="wait_review".equals(refund_state)?"待审核":
			"accept".equals(refund_state)?"接受":
				"cancel".equals(refund_state)?"取消":
				"refuse".equals(refund_state)?"拒绝":
					"end".equals(refund_state)?"退款成功":"未知";
		return this;
	}
	public String getRefund_state_show() {
		return refund_state_show;
	}
	public RefundBean setRefund_state_show(String refund_state_show) {
		this.refund_state_show = refund_state_show;
		return this;
	}
	public Float getRefund_price() {
		return refund_price;
	}
	public RefundBean setRefund_price(Float refund_price) {
		this.refund_price = refund_price;
		return this;
	}
	public Integer getRefund_reason_id() {
		return refund_reason_id;
	}
	public RefundBean setRefund_reason_id(Integer refund_reason_id) {
		this.refund_reason_id = refund_reason_id;
		return this;
	}
	public String getReason_name() {
		return reason_name;
	}
	public RefundBean setReason_name(String reason_name) {
		this.reason_name = reason_name;
		return this;
	}
	public String getRefuse_note() {
		return refuse_note;
	}
	public RefundBean setRefuse_note(String refuse_note) {
		this.refuse_note = refuse_note;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public RefundBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public RefundBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public RefundBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getLogistics_no() {
		return logistics_no;
	}
	public RefundBean setLogistics_no(String logistics_no) {
		this.logistics_no = logistics_no;
		return this;
	}
	public String getLogistics_name() {
		return logistics_name;
	}
	public RefundBean setLogistics_name(String logistics_name) {
		this.logistics_name = logistics_name;
		return this;
	}
	public String getStart_time() {
		return start_time;
	}
	public RefundBean setStart_time(String start_time) {
		this.start_time = start_time;
		return this;
	}
	public String getEnd_time() {
		return end_time;
	}
	public RefundBean setEnd_time(String end_time) {
		this.end_time = end_time;
		return this;
	}
	public String getRefund_img1() {
		return refund_img1;
	}
	public RefundBean setRefund_img1(String refund_img1) {
		this.refund_img1 = refund_img1;
		return this;
	}
	public String getRefund_img2() {
		return refund_img2;
	}
	public RefundBean setRefund_img2(String refund_img2) {
		this.refund_img2 = refund_img2;
		return this;
	}
	public String getRefund_img3() {
		return refund_img3;
	}
	public RefundBean setRefund_img3(String refund_img3) {
		this.refund_img3 = refund_img3;
		return this;
	}
}
