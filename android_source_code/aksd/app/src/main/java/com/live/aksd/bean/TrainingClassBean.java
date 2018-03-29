package com.live.aksd.bean;

/**
 * @author Created by stone
 * @since 17/10/10
 */

public class TrainingClassBean {


    /**
     * class_id : 1
     * class_name : 水维修
     * is_delete : 0
     * create_time : 2017-10-10 15:26:35
     */

    private int class_id;
    private String class_name;
    private String is_delete;
    private String create_time;

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
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
}
