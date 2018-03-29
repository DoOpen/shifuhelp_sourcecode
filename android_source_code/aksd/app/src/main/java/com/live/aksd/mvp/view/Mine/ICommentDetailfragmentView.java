package com.live.aksd.mvp.view.Mine;

import com.live.aksd.mvp.base.BaseView;

import org.json.JSONException;

/**
 * @author Created by stone
 * @since 17/9/8
 */

public interface ICommentDetailfragmentView  extends BaseView{
    void onUploadAssessmentImg(String[] data) throws JSONException;

    void onAssessmentOrder(String data);
}
