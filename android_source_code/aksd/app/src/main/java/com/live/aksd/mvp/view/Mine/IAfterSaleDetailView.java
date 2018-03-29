package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.AfterSaleDetailBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 17/9/1
 */

public interface IAfterSaleDetailView extends BaseView{
    void onGetRefundDetail(AfterSaleDetailBean data);

    void onCancleRefundOrder(String data);
}
