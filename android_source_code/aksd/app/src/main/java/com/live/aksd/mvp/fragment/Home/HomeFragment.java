package com.live.aksd.mvp.fragment.Home;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.AppVersionDetailBean;
import com.live.aksd.bean.BannerBean;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.GrabSingleBean;
import com.live.aksd.bean.StarMemberBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Home.MasterAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Home.HomePresenter;
import com.live.aksd.mvp.view.Home.IHomeView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;
import com.pingplusplus.android.PaymentActivity;
import com.pingplusplus.android.Pingpp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sh-lx on 2017/8/21.
 */

public class HomeFragment extends BaseFragment<IHomeView, HomePresenter> implements IHomeView {
    View loadMore;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.parentView)
    LinearLayout parentView;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.convenientBanner)
    ConvenientBanner<BannerBean> convenientBanner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvNote)
    TextView tvNote;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvNull)
    TextView tvNull;

    private List<BannerBean> bannerList;
    private UserBean userBean;
    private List<StarMemberBean> startList;
    private MasterAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> bannerMap = new HashMap<>();
    private Map<String, String> versionMap = new HashMap<>();
    private GrabSingleBean grabSingleBean;
    private PopupWindow pop;

    //倒计时
    private int sec = 0;
    private Timer localTimer;

    private String versionName;
    private String server_address;
    //定位
    private LocationClientOption mOption;
    private LocationClient mClient;
    private static final int BAIDU_READ_PHONE_STATE = 100;
    private String City;
    private String District;
    private double[] positions = new double[2];
    private int page = 1;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 2:
                    int seconds = (int) msg.getData().get("time");
                    int hour1 = seconds / 3600;
                    int minute1 = (seconds - hour1 * 3600) / 60;
                    int second1 = (seconds - hour1 * 3600 - minute1 * 60);
                    String ho1 = String.valueOf(hour1);
                    String mi1 = String.valueOf(minute1);
                    String se1 = String.valueOf(second1);

                    if (hour1 < 10) {
                        ho1 = "0" + hour1;
                    }
                    if (minute1 < 10) {
                        mi1 = "0" + minute1;
                    }
                    if (second1 < 10) {
                        se1 = "0" + second1;
                    }
                    tvTime.setText("剩余时间 " + ho1 + ":" + mi1 + ":" + se1);
                    if (ho1.equals("00") & mi1.equals("00") & se1.equals("00")) {
                        tvNull.setVisibility(View.VISIBLE);
                        if (localTimer != null) {
                            localTimer.cancel();
                        }
                    }
                    break;
                case 1:
                    tvLocation.setText(District);
                    break;
            }

        }
    };

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI() {
        startList = new ArrayList<>();
        bannerList = new ArrayList<>();

        //明星师傅
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MasterAdapter(context, startList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startWeb("风采展示", "", Constants.BASE_URL + adapter.getItem(position).getStar_worker_info(), "");
            }
        });
        //下拉刷新
        refreshlayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (scrollView.getScrollY() == 0) {
                    refreshlayout.setRefreshing(true);
                    refresh();
                }
            }
        });
        recyclerView.setNestedScrollingEnabled(false);

    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        versionMap.put("version_type", "android");
        getPresenter().getAppVersionDetail(versionMap);

        refresh();
        initPopView();
        //判断是否为android6.0系统版本，如果是，需要动态添加权限
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        } else {
            initLocation();
        }
        //获取版本号
        versionName = getVersionName();

    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    //刷新
    public void refresh() {
        page = 1;
        initLocation();
        bannerMap.put("banner_position", "app_home");
        getPresenter().getBanner(bannerMap);
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getWorkOrderByDistance(map);
        map.put("limit", Integer.MAX_VALUE + "");
        getPresenter().getStarMember(map);


    }


    @Override
    public void onGetBanner(List<BannerBean> list) {
        if (convenientBanner != null) {
            toSetList(bannerList, list, false);
            convenientBanner.setPages(new CBViewHolderCreator() {
                @Override
                public Holder<BannerBean> createHolder() {
                    return new ImageHolder();
                }
            }, bannerList)
                    .setPageIndicator(new int[]{R.drawable.icon_dot_nor, R.drawable.icon_dot_pre})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

            if (!convenientBanner.isTurning()) {
                convenientBanner.startTurning(4000);
            }
            convenientBanner.notifyDataSetChanged();
        }
    }


    public class ImageHolder implements Holder<BannerBean> {
        private ImageView iv;

        @Override
        public View createView(Context context) {
            iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            return iv;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerBean data) {
            Glide.with(context).load(Constants.BASE_URL + data.getBanner_img()).placeholder(R.drawable.live_default).error(R.drawable.live_default).into(iv);
        }
    }


    @Override
    public void getStarMember(List<StarMemberBean> list) {
        refreshlayout.setRefreshing(false);
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getWorkOrderByDistance(GrabSingleBean bean) {

        if (!TextUtils.isEmpty(bean.getOrder_id())) {
            grabSingleBean = bean;
            tvNull.setVisibility(View.GONE);
            tvContent.setText(bean.getOrder_subscribe_content());
            if (!TextUtils.isEmpty(bean.getOrder_subscribe_note())) {
                tvNote.setText(bean.getOrder_subscribe_note());
            }
            tvAddress.setText(bean.getOrder_address_province() + "-" + bean.getOrder_address_city() + "-" + bean.getOrder_address_district() + "-" + bean.getOrder_address_detail());
            long startT = fromDateStringToLong(dqsj());
            long endT = fromDateStringToLong(bean.getOrder_update_time()) + 1000 * 60 * 20;
            if (startT > endT) {
                return;
            }
            if (startT == endT) {
                if (localTimer != null) {
                    localTimer.cancel();
                }
                return;
            }
            sec = (int) (endT - startT) / 1000;
            int hour = (int) sec / 3600;  //共计小时数
            int minute = (sec - hour * 3600) / 60;
            int second = (sec - hour * 3600 - minute * 60);
            if (localTimer != null) {
                localTimer.cancel();
            }

            String se = String.valueOf(second);
            String mi = String.valueOf(minute);
            String ho = String.valueOf(hour);
            if (hour < 10) {
                ho = "0" + hour;
            }
            if (minute < 10) {
                mi = "0" + minute;
            }
            if (second < 10) {
                se = "0" + second;
            }
            tvTime.setText("剩余时间 " + ho + ":" + mi + ":" + se + "");
            localTimer = new Timer();
            localTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    int i = -1 + sec;

                    if (i == 0) {

                    }
                    sec = i;
                    if (i < 0) {
                        sec = 0;
                        localTimer.cancel();
                        return;
                    }
                    Message message = new Message();
                    message.what = 2;
                    Bundle bundle = new Bundle();
                    bundle.putInt("time", sec);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            }, 1000L, 1000L);
        } else {
            tvNull.setVisibility(View.VISIBLE);
            grabSingleBean=null;

        }

    }


    @Override
    public void onUpdateOrderState(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent(Constants.REFRESH_ORDER));
        EventBus.getDefault().post(new FirstEvent("40"));
        refresh();
    }

    @Override
    public void show(String data) {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra(PaymentActivity.EXTRA_CHARGE, data);
        startActivityForResult(intent, Pingpp.REQUEST_CODE_PAYMENT);
    }

    @Override
    public void onInsertSign(String data) {
        showSignPop(data);
    }

    @Override
    public void onGetUser(UserBean data) {
        userBean = data;
        refresh();
    }


    @Override
    public void onGetAppVersionDetail(AppVersionDetailBean data) {


        server_address = data.getServer_address();
        if (!versionName.equals(data.getVersion_no())) {
            if ("0".equals(data.getMust_update())) {
                // Log.e("——----------------------", "不强制更新");

            } else {
                showDialogUpdate();//弹出提示版本更新的对话框
                //Log.e("——----------------------", "强制更新");
            }
        } else {
            //Log.e("——----------------------", "无新版本");
        }


    }


    //----------------------------------------抢单倒计时----------------------------------------------//
    private long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
        Date date = null;   //定义时间类型
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = inputFormat.parse(inVal); //将字符型转换成日期型
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();   //返回毫秒数
    }

    private static String dqsj() {  //此方法用于获得当前系统时间（格式类型2007-11-6 15:10:58）
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());  //返回当前时间
    }


    //----------------------------------------------------------------------------------------------//
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        if (refreshlayout != null) {
            refreshlayout.setRefreshing(false);
        }
        ToastUtils.showToast(context.getApplicationContext(), e.getMessage());
        if (e.getMessage().equals(getText(R.string.pingding).toString())) {
            exitLogin();
        }
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
                    ToastUtils.showToast(context.getApplicationContext(), "支付取消");
                } else if (result.equals("invalid")) {
                    ToastUtils.showToast(context.getApplicationContext(), "请先安装微信客户端");
                }
            }
        }
    }


    @OnClick({R.id.ivReported, R.id.tvSearch, R.id.tvSign, R.id.tvGrab})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tvSearch:
                startJSWeb(Constants.BASE_URL + "app-mall1/index.html#/search?&member_id=" + userBean.getMember_id() + "&member_token=" + userBean.getMember_token() + "&add=" + District, District);
                break;
            case R.id.tvSign:
                getPresenter().getInsertSign(map);
                break;

            case R.id.tvGrab:
                if (grabSingleBean == null) {
                    ToastUtils.showToast(context.getApplicationContext(), "暂无可抢单");
                    return;
                }

                if (TextUtils.isEmpty(userBean.getMember_deposit_money())) {
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage("缴纳保证金后才可以接单！");
                    builder.setPositiveButton("立即缴纳", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
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
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage("确定要接受此单吗？");
                    builder.setMessageNew("姓名：" + grabSingleBean.getOrder_name() + "\n服务内容：" + grabSingleBean.getOrder_subscribe_content() + "\n备注：" + grabSingleBean.getOrder_subscribe_note()
                            + "\n地址：" + grabSingleBean.getOrder_address_province() + grabSingleBean.getOrder_address_city() + grabSingleBean.getOrder_address_district() + grabSingleBean.getOrder_address_detail() + "\n联系电话：" + grabSingleBean.getOrder_phone() + "\n期望上门时间：" + grabSingleBean.getOrder_hope_service_time());
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            map.clear();
                            map.put("member_id", userBean.getMember_id());
                            map.put("member_token", userBean.getMember_token());
                            map.put("order_id", grabSingleBean.getOrder_id());
                            map.put("type", "worker_accept");
                            getPresenter().updateOrderState(map);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreateOne().show();
                }
                break;
            case R.id.ivReported://信息报备
                if (TextUtils.isEmpty(userBean.getMember_deposit_money())) {
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage("缴纳保证金后才可以接单！");
                    builder.setPositiveButton("立即缴纳", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
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
                    startInformationreportedFragment();
                }
                break;

        }
    }


    //----------------------------------------pop----------------------------------------------//

    //初始化支付pop
    public void initPopView() {
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
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("channel", "wx");
                getPresenter().Pay(map);
                pop.dismiss();
            }
        });

        ZFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("channel", "alipay");
                getPresenter().Pay(map);
                pop.dismiss();
            }
        });

    }

    //签到pop
    private void showSignPop(String data) {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_sign, null);
        final PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        TextView ok = (TextView) contentView.findViewById(R.id.tv_ok);
        TextView scores = (TextView) contentView.findViewById(R.id.scores);
        scores.setText("+" + data);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.anim.dialog_in);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, 0);
    }


    //----------------------------------------定位----------------------------------------------//
    //申请定位权限
    public void showContacts() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, BAIDU_READ_PHONE_STATE);
        } else {
            initLocation();
        }
    }

    //定位
    private void initLocation() {
        try {
            initLBS();
            setListener();
            mClient.start();
            mClient.requestLocation();
        } catch (Exception e) {
        }
    }

    private void initLBS() {
        try {
            mOption = new LocationClientOption();
            mOption.setOpenGps(true);
            mOption.setCoorType("bd09ll");
            mOption.setAddrType("all");
            mOption.setScanSpan(100);
            mOption.disableCache(true);
            mClient = new LocationClient(context, mOption);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setListener() {
        mClient.registerLocationListener(new BDLocationListener() {
            public void onReceivePoi(BDLocation arg0) {
            }

            public void onReceiveLocation(BDLocation arg0) {
                Message message = new Message();
                if (arg0.getAddrStr() == null) {
                }
                if (arg0.getAddrStr() != null) {
                    City = arg0.getCity();
                    District = arg0.getDistrict();
                    positions[0] = arg0.getLatitude();
                    positions[1] = arg0.getLongitude();
                    message.what = 1;
                    handler.sendMessage(message);
                }
                mClient.stop();
            }
        });
    }
    //----------------------------------------------------------------------------------------------//

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    //销毁倒计时
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mClient.stop();
        if (localTimer != null) {
            localTimer.cancel();
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("30")) {
            startWeb("", "", Constants.BASE_URL + "app-mall1/index.html#/home?member_id=" + userBean.getMember_id() + "&member_token=" + userBean.getMember_token() + "&add=" + District, "1");
        }

    }

    //----------------------------------------版本更新----------------------------------------------//
    /*
 * 获取当前程序的版本名
 */
    private String getVersionName() {
        //获取packagemanager的实例
        PackageManager packageManager = getActivity().getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Log.e("TAG", "版本号" + packInfo.versionCode);
        Log.e("TAG", "版本名" + packInfo.versionName);
        return packInfo.versionName;
    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置提示框的图标
                        setIcon(R.mipmap.logo).
                // 设置要显示的信息
                        setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        loadNewVersionProgress();//下载最新的版本程序
                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();


    }


    /**
     * 下载新版本程序，需要子线程
     */
    private void loadNewVersionProgress() {
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {


                    File file = getFileFromServer(Constants.BASE_URL + server_address, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    ToastUtils.showToast(context.getApplicationContext(), "下载新版本失败");
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public static File getFileFromServer(String uri, ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory(), time + "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {

            return null;
        }
    }


    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

}


