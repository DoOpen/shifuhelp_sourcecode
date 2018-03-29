package com.live.aksd.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.aksd.R;
import com.live.aksd.bean.WorkTypeBean;
import com.live.aksd.mvp.adapter.SelectWorkTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2017/11/4
 */

public class WorkTypeFragment extends DialogFragment {

    public interface SelectWorkTypeOnclickListener {

        //下一部
        void ok(List<String> worktype);

    }

    private SelectWorkTypeOnclickListener onclickListener;

    public void setSelectReasonOnclickListener(SelectWorkTypeOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }

    @BindView(R.id.secect_list)
    EasyRecyclerView secect_list;
    Unbinder unbinder;

    private SelectWorkTypeAdapter adapter;
    List<WorkTypeBean> workTypeList = new ArrayList<>();
    private List<String> worktype = new ArrayList<>();

    public static WorkTypeFragment newInstance(List<WorkTypeBean> list) {
        Bundle args = new Bundle();
        WorkTypeFragment fragment = new WorkTypeFragment();
        fragment.workTypeList = list;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_work_type, container, false);
        unbinder = ButterKnife.bind(this, view);
        setLayout();
        initViews();
        return view;
    }

    private void setLayout() {
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawableResource(R.color.black_transparent);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.dimAmount = 0;
        window.setAttributes(lp);
    }


    private void initViews() {

        adapter = new SelectWorkTypeAdapter(getContext(), workTypeList);
        secect_list.setAdapter(adapter);
        secect_list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapter.getItem(position).is_select()) {
                    adapter.getItem(position).setIs_select(false);
                } else {
                    adapter.getItem(position).setIs_select(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
        if (adapter.getAllData().size() != 0) {
            adapter.getItem(0).setIs_select(true);
        }
    }


    @OnClick({R.id.close, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;
            case R.id.btn_ok:

                for (int i = 0; i < adapter.getCount(); i++) {
                    if (adapter.getItem(i).is_select()) {
                        worktype.add(adapter.getItem(i).getType_name());
                    }
                }
                if (worktype.size()==0) {
                    ToastUtils.showToast(getContext().getApplicationContext(), "请选择工种");
                    return;
                }
                onclickListener.ok(worktype);
                dismiss();
                break;

        }
    }
}
