package com.live.aksd.bean;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/14
 */

public class GoodsCommentsBean {
    /**
     * member_id : 93
     * order_id : 234
     * assessment_desc : 什么玩意啊
     * assessment_type : merchants
     * assessment_star1 : 3
     * assessment_star2 : 4
     * assessment_star3 : 5
     * relation_id : 39
     * assessmentImgBeans : [{"assessment_img":"/images/banner/banner1.jpg"},{"assessment_img":"/images/banner/banner1.jpg"}]
     */

    private String member_id;
    private String order_id;
    private String assessment_desc;
    private String assessment_type;
    private String assessment_star1;
    private String assessment_star2;
    private String assessment_star3;
    private String relation_id;
    private List<AssessmentImgBeansBean> assessmentImgBeans;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getAssessment_desc() {
        return assessment_desc;
    }

    public void setAssessment_desc(String assessment_desc) {
        this.assessment_desc = assessment_desc;
    }

    public String getAssessment_type() {
        return assessment_type;
    }

    public void setAssessment_type(String assessment_type) {
        this.assessment_type = assessment_type;
    }

    public String getAssessment_star1() {
        return assessment_star1;
    }

    public void setAssessment_star1(String assessment_star1) {
        this.assessment_star1 = assessment_star1;
    }

    public String getAssessment_star2() {
        return assessment_star2;
    }

    public void setAssessment_star2(String assessment_star2) {
        this.assessment_star2 = assessment_star2;
    }

    public String getAssessment_star3() {
        return assessment_star3;
    }

    public void setAssessment_star3(String assessment_star3) {
        this.assessment_star3 = assessment_star3;
    }

    public String getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(String relation_id) {
        this.relation_id = relation_id;
    }

    public List<AssessmentImgBeansBean> getAssessmentImgBeans() {
        return assessmentImgBeans;
    }

    public void setAssessmentImgBeans(List<AssessmentImgBeansBean> assessmentImgBeans) {
        this.assessmentImgBeans = assessmentImgBeans;
    }

    public static class AssessmentImgBeansBean {
        /**
         * assessment_img : /images/banner/banner1.jpg
         */

        private String assessment_img;

        public String getAssessment_img() {
            return assessment_img;
        }

        public void setAssessment_img(String assessment_img) {
            this.assessment_img = assessment_img;
        }
    }




/*
    private  String goods_id;
    private  String goods_mark;
    private  String content;
    private  String img;
    private  String thumb;


    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_mark() {
        return goods_mark;
    }

    public void setGoods_mark(String goods_mark) {
        this.goods_mark = goods_mark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
    */



}
