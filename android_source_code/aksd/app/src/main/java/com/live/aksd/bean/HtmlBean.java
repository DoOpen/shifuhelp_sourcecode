package com.live.aksd.bean;

/**
 * Created by liuxiaoxiang on 2018/3/13.
 */

public class HtmlBean {


    /**
     * html_id : 1
     * html_name : 关于我们
     * html_url : /html/others/2017111402020597035723.html
     * html_url_content :
     * sort : 1
     * is_delete : 0
     * create_time : 2017-11-14 01:51:59
     * update_time : 2017-11-14 01:51:59
     */

    private int html_id;
    private String html_name;
    private String html_url;
    private String html_url_content;
    private int sort;
    private String is_delete;
    private String create_time;
    private String update_time;

    public int getHtml_id() {
        return html_id;
    }

    public void setHtml_id(int html_id) {
        this.html_id = html_id;
    }

    public String getHtml_name() {
        return html_name;
    }

    public void setHtml_name(String html_name) {
        this.html_name = html_name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getHtml_url_content() {
        return html_url_content;
    }

    public void setHtml_url_content(String html_url_content) {
        this.html_url_content = html_url_content;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
