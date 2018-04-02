//
//  LHWEModel.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/18.
//  Copyright © 2017年 tts. All rights reserved.
//

import Foundation


class HomePageBannerModel: HandyJSON {
   
    var banner_id : String?
    var banner_title : String?
    var banner_img : String?
    var banner_url : String?
    var banner_type : String?
    required init(){}
}
class StarMasterModel: HandyJSON {
    var member_id : String?
    var member_type : String?
    var member_head_image : String?
    var member_work_type : String?
    var member_nick_name : String?
    var member_real_name : String?
    var star_worker_info : String?
    required init(){}
}
class WorkFormModel: HandyJSON {
    var order_id : String?
    var order_member_id : String?
    var order_name : String?
    var order_phone : String?
    var order_address_country : String?
    var order_address_province : String?
    var order_address_city : String?
    var order_address_district : String?
    var order_address_detail : String?
    var order_subscribe_content : String?
    var order_subscribe_note : String?
    var order_audit_pass_time : String?
    var order_update_time : String?
    var order_state : String?
    var order_cancle_why : String?
    var order_hope_service_time : String?
    required init(){}
}
class WorkOrderModel: HandyJSON{
    
    var order_id: String?
    var order_member_id : String?
    var order_name : String?
    var order_phone : String?
    var order_address_country : String?
    var order_address_province : String?
    var order_address_city : String?
    var order_address_district : String?
    var order_address_detail : String?
    var order_subscribe_content : String?
    var order_subscribe_note : String?
    var order_subscribe_img1 : String?
    var order_subscribe_img2 : String?
    var order_subscribe_img3 : String?
    var order_state : String?
    var order_accept_id : String?
    var order_cancle_why : String?
    var order_cancle_time : String?
    var order_reality_content : String?
    var order_complete_img1 : String?
    var order_complete_img2 : String?
    var order_complete_img3 : String?
    var order_complete_note : String?
    var order_is_delete : String?
    var order_class_id : String?
    var order_is_cancle : String?
    var order_service_attitude : String?
    var order_service_aging : String?
    var order_sercice_quality : String?
    var order_evaluate_content : String?
    var stateList : String?
    var order_address_longitude : String?
    var order_address_latitude : String?
    var distance : String?
    var order_price : String?
    var order_state_show : String?
    var is_today_order : String?
    var order_accept : OrderAcceptModel?
    
    
    
    required init(){}
}
class OrderAcceptModel: HandyJSON {
    var member_id : String?
    var member_account : String?
    var member_password : String?
    var member_nick_name : String?
    var member_phone : String?
    var member_type : String?
    var member_head_image : String?
    var member_age : String?
    var member_sex : String?
    var member_work_type : String?
    var member_work_age : String?
    var member_state : String?
    
    required init(){}
}
class SystemMessageModel: HandyJSON {
    var msg_desc : String?
    var create_time : String?
    var cellHeight : CGFloat?
    var order_no : String?
    var logistics_state : String?
    var logistics_no : String?
    var goods_img: String?
    var goods_name : String?
    required init(){}
}
class OrderModel: HandyJSON {
    var order_id : String?
    var merchants_id : String?
    var member_id : String?
    var order_no : String?
    var address_id : String?
    var mobile : String?
    var name : String?
    var province : String?
    var city : String?
    var country : String?
    var detailed_address : String?
    var order_state : String?
    var order_total_price : String?
    var order_pay_no : String?
    var order_total_express : String?
    var is_free_express : String? //1快递免邮 0快递配送
    var create_time : String?     //创建时间
    var pay_time : String?
    var send_time : String?
    var end_time : String?
    var express_price : String?   //快递费
    var coupon_price : String?
    var deduct_integral_price : String?
    var order_actual_price : String?
    var custom_remark : String?  //用户留言
    var logistics_name : String?
    var logistics_no : String?
    var orderGoodsBeans : [OrderGoodsBeans]?
    var logisticsDetailBeans : [logisticsDetailModel]?
    required init(){}
}
class OrderGoodsBeans: HandyJSON {
    
    var order_goods_id : String?
    var order_id : String?
    var goods_id : String?
    var goods_num : String?
    var goods_price : String?
    var goods_name : String?
    var goods_img : String?
    var goods_url : String?
    var send_goods_time : String?
    var specification_name : String?
    required init() {}
}
class logisticsDetailModel: HandyJSON {
   
    var logistics_time : String?
    var logistics_state : String?
    var logistics_context : String?
    required init(){}
}
class LogisticsCompanyModel: HandyJSON {
    
    var logistics_name : String?
    required init(){}
}
class RefundReasonModel: HandyJSON {
    
    var refund_reason_id : String?
    var reason_name : String?
    required init(){}
}
class RefundGoodsModel: HandyJSON {
    var refund_id : String?
    var refund_no : String?
    var order_id : String?
    var order_goods_id : String?
    var goods_name : String?
    var create_time : String?
    var refund_count : String?
    var refund_desc : String?
    var refund_state : String?
    var refund_state_show : String?
    var refund_price : String?
    var refund_deduct_integral : String?
    var refund_give_integral : String?
    var refund_order_no : String?
    var refund_type : String?
    var reason_name : String?
    var refuse_desc : String?
    var refund_img1 : String?
    var refund_img2 : String?
    var refund_img3 : String?
    var custom_remark : String?
    var logistics_company : String?
    var logistics_no : String?
    var logistics_phone : String?
    var logistics_name : String?
    var logistics_time : String?
    var audit_time : String?
    var end_time : String?
    var orderGoodsBean : RefundGoodsDetailModel?
    var orderBean : OrderBeanModel?
    var goodsBean : GoodsBeanModel!
    required init(){}
}
class GoodsBeanModel : HandyJSON{
    var merchantsBean : MerchantsBeanModel?
    required init(){}
}
class MerchantsBeanModel : HandyJSON{
    var company_mobile : String?
    required init(){}
}
class OrderBeanModel: HandyJSON {
    
