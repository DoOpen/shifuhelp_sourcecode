package com.live.aksd.mvp.fragment.WorkOrder;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.WorkOrderBean;
import com.live.aksd.mvp.adapter.WorkOrder.WaitOrderAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.WordOrder.WaitWordPresenter;
import com.live.aksd.mvp.view.WordOrder.IWaitWordView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;
import com.pingplusplus.android.PaymentActivity;
import com.pingplusplus.android.Pingpp;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shilei on 2017/8/22.
 */

public class WaitWordFragment extends BaseFragment<IWaitWordView, WaitWordPresenter> implements IWaitWordView {


    @BindView(R.id.easyRecyclerView)
    RecyclerView easyRecyclerView;
    @BindView(R.id.refreshlayout)
    TwinklingRefreshLayout refreshlayout;
    Unbinder unbinder;
    @BindView(R.id.emptyView)
    LinearLayout emptyView;
    private List<WorkOrderBean> listWaitOrder;
    private WaitOrderAdapter waitOrderAdapter;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private int page = 1;
    private PopupWindow pop;

    public static WaitWordFragment newInstance() {

        Bundle args = new Bundle();
        WaitWordFragment fragment = new WaitWordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_currency_list;
    }

    @Override
    public void initUI() {
        initPOPView();
        listWaitOrder = new ArrayList<>();
        waitOrderAdapter = new WaitOrderAdapter(context, listWaitOrder);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(waitOrderAdapter);
        waitOrderAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                startWaitOrderDetailFragment(waitOrderAdapter.getItem(position).getOrder_id());
            }
        });

        SinaRefreshView sinaRefreshView = new SinaRefreshView(context);
        sinaRefreshView.setArrowResource(R.drawable.arrow);
        refreshlayout.setHeaderView(sinaRefreshView);
        refreshlayout.setBottomView(new LoadingView(context));
        refreshlayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = 1;
                refresh();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                page++;
                refresh();
            }
        });

        waitOrderAdapter.setOnOrderClickListener(new WaitOrderAdapter.OnOrderClickListener() {
            @Override
            public void onOrderClick(final String order_id) {
                if (TextUtils.isEmpty(userBean.getMember_deposit_money())) {
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage(getString(R.string.pay_money));
                    builder.setPositiveButton(getString(R.string.pay_son), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            pop.showAtLocation(refreshlayout, Gravity.BOTTOM, 0, 0);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();

                } else {

                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage(getString(R.string.accept_order));
                    builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            map.clear();
                            map.put("member_id", userBean.getMember_id());
                            map.put("member_token", userBean.getMember_token());
                            map.put("order_id", order_id);
                            map.put("type", "worker_accept");
                            getPresenter().updateOrderState(map);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();

                }
            }
        });


    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        page = 1;
        refresh();
    }

    private void refresh() {
        map.clear();
        map.put("page", page + "");
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("type", "worker_wait_accept");
        getPresenter().getOrderListByState(map);
    }


    @Override
    public void onOrderListByState(List<WorkOrderBean> data) {
        refreshlayout.finishRefreshing();
        refreshlayout.finishLoadmore();
        if (page == 1) {
            waitOrderAdapter.clear();
            if (data.size() == 0) {
                emptyView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.GONE);
            }
        }
        waitOrderAdapter.addAll(data);
        waitOrderAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new FirstEvent(Constants.NUMBER_REFRESH));
    }


    @Override
    public void onUpdateOrderState(String data) {
        onResume();
        ToastUtils.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent(Constants.NUMBER_REFRESH));
    }

    @Override
    public void show(String data) {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        intent.putExtra(PaymentActivity.EXTRA_CHARGE, data);
        startActivityForResult(intent, Pingpp.REQUEST_CODE_PAYMENT);
    }

    @Override
    public void onGetUser(UserBean data) {
        userBean = data;
    }

    //----------------------------------------pay----------------------------------------------//
    public void initPOPView() {
        pop = new PopupWindow(context);
        View view = getActivity().getLayoutInflater().inflate(R.layout.pop_ping, null, false);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        TextView cancel_btn = (TextView) view.findViewById(R.id.cancel_btn);
        TextView WX = (TextView) view.findViewById(R.id.WX);
        TextView ZFB = (TextView) view.findViewById(R.id.ZFB);

        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        WX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("channel", "wx");
                getPresenter().Pay(map);
                pop.dismiss();
            }
        });
        ZFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("channel", "alipay");
                getPresenter().Pay(map);
                pop.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");

                if (result.equals("success")) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.pay_success));
                    getPresenter().getUser(map);

                } else if (result.equals("fail")) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.pay_falid));

                } else if (result.equals("cancel")) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.pay_cancel));
                } else if (result.equals("invalid")) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.please_install_wx));
                }
            }
        }
    }

    //----------------------------------------------------------------------------------------------------//
    @Override
    public WaitWordPresenter createPresenter() {
        return new WaitWordPresenter(getApp());
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
