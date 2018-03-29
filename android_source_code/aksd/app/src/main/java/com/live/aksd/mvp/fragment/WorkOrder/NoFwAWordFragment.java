package com.live.aksd.mvp.fragment.WorkOrder;

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
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.WorkOrderBean;
import com.live.aksd.mvp.adapter.WorkOrder.NoServerAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.WordOrder.AWordPresenter;
import com.live.aksd.mvp.view.WordOrder.IAWordView;
import com.live.aksd.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/8/23.
 */

public class NoFwAWordFragment extends BaseFragment<IAWordView, AWordPresenter> implements IAWordView {


    @BindView(R.id.easyRecyclerView)
    RecyclerView easyRecyclerView;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshlayout;
    Unbinder unbinder;
    @BindView(R.id.emptyView)
    LinearLayout emptyView;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<WorkOrderBean> listWaitOrder;
    private NoServerAdapter adapter;
    private int page = 1;


    public static NoFwAWordFragment newInstance() {
        Bundle args = new Bundle();
        NoFwAWordFragment fragment = new NoFwAWordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_currency_list;
    }

    @Override
    public void initUI() {
        listWaitOrder = new ArrayList<>();
        adapter = new NoServerAdapter(context, listWaitOrder);
        adapter.setNotifyOnChange(false);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startWorkOrderDetailsFragment(adapter.getItem(position).getOrder_id(), "0");
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
        page = 1;
        refresh();

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        refresh();
    }

    private void refresh() {
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("type", "worker_accept_not_service");
        map.put("page", page + "");
        getPresenter().getOrderListByState(map);
    }

    @Override
    public void onOrderListByState(List<WorkOrderBean> data) {
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
    public AWordPresenter createPresenter() {
        return new AWordPresenter(getApp());
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        switch (event.getMsg()) {
            case Constants.REFRESH_ORDER:
                refresh();
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
