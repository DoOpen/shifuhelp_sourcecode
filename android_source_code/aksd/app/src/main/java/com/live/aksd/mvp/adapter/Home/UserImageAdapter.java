package com.live.aksd.mvp.adapter.Home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.util.GlideRoundTransform;

import java.util.List;

/**
 * Created by lenove on 2017/7/24.
 */

public class UserImageAdapter extends RecyclerArrayAdapter<String> {


    public UserImageAdapter(Context context, List<String> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_image_layout);
    }

    public class MyImageHolder extends BaseViewHolder<String> {

        private ImageView img, delete;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            img = $(R.id.item_image);
            delete = $(R.id.delete);

        }

        @Override
        public void setData(final String data) {

            Glide.with(getContext())
                    .load(Constants.BASE_URL + data)
                    .error(R.drawable.live_default).transform(new CenterCrop(getContext()),new GlideRoundTransform(getContext(),12))
                    .into(img);
        }

    }


    public interface OnDeleteClick {

        void onDeleteClick(String data);
    }

    private OnDeleteClick onDeleteClick;

    public void setOnDeleteClick(OnDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }
}
