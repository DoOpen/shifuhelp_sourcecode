package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.OrderBeanNew;
import com.live.aksd.util.SizeUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengan88 on 17/6/14.
 */

public class MyOrderListAdapter extends RecyclerArrayAdapter<OrderBeanNew> {

    private int max;

    private RecyclerViewClickListener recyclerViewClickListener;

    public interface RecyclerViewClickListener {
        void OnRecyclerViewClickListener(int id);
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    public MyOrderListAdapter(Context context, List<OrderBeanNew> listBeanList) {
        super(context, listBeanList);
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MallGoodsHolder(parent, viewType);
    }

    public class MallGoodsHolder extends BaseViewHolder<OrderBeanNew> {

        private TextView order_no, order_state, order_total_price, goods_num, tv_fare;
        private RecyclerView goods_list;

        public MallGoodsHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_my_order_list);
            order_no = $(R.id.order_no);
            order_state = $(R.id.order_state);
            goods_list = $(R.id.goods_list);
            goods_list.setLayoutManager(new LinearLayoutManager(getContext()));

            goods_num = $(R.id.goods_num);
            tv_fare = $(R.id.tv_fare);
            order_total_price = $(R.id.order_total_price);


        }

        @Override
        public void setData(final OrderBeanNew person) {
            max = 0;
            order_no.setText("订单号:" + person.getOrder_no());
            tv_fare.setText("（运费:¥" + person.getExpress_price() + "）");

            if (person.getOrder_state().equals("wait_pay")) {
                order_state.setText("等待付款");
            } else if (person.getOrder_state().equals("wait_send")) {
                order_state.setText("等待发货");
            } else if (person.getOrder_state().equals("wait_receive")) {
                order_state.setText("等待收货");
            } else if (person.getOrder_state().equals("wait_assessment")) {
                order_state.setText("等待评价");
            } else if (person.getOrder_state().equals("end")) {
                order_state.setText("已完成");
            } else {
                order_state.setText("交易已关闭");
            }

            goods_list.setAdapter(new CommonAdapter<OrderBeanNew.OrderGoodsBeansBean>(getContext(), R.layout.item_confirm_order, person.getOrderGoodsBeans()) {
                @Override
                protected void convert(ViewHolder holder, OrderBeanNew.OrderGoodsBeansBean bean, int position) {

                    List<String> kinds = new ArrayList<>();
                    for (int i = 0; person.getOrderGoodsBeans().size() > i; i++) {
                        kinds.add(bean.getSpecification_names());
                    }
                    if (kinds.size() > 0)
                        holder.setText(R.id.goods_norm, "规格：" +bean.getSpecification_names() );
                    holder.setText(R.id.goods_name, bean.getGoods_name());
                    holder.setText(R.id.goods_price, "¥" + bean.getSpecification_price());
                    holder.setText(R.id.num, "x" + bean.getGoods_num());
                    Glide.with(getContext())
                            .load(Constants.BASE_URL + bean.getGoods_img())
                            .placeholder(R.mipmap.live_default)
                            .into((ImageView) holder.getView(R.id.goods_img));
                }

            });
            for (int i = 0; i < person.getOrderGoodsBeans().size(); i++) {
                max = max + person.getOrderGoodsBeans().get(i).getGoods_num();
            }
            goods_num.setText("共计" + max + "件商品 总计：");
            order_total_price.setText("¥ " + person.getOrder_total_price());

         /*   goods_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewClickListener != null) {
                        recyclerViewClickListener.OnRecyclerViewClickListener(person.getOrder_id());
                    }

                }
            });*/


        }
    }
}
