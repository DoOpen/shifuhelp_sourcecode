package com.live.aksd.mvp.fragment.Mine;

import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.MyWalletPresenter;
import com.live.aksd.mvp.view.Mine.IMyWalletView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Created by stone
 * @since 17/8/22
 */

public class MyWalletFragment extends BaseFragment<IMyWalletView, MyWalletPresenter> implements IMyWalletView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rootview)
    RelativeLayout rootview;
    @BindView(R.id.tv_total)
    TextView tv_total;
    @BindView(R.id.tv_band)
    TextView tv_band;
    @BindView(R.id.edit_money)
    EditText edit_money;
    @BindView(R.id.canMoney)
    TextView canMoney;
    @BindView(R.id.tv_setpwd)
    TextView tv_setpwd;


    @BindView(R.id.ivRight2)
    ImageView ivRight2;


    @BindView(R.id.tv_freeze)
    TextView tv_freeze;
    @BindView(R.id.tv_extract)
    TextView tv_extract;
    private PopupWindow bind_pop;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> htmlMap = new HashMap<>();
    private String htmlPath;
    private UserBean userBean;

    private String money;//保证金
    private String pay_type = "";//提现方式

    private int freezeMoney = 0; //冻结资金
    private int extractMoney = 0; //可提现资金
    private int totalMoney = 0;//总金额
    private String freeze;
    private String extract;



    public static MyWalletFragment newIntance() {
        Bundle args = new Bundle();
        MyWalletFragment fragment = new MyWalletFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_mywallet;
    }

    @Override
    public void initUI() {
        tvTitle.setText("我的钱包");
        ivRight2.setVisibility(View.VISIBLE);
        ivRight2.setImageResource(R.drawable.history);
        edit_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                canMoney.setText(s.toString() + "元");
            }
        });


    }

    @Override
    public void initData() {
        htmlMap.put("html_name", "用户提现协议");
        getPresenter().getHtmlDetail(htmlMap);


    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getUser(map);
    }

    @Override
    public void onGetUser(UserBean data) {
        userBean = data;
        if (TextUtils.isEmpty(userBean.getMember_pay_password())) {
            tv_setpwd.setText("未设置");
        } else {
            tv_setpwd.setText("已设置");
        }
        money = userBean.getMember_deposit_money();
        freeze = userBean.getMember_freeze_money();
        extract = userBean.getMember_extract_money();

        if (!TextUtils.isEmpty(freeze)) {
            String[] freeMpeny1 = freeze.split("\\.");
            freezeMoney = Integer.parseInt(freeMpeny1[0]);
            tv_freeze.setText(freezeMoney + "元");
        }
        if (!TextUtils.isEmpty(extract)) {
            String[] freeMpeny2 = extract.split("\\.");
            extractMoney = Integer.parseInt(freeMpeny2[0]);
            tv_extract.setText(extractMoney + "元");
        }
        totalMoney = freezeMoney + extractMoney;
        tv_total.setText(totalMoney + "");


        if (!TextUtils.isEmpty(userBean.getMember_alipay())) {
            tv_band.setText("支付宝：" + userBean.getMember_alipay());
            pay_type = "alipay";
        } else if (TextUtils.isEmpty(userBean.getMember_alipay()) & !TextUtils.isEmpty(userBean.getMember_we_chat())) {
            tv_band.setText("微信：" + userBean.getMember_we_chat());
            pay_type = "we_chat";
        } else if (TextUtils.isEmpty(userBean.getMember_alipay()) & TextUtils.isEmpty(userBean.getMember_we_chat()) & !userBean.getMember_bank_code().isEmpty()) {
            tv_band.setText("银行卡：" + userBean.getMember_bank_code());
            pay_type = "bank";

        } else if (TextUtils.isEmpty(userBean.getMember_alipay()) & TextUtils.isEmpty(userBean.getMember_we_chat()) & TextUtils.isEmpty(userBean.getMember_bank_code())) {
            tv_band.setText("未绑定");
        }

    }

    @Override
    public void onApplyCash(String data) {
       // ToastUtils.showToast(context.getApplicationContext(), data);
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("提现成功！ 提现金额将在7个工作日内到账！");
        builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
        onResume();

    }

    @Override
    public void updateMemberDetail(String data) {
    }

    @Override
    public void onGetHtmlDetail(HtmlBean data) {
        htmlPath=data.getHtml_url();
    }


    @Override
    public MyWalletPresenter createPresenter() {
        return new MyWalletPresenter(getApp());
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


    private void startBindpop() {
        View view = View.inflate(context, R.layout.fragment_bind, null);
        bind_pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView alipay = (TextView) view.findViewById(R.id.alipay);

        alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(userBean.getMember_alipay())) {
                    startBindAlipayFragment();
                } else {
                    tv_band.setText("支付宝：" + userBean.getMember_alipay());
                    pay_type = "alipay";
                }

                bind_pop.dismiss();
            }
        });
        TextView btn_alipay = (TextView) view.findViewById(R.id.btn_alipy);
        btn_alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startBindAlipayFragment();
                bind_pop.dismiss();
            }
        });

        TextView wechat = (TextView) view.findViewById(R.id.wechat);
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(userBean.getMember_we_chat())) {
                    startBindWechatFragment();
                } else {
                    tv_band.setText("微信：" + userBean.getMember_we_chat());
                    pay_type = "we_chat";
                }

                bind_pop.dismiss();
            }
        });

        TextView btn_wechat = (TextView) view.findViewById(R.id.btn_wechat);
        btn_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBindWechatFragment();
                bind_pop.dismiss();
            }
        });

        TextView bank = (TextView) view.findViewById(R.id.bank);
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(userBean.getMember_bank_code())) {
                    startBindBankFragment();
                } else {
                    tv_band.setText("银行卡：" + userBean.getMember_bank_code());
                    pay_type = "bank";
                }
                bind_pop.dismiss();
            }
        });

        TextView btn_bank = (TextView) view.findViewById(R.id.btn_bank);
        btn_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBindBankFragment();
                bind_pop.dismiss();
            }
        });
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bind_pop.dismiss();
            }
        });
        bind_pop.setBackgroundDrawable(new BitmapDrawable());
        bind_pop.setFocusable(false);
        bind_pop.setOutsideTouchable(true);
        bind_pop.setContentView(view);
        bind_pop.showAtLocation(rootview, Gravity.CENTER, 0, 0);

    }


    @OnClick({R.id.ivLeft, R.id.rl_withdraw_account, R.id.btnWithdraw, R.id.rl_deposit, R.id.tv_xy, R.id.set_pwd,R.id.ivRight2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivRight2:
                startWithdrawRecordFragment();
                break;
            case R.id.tv_xy:
                startWeb("用户提现协议", "", Constants.BASE_URL + htmlPath, "");
                break;
            case R.id.rl_deposit:
                startDepositMoneyFragment(money);
                break;
            case R.id.rl_withdraw_account:
                startBindpop(); //打开去绑定弹窗
                break;
            case R.id.btnWithdraw: //开始提现
                if (TextUtils.isEmpty(userBean.getMember_pay_password())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请先设置提现密码");
                    return;
                }
                if (tv_band.getText().equals("去绑定")) {
                    ToastUtils.showToast(context.getApplicationContext(), "请选择体现账户");
                    return;
                }
                if (TextUtils.isEmpty(pay_type)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请选择提现方式");
                    return;
                }
                if (StringUtils.isBlank(edit_money.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入提现金额");
                    return;
                }
                if (Integer.parseInt(edit_money.getText().toString()) < 100) {
                    ToastUtils.showToast(context.getApplicationContext(), "提现金额不少于100");
                    return;
                }
                if (TextUtils.isEmpty(extract)) {
                    ToastUtils.showToast(context.getApplicationContext(), "可提现金额不足");
                    return;
                }
                if (Integer.parseInt(edit_money.getText().toString()) > extractMoney) {
                    ToastUtils.showToast(context.getApplicationContext(), "可提现金额不足");
                    return;
                }
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("withdrawal_way", pay_type);
                map.put("withdrawal_price", edit_money.getText().toString());
                startInputPwdFragment();//打开输入密码框
                break;
            case R.id.set_pwd:
                startPayPwdFragment();
                break;

        }
    }

    private InputPwdFragment fragment;

    private void startInputPwdFragment() {
        fragment = InputPwdFragment.newInstance();
        fragment.setOnOverListener(new InputPwdFragment.OnOverListener() {
            @Override
            public void onOver(String state) {
                fragment.dismiss();
                map.put("member_pay_password",state);
                getPresenter().applyCash(map);
            }
        });
        fragment.show(getFragmentManager(), MyWalletFragment.class.getSimpleName());
    }


}
