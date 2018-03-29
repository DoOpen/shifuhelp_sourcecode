package com.live.aksd.mvp.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.WorkTypeBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2017/11/4
 */

public class SelectWorkTypeAdapter  extends RecyclerArrayAdapter<WorkTypeBean> {
    public SelectWorkTypeAdapter(Context context, List<WorkTypeBean> objects) {
        super(context, objects);


    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<WorkTypeBean> {

        TextView reason_name;
        ImageView choose_ok;

        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_select_refundreason);
            reason_name = $(R.id.reason_name);
            choose_ok = $(R.id.choose_ok);
        }

        @Override
        public void setData(final WorkTypeBean data) {
            super.setData(data);
            reason_name.setText(data.getType_name());
          if (data.is_select()){
                choose_ok.setBackgroundResource(R.drawable.xk_h);
            }else{
                choose_ok.setBackgroundResource(R.drawable.xk);
            }


        }
    }
}
