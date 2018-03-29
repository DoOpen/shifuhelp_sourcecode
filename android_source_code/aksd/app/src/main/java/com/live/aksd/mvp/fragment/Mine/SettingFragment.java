package com.live.aksd.mvp.fragment.Mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.HtmlBean;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.presenter.Mine.SettingPresenter;
import com.live.aksd.mvp.view.Mine.ISettingView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.DataCleanManager;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.live.aksd.R.id.btnExit;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class SettingFragment extends BaseFragment<ISettingView, SettingPresenter> implements ISettingView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.cache_size)
    TextView cachesize;
    private Map<String, String> map1 = new HashMap<>();
    private Map<String, String> map2 = new HashMap<>();
    private String htmlPathOne;
    private String htmlPathTwo;

    public static SettingFragment newIntance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initUI() throws Exception {
        tvTitle.setText(R.string.setting);
        cachesize.setText(DataCleanManager.getTotalCacheSize(context));
    }

    @Override
    public void initData() {
        map1.put("html_name", getString(R.string.about_us));
        getPresenter().getHtmlDetailOne(map1);
        map2.put("html_name", getString(R.string.rl_agreement));
        getPresenter().getHtmlDetailTwo(map2);
    }

    @Override
    public void onGetHtmlDetailTwo(HtmlBean data) {
        htmlPathTwo = data.getHtml_url();
    }

    @Override
    public void onGetHtmlDetailOne(HtmlBean data) {
        htmlPathOne = data.getHtml_url();
    }

    @Override
    public SettingPresenter createPresenter() {
        return new SettingPresenter(getApp());
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

    private void exit() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage(getString(R.string.quit));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                EventBus.getDefault().post(new FirstEvent("3"));
                exitLogin();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }

    @OnClick({R.id.ivLeft, R.id.rl_info, R.id.rl_changepwd, R.id.rl_clean, R.id.rl_aboutUs, R.id.rl_agreement, btnExit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.rl_info:
                startPersonalnfoFragment();
                break;
            case R.id.rl_changepwd:
                startChangePwdFragment();
                break;
            case R.id.rl_clean:
                DataCleanManager.clearAllCache(context);
                try {
                    cachesize.setText(DataCleanManager.getTotalCacheSize(getContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rl_aboutUs:
                startWeb(getString(R.string.about_us), "", Constants.BASE_URL + htmlPathOne, "");
                break;
            case R.id.rl_agreement:
                startWeb(getString(R.string.rl_agreement), "", Constants.BASE_URL + htmlPathTwo, "");
                break;
            case R.id.btnExit:
                exit();
                break;


        }
    }


}
