package com.live.aksd.mvp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.king.base.util.LogUtils;
import com.live.aksd.App;
import com.live.aksd.Constants;
import com.live.aksd.bean.OrderDetailBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.mvp.activity.ContentActivity;
import com.live.aksd.util.SPUtils;
import com.live.aksd.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/20
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends MvpFragment<V, P> {

    protected Context context;

    private View rootView;

    private Unbinder mUnbinder;

    protected ProgressDialog mProgressDialog;

    public <T extends View> T findView(@IdRes int id) {
        return (T) rootView.findViewById(id);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        rootView = inflater.inflate(getRootViewId(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        LogUtils.d("onCreateView");

        mProgressDialog = new ProgressDialog(getActivity());
        // mProgressDialog.setCancelable(false);//除了自己以外的所有地方 包含返回键
        mProgressDialog.setCanceledOnTouchOutside(false);// 点击自己以外的地方 不允许放弃
        try {
            initUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

    public View getRootView() {
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public void replaceChildFragment(@IdRes int id, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public App getApp() {
        return (App) getActivity().getApplication();
    }

    public <T> void toSetList(List<T> list, List<T> newList, boolean isMore) {

        if (list != null && newList != null) {
            synchronized (BaseFragment.class) {
                if (!isMore) {
                    list.clear();
                }
                list.addAll(newList);
            }
        }
    }


    public static void registerEvent(Object obj) {
        EventBus.getDefault().register(obj);
    }

    public static void unregisterEvent(Object obj) {
        EventBus.getDefault().unregister(obj);
    }

    public static void sendEvent(Object obj) {
        EventBus.getDefault().post(obj);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Object obj) {

    }

    //--------------------------------

    protected Intent getIntent() {
        return getActivity().getIntent();
    }


    protected Intent getFragmentIntent(int fragmentKey) {
        Intent intent = getContentActivityIntent();
        intent.putExtra(Constants.KEY_FRAGMENT, fragmentKey);
        return intent;
    }

    protected Intent getContentActivityIntent() {
        return new Intent(context, ContentActivity.class);
    }

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(context, cls));
    }


    protected void finish() {
        getActivity().finish();
    }


    protected void startWeb(String title, String url) {
        Intent intent = getFragmentIntent(Constants.WEB_FRAGMENT);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_URL, url);
        startActivity(intent);
    }

    protected void startWeb(String title, String url, String type) {
        Intent intent = getFragmentIntent(Constants.WEB_FRAGMENT);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_URL, url);
        intent.putExtra(Constants.KEY_SLUG, type);
        startActivity(intent);
    }

    protected void startWeb(String title, String img, String url, String type) {
        Intent intent = getFragmentIntent(Constants.WEB_FRAGMENT);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.IMG, img);
        intent.putExtra(Constants.KEY_URL, url);
        intent.putExtra(Constants.KEY_SLUG, type);
        startActivity(intent);
    }

    protected void startJSWeb(String url, String type) {
        Intent intent = getFragmentIntent(Constants.JSWEB_FRAGMENT);
        intent.putExtra(Constants.KEY_URL, url);
        intent.putExtra(Constants.KEY_SLUG, type);
        startActivity(intent);
    }

    protected void startSYT() {
        Intent intent = getFragmentIntent(Constants.SYT);
        startActivity(intent);
    }

    protected void startMD(String id) {
        Intent intent = getFragmentIntent(Constants.MD);
        intent.putExtra(Constants.ORDER_ID, id);
        startActivity(intent);
    }

    //我的资料
    protected void startMydata() {
        Intent intent = getFragmentIntent(Constants.MY_DATA);
        startActivity(intent);
    }

    //资料审核
    protected void startMySH() {
        Intent intent = getFragmentIntent(Constants.MY_SH);
        startActivity(intent);
    }

    //信息报备
    protected void startInformationreportedFragment() {
        Intent intent = getFragmentIntent(Constants.INFORMATION_REPOTRTED);
        startActivity(intent);
    }

    //设置
    protected void startSettingFragment() {
        Intent intent = getFragmentIntent(Constants.SZ);
        startActivity(intent);
    }

    //消息
    protected void startMessageFragment() {
        Intent intent = getFragmentIntent(Constants.MESSAGE);
        startActivity(intent);
    }

    //我的订单
    protected void startMyOrderFragment(String  state) {

        Intent intent = getFragmentIntent(Constants.MY_ORDER);
        intent.putExtra(Constants.STATE, state);
        startActivity(intent);
    }

    //售后
    protected void startAfterSaleFragment() {
        Intent intent = getFragmentIntent(Constants.AFTER_SALE);
        startActivity(intent);
    }

    //我的钱包
    protected void startMyWalletFragment() {
        Intent intent = getFragmentIntent(Constants.MY_WALLET);
        startActivity(intent);
    }

    //我的积分
    protected void startMyScoresFragment() {
        Intent intent = getFragmentIntent(Constants.MY_SCORES);
        startActivity(intent);
    }

    //服务地址
    protected void startServeAddressFragment() {
        Intent intent = getFragmentIntent(Constants.SERVE_ADDRESS);
        startActivity(intent);
    }

    //收货地址
    protected void startRewareAddressFragment() {
        Intent intent = getFragmentIntent(Constants.REWARD_ADDRESS);
        startActivity(intent);
    }

    //我的收藏
    protected void startMyCollectionFragment() {
        Intent intent = getFragmentIntent(Constants.MY_COLLECTION);
        startActivity(intent);
    }

    //打开优惠卷
    protected void startCouponFragment() {
        Intent intent = getFragmentIntent(Constants.COUPON);
        startActivity(intent);
    }

    //我的报备
    protected void startMyReportedFragment() {
        Intent intent = getFragmentIntent(Constants.MY_REPORTED);
        startActivity(intent);
    }

    //软件相关
    protected void startSoftwareRelatedFragment() {
        Intent intent = getFragmentIntent(Constants.SOFTWARE_RELATED);
        startActivity(intent);
    }

    //联系客服
    protected void startContactServeFragment() {
        Intent intent = getFragmentIntent(Constants.CONTACT_SERVICE);
        startActivity(intent);
    }

    //软件分享
    protected void startShareSoftwareFragment() {
        Intent intent = getFragmentIntent(Constants.SHARE_SOFRWARE);
        startActivity(intent);
    }

    //个人资料
    protected void startPersonalnfoFragment() {
        Intent intent = getFragmentIntent(Constants.PERSONAL_INFO);
        startActivity(intent);
    }

    //修改密码
    protected void startChangePwdFragment() {
        Intent intent = getFragmentIntent(Constants.CHANGE_PWD);
        startActivity(intent);
    }

    //系统消息
    protected void startSystemMessageFragment() {
        Intent intent = getFragmentIntent(Constants.SYSTEM_MESSAGE);
        startActivity(intent);
    }

    //订单消息
    protected void startOrderMessageFragment() {
        Intent intent = getFragmentIntent(Constants.RORDER_MESSAGE);
        startActivity(intent);
    }

    //报备详情
    protected void startReportedDetailFragment(String reported_id) {
        Intent intent = getFragmentIntent(Constants.REPOTED_DETAIL);
        intent.putExtra(Constants.REPORTED_ID, reported_id);


        startActivity(intent);
    }


    //绑定微信
    protected void startBindAlipayFragment() {
        Intent intent = getFragmentIntent(Constants.BIND_ALIPAY);
        startActivity(intent);
    }

    //绑定微信
    protected void startBindWechatFragment() {
        Intent intent = getFragmentIntent(Constants.BIND_WECHAT);
        startActivity(intent);
    }

    //绑定银行卡
    protected void startBindBankFragment() {
        Intent intent = getFragmentIntent(Constants.BIND_BANK);
        startActivity(intent);
    }

    //售后订单详情
    protected void startAfterSaleDetailFragment(String refund_id) {
        Intent intent = getFragmentIntent(Constants.AFTER_SALE_DETAIL);
        intent.putExtra(Constants.REFUND_ID, refund_id);
        startActivity(intent);
    }

    //我的订单详情
    protected void startMyOrgerDetailsFragment(String order_id) {
        Intent intent = getFragmentIntent(Constants.ORGER_DETIAILS_FRAGMENT);
        intent.putExtra(Constants.ORDER_ID, order_id);
        startActivity(intent);
    }


    //添加地址
   /* protected void startAddNewAddressFragment(UserBean userBean,String state) {
        Intent intent = getFragmentIntent(Constants.ADD_NEW_ADDRESS);
        intent.putExtra(Constants.USER_BEAN, userBean);
        intent.putExtra(Constants.ADDRESS_STATE, state);
        startActivity(intent);
    }
*/

    //登录
    protected void startLogin() {
        Intent intent = getFragmentIntent(Constants.LOGIN_FRAGMENT);
        startActivity(intent);
    }

    //注册
    protected void statrRegistered() {
        Intent intent = getFragmentIntent(Constants.LOGIN_SIGNUP);
        startActivity(intent);
    }

    //忘记密码
    protected void statrForgetPwd() {
        Intent intent = getFragmentIntent(Constants.LOGIN_FORGET);
        startActivity(intent);
    }

    //保存个人信息
    protected void saveUser(UserBean userBean) {
        // 登录成功
        SPUtils.saveObJ1(getContext(), Constants.USER_BEAN, userBean);
        SpSingleInstance.getSpSingleInstance().setUserBean(userBean);
        SPUtils.saveString(getActivity(), "img", userBean.getMember_head_image());
        SPUtils.saveString(getActivity(), "username", userBean.getMember_real_name());
        SPUtils.saveString(getActivity(), "uid", userBean.getMember_id());
        SPUtils.saveString(getActivity(), "token", userBean.getMember_token());
    }

    //退出登陆返回登陆页面
    protected void exitLogin() {
        SPUtils.saveObJ1(getContext(), Constants.USER_BEAN, null);
        startLogin();
        finish();

    }

    //工单详情
    protected void startWorkOrderDetailsFragment(String work_order_id, String is_return) {
        Intent intent = getFragmentIntent(Constants.WORK_ORDER_DETAIL);
        intent.putExtra(Constants.WORK_ORDER_ID, work_order_id);
        intent.putExtra(Constants.IS_RETURN, is_return);
        startActivity(intent);
    }

    //申请退款
    protected void startRequestRefundFragment(String refund_order_id, String goods_id, String state) {
        Intent intent = getFragmentIntent(Constants.REQUEST_REFUND);
        intent.putExtra(Constants.REFUND_ORDER_ID, refund_order_id);
        intent.putExtra(Constants.GOODS_ID, goods_id);
        intent.putExtra(Constants.REFUND_ORDER_STATE, state);
        startActivity(intent);
    }

    //查看物流
    protected void startLogisticsInformationFragment(String order_id, String logistics_no, String logistics_order_no, String logistics_order_state) {
        Intent intent = getFragmentIntent(Constants.LOGISTICS_INFORMATION);
        intent.putExtra(Constants.ORDER_ID_LOGISTICS, order_id);
        intent.putExtra(Constants.ORDER_LOGISTICS_NO, logistics_no);
        intent.putExtra(Constants.LOGISTICS_ORDER_NO, logistics_order_no);
        intent.putExtra(Constants.LOGISTICS_ORDER_State, logistics_order_state);
        startActivity(intent);
    }

    //我的保证金
    protected void startDepositMoneyFragment(String money) {
        Intent intent = getFragmentIntent(Constants.DEPOSIT_MONREY);
        intent.putExtra(Constants.MONEY, money);
        startActivity(intent);
    }

    //商品订单评价列表
    protected void startGoodsCommentsFragment(ArrayList<OrderDetailBean> orderDetailBeanArrayList, String order_id) {
        Intent intent = getFragmentIntent(Constants.GOODS_COMMENTS);
        intent.putExtra(Constants.ORDER_ID, order_id);
        intent.putParcelableArrayListExtra(Constants.GOODS_COMMTENS_BEAN, orderDetailBeanArrayList);
        startActivity(intent);
    }

    //单个商品评价
    protected void startCommentDetailfragment(String orderId, String goodsImg, String goodsId) {
        Intent intent = getFragmentIntent(Constants.COMMENT_DETAIL);
        intent.putExtra(Constants.ORDER_ID, orderId);
        intent.putExtra(Constants.GOODS_IMG, goodsImg);
        intent.putExtra(Constants.GOODS_ID, goodsId);
        startActivity(intent);
    }

    //退货物流
    protected void startRefundLogisticsFragment(String refund_id) {
        Intent intent = getFragmentIntent(Constants.REFUND_LOGISTICS);
        intent.putExtra(Constants.REFUND_ID, refund_id);
        startActivity(intent);
    }

    //设置提现密码
    protected void startPayPwdFragment() {
        Intent intent = getFragmentIntent(Constants.PAY_PWD);

        startActivity(intent);
    }

    //历史工单
    protected void startHistoryOrderFragment() {
        Intent intent = getFragmentIntent(Constants.HISTORY_ORDER);

        startActivity(intent);
    }

    //技能认定
    protected void startSkillIdentifiedFragment(String choose) {
        Intent intent = getFragmentIntent(Constants.SKILL_IDENTIFIED);
        intent.putExtra(Constants.CHOOSE, choose);
        startActivity(intent);
    }

    //技能认定
    protected void startSchoolFragment() {
        Intent intent = getFragmentIntent(Constants.SCHOOL);
        startActivity(intent);
    }
    //单个培训详情
    protected void startTrainingDetailsFragment(String training_id) {
        Intent intent = getFragmentIntent(Constants.TRANING_DETIAILS_FRAGMENT);
        intent.putExtra(Constants.TRAINING_ID, training_id);
        startActivity(intent);
    }

    //提现纪录
    protected void startWithdrawRecordFragment() {
        Intent intent = getFragmentIntent(Constants.WITHDRAW_RECORD);
        startActivity(intent);
    }

    //支付结果
    protected void startPayResultFragment(String state) {
        Intent intent = getFragmentIntent(Constants.PAY_RESULT);
        intent.putExtra(Constants.STATE, state);
        startActivity(intent);
    }



    //待接单详情
    protected void startWaitOrderDetailFragment(String order_id) {
        Intent intent = getFragmentIntent(Constants.WAIT_ORDER_FRAGMENT);
        intent.putExtra(Constants.ORDER_ID, order_id);
        startActivity(intent);
    }



    //--------------------------------


    /**
     * 隐藏软键盘
     *
     * @param v
     */

//    public void hideInputMethod(final EditText v) {
//
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//        v.clearFocus();
//    }

    /**
     * 显示软键盘
     *
     * @param v
     */
    public void showInputMethod(final EditText v) {

        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    public abstract int getRootViewId();

    public abstract void initUI() throws Exception;

    public abstract void initData();


}
