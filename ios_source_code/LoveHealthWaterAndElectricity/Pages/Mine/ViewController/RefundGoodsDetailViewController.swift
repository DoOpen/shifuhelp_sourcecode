//
//  RefundGoodsDetailViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/11.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class RefundGoodsDetailViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{
    
    @IBOutlet weak var rightBtn: UIButton!
    
    @IBOutlet weak var rightBtnWidth: NSLayoutConstraint!
    
    @IBOutlet weak var leftBtn: UIButton!
    
    @IBOutlet weak var tableView: UITableView!
    
    var model : RefundGoodsModel!
    var cancelRefund : (()->())?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "订单详情"
        self.tableView.register(UINib.init(nibName: "RefundGoodsInfoTableViewCell", bundle: nil), forCellReuseIdentifier: RefundGoodsInfoTableViewCell.description())
        self.tableView.register(UINib.init(nibName: "RefundGoodsReasonTableViewCell", bundle: nil), forCellReuseIdentifier: RefundGoodsReasonTableViewCell.description())
        self.tableView.register(UINib.init(nibName: "RefundGoodsRemarkTableViewCell", bundle: nil), forCellReuseIdentifier: RefundGoodsRemarkTableViewCell.description())
        self.setLayout()
        // Do any additional setup after loading the view.
    }
    func setLayout() {
        self.rightBtn.layer.cornerRadius = 16
        self.rightBtn.layer.borderColor = UIColor.init(hexString: "#EB6900").cgColor
        self.rightBtn.layer.borderWidth = 1
        self.rightBtn.layer.masksToBounds = true
        
        self.leftBtn.layer.cornerRadius = 16
        self.leftBtn.layer.borderColor = UIColor.init(hexString: "#AEAEAE").cgColor
        self.leftBtn.layer.borderWidth = 1
        self.leftBtn.layer.masksToBounds = true
        
        //wait_review：等待审核 accept:接受 refuse：拒绝 end:退款成功 注：多个逗号相隔
        if self.model.refund_type == "with_goods" {
            
            switch self.model.refund_state! {
            case "wait_review":
                self.rightBtn.setTitle("取消退货", for: .normal)
            case "accept":
                self.rightBtnWidth.constant = 99
                self.rightBtn.setTitle("填写退货物流", for: .normal)
            case "refuse":
                self.rightBtnWidth.constant = 0
                self.rightBtn.isHidden = true
            case "end":
                self.rightBtnWidth.constant = 0
                self.rightBtn.isHidden = true
            default:
                break
            }
            
        }else{
            switch self.model.refund_state! {
            case "wait_review":
                self.rightBtn.setTitle("取消退款", for: .normal)
            case "accept":
                self.rightBtnWidth.constant = 0
                self.rightBtn.isHidden = true
            case "refuse":
                self.rightBtnWidth.constant = 0
                self.rightBtn.isHidden = true
            case "end":
                self.rightBtnWidth.constant = 0
                self.rightBtn.isHidden = true
            default:
                break
            }
        }
    }
    func requestRefundGoodsInfo() {
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?getRefundDetail", at: self, params: ["refund_id":self.model.refund_id!], isAuthHide: false, isShowHUD: false, isShowError: false, success: { (response) in
            self.model = RefundGoodsModel.deserialize(from: response["data"] as? NSDictionary)
            self.setLayout()
            self.tableView.reloadData()
        }) { 
            
        }
    }
    //MARK：tableview代理
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if indexPath.row == 0{
            let cell = tableView.dequeueReusableCell(withIdentifier: RefundGoodsInfoTableViewCell.description()) as! RefundGoodsInfoTableViewCell
            cell.model = self.model
            return cell
        }else if indexPath.row == 1{
            let cell = tableView.dequeueReusableCell(withIdentifier: RefundGoodsReasonTableViewCell.description()) as! RefundGoodsReasonTableViewCell
            cell.model = self.model
            return cell
        }else{
            let cell = tableView.dequeueReusableCell(withIdentifier: RefundGoodsRemarkTableViewCell.description()) as! RefundGoodsRemarkTableViewCell
            cell.model = self.model
            return cell
            
        }
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.row == 0 {
            return 367
        }else if indexPath.row == 1{
            return 229
        }else{
            if self.model.refund_state == "refuse" || self.model.refund_state == "end"{
                return 294
            }else{
                return 142
            }
            
        }
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if self.model.refund_type == "with_goods" {
            return 3
        }else{
            return 2
        }
        
    }
    
    @IBAction func bottomBtnAction(_ sender: UIButton) {
        if sender == self.rightBtn{
            if self.model.refund_type == "with_goods"{
                if self.model.refund_state == "wait_review"{
                    print("取消退货")
                    cancel()
                }else if self.model.refund_state == "accept"{
                    print("填写退货物流")
                    let vc = RefundGoodsLogisticsViewController()
                    vc.refundID = self.model.refund_id
                    vc.submitSuccess = { [unowned self] in
                        self.requestRefundGoodsInfo()
                    }
                    self.navigationController?.pushViewController(vc, animated: true)
                }
            }else{
                if self.model.refund_state == "wait_review"{
                    cancel()
                }
                
            }
        }else{
            
            print("联系商家")
            connectMerchant()
        }
    }
    func connectMerchant() {
        
        callCustomerServices(atView: self.view, phoneNum: (self.model.goodsBean.merchantsBean?.company_mobile)!)
    }
    func cancel(){
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?cancleRefundOrder", at: self, params: ["refund_id":self.model.refund_id!],  success: { (response) in
            ProgressHUD.showSuccess(message: "操作成功")
            NotificationCenter.default.post(name: NSNotification.Name(rawValue:ReloadRefundGoodsListNotification), object: nil)
            self.navigationController?.popViewController(animated: true)
        }) { 
            
        }
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
