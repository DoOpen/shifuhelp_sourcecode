package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.WithdrawBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/10/11
 */

public class WithdrawBeanAdapter extends RecyclerArrayAdapter<WithdrawBean> {


    public WithdrawBeanAdapter(Context context, List<WithdrawBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }


    public class ListBeanViewHolder extends BaseViewHolder<WithdrawBean> {
        TextView integral_type, create_time, integral_value;

        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_my_score);
            integral_value = $(R.id.integral_value);
            integral_type = $(R.id.integral_type);
            create_time = $(R.id.create_time);
        }

        @Override
        public void setData(WithdrawBean data) {
            super.setData(data);
            integral_type.setText(data.getWithdrawal_state_show());
            create_time.setText(getContext().getString(R.string.time) + data.getCreate_time());
            integral_value.setText("-" + data.getWithdrawal_price());

        }
    }
}
