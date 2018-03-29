package com.live.aksd.bean;

import java.util.List;

/**
 * Created by lenove on 2017/7/21.
 */

public class CollectionBean {


    /**
     * collection_id : 1
     * member_id : 50
     * relation_id : 290974
     * collection_type : goods
     * is_delete : 0
     * create_time : 2017-08-09 20:28:02.0
     * merchantsBean : {}
     * goodsBean : {"goods_id":290974,"goods_class_id":0,"activity_goods_id":0,"order_goods_id":0,"member_id":"","merchants_id":"1","merchants_name":"","goods_name":"大棚蔬菜有机营养","goods_img":"/images/goods//1502163036478.png","goods_imgs":"","goods_now_price":"0.00","goods_origin_price":"40","goods_pc_price":"","price_cut":"","discount_value":"","goods_grade":"1","goods_state":"1","goods_state_show":"上架中","goods_stock":50,"goods_type":"2","goods_address":"上海","goods_url":"/html/goods//20170808113103.html","goods_url_content":"","day_sales":24,"month_sales":400,"year_sales":12220,"is_business_buy":"0","brand_id":"","brand_name":"","parent_id":"54","parent_name":"","parent_uuid":"","class_id":"","class1_id":"","class1_name":"","class2_id":"","class2_name":"","create_time":"2017-08-08 11:31:03.0","is_recommend":"","is_recommend_show":"","is_give_integral":"","give_integral_value":"0","is_deduct_integral":"","deduct_integral_value":"","deduct_integral_price":"","is_express":"1","express_price":"0","is_delete":"0","goods_uuid":"8568d7aa-1753-4f6d-bcf3-b7766c23798f","goods_parent_uuid":"#605c6c45-153b-421d-aa87-4ab0c623d826#8568d7aa-1753-4f6d-bcf3-b7766c23798f","sort_time":"2017-08-08 11:31:03.0","sort":"","is_end":"","size_id":"","year_id":"","label_id":"","season_id":"","min_price":"","max_price":"","min_pc_price":"","max_pc_price":"","sort_type":"","sort_way":"","is_selling":"0","is_hot":"0","express_free_price":"10000000","goods_desc":"","is_collection":"0","collection_id":"","see_num":"135","is_group_buy":"0","group_buy_count":"","group_buy_now_count":"","group_buy_price":"","is_pre_sale":"","is_pre_sale_id":"","send_goods_time":"","promotion_price":"","promotion_goods_id":"","goods_story_url":"","ssp_gift":"","ssp_gift_id":"","ssp_fresh":"","ssp_fresh_id":"","ssp_baby":"","ssp_baby_id":"","ssp_lady":"","ssp_lady_id":"","ssp_feature":"","ssp_feature_id":"","ssp_import":"","ssp_import_id":"","ssp_promotion":"","ssp_promotion_id":"","is_cross_border":"0","cross_border_tax":"0","goods_sku":"0","goods_skus":"000003","goods_storehouse":"","goods_excise_tax":"0","start_time":"","end_time":"","is_new":"","goods_title":"","type":"","goods_star1":"5","goods_star2":"5","goods_star3":"5","satisfied_count":"0","dissatisfied_count":"0","business_id":"","is_share":"","share_integral":0,"is_price_cut_ranking":0,"is_discount_ranking":0,"goods_no":"016a35e1-7bea-11e7-9bd2-00163e0c168f","goods_parameters":"","goods_parameters_name":"","is_class_recommend":"","class_recommend_img":"","qrcode_img":"/images/qrcode/goods/201708081131031.png","activity_id":"","storehouse_name":"","is_merchants_recommend":"0","is_exact":"","is_goods_exact":"0","is_sales_ranking":"0","is_directly":"0","good_assessment_percent":"100.0","good_assessment_count":0,"bad_assessment_percent":"0","bad_assessment_count":0,"in_assessment_percent":"0","in_assessment_count":0,"assessment_count":0,"assessment_img_count":0,"receive_time":"","goods_replenishment_time":"","goods_storage_instructions":"","goods_addnation_notes":"","goods_form":"","goods_research_areas":"","standard_type":"","album_id":"","goods_count":"0","distance":"","sort_price":"","bidding_price":"","goods_num":0,"merchantsBean":{},"classBannerBeans":[],"goodsRecommendBeans":[],"classRecommendBeans":[],"goodsBeans":[],"goodsImgBeans":[],"brandBeans":[],"goodsParameterBeans":[],"goodsServiceBeans":[],"groupBuyGoodsBeans":[],"standardBeans":[],"activityBean":{},"activityBeans":[],"goodsLabelBeans":[]}
     * informationBean : {}
     * releaseBean : {}
     */

    private String collection_id;
    private String member_id;
    private String relation_id;
    private String collection_type;
    private String is_delete;
    private String create_time;
    private MerchantsBeanBean merchantsBean;

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(String relation_id) {
        this.relation_id = relation_id;
    }

    public String getCollection_type() {
        return collection_type;
    }

