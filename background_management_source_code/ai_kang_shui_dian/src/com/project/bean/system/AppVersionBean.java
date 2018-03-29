package com.project.bean.system;

public class AppVersionBean {
	private Integer version_id;
	private String version_name;
	private String version_type;
	private String version_no;
	private String update_content;
	private String download_address;
	private String server_address;
	private String must_update;
	private String update_time;
	private String server_update;
	public String getServer_update() {
		return server_update;
	}
	public AppVersionBean setServer_update(String server_update) {
		this.server_update = server_update;
		return this;
	}
	public Integer getVersion_id() {
		return version_id;
	}
	public AppVersionBean setVersion_id(Integer version_id) {
		this.version_id = version_id;
		return this;
	}
	public String getVersion_name() {
		return version_name;
	}
	public AppVersionBean setVersion_name(String version_name) {
		this.version_name = version_name;
		return this;
	}
	public String getVersion_type() {
		return version_type;
	}
	public AppVersionBean setVersion_type(String version_type) {
		this.version_type = version_type;
		return this;
	}
	public String getVersion_no() {
		return version_no;
	}
	public AppVersionBean setVersion_no(String version_no) {
		this.version_no = version_no;
		return this;
	}
	public String getUpdate_content() {
		return update_content;
	}
	public AppVersionBean setUpdate_content(String update_content) {
		this.update_content = update_content;
		return this;
	}
	public String getDownload_address() {
		return download_address;
	}
	public AppVersionBean setDownload_address(String download_address) {
		this.download_address = download_address;
		return this;
	}
	public String getServer_address() {
		return server_address;
	}
	public AppVersionBean setServer_address(String server_address) {
		this.server_address = server_address;
		return this;
	}
	public String getMust_update() {
		return must_update;
	}
	public AppVersionBean setMust_update(String must_update) {
		this.must_update = must_update;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public AppVersionBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
}
