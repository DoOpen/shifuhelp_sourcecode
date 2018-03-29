package com.live.aksd.mvp.presenter.Mine;


import com.live.aksd.App;
import com.live.aksd.bean.TracesByJsonBean;
import com.live.aksd.http.HttpResult;
import com.live.aksd.mvp.base.BasePresenter;
import com.live.aksd.mvp.view.Mine.ILogisticsInformationView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by mac1010 on 17/7/28.
 */

public class LogisticsInformationPresenter extends BasePresenter<ILogisticsInformationView> {
    public LogisticsInformationPresenter(App app) {
        super(app);
    }


    public void getTracesByJson(Map<String, String> params) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getOrderLogisticsDetails(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<TracesByJsonBean>>() {


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
                    public void onNext(HttpResult<TracesByJsonBean> topUpAccountBeanHttpResult) {
                        if (topUpAccountBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().ongetTracesByJson(topUpAccountBeanHttpResult.getData());
                            }
                        }
                    }
                });

    }


}
