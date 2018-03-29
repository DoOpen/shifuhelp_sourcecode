package com.live.aksd.mvp.presenter.WordOrder;

import com.live.aksd.App;
import com.live.aksd.bean.WorkOrderNumberBean;
import com.live.aksd.http.HttpResult;
import com.live.aksd.mvp.base.BasePresenter;
import com.live.aksd.mvp.view.WordOrder.IWordOrderView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sh-lx on 2017/8/21.
 */

public class WordOrderPresenter extends BasePresenter<IWordOrderView> {
    public WordOrderPresenter(App app) {
        super(app);
    }

    public void getWorkOrderStateCount(Map<String, String> map) {
        getAppComponent().getAPIService()
                .getWorkOrderStateCount(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<WorkOrderNumberBean>>() {


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
                    public void onNext(HttpResult<WorkOrderNumberBean> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetWorkOrderStateCount(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }
}
