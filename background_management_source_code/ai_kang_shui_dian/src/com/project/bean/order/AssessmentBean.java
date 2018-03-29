package com.project.bean.order;

public class AssessmentBean {
	private Integer assessment_id;
	private Integer member_id;//用户id
	private String member_head_image;
	private String member_nick_name;
	private Integer order_id;//订单id
	private String order_no;//订单号
	private String assessment_desc;//评价内容
	private Float assessment_star1;//星级1
	private Float assessment_star2;//星级2
	private Float assessment_star3;//星级3
	private Integer goods_id;//商品id
	private String assessment_img1;
	private String assessment_img2;
	private String assessment_img3;
	private String create_time;//
	private String update_time;//
	private String is_delete;//
	
	public String getMember_head_image() {
		return member_head_image;
	}
	public AssessmentBean setMember_head_image(String member_head_image) {
		this.member_head_image = member_head_image;
		return this;
	}
	public String getMember_nick_name() {
		return member_nick_name;
	}
	public AssessmentBean setMember_nick_name(String member_nick_name) {
		this.member_nick_name = member_nick_name;
		return this;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public AssessmentBean setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
		return this;
	}
	public String getAssessment_img1() {
		return assessment_img1;
	}
	public AssessmentBean setAssessment_img1(String assessment_img1) {
		this.assessment_img1 = assessment_img1;
		return this;
	}
	public String getAssessment_img2() {
		return assessment_img2;
	}
	public AssessmentBean setAssessment_img2(String assessment_img2) {
		this.assessment_img2 = assessment_img2;
		return this;
	}
	public String getAssessment_img3() {
		return assessment_img3;
	}
	public AssessmentBean setAssessment_img3(String assessment_img3) {
		this.assessment_img3 = assessment_img3;
		return this;
	}
	public AssessmentBean setAssessment_id(Integer assessment_id) {
		this.assessment_id = assessment_id;
		return this;
	}
	public int getAssessment_id() {
		return assessment_id;
	}
	public AssessmentBean setAssessment_id(int assessment_id) {
		this.assessment_id = assessment_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public AssessmentBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public AssessmentBean setOrder_id(Integer order_id) {
		this.order_id = order_id;
		return this;
	}
	public String getOrder_no() {
		return order_no;
	}
	public AssessmentBean setOrder_no(String order_no) {
		this.order_no = order_no;
		return this;
	}
	public String getAssessment_desc() {
		return assessment_desc;
	}
	public AssessmentBean setAssessment_desc(String assessment_desc) {
		this.assessment_desc = assessment_desc;
		return this;
	}
	public Float getAssessment_star1() {
		return assessment_star1;
	}
	public AssessmentBean setAssessment_star1(Float assessment_star1) {
		this.assessment_star1 = assessment_star1;
		return this;
	}
	public Float getAssessment_star2() {
		return assessment_star2;
	}
	public AssessmentBean setAssessment_star2(Float assessment_star2) {
		this.assessment_star2 = assessment_star2;
		return this;
	}
	public Float getAssessment_star3() {
		return assessment_star3;
	}
	public AssessmentBean setAssessment_star3(Float assessment_star3) {
		this.assessment_star3 = assessment_star3;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public AssessmentBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public AssessmentBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public AssessmentBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
}
