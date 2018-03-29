package com.live.aksd.mvp.activity;

import android.content.Intent;
import android.view.animation.Animation;

import com.king.base.SplashActivity;
import com.live.aksd.Constants;
import com.live.aksd.MainActivity;
import com.live.aksd.R;
import com.live.aksd.bean.UserBean;
import com.live.aksd.util.SPUtils;



/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/15
 */

public class WelcomeActivity extends SplashActivity {

    public static final int LOGIN_FRAGMENT = 0X04;
    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public Animation.AnimationListener getAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                UserBean userBean = SPUtils.getObj1(WelcomeActivity.this, Constants.USER_BEAN);
                if (userBean == null) {
                    startLogin();
                } else {
                    if ("1".equals(userBean.getMember_state())) {
                        startActivityFinish(MainActivity.class);
                    } else {
                        startLogin();
                    }
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    protected void startLogin() {
        Intent intent = new Intent(WelcomeActivity.this, ContentActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT, LOGIN_FRAGMENT);
        startActivity(intent);
    }
}
