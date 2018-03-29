package com.live.aksd.http;

import com.live.aksd.bean.AfterSaleDetailBean;
import com.live.aksd.bean.AppStart;
import com.live.aksd.bean.AppVersionDetailBean;
import com.live.aksd.bean.BannerBean;
import com.live.aksd.bean.CouponBean;
import com.live.aksd.bean.GrabSingleBean;
import com.live.aksd.bean.HtmlBean;
import com.live.aksd.bean.IntegralBean;
import com.live.aksd.bean.MyCollectionBean;
import com.live.aksd.bean.OrderBean;
import com.live.aksd.bean.OrderBeanNew;
import com.live.aksd.bean.RefundReasonBean;
import com.live.aksd.bean.ReportedBean;
import com.live.aksd.bean.RewareAddressBean;
import com.live.aksd.bean.ServiceClasssBean;
import com.live.aksd.bean.StarMemberBean;
import com.live.aksd.bean.TracesByJsonBean;
import com.live.aksd.bean.TrainingClassBean;
import com.live.aksd.bean.TrainingDetailBean;
import com.live.aksd.bean.TrainingListBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.UserMessageBean;
import com.live.aksd.bean.WithdrawBean;
import com.live.aksd.bean.WorkOrderBean;
import com.live.aksd.bean.WorkOrderDetailBean;
import com.live.aksd.bean.WorkOrderNumberBean;
import com.live.aksd.bean.WorkTypeBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/13
 */

/**
 * Created by caihan on 2017/1/11.
 *
 * @GET 表明这是get请求
 * @POST 表明这是post请求
 * @PUT 表明这是put请求
 * @DELETE 表明这是delete请求
 * @PATCH 表明这是一个patch请求，该请求是对put请求的补充，用于更新局部资源
 * @HEAD 表明这是一个head请求
 * @OPTIONS 表明这是一个option请求
 * @HTTP 通用注解, 可以替换以上所有的注解，其拥有三个属性：method，path，hasBody
 * @Headers 用于添加固定请求头，可以同时添加多个。通过该注解添加的请求头不会相互覆盖，而是共同存在
 * @Header 作为方法的参数传入，用于添加不固定值的Header，该注解会更新已有的请求头
 * @Body 多用于post请求发送非表单数据, 比如想要以post方式传递json格式数据
 * @Filed 多用于post请求中表单字段, Filed和FieldMap需要FormUrlEncoded结合使用
 * @FiledMap 和@Filed作用一致，用于不确定表单参数
 * @Part 用于表单字段, Part和PartMap与Multipart注解结合使用, 适合文件上传的情况
 * @PartMap 用于表单字段, 默认接受的类型是Map<String,REquestBody>，可用于实现多文件上传
 * <p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * </p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * @Path 用于url中的占位符,{占位符}和PATH只用在URL的path部分，url中的参数使用Query和QueryMap代替，保证接口定义的简洁
 * @Query 用于Get中指定参数
 * @QueryMap 和Query使用类似
 * @Url 指定请求路径
 */
public interface APIService {

    /**
     * 获取App启动页信息
     *
     * @return
     */
    @GET("json/page/app-data/info.json?v=3.0.1&os=1&ver=4")
    Observable<AppStart> getAppStartInfo();

    /**
     * 获取首页轮播
     *
     * @return
     */
    @FormUrlEncoded
    @POST("settingInterfaces.api?getBannerList")
    Observable<HttpResult<List<BannerBean>>> getBanner(@FieldMap Map<String, String> parmer);

    /**
     * 获取首页师傅列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getStarMemberList")
    Observable<HttpResult<List<StarMemberBean>>> getStarMember(@FieldMap Map<String, String> parmer);


    /**
     * 获取首页当前可抢单
     *
     * @return
     */
    @FormUrlEncoded
    @POST("workOrderInterfaces.api?getWorkOrderByDistance")
    Observable<HttpResult<GrabSingleBean>> getWorkOrderByDistance(@FieldMap Map<String, String> parmer);


