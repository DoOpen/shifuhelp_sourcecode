//
//  AppDelegate.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/16.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import IQKeyboardManagerSwift
import UserNotifications

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate, JPUSHRegisterDelegate,  UNUserNotificationCenterDelegate{

    var window: UIWindow?


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.

       
        setKeyBoardManagerDisable()
        
        UMengManager.setUMengOptions()
       
        JPushServiceManager.setJPushOptions(withOption: launchOptions, jgDelegate: self)
        
        if LHWEUserInfoHandler.getUserIDAndToken() == nil {
            
            self.window?.rootViewController = LoginNavigationController.setup()
            
        }else{
            if let m = LHWEUserInfoHandler.getUserInfo() {
                if m.member_state == "-1"{
                    self.window?.rootViewController = LoginNavigationController.setup()
                }else{
                   self.window?.rootViewController = BaseTabBarController()
                }
            }
        }
        return true
    }
    
    func application(_ app: UIApplication, open url: URL, options: [UIApplicationOpenURLOptionsKey : Any] = [:]) -> Bool {
        let str = String(describing: url)
        if str.range(of: "pay/?") != nil{
            let cancelUrl = Pingpp.handleOpen(url) { (response, error) in
                if error == nil{
                    NotificationCenter.default.post(name: NSNotification.Name(rawValue:PaySuccessNotification), object: nil)
                } else {
                    NotificationCenter.default.post(name: NSNotification.Name(rawValue: PayFailNotification), object: nil)
                }
            }
            return cancelUrl
        }
        return  UMSocialManager.default().handleOpen(url)
    }
    func application(_ application: UIApplication, open url: URL, sourceApplication: String?, annotation: Any) -> Bool {
        let str = String(describing: url)
        if str.range(of: "pay/?") != nil{
            let cancelUrl = Pingpp.handleOpen(url) { (response, error) in
                if error == nil{
                    NotificationCenter.default.post(name: NSNotification.Name(rawValue: "paySuccess"), object: nil)
                } else {
                    NotificationCenter.default.post(name: NSNotification.Name(rawValue: "payFail"), object: nil)
                }
            }
            return cancelUrl
        }
        return  UMSocialManager.default().handleOpen(url)
    }
    func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
        
        JPUSHService.registerDeviceToken(deviceToken)
        print(deviceToken)
    }
    func application(_ application: UIApplication, didReceiveRemoteNotification userInfo: [AnyHashable : Any], fetchCompletionHandler completionHandler: @escaping (UIBackgroundFetchResult) -> Void) {
        completionHandler(.newData)
        if UIApplication.shared.applicationState == UIApplicationState.active {
            ProgressHUD.showNoticeOnStatusBar(message: "收到新的派单，请及时接受!")
        } else if UIApplication.shared.applicationState == UIApplicationState.inactive {
           NotificationCenter.default.post(name: NSNotification.Name(rawValue:JumpToWorkOrderVCNotification), object: nil)
        }
        
        
    }
    func applicationWillResignActive(_ application: UIApplication) {
        // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
        // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
        application.applicationIconBadgeNumber = 0
        application.cancelAllLocalNotifications()
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
    }

    func applicationWillTerminate(_ application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
    }
    //MARK: -极光代理
    @available(iOS 10.0, *)

    func jpushNotificationCenter(_ center: UNUserNotificationCenter!, didReceive response: UNNotificationResponse!, withCompletionHandler completionHandler: (() -> Void)!) {
        if response.notification.request.trigger is UNPushNotificationTrigger{
            JPUSHService.handleRemoteNotification(response.notification.request.content.userInfo)
            let userInfoDic = response.notification.request.content.userInfo as! [String: AnyObject]
            print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            print(userInfoDic)
            if UIApplication.shared.applicationState == UIApplicationState.active {
                ProgressHUD.showNoticeOnStatusBar(message: "收到新的派单，请及时接受!")
            } else if UIApplication.shared.applicationState == UIApplicationState.inactive {
                NotificationCenter.default.post(name: NSNotification.Name(rawValue:JumpToWorkOrderVCNotification), object: nil)
            }
            
            
        }
        completionHandler()
    }
    @available(iOS 10.0, *)
    func jpushNotificationCenter(_ center: UNUserNotificationCenter!, willPresent notification: UNNotification!, withCompletionHandler completionHandler: ((Int) -> Void)!) {

        if notification.request.trigger is UNPushNotificationTrigger {
            JPUSHService.handleRemoteNotification(notification.request.content.userInfo)
        }
        completionHandler(7)
    }
    func convertToDictionary(text: String) -> [String: Any]? {
        if let data = text.data(using: .utf8) {
            do {
                return try JSONSerialization.jsonObject(with: data, options: []) as? [String: Any]
            } catch {
                print(error.localizedDescription)
            }
        }
        return nil
    }


}

