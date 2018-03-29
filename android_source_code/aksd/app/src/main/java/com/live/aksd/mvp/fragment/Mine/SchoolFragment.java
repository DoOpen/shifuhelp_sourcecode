package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.TrainingClassBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.SchoolPresenter;
import com.live.aksd.mvp.view.Mine.ISchoolView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/10/9
 */

public class SchoolFragment  extends BaseFragment<ISchoolView, SchoolPresenter> implements ISchoolView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private List<Fragment> listData;
    private List<CharSequence> listTitle = new ArrayList<>();;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;



    public static SchoolFragment newIntance() {
        Bundle args = new Bundle();
        SchoolFragment fragment = new SchoolFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_school;
    }

    @Override
    public void initUI() {

        tvTitle.setText("培训学院");

    }

    @Override
    public void initData() {
        getPresenter().getTrainingClassList();

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
    public SchoolPresenter createPresenter() {
        return new SchoolPresenter(getApp());
    }

    @OnClick({R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;

        }
    }


    @Override
    public void onGetTrainingClassList(List<TrainingClassBean> data) {
        int num=data.size();
        for(int i=0;i<num;i++){
            listTitle.add(data.get(i).getClass_name());
        }
        listData = new ArrayList<>();
        for (int j = 0; j < num; j++)
            listData.add(TrainingClassFragment.newInstance("" + j));
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);

    }
}
