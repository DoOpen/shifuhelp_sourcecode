package com.project.bean.goods;

import java.util.List;
/**
 * 商品信息
 * @author 彭方林
 * @date 2018年4月2日
 */
public class GoodsBean {
	private Integer goods_id;//商品id
	private String goods_num;//商品编码
	private String goods_name;//名称
	private String goods_img;//图片
	private Float goods_min_price;//最小规格价格
	private Float goods_max_price;//最大规格价格
	private String goods_now_price;//现价
	private String goods_desc;//商品简介
	private String goods_url;//详情web地址
	private String goods_url_content;//详情web内容
	private Integer actual_sales;//实际销量
	private Integer total_sales;//总销量
	private Integer month_sales;//月销量
	private Integer day_sales;//日销量
	private Integer goods_stock;//库存
	private Float goods_star1;//星级1
	private Float goods_star2;//星级2
	private Float goods_star3;//星级3
	private Float goods_star_total;//总评价星级
	private Integer assessment_count;//评价数
	private String start_time;//生产开始日期
	private String end_time;//生产结束时间
	private String goods_state;//状态 1上架 0下架
	private String goods_state_show;
	private Float express_price;//邮费
	private Float free_express_price;//满多少包邮
	private String is_delete;//是否删除
	private String create_time;//创建时间
	private String update_time;//更新时间
	private Integer class_id;//分类id
	private String class_uuid;//分类的class_uuid
	private String class_name;//分类名称
	private Integer member_id;//用户id
	private Integer collection_id;//收藏id
	private String goods_ids;
	private String order;//排序字段
	private String show_type;//商品展示位置 app:师傅端  wx:微信端
	private String show_type_show;
	private String buy_type;//购买类型  money:现金购买  integral:积分购买
	private String buy_type_show;
	private String send_range;//派送区域
	private List<GoodsImgBean> goodsImgBeans;//商品图片列表
	private List<GoodsSpecificationBean> goodsSpecificationBeans;//商品规格组合列表
	private List<SpecificationBean> specificationBeans;//规格列表
	private GoodsClassBean goodsClassBean;//商品分类
	
