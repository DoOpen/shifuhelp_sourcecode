package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.UserMessageBean;
import com.live.aksd.mvp.adapter.Mine.OrderMessageAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.OrderMessagePresenter;
import com.live.aksd.mvp.view.Mine.IOrderMessageView;
import com.live.aksd.util.SpSingleInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 17/8/24
 */

public class OrderMessageFragment extends BaseFragment<IOrderMessageView, OrderMessagePresenter> implements IOrderMessageView {

    View loadMore;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    private OrderMessageAdapter adapter;
    List<UserMessageBean> orderMessageList;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private int page = 1;

    public static OrderMessageFragment newIntance() {
        Bundle args = new Bundle();
        OrderMessageFragment fragment = new OrderMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_message;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.order_message);
        orderMessageList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new OrderMessageAdapter(context, orderMessageList);
        recyclerView.setAdapter(adapter);

        //下拉刷新
        recyclerView.setRefreshingColorResources(R.color.colorPrimary);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                refresh();
            }
        });
        //上拉加载
        adapter.setMore(loadMore, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                if (loadMore != null) {
                    loadMore.setVisibility(View.VISIBLE);
                }
                page++;
                refresh();
            }
            @Override
            public void onMoreClick() {

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
        map.put("msg_type", "order");
        map.put("page", page + "");
        getPresenter().getMemberMsgs(map);
    }

    @Override
    public void ongetMemberMsgs(List<UserMessageBean> data) {
        if (page == 1) {
            adapter.clear();

        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

    }

    @Override
    public OrderMessagePresenter createPresenter() {
        return new OrderMessagePresenter(getApp());
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

    @OnClick({R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;

        }
    }


}
