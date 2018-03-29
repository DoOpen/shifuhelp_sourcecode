package com.live.aksd.mvp.adapter.Home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.StarMemberBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/3
 */

public class MasterAdapter extends RecyclerArrayAdapter<StarMemberBean> {


    public MasterAdapter(Context context, List<StarMemberBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBeanViewHolder(parent);
    }

    public class ListBeanViewHolder extends BaseViewHolder<StarMemberBean> {

        private TextView tv_name, tv_worktype;
        private CircleImageView ivAvatar;
        public ListBeanViewHolder(ViewGroup parent) {
            super(parent, R.layout.fragment_home_master);
            ivAvatar = $(R.id.ivAvatar);
            tv_name = $(R.id.tv_name);
            tv_worktype = $(R.id.tv_worktype);
        }
        @Override
        public void setData(final StarMemberBean data) {
            super.setData(data);
            Glide.with(getContext())
                    .load(Constants.BASE_URL + data.getMember_head_image())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.logo)
                    .into(ivAvatar);
            tv_name.setText(data.getMember_real_name());
            tv_worktype.setText(data.getMember_work_type());
        }
    }

}