	public String getClass_name() {
		return class_name;
	}
	public GoodsBean setClass_name(String class_name) {
		this.class_name = class_name;
		return this;
	}
	public String getSend_range() {
		return send_range;
	}
	public GoodsBean setSend_range(String send_range) {
		this.send_range = send_range;
		return this;
	}
	public String getShow_type_show() {
		return show_type_show;
	}
	public GoodsBean setShow_type_show(String show_type_show) {
		this.show_type_show = show_type_show;
		return this;
	}
	public String getBuy_type_show() {
		return buy_type_show;
	}
	public GoodsBean setBuy_type_show(String buy_type_show) {
		this.buy_type_show = buy_type_show;
		return this;
	}
	public String getShow_type() {
		return show_type;
	}
	public GoodsBean setShow_type(String show_type) {
		this.show_type = show_type;
		this.show_type_show=show_type.indexOf("app")>-1&&show_type.indexOf("wx")>-1?"师傅端/微信端":
				show_type.indexOf("app")>-1?"师傅端":
			show_type.indexOf("wx")>-1?"微信端":"未知";
		return this;
	}
	public String getBuy_type() {
		return buy_type;
	}
	public GoodsBean setBuy_type(String buy_type) {
		this.buy_type = buy_type;
		this.buy_type_show="money".equals(buy_type)?"余额":
			"integral".equals(buy_type)?"积分":"未知";
		return this;
	}
	public GoodsClassBean getGoodsClassBean() {
		return goodsClassBean;
	}
	public GoodsBean setGoodsClassBean(GoodsClassBean goodsClassBean) {
		this.goodsClassBean = goodsClassBean;
		return this;
	}
	public Float getGoods_star_total() {
		return goods_star_total;
	}
	public GoodsBean setGoods_star_total(Float goods_star_total) {
		this.goods_star_total = goods_star_total;
		return this;
	}
	public Float getFree_express_price() {
		return free_express_price;
	}
	public GoodsBean setFree_express_price(Float free_express_price) {
		this.free_express_price = free_express_price;
		return this;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public GoodsBean setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
		return this;
	}
	public String getGoods_num() {
		return goods_num;
	}
	public GoodsBean setGoods_num(String goods_num) {
		this.goods_num = goods_num;
		return this;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public GoodsBean setGoods_name(String goods_name) {
		this.goods_name = goods_name;
		return this;
	}
	public String getGoods_img() {
		return goods_img;
	}
	public GoodsBean setGoods_img(String goods_img) {
		this.goods_img = goods_img;
		return this;
	}
	
	public Float getGoods_min_price() {
		return goods_min_price;
	}
	public GoodsBean setGoods_min_price(Float goods_min_price) {
		this.goods_min_price = goods_min_price;
		return this;
	}
	public Float getGoods_max_price() {
		return goods_max_price;
	}
	public GoodsBean setGoods_max_price(Float goods_max_price) {
		this.goods_max_price = goods_max_price;
		return this;
	}
	public String getGoods_now_price() {
		return goods_now_price;
	}
	public GoodsBean setGoods_now_price(String goods_now_price) {
		this.goods_now_price = goods_now_price;
		return this;
	}
	public String getGoods_desc() {
		return goods_desc;
	}
	public GoodsBean setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
		return this;
	}
	public String getGoods_url() {
		return goods_url;
	}
	public GoodsBean setGoods_url(String goods_url) {
		this.goods_url = goods_url;
		return this;
	}
	public String getGoods_url_content() {
		return goods_url_content;
	}
	public GoodsBean setGoods_url_content(String goods_url_content) {
		this.goods_url_content = goods_url_content;
		return this;
	}
	public Integer getActual_sales() {
		return actual_sales;
	}
	public GoodsBean setActual_sales(Integer actual_sales) {
		this.actual_sales = actual_sales;
		return this;
	}
	public Integer getTotal_sales() {
		return total_sales;
	}
	public GoodsBean setTotal_sales(Integer total_sales) {
		this.total_sales = total_sales;
		return this;
	}
	public Integer getMonth_sales() {
		return month_sales;
	}
	public GoodsBean setMonth_sales(Integer month_sales) {
		this.month_sales = month_sales;
		return this;
	}
	public Integer getDay_sales() {
		return day_sales;
	}
	public GoodsBean setDay_sales(Integer day_sales) {
		this.day_sales = day_sales;
		return this;
	}
	public Integer getGoods_stock() {
		return goods_stock;
	}
	public GoodsBean setGoods_stock(Integer goods_stock) {
		this.goods_stock = goods_stock;
		return this;
	}
	public Float getGoods_star1() {
		return goods_star1;
	}
	public GoodsBean setGoods_star1(Float goods_star1) {
		this.goods_star1 = goods_star1;
		return this;
	}
	public Float getGoods_star2() {
		return goods_star2;
	}
	public GoodsBean setGoods_star2(Float goods_star2) {
		this.goods_star2 = goods_star2;
		return this;
	}
	public Float getGoods_star3() {
		return goods_star3;
	}
	public GoodsBean setGoods_star3(Float goods_star3) {
		this.goods_star3 = goods_star3;
		return this;
	}
	public Integer getAssessment_count() {
		return assessment_count;
	}
	public GoodsBean setAssessment_count(Integer assessment_count) {
		this.assessment_count = assessment_count;
		return this;
	}
	public String getGoods_state() {
		return goods_state;
	}
	public GoodsBean setGoods_state(String goods_state) {
		this.goods_state = goods_state;
		return this;
	}
	public String getGoods_state_show() {
		return goods_state_show;
	}
	public GoodsBean setGoods_state_show(String goods_state_show) {
		this.goods_state_show = goods_state_show;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public GoodsBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public GoodsBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public GoodsBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public Integer getClass_id() {
		return class_id;
	}
	public GoodsBean setClass_id(Integer class_id) {
		this.class_id = class_id;
		return this;
	}
	public String getClass_uuid() {
		return class_uuid;
	}
	public GoodsBean setClass_uuid(String class_uuid) {
		this.class_uuid = class_uuid;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public GoodsBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Integer getCollection_id() {
		return collection_id;
	}
	public GoodsBean setCollection_id(Integer collection_id) {
		this.collection_id = collection_id;
		return this;
	}
	public List<GoodsImgBean> getGoodsImgBeans() {
		return goodsImgBeans;
	}
	public GoodsBean setGoodsImgBeans(List<GoodsImgBean> goodsImgBeans) {
		this.goodsImgBeans = goodsImgBeans;
		return this;
	}
	public List<GoodsSpecificationBean> getGoodsSpecificationBeans() {
		return goodsSpecificationBeans;
	}
	public GoodsBean setGoodsSpecificationBeans(List<GoodsSpecificationBean> goodsSpecificationBeans) {
		this.goodsSpecificationBeans = goodsSpecificationBeans;
		return this;
	}
	public List<SpecificationBean> getSpecificationBeans() {
		return specificationBeans;
	}
	public GoodsBean setSpecificationBeans(List<SpecificationBean> specificationBeans) {
		this.specificationBeans = specificationBeans;
		return this;
	}
	public String getStart_time() {
		return start_time;
	}
	public GoodsBean setStart_time(String start_time) {
		this.start_time = start_time;
		return this;
	}
	public String getEnd_time() {
		return end_time;
	}
	public GoodsBean setEnd_time(String end_time) {
		this.end_time = end_time;
		return this;
	}
	public Float getExpress_price() {
		return express_price;
	}
	public GoodsBean setExpress_price(Float express_price) {
		this.express_price = express_price;
		return this;
	}
	public String getGoods_ids() {
		return goods_ids;
	}
	public GoodsBean setGoods_ids(String goods_ids) {
		this.goods_ids = goods_ids;
		return this;
	}
	
	public String getOrder() {
		return order;
	}
	public GoodsBean setOrder(String order) {
		this.order = order;
		return this;
	}
}
