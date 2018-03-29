package com.live.aksd.mvp.presenter.Mine;

import com.live.aksd.App;
import com.live.aksd.http.HttpResult;
import com.live.aksd.mvp.base.BasePresenter;
import com.live.aksd.mvp.view.Mine.IChangePwdView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuxiaoxiang on 2018/3/9.
 */

public class ChangePwdpresenter extends BasePresenter<IChangePwdView>{
    public ChangePwdpresenter(App app) {
        super(app);
    }

    public void getYanZhengMa(Map<String, String> parmer) {
        getAppComponent().getAPIService()
                .sendCode(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {

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
                    public void onNext(HttpResult<String> userBeanHttpResult) {

                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().ongetYanZhengMa(userBeanHttpResult.getData());
                            }
                        }

                    }
                });
    }

    public void getUpPwd(Map<String, String> parmer) {
        getAppComponent().getAPIService()
                .getUpPwd(parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {

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
                    public void onNext(HttpResult<String> userBeanHttpResult) {

                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onUpPwd(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }
}
