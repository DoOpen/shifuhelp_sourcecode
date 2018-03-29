package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.TrainingListBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/10/10
 */

public interface ITrainingClassView extends BaseView{
    void onGetTrainingList(List<TrainingListBean> data);
}
