package com.live.aksd.mvp.presenter;


import com.live.aksd.App;
import com.live.aksd.bean.UserBean;
import com.live.aksd.http.HttpResult;
import com.live.aksd.mvp.base.BasePresenter;
import com.live.aksd.mvp.view.ILoginFragmentView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by mac1010 on 17/7/29.
 */

public class LoginFragmentPresenter extends BasePresenter<ILoginFragmentView> {
    public LoginFragmentPresenter(App app) {
        super(app);
    }


    public void getLogin(Map<String, String> parmer) {
        if (isViewAttached())//为了避免内存泄露，它只持有 View 的弱引用，因此，使用之前需要先判断isViewAttached()并调用getView()来获取引用。
            getView().showProgress();
        getAppComponent().getAPIService()
                .getLogin(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<UserBean>>() {

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<UserBean> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().ongetlogin(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }

    public void getMemberByMobile(Map<String, String> map) {
        getAppComponent().getAPIService()
                .getMemberByMobile(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<UserBean>>() {


                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<UserBean> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetMemberByMobile(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }

}
