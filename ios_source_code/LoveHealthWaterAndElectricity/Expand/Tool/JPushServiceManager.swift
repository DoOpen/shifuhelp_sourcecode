//
//  JPushServiceManager.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/11/1.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit


class JPushServiceManager: NSObject {
    class open func setJPushOptions(withOption: [UIApplicationLaunchOptionsKey: Any]?, jgDelegate:JPUSHRegisterDelegate) {
        JPUSHService.setup(withOption: withOption, appKey: JPushAPPKey, channel: nil, apsForProduction: true)
        let jpush = JPUSHRegisterEntity()
        jpush.types = Int(JPAuthorizationOptions.alert.rawValue) | Int(JPAuthorizationOptions.badge.rawValue) | Int(JPAuthorizationOptions.sound.rawValue)
        
        JPUSHService.register(forRemoteNotificationConfig: jpush, delegate: jgDelegate)
    }
    class func loginJPush(userAlias: String) {
        JPUSHService.setAlias(userAlias, completion: { (code, alias, seq) in
            if code == 0{
                print("极光推送绑定成功")
            }
        }, seq: 1000)
    }
}
