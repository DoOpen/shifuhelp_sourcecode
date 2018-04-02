//
//  OrderDetailViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/25.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class OrderDetailViewController: UIViewController,UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var tableView: UITableView!

    @IBOutlet weak var bottomView: UIView!
    
    @IBOutlet weak var bottomHeight: NSLayoutConstraint!
    
    @IBOutlet weak var leftBtn: UIButton!
    
    @IBOutlet weak var middleBtn: UIButton!
    
    @IBOutlet weak var rightBtn: UIButton!
    
    
    var model : OrderModel!
    var amodel: AddressModel!
    var confirmOrCancelOrder: (()->())?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = "订单详情"
        
        self.getDefaultAddress()
        
        self.rightBtn.layer.cornerRadius = 16
        self.rightBtn.layer.borderWidth = 1
        self.rightBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
        self.rightBtn.layer.masksToBounds = true
        
        self.middleBtn.layer.cornerRadius = 16
        self.middleBtn.layer.borderWidth = 1
        self.middleBtn.layer.borderColor = UIColor.init(hexString: "AEAEAE").cgColor
        self.middleBtn.layer.masksToBounds = true
        
        self.leftBtn.layer.cornerRadius = 16
        self.leftBtn.layer.borderWidth = 1
        self.leftBtn.layer.borderColor = UIColor.init(hexString: "AEAEAE").cgColor
        self.leftBtn.layer.masksToBounds = true
        
        //wait_pay:等待付款 wait_send：等待发货 wait_receive:确认收货 wait_assessment:等待评价 end:已完成 cancel:取消
        if model.order_state == "wait_pay"{
            self.rightBtn.setTitle("付款", for: .normal)
            self.middleBtn.setTitle("取消订单", for: .normal)
            self.leftBtn.setTitle("联系客服", for: .normal)
        } else if model.order_state == "wait_send"{
            self.rightBtn.setTitle("联系客服", for: .normal)
            self.middleBtn.isHidden = true
            self.leftBtn.isHidden = true
        } else if model.order_state == "wait_receive"{
            self.rightBtn.setTitle("确认收货", for: .normal)
            self.middleBtn.setTitle("查看物流", for: .normal)
            self.leftBtn.setTitle("联系客服", for: .normal)
        } else if model.order_state == "wait_assessment" {
            
            self.rightBtn.setTitle("评价", for: .normal)
            self.middleBtn.setTitle("查看物流", for: .normal)
            self.leftBtn.setTitle("联系客服", for: .normal)
            
        }else{
            self.bottomView.isHidden = true
            self.bottomHeight.constant = 0
            self.rightBtn.isHidden = true
            self.middleBtn.isHidden = true
            self.leftBtn.isHidden = true
        }
