//
//  AddressEditView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit


class AddressEditView: UIView {

    @IBOutlet weak var bgBtn: UIButton!
    @IBOutlet weak var backView: UIView!
    
    @IBOutlet weak var nameTF: UITextField!
    
    @IBOutlet weak var phoneTF: UITextField!
    
    @IBOutlet weak var addressBtn: UIButton!
    
    @IBOutlet weak var detailAddressTF: UITextField!
    
    @IBOutlet weak var postCodeTF: UITextField!
    
    var province: String!
    var city : String!
    var district : String!
    var addressID : String!
    var selectAddress : (()->())?
    var updateAddress : (()->())?
    
    var isEdit = false
    var isServiceAddress = false
    var model : AddressModel!
    class func show(atView:UIView, addressModel: AddressModel, isEdit : Bool!) -> AddressEditView{
        let view = Bundle.main.loadNibNamed("AddressEditView", owner: nil, options: nil)?.first as! AddressEditView
        if addressModel.address_id != nil {
            view.model = addressModel
            view.nameTF.text = addressModel.name
            view.phoneTF.text = addressModel.mobile
            view.addressBtn.setTitle(addressModel.province! + addressModel.city! + addressModel.district!, for: .normal)
            view.detailAddressTF.text = addressModel.detailed_address!
            view.postCodeTF.text = addressModel.zip_code
            
        }
        view.isEdit = isEdit
        atView.addSubview(view)
        return view
    }
    private func showView(){
        var frame = self.backView.frame
        let old = frame
        self.alpha = 0.1
        frame.origin.y = self.frame.size.height
        self.backView.frame = frame
        UIView.animateKeyframes(withDuration: 0.25, delay: 0.1, options: .calculationModePaced, animations: {
            self.backView.frame = old
            self.bgBtn.alpha = 0.2
            self.alpha = 1
        }, completion: nil)
    }
    func remove(){
        var frame = self.backView.frame
        frame.origin.y += self.frame.size.height
        UIView.animate(withDuration: 0.25, animations: {
            self.backView.frame = frame
            self.backView.alpha = 0.1
            
        }) { (finish) in
            self.removeFromSuperview()
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
       
        self.frame = CGRect(x: 0, y: -64, width: kScreenWidth, height: kScreenHeight)
        self.bgBtn.backgroundColor = UIColor(hexString: "#333333")
        self.bgBtn.alpha = 0.2
    }
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    
    @IBAction func selectAddressAction(_ sender: UIButton) {
        self.endEditing(true)
        selectAddress?()
        
    }
    
    @IBAction func quitAction(_ sender: UIButton) {
        self.remove()
    }
    
    @IBAction func confirmAction(_ sender: UIButton) {
        if (self.nameTF.text?.isEmpty)!{
            ProgressHUD.showMessage(message: "姓名不能为空")
            return
        }
        if !(self.phoneTF.text?.isPhoneNumber)! && !(self.phoneTF.text?.isTelephone)!{
            ProgressHUD.showMessage(message: "手机号或固定电话格式不正确")
            return
        }
        if self.addressBtn.titleLabel?.text?.characters.count == 0{
            ProgressHUD.showMessage(message: "请选择省市区")
            return
        }
        if (self.detailAddressTF.text?.isEmpty)!{
            ProgressHUD.showMessage(message: "请填写详细地址")
            return
        }
//        if !(self.postCodeTF.text?.isZipcode)!{
//            ProgressHUD.showMessage(message: "邮政编码格式不正确")
//            return
//        }
        if self.postCodeTF.text?.characters.count == 0 {
            self.postCodeTF.text = ""
        }
        
        if isServiceAddress{
            let params : Dictionary<String,String> = ["member_service_phone":self.phoneTF!.text!,"member_service_name":self.nameTF!.text!,"member_service_province":self.province,"member_service_city":self.city,"member_service_detail":self.detailAddressTF.text!,"zip_code":self.postCodeTF.text!,"member_service_district":self.district]

            NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?updateMemberDetail", at: self, params: params, success: { (response) in
                self.updateAddress?()
                NotificationCenter.default.post(name: NSNotification.Name(rawValue:ReloadHomeServiceAddressNotification), object: nil, userInfo: ["address":self.district])
                self.remove()
            }, failure: { 
                ProgressHUD.showMessage(message: "请重新设置")
            })
        }else{
            var params : Dictionary<String,String> = ["mobile":self.phoneTF!.text!,"name":self.nameTF!.text!,"province":self.province,"city":self.city,"detailed_address":self.detailAddressTF.text!,"is_default":"1","zip_code":self.postCodeTF.text!,"district":self.district]
            if isEdit{
                params = ["mobile":self.phoneTF!.text!,"name":self.nameTF!.text!,"province":self.province,"city":self.city,"detailed_address":self.detailAddressTF.text!,"is_default":"1","zip_code":self.postCodeTF.text!,"district":self.district,"address_id":self.model.address_id!]
                
            }
            NetworkingHandle.fetchNetworkData(url: "addressInterfaces.api?insertAddress", at: self, params: params, success: { (response) in
                ProgressHUD.showSuccess(message: "操作成功")
                self.updateAddress?()
                self.remove()
            }) {
                ProgressHUD.showMessage(message: "请重新设置")
            }
        }
    }

//    @IBAction func dismissAction(_ sender: UIButton) {
//        self.remove()
//    }
}
