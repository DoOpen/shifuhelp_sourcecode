package com.live.aksd.mvp.view.WordOrder;

import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.WorkOrderDetailBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * Created by liuxiaoxiang on 2018/3/7.
 */

public interface IWaitOrderDetailView extends BaseView{
    void onGetWorkOrderDetail(WorkOrderDetailBean data);

    void onGetUser(UserBean data);

    void show(String data);

    void onUpdateOrderState(String data);
}