    public void setCollection_type(String collection_type) {
        this.collection_type = collection_type;
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

    public MerchantsBeanBean getMerchantsBean() {
        return merchantsBean;
    }

    public void setMerchantsBean(MerchantsBeanBean merchantsBean) {
        this.merchantsBean = merchantsBean;
    }

    public GoodsBeanBean getGoodsBean() {
        return goodsBean;
    }

    public void setGoodsBean(GoodsBeanBean goodsBean) {
        this.goodsBean = goodsBean;
    }

    public InformationBeanBean getInformationBean() {
        return informationBean;
    }

    public void setInformationBean(InformationBeanBean informationBean) {
        this.informationBean = informationBean;
    }

    public ReleaseBeanBean getReleaseBean() {
        return releaseBean;
    }

    public void setReleaseBean(ReleaseBeanBean releaseBean) {
        this.releaseBean = releaseBean;
    }

    private GoodsBeanBean goodsBean;
    private InformationBeanBean informationBean;
    private ReleaseBeanBean releaseBean;

    public static class MerchantsBeanBean {


        /**
         * merchants_id : 1
         * merchants_account_id :
         * merchants_star1 : 3.90
         * merchants_star2 : 3.94
         * merchants_star3 : 3.98
         * merchants_name : 全球农业网
         * merchants_img : /images/goods//1483448955139.png
         * merchants_type : 2
         * contact_mobile : 12
         * contact_name : 12
         * merchants_address : 12
         * merchants_state : 1
         * merchants_states :
         * merchants_province : 上海
         * merchants_city :
         * merchants_area :
         * merchants_desc_img : /images/merchants/banner2.png
         * express_free_price : 10000000
         * is_delete : 0
         * create_time :
         * card_id :
         * merchants_email :
         * zip_code :
         * member_id :
         * company_mobile :
         * label_ids : 1,2
         * company_name :
         * is_default :
         * qrcode_img :
         * apply_state : 1
         * apply_states :
         * refuse_remark :
         * balance : 0
         * used_balance : 0
         * is_collection : 0
         * collection_id :
         * merchants_desc : 医药界的神话
         * year_sales : 1480641
         * month_sales : 4003
         * day_sales : 160
         * goods_total_num : 6
         * hx_account : tts2
         * hx_pass : 123456
         * hx_nick_name : dsds
         * is_dynamic : 1
         * is_hot : 1
         * custom_id : 1582
         * bidding_price :
         * job_start_time : 9:00
         * job_end_time : 18:00
         * goodsBeans : []
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
        private int balance;
        private int used_balance;
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
        private List<?> goodsBeans;
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

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getUsed_balance() {
            return used_balance;
        }

        public void setUsed_balance(int used_balance) {
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

        public List<?> getGoodsBeans() {
            return goodsBeans;
        }

        public void setGoodsBeans(List<?> goodsBeans) {
            this.goodsBeans = goodsBeans;
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
    public static class GoodsBeanBean {

        /**
         * goods_id : 290974
         * goods_class_id : 0
         * activity_goods_id : 0
         * order_goods_id : 0
         * member_id :
         * merchants_id : 1
         * merchants_name :
         * goods_name : 大棚蔬菜有机营养
         * goods_img : /images/goods//1502163036478.png
         * goods_imgs :
         * goods_now_price : 0.00
         * goods_origin_price : 40
         * goods_pc_price :
         * price_cut :
         * discount_value :
         * goods_grade : 1
         * goods_state : 1
         * goods_state_show : 上架中
         * goods_stock : 50
         * goods_type : 2
         * goods_address : 上海
         * goods_url : /html/goods//20170808113103.html
         * goods_url_content :
         * day_sales : 24
         * month_sales : 400
         * year_sales : 12220
         * is_business_buy : 0
         * brand_id :
         * brand_name :
         * parent_id : 54
         * parent_name :
         * parent_uuid :
         * class_id :
         * class1_id :
         * class1_name :
         * class2_id :
         * class2_name :
         * create_time : 2017-08-08 11:31:03.0
         * is_recommend :
         * is_recommend_show :
         * is_give_integral :
         * give_integral_value : 0
         * is_deduct_integral :
         * deduct_integral_value :
         * deduct_integral_price :
         * is_express : 1
         * express_price : 0
         * is_delete : 0
         * goods_uuid : 8568d7aa-1753-4f6d-bcf3-b7766c23798f
         * goods_parent_uuid : #605c6c45-153b-421d-aa87-4ab0c623d826#8568d7aa-1753-4f6d-bcf3-b7766c23798f
         * sort_time : 2017-08-08 11:31:03.0
         * sort :
         * is_end :
         * size_id :
         * year_id :
         * label_id :
         * season_id :
         * min_price :
         * max_price :
         * min_pc_price :
         * max_pc_price :
         * sort_type :
         * sort_way :
         * is_selling : 0
         * is_hot : 0
         * express_free_price : 10000000
         * goods_desc :
         * is_collection : 0
         * collection_id :
         * see_num : 135
         * is_group_buy : 0
         * group_buy_count :
         * group_buy_now_count :
         * group_buy_price :
         * is_pre_sale :
         * is_pre_sale_id :
         * send_goods_time :
         * promotion_price :
         * promotion_goods_id :
         * goods_story_url :
         * ssp_gift :
         * ssp_gift_id :
         * ssp_fresh :
         * ssp_fresh_id :
         * ssp_baby :
         * ssp_baby_id :
         * ssp_lady :
         * ssp_lady_id :
         * ssp_feature :
         * ssp_feature_id :
         * ssp_import :
         * ssp_import_id :
         * ssp_promotion :
         * ssp_promotion_id :
         * is_cross_border : 0
         * cross_border_tax : 0
         * goods_sku : 0
         * goods_skus : 000003
         * goods_storehouse :
         * goods_excise_tax : 0
         * start_time :
         * end_time :
         * is_new :
         * goods_title :
         * type :
         * goods_star1 : 5
         * goods_star2 : 5
         * goods_star3 : 5
         * satisfied_count : 0
         * dissatisfied_count : 0
         * business_id :
         * is_share :
         * share_integral : 0
         * is_price_cut_ranking : 0
         * is_discount_ranking : 0
         * goods_no : 016a35e1-7bea-11e7-9bd2-00163e0c168f
         * goods_parameters :
         * goods_parameters_name :
         * is_class_recommend :
         * class_recommend_img :
         * qrcode_img : /images/qrcode/goods/201708081131031.png
         * activity_id :
         * storehouse_name :
         * is_merchants_recommend : 0
         * is_exact :
         * is_goods_exact : 0
         * is_sales_ranking : 0
         * is_directly : 0
         * good_assessment_percent : 100.0
         * good_assessment_count : 0
         * bad_assessment_percent : 0
         * bad_assessment_count : 0
         * in_assessment_percent : 0
         * in_assessment_count : 0
         * assessment_count : 0
         * assessment_img_count : 0
         * receive_time :
         * goods_replenishment_time :
         * goods_storage_instructions :
         * goods_addnation_notes :
         * goods_form :
         * goods_research_areas :
         * standard_type :
         * album_id :
         * goods_count : 0
         * distance :
         * sort_price :
         * bidding_price :
         * goods_num : 0
         * merchantsBean : {}
         * classBannerBeans : []
         * goodsRecommendBeans : []
         * classRecommendBeans : []
         * goodsBeans : []
         * goodsImgBeans : []
         * brandBeans : []
         * goodsParameterBeans : []
         * goodsServiceBeans : []
         * groupBuyGoodsBeans : []
         * standardBeans : []
         * activityBean : {}
         * activityBeans : []
         * goodsLabelBeans : []
         */

        private int goods_id;
        private int goods_class_id;
        private int activity_goods_id;
        private int order_goods_id;
        private String member_id;
        private String merchants_id;
        private String merchants_name;
        private String goods_name;
        private String goods_img;
        private String goods_imgs;
        private String goods_now_price;
        private String goods_origin_price;
        private String goods_pc_price;
        private String price_cut;
        private String discount_value;
        private String goods_grade;
        private String goods_state;
        private String goods_state_show;
        private int goods_stock;
        private String goods_type;
        private String goods_address;
        private String goods_url;
        private String goods_url_content;
        private int day_sales;
        private int month_sales;
        private int year_sales;
        private String is_business_buy;
        private String brand_id;
        private String brand_name;
        private String parent_id;
        private String parent_name;
        private String parent_uuid;
        private String class_id;
        private String class1_id;
        private String class1_name;
        private String class2_id;
        private String class2_name;
        private String create_time;
        private String is_recommend;
        private String is_recommend_show;
        private String is_give_integral;
        private String give_integral_value;
        private String is_deduct_integral;
        private String deduct_integral_value;
        private String deduct_integral_price;
        private String is_express;
        private String express_price;
        private String is_delete;
        private String goods_uuid;
        private String goods_parent_uuid;
        private String sort_time;
        private String sort;
        private String is_end;
        private String size_id;
        private String year_id;
        private String label_id;
        private String season_id;
        private String min_price;
        private String max_price;
        private String min_pc_price;
        private String max_pc_price;
        private String sort_type;
        private String sort_way;
        private String is_selling;
        private String is_hot;
        private String express_free_price;
        private String goods_desc;
        private String is_collection;
        private String collection_id;
        private String see_num;
        private String is_group_buy;
        private String group_buy_count;
        private String group_buy_now_count;
        private String group_buy_price;
        private String is_pre_sale;
        private String is_pre_sale_id;
        private String send_goods_time;
        private String promotion_price;
        private String promotion_goods_id;
        private String goods_story_url;
        private String ssp_gift;
        private String ssp_gift_id;
        private String ssp_fresh;
        private String ssp_fresh_id;
        private String ssp_baby;
        private String ssp_baby_id;
        private String ssp_lady;
        private String ssp_lady_id;
        private String ssp_feature;
        private String ssp_feature_id;
        private String ssp_import;
        private String ssp_import_id;
        private String ssp_promotion;
        private String ssp_promotion_id;
        private String is_cross_border;
        private String cross_border_tax;
        private String goods_sku;
        private String goods_skus;
        private String goods_storehouse;
        private String goods_excise_tax;
        private String start_time;
        private String end_time;
        private String is_new;
        private String goods_title;
        private String type;
        private String goods_star1;
        private String goods_star2;
        private String goods_star3;
        private String satisfied_count;
        private String dissatisfied_count;
        private String business_id;
        private String is_share;
        private int share_integral;
        private int is_price_cut_ranking;
        private int is_discount_ranking;
        private String goods_no;
        private String goods_parameters;
        private String goods_parameters_name;
        private String is_class_recommend;
        private String class_recommend_img;
        private String qrcode_img;
        private String activity_id;
        private String storehouse_name;
        private String is_merchants_recommend;
        private String is_exact;
        private String is_goods_exact;
        private String is_sales_ranking;
        private String is_directly;
        private String good_assessment_percent;
        private int good_assessment_count;
        private String bad_assessment_percent;
        private int bad_assessment_count;
        private String in_assessment_percent;
        private int in_assessment_count;
        private int assessment_count;
        private int assessment_img_count;
        private String receive_time;
        private String goods_replenishment_time;
        private String goods_storage_instructions;
        private String goods_addnation_notes;
        private String goods_form;
        private String goods_research_areas;
        private String standard_type;
        private String album_id;
        private String goods_count;
        private String distance;
        private String sort_price;
        private String bidding_price;
        private int goods_num;
        private MerchantsBeanBean merchantsBean;
        private ActivityBeanBean activityBean;
        private List<?> classBannerBeans;
        private List<?> goodsRecommendBeans;
        private List<?> classRecommendBeans;
        private List<?> goodsBeans;
        private List<?> goodsImgBeans;
        private List<?> brandBeans;
        private List<?> goodsParameterBeans;
        private List<?> goodsServiceBeans;
        private List<?> groupBuyGoodsBeans;
        private List<?> standardBeans;
        private List<?> activityBeans;
        private List<?> goodsLabelBeans;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getGoods_class_id() {
            return goods_class_id;
        }

        public void setGoods_class_id(int goods_class_id) {
            this.goods_class_id = goods_class_id;
        }

        public int getActivity_goods_id() {
            return activity_goods_id;
        }

        public void setActivity_goods_id(int activity_goods_id) {
            this.activity_goods_id = activity_goods_id;
        }

        public int getOrder_goods_id() {
            return order_goods_id;
        }

        public void setOrder_goods_id(int order_goods_id) {
            this.order_goods_id = order_goods_id;
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

        public String getMerchants_name() {
            return merchants_name;
        }

        public void setMerchants_name(String merchants_name) {
            this.merchants_name = merchants_name;
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

        public String getGoods_imgs() {
            return goods_imgs;
        }

        public void setGoods_imgs(String goods_imgs) {
            this.goods_imgs = goods_imgs;
        }

        public String getGoods_now_price() {
            return goods_now_price;
        }

        public void setGoods_now_price(String goods_now_price) {
            this.goods_now_price = goods_now_price;
        }

        public String getGoods_origin_price() {
            return goods_origin_price;
        }

        public void setGoods_origin_price(String goods_origin_price) {
            this.goods_origin_price = goods_origin_price;
        }

        public String getGoods_pc_price() {
            return goods_pc_price;
        }

        public void setGoods_pc_price(String goods_pc_price) {
            this.goods_pc_price = goods_pc_price;
        }

        public String getPrice_cut() {
            return price_cut;
        }

        public void setPrice_cut(String price_cut) {
            this.price_cut = price_cut;
        }

        public String getDiscount_value() {
            return discount_value;
        }

        public void setDiscount_value(String discount_value) {
            this.discount_value = discount_value;
        }

        public String getGoods_grade() {
            return goods_grade;
        }

        public void setGoods_grade(String goods_grade) {
            this.goods_grade = goods_grade;
        }

        public String getGoods_state() {
            return goods_state;
        }

        public void setGoods_state(String goods_state) {
            this.goods_state = goods_state;
        }

        public String getGoods_state_show() {
            return goods_state_show;
        }

        public void setGoods_state_show(String goods_state_show) {
            this.goods_state_show = goods_state_show;
        }

        public int getGoods_stock() {
            return goods_stock;
        }

        public void setGoods_stock(int goods_stock) {
            this.goods_stock = goods_stock;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getGoods_address() {
            return goods_address;
        }

        public void setGoods_address(String goods_address) {
            this.goods_address = goods_address;
        }

        public String getGoods_url() {
            return goods_url;
        }

        public void setGoods_url(String goods_url) {
            this.goods_url = goods_url;
        }

        public String getGoods_url_content() {
            return goods_url_content;
        }

        public void setGoods_url_content(String goods_url_content) {
            this.goods_url_content = goods_url_content;
        }

        public int getDay_sales() {
            return day_sales;
        }

        public void setDay_sales(int day_sales) {
            this.day_sales = day_sales;
        }

        public int getMonth_sales() {
            return month_sales;
        }

        public void setMonth_sales(int month_sales) {
            this.month_sales = month_sales;
        }

        public int getYear_sales() {
            return year_sales;
        }

        public void setYear_sales(int year_sales) {
            this.year_sales = year_sales;
        }

        public String getIs_business_buy() {
            return is_business_buy;
        }

        public void setIs_business_buy(String is_business_buy) {
            this.is_business_buy = is_business_buy;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getParent_name() {
            return parent_name;
        }

        public void setParent_name(String parent_name) {
            this.parent_name = parent_name;
        }

        public String getParent_uuid() {
            return parent_uuid;
        }

        public void setParent_uuid(String parent_uuid) {
            this.parent_uuid = parent_uuid;
        }

        public String getClass_id() {
            return class_id;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public String getClass1_id() {
            return class1_id;
        }

        public void setClass1_id(String class1_id) {
            this.class1_id = class1_id;
        }

        public String getClass1_name() {
            return class1_name;
        }

        public void setClass1_name(String class1_name) {
            this.class1_name = class1_name;
        }

        public String getClass2_id() {
            return class2_id;
        }

        public void setClass2_id(String class2_id) {
            this.class2_id = class2_id;
        }

        public String getClass2_name() {
            return class2_name;
        }

        public void setClass2_name(String class2_name) {
            this.class2_name = class2_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(String is_recommend) {
            this.is_recommend = is_recommend;
        }

        public String getIs_recommend_show() {
            return is_recommend_show;
        }

        public void setIs_recommend_show(String is_recommend_show) {
            this.is_recommend_show = is_recommend_show;
        }

        public String getIs_give_integral() {
            return is_give_integral;
        }

        public void setIs_give_integral(String is_give_integral) {
            this.is_give_integral = is_give_integral;
        }

        public String getGive_integral_value() {
            return give_integral_value;
        }

        public void setGive_integral_value(String give_integral_value) {
            this.give_integral_value = give_integral_value;
        }

        public String getIs_deduct_integral() {
            return is_deduct_integral;
        }

        public void setIs_deduct_integral(String is_deduct_integral) {
            this.is_deduct_integral = is_deduct_integral;
        }

        public String getDeduct_integral_value() {
            return deduct_integral_value;
        }

        public void setDeduct_integral_value(String deduct_integral_value) {
            this.deduct_integral_value = deduct_integral_value;
        }

        public String getDeduct_integral_price() {
            return deduct_integral_price;
        }

        public void setDeduct_integral_price(String deduct_integral_price) {
            this.deduct_integral_price = deduct_integral_price;
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

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getGoods_uuid() {
            return goods_uuid;
        }

        public void setGoods_uuid(String goods_uuid) {
            this.goods_uuid = goods_uuid;
        }

        public String getGoods_parent_uuid() {
            return goods_parent_uuid;
        }

        public void setGoods_parent_uuid(String goods_parent_uuid) {
            this.goods_parent_uuid = goods_parent_uuid;
        }

        public String getSort_time() {
            return sort_time;
        }

        public void setSort_time(String sort_time) {
            this.sort_time = sort_time;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getIs_end() {
            return is_end;
        }

        public void setIs_end(String is_end) {
            this.is_end = is_end;
        }

        public String getSize_id() {
            return size_id;
        }

        public void setSize_id(String size_id) {
            this.size_id = size_id;
        }

        public String getYear_id() {
            return year_id;
        }

        public void setYear_id(String year_id) {
            this.year_id = year_id;
        }

        public String getLabel_id() {
            return label_id;
        }

        public void setLabel_id(String label_id) {
            this.label_id = label_id;
        }

        public String getSeason_id() {
            return season_id;
        }

        public void setSeason_id(String season_id) {
            this.season_id = season_id;
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

        public String getMin_pc_price() {
            return min_pc_price;
        }

        public void setMin_pc_price(String min_pc_price) {
            this.min_pc_price = min_pc_price;
        }

        public String getMax_pc_price() {
            return max_pc_price;
        }

        public void setMax_pc_price(String max_pc_price) {
            this.max_pc_price = max_pc_price;
        }

        public String getSort_type() {
            return sort_type;
        }

        public void setSort_type(String sort_type) {
            this.sort_type = sort_type;
        }

        public String getSort_way() {
            return sort_way;
        }

        public void setSort_way(String sort_way) {
            this.sort_way = sort_way;
        }

        public String getIs_selling() {
            return is_selling;
        }

        public void setIs_selling(String is_selling) {
            this.is_selling = is_selling;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getExpress_free_price() {
            return express_free_price;
        }

        public void setExpress_free_price(String express_free_price) {
            this.express_free_price = express_free_price;
        }

        public String getGoods_desc() {
            return goods_desc;
        }

        public void setGoods_desc(String goods_desc) {
            this.goods_desc = goods_desc;
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

        public String getSee_num() {
            return see_num;
        }

        public void setSee_num(String see_num) {
            this.see_num = see_num;
        }

        public String getIs_group_buy() {
            return is_group_buy;
        }

        public void setIs_group_buy(String is_group_buy) {
            this.is_group_buy = is_group_buy;
        }

        public String getGroup_buy_count() {
            return group_buy_count;
        }

        public void setGroup_buy_count(String group_buy_count) {
            this.group_buy_count = group_buy_count;
        }

        public String getGroup_buy_now_count() {
            return group_buy_now_count;
        }

        public void setGroup_buy_now_count(String group_buy_now_count) {
            this.group_buy_now_count = group_buy_now_count;
        }

        public String getGroup_buy_price() {
            return group_buy_price;
        }

        public void setGroup_buy_price(String group_buy_price) {
            this.group_buy_price = group_buy_price;
        }

        public String getIs_pre_sale() {
            return is_pre_sale;
        }

        public void setIs_pre_sale(String is_pre_sale) {
            this.is_pre_sale = is_pre_sale;
        }

        public String getIs_pre_sale_id() {
            return is_pre_sale_id;
        }

        public void setIs_pre_sale_id(String is_pre_sale_id) {
            this.is_pre_sale_id = is_pre_sale_id;
        }

        public String getSend_goods_time() {
            return send_goods_time;
        }

        public void setSend_goods_time(String send_goods_time) {
            this.send_goods_time = send_goods_time;
        }

        public String getPromotion_price() {
            return promotion_price;
        }

        public void setPromotion_price(String promotion_price) {
            this.promotion_price = promotion_price;
        }

        public String getPromotion_goods_id() {
            return promotion_goods_id;
        }

        public void setPromotion_goods_id(String promotion_goods_id) {
            this.promotion_goods_id = promotion_goods_id;
        }

        public String getGoods_story_url() {
            return goods_story_url;
        }

        public void setGoods_story_url(String goods_story_url) {
            this.goods_story_url = goods_story_url;
        }

        public String getSsp_gift() {
            return ssp_gift;
        }

        public void setSsp_gift(String ssp_gift) {
            this.ssp_gift = ssp_gift;
        }

        public String getSsp_gift_id() {
            return ssp_gift_id;
        }

        public void setSsp_gift_id(String ssp_gift_id) {
            this.ssp_gift_id = ssp_gift_id;
        }

        public String getSsp_fresh() {
            return ssp_fresh;
        }

        public void setSsp_fresh(String ssp_fresh) {
            this.ssp_fresh = ssp_fresh;
        }

        public String getSsp_fresh_id() {
            return ssp_fresh_id;
        }

        public void setSsp_fresh_id(String ssp_fresh_id) {
            this.ssp_fresh_id = ssp_fresh_id;
        }

        public String getSsp_baby() {
            return ssp_baby;
        }

        public void setSsp_baby(String ssp_baby) {
            this.ssp_baby = ssp_baby;
        }

        public String getSsp_baby_id() {
            return ssp_baby_id;
        }

        public void setSsp_baby_id(String ssp_baby_id) {
            this.ssp_baby_id = ssp_baby_id;
        }

        public String getSsp_lady() {
            return ssp_lady;
        }

        public void setSsp_lady(String ssp_lady) {
            this.ssp_lady = ssp_lady;
        }

        public String getSsp_lady_id() {
            return ssp_lady_id;
        }

        public void setSsp_lady_id(String ssp_lady_id) {
            this.ssp_lady_id = ssp_lady_id;
        }

        public String getSsp_feature() {
            return ssp_feature;
        }

        public void setSsp_feature(String ssp_feature) {
            this.ssp_feature = ssp_feature;
        }

        public String getSsp_feature_id() {
            return ssp_feature_id;
        }

        public void setSsp_feature_id(String ssp_feature_id) {
            this.ssp_feature_id = ssp_feature_id;
        }

        public String getSsp_import() {
            return ssp_import;
        }

        public void setSsp_import(String ssp_import) {
            this.ssp_import = ssp_import;
        }

        public String getSsp_import_id() {
            return ssp_import_id;
        }

        public void setSsp_import_id(String ssp_import_id) {
            this.ssp_import_id = ssp_import_id;
        }

        public String getSsp_promotion() {
            return ssp_promotion;
        }

        public void setSsp_promotion(String ssp_promotion) {
            this.ssp_promotion = ssp_promotion;
        }

        public String getSsp_promotion_id() {
            return ssp_promotion_id;
        }

        public void setSsp_promotion_id(String ssp_promotion_id) {
            this.ssp_promotion_id = ssp_promotion_id;
        }

        public String getIs_cross_border() {
            return is_cross_border;
        }

        public void setIs_cross_border(String is_cross_border) {
            this.is_cross_border = is_cross_border;
        }

        public String getCross_border_tax() {
            return cross_border_tax;
        }

        public void setCross_border_tax(String cross_border_tax) {
            this.cross_border_tax = cross_border_tax;
        }

        public String getGoods_sku() {
            return goods_sku;
        }

        public void setGoods_sku(String goods_sku) {
            this.goods_sku = goods_sku;
        }

        public String getGoods_skus() {
            return goods_skus;
        }

        public void setGoods_skus(String goods_skus) {
            this.goods_skus = goods_skus;
        }

        public String getGoods_storehouse() {
            return goods_storehouse;
        }

        public void setGoods_storehouse(String goods_storehouse) {
            this.goods_storehouse = goods_storehouse;
        }

        public String getGoods_excise_tax() {
            return goods_excise_tax;
        }

        public void setGoods_excise_tax(String goods_excise_tax) {
            this.goods_excise_tax = goods_excise_tax;
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

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGoods_star1() {
            return goods_star1;
        }

        public void setGoods_star1(String goods_star1) {
            this.goods_star1 = goods_star1;
        }

        public String getGoods_star2() {
            return goods_star2;
        }

        public void setGoods_star2(String goods_star2) {
            this.goods_star2 = goods_star2;
        }

        public String getGoods_star3() {
            return goods_star3;
        }

        public void setGoods_star3(String goods_star3) {
            this.goods_star3 = goods_star3;
        }

        public String getSatisfied_count() {
            return satisfied_count;
        }

        public void setSatisfied_count(String satisfied_count) {
            this.satisfied_count = satisfied_count;
        }

        public String getDissatisfied_count() {
            return dissatisfied_count;
        }

        public void setDissatisfied_count(String dissatisfied_count) {
            this.dissatisfied_count = dissatisfied_count;
        }

        public String getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(String business_id) {
            this.business_id = business_id;
        }

        public String getIs_share() {
            return is_share;
        }

        public void setIs_share(String is_share) {
            this.is_share = is_share;
        }

        public int getShare_integral() {
            return share_integral;
        }

        public void setShare_integral(int share_integral) {
            this.share_integral = share_integral;
        }

        public int getIs_price_cut_ranking() {
            return is_price_cut_ranking;
        }

        public void setIs_price_cut_ranking(int is_price_cut_ranking) {
            this.is_price_cut_ranking = is_price_cut_ranking;
        }

        public int getIs_discount_ranking() {
            return is_discount_ranking;
        }

        public void setIs_discount_ranking(int is_discount_ranking) {
            this.is_discount_ranking = is_discount_ranking;
        }

        public String getGoods_no() {
            return goods_no;
        }

        public void setGoods_no(String goods_no) {
            this.goods_no = goods_no;
        }

        public String getGoods_parameters() {
            return goods_parameters;
        }

        public void setGoods_parameters(String goods_parameters) {
            this.goods_parameters = goods_parameters;
        }

        public String getGoods_parameters_name() {
            return goods_parameters_name;
        }

        public void setGoods_parameters_name(String goods_parameters_name) {
            this.goods_parameters_name = goods_parameters_name;
        }

        public String getIs_class_recommend() {
            return is_class_recommend;
        }

        public void setIs_class_recommend(String is_class_recommend) {
            this.is_class_recommend = is_class_recommend;
        }

        public String getClass_recommend_img() {
            return class_recommend_img;
        }

        public void setClass_recommend_img(String class_recommend_img) {
            this.class_recommend_img = class_recommend_img;
        }

        public String getQrcode_img() {
            return qrcode_img;
        }

        public void setQrcode_img(String qrcode_img) {
            this.qrcode_img = qrcode_img;
        }

        public String getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(String activity_id) {
            this.activity_id = activity_id;
        }

        public String getStorehouse_name() {
            return storehouse_name;
        }

        public void setStorehouse_name(String storehouse_name) {
            this.storehouse_name = storehouse_name;
        }

        public String getIs_merchants_recommend() {
            return is_merchants_recommend;
        }

        public void setIs_merchants_recommend(String is_merchants_recommend) {
            this.is_merchants_recommend = is_merchants_recommend;
        }

        public String getIs_exact() {
            return is_exact;
        }

        public void setIs_exact(String is_exact) {
            this.is_exact = is_exact;
        }

        public String getIs_goods_exact() {
            return is_goods_exact;
        }

        public void setIs_goods_exact(String is_goods_exact) {
            this.is_goods_exact = is_goods_exact;
        }

        public String getIs_sales_ranking() {
            return is_sales_ranking;
        }

        public void setIs_sales_ranking(String is_sales_ranking) {
            this.is_sales_ranking = is_sales_ranking;
        }

        public String getIs_directly() {
            return is_directly;
        }

        public void setIs_directly(String is_directly) {
            this.is_directly = is_directly;
        }

        public String getGood_assessment_percent() {
            return good_assessment_percent;
        }

        public void setGood_assessment_percent(String good_assessment_percent) {
            this.good_assessment_percent = good_assessment_percent;
        }

        public int getGood_assessment_count() {
            return good_assessment_count;
        }

        public void setGood_assessment_count(int good_assessment_count) {
            this.good_assessment_count = good_assessment_count;
        }

        public String getBad_assessment_percent() {
            return bad_assessment_percent;
        }

        public void setBad_assessment_percent(String bad_assessment_percent) {
            this.bad_assessment_percent = bad_assessment_percent;
        }

        public int getBad_assessment_count() {
            return bad_assessment_count;
        }

        public void setBad_assessment_count(int bad_assessment_count) {
            this.bad_assessment_count = bad_assessment_count;
        }

        public String getIn_assessment_percent() {
            return in_assessment_percent;
        }

        public void setIn_assessment_percent(String in_assessment_percent) {
            this.in_assessment_percent = in_assessment_percent;
        }

        public int getIn_assessment_count() {
            return in_assessment_count;
        }

        public void setIn_assessment_count(int in_assessment_count) {
            this.in_assessment_count = in_assessment_count;
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

        public String getReceive_time() {
            return receive_time;
        }

        public void setReceive_time(String receive_time) {
            this.receive_time = receive_time;
        }

        public String getGoods_replenishment_time() {
            return goods_replenishment_time;
        }

        public void setGoods_replenishment_time(String goods_replenishment_time) {
            this.goods_replenishment_time = goods_replenishment_time;
        }

        public String getGoods_storage_instructions() {
            return goods_storage_instructions;
        }

        public void setGoods_storage_instructions(String goods_storage_instructions) {
            this.goods_storage_instructions = goods_storage_instructions;
        }

        public String getGoods_addnation_notes() {
            return goods_addnation_notes;
        }

        public void setGoods_addnation_notes(String goods_addnation_notes) {
            this.goods_addnation_notes = goods_addnation_notes;
        }

        public String getGoods_form() {
            return goods_form;
        }

        public void setGoods_form(String goods_form) {
            this.goods_form = goods_form;
        }

        public String getGoods_research_areas() {
            return goods_research_areas;
        }

        public void setGoods_research_areas(String goods_research_areas) {
            this.goods_research_areas = goods_research_areas;
        }

        public String getStandard_type() {
            return standard_type;
        }

        public void setStandard_type(String standard_type) {
            this.standard_type = standard_type;
        }

        public String getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public String getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(String goods_count) {
            this.goods_count = goods_count;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getSort_price() {
            return sort_price;
        }

        public void setSort_price(String sort_price) {
            this.sort_price = sort_price;
        }

        public String getBidding_price() {
            return bidding_price;
        }

        public void setBidding_price(String bidding_price) {
            this.bidding_price = bidding_price;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public MerchantsBeanBean getMerchantsBean() {
            return merchantsBean;
        }

        public void setMerchantsBean(MerchantsBeanBean merchantsBean) {
            this.merchantsBean = merchantsBean;
        }

        public ActivityBeanBean getActivityBean() {
            return activityBean;
        }

        public void setActivityBean(ActivityBeanBean activityBean) {
            this.activityBean = activityBean;
        }

        public List<?> getClassBannerBeans() {
            return classBannerBeans;
        }

        public void setClassBannerBeans(List<?> classBannerBeans) {
            this.classBannerBeans = classBannerBeans;
        }

        public List<?> getGoodsRecommendBeans() {
            return goodsRecommendBeans;
        }

        public void setGoodsRecommendBeans(List<?> goodsRecommendBeans) {
            this.goodsRecommendBeans = goodsRecommendBeans;
        }

        public List<?> getClassRecommendBeans() {
            return classRecommendBeans;
        }

        public void setClassRecommendBeans(List<?> classRecommendBeans) {
            this.classRecommendBeans = classRecommendBeans;
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

        public List<?> getBrandBeans() {
            return brandBeans;
        }

        public void setBrandBeans(List<?> brandBeans) {
            this.brandBeans = brandBeans;
        }

        public List<?> getGoodsParameterBeans() {
            return goodsParameterBeans;
        }

        public void setGoodsParameterBeans(List<?> goodsParameterBeans) {
            this.goodsParameterBeans = goodsParameterBeans;
        }

        public List<?> getGoodsServiceBeans() {
            return goodsServiceBeans;
        }

        public void setGoodsServiceBeans(List<?> goodsServiceBeans) {
            this.goodsServiceBeans = goodsServiceBeans;
        }

        public List<?> getGroupBuyGoodsBeans() {
            return groupBuyGoodsBeans;
        }

        public void setGroupBuyGoodsBeans(List<?> groupBuyGoodsBeans) {
            this.groupBuyGoodsBeans = groupBuyGoodsBeans;
        }

        public List<?> getStandardBeans() {
            return standardBeans;
        }

        public void setStandardBeans(List<?> standardBeans) {
            this.standardBeans = standardBeans;
        }

        public List<?> getActivityBeans() {
            return activityBeans;
        }

        public void setActivityBeans(List<?> activityBeans) {
            this.activityBeans = activityBeans;
        }

        public List<?> getGoodsLabelBeans() {
            return goodsLabelBeans;
        }

        public void setGoodsLabelBeans(List<?> goodsLabelBeans) {
            this.goodsLabelBeans = goodsLabelBeans;
        }

        public static class MerchantsBeanBean {
        }

        public static class ActivityBeanBean {
        }
    }
    public static class InformationBeanBean {
        /**
         * release_id : 6
         * member_id : 54
         * release_title : 破记录了
         * release_img :
         * release_number : 5
         * release_price : 2254.0
         * release_province : 上海市
         * release_city : 上海市
         * release_country : 浦东新区
         * release_address : 长清路
         * release_details : syqbshjqhwushs_cr/_njjikonkbjvuycrcddfbhhj;njjikkkkivhvgfvgb;nihgghbcfhvfghvf
         * release_type : supply
         * release_type_show : 供应
         * release_order_id : 0
         * release_is_top : 0
         * release_top_price : 0.0
         * release_parent_id : 1
         * release_uuid : 458cfe3d-16a2-4f9c-ad93-818b88532183
         * release_parent_uuid : #c51dac55-78bc-4337-94bb-f3e309a08877#458cfe3d-16a2-4f9c-ad93-818b88532183
         * release_is_delete : 0
         * release_create_time : 2017-08-09 14:31:05.0
         * release_number_attribute_id : 1
         * release_number_attribute_name : 吨
         * release_price_attribute_id : 1
         * release_price_attribute_name : 吨
         * release_car_id : 0
         * relesse_car_name :
         * release_car_no :
         * release_name :
         * release_phone :
         * release_lng :
         * release_lat :
         * sort_way :
         * releaseImgBean : {}
         * release_class_uuid :
         * distance :
         */

        private int release_id;
        private int member_id;
        private String release_title;
        private String release_img;
        private int release_number;
        private double release_price;
        private String release_province;
        private String release_city;
        private String release_country;
        private String release_address;
        private String release_details;
        private String release_type;
        private String release_type_show;
        private int release_order_id;
        private String release_is_top;
        private double release_top_price;
        private int release_parent_id;
        private String release_uuid;
        private String release_parent_uuid;
        private int release_is_delete;
        private String release_create_time;
        private int release_number_attribute_id;
        private String release_number_attribute_name;
        private int release_price_attribute_id;
        private String release_price_attribute_name;
        private int release_car_id;
        private String relesse_car_name;
        private String release_car_no;
        private String release_name;
        private String release_phone;
        private String release_lng;
        private String release_lat;
        private String sort_way;
        private ReleaseImgBeanBean releaseImgBean;
        private String release_class_uuid;
        private String distance;

        public int getRelease_id() {
            return release_id;
        }

        public void setRelease_id(int release_id) {
            this.release_id = release_id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getRelease_title() {
            return release_title;
        }

        public void setRelease_title(String release_title) {
            this.release_title = release_title;
        }

        public String getRelease_img() {
            return release_img;
        }

        public void setRelease_img(String release_img) {
            this.release_img = release_img;
        }

        public int getRelease_number() {
            return release_number;
        }

        public void setRelease_number(int release_number) {
            this.release_number = release_number;
        }

        public double getRelease_price() {
            return release_price;
        }

        public void setRelease_price(double release_price) {
            this.release_price = release_price;
        }

        public String getRelease_province() {
            return release_province;
        }

        public void setRelease_province(String release_province) {
            this.release_province = release_province;
        }

        public String getRelease_city() {
            return release_city;
        }

        public void setRelease_city(String release_city) {
            this.release_city = release_city;
        }

        public String getRelease_country() {
            return release_country;
        }

        public void setRelease_country(String release_country) {
            this.release_country = release_country;
        }

        public String getRelease_address() {
            return release_address;
        }

        public void setRelease_address(String release_address) {
            this.release_address = release_address;
        }

        public String getRelease_details() {
            return release_details;
        }

        public void setRelease_details(String release_details) {
            this.release_details = release_details;
        }

        public String getRelease_type() {
            return release_type;
        }

        public void setRelease_type(String release_type) {
            this.release_type = release_type;
        }

        public String getRelease_type_show() {
            return release_type_show;
        }

        public void setRelease_type_show(String release_type_show) {
            this.release_type_show = release_type_show;
        }

        public int getRelease_order_id() {
            return release_order_id;
        }

        public void setRelease_order_id(int release_order_id) {
            this.release_order_id = release_order_id;
        }

        public String getRelease_is_top() {
            return release_is_top;
        }

        public void setRelease_is_top(String release_is_top) {
            this.release_is_top = release_is_top;
        }

        public double getRelease_top_price() {
            return release_top_price;
        }

        public void setRelease_top_price(double release_top_price) {
            this.release_top_price = release_top_price;
        }

        public int getRelease_parent_id() {
            return release_parent_id;
        }

        public void setRelease_parent_id(int release_parent_id) {
            this.release_parent_id = release_parent_id;
        }

        public String getRelease_uuid() {
            return release_uuid;
        }

        public void setRelease_uuid(String release_uuid) {
            this.release_uuid = release_uuid;
        }

        public String getRelease_parent_uuid() {
            return release_parent_uuid;
        }

        public void setRelease_parent_uuid(String release_parent_uuid) {
            this.release_parent_uuid = release_parent_uuid;
        }

        public int getRelease_is_delete() {
            return release_is_delete;
        }

        public void setRelease_is_delete(int release_is_delete) {
            this.release_is_delete = release_is_delete;
        }

        public String getRelease_create_time() {
            return release_create_time;
        }

        public void setRelease_create_time(String release_create_time) {
            this.release_create_time = release_create_time;
        }

        public int getRelease_number_attribute_id() {
            return release_number_attribute_id;
        }

        public void setRelease_number_attribute_id(int release_number_attribute_id) {
            this.release_number_attribute_id = release_number_attribute_id;
        }

        public String getRelease_number_attribute_name() {
            return release_number_attribute_name;
        }

        public void setRelease_number_attribute_name(String release_number_attribute_name) {
            this.release_number_attribute_name = release_number_attribute_name;
        }

        public int getRelease_price_attribute_id() {
            return release_price_attribute_id;
        }

        public void setRelease_price_attribute_id(int release_price_attribute_id) {
            this.release_price_attribute_id = release_price_attribute_id;
        }

        public String getRelease_price_attribute_name() {
            return release_price_attribute_name;
        }

        public void setRelease_price_attribute_name(String release_price_attribute_name) {
            this.release_price_attribute_name = release_price_attribute_name;
        }

        public int getRelease_car_id() {
            return release_car_id;
        }

        public void setRelease_car_id(int release_car_id) {
            this.release_car_id = release_car_id;
        }

        public String getRelesse_car_name() {
            return relesse_car_name;
        }

        public void setRelesse_car_name(String relesse_car_name) {
            this.relesse_car_name = relesse_car_name;
        }

        public String getRelease_car_no() {
            return release_car_no;
        }

        public void setRelease_car_no(String release_car_no) {
            this.release_car_no = release_car_no;
        }

        public String getRelease_name() {
            return release_name;
        }

        public void setRelease_name(String release_name) {
            this.release_name = release_name;
        }

        public String getRelease_phone() {
            return release_phone;
        }

        public void setRelease_phone(String release_phone) {
            this.release_phone = release_phone;
        }

        public String getRelease_lng() {
            return release_lng;
        }

        public void setRelease_lng(String release_lng) {
            this.release_lng = release_lng;
        }

        public String getRelease_lat() {
            return release_lat;
        }

        public void setRelease_lat(String release_lat) {
            this.release_lat = release_lat;
        }

        public String getSort_way() {
            return sort_way;
        }

        public void setSort_way(String sort_way) {
            this.sort_way = sort_way;
        }

        public ReleaseImgBeanBean getReleaseImgBean() {
            return releaseImgBean;
        }

        public void setReleaseImgBean(ReleaseImgBeanBean releaseImgBean) {
            this.releaseImgBean = releaseImgBean;
        }

        public String getRelease_class_uuid() {
            return release_class_uuid;
        }

        public void setRelease_class_uuid(String release_class_uuid) {
            this.release_class_uuid = release_class_uuid;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public static class ReleaseImgBeanBean {
        }


    }
    public static class ReleaseBeanBean {
        /**
         * release_id : 6
         * member_id : 54
         * release_title : 破记录了
         * release_img :
         * release_number : 5
         * release_price : 2254.0
         * release_province : 上海市
         * release_city : 上海市
         * release_country : 浦东新区
         * release_address : 长清路
         * release_details : syqbshjqhwushs_cr/_njjikonkbjvuycrcddfbhhj;njjikkkkivhvgfvgb;nihgghbcfhvfghvf
         * release_type : supply
         * release_type_show : 供应
         * release_order_id : 0
         * release_is_top : 0
         * release_top_price : 0.0
         * release_parent_id : 1
         * release_uuid : 458cfe3d-16a2-4f9c-ad93-818b88532183
         * release_parent_uuid : #c51dac55-78bc-4337-94bb-f3e309a08877#458cfe3d-16a2-4f9c-ad93-818b88532183
         * release_is_delete : 0
         * release_create_time : 2017-08-09 14:31:05.0
         * release_number_attribute_id : 1
         * release_number_attribute_name : 吨
         * release_price_attribute_id : 1
         * release_price_attribute_name : 吨
         * release_car_id : 0
         * relesse_car_name :
         * release_car_no :
         * release_name :
         * release_phone :
         * release_lng :
         * release_lat :
         * sort_way :
         * releaseImgBean : {}
         * release_class_uuid :
         * distance :
         */

        private int release_id;
        private int member_id;
        private String release_title;
        private String release_img;
        private int release_number;
        private double release_price;
        private String release_province;
        private String release_city;
        private String release_country;
        private String release_address;
        private String release_details;
        private String release_type;
        private String release_type_show;
        private int release_order_id;
        private String release_is_top;
        private double release_top_price;
        private int release_parent_id;
        private String release_uuid;
        private String release_parent_uuid;
        private int release_is_delete;
        private String release_create_time;
        private int release_number_attribute_id;
        private String release_number_attribute_name;
        private int release_price_attribute_id;
        private String release_price_attribute_name;
        private int release_car_id;
        private String relesse_car_name;
        private String release_car_no;
        private String release_name;
        private String release_phone;
        private String release_lng;
        private String release_lat;
        private String sort_way;
        private ReleaseBeans.ReleaseImgBeanBean releaseImgBean;
        private String release_class_uuid;
        private String distance;

        public int getRelease_id() {
            return release_id;
        }

        public void setRelease_id(int release_id) {
            this.release_id = release_id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getRelease_title() {
            return release_title;
        }

        public void setRelease_title(String release_title) {
            this.release_title = release_title;
        }

        public String getRelease_img() {
            return release_img;
        }

        public void setRelease_img(String release_img) {
            this.release_img = release_img;
        }

        public int getRelease_number() {
            return release_number;
        }

        public void setRelease_number(int release_number) {
            this.release_number = release_number;
        }

        public double getRelease_price() {
            return release_price;
        }

        public void setRelease_price(double release_price) {
            this.release_price = release_price;
        }

        public String getRelease_province() {
            return release_province;
        }

        public void setRelease_province(String release_province) {
            this.release_province = release_province;
        }

        public String getRelease_city() {
            return release_city;
        }

        public void setRelease_city(String release_city) {
            this.release_city = release_city;
        }

        public String getRelease_country() {
            return release_country;
        }

        public void setRelease_country(String release_country) {
            this.release_country = release_country;
        }

        public String getRelease_address() {
            return release_address;
        }

        public void setRelease_address(String release_address) {
            this.release_address = release_address;
        }

        public String getRelease_details() {
            return release_details;
        }

        public void setRelease_details(String release_details) {
            this.release_details = release_details;
        }

        public String getRelease_type() {
            return release_type;
        }

        public void setRelease_type(String release_type) {
            this.release_type = release_type;
        }

        public String getRelease_type_show() {
            return release_type_show;
        }

        public void setRelease_type_show(String release_type_show) {
            this.release_type_show = release_type_show;
        }

        public int getRelease_order_id() {
            return release_order_id;
        }

        public void setRelease_order_id(int release_order_id) {
            this.release_order_id = release_order_id;
        }

        public String getRelease_is_top() {
            return release_is_top;
        }

        public void setRelease_is_top(String release_is_top) {
            this.release_is_top = release_is_top;
        }

        public double getRelease_top_price() {
            return release_top_price;
        }

        public void setRelease_top_price(double release_top_price) {
            this.release_top_price = release_top_price;
        }

        public int getRelease_parent_id() {
            return release_parent_id;
        }

        public void setRelease_parent_id(int release_parent_id) {
            this.release_parent_id = release_parent_id;
        }

        public String getRelease_uuid() {
            return release_uuid;
        }

        public void setRelease_uuid(String release_uuid) {
            this.release_uuid = release_uuid;
        }

        public String getRelease_parent_uuid() {
            return release_parent_uuid;
        }

        public void setRelease_parent_uuid(String release_parent_uuid) {
            this.release_parent_uuid = release_parent_uuid;
        }

        public int getRelease_is_delete() {
            return release_is_delete;
        }

        public void setRelease_is_delete(int release_is_delete) {
            this.release_is_delete = release_is_delete;
        }

        public String getRelease_create_time() {
            return release_create_time;
        }

        public void setRelease_create_time(String release_create_time) {
            this.release_create_time = release_create_time;
        }

        public int getRelease_number_attribute_id() {
            return release_number_attribute_id;
        }

        public void setRelease_number_attribute_id(int release_number_attribute_id) {
            this.release_number_attribute_id = release_number_attribute_id;
        }

        public String getRelease_number_attribute_name() {
            return release_number_attribute_name;
        }

        public void setRelease_number_attribute_name(String release_number_attribute_name) {
            this.release_number_attribute_name = release_number_attribute_name;
        }

        public int getRelease_price_attribute_id() {
            return release_price_attribute_id;
        }

        public void setRelease_price_attribute_id(int release_price_attribute_id) {
            this.release_price_attribute_id = release_price_attribute_id;
        }

        public String getRelease_price_attribute_name() {
            return release_price_attribute_name;
        }

        public void setRelease_price_attribute_name(String release_price_attribute_name) {
            this.release_price_attribute_name = release_price_attribute_name;
        }

        public int getRelease_car_id() {
            return release_car_id;
        }

        public void setRelease_car_id(int release_car_id) {
            this.release_car_id = release_car_id;
        }

        public String getRelesse_car_name() {
            return relesse_car_name;
        }

        public void setRelesse_car_name(String relesse_car_name) {
            this.relesse_car_name = relesse_car_name;
        }

        public String getRelease_car_no() {
            return release_car_no;
        }

        public void setRelease_car_no(String release_car_no) {
            this.release_car_no = release_car_no;
        }

        public String getRelease_name() {
            return release_name;
        }

        public void setRelease_name(String release_name) {
            this.release_name = release_name;
        }

        public String getRelease_phone() {
            return release_phone;
        }

        public void setRelease_phone(String release_phone) {
            this.release_phone = release_phone;
        }

        public String getRelease_lng() {
            return release_lng;
        }

        public void setRelease_lng(String release_lng) {
            this.release_lng = release_lng;
        }

        public String getRelease_lat() {
            return release_lat;
        }

        public void setRelease_lat(String release_lat) {
            this.release_lat = release_lat;
        }

        public String getSort_way() {
            return sort_way;
        }

        public void setSort_way(String sort_way) {
            this.sort_way = sort_way;
        }

        public ReleaseBeans.ReleaseImgBeanBean getReleaseImgBean() {
            return releaseImgBean;
        }

        public void setReleaseImgBean(ReleaseBeans.ReleaseImgBeanBean releaseImgBean) {
            this.releaseImgBean = releaseImgBean;
        }

        public String getRelease_class_uuid() {
            return release_class_uuid;
        }

        public void setRelease_class_uuid(String release_class_uuid) {
            this.release_class_uuid = release_class_uuid;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public static class ReleaseImgBeanBean {
        }

    }



}
