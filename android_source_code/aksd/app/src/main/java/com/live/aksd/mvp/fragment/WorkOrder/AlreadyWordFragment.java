package com.live.aksd.mvp.fragment.WorkOrder;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.king.base.adapter.ViewPagerFragmentAdapter;
import com.live.aksd.R;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.WorkOrderNumberBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.WordOrder.AlreadyWordPresenter;
import com.live.aksd.mvp.view.WordOrder.IAlreadyWordView;
import com.live.aksd.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by sh-lx on 2017/8/22.
 */

public class AlreadyWordFragment extends BaseFragment<IAlreadyWordView, AlreadyWordPresenter> implements IAlreadyWordView {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.num_one)
    TextView one;
    @BindView(R.id.num_two)
    TextView two;
    @BindView(R.id.num_three)
    TextView three;
    @BindView(R.id.num_four)
    TextView four;


    private List<Fragment> listData;
    private List<CharSequence> listTitle;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static AlreadyWordFragment newInstance() {
        Bundle args = new Bundle();
        AlreadyWordFragment fragment = new AlreadyWordFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_worder_already;
    }

    @Override
    public void initUI() {

        listTitle = new ArrayList<>();
        listData = new ArrayList<>();
        List<CharSequence> listTemp = new ArrayList<>();
        //----------------------
        listTemp.add(getText(R.string.no_fw));
        listData.add(NoFwAWordFragment.newInstance());
        listTemp.add(getText(R.string.yes_fw));
        listData.add(YesFwAWordFragment.newInstance());
        listTemp.add(getText(R.string.no_sh));
        listData.add(NoShAWordFragment.newInstance());
        listTemp.add(getText(R.string.yes_wc));
        listData.add(OKAWordFragment.newInstance());
        toSetList(listTitle, listTemp, false);

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), listData, listTitle);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
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
        map.put("member_type", "1");
        getPresenter().getWorkOrderStateCount(map);
    }

    @Override
    public void onGetWorkOrderStateCount(WorkOrderNumberBean data) {
        one.setText(data.getWorker_not_service());
        two.setText(data.getWorker_servicing());
        three.setText(data.getWorker_wait_audit());
        four.setText(data.getWorker_complete());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("40")) {
            onResume();
        }
    }

    @Override
    public AlreadyWordPresenter createPresenter() {
        return new AlreadyWordPresenter(getApp());
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


}
