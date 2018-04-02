package com.project.bean.order;

import java.util.List;

/**
 * 快递鸟快递查询信息
 * @author 彭方林
 * @date 2018年4月2日
 */
public class KuaidiniaoSearchBean {
	private String StateEx;//增值物流状态： 1-已揽收， 2-在途中， 201-到达派件城市， 202-派件中， 211-已放入快递柜或驿站， 3-已签收， 311-已取出快递柜或驿站， 4-问题件， 401-发货无信息， 402-超时未签收， 403-超时未更新， 404-拒收（退件）， 412-快递柜或驿站超时未取
	private String LogisticCode;//物流单号
	private String ShipperCode;//快递公司编码
	private String State;//成功与否
	private String EBusinessID;//商户ID
	private String Success;//成功与否
	private String Location;//增值所在城市
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
