package com.live.aksd.bean;

import java.util.List;

/**
 * Created by sh-lx on 2017/8/18.
 */

public class OrderBean {


    /**
     * order_id : 29
     * order_ids :
     * member_id : 1
     * member_group_id :
     * order_no : 1957801254503580675
     * address_id : 25
     * address_mobile : 13688888888
     * address_name : 哈哈
     * address_province : 浙江省
     * address_city : 金华市
     * address_district : 义乌市
     * address_detail : 75级
     * address_zip_code : 18775
     * address_longitude : 120.075058
     * address_latitude : 29.306841
     * order_total_price : 1900.0
     * order_actual_price : 1900.0
     * order_refund_price : 0.0
     * order_type : goods
     * order_state : cancel
     * order_state_show : 取消
     * order_remark :
     * deduct_integral_value : 0
     * deduct_integral_price : 0.0
     * deduct_integral_percent :
     * custom_remark :
     * create_time : 2018-03-28 15:44:31
     * update_time : 2018-03-28 15:44:31
     * cancel_time : 2018-03-28 15:47:58
     * pay_time :
     * send_time :
     * receive_time :
     * assessment_time :
     * is_delete : 0
     * member_is_delete : 0
     * pay_way :
     * pay_way_show :
     * express_price : 0.00
     * express_free_price :
     * pay_no : 1957801263353562119
     * pay_charge : {"id":"ch_mbHWPCXvnbrLivjb5GzjrPeP","object":"charge","created":1522223072,"livemode":true,"paid":false,"refunded":false,"reversed":false,"app":"app_Guf9KSKqXjjDqnHO","channel":"wx","orderNo":"1957801263353562119","clientIp":"127.0.0.1","amount":1,"amountSettle":1,"currency":"cny","subject":"商品购买","body":"商品购买","timeExpire":1522230272,"refunds":{"object":"list","url":"/v1/charges/ch_mbHWPCXvnbrLivjb5GzjrPeP/refunds","hasMore":false,"data":[]},"amountRefunded":0,"metadata":{"order_ids":"29"},"credential":{"object":"credential","wx":{"appId":"wx0401d443f04d3730","partnerId":"1489048892","prepayId":"wx201803281544323164fc669b0770092379","nonceStr":"442e44874c1823a5ac7c41a63a61d8f0","timeStamp":"1522223072","packageValue":"Sign\u003dWXPay","sign":"4661F63E8787E1A1560FACEA7402C7C8"}},"extra":{}}
     * ping_no : ch_mbHWPCXvnbrLivjb5GzjrPeP
     * give_integral_value : 0
     * logistics_id :
     * logistics_no :
     * logistics_name :
     * logistics_code :
     * logistics_state : not
     * logistics_state_show : 无轨迹
     * invoice_state : wait_apply
     * invoice_state_show : 等待申请
     * invoice_type : 0
     * invoice_rise :
     * invoice_no :
     * invoice_desc :
     * invoice_price : 0.00
     * invoice_mobile :
     * invoice_name :
     * invoice_province :
     * invoice_city :
     * invoice_district :
     * invoice_address_detail :
     * invoice_time :
     * start_time :
     * end_time :
     * search_type :
     * buy_type :
     * goods_name :
     * cancel_end_time :
     * member_coupon_id :
     * coupon_full_price :
     * coupon_price :
     * orderGoodsBeans : [{"order_goods_id":31,"order_id":29,"goods_id":30,"goods_num":1,"goods_name":"即热式反渗透净水机","goods_img":"/images/goods/1522202352813771070970.jpg","specification_id":205,"specification_ids":"9","specification_names":"红色","specification_img":"","specification_price":1900,"group_price":"","goods_group_id":"","give_integral_value":"","total_price":"1900.00","is_delete":"0","create_time":"2018-03-28 15:44:31","update_time":"2018-03-28 15:44:31","is_refund":"0","show_type":"app","buy_type":"money","goodsBean":{},"goodsSpecificationBean":{}}]
     * orderLogisticsBeans : []
     * addressBean : {}
     */

