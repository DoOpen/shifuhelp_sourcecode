package com.live.aksd.mvp.presenter.Mine;

import com.live.aksd.App;
import com.live.aksd.bean.CouponBean;
import com.live.aksd.http.HttpResult;
import com.live.aksd.mvp.base.BasePresenter;
import com.live.aksd.mvp.view.Mine.IAllCouponView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Created by stone
 * @since 17/8/29
 */

public class AllCouponPresenter extends BasePresenter<IAllCouponView>{
    public AllCouponPresenter(App app) {
        super(app);
    }

    public void getCoupons(Map<String,String> map){
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getCoupons(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<CouponBean>>>() {


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
                    public void onNext(HttpResult<List<CouponBean>> userBeanHttpResult) {

                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetCoupons(userBeanHttpResult.getData());

                            }
                        }
                    }
                });
    }
}
