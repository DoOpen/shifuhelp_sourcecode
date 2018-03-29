package com.live.aksd.mvp.fragment.WorkOrder;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.WorkOrderNumberBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.WordOrder.WordOrderPresenter;
import com.live.aksd.mvp.view.WordOrder.IWordOrderView;
import com.live.aksd.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/8/21.
 */

public class WordOrderFragment extends BaseFragment<IWordOrderView, WordOrderPresenter> implements IWordOrderView {

    @BindView(R.id.rbDJ)
    RadioButton rbDJ;
    @BindView(R.id.rbYJ)
    RadioButton rbYJ;
    @BindView(R.id.rbYT)
    RadioButton rbYT;
    @BindView(R.id.rg)
    RadioGroup rg;
    Unbinder unbinder;

    private WaitWordFragment waitWordFragment;
    private ReturnWordFragment returnWordFragment;
    private AlreadyWordFragment alreadyWordFragment;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static WordOrderFragment newInstance() {
        Bundle args = new Bundle();
        WordOrderFragment fragment = new WordOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_worder;
    }

    @Override
    public void initUI() {
        rbDJ.setChecked(true);
        showWaitFragment();
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

    private void refresh() {
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("member_type", "1");
        getPresenter().getWorkOrderStateCount(map);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        refresh();
    }

    @Override
    public void onGetWorkOrderStateCount(WorkOrderNumberBean data) {
        rbDJ.setText(getString(R.string.waitorder) + data.getWorker_wait_accept() + ")");
        rbYJ.setText(getString(R.string.alreadyorder) + data.getWorker_accept() + ")");
        rbYT.setText(getString(R.string.refundorder) + data.getWorker_cancle() + ")");
    }


    public void commitShowFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public void showWaitFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (waitWordFragment == null) {
            waitWordFragment = WaitWordFragment.newInstance();
            fragmentTransaction.add(R.id.fragmentContent, waitWordFragment);
        }
        commitShowFragment(fragmentTransaction, waitWordFragment);
    }


    public void showReturnFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (returnWordFragment == null) {
            returnWordFragment = ReturnWordFragment.newInstance();
            fragmentTransaction.add(R.id.fragmentContent, returnWordFragment);
        }
        commitShowFragment(fragmentTransaction, returnWordFragment);
    }

    public void showAlreadyFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (alreadyWordFragment == null) {
            alreadyWordFragment = AlreadyWordFragment.newInstance();
            fragmentTransaction.add(R.id.fragmentContent, alreadyWordFragment);
        }
        commitShowFragment(fragmentTransaction, alreadyWordFragment);
    }


    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        hideFragment(fragmentTransaction, waitWordFragment);
        hideFragment(fragmentTransaction, returnWordFragment);
        hideFragment(fragmentTransaction, alreadyWordFragment);

    }


    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        switch (event.getMsg()) {
            case Constants.NUMBER_REFRESH:
                refresh();
                break;
        }

    }

    @OnClick({R.id.rbDJ, R.id.rbYJ, R.id.rbYT})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbDJ:
                showWaitFragment();
                break;
            case R.id.rbYJ:
                showAlreadyFragment();
                break;
            case R.id.rbYT:
                showReturnFragment();
                break;
        }
    }


    @Override
    public WordOrderPresenter createPresenter() {
        return new WordOrderPresenter(getApp());
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
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

}
