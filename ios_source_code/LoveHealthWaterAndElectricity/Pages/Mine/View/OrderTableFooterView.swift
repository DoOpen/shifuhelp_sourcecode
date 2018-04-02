//
//  OrderTableFooterView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/23.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class OrderTableFooterView: UITableViewHeaderFooterView {
    @IBOutlet weak var priceSum: UILabel!

    @IBOutlet weak var goodsNumSum: UILabel!
    @IBOutlet weak var rightBtn: UIButton!
    
    @IBOutlet weak var leftBtn: UIButton!
    
    @IBOutlet weak var serviceBtn: UIButton!
    
    @IBOutlet weak var bgView: UIView!
    
    @IBOutlet weak var bgViewHeight: NSLayoutConstraint!
    
    // 1 确认订单 2 取消订单
    
    var operationBlock : ((String,String)->())?
    var isRefundGoods = false
    var model : OrderModel!{
        willSet(m){
            if m.is_free_express == "1"{
                m.express_price = "0"
            }
            let attributedStr = NSMutableAttributedString.init(string: "合计:" + m.order_total_price! + "（运费：￥" + m.express_price! + "）")
             attributedStr.addAttributes([NSForegroundColorAttributeName : UIColor.init(hexString: "EB6900")], range: NSRange.init(location: 3, length: (m.order_total_price?.characters.count)!))
            self.priceSum.attributedText = attributedStr
            
            self.goodsNumSum.text = "共" + "\(m.orderGoodsBeans!.count)" + "件商品"
            
            //wait_pay:等待付款 wait_send：等待发货 wait_receive:确认收货 wait_assessment:等待评价 end:已完成 cancel:取消
            if m.order_state == "wait_pay"{
                
                self.rightBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
                
                self.rightBtn.setTitle("付款", for: .normal)
                
                self.leftBtn.setTitle("取消订单", for:.normal)
                self.serviceBtn.isHidden = true
            } else if m.order_state == "wait_send"{
                self.rightBtn.layer.borderColor = UIColor.init(hexString: "AEAEAE").cgColor
                self.rightBtn.setTitle("申请退款", for:.normal)
                self.leftBtn.setTitle("联系客服", for: .normal)
                self.serviceBtn.isHidden = true
            } else if m.order_state == "wait_receive"{
                self.rightBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
                self.rightBtn.setTitle("确认收货", for: .normal)
                self.leftBtn.setTitle("查看物流", for: .normal)
                self.serviceBtn.isHidden = false
            } else if m.order_state == "wait_assessment" {
                self.rightBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
                self.rightBtn.setTitle("评价", for: .normal)
                
                self.leftBtn.setTitle("查看物流", for: .normal)
                self.serviceBtn.isHidden = false
            } else if m.order_state == "end"{
                
                self.rightBtn.layer.borderColor = UIColor.init(hexString: "AEAEAE").cgColor
                self.rightBtn.setTitle("查看物流", for: .normal)
                self.leftBtn.setTitle("联系客服", for: .normal)
                self.serviceBtn.isHidden = false
            } else if m.order_state == "cancel" {
                self.bgView.isHidden = true
                self.bgViewHeight.constant = 0
            }
        }
    }
    var rgModel : RefundGoodsModel!{
        willSet(m){
            self.goodsNumSum.text = "共" + m.refund_count! + "件商品"
            if m.orderGoodsBean?.is_free_express == "1"{
                m.orderGoodsBean?.express_price = "0"
            }
            let attributedStr = NSMutableAttributedString.init(string: "合计:" + m.refund_price! + "（运费：￥" + (m.orderGoodsBean?.express_price!)! + "）")
            attributedStr.addAttributes([NSForegroundColorAttributeName : UIColor.init(hexString: "EB6900")], range: NSRange.init(location: 3, length: (m.refund_price?.characters.count)!))
            self.priceSum.attributedText = attributedStr

            self.leftBtn.isHidden = true
            self.serviceBtn.isHidden = true
            self.rightBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
            self.rightBtn.setTitle("查看详情", for: .normal)
            
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.rightBtn.layer.cornerRadius = 16
        self.rightBtn.layer.borderWidth = 1
        
        self.leftBtn.layer.cornerRadius = 16
        self.leftBtn.layer.borderColor = UIColor.init(hexString: "AEAEAE").cgColor
        self.leftBtn.layer.borderWidth = 1
        
        self.serviceBtn.layer.cornerRadius = 16
        self.serviceBtn.layer.borderColor = UIColor.init(hexString: "AEAEAE").cgColor
        self.serviceBtn.layer.borderWidth = 1
        
    }
    @IBAction func rightAction(_ sender: UIButton) {
        if isRefundGoods{
            print("查看详情")
            let vc = RefundGoodsDetailViewController()
            vc.model = self.rgModel
            self.responderViewController()?.navigationController?.pushViewController(vc, animated: true)
            return
            
        }
        switch model.order_state! {
            
        case "wait_pay":
            
            print("付款")
            pay()
            
        case "wait_send":
            
            print("申请退款")
            drawback()
            
        case "wait_receive":
            
            print("确认收货")
            confirm()
            
        case "wait_assessment":
            
            print("评价")
            evaluate()
            
        case "end":
            
            print("查看物流")
            checkLogistics()
            
        case "cancel":
            return
            
        default:
            return
        }
        
    }
    
    @IBAction func leftAction(_ sender: UIButton) {
        switch model.order_state! {
            
        case "wait_pay":
            
            print("取消订单")
            cancel()
        case "wait_send":
            
            print("联系客服")
           connectService()
            
        case "wait_receive":
            
            print("查看物流")
            checkLogistics()
            
        case "wait_assessment":
            print("查看物流")
            checkLogistics()
            
        case "end":
            print("联系客服")
            connectService()
            
        case "cancel":
            return
            
        default:
            return
        }

    }
    
    @IBAction func serviceAction(_ sender: UIButton) {
        print("联系客服")
        connectService()
    }
    func pay() {
         let vc = UIAlertController.init(title: "选择支付方式", message: "", preferredStyle: .actionSheet)
         let wxAction = UIAlertAction.init(title: "微信", style: .default) { (alert) in
         }
         let sonAction = UIAlertAction.init(title: "支付宝", style: .default) { (alert) in
            
        }
        vc.addAction(wxAction)
        vc.addAction(sonAction)
        self.responderViewController()?.present(vc, animated: true, completion: nil)
    }
    func evaluate() {
//        self.responderViewController()?.navigationController?.pushViewController(GoodsEvaluateViewController(), animated: true)
    }
    func checkLogistics() {
        self.responderViewController()?.navigationController?.pushViewController(CheckLogisticsViewController(), animated: true)
    }
    func confirm() {
//        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?confirmOrder", at: self, success: { (response) in
//          self.operationBlock?("1")
//        })
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?confirmOrder", at: self, params: ["order_id":model.order_id!], success: { (response) in
            
            self.operationBlock?("1",self.model.order_id!)
        }) { 
            ProgressHUD.showMessage(message: "请重新确认订单")
        }
    }
    func drawback(){
        self.responderViewController()?.navigationController?.pushViewController(ApplyRefundViewController(), animated: true)
    }
    func cancel() {
        
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?cancelOrder", at: self, params: ["order_id":model.order_id!], success: { (response) in
            self.operationBlock?("2", self.model.order_id!)
        }) {
            
            ProgressHUD.showMessage(message: "请重新取消订单")
        }
    }
    func connectService(){
        callCustomerServices(atView: self, phoneNum: CustomerServicesPhone)
    }
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */

}
