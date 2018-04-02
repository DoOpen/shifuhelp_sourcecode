package com.project.bean.others;

/**
 * 消息类型
 * @author 彭方林
 * @date 2018年4月2日
 */
public class MsgTypeBean {
	private Integer type_id;
	private String msg_type;//消息类型    cancel_order：取消订单  wait_send：付款成功   refund：退款成功  wait_receive：已发货  register：注册成功  receive：已签收  group_success：拼团成功  member_binding：绑定信息变更
	private String msg_name;//消息名称
	private String msg_desc;//内容
	private String msg_state;//1启用
	private String is_delete;//1删除
	private String create_time;//创建时间
	private String update_time;//修改时间
	public Integer getType_id() {
		return type_id;
	}
	public MsgTypeBean setType_id(Integer type_id) {
		this.type_id = type_id;
		return this;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public MsgTypeBean setMsg_type(String msg_type) {
		this.msg_type = msg_type;
		return this;
	}
	public String getMsg_name() {
		return msg_name;
	}
	public MsgTypeBean setMsg_name(String msg_name) {
		this.msg_name = msg_name;
		return this;
	}
	public String getMsg_desc() {
		return msg_desc;
	}
	public MsgTypeBean setMsg_desc(String msg_desc) {
		this.msg_desc = msg_desc;
		return this;
	}
	public String getMsg_state() {
		return msg_state;
	}
	public MsgTypeBean setMsg_state(String msg_state) {
		this.msg_state = msg_state;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public MsgTypeBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public MsgTypeBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public MsgTypeBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
}
