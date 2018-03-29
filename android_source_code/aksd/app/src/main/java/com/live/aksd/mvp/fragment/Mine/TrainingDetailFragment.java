package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.live.aksd.R;
import com.live.aksd.bean.TrainingDetailBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.TrainingDetailPresenter;
import com.live.aksd.mvp.view.Mine.ITrainingDetailView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 17/10/10
 */

public class TrainingDetailFragment extends BaseFragment<ITrainingDetailView, TrainingDetailPresenter> implements ITrainingDetailView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.h)
    TextView h;
    private String training_id;
    private Map<String, String> map = new HashMap<>();


    public static TrainingDetailFragment newIntance(String training_id) {
        Bundle args = new Bundle();
        TrainingDetailFragment fragment = new TrainingDetailFragment();
        fragment.training_id = training_id;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fgment_training_detail;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {


    }

    @Override
    public TrainingDetailPresenter createPresenter() {
        return new TrainingDetailPresenter(getApp());
    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("class_id", training_id);
        getPresenter().getTraining(map);

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

    @Override
    public void onGetTraining(TrainingDetailBean data) {
        tvTitle.setText(data.getTraining_title());
        h.setText(data.getTraining_title());
    }
}
