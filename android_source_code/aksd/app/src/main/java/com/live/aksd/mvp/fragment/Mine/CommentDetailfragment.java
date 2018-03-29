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
import android.widget.RatingBar;
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
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Home.ImageAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.CommentDetailfragmentpresenter;
import com.live.aksd.mvp.view.Mine.ICommentDetailfragmentView;
import com.live.aksd.util.ImageFactory;
import com.live.aksd.util.SpSingleInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
 * @since 17/9/8
 */

public class CommentDetailfragment extends BaseFragment<ICommentDetailfragmentView, CommentDetailfragmentpresenter> implements ICommentDetailfragmentView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.comment_content)
    EditText comment_content;


    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;

    @BindView(R.id.recycleView)
    EasyRecyclerView recyclerView;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private ImageAdapter adapter;
    private List<String> list = new ArrayList<>();

    private static final int IMAGE = 0X02;
    private String orderId;
    private String goodsImg;
    private String goodsId;
    private String assessment_star1;

    public static CommentDetailfragment newInstance(String orderId, String goodsImg, String goodsId) {
        Bundle args = new Bundle();
        CommentDetailfragment fragment = new CommentDetailfragment();
        fragment.orderId = orderId;
        fragment.goodsImg = goodsImg;
        fragment.goodsId = goodsId;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_comment_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("评价晒单");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("提交");
        Glide.with(context)
                .load(goodsImg)
                .placeholder(R.mipmap.live_default)
                .into(img);


        //添加评价图片
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

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


        //评价星星监听
        assessment_star1 = ratingbar.getNumStars() + "";
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                assessment_star1 = rating + "";
            }
        });

    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }


    protected void show_img(int requestcode) {
        String cachePath = BoxingFileHelper.getCacheDir(getContext());
        if (TextUtils.isEmpty(cachePath)) {
            ToastUtils.showToast(getContext().getApplicationContext(), R.string.boxing_storage_deny);
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

    private JSONArray imgjsonArray = new JSONArray();
    private JSONObject imgjsonObject = new JSONObject();

    @Override
    public void onUploadAssessmentImg(String[] data) throws JSONException {
        for (int i = 0; i < data.length; i++) {
            imgjsonObject.put("assessment_img", data[i] + "");
            imgjsonArray.put(i, imgjsonObject);
        }


    }

    @Override
    public void onAssessmentOrder(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);

    }

    @Override
    public CommentDetailfragmentpresenter createPresenter() {
        return new CommentDetailfragmentpresenter(getApp());
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

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                String commentContent = comment_content.getText().toString();
                if (TextUtils.isEmpty(commentContent)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入评价内容");
                    return;
                }
                String jsonContent = "";
                try {
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < 1; i++) {
                        JSONObject jsonObject = new JSONObject();

                        jsonObject.put("member_id", userBean.getMember_id());
                        jsonObject.put("order_id", orderId);
                        jsonObject.put("assessment_desc", commentContent);
                        jsonObject.put("assessment_type", "goods");
                        jsonObject.put("relation_id", goodsId);
                        jsonObject.put("assessment_star1", assessment_star1);
                        jsonObject.put("assessmentImgBeans", imgjsonArray);
                        jsonArray.put(i,jsonObject);
                    }
                    jsonContent = jsonArray.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getPresenter().postCommentsList(userBean.getMember_id(), userBean.getMember_token(), jsonContent);
                break;
        }

    }
}
