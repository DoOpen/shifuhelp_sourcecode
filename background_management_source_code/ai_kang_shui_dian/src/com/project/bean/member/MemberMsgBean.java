package com.project.bean.member;

/**
 * 用户消息记录
 * @author 彭方林
 * @date 2018年4月2日
 */
public class MemberMsgBean {
	private Integer msg_id;
	private Integer member_id;//用户id
	private String msg_title;//消息标题
	private String msg_desc;//消息内容
	private String msg_type;//消息类型   order:订单消息  system:系统消息
	private Integer order_id;////订单id
	private String order_no;//订单单号
	private String create_time;//创建时间
	private String update_time;//修改时间
	private String is_delete;//1删除
	private String is_read;//1已读
	public Integer getMsg_id() {
		return msg_id;
	}
	public MemberMsgBean setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public MemberMsgBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public String getMsg_title() {
		return msg_title;
	}
	public MemberMsgBean setMsg_title(String msg_title) {
		this.msg_title = msg_title;
		return this;
	}
	public String getMsg_desc() {
		return msg_desc;
	}
	public MemberMsgBean setMsg_desc(String msg_desc) {
		this.msg_desc = msg_desc;
		return this;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public MemberMsgBean setMsg_type(String msg_type) {
		this.msg_type = msg_type;
		return this;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public MemberMsgBean setOrder_id(Integer order_id) {
		this.order_id = order_id;
		return this;
	}
	public String getOrder_no() {
		return order_no;
	}
	public MemberMsgBean setOrder_no(String order_no) {
		this.order_no = order_no;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public MemberMsgBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public MemberMsgBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public MemberMsgBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getIs_read() {
		return is_read;
	}
	public MemberMsgBean setIs_read(String is_read) {
		this.is_read = is_read;
		return this;
	}
	
}
