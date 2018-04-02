package com.project.bean.information;

import java.util.List;

/**
 * 资讯详情
 * @author 彭方林
 * @date 2018年4月2日
 */
public class InformationBean {
	private Integer information_id;
	private String information_title;//资讯标题
	private String information_desc;//资讯详情
	private String information_img;//资讯缩略图
	private String information_url;//富文本地址
	private String information_type;//类型
	private String create_time;//创建时间
	private Integer parent_id;//父节点id
	private Integer is_delete;//1删除
	private Integer is_recommend;//1推荐
	private String is_recommend_show;
	private Integer see_num;//浏览次数
	private String information_author;//作者
	private Integer sort;//权重
	private String infotmation_tag;//资讯标签
	private List<InformationImgBean> informationImgBeans;
	
	public String getInfotmation_tag() {
		return infotmation_tag;
	}
	public InformationBean setInfotmation_tag(String infotmation_tag) {
		this.infotmation_tag = infotmation_tag;
		return this;
	}
	public List<InformationImgBean> getInformationImgBeans() {
		return informationImgBeans;
	}
	public InformationBean setInformationImgBeans(List<InformationImgBean> informationImgBeans) {
		this.informationImgBeans = informationImgBeans;
		return this;
	}
	public String getIs_recommend_show() {
		return is_recommend_show;
	}
	public InformationBean setIs_recommend_show(String is_recommend_show) {
		this.is_recommend_show = is_recommend_show;
		return this;
	}
	public Integer getSort() {
		return sort;
	}
	public InformationBean setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
	public String getInformation_type() {
		return information_type;
	}
	public InformationBean setInformation_type(String information_type) {
		this.information_type = information_type;
		return this;
	}
	public Integer getInformation_id() {
		return information_id;
	}
	public InformationBean setInformation_id(Integer information_id) {
		this.information_id = information_id;
		return this;
	}
	public String getInformation_title() {
		return information_title;
	}
	public InformationBean setInformation_title(String information_title) {
		this.information_title = information_title;
		return this;
	}
	public String getInformation_desc() {
		return information_desc;
	}
	public InformationBean setInformation_desc(String information_desc) {
		this.information_desc = information_desc;
		return this;
	}
	public String getInformation_img() {
		return information_img;
	}
	public InformationBean setInformation_img(String information_img) {
		this.information_img = information_img;
		return this;
	}
	public String getInformation_url() {
		return information_url;
	}
	public InformationBean setInformation_url(String information_url) {
		this.information_url = information_url;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public InformationBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public InformationBean setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
		return this;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public InformationBean setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public Integer getIs_recommend() {
		return is_recommend;
	}
	public InformationBean setIs_recommend(Integer is_recommend) {
		this.is_recommend = is_recommend;
		this.is_recommend_show=1==is_recommend?"推荐":"不推荐";
		return this;
	}
	public Integer getSee_num() {
		return see_num;
	}
	public InformationBean setSee_num(Integer see_num) {
		this.see_num = see_num;
		return this;
	}
	public String getInformation_author() {
		return information_author;
	}
	public InformationBean setInformation_author(String information_author) {
		this.information_author = information_author;
		return this;
	}
	
	
}