    var order_no : String?
    var create_time : String?
    var pay_time : String?
    
    required init(){}
}
class RefundGoodsDetailModel: HandyJSON {
    var goods_id : String?
    var goods_num : String?
    var goods_price : String?
    var goods_name : String?
    var goods_img : String?
    var goods_url : String?
    var specification_name : String?
    var express_price : String?
    var is_free_express : String?
    required init() {}
}
class OrderDetailModel: HandyJSON {
    var order_id : String?                   //订单id
    var order_member_id : String?            //预约发布用户id
    var order_name : String?                 //姓名
    var order_phone : String?                //联系方式
    var order_address_province : String?     //省
    var order_address_city : String?         //市
    var order_address_district : String?     //区
    var order_address_detail : String?       //详细地址
    var order_subscribe_content : String?    //服务内容
    var order_subscribe_note : String?       //服务备注
    var order_subscribe_img1 : String?       //预约现场图片
    var order_subscribe_img2 : String?
    var order_subscribe_img3 : String?
    var order_hope_service_time : String?    //期望上门时间
    var order_create_time : String?          //预约时间
    var order_update_time : String?          //更新时间
    var order_audit_pass_time : String?      //预约审核通过时间
    var order_state : String?                //工单状态码
    var order_accept_id : String?            //接单师傅id
    var order_accept_time : String?          //接单时间
    var order_cancle_why : String?           //退单原因
    var order_cancle_time : String?          //退单时间
    var order_cancle_pass_time : String?     //退单审核通过时间
    var order_reality_content : String?      //实际服务内容
    var order_complete_img1 : String?        //完工现场图片
    var order_complete_img2 : String?
    var order_complete_img3 : String?
    var order_complete_note : String?        //完工备注
    var order_service_time : String?         //实际服务时间
    var order_complete_time : String?        //完工时间
    var order_complete_pass_time : String?   //完工审核通过时间
    var order_accept : String?               //接单师傅对象
    var order_is_cancle : String?            //是否退单
    var order_service_attitude : String?     //服务态度
    var order_service_aging : String?        //服务时效
    var order_sercice_quality : String?      //服务质量
    var order_evaluate_content : String?     //评价内容
    var order_address_longitude : String?    //服务地址经度
    var order_address_latitude : String?     //服务地址纬度
    var order_state_show : String?           //工单状态显示
    var is_today_order : String?             //是否今日订单
    required init() {}
}

class TracesModel: HandyJSON{
    
    var isCurrentState = false
    var logistics_id: String?
    var logistics_time: String?
    var logistics_context : String?
    var logistics_no : String?
    required init(){}
}
class ServicesCategoriesModel: HandyJSON {
    
    var isSelected = false
    var class_id : String?
    var class_name : String?
    var class_price : String?
    required init(){}
}
class AddressModel: HandyJSON {
    
    var address_id : String?
    var member_id : String?
    var mobile : String?
    var name : String?
    var province : String?
    var city : String?
    var country : String?
    var detailed_address : String?
    var is_default : String?
    var address_state : String?
    var zip_code : String?
    var district : String?
    required init(){}
}
class CollectionGoodsModel: HandyJSON {
    var collection_id : String?
    var relation_id : String?
    var goodsBean : GoodsBean?
    required init(){}
}
class GoodsBean: HandyJSON {
    var goods_id : String?
    var goods_name : String?
    var goods_img : String?
    var goods_price : String?
    var goods_sales : String?
    var assessment_count : String?
    required init(){}
}
class PersonInfoModel: HandyJSON {
    var member_account : String?
    var member_password : String?
    var member_freeze_money : String?
    var member_extract_money : String?
    var member_bank_name : String?
    var member_bank_open_name : String?
    var member_bank_user_name : String?
    var member_bank_code  : String?
    var member_bank_phone : String?
    var member_alipay : String?
    var member_we_chat : String?
    var member_pay_password : String?
    required init(){}
}
class ApplyCashsModel: HandyJSON {
    var cash_id : String?
    var cash_time : String?
    var cash_price : String?
    var brank_name : String?
    var brank_code : String?
    var brank_open_mobile : String?
    var apply_state : String?
    
    required init(){}
}
class CouponsModel: HandyJSON {
    var coupon_name : String?
    var coupon_price : String?
    var coupon_full_price : String?
    var start_time : String?
    var end_time : String?
    var coupon_state : String?
    var coupon_desc : String?
    required init(){}
}
class IntegralRecordModel: HandyJSON {
    var integral_value : String?
    var integral_type_show : String?
    var create_time : String?
    var state : String?
    var deduction : String?
    required init(){}
}
class ReportedListModel: HandyJSON {
    var reported_id : String?
    var reported_name : String?
    var reported_phone : String?
    var province : String?
    var city : String?
    var district : String?
    var detail : String?
    var reported_img1 : String?
    var reported_img2 : String?
    var reported_img3 : String?
    var create_time : String?
    var reported_state_show : String?
    var reported_state : String?
    required init(){}
}
class WorkTypeModel: HandyJSON {
    var type_id : String?
    var type_name : String?
    var isSelected = false
    required init(){}
}
