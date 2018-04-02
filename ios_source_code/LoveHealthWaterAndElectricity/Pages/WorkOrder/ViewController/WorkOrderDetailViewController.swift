//
//  WorkOrderDetailViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/27.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class WorkOrderDetailViewController: UIViewController {
    
    @IBOutlet weak var orderState: UILabel!
    
    @IBOutlet weak var orderStateView: UIView!
    
    @IBOutlet weak var stateViewHeight: NSLayoutConstraint!
    
    @IBOutlet weak var name: UILabel!
    
    @IBOutlet weak var phone: UILabel!
    
    @IBOutlet weak var address: UILabel!
    
    @IBOutlet weak var content: UILabel!
    
    @IBOutlet weak var photoView: UIView!
    
    @IBOutlet weak var image1: UIImageView!
    
    @IBOutlet weak var image2: UIImageView!
    
    @IBOutlet weak var image3: UIImageView!
    
    @IBOutlet weak var hopeTime: UILabel!
    
    @IBOutlet weak var publishTime: UILabel!
    
    @IBOutlet weak var receivingTime: UILabel!
    
    @IBOutlet weak var visitTime: UILabel!
    
    @IBOutlet weak var submitTime: UILabel!
    
    @IBOutlet weak var auditTime: UILabel!
    
    @IBOutlet weak var serviceContent: UILabel!
    
    @IBOutlet weak var rightBtn: UIButton!
    
    @IBOutlet weak var middleBtn: UIButton!
    
    @IBOutlet weak var leftBtn: UIButton!
    
    @IBOutlet weak var rule1: UILabel!
    
    @IBOutlet weak var rule2: UILabel!
    
    @IBOutlet weak var rule3: UILabel!
    
    @IBOutlet weak var serviceView: UIView!
    
    @IBOutlet weak var serviceTitle: UILabel!
    
    @IBOutlet weak var serviceImage1: UIImageView!
    
    @IBOutlet weak var serviceImage2: UIImageView!
    
    @IBOutlet weak var serviceImage3: UIImageView!
    
    @IBOutlet weak var serviceImage3Height: NSLayoutConstraint!
    
    @IBOutlet weak var serviceImage1Height: NSLayoutConstraint!
    
    @IBOutlet weak var serviceImage2Height: NSLayoutConstraint!
    
    @IBOutlet weak var serviceTopHeight: NSLayoutConstraint!
    @IBOutlet weak var serviceImageTopHeight: NSLayoutConstraint!
    @IBOutlet weak var serviceViewImageBottomMargin: NSLayoutConstraint!
    @IBOutlet weak var serviceViewTopHeight: NSLayoutConstraint!
    @IBOutlet weak var serviceViewBottomHeight: NSLayoutConstraint!
    @IBOutlet weak var checkTitle: UILabel!
    @IBOutlet weak var btnView: UIView!
    @IBOutlet weak var btnViewHeight: NSLayoutConstraint!
    
    var scView : ServicesCategoriesView!
    var orderID : String!
    var model: OrderDetailModel!
    var sqView : SubmitQuestionView!
    var cancelOrderOrNot : (() -> ())?
    /*order_state各种值对应状态：
     用户-预约待审核 0
     用户-预约审核未通过 1
     师傅-待抢单 2
     师傅-已接单（未服务） 3
     师傅-已接单（未服务退单审核） 4
     师傅-已接单（服务中）5
     师傅-已接单（服务中退单审核） 6
     师傅-已接单（完工待审核）7
     师傅-已完成（待评价）8 9
     用户-已发布（超时待派单） 10
     师傅-待接单（派单待接单） 11
     师傅-服务中（已完工） 12
     用户-已发布 2 10 11
     用户-待服务 3 4 5 6 7 12
     用户-已完成（未评价） 8
     用户-已完成（未评价） 9
     */
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        
        self.title = "详情"
        
        
        
        NotificationCenter.default.addObserver(self, selector: #selector(WorkOrderDetailViewController.showServiceCategoriesView), name: NSNotification.Name.init(rawValue: ShowServicesCategoriesNotification), object: nil)
        
        self.leftBtn.layer.cornerRadius = 16
        self.leftBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
        self.leftBtn.layer.borderWidth = 1
        
        self.middleBtn.layer.cornerRadius = 16
        self.middleBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
        self.middleBtn.layer.borderWidth = 1
        
        self.rightBtn.layer.cornerRadius = 16
        self.rightBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
        self.rightBtn.layer.borderWidth = 1
        
        self.name.text = "姓名：" + self.model.order_name!
        
        self.phone.text = self.model.order_phone!
        self.phone.addGestureRecognizer(UITapGestureRecognizer.init(target: self, action: #selector(WorkOrderDetailViewController.callUserPhone)))
        
        self.address.text = self.model.order_address_province! + self.model.order_address_city! + self.model.order_address_district! + self.model.order_address_detail!
        self.serviceContent.text = "服务内容：" + self.model.order_subscribe_content!
        self.hopeTime.text = "期望上门服务时间：" + self.model.order_hope_service_time!
        self.publishTime.text = "发布时间：" + self.model.order_create_time!
        self.receivingTime.text = "接单时间：" + self.model.order_accept_time!
        
        if self.model.order_subscribe_img1 != "" {
            image1.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + self.model.order_subscribe_img1!)!, placeholder: #imageLiteral(resourceName: "upload"), options: nil, progressBlock: nil, completionHandler: nil)
        }
        if self.model.order_subscribe_img2 != ""{
            image1.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + self.model.order_subscribe_img2!)!, placeholder: #imageLiteral(resourceName: "upload"), options: nil, progressBlock: nil, completionHandler: nil)        }
        if self.model.order_subscribe_img3 != ""{
            image1.kf.setImage(with: URL.init(string:NetworkingHandle.mainHost +  self.model.order_subscribe_img3!)!, placeholder: #imageLiteral(resourceName: "upload"), options: nil, progressBlock: nil, completionHandler: nil)
        }
        
        self.checkTitle.isHidden = true
        self.visitTime.isHidden = true
        self.submitTime.isHidden = true
        self.auditTime.isHidden = true
        
//        if self.model.order_state == "0" || self.model.order_state == "1"{
//            self.orderStateView.backgroundColor = UIColor.init(hexString: "6EC75F")
//            self.orderState.text = "已退单"
//            self.hiddenRules()
//            self.serviceTitle.text = "退单原因"
//            self.serviceContent.text = self.model.order_cancle_why
//            hideServiceControls(isHiddenAll: false)
//            if self.model.order_state == "0"{
//                self.leftBtn.isHidden = true
//                self.middleBtn.isHidden = true
//                self.rightBtn.isHidden = true
//                self.btnView.isHidden = true
//                self.btnViewHeight.constant = 0
//
//            }else if self.model.order_state == "1"{
//                self.visitTime.isHidden = false
//                self.visitTime.text = self.model.order_service_time
//                self.leftBtn.isHidden = true
//                self.middleBtn.isHidden = true
//                self.rightBtn.setTitle("联系客服", for: .normal)
//            }
//
//        }
        
        if self.model.order_state == "3" || self.model.order_state == "4"{
            
            self.showRules()
            
            if self.model.is_today_order == "1" {
        
                self.orderState.text = "今日服务"
                self.orderStateView.backgroundColor = UIColor.init(hexString: "59B1E9")
                self.leftBtn.setTitle("去服务", for: .normal)
                self.serviceView.isHidden = true
                hideServiceControls(isHiddenAll: true)
                
            }
            if self.model.order_is_cancle == "1"{
                
                self.orderState.text = "未服务退单中"
                self.orderStateView.backgroundColor = UIColor.red
                self.serviceTitle.text = "退单原因"
                self.serviceContent.text = self.model.order_cancle_why
                self.rightBtn.setTitle("取消退单", for:.normal)
                hideServiceControls(isHiddenAll: false)
                
            }
                self.orderState.text = "未服务"
                self.orderStateView.backgroundColor = UIColor.init(hexString: "59B1E9")
                self.serviceView.isHidden = true
                self.leftBtn.setTitle("去服务", for: .normal)
                hideServiceControls(isHiddenAll: true)
            
        }else if self.model.order_state == "5" || self.model.order_state == "6"{
            
            print("服务中")
            
            if self.model.order_state == "5" {
                self.showRules()
                self.orderState.text = "服务中"
                self.orderStateView.backgroundColor = UIColor.init(hexString: "59B1E9")
                self.serviceView.isHidden = true
                hideServiceControls(isHiddenAll: true)
                
            }else{
                
                self.visitTime.isHidden = false
                self.visitTime.text = "上门时间：" + self.model.order_service_time!
                self.orderState.text = "服务中退单审核"
                self.orderStateView.backgroundColor = UIColor.init(hexString: "EE784E")
                self.serviceTitle.text = "退单原因"
                self.serviceContent.text = self.model.order_cancle_why
                self.rightBtn.setTitle("联系客服", for: .normal)
                self.middleBtn.setTitle("取消退单", for: .normal)
                self.leftBtn.isHidden = true
                self.checkTitle.isHidden = false
                hideServiceControls(isHiddenAll: false)
                self.hiddenRules()

                
            }
        }else if self.model.order_state == "7"{
            
            print("待审核")
            self.visitTime.isHidden = false
            self.submitTime.isHidden = false
            self.visitTime.text = "上门时间：" + self.model.order_service_time!
            self.submitTime.text = "提交时间：" + self.model.order_complete_time!
            self.orderStateView.backgroundColor = UIColor.init(hexString: "EE784E")
            self.orderState.text = "完工待审核"
            self.serviceTitle.text = "实际服务内容"
            self.serviceContent.text = self.model.order_reality_content!
            self.setServiceImage()
            self.checkTitle.isHidden = false
            self.rightBtn.setTitle("联系客服", for: .normal)
            self.hiddenRules()
            
            self.leftBtn.isHidden = true
            self.middleBtn.isHidden = true
        }else if self.model.order_state == "8" || self.model.order_state == "9"{
            
            self.visitTime.isHidden = false
            self.submitTime.isHidden = false
            self.auditTime.isHidden = false
            self.visitTime.text = "上门时间：" + self.model.order_service_time!
            self.submitTime.text = "提交时间：" + self.model.order_complete_time!
            self.auditTime.text = "审核完成：" + self.model.order_complete_pass_time!
            self.orderState.text = "已完成"
            self.orderStateView.backgroundColor = UIColor.init(hexString: "6EC75F")
            self.serviceTitle.text = "实际服务内容"
            self.serviceContent.text = self.model.order_reality_content!
            self.setServiceImage()
            
            self.rightBtn.setTitle("联系客服", for: .normal)
            self.middleBtn.isHidden = true
            self.leftBtn.isHidden = true
            self.hiddenRules()
            
            print("已完成")
            
        }else if self.model.order_state == "12"{ //师傅-服务中（已完工）
            self.orderState.text = "师傅已完工"
            self.orderStateView.backgroundColor = UIColor.init(hexString: "EE784E")
            self.serviceTitle.text = "实际服务内容"
            self.serviceContent.text = self.model.order_reality_content!
            self.visitTime.isHidden = false
            self.submitTime.isHidden = false
            self.visitTime.text = "上门时间：" + self.model.order_service_time!
            self.submitTime.text = "提交时间：" + self.model.order_complete_time!
            self.checkTitle.isHidden = true
            self.rightBtn.setTitle("联系客服", for: .normal)
            self.leftBtn.isHidden = true
            self.middleBtn.isHidden = true
            self.hiddenRules()
            self.setServiceImage()
        }else if self.model.order_state == "13" || self.model.order_state == "14"{
            self.orderStateView.backgroundColor = UIColor.init(hexString: "6EC75F")
            
            self.hiddenRules()
            self.serviceTitle.text = "未服务退单"
            self.serviceContent.text = self.model.order_cancle_why
            if self.model.order_state == "13"{
                self.leftBtn.isHidden = true
                self.middleBtn.isHidden = true
                self.rightBtn.isHidden = true
                self.btnView.isHidden = true
                self.btnViewHeight.constant = 0
            }else{
                self.orderState.text = "服务中退单"
                self.visitTime.isHidden = false
                self.visitTime.text = "上门时间：" + self.model.order_service_time!
                self.leftBtn.isHidden = true
                self.middleBtn.isHidden = true
                self.rightBtn.setTitle("联系客服", for: .normal)
            }
        }
        
        // Do any additional setup after loading the view.
    }
    func hiddenRules(){
        self.rule1.isHidden = true
        self.rule2.isHidden = true
        self.rule3.isHidden = true
    }
    func showRules(){
        self.rule1.text = " 注：1.每个月最多可退非正常单3次"
        self.rule2.text = "2.当月满三次非正常退单，系统将停止派单"
        self.rule3.text = "3.正常原因退单，则无此限制"
    }
    func callUserPhone() {
        callCustomerServices(atView: self.view, phoneNum: self.model.order_phone!)
    }
    func setServiceImage(){
        if self.model.order_complete_img1 != "" {
            self.serviceImage1.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + self.model.order_complete_img1!), placeholder: #imageLiteral(resourceName: "upload"), options: nil, progressBlock: nil, completionHandler: nil)
        }
        
        if self.model.order_complete_img2 != "" {
            self.serviceImage2.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + self.model.order_complete_img2!), placeholder: #imageLiteral(resourceName: "upload"), options: nil, progressBlock: nil, completionHandler: nil)
        
        }
        if self.model.order_complete_img3 != "" {
            self.serviceImage3.kf.setImage(with: URL.init(string:NetworkingHandle.mainHost + self.model.order_complete_img3!), placeholder: #imageLiteral(resourceName: "upload"), options: nil, progressBlock: nil, completionHandler: nil)
        
        }
    }
       
    func hideServiceControls(isHiddenAll:Bool) {
        
        self.serviceImage1.isHidden = true
        self.serviceImage2.isHidden = true
        self.serviceImage3.isHidden = true
        
        self.serviceImage1Height.constant = 0
        self.serviceImage2Height.constant = 0
        self.serviceImage3Height.constant = 0
        self.serviceImageTopHeight.constant = 0
        //1 实际服务内容隐藏全部 2 退单原因隐藏图片
        if isHiddenAll {
            self.serviceTopHeight.constant = 0
            self.serviceViewImageBottomMargin.constant = -25
            self.serviceViewBottomHeight.constant = 0
        }
    }
    func showServiceCategoriesView() {
        
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?getServiceClasss", at: self, params: ["class_parent_id":"0"], isAuthHide: false, isShowHUD: false, isShowError: true, success: { (response) in
            let data = response["data"] as! NSArray
            self.scView = ServicesCategoriesView.show(atView: self.view, arr: [ServicesCategoriesModel].deserialize(from: data)! as! [ServicesCategoriesModel])
            self.scView.finishSelect = { (strService,price) in
                _ = SubmitServicesCategoriesView.show(atView: self.view, serviceStr: strService, servicePrice: price, order_id:self.model.order_id!)
            }
            
        }, failure: {
            
        })
    }
    
    
    @IBAction func getLocationAction(_ sender: UIButton) {
        print("~~~~~~~~~~~~~~")
        let vc = ShowLocationViewController()
        vc.city = self.model.order_address_city
        vc.dis = self.model.order_address_district
        vc.detailAddress = self.model.order_address_detail
        vc.log = self.model.order_address_longitude
        vc.lat = self.model.order_address_latitude
        self.navigationController?.pushViewController(vc, animated: true)
    }
    
    @IBAction func userOperateAction(_ sender: UIButton) {
        if sender.tag == 0 {
            
            
            if self.model.order_state == "3" {
                if self.model.is_today_order == "1" {
                    print("去服务")
                    self.receiveOrder()
                }
            }else if self.model.order_state == "5"{
                
                print("服务中")
                self.showServiceCategoriesView()
            }
            
            
        }else if sender.tag == 1{
            
            
            if model.order_state == "3"{
                if self.model.order_is_cancle == "0"{
                    showSQView()
                }
            } else if self.model.order_state == "5"{
                
                print("服务中")
                print("申请退单")
                showSQView()
            }else if self.model.order_state == "6"{
                print("取消退单")
                cancelChargeback()
            }
        }else{
            if self.model.order_state == "1" {
                callCustomerServices(atView: self.view, phoneNum: CustomerServicesPhone)
            }else if self.model.order_state == "3" || self.model.order_state == "4"{
                
                if self.model.order_state == "3"{
                    print("联系业主")
                    callCustomerServices(atView: self.view, phoneNum: self.model.order_phone!)
                }else{
                    print("取消退单")
                    cancelChargeback()
                }
            }else if self.model.order_state == "5" || self.model.order_state == "6"{
                
                print("服务中")
                if self.model.order_state == "5" {
                    print("联系业主")
                    
                    callCustomerServices(atView: self.view, phoneNum: self.model.order_phone!)
                }else{
                    
                    print("联系客服")
                    callCustomerServices(atView: self.view, phoneNum: CustomerServicesPhone)
                }
            }else if self.model.order_state == "7"{
                
                print("完工待审核")
                print("联系客服")
                callCustomerServices(atView: self.view, phoneNum: CustomerServicesPhone)
                
            }else if self.model.order_state == "8" || self.model.order_state == "9"{
                
                print("已完成")
                
                print("联系客服")
                callCustomerServices(atView: self.view, phoneNum: CustomerServicesPhone)
                
            }else if self.model.order_state == "12"{
                print("联系客服")
                callCustomerServices(atView: self.view, phoneNum: CustomerServicesPhone)
            }else if self.model.order_state == "14"{
                print("联系客服")
                callCustomerServices(atView: self.view, phoneNum: CustomerServicesPhone)
            }
        }
    }
    func cancelChargeback() {
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?cancleCancleOrder", at: self, params: ["order_id": self.model.order_id!], isAuthHide: true, isShowHUD: true, isShowError: true, hasHeaderRefresh: nil, success: { (response) in
            self.cancelOrderOrNot?()
            NotificationCenter.default.post(name: NSNotification.Name(rawValue:ReloadAcceptWorkOrderNotification), object: nil)
            _ = self.navigationController?.popViewController(animated: true)
        }) { 
            ProgressHUD.showMessage(message: "操作失败")
        }
    }
    func receiveOrder() {
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?updateOrderState", at: self, params: ["type":"worker_service","order_id":self.model.order_id!], success: { (response) in
            ProgressHUD.showMessage(message: "状态已更新")
            self.cancelOrderOrNot?()
            NotificationCenter.default.post(name: NSNotification.Name(rawValue:ReloadAcceptWorkOrderNotification), object: nil)
            self.navigationController?.popViewController(animated: true)
        }) { 
            
        }
    }
    func showSQView() {
        
        sqView = SubmitQuestionView.show(atView:self.view)
        sqView.submitQuestion = { info in
            NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?cancleOrder", at: self, params: ["order_id":self.model.order_id!,"order_cancle_why": info], success: { (response) in
                
                ProgressHUD.showMessage(message: "提交成功")
                self.cancelOrderOrNot?()
                NotificationCenter.default.post(name: NSNotification.Name(rawValue:ReloadAcceptWorkOrderNotification), object: nil)
                self.navigationController?.popViewController(animated: true)
                
            }, failure: { 
                
            })
        }
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setKeyBoardManagerIsEnable()
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        setKeyBoardManagerDisable()
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    deinit {
        NotificationCenter.default.removeObserver(self)
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
