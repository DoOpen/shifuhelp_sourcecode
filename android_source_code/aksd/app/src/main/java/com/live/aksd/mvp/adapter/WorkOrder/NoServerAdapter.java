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

public class NoServerAdapter extends RecyclerArrayAdapter<WorkOrderBean> {

    public NoServerAdapter(Context context, List<WorkOrderBean> listBeanList) {
        super(context, listBeanList);
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MallGoodsHolder(parent, viewType);
    }

    public class MallGoodsHolder extends BaseViewHolder<WorkOrderBean> {

        TextView tvContent, tvNote, tvAddress, tvReturn, tvNow;

        public MallGoodsHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_no_server);
            tvContent = $(R.id.tvContent);
            tvNote = $(R.id.tvNote);
            tvAddress = $(R.id.tvAddress);
            tvReturn = $(R.id.tvReturn);
            tvNow = $(R.id.tvNow);

        }

        @Override
        public void setData(final WorkOrderBean data) {

            if (data.getIs_today_order().equals("1")) {
                tvNow.setVisibility(View.VISIBLE);
            } else {
                tvNow.setVisibility(View.GONE);
            }
            tvContent.setText(data.getOrder_subscribe_content());
            if (!TextUtils.isEmpty(data.getOrder_subscribe_note())){tvNote.setText(data.getOrder_subscribe_note());}
            else {
                tvNote.setText(R.string.no_note);
            }
            tvAddress.setText(data.getOrder_address_province() + "-" + data.getOrder_address_city() + "-" + data.getOrder_address_district() + "-" + data.getOrder_address_detail());

        }
    }
}
