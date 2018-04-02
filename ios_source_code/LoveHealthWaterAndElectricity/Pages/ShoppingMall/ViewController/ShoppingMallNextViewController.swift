//
//  ShoppingMallNextViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/14.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import JavaScriptCore

class ShoppingMallNextViewController: UIViewController, UIWebViewDelegate{

    @IBOutlet weak var webView: UIWebView!
    var vcTitle:String!
    var url:String!
    var context = JSContext()
    var shareView = CommanShareView()
   
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = vcTitle
        
       
        NotificationCenter.default.addObserver(self, selector: #selector(ShoppingMallNextViewController.paySuccess), name: NSNotification.Name(rawValue: PaySuccessNotification), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(ShoppingMallNextViewController.payFail), name: NSNotification.Name(rawValue: PayFailNotification), object: nil)
        self.webView.delegate = self
        self.webView.loadRequest(URLRequest.init(url: URL.init(string: url)!))
        self.webView.scrollView.bounces = false
        // Do any additional setup after loading the view.
    }
    func paySuccess() {
        ProgressHUD.showMessage(message: "支付成功")
        PayResultViewController.jumpPayResultVC(fromVC:self, isSuccess: true)

    }
    func payFail(){
        ProgressHUD.showMessage(message: "支付失败")
        PayResultViewController.jumpPayResultVC(fromVC:self, isSuccess: false)
    }

    func webViewDidStartLoad(_ webView: UIWebView) {
        ProgressHUD.showLoading(toView: self.webView)
    }
    func webViewDidFinishLoad(_ webView: UIWebView) {
        ProgressHUD.hideLoading(toView: self.webView)
        if webView.isLoading {
            return
        }
        self.addJSContext()
    }
    func webView(_ webView: UIWebView, didFailLoadWithError error: Error) {
        ProgressHUD.hideLoading(toView: self.view)
    }
    func addJSContext() {
        
        self.context = webView.value(forKeyPath: "documentView.webView.mainFrame.javaScriptContext") as? JSContext
        self.context?.exceptionHandler = { (context, exception) in
            print("exception：", exception as Any)
        }
        self.addClickShareGoodsAction()
        self.addGoodsDetailAction()
        self.addClickConfirmOrderAction()
        self.addJumpHomePageAction()
        self.addCallCustomerAction()
        self.addAddressAction()
        self.addGoodsEvaluateAction()
        self.addPayOrderAction()
    }
    
    //MARK: 商品相关
    func addPayOrderAction(){
        let temp: @convention(block) () -> () = {
            let array = JSContext.currentArguments()
            DispatchQueue.main.async {
                let vc = UIAlertController.init(title: "选择支付方式", message: "", preferredStyle: .actionSheet)
                let wxAction = UIAlertAction.init(title: "微信", style: .default, handler: { (alert) in
                   self.payGoodsOrder(type: "wx", orderID: (array?.first as AnyObject).toString())
                    
                })
                let sonAction = UIAlertAction.init(title: "支付宝", style: .default, handler: { (alert) in
                    self.payGoodsOrder(type: "alipay", orderID: (array?.first as AnyObject).toString())
                })
                let cancelAction = UIAlertAction.init(title: "取消", style: .cancel, handler: nil)
                
                vc.addAction(wxAction)
                vc.addAction(sonAction)
                vc.addAction(cancelAction)
                self.present(vc, animated: true, completion: nil)
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoPay" as NSCopying & NSObjectProtocol)
    }
    func payGoodsOrder(type: String, orderID: String){
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?payRealOrders", at: self, params: ["order_ids":orderID, "channel":type], success: { (response) in
            let data = response["data"] as! String
            let payDic = getDictionaryFromJSONString(jsonString: data)
            if type == "wx"{
                Pingpp.createPayment(payDic, appURLScheme: WXAppID, withCompletion: { (result, error) in
                    if error != nil{
                        print(error?.description ?? "")
                    }
                })
            }else{
                Pingpp.createPayment(payDic, appURLScheme: SonAppID, withCompletion: { (result, error) in
                    if error != nil{
                        print(error?.description ?? "")
                    }
                })
            }
            
        }) {
        }
    }
    func addGoodsEvaluateAction(){
        let temp: @convention(block) () -> () = {
            let array = JSContext.currentArguments()
            DispatchQueue.main.async {
                
                self.jumpToNextVC(title: "商品评论", url: (array?.first as AnyObject).toString())
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoodpinglun" as NSCopying & NSObjectProtocol)
    }
    func addAddressAction(){
        let temp: @convention(block) () -> () = {
           
            DispatchQueue.main.async {
                self.navigationController?.pushViewController(ReceivingAddressViewController(), animated: true)
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoaddress" as NSCopying & NSObjectProtocol)
    }
    func addCallCustomerAction(){
        let temp: @convention(block) () -> () = {
            DispatchQueue.main.async {
                
                callCustomerServices(atView: self.webView, phoneNum: CustomerServicesPhone)
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appJumpCall" as NSCopying & NSObjectProtocol)
    }
    func addJumpHomePageAction(){
        let temp: @convention(block) () -> () = {
           
            DispatchQueue.main.async {
               self.tabBarController?.selectedIndex = 2
               self.navigationController?.popToRootViewController(animated: true)
                
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoHome" as NSCopying & NSObjectProtocol)
    }
    func addClickConfirmOrderAction(){
        let temp: @convention(block) () -> () = {
            let array = JSContext.currentArguments()
            DispatchQueue.main.async {
                self.jumpToNextVC(title: "确认订单", url: (array?.first as  AnyObject).toString())
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoConfirm" as NSCopying & NSObjectProtocol)
    }
    func addGoodsDetailAction(){
        let temp: @convention(block) () ->() = {
            let array = JSContext.currentArguments() // 这里接到的array中的内容是JSValue类型
            DispatchQueue.main.async {
                
                self.jumpToNextVC(title: "商品详情", url: (array?.first as AnyObject).toString())
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoGoods" as NSCopying & NSObjectProtocol)
    }
    func addClickShareGoodsAction() {
        let temp: @convention(block) () ->() = {
            let array = JSContext.currentArguments() // 这里接到的array中的内容是JSValue类型
            DispatchQueue.main.async {
                self.shareView = CommanShareView.show(atView: self.view, url: (array?.first as AnyObject).toString(), avatar: (array?[3] as AnyObject).toString(), username: (array?[1] as AnyObject).toString(), desc: (array?[2] as AnyObject).toString(), isMineVC: false)
                self.shareView.isMineVC = false
                
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appShareGood" as NSCopying & NSObjectProtocol)
    }
    func jumpToNextVC(title:String,url:String){
        let vc = ShoppingMallNextViewController()
        vc.vcTitle = title
        vc.url = url
        self.navigationController?.pushViewController(vc, animated: true)
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        ProgressHUD.hideLoading(toView: self.view)
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
