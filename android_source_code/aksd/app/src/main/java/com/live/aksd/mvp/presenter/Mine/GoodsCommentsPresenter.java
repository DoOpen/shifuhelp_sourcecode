package com.live.aksd.mvp.presenter.Mine;


import com.king.base.util.LogUtils;
import com.live.aksd.App;
import com.live.aksd.http.HttpResult;
import com.live.aksd.mvp.base.BasePresenter;
import com.live.aksd.mvp.view.Mine.IGoodsCommentsView;

import org.json.JSONException;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/9
 */

public class GoodsCommentsPresenter extends BasePresenter<IGoodsCommentsView> {

    public GoodsCommentsPresenter(App app) {
        super(app);
    }



    public void postCommentsList(String uid, String token ,String content) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .assessmentOrder(uid, token,content)
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
                        LogUtils.d("Response:" + userBeanHttpResult);
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onAssessmentOrder(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }

    public void uploadAssessmentImg(List<MultipartBody.Part> map){
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .uploadAssessmentImg(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String[]>>() {


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
                    public void onNext(HttpResult<String[]> userBeanHttpResult) {

                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                try {
                                    getView().onUploadAssessmentImg(userBeanHttpResult.getData());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
    }

}
