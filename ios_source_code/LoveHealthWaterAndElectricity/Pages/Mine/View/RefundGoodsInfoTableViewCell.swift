//
//  RefundGoodsInfoTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/11.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class RefundGoodsInfoTableViewCell: UITableViewCell {

    @IBOutlet weak var refundState: UILabel!
    
    @IBOutlet weak var time: UILabel!
    
    @IBOutlet weak var goodImg: UIImageView!
    
    @IBOutlet weak var goodsName: UILabel!
    
    @IBOutlet weak var kinds: UILabel!
    
    @IBOutlet weak var price: UILabel!
    
    @IBOutlet weak var num: UILabel!
    
    @IBOutlet weak var reason: UILabel!
    
    @IBOutlet weak var sum: UILabel!
    
    var model : RefundGoodsModel!{
        willSet(m){
            if m.refund_state == "with_goods" {
                switch m.refund_state! {
                case "wait_review":
                    self.refundState.text = "等待商家审核"
                    self.reason.text = "您的申请已提交成功，正在等待商家审核，请耐心等待，谢谢合作！"
                case "accept":
                    self.refundState.text = "恭喜您！商家已同意您的退货申请！"
                    self.time.text = m.audit_time
                    self.reason.text = "恭喜您！商家已同意您的退货申请，请正确填写退货物流信息，如有疑问请联系客服，谢谢合作！"
                case "refuse":
                    self.refundState.text = "对不起！商家已拒绝了您的退货申请！"
                    self.time.text = m.audit_time
                    self.reason.text = "对不起！商家拒绝了您的退货申请，如有疑问请联系客服，谢谢合作！"
                case "end":
                    self.refundState.text = "恭喜您！退货成功！"
                    self.time.text = m.end_time
                    self.reason.text = "恭喜您！退货成功，商家已将货款退回到您的支付账号内，请随时留意，如有疑问请联系客服，谢谢合作！"
                default:
                    break
                }
            }else{
                switch m.refund_state! {
                case "wait_review":
                     self.refundState.text = "等待商家审核"
                     self.reason.text = "您的申请已提交成功，正在等待商家审核,请耐心等待,谢谢合作！"
                case "accept":
                     self.refundState.text = "退款审核通过"
                     self.time.text = m.audit_time
                    self.reason.text = "您的申请已通过商家审核，正在等待商家退款，请耐心等待，谢谢合作！"
                case "refuse":
                    self.refundState.text = "对不起！退款失败了"
                     self.time.text = m.audit_time
                    self.reason.text = "对不起！退款失败了,商家拒绝了退回您的货款！如有疑问请联系客服，谢谢！"
                case "end":
                     self.refundState.text = "恭喜您！退款成功"
                    self.time.text = m.end_time
                    self.reason.text = "恭喜您！退款成功！商家已将货款退回到您的支付账号内，请随时留意，如有疑问请联系客服，谢谢合作！"
                default:
                    break
                }
            }
            
            goodImg.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + (m.orderGoodsBean?.goods_img)!), placeholder: #imageLiteral(resourceName: "logo"), options: nil, progressBlock: nil, completionHandler: nil)
            goodsName.text = m.orderGoodsBean?.goods_name!
            kinds.text = "规格：" + (m.orderGoodsBean?.specification_name!)!
            price.text = "¥" + (m.orderGoodsBean?.goods_price)!
            num.text = "x" + (m.orderGoodsBean?.goods_num!)!
            sum.text = "¥" + m.refund_price!
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
