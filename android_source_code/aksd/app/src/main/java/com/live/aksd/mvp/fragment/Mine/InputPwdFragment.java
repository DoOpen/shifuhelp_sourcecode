package com.live.aksd.mvp.fragment.Mine;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.live.aksd.R;
import com.live.aksd.view.PassWordEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 17/9/21
 */

public class InputPwdFragment extends DialogFragment {


    public interface OnOverListener {
        //输入完成
        void onOver(String state);
    }

    private OnOverListener onOverListener;
    public void setOnOverListener(OnOverListener onOverListener) {
        this.onOverListener = onOverListener;
    }


    @BindView(R.id.pwd)
    PassWordEditText pwd;
    Unbinder unbinder;



    public static InputPwdFragment newInstance() {
        Bundle args = new Bundle();
        InputPwdFragment fragment = new InputPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_input_pwd, container, false);
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
        pwd.setOnTextEndListener(new PassWordEditText.OnTextEndListener() {
            @Override
            public void onTextEndListener(String textString) {
                onOverListener.onOver(textString);
            }
        });
        new Handler().postDelayed(new Runnable(){
            public void run() {
                showInputMethod(pwd);
            }
        }, 100);

    }

    /**
     * 显示软键盘
     *
     * @param v
     */
    public void showInputMethod(final PassWordEditText v) {

        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getApplicationContext()
                .getSystemService(getContext().getApplicationContext().INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

}
