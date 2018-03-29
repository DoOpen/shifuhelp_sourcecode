package com.project.bean.order;

public class AssessmentImgBean {
	private Integer assessment_img_id;
	private Integer assessment_id;
	private String assessment_img;
	private String create_time;
	private String update_time;
	private String is_delete;
	public Integer getAssessment_img_id() {
		return assessment_img_id;
	}
	public AssessmentImgBean setAssessment_img_id(Integer assessment_img_id) {
		this.assessment_img_id = assessment_img_id;
		return this;
	}
	public Integer getAssessment_id() {
		return assessment_id;
	}
	public AssessmentImgBean setAssessment_id(Integer assessment_id) {
		this.assessment_id = assessment_id;
		return this;
	}
	public String getAssessment_img() {
		return assessment_img;
	}
	public AssessmentImgBean setAssessment_img(String assessment_img) {
		this.assessment_img = assessment_img;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public AssessmentImgBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public AssessmentImgBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public AssessmentImgBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
}