//        else if model.order_state == "end"{
//            
//            self.rightBtn.isHidden = true
//            self.middleBtn.isHidden = true
//            self.leftBtn.isHidden = true
//        } else if model.order_state == "cancel" {
//            self.rightBtn.isHidden = true
//            self.middleBtn.isHidden = true
//            self.leftBtn.isHidden = true
//        }

        
        
        
        self.tableView.register(UINib.init(nibName: "OrderDetailTableViewCell", bundle: nil), forCellReuseIdentifier: OrderDetailTableViewCell.description())
        self.tableView.register(UINib.init(nibName: "OrderDetailHeaderView", bundle: nil), forHeaderFooterViewReuseIdentifier: OrderDetailHeaderView.description())
        self.tableView.register(UINib.init(nibName: "OrderDetailFooterView", bundle: nil), forHeaderFooterViewReuseIdentifier: OrderDetailFooterView.description())
        
        
        
        // Do any additional setup after loading the view.
    }
    func getDefaultAddress(){
        NetworkingHandle.fetchNetworkData(url: "addressInterfaces.api?getDefaultAddress", at: self, success: { (response) in
            self.amodel = AddressModel.deserialize(from: response["data"] as? NSDictionary)
            self.tableView.reloadData()
        })
    }
    //MARK: -tableview代理
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: OrderDetailTableViewCell.description()) as! OrderDetailTableViewCell
        cell.model = self.model.orderGoodsBeans?[indexPath.row]
        cell.bottomView.isHidden = true
        cell.refundBtn.isHidden = true
        if self.model.order_state == "wait_send" || self.model.order_state == "wait_assessment"{
            cell.bottomView.isHidden = false
            cell.refundBtn.isHidden = false
        }
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return (self.model.orderGoodsBeans?.count)!
    }
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let header = tableView.dequeueReusableHeaderFooterView(withIdentifier: OrderDetailHeaderView.description()) as! OrderDetailHeaderView
        if self.amodel != nil{
            header.user.text = self.amodel.name
            header.address.text = self.amodel.province! + self.amodel.city! + self.amodel.detailed_address!
        }
        switch self.model.order_state! {
        case "wait_pay":
            header.state.text = "等待付款"
            header.detail.text = "请及时支付订单"
        case "wait_send":
            header.state.text = "等待商家发货"
            header.detail.text = "您的商品打包中，请耐心等候哦"
        case "wait_receive":
            header.state.text = "商家已发货"
            header.detail.text = "您的商品打包中，请耐心等候哦"
        case "wait_assessment":
            header.state.text = "交易成功"
            header.detail.text = "买家已确认收货"
        case "end":
            header.state.text = "交易完成"
            header.detail.text = ""
        default:
            header.state.text = "交易取消"
            header.detail.text = ""
        }
        return header
    }
    func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
        let footer = tableView.dequeueReusableHeaderFooterView(withIdentifier: OrderDetailFooterView.description()) as! OrderDetailFooterView

        if self.model.is_free_express == "0"{
            footer.sendType.text = "快递配送"
            footer.postage.text = "¥" + self.model.express_price!
        }else if self.model.is_free_express == "1"{
            footer.sendType.text = "快递免邮"
        }
        if self.model.coupon_price != ""{
            footer.discount.text = "¥" + self.model.coupon_price!
        }
        if self.model.deduct_integral_price != ""{
            footer.secondDiscount.text = "¥" + self.model.deduct_integral_price!
        }
        let attributedstr = NSMutableAttributedString(string: "实际应付：¥" + self.model.order_actual_price!)
        attributedstr.addAttributes([NSForegroundColorAttributeName : UIColor.init(hexString: "#EB6900")], range: NSRange.init(location: 5, length: (self.model.order_actual_price?.characters.count)! + 1))
        footer.sum.attributedText = attributedstr
        footer.orderNum.text = "订单编号：" + self.model.order_no!
        footer.orderCreateTime.text = "订单创建时间：" + self.model.create_time!
        footer.userMessage.text = self.model.custom_remark!
        
        switch self.model.order_state! {
        case "wait_pay":
            footer.payTime.isHidden = true
            footer.sendGoodsTime.isHidden = true
            footer.finishTime.isHidden = true
        case "wait_send":
            footer.payTime.text = "付款时间：" + self.model.pay_time!
            footer.sendGoodsTime.isHidden = true
            footer.finishTime.isHidden = true
        case "wait_receive":
            footer.payTime.text = "付款时间：" + self.model.pay_time!
            footer.sendGoodsTime.text = "发货时间：" + self.model.send_time!
            footer.finishTime.isHidden = true
        case "wait_assessment":
            footer.payTime.text = "付款时间：" + self.model.pay_time!
            footer.sendGoodsTime.text = "发货时间：" + self.model.send_time!
            footer.finishTime.text = "完成时间：" + self.model.end_time!
        case "end":
            footer.payTime.text = "付款时间：" + self.model.pay_time!
            footer.sendGoodsTime.text = "发货时间：" + self.model.send_time!
            footer.finishTime.text = "完成时间：" + self.model.end_time!
        default:
            footer.payTime.isHidden = true
            footer.sendGoodsTime.isHidden = true
            footer.finishTime.isHidden = true
            break
        }
        return footer
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 166
    }
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        switch self.model.order_state! {
        case "wait_pay":
           return 375
        case "wait_send":
           return 395
        case "wait_receive":
            return 424
        case "wait_assessment":
            return 450
        case "end":
           return 450
        default:
            return 375
        }

        
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if self.model.order_state == "wait_send" || self.model.order_state == "wait_assessment"{
            return 163
        }
        return 114
        
    }
    @IBAction func btnClickedAction(_ sender: UIButton) {
        if sender == self.rightBtn{
            switch self.model.order_state! {
            case "wait_pay":
                pay()
            case "wait_send":
                connectService()
            case "wait_receive":
                confirm()
            case "wait_assessment":
                evaluate()
            default:
                break
            }
            
        }else if sender == self.middleBtn{
            switch self.model.order_state! {
            case "wait_pay":
                cancel()
            case "wait_send":
                break
            case "wait_receive":
                checkLogistics()
            case "wait_assessment":
                checkLogistics()
            default:
                break
            }
            
        }else{
            switch self.model.order_state! {
            case "wait_pay":
                connectService()
            case "wait_send":
                break
            case "wait_receive":
                connectService()
            case "wait_assessment":
                connectService()
            default:
                break
            }
        }
    }
    func pay() {
        let vc = UIAlertController.init(title: "选择支付方式", message: "", preferredStyle: .actionSheet)
        let wxAction = UIAlertAction.init(title: "微信", style: .default) { (alert) in
            self.payGoodsOrder(type: "wx", orderID: self.model.order_id!)
        }
        let sonAction = UIAlertAction.init(title: "支付宝", style: .default) { (alert) in
            self.payGoodsOrder(type: "alipay", orderID: self.model.order_id!)
        }
        let cancelAction = UIAlertAction.init(title: "取消", style: .cancel, handler: nil)
        vc.addAction(wxAction)
        vc.addAction(sonAction)
        vc.addAction(cancelAction)
        self.present(vc, animated: true, completion: nil)
    }
    func payGoodsOrder(type: String, orderID: String){
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?payRealOrders", at: self, params: ["order_ids":orderID, "channel":type], success: { (response) in
            let data = response["data"] as! String
            let payDic = getDictionaryFromJSONString(jsonString: data)
            if type == "wx"{
                Pingpp.createPayment(payDic, appURLScheme: WXAppID, withCompletion: { (result, error) in
                    if error != nil{
                        print(error?.description ?? "")
                    }
                })
            }else{
                Pingpp.createPayment(payDic, appURLScheme: SonAppID, withCompletion: { (result, error) in
                    if error != nil{
                        print(error?.description ?? "")
                    }
                })
            }
        }) {
        }
    }
    func evaluate() {
        let vc = EvaluateGoodsViewController()
        vc.goodsArr = self.model.orderGoodsBeans!
        vc.evaluateSuccess = { [unowned self] in
            self.navigationController?.popViewController(animated: true)
            
        }
        self.navigationController?.pushViewController(vc, animated: true)
    }
    func checkLogistics() {

        let vc = CheckLogisticsViewController()
        vc.model = self.model
        self.navigationController?.pushViewController(vc, animated: true)
    }
    func confirm() {
        
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?confirmOrder", at: self, params: ["order_id":model.order_id!], success: { (response) in
            ProgressHUD.showMessage(message: "操作成功")
            self.confirmOrCancelOrder?()
            self.navigationController?.popViewController(animated: true)
        }) {
            ProgressHUD.showMessage(message: "请重新确认订单")
        }
    }
    func drawback(){
        self.navigationController?.pushViewController(ApplyRefundViewController(), animated: true)
    }
    func cancel() {
        
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?cancelOrder", at: self, params: ["order_id":model.order_id!], success: { (response) in
            ProgressHUD.showMessage(message: "操作成功")
            self.confirmOrCancelOrder?()
            self.navigationController?.popViewController(animated: true)
        }) {
            
            ProgressHUD.showMessage(message: "请重新取消订单")
        }
    }
    func connectService(){
        callCustomerServices(atView: self.view, phoneNum: CustomerServicesPhone)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
