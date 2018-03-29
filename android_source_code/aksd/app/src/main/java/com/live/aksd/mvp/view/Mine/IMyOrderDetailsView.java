package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.OrderBean;
import com.live.aksd.bean.OrderBeanNew;
import com.live.aksd.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 17/8/30
 */

public interface IMyOrderDetailsView extends BaseView {


    void onOneOrderDetail(OrderBean data);

    void onCancelOrder(String data);

    void onConfirmOrder(String data);

    void onPayRealOrders(String data);
}
