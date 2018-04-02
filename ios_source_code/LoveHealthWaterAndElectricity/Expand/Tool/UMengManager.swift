//
//  UMengManager.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/16.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class UMengManager: NSObject {

    class open func setUMengOptions(){
        UMSocialManager.default().openLog(true)
        UMSocialManager.default().umSocialAppkey = UMengKey
        UMSocialManager.default().setPlaform(UMSocialPlatformType.wechatSession, appKey: WXAppID, appSecret: WXAppSecret, redirectURL: "")
        UMSocialManager.default().setPlaform(UMSocialPlatformType.QQ, appKey: QQAppID, appSecret: QQAppSecret, redirectURL: "")
    }
    class open func shareGoodsTo3rdPlatform(type: UMSocialPlatformType, atVC:UIViewController, shareURL: String, goodsTitle:String, goodsDesc: String, goodsImg: String, success : (()->())? = nil, failure : @escaping (String)->()){
        let object = UMShareWebpageObject()
        object.webpageUrl = shareURL
        object.title = goodsTitle
        object.descr = goodsDesc
        let data = try? Data.init(contentsOf: URL.init(string: NetworkingHandle.mainHost + goodsImg)!)
        let image = UIImage.init(data: data!)
        object.thumbImage = image
        
        let messageObject = UMSocialMessageObject(mediaObject: object)
        
        UMSocialManager.default().share(to: type, messageObject: messageObject, currentViewController: atVC) { (data, error) in
            if error == nil {
                success?()
            } else {
                failure((error?.localizedDescription)!)
                
            }
        }
    }
    class open func shareTo3rdPlatform(type: UMSocialPlatformType, atVC:UIViewController, shareURL: String, success : (()->())? = nil, failure : @escaping (String)->()){
        let object = UMShareWebpageObject()
        object.webpageUrl = "http://wx.shifuhelp.com/extension.html"
        object.title = "师傅上门Pro"
        object.descr = "师傅上门Pro是一款专业的安装维修服务手机软件，让安装维修从此变得更便捷，赶紧下载体验吧！"
        object.thumbImage = #imageLiteral(resourceName: "logo")
        
        let messageObject = UMSocialMessageObject(mediaObject: object)
        
        UMSocialManager.default().share(to: type, messageObject: messageObject, currentViewController: atVC) { (data, error) in
            if error == nil {
                success?()
            } else {
                failure((error?.localizedDescription)!)
               
            }
        }
    }
}
