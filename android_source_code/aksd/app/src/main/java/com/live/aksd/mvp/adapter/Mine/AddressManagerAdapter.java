package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.RewareAddressBean;

import java.util.List;

/**
 * Created by lenove on 2017/7/24.
 */

public class AddressManagerAdapter extends RecyclerArrayAdapter<RewareAddressBean> {


    public AddressManagerAdapter(Context context, List<RewareAddressBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAddressManagerHolder(parent, R.layout.address_manager_item);
    }

    public class MyAddressManagerHolder extends BaseViewHolder<RewareAddressBean> {

        private TextView name, phone, isdefault, address, setDefault, editor, delete;

        public MyAddressManagerHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            name = $(R.id.address_manager_item_name);
            phone = $(R.id.address_manager_item_phone);
            isdefault = $(R.id.address_manager_item_default_tv);
            address = $(R.id.address_manager_item_address);
            setDefault = $(R.id.address_manager_item_default);
            editor = $(R.id.address_manager_item_editor);
            delete = $(R.id.address_manager_item_delete);
        }

        @Override
        public void setData(final RewareAddressBean data) {
            name.setText(data.getAddress_name());
            phone.setText(data.getAddress_mobile());
            address.setText(data.getAddress_province() + " " + data.getAddress_city() + " " + data.getAddress_district() + " " + data.getAddress_detail());

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.address_manager_item_default:
                            editorClick.onDefaultClick(data);
                            break;
                        case R.id.address_manager_item_editor:
                            editorClick.onEditorClick(data);
                            break;
                        case R.id.address_manager_item_delete:
                            editorClick.onDeleteClick(data);
                            break;
                    }
                }
            };

            if ("1".equals(data.getIs_default())) {
                isdefault.setVisibility(View.VISIBLE);
                setDefault.setVisibility(View.GONE);
            } else {
                isdefault.setVisibility(View.GONE);
                setDefault.setVisibility(View.VISIBLE);
                setDefault.setOnClickListener(clickListener);
            }
            editor.setOnClickListener(clickListener);
            delete.setOnClickListener(clickListener);


        }
    }

    private editorOnClick editorClick;

    public void setEditorClick(editorOnClick editorClick) {
        this.editorClick = editorClick;
    }

    public interface editorOnClick {
        void onEditorClick(RewareAddressBean bean);

        void onDefaultClick(RewareAddressBean bean);

        void onDeleteClick(RewareAddressBean bean);
    }

}
