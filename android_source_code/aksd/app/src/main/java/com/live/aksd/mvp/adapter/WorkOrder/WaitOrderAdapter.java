package com.live.aksd.mvp.adapter.WorkOrder;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
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

public class WaitOrderAdapter extends RecyclerArrayAdapter<WorkOrderBean> {

    public WaitOrderAdapter(Context context, List<WorkOrderBean> listBeanList) {
        super(context, listBeanList);
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MallGoodsHolder(parent, viewType);
    }

    public class MallGoodsHolder extends BaseViewHolder<WorkOrderBean> {

        TextView tv_tittle, tv_describe, tv_location, qiangdan;

        public MallGoodsHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_wait_order);
            tv_tittle = $(R.id.tv_tittle);
            tv_describe = $(R.id.tv_describe);
            tv_location = $(R.id.tv_location);
            qiangdan = $(R.id.qiangdan);



        }

        @Override
        public void setData(final WorkOrderBean data) {
            tv_tittle.setText(data.getOrder_subscribe_content());
            if (!TextUtils.isEmpty(data.getOrder_subscribe_note())) {
                tv_describe.setText(data.getOrder_subscribe_note());
            }else {
                tv_describe.setText(R.string.no_note);
            }
            tv_location.setText(data.getOrder_address_province() + "-" + data.getOrder_address_city() + "-" + data.getOrder_address_district() + "-" + data.getOrder_address_detail());
            qiangdan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onOrderClickListener!=null){
                    onOrderClickListener.onOrderClick(data.getOrder_id());}
                }
            });

        }
    }

    private  OnOrderClickListener onOrderClickListener;
    public interface  OnOrderClickListener{void onOrderClick(String order_id);};
    public void setOnOrderClickListener(OnOrderClickListener onOrderClickListener){
        this.onOrderClickListener=onOrderClickListener;
    }
}
