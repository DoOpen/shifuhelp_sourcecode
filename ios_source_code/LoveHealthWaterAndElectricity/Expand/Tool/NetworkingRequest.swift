//
//  NetworkingRequest.swift
//  CrazyEstate
//
//  Created by Luiz on 2017/1/16.
//  Copyright © 2017年 liangyi. All rights reserved.
//

import UIKit
import Alamofire
import MJRefresh
import IQKeyboardManagerSwift

class NetworkingHandle: NSObject {
    //测试服务器 http://aksd.qubaotang.cn/
    //正式服务器 http://wx.shifuhelp.com/
    static var mainHost = "http://aksd.qubaotang.cn/"
    // POST 请求 Info
    static func fetchNetworkData(url: String, at: UIResponder, params: Dictionary<String, Any> = [:], isAuthHide: Bool = true, isShowHUD: Bool = true, isShowError: Bool = true, hasHeaderRefresh: UIScrollView? = nil, success: @escaping (Dictionary<String, Any>) -> (), failure: (() -> ())? = nil) {
        var params = params
        if let m = LHWEUserInfoHandler.getUserIDAndToken() {
            params["member_id"] = m.id
            params["member_token"] = m.token
           
        }
        print(params)
        var atView: UIView
        if at is UIViewController {
            atView = (at as! UIViewController).view
        } else if at is UIView {
            if let vc = (at as? UIView)?.responderViewController() {
                atView = vc.view
            } else {
                atView = UIApplication.shared.keyWindow!
            }
        } else {
            atView = UIApplication.shared.keyWindow!
        }
        if hasHeaderRefresh == nil, isShowHUD {
            ProgressHUD.showLoading(toView: atView)
        }
        UIApplication.shared.isNetworkActivityIndicatorVisible = true
        Alamofire.request(mainHost + url, method: .post, parameters: params).responseJSON { (dataResponse) in
            UIApplication.shared.isNetworkActivityIndicatorVisible = false
            if let scrollView = hasHeaderRefresh {
                scrollView.mj_header?.endRefreshing()
                scrollView.mj_footer?.endRefreshing()
            } else if isAuthHide, isShowHUD {
                ProgressHUD.hideLoading(toView: atView)
            }
            if let error = dataResponse.result.error {
                if let status = dataResponse.response?.statusCode {
                    if status == 500{
                        
                    }else{
                     ProgressHUD.showNoticeOnStatusBar(message: "\(status)-服务器访问失败")
                    }
                } else {
                    ProgressHUD.showNoticeOnStatusBar(message: error.localizedDescription)
                }
                failure?()
            } else {
                guard let json = dataResponse.result.value else {
                    failure?()
                    ProgressHUD.showNoticeOnStatusBar(message: "未知错误")
                    return
                }
                print("##########\(json)")
                let result: Dictionary<String, Any> = json as! Dictionary
                let code: String = (result["status"] as? String)!
                if code == "ok" {
                    if atView is UIWindow || atView.superview != nil {
                        success(result)
                    }
                } else {
                    switch code {
                    case "pending":
                        ProgressHUD.showNoticeOnStatusBar(message: "别处登录")
                        LHWEUserInfoHandler.deleteUserInfo()
                    
                        UIApplication.shared.keyWindow?.rootViewController = LoginNavigationController.setup()
                    default:
                        failure?()
                        if isShowError {
                            ProgressHUD.showMessage(message: result["error"] as! String)
                        }
                    }
                }
            }
        }
    }
      
