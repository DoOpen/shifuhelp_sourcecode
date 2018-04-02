//
//  RegisterViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/16.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class RegisterViewController: UIViewController, UINavigationControllerDelegate{

    @IBOutlet weak var phoneTF: UITextField!
    
    @IBOutlet weak var passwordTF: UITextField!
    
    @IBOutlet weak var secondPasswordTF: UITextField!
    
    @IBOutlet weak var codeTF: UITextField!
    
    @IBOutlet weak var codeBtn: UIButton!
    
    @IBOutlet weak var loginBtn: UIButton!
    
    @IBOutlet weak var agreementBtn: UIButton!
    
    @IBOutlet weak var checkBtn: UIButton!
    
    var marginView1 = UIView()
    var marginView2 = UIView()
    var marginView3 = UIView()
    var marginView4 = UIView()
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationController?.delegate = self
        
        
        
        
        
        self.codeBtn.layer.cornerRadius = kCornerRadius
        self.codeBtn.layer.borderColor = themeColor.cgColor
        self.codeBtn.layer.borderWidth = 0.5
        self.codeBtn.layer.masksToBounds = true
        
        self.loginBtn.layer.cornerRadius = kCornerRadius
        self.loginBtn.layer.masksToBounds = true
        
        
        marginView1.frame = CGRect(x: 0, y: 0, width: 15, height: 49)
        marginView2.frame = CGRect(x: 0, y: 0, width: 15, height: 49)
        marginView3.frame = CGRect(x: 0, y: 0, width: 15, height: 49)
        marginView4.frame = CGRect(x: 0, y: 0, width: 15, height: 49)
        
        self.phoneTF.leftViewMode = .always
        self.phoneTF.leftView = marginView1
        self.phoneTF.layer.cornerRadius = kCornerRadius
        self.phoneTF.layer.masksToBounds = true
        let phoneTFRight = UIView.init(frame: CGRect(x: 0, y: 0, width: 40, height: 50))
        let imgRight = UIImageView.init(frame: CGRect.init(x: 5, y: 10, width: 30, height: 30))
        imgRight.image = #imageLiteral(resourceName: "shouji").withRenderingMode(.alwaysOriginal)
        phoneTFRight.addSubview(imgRight)
        self.phoneTF.rightView = phoneTFRight
        self.phoneTF.rightViewMode = .always
        
        
        self.passwordTF.leftViewMode = .always
        self.passwordTF.leftView = marginView2
        self.passwordTF.layer.cornerRadius = kCornerRadius
        self.passwordTF.layer.masksToBounds = true
        self.passwordTF.isSecureTextEntry = true
        let passwordTFRight = UIView.init(frame: CGRect(x: 0, y: 0, width: 40, height: 50))
        let passwordBtnRight = UIButton.init(frame: CGRect.init(x: 5, y: 10, width: 30, height: 30))
        passwordBtnRight.setImage(#imageLiteral(resourceName: "eye"), for: .normal)
        passwordBtnRight.setImage(#imageLiteral(resourceName: "eye_h"), for: .selected)
        passwordBtnRight.tag = 1001
        passwordBtnRight.addTarget(self, action: #selector(RegisterViewController.closeOrOpenSecurity(sender:)), for: .touchUpInside)
        passwordTFRight.addSubview(passwordBtnRight)
        self.passwordTF.rightView = passwordTFRight
        self.passwordTF.rightViewMode = .always
        
        
        self.secondPasswordTF.leftViewMode = .always
        self.secondPasswordTF.leftView = marginView3
        self.secondPasswordTF.layer.cornerRadius = kCornerRadius
        self.secondPasswordTF.layer.masksToBounds = true
        self.secondPasswordTF.isSecureTextEntry = true
        let secondPasswordTFRight = UIView.init(frame: CGRect(x: 0, y: 0, width: 40, height: 50))
        let secondPasswordBtnRight = UIButton.init(frame: CGRect.init(x: 5, y: 10, width: 30, height: 30))
        secondPasswordBtnRight.setImage(#imageLiteral(resourceName: "eye"), for: .normal)
        secondPasswordBtnRight.setImage(#imageLiteral(resourceName: "eye_h"), for: .selected)
        secondPasswordBtnRight.tag = 1002
        secondPasswordBtnRight.addTarget(self, action: #selector(RegisterViewController.closeOrOpenSecurity(sender:)), for: .touchUpInside)
        secondPasswordTFRight.addSubview(secondPasswordBtnRight)
        self.secondPasswordTF.rightView = secondPasswordTFRight
        self.secondPasswordTF.rightViewMode = .always
        
        self.codeTF.leftViewMode = .always
        self.codeTF.leftView = marginView4
        self.codeTF.layer.cornerRadius = kCornerRadius
        self.codeTF.layer.masksToBounds = true
        
        let attributedString = NSMutableAttributedString.init(string: "我已阅读并同意《用户注册协议》")
        attributedString.addAttribute(NSForegroundColorAttributeName, value:UIColor.init(hexString: "#0090EB"), range: NSRange.init(location: 7, length: 8))
        self.agreementBtn.setAttributedTitle(attributedString, for: .normal)
        self.agreementBtn.addTarget(self, action: #selector(RegisterViewController.agreementClickedAction), for: .touchUpInside)
        // Do any additional setup after loading the view.
    }
    func agreementClickedAction() {
        print("用户协议")
        let vc = CommanWebViewController()
        vc.title = "用户协议"
        vc.url = NetworkingHandle.mainHost + UserRegisterAgreementURL
        vc.isFromRegisterVC = true
        self.navigationController?.pushViewController(vc, animated: true)
    }
    func closeOrOpenSecurity(sender: UIButton){
        sender.isSelected = !sender.isSelected
        if sender.tag == 1001 {
            self.passwordTF.isSecureTextEntry = !sender.isSelected
        }else{
            self.secondPasswordTF.isSecureTextEntry = !sender.isSelected
        }
    }
    
    @IBAction func sendVerificationCodeAction(_ sender: UIButton) {
        if !(phoneTF.text?.isPhoneNumber)! {
            ProgressHUD.showMessage(message: "请输入正确的手机号")
            return
        }
        
        UIApplication.shared.keyWindow?.endEditing(true)
        let timer = fetchVerificationCodeCountdown(button: codeBtn, timeOut: 60)
        NetworkingHandle.fetchNetworkData(url: "othersInterfaces.api?sendCode", at: self, params: ["mobile": phoneTF.text!, "code_type": "member_register"], success: { (result) in
            ProgressHUD.showSuccess(message: "发送成功")
        }, failure: { [unowned self] errorCode in
            timer.cancel()
            self.codeBtn.setTitle("发送验证码", for: .normal)
            self.codeBtn.isEnabled = true
        })
    }
    @IBAction func checkAction(_ sender: UIButton) {
        sender.isSelected = !sender.isSelected
    }
    
    @IBAction func loginAction(_ sender: UIButton) {
        if !(phoneTF.text?.isPhoneNumber)! {
            ProgressHUD.showMessage(message: "请输入正确的手机号")
            return
        }

        if (passwordTF.text?.characters.count)! < 6{
            ProgressHUD.showMessage(message: "请将密码设置位数在六位以上")
            return
        }
        if passwordTF.text != secondPasswordTF.text {
            ProgressHUD.showMessage(message: "两次输入的密码不一致")
            return
        }
        
        if self.checkBtn.isSelected == false {
            ProgressHUD.showMessage(message: "请点击遵守《用户协议》")
            return
        }
        if self.codeTF.text?.characters.count == 0 {
            ProgressHUD.showMessage(message: "验证码不能为空")
            return
        }
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?memberRegister", at: self, params: ["member_account": phoneTF.text!,"member_password":passwordTF.text!, "code":codeTF.text!], isAuthHide: false, isShowHUD: false, isShowError: true, hasHeaderRefresh: nil, success: { (response) in
            
            let data = response["data"] as? NSDictionary
            let userModel = LHWEUserInfoModel.deserialize(from: data)
            LHWEUserInfoHandler.saveUserInfo(model: userModel!)
            ProgressHUD.showMessage(message: "登录成功")
            if userModel?.member_state == "-1"{
                 self.navigationController?.pushViewController(SubmitPersonInformationViewController(), animated: true)
                
            }else if userModel?.member_state == "0"{
                 self.navigationController?.pushViewController(CheckInformationViewController(), animated: true)
                
            }else{
                 UIApplication.shared.keyWindow?.rootViewController = BaseTabBarController()
            }
           
            
        }) { 
            
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        setStatusBarBGColor(color: UIColor.white)
        setKeyBoardManagerIsEnable()
        self.navigationController?.setNavigationBarHidden(true, animated: false)
    }
    override func viewWillDisappear(_ animated: Bool) {
        setKeyBoardManagerDisable()
        self.navigationController?.setNavigationBarHidden(false, animated: false)
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func back(_ sender: UIButton) {
        self.navigationController?.popViewController(animated: true)
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
