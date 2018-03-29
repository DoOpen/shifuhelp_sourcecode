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
import com.live.aksd.bean.AfterSaleDetailBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Mine.AfterSaleAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.AfterSalePresenter;
import com.live.aksd.mvp.view.Mine.IAfterSaleView;
import com.live.aksd.util.SpSingleInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class AfterSaleFragment extends BaseFragment<IAfterSaleView, AfterSalePresenter> implements IAfterSaleView {
    View loadMore;
    private  TextView tvEmpty;
    private TextView tvTips;

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.after_sale_recycle)
    EasyRecyclerView afterSaleRecycle;

    private AfterSaleAdapter adapter;
    List<AfterSaleDetailBean> afterSaleList;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;
    private boolean isMore;


    public static AfterSaleFragment newIntance() {
        Bundle args = new Bundle();
        AfterSaleFragment fragment = new AfterSaleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_aftersale;
    }

    @Override
    public void initUI() {
        tvTitle.setText("售后订单");
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        tvTips = (TextView) afterSaleRecycle.findViewById(R.id.tvTips);
        tvEmpty = (TextView) afterSaleRecycle.findViewById(R.id.tvEmpty);
        afterSaleList = new ArrayList<>();
        adapter = new AfterSaleAdapter(context, afterSaleList);
        afterSaleRecycle.setAdapter(adapter);
        afterSaleRecycle.setLayoutManager(new LinearLayoutManager(context));
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                page=1;
                startAfterSaleDetailFragment(adapter.getAllData().get(position).getRefund_id()+"");//售后查看详情
            }
        });

        //刷新
        afterSaleRecycle.setRefreshingColorResources(R.color.colorPrimary);
        afterSaleRecycle.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                map.put("page",page+"");
                getPresenter().getMemberRefunds(map);
            }
        });
        //下拉加载
        adapter.setMore(loadMore, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                if (loadMore != null) {
                    loadMore.setVisibility(View.VISIBLE);
                }
                page++;
                map.put("page", page+"");
                getPresenter().getMemberRefunds(map);
            }
            @Override
            public void onMoreClick() {

            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("page","1");
        getPresenter().getMemberRefunds(map);
    }

    @Override
    public void onGetMemberRefunds(List<AfterSaleDetailBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }



    @Override
    public AfterSalePresenter createPresenter() {
        return new AfterSalePresenter(getApp());
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
