package com.live.aksd.bean;

/**
 * @author Created by stone
 * @since 17/8/31
 */

public class WorkOrderBean {


    /**
     * order_id : 48
     * order_member_id : 9
     * order_name : 已派单待接单
     * order_phone : 13111864235
     * order_address_country : 中国
     * order_address_province : 上海
     * order_address_city : 上海市
     * order_address_district : 浦东新区
     * order_address_detail : 杨新路77弄华夏小区
     * order_subscribe_content : 水电安装,清工,别墅,50元/平米
     * order_subscribe_note : 水管有水，厨房没水
     * order_subscribe_img1 : https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png
     * order_subscribe_img2 : https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png
     * order_subscribe_img3 : https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png
     * order_hope_service_time : 2017-07-21 17:01:10.0
     * order_create_time : 2017-07-24 05:32:33.0
     * order_update_time : 2017-07-24 05:32:33.0
     * order_audit_pass_time : 2017-08-10 17:58:58.0
     * order_state : 11
     * order_accept_id : 24
     * order_accept_time : 2017-08-25 11:00:56.0
     * order_cancle_why : 主人不在家
     * order_cancle_time : 2017-08-25 16:37:30.0
     * order_cancle_pass_time : 2017-08-25 16:37:30.0
     * order_reality_content : 哈哈哈
     * order_complete_img1 : https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png
     * order_complete_img2 : https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png
     * order_complete_img3 : https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png
     * order_complete_note :
     * order_service_time :
     * order_complete_time : 2017-08-25 16:35:57.0
     * order_complete_pass_time : 2017-08-25 16:35:57.0
     * order_is_delete : 0
     * order_class_id : 1
     * order_accept : {"member_id":24,"member_account":"","member_password":"","member_token":"c9d826f2-7a45-42cc-bbe6-b639079a0a07","member_real_name":"","member_nick_name":"","member_phone":"","member_type":"","member_create_time":"","member_update_time":"","member_is_delete":"","member_head_image":"","member_sex":"","member_age":"","member_work_type":"","member_work_age":"","member_state":"","member_integral":"","member_bank_name":"","member_bank_open_name":"","member_bank_user_name":"","member_bank_code":"","member_bank_phone":"","member_certificate":"","member_freeze_money":"","member_extract_money":"","member_deposit_money":"","member_alipay":"","member_alipay_real_name":"","member_we_chat":"","member_we_chat_real_name":"","member_open_id":"","member_service_name":"","member_service_phone":"","member_service_country":"","member_service_province":"","member_service_city":"","member_service_district":"","member_service_detail":"","member_qrcode_img":"","invitation_code":"","fill_invitation_code":"","member_pay_password":"","merchants_account_id":"","member_is_star":"","month_time":"","month_integral":"","member_service_longitude":"","member_service_latitude":"","member_service_number":"","member_good_rate":"","nead_deposit":"","is_sign":"","member_old_password":""}
     * order_is_cancle : 0
     * order_service_attitude : 3
     * order_service_aging : 5
     * order_sercice_quality : 5
     * order_evaluate_content : 很好
     * stateList :
     * order_address_longitude : 121.4976562127031
     * order_address_latitude : 31.166709902424408
     * distance :
     * order_price : 45
     * order_state_show : 派单未接单
     * is_today_order : 1
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
    private String order_is_cancle;
    private String order_service_attitude;
    private String order_service_aging;
    private String order_sercice_quality;
    private String order_evaluate_content;
    private String stateList;
    private String order_address_longitude;
    private String order_address_latitude;
    private String distance;
    private String order_price;
    private String order_state_show;
    private String is_today_order;
    private OrderAcceptBean order_accept;

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

    public String getOrder_accept_id() {
        return order_accept_id;
    }

    public void setOrder_accept_id(String order_accept_id) {
        this.order_accept_id = order_accept_id;
    }

    public String getOrder_is_delete() {
        return order_is_delete;
    }

    public void setOrder_is_delete(String order_is_delete) {
        this.order_is_delete = order_is_delete;
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

    public String getOrder_sercice_quality() {
        return order_sercice_quality;
    }

    public void setOrder_sercice_quality(String order_sercice_quality) {
        this.order_sercice_quality = order_sercice_quality;
    }

    public String getOrder_accept_time() {
        return order_accept_time;
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



    public OrderAcceptBean getOrder_accept() {
        return order_accept;
    }

    public void setOrder_accept(OrderAcceptBean order_accept) {
        this.order_accept = order_accept;
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

    public static class OrderAcceptBean {
        /**
         * member_id : 24
         * member_account :
         * member_password :
         * member_token : c9d826f2-7a45-42cc-bbe6-b639079a0a07
         * member_real_name :
         * member_nick_name :
         * member_phone :
         * member_type :
         * member_create_time :
         * member_update_time :
         * member_is_delete :
         * member_head_image :
         * member_sex :
         * member_age :
         * member_work_type :
         * member_work_age :
         * member_state :
         * member_integral :
         * member_bank_name :
         * member_bank_open_name :
         * member_bank_user_name :
         * member_bank_code :
         * member_bank_phone :
         * member_certificate :
         * member_freeze_money :
         * member_extract_money :
         * member_deposit_money :
         * member_alipay :
         * member_alipay_real_name :
         * member_we_chat :
         * member_we_chat_real_name :
         * member_open_id :
         * member_service_name :
         * member_service_phone :
         * member_service_country :
         * member_service_province :
         * member_service_city :
         * member_service_district :
         * member_service_detail :
         * member_qrcode_img :
         * invitation_code :
         * fill_invitation_code :
         * member_pay_password :
         * merchants_account_id :
         * member_is_star :
         * month_time :
         * month_integral :
         * member_service_longitude :
         * member_service_latitude :
         * member_service_number :
         * member_good_rate :
         * nead_deposit :
         * is_sign :
         * member_old_password :
         */

        private String member_id;
        private String member_account;
        private String member_password;
        private String member_token;
        private String member_real_name;
        private String member_nick_name;
        private String member_phone;
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
        private String member_bank_user_name;
        private String member_bank_code;
        private String member_bank_phone;
        private String member_certificate;
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
        private String invitation_code;
        private String fill_invitation_code;
        private String member_pay_password;
        private String merchants_account_id;
        private String member_is_star;
        private String month_time;
        private String month_integral;
        private String member_service_longitude;
        private String member_service_latitude;
        private String member_service_number;
        private String member_good_rate;
        private String nead_deposit;
        private String is_sign;
        private String member_old_password;

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

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

        public String getMember_integral() {
            return member_integral;
        }

        public void setMember_integral(String member_integral) {
            this.member_integral = member_integral;
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

        public String getMember_bank_user_name() {
            return member_bank_user_name;
        }

        public void setMember_bank_user_name(String member_bank_user_name) {
            this.member_bank_user_name = member_bank_user_name;
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

        public String getInvitation_code() {
            return invitation_code;
        }

        public void setInvitation_code(String invitation_code) {
            this.invitation_code = invitation_code;
        }

        public String getFill_invitation_code() {
            return fill_invitation_code;
        }

        public void setFill_invitation_code(String fill_invitation_code) {
            this.fill_invitation_code = fill_invitation_code;
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

        public String getMonth_time() {
            return month_time;
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
    }
}
