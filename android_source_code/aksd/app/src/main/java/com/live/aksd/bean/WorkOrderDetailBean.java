package com.live.aksd.bean;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/1
 */

public class WorkOrderDetailBean {


    /**
     * order_id : 323
     * order_member_id : 133
     * order_name : 测试006
     * order_phone : 15557988275
     * order_address_country :
     * order_address_province : 江苏省
     * order_address_city : 淮安市
     * order_address_district : 金湖县
     * order_address_detail : 十佳大道
     * order_subscribe_content : 家电清洗空调清洗其他172元/平方
     * order_subscribe_note :
     * order_subscribe_img1 :
     * order_subscribe_img2 :
     * order_subscribe_img3 :
     * orderSubscribeImgBeans : []
     * order_hope_service_time : 2018-03-09 21:57:00
     * order_create_time : 2018-03-14 10:05:43
     * order_update_time : 2018-03-14 10:05:43
     * order_audit_pass_time :
     * order_state : 11
     * order_accept_id : 195
     * order_accept_time :
     * order_cancle_why :
     * order_cancle_time :
     * order_cancle_pass_time :
     * order_reality_content :
     * orderCompleteImgBeans : []
     * order_complete_img1 :
     * order_complete_img2 :
     * order_complete_img3 :
     * order_complete_note :
     * order_service_time :
     * order_complete_time :
     * order_complete_pass_time :
     * order_is_delete : 0
     * order_class_id : 37
     * order_accept : {"member_id":195,"member_account":"18438558202","member_password":"e10adc3949ba59abbe56e057f20f883e","member_token":"cc3b6b8b-8ce4-47e4-88f5-56bfa5ca702b","member_real_name":"哈哈","member_nick_name":"shifuhelp053157","member_phone":"18438558202","id_number":"123858558855668875","special_skill":"电工八级","recommend_phone":"15687569852","custome_refuse_note":"叫哥给你通过","member_type":"1","member_create_time":"2018-03-09 16:57:04","member_update_time":"2018-03-13 17:59:17","member_is_delete":"0","member_head_image":"/images/member/15208227418691374506350.jpg","member_sex":"男","member_age":"55","member_work_type":"水工/电工/水电工/家电维工","member_work_age":"25","member_state":"1","member_integral":330,"member_bank_name":"","member_bank_open_name":"","member_bank__user_name":"","member_bank_code":"","member_bank_phone":"","member_certificate":"/html/member/20180313173931953316089.html","member_certificate_content":"","member_freeze_money":0,"member_extract_money":0,"member_deposit_money":0.01,"member_alipay":"","member_alipay_real_name":"","member_we_chat":"","member_we_chat_real_name":"","member_open_id":"","member_service_name":"哈哈","member_service_phone":"18438426853","member_service_country":"中国","member_service_province":"上海市","member_service_city":"上海市","member_service_district":"浦东新区","member_service_detail":"杨思地铁站","member_qrcode_img":"","member_pay_password":"","merchants_account_id":"","member_is_star":"1","member_is_star_show":"是","month_time":"","month_integral":"0","member_service_longitude":"121.493443","member_service_latitude":"31.161054","member_service_number":"0","member_good_rate":"100%","nead_deposit":"0.01","is_sign":"0","member_old_password":"","star_worker_info":"/html/member/20180313173931678467557.html","star_worker_info_content":"","member_state_show":"审核通过","customer_note":"","is_disable":"0","is_disable_show":"启用","disable_note":"","district":"","addressBeans":[]}
     * order_is_cancle : 0
     * order_service_attitude :
     * order_service_aging :
     * order_sercice_quality :
     * order_evaluate_content :
     * stateList :
     * order_address_longitude : 119.015285
     * order_address_latitude : 33.610353
     * distance :
     * order_price : 0.00
     * order_state_show : 已派单待接单
     * is_today_order :
     * complaints_content :
     * complaints_time :
     * is_complaints : 0
     * district :
     * deposit_price :
     * is_lock : 0
     * lock_id :
     * worker_name :
     * work_area :
     * hope_complete_time :
     * work_requirements :
     * recommend_phone :
     */

