package com.live.aksd.mvp.fragment.Mine;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.king.base.util.ToastUtils;
import com.live.aksd.R;
import com.live.aksd.bean.RewareAddressBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Mine.AddressManagerAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.RewareAddressPresenter;
import com.live.aksd.mvp.view.Mine.IRewareAddressView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class ReceiptAddressFragment extends BaseFragment<IRewareAddressView, RewareAddressPresenter> implements IRewareAddressView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rl_empty)
    RelativeLayout rl_empty;
    @BindView(R.id.address_list_receive)
    EasyRecyclerView address_list_receive;

    private UserBean userBean;
    private Map<String, String>  map = new HashMap<>();
    private RewareAddressBean rewareAddressbean = new RewareAddressBean();
    private List<RewareAddressBean> list;
    private AddressManagerAdapter adapter;
    private int position;


    public static ReceiptAddressFragment newIntance() {
        Bundle args = new Bundle();
        ReceiptAddressFragment fragment = new ReceiptAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_rewareaddress;
    }

    @Override
    public void initUI() {
        tvTitle.setText("收货地址");
        list = new ArrayList<>();
        adapter = new AddressManagerAdapter(getContext(), list);
        address_list_receive.setLayoutManager(new LinearLayoutManager(getContext()));
        address_list_receive.setAdapter(adapter);

        adapter.setEditorClick(new AddressManagerAdapter.editorOnClick() {
            @Override
            public void onEditorClick(RewareAddressBean bean) {
                rewareAddressbean = bean;
                startAddAddress(rewareAddressbean, "2");
            }

            @Override
            public void onDefaultClick(RewareAddressBean bean) {
                setDefaultAddress(bean);
            }

            @Override
            public void onDeleteClick(RewareAddressBean data) {
                deleteAddress(data);
            }
        });


    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getOwnerAddress(map);
    }




    //设置默认地址
    private void setDefaultAddress(final RewareAddressBean bean) {
        String name = bean.getAddress_name();
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("将 " + name + " 设置为默认地址 ？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                String addressId = bean.getAddress_id();
                map.put("address_id", addressId);
                getPresenter().setDefaultAddress(map);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }


    //删除地址
    private void deleteAddress(final RewareAddressBean bean) {
        String name = bean.getAddress_name();
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage("是否删除 " + name + " ？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String addressId = bean.getAddress_id();
                map.put("address_id", addressId);
                getPresenter().getdeleteAddress(map);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }


    @Override
    public void OnOwnerAddress(List<RewareAddressBean> data) {
        if (data.size()>0) {
            rl_empty.setVisibility(View.GONE);
        }else{
            rl_empty.setVisibility(View.VISIBLE);
        }
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteAddress(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        getPresenter().getOwnerAddress(map);


    }

    @Override
    public void OnInsertAddress(String data) {
        ToastUtils.showToast(context.getApplicationContext(), "添加成功");
        getPresenter().getOwnerAddress(map);

    }

    @Override
    public void onDefaultAddress(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        getPresenter().getOwnerAddress(map);

    }


    private void startAddAddress(RewareAddressBean rewareAddressbean, final String state) {
        AddReceiptAddressFragment fragment = AddReceiptAddressFragment.newInstance(rewareAddressbean, state);

        fragment.setOpenSelectClickListener(new AddReceiptAddressFragment.OpenSelectOnclickListener() {
            @Override
            public void onOpen(String state) {
                cityPicker(state);
            }

            @Override
            public void onOk(RewareAddressBean bean, String state) {
                if(state.equals("2")){
                    map.put("address_id", bean.getAddress_id());
                }

                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("address_name", bean.getAddress_name());
                map.put("address_mobile", bean.getAddress_mobile());
                map.put("address_province", bean.getAddress_province());
                map.put("address_city", bean.getAddress_city());
                map.put("address_district", bean.getAddress_district());
                map.put("address_detail", bean.getAddress_detail());
                map.put("is_default", "0");
                if(!TextUtils.isEmpty(bean.getAddress_zip_code())){
                    map.put("address_zip_code", bean.getAddress_zip_code());
                }
                getPresenter().getInsertAddress(map);

            }

        });
        fragment.show(getFragmentManager(), AddReceiptAddressFragment.class.getSimpleName());
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
                rewareAddressbean.setAddress_province(province);
                rewareAddressbean.setAddress_city(city);
                rewareAddressbean.setAddress_district(district);
                rewareAddressbean.setAddress_zip_code(code);

                startAddAddress(rewareAddressbean, state);

            }

            @Override
            public void onCancel() {
                startAddAddress(rewareAddressbean,state);
            }

        });
    }

    @Override
    public RewareAddressPresenter createPresenter() {
        return new RewareAddressPresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showToast(context.getApplicationContext(), e + "");

    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().getOwnerAddress(map);
    }


    @OnClick({R.id.ivLeft, R.id.add_new_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.add_new_address:
                map.clear();
                rewareAddressbean=new RewareAddressBean();
                startAddAddress(rewareAddressbean, "1");
                break;

        }
    }


}
