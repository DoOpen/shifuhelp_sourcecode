package com.live.aksd.mvp.presenter.Mine;


import com.live.aksd.App;
import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.http.HttpResult;
import com.live.aksd.mvp.base.BasePresenter;
import com.live.aksd.mvp.view.Mine.IMineView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/9
 */

public class MinePresenter extends BasePresenter<IMineView> {

    public MinePresenter(App app) {
        super(app);
    }

     public void getUser(Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
       getAppComponent().getAPIService()
                .getUserCenter(parmer)
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
                                getView().onGetUser(userBeanHttpResult.getData());

                            }
                        }

                    }
                });
    }



    public void getInsertSign(Map<String, String> map) {
        getAppComponent().getAPIService()
                .insertSign(map)
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
                                getView().onInsertSign(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }

    public void getHtmlDetail(Map<String, String> map) {
        getAppComponent().getAPIService()
                .getHtmlDetail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<HtmlBean>>() {


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
                    public void onNext(HttpResult<HtmlBean> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetHtmlDetail(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }

}
