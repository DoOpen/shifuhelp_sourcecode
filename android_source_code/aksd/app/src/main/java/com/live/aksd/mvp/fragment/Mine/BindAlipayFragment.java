package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.BindPresenter;
import com.live.aksd.mvp.view.Mine.IBindView;
import com.live.aksd.util.SpSingleInstance;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 17/8/24
 */

public class BindAlipayFragment extends BaseFragment<IBindView, BindPresenter> implements IBindView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.alipayNum)
    EditText phone;
    @BindView(R.id.realName)
    EditText realName;
    @BindView(R.id.verificationCode)
    EditText verificationCode;
    @BindView(R.id.get_yzm)
    TextView getYzm;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static BindAlipayFragment newIntance() {
        Bundle args = new Bundle();
        BindAlipayFragment fragment = new BindAlipayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_bind_alipay;
    }

    @Override
    public void initUI() {
        tvTitle.setText("绑定支付宝");


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    //获取验证码返回的结果
    @Override
    public void ongetYanZhengMa(String data) {
        ToastUtils.showToast(context.getApplicationContext(), "验证码已发送");
        getYzm.setEnabled(true);
        timer.start();
    }

    //绑定支付宝返回的结果
    @Override
    public void onBindOtherNo(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        timer.cancel();
        finish();
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
    public BindPresenter createPresenter() {
        return new BindPresenter(getApp());
    }


    //获取验证码
    private void clickYZM() {
        if (TextUtils.isEmpty(phone.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入手机号");
            return;
        }
        getYzm.setEnabled(false);
        Map<String, String> yanzhema = new HashMap<>();
        yanzhema.put("mobile", phone.getText().toString());
        yanzhema.put("code_type", "bind_alipay");
        getPresenter().getYanZhengMa(yanzhema);
    }

    //验证码倒计时
    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            getYzm.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            getYzm.setEnabled(true);
            getYzm.setText("获取验证码");
        }
    };


    @OnClick({R.id.ivLeft, R.id.get_yzm, R.id.btnBind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.get_yzm:
                clickYZM();
                break;
            case R.id.btnBind:
                if (TextUtils.isEmpty(phone.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入支付宝账号");
                    return;
                }
                if (StringUtils.isBlank(realName.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入真实姓名");
                    return;
                }
                if (StringUtils.isBlank(verificationCode.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入您获取的验证码");
                    return;
                }
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("code_type", "bind_alipay");
                map.put("code", verificationCode.getText().toString());
                map.put("member_alipay", phone.getText().toString());
                map.put("memebr_alipay_real_name", realName.getText().toString());
                getPresenter().bindOtherNo(map);

                break;


        }
    }
}
