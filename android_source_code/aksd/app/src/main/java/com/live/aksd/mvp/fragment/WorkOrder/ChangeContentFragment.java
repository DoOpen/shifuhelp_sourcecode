package com.live.aksd.mvp.fragment.WorkOrder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.base.util.ToastUtils;
import com.live.aksd.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuxiaoxiang on 2018/3/14.
 */

public class ChangeContentFragment extends BottomSheetDialogFragment {


    Unbinder unbinder;
    @BindView(R.id.class_name)
    TextView className;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.tvMthod)
    TextView tvMthod;
    @BindView(R.id.tvArea)
    TextView tvArea;
    @BindView(R.id.tvrequest)
    TextView tvrequest;
    @BindView(R.id.etMthod)
    EditText etMthod;
    @BindView(R.id.etArea)
    EditText etArea;
    @BindView(R.id.etrequest)
    EditText etrequest;
    @BindView(R.id.ok)
    TextView ok;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnclickListener {

        void ok(String content, String are, String mothod);
    }

    private OnclickListener onclickListener;

    public void setOnclickListener(OnclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }


    private String content;
    private String are;
    private String mothed;

    public static ChangeContentFragment newInstance(String content, String are, String mothed) {
        Bundle args = new Bundle();
        ChangeContentFragment fragment = new ChangeContentFragment();
        fragment.content = content;
        fragment.are = are;
        fragment.mothed = mothed;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initViews() {
        if (!TextUtils.isEmpty(content)) {
            etrequest.setText(content);
        }
        if (!TextUtils.isEmpty(mothed)) {
            etMthod.setText(mothed);
        }
        if (!TextUtils.isEmpty(are)) {
            etArea.setText(are);
        }

    }

    @OnClick({R.id.close, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;
            case R.id.ok:
                if (TextUtils.isEmpty(etMthod.getText().toString().trim())) {
                    ToastUtils.showToast(getContext().getApplicationContext(), getString(R.string.can_null));
                    return;
                }
                if (TextUtils.isEmpty(etArea.getText().toString().trim())) {
                    ToastUtils.showToast(getContext().getApplicationContext(), getString(R.string.can_null));
                    return;
                }
                dismiss();
                onclickListener.ok(etrequest.getText().toString().trim(), etArea.getText().toString().trim(), etMthod.getText().toString().trim());
                break;

        }
    }


}
