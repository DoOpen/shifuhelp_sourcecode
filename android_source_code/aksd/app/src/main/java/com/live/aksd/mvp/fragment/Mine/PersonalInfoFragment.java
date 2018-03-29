package com.live.aksd.mvp.fragment.Mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.WorkTypeBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.PersonalInfoPersenter;
import com.live.aksd.mvp.presenter.MyDataPersenter;
import com.live.aksd.mvp.view.IMyDataView;
import com.live.aksd.mvp.view.Mine.IPersonalInfoView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.ImageFactory;
import com.live.aksd.util.LoadingUtil;
import com.live.aksd.util.SpSingleInstance;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * @author Created by stone
 * @since 17/8/24
 */

public class PersonalInfoFragment extends BaseFragment<IPersonalInfoView, PersonalInfoPersenter> implements IPersonalInfoView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.ivRight2)
    ImageView ivRight2;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.top_toolbar)
    LinearLayout topToolbar;
    @BindView(R.id.img)
    CircleImageView img;
    @BindView(R.id.upload_img)
    RelativeLayout uploadImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.rl_age)
    RelativeLayout rlAge;
    @BindView(R.id.tv_work)
    TextView tvWork;
    @BindView(R.id.rl_work)
    RelativeLayout rlWork;
    @BindView(R.id.tv_workage)
    TextView tvWorkage;
    @BindView(R.id.rl_workage)
    RelativeLayout rlWorkage;
    @BindView(R.id.rl_certification)
    RelativeLayout rlCertification;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.all_bg)
    LinearLayout allBg;
    Unbinder unbinder;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String path;
    private String imgPath;

    public static PersonalInfoFragment newIntance() {
        Bundle args = new Bundle();
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_personal_info;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.personal_information);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        Glide.with(context).load(Constants.BASE_URL + userBean.getMember_head_image()).diskCacheStrategy(DiskCacheStrategy.ALL).into(img);
        tvName.setText(userBean.getMember_real_name());
        tv_phone.setText(userBean.getMember_phone());
        tv_address.setText(userBean.getMember_service_province() + userBean.getMember_service_city() + userBean.getMember_service_district() + userBean.getMember_service_detail());
        tvSex.setText(userBean.getMember_sex());
        tvAge.setText(userBean.getMember_age());
        tvWork.setText(userBean.getMember_work_type());
        tvWorkage.setText(userBean.getMember_work_age() + getString(R.string.year));
    }


    @Override
    public void updateMemberDetail(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getUser(map);
    }

    @Override
    public void onGetUser(UserBean data) {
        saveUser(data);
        userBean = data;
    }

    @Override
    public void uploadImg(String data) {
        imgPath = data;
        LoadingUtil.hideLoading();
    }


    protected void showImg() {
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
                    Glide.with(getContext()).load(path).error(R.drawable.live_default).into(img);
                    upImg();
                }
                break;
        }
    }

    private void upImg() {
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
                .addFormDataPart("path", "/images/member")
                .addFormDataPart("head_img", _file.getName(), RequestBody.create(MediaType.parse("image/*"), _file))
                .build();
        getPresenter().uploadImg(requestBody);
        LoadingUtil.showLoading(context, getString(R.string.img_loading));
    }

    @Override
    public PersonalInfoPersenter createPresenter() {
        return new PersonalInfoPersenter(getApp());
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


    //----------------------------------------更改头像时间差----------------------------------------------//
    private long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
        Date date = null;   //定义时间类型
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = inputFormat.parse(inVal); //将字符型转换成日期型
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();   //返回毫秒数
    }

    private static String dqsj() {  //此方法用于获得当前系统时间（格式类型2007-11-6 15:10:58）
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date date = new Date(System.currentTimeMillis());
        return df.format(date);  //返回当前时间
    }


    //----------------------------------------------------------------------------------------------//

    @OnClick({R.id.ivLeft, R.id.upload_img, R.id.rl_certification, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.upload_img:
                String dqsj = dqsj();
                String xgsj = userBean.getLast_update_head_img_time().substring(0, 10);
                if (dqsj.equals(xgsj)) {
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage(getString(R.string.head_img_once));
                    builder.setPositiveButton(getString(R.string.know), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();
                    return;

                }
                showImg();
                break;
            case R.id.rl_certification:
                //证书
                startWeb(getString(R.string.my_certificater), "", Constants.BASE_URL + userBean.getMember_certificate(), "");
                break;
            case R.id.btnSave:
                if (TextUtils.isEmpty(imgPath)) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.img_loading_fail));
                    return;
                }
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("member_head_image", imgPath);
                getPresenter().updateMemberDetail(map);
                break;
        }
    }


}
