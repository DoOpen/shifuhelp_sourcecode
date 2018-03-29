package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.HtmlBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 17/8/24
 */

public interface ISettingView extends BaseView {

    void onGetHtmlDetailTwo(HtmlBean data);

    void onGetHtmlDetailOne(HtmlBean data);
}
