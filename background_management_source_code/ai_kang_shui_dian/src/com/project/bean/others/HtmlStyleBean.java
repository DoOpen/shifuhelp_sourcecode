package com.project.bean.others;
/**
 * html模板
 * @author 彭方林
 * @date 2018年4月2日
 */
public class HtmlStyleBean {
	private Integer style_id;
	private String style_type;//风格类型
	private String style_desc;//内容
	private String is_delete;//1删除
	private String create_time;//创建时间
	public Integer getStyle_id() {
		return style_id;
	}
	public HtmlStyleBean setStyle_id(Integer style_id) {
		this.style_id = style_id;
		return this;
	}
	public String getStyle_type() {
		return style_type;
	}
	public HtmlStyleBean setStyle_type(String style_type) {
		this.style_type = style_type;
		return this;
	}
	public String getStyle_desc() {
		return style_desc;
	}
	public HtmlStyleBean setStyle_desc(String style_desc) {
		this.style_desc = style_desc;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public HtmlStyleBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public HtmlStyleBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
}
