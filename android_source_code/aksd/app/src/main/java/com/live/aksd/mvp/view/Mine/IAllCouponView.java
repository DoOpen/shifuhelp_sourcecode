package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.CouponBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/8/29
 */

public interface IAllCouponView extends BaseView{
    void onGetCoupons(List<CouponBean> data);
}
