package com.project.bean.pay;

import java.security.Timestamp;

import com.sun.javafx.collections.MappingChange.Map;
/**
 * ping++退款对象
 * @author 方林
 *
 */
public class PingRefundBean {
	private String id;//退款对象  id ，由 Ping++ 生成，27 位长度字符串
	private String object;//值为 "refund"
	private String order_no;//退款的订单号，由 Ping++ 生成
	private Integer amount;//退款金额大于 0，单位为对应币种的最小货币单位，例如：人民币为分（如退款金额为 1 元，此处请填 100）
	private boolean succeed;//退款是否成功
	private String status;//退款状态（目前支持三种状态: pending: 处理中; succeeded: 成功; failed: 失败）
	private boolean created;//退款创建的时间，用 Unix 时间戳表示
	private Timestamp time_succeed;//退款成功的时间，用 Unix 时间戳表示
	private String description;//退款详情，最多 255 个 Unicode 字符
	private String failure_code;//退款的错误码，详见 错误 中的错误码
	private String failure_msg;//退款消息的描述
	private Map<String, Object> metadata;//参考 "元数据"文档 
	private String charge;//refund 对象的所在  charge 对象的  id
	private String charge_order_no;//商户订单号，这边返回的是  charge 对象中的  order_no 参数
	private String transaction_no;//支付渠道返回的交易流水号，部分渠道返回该字段为空
	private String funding_source;//微信退款资金来源。取值范围： unsettled_funds ：使用未结算资金退款； recharge_funds ：使用可用余额退款。注：默认值  unsettled_funds ，该参数仅适用于所有微信渠道老资金流商户使用，包括  wx ，  wx_pub ，  wx_pub_qr ，  wx_lite ，  wx_wap 五个渠道；新资金流退款资金默认从基本账户中扣除。该参数仅在请求退款，传入该字段时返回
	public String getId() {
		return id;
	}
	public PingRefundBean setId(String id) {
		this.id = id;
		return this;
	}
	public String getObject() {
		return object;
	}
	public PingRefundBean setObject(String object) {
		this.object = object;
		return this;
	}
	public String getOrder_no() {
		return order_no;
	}
	public PingRefundBean setOrder_no(String order_no) {
		this.order_no = order_no;
		return this;
	}
	public Integer getAmount() {
		return amount;
	}
	public PingRefundBean setAmount(Integer amount) {
		this.amount = amount;
		return this;
	}
	public boolean getSucceed() {
		return succeed;
	}
	public PingRefundBean setSucceed(boolean succeed) {
		this.succeed = succeed;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public PingRefundBean setStatus(String status) {
		this.status = status;
		return this;
	}
	public boolean isCreated() {
		return created;
	}
	public PingRefundBean setCreated(boolean created) {
		this.created = created;
		return this;
	}
	public Timestamp getTime_succeed() {
		return time_succeed;
	}
	public PingRefundBean setTime_succeed(Timestamp time_succeed) {
		this.time_succeed = time_succeed;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public PingRefundBean setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getFailure_code() {
		return failure_code;
	}
	public PingRefundBean setFailure_code(String failure_code) {
		this.failure_code = failure_code;
		return this;
	}
	public String getFailure_msg() {
		return failure_msg;
	}
	public PingRefundBean setFailure_msg(String failure_msg) {
		this.failure_msg = failure_msg;
		return this;
	}
	public Map<String, Object> getMetadata() {
		return metadata;
	}
	public PingRefundBean setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getCharge() {
		return charge;
	}
	public PingRefundBean setCharge(String charge) {
		this.charge = charge;
		return this;
	}
	public String getCharge_order_no() {
		return charge_order_no;
	}
	public PingRefundBean setCharge_order_no(String charge_order_no) {
		this.charge_order_no = charge_order_no;
		return this;
	}
	public String getTransaction_no() {
		return transaction_no;
	}
	public PingRefundBean setTransaction_no(String transaction_no) {
		this.transaction_no = transaction_no;
		return this;
	}
	public String getFunding_source() {
		return funding_source;
	}
	public PingRefundBean setFunding_source(String funding_source) {
		this.funding_source = funding_source;
		return this;
	}
}
