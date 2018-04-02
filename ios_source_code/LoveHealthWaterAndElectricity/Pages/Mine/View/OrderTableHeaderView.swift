//
//  OrderTableHeaderView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/23.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class OrderTableHeaderView: UITableViewHeaderFooterView {

    @IBOutlet weak var orderNum: UILabel!
    
    @IBOutlet weak var orderState: UILabel!
    
    var model : OrderModel!{
        willSet(m){
            //wait_pay:等待付款 
            //wait_send：等待发
            //wait_receive:确认收货
            //wait_assessment:等待评价
            orderNum.text = "订单号：" + m.order_no!
            switch m.order_state! {
            case "wait_pay":
                orderState.text = "等待付款"
            case "wait_send":
                orderState.text = "等待发货"
            case "wait_receive":
                orderState.text = "确认收货"
            case "wait_assessment":
                orderState.text = "等待评价"
            case "end":
                orderState.text = "已完成"
            case "cancel":
                orderState.text = "交易已关闭"
            default:
                return
            }
        }
    }
    var rgModel : RefundGoodsModel!{
        willSet(m){
            orderNum.text = "售后编号：" + m.refund_no!
            //wait_review：等待审核 accept:接受 refuse：拒绝 end:退款成功
            if m.refund_type == "with_goods"{
                switch m.refund_state! {
                case "wait_review":
                    orderState.text = "等待审核"
                case "accept":
                    orderState.text = "退货中"
                case "refuse":
                    orderState.text = "退货失败"
                case "end":
                    orderState.text = "退货成功"
                default:
                    break
                }
            }else{
                switch m.refund_state! {
                case "wait_review":
                    orderState.text = "等待审核"
                case "accept":
                    orderState.text = "退款中"
                case "refuse":
                    orderState.text = "退款失败"
                case "end":
                    orderState.text = "退款成功"
                default:
                    break
                }
            }
            
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */

}
