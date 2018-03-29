package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public interface IMyWalletView extends BaseView{
    void onGetUser(UserBean data);

    void onApplyCash(String data);

    void updateMemberDetail(String data);

    void onGetHtmlDetail(HtmlBean data);
}
