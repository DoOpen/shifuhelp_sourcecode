package com.live.aksd.mvp.fragment.Mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.BoxingManager;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bilibili.boxing_impl.view.SpacesItemDecoration;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.GoodsCommentsBean;
import com.live.aksd.bean.OrderDetailBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.GoodsCommentsPresenter;
import com.live.aksd.mvp.view.Mine.IGoodsCommentsView;
import com.live.aksd.util.ImageFactory;
import com.live.aksd.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * Created by Burns on 17/6/15.
 */

public class GoodsCommentsFragment extends BaseFragment<IGoodsCommentsView, GoodsCommentsPresenter> implements IGoodsCommentsView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;


    @BindView(R.id.rv_order_goods)
    RecyclerView rvOrderGoods;
    @BindView(R.id.yes)
    TextView yes;
    @BindView(R.id.progress)
    ProgressBar progress;

    private MediaResultAdapter adapter;
    private CommentsAdapter commentsAdapter;
    private int clickImg;
    private String orderNo;

    private Map<String, String> map;
    private UserBean userBean;
    private int position;


    private List<GoodsCommentsBean> GoodsCommentslist;
    private ArrayList<OrderDetailBean> orderDetailBeanArrayList;

    public static GoodsCommentsFragment newInstance(ArrayList<OrderDetailBean> orderDetailBeanArrayList, String orderNo) {
        Bundle args = new Bundle();
        GoodsCommentsFragment fragment = new GoodsCommentsFragment();
        fragment.orderDetailBeanArrayList = orderDetailBeanArrayList;
        fragment.orderNo = orderNo;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_goods_coomtens;
    }

    @Override
    public void initUI() {
        tvTitle.setText("发布评价");
        rvOrderGoods.setLayoutManager(new LinearLayoutManager(getContext()));
        commentsAdapter = new CommentsAdapter(orderDetailBeanArrayList);
        rvOrderGoods.setAdapter(commentsAdapter);
    }

    @Override
    public void initData() {
        map = new HashMap<>();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("order_id", orderNo);
    }

    @Override
    public void onAssessmentOrder(String data) {
        progress.setVisibility(View.GONE);
        yes.setEnabled(true);
        ToastUtils.showToast(getContext().getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent(Constants.ORGER_DETIAILS_FRAGMENT+""));
        finish();
    }

    @Override
    public void onUploadAssessmentImg(String[] data) throws JSONException {

        OrderDetailBean obean = orderDetailBeanArrayList.get(position);
        GoodsCommentsBean bean  = new GoodsCommentsBean();
        List<GoodsCommentsBean.AssessmentImgBeansBean> imglist=  new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            GoodsCommentsBean.AssessmentImgBeansBean assessmentImgBeansBean=new GoodsCommentsBean.AssessmentImgBeansBean();
            assessmentImgBeansBean.setAssessment_img(data[i]);
            imglist.add(assessmentImgBeansBean);
        }
        bean.setOrder_id(obean.getOrder_id());
        bean.setRelation_id(obean.getGoods_id());
        bean.setAssessment_type("goods");
        bean.setAssessment_star1(obean.getGoods_mark()==0?"5":obean.getGoods_mark()+"");
        if (TextUtils.isEmpty(obean.getContent())){
            ToastUtils.showToast(context.getApplicationContext(),"请填写评价内容");
            return;
        }
        bean.setAssessment_desc(obean.getContent());
        bean.setAssessmentImgBeans(imglist);
        GoodsCommentslist.add(bean);
        position++;
        if (orderDetailBeanArrayList.size()==position){

            Gson gson = new Gson();
            String content =gson.toJson(GoodsCommentslist);
            getPresenter().postCommentsList(userBean.getMember_id(), userBean.getMember_token(), content);
        }else{
            for (int i = position;orderDetailBeanArrayList.size()>i;i++){
                OrderDetailBean obean1 = orderDetailBeanArrayList.get(i);
                position = i;
                if (obean1.getMedis()!=null&&obean1.getMedis().size()>0){
                    upimg(orderDetailBeanArrayList.get(position).getMedis());
                    return;
                }else{
                    GoodsCommentsBean bean1  = new GoodsCommentsBean();
                    bean.setOrder_id(obean.getOrder_id());
                    bean.setRelation_id(obean.getGoods_id());
                    bean.setAssessment_type("goods");
                    bean.setAssessment_star1(obean.getGoods_mark()==0?"5":obean.getGoods_mark()+"");
                    if (TextUtils.isEmpty(obean.getContent())){
                        ToastUtils.showToast(context.getApplicationContext(),"请填写评价内容");
                        return;
                    }
                    bean.setAssessment_desc(obean.getContent());
                    bean.setAssessmentImgBeans(imglist);
                    GoodsCommentslist.add(bean1);
                }
            }

            Gson gson = new Gson();
            String content =gson.toJson(GoodsCommentslist);
            getPresenter().postCommentsList(userBean.getMember_id(), userBean.getMember_token(), content);
        }

    }


    private void upimg(final List<BaseMedia> medias) {
        new Thread() {
            @Override
            public void run() {
                try {
                    List<MultipartBody.Part> files = new ArrayList<>();
                    for (int i = 0; i < medias.size(); i++) {
                        String path = medias.get(i).getPath();
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
                                imageFactory = null;
                            }
                        }
                        File _file = new File(out_path);
                        MultipartBody.Part b_cover = MultipartBody.Part.createFormData("img", _file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), _file));
                        files.add(b_cover);
                    }

                    getPresenter().uploadAssessmentImg(files);

                } catch (Exception e) {
                }
            }
        }.start();


    }


    class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {
        private List<OrderDetailBean> DetailBeanList = new ArrayList<>();

        public CommentsAdapter(List<OrderDetailBean> DetailBeanList) {
            this.DetailBeanList = DetailBeanList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_order_goods_comments, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            Glide.with(getContext())
                    .load(Constants.BASE_URL+DetailBeanList.get(position).getImg())
                    .placeholder(R.drawable.live_default)
                    .bitmapTransform(new CropCircleTransformation(getContext()))
                    .into(holder.img);

            try {
                if (DetailBeanList.get(position).getMedis().size() > 0) {
                    holder.go_to_gallery.setVisibility(View.GONE);
                    holder.mediaRecycleView.setVisibility(View.VISIBLE);
                }

                adapter = new MediaResultAdapter();
                adapter.setList(DetailBeanList.get(position).getMedis());
                holder.mediaRecycleView.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();
            }

            holder.et_goods_content.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    DetailBeanList.get(position).setContent(s.toString());
                }
            });


            holder.ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    DetailBeanList.get(position).setGoods_mark(rating);
                }
            });


            holder.go_to_gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickImg = position;
                    show_img();
                }
            });
        }

        @Override
        public int getItemCount() {
            return DetailBeanList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView img, go_to_gallery;
            private EditText et_goods_content;
            private RatingBar ratingbar;
            private RecyclerView mediaRecycleView;


            MyViewHolder(View view) {
                super(view);
                go_to_gallery = (ImageView) view.findViewById(R.id.go_to_gallery);
                img = (ImageView) view.findViewById(R.id.img);
                et_goods_content = (EditText) view.findViewById(R.id.et_goods_content);
                ratingbar = (RatingBar) view.findViewById(R.id.ratingbar);

                mediaRecycleView = (RecyclerView) view.findViewById(R.id.media_recycle_view);
                mediaRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                mediaRecycleView.addItemDecoration(new SpacesItemDecoration(8));
            }
        }
    }


    private class MediaResultAdapter extends RecyclerView.Adapter {
        private ArrayList<BaseMedia> mList;

        MediaResultAdapter() {
            mList = new ArrayList<>();
        }

        void setList(List<BaseMedia> list) {
            if (list == null) {
                return;
            }
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_boxing_simple_media_item, parent, false);
            int height = parent.getMeasuredHeight() / 4;
            view.setMinimumHeight(height);
            return new MediaViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MediaViewHolder) {
                MediaViewHolder mediaViewHolder = (MediaViewHolder) holder;
                mediaViewHolder.mImageView.setImageResource(BoxingManager.getInstance().getBoxingConfig().getMediaPlaceHolderRes());
                BaseMedia media = mList.get(position);
                String path;
                if (media instanceof ImageMedia) {
                    path = ((ImageMedia) media).getThumbnailPath();
                } else {
                    path = media.getPath();
                }
                BoxingMediaLoader.getInstance().displayThumbnail(mediaViewHolder.mImageView, path, 150, 150);
                mediaViewHolder.itemView.setTag(position);

                mediaViewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show_img();
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }
    }

    private class MediaViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        MediaViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.media_item);
        }
    }

    protected void show_img() {
        BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(3).needCamera(R.drawable.ic_boxing_camera_white).needGif();
        Boxing.of(config).withIntent(getContext(), BoxingActivity.class).start(this, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        OrderDetailBean bean = null;
        final ArrayList<BaseMedia> medias = Boxing.getResult(data);
        if (requestCode == REQUEST_CODE) {
            bean = orderDetailBeanArrayList.get(clickImg);
            bean.setMedis(medias);
            orderDetailBeanArrayList.set(clickImg, bean);
            commentsAdapter.notifyDataSetChanged();

        }
    }


    @NonNull
    @Override
    public GoodsCommentsPresenter createPresenter() {
        return new GoodsCommentsPresenter(getApp());
    }


    @Override
    public void showProgress() {
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showToast(getContext().getApplicationContext(), e.getMessage());
        progress.setVisibility(View.GONE);
        yes.setEnabled(true);
    }

    @OnClick({R.id.ivLeft, R.id.yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.yes:


                if (GoodsCommentslist == null) {
                    GoodsCommentslist = new ArrayList<>();
                } else {
                    GoodsCommentslist.clear();
                }

                for (int i = 0; orderDetailBeanArrayList.size() > i; i++) {
                    position = i;
                    if (orderDetailBeanArrayList.get(i).getMedis() != null && orderDetailBeanArrayList.get(i).getMedis().size() > 0) {
                        upimg(orderDetailBeanArrayList.get(position).getMedis());
                        return;
                    } else {
                        OrderDetailBean obean = orderDetailBeanArrayList.get(position);
                        GoodsCommentsBean bean = new GoodsCommentsBean();
                        bean.setMember_id(userBean.getMember_id());
                        bean.setOrder_id(obean.getOrder_id());
                        bean.setRelation_id(obean.getGoods_id());
                        bean.setAssessment_type("goods");
                        if (TextUtils.isEmpty(obean.getContent())){
                            ToastUtils.showToast(context.getApplicationContext(),"请填写评价内容");
                            return;
                        }
                        bean.setAssessment_desc(obean.getContent());
                        bean.setAssessment_star1(obean.getGoods_mark() == 0 ? "5" : obean.getGoods_mark() + "");
                        GoodsCommentslist.add(bean);
                    }
                }
                Gson gson = new Gson();
                String content = gson.toJson(GoodsCommentslist);
                getPresenter().postCommentsList(userBean.getMember_id(), userBean.getMember_token(), content);
                progress.setVisibility(View.VISIBLE);
                yes.setEnabled(false);
                break;
        }
    }


}


