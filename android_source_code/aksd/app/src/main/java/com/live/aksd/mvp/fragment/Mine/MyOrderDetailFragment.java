package com.live.aksd.mvp.fragment.Mine;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.king.base.util.ToastUtils;
import com.live.aksd.R;
import com.live.aksd.bean.OrderBean;
import com.live.aksd.bean.OrderBeanNew;
import com.live.aksd.bean.OrderDetailBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Mine.DetailGoodsAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.MyOrderDetailsPersenter;
import com.live.aksd.mvp.view.Mine.IMyOrderDetailsView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;
import com.pingplusplus.android.PaymentActivity;
import com.pingplusplus.android.Pingpp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * @author Created by stone
 * @since 17/8/25
 */

public class MyOrderDetailFragment extends BaseFragment<IMyOrderDetailsView, MyOrderDetailsPersenter> implements IMyOrderDetailsView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    //订单状态
    @BindView(R.id.top_tittle)
    TextView top_tittle;

    //收货人姓名，电话，地址
    @BindView(R.id.address_name)
    TextView address_name;
    @BindView(R.id.address_phone)
    TextView address_phone;
    @BindView(R.id.address_recept)
    TextView address_recept;

    //商品列表
    @BindView(R.id.goods_recycle)
    EasyRecyclerView goods_recycle;

    //快递方式
    @BindView(R.id.delivery_type)
    TextView delivery_type;
    //买家留言
    @BindView(R.id.leave_message_content)
    TextView leave_message_content;

    //总金额 快递费 优惠卷积分折扣
    @BindView(R.id.total_price)
    TextView total_price;
    @BindView(R.id.price1)
    TextView price1;
    @BindView(R.id.price2)
    TextView price2;
    @BindView(R.id.price3)
    TextView price3;


    //订单号和时间信息
    @BindView(R.id.ll_time)
    LinearLayout ll_time;
    @BindView(R.id.order_no)
    TextView order_no;
    @BindView(R.id.order_creat_time)
    TextView order_creat_time;
    @BindView(R.id.pay_time)
    TextView pay_time;
    @BindView(R.id.order_end_time)
    TextView order_end_time;
    @BindView(R.id.order_send_time)
    TextView order_send_time;


    //底部操作按钮
    @BindView(R.id.ll_btn_group)
    RelativeLayout ll_btn_group;
    @BindView(R.id.operation_button1)
    Button operation_button1;
    @BindView(R.id.operation_button2)
    Button operation_button2;
    @BindView(R.id.operation_button3)
    Button operation_button3;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private PopupWindow payPop;

    private DetailGoodsAdapter adapter;
    private List<OrderBean.OrderGoodsBeansBean> orderGoodsList;

    //电话
    private String number = "";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    private String order_id;
    private String logistics_no;//物流单号
    private String logistics_order_no;//订单号
    private String logistics_pinyin;//公司


    public static MyOrderDetailFragment newIntance(String order_id) {
        Bundle args = new Bundle();
        MyOrderDetailFragment fragment = new MyOrderDetailFragment();
        fragment.order_id = order_id;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("订单详情");
        orderGoodsList = new ArrayList<>();
        adapter = new DetailGoodsAdapter(context, orderGoodsList);
        goods_recycle.setAdapter(adapter);
        goods_recycle.setLayoutManager(new LinearLayoutManager(context));
        adapter.setrefundGoodsClickListener(new DetailGoodsAdapter.RefundGoodsClickListener() {
            @Override
            public void OnrefundGoodsClickListener(View v, OrderBean.OrderGoodsBeansBean data) {

                if (data.getOrder_state().equals("wait_send")) {
                    startRequestRefundFragment(order_id, data.getOrder_goods_id() + "", "1");
                } else if (data.getOrder_state().equals("wait_assessment")) {
                    startRequestRefundFragment(order_id, data.getOrder_goods_id() + "", "2");
                }
            }
        });


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("order_id", order_id);
        getPresenter().getOneOrderDetail(map);
    }

    @Override
    public void onOneOrderDetail(OrderBean data) {
        adapter.clear();
        adapter.addAll(data.getOrderGoodsBeans());
        adapter.notifyDataSetChanged();
        if (data.getOrder_state().equals("wait_pay")) {//待付款
            pay_time.setVisibility(View.GONE);
        } else if (data.getOrder_state().equals("wait_send")) {//待发货
            pay_time.setVisibility(View.VISIBLE);
            pay_time.setText("付款时间：" + data.getPay_time());
            operation_button1.setVisibility(View.GONE);
            operation_button2.setVisibility(View.GONE);
            operation_button3.setText("联系客服");
            for (int i = 0; i < data.getOrderGoodsBeans().size(); i++) {
                data.getOrderGoodsBeans().get(i).setOrder_state("wait_send");
            }
        } else if (data.getOrder_state().equals("wait_receive")) {//待收货
            pay_time.setVisibility(View.VISIBLE);
            pay_time.setText("付款时间：" + data.getPay_time());
            order_send_time.setVisibility(View.VISIBLE);
            order_send_time.setText("发货时间：" + data.getSend_time());
            operation_button2.setText("查看物流");
            operation_button3.setText("确认收货");
        } else if (data.getOrder_state().equals("wait_assessment")) {//待评价
            pay_time.setVisibility(View.VISIBLE);
            pay_time.setText("付款时间：" + data.getPay_time());
            order_send_time.setVisibility(View.VISIBLE);
            order_send_time.setText("发货时间：" + data.getSend_time());
            order_end_time.setVisibility(View.VISIBLE);
            order_end_time.setText("成交时间：" + data.getEnd_time());
            operation_button2.setText("查看物流");
            operation_button3.setText("评价");
            for (int i = 0; i < data.getOrderGoodsBeans().size(); i++) {
                data.getOrderGoodsBeans().get(i).setOrder_state("wait_assessment");
            }
        } else {
            ll_btn_group.setVisibility(View.GONE);
        }

        address_name.setText("收货人:" + data.getAddress_name());
        address_phone.setText("电话:" + data.getAddress_mobile());
        address_recept.setText("收货地址:" + data.getAddress_province() + data.getAddress_city() + data.getAddress_district() + data.getAddress_detail());
        if ("0".equals(data.getExpress_price())) {
            delivery_type.setText("快递免邮");
        } else {
            delivery_type.setText("快递配送");
        }

        top_tittle.setText(data.getOrder_state_show());
        price1.setText("¥" + data.getExpress_price());
        price2.setText("¥" + data.getCoupon_price());
        price3.setText("¥" + data.getDeduct_integral_price());
        total_price.setText("¥" + data.getOrder_actual_price());
        leave_message_content.setText(data.getCustom_remark());
        order_no.setText("订单编号：" + data.getOrder_no());
        order_creat_time.setText("订单创建时间：" + data.getCreate_time());

        //查看物流用到的信息
        logistics_no = data.getLogistics_no();
        logistics_order_no = data.getOrder_no();
        logistics_pinyin = data.getLogistics_name();
        //评价订单用到的信息
        orderGoodsList.clear();
        for (int i = 0; i < data.getOrderGoodsBeans().size(); i++) {
            orderGoodsList.add(data.getOrderGoodsBeans().get(i));
        }
    }


    //取消订单请求返回
    @Override
    public void onCancelOrder(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();
    }

    //确认订单请求返回
    @Override
    public void onConfirmOrder(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void onPayRealOrders(String data) {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra(PaymentActivity.EXTRA_CHARGE, data);
        startActivityForResult(intent, Pingpp.REQUEST_CODE_PAYMENT);
    }

    //拨打客服dialog
    private void callCustomServer() {
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


    //支付popWindow
    public void showPayPop() {
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("order_ids", order_id);
        payPop = new PopupWindow(context);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pop_ping, null);
        payPop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        payPop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        payPop.setBackgroundDrawable(new BitmapDrawable());
        payPop.setFocusable(true);
        payPop.setOutsideTouchable(true);
        payPop.setContentView(view);
        payPop.showAtLocation(getView(), Gravity.BOTTOM, 0, 0);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        TextView cancel_btn = (TextView) view.findViewById(R.id.cancel_btn);
        TextView WX = (TextView) view.findViewById(R.id.WX);
        TextView ZFB = (TextView) view.findViewById(R.id.ZFB);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                payPop.dismiss();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPop.dismiss();
            }
        });
        WX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("channel", "wx");
                map.put("type", "android");
                getPresenter().payRealOrders(map);
                payPop.dismiss();
            }
        });

        ZFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("channel", "alipay");
                map.put("type", "android");
                getPresenter().payRealOrders(map);
                payPop.dismiss();
            }
        });
    }


    @Override
    public MyOrderDetailsPersenter createPresenter() {
        return new MyOrderDetailsPersenter(getApp());
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");

                if (result.equals("success")) {
                    ToastUtils.showToast(context.getApplicationContext(), "支付成功");
                    finish();

                } else if (result.equals("fail")) {
                    ToastUtils.showToast(context.getApplicationContext(), "支付失败");

                } else if (result.equals("cancel")) {
                    ToastUtils.showToast(context.getApplicationContext(), "取消支付");
                } else if (result.equals("invalid")) {
                    ToastUtils.showToast(context.getApplicationContext(), "请先安装微信客户端");
                }
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                //ToastUtils.showToast(context.getApplicationContext(), result + errorMsg + extraMsg);
            }
        }
    }


    @OnClick({R.id.ivLeft, R.id.operation_button1, R.id.operation_button2, R.id.operation_button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.operation_button1:
                //拨打客服电话
                callCustomServer();
                break;
            case R.id.operation_button2:
                if (operation_button2.getText().equals("取消订单")) {//取消订单
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage("确定要取消此订单吗？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            getPresenter().cancelOrder(map);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();
                } else {//打开查看物流

                    startWeb("查看物流", "", "https://m.kuaidi100.com/index_all.html?type=" + logistics_pinyin + "" + "&postid=" + logistics_no, "");
                    //startLogisticsInformationFragment(order_id, logistics_no, logistics_order_no, "order");
                }
                break;
            case R.id.operation_button3:
                //付款or联系客服
                if (operation_button3.getText().equals("付款")) {//订单支付
                    showPayPop();
                } else if (operation_button3.getText().equals("确认收货")) {//确认收货
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage("在你确认收货前，请确保您已经收到货！是否确认收货？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            getPresenter().confirmOrder(map);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();
                } else if (operation_button3.getText().equals("评价")) { //评价订单

                    ArrayList<OrderDetailBean> OrderDetailBean = new ArrayList<>();//传入评论页面值
                    for (int i = 0; i < orderGoodsList.size(); i++) {
                        OrderDetailBean bean = new OrderDetailBean();
                        bean.setGoods_id(orderGoodsList.get(i).getGoods_id());
                        bean.setOrder_id(order_id);
                        bean.setImg(orderGoodsList.get(i).getGoods_img());
                        OrderDetailBean.add(bean);
                    }
                    startGoodsCommentsFragment(OrderDetailBean, order_id);
                } else {
                    callCustomServer();//拨打客服电话
                }
                break;

        }
    }


}
