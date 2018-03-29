package com.live.aksd.mvp.view.Home;

import com.live.aksd.bean.HtmlBean;
import com.live.aksd.mvp.base.BaseView;

/**
 * @author Created by stone
 * @since 17/8/24
 */

public interface IInformationreportedView extends BaseView {
    void onAddReported(String data);

    void onUploadImgs(String[] data);

    void onGetHtmlDetail(HtmlBean data);
}
