package com.live.aksd.mvp.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.king.base.util.StringUtils;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.base.SimpleFragment;
import com.live.aksd.util.SpSingleInstance;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by sh-lx on 2017/8/16.
 */

public class JsSearchFragment extends SimpleFragment {



    @BindView(R.id.edit_search)
    EditText etKey;

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    Unbinder unbinder;
    @BindView(R.id.btn_search)
    TextView btnSearch;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.vError)
    LinearLayout vError;
    @BindView(R.id.fc)
    FrameLayout fc;
    private int popupWidth;
    private int popupHeight;
    private View parentView;
    private PopupWindow pop1;
    private TextView sp_tv;
    private TextView sj_tv;
    private String url = "";
    private String type;
    private JsWebFragment webFragment;
    private boolean is_xz = false;
    private UserBean userBean;
    private String member_id, member_token;

    public static JsSearchFragment newInstance(String url, String type) {
        Bundle args = new Bundle();

        JsSearchFragment fragment = new JsSearchFragment();
        fragment.url = url;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_js_web;
    }

    @Override
    public void initUI() {
        pop1();
        webFragment = JsWebFragment.newInstance(url, "", "1", "");
        replaceChildFragment(R.id.fc, webFragment);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if (userBean != null) {

            member_id = userBean.getMember_id();
            member_token = userBean.getMember_token();
        } else {
            member_id = "";
            member_token = "";
        }
    }

    private void pop1() {
       /* pop1 = new PopupWindow(getActivity());
        View view = View.inflate(getActivity(), R.layout.pop3, null);
        RelativeLayout all_view = (RelativeLayout) view.findViewById(R.id.all_view);

        LinearLayout ll_sp = (LinearLayout) view.findViewById(R.id.ll_sp);
        LinearLayout ll_sj = (LinearLayout) view.findViewById(R.id.ll_sj);


        sp_tv = (TextView) view.findViewById(R.id.sp_tv);
        sj_tv = (TextView) view.findViewById(R.id.sj_tv);


        ll_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp_tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                sj_tv.setTextColor(getResources().getColor(R.color.white));
                xz_tv.setText(sp_tv.getText().toString());
                pop1.dismiss();

            }
        });
        ll_sj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp_tv.setTextColor(getResources().getColor(R.color.white));
                sj_tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                xz_tv.setText(sj_tv.getText().toString());
                pop1.dismiss();
            }
        });


        all_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop1.dismiss();
            }
        });

        pop1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop1.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop1.setBackgroundDrawable(new BitmapDrawable());
        pop1.setFocusable(true);
        pop1.setOutsideTouchable(true);
        pop1.setContentView(view);

        //获取自身的长宽高
        //parentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupHeight = view.getMeasuredHeight();
        popupWidth = view.getMeasuredWidth();
        popupHeight = btn_dq.getMeasuredHeight();*/
    }

    /**
     * 设置显示在v上方(以v的左边距为开始位置)
     *
     * @param v
     */
    public void showUp(View v) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //在控件上方显示
        pop1.showAtLocation(v, Gravity.NO_GRAVITY, (location[0]), location[1] + 100);
    }

    private boolean checkInputKey() {
        if (StringUtils.isBlank(etKey.getText())) {
            ToastUtils.showToast(context.getApplicationContext(), R.string.tips_search_keywords_cannot_be_empty);
            return false;
        }
        return true;
    }

    @OnClick({R.id.ivLeft, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.btn_search:

                if (StringUtils.isBlank(etKey.getText())) {
                    ToastUtils.showToast(context.getApplicationContext(), R.string.tips_search_keywords_cannot_be_empty);
                    return ;
                }
                //hideInputMethod(etKey);
                    webFragment = JsWebFragment.newInstance(Constants.BASE_URL+"app-mall1/index.html#/search_goods?goodsName=" + etKey.getText() + "&member_id=" + member_id + "&member_token=" + member_token+"&add="+type, "", "1", "");
                    replaceChildFragment(R.id.fc, webFragment);
                break;


        }
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
