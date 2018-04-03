package com.live.aksd.mvp.fragment.WorkOrder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.live.aksd.R;
import com.live.aksd.bean.WorkOrderDetailBean;
import com.live.aksd.mvp.adapter.ImageMediaAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;

/**
 * @author Created by stone
 * @since 17/9/2
 */

public class CompletedSubmissionFragment extends DialogFragment {

    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.tvRealContent)
    TextView tvRealContent;

    @BindView(R.id.etOtherContent)
    EditText etOtherContent;
    @BindView(R.id.etOtherPrice)
    EditText etOtherPrice;

    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.etNote)
    EditText etNote;
    @BindView(R.id.tvCommit)
    TextView tvCommit;
    @BindView(R.id.imgCloce)
    ImageView imgCloce;
    Unbinder unbinder;
    @BindView(R.id.etTotalPrice)
    EditText etTotalPrice;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.etRealContent)
    EditText etRealContent;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.tvRealArea)
    TextView tvRealArea;
    @BindView(R.id.line8)
    View line8;
    @BindView(R.id.tvUnitPrice)
    TextView tvUnitPrice;
    @BindView(R.id.line9)
    View line9;
    @BindView(R.id.tvOtherContent)
    TextView tvOtherContent;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.tvOtherPrice)
    TextView tvOtherPrice;
    @BindView(R.id.line4)
    View line4;
    @BindView(R.id.tvTotalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.line6)
    View line6;
	//sfsm xiaozhangxin: [修改内容] add code @{
	@BindView(R.id.etRealArea)
	EditText etRealArea;
	// @}



    public interface CompletedSubmissionOnclickListener {


        //上传图片
        void upLoadImage(List<MultipartBody.Part> map);

        //提交
        void commit(WorkOrderDetailBean workOrderDetailBean, String otherContent, String oterPrice);

        //关闭dialogfragment
        void onClose();
    }

    private CompletedSubmissionOnclickListener onclickListener;

    public void setCompletedSubmissionOnclickListener(CompletedSubmissionOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }


    private WorkOrderDetailBean workOrderDetailBean;
    private ImageMediaAdapter adapter;
    private List<String> list;
    private static final int IMAGE = 0X02;
    private String price;
    String service_class_price;
    String work_area;

    public static CompletedSubmissionFragment newInstance(WorkOrderDetailBean workOrderDetailBean) {
        Bundle args = new Bundle();
        CompletedSubmissionFragment fragment = new CompletedSubmissionFragment();
        fragment.workOrderDetailBean = workOrderDetailBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_completed, container, false);
        unbinder = ButterKnife.bind(this, view);
        setLayout();
        initViews();
        return view;
    }

    private void setLayout() {
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawableResource(R.color.black_transparent);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.dimAmount = 0;
        window.setAttributes(lp);
    }


    private void initViews() {
        service_class_price = workOrderDetailBean.getService_class_price();
        work_area = workOrderDetailBean.getWork_area();
		// sfsm  zhoushilei: [师傅完工面积可编辑] modified code @{
        if (work_area.contains(".")){
            work_area=work_area.substring(0,work_area.indexOf("."));
        }
        String order_reality_content = workOrderDetailBean.getOrder_reality_content();
        if (order_reality_content.contains("null")) {
            order_reality_content = order_reality_content.substring(4, order_reality_content.length());
        }
        etRealContent.setText(order_reality_content);
        tvUnitPrice.setText(getString(R.string.single_price) + service_class_price);
        if ("0".equals(workOrderDetailBean.getOrder_type())) {
            etRealArea.setText(work_area);
            float realtotal = Float.parseFloat(work_area) * Float.parseFloat(service_class_price);
            tvTotalPrice.setText(getString(R.string.toutal_price) + realtotal);
        } else {
            etRealArea.setVisibility(View.GONE);
            tvTotalPrice.setText(getString(R.string.toutal_price) + service_class_price);
        }
		// @}

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        list.add("");
        adapter = new ImageMediaAdapter(getContext(), list);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                show_img(IMAGE);
            }
        });

	
		
        etOtherPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(etOtherPrice.getText())) {
                    etOtherPrice.setText("0");
                }
				// sfsm  zhoushilei: [师傅完工面积可编辑] modified code @{
                if ("0".equals(workOrderDetailBean.getOrder_type())) {
                    double realtotal = Double.parseDouble(etRealArea.getText().toString()) * Double.parseDouble(service_class_price) + Double.parseDouble(etOtherPrice.getText().toString());
                    tvTotalPrice.setText(getString(R.string.toutal_price) + realtotal);
                } else {
                    double i = Double.parseDouble(service_class_price) + Double.parseDouble(etOtherPrice.getText().toString());
                    tvTotalPrice.setText(getString(R.string.toutal_price) + i);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etRealArea.addTextChangedListener(new TextWatcher() {
            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String otherPrice = etOtherPrice.getText().toString();
                if (TextUtils.isEmpty(otherPrice)){
                    otherPrice="0";
                }
                if (TextUtils.isEmpty(etRealArea.getText().toString())) {
                    etRealArea.setText("0");
                }
                if ("0".equals(workOrderDetailBean.getOrder_type())) {
                    double realtotal = Double.parseDouble(etRealArea.getText().toString()) * Double.parseDouble(service_class_price) + Double.parseDouble(otherPrice);
                    tvTotalPrice.setText(getString(R.string.toutal_price) + realtotal);
                } else {
                    double i = Double.parseDouble(service_class_price) + Double.parseDouble(otherPrice);
                    tvTotalPrice.setText(getString(R.string.toutal_price) + i);
                }
			// @}

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
		
		

    }


    @OnClick({R.id.imgCloce, R.id.tvCommit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgCloce:
                dismiss();
                break;
            case R.id.tvCommit:
                upImage();
                String markInformation = etNote.getText().toString();
                workOrderDetailBean.setOrder_price(etTotalPrice.getText().toString());
                workOrderDetailBean.setOrder_complete_note(markInformation);
                onclickListener.commit(workOrderDetailBean, etOtherContent.getText().toString(), etOtherPrice.getText().toString());
                break;

        }
    }


    //-----------------------------------------------上传完工图片------------------------------------------------------//

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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(4-adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri))
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        Boxing.of(singleCropImgConfig).withIntent(getContext(), BoxingActivity.class).start(this, requestcode);
    }

    //从相册选择图片返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE:
                if (resultCode == RESULT_OK) {
                    adapter.remove(adapter.getAllData().size()-1);
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapter.add(((ImageMedia) media).getThumbnailPath());
                        } else {
                            adapter.add(media.getPath());
                        }
                    }
                    if (adapter.getAllData().size() > 0) {

                    }
                    if (adapter.getAllData().size() < 3) {
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
        final List<String> allData = adapter.getAllData();
        Luban.with(getActivity())
                .load(allData)
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
                            onclickListener.upLoadImage(files);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    } //设置回调

                }).launch();    //启动压缩

    }
}
