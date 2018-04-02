package com.project.bean.others;

/**
 * 意见反馈图片
 * @author 彭方林
 * @date 2018年4月2日
 */
public class AdviceImgBean {
	private Integer advice_img_id;
	private Integer advice_id;//意见id
	private String advice_img;//图片路径
	private String create_time;//创建时间
	private String is_delete;//1删除
	public Integer getAdvice_img_id() {
		return advice_img_id;
	}
	public AdviceImgBean setAdvice_img_id(Integer advice_img_id) {
		this.advice_img_id = advice_img_id;
		return this;
	}
	public Integer getAdvice_id() {
		return advice_id;
	}
	public AdviceImgBean setAdvice_id(Integer advice_id) {
		this.advice_id = advice_id;
		return this;
	}
	public String getAdvice_img() {
		return advice_img;
	}
	public AdviceImgBean setAdvice_img(String advice_img) {
		this.advice_img = advice_img;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public AdviceImgBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public AdviceImgBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
}
