//
//  SetPayPasswordViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/6.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

import IQKeyboardManagerSwift

class SetPayPasswordViewController: UIViewController {

    @IBOutlet weak var phone: UILabel!
    
    @IBOutlet weak var pwdTF1: UITextField!
    
    @IBOutlet weak var pwdTF2: UITextField!
    
    @IBOutlet weak var codeTF: UITextField!
    
    @IBOutlet weak var getCodeBtn: UIButton!
    
    @IBOutlet weak var confirmBtn: UIButton!
    
    
//    var tf1 : JJCPayCodeTextField!
//    var tf2 : JJCPayCodeTextField!
//    var tf1Password : String!
//    var tf2Password : String!
//    
//    var tips1 = UILabel()
//    var tips2 = UILabel()
//    
//    var confirmBtn = UIButton.init(type: .custom)
    var leftView1 = UIView()
    var leftView2 = UIView()
    var setSuccess : (()->())?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = "设置支付密码"
//        self.automaticallyAdjustsScrollViewInsets = false
        
        IQKeyboardManager.sharedManager().enable = true
        IQKeyboardManager.sharedManager().enableAutoToolbar = true
        
        
//        self.tips1.frame = CGRect(x: 50, y: 70, width: kScreenWidth - 100, height: 30)
//        self.tips1.text = "请输入支付密码"
//        self.tips1.font = UIFont.systemFont(ofSize: 14)
//        self.tips1.textColor = UIColor.init(hexString: "#242424")
//        self.tips1.textAlignment = .center
//        self.view.addSubview(self.tips1)
//        
//        
//        tf1 = JJCPayCodeTextField(frame: CGRect.init(x: 50, y: 100, width: (kScreenWidth - 100), height: (kScreenWidth - 100)/6.0), textFieldType: .wholeBorder)
//        tf1.borderColor = themeColor
//        
//        tf1.finishedBlock = { payCode in
//            self.tf1Password = payCode
//        }
//        tf1.ciphertext = "●";
//        self.view.addSubview(tf1)
//        
//        
//        self.tips2.frame = CGRect(x: 50, y: tf1.frame.origin.y + tf1.frame.size.height + 30, width: kScreenWidth - 100, height: 30)
//        self.tips2.text = "请确认支付密码"
//        self.tips2.font = UIFont.systemFont(ofSize: 14)
//        self.tips2.textColor = UIColor.init(hexString: "#242424")
//        self.tips2.textAlignment = .center
//        self.view.addSubview(self.tips2)
//        
//        tf2 = JJCPayCodeTextField(frame: CGRect.init(x: 50, y: tf1.frame.origin.y + tf1.frame.size.height + 60, width: (kScreenWidth - 100), height: (kScreenWidth - 100)/6.0), textFieldType: .wholeBorder)
//        tf2.borderColor = themeColor
//        tf2.finishedBlock = { payCode in
//            self.tf2Password = payCode
//        }
//        tf2.ciphertext = "●";
//        self.view.addSubview(tf2)
//        
//        self.confirmBtn.setTitle("确定", for: .normal)
//        self.confirmBtn.frame = CGRect(x: 50, y: kScreenHeight - 80 - 64, width: kScreenWidth - 100, height: 50)
        
        self.pwdTF1.layer.cornerRadius = kCornerRadius
        self.pwdTF1.layer.masksToBounds = true
        
        self.pwdTF2.layer.cornerRadius = kCornerRadius
        self.pwdTF2.layer.masksToBounds = true
        
        self.phone.text = addSymbolToText()
        
        self.confirmBtn.layer.cornerRadius = kCornerRadius
        self.confirmBtn.layer.masksToBounds = true
        self.confirmBtn.backgroundColor = themeColor
        self.getCodeBtn.layer.cornerRadius = kCornerRadius
        self.getCodeBtn.layer.borderWidth = 1.0
        self.getCodeBtn.layer.masksToBounds = true
        self.getCodeBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
        
        self.leftView1.backgroundColor = UIColor.white
        self.leftView1.frame = CGRect.init(x: 0, y: 0, width: 15, height: 55)
        self.pwdTF1.leftView = self.leftView1
        self.pwdTF1.leftViewMode = .always
        
        self.leftView2.backgroundColor = UIColor.white
        self.leftView2.frame = CGRect.init(x: 0, y: 0, width: 15, height: 55)
        self.pwdTF2.leftView = self.leftView2
        self.pwdTF2.leftViewMode = .always
        
        
        // Do any additional setup after loading the view.
    }
    func addSymbolToText() -> String {
        let str : NSString = LHWEUserInfoHandler.getUserInfo()!.member_phone! as NSString
        let result = str.substring(with: NSRange.init(location: 0, length: 3)) + "****" + str.substring(with: NSRange.init(location: str.length - 4, length: 4))
        return result
    }
    @IBAction func getCodeAction(_ sender: UIButton) {
        
        
        UIApplication.shared.keyWindow?.endEditing(true)
        let timer = fetchVerificationCodeCountdown(button: getCodeBtn, timeOut: 60)
        NetworkingHandle.fetchNetworkData(url: "othersInterfaces.api?sendCode", at: self, params: ["mobile": LHWEUserInfoHandler.getUserInfo()!.member_phone!, "code_type": "balance_passwrod"], success: { (result) in
             ProgressHUD.showSuccess(message: "发送成功")
        }, failure: { [unowned self] errorCode in
            timer.cancel()
            self.getCodeBtn.setTitle("发送验证码", for: .normal)
            self.getCodeBtn.isEnabled = true
        })
    }
    
    @IBAction func confirmAction(_ sender: UIButton) {
        
        if (self.codeTF.text?.isEmpty)! {
            ProgressHUD.showMessage(message: "验证码不能为空")
            return
        }
        if (self.pwdTF1.text?.isEmpty)! || (self.pwdTF2.text?.isEmpty)!{
            ProgressHUD.showMessage(message: "密码不能为空")
            return
        }
        if (self.pwdTF1.text?.characters.count)! != 6 || (self.pwdTF2.text?.characters.count)! != 6 {
            ProgressHUD.showMessage(message: "请将密码长度设置为6位")
            return
        }
        if self.pwdTF1.text != self.pwdTF2.text{
            ProgressHUD.showMessage(message: "两次输入密码不一致")
            return
        }
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?setPayPassword", at: self, params: ["code":self.codeTF.text!,"member_phone":LHWEUserInfoHandler.getUserInfo()!.member_phone!,"member_pay_password":self.pwdTF1.text!], success: { (response) in
            ProgressHUD.showSuccess(message: "设置成功")
            self.setSuccess?()
            self.navigationController?.popViewController(animated: true)
        }) { 
            ProgressHUD.showMessage(message: "请重新设置")
        }
        
        
    }
//    func confirmPassword() {
//        
//        if self.tf1Password.isEmpty || self.tf2Password.isEmpty {
//            ProgressHUD.showMessage(message: "密码不能设置为空")
//            return
//        }
//        if self.tf1Password != self.tf2Password{
//            ProgressHUD.showMessage(message: "两次输入的密码不一致")
//            return
//        }
//        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getMemberDetail", at: self, params: ["member_pay_password":self.tf1Password],  success: { (response) in
//            
//             ProgressHUD.showMessage(message: "设置成功")
//             self.navigationController?.popViewController(animated: true)
//            
//        }) { 
//            ProgressHUD.showMessage(message: "请重新设置")
//        }
//        
//    }
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
