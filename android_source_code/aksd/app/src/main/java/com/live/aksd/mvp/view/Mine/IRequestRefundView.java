package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.OrderBean;
import com.live.aksd.bean.OrderBeanNew;
import com.live.aksd.bean.RefundReasonBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/5
 */

public interface IRequestRefundView extends BaseView{
    void onRefundOrder(String data);


    void onOneOrderDetail(OrderBean data);

    void onGetRefundsReasons(List<RefundReasonBean> data);

    void onUploadAssessmentImg(String[] data);
}
