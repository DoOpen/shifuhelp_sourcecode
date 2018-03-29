package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.fragment.JsWebFragment;
import com.live.aksd.mvp.presenter.Mine.MyScoresPresenter;
import com.live.aksd.mvp.view.Mine.IMyScoresView;
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

public class MyScoresFragment extends BaseFragment<IMyScoresView, MyScoresPresenter> implements IMyScoresView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.total_score)
    TextView total_score;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private String htmlPath;
    private List<CharSequence> listTitle;
    private List<Fragment> listData;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> htmlMap = new HashMap<>();
    private UserBean userBean;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    public static MyScoresFragment newIntance() {
        Bundle args = new Bundle();
        MyScoresFragment fragment = new MyScoresFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_myscores;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.my_integral);
        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
        listTitle.add(getString(R.string.obtain_record));
        listTitle.add(getString(R.string.use_rule));
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        htmlMap.put("html_name", "积分使用规则");
        getPresenter().getHtmlDetail(htmlMap);

    }


    @Override
    public void onResume() {
        super.onResume();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getUserIntegral(map);
    }

    @Override
    public void onGetUserIntegral(String data) {
        total_score.setText(data);
    }

    @Override
    public void onGetHtmlDetail(HtmlBean data) {
        htmlPath = data.getHtml_url();
        listData.add(new ScoreObtainFragment());
        listData.add(JsWebFragment.newInstance(Constants.BASE_URL + data.getHtml_url(), "", "1", ""));
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
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
    public MyScoresPresenter createPresenter() {
        return new MyScoresPresenter(getApp());
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
