package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.live.aksd.R;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.CouponPresenter;
import com.live.aksd.mvp.view.Mine.ICouponView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class CouponFragment extends BaseFragment<ICouponView, CouponPresenter> implements ICouponView  {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;


    private String state;//类型

    Unbinder unbinder;
    private List<Fragment> listData;
    private List<CharSequence> listTitle;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;


    public static CouponFragment newIntance(String state) {
        Bundle args = new Bundle();
        CouponFragment fragment = new CouponFragment();
        fragment.state=state;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return  R.layout.fragment_coupon;
    }

    @Override
    public void initUI() {
        tvTitle.setText("我的优惠卷");

        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
        listTitle.add("未使用");
        listTitle.add("已使用");
        listTitle.add("已过期");
        for (int j = 0; j < 3; j++)
            listData.add(AllCouponFragment.newInstance("" + j));

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        switch (state) {
            case "0":
                viewPager.setCurrentItem(0);
                break;
            case "1":
                viewPager.setCurrentItem(1);
                break;

            case "2":
                viewPager.setCurrentItem(2);
                break;

        }
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void initData() {

    }



    @Override
    public CouponPresenter createPresenter() {
        return new CouponPresenter(getApp());
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