    /**
     * 发送验证码
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("settingInterfaces.api?sendCode")
    Observable<HttpResult<String>> sendCode(@FieldMap Map<String, String> parmer);

    /**
     * 用户注册
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?memberRegister")
    Observable<HttpResult<UserBean>> getZhuCe(@FieldMap Map<String, String> parmer);


    /**
     * 忘记密码
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?memberForgetPassword")
    Observable<HttpResult<String>> getwangjiminma(@FieldMap Map<String, String> parmer);

    /**
     * 修改密码
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?memberUpdatePassword")
    Observable<HttpResult<String>> getUpPwd(@FieldMap Map<String, String> parmer);

    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?memberLogin")
    Observable<HttpResult<UserBean>> getLogin(@FieldMap Map<String, String> parmer);

    /**
     * 上传头像
     *
     * @return
     */
    @POST("memberInterfaces.api?uploadMemberImg")
    Observable<HttpResult<String>> upLoad(@Body RequestBody Body);


    /**
     * 获取个人数据
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getMemberDetail")
    Observable<HttpResult<UserBean>> getUserCenter(@FieldMap Map<String, String> parmer);

    /**
     * 获取支付信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?payDeposit")
    Observable<HttpResult<String>> postPay(@FieldMap Map<String, String> map);

    /**
     * 付款订单（真实支付）
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?payRealOrderList")
    Observable<HttpResult<String>> payRealOrders(@FieldMap Map<String, String> map);

    /**
     * 修改用户基本信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?updateMemberDetail")
    Observable<HttpResult<String>> updateMemberDetail(@FieldMap Map<String, String> map);

    /**
     * 提交审核
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?submitAudit")
    Observable<HttpResult<String>> submitAudit(@FieldMap Map<String, String> map);

    /**
     * 获取收货地址列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("addressInterfaces.api?getMemberAddressList")
    Observable<HttpResult<List<RewareAddressBean>>> getOwnerAddress(@FieldMap Map<String, String> map);

    /**
     * 删除地址
     *
     * @return
     */
    @FormUrlEncoded
    @POST("addressInterfaces.api?deleteAddress")
    Observable<HttpResult<String>> deleteAddress(@FieldMap Map<String, String> map);

    /**
     * 增加／修改地址
     *
     * @return
     */
    @FormUrlEncoded
    @POST("addressInterfaces.api?insertUpdateAddress")
    Observable<HttpResult<String>> insertAddress(@FieldMap Map<String, String> map);

    /**
     * 设置默认地址
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("addressInterfaces.api?setDefaultAddress")
    Observable<HttpResult<String>> setDefaultAddress(@FieldMap Map<String, String> map);


    /**
     * 获得收藏列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("collectionInterfaces.api?getCollectionList")
    Observable<HttpResult<List<MyCollectionBean>>> getCollection(@FieldMap Map<String, String> map);

    /**
     * 批量取消收藏
     *
     * @return
     */
    @FormUrlEncoded
    @POST("collectionInterfaces.api?cancelAllCollection")
    Observable<HttpResult<String>> cancelAllCollection(@FieldMap Map<String, String> map);


    /**
     * 用户消息列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getMemberMsgList")
    Observable<HttpResult<List<UserMessageBean>>> getMemberMsgs(@FieldMap Map<String, String> map);


    /**
     * 获得订单列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?getOrderList")
    Observable<HttpResult<List<OrderBeanNew>>> getOrders(@FieldMap Map<String, String> map);

    /**
     * 用户签到
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("signInterfaces.api?insertSign")
    Observable<HttpResult<String>> insertSign(@FieldMap Map<String, String> map);

    /**
     * 退单---用户退单列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?getRefundOrderList")
    Observable<HttpResult<List<AfterSaleDetailBean>>> getMemberRefunds(@FieldMap Map<String, String> map);

    /**
     * 退单---单个订单详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?getRefundDetail")
    Observable<HttpResult<AfterSaleDetailBean>> getRefundDetail(@FieldMap Map<String, String> map);


    /**
     * 用户优惠券列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("couponInterfaces.api?getCouponList")
    Observable<HttpResult<List<CouponBean>>> getCoupons(@FieldMap Map<String, String> map);

    /**
     * 师傅报备
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?insertReported")
    Observable<HttpResult<String>> addReported(@FieldMap Map<String, String> map);


    /**
     * 师傅报备信息列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getReportedList")
    Observable<HttpResult<List<ReportedBean>>> getReportedList(@FieldMap Map<String, String> map);


    /**
     * 单个订单详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?getOrderDetail")
    Observable<HttpResult<OrderBean>> getOneOrderDetail(@FieldMap Map<String, String> map);


    /**
     * 上传单张图片/文件
     *
     * @return
     */
    @POST("settingInterfaces.api?uploadImg")
    Observable<HttpResult<String>> uploadImg(@Body RequestBody Body);


