package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.PayPwdPresenter;
import com.live.aksd.mvp.view.Mine.IPayPwdView;
import com.live.aksd.util.SpSingleInstance;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/9/21
 */

public class PayPwdFragment extends BaseFragment<IPayPwdView, PayPwdPresenter> implements IPayPwdView {

   /* @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(pwd1)
    PassWordEditText pwdone;
    @BindView(pwd2)
    PassWordEditText pwdtwo;*/

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.member_account)
    TextView member_account;
    @BindView(R.id.registered_new_password)
    EditText registeredNewPassword;
    @BindView(R.id.registered_news_password)
    EditText registeredNewsPassword;
    @BindView(R.id.registered_yzm)
    EditText registeredYzm;
    @BindView(R.id.get_yzm)
    TextView getYzm;


    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private String state;

    Unbinder unbinder;

    public static PayPwdFragment newInstance() {
        Bundle args = new Bundle();
        PayPwdFragment fragment = new PayPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_pay_pwd;
    }

    @Override
    public void initUI() {
        tvTitle.setText("设置提现密码");
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        if (!TextUtils.isEmpty(userBean.getMember_account())) {
            StringBuffer buffer = new StringBuffer(userBean.getMember_account());
            buffer.replace(3, 7, "****");
            member_account.setText(buffer.toString());
        }
    }

    private void clickYZM() {
        map.put("mobile", userBean.getMember_account());
        map.put("code_type", "balance_passwrod");
        getPresenter().getYanZhengMa(map);
        getYzm.setEnabled(false);

    }

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

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }


    @Override
    public void onSetPayPassword(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void ongetYanZhengMa(String data) {
        ToastUtils.showToast(context.getApplicationContext(), "验证码已发送");
        getYzm.setEnabled(true);
        timer.start();

    }

    @Override
    public PayPwdPresenter createPresenter() {
        return new PayPwdPresenter(getApp());
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


    @OnClick({R.id.ivLeft, R.id.ok, R.id.get_yzm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.get_yzm:
                clickYZM();
                break;
            case R.id.ok:
                String pwdo = registeredNewPassword.getText().toString();
                if (TextUtils.isEmpty(pwdo)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入支付密码");
                    return;
                }

                if (pwdo.length() != 6) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入6位的提现密码");
                    return;
                }
                String pwdt = registeredNewsPassword.getText().toString();


                if (TextUtils.isEmpty(pwdt)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请确认支付密码");
                    return;
                }
                if (!pwdo.equals(pwdt)) {
                    ToastUtils.showToast(context.getApplicationContext(), "两次输入的密码不相同");
                    return;
                }
                if (TextUtils.isEmpty(registeredYzm.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入验证码");
                    return;
                }
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("member_phone", userBean.getMember_account());
                map.put("code", registeredYzm.getText().toString());
                map.put("member_pay_password", pwdo);
                getPresenter().setPayPassword(map);
                break;

        }
    }


}
