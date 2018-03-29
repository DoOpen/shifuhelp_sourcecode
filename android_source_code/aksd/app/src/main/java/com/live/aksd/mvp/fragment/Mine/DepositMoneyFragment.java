package com.live.aksd.mvp.fragment.Mine;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.DepositMoneyPressent;
import com.live.aksd.mvp.view.Mine.IDepositMoneyView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;
import com.pingplusplus.android.PaymentActivity;
import com.pingplusplus.android.Pingpp;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 17/9/7
 */

public class DepositMoneyFragment extends BaseFragment<IDepositMoneyView, DepositMoneyPressent> implements IDepositMoneyView {


    @BindView(R.id.parentview)
    LinearLayout parentview;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.money)
    TextView tvMoney;
    @BindView(R.id.ok)
    TextView ok;

    @BindView(R.id.webView)
    WebView WVH5;


    private String money;
    private PopupWindow pop;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> htmlMap = new HashMap<>();
    private String htmlPath;

    public static DepositMoneyFragment newIntance(String money) {
        Bundle args = new Bundle();
        DepositMoneyFragment fragment = new DepositMoneyFragment();
        fragment.money = money;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_deposit_money;
    }

    @Override
    public void initUI() {
        initPOPView();
        tvTitle.setText(R.string.desposit_money);

        if (TextUtils.isEmpty(money)) {
            tvMoney.setText("0");
        } else {
            tvMoney.setText(money);
            ok.setText("已缴纳保证金");
        }


    }

    @Override
    public void initData() {
        htmlMap.put("html_name", "保证金说明");
        getPresenter().getHtmlDetail(htmlMap);
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
    }




    //ping++调起支付
    @Override
    public void show(String data) {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra(PaymentActivity.EXTRA_CHARGE, data);
        startActivityForResult(intent, Pingpp.REQUEST_CODE_PAYMENT);

    }

    @Override
    public void onGetUser(UserBean data) {
        userBean = data;
        tvMoney.setText(userBean.getMember_deposit_money());

    }

    @Override
    public void onGetHtmlDetail(HtmlBean data) {
        htmlPath = data.getHtml_url();
        WVH5.loadUrl(Constants.BASE_URL + data.getHtml_url());
    }




    @OnClick({R.id.ivLeft, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:
                if (TextUtils.isEmpty(userBean.getMember_deposit_money())) {
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage("缴纳保证金后才可以接单！");
                    builder.setPositiveButton("立即缴纳", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            pop.showAtLocation(parentview, Gravity.BOTTOM, 0, 0);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();
                } else {
                    ToastUtils.showToast(context.getApplicationContext(), "你已经缴纳过保证金");
                }

                break;


        }
    }

    //初始化支付pop
    public void initPOPView() {
        pop = new PopupWindow(context);
        View view = getActivity().getLayoutInflater().inflate(R.layout.pop_ping, null, false);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        TextView cancel_btn = (TextView) view.findViewById(R.id.cancel_btn);
        TextView WX = (TextView) view.findViewById(R.id.WX);
        TextView ZFB = (TextView) view.findViewById(R.id.ZFB);

        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        WX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("channel", "wx");
                getPresenter().Pay(map);
                pop.dismiss();
            }
        });

        ZFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("channel", "alipay");
                getPresenter().Pay(map);
                pop.dismiss();
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");

                if (result.equals("success")) {
                    ToastUtils.showToast(context.getApplicationContext(), "支付成功");
                    getPresenter().getUser(map);

                } else if (result.equals("fail")) {
                    ToastUtils.showToast(context.getApplicationContext(), "支付失败");

                } else if (result.equals("cancel")) {
                } else if (result.equals("invalid")) {
                    ToastUtils.showToast(context.getApplicationContext(), "请先安装微信客户端");
                }
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
            }
        }
    }



    @Override
    public DepositMoneyPressent createPresenter() {
        return new DepositMoneyPressent(getApp());
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
}
