package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.ReportedBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public interface IAllReportedView extends BaseView{
    void onGetReportedList(List<ReportedBean> data);
}
