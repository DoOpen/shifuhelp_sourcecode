package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.IntegralBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/12
 */

public class ScoreObtainAdapter extends RecyclerArrayAdapter<IntegralBean> {


    public ScoreObtainAdapter(Context context, List<IntegralBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<IntegralBean> {
        TextView integral_type,create_time,integral_value;

        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_my_score);
            integral_value = $(R.id.integral_value);
            integral_type = $(R.id.integral_type);
            create_time = $(R.id.create_time);
        }

        @Override
        public void setData(IntegralBean data) {
            super.setData(data);
            integral_type.setText( data.getIntegral_type_show());
            create_time.setText("时间："+data.getCreate_time());
            integral_value.setText("+"+data.getIntegral_value());

        }
    }
}
