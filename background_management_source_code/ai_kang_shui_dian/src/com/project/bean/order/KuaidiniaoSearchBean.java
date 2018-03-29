package com.project.bean.order;

import java.util.List;

public class KuaidiniaoSearchBean {
	private String StateEx;
	private String LogisticCode;
	private String ShipperCode;
	private String State;
	private String EBusinessID;
	private String Success;
	private String Location;
	private List<TracesBean> Traces;

	public String getStateEx() {
		return StateEx;
	}

	public KuaidiniaoSearchBean setStateEx(String stateEx) {
		StateEx = stateEx;
		return this;
	}

	public String getLogisticCode() {
		return LogisticCode!=null?LogisticCode.trim():LogisticCode;
	}

	public KuaidiniaoSearchBean setLogisticCode(String logisticCode) {
		LogisticCode = logisticCode;
		return this;
	}

	public String getShipperCode() {
		return ShipperCode;
	}

	public KuaidiniaoSearchBean setShipperCode(String shipperCode) {
		ShipperCode = shipperCode;
		return this;
	}

	public String getState() {
		return State;
	}

	public KuaidiniaoSearchBean setState(String state) {
		State = state;
		return this;
	}

	public String getEBusinessID() {
		return EBusinessID;
	}

	public KuaidiniaoSearchBean setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
		return this;
	}

	public String getSuccess() {
		return Success;
	}

	public KuaidiniaoSearchBean setSuccess(String success) {
		Success = success;
		return this;
	}

	public String getLocation() {
		return Location;
	}

	public KuaidiniaoSearchBean setLocation(String location) {
		Location = location;
		return this;
	}

	public List<TracesBean> getTraces() {
		return Traces;
	}

	public KuaidiniaoSearchBean setTraces(List<TracesBean> traces) {
		Traces = traces;
		return this;
	}

	public class TracesBean {
		private String Action;
		private String AcceptStation;
		private String AcceptTime;
		private String Location;

		public String getAction() {
			return Action;
		}

		public TracesBean setAction(String action) {
			Action = action;
			return this;
		}

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

		public String getLocation() {
			return Location;
		}

		public TracesBean setLocation(String location) {
			Location = location;
			return this;
		}
	}
}
