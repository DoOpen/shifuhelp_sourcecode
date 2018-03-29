package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.live.aksd.R;
import com.live.aksd.mvp.base.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class MyReportedFragment extends SimpleFragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private List<Fragment> listData;
    private List<CharSequence> listTitle;


    public static MyReportedFragment newIntance() {
        Bundle args = new Bundle();
        MyReportedFragment fragment = new MyReportedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_myorder;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.my_reproted);
        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
        listTitle.add(getString(R.string.wait_review));
        listTitle.add(getString(R.string.already_reproted));
        listTitle.add(getString(R.string.refuse));
        for (int j = 0; j < 3; j++) {
            listData.add(AllReportedFragment.newInstance("" + j));
        }
        ViewPagerFragmentAdapter viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;

        }
    }








   /* @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.reported_num)
    TextView reported_num;
    @BindView(R.id.my_reported_recycle)
    EasyRecyclerView myReportedRecycle;


    private MyReportedAdapter adapter;
    List<ReportedBean> myReportedList;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;


    public static MyReportedFragment newIntance() {
        Bundle args = new Bundle();
        MyReportedFragment fragment = new MyReportedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_myreported;
    }

    @Override
    public void initUI() {

        tvTitle.setText("我的报备");
        myReportedList = new ArrayList<>();
        adapter = new MyReportedAdapter(context, myReportedList);
        myReportedRecycle.setAdapter(adapter);
        myReportedRecycle.setLayoutManager(new LinearLayoutManager(context));
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startReportedDetailFragment(adapter.getItem(position).getReported_id()+""); //打开报备详情
            }
        });




    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("page", "1");


    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().getReportedListByMemberId(map);
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
    public void onGetReportedListByMemberId(List<ReportedBean> data) {
        reported_num.setText("共报备"+data.size()+"条");
        adapter.clear();
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
    }*/
}