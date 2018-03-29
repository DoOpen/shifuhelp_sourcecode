package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.MyCollectionBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/8/23
 */

public class MyCollectionAdapter  extends RecyclerArrayAdapter<MyCollectionBean> {

    public MyCollectionAdapter(Context context, List<MyCollectionBean> objects) {
        super(context, objects);


    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<MyCollectionBean> {

         TextView tv_name,tv_price,tv_buyNum;
                 ImageView  img;
        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_my_collection);
            img = $(R.id.img);
            tv_name = $(R.id.tv_name);
            tv_price = $(R.id.tv_price);
            tv_buyNum = $(R.id.tv_buyNum);
        }

        @Override
        public void setData(MyCollectionBean data) {
            super.setData(data);


            tv_name.setText(data.getGoodsBean().getGoods_name());
            tv_price.setText("¥ " +data.getGoodsBean().getExpress_price());
            tv_buyNum.setText(data.getGoodsBean().getExpress_price()+"人购买");
            Glide.with(getContext())
                    .load(Constants.BASE_URL + data.getGoodsBean().getGoods_url())
                    .placeholder(R.mipmap.live_default)
                    .into(img);
        }
    }
}
