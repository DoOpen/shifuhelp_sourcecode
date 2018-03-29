package com.live.aksd.mvp.adapter.WorkOrder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.MasterBean;

import java.util.List;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/3
 */

public class ReturnWordAdapter extends RecyclerArrayAdapter<MasterBean> {


    public ReturnWordAdapter(Context context, List<MasterBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<MasterBean> {

        TextView home_class_name;
        ImageView home_class_img;


        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.fragment_worder_return);
           /* home_class_name = $(R.id.home_class_name);
            home_class_img = $(R.id.home_class_img);*/
        }

        @Override
        public void setData(final MasterBean data) {
            super.setData(data);


        }
    }


}
