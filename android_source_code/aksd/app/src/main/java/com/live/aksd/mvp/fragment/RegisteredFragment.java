package com.live.aksd.mvp.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.RegisteredFragmentpresenter;
import com.live.aksd.mvp.view.IRegisteredFragmentView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.live.aksd.R.string.login_phone_yzm;

/**
 * Created by mac1010 on 17/7/29.
 */

public class RegisteredFragment extends BaseFragment<IRegisteredFragmentView, RegisteredFragmentpresenter> implements IRegisteredFragmentView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPwdOne)
    EditText etPwdOne;
    @BindView(R.id.ckOne)
    CheckBox ckOne;
    @BindView(R.id.etPwdTwo)
    EditText etPwdTwo;
    @BindView(R.id.ckTwo)
    CheckBox ckTwo;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.tvProtocol)
    TextView tvProtocol;

    Unbinder unbinder;

    private String code;
    private Map<String, String> map = new HashMap<>();
    private String htmlPath;

    public static RegisteredFragment newInstance() {
        Bundle args = new Bundle();
        RegisteredFragment fragment = new RegisteredFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_phone_registered;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.signup);

        ckOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ckOne.setButtonDrawable(R.drawable.eye_h);
                    etPwdOne.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    ckOne.setButtonDrawable(R.drawable.eye);
                    etPwdOne.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        ckTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ckTwo.setButtonDrawable(R.drawable.eye_h);
                    etPwdTwo.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    ckTwo.setButtonDrawable(R.drawable.eye);
                    etPwdTwo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });


        etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    if (etPhone != null) {
                        String s = etPhone.getText().toString();
                        if (!TextUtils.isEmpty(s)) {
                            map.put("member_account", s);
                            getPresenter().getMemberByMobile(map);
                        }
                    }

                }
            }
        });
    }

    @Override
    public void initData() {
        map.put("html_name", "师傅注册协议");
        getPresenter().getHtmlDetail(map);
    }


    @Override
    public RegisteredFragmentpresenter createPresenter() {
        return new RegisteredFragmentpresenter(getApp());
    }

    @Override
    public void onRegister(UserBean data) {
        saveUser(data);
        startMydata();
        finish();
    }

    @Override
    public void onGetCode(String data) {
        ToastUtils.showToast(context.getApplicationContext(), getString(R.string.send_code));
        tvGetCode.setEnabled(true);
        timer.start();
        //etCode.setText(data);

    }


    @Override
    public void onGetMemberByMobile(UserBean data) {
        if (data != null) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.already_register));
        }

    }

    @Override
    public void onGetHtmlDetail(HtmlBean data) {

        htmlPath = data.getHtml_url();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (!"账号不存在".equals(e.getMessage().toString())) {
            ToastUtils.showToast(context.getApplicationContext(), e.getMessage());
        }

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

    private boolean checkInputKey() {
        if (StringUtils.isBlank(etPhone.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), R.string.login_activity_et_input_phone);
            return false;
        }

        if (TextUtils.isEmpty(etPhone.getText()) || TextUtils.isEmpty(etPwdOne.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.input_pwd));
            return false;
        }
        String s = etPwdOne.getText().toString();
        if (s.length() < 6) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.pwd_length_no));
            return false;
        }

        if (etPwdTwo.getText().equals(etPwdOne.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.twice_diffent));
            return false;
        }
        if (StringUtils.isBlank(etPhone.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), login_phone_yzm);
            return false;
        }
        return true;
    }

    private void clickZC() {
        if (checkInputKey()) {
            Map<String, String> zhuce = new HashMap<>();
            zhuce.clear();
            zhuce.put("member_account", etPhone.getText().toString());
            zhuce.put("member_password", etPwdOne.getText().toString());
            zhuce.put("code", etCode.getText().toString());
            getPresenter().getZhuCe(zhuce);
        }
    }

    private void clickYZM() {
        if (StringUtils.isBlank(etPhone.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), R.string.login_activity_et_input_phone);
            return;
        }
        Map<String, String> codeMap = new HashMap<>();
        codeMap.clear();
        codeMap.put("mobile", etPhone.getText().toString());
        codeMap.put("code_type", "register");
        getPresenter().getCode(codeMap);
        tvGetCode.setEnabled(false);
    }

    CountDownTimer timer = new CountDownTimer(90000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvGetCode.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            tvGetCode.setEnabled(true);
            tvGetCode.setText(R.string.get_code);
        }
    };

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    @OnClick({R.id.ivLeft, R.id.tvGetCode, R.id.btnRegister, R.id.tvProtocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvGetCode:
                clickYZM();
                break;
            case R.id.btnRegister:
                clickZC();
                break;
            case R.id.tvProtocol:
                startWeb(getString(R.string.register_protocol), "", Constants.BASE_URL + htmlPath, "");
                break;
        }
    }


}
