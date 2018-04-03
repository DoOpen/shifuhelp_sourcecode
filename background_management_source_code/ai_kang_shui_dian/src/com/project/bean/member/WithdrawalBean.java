package com.project.bean.member;

/**
 * 用户提现
 * @author 彭方林
 * @date 2018年4月2日
 */
public class WithdrawalBean {
	private Integer withdrawal_id;
	private Integer member_id;//用户id
	private String member_account;//用户账号
	private String member_real_name;//用户真实姓名
	private Float withdrawal_price;//提现金额
	private String withdrawal_state;//提现状态     wait_review:等待审核  accept:接受  refuse:拒绝 end:已打款
	private String withdrawal_state_show;
	private String withdrawal_no;//提现账号
	private String withdrawal_way;//提现方式  bank：银行卡提现   we_chat微信  alipay:支付宝
	private String withdrawal_way_show;
	private String is_delete;//1删除
	private String pay_time;//打款时间
	private String create_time;//创建时间
	private String update_time;//修改时间
	private String start_time;//搜索开始时间
	private String end_time;//搜索结束时间
	private String bank_name;//银行名称
	private String bank_open_name;//开户行名称
	private String bank_user_name;//开户人姓名
	private String bank_open_mobile;//开户人手机号
	private String refuse_note;//拒绝原因
	private String order_no;//提现流水号
	private String member_extract_money;//用户可提现余额
	
	public String getMember_extract_money() {
		return member_extract_money;
	}
	public WithdrawalBean setMember_extract_money(String member_extract_money) {
		this.member_extract_money = member_extract_money;
		return this;
	}
	public String getOrder_no() {
		return order_no;
	}
	public WithdrawalBean setOrder_no(String order_no) {
		this.order_no = order_no;
		return this;
	}
	public String getRefuse_note() {
		return refuse_note;
	}
	public WithdrawalBean setRefuse_note(String refuse_note) {
		this.refuse_note = refuse_note;
		return this;
	}
	public String getMember_account() {
		return member_account;
	}
	public WithdrawalBean setMember_account(String member_account) {
		this.member_account = member_account;
		return this;
	}
	public String getMember_real_name() {
		return member_real_name;
	}
	public WithdrawalBean setMember_real_name(String member_real_name) {
		this.member_real_name = member_real_name;
		return this;
	}
	public String getBank_name() {
		return bank_name;
	}
	public WithdrawalBean setBank_name(String bank_name) {
		this.bank_name = bank_name;
		return this;
	}
	public String getBank_open_name() {
		return bank_open_name;
	}
	public WithdrawalBean setBank_open_name(String bank_open_name) {
		this.bank_open_name = bank_open_name;
		return this;
	}
	public String getBank_user_name() {
		return bank_user_name;
	}
	public WithdrawalBean setBank_user_name(String bank_user_name) {
		this.bank_user_name = bank_user_name;
		return this;
	}
	public String getBank_open_mobile() {
		return bank_open_mobile;
	}
	public WithdrawalBean setBank_open_mobile(String bank_open_mobile) {
		this.bank_open_mobile = bank_open_mobile;
		return this;
	}
	public String getWithdrawal_no() {
		return withdrawal_no;
	}
	public WithdrawalBean setWithdrawal_no(String withdrawal_no) {
		this.withdrawal_no = withdrawal_no;
		return this;
	}
	public String getWithdrawal_way() {
		return withdrawal_way;
	}
	public WithdrawalBean setWithdrawal_way(String withdrawal_way) {
		this.withdrawal_way = withdrawal_way;
		this.withdrawal_way_show="alipay".equals(withdrawal_way)?"支付宝":
			"we_chat".equals(withdrawal_way)?"微信":
				"bank".equals(withdrawal_way)?"银行卡":"未知";
		return this;
	}
	public String getWithdrawal_way_show() {
		return withdrawal_way_show;
	}
	public WithdrawalBean setWithdrawal_way_show(String withdrawal_way_show) {
		this.withdrawal_way_show = withdrawal_way_show;
		return this;
	}
	public Integer getWithdrawal_id() {
		return withdrawal_id;
	}
	public WithdrawalBean setWithdrawal_id(Integer withdrawal_id) {
		this.withdrawal_id = withdrawal_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public WithdrawalBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Float getWithdrawal_price() {
		return withdrawal_price;
	}
	public WithdrawalBean setWithdrawal_price(Float withdrawal_price) {
		this.withdrawal_price = withdrawal_price;
		return this;
	}
	public String getWithdrawal_state() {
		return withdrawal_state;
	}
	public WithdrawalBean setWithdrawal_state(String withdrawal_state) {
		this.withdrawal_state = withdrawal_state;
		this.withdrawal_state_show="wait_review".equals(withdrawal_state)?"待审核":
			"accept".equals(withdrawal_state)?"接受":
				"refuse".equals(withdrawal_state)?"拒绝":
					"end".equals(withdrawal_state)?"已打款":"未知";
		return this;
	}
	public String getWithdrawal_state_show() {
		return withdrawal_state_show;
	}
	public WithdrawalBean setWithdrawal_state_show(String withdrawal_state_show) {
		this.withdrawal_state_show = withdrawal_state_show;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public WithdrawalBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getPay_time() {
		return pay_time;
	}
	public WithdrawalBean setPay_time(String pay_time) {
		this.pay_time = pay_time;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public WithdrawalBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public WithdrawalBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public String getStart_time() {
		return start_time;
	}
	public WithdrawalBean setStart_time(String start_time) {
		this.start_time = start_time;
		return this;
	}
	public String getEnd_time() {
		return end_time;
	}
	public WithdrawalBean setEnd_time(String end_time) {
		this.end_time = end_time;
		return this;
	}
}