    private int order_id;
    private String order_ids;
    private int member_id;
    private String member_group_id;
    private String order_no;
    private int address_id;
    private String address_mobile;
    private String address_name;
    private String address_province;
    private String address_city;
    private String address_district;
    private String address_detail;
    private String address_zip_code;
    private String address_longitude;
    private String address_latitude;
    private double order_total_price;
    private double order_actual_price;
    private double order_refund_price;
    private String order_type;
    private String order_state;
    private String order_state_show;
    private String order_remark;
    private int deduct_integral_value;
    private double deduct_integral_price;
    private String deduct_integral_percent;
    private String custom_remark;
    private String create_time;
    private String update_time;
    private String cancel_time;
    private String pay_time;
    private String send_time;
    private String receive_time;
    private String assessment_time;
    private String is_delete;
    private String member_is_delete;
    private String pay_way;
    private String pay_way_show;
    private String express_price;
    private String express_free_price;
    private String pay_no;
    private String pay_charge;
    private String ping_no;
    private int give_integral_value;
    private String logistics_id;
    private String logistics_no;
    private String logistics_name;
    private String logistics_code;
    private String logistics_state;
    private String logistics_state_show;
    private String invoice_state;
    private String invoice_state_show;
    private String invoice_type;
    private String invoice_rise;
    private String invoice_no;
    private String invoice_desc;
    private String invoice_price;
    private String invoice_mobile;
    private String invoice_name;
    private String invoice_province;
    private String invoice_city;
    private String invoice_district;
    private String invoice_address_detail;
    private String invoice_time;
    private String start_time;
    private String end_time;
    private String search_type;
    private String buy_type;
    private String goods_name;
    private String cancel_end_time;
    private String member_coupon_id;
    private String coupon_full_price;
    private String coupon_price;
    private AddressBeanBean addressBean;
    private List<OrderGoodsBeansBean> orderGoodsBeans;
    private List<?> orderLogisticsBeans;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_ids() {
        return order_ids;
    }

