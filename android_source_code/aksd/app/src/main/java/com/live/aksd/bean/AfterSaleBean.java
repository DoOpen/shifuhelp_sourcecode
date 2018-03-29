package com.live.aksd.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Created by stone
 * @since 17/8/23
 * 售后订单
 */

public class AfterSaleBean implements Serializable{


    /**
     * refund_id : 8
     * refund_no : 20170224111056
     * member_id : 24
     * nick_name :
     * order_id : 8
     * name :
     * mobile :
     * order_goods_id : 8
     * goods_id : 8
     * goods_name :
     * refund_count : 3
     * refund_desc : OMG您
     * refund_state : accept
     * refund_state_show : 已接受
     * create_time : 2017-02-24 11:10:56.0
     * merchants_id :
     * merchants_name :
     * refund_price : 189.0
     * refund_deduct_integral :
     * refund_give_integral : 0
     * refund_order_no :
     * hx_account :
     * merchants_img :
     * refund_reason_id : 2
     * reason_name : 不想要了
     * order_no : 1683687254083503107
     * start_time :
     * end_time :
     * member_coupon_id :
     * refuse_desc :
     * refund_integral_value :
     * order_actual_price : 155.59
     * goods_sku :
     * custom_remark :
     * goodsBean : {"goods_id":8,"member_id":"","merchants_id":"1","goods_name":"拆豁妄楞责依农磕檬韶","goods_img":"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png","goods_price":"4943.00","goods_sales":910,"goods_inventory":5188,"goods_state":1,"goods_class":"1","goods_fast_pay":1,"goods_free_mail":1,"goods_cancle":1,"goods_is_delete":0,"goods_get_integral":50,"goods_goods_score":"5","goods_mail_score":"5","goods_delivery_score":"5","goods_create_time":"2017-08-03 15:45:34.0","goods_update_time":"2017-08-09 04:57:48.0","goods_desc":"","goods_url":"","min_price":"","max_price":"","goods_type":"2","sort":"","sort_way":"","goods_uuid":"aaa","goods_num":"","assessment_count":5,"assessment_img_count":2,"goods_url_content":"","merchantsBean":{"merchants_id":1,"merchants_account_id":"","merchants_star1":"4.98","merchants_star2":"4.80","merchants_star3":"4.98","merchants_name":"荷柏瑞","merchants_img":"/images/merchants//1482471175605.png","merchants_type":"2","contact_mobile":"12","contact_name":"12","merchants_address":"12","merchants_state":"1","merchants_states":"","merchants_province":"上海","merchants_city":"","merchants_area":"","merchants_desc_img":"/images/member/default_head.png","express_free_price":"0","is_delete":"0","create_time":"","card_id":"","merchants_email":"","zip_code":"","member_id":"","company_mobile":"","label_ids":"","company_name":"","is_default":"","qrcode_img":"","apply_state":"1","apply_states":"","refuse_remark":"","balance":0,"used_balance":0,"is_collection":"0","collection_id":"","merchants_desc":"","year_sales":"","month_sales":"","day_sales":"","goods_total_num":"65","hx_account":"","hx_pass":"","hx_nick_name":"","is_dynamic":"","is_hot":"","custom_id":"","bidding_price":"","job_start_time":"","job_end_time":"","merchantsImgBeans":[],"merchantsDescImgBeans":[],"merchantsLabelBeans":[]},"goodsBeans":[],"is_express":"0","express_price":"5","goodsImgBeans":[],"goodsClassBean":{},"goods_code":"13","goods_state_show":"上架","goodsSpecificationBeans":[],"specificationBeans":[]}
     * memberBean : {}
     * orderGoodsBean : {}
     * refundImgBeans : []
     */

