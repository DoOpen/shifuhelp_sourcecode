package com.live.aksd.mvp.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.ForgetPwdPresenter;
import com.live.aksd.mvp.presenter.RegisteredFragmentpresenter;
import com.live.aksd.mvp.view.IForgetPwdView;
import com.live.aksd.mvp.view.IRegisteredFragmentView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.live.aksd.R.id.registered_number;

/**
 * @author Created by stone
 * @since 17/8/24
 */

public class ForgetPwdFragment extends BaseFragment<IForgetPwdView, ForgetPwdPresenter> implements IForgetPwdView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;

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
    @BindView(registered_number)
    EditText registeredNumber;
    Unbinder unbinder;

    private Map<String, String> map = new HashMap<>();


    public static ForgetPwdFragment newInstance() {
        Bundle args = new Bundle();
        ForgetPwdFragment fragment = new ForgetPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_forget_pwd;
    }

    @Override
    public void initUI() {
        tvTitle.setText("忘记密码");
    }

    @Override
    public void initData() {
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e.getMessage().equals("账号不存在")) {
            ToastUtils.showToast(context.getApplicationContext(), "该手机号未注册，请先注册");
        } else {
            ToastUtils.showToast(context.getApplicationContext(), e.getMessage());
        }

    }


    @Override
    public void ongetYanZhengMa(String data) {
        ToastUtils.showToast(context.getApplicationContext(), "验证码已发送");
        getYzm.setEnabled(true);
        //registeredYzm.setText(data);
    }

    @Override
    public void ongetwangjiminma(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();

    }


    private void clickYZM() {
        map.put("mobile", registeredNumber.getText().toString());
        map.put("code_type", "forget_password");
        getPresenter().getYanZhengMa(map);
        getYzm.setEnabled(false);
        timer.start();

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
                if (TextUtils.isEmpty(registeredNumber.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入手机号");
                    return;
                }
                clickYZM();
                break;
            case R.id.btnLogin:
                if (TextUtils.isEmpty(registeredNumber.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入手机号");
                    return;
                }

                if (!(registeredNumber.getText().toString()).matches("^1[3|4|5|7|8][0-9]{9}$")) {
                    ToastUtils.showToast(context.getApplicationContext(), "请填写正确手机号");
                    return;
                }

                if (TextUtils.isEmpty(registeredNewPassword.getText().toString()) || TextUtils.isEmpty(registeredNewsPassword.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入密码");
                    return;
                } else {
                    if (registeredNewPassword.getText().equals(registeredNewsPassword.getText())) {
                        ToastUtils.showToast(context.getApplicationContext(), "两次输入不一致");
                        return;
                    }
                }
                if (TextUtils.isEmpty(registeredYzm.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入验证码");
                    return;
                }
                Map<String, String> zhuce = new HashMap<>();
                zhuce.put("member_account", registeredNumber.getText().toString());
                zhuce.put("member_password", registeredNewPassword.getText().toString());
                zhuce.put("code", registeredYzm.getText().toString());
                getPresenter().getwangjiminma(zhuce);
                break;
        }
    }

    @Override
    public ForgetPwdPresenter createPresenter() {
        return new ForgetPwdPresenter(getApp());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
