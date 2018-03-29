package com.live.aksd.bean;

/**
 * @author Created by stone
 * @since 17/9/12
 */

public class IntegralBean {


    /**
     * integral_id : 6
     * member_id : 24
     * integral_value : 10
     * is_delete : 0
     * integral_type : sign
     * integral_type_show : 签到获得
     * create_time : 2017-08-29 11:52:45.0
     * relation_id : 8697
     * deduction :
     * state : add
     */

    private int integral_id;
    private String member_id;
    private String integral_value;
    private String is_delete;
    private String integral_type;
    private String integral_type_show;
    private String create_time;
    private String relation_id;
    private String deduction;
    private String state;

    public int getIntegral_id() {
        return integral_id;
    }

    public void setIntegral_id(int integral_id) {
        this.integral_id = integral_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getIntegral_value() {
        return integral_value;
    }

    public void setIntegral_value(String integral_value) {
        this.integral_value = integral_value;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getIntegral_type() {
        return integral_type;
    }

    public void setIntegral_type(String integral_type) {
        this.integral_type = integral_type;
    }

    public String getIntegral_type_show() {
        return integral_type_show;
    }

    public void setIntegral_type_show(String integral_type_show) {
        this.integral_type_show = integral_type_show;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(String relation_id) {
        this.relation_id = relation_id;
    }

    public String getDeduction() {
        return deduction;
    }

    public void setDeduction(String deduction) {
        this.deduction = deduction;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
