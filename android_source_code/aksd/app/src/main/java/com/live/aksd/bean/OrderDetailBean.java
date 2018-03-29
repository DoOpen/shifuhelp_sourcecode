package com.live.aksd.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.bilibili.boxing.model.entity.BaseMedia;

import java.util.List;

/**
 * Created by zhengan88 on 17/6/20.
 */

public class OrderDetailBean implements Parcelable {
    /**
     * id : 2
     * order_id : 4
     * number : 3
     * goods_id : 2
     * kinds_id : 38,40
     * name : 小巧马克杯
     * sale_price : 30.00
     * price : 43.00
     * img : http://bs.tstmobile.com/Uploads/image/goods/20170306/58bcfecaedea9.png
     * thumb : http://bs.tstmobile.com/Uploads/image/goods/20170306/58bcfedf01871.png
     * kinds : 款式,大小
     * kinds_detail : [{"kind":"款式","kind_detail":"红色"},{"kind":"大小","kind_detail":"iPhone6 Plus"}]
     */

    private String id;
    private String order_id;
    private String number;
    private String goods_id;
    private String kinds_id;
    private String name;
    private String sale_price;
    private String price;
    private String img;
    private String thumb;
    private String kinds;
    private float goods_mark;
    private String content;

    public OrderDetailBean() {

    }

    public float getGoods_mark() {
        return goods_mark;
    }

    public void setGoods_mark(float goods_mark) {
        this.goods_mark = goods_mark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private List<KindsDetailBean> kinds_detail;

    public List<BaseMedia> getMedis() {
        return medis;
    }

    public void setMedis(List<BaseMedia> medis) {
        this.medis = medis;
    }

    private List<BaseMedia> medis;

    public OrderDetailBean(Parcel in) {
        id = in.readString();
        order_id = in.readString();
        number = in.readString();
        goods_id = in.readString();
        kinds_id = in.readString();
        name = in.readString();
        sale_price = in.readString();
        price = in.readString();
        img = in.readString();
        thumb = in.readString();
        kinds = in.readString();
    }

    public static final Creator<OrderDetailBean> CREATOR = new Creator<OrderDetailBean>() {
        @Override
        public OrderDetailBean createFromParcel(Parcel in) {
            return new OrderDetailBean(in);
        }

        @Override
        public OrderDetailBean[] newArray(int size) {
            return new OrderDetailBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getKinds_id() {
        return kinds_id;
    }

    public void setKinds_id(String kinds_id) {
        this.kinds_id = kinds_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds;
    }

    public List<KindsDetailBean> getKinds_detail() {
        return kinds_detail;
    }

    public void setKinds_detail(List<KindsDetailBean> kinds_detail) {
        this.kinds_detail = kinds_detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(order_id);
        dest.writeString(number);
        dest.writeString(goods_id);
        dest.writeString(kinds_id);
        dest.writeString(name);
        dest.writeString(sale_price);
        dest.writeString(price);
        dest.writeString(img);
        dest.writeString(thumb);
        dest.writeString(kinds);
    }

    public static class KindsDetailBean {
        /**
         * kind : 款式
         * kind_detail : 红色
         */

        private String kind;
        private String kind_detail;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getKind_detail() {
            return kind_detail;
        }

        public void setKind_detail(String kind_detail) {
            this.kind_detail = kind_detail;
        }
    }

}