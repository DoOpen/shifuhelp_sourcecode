package com.live.aksd.mvp.adapter.WorkOrder;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.WorkOrderBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/1
 */

public class WaitWordOrderAdapter extends RecyclerArrayAdapter<WorkOrderBean> {

    public WaitWordOrderAdapter(Context context, List<WorkOrderBean> listBeanList) {
        super(context, listBeanList);
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MallGoodsHolder(parent, viewType);
    }

    public class MallGoodsHolder extends BaseViewHolder<WorkOrderBean> {

        TextView tv_tittle,tv_describe,tv_location,tv_time,qiangdan;

        public MallGoodsHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.fragment_home_single);
            tv_tittle = $(R.id.tv_tittle);
            tv_describe = $(R.id.tv_describe);
            tv_location = $(R.id.tv_location);
            tv_time = $(R.id.tv_time);
            qiangdan = $(R.id.qiangdan);



        }

        @Override
        public void setData(final WorkOrderBean data) {
            tv_tittle.setText(data.getOrder_subscribe_content());
            tv_describe.setText(data.getOrder_subscribe_note());
            tv_location.setText(data.getOrder_address_province()+"-"+data.getOrder_address_city()+"-"+data.getOrder_address_district()+"-"+data.getOrder_address_detail());
            tv_time.setVisibility(View.GONE);
            qiangdan.setText("接单");
        }
    }
}
