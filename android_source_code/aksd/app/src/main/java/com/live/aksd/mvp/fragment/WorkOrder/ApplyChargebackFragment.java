package com.live.aksd.mvp.fragment.WorkOrder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.king.base.util.ToastUtils;
import com.live.aksd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/9/2
 * 工单填写退单原因的dialogfragment
 *
 */

public class ApplyChargebackFragment extends DialogFragment {

    public interface ApplyChargebackOnclickListener {
        void onOk(String reason);

    }

    private ApplyChargebackOnclickListener onclickListener;

    public void setApplyChargebackOnclickListener(ApplyChargebackOnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }


    @BindView(R.id.et_reason)
    EditText et_reason;
    Unbinder unbinder;


    public static ApplyChargebackFragment newInstance() {
        Bundle args = new Bundle();
        ApplyChargebackFragment fragment = new ApplyChargebackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_apply_chargeback, container, false);
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

    }


    @OnClick({R.id.close, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;
            case R.id.btn_ok:
                if (TextUtils.isEmpty(et_reason.getText().toString())){
                    ToastUtils.showToast(getContext().getApplicationContext(),getString(R.string.refund_reson));
                    return;
                }
                onclickListener.onOk(et_reason.getText().toString());
                dismiss();

                break;
        }
    }
}
