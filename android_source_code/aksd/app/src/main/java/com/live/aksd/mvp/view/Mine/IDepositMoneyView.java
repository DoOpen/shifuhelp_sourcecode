package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 17/9/7
 */

public interface IDepositMoneyView extends BaseView {
    void show(String data);

    void onGetUser(UserBean data);

    void onGetHtmlDetail(HtmlBean data);
}