    // 图片上传
    static func uploadPicture(url: String, atVC: UIViewController, image: UIImage, params: Dictionary<String, String> = [:], isAuthHide: Bool = true, uploadSuccess: @escaping (_: Dictionary<String, Any>) -> (), failure: (() -> ())? = nil) {
        var params = params
        if let m = LHWEUserInfoHandler.getUserIDAndToken() {
            params["member_id"] = m.id
            params["member_token"] = m.token
        }
        UIApplication.shared.isNetworkActivityIndicatorVisible = true
        ProgressHUD.showLoading(toView: atVC.view)
        Alamofire.upload(multipartFormData: { (multipartFormData) in
            let data = UIImageJPEGRepresentation(image, 0.5)
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = "yyyyMMddHHmmssms"
            let imgName = dateFormatter.string(from: Date()) + String(arc4random()) + ".png"
            multipartFormData.append(data!, withName: "member_head_image", fileName: imgName, mimeType: "image/png")
            for (key, value) in params {
                multipartFormData.append(value.data(using: String.Encoding.utf8)!, withName: key)
            }
            multipartFormData.append("member_head_image".data(using: String.Encoding.utf8)!, withName: "file_param")
        }, to: URL(string: mainHost + url)!) { (encodingResult) in
            switch encodingResult {
            case .success(let upload, _, _):
                upload.responseJSON(completionHandler: { (response) in
                    if isAuthHide {
                        ProgressHUD.hideLoading(toView: atVC.view)
                    }
                    UIApplication.shared.isNetworkActivityIndicatorVisible = false
                    if response.response?.statusCode == 200 {
                        guard let json = response.result.value else {
                            ProgressHUD.showNoticeOnStatusBar(message: "未知错误")
                            failure?()
                            return
                        }
                        let result: Dictionary<String, Any> = json as! Dictionary
                        print(result)
                        let code: String = (result["status"] as? String)!
                        if code == "ok" {
                            if atVC.view.superview != nil {
                                uploadSuccess(result)
                            }
                        } else {
                            switch code {
                            case "pending":
                                ProgressHUD.showNoticeOnStatusBar(message: "别处登录")
                                LHWEUserInfoHandler.deleteUserInfo()
                                UIApplication.shared.keyWindow?.rootViewController = LoginNavigationController.setup()
                            default:
                                failure?()
                                ProgressHUD.showMessage(message: result["error"] as! String)
                            }
                        }
                    } else {
                        
                        failure?()
                        ProgressHUD.showNoticeOnStatusBar(message: "\(String(describing: response.response?.statusCode))-服务器访问失败")
                    }
                })
                
            case .failure(let error):
                if isAuthHide {
                    ProgressHUD.hideLoading(toView: atVC.view)
                }
                UIApplication.shared.isNetworkActivityIndicatorVisible = false
                failure?()
                ProgressHUD.showNoticeOnStatusBar(message: error.localizedDescription)
            }
        }
    }
    // 多图
    static func uploadOneMorePicture(url: String, atVC: UIViewController, images: [UIImage], params: Dictionary<String, String> = [:], isAuthHide: Bool = true, uploadSuccess: @escaping (_: Dictionary<String, Any>) -> (), failure: (() -> ())? = nil) {
        
        var params = params
        if let m = LHWEUserInfoHandler.getUserIDAndToken() {
            params["member_id"] = m.id
            params["member_token"] = m.token
        }
        UIApplication.shared.isNetworkActivityIndicatorVisible = true
        ProgressHUD.showLoading(toView: atVC.view)
        Alamofire.upload(multipartFormData: { (multipartFormData) in
            if images.count == 0 {
                ProgressHUD.showMessage(message: "请选择图片")
                return
            }
            for (index, value) in images.enumerated() {
                let data = UIImageJPEGRepresentation(value, 0.65)
                let dateFormatter = DateFormatter()
                dateFormatter.dateFormat = "yyyyMMddHHmmssms"
                let imgName = dateFormatter.string(from: Date()) + "\(index).png"
                multipartFormData.append(data!, withName: "\(index)", fileName: imgName, mimeType: "image/png")
            }
            for (key, value) in params {
                multipartFormData.append(value.data(using: String.Encoding(rawValue: String.Encoding.utf8.rawValue))!, withName: key)
                
            }
        }, to: URL(string: mainHost + url)!) { (encodingResult) in
            switch encodingResult {
            case .success(let upload, _, _):
                upload.responseJSON(completionHandler: { (response) in
                    if isAuthHide {
                        ProgressHUD.hideLoading(toView: atVC.view)
                    }
                    UIApplication.shared.isNetworkActivityIndicatorVisible = false
                    if response.response?.statusCode == 200 {
                        guard let json = response.result.value else {
                            ProgressHUD.showNoticeOnStatusBar(message: "未知错误")
                            failure?()
                            return
                        }
                        let result: Dictionary<String, Any> = json as! Dictionary
                        print(result)
                        let code: String = (result["status"] as? String)!
                        if code == "ok" {
                            if atVC.view.superview != nil {
                                uploadSuccess(result)
                            }
                        } else {
                            switch code {
                            case "pending":
                                ProgressHUD.showNoticeOnStatusBar(message: result["error"] as! String)
                                LHWEUserInfoHandler.deleteUserInfo()
                                
                                UIApplication.shared.keyWindow?.rootViewController = LoginNavigationController.setup()
                            default:
                                ProgressHUD.showMessage(message: result["error"] as! String)
                                failure?()
                            }
                        }
                    } else {
                        failure?()
                        ProgressHUD.showNoticeOnStatusBar(message: "\(String(describing: response.response?.statusCode))-服务器访问失败")
                    }
                })
                
            case .failure(let error):
                if isAuthHide {
                    ProgressHUD.hideLoading(toView: atVC.view)
                }
                UIApplication.shared.isNetworkActivityIndicatorVisible = false
                failure?()
                ProgressHUD.showNoticeOnStatusBar(message: error.localizedDescription)
            }
        }
    }

    
}
//MARK: 倒计时验证码
func fetchVerificationCodeCountdown(button: UIButton, timeOut: Int) -> DispatchSourceTimer {
    var timeout = timeOut
    let timer = DispatchSource.makeTimerSource(queue: DispatchQueue.global())
    timer.setEventHandler {
        
        if timeout <= 1 {
            timer.cancel()
            
            DispatchQueue.main.sync {
                
                button.setTitle("发送验证码", for: .normal)
                button.isEnabled = true
            }
        } else {
            DispatchQueue.main.sync {
                
                button.setTitle("\(timeout)秒后重发", for: .normal)
                button.isEnabled = false
            }
            timeout -= 1
        }
    }
    timer.scheduleRepeating(deadline: .now(), interval: .seconds(1))
    timer.resume()
    
    return timer
}

