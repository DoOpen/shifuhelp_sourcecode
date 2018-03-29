package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.ReportedBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 17/8/31
 */

public interface IReportedDetailView extends BaseView {
    void onGetReportedDetail(ReportedBean data);

    void onDeleteReported(String data);
}
