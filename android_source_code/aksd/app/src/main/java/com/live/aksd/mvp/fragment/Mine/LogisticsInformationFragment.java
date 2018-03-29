package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.live.aksd.R;
import com.live.aksd.bean.TracesByJsonBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Mine.LogisticsInformationAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.LogisticsInformationPresenter;
import com.live.aksd.mvp.view.Mine.ILogisticsInformationView;
import com.live.aksd.util.SpSingleInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;



/**
 * Created by mac1010 on 17/7/28.
 */

public class LogisticsInformationFragment extends BaseFragment<ILogisticsInformationView, LogisticsInformationPresenter> implements ILogisticsInformationView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.zhuang_tai)
    TextView zhuangTai;
    @BindView(R.id.wu_liu_gong_si)
    TextView wuLiuGongSi;
    @BindView(R.id.ding_dan_bian_hao)
    TextView dingDanBianHao;
    @BindView(R.id.yun_dan_bian_hao)
    TextView yun_dan_bian_hao;
    @BindView(R.id.logistics_info_recyclerview)
    RecyclerView Recyclerview;


    private List<TracesByJsonBean.LogisticsDetailBeansBean> list;
    private UserBean userBean;
    private String order_id;
    private String logistics_no;
    private String logistics_order_no;
    private String type;


    private Map<String, String> map = new HashMap<>();
    private LogisticsInformationAdapter adapter;

    public static LogisticsInformationFragment newIntance(String order_id, String logistics_no,String logistics_order_no,String type) {

        Bundle args = new Bundle();
        LogisticsInformationFragment fragment = new LogisticsInformationFragment();
        fragment.order_id = order_id;
        fragment.logistics_no = logistics_no;
        fragment.logistics_order_no=logistics_order_no;
        fragment.type=type;

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.logistics_info_fragment;
    }

    @Override
    public void initUI() {

        tvTitle.setText("查看物流");
        dingDanBianHao.setText("订单编号："+logistics_order_no);
        yun_dan_bian_hao.setText("运单编号："+logistics_no);
        list = new ArrayList<>();

        adapter = new LogisticsInformationAdapter(context, list);
        Recyclerview.setLayoutManager(new LinearLayoutManager(context));
        Recyclerview.setAdapter(adapter);





    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("order_id", order_id);
        map.put("logistics_no", logistics_no);
        map.put("type", type);
        getPresenter().getTracesByJson(map);

    }

    @Override
    public void ongetTracesByJson(TracesByJsonBean data) {
        zhuangTai.setText(data.getLogisticsBean().getlogistics_state());
        wuLiuGongSi.setText("物流公司："+data.getLogisticsBean().getLogistics_name());
        adapter.clear();
        adapter.addAll(data.getLogisticsDetailBeans());
        adapter.notifyDataSetChanged();
    }

    @Override
    public LogisticsInformationPresenter createPresenter() {
        return new LogisticsInformationPresenter(getApp());
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

    @OnClick(R.id.ivLeft)
    public void onViewClicked() {
        finish();
    }

}
