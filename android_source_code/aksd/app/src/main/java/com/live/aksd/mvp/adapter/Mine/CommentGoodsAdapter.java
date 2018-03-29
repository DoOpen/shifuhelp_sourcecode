package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.OrderBeanNew;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/8
 */

public class CommentGoodsAdapter  extends RecyclerArrayAdapter<OrderBeanNew.OrderGoodsBeansBean> {
    public CommentGoodsAdapter(Context context, List<OrderBeanNew.OrderGoodsBeansBean> orderGoodsList) {
        super(context, orderGoodsList);
    }

    private CommentGoodsClickListener commentGoodsClickListener;//评价晒单

    public interface CommentGoodsClickListener {
        void OnCommentGoodsClickListener(View v, OrderBeanNew.OrderGoodsBeansBean data);
    }

    public void setcommentGoodsClickListener(CommentGoodsClickListener commentGoodsClickListener) {
        this.commentGoodsClickListener = commentGoodsClickListener;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<OrderBeanNew.OrderGoodsBeansBean> {
       // private TextView goods_name, goods_norm, goods_price, order_state_btn;
        private ImageView goods_img;
        private EditText et_goods_content;

        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_comment_goods);
            goods_img = $(R.id.img);
            et_goods_content = $(R.id.et_goods_content);

          /*  goods_name = $(R.id.goods_name);
            goods_norm = $(R.id.goods_norm);
            goods_price = $(R.id.goods_price);
            order_state_btn = $(R.id.order_state_btn);*/


        }

        @Override
        public void setData(final OrderBeanNew.OrderGoodsBeansBean data) {
            super.setData(data);

            Glide.with(getContext())
                    .load(data.getGoods_img())
                    .placeholder(R.mipmap.live_default)
                    .into(goods_img);
         /*   goods_name.setText(data.getGoods_name());
            goods_price.setText("¥" + data.getGoods_price());
            order_state_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (commentGoodsClickListener!=null){
                        commentGoodsClickListener.OnCommentGoodsClickListener(v, data);}

                }
            });*/

        }
    }
}
