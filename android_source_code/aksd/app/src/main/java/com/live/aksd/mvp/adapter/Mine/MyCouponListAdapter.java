package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.CouponBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/8/29
 */


public class MyCouponListAdapter extends RecyclerArrayAdapter<CouponBean> {
    public MyCouponListAdapter(Context context, List<CouponBean> listBeanList) {
        super(context, listBeanList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CouponListHolder(parent,viewType);
    }

    public class CouponListHolder extends BaseViewHolder<CouponBean> {

        TextView coupon_name,coupon_price,coupon_full_price,time;
        ImageView coupon_state;


        public CouponListHolder(ViewGroup parent, @LayoutRes int res) {

            super(parent, R.layout.item_coupon_view);
            coupon_name = $(R.id.coupon_name);
            coupon_price = $(R.id.coupon_price);
            coupon_full_price = $(R.id.coupon_full_price);
            time = $(R.id.time);
            coupon_state = $(R.id.coupon_state);

        }

        @Override
        public void setData(CouponBean data) {
            super.setData(data);
            if (data.getCoupon_state().equals("expired")){
                coupon_state.setVisibility(View.VISIBLE);
            }
            coupon_name.setText(data.getCoupon_name());
            coupon_price.setText("¥"+data.getCoupon_price());
            coupon_full_price.setText("满"+data.getCoupon_full_price()+"减"+data.getCoupon_price());
            time.setText("有效期："+data.getStart_time().substring(0,10)+" ~ "+data.getEnd_time().substring(0,10));







        }
    }


    }
