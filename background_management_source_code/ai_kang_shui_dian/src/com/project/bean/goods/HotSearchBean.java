package com.project.bean.goods;

/**
 * 热搜
 * @author 彭方林
 * @date 2018年4月2日
 */
public class HotSearchBean {
	private Integer search_id;
	private String search_name;//搜索关键字
	private String search_num;//搜索次数
	private String create_time;//创建时间
	public Integer getSearch_id() {
		return search_id;
	}
	public HotSearchBean setSearch_id(Integer search_id) {
		this.search_id = search_id;
		return this;
	}
	public String getSearch_name() {
		return search_name;
	}
	public HotSearchBean setSearch_name(String search_name) {
		this.search_name = search_name;
		return this;
	}
	public String getSearch_num() {
		return search_num;
	}
	public HotSearchBean setSearch_num(String search_num) {
		this.search_num = search_num;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public HotSearchBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
}
