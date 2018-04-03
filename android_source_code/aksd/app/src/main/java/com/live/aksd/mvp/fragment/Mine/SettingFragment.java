package com.live.aksd.mvp.fragment.Mine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;
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
    @BindView(R.id.tvVoid)
    TextView tvVoid;
    Unbinder unbinder;
    @BindView(R.id.rl_changepwd)
    TextView rlChangepwd;
    @BindView(R.id.rl_clean)
    TextView rlClean;
    @BindView(R.id.swVoid)
    Switch swVoid;
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
		//sfsm zhoushilei: add code @{
		cachesize.setText(DataCleanManager.getTotalCacheSize(context));
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        String voice = sharedPre.getString("voice", "");
        if ("on".equals(voice)) {
            swVoid.setChecked(true);
            swVoid.setText(R.string.already_no);
        } else {
            swVoid.setChecked(false);
            swVoid.setText(R.string.already_off);
        }
     
        swVoid.setTextColor(getResources().getColor(R.color.gray_20));
        swVoid.setTextSize(12);
        swVoid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swVoid.setText(R.string.already_no);
                    saveVoid(context, "on");

                } else {
                    swVoid.setText(R.string.already_off);
                    saveVoid(context, "off");
                }
            }
        });
		// @}
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

	//sfsm zhoushilei: add code @{
    /**
     * ??SharedPreferences?????????
     *
     * @param context
     * @param state
     */
    public static void saveVoid(Context context, String state) {
        //??SharedPreferences??
        SharedPreferences sharedPre = context.getSharedPreferences("config", MODE_PRIVATE);
        //??Editor??
        SharedPreferences.Editor editor = sharedPre.edit();
        //????
        editor.putString("voice", state);
        //??
        editor.commit();
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
	// @}
}
