//
//  LoginViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/16.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import IQKeyboardManagerSwift
class LocationManager: NSObject, CLLocationManagerDelegate {
    let manager = CLLocationManager()
    var log = "121.495832"
    var lag = "31.166807"
    var city = "上海市"
    var district = ""
    static var shared = LocationManager()
    
    private override init() {
        super.init()
        manager.desiredAccuracy = kCLLocationAccuracyBest
        manager.delegate = self
        manager.distanceFilter = 100
        manager.requestWhenInUseAuthorization()
        updateLocation()
    }
    
    func updateLocation() {
        if (CLLocationManager.locationServicesEnabled()) {
            manager.startUpdatingLocation()
            print("定位开始")
        }
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        let location = locations.last
        let coordinate: CLLocationCoordinate2D = (location?.coordinate)!
        manager.stopUpdatingLocation()
        
        print(coordinate.longitude)
        print(coordinate.latitude)
        
        lag = String(coordinate.latitude)
        log = String(coordinate.longitude)
        
        lonLatToCity(location: location!)
    }
    
    func lonLatToCity(location: CLLocation) {
        CLGeocoder().reverseGeocodeLocation(location) { (placemark, error) -> Void in
            if error == nil {
                let mark = placemark?.first
                //这个是城市
                self.city = mark?.addressDictionary?["City"] as? String ?? "上海市"
                self.district = mark?.addressDictionary?["SubLocality"] as? String ?? "上海市"
                NotificationCenter.default.post(name: NSNotification.Name(rawValue:"locationUpdate"), object: nil, userInfo: ["district":self.district])
            }
        }
    }
}

class LoginViewController: UIViewController{

    @IBOutlet weak var phoneTF: UITextField!
    
    @IBOutlet weak var passwordTF: UITextField!
    
    @IBOutlet weak var loginBtn: UIButton!
    
    
    var eyeBtn = UIButton.init(type: .custom)
    
    override func viewDidLoad() {
        super.viewDidLoad()

        LocationManager.shared.updateLocation()
        if let phone = UserDefaults.standard.object(forKey: "member_phone") as? String{
            if phone.characters.count != 0{
                self.phoneTF.text = phone

            }
        }

        self.phoneTF.layer.cornerRadius = kCornerRadius
        self.passwordTF.layer.cornerRadius = kCornerRadius
        self.loginBtn.layer.cornerRadius = kCornerRadius
        
        self.phoneTF.leftView = UIImageView.init(image: #imageLiteral(resourceName: "shouji"))
        self.passwordTF.leftView = UIImageView.init(image: #imageLiteral(resourceName: "mima"))
        self.phoneTF.leftViewMode = .always
        self.passwordTF.leftViewMode = .always
        
        self.passwordTF.rightViewMode = .always
        self.passwordTF.isSecureTextEntry = true
        let rightView = UIView(frame: CGRect.init(x: 0, y: 0, width: 40, height: 49))
        eyeBtn.setImage(#imageLiteral(resourceName: "eye"), for: .normal)
        eyeBtn.setImage(#imageLiteral(resourceName: "eye_h"), for: .selected)
        eyeBtn.addTarget(self, action: #selector(LoginViewController.passwordSecurity(sender:)), for: .touchUpInside)
        eyeBtn.frame = CGRect.init(x: 0, y: 0, width: 40, height: 49)
        rightView.addSubview(eyeBtn)
        self.passwordTF.rightView = rightView
        
        
        
        // Do any additional setup after loading the view.
    }
    func passwordSecurity(sender: UIButton) {
        sender.isSelected = !sender.isSelected
        self.passwordTF.isSecureTextEntry = !sender.isSelected
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func registerAction(_ sender: UIButton) {
        
        self.navigationController?.pushViewController(RegisterViewController(), animated: true)
    }

    @IBAction func LoginAction(_ sender: UIButton) {
        
        if !(phoneTF.text?.isPhoneNumber)!{
            ProgressHUD.showMessage(message: "手机号格式错误")
            return
        }
        if passwordTF.text == nil {
            ProgressHUD.showMessage(message: "密码不能为空")
            return
        }
        
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?memberLogin", at: self, params: ["member_account":phoneTF.text!,"member_password":passwordTF.text!], isAuthHide: true, isShowHUD: true, isShowError: true, hasHeaderRefresh: nil, success: { (response) in
            ProgressHUD.hideLoading(toView: self.view)
            self.view.endEditing(true)
            let data = response["data"] as? NSDictionary
            let userModel = LHWEUserInfoModel.deserialize(from: data)
            LHWEUserInfoHandler.saveUserInfo(model: userModel!)
            if userModel?.member_state == "-1"{
                self.navigationController?.pushViewController(SubmitPersonInformationViewController(), animated: true)
            }else if userModel?.member_state == "0"{
                if userModel?.member_real_name?.count != 0 || userModel?.member_head_image?.count != 0 || userModel?.member_phone?.count != 0{
                    self.navigationController?.pushViewController(CheckInformationViewController(), animated: true)
                }else{
                self.navigationController?.pushViewController(SubmitPersonInformationViewController(), animated: true)
                }
                
                
            }else{
                
                UIApplication.shared.keyWindow?.rootViewController = BaseTabBarController()
            }

        }) { 
            
        }
    }
    override func viewWillAppear(_ animated: Bool) {
        setStatusBarBGColor(color: UIColor.white)
        setKeyBoardManagerIsEnable()
        self.navigationController?.setNavigationBarHidden(true, animated: false)
        
    }
    override func viewWillDisappear(_ animated: Bool) {
        setKeyBoardManagerDisable()
        self.navigationController?.setNavigationBarHidden(false, animated: false)
    }
    @IBAction func forgetPassword(_ sender: UIButton) {
        let vc = ForgetPasswordViewController()
        vc.isFromLogin = true
        vc.title = "忘记密码"
        self.navigationController?.pushViewController(vc, animated: true)
    }
    deinit {
        self.navigationController?.navigationBar.setBackgroundImage(nil, for: .default)
        self.navigationController?.navigationBar.shadowImage = nil
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
