package com.live.aksd.mvp.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.king.base.util.ToastUtils;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.WorkTypeBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.fragment.Mine.RequestRefundFragment;
import com.live.aksd.mvp.presenter.MyDataPersenter;
import com.live.aksd.mvp.view.IMyDataView;
import com.live.aksd.util.ImageFactory;
import com.live.aksd.util.LoadingUtil;
import com.live.aksd.util.SpSingleInstance;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * Created by lenove on 2017/7/21.
 */

public class MyDataFragment extends BaseFragment<IMyDataView, MyDataPersenter> implements IMyDataView {

    @BindView(R.id.all_bg)
    ConstraintLayout all_bg;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.img)
    CircleImageView img;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_age)
    EditText tvAge;

    @BindView(R.id.etId)
    EditText etId;
    @BindView(R.id.etSkill)
    EditText etSkill;
    @BindView(R.id.etRecommend)
    EditText etRecommend;

    @BindView(R.id.tv_phone)
    EditText tv_phone;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_address_detail)
    EditText tv_address_detail;


    @BindView(R.id.tv_work)
    TextView tvWork;
    @BindView(R.id.tv_workage)
    EditText tvWorkage;

    @BindView(R.id.btnSave)
    Button btnSave;
    Unbinder unbinder;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private String path;
    private String imgPath="";
    private PopupWindow sexPop;


    private List<WorkTypeBean> workTypeList = new ArrayList<>();

    public static MyDataFragment newIntance() {
        Bundle args = new Bundle();
        MyDataFragment fragment = new MyDataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_registered_info;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.improve_information);

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getUser(map);
        getPresenter().getWorkTypeList(map);
    }

    @Override
    public void submitAudit(String data) {
        startMySH();
        finish();
    }

    @Override
    public void updateMemberDetail(String data) {
        getPresenter().submitAudit(map);
    }

    @Override
    public void onGetUser(UserBean data) {
        userBean = data;
    }

    @Override
    public void onGetWorkTypeList(List<WorkTypeBean> data) {
        workTypeList = data;
    }

    @Override
    public void uploadImg(String data) {
        imgPath=data;
        LoadingUtil.hideLoading();
        ToastUtils.showToast(context.getApplicationContext(), getString(R.string.load_success));
    }


    private void startSexpop() {
        View view = View.inflate(context, R.layout.item_sex, null);
        sexPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView male = (TextView) view.findViewById(R.id.male);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSex.setText("男");
                sexPop.dismiss();
            }
        });
        TextView famale = (TextView) view.findViewById(R.id.famale);
        famale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSex.setText("女");
                sexPop.dismiss();
            }
        });

        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexPop.dismiss();
            }
        });
        sexPop.setBackgroundDrawable(new BitmapDrawable());
        sexPop.setFocusable(false);
        sexPop.setOutsideTouchable(true);
        sexPop.setContentView(view);
        sexPop.showAtLocation(all_bg, Gravity.CENTER, 0, 0);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    if (medias.get(0) instanceof ImageMedia) {
                        path = ((ImageMedia) medias.get(0)).getThumbnailPath();
                    } else {
                        path = medias.get(0).getPath();
                    }
                    BoxingMediaLoader.getInstance().displayThumbnail(img, path, 480, 480);
                    upimg();
                }
                break;
        }
    }


    private void upimg() {
        String outFilePath = context.getExternalCacheDir().getPath() + System.currentTimeMillis() + ".jpg";
        ImageFactory imageFactory = new ImageFactory();
        Bitmap ratio = imageFactory.ratio(path, 480, 480);
        try {
            imageFactory.storeImage(ratio, outFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ratio != null) {
                ratio.recycle();
                ratio = null;
            }
        }
        File _file = new File(outFilePath);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("path","/images/member")
                .addFormDataPart("head_img", _file.getName(), RequestBody.create(MediaType.parse("image/*"), _file))
                .build();
        getPresenter().uploadImg(requestBody);
        LoadingUtil.showLoading(context, getString(R.string.img_loading));

    }

    protected void show_img() {
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG).withCropOption(new BoxingCropOption(destUri))
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, REQUEST_CODE);
    }

    /**
     * 显示城市列表
     */
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

                tv_address.setText(province + " " + city + " " + district);
                map.put("member_service_province", province);
                map.put("member_service_city", city);
                map.put("member_service_district", district);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    //选择工种
    private void startWorkTypeFragment(List<WorkTypeBean> list) {
        WorkTypeFragment fragment = WorkTypeFragment.newInstance(list);
        fragment.setSelectReasonOnclickListener(new WorkTypeFragment.SelectWorkTypeOnclickListener() {
            @Override
            public void ok(List<String> worktype) {
                String s = "";
                for (int i = 0; i < worktype.size(); i++) {
                    if (i == (worktype.size() - 1)) {
                        s = s + worktype.get(i);
                    } else {
                        s = s + worktype.get(i) + "/";
                    }
                }
                tvWork.setText(s);
            }
        });
        fragment.show(getFragmentManager(), RequestRefundFragment.class.getSimpleName());
    }


    @Override
    public MyDataPersenter createPresenter() {
        return new MyDataPersenter(getApp());
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


    @OnClick({R.id.ivLeft, R.id.img, R.id.tv_sex, R.id.tv_address, R.id.btnSave, R.id.tv_work})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.img:
                show_img();
                break;
            case R.id.tv_sex:
                startSexpop();
                break;
            case R.id.tv_work:
                startWorkTypeFragment(workTypeList);
                break;

            case R.id.tv_address:
                //选择地址
               // hideInputMethod(tv_phone);
                cityPicker();
                break;
            case R.id.btnSave:
                String name = tvName.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入姓名");
                    return;
                }
                String sex = tvSex.getText().toString().trim();
                if (TextUtils.isEmpty(sex)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入性别");
                    return;
                }
                String age = tvAge.getText().toString().trim();
                if (TextUtils.isEmpty(age)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入年龄");
                    return;
                }

                String idNumber = etId.getText().toString().trim();
                if (TextUtils.isEmpty(idNumber)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入身份证号");
                    return;
                }
                String phone = tv_phone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入手机号");
                    return;
                }


                String address = tv_address.getText().toString().trim();
                if (TextUtils.isEmpty(address)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请选择您的所在地址");
                    return;
                }

                String address_detail = tv_address_detail.getText().toString().trim();
                if (TextUtils.isEmpty(address_detail)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入详细地址");
                    return;
                }

                String workType = tvWork.getText().toString().trim().toString().trim();
                if (TextUtils.isEmpty(workType)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请选择工种");
                    return;
                }
                String workAge = tvWorkage.getText().toString().trim();
                if (TextUtils.isEmpty(workAge)) {
                    ToastUtils.showToast(context.getApplicationContext(), "请输入工龄");
                    return;
                }

                map.put("member_real_name", name);
                map.put("member_service_name", name);
                map.put("member_sex", sex);
                map.put("member_age", age);
                map.put("id_number", idNumber);
                map.put("member_service_phone", phone);
                map.put("member_service_detail", address_detail);
                map.put("member_work_type", workType);
                map.put("member_work_age", workAge);
               // map.put("member_head_image", imgPath);
                map.put("special_skill", etSkill.getText().toString());
                map.put("recommend_phone", etRecommend.getText().toString());
                getPresenter().updateMemberDetail(map);
                break;


        }
    }
}