package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.WithdrawBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/10/11
 */

public interface IWithdrawRecordView extends BaseView{
    void onGetApplyCashs(List<WithdrawBean> data);
}
