package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.AfterSaleDetailBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/1
 */

public class AfterSaleDetailGoodsAdapter extends RecyclerArrayAdapter<AfterSaleDetailBean.GoodsBeanBean> {
    public AfterSaleDetailGoodsAdapter(Context context, List<AfterSaleDetailBean.GoodsBeanBean> orderGoodsList) {
        super(context, orderGoodsList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<AfterSaleDetailBean.GoodsBeanBean> {
        TextView goods_name,goods_norm,goods_price;
        ImageView goods_img;

        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_confirm_order);
            goods_img = $(R.id.goods_img);
            goods_name = $(R.id.goods_name);
            goods_norm = $(R.id.goods_norm);
            goods_price = $(R.id.goods_price);
        }

        @Override
        public void setData(AfterSaleDetailBean.GoodsBeanBean data) {
            super.setData(data);


        }
    }
}
