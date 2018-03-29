package com.live.aksd.mvp.presenter.Mine;

import com.live.aksd.App;
import com.live.aksd.http.HttpResult;
import com.live.aksd.mvp.base.BasePresenter;
import com.live.aksd.mvp.view.Mine.IRefundLogisticsView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Created by stone
 * @since 17/9/8
 */

public class RefundLogisticsPresenter extends BasePresenter<IRefundLogisticsView> {

    public RefundLogisticsPresenter(App app) {
        super(app);
    }

    public void updateRefundOrderLogistics(Map<String, String> map) {
        getAppComponent().getAPIService()
                .updateRefundOrderLogistics(map)
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
                    public void onNext(HttpResult<String>userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onUpdateRefundOrderLogistics(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }
}
