//
//  CurrentWorkFormTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/18.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class CurrentWorkFormTableViewCell: UITableViewCell {

    @IBOutlet weak var getFormBtn: UIButton!
    
    @IBOutlet weak var title: UILabel!
    
    @IBOutlet weak var beizhu: UILabel!
    
    @IBOutlet weak var address: UILabel!
    
    @IBOutlet weak var time: UILabel!
    
    @IBOutlet weak var bgView: UIView!
    
    @IBOutlet weak var stateView: UIView!
    
    @IBOutlet weak var state: UILabel!
    
    @IBOutlet weak var stateViewHeight: NSLayoutConstraint!
    
    @IBOutlet weak var emptyStateLab: UILabel!
    
    
    @IBOutlet weak var emptyStateImage: UIImageView!
    
    var isOrderEmpty : Bool!{
        willSet{
            if newValue {
                emptyStateImage.isHidden = false
                emptyStateLab.isHidden = false
            }else{
                emptyStateImage.isHidden = true
                emptyStateLab.isHidden = true
            }
            
        }
    }
    
    var successGetOrder:(()->())?
    var checkDetail:( (WorkOrderModel) -> ())?
    var type : String!{
        willSet{
            if newValue == "1"{
                self.getFormBtn.setTitle("接单", for: .normal)
                self.stateView.isHidden = true
                self.stateViewHeight.constant = 0
                self.state.isHidden = true
            }else if newValue == "2"{
                self.getFormBtn.setTitle("接单", for: .normal)
            }else{
                self.getFormBtn.setTitle("查看详情", for: .normal)
            }
        }
    }
    var model: WorkFormModel!{
        willSet(m){
            self.stateView.isHidden = true
            self.stateViewHeight.constant = 0
            self.state.isHidden = true
            
            beizhu.text = m.order_subscribe_note
            title.text = m.order_subscribe_content
            address.text = m.order_address_province! + m.order_address_district! + m.order_address_detail!
            if m.order_update_time?.count != 0 {
                let endDate = stringToDate(dateStr: m.order_update_time!)
                let sumTimeInterval = endDate.timeIntervalSince1970 + 20 * 60
                calculateTime(endDateStr: timeStampToString(timeStamp:String(sumTimeInterval),format:"yyyy-MM-dd HH:mm:ss"), showLabel: time, buttonState: getFormBtn)
            }
            
        }
    }
    var amodel : WorkOrderModel!{
        willSet(m){
            
            
            if m.order_state == "3" || m.order_state == "4"{
                if m.is_today_order == "1"{
                    self.state.text = "今日服务"
                    self.stateView.backgroundColor = UIColor(hexString: "#59B1E9")
                }else if m.order_is_cancle == "1"{
                    self.state.text = "退单中"
                    self.stateView.backgroundColor = UIColor(hexString: "#EE784E")
                }else{
                    self.stateView.isHidden = true
                    self.stateViewHeight.constant = 0
                    self.state.isHidden = true
                }

            }else{
                self.stateView.isHidden = true
                self.stateViewHeight.constant = 0
                self.state.isHidden = true
            }
            
            title.text = m.order_subscribe_content
            beizhu.text = m.order_subscribe_note
            address.text = m.order_address_province! + m.order_address_district! + m.order_address_detail!
            time.isHidden = true
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        
        self.bgView.layer.cornerRadius = kCornerRadius
        self.bgView.layer.masksToBounds = true
        
        self.stateView.layer.cornerRadius = kCornerRadius
        self.stateView.layer.masksToBounds = true
        
        getFormBtn.layer.cornerRadius = 60/2
        getFormBtn.layer.borderColor = UIColor.init(hexString: "#EB6900").cgColor
        getFormBtn.layer.borderWidth = 1
        getFormBtn.layer.masksToBounds = true
    
        // Initialization code
    }

    @IBAction func getOrderAction(_ sender: UIButton) {
        if self.type == "1"{

            if LHWEUserInfoHandler.getUserInfo()?.member_deposit_money == "" || LHWEUserInfoHandler.getUserInfo()?.member_deposit_money?.count == 0{
                let vc = UIAlertController.init(title: "请先缴纳保证金", message: "", preferredStyle: .actionSheet)
                let wxAction = UIAlertAction.init(title: "微信", style: .default, handler: { (alert) in
                    self.payDeposit(type: "wx")
                })
                let sonAction = UIAlertAction.init(title: "支付宝", style: .default, handler: { (alert) in
                    self.payDeposit(type: "alipay")
                })
                let cancelAction = UIAlertAction.init(title: "取消", style: .cancel, handler: nil)
                
                vc.addAction(wxAction)
                vc.addAction(sonAction)
                vc.addAction(cancelAction)
                self.responderViewController()?.present(vc, animated: true, completion: nil)
                return
            }
            let str1 = self.model.order_subscribe_content! + "\n"
            let str2 = "备注：" + self.model.order_subscribe_note! + "\n"
            let str3 = "地址：" + self.address.text! + "\n"
            let str4 = "联系电话：" + self.model.order_phone! + "\n"
            let str5 = "期望上门时间：" + self.model.order_hope_service_time!
           
            
            let vc = UIAlertController.init(title: "确定要接受此单吗？", message: str1 + str2 + str3 + str4 + str5,  preferredStyle: .alert)
            let subView1 = vc.view.subviews[0]
            let subView2 = subView1.subviews[0]
            let subView3 = subView2.subviews[0]
            let subView4 = subView3.subviews[0]
            let subView5 = subView4.subviews[0]
            
           // 取title和message：
            _ = subView5.subviews[0] as! UILabel
            let message = subView5.subviews[1] as! UILabel
            message.textAlignment = .left
            
            let action = UIAlertAction.init(title: "确定", style: .default, handler: { (alert) in
                NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?updateOrderState", at: self.responderViewController()!, params: ["order_id":self.model.order_id!,"type":"worker_accept"], isAuthHide: false, isShowHUD: false, isShowError: false, hasHeaderRefresh:nil, success: { (response) in
                    ProgressHUD.showSuccess(message: "接单成功")
                    self.successGetOrder?()
                }, failure: { 
                    
                })
            })
            let cancel = UIAlertAction.init(title: "取消", style: .cancel, handler: nil)
            vc.addAction(action)
            vc.addAction(cancel)
            self.responderViewController()?.present(vc, animated: true, completion: nil)
        } else if type == "2"{
            if LHWEUserInfoHandler.getUserInfo()?.member_deposit_money == "" || LHWEUserInfoHandler.getUserInfo()?.member_deposit_money?.count == 0{
                let vc = UIAlertController.init(title: "请先缴纳保证金", message: "", preferredStyle: .actionSheet)
                let wxAction = UIAlertAction.init(title: "微信", style: .default, handler: { (alert) in
                    self.payDeposit(type: "wx")
                })
                let sonAction = UIAlertAction.init(title: "支付宝", style: .default, handler: { (alert) in
                    self.payDeposit(type: "alipay")
                })
                let cancelAction = UIAlertAction.init(title: "取消", style: .cancel, handler: nil)
                
                vc.addAction(wxAction)
                vc.addAction(sonAction)
                vc.addAction(cancelAction)
                self.responderViewController()?.present(vc, animated: true, completion: nil)
                return
            }
            let vc = UIAlertController.init(title: "确定要接受此单吗？", message: "",  preferredStyle: .alert)
            let action = UIAlertAction.init(title: "确定", style: .default, handler: { (alert) in
                NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?updateOrderState", at: self.responderViewController()!, params: ["order_id":self.amodel.order_id!,"type":"worker_accept"], isAuthHide: false, isShowHUD: false, isShowError: false, hasHeaderRefresh:nil, success: { (response) in
                    ProgressHUD.showSuccess(message: "接单成功")
                    self.successGetOrder?()
                }, failure: {
                    
                })
            })
            let cancel = UIAlertAction.init(title: "取消", style: .cancel, handler: nil)
            vc.addAction(action)
            vc.addAction(cancel)
            self.responderViewController()?.present(vc, animated: true, completion: nil)
        } else {
            NotificationCenter.default.post(name: NSNotification.Name(rawValue:PushToWorderDetailVC), object: nil, userInfo: ["model":self.amodel])
        }
    }
    func payDeposit(type: String) {
        
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?payDeposit", at: self, params: ["channel":type], isAuthHide: false, isShowHUD: true, isShowError: true, hasHeaderRefresh: nil, success: { (response) in
            ProgressHUD.hideLoading(toView: (self.responderViewController()?.view)!)
            let data = response["data"] as! String
            
            let payDic = getDictionaryFromJSONString(jsonString: data)
            
            if type == "wx"{
                Pingpp.createPayment(payDic, appURLScheme: WXAppID, withCompletion: { (result, error) in
                    if error != nil{
                        print(error?.description ?? "")
                    }else{
                        
                    }
                })
            }else{
                Pingpp.createPayment(payDic, appURLScheme: SonAppID, withCompletion: { (result, error) in
                    if error != nil{
                        print(error?.description ?? "")
                    }else{
                    }
                    
                })
            }
        }) {
            
        }
    }
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
