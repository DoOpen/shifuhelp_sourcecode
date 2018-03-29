package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.IntegralBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/12
 */

public interface IScoreObtainView  extends BaseView{
    void onGetIntegralGetRecord(List<IntegralBean> data);
}
