package com.live.aksd.mvp.view.Mine;


import com.live.aksd.mvp.base.BaseView;

import org.json.JSONException;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/9
 */

public interface IGoodsCommentsView extends BaseView {



    void onAssessmentOrder(String data);

    void onUploadAssessmentImg(String[] data) throws JSONException;
}
