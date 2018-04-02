package com.project.bean.order;

import java.util.List;

import com.project.bean.member.AddressBean;

/**
 * 订单
 * @author 彭方林
 * @date 2018年4月2日
 */
public class OrderBean {
	private Integer order_id;//订单id
	private String order_ids;//订单id列表
	private Integer member_id;//用户id
	private Integer member_group_id;//用户团购id
	private String order_no;//订单号
	private Integer address_id;//收货地址id
	private String address_mobile;//收货人手机号
	private String address_name;//收货人姓名
	private String address_province;//收货人地址-省
	private String address_city;//收货人地址-市
	private String address_district;//收货人地址-区
	private String address_detail;//收货人地址-详细地址
	private String address_zip_code;//说过人地址-邮编
	private String address_longitude;//收货人地址-经度
	private String address_latitude;//收货人地址-纬度
	private Float order_total_price;//订单总价
	private Float order_actual_price;//订单实际支付价格
	private Float order_refund_price;//订单退款金额
	private String order_type;//订单类型 goods:普通订单
	private String order_state;// 订单状态  refund:退款数量 正好是购买数量 cancel：取消  wait_pay:待付款  wait_send:待发货  wait_receive：待确认收货 wait_assessment：待评价 end：已结束
	private String order_state_show;//订单显示状态
	private String order_remark;//订单备注
	private Integer deduct_integral_value;//抵扣积分值
	private Float deduct_integral_price;//抵扣金额
	private String deduct_integral_percent;//抵扣金额的百分比
	private String custom_remark;//客服备注
	private String create_time;//下单时间
	private String update_time;//更新时间
	private String cancel_time;//取消时间
	private String pay_time;//付款时间
	private String send_time;//发货时间
	private String receive_time;//收货时间
	private String assessment_time;//评价时间
	private String is_delete;//是否删除
	private String member_is_delete;//用户是否删除
	private String pay_way;//支付方式
	private String pay_way_show;
	private String express_price;//邮费
	private String express_free_price;//满多少包邮
	private String pay_no;//支付付款订单号
	private String pay_charge;//支付凭证
	private String ping_no;//ping++生成的支付订单号
	private Integer give_integral_value;//赠送积分值
	private Integer logistics_id;//物流公司id
	private String logistics_no;//物流单号
	private String logistics_name;//物流公司名称
	private String logistics_code;//物流公司名称缩写
	private String logistics_state;//0-无轨迹 2-在途中 3-签收 4-问题件
	private String logistics_state_show;//物流状态显示
	private String invoice_state;//发票状态
	private String invoice_state_show;//发票状态显示
	private String invoice_type;//发票类型 0:个人 1:公司
	private String invoice_rise;//发票抬头
	private String invoice_no;//纳税人识别号
	private String invoice_desc;//发票内容
	private String invoice_price;//发票金额
	private String invoice_mobile;//收件人手机号
	private String invoice_name;//收件人姓名
	private String invoice_province;//收件人省
	private String invoice_city;//收件人市
	private String invoice_district;//收件人区
	private String invoice_address_detail;//收件人详细地址
	private String invoice_time;//申请时间
	private String start_time;//订单搜索开始时间
	private String end_time;//订单搜索结束时间
	private String search_type;//搜索类型
	private String buy_type;//购买类型
	private String goods_name;//商品名称
	private String cancel_end_time;//取消订单结束时间
	private Integer member_coupon_id;//用户领取的优惠券id
	private String coupon_full_price;//优惠券满减价格
	private String coupon_price;//优惠券价格
	private List<OrderGoodsBean> orderGoodsBeans;//订单商品列表
	private List<OrderLogisticsBean> orderLogisticsBeans;
	private AddressBean addressBean;
	
	
	public String getBuy_type() {
		return buy_type;
	}
	public OrderBean setBuy_type(String buy_type) {
		this.buy_type = buy_type;
		return this;
	}
	public String getMember_is_delete() {
		return member_is_delete;
	}
	public OrderBean setMember_is_delete(String member_is_delete) {
		this.member_is_delete = member_is_delete;
		return this;
	}
	
