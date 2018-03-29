package com.live.aksd.mvp.fragment.Mine;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.ServeAddressPresenter;
import com.live.aksd.mvp.view.Mine.IServeAddressView;
import com.live.aksd.util.SpSingleInstance;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class ServeAddressFragment extends BaseFragment<IServeAddressView, ServeAddressPresenter> implements IServeAddressView, View.OnClickListener {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ll_server_address)
    LinearLayout ll_server_address;
    @BindView(R.id.rl_empty)
    RelativeLayout rl_empty;
    @BindView(R.id.address_manager_item_name)
    TextView address_manager_item_name;
    @BindView(R.id.address_manager_item_phone)
    TextView address_manager_item_phone;
    @BindView(R.id.address_manager_item_address)
    TextView address_manager_item_address;
    @BindView(R.id.address_manager_item_editor)
    TextView addressEditor;


    private UserBean userBean;
    private Map<String, String> map  = new HashMap<>();
    private String state="1";


    public static ServeAddressFragment newIntance() {
        Bundle args = new Bundle();
        ServeAddressFragment fragment = new ServeAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_serveraddress;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initUI() {
        tvTitle.setText("服务地址");
        addressEditor.setOnClickListener(this);

    }

    @Override
    public void initData() {

        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if (userBean.getMember_service_city().isEmpty()) {
            rl_empty.setVisibility(View.VISIBLE);
            ll_server_address.setVisibility(View.GONE);
        } else {
            rl_empty.setVisibility(View.GONE);
            ll_server_address.setVisibility(View.VISIBLE);
            address_manager_item_name.setText(userBean.getMember_real_name());
            address_manager_item_phone.setText(userBean.getMember_service_phone());
            address_manager_item_address.setText(userBean.getMember_service_province()
                    + userBean.getMember_service_city() + userBean.getMember_service_district()
                    + userBean.getMember_service_detail());
        }

    }


    private void startAddAddress(UserBean userBean, String state) {
        AddServerAddressFragment fragment = AddServerAddressFragment.newInstance(userBean, state);
        fragment.setOpenSelectClickListener(new AddServerAddressFragment.OpenSelectOnclickListener() {
            @Override
            public void onOpen(String state) {
                cityPicker(state);
            }
        });
        fragment.show(getFragmentManager(), AddServerAddressFragment.class.getSimpleName());
    }


    @Override
    public void onResume() {
        super.onResume();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getUser(map);
    }

    @Override
    public ServeAddressPresenter createPresenter() {
        return new ServeAddressPresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void updateMemberDetail(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        initData();
    }

    @Override
    public void onGetUser(UserBean data) {
        userBean = data;
        SpSingleInstance.getSpSingleInstance().setUserBean(userBean);
    }


    /**
     * 显示城市列表
     */
    private void cityPicker(final String state) {
        CityPicker cityPicker = new CityPicker.Builder(getActivity())
                .textSize(16)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#fafafa")
                .titleTextColor("#666666")
                .backgroundPop(0xa0000000)
                .confirTextColor("#EC6B1A")
                .cancelTextColor("#999999")
                .province("浙江省")
                .city("金华市")
                .district("义乌市")
                .textColor(Color.parseColor("#666666"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(10)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                userBean.setMember_service_province(province);
                userBean.setMember_service_city(city);
                userBean.setMember_service_district(district);
                startAddAddress(userBean, state);

            }


            @Override
            public void onCancel() {
                startAddAddress(userBean, state);

            }

        });
    }


    @OnClick({R.id.ivLeft, R.id.add_new_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.add_new_address:
                startAddAddress(userBean, state);
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(UserBean event) {
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("Member_service_province", event.getMember_service_province());
        map.put("Member_service_city", event.getMember_service_city());
        map.put("Member_service_district", event.getMember_service_district());
        map.put("Member_service_detail", event.getMember_service_detail());
        getPresenter().updateMemberDetail(map);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address_manager_item_editor:
                state="2";
                startAddAddress(userBean, state);
                break;

        }
    }
}
