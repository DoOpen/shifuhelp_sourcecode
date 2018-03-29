package com.live.aksd.mvp.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.live.aksd.MainActivity;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.SimpleFragment;
import com.live.aksd.util.SpSingleInstance;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 17/10/13
 */

public class PayResultFragment extends SimpleFragment {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.iv_result)
    ImageView iv_result;
    private UserBean userBean;
    private String state;

    public static PayResultFragment newIntance(String state) {
        Bundle args = new Bundle();
        PayResultFragment fragment = new PayResultFragment();
        fragment.state = state;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_pay_result;
    }

    @Override
    public void initUI() throws Exception {
        if (state.equals("1")) {
            tvTitle.setText("支付成功");
            one.setText("订单支付成功！");
            two.setText("查看订单");
            three.setText("返回首页");
        } else {
            one.setText("订单支付失败！");
            two.setText("再次支付");
            three.setText("重新下单");
            iv_result.setImageResource(R.drawable.zfsb);
        }


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();

    }

    @OnClick({R.id.ivLeft, R.id.three, R.id.two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.two:
                if (two.getText().equals("查看订单")) {
                    startMyOrderFragment("2");
                } else {
                    startMyOrderFragment("1");
                }
                finish();
                break;
            case R.id.three:
                if (three.getText().equals("返回首页")) {
                    startActivity(MainActivity.class);
                }
                finish();
                break;
        }
    }
}
