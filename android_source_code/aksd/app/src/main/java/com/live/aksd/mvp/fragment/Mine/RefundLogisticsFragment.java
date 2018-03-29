package com.live.aksd.mvp.fragment.Mine;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Mine.LogisticsCompanyAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.RefundLogisticsPresenter;
import com.live.aksd.mvp.view.Mine.IRefundLogisticsView;
import com.live.aksd.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Created by stone
 * @since 17/9/8
 */

public class RefundLogisticsFragment extends BaseFragment<IRefundLogisticsView, RefundLogisticsPresenter> implements IRefundLogisticsView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.logistics_company)
    EditText logistics_company;

    @BindView(R.id.logistics_no)
    EditText logistics_no;

    @BindView(R.id.phone)
    EditText phone;

    @BindView(R.id.name)
    EditText name;
    private EasyRecyclerView recycleview;
    private LogisticsCompanyAdapter adapter;



    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private String refund_id;
    private PopupWindow pop;

    private List<String> companyList=new ArrayList<>();



    public static RefundLogisticsFragment newInstance(String refund_id) {
        Bundle args = new Bundle();
        RefundLogisticsFragment fragment = new RefundLogisticsFragment();
        fragment.refund_id=refund_id;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_refund_logistics;
    }

    @Override
    public void initUI() {
        tvTitle.setText("订单详情");
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("refund_id", refund_id);
        companyList.add("顺丰快递");
        companyList.add("申通快递");
        companyList.add("圆通快递");
        companyList.add("韵达快递");

    }


    @Override
    public RefundLogisticsPresenter createPresenter() {
        return new RefundLogisticsPresenter(getApp());
    }



    public void showPop()  {
        pop = new PopupWindow(context);
        View view = getActivity().getLayoutInflater().inflate(R.layout.logistics_compay_pop, null, false);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        recycleview = (EasyRecyclerView) view.findViewById(R.id.recycleview);
        recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new LogisticsCompanyAdapter(context,companyList);
        recycleview.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                logistics_company.setText(adapter.getAllData().get(position));
                pop.dismiss();
            }
        });
        pop.showAtLocation(getRootView(), Gravity.BOTTOM, 0, 0);
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

    @OnClick({R.id.ivLeft, R.id.zhankai, R.id.ok,R.id.logistics_company})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.zhankai:
                showPop();
                break;

            case R.id.ok:

                String logisticscompany = logistics_company.getText().toString();
                if (TextUtils.isEmpty(logisticscompany)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请选择物流公司");
                    return;
                }
                String logisticsno = logistics_no.getText().toString();
                if (TextUtils.isEmpty(logisticsno)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入物流单号");
                    return;
                }
                String phonem = phone.getText().toString();
                if (TextUtils.isEmpty(phonem)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入联系电话");
                    return;
                }
                String namem = name.getText().toString();
                map.put("logistics_company",logisticscompany);
                map.put("logistics_no",logisticsno);
                map.put("logistics_phone",phonem);
                map.put("logistics_name",namem);
                getPresenter().updateRefundOrderLogistics(map);
                break;
        }
    }

    @Override
    public void onUpdateRefundOrderLogistics(String data) {
        ToastUtils.showToast(context.getApplicationContext(),data);
        EventBus.getDefault().post(new FirstEvent(Constants.AFTER_SALE_DETAIL+""));
        finish();
    }
}
