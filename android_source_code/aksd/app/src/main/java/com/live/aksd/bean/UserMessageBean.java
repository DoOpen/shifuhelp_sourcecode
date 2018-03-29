package com.live.aksd.bean;

/**
 * @author Created by stone
 * @since 17/8/28
 */

public class UserMessageBean {


    /**
     * msg_id : 1
     * member_id : 1
     * msg_desc : 您的订单已发送
     * msg_type : order
     * order_id : 1
     * order_no : 1312312
     * create_time : 2017-03-06 16:17:07.0
     * is_delete : 0
     */

    private int msg_id;
    private String member_id;
    private String msg_desc;
    private String msg_type;
    private String order_id;
    private String order_no;
    private String create_time;
    private String is_delete;

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMsg_desc() {
        return msg_desc;
    }

    public void setMsg_desc(String msg_desc) {
        this.msg_desc = msg_desc;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getCreate_time() {
        return create_time.substring(0,19);
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}
