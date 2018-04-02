//
//  Extension.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/16.
//  Copyright © 2017年 tts. All rights reserved.
//

import Foundation
import UIKit

extension String {
    // 是否全是数字
    var isPureInt: Bool {
        let scan = Scanner(string: self)
        var val: Int = 0
        return scan.scanInt(&val) && scan.isAtEnd
    }
    // 16进制
    var isHexCharacter: Bool {
        let regex = "^[0-9a-fA-F]+$"
        let predicate = NSPredicate(format: "SELF MATCHES %@", regex)
        return predicate.evaluate(with: self)
    }
    /// 密码限制
    var isProperPassword: Bool {
        if self.characters.count < 6 {
            return false
        }
        let regex = "^[0-9A-Za-z]{6,16}$"
        let predicate = NSPredicate(format: "SELF MATCHES %@", regex)
        return predicate.evaluate(with: self)
    }
    /// 验证手机号
    var isPhoneNumber: Bool {
        let regex = "^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(17[^2,^4,^9,\\D])|(18[0-9]))\\d{8}$"
        let predicate = NSPredicate(format: "SELF MATCHES %@", regex)
        return predicate.evaluate(with: self)
    }
    /// 验证固定电话
    var isTelephone : Bool{
        let regex = "^0(10|2[0-5789]|\\d{3})\\d{7,8}$"
        let predicate = NSPredicate(format: "SELF MATCHES %@", regex)
        return predicate.evaluate(with:self)
    }
    
    /// 验证邮编号
    var isZipcode: Bool {
        let regex = "[1-9]\\d{5}(?!\\d)"
        let predicate = NSPredicate(format: "SELF MATCHES %@", regex)
        return predicate.evaluate(with: self)
    }
    /// 验证银行卡
    var isBankCard: Bool {
        var oddSum = 0     //奇数求和
        var evenSum = 0    //偶数求和
        var allSum = 0
        
        let count = self.characters.count
        if  count < 15 || count > 19 {
            print("银行卡号位数不对，一般15-19位，该卡号\(count)位")
            return false
        }
        let array = Array(self.characters)
        
        for (i, value) in array.enumerated().reversed() {
            guard let t = Int(String.init(value)) else {
                print("银行卡号应该全是数组，你输入了其他字符")
                return false
            }
            let index = count - i
            if index % 2 == 0 {
                let temp = t * 2
                evenSum += temp > 9 ? temp - 9 : temp
            } else {
                oddSum += t
            }
        }
        allSum = oddSum + evenSum
        if allSum % 10 == 0 {
            print("💐、银行卡号格式正确")
            return true
        }
        print("银行卡号格式不对")
        return false
    }
    /// 精确验证身份证号
    var isIDCard: Bool {
        var msg: String
        let count = self.characters.count
        
        if count != 18, count != 15 {
            msg = "中国公民身份证的长度应为15位或者18位，而您输入的长度为: \(self.characters.count)位"
            print(msg)
            return false
        }
        // 地区码
        let areas = ["11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91"]
        let areaCode = self.substring(to: self.at(2))
        if !areas.contains(areaCode) {
            print("不存在地区码: \(areaCode)")
            return false
        }
        var id = self
        if count == 15 {
            // 将15位转为18位
            id.insert(Character("1"), at: self.at(6))
            id.insert(Character("9"), at: id.at(7))
            id.append("1")
            print("15位: \(self), 被转为18位: \(id)")
        }
        let year = Int(id.substring(with: id.at(6)..<id.at(10)))!
        var regular = "^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9X|x]$"
        // 闰年
        if year % 400 == 0 || (year % 4 == 0 && year % 100 != 0) {
            regular = "^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9X|x]$"
        }
        let predicate = NSPredicate(format: "SELF MATCHES %@", regular)
        let isMatch = predicate.evaluate(with: id)
        if count == 15, isMatch {
            print("满足15位身份证格式")
            return true
        }
        // 18 位
        if isMatch {
            // 校验位计算，对应系数
            let conefficient = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]
            var num = 0
            for i in 0..<count - 1 {
                num += Int(self.substring(with: self.at(i)..<self.at(i + 1)))! * conefficient[i]
            }
            // 结果校验码
            let checkCode = ["1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"]
            if checkCode[num % 11] == self.substring(from: self.index(before: self.endIndex)).uppercased() {
                print("满足18位身份证格式")
                return true
            } else {
                print("校验码不对, 正常应为: \(checkCode[num % 11])")
            }
        } else {
            print("可能年月日格式不对")
        }
        return false
    }
    
    func at(_ index: String.IndexDistance) -> String.Index {
        
        return self.index(self.startIndex, offsetBy: index)
    }
}
/*****************************************************************************/
typealias Task = (_ cancel : Bool) -> Void
func delay(_ time: TimeInterval, task: @escaping ()->()) -> Task? {
    func dispatch_later(block: @escaping ()->()) {
        let t = DispatchTime.now() + time
        DispatchQueue.main.asyncAfter(deadline: t, execute: block)
    }
    var closure: (()->Void)? = task
    var result: Task?
    
    let delayedClosure: Task = {
        cancel in
        if let internalClosure = closure {
            if (cancel == false) {
                DispatchQueue.main.async(execute: internalClosure)
            }
        }
        closure = nil
        result = nil
    }
    
    result = delayedClosure
    
    dispatch_later {
        if let delayedClosure = result {
            delayedClosure(false)
        }
    }
    return result
}
func cancel(_ task: Task?) {
    task?(true)
}
/*****************************************************************************/

