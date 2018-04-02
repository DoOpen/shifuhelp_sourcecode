//
//  CommanWebViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/18.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import WebKit
import SnapKit

class CommanWebViewController: UIViewController,WKNavigationDelegate, WKUIDelegate {

    var web = WKWebView()
    var url: String!
    var isFromRegisterVC = false
    override func viewDidLoad() {
        super.viewDidLoad()
        self.web.uiDelegate = self
        self.web.navigationDelegate = self
        self.view.addSubview(self.web)
       
        self.web.load(URLRequest.init(url: URL(string:self.url)!))
        self.web.scrollView.bounces = false
        self.web.snp.makeConstraints { (make) in
            make.edges.equalToSuperview()
        }
        
        // Do any additional setup after loading the view.
    }

    //MARK: -delegate
    func webView(_ webView: WKWebView, didStartProvisionalNavigation navigation: WKNavigation!) {
        ProgressHUD.showLoading(toView: self.view)
    }
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        ProgressHUD.hideLoading(toView: self.view)
    }
    func webView(_ webView: WKWebView, didFail navigation: WKNavigation!, withError error: Error) {
        ProgressHUD.hideLoading(toView: self.view)
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setStatusBarBGColor(color: themeColor)
        self.navigationController?.setNavigationBarHidden(false, animated: true)
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        if isFromRegisterVC {
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
