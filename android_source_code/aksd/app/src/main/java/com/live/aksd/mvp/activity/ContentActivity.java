package com.live.aksd.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.king.base.util.LogUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.OrderDetailBean;
import com.live.aksd.mvp.fragment.ForgetPwdFragment;
import com.live.aksd.mvp.fragment.Home.ReportedFragment;
import com.live.aksd.mvp.fragment.JsSearchFragment;
import com.live.aksd.mvp.fragment.JsWebFragment;
import com.live.aksd.mvp.fragment.LoginFragment;
import com.live.aksd.mvp.fragment.Mine.AfterSaleDetailFragment;
import com.live.aksd.mvp.fragment.Mine.AfterSaleFragment;
import com.live.aksd.mvp.fragment.Mine.BindAlipayFragment;
import com.live.aksd.mvp.fragment.Mine.BindBankFragment;
import com.live.aksd.mvp.fragment.Mine.BindWechatFragment;
import com.live.aksd.mvp.fragment.Mine.ChangePwdFragment;
import com.live.aksd.mvp.fragment.Mine.CouponFragment;
import com.live.aksd.mvp.fragment.Mine.DepositMoneyFragment;
import com.live.aksd.mvp.fragment.Mine.GoodsCommentsFragment;
import com.live.aksd.mvp.fragment.Mine.HistoryOrderFragment;
import com.live.aksd.mvp.fragment.Mine.LogisticsInformationFragment;
import com.live.aksd.mvp.fragment.Mine.MessageFragment;
import com.live.aksd.mvp.fragment.Mine.MyCollectionFragment;
import com.live.aksd.mvp.fragment.Mine.MyOrderDetailFragment;
import com.live.aksd.mvp.fragment.Mine.MyOrderFragment;
import com.live.aksd.mvp.fragment.Mine.MyReportedFragment;
import com.live.aksd.mvp.fragment.Mine.MyScoresFragment;
import com.live.aksd.mvp.fragment.Mine.MyWalletFragment;
import com.live.aksd.mvp.fragment.Mine.OrderMessageFragment;
import com.live.aksd.mvp.fragment.Mine.PayPwdFragment;
import com.live.aksd.mvp.fragment.Mine.PersonalInfoFragment;
import com.live.aksd.mvp.fragment.Mine.ReceiptAddressFragment;
import com.live.aksd.mvp.fragment.Mine.RefundLogisticsFragment;
import com.live.aksd.mvp.fragment.Mine.ReportedDetailFragment;
import com.live.aksd.mvp.fragment.Mine.RequestRefundFragment;
import com.live.aksd.mvp.fragment.Mine.SchoolFragment;
import com.live.aksd.mvp.fragment.Mine.ServeAddressFragment;
import com.live.aksd.mvp.fragment.Mine.SettingFragment;
import com.live.aksd.mvp.fragment.Mine.SkillIdentifiedFragment;
import com.live.aksd.mvp.fragment.Mine.SoftwareRelatedFragment;
import com.live.aksd.mvp.fragment.Mine.SystemMessageFragment;
import com.live.aksd.mvp.fragment.Mine.TrainingDetailFragment;
import com.live.aksd.mvp.fragment.Mine.WithdrawRecordFragment;
import com.live.aksd.mvp.fragment.MyDataFragment;
import com.live.aksd.mvp.fragment.PayResultFragment;
import com.live.aksd.mvp.fragment.RegisteredFragment;
import com.live.aksd.mvp.fragment.SHFragment;
import com.live.aksd.mvp.fragment.WorkOrder.WaitOrderDetailFragment;
import com.live.aksd.mvp.fragment.WorkOrder.WorkOrderDetailFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/20
 */

public class ContentActivity extends AppCompatActivity {

