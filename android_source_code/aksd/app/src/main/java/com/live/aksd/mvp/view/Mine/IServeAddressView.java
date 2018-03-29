package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public interface IServeAddressView extends BaseView{
    void updateMemberDetail(String data);

    void onGetUser(UserBean data);
}
