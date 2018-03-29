package com.live.aksd.bean;

/**
 * Created by liuxiaoxiang on 2018/3/20.
 */

public class AppVersionDetailBean {


    /**
     * version_id : 1
     * version_name : 安卓
     * version_type : android
     * version_no : 2.0.4
     * update_content : 2.0版本
     * download_address : www.baidu.com
     * server_address : /apk/15215338652961131264653.apk
     * must_update : 0
     * update_time : 2018-03-20 16:17:47
     */

    private int version_id;
    private String version_name;
    private String version_type;
    private String version_no;
    private String update_content;
    private String download_address;
    private String server_address;
    private String must_update;

    public String getServer_update() {
        return server_update;
    }

    public void setServer_update(String server_update) {
        this.server_update = server_update;
    }

    private String update_time;
private String server_update;

    public int getVersion_id() {
        return version_id;
    }

    public void setVersion_id(int version_id) {
        this.version_id = version_id;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getVersion_type() {
        return version_type;
    }

    public void setVersion_type(String version_type) {
        this.version_type = version_type;
    }

    public String getVersion_no() {
        return version_no;
    }

    public void setVersion_no(String version_no) {
        this.version_no = version_no;
    }

    public String getUpdate_content() {
        return update_content;
    }

    public void setUpdate_content(String update_content) {
        this.update_content = update_content;
    }

    public String getDownload_address() {
        return download_address;
    }

    public void setDownload_address(String download_address) {
        this.download_address = download_address;
    }

    public String getServer_address() {
        return server_address;
    }

    public void setServer_address(String server_address) {
        this.server_address = server_address;
    }

    public String getMust_update() {
        return must_update;
    }

    public void setMust_update(String must_update) {
        this.must_update = must_update;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
