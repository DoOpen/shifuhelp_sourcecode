package com.live.aksd.mvp.view.Home;

import com.live.aksd.bean.AppVersionDetailBean;
import com.live.aksd.bean.BannerBean;
import com.live.aksd.bean.GrabSingleBean;
import com.live.aksd.bean.StarMemberBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * Created by sh-lx on 2017/8/21.
 */

public interface IHomeView extends BaseView {

    void onGetBanner(List<BannerBean> list);

    void getStarMember(List<StarMemberBean> list);

    void getWorkOrderByDistance(GrabSingleBean bean);


    void onUpdateOrderState(String data);

    void show(String data);

    void onInsertSign(String data);

    void onGetUser(UserBean data);

    void onGetAppVersionDetail(AppVersionDetailBean data);
}
