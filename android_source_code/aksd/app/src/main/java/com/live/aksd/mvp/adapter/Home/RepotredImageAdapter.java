package com.live.aksd.mvp.adapter.Home;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;

import java.util.List;

/**
 * Created by lenove on 2017/7/24.
 */

public class RepotredImageAdapter extends RecyclerArrayAdapter<String> {


    public RepotredImageAdapter(Context context, List<String> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyImageHolder(parent, R.layout.item_image_reported);
    }

    public class MyImageHolder extends BaseViewHolder<String> {

        private ImageView img,delete;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            img = $(R.id.item_image);
            delete = $(R.id.delete);

        }

        @Override
        public void setData(final String data) {
            if (TextUtils.isEmpty(data)){
                delete.setVisibility(View.GONE);}
            else {
                delete.setVisibility(View.VISIBLE);
            }
            Glide.with(getContext())
                    .load(data)
                    .error(R.mipmap.upload)
                    .into(img);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClick.onDeleteClick(getDataPosition());
                }
            });
        }
    }

    public interface OnDeleteClick {

        void onDeleteClick(int data);
    }

    private OnDeleteClick onDeleteClick;

    public void setOnDeleteClick(OnDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }

}
