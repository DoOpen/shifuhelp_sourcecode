package com.project.bean.others;

/**
 * 验证码
 * @author 彭方林
 * @date 2018年4月2日
 */
public class CodeBean {
	private Integer code_id;
	private String mobile;//手机号
	private String code;//验证码
	private String code_type;//注册：register 忘记密码: forget_password  修改密码:update_password 绑定手机:bind_mobile 修改手机：update_mobile 发单:send_order 支付余额密码：balance_passwrod 余额支付： balance_pay 信用支付： trust_pay 提现申请：apply_cash 修改密码：update_password 绑定支付宝：bind_alipay 绑定银行卡：bind_bank 绑定微信号：bind_we_chat
	private String code_desc;//内容
	private String create_time;//创建时间
	private String effective_time;//到期时间
	public Integer getCode_id() {
		return code_id;
	}
	public CodeBean setCode_id(Integer code_id) {
		this.code_id = code_id;
		return this;
	}
	public String getMobile() {
		return mobile;
	}
	public CodeBean setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}
	public String getCode() {
		return code;
	}
	public CodeBean setCode(String code) {
		this.code = code;
		return this;
	}
	public String getCode_type() {
		return code_type;
	}
	public CodeBean setCode_type(String code_type) {
		this.code_type = code_type;
		return this;
	}
	public String getCode_desc() {
		return code_desc;
	}
	public CodeBean setCode_desc(String code_desc) {
		this.code_desc = code_desc;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public CodeBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getEffective_time() {
		return effective_time;
	}
	public CodeBean setEffective_time(String effective_time) {
		this.effective_time = effective_time;
		return this;
	}
	
	
}
