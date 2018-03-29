package com.live.aksd.mvp.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.RewareAddressBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.fragment.Mine.AddReceiptAddressFragment;
import com.live.aksd.mvp.presenter.WebPresenter;
import com.live.aksd.mvp.view.IWebView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.pingplusplus.android.PaymentActivity;
import com.pingplusplus.android.Pingpp;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * Created by sh-lx on 2017/7/10.
 */

public class JsWebFragment extends BaseFragment<IWebView, WebPresenter> implements IWebView {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivRight2)
    ImageView ivRight2;
    @BindView(R.id.top_toolbar)
    LinearLayout topToolbar;
    @BindView(R.id.ll_top_toolbar)
    LinearLayout ll_top_toolbar;
    @BindView(R.id.parentView)
    LinearLayout parentView;
    @BindView(R.id.tvTitlen)
    TextView tvTitlen;

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.webView)
    WebView WVH5;
    @BindView(R.id.progressBar)
    ProgressBar PBH5;

    private PopupWindow pop;
    private RewareAddressBean rewareAddressbean = new RewareAddressBean();
    HashMap<String, String> map = new HashMap<>();
    private String order_ids;

    private String url = "";
    private String title = "";
    private String type = "";
    private String img = "";
    private Handler mhandler = new Handler();
    private UserBean userBean;
    private String member_id, member_token;
    //分享相关
    private UMImage mImage = null;
    private SHARE_MEDIA mShare_meidia = SHARE_MEDIA.WEIXIN;
    private String mShareUrl = "";
    private String mCoverUrl = "";
    private String mTitle = ""; //标题
    //电话
    private String number = "";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private boolean is_reload = false;

    public static JsWebFragment newInstance(String url, String title, String type, String img) {
        Bundle args = new Bundle();
        JsWebFragment fragment = new JsWebFragment();
        fragment.url = url;
        fragment.title = title;
        fragment.type = type;
        fragment.img = img;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_webwiew;
    }


    @SuppressWarnings("deprecation")
    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface", "NewApi"})
    @Override
    public void initUI() {

        tvTitle.setText(title);
        if (type.equals("1")) {
            topToolbar.setVisibility(View.GONE);
        } else if (type.equals("2")) {
            tvTitle.setTextColor(Color.parseColor("#333333"));
            ivLeft.setImageResource(R.drawable.btn_back_selector);
        } else if (type.equals("3")) {
            topToolbar.setVisibility(View.GONE);
            ll_top_toolbar.setVisibility(View.VISIBLE);
            tvTitlen.setText(title);
        } else if (type.equals("4")) {
            ivRight2.setVisibility(View.VISIBLE);
        } else if (type.equals("5")) {
            ivRight2.setVisibility(View.VISIBLE);
            ivRight2.setImageResource(R.drawable.gwc_wx);
        } else if (type.equals("6")) {
            ivRight2.setVisibility(View.VISIBLE);
            ivRight2.setImageResource(R.drawable.searchx);
        }

        WVH5.getSettings().setJavaScriptEnabled(true); // 加上这句话才能使用javascript方法
     /*   WVH5.getSettings().setDefaultTextEncodingName("UTF-8"); // 加上这句话才能使用javascript方法
        WVH5.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);*/
        WVH5.requestFocus();// 如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件�?
        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型�? 1、LayoutAlgorithm.NARROW_COLUMNS
         * ：�?应内容大�? 2、LayoutAlgorithm.SINGLE_COLUMN : 适应屏幕，内容将自动缩放
         */
        WVH5.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // 启动缓存
        WVH5.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        WVH5.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        //WVH5.getSettings().setAppCacheEnabled(false);
        // WVH5.clearCache(true);
        WVH5.addJavascriptInterface(new JavaScriptInterface(getActivity()), "android");// h5回调java函数

        if (Build.VERSION.SDK_INT >= 19) {// 页面finish后再发起图片加载
            WVH5.getSettings().setLoadsImagesAutomatically(true);
        } else {
            WVH5.getSettings().setLoadsImagesAutomatically(false);
        }
        /*
         * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
		 * {//加载白块同时界面闪烁现象 关闭硬件加�? WVH5.setLayerType(View.LAYER_TYPE_SOFTWARE,
		 * null); }
		 */
        WVH5.setWebChromeClient(new WebChromeClient() {
            // 进度�?
            public void onProgressChanged(WebView view, int newProgress) {
                if (PBH5 != null) {
                    if (newProgress == 100) {
                        PBH5.setVisibility(View.GONE);//加载完网页进度条消失
                    } else {
                        PBH5.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                        PBH5.setProgress(newProgress);//设置进度值
                    }
                }
            }
        });

        WVH5.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });


        WVH5.setOnLongClickListener(new View.OnLongClickListener() {// 屏蔽系统长按复制 粘贴事件
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        Map<String, String> extraHeaders = new HashMap<String, String>();
        extraHeaders.put("Referer", "http://www.google.com");// 防止盗链
        WVH5.loadUrl(url, extraHeaders);
        //WVH5.loadUrl("http://hbr.tstweiguanjia.com/hbrH5/activity.html?activity_id=19", extraHeaders);

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if (userBean != null) {
            member_id = userBean.getMember_id();
            member_token = userBean.getMember_token();
        }
        //初始化支付pop
        initPOPView();
    }

    //初始化支付pop
    public void initPOPView() {
        map.put("member_id", member_id);
        map.put("member_token", member_token);
        map.put("type", "android");
        map.put("order_ids", order_ids);
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

    /*    parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });*/
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayResultFragment("2");
                finish();
                pop.dismiss();
            }
        });

        WX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("channel", "wx");
                getPresenter().payRealOrders(map);
                pop.dismiss();
            }
        });

        ZFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("channel", "alipay");
                getPresenter().payRealOrders(map);
                pop.dismiss();
            }
        });

    }

    @Override
    public WebPresenter createPresenter() {
        return new WebPresenter(getApp());
    }


    @Override
    public void OnInsertAddress(String data) {
        ToastUtils.showToast(context.getApplicationContext(), "添加成功");
        WVH5.reload();
    }

    @Override
    public void onPayRealOrders(String data) {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra(PaymentActivity.EXTRA_CHARGE, data);
        startActivityForResult(intent, Pingpp.REQUEST_CODE_PAYMENT);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showToast(context.getApplicationContext(), e.getMessage().toString());
    }


    class JavaScriptInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page 由Js调用执行Native本地Java方法
         */
        @JavascriptInterface
        public void showToast(String toast) {
            WVH5.loadUrl("javascript:init('abc')");
        }


        //首页
        @JavascriptInterface
        public void appGoAppHome() {
            EventBus.getDefault().post(new FirstEvent("20"));
            finish();
        }

        @JavascriptInterface
        public void appGoHome() {
            EventBus.getDefault().post(new FirstEvent("20"));
            finish();
        }

        //查看购物车
        @JavascriptInterface
        public void appCart(String toast) {
            startWeb("购物车", "", toast, "1");
        }

        //商品分类
        @JavascriptInterface
        public void appGoodslist(String toast) {
            startWeb("商品分类", "", toast, "6");
        }
        //积分商品
        @JavascriptInterface
        public void appGoodslistIntegral(String toast) {
            startWeb("积分商品", "", toast, "6");
        }

        //商品详情
        @JavascriptInterface
        public void appGoGoods(String toast) {
            startWeb("商品详情", "", toast, "5");
        }

        @JavascriptInterface
        public void appGoodpinglun(String toast) {
            startWeb("订单详情", "", toast, "");
        }

        //商品支付
        @JavascriptInterface
        public void appGoConfirm(String toast) {
            startWeb("确认订单", "", toast, "");
        }

        @JavascriptInterface
        public void appGoPay(String toast) {
            map.put("order_ids", toast);
            pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
        }

        //商品搜索
        @JavascriptInterface
        public void appSearch(String toast) {
            startJSWeb(Constants.BASE_URL+"app-mall1/index.html#/search?&member_id=" + userBean.getMember_id() + "&member_token=" + userBean.getMember_token() + "&add=" + toast, toast);
        }

        //商品分享
        @JavascriptInterface
        public void appShareGood(String toast, String goods_tittle, String goods_dec, String goods_img) {
            mShareUrl = toast;
            mTitle = goods_tittle;
            mCoverUrl = goods_dec;
            mImage = new UMImage(context, Constants.BASE_URL + goods_img);
            showShareDialog();

        }

        //商城banner外链
        @JavascriptInterface
        public void appGoLink(String toast, String tittle) {
            startWeb(tittle, "", toast, "");
        }

        //banner
        @JavascriptInterface
        public void appGoodsdetail(String toast) {
            startWeb("商品详情", "", toast, "5");
        }


        //收获地址
        @JavascriptInterface
        public void appGoaddress() {
            //startRewareAddressFragment();
            map.clear();
            rewareAddressbean = new RewareAddressBean();
            startAddAddress(rewareAddressbean, "1");
        }

        //积分
        @JavascriptInterface
        public void appGoPoints() {
            startMyScoresFragment();
        }

        //优惠卷
        @JavascriptInterface
        public void appGoCoupon() {
            startCouponFragment();
        }

        //收货地址
        @JavascriptInterface
        public void appGoAddress() {
            startRewareAddressFragment();
        }

        //收藏
        @JavascriptInterface
        public void appGoCollection() {
            startMyCollectionFragment();
        }


        //订单
        @JavascriptInterface
        public void appOrderList(String state) {
            switch (state) {
                case "1":
                    startMyOrderFragment("0");
                    break;
                case "2":
                    startMyOrderFragment("1");
                    break;
                case "3":
                    startMyOrderFragment("3");
                    break;
                case "4":
                    startMyOrderFragment("4");
                    break;
            }
        }

        //售后
        @JavascriptInterface
        public void appSH() {
            startAfterSaleFragment();
        }

        //联系商家 打电话
        @JavascriptInterface
        public void appJumpCall() {
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

    }

    //----------------------------------------添加收货地址----------------------------------------------//

    private void startAddAddress(RewareAddressBean rewareAddressbean, final String state) {
        AddReceiptAddressFragment fragment = AddReceiptAddressFragment.newInstance(rewareAddressbean, state);

        fragment.setOpenSelectClickListener(new AddReceiptAddressFragment.OpenSelectOnclickListener() {
            @Override
            public void onOpen(String state) {
                cityPicker(state);
            }

            @Override
            public void onOk(RewareAddressBean bean, String state) {
                if (state.equals("2")) {
                    map.put("address_id", bean.getAddress_id());
                }
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("address_mobile", bean.getAddress_mobile());
                map.put("address_name", bean.getAddress_name());
                map.put("address_province", bean.getAddress_province());
                map.put("address_city", bean.getAddress_city());
                map.put("address_detail", bean.getAddress_detail());
                map.put("address_zip_code", bean.getAddress_zip_code());
                map.put("address_district", bean.getAddress_district());
                map.put("is_default", "0");
                getPresenter().getInsertAddress(map);

            }

        });
        fragment.show(getFragmentManager(), AddReceiptAddressFragment.class.getSimpleName());
    }

    /**
     * 显示城市列表
     */
    private void cityPicker(final String state) {
        CityPicker cityPicker = new CityPicker.Builder(getActivity())
                .textSize(16)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#fafafa")
                .titleTextColor("#666666")
                .backgroundPop(0xa0000000)
                .confirTextColor("#EC6B1A")
                .cancelTextColor("#999999")
                .province("浙江省")
                .city("金华市")
                .district("义乌市")
                .textColor(Color.parseColor("#666666"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(10)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                rewareAddressbean.setAddress_province(province);
                rewareAddressbean.setAddress_city(city);
                rewareAddressbean.setAddress_district(district);
                rewareAddressbean.setAddress_zip_code(code);
                startAddAddress(rewareAddressbean, state);

            }

            @Override
            public void onCancel() {
                startAddAddress(rewareAddressbean, state);
            }

        });
    }


    //----------------------------------------打电话----------------------------------------------//
    private void sq() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                // 弹窗需要解释为何需要该权限，再次请求授权
                //Toast.makeText(MainActivity.this, "请授权！", Toast.LENGTH_LONG).show();
                ToastUtils.showToast(context.getApplicationContext(), "请授权！");
                // 帮跳转到该应用的设置界面，让用户手动授权
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                // 不需要解释为何需要该权限，直接请求授权
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
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
    public void onResume() {
        super.onResume();
        if (is_reload) {
            WVH5.reload();  //刷新
        }
    }

    //----------------------------------------分享-----------------------------------------------//
    private void showShareDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.share_dialog, null);
        final AlertDialog mDialog = new AlertDialog.Builder(context).create();
        mDialog.setCanceledOnTouchOutside(true); // 外部点击取消
        mDialog.show();// 显示创建的AlertDialog，并显示，必须放在Window设置属性之前
        Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(view);//这一步必须指定，否则不出现弹窗
            WindowManager.LayoutParams mParams = window.getAttributes();
            mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
            window.setBackgroundDrawableResource(android.R.color.white);
            window.setWindowAnimations(R.style.AnimBottom);
            window.setAttributes(mParams);
        }

        TextView btn_wx = (TextView) view.findViewById(R.id.btn_share_wx);
        TextView btn_circle = (TextView) view.findViewById(R.id.btn_share_circle);
        TextView btn_qq = (TextView) view.findViewById(R.id.btn_share_qq);
        TextView btn_qzone = (TextView) view.findViewById(R.id.btn_share_qzone);
        TextView btn_wb = (TextView) view.findViewById(R.id.btn_share_wb);
        TextView btn_cancle = (TextView) view.findViewById(R.id.btn_share_url);

        btn_wx.setOnClickListener(mShareBtnClickListen);
        btn_circle.setOnClickListener(mShareBtnClickListen);
        btn_qq.setOnClickListener(mShareBtnClickListen);
        btn_qzone.setOnClickListener(mShareBtnClickListen);
        btn_wb.setOnClickListener(mShareBtnClickListen);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager manager = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
                manager.setText(mShareUrl);
                Toast.makeText(getContext().getApplicationContext(), "复制链接成功", Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });
    }

    private View.OnClickListener mShareBtnClickListen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_share_wx:
                    mShare_meidia = SHARE_MEDIA.WEIXIN;
                    break;
                case R.id.btn_share_circle:
                    mShare_meidia = SHARE_MEDIA.WEIXIN_CIRCLE;
                    break;
                case R.id.btn_share_qq:
                    mShare_meidia = SHARE_MEDIA.QQ;
                    break;
                case R.id.btn_share_qzone:
                    mShare_meidia = SHARE_MEDIA.QZONE;
                    break;

                default:
                    break;
            }

            ShareAction shareAction = new ShareAction(getActivity());
            UMWeb web = new UMWeb(mShareUrl);
            web.setThumb(mImage);
            web.setTitle(mTitle);
            web.setDescription(mCoverUrl);//描述

            shareAction.withMedia(web);
            shareAction.setCallback(umShareListener);
            shareAction.setPlatform(mShare_meidia).share();
        }
    };


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(getActivity(), "分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), "分享失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");

                if (result.equals("success")) {
                    //ToastUtils.showToast(context.getApplicationContext(), "支付成功");
                    startPayResultFragment("1");
                    finish();
                } else if (result.equals("fail")) {
                    //ToastUtils.showToast(context.getApplicationContext(), "支付失败");
                    startPayResultFragment("2");
                    finish();

                } else if (result.equals("cancel")) {
                    ToastUtils.showToast(context.getApplicationContext(), "取消支付");
                    startPayResultFragment("2");
                    finish();

                } else if (result.equals("invalid")) {
                    ToastUtils.showToast(context.getApplicationContext(), "请先安装微信客户端");
                }

            }
        }
    }


    @OnClick({R.id.ivLeft, R.id.ivRight2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivRight2:

                if (title.equals("商品详情")) {
                    startWeb("购物车", "", Constants.BASE_URL+"app-mall1/index.html#/cart?&member_id=" + userBean.getMember_id() + "&member_token=" + userBean.getMember_token(), "1");
                } else if (title.equals("商品分类")) {
                    startJSWeb(Constants.BASE_URL+"app-mall1/index.html#/search?&member_id=" + userBean.getMember_id() + "&member_token=" + userBean.getMember_token(), "");
                }
                break;
        }
    }


}
