package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.TracesByJsonBean;

import java.util.List;



/**
 * Created by mac1010 on 17/7/28.
 */

public class LogisticsInformationAdapter extends RecyclerArrayAdapter<TracesByJsonBean.LogisticsDetailBeansBean> {

    public LogisticsInformationAdapter(Context context, List<TracesByJsonBean.LogisticsDetailBeansBean> listBeanList) {
        super(context,listBeanList);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MallGoodsHolder(parent);
    }

    public class MallGoodsHolder extends BaseViewHolder<TracesByJsonBean.LogisticsDetailBeansBean> {

        private TextView address_tv,time_tv,image_tv;
        private ImageView state_img;

        public MallGoodsHolder(ViewGroup parent) {
            super(parent, R.layout.logistics_info_item);
            address_tv = $(R.id.logistics_address_tv);
            time_tv = $(R.id.logistics_time_tv);
            state_img = $(R.id.logistis_state_img);
            image_tv = $(R.id.logistis_state_tv);
        }

        @Override
        public void setData(TracesByJsonBean.LogisticsDetailBeansBean bean) {
            Log.i("ViewHolder", "position" + getDataPosition());
            address_tv.setText(bean.getLogistics_context());
            time_tv.setText(bean.getLogistics_time());

            if(getDataPosition()==0){
                state_img.setBackgroundResource(R.drawable.zt_h);
                address_tv.setTextColor(getContext().getResources().getColor(R.color.gray_30));
                time_tv.setTextColor(getContext().getResources().getColor(R.color.gray_30));
               // address_tv.setTextColor(Color.parseColor("#EC6B1A"));
            }
        }
    }
}