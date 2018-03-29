package com.project.bean.training;

public class TrainingBean {
	private Integer training_id;
	private String training_title;
	private String training_profile;
	private String training_content_url;
	private String training_video_url;
	private String is_delete;
	private String create_time;
	private Integer class_id;
	private String type;
	private String training_img;
	public String getTraining_img() {
		return training_img;
	}
	public TrainingBean setTraining_img(String training_img) {
		this.training_img = training_img;
		return this;
	}
	public Integer getTraining_id() {
		return training_id;
	}
	public TrainingBean setTraining_id(Integer training_id) {
		this.training_id = training_id;
		return this;
	}
	public String getTraining_title() {
		return training_title;
	}
	public TrainingBean setTraining_title(String training_title) {
		this.training_title = training_title;
		return this;
	}
	public String getTraining_profile() {
		return training_profile;
	}
	public TrainingBean setTraining_profile(String training_profile) {
		this.training_profile = training_profile;
		return this;
	}
	public String getTraining_content_url() {
		return training_content_url;
	}
	public TrainingBean setTraining_content_url(String training_content_url) {
		this.training_content_url = training_content_url;
		return this;
	}
	public String getTraining_video_url() {
		return training_video_url;
	}
	public TrainingBean setTraining_video_url(String training_video_url) {
		this.training_video_url = training_video_url;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public TrainingBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public TrainingBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public Integer getClass_id() {
		return class_id;
	}
	public TrainingBean setClass_id(Integer class_id) {
		this.class_id = class_id;
		return this;
	}
	public String getType() {
		return type;
	}
	public TrainingBean setType(String type) {
		this.type = type;
		return this;
	}
}
