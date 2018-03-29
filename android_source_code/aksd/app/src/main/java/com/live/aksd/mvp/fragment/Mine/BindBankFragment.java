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

public  class BindBankFragment extends BaseFragment<IBindView, BindPresenter> implements IBindView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.bankName)
    EditText bankName;
    @BindView(R.id.bankNum)
    EditText bankNum;
    @BindView(R.id.bankAddressName)
    EditText bankAddressName;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.realName)
    EditText realName;
    @BindView(R.id.verificationCode)
    EditText verificationCode;
    @BindView(R.id.get_yzm)
    TextView getYzm;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static BindBankFragment newIntance() {
        Bundle args = new Bundle();
        BindBankFragment fragment = new BindBankFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_bind_bank;
    }

    @Override
    public void initUI() {
        tvTitle.setText("绑定银行卡");
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("code_type", "bind_bank");

    }


    @Override
    public BindPresenter createPresenter() {
        return new BindPresenter(getApp());
    }

    //获取验证码返回的结果
    @Override
    public void ongetYanZhengMa(String data) {
        ToastUtils.showToast(context.getApplicationContext(), "验证码已发送");
        getYzm.setEnabled(true);
        timer.start();
    }

    //绑定银行卡返回的结果
    @Override
    public void onBindOtherNo(String data) {
        ToastUtils.showToast(context.getApplicationContext(),data);
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

    //获取验证码
    private void clickYZM() {
        if (TextUtils.isEmpty(phone.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入手机号");
            return;
        }
        getYzm.setEnabled(false);
        Map<String, String> yanzhema = new HashMap<>();
        yanzhema.put("mobile", phone.getText().toString());
        yanzhema.put("code_type", "bind_bank");
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
            case R.id.get_yzm://获取验证码
                clickYZM();
                break;
            case R.id.btnBind:

                if (TextUtils.isEmpty(bankName.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入绑定所需银行卡所在银行");
                    return;
                }
                if (StringUtils.isBlank(bankNum.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入需要绑定的卡号");
                    return;
                }
                if (StringUtils.isBlank(realName.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入持卡人姓名");
                    return;
                }
                if (TextUtils.isEmpty(bankAddressName.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入开户行名称");
                    return;
                }
                if (StringUtils.isBlank(phone.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入银行预留手机号");
                    return;
                }
                if (StringUtils.isBlank(verificationCode.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入您获取的验证码");
                    return;
                }

                map.put("member_bank_name", bankName.getText().toString());
                map.put("member_bank_code", bankNum.getText().toString());
                map.put("member_bank__user_name", realName.getText().toString());
                map.put("member_bank_open_name", bankAddressName.getText().toString());
                map.put("member_bank_phone", phone.getText().toString());
                map.put("code", verificationCode.getText().toString());

                getPresenter().bindOtherNo(map);

                break;


        }
    }
}
