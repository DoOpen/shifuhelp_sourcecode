package com.live.aksd.mvp.view;


import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * Created by lenove on 2017/7/21.
 */

public interface ISHView extends BaseView {

    void onGetUser(UserBean data);

}
