package com.live.aksd.mvp.fragment.Mine;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.AfterSaleDetailBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.AfterSaleDetailPersenter;
import com.live.aksd.mvp.view.Mine.IAfterSaleDetailView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * @author Created by stone
 * @since 17/9/1
 */

public class AfterSaleDetailFragment extends BaseFragment<IAfterSaleDetailView, AfterSaleDetailPersenter> implements IAfterSaleDetailView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ordertype_tittle)
    TextView ordertype_tittle;
    @BindView(R.id.ordertype_time)
    TextView ordertype_time;


    @BindView(R.id.order_no)
    TextView order_no;
    @BindView(R.id.create_time)
    TextView create_time;
    @BindView(R.id.reason_name)
    TextView reason_name;
    @BindView(R.id.refund_price)
    TextView refund_price;
    @BindView(R.id.cancel_request)
    RelativeLayout cancel_request;

    @BindView(R.id.pay_timer)
    TextView pay_timer;


    @BindView(R.id.refund_money_yv)
    TextView refund_money;


    @BindView(R.id.order_state_tv)
    TextView order_state_tv;
  /*  @BindView(R.id.order_state_btn)
    TextView order_state_btn;*/

    @BindView(R.id.goods_img)
    ImageView goods_img;
    @BindView(R.id.goods_name)
    TextView goods_name;
    @BindView(R.id.goods_norm)
    TextView goods_norm;
    @BindView(R.id.num)
    TextView num;

    @BindView(R.id.goods_price)
    TextView goods_price;


    @BindView(R.id.btn_one)
    Button btn_one;
    @BindView(R.id.btn_two)
    Button btn_two;
    @BindView(R.id.btn_three)
    Button btn_three;


    @BindView(R.id.rl_refund_account)
    RelativeLayout rl_refund_account;
    @BindView(R.id.refund_pay_type)
    TextView refund_pay_type;
    @BindView(R.id.account_tv)
    TextView account_tv;


    @BindView(R.id.ll_picture)
    LinearLayout ll_picture;
    @BindView(R.id.ll_logistics)
    LinearLayout ll_logistics;
    @BindView(R.id.logistics_company)
    TextView logistics_company;
    @BindView(R.id.logistics_no)
    TextView logistics_num;
    @BindView(R.id.logistics_phone)
    TextView logistics_phone;
    @BindView(R.id.logistics_name)
    TextView logistics_name;
    @BindView(R.id.logistics_start_time)
    TextView logistics_start_time;


    @BindView(R.id.refund_img1)
    ImageView refund_img1;
    @BindView(R.id.refund_img2)
    ImageView refund_img2;
    @BindView(R.id.refund_img3)
    ImageView refund_img3;


    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private String refund_id;

    //物流
    private String order_id;
    private String logistics_no;
    private String logistics_order_no;
    private String logistics_order_state = "";
    //电话
    private String number = "";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    public static AfterSaleDetailFragment newIntance(String refund_id) {
        Bundle args = new Bundle();
        AfterSaleDetailFragment fragment = new AfterSaleDetailFragment();
        fragment.refund_id = refund_id;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_after_sale_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("订单详情");
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("refund_id", refund_id);
        getPresenter().getRefundDetail(map);

    }


    @Override
    public void onGetRefundDetail(AfterSaleDetailBean data) {
        if (!TextUtils.isEmpty(data.getGoodsBean().getMerchantsBean().getContact_mobile())) {
            number = data.getGoodsBean().getMerchantsBean().getContact_mobile();//商家电话
        }
        if (!TextUtils.isEmpty(data.getLogistics_no())) {
            logistics_no = data.getLogistics_no();
        }

        order_id = data.getOrder_id();
        ordertype_time.setVisibility(View.VISIBLE);
        ordertype_time.setText(data.getAudit_time());
        order_no.setText("订单编号：" + data.getOrder_no());
        create_time.setText("订单创建时间：" + data.getCreate_time());
        pay_timer.setText("付款时间:" + data.getOrderBean().getPay_time());
        reason_name.setText("退款原因：" + data.getReason_name());
        refund_price.setText("退款金额：" + data.getRefund_price());
        refund_money.setText("¥" + data.getRefund_price());
        goods_name.setText(data.getOrderGoodsBean().getGoods_name());
        goods_norm.setText("规格：" + data.getOrderGoodsBean().getSpecification_name());
        goods_price.setText("¥" + data.getOrderGoodsBean().getGoods_price());
        num.setText("x" + data.getRefund_count());
        Glide.with(getContext())
                .load(Constants.BASE_URL + data.getOrderGoodsBean().getGoods_img())
                .placeholder(R.mipmap.live_default)
                .into(goods_img);

        if (data.getRefund_type().equals("money")) {
            //wait_review：等待审核 accept:接受 refuse：拒绝 end:退款成功
            if (data.getRefund_state().equals("wait_review")) {
                ordertype_tittle.setText("等待商家审核");// 联系商家  取消退款 & 退货中 取消退款
                cancel_request.setVisibility(View.VISIBLE);
                order_state_tv.setText("退款中");
                order_state_tv.setText("退款中");
                //order_state_btn.setVisibility(View.GONE);
                // order_state_btn.setText("取消退款");
                btn_three.setVisibility(View.VISIBLE);
                btn_three.setText("取消退款");
            } else if (data.getRefund_state().equals("accept")) {
                ordertype_time.setVisibility(View.VISIBLE);
                ordertype_time.setText(data.getCreate_time());
                ordertype_tittle.setText("恭喜您！商家已同意您的退款申请！");
                cancel_request.setVisibility(View.VISIBLE);
                order_state_tv.setText("退款中");
                //order_state_btn.setVisibility(View.GONE);
            } else if (data.getRefund_state().equals("refuse")) {
                ordertype_tittle.setText("对不起！商家已拒绝了您的退货申请！");
            } else if (data.getRefund_state().equals("end")) {
                ordertype_tittle.setText("恭喜您！退款成功！");
                ordertype_time.setVisibility(View.VISIBLE);
                ordertype_time.setText(data.getEnd_time());

            }
        } else {
            ll_picture.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(Constants.BASE_URL + data.getRefund_img1())
                    .placeholder(R.mipmap.live_default)
                    .into(refund_img1);
            Glide.with(getContext())
                    .load(Constants.BASE_URL + data.getRefund_img2())
                    .placeholder(R.mipmap.live_default)
                    .into(refund_img2);
            Glide.with(getContext())
                    .load(Constants.BASE_URL + data.getRefund_img3())
                    .placeholder(R.mipmap.live_default)
                    .into(refund_img3);
            //wait_review：等待审核 accept:接受 refuse：拒绝 end:退款成功
            if (data.getRefund_state().equals("wait_review")) {// 联系商家 查看物流 取消退货 & 退货中 取消退货
                ordertype_tittle.setText("等待商家审核");
                cancel_request.setVisibility(View.VISIBLE);
                order_state_tv.setText("退货中");
                //order_state_btn.setText("取消退货");
                btn_one.setText("联系客服");
                btn_three.setVisibility(View.VISIBLE);
                btn_three.setText("取消退货");
            } else if (data.getRefund_state().equals("accept")) {//填写退货物流 联系商家 & 退货中 取消退货
                ordertype_time.setVisibility(View.VISIBLE);
                ordertype_time.setText(data.getCreate_time());
                if (TextUtils.isEmpty(data.getLogistics_company())) {
                    ordertype_tittle.setText("恭喜您！商家已同意您的退货申请！");
                    cancel_request.setVisibility(View.VISIBLE);
                    order_state_tv.setText("退货中");
                    //order_state_btn.setVisibility(View.GONE);
                    btn_one.setText("联系客服");
                    btn_three.setVisibility(View.VISIBLE);
                    btn_three.setText("填写退货物流");
                } else {
                    ordertype_tittle.setText("等待商家回收退回商品！请耐心等待！");
                    btn_one.setText("联系客服");
                    ll_logistics.setVisibility(View.VISIBLE);
                    logistics_company.setText("退货物流：" + data.getLogistics_company());
                    logistics_num.setText("退货单号：" + data.getLogistics_no());
                    logistics_phone.setText("联系电话：" + data.getLogistics_phone());
                    logistics_name.setText("退货人姓名：" + data.getLogistics_name());
                    logistics_start_time.setText("提交时间：" + data.getLogistics_time());

                }
            } else if (data.getRefund_state().equals("refuse")) {
                ordertype_tittle.setText("对不起！商家已拒绝了您的退货申请！");
                btn_one.setText("联系客服");
            } else if (data.getRefund_state().equals("end")) {
                ordertype_tittle.setText("恭喜您！退货成功！");
                ordertype_time.setVisibility(View.VISIBLE);
                ordertype_time.setText(data.getEnd_time());
                btn_one.setText("联系客服");
                ll_logistics.setVisibility(View.VISIBLE);
                logistics_company.setText("退货物流：" + data.getLogistics_company());
                logistics_num.setText("退货单号：" + data.getLogistics_no());
                logistics_phone.setText("联系电话：" + data.getLogistics_phone());
                logistics_name.setText("退货人姓名：" + data.getLogistics_name());
                logistics_start_time.setText("提交时间：" + data.getLogistics_time());

            }
        }


    }

    @Override
    public void onCancleRefundOrder(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data, 1000);
        finish();
    }

    @Override
    public AfterSaleDetailPersenter createPresenter() {
        return new AfterSaleDetailPersenter(getApp());
    }


    //----------------------------------------打电话----------------------------------------------//
    private void sq() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                ToastUtils.showToast(context.getApplicationContext(), "请授权！");
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                // 不需要解释为何需要该权限，直接请求授权
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            // 已经获得授权，可以打电话
            CallPhone();
        }
    }

    private void CallPhone() {
        Intent intent = new Intent(); // 意图对象：动作 + 数据
        intent.setAction(Intent.ACTION_CALL); // 设置动作
        Uri data = Uri.parse("tel:" + number); // 设置数据
        intent.setData(data);
        startActivity(intent); // 激活Activity组件
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

    @OnClick({R.id.ivLeft, R.id.btn_one, R.id.btn_three, R.id.btn_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.btn_one:
                if (btn_one.getText().equals("联系商家")) {
                    if (TextUtils.isEmpty(number)) {
                        ToastUtils.showToast(context.getApplicationContext(), "抱歉，暂时无法获取商家联系电话，请选择其它方式");
                        return;
                    }

                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage("是否拨打" + number + "商家电话？");
                    builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            sq();
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();
                } else {
                    number = getText(R.string.lxrPhone).toString();
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage("是否拨打" + number + "客服电话？");
                    builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            sq();
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();
                }
                break;
            case R.id.btn_two://退货审核时查看物流
                startLogisticsInformationFragment(order_id, logistics_no, logistics_order_no, "refund");
                break;
            case R.id.btn_three:
                if (btn_three.getText().equals("取消退货")) {
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage("确认是否取消退货申请？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            getPresenter().cancleRefundOrder(map);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();

                } else if (btn_three.getText().equals("取消退款")) {
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage("确认是否取消退款申请？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            getPresenter().cancleRefundOrder(map);
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
                    startRefundLogisticsFragment(refund_id);
                }
                break;


        }
    }


}
