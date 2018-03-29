package com.live.aksd.mvp.view;


import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.WorkTypeBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * Created by lenove on 2017/7/21.
 */

public interface IMyDataView extends BaseView {



    void submitAudit(String data);

    void updateMemberDetail(String data);

    void onGetUser(UserBean data);

    void onGetWorkTypeList(List<WorkTypeBean> data);

    void uploadImg(String data);
}
