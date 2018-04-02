//
//  SearchGoodsViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/11/1.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import JavaScriptCore

class SearchGoodsViewController: UIViewController, UIWebViewDelegate, UITextFieldDelegate{

    @IBOutlet weak var webView: UIWebView!
    var context = JSContext()
    var vcTitle:String!
    var url:String!
    var address = ""
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setNavigationBar()
        self.webView.delegate = self
        self.webView.loadRequest(URLRequest.init(url: URL.init(string: url)!))
        self.webView.scrollView.bounces = false
        // Do any additional setup after loading the view.
    }
    func setNavigationBar(){
        let views = UIView(frame: CGRect(x: 0, y: 0, width: kScreenWidth - 150, height: 44))
        views.backgroundColor = UIColor.clear
        self.searchTF.frame = CGRect.init(x: 0, y: 7, width: kScreenWidth - 150, height: 30)
        views.addSubview(self.searchTF)
        self.navigationItem.titleView = views
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
        addGoodsDetailAction()
       
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
    fileprivate lazy var searchTF : UITextField = {
        let tf = UITextField()
        tf.delegate = self
        tf.returnKeyType = .search
        tf.backgroundColor = UIColor.white
        tf.clearButtonMode = .whileEditing
        let imageLeft = UIImageView.init(image: #imageLiteral(resourceName: "sousuo"))
        imageLeft.frame = CGRect.init(x: 0, y: 0, width: 25, height: 30)
        imageLeft.contentMode = .scaleAspectFill
        tf.leftView = imageLeft
        tf.leftViewMode = .always
        tf.layer.cornerRadius = kCornerRadius
        tf.layer.masksToBounds = true
        tf.placeholder = "搜索您想要的商品"
        tf.font = defaultFont(size: 14)
        tf.clearsOnBeginEditing = true
        return tf
    }()
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        if textField.text?.characters.count == 0 {
            ProgressHUD.showMessage(message: "请输入您想要的商品")
        }else{
            let vc = ShoppingMallNextViewController()
            vc.vcTitle = "商品搜索"
            let str1 = NetworkingHandle.mainHost + "app-mall1/index.html#/search_goods?goodsName="
            let str2 = "&member_id=" + (LHWEUserInfoHandler.getUserIDAndToken()?.id)! + "&member_token=" + (LHWEUserInfoHandler.getUserIDAndToken()?.token)!
            let str3 = "&add=" + address
            let url = str1 + textField.text!.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)! + str2 + str3.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
            //中文字符转码
            vc.url = url
            print(vc.url)
            self.navigationController?.pushViewController(vc, animated: true)
        }
        return true
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        ProgressHUD.hideLoading(toView: self.view)
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
