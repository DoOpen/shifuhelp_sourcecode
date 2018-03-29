package com.live.aksd.mvp.view;


import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * Created by mac1010 on 17/7/29.
 */

public interface ILoginFragmentView extends BaseView {


    void ongetlogin(UserBean data);

    void onGetMemberByMobile(UserBean data);
}
