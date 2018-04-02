//
//  BindWXOrSonViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/25.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class BindWXOrSonViewController: UIViewController {
    @IBOutlet weak var headImg: UIImageView!

    @IBOutlet weak var headTitle: UILabel!
       
    @IBOutlet weak var phoneTF: UITextField!
    
    @IBOutlet weak var nameTF: UITextField!
    
    @IBOutlet weak var codeTF: UITextField!
    
    @IBOutlet weak var codeBtn: UIButton!
    
    @IBOutlet weak var bindBtn: UIButton!
    
    
    var isBindWX = true
    var bindSuccess : ((String)->())?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if !isBindWX {
            self.headImg.image = #imageLiteral(resourceName: "zhifubao")
            self.headTitle.text = "该支付宝帐号将成为您的提现账号"
            self.phoneTF.placeholder = "请输入支付宝账号"
        }
        
        self.bindBtn.layer.cornerRadius = kCornerRadius
        self.bindBtn.layer.masksToBounds = true
        self.codeBtn.layer.cornerRadius = kCornerRadius
        self.codeBtn.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
        self.codeBtn.layer.borderWidth = 1
        self.codeBtn.layer.masksToBounds = true
        
        // Do any additional setup after loading the view.
    }

    @IBAction func confirmBindAction(_ sender: UIButton) {
        if (self.phoneTF.text?.isEmpty)! || (self.nameTF.text?.isEmpty)! || (self.codeTF.text?.isEmpty)! {
            ProgressHUD.showMessage(message: "请完善信息")
            return
        }
        var params : Dictionary<String,String>
        if isBindWX {
            params = ["code_type":"bind_we_chat","code":self.codeTF.text!,"member_we_chat":self.phoneTF.text!,"member_we_chat_real_name":self.nameTF.text!]
        }else{
            params = ["code_type":"bind_alipay","code":self.codeTF.text!,"member_alipay":self.phoneTF.text!,"memebr_alipay_real_name":self.nameTF.text!]
        }
        
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?bindOtherNo", at: self, params: params, success: { (response) in
            ProgressHUD.showSuccess(message: "绑定成功")
            self.bindSuccess?((self.phoneTF.text!))
            self.navigationController?.popViewController(animated: true)
        }) { 
            
        }
    }
    
    @IBAction func getCodeAction(_ sender: UIButton) {
        if !(phoneTF.text?.isPhoneNumber)! {
            ProgressHUD.showMessage(message: "请输入正确的手机号")
            return
        }
        var type = "bind_we_chat"
        if !isBindWX{
            type = "bind_alipay"
        }
        UIApplication.shared.keyWindow?.endEditing(true)
        let timer = fetchVerificationCodeCountdown(button: codeBtn, timeOut: 60)
        NetworkingHandle.fetchNetworkData(url: "othersInterfaces.api?sendCode", at: self, params: ["mobile": phoneTF.text!, "code_type": type], success: { (result) in
             ProgressHUD.showSuccess(message: "发送成功")
            
        }, failure: { [unowned self] errorCode in
            timer.cancel()
            self.codeBtn.setTitle("发送验证码", for: .normal)
            self.codeBtn.isEnabled = true
        })

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
