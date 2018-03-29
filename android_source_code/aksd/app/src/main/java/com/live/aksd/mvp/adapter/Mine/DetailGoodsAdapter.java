package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.OrderBean;
import com.live.aksd.bean.OrderBeanNew;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/8/31
 */

public class DetailGoodsAdapter extends RecyclerArrayAdapter<OrderBean.OrderGoodsBeansBean> {
    public DetailGoodsAdapter(Context context, List<OrderBean.OrderGoodsBeansBean> orderGoodsList) {
        super(context, orderGoodsList);
    }

    private RefundGoodsClickListener refundGoodsClickListener;//申请退款or退货

    public interface RefundGoodsClickListener {
        void OnrefundGoodsClickListener(View v, OrderBean.OrderGoodsBeansBean data);
    }

    public void setrefundGoodsClickListener(RefundGoodsClickListener refundGoodsClickListener) {
        this.refundGoodsClickListener = refundGoodsClickListener;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<OrderBean.OrderGoodsBeansBean> {
        private TextView goods_name, goods_norm, goods_price, order_state_btn, num;
        private ImageView goods_img;
        private RelativeLayout rl_order_state_btn;

        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_confirm_order_new);
            goods_img = $(R.id.goods_img);
            goods_name = $(R.id.goods_name);
            goods_norm = $(R.id.goods_norm);

            goods_price = $(R.id.goods_price);
            num = $(R.id.num);
            order_state_btn = $(R.id.order_state_btn);
            rl_order_state_btn = $(R.id.rl_order_state_btn);


        }

        @Override
        public void setData(final OrderBean.OrderGoodsBeansBean data) {
            super.setData(data);
        /*    int refund_num = 0;
            if (!TextUtils.isEmpty(data.getrefund_num())) {
                refund_num = Integer.valueOf(data.getrefund_num());
            } else {
                refund_num = 0;
            }
            if (data.getGoods_num() - refund_num == 0) {*/

            if (data.getIs_refund().equals("1")) {
                rl_order_state_btn.setVisibility(View.VISIBLE);
                order_state_btn.setText("已申请");

            } else {

                if ("wait_send".equals(data.getOrder_state())) {
                    rl_order_state_btn.setVisibility(View.VISIBLE);
                    order_state_btn.setText("申请退款");
                } else if ("wait_assessment".equals(data.getOrder_state())) {
                    rl_order_state_btn.setVisibility(View.VISIBLE);
                    order_state_btn.setText("申请退货");

                }
                order_state_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (refundGoodsClickListener != null) {
                            refundGoodsClickListener.OnrefundGoodsClickListener(v, data);
                        }

                    }
                });
            }
            goods_norm.setText("规格：" + data.getSpecification_names());
            goods_name.setText(data.getGoods_name());
            goods_price.setText("¥" + data.getSpecification_price());
            num.setText("X" + data.getGoods_num());
            Glide.with(getContext())
                    .load(Constants.BASE_URL + data.getGoods_img())
                    .placeholder(R.mipmap.live_default)
                    .into(goods_img);


        }
    }
}
