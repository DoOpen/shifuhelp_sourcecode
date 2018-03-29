package com.live.aksd.mvp.presenter.WordOrder;

import com.live.aksd.App;
import com.live.aksd.bean.WorkOrderBean;
import com.live.aksd.http.HttpResult;
import com.live.aksd.mvp.base.BasePresenter;
import com.live.aksd.mvp.view.WordOrder.IAWordView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by sh-lx on 2017/8/21.
 */

public class AWordPresenter extends BasePresenter<IAWordView> {
    public AWordPresenter(App app) {
        super(app);
    }

    public void getOrderListByState(Map<String, String> map) {
        getAppComponent().getAPIService()
                .getOrderListByState(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<WorkOrderBean>>>() {


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
                    public void onNext(HttpResult<List<WorkOrderBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onOrderListByState(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }
}
