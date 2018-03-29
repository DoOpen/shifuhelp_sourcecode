package com.live.aksd.mvp.view.Mine;


import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * Created by sh-lx on 2017/6/6.
 */

public interface IMineView extends BaseView {

    void onGetUser(UserBean userCenterBean);

    void onInsertSign(String data);

    void onGetHtmlDetail(HtmlBean data);

    //void onOrdersCount(OrdersCountBean bean);
}
