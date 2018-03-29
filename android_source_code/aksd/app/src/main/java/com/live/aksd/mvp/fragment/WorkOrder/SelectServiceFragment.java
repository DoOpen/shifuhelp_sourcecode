package com.live.aksd.mvp.fragment.WorkOrder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.aksd.R;
import com.live.aksd.bean.ServiceClasssBean;
import com.live.aksd.mvp.adapter.WorkOrder.SelectServiceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * @author Created by stone
 * @since 17/9/2
 */

public class SelectServiceFragment extends DialogFragment {

    public interface SelectServiceOnclickListener {

        //下一部
        void next(int classParentId, String orderContent);

        //上一步
        void Previous();

        //关闭dialogfragmnt
        void onClose();
    }

    private SelectServiceOnclickListener onclickListener;

    public void setSelectServiceOnclickListener(SelectServiceOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }


    @BindView(R.id.secect_list)
    RecyclerView secect_list;
    @BindView(R.id.secect_list2)
    RecyclerView secect_list2;
    @BindView(R.id.ll_rv)
    RelativeLayout ll_rv;

    Unbinder unbinder;

    private SelectServiceAdapter adapter;
    List<ServiceClasssBean> selectServiceList = new ArrayList<>();
    List<String> chooseClasss = new ArrayList<>();
    private int classId = 999;
    private String className;
    private int itemPosition;

    public static SelectServiceFragment newInstance(List<ServiceClasssBean> list) {
        Bundle args = new Bundle();
        SelectServiceFragment fragment = new SelectServiceFragment();
        fragment.selectServiceList = list;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_service, container, false);
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


    public RecyclerView getRecycleview() {
        return secect_list;
    }

    public SelectServiceAdapter getAdapter() {
        if (adapter == null) {
            return adapter = new SelectServiceAdapter(context, selectServiceList);
        } else {
            return adapter;
        }

    }


    private void initViews() {
        if (adapter == null) {
            adapter = new SelectServiceAdapter(context, selectServiceList);
        }


        secect_list.setAdapter(adapter);
        secect_list.setLayoutManager(new GridLayoutManager(getContext(), 3));
        secect_list2.setAdapter(adapter);
        secect_list2.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showAnimation();
                for (int i = 0; i < adapter.getCount(); i++) {
                    adapter.getItem(i).setIs_select(false);
                }
                adapter.getItem(position).setIs_select(true);
                adapter.notifyDataSetChanged();
                classId = adapter.getItem(position).getClass_id();
                className = adapter.getItem(position).getClass_name();
                onclickListener.next(classId, className);


            }
        });


    }

    public void showAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.left_out);
        secect_list.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.left_in);
                secect_list2.startAnimation(animation2);
                animation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //ll_rv.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }

            @Override
            public void onAnimationEnd(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @OnClick({R.id.close, R.id.btn_next, R.id.back,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                onclickListener.onClose();
                dismiss();
                break;
            case R.id.btn_next:
                if (classId == 999) {
                    ToastUtils.showToast(getContext().getApplicationContext(), "请选择服务类别");
                    return;
                }
                onclickListener.next(classId, className);
                classId = 999;

                break;
            case R.id.back:
                chooseClasss.clear();
                onclickListener.Previous();
                break;
        }
    }
}
