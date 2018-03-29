package com.live.aksd;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/8
 */

public final class Constants {


    public static final String BASE_URL = "http://wx.shifuhelp.com/";



    public static final String KEY_FRAGMENT = "key_fragment";

    public static final String KEY_TITLE = "key_title";

    public static final String KEY_IS_TAB_LIVE = "key_is_tab_live";

    public static final String KEY_UID = "key_uid";
    public static final String USER_ID = "member_id";
    public static final String USER_TOKEN = "member_token";

    public static final String KEY_SLUG = "key_slug";

    public static final String KEY_URL = "key_url";

    public static final String KEY_COVER = "key_cover";

    public static final String USER_BEAN = "userbean";

    public static final String GOODS_ID = "goods_id";

    public static final String WORK_ORDER_BY_DISTANCE = "work_order_by_distance";
    public static final String ORDER_ID = "order_id";
    public static final String REPORTED_ID = "reported_id";
    public static final String REFUND_ID = "refund_id";
    public static final String WORK_ORDER_ID = "work_order_id";
    public static final String REFUND_ORDER_ID = "refund_order_id";
    public static final String REFUND_ORDER_STATE = "state";
    public static final String ORDER_ID_LOGISTICS = "order_id";
    public static final String ORDER_LOGISTICS_NO = "logistics_no";
    public static final String LOGISTICS_ORDER_NO = "logistics_order_no";
    public static final String LOGISTICS_ORDER_State = "logistics_order_state";
    public static final String GOODS_IMG = "goodsImg";
    public static final String IS_RETURN = "is_return";
    public static final String GOODS_COMMTENS_BEAN = "goods_comments_bean";
    public static final String TRAINING_ID = "training_id";
    public static final String CHOOSE = "choose";


    public static final String Latitude = "Latitude";

    public static final String Longitude = "Longitude";


    public static final String ORDER_NO = "order_no";

    public static final String MONEY = "money";

    public static final String ADDRESS_BEAN = "address_bean";
    public static final String REFRESH_ORDER = "refresh_order";

    public static final String STATE = "state";

    public static final String TYPE = "type";

    public static final String IMG = "img";

    public static final String TIME = "time";

    public static final String TEMPLE_ID = "temple_id";

    public static final String TEMPLE_BEAN = "temple_bean";
    public static final String HOTSJ_BEAN = "HotSJListBean";


    public static final String SJ_FL = "SJ_FL";

    public static final String SJ_BT = "SJ_BT";
    public static final String RECORD_BEAN = "RECORD_BEAN";
    public static final String UOIMG_OVER = "upimg_over";
    public static final String NUMBER_REFRESH = "number_refresh";


    public static final String GoodsByStandardBean = "GoodsByStandardBean";

    public static final String WEIXIN_SHARE_ID = "wx0401d443f04d3730";
    public static final String WEIXIN_SHARE_SECRECT = "a47b1ee9b47b6b1d8331cf4eea8f3231";

    public static final String SINA_WEIBO_SHARE_ID = "";
    public static final String SINA_WEIBO_SHARE_SECRECT = "";
    public static final String SINA_WEIBO_SHARE_REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";

    public static final String QQZONE_SHARE_ID = "1106371264";
    public static final String QQZONE_SHARE_SECRECT = "h3Xun8HcnVrPZW7z";

    public static final String SHOPPINGCART_LIST = "shoppingcart_list";



    public static final int WRITE_PERMISSION_REQ_CODE = 2;
    public static final int TOKEN_EXPRIED = 0X98;
    //-----------------------------------------


    public static final int ROOM_FRAGMENT = 0X01;
    public static final int LOGIN_FORGET = 0X02;//忘记密码
    public static final int WEB_FRAGMENT = 0X03;
    public static final int LOGIN_FRAGMENT = 0X04;
    public static final int MY_DATA = 0X05;//完善资料;
    public static final int MY_SH = 0X06;//资料审核;
    public static final int JSWEB_FRAGMENT = 0X7;
    public static final int PERSONAL_FRAGMENT = 0X08;//修改个人
    public static final int LOGIN_SIGNUP = 0X09;//注册
    public static final int LOGIN_UP_PAW = 0X10;//修改密码
    public static final int LIVESTEP = 0X11;//开启直播配置
    public static final int LIVE = 0X12;//开启直播
    public static final int LIVE_STOPLIVE = 0X13;//主播下线，离开直播间
    public static final int PERSONAL_COLLECTION = 0X14;//打开收藏列表

