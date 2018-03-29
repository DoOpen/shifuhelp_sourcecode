package com.live.aksd.bean;

import java.io.Serializable;

/**
 * @author Created by stone
 * @since 17/8/23
 */

public class ReportedBean implements Serializable {


    /**
     * reported_id : 32
     * member_id : 42
     * reported_name : wzg
     * reported_phone : 17191934140
     * country :
     * province : 北京市
     * city : 北京市
     * district : 东城区
     * detail : 11111
     * is_delete : 0
     * reported_img1 : /images/reported/1505903421729.png
     * reported_img2 :
     * reported_img3 :
     * create_time : 2017-09-20 06:30:43.0
     * update_time : 2017-09-20 06:30:43.0
     * reported_state : wait_audit
     * reported_state_show : 待审核
     */

    private String reported_id;
    private int member_id;
    private String reported_name;
    private String reported_phone;
    private String country;
    private String province;
    private String city;
    private String district;
    private String detail;
    private int is_delete;
    private String reported_img1;
    private String reported_img2;
    private String reported_img3;

    public String getRefuse_note() {
        return refuse_note;
    }

    public void setRefuse_note(String refuse_note) {
        this.refuse_note = refuse_note;
    }

    public String getReported_note() {
        return reported_note;
    }

    public void setReported_note(String reported_note) {
        this.reported_note = reported_note;
    }

    private String create_time;
    private String update_time;
    private String reported_state;
    private String reported_state_show;
    private String refuse_note;
    private String reported_note;

    public String getReported_id() {
        return reported_id;
    }

    public void setReported_id(String reported_id) {
        this.reported_id = reported_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getReported_name() {
        return reported_name;
    }

    public void setReported_name(String reported_name) {
        this.reported_name = reported_name;
    }

    public String getReported_phone() {
        return reported_phone;
    }

    public void setReported_phone(String reported_phone) {
        this.reported_phone = reported_phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public String getReported_img1() {
        return reported_img1;
    }

    public void setReported_img1(String reported_img1) {
        this.reported_img1 = reported_img1;
    }

    public String getReported_img2() {
        return reported_img2;
    }

    public void setReported_img2(String reported_img2) {
        this.reported_img2 = reported_img2;
    }

    public String getReported_img3() {
        return reported_img3;
    }

    public void setReported_img3(String reported_img3) {
        this.reported_img3 = reported_img3;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getReported_state() {
        return reported_state;
    }

    public void setReported_state(String reported_state) {
        this.reported_state = reported_state;
    }

    public String getReported_state_show() {
        return reported_state_show;
    }

    public void setReported_state_show(String reported_state_show) {
        this.reported_state_show = reported_state_show;
    }
}
