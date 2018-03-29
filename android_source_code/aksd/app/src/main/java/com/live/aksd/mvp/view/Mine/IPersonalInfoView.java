package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * Created by liuxiaoxiang on 2018/3/12.
 */

public interface IPersonalInfoView extends BaseView{
    void uploadImg(String data);

    void updateMemberDetail(String data);

    void onGetUser(UserBean data);
}
