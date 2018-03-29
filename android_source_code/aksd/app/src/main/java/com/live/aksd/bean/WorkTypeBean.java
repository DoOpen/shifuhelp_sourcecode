package com.live.aksd.bean;

/**
 * @author Created by stone
 * @since 2017/11/4
 */

public class WorkTypeBean {


    public boolean is_select() {
        return is_select;
    }

    public void setIs_select(boolean is_select) {
        this.is_select = is_select;
    }

    /**
     * type_id : 1
     * type_name : 电工
     * is_delete : 0
     * create_time : 2017-10-30 10:25:11
     * class_id : 2
     */

    private int type_id;
    private String type_name;
    private String is_delete;
    private String create_time;
    private int class_id;
    private boolean is_select;

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }
}
