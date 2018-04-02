package com.project.bean.others;

/**
 * html
 * @author 彭方林
 * @date 2018年4月2日
 */
public class HtmlBean {
	private Integer html_id;
	private String html_name;//名称
	private String html_url;//路径
	private String html_url_content;//内容
	private Integer sort;//权重
	private String is_delete;//1删除
	private String create_time;//创建时间
	private String update_time;//修改时间
	public Integer getHtml_id() {
		return html_id;
	}
	public HtmlBean setHtml_id(Integer html_id) {
		this.html_id = html_id;
		return this;
	}
	public String getHtml_name() {
		return html_name;
	}
	public HtmlBean setHtml_name(String html_name) {
		this.html_name = html_name;
		return this;
	}
	public String getHtml_url() {
		return html_url;
	}
	public HtmlBean setHtml_url(String html_url) {
		this.html_url = html_url;
		return this;
	}
	public String getHtml_url_content() {
		return html_url_content;
	}
	public HtmlBean setHtml_url_content(String html_url_content) {
		this.html_url_content = html_url_content;
		return this;
	}
	public Integer getSort() {
		return sort;
	}
	public HtmlBean setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public HtmlBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public HtmlBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public HtmlBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
}
