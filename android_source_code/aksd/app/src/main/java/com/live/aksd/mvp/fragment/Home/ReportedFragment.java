package com.live.aksd.mvp.fragment.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.adapter.Home.RepotredImageAdapter;
import com.live.aksd.mvp.adapter.ImageMediaAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Home.InformationreportedPresenter;
import com.live.aksd.mvp.view.Home.IInformationreportedView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.LoadingUtil;
import com.live.aksd.util.SpSingleInstance;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.io.File;
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
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;

/**
 * @author Created by stone
 * @since 17/8/23
 */

public class ReportedFragment extends BaseFragment<IInformationreportedView, InformationreportedPresenter> implements IInformationreportedView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.etAddressDetail)
    EditText etAddressDetail;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btnReported)
    Button btnReported;
    @BindView(R.id.etNoteDetail)
    EditText etNoteDetail;

    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> htmlMap = new HashMap<>();

    private List<String> list;
    private RepotredImageAdapter adapter;
    private static final int IMAGE = 0X02;

    private List<String> imgArray = new ArrayList();
    private String htmlPath;

    public static ReportedFragment newIntance() {
        Bundle args = new Bundle();
        ReportedFragment fragment = new ReportedFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_information_reported;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.information_reported);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		// sfsm  zhoushilei:  modified code @{
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        list = new ArrayList<>();
        list.add("");
        adapter = new RepotredImageAdapter(context, list);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapter.getAllData().size() <= 3) {
                    show_img(IMAGE);
                } else {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.max_three));
                }
            }
        });
        adapter.setOnDeleteClick(new RepotredImageAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(int data) {
                adapter.remove(data);
            }
        });
		// @}

    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        htmlMap.put("html_name", "报备相关");
        getPresenter().getHtmlDetail(htmlMap);

    }

    @Override
    public void onAddReported(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        LoadingUtil.hideLoading();
        finish();
    }


    @Override
    public void onUploadImgs(String[] data) {
        for (int i = 0; i < data.length; i++) {
            map.put("reported_img" + (i + 1), data[i]);
        }
        getPresenter().addReported(map);
    }

    @Override
    public void onGetHtmlDetail(HtmlBean data) {
        htmlPath = data.getHtml_url();
    }


    //-----------------------------------------------上传报备图片------------------------------------------------------//

    protected void show_img(int requestcode) {
        String cachePath = BoxingFileHelper.getCacheDir(context);
        if (TextUtils.isEmpty(cachePath)) {
            ToastUtils.showToast(context.getApplicationContext(), R.string.boxing_storage_deny);
            return;
        }
        Uri destUri = new Uri.Builder()
                .scheme("file")
                .appendPath(cachePath)
                .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                .build();
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(4 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri))
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);


        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, requestcode);
    }

    //从相册选择图片返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE:
                if (resultCode == RESULT_OK) {
                    //adapter.clear();
                    adapter.remove(adapter.getAllData().size() - 1);
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);

                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapter.add(((ImageMedia) media).getThumbnailPath());

                        } else {
                            adapter.add(media.getPath());
                            imgArray.add(media.getPath());
                        }
                    }
                    if (adapter.getAllData().size() > 0) {

                    }
                    if (adapter.getAllData().size() <= 3) {
                        adapter.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }


    //上传图片
    private void upImage() {
        final List<MultipartBody.Part> files = new ArrayList<>();
        adapter.remove(adapter.getAllData().size() - 1);
        final List<String> allData = adapter.getAllData();
        Luban.with(getActivity())
                .load(allData)                                   // 传人要压缩的图片列表
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        MultipartBody.Part b_cover = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                        files.add(b_cover);
                        if (files.size() == allData.size()) {
                            getPresenter().uploadImgs(files);

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    } //设置回调

                }).launch();    //启动压缩

    }

//    //上传图片
//    private void upImage() {
//        new Thread() {
//            @Override
//            public void run() {
//                List<MultipartBody.Part> files = new ArrayList<>();
//                for (int i = 0; i < adapter.getAllData().size() - 1; i++) {
//                    String path = adapter.getAllData().get(i);
//                    if (TextUtils.isEmpty(path)) {
//                        return;
//                    }
//                    String outFilePath = context.getExternalCacheDir().getPath() + System.currentTimeMillis() + ".jpg";
//                    ImageFactory imageFactory = new ImageFactory();
//                    Bitmap ratio = imageFactory.ratio(path, 480, 480);
//                    try {
//                        imageFactory.storeImage(ratio, outFilePath);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (ratio != null) {
//                            ratio.recycle();
//                            //ratio = null;
//                        }
//                    }
//                    File _file = new File(outFilePath);
//                    //构建body
//                    MultipartBody.Part b_cover = MultipartBody.Part.createFormData("img", _file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), _file));
//                    files.add(b_cover);
//                }
//                getPresenter().uploadImgs(files);
//
//            }
//        }.start();
//    }
    //-------------------------------------选择地址----------------------------------------------------------------//

    private void cityPicker() {
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

                tvAddress.setText(province + " " + city + " " + district);
                map.put("province", province);
                map.put("city", city);
                map.put("district", district);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    @OnClick({R.id.ivLeft, R.id.tvInstructions, R.id.tvAddress, R.id.btnReported})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvInstructions:
                startWeb("报备相关", "", Constants.BASE_URL + htmlPath, "");
                break;
            case R.id.tvAddress:
                // hideInputMethod(etName);
                cityPicker();
                break;
            case R.id.btnReported:
                String name = etName.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入姓名");
                    return;
                }
                String phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入电话号码");
                    return;
                }
                final String address = tvAddress.getText().toString().trim();
                if (TextUtils.isEmpty(address)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请选择地址");
                    return;
                }
                String detailsAddress = etAddressDetail.getText().toString().trim();
                if (TextUtils.isEmpty(detailsAddress)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入详细地址");
                    return;
                }
                map.put("reported_name", name);
                map.put("reported_phone", phone);
                map.put("detail", detailsAddress);
                map.put("reported_note", etNoteDetail.getText().toString());
                final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage(getString(R.string.is_reproted));
                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (adapter.getAllData().size() > 1) {
                            upImage();
                        } else {
                            getPresenter().addReported(map);
                        }

                        LoadingUtil.showLoading(context, getResources().getString(R.string.reported_loading));
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();
                break;

        }
    }


    @Override
    public InformationreportedPresenter createPresenter() {
        return new InformationreportedPresenter(getApp());
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

}
