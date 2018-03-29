package com.live.aksd.mvp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.MainActivity;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.LoginFragmentPresenter;
import com.live.aksd.mvp.view.ILoginFragmentView;
import com.live.aksd.util.SPUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mac1010 on 17/7/29.
 */

public class LoginFragment extends BaseFragment<ILoginFragmentView, LoginFragmentPresenter> implements ILoginFragmentView {


    @BindView(R.id.gif_id)
    ImageView gifId;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.ll_zh)
    LinearLayout llZh;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.ll_pw)
    LinearLayout llPw;
    @BindView(R.id.ll_pwd_phone)
    LinearLayout llPwdPhone;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvSignup)
    TextView tvSignup;
    @BindView(R.id.tvForgetPwd)
    TextView tvForgetPwd;
    @BindView(R.id.login_eyeopen)
    CheckBox loginEyeopen;
    Unbinder unbinder;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();


    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initUI() {
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        String username = sharedPre.getString("username", "");
        if (!TextUtils.isEmpty(username)) {
            phone.setText(username);
        }
        loginEyeopen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    loginEyeopen.setButtonDrawable(R.drawable.eye_h);
                    pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    loginEyeopen.setButtonDrawable(R.drawable.eye);
                    pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    if (phone != null) {
                        String s = phone.getText().toString();
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
        userBean = SPUtils.getObj1(context.getApplicationContext(), Constants.USER_BEAN);

    }

    @Override
    public LoginFragmentPresenter createPresenter() {
        return new LoginFragmentPresenter(getApp());
    }


    //-1表示未填写个人信息  0表示审核中  1表示审核通过 2未通过
    @Override
    public void ongetlogin(UserBean data) {
        saveUser(data);
        switch (data.getMember_state()) {
            case "-1":
                startMydata();
                break;

            case "0":
                startMySH();
                break;
            case "1":
                JPushInterface.setAlias(context.getApplicationContext(), data.getMember_id(), new TagAliasCallback() {
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                    }
                });
                startActivity(MainActivity.class);
                finish();
                break;
            case "2":
                startMySH();
                break;
        }

    }

    @Override
    public void onGetMemberByMobile(UserBean data) {
        if (data == null) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.no_register));
        }
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showToast(context.getApplicationContext(), e.getMessage());

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

    @OnClick({R.id.btnLogin, R.id.tvSignup, R.id.tvForgetPwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                saveLoginInfo(context, phone.getText().toString());
                map.put("member_account", phone.getText().toString());
                map.put("member_password", pwd.getText().toString());
                getPresenter().getLogin(map);
                break;
            case R.id.tvSignup:
                statrRegistered();
                break;
            case R.id.tvForgetPwd:
                statrForgetPwd();
                break;
        }
    }


    /**
     * 使用SharedPreferences保存用户登录信息
     *
     * @param context
     * @param username
     */
    public static void saveLoginInfo(Context context, String username) {
        //获取SharedPreferences对象
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor = sharedPre.edit();
        //设置参数
        editor.putString("username", username);
        //提交
        editor.commit();
    }
}
