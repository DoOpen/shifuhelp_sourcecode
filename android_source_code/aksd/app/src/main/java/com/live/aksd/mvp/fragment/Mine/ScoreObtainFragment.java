package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.live.aksd.R;
import com.live.aksd.bean.IntegralBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Mine.ScoreObtainAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.ScoreObtainPresenter;
import com.live.aksd.mvp.view.Mine.IScoreObtainView;
import com.live.aksd.util.SpSingleInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/8/23
 */

public class ScoreObtainFragment extends BaseFragment<IScoreObtainView, ScoreObtainPresenter> implements IScoreObtainView {


    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshlayout;
    Unbinder unbinder;
    @BindView(R.id.emptyView)
    LinearLayout emptyView;
    @BindView(R.id.easyRecyclerView)
    RecyclerView easyRecyclerView;
    private int page = 1;


    private ScoreObtainAdapter adapter;
    List<IntegralBean> myReportedList;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static ScoreObtainFragment newIntance() {
        Bundle args = new Bundle();
        ScoreObtainFragment fragment = new ScoreObtainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_currency_list;
    }

    @Override
    public void initUI() {
        myReportedList = new ArrayList<>();
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ScoreObtainAdapter(context, myReportedList);
        easyRecyclerView.setAdapter(adapter);

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

    private void refresh() {
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("state", "add");
        map.put("page", page + "");
        getPresenter().getIntegralGetRecord(map);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
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
    public ScoreObtainPresenter createPresenter() {
        return new ScoreObtainPresenter(getApp());
    }

    @Override
    public void onGetIntegralGetRecord(List<IntegralBean> data) {
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
