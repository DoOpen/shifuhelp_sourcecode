package com.live.aksd;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.bilibili.boxing.BoxingCrop;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.king.thread.nevercrash.NeverCrash;
import com.live.aksd.bean.UserBean;
import com.live.aksd.dao.greendao.DaoMaster;
import com.live.aksd.dao.greendao.DaoSession;
import com.live.aksd.di.component.AppComponent;
import com.live.aksd.di.component.DaggerAppComponent;
import com.live.aksd.di.module.AppModule;
import com.live.aksd.impl.BoxingFrescoLoader;
import com.live.aksd.impl.BoxingUcrop;
import com.live.aksd.util.SPUtils;
import com.live.aksd.util.SpSingleInstance;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;

/**
 * href="mailto:jenly1314@gmail.com">Jenly</a>
 *
 * @since 2017/2/13
 */

public class App extends Application {

    private static final String BUGLY_ID = "812b0b1";

    private DaoMaster.DevOpenHelper mHelper;

    private DaoSession mDaoSession;

    private AppComponent mAppComponent;

    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        //Beta.installTinker();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initDatabase();
        setStrictMode();
        //初始化极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        UserBean userBean = SPUtils.getObj1(getApplicationContext(), Constants.USER_BEAN);
        SpSingleInstance.getSpSingleInstance().setUserBean(userBean);

        //配置分享第三方平台的appkey
        PlatformConfig.setWeixin(Constants.WEIXIN_SHARE_ID, Constants.WEIXIN_SHARE_SECRECT);
        PlatformConfig.setSinaWeibo(Constants.SINA_WEIBO_SHARE_ID, Constants.SINA_WEIBO_SHARE_SECRECT, Constants.SINA_WEIBO_SHARE_REDIRECT_URL);
        PlatformConfig.setQQZone(Constants.QQZONE_SHARE_ID, Constants.QQZONE_SHARE_SECRECT);

        //EaseUI.getInstance().init(this, null);

        IBoxingMediaLoader loader = new BoxingFrescoLoader(this);
        BoxingMediaLoader.getInstance().init(loader);
        BoxingCrop.getInstance().init(new BoxingUcrop());


        //异常处理


//        NeverCrash.init(new NeverCrash.CrashHandler() {
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//
//            }
//        });

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this, Constants.BASE_URL)).build();


        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        //初始化Leak内存泄露检测工具
        // LeakCanary.install(this);

    }

    public void initDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "tv-db", null);

        DaoMaster daoMaster = new DaoMaster(mHelper.getWritableDatabase());

        mDaoSession = daoMaster.newSession();
    }

    public AppComponent getAppCommponent() {
        return mAppComponent;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    @TargetApi(9)
    protected void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }

    public static Context getGlobalContext() {
        return mContext;
    }

}



