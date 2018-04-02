//
//  ServiceAddressView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/11/6.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class ServiceAddressView: UIView {

    
    @IBOutlet weak var bgBtn: UIButton!
    @IBOutlet weak var backView: UIView!
    
    @IBOutlet weak var address: UILabel!
    
    @IBOutlet weak var detailAddressTF: UITextField!
    var selectAddress : (()->())?
    var updateAddress : (()->())?
    var province: String!
    var city : String!
    var district : String!
    class func show(atView:UIView) -> ServiceAddressView{
        let view = Bundle.main.loadNibNamed("ServiceAddressView", owner: nil, options: nil)?.first as! ServiceAddressView
        atView.addSubview(view)
        view.showView()
        return view
    }
    private func showView(){
        var frame = self.backView.frame
        let old = frame
        self.alpha = 0.1
        frame.origin.y = self.frame.size.height
        self.backView.frame = frame
        UIView.animateKeyframes(withDuration: 0.25, delay: 0.1, options: .calculationModePaced, animations: {
            self.backView.frame = old
            self.bgBtn.alpha = 0.2
            self.alpha = 1
        }, completion: nil)
    }
    func remove(){
        var frame = self.backView.frame
        frame.origin.y += self.frame.size.height
        UIView.animate(withDuration: 0.25, animations: {
            self.backView.frame = frame
            self.backView.alpha = 0.1
            
        }) { (finish) in
            self.removeFromSuperview()
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        self.frame = CGRect(x: 0, y: -64, width: kScreenWidth, height: kScreenHeight)
        self.bgBtn.backgroundColor = UIColor(hexString: "#333333")
        self.bgBtn.alpha = 0.2
    }
    
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */

    @IBAction func dismissAction(_ sender: UIButton) {
        self.remove()
    }
    @IBAction func selectAddressAction(_ sender: UIButton) {
        self.selectAddress?()
    }
    
    @IBAction func confirmAction(_ sender: UIButton) {
        if self.address.text?.characters.count == 0{
            ProgressHUD.showMessage(message: "请选择省市区")
            return
        }
        if (self.detailAddressTF.text?.isEmpty)!{
            ProgressHUD.showMessage(message: "请填写详细地址")
            return
        }
        let params : Dictionary<String,String> = ["member_service_phone":(LHWEUserInfoHandler.getUserInfo()?.member_phone)!,"member_service_name":(LHWEUserInfoHandler.getUserInfo()?.member_real_name)!,"member_service_province":self.province,"member_service_city":self.city,"member_service_detail":self.detailAddressTF.text!,"zip_code":"","member_service_district":self.district]
        
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?updateMemberDetail", at: self, params: params, success: { (response) in
            self.updateAddress?()
            NotificationCenter.default.post(name: NSNotification.Name(rawValue:ReloadHomeServiceAddressNotification), object: nil, userInfo: ["address":self.district])
            self.remove()
        }, failure: {
            ProgressHUD.showMessage(message: "请重新设置")
        })
    }
}
