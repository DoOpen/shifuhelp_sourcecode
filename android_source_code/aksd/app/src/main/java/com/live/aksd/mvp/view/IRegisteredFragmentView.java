package com.live.aksd.mvp.view;


import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * Created by mac1010 on 17/7/29.
 */

public interface IRegisteredFragmentView extends BaseView {

    void onRegister(UserBean data);


    void onGetCode(String data);


    void onGetMemberByMobile(UserBean data);

    void onGetHtmlDetail(HtmlBean data);
}
