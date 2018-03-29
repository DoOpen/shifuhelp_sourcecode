package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.OrderBeanNew;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/6
 */

public class RefundGoodsAdapter  extends RecyclerArrayAdapter<OrderBeanNew.OrderGoodsBeansBean> {
    public RefundGoodsAdapter(Context context, List<OrderBeanNew.OrderGoodsBeansBean> orderGoodsList) {
        super(context, orderGoodsList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<OrderBeanNew.OrderGoodsBeansBean> {
        private TextView goods_name, goods_norm, goods_price;
        private ImageView goods_img;

        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_refund_goods);
            goods_img = $(R.id.goods_img);
            goods_name = $(R.id.goods_name);
            goods_norm = $(R.id.goods_norm);
            goods_price = $(R.id.goods_price);



        }

        @Override
        public void setData(final OrderBeanNew.OrderGoodsBeansBean data) {
            super.setData(data);
            goods_name.setText(data.getGoods_name());
            goods_price.setText("¥" + data.getGoods_price());
            Glide.with(getContext())
                    .load(data.getGoods_img())
                    .placeholder(R.mipmap.live_default)
                    .into(goods_img);



        }
    }
}
