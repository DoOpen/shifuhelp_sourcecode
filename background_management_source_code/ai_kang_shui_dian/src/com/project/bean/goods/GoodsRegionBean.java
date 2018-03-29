package com.project.bean.goods;

public class GoodsRegionBean {
	private Integer region_id;
	private String region_name;
	private String is_delete;
	private String create_time;
	private String update_time;
	

	public Integer getRegion_id() {
		return region_id;
	}
	public GoodsRegionBean setRegion_id(Integer region_id) {
		this.region_id = region_id;
		return this;
	}
	public String getRegion_name() {
		return region_name;
	}
	public GoodsRegionBean setRegion_name(String region_name) {
		this.region_name = region_name;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public GoodsRegionBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public GoodsRegionBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public GoodsRegionBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
}