    int fragmentKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        swichFragment(getIntent());
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventExit(Boolean isBool) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("1")) {

        } else if (event.getMsg().equals("2")) {

        } else if (event.getMsg().equals("3")) {
            finish();

        } else if (event.getMsg().equals("4")) {
            finish();
        } else if (event.getMsg().equals("5")) {
            finish();
        } else if (event.getMsg().equals(fragmentKey + "")) {
            finish();
        }


    }


    public void swichFragment(Intent intent) {

        fragmentKey = intent.getIntExtra(Constants.KEY_FRAGMENT, 0);
        switch (fragmentKey) {
            case Constants.MY_DATA:
                //我的资料
                replaceFragment(MyDataFragment.newIntance());
                break;
            case Constants.LOGIN_SIGNUP:
                //注册
                replaceFragment(RegisteredFragment.newInstance());
                break;
            case Constants.LOGIN_FORGET:
                //忘记密码
                replaceFragment(ForgetPwdFragment.newInstance());
                break;
            case Constants.MY_SH:
                //资料审核
                replaceFragment(SHFragment.newIntance());
                break;


            case Constants.LOGIN_FRAGMENT:
                replaceFragment(LoginFragment.newInstance());
                break;
            case Constants.SZ:
                //设置
                replaceFragment(SettingFragment.newIntance());
                break;
            case Constants.MESSAGE:

                replaceFragment(MessageFragment.newIntance());
                break;
            case Constants.MY_ORDER:
                //我的订单
                String state_order = intent.getStringExtra(Constants.STATE);
                replaceFragment(MyOrderFragment.newIntance(state_order));

                break;
            case Constants.AFTER_SALE:
                //售后
                replaceFragment(AfterSaleFragment.newIntance());

                break;
            case Constants.MY_WALLET:
                //我的钱包
                replaceFragment(MyWalletFragment.newIntance());

                break;
            case Constants.MY_SCORES:
                //我的积分
                replaceFragment(MyScoresFragment.newIntance());

                break;
            case Constants.SERVE_ADDRESS:
                //服务地址
                replaceFragment(ServeAddressFragment.newIntance());

                break;
            case Constants.REWARD_ADDRESS:
                //收货地址
                replaceFragment(ReceiptAddressFragment.newIntance());

                break;
            case Constants.MY_COLLECTION:
                //我的收藏
                replaceFragment(MyCollectionFragment.newIntance());

                break;
            case Constants.COUPON:
                //优惠卷
                replaceFragment(CouponFragment.newIntance("0"));

                break;

            case Constants.MY_REPORTED:
                //我的报备
                replaceFragment(MyReportedFragment.newIntance());

                break;
            case Constants.SOFTWARE_RELATED:
                //软件相关
                replaceFragment(SoftwareRelatedFragment.newIntance());

                break;

            case Constants.INFORMATION_REPOTRTED:
                //信息报备
                replaceFragment(ReportedFragment.newIntance());
                break;
            case Constants.PERSONAL_INFO:
                //个人资料
                replaceFragment(PersonalInfoFragment.newIntance());
                break;
            case Constants.CHANGE_PWD:
                //修改密码
                replaceFragment(ChangePwdFragment.newIntance());
                break;
            case Constants.SYSTEM_MESSAGE:
                //个人资料
                replaceFragment(SystemMessageFragment.newIntance());
                break;
            case Constants.RORDER_MESSAGE:

                replaceFragment(OrderMessageFragment.newIntance());
                break;

            case Constants.REPOTED_DETAIL:
                //报备详情
                String reported_id = intent.getStringExtra(Constants.REPORTED_ID);
                replaceFragment(ReportedDetailFragment.newIntance(reported_id));
                break;
            case Constants.BIND_ALIPAY:
                //绑定支付宝
                replaceFragment(BindAlipayFragment.newIntance());
                break;
            case Constants.BIND_WECHAT:
                //绑定微信
                replaceFragment(BindWechatFragment.newIntance());
                break;

            case Constants.BIND_BANK:
                //绑定银行卡
                replaceFragment(BindBankFragment.newIntance());
                break;

            case Constants.AFTER_SALE_DETAIL:
                //售后详情
                String refund_id = intent.getStringExtra(Constants.REFUND_ID);
                replaceFragment(AfterSaleDetailFragment.newIntance(refund_id));
                break;
            case Constants.ORGER_DETIAILS_FRAGMENT:
                //订单详情
                String order_id = intent.getStringExtra(Constants.ORDER_ID);
                replaceFragment(MyOrderDetailFragment.newIntance(order_id));

                break;
            case Constants.DEPOSIT_MONREY:
                //我的保证金
                String money = intent.getStringExtra(Constants.MONEY);
                replaceFragment(DepositMoneyFragment.newIntance(money));

                break;

            case Constants.WORK_ORDER_DETAIL:
                //工单详情
                String is_return = intent.getStringExtra(Constants.IS_RETURN);
                String work_order_id = intent.getStringExtra(Constants.WORK_ORDER_ID);
                replaceFragment(WorkOrderDetailFragment.newIntance(work_order_id, is_return));

                break;
            /*case Constants.ADD_NEW_ADDRESS:
                //添加地址
                String state = getIntent().getStringExtra(Constants.ADDRESS_STATE);
                UserBean usebean = (UserBean) getIntent().getSerializableExtra(Constants.USER_BEAN);
                replaceFragment(AddNewAddressFragment.newInstance(usebean,state));
                break;*/

            case Constants.REQUEST_REFUND:
                //申请退款
                String goods_id = intent.getStringExtra(Constants.GOODS_ID);
                String refund_order_id = intent.getStringExtra(Constants.REFUND_ORDER_ID);
                String state = intent.getStringExtra(Constants.REFUND_ORDER_STATE);
                replaceFragment(RequestRefundFragment.newIntance(refund_order_id, goods_id, state));
                break;
            case Constants.LOGISTICS_INFORMATION:
                //查看物流
                String id = intent.getStringExtra(Constants.ORDER_ID_LOGISTICS);
                String logistics_no = intent.getStringExtra(Constants.ORDER_LOGISTICS_NO);
                String logistics_order_no = intent.getStringExtra(Constants.LOGISTICS_ORDER_NO);
                String logistics_order_state = intent.getStringExtra(Constants.LOGISTICS_ORDER_State);
                replaceFragment(LogisticsInformationFragment.newIntance(id, logistics_no, logistics_order_no, logistics_order_state));
                break;


            case Constants.WEB_FRAGMENT:
                String wtitle = intent.getStringExtra(Constants.KEY_TITLE);
                String url = intent.getStringExtra(Constants.KEY_URL);
                String is_type = intent.getStringExtra(Constants.KEY_SLUG);
                String img = intent.getStringExtra(Constants.IMG);
                //replaceFragment(WebFragment.newInstance(url, title, ""));
                replaceFragment(JsWebFragment.newInstance(url, wtitle, is_type, img));
                break;

            case Constants.GOODS_COMMENTS:
                //商品评价列表
                String order_id1 = intent.getStringExtra(Constants.ORDER_ID);
                ArrayList<OrderDetailBean> orderDetailBeen = intent.getParcelableArrayListExtra(Constants.GOODS_COMMTENS_BEAN);
                replaceFragment(GoodsCommentsFragment.newInstance(orderDetailBeen, order_id1));
                break;
            case Constants.REFUND_LOGISTICS:
                //申请退货物流填写
                String refund_ido = intent.getStringExtra(Constants.REFUND_ID);
                replaceFragment(RefundLogisticsFragment.newInstance(refund_ido));
                break;
          /*  case Constants.COMMENT_DETAIL:
                //赏评评价详情界面
                String orderID= intent.getStringExtra(Constants.ORDER_ID);
                String goodsImg= intent.getStringExtra(Constants.GOODS_IMG);
                String goodsID= intent.getStringExtra(Constants.GOODS_ID);
                replaceFragment(CommentDetailfragment.newInstance(orderID,goodsImg,goodsID));
                break;*/

            case Constants.JSWEB_FRAGMENT:
                String js_url = intent.getStringExtra(Constants.KEY_URL);
                String js_type = intent.getStringExtra(Constants.KEY_SLUG);
                replaceFragment(JsSearchFragment.newInstance(js_url, js_type));
                break;

            case Constants.PAY_PWD:
                replaceFragment(PayPwdFragment.newInstance());
                break;
            case Constants.HISTORY_ORDER:
                //历史工单
                replaceFragment(HistoryOrderFragment.newInstance());
                break;
            case Constants.SKILL_IDENTIFIED:
                //技能认定
                String choose = intent.getStringExtra(Constants.CHOOSE);
                replaceFragment(SkillIdentifiedFragment.newIntance(choose));
                break;
            case Constants.SCHOOL:
                //培训学院
                replaceFragment(SchoolFragment.newIntance());
                break;

            case Constants.TRANING_DETIAILS_FRAGMENT:
                //单个培训详情
                String training_id = intent.getStringExtra(Constants.TRAINING_ID);
                replaceFragment(TrainingDetailFragment.newIntance(training_id));

                break;

            case Constants.WITHDRAW_RECORD:
                //提现记录
                replaceFragment(WithdrawRecordFragment.newIntance());

                break;

            case Constants.PAY_RESULT:
                //支付结果
                String state_id = intent.getStringExtra(Constants.STATE);
                replaceFragment(PayResultFragment.newIntance(state_id));

                break;

            case Constants.WAIT_ORDER_FRAGMENT:
                ////待接单详情
                String wait_order_id = intent.getStringExtra(Constants.ORDER_ID);
                replaceFragment(WaitOrderDetailFragment.newInstance(wait_order_id));

                break;



            default:
                LogUtils.d("Not found fragment:" + Integer.toHexString(fragmentKey));
                break;
        }
    }


    public void replaceFragment(Fragment fragmnet) {
        replaceFragment(R.id.fragmentContent, fragmnet);
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

}
