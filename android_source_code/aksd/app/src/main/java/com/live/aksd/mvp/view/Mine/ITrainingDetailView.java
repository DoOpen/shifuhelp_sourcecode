package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.TrainingDetailBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 17/10/10
 */

public interface ITrainingDetailView extends BaseView{
    void onGetTraining(TrainingDetailBean data);
}
