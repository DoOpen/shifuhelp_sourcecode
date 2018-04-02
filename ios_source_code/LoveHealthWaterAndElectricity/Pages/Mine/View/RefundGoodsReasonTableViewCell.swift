//
//  RefundGoodsReasonTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/11.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class RefundGoodsReasonTableViewCell: UITableViewCell {

    @IBOutlet weak var orderNo: UILabel!
    
    @IBOutlet weak var createTime: UILabel!
    
    @IBOutlet weak var payTime: UILabel!
    
    @IBOutlet weak var refundReason: UILabel!
    
    @IBOutlet weak var refundMoney: UILabel!
    
    @IBOutlet weak var applyTime: UILabel!
    
    
    @IBOutlet weak var afterSaleNum: UILabel!
    
    var model : RefundGoodsModel!{
        willSet(m){
            
            self.orderNo.text = "订单编号：" + (m.orderBean?.order_no!)!
            self.createTime.text = "订单创建时间：" + (m.orderBean?.create_time!)!
            self.payTime.text = "支付时间：" + (m.orderBean?.pay_time!)!
            self.refundReason.text = "退款原因：" + m.reason_name!
            self.refundMoney.text = "退款金额：" + m.refund_price!
            self.applyTime.text = "申请时间：" + m.create_time!
            self.afterSaleNum.text = "售后单号：" + m.refund_no!
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
