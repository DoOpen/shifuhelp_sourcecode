package com.project.bean.pay;

import java.util.HashMap;
import java.util.List;

/**
 * ping++支付成功回调对象
 * @author 方林
 *
 */
public class PingChargeBean {
	private String id;//由 Ping++ 生成的支付对象 ID， 27 位字符串
	private Long created;//支付创建时的 Unix 时间戳
	private boolean livemode;//是否处于  live 模式
	private String type;//类型
	private String object;
	private String request;
	private String pending_webhooks;
	private DataBean data;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getCreated() {
		return created;
	}
	public void setCreated(Long created) {
		this.created = created;
	}
	public boolean isLivemode() {
		return livemode;
	}
	public void setLivemode(boolean livemode) {
		this.livemode = livemode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public DataBean getData() {
		return data;
	}
	public void setData(DataBean data) {
		this.data = data;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getPending_webhooks() {
		return pending_webhooks;
	}
	public void setPending_webhooks(String pending_webhooks) {
		this.pending_webhooks = pending_webhooks;
	}
	public static class DataBean{
		private ObjectBean object;
		public ObjectBean getObject() {
			return object;
		}
		public void setObject(ObjectBean object) {
			this.object = object;
		}
		public static class ObjectBean{
			private String id;//由 Ping++ 生成的支付对象 ID， 27 位字符串
			private String object;//值为 "charge"
			private Long created;//支付创建时的 Unix 时间戳
			private boolean livemode;//是否处于  live 模式
			private String type;//支付状态:成功/失败
			private boolean paid;//是否已付款
			private boolean refunded;//是否存在退款信息，无论退款是否成功
			private boolean reversed;//订单是否撤销
			private String app;//支付使用的  app 对象的  id 
			private String channel;//支付使用的第三方支付渠道
			private String order_no;//商户订单号，适配每个渠道对此参数的要求，必须在商户系统内唯一。( alipay 、 alipay_wap 、  alipay_qr 、 alipay_scan 、 alipay_pc_direct : 1-64 位，可包含字母、数字、下划线；  wx : 2-32 位； wx_pub_scan :1-32 位的数字和字母组合；  bfb : 1-20 位；  upacp : 8-40 位；  yeepay_wap :1-50 位；  jdpay_wap : 1-30 位；  qpay :1-30 位； cmb_wallet : 6-32 位的数字和字母组合，一天内不能重复，订单日期+订单号唯一定位一笔订单，示例: 20170808test01)。注：推荐使用 8-20 位，要求数字或字母，不允许特殊字符
			private String client_ip;//发起支付请求客户端的 IP 地址，格式为 IPv4 整型，如 127.0.0.1
			private Integer amount;//订单总金额（必须大于 0），单位为对应币种的最小货币单位，人民币为分。如订单总金额为 1 元， amount 为 100，么么贷商户请查看申请的借贷金额范围
			private Integer amount_settle;//清算金额，单位为对应币种的最小货币单位，人民币为分
			private String currency;//3 位 ISO 货币代码，人民币为  cny
			private String subject;//商品标题，该参数最长为 32 个 Unicode 字符。银联全渠道（ upacp / upacp_wap ）限制在 32 个字节；支付宝部分渠道不支持特殊字符
			private String body;//商品描述信息，该参数最长为 128 个 Unicode 字符。 yeepay_wap 对于该参数长度限制为 100 个 Unicode 字符；支付宝部分渠道不支持特殊字符
			private HashMap<String, Object> extra;//特定渠道发起交易时需要的额外参数，以及部分渠道支付成功返回的额外参数，详细参考 <<支付渠道 extra 参数说明>>
			private Long time_paid;//订单支付完成时的 Unix 时间戳。（银联支付成功时间为接收异步通知的时间）
			private Long time_expire;//订单失效时的 Unix 时间戳。时间范围在订单创建后的 1 分钟到 15 天，默认为 1 天，创建时间以 Ping++ 服务器时间为准。 微信对该参数的有效值限制为 2 小时内；银联对该参数的有效值限制为 1 小时内
			private Long time_settle;//订单清算时间，用 Unix 时间戳表示。（暂不生效
			private String transaction_no;//支付渠道返回的交易流水号
			private Refunds refunds;//退款详情列表
			private Integer amount_refunded;//已退款总金额，单位为对应币种的最小货币单位，例如：人民币为分
			private String failure_code;//订单的错误码，详见 <<错误>> 中的错误码描述
			private String failure_msg;//订单的错误消息的描述
			private HashMap<String, Object> metadata;//详见 <<元数据>>
			private CredentialBean credential;//支付凭证，用于客户端发起支付
			private DescriptionBean description;//订单附加说明，最多 255 个 Unicode 字符
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getString() {
				return object;
			}
			public void setString(String object) {
				this.object = object;
			}
			public Long getCreated() {
				return created;
			}
			public void setCreated(Long created) {
				this.created = created;
			}
			public boolean isLivemode() {
				return livemode;
			}
			public void setLivemode(boolean livemode) {
				this.livemode = livemode;
			}
			public String getObject() {
				return object;
			}
			public void setObject(String object) {
				this.object = object;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public boolean isPaid() {
				return paid;
			}
			public void setPaid(boolean paid) {
				this.paid = paid;
			}
			public boolean isRefunded() {
				return refunded;
			}
			public void setRefunded(boolean refunded) {
				this.refunded = refunded;
			}
			public boolean isReversed() {
				return reversed;
			}
			public void setReversed(boolean reversed) {
				this.reversed = reversed;
			}
			public String getApp() {
				return app;
			}
			public void setApp(String app) {
				this.app = app;
			}
			public String getChannel() {
				return channel;
			}
			public void setChannel(String channel) {
				this.channel = channel;
			}
			public String getOrder_no() {
				return order_no;
			}
			public void setOrder_no(String order_no) {
				this.order_no = order_no;
			}
			public String getClient_ip() {
				return client_ip;
			}
			public void setClient_ip(String client_ip) {
				this.client_ip = client_ip;
			}
			public Integer getAmount() {
				return amount;
			}
			public void setAmount(Integer amount) {
				this.amount = amount;
			}
			public Integer getAmount_settle() {
				return amount_settle;
			}
			public void setAmount_settle(Integer amount_settle) {
				this.amount_settle = amount_settle;
			}
			public String getCurrency() {
				return currency;
			}
			public void setCurrency(String currency) {
				this.currency = currency;
			}
			public String getSubject() {
				return subject;
			}
			public void setSubject(String subject) {
				this.subject = subject;
			}
			public String getBody() {
				return body;
			}
			public void setBody(String body) {
				this.body = body;
			}
			public HashMap<String, Object> getExtra() {
				return extra;
			}
			public void setExtra(HashMap<String, Object> extra) {
				this.extra = extra;
			}
			public Long getTime_paid() {
				return time_paid;
			}
			public void setTime_paid(Long time_paid) {
				this.time_paid = time_paid;
			}
			public Long getTime_expire() {
				return time_expire;
			}
			public void setTime_expire(Long time_expire) {
				this.time_expire = time_expire;
			}
			public Long getTime_settle() {
				return time_settle;
			}
			public void setTime_settle(Long time_settle) {
				this.time_settle = time_settle;
			}
			public String getTransaction_no() {
				return transaction_no;
			}
			public void setTransaction_no(String transaction_no) {
				this.transaction_no = transaction_no;
			}
			public Refunds getRefunds() {
				return refunds;
			}
			public void setRefunds(Refunds refunds) {
				this.refunds = refunds;
			}
			public Integer getAmount_refunded() {
				return amount_refunded;
			}
			public void setAmount_refunded(Integer amount_refunded) {
				this.amount_refunded = amount_refunded;
			}
			public String getFailure_code() {
				return failure_code;
			}
			public void setFailure_code(String failure_code) {
				this.failure_code = failure_code;
			}
			public String getFailure_msg() {
				return failure_msg;
			}
			public void setFailure_msg(String failure_msg) {
				this.failure_msg = failure_msg;
			}
			public HashMap<String, Object> getMetadata() {
				return metadata;
			}
			public void setMetadata(HashMap<String, Object> metadata) {
				this.metadata = metadata;
			}
			public CredentialBean getCredential() {
				return credential;
			}
			public void setCredential(CredentialBean credential) {
				this.credential = credential;
			}
			public DescriptionBean getDescription() {
				return description;
			}
			public void setDescription(DescriptionBean description) {
				this.description = description;
			}

			/**
			 * 存放Charge关联的Refund容器
			 * @author 方林
			 *
			 */
			public static class Refunds{
				private String object;
				private String url;
				private boolean has_more;
				private List<PingRefundBean> data;
				public String getString() {
					return object;
				}
				public void setString(String object) {
					this.object = object;
				}
				public String getUrl() {
					return url;
				}
				public void setUrl(String url) {
					this.url = url;
				}
				public boolean isHas_more() {
					return has_more;
				}
				public void setHas_more(boolean has_more) {
					this.has_more = has_more;
				}
				public String getObject() {
					return object;
				}
				public void setObject(String object) {
					this.object = object;
				}
				public List<PingRefundBean> getData() {
					return data;
				}
				public void setData(List<PingRefundBean> data) {
					this.data = data;
				}
			}
			public static class CredentialBean{}
			public static class DescriptionBean{};
		}
	}
}