    public void setOrder_ids(String order_ids) {
        this.order_ids = order_ids;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_group_id() {
        return member_group_id;
    }

    public void setMember_group_id(String member_group_id) {
        this.member_group_id = member_group_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress_mobile() {
        return address_mobile;
    }

    public void setAddress_mobile(String address_mobile) {
        this.address_mobile = address_mobile;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getAddress_province() {
        return address_province;
    }

    public void setAddress_province(String address_province) {
        this.address_province = address_province;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_district() {
        return address_district;
    }

    public void setAddress_district(String address_district) {
        this.address_district = address_district;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getAddress_zip_code() {
        return address_zip_code;
    }

    public void setAddress_zip_code(String address_zip_code) {
        this.address_zip_code = address_zip_code;
    }

    public String getAddress_longitude() {
        return address_longitude;
    }

    public void setAddress_longitude(String address_longitude) {
        this.address_longitude = address_longitude;
    }

    public String getAddress_latitude() {
        return address_latitude;
    }

    public void setAddress_latitude(String address_latitude) {
        this.address_latitude = address_latitude;
    }

    public double getOrder_total_price() {
        return order_total_price;
    }

    public void setOrder_total_price(double order_total_price) {
        this.order_total_price = order_total_price;
    }

    public double getOrder_actual_price() {
        return order_actual_price;
    }

    public void setOrder_actual_price(double order_actual_price) {
        this.order_actual_price = order_actual_price;
    }

    public double getOrder_refund_price() {
        return order_refund_price;
    }

    public void setOrder_refund_price(double order_refund_price) {
        this.order_refund_price = order_refund_price;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public String getOrder_state_show() {
        return order_state_show;
    }

    public void setOrder_state_show(String order_state_show) {
        this.order_state_show = order_state_show;
    }

    public String getOrder_remark() {
        return order_remark;
    }

    public void setOrder_remark(String order_remark) {
        this.order_remark = order_remark;
    }

    public int getDeduct_integral_value() {
        return deduct_integral_value;
    }

    public void setDeduct_integral_value(int deduct_integral_value) {
        this.deduct_integral_value = deduct_integral_value;
    }

    public double getDeduct_integral_price() {
        return deduct_integral_price;
    }

    public void setDeduct_integral_price(double deduct_integral_price) {
        this.deduct_integral_price = deduct_integral_price;
    }

    public String getDeduct_integral_percent() {
        return deduct_integral_percent;
    }

    public void setDeduct_integral_percent(String deduct_integral_percent) {
        this.deduct_integral_percent = deduct_integral_percent;
    }

    public String getCustom_remark() {
        return custom_remark;
    }

    public void setCustom_remark(String custom_remark) {
        this.custom_remark = custom_remark;
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

    public String getCancel_time() {
        return cancel_time;
    }

    public void setCancel_time(String cancel_time) {
        this.cancel_time = cancel_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(String receive_time) {
        this.receive_time = receive_time;
    }

    public String getAssessment_time() {
        return assessment_time;
    }

    public void setAssessment_time(String assessment_time) {
        this.assessment_time = assessment_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getMember_is_delete() {
        return member_is_delete;
    }

    public void setMember_is_delete(String member_is_delete) {
        this.member_is_delete = member_is_delete;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public String getPay_way_show() {
        return pay_way_show;
    }

    public void setPay_way_show(String pay_way_show) {
        this.pay_way_show = pay_way_show;
    }

    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

    public String getExpress_free_price() {
        return express_free_price;
    }

    public void setExpress_free_price(String express_free_price) {
        this.express_free_price = express_free_price;
    }

    public String getPay_no() {
        return pay_no;
    }

    public void setPay_no(String pay_no) {
        this.pay_no = pay_no;
    }

    public String getPay_charge() {
        return pay_charge;
    }

    public void setPay_charge(String pay_charge) {
        this.pay_charge = pay_charge;
    }

    public String getPing_no() {
        return ping_no;
    }

    public void setPing_no(String ping_no) {
        this.ping_no = ping_no;
    }

    public int getGive_integral_value() {
        return give_integral_value;
    }

    public void setGive_integral_value(int give_integral_value) {
        this.give_integral_value = give_integral_value;
    }

    public String getLogistics_id() {
        return logistics_id;
    }

    public void setLogistics_id(String logistics_id) {
        this.logistics_id = logistics_id;
    }

    public String getLogistics_no() {
        return logistics_no;
    }

    public void setLogistics_no(String logistics_no) {
        this.logistics_no = logistics_no;
    }

    public String getLogistics_name() {
        return logistics_name;
    }

    public void setLogistics_name(String logistics_name) {
        this.logistics_name = logistics_name;
    }

    public String getLogistics_code() {
        return logistics_code;
    }

    public void setLogistics_code(String logistics_code) {
        this.logistics_code = logistics_code;
    }

    public String getLogistics_state() {
        return logistics_state;
    }

    public void setLogistics_state(String logistics_state) {
        this.logistics_state = logistics_state;
    }

    public String getLogistics_state_show() {
        return logistics_state_show;
    }

    public void setLogistics_state_show(String logistics_state_show) {
        this.logistics_state_show = logistics_state_show;
    }

    public String getInvoice_state() {
        return invoice_state;
    }

    public void setInvoice_state(String invoice_state) {
        this.invoice_state = invoice_state;
    }

    public String getInvoice_state_show() {
        return invoice_state_show;
    }

    public void setInvoice_state_show(String invoice_state_show) {
        this.invoice_state_show = invoice_state_show;
    }

    public String getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(String invoice_type) {
        this.invoice_type = invoice_type;
    }

    public String getInvoice_rise() {
        return invoice_rise;
    }

    public void setInvoice_rise(String invoice_rise) {
        this.invoice_rise = invoice_rise;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getInvoice_desc() {
        return invoice_desc;
    }

    public void setInvoice_desc(String invoice_desc) {
        this.invoice_desc = invoice_desc;
    }

    public String getInvoice_price() {
        return invoice_price;
    }

    public void setInvoice_price(String invoice_price) {
        this.invoice_price = invoice_price;
    }

    public String getInvoice_mobile() {
        return invoice_mobile;
    }

    public void setInvoice_mobile(String invoice_mobile) {
        this.invoice_mobile = invoice_mobile;
    }

    public String getInvoice_name() {
        return invoice_name;
    }

    public void setInvoice_name(String invoice_name) {
        this.invoice_name = invoice_name;
    }

    public String getInvoice_province() {
        return invoice_province;
    }

    public void setInvoice_province(String invoice_province) {
        this.invoice_province = invoice_province;
    }

    public String getInvoice_city() {
        return invoice_city;
    }

    public void setInvoice_city(String invoice_city) {
        this.invoice_city = invoice_city;
    }

    public String getInvoice_district() {
        return invoice_district;
    }

    public void setInvoice_district(String invoice_district) {
        this.invoice_district = invoice_district;
    }

    public String getInvoice_address_detail() {
        return invoice_address_detail;
    }

    public void setInvoice_address_detail(String invoice_address_detail) {
        this.invoice_address_detail = invoice_address_detail;
    }

    public String getInvoice_time() {
        return invoice_time;
    }

    public void setInvoice_time(String invoice_time) {
        this.invoice_time = invoice_time;
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

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public String getBuy_type() {
        return buy_type;
    }

    public void setBuy_type(String buy_type) {
        this.buy_type = buy_type;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getCancel_end_time() {
        return cancel_end_time;
    }

    public void setCancel_end_time(String cancel_end_time) {
        this.cancel_end_time = cancel_end_time;
    }

    public String getMember_coupon_id() {
        return member_coupon_id;
    }

    public void setMember_coupon_id(String member_coupon_id) {
        this.member_coupon_id = member_coupon_id;
    }

    public String getCoupon_full_price() {
        return coupon_full_price;
    }

    public void setCoupon_full_price(String coupon_full_price) {
        this.coupon_full_price = coupon_full_price;
    }

    public String getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(String coupon_price) {
        this.coupon_price = coupon_price;
    }

    public AddressBeanBean getAddressBean() {
        return addressBean;
    }

    public void setAddressBean(AddressBeanBean addressBean) {
        this.addressBean = addressBean;
    }

    public List<OrderGoodsBeansBean> getOrderGoodsBeans() {
        return orderGoodsBeans;
    }

    public void setOrderGoodsBeans(List<OrderGoodsBeansBean> orderGoodsBeans) {
        this.orderGoodsBeans = orderGoodsBeans;
    }

    public List<?> getOrderLogisticsBeans() {
        return orderLogisticsBeans;
    }

    public void setOrderLogisticsBeans(List<?> orderLogisticsBeans) {
        this.orderLogisticsBeans = orderLogisticsBeans;
    }

    public static class AddressBeanBean {
    }

    public static class OrderGoodsBeansBean {
        /**
         * order_goods_id : 31
         * order_id : 29
         * goods_id : 30
         * goods_num : 1
         * goods_name : 即热式反渗透净水机
         * goods_img : /images/goods/1522202352813771070970.jpg
         * specification_id : 205
         * specification_ids : 9
         * specification_names : 红色
         * specification_img :
         * specification_price : 1900.0
         * group_price :
         * goods_group_id :
         * give_integral_value :
         * total_price : 1900.00
         * is_delete : 0
         * create_time : 2018-03-28 15:44:31
         * update_time : 2018-03-28 15:44:31
         * is_refund : 0
         * show_type : app
         * buy_type : money
         * goodsBean : {}
         * goodsSpecificationBean : {}
         */

        private int order_goods_id;
        private int order_id;
        private String goods_id;
        private int goods_num;
        private String goods_name;
        private String goods_img;
        private int specification_id;
        private String specification_ids;
        private String specification_names;
        private String specification_img;
        private double specification_price;
        private String group_price;
        private String goods_group_id;
        private String give_integral_value;

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        private String total_price;
        private String is_delete;
        private String create_time;
        private String update_time;
        private String is_refund;
        private String show_type;
        private String buy_type;
        private String order_state;
        private GoodsBeanBean goodsBean;
        private GoodsSpecificationBeanBean goodsSpecificationBean;

        public int getOrder_goods_id() {
            return order_goods_id;
        }

        public void setOrder_goods_id(int order_goods_id) {
            this.order_goods_id = order_goods_id;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
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

        public int getSpecification_id() {
            return specification_id;
        }

        public void setSpecification_id(int specification_id) {
            this.specification_id = specification_id;
        }

        public String getSpecification_ids() {
            return specification_ids;
        }

        public void setSpecification_ids(String specification_ids) {
            this.specification_ids = specification_ids;
        }

        public String getSpecification_names() {
            return specification_names;
        }

        public void setSpecification_names(String specification_names) {
            this.specification_names = specification_names;
        }

        public String getSpecification_img() {
            return specification_img;
        }

        public void setSpecification_img(String specification_img) {
            this.specification_img = specification_img;
        }

        public double getSpecification_price() {
            return specification_price;
        }

        public void setSpecification_price(double specification_price) {
            this.specification_price = specification_price;
        }

        public String getGroup_price() {
            return group_price;
        }

        public void setGroup_price(String group_price) {
            this.group_price = group_price;
        }

        public String getGoods_group_id() {
            return goods_group_id;
        }

        public void setGoods_group_id(String goods_group_id) {
            this.goods_group_id = goods_group_id;
        }

        public String getGive_integral_value() {
            return give_integral_value;
        }

        public void setGive_integral_value(String give_integral_value) {
            this.give_integral_value = give_integral_value;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
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

        public String getIs_refund() {
            return is_refund;
        }

        public void setIs_refund(String is_refund) {
            this.is_refund = is_refund;
        }

        public String getShow_type() {
            return show_type;
        }

        public void setShow_type(String show_type) {
            this.show_type = show_type;
        }

        public String getBuy_type() {
            return buy_type;
        }

        public void setBuy_type(String buy_type) {
            this.buy_type = buy_type;
        }

        public GoodsBeanBean getGoodsBean() {
            return goodsBean;
        }

        public void setGoodsBean(GoodsBeanBean goodsBean) {
            this.goodsBean = goodsBean;
        }

        public GoodsSpecificationBeanBean getGoodsSpecificationBean() {
            return goodsSpecificationBean;
        }

        public void setGoodsSpecificationBean(GoodsSpecificationBeanBean goodsSpecificationBean) {
            this.goodsSpecificationBean = goodsSpecificationBean;
        }

        public static class GoodsBeanBean {
        }

        public static class GoodsSpecificationBeanBean {
        }
    }
}