    /**
     * 上传多张图片/文件
     *
     * @return
     */


    @Multipart
    @POST("settingInterfaces.api?uploadImgs")
    Observable<HttpResult<String[]>> uploadImgs(@Part List<MultipartBody.Part> cover);


    /**
     * 上传发布信息图片
     */
    @Multipart
    @POST("workOrderInterfaces.api?uploadWorkOrderImg")
    Observable<HttpResult<String[]>> uploadWorkOrderImg(@Part List<MultipartBody.Part> cover);


    /**
     * 上传评价图片
     */
    @Multipart
    @POST("orderInterfaces.api?uploadAssessmentImg")
    Observable<HttpResult<String[]>> uploadAssessmentImg(@Part List<MultipartBody.Part> cover);


    /**
     * 报备信息详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getReportedDetail")
    Observable<HttpResult<ReportedBean>> getReported(@FieldMap Map<String, String> map);


    /**
     * 获取工单列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("workOrderInterfaces.api?getOrderListByState")
    Observable<HttpResult<List<WorkOrderBean>>> getOrderListByState(@FieldMap Map<String, String> map);

    /**
     * 服务工单详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("workOrderInterfaces.api?getWorkOrderDetail")
    Observable<HttpResult<WorkOrderDetailBean>> getWorkOrderDetail(@FieldMap Map<String, String> map);


    /**
     * 服务工单退单详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("workOrderInterfaces.api?getRefundWorkOrderDetail")
    Observable<HttpResult<WorkOrderDetailBean>> getRefundWorkOrderDetail(@FieldMap Map<String, String> map);


    /**
     * 报备信息修改
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?updateReported")
    Observable<HttpResult<String>> updateReported(@FieldMap Map<String, String> map);

    /**
     * 第三方账号绑定
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?bindOtherNo")
    Observable<HttpResult<String>> bindOtherNo(@FieldMap Map<String, String> map);

    /**
     * 申请提现
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?applyWithdrawal")
    Observable<HttpResult<String>> applyCash(@FieldMap Map<String, String> map);

    /**
     * 申请退单（工单）
     *
     * @return
     */
    @FormUrlEncoded
    @POST("workOrderInterfaces.api?cancleOrder")
    Observable<HttpResult<String>> cancleOrder(@FieldMap Map<String, String> map);


    /**
     * 获去服务分类列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("workOrderInterfaces.api?getServiceClassList")
    Observable<HttpResult<List<ServiceClasssBean>>> getServiceClasss(@FieldMap Map<String, String> map);

    /**
     * 积分获取记录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getIntegralGetRecord")
    Observable<HttpResult<List<IntegralBean>>> getIntegralGetRecord(@FieldMap Map<String, String> map);


    /**
     * 工单状态更改
     *
     * @return
     */
    @FormUrlEncoded
    @POST("workOrderInterfaces.api?updateOrderState")
    Observable<HttpResult<String>> updateOrderState(@FieldMap Map<String, String> map);