	public String getOrder_ids() {
		return order_ids;
	}
	public OrderBean setOrder_ids(String order_ids) {
		this.order_ids = order_ids;
		return this;
	}
	public Integer getMember_coupon_id() {
		return member_coupon_id;
	}
	public OrderBean setMember_coupon_id(Integer member_coupon_id) {
		this.member_coupon_id = member_coupon_id;
		return this;
	}
	public String getCoupon_full_price() {
		return coupon_full_price;
	}
	public OrderBean setCoupon_full_price(String coupon_full_price) {
		this.coupon_full_price = coupon_full_price;
		return this;
	}
	public String getCoupon_price() {
		return coupon_price;
	}
	public OrderBean setCoupon_price(String coupon_price) {
		this.coupon_price = coupon_price;
		return this;
	}
	public String getExpress_price() {
		return express_price;
	}
	public OrderBean setExpress_price(String express_price) {
		this.express_price = express_price;
		return this;
	}
	public String getExpress_free_price() {
		return express_free_price;
	}
	public OrderBean setExpress_free_price(String express_free_price) {
		this.express_free_price = express_free_price;
		return this;
	}
	public Integer getLogistics_id() {
		return logistics_id;
	}
	public OrderBean setLogistics_id(Integer logistics_id) {
		this.logistics_id = logistics_id;
		return this;
	}
	public String getPay_way_show() {
		return pay_way_show;
	}
	public OrderBean setPay_way_show(String pay_way_show) {
		this.pay_way_show = pay_way_show;
		return this;
	}
	public String getCancel_end_time() {
		return cancel_end_time;
	}
	public OrderBean setCancel_end_time(String cancel_end_time) {
		this.cancel_end_time = cancel_end_time;
		return this;
	}
	public AddressBean getAddressBean() {
		return addressBean;
	}
	public OrderBean setAddressBean(AddressBean addressBean) {
		this.addressBean = addressBean;
		return this;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public OrderBean setOrder_id(Integer order_id) {
		this.order_id = order_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public OrderBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public String getOrder_no() {
		return order_no;
	}
	public OrderBean setOrder_no(String order_no) {
		this.order_no = order_no;
		return this;
	}
	public Integer getAddress_id() {
		return address_id;
	}
	public OrderBean setAddress_id(Integer address_id) {
		this.address_id = address_id;
		return this;
	}
	public String getAddress_mobile() {
		return address_mobile;
	}
	public OrderBean setAddress_mobile(String address_mobile) {
		this.address_mobile = address_mobile;
		return this;
	}
	public String getAddress_name() {
		return address_name;
	}
	public OrderBean setAddress_name(String address_name) {
		this.address_name = address_name;
		return this;
	}
	public String getAddress_province() {
		return address_province;
	}
	public OrderBean setAddress_province(String address_province) {
		this.address_province = address_province;
		return this;
	}
	public String getAddress_city() {
		return address_city;
	}
	public OrderBean setAddress_city(String address_city) {
		this.address_city = address_city;
		return this;
	}
	public String getAddress_district() {
		return address_district;
	}
	public OrderBean setAddress_district(String address_district) {
		this.address_district = address_district;
		return this;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public OrderBean setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
		return this;
	}
	public String getAddress_zip_code() {
		return address_zip_code;
	}
	public OrderBean setAddress_zip_code(String address_zip_code) {
		this.address_zip_code = address_zip_code;
		return this;
	}
	public String getAddress_longitude() {
		return address_longitude;
	}
	public OrderBean setAddress_longitude(String address_longitude) {
		this.address_longitude = address_longitude;
		return this;
	}
	public String getAddress_latitude() {
		return address_latitude;
	}
	public OrderBean setAddress_latitude(String address_latitude) {
		this.address_latitude = address_latitude;
		return this;
	}
	public Float getOrder_total_price() {
		return order_total_price;
	}
	public OrderBean setOrder_total_price(Float order_total_price) {
		this.order_total_price = order_total_price;
		return this;
	}
	public Float getOrder_actual_price() {
		return order_actual_price;
	}
	public OrderBean setOrder_actual_price(Float order_actual_price) {
		this.order_actual_price = order_actual_price;
		return this;
	}
	public Float getOrder_refund_price() {
		return order_refund_price;
	}
	public OrderBean setOrder_refund_price(Float order_refund_price) {
		this.order_refund_price = order_refund_price;
		return this;
	}
	public String getOrder_type() {
		return order_type;
	}
	public OrderBean setOrder_type(String order_type) {
		this.order_type = order_type;
		return this;
	}
	public String getOrder_state() {
		return order_state;
	}
	public OrderBean setOrder_state(String order_state) {
		this.order_state = order_state;
		this.order_state_show="refund".equals(order_state)?"已退款":
			"cancel".equals(order_state)?"取消":
				"wait_pay".equals(order_state)?"待付款":
					"wait_send".equals(order_state)?"待发货":
						"wait_receive".equals(order_state)?"待收货":
							"wait_assessment".equals(order_state)?"待评价":
								"refund".equals(order_state)?"售后":
								"end".equals(order_state)?"已完成":"未知";
		return this;
	}
	public String getOrder_state_show() {
		return order_state_show;
	}
	public OrderBean setOrder_state_show(String order_state_show) {
		this.order_state_show = order_state_show;
		return this;
	}
	public String getOrder_remark() {
		return order_remark;
	}
	public OrderBean setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
		return this;
	}
	public Integer getDeduct_integral_value() {
		return deduct_integral_value;
	}
	public OrderBean setDeduct_integral_value(Integer deduct_integral_value) {
		this.deduct_integral_value = deduct_integral_value;
		return this;
	}
	public Float getDeduct_integral_price() {
		return deduct_integral_price;
	}
	public OrderBean setDeduct_integral_price(Float deduct_integral_price) {
		this.deduct_integral_price = deduct_integral_price;
		return this;
	}
	public String getDeduct_integral_percent() {
		return deduct_integral_percent;
	}
	public OrderBean setDeduct_integral_percent(String deduct_integral_percent) {
		this.deduct_integral_percent = deduct_integral_percent;
		return this;
	}
	public String getCustom_remark() {
		return custom_remark;
	}
	public OrderBean setCustom_remark(String custom_remark) {
		this.custom_remark = custom_remark;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public OrderBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public OrderBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public String getCancel_time() {
		return cancel_time;
	}
	public OrderBean setCancel_time(String cancel_time) {
		this.cancel_time = cancel_time;
		return this;
	}
	public String getPay_time() {
		return pay_time;
	}
	public OrderBean setPay_time(String pay_time) {
		this.pay_time = pay_time;
		return this;
	}
	public String getSend_time() {
		return send_time;
	}
	public OrderBean setSend_time(String send_time) {
		this.send_time = send_time;
		return this;
	}
	public String getReceive_time() {
		return receive_time;
	}
	public OrderBean setReceive_time(String receive_time) {
		this.receive_time = receive_time;
		return this;
	}
	public String getAssessment_time() {
		return assessment_time;
	}
	public OrderBean setAssessment_time(String assessment_time) {
		this.assessment_time = assessment_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public OrderBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getPay_way() {
		return pay_way;
	}
	public OrderBean setPay_way(String pay_way) {
		this.pay_way = pay_way;
		this.pay_way_show="member_balance".equals(pay_way)?"余额":
			"alipay".equals(pay_way)?"支付宝":
				"wx".equals(pay_way)?"微信":
					"wx_pub".equals(pay_way)?"微信公众号":
						"wx_pub_qr".equals(pay_way)?"微信公众号扫码":
							"alipay_pc_direct".equals(pay_way)?"支付宝pc":
								"alipay_wap".equals(pay_way)?"支付宝网页支付":
									"wx_wap".equals(pay_way)?"微信网页支付":"未知";
		return this;
	}
	public String getPay_no() {
		return pay_no;
	}
	public OrderBean setPay_no(String pay_no) {
		this.pay_no = pay_no;
		return this;
	}
	public String getPay_charge() {
		return pay_charge;
	}
	public OrderBean setPay_charge(String pay_charge) {
		this.pay_charge = pay_charge;
		return this;
	}
	public String getPing_no() {
		return ping_no;
	}
	public OrderBean setPing_no(String ping_no) {
		this.ping_no = ping_no;
		return this;
	}
	public Integer getGive_integral_value() {
		return give_integral_value;
	}
	public OrderBean setGive_integral_value(Integer give_integral_value) {
		this.give_integral_value = give_integral_value;
		return this;
	}
	public String getLogistics_no() {
		return logistics_no;
	}
	public OrderBean setLogistics_no(String logistics_no) {
		this.logistics_no = logistics_no;
		return this;
	}
	public String getLogistics_name() {
		return logistics_name;
	}
	public OrderBean setLogistics_name(String logistics_name) {
		this.logistics_name = logistics_name;
		return this;
	}
	public String getLogistics_code() {
		return logistics_code;
	}
	public OrderBean setLogistics_code(String logistics_code) {
		this.logistics_code = logistics_code;
		return this;
	}
	public String getLogistics_state() {
		return logistics_state;
	}
	public OrderBean setLogistics_state(String logistics_state) {
		this.logistics_state = logistics_state;
		this.logistics_state_show="not".equals(logistics_state)?"无轨迹":
			"sending".equals(logistics_state)?"在途中":
				"end".equals(logistics_state)?"签收 ":
					"problem".equals(logistics_state)?"问题件":"未知";
		return this;
	}
	public String getLogistics_state_show() {
		return logistics_state_show;
	}
	public OrderBean setLogistics_state_show(String logistics_state_show) {
		this.logistics_state_show = logistics_state_show;
		return this;
	}
	public String getInvoice_state() {
		return invoice_state;
	}
	public OrderBean setInvoice_state(String invoice_state) {
		this.invoice_state = invoice_state;
		this.invoice_state_show="wait_apply".equals(invoice_state)?"等待申请":
			"wait_accept".equals(invoice_state)?"等待审核":
				"accept".equals(invoice_state)?"接受":
					"refuse".equals(invoice_state)?"拒绝":
						"end".equals(invoice_state)?"已开票":"未知";
		return this;
	}
	public String getInvoice_state_show() {
		return invoice_state_show;
	}
	public OrderBean setInvoice_state_show(String invoice_state_show) {
		this.invoice_state_show = invoice_state_show;
		return this;
	}
	public String getInvoice_type() {
		return invoice_type;
	}
	public OrderBean setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
		return this;
	}
	public String getInvoice_rise() {
		return invoice_rise;
	}
	public OrderBean setInvoice_rise(String invoice_rise) {
		this.invoice_rise = invoice_rise;
		return this;
	}
	public String getInvoice_no() {
		return invoice_no;
	}
	public OrderBean setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
		return this;
	}
	public String getInvoice_desc() {
		return invoice_desc;
	}
	public OrderBean setInvoice_desc(String invoice_desc) {
		this.invoice_desc = invoice_desc;
		return this;
	}
	public String getInvoice_price() {
		return invoice_price;
	}
	public OrderBean setInvoice_price(String invoice_price) {
		this.invoice_price = invoice_price;
		return this;
	}
	public String getInvoice_mobile() {
		return invoice_mobile;
	}
	public OrderBean setInvoice_mobile(String invoice_mobile) {
		this.invoice_mobile = invoice_mobile;
		return this;
	}
	public String getInvoice_name() {
		return invoice_name;
	}
	public OrderBean setInvoice_name(String invoice_name) {
		this.invoice_name = invoice_name;
		return this;
	}
	public String getInvoice_province() {
		return invoice_province;
	}
	public OrderBean setInvoice_province(String invoice_province) {
		this.invoice_province = invoice_province;
		return this;
	}
	public String getInvoice_city() {
		return invoice_city;
	}
	public OrderBean setInvoice_city(String invoice_city) {
		this.invoice_city = invoice_city;
		return this;
	}
	public String getInvoice_district() {
		return invoice_district;
	}
	public OrderBean setInvoice_district(String invoice_district) {
		this.invoice_district = invoice_district;
		return this;
	}
	public String getInvoice_address_detail() {
		return invoice_address_detail;
	}
	public OrderBean setInvoice_address_detail(String invoice_address_detail) {
		this.invoice_address_detail = invoice_address_detail;
		return this;
	}
	public String getInvoice_time() {
		return invoice_time;
	}
	public OrderBean setInvoice_time(String invoice_time) {
		this.invoice_time = invoice_time;
		return this;
	}
	public String getStart_time() {
		return start_time;
	}
	public OrderBean setStart_time(String start_time) {
		this.start_time = start_time;
		return this;
	}
	public String getEnd_time() {
		return end_time;
	}
	public OrderBean setEnd_time(String end_time) {
		this.end_time = end_time;
		return this;
	}
	public List<OrderGoodsBean> getOrderGoodsBeans() {
		return orderGoodsBeans;
	}
	public OrderBean setOrderGoodsBeans(List<OrderGoodsBean> orderGoodsBeans) {
		this.orderGoodsBeans = orderGoodsBeans;
		return this;
	}
	public String getSearch_type() {
		return search_type;
	}
	public OrderBean setSearch_type(String search_type) {
		this.search_type = search_type;
		return this;
	}
	public List<OrderLogisticsBean> getOrderLogisticsBeans() {
		return orderLogisticsBeans;
	}
	public OrderBean setOrderLogisticsBeans(List<OrderLogisticsBean> orderLogisticsBeans) {
		this.orderLogisticsBeans = orderLogisticsBeans;
		return this;
	}
	public Integer getMember_group_id() {
		return member_group_id;
	}
	public OrderBean setMember_group_id(Integer member_group_id) {
		this.member_group_id = member_group_id;
		return this;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public OrderBean setGoods_name(String goods_name) {
		this.goods_name = goods_name;
		return this;
	}
}
