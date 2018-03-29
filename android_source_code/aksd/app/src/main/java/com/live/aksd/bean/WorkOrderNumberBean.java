package com.live.aksd.bean;

/**
 * @author Created by stone
 * @since 2017/10/16
 */

public class WorkOrderNumberBean {


    /**
     * worker_not_service : 1
     * worker_wait_audit : 28
     * worker_complete : 15
     * worker_cancle : 16
     * worker_servicing : 25
     * worker_wait_accept : 0
     * worker_accept : 69
     */

    private String worker_not_service;
    private String worker_wait_audit;
    private String worker_complete;
    private String worker_cancle;
    private String worker_servicing;
    private String worker_wait_accept;
    private String worker_accept;

    public String getWorker_not_service() {
        return worker_not_service;
    }

    public void setWorker_not_service(String worker_not_service) {
        this.worker_not_service = worker_not_service;
    }

    public String getWorker_wait_audit() {
        return worker_wait_audit;
    }

    public void setWorker_wait_audit(String worker_wait_audit) {
        this.worker_wait_audit = worker_wait_audit;
    }

    public String getWorker_complete() {
        return worker_complete;
    }

    public void setWorker_complete(String worker_complete) {
        this.worker_complete = worker_complete;
    }

    public String getWorker_cancle() {
        return worker_cancle;
    }

    public void setWorker_cancle(String worker_cancle) {
        this.worker_cancle = worker_cancle;
    }

    public String getWorker_servicing() {
        return worker_servicing;
    }

    public void setWorker_servicing(String worker_servicing) {
        this.worker_servicing = worker_servicing;
    }

    public String getWorker_wait_accept() {
        return worker_wait_accept;
    }

    public void setWorker_wait_accept(String worker_wait_accept) {
        this.worker_wait_accept = worker_wait_accept;
    }

    public String getWorker_accept() {
        return worker_accept;
    }

    public void setWorker_accept(String worker_accept) {
        this.worker_accept = worker_accept;
    }
}
