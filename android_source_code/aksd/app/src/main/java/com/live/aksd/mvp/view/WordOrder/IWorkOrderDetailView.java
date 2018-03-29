package com.live.aksd.mvp.view.WordOrder;

import com.live.aksd.bean.ServiceClasssBean;
import com.live.aksd.bean.WorkOrderDetailBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/1
 */

public interface IWorkOrderDetailView extends BaseView{
    void onGetWorkOrderDetail(WorkOrderDetailBean data);

    void onCancleOrder(String data);

    void onGetServiceClasss(List<ServiceClasssBean> data);

    void onUpdateOrderState(String data);


    void onCancleCancleOrder(String data);

    void onGetRefundWorkOrderDetail(WorkOrderDetailBean data);

    void onUpdateWorkOrder(String data);

    void onUploadImgs(String[] data);
}
