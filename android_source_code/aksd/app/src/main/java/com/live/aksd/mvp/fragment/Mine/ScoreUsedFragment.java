package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;

import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.mvp.base.SimpleFragment;
import com.live.aksd.mvp.fragment.JsWebFragment;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class ScoreUsedFragment extends SimpleFragment {

    private JsWebFragment webFragment;
    public static ScoreUsedFragment newIntance() {
        Bundle args = new Bundle();
        ScoreUsedFragment fragment = new ScoreUsedFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.item_scores_used;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {
        startWeb("费用标准", "", Constants.BASE_URL + "/html/others/charge_standard.html", "");

      /*  if (webFragment == null) {
            webFragment = JsWebFragment.newInstance(Constants.BASE_URL+"web/goodsDetail.html?goods_id=" + goods_id, "", "1", "");
        }
        replaceChildFragment(R.id.webContent, webFragment);*/
    }


}
