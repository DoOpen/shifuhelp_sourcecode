package com.live.aksd.mvp.fragment.Mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.OrderBean;
import com.live.aksd.bean.OrderBeanNew;
import com.live.aksd.bean.RefundReasonBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Home.ImageAdapter;
import com.live.aksd.mvp.adapter.Mine.RefundGoodsAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.RequestRefundPresenter;
import com.live.aksd.mvp.view.Mine.IRequestRefundView;
import com.live.aksd.util.ImageFactory;
import com.live.aksd.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * @author Created by stone
 * @since 17/9/5
 */

public class RequestRefundFragment extends BaseFragment<IRequestRefundView, RequestRefundPresenter> implements IRequestRefundView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @BindView(R.id.return_goods_tittle)
    TextView return_goods_tittle;
    @BindView(R.id.rl_return_goods1)
    RelativeLayout rl_return_goods1;
    @BindView(R.id.rl_return_goods2)
    RelativeLayout rl_return_goods2;
    @BindView(R.id.returngood_or_returnmoney)
    TextView returngood_or_returnmoney;
    @BindView(R.id.tv_refund_reason)
    TextView tv_refund_reason;
    @BindView(R.id.et_refund_describle)
    EditText et_refund_describle;
    /*  @BindView(R.id.goods_recycle)
      EasyRecyclerView goods_recycle;*/
    @BindView(R.id.select_type1)
    ImageView select_type1;
    @BindView(R.id.select_type2)
    ImageView select_type2;

    @BindView(R.id.goods_img)
    ImageView goods_img;
    @BindView(R.id.goods_name)
    TextView goods_name;
    @BindView(R.id.goods_norm)
    TextView goods_norm;
    @BindView(R.id.goods_price)
    TextView goods_price;
    @BindView(R.id.num)
    TextView num;

    @BindView(R.id.refundprice)
    TextView refundprice;
    @BindView(R.id.prcietype)
    TextView prcietype;

    @BindView(R.id.tv_upimg)
    TextView tv_upimg;


    private RefundGoodsAdapter goodsaAdapter;
    List<OrderBeanNew.OrderGoodsBeansBean> orderGoodsList;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String order_id;
    private String state;
    private Boolean isCheck1 = false;
    private Boolean isCheck2 = false;
    private String imgs = "";
    private int goodsnum = 1;
    private int getGoodsnum;

    private String goods_id;
    private ImageAdapter adapter;
    private List<String> list;
    private static final int IMAGE = 0X02;


    private List<RefundReasonBean> reasonList = new ArrayList<>();


    public static RequestRefundFragment newIntance(String order_id, String goods_id, String state) {
        Bundle args = new Bundle();
        RequestRefundFragment fragment = new RequestRefundFragment();
        fragment.order_id = order_id;
        fragment.goods_id = goods_id;
        fragment.state = state;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_request_refund;
    }

    @Override
    public void initUI() {


        if (state.equals("1")) {
            tvTitle.setText("申请退款");
            return_goods_tittle.setVisibility(View.GONE);
            rl_return_goods1.setVisibility(View.GONE);
            rl_return_goods2.setVisibility(View.GONE);
            tv_upimg.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);

            prcietype.setText("退款金额");
            returngood_or_returnmoney.setText("退款原因（必填）");

        } else {
            tvTitle.setText("申请退货");
        }

        num.setText(goodsnum + "");
       /* orderGoodsList = new ArrayList<>();
        goodsaAdapter = new RefundGoodsAdapter(context, orderGoodsList);
        goods_recycle.setAdapter(goodsaAdapter);
        goods_recycle.setLayoutManager(new LinearLayoutManager(context)); */

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        list.add("");
        adapter = new ImageAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                show_img(IMAGE);
            }
        });
        adapter.setOnDeleteClick(new ImageAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(String data) {
                adapter.remove(data);
                adapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public void initData() {
        getPresenter().getRefundsReasons(map);
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("order_id", order_id);
        map.put("order_goods_id", goods_id);
        getPresenter().getOneOrderDetail(map);
    }


    @Override
    public RequestRefundPresenter createPresenter() {
        return new RequestRefundPresenter(getApp());
    }

    @Override
    public void onRefundOrder(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent(Constants.ORGER_DETIAILS_FRAGMENT+""));
        finish();


    }

    @Override
    public void onOneOrderDetail(OrderBean data) {


        for (int i = 0; i < data.getOrderGoodsBeans().size(); i++) {
            int id = data.getOrderGoodsBeans().get(i).getOrder_goods_id();
            if (goods_id.equals(id + "")) {
                getGoodsnum = data.getOrderGoodsBeans().get(i).getGoods_num();
                goods_name.setText(data.getOrderGoodsBeans().get(i).getGoods_name());
                goods_norm.setText("规格：" + data.getOrderGoodsBeans().get(i).getSpecification_names());
                goods_price.setText("¥" + data.getOrderGoodsBeans().get(i).getSpecification_price());
                refundprice.setText("¥" + data.getOrderGoodsBeans().get(i).getSpecification_price());
                Glide.with(getContext())
                        .load(Constants.BASE_URL+data.getOrderGoodsBeans().get(i).getGoods_img())
                        .placeholder(R.mipmap.live_default)
                        .into(goods_img);
            }
        }


    }

    @Override
    public void onGetRefundsReasons(List<RefundReasonBean> data) {
        reasonList = data;
    }

    @Override
    public void onUploadAssessmentImg(String[] data) {
        for (int i = 0; i < data.length; i++) {
            map.put("refund_img" + (i + 1), data[i]);
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
        ToastUtils.showToast(context.getApplicationContext(), e.getMessage());

    }

    protected void show_img(int requestcode) {
        String cachePath = BoxingFileHelper.getCacheDir(getContext());
        if (TextUtils.isEmpty(cachePath)) {
            ToastUtils.showToast(context.getApplicationContext(), R.string.boxing_storage_deny);
            return;
        }
        Uri destUri = new Uri.Builder()
                .scheme("file")
                .appendPath(cachePath)
                .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                .build();
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(3).withCropOption(new BoxingCropOption(destUri))
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        Boxing.of(singleCropImgConfig).withIntent(getContext(), BoxingActivity.class).start(this, requestcode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case IMAGE:
                if (resultCode == RESULT_OK) {
                    adapter.clear();
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);

                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapter.add(((ImageMedia) media).getThumbnailPath());
                        } else {
                            adapter.add(media.getPath());
                        }

                    }
                    adapter.add("");
                    adapter.notifyDataSetChanged();
                    upImage();
                }
                break;
        }
    }


    private void upImage() {
        if (adapter.getAllData().size() > 0) {
            List<MultipartBody.Part> files = new ArrayList<>();
            for (int i = 0; i < adapter.getAllData().size() - 1; i++) {
                String path = adapter.getAllData().get(i);
                String out_path = getActivity().getExternalCacheDir().getPath() + System.currentTimeMillis() + ".jpg";
                ImageFactory imageFactory = new ImageFactory();
                Bitmap ratio = imageFactory.ratio(path, 480, 480);
                try {
                    imageFactory.storeImage(ratio, out_path);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (ratio != null) {
                        ratio.recycle();
                        ratio = null;
                    }
                }
                File _file = new File(out_path);
                MultipartBody.Part b_cover = MultipartBody.Part.createFormData("img", _file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), _file));
                files.add(b_cover);
            }
            getPresenter().uploadAssessmentImg(files);
        }

    }


    //打开选择退款原因fragment
    private void startRefundReasonFragment(List<RefundReasonBean> list) {
        RefundReasonFragment fragment = RefundReasonFragment.newInstance(list);
        fragment.setSelectReasonOnclickListener(new RefundReasonFragment.SelectReasonOnclickListener() {
            @Override
            public void ok(String reasonName, int reasonId) {
                tv_refund_reason.setText(reasonName);
                map.put("reason_name", reasonName);
                map.put("refund_reason_id", reasonId + "");
            }
        });

        fragment.show(getFragmentManager(), RequestRefundFragment.class.getSimpleName());
    }

    @OnClick({R.id.ivLeft, R.id.ok, R.id.rl_address, R.id.rl_return_goods1, R.id.rl_return_goods2, R.id.jian, R.id.jia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:
                if (state.equals("1")) {//申请退款
                    String reason = tv_refund_reason.getText().toString();
                    if (TextUtils.isEmpty(reason)) {
                        ToastUtils.showToast(context.getApplicationContext(), "请选择退款原因");
                        return;
                    }
                    map.put("refund_type", "money");
                    map.put("order_goods_id", goods_id);
                    map.put("refund_count", goodsnum + "");
                    map.put("refund_desc", et_refund_describle.getText().toString());
                    getPresenter().refundOrder(map);
                } else {          //申请退货
                    if (!isCheck1 & !isCheck2) {
                        ToastUtils.showToast(context.getApplicationContext(), "请选择退货类型");
                        return;
                    }
                    String reason = tv_refund_reason.getText().toString();
                    if (TextUtils.isEmpty(reason)) {
                        ToastUtils.showToast(context.getApplicationContext(), "请选择退货原因");
                        return;
                    }

                    if (isCheck1) {
                        map.put("refund_type", "not_goods");
                    } else {
                        map.put("refund_type", "with_goods");
                    }
                    map.put("order_goods_id", goods_id);
                    map.put("refund_count", goodsnum + "");
                    map.put("refund_desc", et_refund_describle.getText().toString());
                    getPresenter().refundOrder(map);

                }

                break;
            case R.id.rl_address:       //选择退货or退款原因
                startRefundReasonFragment(reasonList);
                break;
            case R.id.rl_return_goods1:   //退货时选择类型_未到货物退货
                if (isCheck1) {
                    select_type1.setBackgroundResource(R.drawable.xk);
                    isCheck1 = false;
                } else {
                    select_type1.setBackgroundResource(R.drawable.xk_h);
                    if (isCheck2) {
                        select_type2.setBackgroundResource(R.drawable.xk);
                        isCheck2 = false;
                    }
                    isCheck1 = true;
                }

                break;
            case R.id.rl_return_goods2: //退货时选择类型_收到货物退货

                if (isCheck2) {
                    select_type2.setBackgroundResource(R.drawable.xk);
                    isCheck2 = false;
                } else {
                    select_type2.setBackgroundResource(R.drawable.xk_h);
                    if (isCheck1) {
                        select_type1.setBackgroundResource(R.drawable.xk);
                        isCheck1 = false;
                    }
                    isCheck2 = true;
                }
                break;
            case R.id.jian:      //商品数量减
                if (goodsnum <= 1) {
                    ToastUtils.showToast(context.getApplicationContext(), "退货数量不能为0");
                    return;
                }
                goodsnum--;
                num.setText(goodsnum + "");

                break;
            case R.id.jia:         //商品数量加
                if (goodsnum >= getGoodsnum) {
                    ToastUtils.showToast(context.getApplicationContext(), "已超出购买数量");
                    return;
                }
                goodsnum++;
                num.setText(goodsnum + "");
                break;
        }
    }


}
