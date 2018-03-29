package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.widget.SwipeRefreshLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.CouponBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Mine.MyCouponListAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.AllCouponPresenter;
import com.live.aksd.mvp.view.Mine.IAllCouponView;
import com.live.aksd.util.SpSingleInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author Created by stone
 * @since 17/8/23
 */

public class AllCouponFragment extends BaseFragment<IAllCouponView, AllCouponPresenter> implements IAllCouponView {

    @BindView(R.id.coupon_list)
    EasyRecyclerView coupon_list;
    View loadMore;
    private int page = 1;
    private String state;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    private MyCouponListAdapter adapter;
    List<CouponBean> myCouponListBean;

    public static AllCouponFragment newInstance(String state) {
        Bundle args = new Bundle();
        AllCouponFragment fragment = new AllCouponFragment();
        fragment.state=state;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.item_coupon;
    }

    @Override
    public void initUI() {
        myCouponListBean = new ArrayList<>();
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        coupon_list.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MyCouponListAdapter(getContext(), myCouponListBean);
        coupon_list.setAdapter(adapter);
        //刷新
        coupon_list.setRefreshingColorResources(R.color.colorPrimary);
        coupon_list.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                map.put("page",page+"");
                getPresenter().getCoupons(map);
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
                getPresenter().getCoupons(map);
            }
            @Override
            public void onMoreClick() {

            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("page","1");
        switch (state) {
            case "0":
                map.put("coupon_state", "not_used");
                break;
            case "1":
                map.put("coupon_state", "already_used");
                break;

            case "2":
                map.put("coupon_state", "expired");
                break;

        }
        getPresenter().getCoupons(map);

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
    public AllCouponPresenter createPresenter() {
        return new AllCouponPresenter(getApp());
    }

    @Override
    public void onGetCoupons(List<CouponBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

    }
}
