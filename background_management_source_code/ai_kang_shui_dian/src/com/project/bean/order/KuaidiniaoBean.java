package com.project.bean.order;

/**
 * 快递鸟
 * @author 彭方林
 * @date 2018年4月2日
 */
public class KuaidiniaoBean {
	private String EBusinessID;//商户ID，请在快递鸟我的服务页面查看
	private String UpdateTime;//更新时间 YYYY-MM-DD HH:MM:SS
	private boolean Success;//成功与否
	private String Reason;//失败原因
	private String EstimatedDeliveryTime;//订单预计到货时间yyyy-mm-dd（即将上线）
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
