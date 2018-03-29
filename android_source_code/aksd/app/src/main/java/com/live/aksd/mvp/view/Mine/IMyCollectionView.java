package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.MyCollectionBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public interface IMyCollectionView extends BaseView{
    void ongetCollection(List<MyCollectionBean> data);

    void cancelAllCollection(String code);
}
