package com.project.bean.workOrder;

import com.project.bean.member.MemberBean;

/**
 * ping++支付记录
 * @author 彭方林
 * @date 2018年4月2日
 */
public class PingHistoryBean {
	private Integer ping_id;
	private String order_no;//订单号
	private Integer order_id;//订单id
	private String ping_type;//workerDeposit:师傅押金workOrderDeposit:预约工单押金workOrderComplete：工单结算金额
	private String ping_type_show;
	private String create_time;//创建时间
	private Float price;//支付价格
	private Integer member_id;//用户id
	private String member_real_name;//用户真实姓名
	private String member_account;//用户账号
	private String member_nick_name;//用户昵称
	private String channel;//支付凭据
	private MemberBean memberBean;
	private String start_time;//搜索开始时间
	private String end_time;//搜索结束时间
	public String getStart_time() {
		return start_time;
	}
	public PingHistoryBean setStart_time(String start_time) {
		this.start_time = start_time;
		return this;
	}
	public String getEnd_time() {
		return end_time;
	}
	public PingHistoryBean setEnd_time(String end_time) {
		this.end_time = end_time;
		return this;
	}
	public String getMember_account() {
		return member_account;
	}
	public PingHistoryBean setMember_account(String member_account) {
		this.member_account = member_account;
		return this;
	}
	public MemberBean getMemberBean() {
		return memberBean;
	}
	public PingHistoryBean setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
		return this;
	}
	public String getChannel() {
		return channel;
	}
	public PingHistoryBean setChannel(String channel) {
		this.channel = channel;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public PingHistoryBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Integer getPing_id() {
		return ping_id;
	}
	public PingHistoryBean setPing_id(Integer ping_id) {
		this.ping_id = ping_id;
		return this;
	}
	public String getOrder_no() {
		return order_no;
	}
	public PingHistoryBean setOrder_no(String order_no) {
		this.order_no = order_no;
		return this;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public PingHistoryBean setOrder_id(Integer order_id) {
		this.order_id = order_id;
		return this;
	}
	public String getPing_type() {
		return ping_type;
	}
	public PingHistoryBean setPing_type(String ping_type) {
		this.ping_type = ping_type;
		this.ping_type_show="workerDeposit".equals(ping_type)?"师傅注册押金":
			"workOrderDeposit".equals(ping_type)?"工单预约押金":
				"workOrderComplete".equals(ping_type)?"工单结算金额":"未知";
		return this;
	}
	public String getPing_type_show() {
		return ping_type_show;
	}
	public PingHistoryBean setPing_type_show(String ping_type_show) {
		this.ping_type_show = ping_type_show;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public PingHistoryBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public Float getPrice() {
		return price;
	}
	public PingHistoryBean setPrice(Float price) {
		this.price = price;
		return this;
	}
	public String getMember_real_name() {
		return member_real_name;
	}
	public PingHistoryBean setMember_real_name(String member_real_name) {
		this.member_real_name = member_real_name;
		return this;
	}
	public String getMember_nick_name() {
		return member_nick_name;
	}
	public PingHistoryBean setMember_nick_name(String member_nick_name) {
		this.member_nick_name = member_nick_name;
		return this;
	}
}