    private int refund_id;
    private String refund_no;
    private String member_id;
    private String nick_name;
    private String order_id;
    private String name;
    private String mobile;
    private String order_goods_id;
    private String goods_id;
    private String goods_name;
    private String refund_count;
    private String refund_desc;
    private String refund_state;
    private String refund_state_show;
    private String create_time;
    private String merchants_id;
    private String merchants_name;
    private String refund_price;
    private String refund_deduct_integral;
    private String refund_give_integral;
    private String refund_order_no;
    private String hx_account;
    private String merchants_img;
    private String refund_reason_id;
    private String reason_name;
    private String order_no;
    private String start_time;
    private String end_time;
    private String member_coupon_id;
    private String refuse_desc;
    private String refund_integral_value;
    private String order_actual_price;
    private String goods_sku;
    private String custom_remark;
    private GoodsBeanBean goodsBean;
    private MemberBeanBean memberBean;
    private OrderGoodsBeanBean orderGoodsBean;
    private List<?> refundImgBeans;

    public int getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(int refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_no() {
        return refund_no;
    }

    public void setRefund_no(String refund_no) {
        this.refund_no = refund_no;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrder_goods_id() {
        return order_goods_id;
    }

    public void setOrder_goods_id(String order_goods_id) {
        this.order_goods_id = order_goods_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(String refund_count) {
        this.refund_count = refund_count;
    }

    public String getRefund_desc() {
        return refund_desc;
    }

    public void setRefund_desc(String refund_desc) {
        this.refund_desc = refund_desc;
    }

    public String getRefund_state() {
        return refund_state;
    }

    public void setRefund_state(String refund_state) {
        this.refund_state = refund_state;
    }

    public String getRefund_state_show() {
        return refund_state_show;
    }

    public void setRefund_state_show(String refund_state_show) {
        this.refund_state_show = refund_state_show;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getMerchants_id() {
        return merchants_id;
    }

    public void setMerchants_id(String merchants_id) {
        this.merchants_id = merchants_id;
    }

    public String getMerchants_name() {
        return merchants_name;
    }

    public void setMerchants_name(String merchants_name) {
        this.merchants_name = merchants_name;
    }

    public String getRefund_price() {
        return refund_price;
    }

    public void setRefund_price(String refund_price) {
        this.refund_price = refund_price;
    }

    public String getRefund_deduct_integral() {
        return refund_deduct_integral;
    }

    public void setRefund_deduct_integral(String refund_deduct_integral) {
        this.refund_deduct_integral = refund_deduct_integral;
    }

    public String getRefund_give_integral() {
        return refund_give_integral;
    }

    public void setRefund_give_integral(String refund_give_integral) {
        this.refund_give_integral = refund_give_integral;
    }

    public String getRefund_order_no() {
        return refund_order_no;
    }

    public void setRefund_order_no(String refund_order_no) {
        this.refund_order_no = refund_order_no;
    }

    public String getHx_account() {
        return hx_account;
    }

    public void setHx_account(String hx_account) {
        this.hx_account = hx_account;
    }

    public String getMerchants_img() {
        return merchants_img;
    }

    public void setMerchants_img(String merchants_img) {
        this.merchants_img = merchants_img;
    }

    public String getRefund_reason_id() {
        return refund_reason_id;
    }

    public void setRefund_reason_id(String refund_reason_id) {
        this.refund_reason_id = refund_reason_id;
    }

    public String getReason_name() {
        return reason_name;
    }

    public void setReason_name(String reason_name) {
        this.reason_name = reason_name;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getMember_coupon_id() {
        return member_coupon_id;
    }

    public void setMember_coupon_id(String member_coupon_id) {
        this.member_coupon_id = member_coupon_id;
    }

    public String getRefuse_desc() {
        return refuse_desc;
    }

    public void setRefuse_desc(String refuse_desc) {
        this.refuse_desc = refuse_desc;
    }

    public String getRefund_integral_value() {
        return refund_integral_value;
    }

    public void setRefund_integral_value(String refund_integral_value) {
        this.refund_integral_value = refund_integral_value;
    }

    public String getOrder_actual_price() {
        return order_actual_price;
    }

    public void setOrder_actual_price(String order_actual_price) {
        this.order_actual_price = order_actual_price;
    }

    public String getGoods_sku() {
        return goods_sku;
    }

    public void setGoods_sku(String goods_sku) {
        this.goods_sku = goods_sku;
    }

    public String getCustom_remark() {
        return custom_remark;
    }

    public void setCustom_remark(String custom_remark) {
        this.custom_remark = custom_remark;
    }

    public GoodsBeanBean getGoodsBean() {
        return goodsBean;
    }

    public void setGoodsBean(GoodsBeanBean goodsBean) {
        this.goodsBean = goodsBean;
    }

    public MemberBeanBean getMemberBean() {
        return memberBean;
    }

    public void setMemberBean(MemberBeanBean memberBean) {
        this.memberBean = memberBean;
    }

    public OrderGoodsBeanBean getOrderGoodsBean() {
        return orderGoodsBean;
    }

    public void setOrderGoodsBean(OrderGoodsBeanBean orderGoodsBean) {
        this.orderGoodsBean = orderGoodsBean;
    }

    public List<?> getRefundImgBeans() {
        return refundImgBeans;
    }

    public void setRefundImgBeans(List<?> refundImgBeans) {
        this.refundImgBeans = refundImgBeans;
    }

    public static class GoodsBeanBean {
        /**
         * goods_id : 8
         * member_id :
         * merchants_id : 1
         * goods_name : 拆豁妄楞责依农磕檬韶
         * goods_img : https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png
         * goods_price : 4943.00
         * goods_sales : 910
         * goods_inventory : 5188
         * goods_state : 1
         * goods_class : 1
         * goods_fast_pay : 1
         * goods_free_mail : 1
         * goods_cancle : 1
         * goods_is_delete : 0
         * goods_get_integral : 50
         * goods_goods_score : 5
         * goods_mail_score : 5
         * goods_delivery_score : 5
         * goods_create_time : 2017-08-03 15:45:34.0
         * goods_update_time : 2017-08-09 04:57:48.0
         * goods_desc :
         * goods_url :
         * min_price :
         * max_price :
         * goods_type : 2
         * sort :
         * sort_way :
         * goods_uuid : aaa
         * goods_num :
         * assessment_count : 5
         * assessment_img_count : 2
         * goods_url_content :
         * merchantsBean : {"merchants_id":1,"merchants_account_id":"","merchants_star1":"4.98","merchants_star2":"4.80","merchants_star3":"4.98","merchants_name":"荷柏瑞","merchants_img":"/images/merchants//1482471175605.png","merchants_type":"2","contact_mobile":"12","contact_name":"12","merchants_address":"12","merchants_state":"1","merchants_states":"","merchants_province":"上海","merchants_city":"","merchants_area":"","merchants_desc_img":"/images/member/default_head.png","express_free_price":"0","is_delete":"0","create_time":"","card_id":"","merchants_email":"","zip_code":"","member_id":"","company_mobile":"","label_ids":"","company_name":"","is_default":"","qrcode_img":"","apply_state":"1","apply_states":"","refuse_remark":"","balance":0,"used_balance":0,"is_collection":"0","collection_id":"","merchants_desc":"","year_sales":"","month_sales":"","day_sales":"","goods_total_num":"65","hx_account":"","hx_pass":"","hx_nick_name":"","is_dynamic":"","is_hot":"","custom_id":"","bidding_price":"","job_start_time":"","job_end_time":"","merchantsImgBeans":[],"merchantsDescImgBeans":[],"merchantsLabelBeans":[]}
         * goodsBeans : []
         * is_express : 0
         * express_price : 5
         * goodsImgBeans : []
         * goodsClassBean : {}
         * goods_code : 13
         * goods_state_show : 上架
         * goodsSpecificationBeans : []
         * specificationBeans : []
         */

        private int goods_id;
        private String member_id;
        private String merchants_id;
        private String goods_name;
        private String goods_img;
        private String goods_price;
        private int goods_sales;
        private int goods_inventory;
        private int goods_state;
        private String goods_class;
        private int goods_fast_pay;
        private int goods_free_mail;
        private int goods_cancle;
        private int goods_is_delete;
        private int goods_get_integral;
        private String goods_goods_score;
        private String goods_mail_score;
        private String goods_delivery_score;
        private String goods_create_time;
        private String goods_update_time;
        private String goods_desc;
        private String goods_url;
        private String min_price;
        private String max_price;
        private String goods_type;
        private String sort;
        private String sort_way;
        private String goods_uuid;
        private String goods_num;
        private int assessment_count;
        private int assessment_img_count;
        private String goods_url_content;
        private MerchantsBeanBean merchantsBean;
        private String is_express;
        private String express_price;
        private GoodsClassBeanBean goodsClassBean;
        private String goods_code;
        private String goods_state_show;
        private List<?> goodsBeans;
        private List<?> goodsImgBeans;
        private List<?> goodsSpecificationBeans;
        private List<?> specificationBeans;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMerchants_id() {
            return merchants_id;
        }

        public void setMerchants_id(String merchants_id) {
            this.merchants_id = merchants_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public int getGoods_sales() {
            return goods_sales;
        }

        public void setGoods_sales(int goods_sales) {
            this.goods_sales = goods_sales;
        }

        public int getGoods_inventory() {
            return goods_inventory;
        }

        public void setGoods_inventory(int goods_inventory) {
            this.goods_inventory = goods_inventory;
        }

        public int getGoods_state() {
            return goods_state;
        }

        public void setGoods_state(int goods_state) {
            this.goods_state = goods_state;
        }

        public String getGoods_class() {
            return goods_class;
        }

        public void setGoods_class(String goods_class) {
            this.goods_class = goods_class;
        }

        public int getGoods_fast_pay() {
            return goods_fast_pay;
        }

        public void setGoods_fast_pay(int goods_fast_pay) {
            this.goods_fast_pay = goods_fast_pay;
        }

        public int getGoods_free_mail() {
            return goods_free_mail;
        }

        public void setGoods_free_mail(int goods_free_mail) {
            this.goods_free_mail = goods_free_mail;
        }

        public int getGoods_cancle() {
            return goods_cancle;
        }

        public void setGoods_cancle(int goods_cancle) {
            this.goods_cancle = goods_cancle;
        }

        public int getGoods_is_delete() {
            return goods_is_delete;
        }

        public void setGoods_is_delete(int goods_is_delete) {
            this.goods_is_delete = goods_is_delete;
        }

        public int getGoods_get_integral() {
            return goods_get_integral;
        }

        public void setGoods_get_integral(int goods_get_integral) {
            this.goods_get_integral = goods_get_integral;
        }

        public String getGoods_goods_score() {
            return goods_goods_score;
        }

        public void setGoods_goods_score(String goods_goods_score) {
            this.goods_goods_score = goods_goods_score;
        }

        public String getGoods_mail_score() {
            return goods_mail_score;
        }

        public void setGoods_mail_score(String goods_mail_score) {
            this.goods_mail_score = goods_mail_score;
        }

        public String getGoods_delivery_score() {
            return goods_delivery_score;
        }

        public void setGoods_delivery_score(String goods_delivery_score) {
            this.goods_delivery_score = goods_delivery_score;
        }

        public String getGoods_create_time() {
            return goods_create_time;
        }

        public void setGoods_create_time(String goods_create_time) {
            this.goods_create_time = goods_create_time;
        }

        public String getGoods_update_time() {
            return goods_update_time;
        }

        public void setGoods_update_time(String goods_update_time) {
            this.goods_update_time = goods_update_time;
        }

        public String getGoods_desc() {
            return goods_desc;
        }

        public void setGoods_desc(String goods_desc) {
            this.goods_desc = goods_desc;
        }

        public String getGoods_url() {
            return goods_url;
        }

        public void setGoods_url(String goods_url) {
            this.goods_url = goods_url;
        }

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }

        public String getMax_price() {
            return max_price;
        }

        public void setMax_price(String max_price) {
            this.max_price = max_price;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getSort_way() {
            return sort_way;
        }

        public void setSort_way(String sort_way) {
            this.sort_way = sort_way;
        }

        public String getGoods_uuid() {
            return goods_uuid;
        }

        public void setGoods_uuid(String goods_uuid) {
            this.goods_uuid = goods_uuid;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public int getAssessment_count() {
            return assessment_count;
        }

        public void setAssessment_count(int assessment_count) {
            this.assessment_count = assessment_count;
        }

        public int getAssessment_img_count() {
            return assessment_img_count;
        }

        public void setAssessment_img_count(int assessment_img_count) {
            this.assessment_img_count = assessment_img_count;
        }

        public String getGoods_url_content() {
            return goods_url_content;
        }

        public void setGoods_url_content(String goods_url_content) {
            this.goods_url_content = goods_url_content;
        }

        public MerchantsBeanBean getMerchantsBean() {
            return merchantsBean;
        }

        public void setMerchantsBean(MerchantsBeanBean merchantsBean) {
            this.merchantsBean = merchantsBean;
        }

        public String getIs_express() {
            return is_express;
        }

        public void setIs_express(String is_express) {
            this.is_express = is_express;
        }

        public String getExpress_price() {
            return express_price;
        }

        public void setExpress_price(String express_price) {
            this.express_price = express_price;
        }

        public GoodsClassBeanBean getGoodsClassBean() {
            return goodsClassBean;
        }

        public void setGoodsClassBean(GoodsClassBeanBean goodsClassBean) {
            this.goodsClassBean = goodsClassBean;
        }

        public String getGoods_code() {
            return goods_code;
        }

        public void setGoods_code(String goods_code) {
            this.goods_code = goods_code;
        }

        public String getGoods_state_show() {
            return goods_state_show;
        }

        public void setGoods_state_show(String goods_state_show) {
            this.goods_state_show = goods_state_show;
        }

        public List<?> getGoodsBeans() {
            return goodsBeans;
        }

        public void setGoodsBeans(List<?> goodsBeans) {
            this.goodsBeans = goodsBeans;
        }

        public List<?> getGoodsImgBeans() {
            return goodsImgBeans;
        }

        public void setGoodsImgBeans(List<?> goodsImgBeans) {
            this.goodsImgBeans = goodsImgBeans;
        }

        public List<?> getGoodsSpecificationBeans() {
            return goodsSpecificationBeans;
        }

        public void setGoodsSpecificationBeans(List<?> goodsSpecificationBeans) {
            this.goodsSpecificationBeans = goodsSpecificationBeans;
        }

        public List<?> getSpecificationBeans() {
            return specificationBeans;
        }

        public void setSpecificationBeans(List<?> specificationBeans) {
            this.specificationBeans = specificationBeans;
        }

        public static class MerchantsBeanBean {
            /**
             * merchants_id : 1
             * merchants_account_id :
             * merchants_star1 : 4.98
             * merchants_star2 : 4.80
             * merchants_star3 : 4.98
             * merchants_name : 荷柏瑞
             * merchants_img : /images/merchants//1482471175605.png
             * merchants_type : 2
             * contact_mobile : 12
             * contact_name : 12
             * merchants_address : 12
             * merchants_state : 1
             * merchants_states :
             * merchants_province : 上海
             * merchants_city :
             * merchants_area :
             * merchants_desc_img : /images/member/default_head.png
             * express_free_price : 0
             * is_delete : 0
             * create_time :
             * card_id :
             * merchants_email :
             * zip_code :
             * member_id :
             * company_mobile :
             * label_ids :
             * company_name :
             * is_default :
             * qrcode_img :
             * apply_state : 1
             * apply_states :
             * refuse_remark :
             * balance : 0.0
             * used_balance : 0.0
             * is_collection : 0
             * collection_id :
             * merchants_desc :
             * year_sales :
             * month_sales :
             * day_sales :
             * goods_total_num : 65
             * hx_account :
             * hx_pass :
             * hx_nick_name :
             * is_dynamic :
             * is_hot :
             * custom_id :
             * bidding_price :
             * job_start_time :
             * job_end_time :
             * merchantsImgBeans : []
             * merchantsDescImgBeans : []
             * merchantsLabelBeans : []
             */

            private int merchants_id;
            private String merchants_account_id;
            private String merchants_star1;
            private String merchants_star2;
            private String merchants_star3;
            private String merchants_name;
            private String merchants_img;
            private String merchants_type;
            private String contact_mobile;
            private String contact_name;
            private String merchants_address;
            private String merchants_state;
            private String merchants_states;
            private String merchants_province;
            private String merchants_city;
            private String merchants_area;
            private String merchants_desc_img;
            private String express_free_price;
            private String is_delete;
            private String create_time;
            private String card_id;
            private String merchants_email;
            private String zip_code;
            private String member_id;
            private String company_mobile;
            private String label_ids;
            private String company_name;
            private String is_default;
            private String qrcode_img;
            private String apply_state;
            private String apply_states;
            private String refuse_remark;
            private double balance;
            private double used_balance;
            private String is_collection;
            private String collection_id;
            private String merchants_desc;
            private String year_sales;
            private String month_sales;
            private String day_sales;
            private String goods_total_num;
            private String hx_account;
            private String hx_pass;
            private String hx_nick_name;
            private String is_dynamic;
            private String is_hot;
            private String custom_id;
            private String bidding_price;
            private String job_start_time;
            private String job_end_time;
            private List<?> merchantsImgBeans;
            private List<?> merchantsDescImgBeans;
            private List<?> merchantsLabelBeans;

            public int getMerchants_id() {
                return merchants_id;
            }

            public void setMerchants_id(int merchants_id) {
                this.merchants_id = merchants_id;
            }

            public String getMerchants_account_id() {
                return merchants_account_id;
            }

            public void setMerchants_account_id(String merchants_account_id) {
                this.merchants_account_id = merchants_account_id;
            }

            public String getMerchants_star1() {
                return merchants_star1;
            }

            public void setMerchants_star1(String merchants_star1) {
                this.merchants_star1 = merchants_star1;
            }

            public String getMerchants_star2() {
                return merchants_star2;
            }

            public void setMerchants_star2(String merchants_star2) {
                this.merchants_star2 = merchants_star2;
            }

            public String getMerchants_star3() {
                return merchants_star3;
            }

            public void setMerchants_star3(String merchants_star3) {
                this.merchants_star3 = merchants_star3;
            }

            public String getMerchants_name() {
                return merchants_name;
            }

            public void setMerchants_name(String merchants_name) {
                this.merchants_name = merchants_name;
            }

            public String getMerchants_img() {
                return merchants_img;
            }

            public void setMerchants_img(String merchants_img) {
                this.merchants_img = merchants_img;
            }

            public String getMerchants_type() {
                return merchants_type;
            }

            public void setMerchants_type(String merchants_type) {
                this.merchants_type = merchants_type;
            }

            public String getContact_mobile() {
                return contact_mobile;
            }

            public void setContact_mobile(String contact_mobile) {
                this.contact_mobile = contact_mobile;
            }

            public String getContact_name() {
                return contact_name;
            }

            public void setContact_name(String contact_name) {
                this.contact_name = contact_name;
            }

            public String getMerchants_address() {
                return merchants_address;
            }

            public void setMerchants_address(String merchants_address) {
                this.merchants_address = merchants_address;
            }

            public String getMerchants_state() {
                return merchants_state;
            }

            public void setMerchants_state(String merchants_state) {
                this.merchants_state = merchants_state;
            }

            public String getMerchants_states() {
                return merchants_states;
            }

            public void setMerchants_states(String merchants_states) {
                this.merchants_states = merchants_states;
            }

            public String getMerchants_province() {
                return merchants_province;
            }

            public void setMerchants_province(String merchants_province) {
                this.merchants_province = merchants_province;
            }

            public String getMerchants_city() {
                return merchants_city;
            }

            public void setMerchants_city(String merchants_city) {
                this.merchants_city = merchants_city;
            }

            public String getMerchants_area() {
                return merchants_area;
            }

            public void setMerchants_area(String merchants_area) {
                this.merchants_area = merchants_area;
            }

            public String getMerchants_desc_img() {
                return merchants_desc_img;
            }

            public void setMerchants_desc_img(String merchants_desc_img) {
                this.merchants_desc_img = merchants_desc_img;
            }

            public String getExpress_free_price() {
                return express_free_price;
            }

            public void setExpress_free_price(String express_free_price) {
                this.express_free_price = express_free_price;
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

            public String getCard_id() {
                return card_id;
            }

            public void setCard_id(String card_id) {
                this.card_id = card_id;
            }

            public String getMerchants_email() {
                return merchants_email;
            }

            public void setMerchants_email(String merchants_email) {
                this.merchants_email = merchants_email;
            }

            public String getZip_code() {
                return zip_code;
            }

            public void setZip_code(String zip_code) {
                this.zip_code = zip_code;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getCompany_mobile() {
                return company_mobile;
            }

            public void setCompany_mobile(String company_mobile) {
                this.company_mobile = company_mobile;
            }

            public String getLabel_ids() {
                return label_ids;
            }

            public void setLabel_ids(String label_ids) {
                this.label_ids = label_ids;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public String getQrcode_img() {
                return qrcode_img;
            }

            public void setQrcode_img(String qrcode_img) {
                this.qrcode_img = qrcode_img;
            }

            public String getApply_state() {
                return apply_state;
            }

            public void setApply_state(String apply_state) {
                this.apply_state = apply_state;
            }

            public String getApply_states() {
                return apply_states;
            }

            public void setApply_states(String apply_states) {
                this.apply_states = apply_states;
            }

            public String getRefuse_remark() {
                return refuse_remark;
            }

            public void setRefuse_remark(String refuse_remark) {
                this.refuse_remark = refuse_remark;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public double getUsed_balance() {
                return used_balance;
            }

            public void setUsed_balance(double used_balance) {
                this.used_balance = used_balance;
            }

            public String getIs_collection() {
                return is_collection;
            }

            public void setIs_collection(String is_collection) {
                this.is_collection = is_collection;
            }

            public String getCollection_id() {
                return collection_id;
            }

            public void setCollection_id(String collection_id) {
                this.collection_id = collection_id;
            }

            public String getMerchants_desc() {
                return merchants_desc;
            }

            public void setMerchants_desc(String merchants_desc) {
                this.merchants_desc = merchants_desc;
            }

            public String getYear_sales() {
                return year_sales;
            }

            public void setYear_sales(String year_sales) {
                this.year_sales = year_sales;
            }

            public String getMonth_sales() {
                return month_sales;
            }

            public void setMonth_sales(String month_sales) {
                this.month_sales = month_sales;
            }

            public String getDay_sales() {
                return day_sales;
            }

            public void setDay_sales(String day_sales) {
                this.day_sales = day_sales;
            }

            public String getGoods_total_num() {
                return goods_total_num;
            }

            public void setGoods_total_num(String goods_total_num) {
                this.goods_total_num = goods_total_num;
            }

            public String getHx_account() {
                return hx_account;
            }

            public void setHx_account(String hx_account) {
                this.hx_account = hx_account;
            }

            public String getHx_pass() {
                return hx_pass;
            }

            public void setHx_pass(String hx_pass) {
                this.hx_pass = hx_pass;
            }

            public String getHx_nick_name() {
                return hx_nick_name;
            }

            public void setHx_nick_name(String hx_nick_name) {
                this.hx_nick_name = hx_nick_name;
            }

            public String getIs_dynamic() {
                return is_dynamic;
            }

            public void setIs_dynamic(String is_dynamic) {
                this.is_dynamic = is_dynamic;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public String getCustom_id() {
                return custom_id;
            }

            public void setCustom_id(String custom_id) {
                this.custom_id = custom_id;
            }

            public String getBidding_price() {
                return bidding_price;
            }

            public void setBidding_price(String bidding_price) {
                this.bidding_price = bidding_price;
            }

            public String getJob_start_time() {
                return job_start_time;
            }

            public void setJob_start_time(String job_start_time) {
                this.job_start_time = job_start_time;
            }

            public String getJob_end_time() {
                return job_end_time;
            }

            public void setJob_end_time(String job_end_time) {
                this.job_end_time = job_end_time;
            }

            public List<?> getMerchantsImgBeans() {
                return merchantsImgBeans;
            }

            public void setMerchantsImgBeans(List<?> merchantsImgBeans) {
                this.merchantsImgBeans = merchantsImgBeans;
            }

            public List<?> getMerchantsDescImgBeans() {
                return merchantsDescImgBeans;
            }

            public void setMerchantsDescImgBeans(List<?> merchantsDescImgBeans) {
                this.merchantsDescImgBeans = merchantsDescImgBeans;
            }

            public List<?> getMerchantsLabelBeans() {
                return merchantsLabelBeans;
            }

            public void setMerchantsLabelBeans(List<?> merchantsLabelBeans) {
                this.merchantsLabelBeans = merchantsLabelBeans;
            }
        }

        public static class GoodsClassBeanBean {
        }
    }

    public static class MemberBeanBean {
    }

    public static class OrderGoodsBeanBean {
    }
}