//MARK: 抢单管理
var timer: DispatchSourceTimer?

func calculateTime(endDateStr: String, showLabel: UILabel, buttonState:UIButton){
    print(endDateStr)
    
    let df = DateFormatter()
    df.dateFormat = "yyyy-MM-dd HH:mm:ss"
    df.timeZone = TimeZone.current
    let endDate = df.date(from: endDateStr)
    let startDate = Date()
    
    //时间间隔
    let timeInterval : TimeInterval = endDate!.timeIntervalSince(startDate)
    if timer == nil {
        //剩余时间
        var timeout = timeInterval
        //创建全局队列
        let queue = DispatchQueue.global()
        //在全局队列下创建一个时间源
        timer = DispatchSource.makeTimerSource(flags: [], queue: queue)
        //设定循环的间隔是一秒,并且立即开始
        timer?.scheduleRepeating(wallDeadline: DispatchWallTime.now(), interval: .seconds(1))
        //时间源出发事件
        timer?.setEventHandler(handler: {
            if timeout <= 0{
                timer?.cancel()
                timer = nil
                DispatchQueue.main.async(execute: {
                    print("已经过期")
                    buttonState.setTitle("过期", for: .normal)
                    buttonState.layer.borderColor = UIColor.init(hexString: "dbdbdb").cgColor
                    buttonState.setTitleColor(UIColor.init(hexString: "808080"), for: .normal)
                    buttonState.isEnabled = false
                    showLabel.text = "已经过期"
                })
            }else{
                //计算剩余时间
                let days = Int(timeout) / (3600 * 24)
                if days == 0 {
                    
                }
                let hours = (Int(timeout) - Int(days) * 24 * 3600) / 3600
                let minutes = (Int(timeout) - Int(days) * 24 * 3600 - Int(hours) * 3600) / 60
                let seconds = Int(timeout) - Int(days) * 24 * 3600 - Int(hours) * 3600 - Int(minutes) * 60
                DispatchQueue.main.async(execute: {
                    
                    
                    if days == 0 {
                        if minutes >= 20{
                            buttonState.setTitle("准备", for: .normal)
                            buttonState.layer.borderColor = UIColor.init(hexString: "dbdbdb").cgColor
                            buttonState.setTitleColor(UIColor.init(hexString: "808080"), for: .normal)
                            buttonState.isEnabled = false

                            showLabel.text = "\(hours*3600 + minutes * 60 + seconds - 1200)秒后可以抢单"
                            
                        }else{
                            buttonState.isEnabled = true
                            buttonState.setTitle("抢单", for: .normal)
                            buttonState.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
                            buttonState.setTitleColor(UIColor.init(hexString: "EB6900"), for: .normal)
                            if seconds < 10{
                                showLabel.text = "\(hours):\(minutes):0\(seconds)"

                            }else{
                                showLabel.text = "\(hours):\(minutes):\(seconds)"

                            }
                        }
                       

                    } else {
                        if minutes >= 20{
                            
                            buttonState.setTitle("准备", for: .normal)
                            buttonState.layer.borderColor = UIColor.init(hexString: "dbdbdb").cgColor
                            buttonState.setTitleColor(UIColor.init(hexString: "808080"), for: .normal)
                            buttonState.isEnabled = false
                            
                            showLabel.text = "\(hours*3600 + minutes * 60 + seconds - 1200)秒后可以抢单"
                            
                        }else{
                            buttonState.isEnabled = true
                            buttonState.setTitle("抢单", for: .normal)
                            buttonState.layer.borderColor = UIColor.init(hexString: "EB6900").cgColor
                            buttonState.setTitleColor(UIColor.init(hexString: "EB6900"), for: .normal)
                            if seconds < 10{
                                
                                showLabel.text = "\(days)天\(hours)时\(minutes)分0\(seconds)秒"
                            }else{
                                
                                showLabel.text = "\(days)天\(hours)时\(minutes)分\(seconds)秒"

                            }
                        }
                       
                    }
                    
                })
                
                timeout -= 1
            }
        })
        //启动时间源
        timer?.resume()
    }
    
}

