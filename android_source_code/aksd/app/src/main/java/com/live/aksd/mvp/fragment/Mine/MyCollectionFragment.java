package com.live.aksd.mvp.fragment.Mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.MyCollectionBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.MyCollectionPresenter;
import com.live.aksd.mvp.view.Mine.IMyCollectionView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;

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

public class MyCollectionFragment extends BaseFragment<IMyCollectionView, MyCollectionPresenter> implements IMyCollectionView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.bottom_rl)
    RelativeLayout bottom_rl;
    @BindView(R.id.check_all)
    ImageView check_all;

    @BindView(R.id.my_collection_recycle)
    EasyRecyclerView myCollectionRecycle;
    View loadMore;
    private int page = 1;


    private MyCollectionAdapter adapter;
    private List<MyCollectionBean> list;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private String collection_ids="";
    int onClickNum = 1;
    int selectType = 0;//控制显示
    boolean is_selectAll = false;
    ArrayList<String> DelList = new ArrayList<>();

    private boolean editorOrcomplete = false; //false： 编辑 or  删除  true： 完成 or 删除

    public static MyCollectionFragment newIntance() {
        Bundle args = new Bundle();
        MyCollectionFragment fragment = new MyCollectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_mycollection;
    }

    @Override
    public void initUI() {
        tvTitle.setText("我的收藏");
        tvRight.setText("编辑");
        tvRight.setVisibility(View.VISIBLE);
        bottom_rl.setVisibility(View.GONE);
        list = new ArrayList<>();
        loadMore = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.load_more, null);
        adapter = new MyCollectionAdapter(list);
        myCollectionRecycle.setAdapter(adapter);
        myCollectionRecycle.setLayoutManager(new LinearLayoutManager(context));
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                page=1;
                String goods_id=adapter.getAllData().get(position).getRelation_id();
                startWeb("商品详情", "", Constants.BASE_URL+"app-mall-new/index.html#/goods_detail??&dd="+goods_id+"&member_id=" + userBean.getMember_id() + "&member_token=" + userBean.getMember_token(), "5");
            }
        });
        //刷新
        myCollectionRecycle.setRefreshingColorResources(R.color.colorPrimary);
        myCollectionRecycle.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                map.put("page",page+"");
                getPresenter().getCollection(map);
            }
        });
        //加载
        adapter.setMore(loadMore, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                if (loadMore != null) {
                    loadMore.setVisibility(View.VISIBLE);
                }
                page++;
                map.put("page", page+"");
                getPresenter().getCollection(map);
            }
            @Override
            public void onMoreClick() {

            }
        });


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();


    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("collection_type", "goods");
        map.put("page", "1");
        getPresenter().getCollection(map);
    }

    @Override
    public void ongetCollection(List<MyCollectionBean> data) {
        list = data;

        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(list);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void cancelAllCollection(String code) {
        page = 1;
        map.put("page",page+"");
        getPresenter().getCollection(map);
    }

    @Override
    public MyCollectionPresenter createPresenter() {
        return new MyCollectionPresenter(getApp());
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

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.all_list, R.id.tv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.tvRight:
                editorOrcomplete = !editorOrcomplete;
                if (editorOrcomplete) {
                    selectType = 1;
                    bottom_rl.setVisibility(View.VISIBLE);
                    tvRight.setText(getResources().getString(R.string.complete));

                } else {
                    selectType = 0;
                    bottom_rl.setVisibility(View.GONE);
                    tvRight.setText(getResources().getString(R.string.editor));
                }
                for (int i = 0; i < adapter.getCount(); i++) {
                    adapter.getItem(i).setIs_selectAll(false);
                }
                adapter.notifyDataSetChanged();
                //getPresenter().getCollection(map);
                break;
            case R.id.all_list:
                if (is_selectAll) {
                    is_selectAll = false;
                    check_all.setImageResource(R.drawable.unchecked);
                } else {
                    is_selectAll = true;
                    check_all.setImageResource(R.drawable.checked);
                }
                for (int i = 0; i < adapter.getCount(); i++) {
                    adapter.getItem(i).setIs_selectAll(is_selectAll);
                }
                adapter.notifyDataSetChanged();
                //getPresenter().getCollection(map);
                break;
            case R.id.tv_delete:
                if (DelList.size()==0){
                    ToastUtils.showToast(getContext().getApplicationContext(),"请选择要删除的条目");
                    return;
                }
                CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage("真的要删除收藏的商品吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (DelList != null) {
                            for (int i = 0; i < DelList.size(); i++) {
                                collection_ids += DelList.get(i) + ",";
                            }
                            collection_ids = collection_ids.substring(0, collection_ids.length() - 1);
                            map.put("collection_ids", collection_ids);
                            getPresenter().cancelAllCollection(map);
                            collection_ids = "";
                        }


                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();
                break;


        }
    }


    public class MyCollectionAdapter extends RecyclerArrayAdapter<MyCollectionBean> {

        public MyCollectionAdapter(List<MyCollectionBean> objects) {
            super(context, objects);


        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new ListBeanViewHolder(parent);
        }

        public class ListBeanViewHolder extends BaseViewHolder<MyCollectionBean> {

            TextView tv_name, tv_price, tv_buyNum;
            ImageView img, is_select;

            public ListBeanViewHolder(ViewGroup parent) {
                super(parent, R.layout.item_my_collection);
                img = $(R.id.img);
                tv_name = $(R.id.tv_name);
                tv_price = $(R.id.tv_price);
                tv_buyNum = $(R.id.tv_buyNum);
                is_select = $(R.id.is_select);
            }

            @Override
            public void setData(final MyCollectionBean data) {
                super.setData(data);

                if (selectType == 0) {
                    is_select.setVisibility(View.GONE);
                } else {
                    is_select.setVisibility(View.VISIBLE);
                    if (is_selectAll) {
                        is_select.setImageResource(R.drawable.checked);
                        DelList.add(data.getCollection_id() + "");

                    } else {
                        is_select.setImageResource(R.drawable.unchecked);
                        DelList.remove(data.getCollection_id() + "");
                    }
                }

                is_select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (DelList.contains(data.getCollection_id() + "")) {
                            is_select.setImageResource(R.drawable.unchecked);
                            DelList.remove(data.getCollection_id() + "");
                        } else {
                            is_select.setImageResource(R.drawable.checked);
                            DelList.add(data.getCollection_id() + "");
                        }
                    }
                });

                tv_name.setText(data.getGoodsBean().getGoods_name());
                tv_price.setText("¥ " + data.getGoodsBean().getGoods_price());
                tv_buyNum.setText(data.getGoodsBean().getGoods_sales() + "人购买");
                Glide.with(getContext())
                        .load(Constants.BASE_URL + data.getGoodsBean().getGoods_img())
                        .placeholder(R.mipmap.live_default)
                        .into(img);
            }
        }
    }
}
