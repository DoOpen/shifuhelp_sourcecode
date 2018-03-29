package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.SystemUtils;
import com.live.aksd.R;
import com.live.aksd.bean.TrainingListBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Mine.TrainingListAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.TrainingClassPresenter;
import com.live.aksd.mvp.view.Mine.ITrainingClassView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author Created by stone
 * @since 17/10/10
 */

 public class TrainingClassFragment extends BaseFragment<ITrainingClassView, TrainingClassPresenter> implements ITrainingClassView {

    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView easyRecyclerView;
    private String state;
    private UserBean userBean;
    View loadMore;
    private TextView tvEmpty;
    private TextView tvTips;
    private Map<String, String> map = new HashMap<>();

    private TrainingListAdapter adapter;
    List<TrainingListBean> trainingListBean;
    private boolean isMore;

    private int page = 1;
    private int maxPage = 1;


    public static TrainingClassFragment newInstance(String state) {
        Bundle args = new Bundle();
        TrainingClassFragment fragment = new TrainingClassFragment();
        fragment.state = state;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.my_order_list;
    }

    @Override
    public void initUI() {
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        tvTips = (TextView) easyRecyclerView.findViewById(R.id.tvTips);
        tvEmpty = (TextView) easyRecyclerView.findViewById(R.id.tvEmpty);
        trainingListBean = new ArrayList<>();
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TrainingListAdapter(getContext(), trainingListBean);
        easyRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startTrainingDetailsFragment(adapter.getAllData().get(position).getClass_id() + "");
            }
        });

        //刷新
        easyRecyclerView.setRefreshingColorResources(R.color.colorPrimary);
        easyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getTrainingList(map);
            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public TrainingClassPresenter createPresenter() {
        return new TrainingClassPresenter(getApp());
    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("class_id", state);
        getPresenter().getTrainingList(map);
    }

    @Override
    public void onGetTrainingList(List<TrainingListBean> data) {
        adapter.clear();
        adapter.addAll(data);
        refreshView();
    }

    public void refreshView() {
        adapter.notifyDataSetChanged();
        easyRecyclerView.setRefreshing(false);
        if (adapter.getCount() == 0) {
            if (SystemUtils.isNetWorkActive(context)) {
                tvEmpty.setText(R.string.no_more_ture);
            } else {
                tvTips.setText(R.string.network_unavailable);
            }
            easyRecyclerView.showEmpty();
        }
        if (adapter.getCount() >= (page) * 10) {
            page++;
            isMore = true;
        } else {
            if (loadMore != null) {
                loadMore.setVisibility(View.GONE);
            }
            isMore = false;
        }
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


}
