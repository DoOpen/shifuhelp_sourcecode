package com.project.bean.member;

/**
 * 优惠券
 * @author 彭方林
 * @date 2018年4月2日
 */
public class CouponBean {
	private Integer coupon_id;
	private Integer member_coupon_id;//用户领取优惠券id
	private String coupon_name;//优惠卷名称
	private Integer coupon_price;//优惠卷价格
	private Integer coupon_full_price;//优惠卷满减价格
	private String coupon_desc;//优惠卷介绍
	private String start_time;//开始时间
	private String end_time;//结束时间
	private String coupon_type;//优惠卷类型      goods:商品  merchants:商家 class:分类   platform:平台
	private String coupon_type_show;//
	private String is_delete;//1删除
	private String create_time;//创建时间
	private String coupon_img;//展示图片
	private String coupon_way;//领取方式
	private String coupon_postion;//展示位置  coupon:领卷中心
	private String coupon_postion_show;//
	private Integer goods_id;//可使用商品id
	private Integer class_id;//可使用分类id
	private String class_uuid;//可使用分类uuid
	private Integer member_id;//用户id
	private Integer valid_day;//有效天数
	private String coupon_state;//状态      not_used:未使用  already_used:已使用  expired:过期
	private String is_save_take;//是否保存已领取 1保留0不保留
	private String is_repeat_take;//是否允许重复领取 1允许 0不允许
	public String getIs_save_take() {
		return is_save_take;
	}
	public CouponBean setIs_save_take(String is_save_take) {
		this.is_save_take = is_save_take;
		return this;
	}
	public String getIs_repeat_take() {
		return is_repeat_take;
	}
	public CouponBean setIs_repeat_take(String is_repeat_take) {
		this.is_repeat_take = is_repeat_take;
		return this;
	}
	public Integer getMember_coupon_id() {
		return member_coupon_id;
	}
	public CouponBean setMember_coupon_id(Integer member_coupon_id) {
		this.member_coupon_id = member_coupon_id;
		return this;
	}
	public String getCoupon_state() {
		return coupon_state;
	}
	public CouponBean setCoupon_state(String coupon_state) {
		this.coupon_state = coupon_state;
		return this;
	}
	public Integer getCoupon_id() {
		return coupon_id;
	}
	public CouponBean setCoupon_id(Integer coupon_id) {
		this.coupon_id = coupon_id;
		return this;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public CouponBean setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
		return this;
	}
	public Integer getCoupon_price() {
		return coupon_price;
	}
	public CouponBean setCoupon_price(Integer coupon_price) {
		this.coupon_price = coupon_price;
		return this;
	}
	public Integer getCoupon_full_price() {
		return coupon_full_price;
	}
	public CouponBean setCoupon_full_price(Integer coupon_full_price) {
		this.coupon_full_price = coupon_full_price;
		return this;
	}
	public String getCoupon_desc() {
		return coupon_desc;
	}
	public CouponBean setCoupon_desc(String coupon_desc) {
		this.coupon_desc = coupon_desc;
		return this;
	}
	public String getStart_time() {
		return start_time;
	}
	public CouponBean setStart_time(String start_time) {
		this.start_time = start_time;
		return this;
	}
	public String getEnd_time() {
		return end_time;
	}
	public CouponBean setEnd_time(String end_time) {
		this.end_time = end_time;
		return this;
	}
	public String getCoupon_type() {
		return coupon_type;
	}
	public CouponBean setCoupon_type(String coupon_type) {
		this.coupon_type = coupon_type;
		this.coupon_type_show="goods".equals(coupon_type)?"商品":
			"merchants".equals(coupon_type)?"商家":
				"class".equals(coupon_type)?"分类":
					"platform".equals(coupon_type)?"平台":"未知";
		return this;
	}
	public String getCoupon_type_show() {
		return coupon_type_show;
	}
	public CouponBean setCoupon_type_show(String coupon_type_show) {
		this.coupon_type_show = coupon_type_show;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public CouponBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public CouponBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getCoupon_img() {
		return coupon_img;
	}
	public CouponBean setCoupon_img(String coupon_img) {
		this.coupon_img = coupon_img;
		return this;
	}
	public String getCoupon_way() {
		return coupon_way;
	}
	public CouponBean setCoupon_way(String coupon_way) {
		this.coupon_way = coupon_way;
		return this;
	}
	public String getCoupon_postion() {
		return coupon_postion;
	}
	public CouponBean setCoupon_postion(String coupon_postion) {
		this.coupon_postion = coupon_postion;
		this.coupon_postion_show="coupon".equals(coupon_postion)?"领卷中心":
			"system".equals(coupon_postion)?"后台":"未知";
		return this;
	}
	public String getCoupon_postion_show() {
		return coupon_postion_show;
	}
	public CouponBean setCoupon_postion_show(String coupon_postion_show) {
		this.coupon_postion_show = coupon_postion_show;
		return this;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public CouponBean setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
		return this;
	}
	public Integer getClass_id() {
		return class_id;
	}
	public CouponBean setClass_id(Integer class_id) {
		this.class_id = class_id;
		return this;
	}
	public String getClass_uuid() {
		return class_uuid;
	}
	public CouponBean setClass_uuid(String class_uuid) {
		this.class_uuid = class_uuid;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public CouponBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public Integer getValid_day() {
		return valid_day;
	}
	public CouponBean setValid_day(Integer valid_day) {
		this.valid_day = valid_day;
		return this;
	}
}
