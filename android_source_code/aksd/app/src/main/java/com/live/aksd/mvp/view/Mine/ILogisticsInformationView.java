package com.live.aksd.mvp.view.Mine;


import com.live.aksd.bean.TracesByJsonBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * Created by mac1010 on 17/7/28.
 */

public interface ILogisticsInformationView extends BaseView {
    void ongetTracesByJson(TracesByJsonBean data);
}
