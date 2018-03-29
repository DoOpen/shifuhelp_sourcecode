package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.WithdrawBean;
import com.live.aksd.mvp.adapter.Mine.WithdrawBeanAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.WithdrawRecordPresenter;
import com.live.aksd.mvp.view.Mine.IWithdrawRecordView;
import com.live.aksd.util.SpSingleInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/10/11
 */

public class WithdrawRecordFragment extends BaseFragment<IWithdrawRecordView, WithdrawRecordPresenter> implements IWithdrawRecordView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshlayout;
    Unbinder unbinder;
    @BindView(R.id.emptyView)
    LinearLayout emptyView;
    @BindView(R.id.easyRecyclerView)
    RecyclerView recycleView;

    private WithdrawBeanAdapter adapter;
    List<WithdrawBean> myWithdrawList;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;

    public static WithdrawRecordFragment newIntance() {
        Bundle args = new Bundle();
        WithdrawRecordFragment fragment = new WithdrawRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.item_scores_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.withdraw_record);
        myWithdrawList = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new WithdrawBeanAdapter(context, myWithdrawList);
        recycleView.setAdapter(adapter);

        SinaRefreshView sinaRefreshView = new SinaRefreshView(context);
        sinaRefreshView.setArrowResource(R.drawable.arrow);
        refreshlayout.setHeaderView(sinaRefreshView);
        refreshlayout.setBottomView(new LoadingView(context));
        refreshlayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = 1;
                refresh();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                page++;
                refresh();
            }
        });
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();

    }

    private void refresh() {
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("page", page + "");
        getPresenter().getApplyCashs(map);
    }


    @Override
    public void showProgress() {
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
    }


    @Override
    public WithdrawRecordPresenter createPresenter() {
        return new WithdrawRecordPresenter(getApp());
    }

    @Override
    public void onGetApplyCashs(List<WithdrawBean> data) {
        refreshlayout.finishRefreshing();
        refreshlayout.finishLoadmore();

        if (page == 1) {
            adapter.clear();
            if (data.size() == 0) {
                emptyView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.GONE);
            }

        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
