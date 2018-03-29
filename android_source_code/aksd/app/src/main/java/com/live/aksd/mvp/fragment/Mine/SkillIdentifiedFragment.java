package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.live.aksd.R;
import com.live.aksd.bean.AfterSaleDetailBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.AfterSalePresenter;
import com.live.aksd.mvp.view.Mine.IAfterSaleView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 17/10/9
 */

public class SkillIdentifiedFragment extends BaseFragment<IAfterSaleView, AfterSalePresenter> implements IAfterSaleView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private String choose;

    public static SkillIdentifiedFragment newIntance(String choose) {
        Bundle args = new Bundle();
        SkillIdentifiedFragment fragment = new SkillIdentifiedFragment();
        fragment.choose = choose;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_skillidentifie;
    }

    @Override
    public void initUI() {
        if (choose.equals("1")) {
            tvTitle.setText("技能认定");
        } else {
            tvTitle.setText("培训学院");
        }


    }

    @Override
    public void initData() {
    }


    @Override
    public void onGetMemberRefunds(List<AfterSaleDetailBean> data) {

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
