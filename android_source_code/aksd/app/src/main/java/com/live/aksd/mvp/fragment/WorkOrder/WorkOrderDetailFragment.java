package com.live.aksd.mvp.fragment.WorkOrder;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;
import com.live.aksd.Constants;
import com.live.aksd.R;
import com.live.aksd.bean.FirstEvent;
import com.live.aksd.bean.ServiceClasssBean;
import com.live.aksd.bean.UserBean;
import com.live.aksd.bean.WorkOrderDetailBean;
import com.live.aksd.mvp.activity.LocationDemo;
import com.live.aksd.mvp.adapter.Home.ImageAdapter;
import com.live.aksd.mvp.adapter.Home.UserImageAdapter;
import com.live.aksd.mvp.adapter.WorkOrder.SelectServiceAdapter;
import com.live.aksd.mvp.base.BaseFragment;
import com.live.aksd.mvp.fragment.image.ShowPictureActivity;
import com.live.aksd.mvp.presenter.WordOrder.WorkOrderDetailPersenter;
import com.live.aksd.mvp.view.WordOrder.IWorkOrderDetailView;
import com.live.aksd.util.CustomDialog;
import com.live.aksd.util.LoadingUtil;
import com.live.aksd.util.SpSingleInstance;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MultipartBody;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * @author Created by stone
 * @since 17/9/1
 */

