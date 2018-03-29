package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.AppVersionDetailBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public interface ISoftwareRelatedView extends BaseView {
    void onGetAppVersionDetail(AppVersionDetailBean data);
}
