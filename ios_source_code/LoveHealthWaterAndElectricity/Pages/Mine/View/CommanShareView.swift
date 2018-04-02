//
//  CommanShareView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/15.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class CommanShareView: UIView {

    @IBOutlet weak var bgBtn: UIButton!
    
    @IBOutlet weak var backView: UIView!
    
    var urls: String?
    var avatars: String?
    var usernames: String?
    var desc : String?
    var isMineVC = true
    class func show(atView: UIView,url: String,avatar: String, username: String, desc: String, isMineVC : Bool) -> CommanShareView {
        
        let view = Bundle.main.loadNibNamed("CommanShareView", owner: nil
            , options: nil)!.first as! CommanShareView
        view.avatars = avatar
        view.usernames = username
        view.urls = url
        view.desc = desc
        if isMineVC {
            view.frame = CGRect(x: 0, y: 0, width: kScreenWidth, height: kScreenHeight)
        }else{
            view.frame = CGRect(x: 0, y: -64 , width: kScreenWidth, height: kScreenHeight)
        }
        
        atView.addSubview(view)
        view.showTheView()
        return view
    }
    private  func showTheView() {
        var frame = self.backView.frame
        let oldFrame =  frame
        self.alpha = 0.1
        frame.origin.y = self.frame.size.height
        self.backView.frame = frame
        UIView.animate(withDuration: 0.25, animations: {
            self.backView.frame = oldFrame
            // self.bgBtn.alpha = 0.2
            self.alpha = 1
        })
    }
    private func dismiss() {
        
        var frame = self.backView.frame
        frame.origin.y += frame.size.height
        UIView.animate(withDuration: 0.25, animations: {
            self.backView.frame = frame
            
            self.alpha = 0.1
        }) { (finished) in
            self.removeFromSuperview()
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()

        self.bgBtn.backgroundColor = UIColor(hexString: "#333333")
        self.bgBtn.alpha = 0.2
    }
    
    @IBAction func dismissAction(_ sender: UIButton) {
        self.dismiss()
    }
    @IBAction func ShareBtnAction(_ sender: UIButton) {
        switch sender.tag {
        case 1000:
            shareAction(type: .wechatTimeLine)
        case 1001:
            shareAction(type: .QQ)
        case 1002:
            shareAction(type: .wechatSession)
        case 1003:
            shareAction(type: .qzone)
        default:
            break
        }
    }
    func shareAction(type: UMSocialPlatformType) {
        
        var vc = self.responderViewController()
        if vc == nil{
            vc = MineViewController.init()
            UMengManager.shareTo3rdPlatform(type: type, atVC: vc!, shareURL: self.urls!, success: {
                ProgressHUD.showSuccess(message: "分享成功")
                
                
            }) { (error) in
                
                print(error)
                if error.contains("message=Operation is canel"){
                    ProgressHUD.showMessage(message: "取消授权")
                }else{
                    ProgressHUD.showMessage(message: "分享失败")
                }
            }
        }else{
            UMengManager.shareGoodsTo3rdPlatform(type: type, atVC: vc!, shareURL: self.urls!, goodsTitle: usernames!, goodsDesc: desc!, goodsImg: avatars!, success: {
                ProgressHUD.showSuccess(message: "分享成功")
                
            }, failure: { (error) in
                
            })
        }
        self.dismiss()
        
        
    }
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */

}
