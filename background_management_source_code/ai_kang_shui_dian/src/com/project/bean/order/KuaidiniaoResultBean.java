package com.project.bean.order;

import java.util.List;

public class KuaidiniaoResultBean {
	private Integer Count;
	private String EBusinessID;
	private String PushTime;
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
		private String CallBack;
		private String EBusinessID;
		private String LogisticCode;
		private String OrderCode;
		private String Reason;
		private String ShipperCode;
		private String State;
		private boolean Success;
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
			private String AcceptStation;
			private String AcceptTime;
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