    private String order_id;
    private String order_member_id;
    private String order_name;
    private String order_phone;
    private String order_address_country;
    private String order_address_province;
    private String order_address_city;
    private String order_address_district;
    private String order_address_detail;
    private String order_subscribe_content;
    private String order_subscribe_note;
    private String order_subscribe_img1;
    private String order_subscribe_img2;
    private String order_subscribe_img3;
    private String order_hope_service_time;
    private String order_create_time;
    private String order_update_time;
    private String order_audit_pass_time;
    private String order_state;
    private String order_accept_id;
    private String order_accept_time;
    private String order_cancle_why;
    private String order_cancle_time;
    private String order_cancle_pass_time;
    private String order_reality_content;
    private String order_complete_img1;
    private String order_complete_img2;
    private String order_complete_img3;
    private String order_complete_note;
    private String order_service_time;
    private String order_complete_time;
    private String order_complete_pass_time;
    private String order_is_delete;
    private String order_class_id;
    private OrderAcceptBean order_accept;
    private String order_is_cancle;
    private String order_service_attitude;
    private String order_service_aging;
    private String order_service_quality;
    private String order_evaluate_content;
    private String stateList;
    private String order_address_longitude;

    public String getService_class_price() {
        return service_class_price;
    }

    public void setService_class_price(String service_class_price) {
        this.service_class_price = service_class_price;
    }

    private String order_address_latitude;
    private String  service_class_price;

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    private String order_type;

    public String getOrder_service_quality() {
        return order_service_quality;
    }

    public void setOrder_service_quality(String order_service_quality) {
        this.order_service_quality = order_service_quality;
    }


    private String distance;
    private String order_price;
    private String order_state_show;
    private String is_today_order;
    private String complaints_content;
    private String complaints_time;
    private String is_complaints;
    private String district;
    private String deposit_price;
    private String is_lock;
    private String lock_id;
    private String worker_name;
    private String work_area;
    private String work_way;
    private String hope_complete_time;
    private String work_requirements;
    private String recommend_phone;
    private List<String> orderSubscribeImgBeans;
    private List<String> orderCompleteImgBeans;
    private String others_service_content;
    private String  others_price;

    public String getOthers_service_content() {
        return others_service_content;
    }

    public void setOthers_service_content(String others_service_content) {
        this.others_service_content = others_service_content;
    }

    public String getOthers_price() {
        return others_price;
    }

    public void setOthers_price(String others_price) {
        this.others_price = others_price;
    }

    public String getOrder_final_price() {
        return order_final_price;
    }

    public void setOrder_final_price(String order_final_price) {
        this.order_final_price = order_final_price;
    }

    private String order_final_price;

    public String getWork_way() {
        return work_way;
    }

    public void setWork_way(String work_way) {
        this.work_way = work_way;
    }



    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_phone() {
        return order_phone;
    }

    public void setOrder_phone(String order_phone) {
        this.order_phone = order_phone;
    }

    public String getOrder_address_country() {
        return order_address_country;
    }

    public void setOrder_address_country(String order_address_country) {
        this.order_address_country = order_address_country;
    }

    public String getOrder_address_province() {
        return order_address_province;
    }

    public void setOrder_address_province(String order_address_province) {
        this.order_address_province = order_address_province;
    }

    public String getOrder_address_city() {
        return order_address_city;
    }

    public void setOrder_address_city(String order_address_city) {
        this.order_address_city = order_address_city;
    }

    public String getOrder_address_district() {
        return order_address_district;
    }

    public void setOrder_address_district(String order_address_district) {
        this.order_address_district = order_address_district;
    }

    public String getOrder_address_detail() {
        return order_address_detail;
    }

