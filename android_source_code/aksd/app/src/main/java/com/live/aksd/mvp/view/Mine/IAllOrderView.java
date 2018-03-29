package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.OrderBeanNew;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public interface IAllOrderView extends BaseView{
    void onGetOrders(List<OrderBeanNew> data);

}
