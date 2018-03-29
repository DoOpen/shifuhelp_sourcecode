package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.TrainingClassBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/10/10
 */

public interface ISchoolView extends BaseView{
    void onGetTrainingClassList(List<TrainingClassBean> data);
}
