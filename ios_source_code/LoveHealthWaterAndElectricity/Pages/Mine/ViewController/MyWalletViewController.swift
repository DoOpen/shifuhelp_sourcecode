//
//  MyWalletViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/25.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class MyWalletViewController: UIViewController, UITextFieldDelegate{

    @IBOutlet weak var sumMoney: UILabel!
    
    @IBOutlet weak var canWithdraw: UILabel!
    
    @IBOutlet weak var hasFreeze: UILabel!
    
    
    @IBOutlet weak var bindState: UILabel!
    
    @IBOutlet weak var withdrawTF: UITextField!
    
    @IBOutlet weak var account: UILabel!
    
    @IBOutlet weak var confirmBtn: UIButton!
    
    @IBOutlet weak var agreement: UILabel!
    
    @IBOutlet weak var passwordState: UILabel!
    
    @IBOutlet weak var agreementBtn: UIButton!
    
    var model : PersonInfoModel!
    var isBind = false
    var payPasswordView : PayPasswordView!
    override func viewWillAppear(_ animated: Bool) {
        self.getUserInfo()
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "我的钱包"
        
        self.navigationItem.rightBarButtonItem = UIBarButtonItem.init(image: #imageLiteral(resourceName: "history"), style: .done, target: self, action: #selector(MyWalletViewController.checkEarningsHistory))
        
        NotificationCenter.default.addObserver(self, selector: #selector(textDidChange(noti:)), name: NSNotification.Name.UITextFieldTextDidChange, object: self.withdrawTF)
        
        let  str = "我已阅读并同意《用户提现协议》"
        let attributedStr = NSMutableAttributedString(string: str)
        attributedStr.addAttributes([NSForegroundColorAttributeName:UIColor.init(hexString: "0090EB")], range: NSRange.init(location: 7, length: 8))
        self.agreement.attributedText = attributedStr
        self.agreement.addGestureRecognizer(UITapGestureRecognizer.init(target: self, action: #selector(MyWalletViewController.checkAgreement)))
        
        self.withdrawTF.delegate = self
        
        self.confirmBtn.layer.cornerRadius = kCornerRadius
        self.confirmBtn.layer.masksToBounds = true
        
        // Do any additional setup after loading the view.
    }
    func getUserInfo() {
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getMemberDetail", at: self, success: { (response) in
            let data = response["data"] as! NSDictionary
            self.model = PersonInfoModel.deserialize(from: data)
            if self.model.member_extract_money != "", self.model.member_freeze_money != ""{
                self.sumMoney.text = String(Double(self.model.member_freeze_money!)! + Double(self.model.member_extract_money!)!) + "元"
            }else if self.model.member_extract_money != "", self.model.member_freeze_money == ""{
                
                self.sumMoney.text = String(Double(self.model.member_extract_money!)!) + "元"
                self.canWithdraw.text = "可提现金额：" + self.model.member_extract_money!
                self.hasFreeze.text = "已冻结金额：0"
                
            }else if self.model.member_freeze_money != "", self.model.member_extract_money == ""{
                
                self.sumMoney.text = String(Double(self.model.member_freeze_money!)!) + "元"
                self.hasFreeze.text = "已冻结金额：" + self.model.member_freeze_money!
                self.canWithdraw.text = "可提现金额：0"
                
            }else{
                
                self.sumMoney.text = "0元"
                self.canWithdraw.text = "可提现金额：0"
                self.hasFreeze.text = "已冻结金额：0"
            }
            
            if self.model.member_bank_name == "",self.model.member_we_chat == "", self.model.member_alipay == ""{
                self.bindState.text = "去绑定"
                self.isBind = true
            }
            if self.model.member_alipay != ""{
                self.bindState.text = "支付宝：" + self.model.member_alipay!
            }
            if self.model.member_we_chat != ""{
                self.bindState.text = "微信：" + self.model.member_we_chat!
            }
            if self.model.member_bank_name != ""{
                self.bindState.text = "银行卡：" + self.model.member_bank_name!
            }
            if self.model.member_pay_password == ""{
                self.passwordState.text = "请设置支付密码"
            }else{
                self.passwordState.text = "已经设置过支付密码"
            }
            
        })
    }
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        let text = (textField.text! as NSString).replacingCharacters(in: range, with: string)
        if text.characters.count == 0 {
            return true
        }
        if Double(text) == nil {
            return false
        }
        if Double(text)! > Double((self.model!.member_extract_money!))! {
            return false
        }
        return true
    }
    func textDidChange(noti:NSNotification){
        
        let tf = noti.object as! UITextField
        
        if tf.text?.characters.count == 0 {
            self.account.text = "0"
        }else{
            if Double(tf.text!)! < 100.0 {
                return
            }
            self.account.text = tf.text! + "元"

        }
        
    }
    func checkEarningsHistory() {

        let vc = EarningsRecordDetailViewController()
        vc.title = "提现记录"
        self.navigationController?.pushViewController(vc, animated: true)
        
    }
    func checkAgreement() {
        let vc = CommanWebViewController()
        vc.url = NetworkingHandle.mainHost + WithdrawAgreementURL
        vc.title = "用户提现协议"
        self.navigationController?.pushViewController(vc, animated: true)
    }
    
    
    @IBAction func SetPayPasswordAction(_ sender: UIButton) {
        
        let vc = SetPayPasswordViewController()
        
        vc.setSuccess = { [unowned self] in
            self.getUserInfo()
        }
        
        self.navigationController?.pushViewController(vc, animated: true)
    }
    
    
    @IBAction func protectedMoneyAction(_ sender: UIButton) {
        let vc = MarginMoneyViewController()
        vc.url = NetworkingHandle.mainHost + MarginURL
        vc.title = "我的保证金"
        self.navigationController?.pushViewController(vc, animated: true)
    }
    
    @IBAction func agreementAction(_ sender: UIButton) {
        sender.isSelected = !sender.isSelected
    }
    
    @IBAction func bindAccount(_ sender: UIButton) {
        if isBind {
            let alertVC = UIAlertController.init(title: "选择绑定账户类型", message: "", preferredStyle: .actionSheet)
            let actionSon = UIAlertAction.init(title: "绑定支付宝账户", style: .default) { (alert) in
                let vc = BindWXOrSonViewController()
                vc.isBindWX = false
                vc.title = "绑定支付宝"
                vc.bindSuccess = { account in
                    self.bindState.text = "支付宝：" + account
                }
                self.navigationController?.pushViewController(vc, animated: true)
            }
            let actionWX = UIAlertAction.init(title: "绑定微信账户", style: .default) { (alert) in
                let vc = BindWXOrSonViewController()
                vc.title = "绑定微信"
                vc.bindSuccess = { account in
                    self.bindState.text = "微信：" + account
                }
                self.navigationController?.pushViewController(vc, animated: true)
                
            }
            let bankAction = UIAlertAction.init(title: "绑定银行卡账户", style: .default) { (alert) in
                
                let vc = BindBankViewController()
                vc.title = "绑定银行卡"
                vc.bindSuccess = { account in
                    self.bindState.text = "银行卡：" + account
                }
                self.navigationController?.pushViewController(vc, animated: true)
            }
            let cancelAction = UIAlertAction.init(title: "取消", style: .cancel, handler: nil)
            alertVC.addAction(actionSon)
            alertVC.addAction(actionWX)
            alertVC.addAction(bankAction)
            alertVC.addAction(cancelAction)
            self.present(alertVC, animated: true, completion: nil)
        }else{
            let alertVC = UIAlertController.init(title: "选择要提现的账户", message: "", preferredStyle: .actionSheet)
            let actionSon = UIAlertAction.init(title: "支付宝账户", style: .default) { (alert) in
                if self.model.member_alipay == ""{
                    let vc = BindWXOrSonViewController()
                    vc.isBindWX = false
                    vc.title = "绑定支付宝"
                    vc.bindSuccess = { account in
                        self.bindState.text = "支付宝：" + account
                    }
                    self.navigationController?.pushViewController(vc, animated: true)
                }else{
                    self.bindState.text = "支付宝：" + self.model.member_alipay!
                }
            }
            let actionWX = UIAlertAction.init(title: "微信账户", style: .default) { (alert) in
                if self.model.member_we_chat == ""{
                    let vc = BindWXOrSonViewController()
                    vc.title = "绑定微信"
                    vc.bindSuccess = { account in
                        self.bindState.text = "微信：" + account
                    }
                    self.navigationController?.pushViewController(vc, animated: true)
                }else{
                    self.bindState.text = "微信：" + self.model.member_we_chat!
                }
            }
            let bankAction = UIAlertAction.init(title: "银行卡账户", style: .default) { (alert) in
                if self.model.member_bank_name == ""{
                    let vc = BindBankViewController()
                    vc.title = "绑定银行卡"
                    vc.bindSuccess = { account in
                        self.bindState.text = "银行卡：" + account
                    }
                    self.navigationController?.pushViewController(vc, animated: true)

                }else{
                    
                    self.bindState.text = "银行卡：" + self.model.member_bank_name!
                }
            }
            let cancelAction = UIAlertAction.init(title: "取消", style: .cancel, handler: nil)
            alertVC.addAction(actionSon)
            alertVC.addAction(actionWX)
            alertVC.addAction(bankAction)
            alertVC.addAction(cancelAction)
            self.present(alertVC, animated: true, completion: nil)
        }
        
        
    }
    
    @IBAction func confirmAction(_ sender: UIButton) {
        
        
        if !agreementBtn.isSelected {
            ProgressHUD.showMessage(message: "请遵守《用户提现协议》")
            return
        }
        if isBind{
            ProgressHUD.showMessage(message: "请先绑定提现账户")
            return
        }
        if (self.withdrawTF.text?.isEmpty)!{
            ProgressHUD.showMessage(message: "请输入提现金额")
            return
        }
        if Int(self.withdrawTF.text!)! < 100{
            ProgressHUD.showMessage(message: "金额大于必须100元才能提现")
            return
        }
        if self.model.member_pay_password == ""{
            ProgressHUD.showMessage(message: "请先设置支付密码")
            return
        }
        var payType = ""
        
        if self.bindState.text! == "银行卡：" + self.model.member_bank_name!{
            
            payType = "bank"
            
        }else if self.bindState.text! == "微信：" + self.model.member_we_chat!{
            
            payType = "we_chat"
            
        }else{
            
            payType = "alipay"
        }
        self.payPasswordView = PayPasswordView.show(atView: self.view)
        self.payPasswordView.finishEdit = { password in
            print(password)
            NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?applyCash", at: self, params: ["pay_type":payType,"cash_price":self.withdrawTF.text!,"member_pay_password":password], success: { (response) in
                ProgressHUD.showMessage(message: "提现申请已提交")
                self.getUserInfo()
                self.withdrawTF.text = ""
                self.canWithdraw.text = "0"
                self.payPasswordView.remove()
            }) {
                self.payPasswordView.passwordTF.clearKeyCode()
            }
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
