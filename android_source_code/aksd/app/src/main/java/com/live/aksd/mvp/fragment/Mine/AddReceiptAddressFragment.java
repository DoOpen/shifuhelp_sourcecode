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
import com.live.aksd.bean.RewareAddressBean;
import com.live.aksd.bean.UserBean;

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

public class AddReceiptAddressFragment extends DialogFragment {


    public interface OpenSelectOnclickListener {

        //打开地址选择
        void onOpen(String state);

        //增加／修改地址
        void onOk(RewareAddressBean bean,String state);

    }

    private OpenSelectOnclickListener onclickListener;

    public void setOpenSelectClickListener(OpenSelectOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }


    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.area)
    TextView area;
    @BindView(R.id.select_area)
    RelativeLayout selectArea;
    @BindView(R.id.details_address)
    EditText detailsAddress;
    @BindView(R.id.postal_code)
    EditText postalCode;
    @BindView(R.id.tv_addoredit)
    TextView tv_addoredit;

    private UserBean userBean;
    private RewareAddressBean rewareAddressbean;
    private Map<String, String> map = new HashMap<>();
    private String state;

    Unbinder unbinder;

    public static AddReceiptAddressFragment newInstance(RewareAddressBean rewareAddressbean, String state) {
        Bundle args = new Bundle();
        AddReceiptAddressFragment fragment = new AddReceiptAddressFragment();
        fragment.rewareAddressbean = rewareAddressbean;
        fragment.state = state;


        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_editor_address, container, false);
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
        if (state.equals("1")) {
            tv_addoredit.setText("添加收货地址");
        } else {
            tv_addoredit.setText("编辑收货地址");
        }
        if (rewareAddressbean.getAddress_province() != null) {
            area.setText(rewareAddressbean.getAddress_province() + " " + rewareAddressbean.getAddress_city() + "  " + rewareAddressbean.getAddress_district());
        }else{
            area.setText("浙江省 金华市 义乌市");
            rewareAddressbean.setAddress_province("浙江省");
            rewareAddressbean.setAddress_city("金华市");
            rewareAddressbean.setAddress_district("义乌市");
        }

        etName.setText(rewareAddressbean.getAddress_name());
        etPhone.setText(rewareAddressbean.getAddress_mobile());
        postalCode.setText(rewareAddressbean.getAddress_zip_code());
        detailsAddress.setText(rewareAddressbean.getAddress_detail());
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

                String name = etName.getText() + "";
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showToast(getContext().getApplicationContext(), "请填写收货人姓名");
                    return;
                }

                String mobile = etPhone.getText() + "";
                if (TextUtils.isEmpty(mobile)) {
                    ToastUtils.showToast(getContext().getApplicationContext(), "请填写收货人手机号");
                    return;
                }
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

                String mpostalCode = postalCode.getText() + "";
                if (!TextUtils.isEmpty(mpostalCode)) {
                    rewareAddressbean.setAddress_zip_code(mpostalCode);
                }
                rewareAddressbean.setAddress_name(name);
                rewareAddressbean.setAddress_mobile(mobile);
                rewareAddressbean.setAddress_detail(address_detailed);
                onclickListener.onOk(rewareAddressbean,state);
                dismiss();
                break;
            case R.id.select_area:
                rewareAddressbean.setAddress_name(etName.getText().toString());
                rewareAddressbean.setAddress_mobile(etPhone.getText().toString());
                rewareAddressbean.setAddress_detail(detailsAddress.getText().toString());
                onclickListener.onOpen(state);
                dismiss();
                break;


        }
    }

}
