//
//  ProgressHUD.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/16.
//  Copyright © 2017年 tts. All rights reserved.
//

import Foundation
import UIKit

class ProgressHUD: NSObject {
    static func showSuccess(message: String) -> () {
        SwiftNotice.showNoticeWithText(.success, text: message, autoClear: true, autoClearTime: 3)
    }
    static func showMessage(message: String) -> () {
        UIApplication.shared.keyWindow?.showMessage(message, interval: 1, position: "center" as AnyObject)
    }
    static func showLoading(toView: UIView, message: String = "努力加载中...") -> () {
        toView.showLoadingTilteActivity(message, position: "center" as AnyObject?)
    }
    static func hideLoading(toView: UIView) -> () {
        toView.hideActivity()
    }
    static func showNoticeOnStatusBar(message: String) {
        SwiftNotice.noticeOnStatusBar(message, autoClear: true, autoClearTime: 3)
    }
}
