//
//  InformationReportViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/20.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class InformationReportViewController: UIViewController,BBSAddPhotoViewDelegate {

    
    @IBOutlet weak var name: UITextField!
   
    
    @IBOutlet weak var phone: UITextField!
    
    @IBOutlet weak var detailAddress: UITextField!
    
    @IBOutlet weak var photoView: UIView!
    
    @IBOutlet weak var confirmBtn: UIButton!
    
    @IBOutlet weak var limitNum: UILabel!
    
    @IBOutlet weak var addressBtn: UIButton!
    var province = ""
    var city = ""
    var country = ""
    
    var imageStrArr : [String?] = []
    var addPhotoView: BBSAddPhotoView!
    
    deinit {
        NotificationCenter.default.removeObserver(self)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        NotificationCenter.default.addObserver(self, selector: #selector(InformationReportViewController.paySuccess), name: NSNotification.Name(rawValue: PaySuccessNotification), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(InformationReportViewController.payFail), name: NSNotification.Name(rawValue: PayFailNotification), object: nil)
        
        
        self.title = "信息报备"
        self.confirmBtn.layer.cornerRadius = kCornerRadius
        self.confirmBtn.layer.masksToBounds = true
        
        addPhotoView = BBSAddPhotoView()
        addPhotoView.myDelegate = self
        addPhotoView.imageReduceBlock = { imageArray in
            if imageArray.count == 0{
                self.imageStrArr.removeAll()
                self.addPhotoView.updateLayout()
            }else{
                NetworkingHandle.uploadOneMorePicture(url: "memberInterfaces.api?uploadReportedImg", atVC: self, images: imageArray, uploadSuccess: { (response) in
                    
                    self.imageStrArr = response["data"] as! [String]
                    self.addPhotoView.updateLayout()
                })
            }
           
        }
        self.photoView.addSubview(addPhotoView)
        
        addPhotoView.snp.makeConstraints { (make) in
            make.left.equalTo(16)
            make.width.equalTo(kScreenWidth - 32)
            make.bottom.equalTo(self.limitNum.snp.top).offset(-20)
            make.height.equalTo(81)
        }
        // Do any additional setup after loading the view.
    }
    func paySuccess() {
        self.getUserInfo()
        
    }
    func payFail(){
        ProgressHUD.showMessage(message: "支付失败")
    }
    func getUserInfo() {
        
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getMemberDetail", at: self,isAuthHide: true, isShowHUD: true, success: { (response) in
            let data = response["data"] as! NSDictionary
            LHWEUserInfoHandler.saveUserInfo(model: LHWEUserInfoModel.deserialize(from: data)!)
            ProgressHUD.showMessage(message: "支付成功")
        })
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func submitAction(_ sender: UIButton) {
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
            self.present(vc, animated: true, completion: nil)
            return
        }
        if (name.text?.isEmpty)!{
            ProgressHUD.showMessage(message: " 请输入姓名")
            return
        }
        if !(self.phone.text?.isPhoneNumber)! && !((self.phone.text?.isTelephone)!) {
            
            ProgressHUD.showMessage(message: "(手机号/电话)格式不正确")
            return
        }
        if (detailAddress.text?.isEmpty)!{
            ProgressHUD.showMessage(message: "详细地址不能为空")
            return
        }
        if self.imageStrArr.count == 0 {
            ProgressHUD.showMessage(message: "请上传报备图片")
            return
        }
        let vc = UIAlertController.init(title: "提示", message: "是否确定报备此条信息？", preferredStyle: .alert)
        let okAction = UIAlertAction.init(title: "是", style: .default) { (alert) in
            var params : Dictionary<String, String> = [:]
            params = ["reported_name":self.name.text!,"reported_phone":self.phone.text!, "province": self.province, "city": self.city, "district": self.country, "detail":self.detailAddress.text!]
            if self.imageStrArr.count != 0{
                for (index, str) in self.imageStrArr.enumerated() {
                    params["reported_img" + "\(index + 1)"] = str
                }
            }
            NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?addReported", at: self, params: params, success: { (response) in
                ProgressHUD.showSuccess(message: "提交成功")
                self.navigationController?.popViewController(animated: true)
            }) {
                
            }
        }
        let cancelAction = UIAlertAction.init(title: "否", style: .cancel, handler: nil)
        vc.addAction(okAction)
        vc.addAction(cancelAction)
        self.present(vc, animated: true, completion: nil)
        
        
    }
    
    @IBAction func remarksAction(_ sender: UIButton) {
        print("报备相关")
        let vc = CommanWebViewController()
        vc.url = NetworkingHandle.mainHost + ReportURL
        vc.title = "报备相关"
        self.navigationController?.pushViewController(vc, animated: true)
    }
    
    @IBAction func selectAddressAction(_ sender: UIButton) {
        self.view.endEditing(true)
        let v = ProvinceCityDistrictChoice(frame: self.view.bounds)
        v.show(choiced: { [unowned self] (p, c, d) in
            self.province = p
            self.city = c
            self.country = d
            
            self.addressBtn.setTitleColor(UIColor.init(hexString: "808080"), for: .normal)
            if p == c{
                self.addressBtn.setTitle(p + "-" + d, for: .normal)
            }else{
                self.addressBtn.setTitle(p + "-" + c + "-" + d, for: .normal)
            }
        })
        self.view.addSubview(v)
    }
    func payDeposit(type: String) {
        
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?payDeposit", at: self, params: ["channel":type], isAuthHide: false, isShowHUD: true, isShowError: true, hasHeaderRefresh: nil, success: { (response) in
            ProgressHUD.hideLoading(toView: (self.view)!)
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
    
    //MARK: - delegate
    
    func bbsAddPhotoView(addPhotoView: BBSAddPhotoView, clickButton index:Int)
    {
        if index == 100 {
            
            let actionSheet = ZLPhotoActionSheet()
            actionSheet.maxSelectCount = 3 - addPhotoView.imagesData.count
            
            actionSheet.showPhotoLibrary(withSender: self, last: [], completion: {(selectPhotos, selectPhotoModels) in
                self.addPhotoView.imagesData = self.addPhotoView.imagesData + selectPhotos
                NetworkingHandle.uploadOneMorePicture(url: "memberInterfaces.api?uploadReportedImg", atVC: self, images: self.addPhotoView.imagesData, uploadSuccess: { (response) in
                    self.imageStrArr = (response["data"] as? [String])!
                    self.addPhotoView.updateLayout()
                })
               
            })
            
            
            
        }
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
