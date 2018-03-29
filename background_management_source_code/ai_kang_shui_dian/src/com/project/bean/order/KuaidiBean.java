package com.project.bean.order;

public class KuaidiBean {
	private String ShipperCode;
	private String LogisticCode;
	private KuaidiSenderBean Sender;
	private KuaidiReceiverBean Receiver;
	
	public KuaidiSenderBean getSender() {
		return Sender;
	}
	public KuaidiBean setSender(KuaidiSenderBean sender) {
		Sender = sender;
		return this;
	}
	public KuaidiReceiverBean getReceiver() {
		return Receiver;
	}
	public KuaidiBean setReceiver(KuaidiReceiverBean receiver) {
		Receiver = receiver;
		return this;
	}
	public String getShipperCode() {
		return ShipperCode;
	}
	public KuaidiBean setShipperCode(String shipperCode) {
		ShipperCode = shipperCode;
		return this;
	}
	public String getLogisticCode() {
		return LogisticCode;
	}
	public KuaidiBean setLogisticCode(String logisticCode) {
		LogisticCode = logisticCode;
		return this;
	}
	

}
