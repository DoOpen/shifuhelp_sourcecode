//
//  MarginMoneyViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/27.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class MarginMoneyViewController: UIViewController, UIWebViewDelegate{

    @IBOutlet weak var money: UILabel!
    
    @IBOutlet weak var webView: UIWebView!
    
    @IBOutlet weak var marginBtn: UIButton!
     var url: String!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        NotificationCenter.default.addObserver(self, selector: #selector(MarginMoneyViewController.paySuccess), name: NSNotification.Name(rawValue: PaySuccessNotification), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(MarginMoneyViewController.payFail), name: NSNotification.Name(rawValue: PayFailNotification), object: nil)
        self.webView.loadRequest(URLRequest.init(url: URL.init(string: url)!))
        self.marginBtn.layer.cornerRadius = kCornerRadius
        self.marginBtn.layer.masksToBounds = true
        self.getMarginMoneyState()
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
            self.getMarginMoneyState()
            ProgressHUD.showMessage(message: "支付成功")
        })
    }
    func getMarginMoneyState(){
        if LHWEUserInfoHandler.getUserInfo()?.member_deposit_money?.count == 0 {
            self.marginBtn.setTitle("缴纳押金", for: .normal)
            self.money.text = (LHWEUserInfoHandler.getUserInfo()?.nead_deposit!)! + "元"
        }else{
            self.marginBtn.setTitle("已经缴纳", for: .normal)
            self.marginBtn.isEnabled = false
            self.marginBtn.isUserInteractionEnabled = false
        }
    }
    @IBAction func payDepositAction(_ sender: UIButton) {
        let vc = UIAlertController.init(title: "缴纳保证金", message: "", preferredStyle: .actionSheet)
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
    func webViewDidStartLoad(_ webView: UIWebView) {
        ProgressHUD.showLoading(toView: self.view)
    }
    func webViewDidFinishLoad(_ webView: UIWebView) {
        ProgressHUD.hideLoading(toView: self.view)
    }
    func webView(_ webView: UIWebView, didFailLoadWithError error: Error) {
        ProgressHUD.hideLoading(toView: self.view)
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        ProgressHUD.hideLoading(toView: self.view)
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