    public void setOrder_address_detail(String order_address_detail) {
        this.order_address_detail = order_address_detail;
    }

    public String getOrder_subscribe_content() {
        return order_subscribe_content;
    }

    public void setOrder_subscribe_content(String order_subscribe_content) {
        this.order_subscribe_content = order_subscribe_content;
    }

    public String getOrder_subscribe_note() {
        return order_subscribe_note;
    }

    public void setOrder_subscribe_note(String order_subscribe_note) {
        this.order_subscribe_note = order_subscribe_note;
    }

    public String getOrder_subscribe_img1() {
        return order_subscribe_img1;
    }

    public void setOrder_subscribe_img1(String order_subscribe_img1) {
        this.order_subscribe_img1 = order_subscribe_img1;
    }

    public String getOrder_subscribe_img2() {
        return order_subscribe_img2;
    }

    public void setOrder_subscribe_img2(String order_subscribe_img2) {
        this.order_subscribe_img2 = order_subscribe_img2;
    }

    public String getOrder_subscribe_img3() {
        return order_subscribe_img3;
    }

    public void setOrder_subscribe_img3(String order_subscribe_img3) {
        this.order_subscribe_img3 = order_subscribe_img3;
    }

    public String getOrder_hope_service_time() {
        return order_hope_service_time;
    }

    public void setOrder_hope_service_time(String order_hope_service_time) {
        this.order_hope_service_time = order_hope_service_time;
    }

    public String getOrder_create_time() {
        return order_create_time;
    }

    public void setOrder_create_time(String order_create_time) {
        this.order_create_time = order_create_time;
    }

    public String getOrder_update_time() {
        return order_update_time;
    }

    public void setOrder_update_time(String order_update_time) {
        this.order_update_time = order_update_time;
    }

    public String getOrder_audit_pass_time() {
        return order_audit_pass_time;
    }

    public void setOrder_audit_pass_time(String order_audit_pass_time) {
        this.order_audit_pass_time = order_audit_pass_time;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }



