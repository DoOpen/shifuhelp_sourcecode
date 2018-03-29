package com.live.aksd.mvp.view.Mine;

import com.live.aksd.bean.RewareAddressBean;
import com.live.aksd.mvp.base.BaseView;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public interface IRewareAddressView extends BaseView{

    void OnOwnerAddress(List<RewareAddressBean> data);

    void onDeleteAddress(String data);

    void OnInsertAddress(String data);

    void onDefaultAddress(String data);
}
