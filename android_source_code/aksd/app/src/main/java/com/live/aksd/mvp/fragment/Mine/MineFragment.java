package com.live.aksd.mvp.fragment.Mine;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.MinePresenter;
import com.live.aksd.mvp.view.Mine.IMineView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.SpSingleInstance;
import com.live.aksd.util.TranslucentScrollView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * Created by sh-lx on 2017/8/21.
 */

public class MineFragment extends BaseFragment<IMineView, MinePresenter> implements IMineView {


    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvWorkType)
    TextView tvWorkType;
    @BindView(R.id.sign_ok)
    Button sign_ok;
    @BindView(R.id.sign)
    Button sign;
    @BindView(R.id.tv_tousu)
    TextView tv_tousu;

    Unbinder unbinder;


    //分享相关
    private UMImage mImage = null;
    private SHARE_MEDIA mShare_meidia = SHARE_MEDIA.WEIXIN;
    private String mShareUrl = "http://wx.shifuhelp.com/extension.html";
    private String mCoverUrl = "师傅上门Pro是一款专业的安装维修服务手机软件，让安装维修从此变得更便捷，赶紧下载体验吧！";
    private String mTitle = "师傅上门Pro"; //标题


    private AlertDialog mDialog;
    private String htmlPath;

    //电话
    private String number = "";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> htmlMap = new HashMap<>();
    private UserBean userBean;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initUI() {
        tv_tousu.setText("投诉热线：" + getText(R.string.lxrPhone));

    }

    @Override
    public void initData() {
        htmlMap.put("html_name", getString(R.string.standard));
        getPresenter().getHtmlDetail(htmlMap);
    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        getPresenter().getUser(map);
    }


    private void showSignPop(String data) {
        sign_ok.setVisibility(View.VISIBLE);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.pop_sign, null);
        final PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        TextView ok = (TextView) contentView.findViewById(R.id.tv_ok);
        TextView scores = (TextView) contentView.findViewById(R.id.scores);
        scores.setText("+" + data);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.anim.dialog_in);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(getView(), Gravity.CENTER, 0, 0);
    }


    @Override
    public void onGetUser(UserBean userCenterBean) {
        saveUser(userCenterBean);

        if (userCenterBean.getIs_sign().equals("1")) {
            sign_ok.setVisibility(View.VISIBLE);
        } else {
            sign_ok.setVisibility(View.GONE);
        }
        tvName.setText(userCenterBean.getMember_real_name());
        tvWorkType.setText(userCenterBean.getMember_work_type());
        Glide.with(context).load(Constants.BASE_URL + userCenterBean.getMember_head_image()).error(R.drawable.logo).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatar);

    }

    @Override
    public void onInsertSign(String data) {
        showSignPop(data);
    }

    @Override
    public void onGetHtmlDetail(HtmlBean data) {
        htmlPath = data.getHtml_url();
    }


    @OnClick({R.id.settting, R.id.message, R.id.my_wallet, R.id.serve_address,
            R.id.my_certificate, R.id.cost_standards, R.id.my_reported, R.id.software_related, R.id.contact_serve,
            R.id.share_sofeware, R.id.sign, R.id.tv_tousu, R.id.history_order, R.id.skills_identified, R.id.school})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.settting:
                //设置
                startSettingFragment();
                break;
            case R.id.message:
                //消息
                startMessageFragment();
                break;
            case R.id.my_wallet:
                //钱包
                startMyWalletFragment();
                break;

            case R.id.history_order:
                //历史工单
                startHistoryOrderFragment();
                break;
            case R.id.serve_address:
                //服务地址
                startServeAddressFragment();
                break;

            case R.id.skills_identified:
                //技能认定
                startSkillIdentifiedFragment("1");
                break;
            case R.id.school:
                //培训学院
                startSkillIdentifiedFragment("2");
                break;
            case R.id.my_certificate:
                //证书
                startWeb(getString(R.string.my_certificate), "", Constants.BASE_URL + userBean.getMember_certificate(), "");
                break;
            case R.id.cost_standards:
                //费用标准
                startWeb(getString(R.string.standard_costs), "", Constants.BASE_URL + htmlPath, "");

                break;
            case R.id.my_reported:
                //报备
                startMyReportedFragment();
                break;
            case R.id.software_related:
                //软件相关
                startSoftwareRelatedFragment();
                break;
            case R.id.contact_serve:
                //联系客服
                number = getText(R.string.lxrPhone).toString();
                final CustomDialog.Builder phoneBuilder = new CustomDialog.Builder(context);
                phoneBuilder.setMessage(getString(R.string.call) + number + getString(R.string.customer_phone));
                phoneBuilder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sq();
                        dialog.dismiss();
                    }
                });
                phoneBuilder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                phoneBuilder.onCreate().show();
                break;
            case R.id.share_sofeware:
                //软件分享
                showShareDialog();
                break;
            case R.id.sign:
                //签到
                getPresenter().getInsertSign(map);
                break;
            case R.id.tv_tousu:
                //联系客服
                number = getText(R.string.lxrPhone).toString();
                final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
                builder1.setMessage(getString(R.string.call) + number + getString(R.string.complaints_phone));
                builder1.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sq();
                        dialog.dismiss();
                    }
                });
                builder1.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder1.onCreate().show();
                break;
        }
    }


    //----------------------------------------打电话----------------------------------------------//
    private void sq() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                ToastUtils.showToast(context.getApplicationContext(), getString(R.string.authorized));
                // 帮跳转到该应用的设置界面，让用户手动授权
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            CallPhone();
        }
    }

    private void CallPhone() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL); // 设置动作
        Uri data = Uri.parse("tel:" + number); // 设置数据
        intent.setData(data);
        startActivity(intent); // 激活Activity组件
    }

    //----------------------------------------分享-----------------------------------------------//
    private void showShareDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.share_dialog, null);
        mDialog = new AlertDialog.Builder(context).create();
        mDialog.setCanceledOnTouchOutside(true); // 外部点击取消
        mDialog.show();// 显示创建的AlertDialog，并显示，必须放在Window设置属性之前
        Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(view);//这一步必须指定，否则不出现弹窗
            WindowManager.LayoutParams mParams = window.getAttributes();
            mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
            window.setBackgroundDrawableResource(android.R.color.white);
            window.setWindowAnimations(R.style.AnimBottom);
            window.setAttributes(mParams);
        }

        TextView btn_wx = (TextView) view.findViewById(R.id.btn_share_wx);
        TextView btn_circle = (TextView) view.findViewById(R.id.btn_share_circle);
        TextView btn_qq = (TextView) view.findViewById(R.id.btn_share_qq);
        TextView btn_qzone = (TextView) view.findViewById(R.id.btn_share_qzone);
        TextView btn_wb = (TextView) view.findViewById(R.id.btn_share_wb);
        TextView btn_url = (TextView) view.findViewById(R.id.btn_share_url);

        btn_wx.setOnClickListener(mShareBtnClickListen);
        btn_circle.setOnClickListener(mShareBtnClickListen);
        btn_qq.setOnClickListener(mShareBtnClickListen);
        btn_qzone.setOnClickListener(mShareBtnClickListen);
        btn_wb.setOnClickListener(mShareBtnClickListen);
        btn_url.setOnClickListener(mShareBtnClickListen);
    }

    private View.OnClickListener mShareBtnClickListen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_share_wx:
                    mShare_meidia = SHARE_MEDIA.WEIXIN;
                    mDialog.dismiss();
                    break;
                case R.id.btn_share_circle:
                    mShare_meidia = SHARE_MEDIA.WEIXIN_CIRCLE;
                    mDialog.dismiss();
                    break;
                case R.id.btn_share_qq:
                    mShare_meidia = SHARE_MEDIA.QQ;
                    mDialog.dismiss();
                    break;
                case R.id.btn_share_qzone:
                    mShare_meidia = SHARE_MEDIA.QZONE;
                    mDialog.dismiss();
                    break;
                case R.id.btn_share_wb:
                    mShare_meidia = SHARE_MEDIA.SINA;
                    mDialog.dismiss();
                    break;
                case R.id.btn_share_url:
                    ClipboardManager manager = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
                    manager.setText(mShareUrl);
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.copy_success));
                    mDialog.dismiss();
                    return;

                default:
                    break;
            }

            ShareAction shareAction = new ShareAction(getActivity());
            UMWeb web = new UMWeb(mShareUrl);
            mImage = new UMImage(context, R.drawable.logo_um);
            web.setThumb(mImage);
            web.setTitle(mTitle);
            web.setDescription(mCoverUrl);
            shareAction.withMedia(web);
            shareAction.setCallback(umShareListener);
            shareAction.setPlatform(mShare_meidia).share();
        }
    };


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.share_success));
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.share_fail));
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showToast(context.getApplicationContext(), getString(R.string.share_cancel));
        }
    };

    //----------------------------------------------------------------------------------------------//
    @Override
    public MinePresenter createPresenter() {
        return new MinePresenter(getApp());
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
      /*  if (e.getMessage().equals(getText(R.string.pingding).toString())) {
            EventBus.getDefault().post(new FirstEvent("3"));
            exitLogin();
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
