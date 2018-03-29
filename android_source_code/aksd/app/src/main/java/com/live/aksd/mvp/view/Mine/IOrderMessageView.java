package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.UserMessageBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/8/28
 */

public interface IOrderMessageView extends BaseView{
    void ongetMemberMsgs(List<UserMessageBean> data);
}
