//
//  ForgetPasswordViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/22.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class ForgetPasswordViewController: UIViewController {

    @IBOutlet weak var account: UILabel!
    
    @IBOutlet weak var old: UITextField!
    
    @IBOutlet weak var new: UITextField!
    
    @IBOutlet weak var onceMore: UITextField!
    
    @IBOutlet weak var code: UITextField!
    
    @IBOutlet weak var codeBtn: UIButton!
    
    @IBOutlet weak var changeBtn: UIButton!
    
    @IBOutlet weak var titleLab: UILabel!
    
    var isFromLogin = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        if isFromLogin {
            self.titleLab.isHidden = true
            self.account.isHidden = true
            self.old.placeholder = "请输入您的手机号"
            self.changeBtn.setTitle("确定", for: .normal)
        }else{
           self.account.text = getMobileNumber(account: (LHWEUserInfoHandler.getUserInfo()?.member_phone!)!) 
        }
        
        
        
        self.codeBtn.layer.cornerRadius = kCornerRadius
        self.codeBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
        self.codeBtn.layer.borderWidth = 0.5
        
        self.changeBtn.layer.cornerRadius = kCornerRadius
        self.changeBtn.layer.masksToBounds = true
        
        self.old.layer.cornerRadius = kCornerRadius
        self.old.layer.masksToBounds = true
        
        self.new.layer.cornerRadius = kCornerRadius
        self.new.layer.masksToBounds = true
        
        self.onceMore.layer.cornerRadius = kCornerRadius
        self.onceMore.layer.masksToBounds = true
        
        let oldLeft = UIView.init(frame: CGRect(x: 0, y: 0, width: 16, height: 57))
        self.old.leftView = oldLeft
        self.old.leftViewMode = .always
        
        let newLeft = UIView.init(frame: CGRect(x: 0, y: 0, width: 16, height: 57))
        self.new.leftView = newLeft
        self.new.leftViewMode = .always
        
        let onceMoreLeft = UIView.init(frame: CGRect(x: 0, y: 0, width: 16, height: 57))
        self.onceMore.leftView = onceMoreLeft
        self.onceMore.leftViewMode = .always
        
        
        // Do any additional setup after loading the view.
    }
    func getMobileNumber(account: String) -> String{
        if account.isPhoneNumber{
            return (account as NSString).substring(to: 3) + "***" + (account as NSString).substring(from: account.characters.count - 4)
        }
        return "18812345678"
    }
    @IBAction func getCodeAction(_ sender: UIButton) {
        UIApplication.shared.keyWindow?.endEditing(true)
        if isFromLogin {
            if !(self.old.text?.isPhoneNumber)!{
                ProgressHUD.showMessage(message: "手机号格式不正确")
                return
            }
        }
        let timer = fetchVerificationCodeCountdown(button: codeBtn, timeOut: 60)
        
        var type = "update_password"
        if isFromLogin{
            type = "forget_passwrod"
        }
        NetworkingHandle.fetchNetworkData(url: "othersInterfaces.api?sendCode", at: self, params: ["mobile": LHWEUserInfoHandler.getUserInfo()?.member_phone! ?? "", "code_type": type], success: { (result) in
             ProgressHUD.showSuccess(message: "发送成功")
            
        }, failure: { [unowned self] errorCode in
            
            timer.cancel()
            self.codeBtn.setTitle("发送验证码", for: .normal)
            self.codeBtn.isEnabled = true
        })

    }
    
    @IBAction func chagePasswordAction(_ sender: UIButton) {
        
        if isFromLogin{
            if !(self.old.text?.isPhoneNumber)! {
                ProgressHUD.showMessage(message: "手机号格式不正确")
                return
            }
            
        }else{
            if (self.old.text?.isEmpty)!{
                ProgressHUD.showMessage(message: "请输入原来的密码")
                return
            }
        }
        
        
        if (self.new.text?.isEmpty)! || (self.onceMore.text?.isEmpty)!{
            ProgressHUD.showMessage(message: "新密码需要输入两次")
            return
        }
        if self.new.text != self.onceMore.text{
            ProgressHUD.showMessage(message: "两次输入的密码不一致")
            return
        }
        if self.code.text == nil{
            ProgressHUD.showMessage(message: "验证码不能为空")
            return
        }
        if isFromLogin {
            let params : Dictionary<String,String> = ["member_account":self.old.text!,"member_password":self.new.text!,"code":self.code.text!]
            NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?memberForgetPassword", at: self, params: params, isAuthHide: true, isShowHUD: true, isShowError: true, hasHeaderRefresh: nil, success: { (response) in
                ProgressHUD.showSuccess(message: "设置成功")
                UIApplication.shared.keyWindow?.rootViewController = BaseTabBarController()
            }, failure: { 
                
            })
        }else{
            let params : Dictionary<String,String> = ["member_account":(LHWEUserInfoHandler.getUserInfo()?.member_phone)!,"member_password":self.new.text!, "member_old_password":self.old.text!,"code":self.code.text!]
            NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?memberUpdatePassword", at: self, params: params,  success: { (response) in
                ProgressHUD.showSuccess(message: "修改成功")
                self.navigationController?.popViewController(animated: true)
                
            }) {
                ProgressHUD.showMessage(message: "请重新修改")
                self.old.text = ""
                self.new.text = ""
                self.onceMore.text = ""
                self.code.text = ""
            }
        }
        
        
    }
    override func viewWillAppear(_ animated: Bool) {
        
        if isFromLogin {
           setStatusBarBGColor(color: themeColor)
            self.navigationController?.setNavigationBarHidden(false, animated: false)
        }
        
    }
    override func viewWillDisappear(_ animated: Bool) {
        if isFromLogin {
           setStatusBarBGColor(color: .white)
            self.navigationController?.setNavigationBarHidden(true, animated: true)
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
