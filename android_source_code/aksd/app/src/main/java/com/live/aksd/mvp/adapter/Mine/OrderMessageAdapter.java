package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.UserMessageBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/8/24
 */

public class OrderMessageAdapter extends RecyclerArrayAdapter<UserMessageBean> {


    public OrderMessageAdapter(Context context, List<UserMessageBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<UserMessageBean> {

        TextView tvTime,tvState,tvOrderNo,tvName;
        ImageView tvImg;

        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_order_message);

            tvTime = $(R.id.tvTime);
            tvState = $(R.id.tvState);
            tvOrderNo = $(R.id.tvOrderNo);
            tvImg = $(R.id.tvImg);
            tvName = $(R.id.tvName);

        }

        @Override
        public void setData(UserMessageBean data) {
            super.setData(data);
            tvTime.setText(data.getCreate_time());
            tvState.setText(data.getMsg_type());
            tvName.setText(data.getMsg_desc());
            tvOrderNo.setText("运单号："+data.getOrder_no());


        }
    }
}