    public static final int PERSONAL_FK = 0X18;//打开反馈
    public static final int HOME_FX = 0X19;//打开法讯
    public static final int HOME_ZL = 0X20;//打开更多转轮
    public static final int HOME_JF = 0X21;//打开更多转轮
    public static final int PERSONAL_NICKNAME = 0X22;//设置昵称
    public static final int HOME_YD = 0X23;//打开阅读
    public static final int HOME_YD_INFO = 0X24;//打开阅读详情页
    public static final int HOME_DT = 0X25;//打开答题
    public static final int HOME_UP_PHONE = 0X26;//修改手机号
    public static final int SYT = 0X28;//打开示意图
    public static final int MD = 0X29;//打开集福名单


    public static final int MESSAGE = 0X32;//打开消息
    public static final int SZ = 0X33;//打开设置
    public static final int MY_ORDER = 0X34;//打开我的订单
    public static final int AFTER_SALE = 0X35;//打开售后
    public static final int MY_WALLET = 0X36;//打开我的钱包
    public static final int MY_SCORES = 0X37;//打开我的积分
    public static final int SERVE_ADDRESS = 0X38;//打开服务地址
    public static final int REWARD_ADDRESS = 0X39;//打开收货地址
    public static final int MY_COLLECTION = 0X40;//打开我的收藏
    public static final int COUPON = 0X41;//打开优惠卷
    public static final int MY_CERTIGFICATE = 0X42;//打开我的证书
    public static final int COST_STANDARDS = 0X43;//打开费用标准
    public static final int MY_REPORTED = 0X44;//打开我的报备
    public static final int SOFTWARE_RELATED = 0X45;//打开软件相关
    public static final int CONTACT_SERVICE = 0X46;//打开联系客服
    public static final int SHARE_SOFRWARE = 0X47;//打开软件分享
    public static final int INFORMATION_REPOTRTED = 0X48;//打开信息报备
    public static final int PERSONAL_INFO = 0X49;//打开个人资料
    public static final int REPOTED_DETAIL = 0X50;//报备详情
    public static final int BIND_ALIPAY = 0X51;//打开绑定支付宝
    public static final int BIND_WECHAT = 0X52;//打开绑定微信
    public static final int BIND_BANK = 0X53;//绑定银行卡
    public static final int AFTER_SALE_DETAIL = 0X54;//售后详情
    public static final int ADD_NEW_ADDRESS = 0X55;//添加地址
    public static final int ORGER_DETIAILS_FRAGMENT = 0X56;//我的订单详情
    public static final int WORK_ORDER_DETAIL = 0X57;//工单详情
    public static final int REQUEST_REFUND = 0X58;//申请退款
    public static final int LOGISTICS_INFORMATION = 0X59;//查看物流
    public static final int DEPOSIT_MONREY = 0X60;//我的保证金
    public static final int GOODS_COMMENTS = 0X61;//评价列表
    public static final int REFUND_LOGISTICS = 0X62;//退货物流
    public static final int COMMENT_DETAIL = 0X63;//单个商品评价
    public static final int  PAY_PWD = 0X64;//设置提现密码
    public static final int  HISTORY_ORDER = 0X65;//历史工单
    public static final int  SKILL_IDENTIFIED = 0X66;//技能认定
    public static final int  SCHOOL = 0X67;//培训学院
    public static final int  TRANING_DETIAILS_FRAGMENT = 0X68;//单个培训详情

    public static final int CHANGE_PWD = 0X69;//打开修改密码
    public static final int SYSTEM_MESSAGE = 0X70;//打开系统消息
    public static final int RORDER_MESSAGE = 0X71;//打开订单消息
    public static final int  WITHDRAW_RECORD = 0X75;//提现纪录
    public static final int  PAY_RESULT = 0X76;//支付结果
    public static final int  WAIT_ORDER_FRAGMENT = 0X77;//待接单详情







    public static final String ADDRESS_STATE = "";//添加地址标识



}
