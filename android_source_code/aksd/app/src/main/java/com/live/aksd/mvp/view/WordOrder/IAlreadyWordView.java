package com.live.aksd.mvp.view.WordOrder;

import com.live.aksd.bean.WorkOrderNumberBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * Created by sh-lx on 2017/8/21.
 */

public interface IAlreadyWordView extends BaseView {
    void onGetWorkOrderStateCount(WorkOrderNumberBean data);
}
