package com.live.aksd.mvp.base;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.king.base.util.LogUtils;
import com.live.aksd.App;
import com.live.aksd.dao.greendao.DaoSession;
import com.live.aksd.di.component.AppComponent;

import javax.inject.Inject;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/20
 */

public class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {

    private App app;

    private DaoSession mDaoSession;

    private AppComponent mAppComponent;

    @Inject
    public BasePresenter(App app){
        super();
        this.app = app;
        mDaoSession = app.getDaoSession();
        mAppComponent = app.getAppCommponent();
    }


    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public App getApp(){
        return getApp();
    }

    @Override
    public boolean isViewAttached() {
        LogUtils.d("isViewAttached:" + super.isViewAttached());
        return super.isViewAttached();
    }
}
