package com.live.aksd.bean;

/**
 * @author Created by stone
 * @since 17/9/6
 */

public class RefundReasonBean {


    /**
     * refund_reason_id : 1
     * reason_name : 不想要了
     * create_time : 2016-10-14 11:20:51.0
     * is_delete : 0
     * sort : 1
     */

    private int refund_reason_id;
    private String reason_name;
    private String create_time;
    private String is_delete;
    private String sort;
    private boolean is_select;

    public boolean is_select() {
        return is_select;
    }

    public void setIs_select(boolean is_selectAll) {
        this.is_select = is_selectAll;
    }



    public int getRefund_reason_id() {
        return refund_reason_id;
    }

    public void setRefund_reason_id(int refund_reason_id) {
        this.refund_reason_id = refund_reason_id;
    }

    public String getReason_name() {
        return reason_name;
    }

    public void setReason_name(String reason_name) {
        this.reason_name = reason_name;
    }

    public String getCreate_time() {
        return create_time;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