var orderTimer: DispatchSourceTimer?
func calculateOrderTime(endDateStr: String, showLabel: UILabel, finishCalculate: (()->())? = nil){
    let df = DateFormatter()
    df.dateFormat = "yyyy-MM-dd HH:mm:ss"
    df.timeZone = TimeZone.current
    let endDate = df.date(from: endDateStr)
    let startDate = Date()
    //时间间隔
    let timeInterval : TimeInterval = endDate!.timeIntervalSince(startDate)
    if orderTimer == nil{
        //剩余时间
        var timeout = timeInterval
        //创建全局队列
        let queue = DispatchQueue.global()
        //在全局队列下创建一个时间源
        orderTimer = DispatchSource.makeTimerSource(flags: [], queue: queue)
        //设定循环的间隔是一秒,并且立即开始
        orderTimer?.scheduleRepeating(wallDeadline: DispatchWallTime.now(), interval: .seconds(1))
        orderTimer?.setEventHandler(handler: {
            if timeout <= 0{
                orderTimer?.cancel()
                orderTimer = nil
                DispatchQueue.main.async(execute: {
                    finishCalculate?()
                    showLabel.text = "超时支付，订单已经被取消"
                })
            }else{
                //计算剩余时间
                let days = Int(timeout) / (3600 * 24)
                if days == 0 {
                    
                }
                
                let hours = (Int(timeout) - Int(days) * 24 * 3600) / 3600
                let minutes = (Int(timeout) - Int(days) * 24 * 3600 - Int(hours) * 3600) / 60
                let seconds = Int(timeout) - Int(days) * 24 * 3600 - Int(hours) * 3600 - Int(minutes) * 60
                showLabel.text = "\(minutes)分\(seconds)秒后未支付，订单将自动删除"
            }
            timeout -= 1
        })
        //启动时间源
        orderTimer?.resume()
    }
    
}
func stringToDate(dateStr: String) -> Date{
    
    let str : NSString = dateStr as NSString
    let subStr = str.substring(to: str.length - 2)
    let df = DateFormatter()
    df.dateFormat = "yyyy-MM-dd HH:mm:ss"
    df.timeZone = TimeZone.current
    let endDate = df.date(from: subStr as String)
    return endDate!
}
/**
 时间戳转时间
 
 
 :param: timeStamp <#timeStamp description#>
 
 :returns: return time
 */
