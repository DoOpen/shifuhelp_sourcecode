package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.ChangePwdpresenter;
import com.live.aksd.mvp.presenter.RegisteredFragmentpresenter;
import com.live.aksd.mvp.view.IRegisteredFragmentView;
import com.live.aksd.mvp.view.Mine.IChangePwdView;
import com.live.aksd.util.SpSingleInstance;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Created by stone
 * @since 17/8/24
 */

public class ChangePwdFragment extends BaseFragment<IChangePwdView, ChangePwdpresenter> implements IChangePwdView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.member_account)
    TextView member_account;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.registered_tow_password)
    EditText registeredTowPassword;
    @BindView(R.id.registered_new_password)
    EditText registeredNewPassword;
    @BindView(R.id.registered_news_password)
    EditText registeredNewsPassword;
    @BindView(R.id.registered_yzm)
    EditText registeredYzm;
    @BindView(R.id.get_yzm)
    TextView getYzm;
    @BindView(R.id.btnLogin)
    Button btnLogin;


    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    public static ChangePwdFragment newIntance() {
        Bundle args = new Bundle();
        ChangePwdFragment fragment = new ChangePwdFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_change_pwd;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.change_password);

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


    @Override
    public ChangePwdpresenter createPresenter() {
        return new ChangePwdpresenter(getApp());
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
    public void ongetYanZhengMa(String data) {
        ToastUtils.showToast(context.getApplicationContext(), "验证码已发送");
        getYzm.setEnabled(true);
        timer.start();
    }


    @Override
    public void onUpPwd(String data) {
        ToastUtils.showToast(context.getApplicationContext(), "密码已重置，请重新登录!");
        exitLogin();
        finish();
    }


    private void clickYZM() {
        map.put("mobile", userBean.getMember_account());
        map.put("code_type", "update_password");
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

    @OnClick({R.id.ivLeft, R.id.get_yzm, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.get_yzm:
                clickYZM();
                break;
            case R.id.btnLogin:

                String pwdOne = registeredNewsPassword.getText().toString();
                String pwdTwo = registeredNewPassword.getText().toString();
                String oldPwd = registeredTowPassword.getText().toString();
                if (TextUtils.isEmpty(pwdOne) || TextUtils.isEmpty(oldPwd)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入密码");
                    return;
                } else {
                    if (!pwdOne.equals(pwdTwo)) {
                        ToastUtils.showToast(context.getApplicationContext(), "两次新密码不一致");
                        return;
                    }
                }
                if (TextUtils.isEmpty(registeredYzm.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入验证码");
                    return;
                }
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("member_account", userBean.getMember_account());
                map.put("member_old_password", registeredTowPassword.getText().toString());
                map.put("member_password", registeredNewPassword.getText().toString());
                map.put("code", registeredYzm.getText().toString());
                getPresenter().getUpPwd(map);
                break;
        }
    }


}
