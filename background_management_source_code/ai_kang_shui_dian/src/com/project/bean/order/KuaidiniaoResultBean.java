package com.project.bean.order;

import java.util.List;

/**
 * 快递鸟返回结果
 * @author 彭方林
 * @date 2018年4月2日
 */
public class KuaidiniaoResultBean {
	private Integer Count;//推送物流单号轨迹个数
	private String EBusinessID;//用户电商ID
	private String PushTime;//推送时间
	private List<DataBean> Data;
	public Integer getCount() {
		return Count;
	}
	public KuaidiniaoResultBean setCount(Integer count) {
		Count = count;
		return this;
	}
	public String getEBusinessID() {
		return EBusinessID;
	}
	public KuaidiniaoResultBean setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
		return this;
	}

	public String getPushTime() {
		return PushTime;
	}
	public KuaidiniaoResultBean setPushTime(String pushTime) {
		PushTime = pushTime;
		return this;
	}
	public List<DataBean> getData() {
		return Data;
	}
	public KuaidiniaoResultBean setData(List<DataBean> data) {
		Data = data;
		return this;
	}
	public class DataBean{
		private String CallBack;//订阅接口的Bk值
		private String EBusinessID;//商户ID
		private String LogisticCode;//快递单号
		private String OrderCode;//订单编号
		private String Reason;//失败原因
		private String ShipperCode;//快递公司编码
		private String State;//物流状态: 0-无轨迹，1-已揽收，2-在途中 201-到达派件城市，3-签收,4-问题件
		private boolean Success;//成功与否：true,false
		private List<TracesBean> Traces;
		public String getCallBack() {
			return CallBack;
		}
		public DataBean setCallBack(String callBack) {
			CallBack = callBack;
			return this;
		}
		public String getEBusinessID() {
			return EBusinessID;
		}
		public DataBean setEBusinessID(String eBusinessID) {
			EBusinessID = eBusinessID;
			return this;
		}
		
		public String getLogisticCode() {
			return LogisticCode;
		}
		public DataBean setLogisticCode(String logisticCode) {
			LogisticCode = logisticCode;
			return this;
		}
		public String getOrderCode() {
			return OrderCode;
		}
		public DataBean setOrderCode(String orderCode) {
			OrderCode = orderCode;
			return this;
		}
		public String getReason() {
			return Reason;
		}
		public DataBean setReason(String reason) {
			Reason = reason;
			return this;
		}
		public String getShipperCode() {
			return ShipperCode;
		}
		public DataBean setShipperCode(String shipperCode) {
			ShipperCode = shipperCode;
			return this;
		}
		public String getState() {
			return State;
		}
		public DataBean setState(String state) {
			State = state;
			return this;
		}
		public boolean isSuccess() {
			return Success;
		}
		public DataBean setSuccess(boolean success) {
			Success = success;
			return this;
		}
		public List<TracesBean> getTraces() {
			return Traces;
		}
		public DataBean setTraces(List<TracesBean> traces) {
			Traces = traces;
			return this;
		}
		public class TracesBean{
			private String AcceptStation;//描述
			private String AcceptTime;//时间
			public String getAcceptStation() {
				return AcceptStation;
			}
			public TracesBean setAcceptStation(String acceptStation) {
				AcceptStation = acceptStation;
				return this;
			}
			public String getAcceptTime() {
				return AcceptTime;
			}
			public TracesBean setAcceptTime(String acceptTime) {
				AcceptTime = acceptTime;
				return this;
			}
		}
	}
}
