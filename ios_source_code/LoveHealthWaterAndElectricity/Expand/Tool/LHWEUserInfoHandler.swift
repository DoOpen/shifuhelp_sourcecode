//
//  LHWEUserInfoHandler.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/16.
//  Copyright © 2017年 tts. All rights reserved.
//

import Foundation


import Foundation


class LHWEUserInfoModel: HandyJSON {
    
    var member_id : String?
    var member_account : String?
    var member_password : String?
    var member_token : String?
    var member_real_name : String? //真实姓名
    var member_nick_name : String? //昵称
    var member_phone : String?     //电话
    var member_type : String?      //0:用户，1:师傅。
    var member_head_image : String?//头像
    var member_sex : String?       //性别
    var member_age : String?       //年龄
    var member_work_age : String?  //工龄
    var member_work_type : String? //工种
    var is_sign : String?          //是否签到
    var member_state : String?     //是否认证 -1未审核 0审核中 1审核通过
    var member_deposit_money : String? //是否交押金 不为空则已付押金
    var nead_deposit : String?     //押金
    var member_service_name : String?
    var member_service_phone : String?
    var member_service_country : String?
    var member_service_province : String?
    var member_service_city : String?
    var member_service_district : String?
    var member_service_detail : String?
    var member_certificate : String? //证书URL
    required init(){}
}

class LHWEUserInfoHandler: NSObject {
    
    class func saveUserInfo(model: LHWEUserInfoModel){
        
        JPushServiceManager.loginJPush(userAlias: model.member_id!)
        
        UserDefaults.standard.set(model.member_token, forKey: "member_token")
        UserDefaults.standard.set(model.member_id, forKey: "member_id")
        UserDefaults.standard.set(model.member_nick_name, forKey: "member_nick_name")
        UserDefaults.standard.set(model.member_sex, forKey: "member_sex")
        UserDefaults.standard.set(NetworkingHandle.mainHost + model.member_head_image!, forKey: "member_head_image")
        UserDefaults.standard.set(model.member_age, forKey: "member_age")
        UserDefaults.standard.set(model.member_phone, forKey: "member_phone")
        UserDefaults.standard.set(model.member_work_age, forKey: "member_work_age")
        UserDefaults.standard.set(model.member_state, forKey: "member_state")
        UserDefaults.standard.set(model.member_deposit_money, forKey: "member_deposit_money")
        UserDefaults.standard.set(model.nead_deposit, forKey: "nead_deposit")
        UserDefaults.standard.set(model.member_real_name, forKey: "member_real_name")
        UserDefaults.standard.set(model.is_sign, forKey: "is_sign")
        UserDefaults.standard.set(NetworkingHandle.mainHost + model.member_certificate!, forKey: "member_certificate")
        UserDefaults.standard.set(model.member_service_city, forKey: "member_service_city")
        UserDefaults.standard.set(model.member_service_district, forKey: "member_service_district")
        
    }
    class func saveBaseUserInfo(model: LHWEUserInfoModel){
        UserDefaults.standard.set(model.member_real_name, forKey: "member_real_name")
        UserDefaults.standard.set(model.member_sex, forKey: "member_sex")
        UserDefaults.standard.set(model.member_age, forKey: "member_age")
        UserDefaults.standard.set(model.member_work_type, forKey: "member_work_type")
        UserDefaults.standard.set(model.member_work_age, forKey: "member_work_age")
        UserDefaults.standard.set(model.member_service_city, forKey: "member_service_city")
        UserDefaults.standard.set(model.member_service_district, forKey: "member_service_district")
    }
    class func getBaseUserInfo() -> LHWEUserInfoModel{
        let model = LHWEUserInfoModel()
        model.member_service_city = UserDefaults.standard.object(forKey: "member_service_city") as? String ?? ""
        model.member_service_district = UserDefaults.standard.object(forKey: "member_service_district") as? String ?? ""
        return model
    }
    class func getUserInfo() -> LHWEUserInfoModel? {
        if UserDefaults.standard.object(forKey: "member_token") == nil {
            return nil
        }
        let model = LHWEUserInfoModel()
        model.member_token = UserDefaults.standard.object(forKey: "member_token") as? String ?? ""
        model.member_id = UserDefaults.standard.object(forKey: "member_id") as? String ?? ""
        model.member_nick_name = UserDefaults.standard.object(forKey: "member_nick_name") as? String ?? ""
        model.member_sex = UserDefaults.standard.object(forKey: "member_sex") as? String ?? ""
        model.member_head_image = UserDefaults.standard.object(forKey: "member_head_image") as? String ?? ""
        model.member_age = UserDefaults.standard.object(forKey: "member_age") as? String ?? ""
        model.member_work_type = UserDefaults.standard.object(forKey: "member_work_type") as? String ?? ""
        model.member_phone = UserDefaults.standard.object(forKey: "member_phone") as? String ?? ""
        model.member_state = UserDefaults.standard.object(forKey: "member_state") as? String ?? ""
        model.member_deposit_money = UserDefaults.standard.object(forKey: "member_deposit_money") as? String ?? ""
        model.member_real_name = UserDefaults.standard.object(forKey: "member_real_name") as? String ?? ""
        model.nead_deposit = UserDefaults.standard.object(forKey: "nead_deposit") as? String ?? ""
        model.member_work_age = UserDefaults.standard.object(forKey: "member_work_age") as? String ?? ""
        model.member_service_city = UserDefaults.standard.object(forKey: "member_service_city") as? String ?? ""
        model.is_sign = UserDefaults.standard.object(forKey: "is_sign") as? String ?? ""
        model.member_certificate = UserDefaults.standard.object(forKey: "member_certificate") as? String ?? ""
        model.member_service_district = UserDefaults.standard.object(forKey: "member_service_district") as? String ?? ""
        return model
    }
    class func getUserIDAndToken() -> (id: String, token: String)? {
        if UserDefaults.standard.object(forKey: "member_token") == nil {
            return nil
        }
        let token = UserDefaults.standard.object(forKey: "member_token") as? String ?? ""
        let user_id = UserDefaults.standard.object(forKey: "member_id") as? String ?? ""
        return (user_id, token)
    }
    class func updateImg(img: String){
        UserDefaults.standard.set(img, forKey: "member_head_image")
    }
    class func deleteUserInfo(){
        
        UserDefaults.standard.removeObject(forKey: "member_token")
       
    }
}
