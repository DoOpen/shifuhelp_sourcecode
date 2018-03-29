package com.project.bean.others;

/**
 * 验证码
 *
 */
public class CodeBean {
	private Integer code_id;
	private String mobile;
	private String code;
	private String code_type;
	private String code_desc;
	private String create_time;
	private String effective_time;
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