extension UIView {
    // x
    var x : CGFloat {
        
        get {
            
            return frame.origin.x
        }
        
        set(newVal) {
            
            var tmpFrame : CGRect = frame
            tmpFrame.origin.x     = newVal
            frame                 = tmpFrame
        }
    }
    
    // y
    var y : CGFloat {
        
        get {
            
            return frame.origin.y
        }
        
        set(newVal) {
            
            var tmpFrame : CGRect = frame
            tmpFrame.origin.y     = newVal
            frame                 = tmpFrame
        }
    }
    
    // height
    var height : CGFloat {
        
        get {
            
            return frame.size.height
        }
        
        set(newVal) {
            
            var tmpFrame : CGRect = frame
            tmpFrame.size.height  = newVal
            frame                 = tmpFrame
        }
    }
    
    // width
    var width : CGFloat {
        
        get {
            
            return frame.size.width
        }
        
        set(newVal) {
            
            var tmpFrame : CGRect = frame
            tmpFrame.size.width   = newVal
            frame                 = tmpFrame
        }
    }
    
    // left
    var left : CGFloat {
        
        get {
            
            return x
        }
        
        set(newVal) {
            
            x = newVal
        }
    }
    
    // right
    var right : CGFloat {
        
        get {
            
            return x + width
        }
        
        set(newVal) {
            
            x = newVal - width
        }
    }
    
    // top
    var top : CGFloat {
        
        get {
            
            return y
        }
        
        set(newVal) {
            
            y = newVal
        }
    }
    
    // bottom
    var bottom : CGFloat {
        
        get {
            
            return y + height
        }
        
        set(newVal) {
            
            y = newVal - height
        }
    }
    
    var centerX : CGFloat {
        
        get {
            
            return center.x
        }
        
        set(newVal) {
            
            center = CGPoint(x: newVal, y: center.y)
        }
    }
    
    var centerY : CGFloat {
        
        get {
            
            return center.y
        }
        
        set(newVal) {
            
            center = CGPoint(x: center.x, y: newVal)
        }
    }
    
    var middleX : CGFloat {
        
        get {
            
            return width / 2
        }
    }
    
    var middleY : CGFloat {
        
        get {
            
            return height / 2
        }
    }
    
    var middlePoint : CGPoint {
        
        get {
            
            return CGPoint(x: middleX, y: middleY)
        }
    }
    