func timeStampToString(timeStamp:String,format:String)->String {
    
    let string = NSString(string: timeStamp)
    
    let timeSta:TimeInterval = string.doubleValue
    let dfmatter = DateFormatter()
    dfmatter.timeStyle = .short
    dfmatter.dateStyle = .medium
    dfmatter.dateFormat = format
    
    let date = NSDate(timeIntervalSince1970: timeSta)
    
    print(dfmatter.string(from: date as Date))
    return dfmatter.string(from: date as Date)
}
//MARK: 拨打电话
func callCustomerServices(atView: UIView, phoneNum: String){
    if phoneNum.characters.count == 0 {
        return
    }
    let callWebView = UIWebView()
    callWebView.loadRequest(URLRequest(url:URL(string: "tel:\(phoneNum)")!))
    atView.addSubview(callWebView)
    
    if #available(iOS 10.0, *){
        UIApplication.shared.open(URL(string:phoneNum)!, options: ["":""], completionHandler: nil)
    } else {
        UIApplication.shared.openURL(URL.init(string: phoneNum)!)
        
        // Fallback on earlier versions
    }
    
//    let alertVC = UIAlertController.init(title: "提示", message: "是否拨打" + phoneNum + "?", preferredStyle: .alert)
//    let okAction = UIAlertAction.init(title: "是", style: .default) { (alert) in
//
//    }
//    let cancelAction = UIAlertAction.init(title: "否", style: .cancel, handler: nil)
//    alertVC.addAction(okAction)
//    alertVC.addAction(cancelAction)
//    atView.responderViewController()?.present(alertVC, animated: true, completion: nil)
   
}
//MARK: json字符串转字典
func getDictionaryFromJSONString(jsonString: String) -> NSDictionary {
    let jsonData : Data = jsonString.data(using: String.Encoding.utf8)!
    let dict = try? JSONSerialization.jsonObject(with: jsonData, options: .mutableContainers)
    if dict != nil{
        return (dict as? NSDictionary)!
    }
    return NSDictionary()
}
//MARK: 键盘管理
func setKeyBoardManagerIsEnable(){
    IQKeyboardManager.sharedManager().enable = true
    IQKeyboardManager.sharedManager().enableAutoToolbar = true
    IQKeyboardManager.sharedManager().shouldResignOnTouchOutside = true
    IQKeyboardManager.sharedManager().toolbarManageBehaviour = .bySubviews
    IQKeyboardManager.sharedManager().toolbarDoneBarButtonItemText = "完成"
    
}

func setKeyBoardManagerDisable(){
    
    IQKeyboardManager.sharedManager().enable = false
    IQKeyboardManager.sharedManager().enableAutoToolbar = false
}
//MARK: 颜色变图片
func getImage(color: UIColor, height: CGFloat) -> UIImage {
    let rect = CGRect(x: 0, y: 0, width: 1, height: height)
    UIGraphicsBeginImageContext(rect.size)
    let context = UIGraphicsGetCurrentContext()
    context?.setFillColor(color.cgColor)
    context?.fill(rect)
    let img = UIGraphicsGetImageFromCurrentImageContext()
    UIGraphicsEndImageContext()
    return img!
}
//MARK: 设置状态栏颜色
func setStatusBarBGColor(color:UIColor){
    let statusBarBG = UIApplication.shared.value(forKey: "statusBarWindow") as! UIView
    let statusBarView = statusBarBG.value(forKey: "statusBar") as! UIView
    statusBarView.backgroundColor = color
}

