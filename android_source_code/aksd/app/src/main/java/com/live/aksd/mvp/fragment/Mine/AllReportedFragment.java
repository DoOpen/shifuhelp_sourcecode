package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.live.aksd.R;
import com.live.aksd.bean.ReportedBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Mine.MyReportedAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.AllReportedPresenter;
import com.live.aksd.mvp.view.Mine.IAllReportedView;
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
 * @since 2017/10/16
 */

public class AllReportedFragment extends BaseFragment<IAllReportedView, AllReportedPresenter> implements IAllReportedView {

    @BindView(R.id.easyRecyclerView)
    RecyclerView easyRecyclerView;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshlayout;
    Unbinder unbinder;
    @BindView(R.id.emptyView)
    LinearLayout emptyView;
    private String state;
    private MyReportedAdapter adapter;

    List<ReportedBean> myReportedList;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;

    public static AllReportedFragment newInstance(String state) {
        Bundle args = new Bundle();
        AllReportedFragment fragment = new AllReportedFragment();
        fragment.state = state;
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
        adapter = new MyReportedAdapter(getContext(), myReportedList);
        easyRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startReportedDetailFragment(adapter.getItem(position).getReported_id());
            }
        });

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

    @Override
    public void onResume() {
        super.onResume();
        page = 1;

    }

    private void refresh() {
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("page", page + "");
        switch (state) {
            case "0":
                map.put("reported_state", "wait_audit");
                break;
            case "1":
                map.put("reported_state", "success");
                break;
            case "2":
                map.put("reported_state", "refuse");
                break;

        }
        getPresenter().getReportedList(map);
    }

    @Override
    public void onGetReportedList(List<ReportedBean> data) {
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
    public AllReportedPresenter createPresenter() {
        return new AllReportedPresenter(getApp());
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
