package com.live.aksd.mvp.fragment.WorkOrder;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.WorkOrderDetailBean;
import com.live.aksd.mvp.activity.LocationDemo;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.WordOrder.WaitOrderDetailPresenter;
import com.live.aksd.mvp.view.WordOrder.IWaitOrderDetailView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;
import com.pingplusplus.android.PaymentActivity;
import com.pingplusplus.android.Pingpp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * Created by liuxiaoxiang on 2018/3/7.
 */

public class WaitOrderDetailFragment extends BaseFragment<IWaitOrderDetailView, WaitOrderDetailPresenter> implements IWaitOrderDetailView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.orderState)
    TextView orderState;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvNote)
    TextView tvNote;
    @BindView(R.id.releaseTime)
    TextView releaseTime;

    @BindView(R.id.tvMap)
    TextView tvMap;
    @BindView(R.id.tvCustomer)
    TextView tvCustomer;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.parentView)
    LinearLayout parentView;

    @BindView(R.id.tvMethod)
    TextView tvMethod;
    @BindView(R.id.tvArea)
    TextView tvArea;
    @BindView(R.id.tvRequest)
    TextView tvRequest;
    @BindView(R.id.hopeStartTime)
    TextView hopeStartTime;
    @BindView(R.id.hopeEndTime)
    TextView hopeEndTime;
    Unbinder unbinder;
    private String order_id;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private WorkOrderDetailBean workOrderDetailBean;
    private PopupWindow pop;
    //电话
    private String number;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    public static WaitOrderDetailFragment newInstance(String order_id) {
        Bundle args = new Bundle();
        WaitOrderDetailFragment fragment = new WaitOrderDetailFragment();
        fragment.order_id = order_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_wait_order_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.wait_order_detail);
        initPOPView();
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("order_id", order_id);
        getPresenter().getWorkOrderDetail(map);
    }

    @Override
    public void onGetWorkOrderDetail(WorkOrderDetailBean data) {
        workOrderDetailBean = data;
        tvName.setText(getString(R.string.detail_name) + data.getOrder_name());
        tvPhone.setText(getString(R.string.detail_phone) + data.getOrder_phone());
        tvAddress.setText(getString(R.string.detail_address) + data.getOrder_address_province() + " " + data.getOrder_address_city() + " " + data.getOrder_address_district() + " " + data.getOrder_address_detail());
        tvContent.setText(getString(R.string.detail_content) + data.getOrder_subscribe_content());
        if (!TextUtils.isEmpty(data.getOrder_subscribe_note())) {
            tvNote.setText(getString(R.string.detail_note) + data.getOrder_subscribe_note());
        }
        releaseTime.setText(getString(R.string.detail_release_time) + data.getOrder_create_time());
        hopeStartTime.setText(getString(R.string.hope_start_time) + data.getOrder_hope_service_time());

        //Order_type 0水电安装 1水电维修
        if ("0".equals(data.getOrder_type())) {
            hopeEndTime.setText(getString(R.string.hope_end_time) + data.getHope_complete_time());
            tvArea.setText(getString(R.string.detail_are) + data.getWork_area() + getString(R.string.square_meter));
            tvMethod.setText(getString(R.string.detail_method) + data.getWork_way());
            if (!TextUtils.isEmpty(data.getOrder_subscribe_note())) {
                tvRequest.setText(getString(R.string.detail_request) + data.getWork_requirements());
            }
        } else {
            hopeEndTime.setVisibility(View.GONE);
            tvArea.setVisibility(View.GONE);
            tvMethod.setVisibility(View.GONE);
            tvRequest.setVisibility(View.GONE);
        }

    }

    @Override
    public void onGetUser(UserBean data) {
        saveUser(data);
        userBean = data;

    }

    @Override
    public void show(String data) {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra(PaymentActivity.EXTRA_CHARGE, data);
        startActivityForResult(intent, Pingpp.REQUEST_CODE_PAYMENT);
    }

    @Override
    public void onUpdateOrderState(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();
    }





    //----------------------------------------pay----------------------------------------------//

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
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.pay_success));
                    getPresenter().getUser(map);

                } else if (result.equals("fail")) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.pay_falid));

                } else if (result.equals("cancel")) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.pay_cancel));
                } else if (result.equals("invalid")) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.please_install_wx));
                }
            }
        }
    }


    //----------------------------------------call----------------------------------------------//
    private void sq() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                ToastUtils.showToast(context.getApplicationContext(), getString(R.string.authorized));
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            CallPhone();
        }
    }

    private void CallPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + number);
        intent.setData(data);
        startActivity(intent);
    }


    @OnClick({R.id.ivLeft, R.id.ok, R.id.tvMap, R.id.tvCustomer})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.tvCustomer:
                number = getText(R.string.lxrPhone).toString();
                final CustomDialog.Builder phoneBuilder = new CustomDialog.Builder(context);
                phoneBuilder.setMessage(getString(R.string.call) + number + getString(R.string.customer_phone));
                phoneBuilder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sq();
                        dialog.dismiss();
                    }
                });
                phoneBuilder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                phoneBuilder.onCreate().show();
                break;
            case R.id.ok:
                if (TextUtils.isEmpty(userBean.getMember_deposit_money())) {
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage(getString(R.string.pay_money));
                    builder.setPositiveButton(getString(R.string.pay_son), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();

                } else {

                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage(getString(R.string.accept_order));
                    builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            map.clear();
                            map.put("member_id", userBean.getMember_id());
                            map.put("member_token", userBean.getMember_token());
                            map.put("order_id", order_id);
                            map.put("type", "worker_accept");
                            getPresenter().updateOrderState(map);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();

                }
                break;
            case R.id.tvMap:
                Intent intent = new Intent(context, LocationDemo.class);
                intent.putExtra(Constants.Latitude, workOrderDetailBean.getOrder_address_latitude());
                intent.putExtra(Constants.Longitude, workOrderDetailBean.getOrder_address_longitude());
                startActivity(intent);
                break;
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
    public WaitOrderDetailPresenter createPresenter() {
        return new WaitOrderDetailPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
