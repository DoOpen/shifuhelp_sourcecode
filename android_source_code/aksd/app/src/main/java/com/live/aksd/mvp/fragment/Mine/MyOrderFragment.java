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
import com.live.aksd.mvp.presenter.Mine.MyOrderPresenter;
import com.live.aksd.mvp.view.Mine.IMyOrderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class MyOrderFragment extends BaseFragment<IMyOrderView, MyOrderPresenter> implements IMyOrderView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private String state;//类型
    private List<Fragment> listData;
    private List<CharSequence> listTitle;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;


    public static MyOrderFragment newIntance(String state) {
        Bundle args = new Bundle();
        MyOrderFragment fragment = new MyOrderFragment();
        fragment.setArguments(args);
        fragment.state = state;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_myorder;
    }

    @Override
    public void initUI() {
        tvTitle.setText(getString(R.string.mine_order));
        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
        listTitle.add(getText(R.string.all_order));
        listTitle.add(getText(R.string.nopayment));
        listTitle.add(getText(R.string.nogoods_receipt2));
        listTitle.add(getText(R.string.nogoods_receipt));
        listTitle.add(getText(R.string.noevaluate));

        List<CharSequence> listTemp = new ArrayList<>();
        for (int j = 0; j < 5; j++)
            listData.add(AllOrderFragment.newInstance("" + j));

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
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

            case "3":
                viewPager.setCurrentItem(3);
                break;

            case "4":
                viewPager.setCurrentItem(4);
                break;
        }

        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void initData() {

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
    public MyOrderPresenter createPresenter() {
        return new MyOrderPresenter(getApp());
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