    /**
     * 取消订单
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?cancelOrder")
    Observable<HttpResult<String>> cancelOrder(@FieldMap Map<String, String> map);


    /**
     * 退单---订单退款
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?refundOrderNoFile")
    Observable<HttpResult<String>> refundOrder(@FieldMap Map<String, String> map);

    /**
     * 订单物流列表
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?getOrderLogisticsDetails")
    Observable<HttpResult<TracesByJsonBean>> getOrderLogisticsDetails(@FieldMap Map<String, String> map);

    /**
     * 退单---退单原因列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?getRefundsReasons")
    Observable<HttpResult<List<RefundReasonBean>>> getRefundsReasons(@FieldMap Map<String, String> map);

    /**
     * 确认收货
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?confirmOrder")
    Observable<HttpResult<String>> confirmOrder(@FieldMap Map<String, String> map);


    /**
     * 订单评论
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?assessmentOrder")
    Observable<HttpResult<String>> assessmentOrder(@Field("member_id") String member_id, @Field("member_token") String member_token, @Field("json") String content);


    /**
     * 提交退货物流信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?updateRefundOrderLogistics")
    Observable<HttpResult<String>> updateRefundOrderLogistics(@FieldMap Map<String, String> map);

    /**
     * 取消退单（工单）
     *
     * @return
     */
    @FormUrlEncoded
    @POST("workOrderInterfaces.api?cancleCancleOrder")
    Observable<HttpResult<String>> cancleCancleOrder(@FieldMap Map<String, String> map);


    /**
     * 取消退单（售后订单）
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?cancleRefundOrder")
    Observable<HttpResult<String>> cancleRefundOrder(@FieldMap Map<String, String> map);

    /**
     * 用户积分
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getUserIntegral")
    Observable<HttpResult<String>> getUserIntegral(@FieldMap Map<String, String> map);


    /**
     * 用户是否存在
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getMemberByMobile")
    Observable<HttpResult<UserBean>> getMemberByMobile(@FieldMap Map<String, String> map);


    /**
     * 设置支付密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?setPayPassword")
    Observable<HttpResult<String>> setPayPassword(@FieldMap Map<String, String> map);

    /**
     * 删除用户报备信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?deleteReported")
    Observable<HttpResult<String>> deleteReported(@FieldMap Map<String, String> map);

    /**
     * 培训类别列表
     *
     * @return
     */
    @POST("trainingInterfaces.api?getTrainingClassList")
    Observable<HttpResult<List<TrainingClassBean>>> getTrainingClassList();

    /**
     * 分类下培训列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("trainingInterfaces.api?getTrainingList")
    Observable<HttpResult<List<TrainingListBean>>> getTrainingList(@FieldMap Map<String, String> map);

    /**
     * 单个培训详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("trainingInterfaces.api?getTraining")
    Observable<HttpResult<TrainingDetailBean>> getTraining(@FieldMap Map<String, String> map);

    /**
     * 申请体现列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getWithdrawalList")
    Observable<HttpResult<List<WithdrawBean>>> getApplyCashs(@FieldMap Map<String, String> map);

    /**
     * 工单各个状态统计
     *
     * @return
     */
    @FormUrlEncoded
    @POST("workOrderInterfaces.api?getWorkOrderStateCount")
    Observable<HttpResult<WorkOrderNumberBean>> getWorkOrderStateCount(@FieldMap Map<String, String> map);


    /**
     * 工种列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getWorkTypeList")
    Observable<HttpResult<List<WorkTypeBean>>> getWorkTypeList(@FieldMap Map<String, String> map);


    /**
     * 图文详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("settingInterfaces.api?getHtmlDetail")
    Observable<HttpResult<HtmlBean>> getHtmlDetail(@FieldMap Map<String, String> map);


    /**
     * 修改工单信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("workOrderInterfaces.api?updateWorkOrder")
    Observable<HttpResult<String>> updateWorkOrder(@FieldMap Map<String, String> map);


    /**
     * 图文详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("settingInterfaces.api?getAppVersionDetail")
    Observable<HttpResult<AppVersionDetailBean>> getAppVersionDetail(@FieldMap Map<String, String> map);


}
