package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.TrainingListBean;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/10/10
 */

public class TrainingListAdapter extends RecyclerArrayAdapter<TrainingListBean> {

    private int max;

    public TrainingListAdapter(Context context, List<TrainingListBean> listBeanList) {
        super(context, listBeanList);
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrainingListHolder(parent, viewType);
    }

    public class TrainingListHolder extends BaseViewHolder<TrainingListBean> {

        private TextView tv_tittle, tv_content, bg_state;
        private ImageView img;

        public TrainingListHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_training_list);
            img = $(R.id.img);
            tv_tittle = $(R.id.tv_tittle);
            tv_content = $(R.id.tv_content);
            bg_state = $(R.id.bg_state);


        }

        @Override
        public void setData(final TrainingListBean data) {
            bg_state.setText(data.getType());
            tv_tittle.setText(data.getTraining_title());
            tv_content.setText(data.getTraining_profile());
            Glide.with(getContext())
                    .load(data.getTraining_img())
                    .placeholder(R.mipmap.live_default)
                    .into(img);
        }
    }
}
