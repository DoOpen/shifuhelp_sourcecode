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
import com.live.aksd.bean.AfterSaleDetailBean;

import java.util.List;


/**
 * @author Created by stone
 * @since 17/8/23
 */

public class AfterSaleAdapter extends RecyclerArrayAdapter<AfterSaleDetailBean> {


    public AfterSaleAdapter(Context context, List<AfterSaleDetailBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<AfterSaleDetailBean> {


        private TextView order_no, order_state,tv_fare,goods_name,goods_price,num, order_total_price, goods_norm,goods_num;
        private  ImageView goods_img;

        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_after_sale);
            order_no = $(R.id.order_no);
            order_state = $(R.id.order_state);
            goods_img = $(R.id.goods_img);
            goods_name = $(R.id.goods_name);
            goods_norm = $(R.id.goods_norm);
            goods_price = $(R.id.goods_price);
            goods_num = $(R.id.goods_num);
            tv_fare = $(R.id.tv_fare);
            num = $(R.id.num);

            order_total_price = $(R.id.order_total_price);

        }

        @Override
        public void setData(final AfterSaleDetailBean data) {
            super.setData(data);
            order_no.setText("售后编号：" + data.getRefund_no());

            if (data.getRefund_type().equals("money")) {
                //wait_review：等待审核 accept:接受 refuse：拒绝 end:退款成功
                if (data.getRefund_state().equals("wait_review")) {
                    order_state.setText("退款等待审核");
                } else if (data.getRefund_state().equals("accept")) {
                    order_state.setText("退款中");
                } else if (data.getRefund_state().equals("refuse")) {
                    order_state.setText("退款失败");
                } else if (data.getRefund_state().equals("end")) {
                    order_state.setText("退款成功");
                }
            }else{
                //wait_review：等待审核 accept:接受 refuse：拒绝 end:退款成功
                if (data.getRefund_state().equals("wait_review")) {
                    order_state.setText("退货等待审核");
                } else if (data.getRefund_state().equals("accept")) {
                    order_state.setText("退货中");
                } else if (data.getRefund_state().equals("refuse")) {
                    order_state.setText("退货失败");
                } else if (data.getRefund_state().equals("end")) {
                    order_state.setText("退货成功");
                }


            }
            Glide.with(getContext())
                    .load(Constants.BASE_URL+data.getOrderGoodsBean().getGoods_img())
                    .placeholder(R.mipmap.live_default)
                    .into(goods_img);

            goods_name.setText(data.getOrderGoodsBean().getGoods_name());
            goods_norm.setText("规格："+data.getOrderGoodsBean().getSpecification_name());
            goods_price.setText("¥"+data.getOrderGoodsBean().getGoods_price());
            num.setText("x"+data.getRefund_count());
            order_total_price.setText("¥" + data.getRefund_price());
            goods_num.setText("共计" +   data.getRefund_count() + "件商品 总计：");

            //tv_fare.setText("（运费:¥"+ data.getOrderGoodsBean().getExpress_price()+"）");





        }
    }
}
