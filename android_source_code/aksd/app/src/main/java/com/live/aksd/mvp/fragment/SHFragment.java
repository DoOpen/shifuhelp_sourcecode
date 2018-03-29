package com.live.aksd.mvp.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.live.aksd.MainActivity;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.SHPersenter;
import com.live.aksd.mvp.view.ISHView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;
import com.pingplusplus.android.PaymentActivity;
import com.pingplusplus.android.Pingpp;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * Created by sh-lx on 2017/8/24.
 */

public class SHFragment extends BaseFragment<ISHView, SHPersenter> implements ISHView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;


    @BindView(R.id.sqz)
    ImageView sqz;
    @BindView(R.id.shz)
    ImageView shz;
    @BindView(R.id.shg)
    ImageView shg;


    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvID)
    TextView tvID;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvWorkType)
    TextView tvWorkType;
    @BindView(R.id.tvWorkAge)
    TextView tvWorkage;
    @BindView(R.id.tvSkill)
    TextView tvSkill;
    @BindView(R.id.tvRecommend)
    TextView tvRecommend;

    @BindView(R.id.tvRefuse)
    TextView tvRefuse;
    @BindView(R.id.tvAgain)
    TextView tvAgain;

    @BindView(R.id.tvResult)
    TextView tvResult;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    //电话
    private String number;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    public static SHFragment newIntance() {
        Bundle args = new Bundle();
        SHFragment fragment = new SHFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_registered_information;
    }

    @Override
    public void initUI() {
        tvTitle.setText("资料审核");
    }

    @Override
    public void initData() {

        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getUser(map);
    }

    @Override
    public SHPersenter createPresenter() {
        return new SHPersenter(getApp());
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
    public void onGetUser(UserBean data) {
        saveUser(data);

        //member_state -1未审核0审核中1审核通过2审核未通过
        switch (data.getMember_state()) {
            case "0":
                shz.setImageResource(R.drawable.zt_h);
                break;
            case "1":
                shz.setImageResource(R.drawable.zt_h);
                shg.setImageResource(R.drawable.zt_h);
                break;
            case "2":
                tvResult.setText("审核未通过");
                tvResult.setTextColor(getResources().getColor(R.color.bg_2));
                tvRefuse.setVisibility(View.VISIBLE);
                tvRefuse.setText(userBean.getCustome_refuse_note());
                tvAgain.setVisibility(View.VISIBLE);
                shz.setImageResource(R.drawable.zt_h);
                shg.setImageResource(R.drawable.red_point);
                break;
            case "-1":
                break;
        }

        tvName.setText(data.getMember_real_name());
        tvSex.setText(data.getMember_sex());
        tvAge.setText(data.getMember_age());
        tvPhone.setText(data.getMember_service_phone());
        tvAddress.setText(data.getMember_service_province() + data.getMember_service_city() + data.getMember_service_district() + data.getMember_service_detail());
        tvWorkType.setText(data.getMember_work_type());
        tvWorkage.setText(data.getMember_work_age() + "年");
        tvID.setText(data.getId_number());
        if (!TextUtils.isEmpty(data.getSpecial_skill())) {
            tvSkill.setText(data.getSpecial_skill());
        }
        if (!TextUtils.isEmpty(data.getRecommend_phone())) {
            tvRecommend.setText(data.getRecommend_phone());
        }
    }


    //----------------------------------------打电话----------------------------------------------//
    private void sq() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                // 弹窗需要解释为何需要该权限，再次请求授权
                //Toast.makeText(MainActivity.this, "请授权！", Toast.LENGTH_LONG).show();
                ToastUtils.showToast(context.getApplicationContext(), "请授权！");
                // 帮跳转到该应用的设置界面，让用户手动授权
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                // 不需要解释为何需要该权限，直接请求授权
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            // 已经获得授权，可以打电话
            CallPhone();
        }
    }

    private void CallPhone() {
        Intent intent = new Intent(); // 意图对象：动作 + 数据
        intent.setAction(Intent.ACTION_CALL); // 设置动作
        Uri data = Uri.parse("tel:" + number); // 设置数据
        intent.setData(data);
        startActivity(intent); // 激活Activity组件
    }

    @OnClick({R.id.ivLeft, R.id.tvCustomer,R.id.tvAgain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvAgain:
              startMydata();
                finish();
                break;
            case R.id.tvCustomer: //联系客服
                number = getText(R.string.lxrPhone).toString();
                final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage("是否拨打" + number + "客服电话？");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sq();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();

                break;

        }
    }


}
