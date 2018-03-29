package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.live.aksd.R;
import com.live.aksd.bean.HtmlBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.base.SimpleFragment;
import com.live.aksd.mvp.presenter.Mine.SettingPresenter;
import com.live.aksd.mvp.view.Mine.ISettingView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class MessageFragment extends SimpleFragment {


    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public static MessageFragment newIntance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.my_message);

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.ivLeft, R.id.tvSystem, R.id.tvOrder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvSystem:
                startSystemMessageFragment();

                break;
            case R.id.tvOrder:
                startOrderMessageFragment();

                break;

        }
    }

}