    public String getOrder_accept_time() {
        return order_accept_time;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_member_id() {
        return order_member_id;
    }

    public void setOrder_member_id(String order_member_id) {
        this.order_member_id = order_member_id;
    }

    public String getOrder_accept_id() {
        return order_accept_id;
    }

    public void setOrder_accept_id(String order_accept_id) {
        this.order_accept_id = order_accept_id;
    }

    public String getOrder_class_id() {
        return order_class_id;
    }

    public void setOrder_class_id(String order_class_id) {
        this.order_class_id = order_class_id;
    }

    public String getOrder_is_cancle() {
        return order_is_cancle;
    }

    public void setOrder_is_cancle(String order_is_cancle) {
        this.order_is_cancle = order_is_cancle;
    }

    public void setOrder_accept_time(String order_accept_time) {
        this.order_accept_time = order_accept_time;
    }

    public String getOrder_cancle_why() {
        return order_cancle_why;
    }

    public void setOrder_cancle_why(String order_cancle_why) {
        this.order_cancle_why = order_cancle_why;
    }

    public String getOrder_cancle_time() {
        return order_cancle_time;
    }

    public void setOrder_cancle_time(String order_cancle_time) {
        this.order_cancle_time = order_cancle_time;
    }

    public String getOrder_cancle_pass_time() {
        return order_cancle_pass_time;
    }

    public void setOrder_cancle_pass_time(String order_cancle_pass_time) {
        this.order_cancle_pass_time = order_cancle_pass_time;
    }

    public String getOrder_reality_content() {
        return order_reality_content;
    }

    public void setOrder_reality_content(String order_reality_content) {
        this.order_reality_content = order_reality_content;
    }

    public String getOrder_complete_img1() {
        return order_complete_img1;
    }

    public void setOrder_complete_img1(String order_complete_img1) {
        this.order_complete_img1 = order_complete_img1;
    }

    public String getOrder_complete_img2() {
        return order_complete_img2;
    }

    public void setOrder_complete_img2(String order_complete_img2) {
        this.order_complete_img2 = order_complete_img2;
    }

    public String getOrder_complete_img3() {
        return order_complete_img3;
    }

    public void setOrder_complete_img3(String order_complete_img3) {
        this.order_complete_img3 = order_complete_img3;
    }

    public String getOrder_complete_note() {
        return order_complete_note;
    }

    public void setOrder_complete_note(String order_complete_note) {
        this.order_complete_note = order_complete_note;
    }

    public String getOrder_service_time() {
        return order_service_time;
    }

    public void setOrder_service_time(String order_service_time) {
        this.order_service_time = order_service_time;
    }

    public String getOrder_complete_time() {
        return order_complete_time;
    }

    public void setOrder_complete_time(String order_complete_time) {
        this.order_complete_time = order_complete_time;
    }

    public String getOrder_complete_pass_time() {
        return order_complete_pass_time;
    }

    public void setOrder_complete_pass_time(String order_complete_pass_time) {
        this.order_complete_pass_time = order_complete_pass_time;
    }

    public String getOrder_is_delete() {
        return order_is_delete;
    }

    public void setOrder_is_delete(String order_is_delete) {
        this.order_is_delete = order_is_delete;
    }



    public OrderAcceptBean getOrder_accept() {
        return order_accept;
    }

    public void setOrder_accept(OrderAcceptBean order_accept) {
        this.order_accept = order_accept;
    }


    public String getOrder_service_attitude() {
        return order_service_attitude;
    }

    public void setOrder_service_attitude(String order_service_attitude) {
        this.order_service_attitude = order_service_attitude;
    }

    public String getOrder_service_aging() {
        return order_service_aging;
    }

    public void setOrder_service_aging(String order_service_aging) {
        this.order_service_aging = order_service_aging;
    }



    public String getOrder_evaluate_content() {
        return order_evaluate_content;
    }

    public void setOrder_evaluate_content(String order_evaluate_content) {
        this.order_evaluate_content = order_evaluate_content;
    }

    public String getStateList() {
        return stateList;
    }

    public void setStateList(String stateList) {
        this.stateList = stateList;
    }

    public String getOrder_address_longitude() {
        return order_address_longitude;
    }

    public void setOrder_address_longitude(String order_address_longitude) {
        this.order_address_longitude = order_address_longitude;
    }

    public String getOrder_address_latitude() {
        return order_address_latitude;
    }

    public void setOrder_address_latitude(String order_address_latitude) {
        this.order_address_latitude = order_address_latitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder_state_show() {
        return order_state_show;
    }

    public void setOrder_state_show(String order_state_show) {
        this.order_state_show = order_state_show;
    }

    public String getIs_today_order() {
        return is_today_order;
    }

    public void setIs_today_order(String is_today_order) {
        this.is_today_order = is_today_order;
    }

    public String getComplaints_content() {
        return complaints_content;
    }

    public void setComplaints_content(String complaints_content) {
        this.complaints_content = complaints_content;
    }

    public String getComplaints_time() {
        return complaints_time;
    }

    public void setComplaints_time(String complaints_time) {
        this.complaints_time = complaints_time;
    }

    public String getIs_complaints() {
        return is_complaints;
    }

    public void setIs_complaints(String is_complaints) {
        this.is_complaints = is_complaints;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDeposit_price() {
        return deposit_price;
    }

    public void setDeposit_price(String deposit_price) {
        this.deposit_price = deposit_price;
    }

    public String getIs_lock() {
        return is_lock;
    }

    public void setIs_lock(String is_lock) {
        this.is_lock = is_lock;
    }

    public String getLock_id() {
        return lock_id;
    }

    public void setLock_id(String lock_id) {
        this.lock_id = lock_id;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getWork_area() {
        return work_area;
    }

    public void setWork_area(String work_area) {
        this.work_area = work_area;
    }

    public String getHope_complete_time() {
        return hope_complete_time;
    }

    public void setHope_complete_time(String hope_complete_time) {
        this.hope_complete_time = hope_complete_time;
    }

    public String getWork_requirements() {
        return work_requirements;
    }

    public void setWork_requirements(String work_requirements) {
        this.work_requirements = work_requirements;
    }

    public String getRecommend_phone() {
        return recommend_phone;
    }

    public void setRecommend_phone(String recommend_phone) {
        this.recommend_phone = recommend_phone;
    }

    public List<String> getOrderSubscribeImgBeans() {
        return orderSubscribeImgBeans;
    }

    public void setOrderSubscribeImgBeans(List<String> orderSubscribeImgBeans) {
        this.orderSubscribeImgBeans = orderSubscribeImgBeans;
    }

    public List<String> getOrderCompleteImgBeans() {
        return orderCompleteImgBeans;
    }

    public void setOrderCompleteImgBeans(List<String> orderCompleteImgBeans) {
        this.orderCompleteImgBeans = orderCompleteImgBeans;
    }

    public static class OrderAcceptBean {
        /**
         * member_id : 195
         * member_account : 18438558202
         * member_password : e10adc3949ba59abbe56e057f20f883e
         * member_token : cc3b6b8b-8ce4-47e4-88f5-56bfa5ca702b
         * member_real_name : 哈哈
         * member_nick_name : shifuhelp053157
         * member_phone : 18438558202
         * id_number : 123858558855668875
         * special_skill : 电工八级
         * recommend_phone : 15687569852
         * custome_refuse_note : 叫哥给你通过
         * member_type : 1
         * member_create_time : 2018-03-09 16:57:04
         * member_update_time : 2018-03-13 17:59:17
         * member_is_delete : 0
         * member_head_image : /images/member/15208227418691374506350.jpg
         * member_sex : 男
         * member_age : 55
         * member_work_type : 水工/电工/水电工/家电维工
         * member_work_age : 25
         * member_state : 1
         * member_integral : 330
         * member_bank_name :
         * member_bank_open_name :
         * member_bank__user_name :
         * member_bank_code :
         * member_bank_phone :
         * member_certificate : /html/member/20180313173931953316089.html
         * member_certificate_content :
         * member_freeze_money : 0
         * member_extract_money : 0
         * member_deposit_money : 0.01
         * member_alipay :
         * member_alipay_real_name :
         * member_we_chat :
         * member_we_chat_real_name :
         * member_open_id :
         * member_service_name : 哈哈
         * member_service_phone : 18438426853
         * member_service_country : 中国
         * member_service_province : 上海市
         * member_service_city : 上海市
         * member_service_district : 浦东新区
         * member_service_detail : 杨思地铁站
         * member_qrcode_img :
         * member_pay_password :
         * merchants_account_id :
         * member_is_star : 1
         * member_is_star_show : 是
         * month_time :
         * month_integral : 0
         * member_service_longitude : 121.493443
         * member_service_latitude : 31.161054
         * member_service_number : 0
         * member_good_rate : 100%
         * nead_deposit : 0.01
         * is_sign : 0
         * member_old_password :
         * star_worker_info : /html/member/20180313173931678467557.html
         * star_worker_info_content :
         * member_state_show : 审核通过
         * customer_note :
         * is_disable : 0
         * is_disable_show : 启用
         * disable_note :
         * district :
         * addressBeans : []
         */

        private String member_id;
        private String member_account;
        private String member_password;
        private String member_token;
        private String member_real_name;
        private String member_nick_name;
        private String member_phone;
        private String id_number;
        private String special_skill;
        private String recommend_phone;
        private String custome_refuse_note;
        private String member_type;
        private String member_create_time;
        private String member_update_time;
        private String member_is_delete;
        private String member_head_image;
        private String member_sex;
        private String member_age;
        private String member_work_type;
        private String member_work_age;
        private String member_state;
        private String member_integral;
        private String member_bank_name;
        private String member_bank_open_name;
        private String member_bank__user_name;
        private String member_bank_code;
        private String member_bank_phone;
        private String member_certificate;
        private String member_certificate_content;
        private String member_freeze_money;
        private String member_extract_money;
        private String member_deposit_money;
        private String member_alipay;
        private String member_alipay_real_name;
        private String member_we_chat;
        private String member_we_chat_real_name;
        private String member_open_id;
        private String member_service_name;
        private String member_service_phone;
        private String member_service_country;
        private String member_service_province;
        private String member_service_city;
        private String member_service_district;
        private String member_service_detail;
        private String member_qrcode_img;
        private String member_pay_password;
        private String merchants_account_id;
        private String member_is_star;
        private String member_is_star_show;
        private String month_time;
        private String month_integral;
        private String member_service_longitude;
        private String member_service_latitude;
        private String member_service_number;
        private String member_good_rate;
        private String nead_deposit;
        private String is_sign;
        private String member_old_password;
        private String star_worker_info;
        private String star_worker_info_content;
        private String member_state_show;
        private String customer_note;
        private String is_disable;
        private String is_disable_show;
        private String disable_note;
        private String district;



        public String getMember_account() {
            return member_account;
        }

        public void setMember_account(String member_account) {
            this.member_account = member_account;
        }

        public String getMember_password() {
            return member_password;
        }

        public void setMember_password(String member_password) {
            this.member_password = member_password;
        }

        public String getMember_token() {
            return member_token;
        }

        public void setMember_token(String member_token) {
            this.member_token = member_token;
        }

        public String getMember_real_name() {
            return member_real_name;
        }

        public void setMember_real_name(String member_real_name) {
            this.member_real_name = member_real_name;
        }

        public String getMember_nick_name() {
            return member_nick_name;
        }

        public void setMember_nick_name(String member_nick_name) {
            this.member_nick_name = member_nick_name;
        }

        public String getMember_phone() {
            return member_phone;
        }

        public void setMember_phone(String member_phone) {
            this.member_phone = member_phone;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getSpecial_skill() {
            return special_skill;
        }

        public void setSpecial_skill(String special_skill) {
            this.special_skill = special_skill;
        }

        public String getRecommend_phone() {
            return recommend_phone;
        }

        public void setRecommend_phone(String recommend_phone) {
            this.recommend_phone = recommend_phone;
        }

        public String getCustome_refuse_note() {
            return custome_refuse_note;
        }

        public void setCustome_refuse_note(String custome_refuse_note) {
            this.custome_refuse_note = custome_refuse_note;
        }

        public String getMember_type() {
            return member_type;
        }

        public void setMember_type(String member_type) {
            this.member_type = member_type;
        }

        public String getMember_create_time() {
            return member_create_time;
        }

        public void setMember_create_time(String member_create_time) {
            this.member_create_time = member_create_time;
        }

        public String getMember_update_time() {
            return member_update_time;
        }

        public void setMember_update_time(String member_update_time) {
            this.member_update_time = member_update_time;
        }

        public String getMember_is_delete() {
            return member_is_delete;
        }

        public void setMember_is_delete(String member_is_delete) {
            this.member_is_delete = member_is_delete;
        }

        public String getMember_head_image() {
            return member_head_image;
        }

        public void setMember_head_image(String member_head_image) {
            this.member_head_image = member_head_image;
        }

        public String getMember_sex() {
            return member_sex;
        }

        public void setMember_sex(String member_sex) {
            this.member_sex = member_sex;
        }

        public String getMember_age() {
            return member_age;
        }

        public void setMember_age(String member_age) {
            this.member_age = member_age;
        }

        public String getMember_work_type() {
            return member_work_type;
        }

        public void setMember_work_type(String member_work_type) {
            this.member_work_type = member_work_type;
        }

        public String getMember_work_age() {
            return member_work_age;
        }

        public void setMember_work_age(String member_work_age) {
            this.member_work_age = member_work_age;
        }

        public String getMember_state() {
            return member_state;
        }

        public void setMember_state(String member_state) {
            this.member_state = member_state;
        }



        public String getMember_bank_name() {
            return member_bank_name;
        }

        public void setMember_bank_name(String member_bank_name) {
            this.member_bank_name = member_bank_name;
        }

        public String getMember_bank_open_name() {
            return member_bank_open_name;
        }

        public void setMember_bank_open_name(String member_bank_open_name) {
            this.member_bank_open_name = member_bank_open_name;
        }

        public String getMember_bank__user_name() {
            return member_bank__user_name;
        }

        public void setMember_bank__user_name(String member_bank__user_name) {
            this.member_bank__user_name = member_bank__user_name;
        }

        public String getMember_bank_code() {
            return member_bank_code;
        }

        public void setMember_bank_code(String member_bank_code) {
            this.member_bank_code = member_bank_code;
        }

        public String getMember_bank_phone() {
            return member_bank_phone;
        }

        public void setMember_bank_phone(String member_bank_phone) {
            this.member_bank_phone = member_bank_phone;
        }

        public String getMember_certificate() {
            return member_certificate;
        }

        public void setMember_certificate(String member_certificate) {
            this.member_certificate = member_certificate;
        }

        public String getMember_certificate_content() {
            return member_certificate_content;
        }

        public void setMember_certificate_content(String member_certificate_content) {
            this.member_certificate_content = member_certificate_content;
        }


        public String getMember_deposit_money() {
            return member_deposit_money;
        }

        public void setMember_deposit_money(String member_deposit_money) {
            this.member_deposit_money = member_deposit_money;
        }

        public String getMember_alipay() {
            return member_alipay;
        }

        public void setMember_alipay(String member_alipay) {
            this.member_alipay = member_alipay;
        }

        public String getMember_alipay_real_name() {
            return member_alipay_real_name;
        }

        public void setMember_alipay_real_name(String member_alipay_real_name) {
            this.member_alipay_real_name = member_alipay_real_name;
        }

        public String getMember_we_chat() {
            return member_we_chat;
        }

        public void setMember_we_chat(String member_we_chat) {
            this.member_we_chat = member_we_chat;
        }

        public String getMember_we_chat_real_name() {
            return member_we_chat_real_name;
        }

        public void setMember_we_chat_real_name(String member_we_chat_real_name) {
            this.member_we_chat_real_name = member_we_chat_real_name;
        }

        public String getMember_open_id() {
            return member_open_id;
        }

        public void setMember_open_id(String member_open_id) {
            this.member_open_id = member_open_id;
        }

        public String getMember_service_name() {
            return member_service_name;
        }

        public void setMember_service_name(String member_service_name) {
            this.member_service_name = member_service_name;
        }

        public String getMember_service_phone() {
            return member_service_phone;
        }

        public void setMember_service_phone(String member_service_phone) {
            this.member_service_phone = member_service_phone;
        }

        public String getMember_service_country() {
            return member_service_country;
        }

        public void setMember_service_country(String member_service_country) {
            this.member_service_country = member_service_country;
        }

        public String getMember_service_province() {
            return member_service_province;
        }

        public void setMember_service_province(String member_service_province) {
            this.member_service_province = member_service_province;
        }

        public String getMember_service_city() {
            return member_service_city;
        }

        public void setMember_service_city(String member_service_city) {
            this.member_service_city = member_service_city;
        }

        public String getMember_service_district() {
            return member_service_district;
        }

        public void setMember_service_district(String member_service_district) {
            this.member_service_district = member_service_district;
        }

        public String getMember_service_detail() {
            return member_service_detail;
        }

        public void setMember_service_detail(String member_service_detail) {
            this.member_service_detail = member_service_detail;
        }

        public String getMember_qrcode_img() {
            return member_qrcode_img;
        }

        public void setMember_qrcode_img(String member_qrcode_img) {
            this.member_qrcode_img = member_qrcode_img;
        }

        public String getMember_pay_password() {
            return member_pay_password;
        }

        public void setMember_pay_password(String member_pay_password) {
            this.member_pay_password = member_pay_password;
        }

        public String getMerchants_account_id() {
            return merchants_account_id;
        }

        public void setMerchants_account_id(String merchants_account_id) {
            this.merchants_account_id = merchants_account_id;
        }

        public String getMember_is_star() {
            return member_is_star;
        }

        public void setMember_is_star(String member_is_star) {
            this.member_is_star = member_is_star;
        }

        public String getMember_is_star_show() {
            return member_is_star_show;
        }

        public void setMember_is_star_show(String member_is_star_show) {
            this.member_is_star_show = member_is_star_show;
        }

        public String getMonth_time() {
            return month_time;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMember_integral() {
            return member_integral;
        }

        public void setMember_integral(String member_integral) {
            this.member_integral = member_integral;
        }

        public String getMember_freeze_money() {
            return member_freeze_money;
        }

        public void setMember_freeze_money(String member_freeze_money) {
            this.member_freeze_money = member_freeze_money;
        }

        public String getMember_extract_money() {
            return member_extract_money;
        }

        public void setMember_extract_money(String member_extract_money) {
            this.member_extract_money = member_extract_money;
        }

        public void setMonth_time(String month_time) {
            this.month_time = month_time;
        }

        public String getMonth_integral() {
            return month_integral;
        }

        public void setMonth_integral(String month_integral) {
            this.month_integral = month_integral;
        }

        public String getMember_service_longitude() {
            return member_service_longitude;
        }

        public void setMember_service_longitude(String member_service_longitude) {
            this.member_service_longitude = member_service_longitude;
        }

        public String getMember_service_latitude() {
            return member_service_latitude;
        }

        public void setMember_service_latitude(String member_service_latitude) {
            this.member_service_latitude = member_service_latitude;
        }

        public String getMember_service_number() {
            return member_service_number;
        }

        public void setMember_service_number(String member_service_number) {
            this.member_service_number = member_service_number;
        }

        public String getMember_good_rate() {
            return member_good_rate;
        }

        public void setMember_good_rate(String member_good_rate) {
            this.member_good_rate = member_good_rate;
        }

        public String getNead_deposit() {
            return nead_deposit;
        }

        public void setNead_deposit(String nead_deposit) {
            this.nead_deposit = nead_deposit;
        }

        public String getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(String is_sign) {
            this.is_sign = is_sign;
        }

        public String getMember_old_password() {
            return member_old_password;
        }

        public void setMember_old_password(String member_old_password) {
            this.member_old_password = member_old_password;
        }

        public String getStar_worker_info() {
            return star_worker_info;
        }

        public void setStar_worker_info(String star_worker_info) {
            this.star_worker_info = star_worker_info;
        }

        public String getStar_worker_info_content() {
            return star_worker_info_content;
        }

        public void setStar_worker_info_content(String star_worker_info_content) {
            this.star_worker_info_content = star_worker_info_content;
        }

        public String getMember_state_show() {
            return member_state_show;
        }

        public void setMember_state_show(String member_state_show) {
            this.member_state_show = member_state_show;
        }

        public String getCustomer_note() {
            return customer_note;
        }

        public void setCustomer_note(String customer_note) {
            this.customer_note = customer_note;
        }

        public String getIs_disable() {
            return is_disable;
        }

        public void setIs_disable(String is_disable) {
            this.is_disable = is_disable;
        }

        public String getIs_disable_show() {
            return is_disable_show;
        }

        public void setIs_disable_show(String is_disable_show) {
            this.is_disable_show = is_disable_show;
        }

        public String getDisable_note() {
            return disable_note;
        }

        public void setDisable_note(String disable_note) {
            this.disable_note = disable_note;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }


    }
}
