package com.project.bean.order;

public class KuaidiniaoBean {
	private String EBusinessID;
	private String UpdateTime;
	private boolean Success;
	private String Reason;
	private String EstimatedDeliveryTime;
	public String getEBusinessID() {
		return EBusinessID;
	}
	public KuaidiniaoBean setEBusinessID(String eBusinessID) {
		EBusinessID = eBusinessID;
		return this;
	}
	public String getUpdateTime() {
		return UpdateTime;
	}
	public KuaidiniaoBean setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
		return this;
	}
	public boolean isSuccess() {
		return Success;
	}
	public KuaidiniaoBean setSuccess(boolean success) {
		Success = success;
		return this;
	}
	public String getReason() {
		return Reason;
	}
	public KuaidiniaoBean setReason(String reason) {
		Reason = reason;
		return this;
	}
	public String getEstimatedDeliveryTime() {
		return EstimatedDeliveryTime;
	}
	public KuaidiniaoBean setEstimatedDeliveryTime(String estimatedDeliveryTime) {
		EstimatedDeliveryTime = estimatedDeliveryTime;
		return this;
	}
}
