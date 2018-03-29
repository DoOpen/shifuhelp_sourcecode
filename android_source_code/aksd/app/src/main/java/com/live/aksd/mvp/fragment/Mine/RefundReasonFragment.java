package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
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
import com.live.aksd.bean.RefundReasonBean;
import com.live.aksd.mvp.adapter.Mine.SelectReasonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * @author Created by stone
 * @since 17/9/2
 */

public class RefundReasonFragment extends DialogFragment {

    public interface SelectReasonOnclickListener {

        //下一部
        void ok(String orderContent,int reasonId);

    }

    private SelectReasonOnclickListener onclickListener;

    public void setSelectReasonOnclickListener(SelectReasonOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }

    @BindView(R.id.secect_list)
    EasyRecyclerView secect_list;
    Unbinder unbinder;

    private SelectReasonAdapter adapter;
    List<RefundReasonBean> selectReasonList = new ArrayList<>();
    private String reasonName="";
    private int reasonId;

    public static RefundReasonFragment newInstance(List<RefundReasonBean> list) {
        Bundle args = new Bundle();
        RefundReasonFragment fragment = new RefundReasonFragment();
        fragment.selectReasonList = list;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_refund_reason, container, false);
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

        adapter = new SelectReasonAdapter(getContext(), selectReasonList);
        secect_list.setAdapter(adapter);
        secect_list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                for(int i=0;i<adapter.getAllData().size();i++){
                    adapter.getItem(i).setIs_select(false);
                }
                if (adapter.getItem(position).is_select()){
                    adapter.getItem(position).setIs_select(false);
                }else{
                    adapter.getItem(position).setIs_select(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.close, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;
            case R.id.btn_ok:
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (adapter.getItem(i).is_select()){
                        reasonName=adapter.getItem(i).getReason_name();
                        reasonId=adapter.getItem(i).getRefund_reason_id();
                    }
                }
                if (TextUtils.isEmpty(reasonName)){
                    ToastUtils.showToast(getContext().getApplicationContext(),"请选择退款原因");
                    return;
                }
                onclickListener.ok(reasonName,reasonId);
                dismiss();
                break;

        }
    }
}
