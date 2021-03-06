package com.live.aksd.mvp.presenter.Mine;

import com.live.aksd.App;
import com.live.aksd.bean.IntegralBean;
import com.live.aksd.http.HttpResult;
import com.live.aksd.mvp.base.BasePresenter;
import com.live.aksd.mvp.view.Mine.IScoreObtainView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Created by stone
 * @since 17/9/12
 */

public class ScoreObtainPresenter extends BasePresenter<IScoreObtainView> {
    public ScoreObtainPresenter(App app) {
        super(app);
    }

    public void getIntegralGetRecord(Map<String, String> map) {
        getAppComponent().getAPIService()
                .getIntegralGetRecord(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<IntegralBean>>>() {


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
                    public void onNext(HttpResult<List<IntegralBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetIntegralGetRecord(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }
}
