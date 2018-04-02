//
//  BindBankViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class BindBankViewController: UIViewController {

    @IBOutlet weak var bankName: UITextField!
    
    @IBOutlet weak var bankNumber: UITextField!
    
    @IBOutlet weak var bankUser: UITextField!
    
    @IBOutlet weak var openBankName: UITextField!
    
    @IBOutlet weak var phoneTF: UITextField!
    
    @IBOutlet weak var codeTF: UITextField!
    
    @IBOutlet weak var codeBtn: UIButton!
    
    @IBOutlet weak var bindBtn: UIButton!
    
    
    var bindSuccess : ((String)->())?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.codeBtn.layer.cornerRadius = kCornerRadius
        self.codeBtn.layer.borderWidth = 1
        self.codeBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
        self.codeBtn.layer.masksToBounds = true
        
        
        self.bindBtn.layer.cornerRadius = kCornerRadius
        self.bindBtn.layer.masksToBounds = true
        // Do any additional setup after loading the view.
    }

    @IBAction func getCodeAction(_ sender: UIButton) {
        if !(phoneTF.text?.isPhoneNumber)! {
            ProgressHUD.showMessage(message: "请输入正确的手机号")
            return
        }
        
        UIApplication.shared.keyWindow?.endEditing(true)
        
        let timer = fetchVerificationCodeCountdown(button: codeBtn, timeOut: 60)
        NetworkingHandle.fetchNetworkData(url: "othersInterfaces.api?sendCode", at: self, params: ["mobile": phoneTF.text!, "code_type": "bind_bank"], success: { (result) in
             ProgressHUD.showSuccess(message: "发送成功")
        }, failure: { [unowned self] errorCode in
            timer.cancel()
            self.codeBtn.setTitle("发送验证码", for: .normal)
            self.codeBtn.isEnabled = true
        })

    }
    
    
    @IBAction func confirmAction(_ sender: UIButton) {
        
        if (self.bankName.text?.isEmpty)! || (self.bankUser.text?.isEmpty)! || (self.openBankName.text?.isEmpty)! || (self.codeTF.text?.isEmpty)! {
            ProgressHUD.showMessage(message: "请完善信息")
            return
        }
        if !(self.bankNumber.text?.isBankCard)!{
            ProgressHUD.showMessage(message: "银行卡号格式不正确")
            return
        }
        if !(self.phoneTF.text?.isPhoneNumber)! {
            ProgressHUD.showMessage(message: "手机号格式不正确")
            return
        }
        let params : Dictionary<String,String> = ["code_type":"bind_bank","code":self.codeTF.text!,"member_bank_name":self.bankName.text!,"member_bank_code":self.bankNumber.text!,"member_bank__user_name":self.bankUser.text!,"member_bank_open_name":self.openBankName.text!,"member_bank_phone":self.phoneTF.text!]
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?bindOtherNo", at: self, params: params, success: { (response) in
            ProgressHUD.showSuccess(message: "绑定成功")
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
