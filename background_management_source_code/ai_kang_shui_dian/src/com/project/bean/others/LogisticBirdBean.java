package com.project.bean.others;

import java.util.List;

public class LogisticBirdBean {
	private String EBusinessID;
	private String OrderCode;
	private String ShipperCode;
	private String LogisticCode;
	private boolean Success;
	private String Reason;
	private String State;
	private List<Traces> Traces;
	public String getEBusinessID() {
		return EBusinessID;
	}
	public LogisticBirdBean setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
		return this;
	}
	public String getOrderCode() {
		return OrderCode;
	}
	public LogisticBirdBean setOrderCode(String orderCode) {
		OrderCode = orderCode;
		return this;
	}
	public String getShipperCode() {
		return ShipperCode;
	}
	public LogisticBirdBean setShipperCode(String shipperCode) {
		ShipperCode = shipperCode;
		return this;
	}
	public String getLogisticCode() {
		return LogisticCode;
	}
	public LogisticBirdBean setLogisticCode(String logisticCode) {
		LogisticCode = logisticCode;
		return this;
	}
	public boolean isSuccess() {
		return Success;
	}
	public LogisticBirdBean setSuccess(boolean success) {
		Success = success;
		return this;
	}
	public String getReason() {
		return Reason;
	}
	public LogisticBirdBean setReason(String reason) {
		Reason = reason;
		return this;
	}
	public String getState() {
		return State;
	}
	public LogisticBirdBean setState(String state) {
		State = state;
		return this;
	}
	public List<Traces> getTraces() {
		return Traces;
	}
	public LogisticBirdBean setTraces(List<Traces> traces) {
		Traces = traces;
		return this;
	}
}
class Traces{
	private String AcceptTime;
	private String AcceptStation;
	private String Remark;
	public String getAcceptTime() {
		return AcceptTime;
	}
	public Traces setAcceptTime(String acceptTime) {
		AcceptTime = acceptTime;
		return this;
	}
	public String getAcceptStation() {
		return AcceptStation;
	}
	public Traces setAcceptStation(String acceptStation) {
		AcceptStation = acceptStation;
		return this;
	}
	public String getRemark() {
		return Remark;
	}
	public Traces setRemark(String remark) {
		Remark = remark;
		return this;
	}
}
