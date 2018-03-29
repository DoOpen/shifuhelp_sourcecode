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
import com.live.aksd.bean.OrderBeanNew;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Mine.MyOrderListAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.AllOrderPresenter;
import com.live.aksd.mvp.view.Mine.IAllOrderView;
import com.live.aksd.util.SpSingleInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhengan88 on 17/6/14.
 */

public class AllOrderFragment extends BaseFragment<IAllOrderView, AllOrderPresenter> implements IAllOrderView {

    @BindView(R.id.easyRecyclerView)
    RecyclerView easyRecyclerView;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshlayout;
    Unbinder unbinder;
    @BindView(R.id.emptyView)
    LinearLayout emptyView;
    private String state;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    private MyOrderListAdapter adapter;
    List<OrderBeanNew> myOrderListBean;
    private boolean isMore;

    private int page = 1;
    private int maxPage = 1;
    //电话

    public static AllOrderFragment newInstance(String state) {
        Bundle args = new Bundle();
        AllOrderFragment fragment = new AllOrderFragment();
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
        myOrderListBean = new ArrayList<>();
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MyOrderListAdapter(getContext(), myOrderListBean);
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                startMyOrgerDetailsFragment(adapter.getAllData().get(position).getOrder_id() + "");
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

    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
    }

    private void refresh() {
        page = 1;
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("page", page + "");
        switch (state) {
            case "0":
                break;
            case "1":
                map.put("order_state", "wait_pay");
                break;
            case "2":
                map.put("order_state", "wait_send");
                break;
            case "3":
                map.put("order_state", "wait_receive");
                break;
            case "4":
                map.put("order_state", "wait_assessment");
                break;
        }

        getPresenter().getOrders(map);
    }

    @Override
    public void onGetOrders(List<OrderBeanNew> data) {
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
    public AllOrderPresenter createPresenter() {
        return new AllOrderPresenter(getApp());
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