    //查找vc
    func responderViewController() -> UIViewController? {
        var next = self.superview
        while next != nil {
            let responder = next?.next
            if responder!.isKind(of: UIViewController.self) {
                return responder as? UIViewController
            }
            next = next?.superview
        }
        return nil
    }
    
}
extension UIBarButtonItem {
    convenience init(title: String, target: Any?, action: Selector?) {
        self.init(title: title, style: .done, target: target, action: action)
        self.setTitleTextAttributes([NSForegroundColorAttributeName: UIColor.white, NSFontAttributeName: UIFont.systemFont(ofSize: 16)], for: .normal)
    }
}

extension UINavigationBar {
    func backgroundColor(_ color: UIColor) {
        self.subviews.first?.subviews.first?.backgroundColor = color
    }
}
enum ButtonImageForwardType : Int {
    case LeftForward = 0
    case BottomForward
}
extension UIButton{
    func setImageForwardLayout(btnType: ButtonImageForwardType) {
        
        let titleFrame = self.titleLabel?.frame
        let imageFrame = self.imageView?.frame
       
        let space : CGFloat = (titleFrame?.origin.x)! - (imageFrame?.origin.x)! - (imageFrame?.size.width)!
        if btnType == .LeftForward{
            
            self.imageEdgeInsets = UIEdgeInsetsMake(0, (titleFrame?.size.width)!, 0, -((titleFrame?.size.width)! + space))
            if kScreenHeight == CGFloat(IPHONE5_5S_SEHeight){
                self.titleEdgeInsets = UIEdgeInsetsMake(0, ((titleFrame?.origin.x)! - (imageFrame?.origin.x)!) + 30, 0, 0)
            }else{
                self.titleEdgeInsets = UIEdgeInsetsMake(0, ((titleFrame?.origin.x)! - (imageFrame?.origin.x)!), 0, 0)
            }
            
            
            
        }else if btnType == .BottomForward{
            
            self.titleLabel?.textAlignment = .center
            
            self.imageEdgeInsets = UIEdgeInsetsMake(0, -((titleFrame?.size.width)! - (imageFrame?.size.width)!)/2, (titleFrame?.size.height)! + space + 5, -((titleFrame?.size.width)!))
            self.titleEdgeInsets = UIEdgeInsetsMake((imageFrame?.size.height)!, -((imageFrame?.size.width)!), 0, 0)
        }
        
        
    }
}
extension UIColor {
    public convenience init(hexString: String) {
        let hexString = hexString.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
        let scanner   = Scanner(string: hexString)
        
        if hexString.hasPrefix("#") {
            scanner.scanLocation = 1
        }
        var color: UInt32 = 0
        
        if scanner.scanHexInt32(&color) {
            self.init(hex: color)
        }
        else {
            self.init(hex: 0x000000)
        }
    }
    public convenience init(hex: UInt32) {
        let mask = 0x000000FF
        
        let r = Int(hex >> 16) & mask
        let g = Int(hex >> 8) & mask
        let b = Int(hex) & mask
        
        let red   = CGFloat(r) / 255
        let green = CGFloat(g) / 255
        let blue  = CGFloat(b) / 255
        
        self.init(red:red, green:green, blue:blue, alpha:1)
    }
}
extension Timer {
    // 暂停
    func pause() {
        if self.isValid {
            self.fireDate = Date.distantFuture
        }
    }
    // 重新开始
    func resume() {
        if self.isValid {
            self.fireDate = Date()
        }
    }
    func resumeAfter(timeInterval: TimeInterval) {
        if self.isValid {
            self.fireDate = Date(timeIntervalSinceNow: timeInterval)
        }
    }
}
extension Array{
    func enumerateKeysAndObjects(option:(Element,Int)->()){
        for i in 0  ..< self.count  {
            option(self[i],i)
        }
    }
}
/**Dictionary*/
extension Dictionary {
    /**得到所有键 数组*/
    func  allKeys() ->[Key]{
        var array = [Key]()
        var  gener = self.keys.makeIterator()
        while let key = gener.next() {
            array.append(key)
        }
        return array;
    }
    /**遍历字典*/
    func enumerateKeysAndObjects(option:(Key,Value,Int)->()){
        let allK = self.allKeys()
        for i in 0  ..< allK.count  {
            let value = self[allK[i]]
            option(allK[i],value!,i)
        }
    }
}

