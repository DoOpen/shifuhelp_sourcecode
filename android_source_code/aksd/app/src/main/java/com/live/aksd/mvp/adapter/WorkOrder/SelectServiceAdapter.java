package com.live.aksd.mvp.adapter.WorkOrder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.ServiceClasssBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/4
 */

public class SelectServiceAdapter extends RecyclerArrayAdapter<ServiceClasssBean> {

    public SelectServiceAdapter(Context context, List<ServiceClasssBean> objects) {
        super(context, objects);


    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<ServiceClasssBean> {

        TextView tv_class;

        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_select_service);
            tv_class = $(R.id.tv_class);

        }

        @Override
        public void setData(final ServiceClasssBean data) {
            super.setData(data);
            if (data.is_select()){
                tv_class.setTextColor(getContext().getResources().getColor(R.color.bg_4));
                tv_class.setBackgroundResource(R.drawable.setbar_zz);
            }else{
                tv_class.setTextColor(getContext().getResources().getColor(R.color.tx_4));
                tv_class.setBackgroundResource(R.drawable.setbar_select_class);
            }
            tv_class.setText(data.getClass_name());


        }
    }
}
