package com.live.aksd.bean;

import java.util.List;

/**
 * Created by mac1010 on 17/7/28.
 */

public class TracesByJsonBean {


    /**
     * logisticsBean : {"logistics_id":1,"logistics_name":"中通","logistics_pinyin":"zhongtong","is_delete":"0","create_time":"2016-12-20 09:46:59.0"}
     * logisticsDetailBeans : [{"logistics_id":23,"logistics_time":"2017-02-13 12:39:38.0","logistics_context":"[龙岗镇区] [深圳市] [龙岗镇区]的派件已签收 感谢使用中通快递,期待再次为您服务!","cretate_time":"2017-02-22 11:22:59.0","is_delete":"0","logistics_no":"427069144961"},{"logistics_id":24,"logistics_time":"2017-02-13 09:09:00.0","logistics_context":"[龙岗镇区] [深圳市] [龙岗镇区]的黎业刚正在第1次派件 电话:13145964953 请保持电话畅通、耐心等待","cretate_time":"2017-02-22 11:22:59.0","is_delete":"0","logistics_no":"427069144961"},{"logistics_id":25,"logistics_time":"2017-02-12 15:03:46.0","logistics_context":"[龙岗镇区] [深圳市] 快件到达 [龙岗镇区]","cretate_time":"2017-02-22 11:22:59.0","is_delete":"0","logistics_no":"427069144961"},{"logistics_id":26,"logistics_time":"2017-02-12 09:21:37.0","logistics_context":"[深圳中心] [深圳市] 快件离开 [深圳中心]已发往[龙岗镇区]","cretate_time":"2017-02-22 11:22:59.0","is_delete":"0","logistics_no":"427069144961"},{"logistics_id":27,"logistics_time":"2017-02-12 09:05:39.0","logistics_context":"[深圳中心] [深圳市] 快件到达 [深圳中心]","cretate_time":"2017-02-22 11:22:59.0","is_delete":"0","logistics_no":"427069144961"},{"logistics_id":28,"logistics_time":"2017-02-12 04:37:01.0","logistics_context":"[东莞中心] [东莞市] 快件离开 [东莞中心]已发往[深圳中心]","cretate_time":"2017-02-22 11:22:59.0","is_delete":"0","logistics_no":"427069144961"},{"logistics_id":29,"logistics_time":"2017-02-12 04:27:32.0","logistics_context":"[东莞中心] [东莞市] 快件到达 [东莞中心]","cretate_time":"2017-02-22 11:22:59.0","is_delete":"0","logistics_no":"427069144961"},{"logistics_id":30,"logistics_time":"2017-02-11 03:41:24.0","logistics_context":"[郑州中转] [郑州市] 快件离开 [郑州中转]已发往[东莞中心]","cretate_time":"2017-02-22 11:22:59.0","is_delete":"0","logistics_no":"427069144961"},{"logistics_id":31,"logistics_time":"2017-02-11 03:39:29.0","logistics_context":"[郑州] [郑州市] 快件到达 [郑州]","cretate_time":"2017-02-22 11:22:59.0","is_delete":"0","logistics_no":"427069144961"},{"logistics_id":32,"logistics_time":"2017-02-10 22:54:53.0","logistics_context":"[郑州侯寨] [郑州市] 快件离开 [郑州侯寨]已发往[深圳中心]","cretate_time":"2017-02-22 11:22:59.0","is_delete":"0","logistics_no":"427069144961"},{"logistics_id":33,"logistics_time":"2017-02-10 12:48:10.0","logistics_context":"[郑州侯寨] [郑州市] [郑州侯寨]的董事惠已收件 电话:15343827879","cretate_time":"2017-02-22 11:22:59.0","is_delete":"0","logistics_no":"427069144961"}]
     */

    private LogisticsBeanBean logisticsBean;
    private List<LogisticsDetailBeansBean> logisticsDetailBeans;

    public LogisticsBeanBean getLogisticsBean() {
        return logisticsBean;
    }

    public void setLogisticsBean(LogisticsBeanBean logisticsBean) {
        this.logisticsBean = logisticsBean;
    }

    public List<LogisticsDetailBeansBean> getLogisticsDetailBeans() {
        return logisticsDetailBeans;
    }

    public void setLogisticsDetailBeans(List<LogisticsDetailBeansBean> logisticsDetailBeans) {
        this.logisticsDetailBeans = logisticsDetailBeans;
    }

    public static class LogisticsBeanBean {
        /**
         * logistics_id : 1
         * logistics_name : 中通
         * logistics_pinyin : zhongtong
         * is_delete : 0
         * create_time : 2016-12-20 09:46:59.0
         */

        private int logistics_id;
        private String logistics_name;
        private String logistics_pinyin;
        private String is_delete;
        private String create_time;
        private String logistics_state;

        public String getlogistics_state() {
            return logistics_state;
        }

        public void setlogistics_state(String logistics_state) {
            this.logistics_state = logistics_state;
        }

        public int getLogistics_id() {
            return logistics_id;
        }

        public void setLogistics_id(int logistics_id) {
            this.logistics_id = logistics_id;
        }

        public String getLogistics_name() {
            return logistics_name;
        }

        public void setLogistics_name(String logistics_name) {
            this.logistics_name = logistics_name;
        }

        public String getLogistics_pinyin() {
            return logistics_pinyin;
        }

        public void setLogistics_pinyin(String logistics_pinyin) {
            this.logistics_pinyin = logistics_pinyin;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }

    public static class LogisticsDetailBeansBean {
        /**
         * logistics_id : 23
         * logistics_time : 2017-02-13 12:39:38.0
         * logistics_context : [龙岗镇区] [深圳市] [龙岗镇区]的派件已签收 感谢使用中通快递,期待再次为您服务!
         * cretate_time : 2017-02-22 11:22:59.0
         * is_delete : 0
         * logistics_no : 427069144961
         */

        private int logistics_id;
        private String logistics_time;
        private String logistics_context;
        private String cretate_time;
        private String is_delete;
        private String logistics_no;



        public int getLogistics_id() {
            return logistics_id;
        }

        public void setLogistics_id(int logistics_id) {
            this.logistics_id = logistics_id;
        }

        public String getLogistics_time() {
            return logistics_time;
        }

        public void setLogistics_time(String logistics_time) {
            this.logistics_time = logistics_time;
        }

        public String getLogistics_context() {
            return logistics_context;
        }

        public void setLogistics_context(String logistics_context) {
            this.logistics_context = logistics_context;
        }

        public String getCretate_time() {
            return cretate_time;
        }

        public void setCretate_time(String cretate_time) {
            this.cretate_time = cretate_time;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getLogistics_no() {
            return logistics_no;
        }

        public void setLogistics_no(String logistics_no) {
            this.logistics_no = logistics_no;
        }
    }
}
