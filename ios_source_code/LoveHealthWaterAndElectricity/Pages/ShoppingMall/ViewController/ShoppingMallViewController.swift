//
//  ShoppingMallViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/14.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import JavaScriptCore
import WebKit

class ShoppingMallViewController: UIViewController, UIWebViewDelegate {

    @IBOutlet weak var webView: UIWebView!
    var context = JSContext()
    var city = "上海"
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.navigationBar.isHidden = true
        self.tabBarController?.tabBar.isHidden = true
        setStatusBarBGColor(color: themeColor)
        
        if #available(iOS 11, *) {
            self.webView.scrollView.contentInsetAdjustmentBehavior = .always
        }else{
            self.automaticallyAdjustsScrollViewInsets = true
        }
        
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        ProgressHUD.hideLoading(toView: self.view)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
        self.navigationController?.navigationBar.isHidden = false

    }
    override func viewWillLayoutSubviews() {
        super.viewWillLayoutSubviews()
        self.view.frame = CGRect.init(x: 0, y: 0, width: kScreenWidth, height: kScreenHeight)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
       
        
        
        self.webView.delegate = self
        self.webView.scrollView.bounces = false
        self.webView.scrollView.showsVerticalScrollIndicator = false
        let str = NetworkingHandle.mainHost + "app-mall1/#/home?member_id=" + (LHWEUserInfoHandler.getUserInfo()?.member_id)! + "&member_token=" + (LHWEUserInfoHandler.getUserInfo()?.member_token)!
        let str1 = "&add=" + (LocationManager.shared.district.addingPercentEncoding(withAllowedCharacters: .urlHostAllowed)!)
        self.webView.loadRequest(URLRequest.init(url: URL.init(string: str + str1)!))
        
        // Do any additional setup after loading the view.
    }
    
    
    
    func webViewDidStartLoad(_ webView: UIWebView) {
        ProgressHUD.showLoading(toView: self.webView)
    }
    func webViewDidFinishLoad(_ webView: UIWebView) {
        ProgressHUD.hideLoading(toView: self.webView)
        addJSContext()
        
    }
    func webView(_ webView: UIWebView, didFailLoadWithError error: Error) {
        ProgressHUD.hideLoading(toView: self.webView)
    }
    func addJSContext() {
        
        self.context = webView.value(forKeyPath: "documentView.webView.mainFrame.javaScriptContext") as? JSContext
        self.context?.exceptionHandler = { (context, exception) in
            print("exception：", exception as Any)
            
        }
        self.addClickConfirmOrderAction()
        self.clickShopCartAction()
        self.clickBannerAction()
        self.clickGoodsListAction()
        self.clickSearchGoodsAction()
        self.clickMessageAction()
        self.clickGoodsDetailAction()
        self.clickHomeAction()
        self.addBannerClickAction()
        //个人中心
        self.addJumpToIntegralVCAction()
        self.addJumpToCouponListVCAction()
        self.addJumpToAddressAction()
        self.addJumpToGoodsCollectionAction()
        self.addJumpToOrderListVCAction()
        self.addAfterSaleVCAction()
        self.addCallCustomerAction()
    }
    //banner链接
    func addBannerClickAction(){
        let temp: @convention(block) ()->() = {
            let array = JSContext.currentArguments()
            for object in array! {
                print(object)
            }
            DispatchQueue.main.async {
                let vc = CommanWebViewController()
                vc.url = (array?.first as AnyObject).toString()!
                vc.title = (array?.last as AnyObject).toString()!
                self.navigationController?.pushViewController(vc, animated: true)
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoLink" as NSCopying & NSObjectProtocol)
    }
    //MARK: 个人中心相关
    func addJumpToIntegralVCAction() {
       
        let temp: @convention(block) ()->() = {
            DispatchQueue.main.async {
                let vc = IntegralViewController()
                vc.title = "我的积分"
                self.navigationController?.pushViewController(vc, animated: true)
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoPoints" as NSCopying & NSObjectProtocol)
    }
    func addJumpToCouponListVCAction() {
        let temp: @convention(block) ()->() = {
            DispatchQueue.main.async {
                let vc = MyCouponViewController()
                vc.title = "我的优惠券"
                self.navigationController?.pushViewController(vc, animated: true)
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoCoupon" as NSCopying & NSObjectProtocol)
    }
    func addJumpToAddressAction() {
        let temp: @convention(block) ()->() = {
            DispatchQueue.main.async {
                let vc = ReceivingAddressViewController()
                vc.title = "收货地址"
                self.navigationController?.pushViewController(vc, animated: true)
                
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoAddress" as NSCopying & NSObjectProtocol)
    }
    func addJumpToGoodsCollectionAction() {
        let temp: @convention(block) ()->() = {
            DispatchQueue.main.async {
               self.navigationController?.pushViewController(MyCollectionViewController(), animated: true)
                
                
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoCollection" as NSCopying & NSObjectProtocol)
    }
    func addJumpToOrderListVCAction() {
        let temp: @convention(block) ()->() = {
            DispatchQueue.main.async {
                self.navigationController?.pushViewController(AfterSaleViewController(), animated: true)
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appSH" as NSCopying & NSObjectProtocol)
    }
    func addAfterSaleVCAction(){
        let temp: @convention(block) ()->() = {
            let array = JSContext.currentArguments()
            DispatchQueue.main.async {
                let state = (array?.first as AnyObject).toString()!
                print(state)
                let vcType = Int(state)!
                let vc = MyOrderViewController()
                if vcType >= 3{
                    vc.type = vcType
                }else{
                    vc.type = vcType - 1

                }
                self.navigationController?.pushViewController(vc, animated: true)
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appOrderList" as NSCopying & NSObjectProtocol)
    }
    func addCallCustomerAction(){
        let temp: @convention(block) () -> () = {
            DispatchQueue.main.async {
                
                callCustomerServices(atView: self.view, phoneNum: CustomerServicesPhone)
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appJumpCall" as NSCopying & NSObjectProtocol)
    }
    //MARK: 商品相关
    func clickHomeAction(){
        let temp: @convention(block) ()->() = {
            
            DispatchQueue.main.async {
                self.tabBarController?.selectedIndex = 0
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoAppHome" as NSCopying & NSObjectProtocol)
    }
    func clickGoodsDetailAction() {
        let temp: @convention(block) ()->() = {
            let array = JSContext.currentArguments()
            DispatchQueue.main.async {
                self.jumpToNextVC(title: "商品详情", url: (array?.first as AnyObject).toString())
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoGoods" as NSCopying & NSObjectProtocol)
    }
    func clickShopCartAction() {
        let temp: @convention(block) ()->() = {
            let array = JSContext.currentArguments() // 这里接到的array中的内容是JSValue类型
            DispatchQueue.main.async {
                
                
                self.jumpToNextVC(title: "购物车", url: (array?.first as AnyObject).toString())
                
            }
            
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appCart" as NSCopying & NSObjectProtocol)
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
    func clickBannerAction(){
        let temp: @convention(block) ()->() = {
            let array = JSContext.currentArguments() // 这里接到的array中的内容是JSValue类型
            DispatchQueue.main.async {
                self.jumpToNextVC(title: "商品详情", url: (array?.first as AnyObject).toString())
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoodsdetail" as NSCopying & NSObjectProtocol)
    }
    func clickGoodsListAction(){
        let temp: @convention(block) () ->() = {
            let array = JSContext.currentArguments() // 这里接到的array中的内容是JSValue类型
            DispatchQueue.main.async {
                self.jumpToNextVC(title: "商品分类", url: (array?.first as AnyObject).toString())
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoodslist" as NSCopying & NSObjectProtocol)
    }
    func clickSearchGoodsAction() {
        let temp: @convention(block) ()->() = {
            let array = JSContext.currentArguments() // 这里接到的array中的内容是JSValue类型
            DispatchQueue.main.async {
                self.jumpToSearchGoodsVC(address: (array?.first as AnyObject).toString())
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appSearch" as NSCopying & NSObjectProtocol)
    }
    func clickMessageAction() {
        let temp: @convention(block) ()->() = {
            DispatchQueue.main.async {
                self.navigationController?.pushViewController(MessageViewController(), animated: true)
            }
        }
        self.context?.setObject(unsafeBitCast(temp, to: AnyObject.self), forKeyedSubscript: "appGoNews" as NSCopying & NSObjectProtocol)
    }
    func jumpToNextVC(title:String,url:String){
        let vc = ShoppingMallNextViewController()
        vc.vcTitle = title
        vc.url = url
        self.navigationController?.pushViewController(vc, animated: true)
    }
    func jumpToSearchGoodsVC(address:String) {
        
        let vc = SearchGoodsViewController()
        let str1 = NetworkingHandle.mainHost + "app-mall1/index.html#/search"
        let str2 = "?member_id=" + (LHWEUserInfoHandler.getUserIDAndToken()?.id)! + "&member_token=" + (LHWEUserInfoHandler.getUserIDAndToken()?.token)!
        let str3 = "&add=" + (address.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!)
        vc.url = str1 + str2 + str3
        vc.address = address
        self.navigationController?.pushViewController(vc, animated: true)

    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    deinit {
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
