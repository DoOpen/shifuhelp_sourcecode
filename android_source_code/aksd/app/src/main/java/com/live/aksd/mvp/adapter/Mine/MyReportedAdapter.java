package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.ReportedBean;

import java.util.List;


/**
 * @author Created by stone
 * @since 17/8/23
 */

public class MyReportedAdapter extends RecyclerArrayAdapter<ReportedBean> {


    public MyReportedAdapter(Context context, List<ReportedBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<ReportedBean> {
        TextView tvName, tvTime, tvState;

        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_my_reported);
            tvName = $(R.id.tvName);
            tvTime = $(R.id.tvTime);
            tvState = $(R.id.tvState);
        }

        @Override
        public void setData(ReportedBean data) {
            super.setData(data);
            tvName.setText("姓名：" + data.getReported_name());
            tvTime.setText("时间：" + data.getCreate_time());
            tvState.setText(data.getReported_state_show());

        }
    }
}