public class WorkOrderDetailFragment extends BaseFragment<IWorkOrderDetailView, WorkOrderDetailPersenter> implements IWorkOrderDetailView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.orderState)
    TextView orderState;
    @BindView(R.id.tvChange)
    TextView tvChange;

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvMethod)
    TextView tvMethod;
    @BindView(R.id.tvArea)
    TextView tvArea;
    @BindView(R.id.tvRequest)
    TextView tvRequest;
    @BindView(R.id.tvNote)
    TextView tvNote;
    @BindView(R.id.releaseTime)
    TextView releaseTime;
    @BindView(R.id.hopeStartTime)
    TextView hopeStartTime;
    @BindView(R.id.hopeEndTime)
    TextView hopeEndTime;
    @BindView(R.id.tvAcceptTime)
    TextView tvAcceptTime;
    @BindView(R.id.tvComeTime)
    TextView tvComeTime;
    @BindView(R.id.tvSubmitTime)
    TextView tvSubmitTime;
    @BindView(R.id.tvPassTime)
    TextView tvPassTime;
    @BindView(R.id.tvMap)
    TextView tvMap;

    @BindView(R.id.tvActualService)
    TextView tvActualService;
    @BindView(R.id.tvOtherService)
    TextView tvOtherService;
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_check)
    TextView tvCheck;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    Unbinder unbinder;

    @BindView(R.id.tvRefundTittle)
    TextView tvRefundTittle;
    @BindView(R.id.tvRefundContent)
    TextView tvRefundContent;
    @BindView(R.id.tvActualPrcie)
    TextView tvActualPrcie;
    @BindView(R.id.tvOtherPrcie)
    TextView tvOtherPrcie;
    @BindView(R.id.tvToatalPrcie)
    TextView tvToatalPrcie;


    @BindView(R.id.parentView)
    LinearLayout parentView;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.ivRight2)
    ImageView ivRight2;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llCompleteImg)
    LinearLayout llCompleteImg;
    @BindView(R.id.llRefund)
    LinearLayout llRefund;
    @BindView(R.id.userRecyclerView)
    RecyclerView userRecyclerView;
    @BindView(R.id.llUserImg)
    LinearLayout llUserImg;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.tvAssessTittle)
    TextView tvAssessTittle;
    @BindView(R.id.tvAssessContent)
    TextView tvAssessContent;
    @BindView(R.id.llAssess)
    LinearLayout llAssess;


    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> serverMap = new HashMap<>();
    private List<ServiceClasssBean> list;
    private String order_reality_content;  //已完工（实际服务分类）
    private String order_id;
    private String is_return;
    private CompletedSubmissionFragment completeFragment;

    private WorkOrderDetailBean workOrderDetailBean;

    //电话
    private String customerNumber;
    private String userNumber;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private String price;

    private SelectServiceAdapter adapter;
    private SelectServiceFragment fragmentt;
    private String[] imgArray = {};

    private ImageAdapter imgAdapter;
    List<String> imgList;

    private UserImageAdapter userImgAdapter;
    List<String> userImgList;


    public static WorkOrderDetailFragment newIntance(String order_id, String is_return) {
        Bundle args = new Bundle();
        WorkOrderDetailFragment fragment = new WorkOrderDetailFragment();
        fragment.order_id = order_id;
        fragment.is_return = is_return;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_workorder_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.order_detail);
        list = new ArrayList<>();
        //师傅完工图片
        imgList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        imgAdapter = new ImageAdapter(context, imgList);
        recyclerView.setAdapter(imgAdapter);
        imgAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ShowPictureActivity.class);
                intent.putExtra("imagelist", (Serializable) imgAdapter.getAllData());
                intent.putExtra("position", position);
                getContext().startActivity(intent);
            }
        });
        //用户预约图片
        userImgList = new ArrayList<>();
        userRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        userImgAdapter = new UserImageAdapter(context, userImgList);
        userRecyclerView.setAdapter(userImgAdapter);
        userImgAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ShowPictureActivity.class);
                intent.putExtra("imagelist", (Serializable) userImgAdapter.getAllData());
                intent.putExtra("position", position);
                getContext().startActivity(intent);
            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();

    }

    @Override
    public void onResume() {
        super.onResume();
        map.clear();
        map.put("member_id", userBean.getMember_id());
        map.put("member_token", userBean.getMember_token());
        map.put("order_id", order_id);
        switch (is_return) {
            case "1":
                getPresenter().getRefundWorkOrderDetail(map);
                break;
            case "0":
                getPresenter().getWorkOrderDetail(map);
                break;
        }
    }


    public void init(WorkOrderDetailBean data) {


        userNumber = data.getOrder_phone();
        orderState.setText(data.getOrder_state_show());
        tvName.setText(getString(R.string.detail_name) + data.getOrder_name());
        tvPhone.setText(getString(R.string.detail_phone) + data.getOrder_phone());
        tvAddress.setText(getString(R.string.detail_address) + data.getOrder_address_province() + " " + data.getOrder_address_city() + " " + data.getOrder_address_district() + " " + data.getOrder_address_detail());
        tvContent.setText(getString(R.string.detail_content) + data.getOrder_subscribe_content() + getString(R.string.yuan_area));
        releaseTime.setText(getString(R.string.detail_release_time) + data.getOrder_create_time());
        hopeStartTime.setText(getString(R.string.hope_start_time) + data.getOrder_hope_service_time());
        tvAcceptTime.setText(getString(R.string.detail_accept_time) + data.getOrder_accept_time());
        //Order_type 0水电安装 1水电维修
        if ("0".equals(data.getOrder_type())) {
            hopeEndTime.setText(getString(R.string.hope_end_time) + data.getHope_complete_time());
            tvArea.setText(getString(R.string.detail_are) + data.getWork_area() + getString(R.string.square_meter));
            tvMethod.setText(getString(R.string.detail_method) + data.getWork_way());
            if (!TextUtils.isEmpty(data.getWork_requirements())) {
                tvRequest.setText(getString(R.string.detail_request) + data.getWork_requirements());
            }
        } else {
            hopeEndTime.setVisibility(View.GONE);
            tvArea.setVisibility(View.GONE);
            tvMethod.setVisibility(View.GONE);
            tvRequest.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(data.getOrder_subscribe_note())) {
            tvNote.setText(getString(R.string.detail_note) + data.getOrder_subscribe_note());
        }

        if (!TextUtils.isEmpty(data.getOrder_service_time())) {
            tvComeTime.setText(getString(R.string.come_time) + data.getOrder_service_time());
        }

        if (!TextUtils.isEmpty(data.getOrder_complete_time())) {
            tvSubmitTime.setText(getString(R.string.commit_time) + data.getOrder_complete_time());
        }

        if (!TextUtils.isEmpty(data.getOrder_complete_pass_time())) {
            tvPassTime.setText(getString(R.string.shenhe_time) + data.getOrder_complete_pass_time());
        }

        if (data.getOrderSubscribeImgBeans().size() >= 1) {
            llUserImg.setVisibility(View.VISIBLE);
            userImgAdapter.clear();
            userImgAdapter.addAll(data.getOrderSubscribeImgBeans());
            userImgAdapter.notifyDataSetChanged();
        }

        switch (data.getOrder_state()) {
            case "3"://已接单_未服务
                tvComeTime.setVisibility(View.GONE);
                orderState.setBackgroundColor(getResources().getColor(R.color.lanse));
                tvOne.setText(R.string.go_server);
                tvThree.setText(R.string.lianxi_user);
                break;
            case "5"://服务中
                orderState.setBackgroundColor(getResources().getColor(R.color.lanse));
                tvOne.setText(R.string.completed);
                tvThree.setText(R.string.lianxi_user);
				//sfsm  zhoushilei: [修改内容] remove code @{
               /* if ("0".equals(data.getOrder_type())) {
                    tvChange.setVisibility(View.VISIBLE);
                }*/
				// @}
                break;
            case "4"://未服务退单待审核
            case "6"://服务中退单待审核
                orderState.setBackgroundColor(getResources().getColor(R.color.bg_4));
                tvOne.setVisibility(View.GONE);
                tvTwo.setText(R.string.cancel_order);
                tvCheck.setVisibility(View.VISIBLE);
                llRefund.setVisibility(View.VISIBLE);
                tvRefundContent.setText(data.getOrder_cancle_why());
                break;

            case "7"://完工未评价
                //sfsm zhoushilei: [工单详情增加用户评价显示] add code @{
                orderState.setBackgroundColor(getResources().getColor(R.color.bg_8));
                tvOne.setVisibility(View.GONE);
                tvTwo.setVisibility(View.GONE);

                tvSubmitTime.setVisibility(View.VISIBLE);
                tvActualService.setVisibility(View.VISIBLE);
                tvOtherService.setVisibility(View.VISIBLE);
                tvActualPrcie.setVisibility(View.VISIBLE);
                tvOtherPrcie.setVisibility(View.VISIBLE);
                tvToatalPrcie.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(data.getOthers_service_content())) {
                    tvOtherService.setText(getString(R.string.other_contente) + data.getOthers_service_content());
                }
                if (!TextUtils.isEmpty(data.getOthers_price())) {
                    tvOtherPrcie.setText(getString(R.string.other_pricer) + data.getOthers_price() + getString(R.string.yuan));
                }
                tvActualService.setText(getString(R.string.actual_content) + data.getOrder_subscribe_content() + getString(R.string.yuan_area));
                tvActualPrcie.setText(getString(R.string.actual_price) + data.getOrder_price() + getString(R.string.yuan));
                tvToatalPrcie.setText(getString(R.string.total_price) + data.getOrder_final_price() + getString(R.string.yuan));

                llCompleteImg.setVisibility(View.VISIBLE);
                imgAdapter.clear();
                imgAdapter.addAll(data.getOrderCompleteImgBeans());
                imgAdapter.notifyDataSetChanged();
                break;
            // @}
            case "9"://完工已评价
                orderState.setBackgroundColor(getResources().getColor(R.color.bg_8));
                tvOne.setVisibility(View.GONE);
                tvTwo.setVisibility(View.GONE);

                tvSubmitTime.setVisibility(View.VISIBLE);
                tvActualService.setVisibility(View.VISIBLE);
                tvOtherService.setVisibility(View.VISIBLE);
                tvActualPrcie.setVisibility(View.VISIBLE);
                tvOtherPrcie.setVisibility(View.VISIBLE);
                tvToatalPrcie.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(data.getOthers_service_content())) {
                    tvOtherService.setText(getString(R.string.other_contente) + data.getOthers_service_content());
                }
                if (!TextUtils.isEmpty(data.getOthers_price())) {
                    tvOtherPrcie.setText(getString(R.string.other_pricer) + data.getOthers_price() + getString(R.string.yuan));
                }
                tvActualService.setText(getString(R.string.actual_content) + data.getOrder_subscribe_content() + getString(R.string.yuan_area));
                tvActualPrcie.setText(getString(R.string.actual_price) + data.getOrder_price() + getString(R.string.yuan));
                tvToatalPrcie.setText(getString(R.string.total_price) + data.getOrder_final_price() + getString(R.string.yuan));

                llCompleteImg.setVisibility(View.VISIBLE);
                imgAdapter.clear();
                imgAdapter.addAll(data.getOrderCompleteImgBeans());
                imgAdapter.notifyDataSetChanged();
                //sfsm zhoushilei: [工单详情增加用户评价] add code @{
                llAssess.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(data.getOrder_evaluate_content())) {
                    tvAssessContent.setText("暂无评价内容");
                } else {
                    tvAssessContent.setText(data.getOrder_evaluate_content());
                }
                // @}
                break;
            case "8"://完工用户待确认
            case "12"://完工待审核
                orderState.setBackgroundColor(getResources().getColor(R.color.bg_4));
                tvOne.setVisibility(View.GONE);
                tvTwo.setVisibility(View.GONE);

                tvSubmitTime.setVisibility(View.VISIBLE);
                tvActualService.setVisibility(View.VISIBLE);
                tvOtherService.setVisibility(View.VISIBLE);
                tvActualPrcie.setVisibility(View.VISIBLE);
                tvOtherPrcie.setVisibility(View.VISIBLE);
                tvToatalPrcie.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(data.getOthers_service_content())) {
                    tvOtherService.setText(getString(R.string.other_contente) + data.getOthers_service_content());
                }
                if (!TextUtils.isEmpty(data.getOthers_price())) {
                    tvOtherPrcie.setText(getString(R.string.other_pricer) + data.getOthers_price() + getString(R.string.yuan));
                }
                tvActualService.setText(getString(R.string.actual_content) + data.getOrder_subscribe_content() + getString(R.string.yuan_area));
                tvActualPrcie.setText(getString(R.string.actual_price) + data.getOrder_price() + getString(R.string.yuan));
                tvToatalPrcie.setText(getString(R.string.total_price) + data.getOrder_final_price() + getString(R.string.yuan));
                llCompleteImg.setVisibility(View.VISIBLE);
                imgAdapter.clear();
                imgAdapter.addAll(data.getOrderCompleteImgBeans());
                imgAdapter.notifyDataSetChanged();
                break;
            case "13"://退单
            case "14"://退单
                llRefund.setVisibility(View.VISIBLE);
                tvRefundContent.setText(data.getOrder_cancle_why());
                tvOne.setVisibility(View.GONE);
                tvTwo.setVisibility(View.GONE);
                break;
            default:
                break;
        }


    }


    //----------------------------------------网络请求返回----------------------------------------------//

    @Override
    public void onGetWorkOrderDetail(WorkOrderDetailBean data) {
        workOrderDetailBean = data;
        init(data);
    }

    @Override
    public void onGetRefundWorkOrderDetail(WorkOrderDetailBean data) {
        workOrderDetailBean = data;
        init(data);
    }

    @Override
    public void onUpdateWorkOrder(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        onResume();
    }


    @Override
    public void onCancleOrder(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("40"));
        EventBus.getDefault().post(new FirstEvent(Constants.REFRESH_ORDER));

        finish();
    }

    @Override
    public void onUpdateOrderState(String data) {
        ToastUtils.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("40"));
        EventBus.getDefault().post(new FirstEvent(Constants.REFRESH_ORDER));
        finish();
    }

    @Override
    public void onCancleCancleOrder(String data) {
        EventBus.getDefault().post(new FirstEvent("40"));
        ToastUtils.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent(Constants.REFRESH_ORDER));
        finish();
    }


    @Override
    public void onGetServiceClasss(List<ServiceClasssBean> data) {
        if (data.size() == 0) {
            map.clear();
            fragmentt.dismiss();
            workOrderDetailBean.setOrder_reality_content(order_reality_content.trim());
            startCompletedSubmissionFragment(workOrderDetailBean);
            order_reality_content = "";
            return;
        }

        list.clear();
        list = data;
        adapter.removeAll();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onUploadImgs(String[] data) {
        imgArray = data;
        for (int i = 0; i < data.length; i++) {
            map.put("order_complete_img" + (i + 1), data[i]);
        }
        LoadingUtil.hideLoading();
        ToastUtils.showToast(context.getApplicationContext(), R.string.load_success);
    }


//----------------------------------------打开DialogFragment----------------------------------------------//

    //打开选择服务分类fragment
    private void startSelectServiceFragment(List<ServiceClasssBean> list) {
        fragmentt = SelectServiceFragment.newInstance(list);
        fragmentt.setSelectServiceOnclickListener(new SelectServiceFragment.SelectServiceOnclickListener() {
            @Override
            public void next(int classParentId, String orderContent) {
                order_reality_content = (order_reality_content + orderContent).trim();
                serverMap.clear();
                serverMap.put("class_parent_id", classParentId + "");
                getPresenter().getServiceClasss(serverMap);
                //orderContentList.add(orderContent);
            }

            @Override
            public void Previous() {
                order_reality_content = "";
                serverMap.clear();
                serverMap.put("class_parent_id", "-1");
                getPresenter().getServiceClasss(serverMap);
            }

            @Override
            public void onClose() {
                order_reality_content = "";
            }
        });
        fragmentt.show(getFragmentManager(), WorkOrderDetailFragment.class.getSimpleName());
        adapter = fragmentt.getAdapter();
    }

    //打开选择服务分类最后一步fragment
    private void startCompletedSubmissionFragment(WorkOrderDetailBean workOrderDetailBean) {
        completeFragment = CompletedSubmissionFragment.newInstance(workOrderDetailBean);
        completeFragment.setCompletedSubmissionOnclickListener(new CompletedSubmissionFragment.CompletedSubmissionOnclickListener() {

            @Override
            public void upLoadImage(List<MultipartBody.Part> map) {
                getPresenter().uploadImgs(map);
                LoadingUtil.showLoading(getActivity(), getResources().getString(R.string.img_loading));
            }

            @Override
            public void commit(WorkOrderDetailBean workOrderDetailBean, String otherCOntent, String otherPrice) {
                if (imgArray.length <= 0) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.choose_img));
                    return;
                }
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("order_id", order_id);
                map.put("type", "worker_complete");
                map.put("order_reality_content", workOrderDetailBean.getOrder_reality_content());
                map.put("order_complete_note", workOrderDetailBean.getOrder_complete_note());
                map.put("order_price", workOrderDetailBean.getOrder_price());
                map.put("others_service_content", otherCOntent);
                map.put("others_price", otherPrice);
                getPresenter().updateOrderState(map);
                completeFragment.dismiss();
            }

            @Override
            public void onClose() {
                order_reality_content = "";
            }
        });
        completeFragment.show(getFragmentManager(), WorkOrderDetailFragment.class.getSimpleName());
    }

    //申请退单fragment
    private void startApplyChangeBackFragment() {
        ApplyChargebackFragment fragment = ApplyChargebackFragment.newInstance();
        fragment.setApplyChargebackOnclickListener(new ApplyChargebackFragment.ApplyChargebackOnclickListener() {
            @Override
            public void onOk(String reason) {
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("order_id", order_id);
                map.put("order_cancle_why", reason);
                getPresenter().cancleOrder(map);
            }
        });
        fragment.show(getFragmentManager(), WorkOrderDetailFragment.class.getSimpleName());
    }


    //修改服务内容
    private void startChangeContentFragment(String content, String are, String mothed) {
        ChangeContentFragment fragment = ChangeContentFragment.newInstance(content, are, mothed);
        fragment.setOnclickListener(new ChangeContentFragment.OnclickListener() {

            @Override
            public void ok(String content, String are, String mothod) {
                map.clear();
                map.put("member_id", userBean.getMember_id());
                map.put("member_token", userBean.getMember_token());
                map.put("order_id", order_id);
                map.put("work_area", are);
                map.put("work_requirements", content);
                map.put("work_way", mothod);
                getPresenter().updateWorkOrder(map);
            }
        });
        fragment.show(getFragmentManager(), WorkOrderDetailFragment.class.getSimpleName());
    }

    //--------------------------------------------------------------------------------------//


    @OnClick({R.id.tvChange, R.id.ivLeft, R.id.tvOne, R.id.tvTwo, R.id.tvThree, R.id.tvMap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvChange:
                startChangeContentFragment(workOrderDetailBean.getWork_requirements(), workOrderDetailBean.getWork_area(), workOrderDetailBean.getWork_way());
                break;
            case R.id.tvMap:
                Intent intent = new Intent(context, LocationDemo.class);
                intent.putExtra(Constants.Latitude, workOrderDetailBean.getOrder_address_latitude());
                intent.putExtra(Constants.Longitude, workOrderDetailBean.getOrder_address_longitude());
                startActivity(intent);
                break;

            case R.id.tvOne:
                if (tvOne.getText().equals(getString(R.string.completed))) {

                    if ("0".equals(workOrderDetailBean.getOrder_type())) {
                        startCompletedSubmissionFragment(workOrderDetailBean);
                    } else {
                        serverMap.clear();
                        serverMap.put("class_parent_id", "-1");
                        getPresenter().getServiceClasss(serverMap);
                        startSelectServiceFragment(list);
                    }
                } else if (tvOne.getText().equals(getString(R.string.go_server))) {
                    map.clear();
                    map.put("member_id", userBean.getMember_id());
                    map.put("member_token", userBean.getMember_token());
                    map.put("order_id", order_id);
                    map.put("type", "worker_service");
                    getPresenter().updateOrderState(map);
                }
                break;
            case R.id.tvTwo:
                if (tvTwo.getText().equals(getString(R.string.shenqing_refund))) {
                    startApplyChangeBackFragment();
                } else if (tvTwo.getText().equals(getString(R.string.cancel_order))) {
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage(getString(R.string.is_cancel_order));
                    builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            map.clear();
                            map.put("member_id", userBean.getMember_id());
                            map.put("member_token", userBean.getMember_token());
                            map.put("order_id", order_id);
                            getPresenter().cancleCancleOrder(map);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();
                }
                break;

            case R.id.tvThree:
                if (tvThree.getText().equals(getString(R.string.lianxi_user))) {//拨打业主电话
                    if (TextUtils.isEmpty(userNumber)) {
                        ToastUtils.showToast(context.getApplicationContext(), getString(R.string.no_user_phone));
                        return;
                    }
                    final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                    builder.setMessage(getString(R.string.call) + userNumber + getString(R.string.user_phone));
                    builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            sq(userNumber);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.onCreate().show();
                } else if (tvThree.getText().equals(getString(R.string.cutomer_server))) {
                    customerNumber = getText(R.string.lxrPhone).toString();
                    final CustomDialog.Builder phoneBuilder = new CustomDialog.Builder(context);
                    phoneBuilder.setMessage(getString(R.string.call) + customerNumber + getString(R.string.customer_phone));
                    phoneBuilder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            sq(customerNumber);
                            dialog.dismiss();
                        }
                    });
                    phoneBuilder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    phoneBuilder.onCreate().show();
                }
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a completeFragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public WorkOrderDetailPersenter createPresenter() {
        return new WorkOrderDetailPersenter(getApp());
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showToast(context.getApplicationContext(), e.getMessage());
    }


    //----------------------------------------打电话----------------------------------------------//
    private void sq(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                ToastUtils.showToast(context.getApplicationContext(), getString(R.string.authorized));
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            CallPhone(phoneNumber);
        }
    }

    private void CallPhone(String phoneNumber) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);
        startActivity(intent);
    }
}
