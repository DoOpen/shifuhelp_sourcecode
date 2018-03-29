package com.live.aksd.bean;

/**
 * @author Created by stone
 * @since 17/10/10
 */

public class TrainingDetailBean {

    /**
     * training_id : 1
     * training_title : 水维修
     * training_profile : 这是水维修
     * training_content_url :
     * training_video_url :
     * is_delete : 0
     * create_time : 2017-10-10 15:35:54
     * class_id : 1
     * type : 文章
     * training_img :
     */

    private int training_id;
    private String training_title;
    private String training_profile;
    private String training_content_url;
    private String training_video_url;
    private String is_delete;
    private String create_time;
    private int class_id;
    private String type;
    private String training_img;

    public int getTraining_id() {
        return training_id;
    }

    public void setTraining_id(int training_id) {
        this.training_id = training_id;
    }

    public String getTraining_title() {
        return training_title;
    }

    public void setTraining_title(String training_title) {
        this.training_title = training_title;
    }

    public String getTraining_profile() {
        return training_profile;
    }

    public void setTraining_profile(String training_profile) {
        this.training_profile = training_profile;
    }

    public String getTraining_content_url() {
        return training_content_url;
    }

    public void setTraining_content_url(String training_content_url) {
        this.training_content_url = training_content_url;
    }

    public String getTraining_video_url() {
        return training_video_url;
    }

    public void setTraining_video_url(String training_video_url) {
        this.training_video_url = training_video_url;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTraining_img() {
        return training_img;
    }

    public void setTraining_img(String training_img) {
        this.training_img = training_img;
    }
}
