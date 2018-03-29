package com.project.bean.member;

public class BalanceHistoryBean {
	private Integer history_id;
	private Integer member_id;
	private String title;
	private String order_no;
	private Float price;
	private String create_time;
	private String type;
	private String ping;
	public String getPing() {
		return ping;
	}
	public BalanceHistoryBean setPing(String ping) {
		this.ping = ping;
		return this;
	}
	public Integer getHistory_id() {
		return history_id;
	}
	public BalanceHistoryBean setHistory_id(Integer history_id) {
		this.history_id = history_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public BalanceHistoryBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public BalanceHistoryBean setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getOrder_no() {
		return order_no;
	}
	public BalanceHistoryBean setOrder_no(String order_no) {
		this.order_no = order_no;
		return this;
	}
	public Float getPrice() {
		return price;
	}
	public BalanceHistoryBean setPrice(Float price) {
		this.price = price;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public BalanceHistoryBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getType() {
		return type;
	}
	public BalanceHistoryBean setType(String type) {
		this.type = type;
		return this;
	}
}
