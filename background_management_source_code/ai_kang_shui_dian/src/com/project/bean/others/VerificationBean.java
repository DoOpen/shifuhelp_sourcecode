package com.project.bean.others;

/**
 * 短信平台配置信息
 * @author 彭方林
 * @date 2018年4月2日
 */
public class VerificationBean {
	private Integer verification_id;
	private String verification_url;//接口调用地址
	private String verification_account;//账号
	private String verification_password;//密码
	private String verification_content;//验证码内容
	private String verification_userid;//用户id
	private String key_account;//私钥账号
	private String key_mobile;//私钥手机号
	private String key_content;//私钥内容
	private String key_password;//私钥密码
	private String key_userid;//私钥用户id
	private Integer effective_time;//到期时间
	
	public String getVerification_userid() {
		return verification_userid==null?"":verification_userid;
	}
	public VerificationBean setVerification_userid(String verification_userid) {
		this.verification_userid = verification_userid;
		return this;
	}
	public String getKey_userid() {
		return key_userid==null?"":key_userid;
	}
	public VerificationBean setKey_userid(String key_userid) {
		this.key_userid = key_userid;
		return this;
	}
	public Integer getEffective_time() {
		return effective_time;
	}
	public VerificationBean setEffective_time(Integer effective_time) {
		this.effective_time = effective_time;
		return this;
	}
	public String getKey_account() {
		return key_account;
	}
	public VerificationBean setKey_account(String key_account) {
		this.key_account = key_account;
		return this;
	}
	public String getKey_mobile() {
		return key_mobile;
	}
	public VerificationBean setKey_mobile(String key_mobile) {
		this.key_mobile = key_mobile;
		return this;
	}
	public String getKey_content() {
		return key_content;
	}
	public VerificationBean setKey_content(String key_content) {
		this.key_content = key_content;
		return this;
	}
	
	public String getKey_password() {
		return key_password;
	}
	public VerificationBean setKey_password(String key_password) {
		this.key_password = key_password;
		return this;
	}
	public Integer getVerification_id() {
		return verification_id;
	}
	public VerificationBean setVerification_id(Integer verification_id) {
		this.verification_id = verification_id;
		return this;
	}
	public String getVerification_url() {
		return verification_url;
	}
	public VerificationBean setVerification_url(String verification_url) {
		this.verification_url = verification_url;
		return this;
	}
	public String getVerification_account() {
		return verification_account;
	}
	public VerificationBean setVerification_account(String verification_account) {
		this.verification_account = verification_account;
		return this;
	}
	public String getVerification_password() {
		return verification_password;
	}
	public VerificationBean setVerification_password(String verification_password) {
		this.verification_password = verification_password;
		return this;
	}
	public String getVerification_content() {
		return verification_content;
	}
	public VerificationBean setVerification_content(String verification_content) {
		this.verification_content = verification_content;
		return this;
	}
		
}
