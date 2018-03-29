package com.live.aksd.mvp.fragment.Mine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * @author Created by stone
 * @since 17/8/22
 */

public class AddServerAddressFragment extends DialogFragment {

    public interface OpenSelectOnclickListener {
        //打开地址选择
        void onOpen(String state);
    }

    private OpenSelectOnclickListener onclickListener;

    public void setOpenSelectClickListener(OpenSelectOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }

    @BindView(R.id.area)
    TextView area;
    @BindView(R.id.select_area)
    RelativeLayout selectArea;
    @BindView(R.id.details_address)
    EditText detailsAddress;
    @BindView(R.id.tv_addoredit)
    TextView tv_addoredit;

    private UserBean userBean;
    private String state;

    private Map<String, String> map = new HashMap<>();
    Unbinder unbinder;

    public static AddServerAddressFragment newInstance(UserBean userBean, String state) {
        Bundle args = new Bundle();
        AddServerAddressFragment fragment = new AddServerAddressFragment();
        fragment.userBean = userBean;
        fragment.state = state;
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_editor_server_address, container, false);
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
        if (state.equals("1")){
            tv_addoredit.setText("添加服务地址");
            area.setText("上海市 上海市 浦东新区");
        }else {
            tv_addoredit.setText("编辑服务地址");
            area.setText(userBean.getMember_service_province() + " " + userBean.getMember_service_city() + "  " + userBean.getMember_service_district());
        }

        detailsAddress.setText(userBean.getMember_service_detail());
    }


    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public void hideInputMethod(final EditText v) {

        InputMethodManager imm = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        v.clearFocus();
    }


    @OnClick({R.id.close, R.id.add_new_address, R.id.select_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;
            case R.id.add_new_address:


                String address = area.getText() + "";
                if (TextUtils.isEmpty(address)) {
                    ToastUtils.showToast(getContext().getApplicationContext(), "请选择区域");
                    return;
                }

                String address_detailed = detailsAddress.getText() + "";
                if (TextUtils.isEmpty(address_detailed)) {
                    ToastUtils.showToast(getContext().getApplicationContext(), "请输入详细地址");
                    return;
                }
                userBean.setMember_service_detail(detailsAddress.getText().toString());
                EventBus.getDefault().post(userBean);
                dismiss();
                break;
            case R.id.select_area:
                userBean.setMember_service_detail(detailsAddress.getText().toString());
                onclickListener.onOpen(state);
                dismiss();
                break;


        }
    }

}
