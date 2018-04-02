//
//  MineCollectionReusableView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/21.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class MineCollectionReusableView: UICollectionReusableView {

    @IBOutlet weak var userImg: UIImageView!
    
    @IBOutlet weak var signInBtn: UIButton!
    
    @IBOutlet weak var user: UILabel!
    
    @IBOutlet weak var userImgSize: NSLayoutConstraint!
    
    @IBOutlet weak var SignBtnBottomMargin: NSLayoutConstraint!
    var model : LHWEUserInfoModel!{
        willSet(m){
            
            let attributedStr = NSMutableAttributedString.init(string: (m?.member_real_name)! + "  " + (m?.member_work_type)!)
            attributedStr.addAttributes([NSForegroundColorAttributeName:UIColor.init(hexString: "242424"),NSFontAttributeName:defaultFont(size: 16)], range: NSRange.init(location: 0, length: (m?.member_real_name!.characters.count)!))
            self.user.attributedText = attributedStr
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        NotificationCenter.default.addObserver(self, selector: #selector(MineCollectionReusableView.setImage), name: NSNotification.Name.init(rawValue:"image"), object: nil)
        if kScreenHeight == CGFloat(IPHONE5_5S_SEHeight) {
            self.userImgSize.constant = 70
            self.SignBtnBottomMargin.constant = 14
        }
        self.signInBtn.layer.cornerRadius = kCornerRadius
        self.signInBtn.layer.masksToBounds = true
        self.userImg.layer.cornerRadius = self.userImgSize.constant/2
        
        self.userImg.layer.masksToBounds = true
        self.userImg.contentMode = .scaleAspectFill
        
        setImage()
        

                
    }
    func setImage() {
        self.userImg.kf.setImage(with: URL.init(string: (LHWEUserInfoHandler.getUserInfo()?.member_head_image)!), placeholder: #imageLiteral(resourceName: "logo"), options: nil, progressBlock: nil, completionHandler: nil)
    }
    @IBAction func setAction(_ sender: UIButton) {
        
        self.responderViewController()?.navigationController?.pushViewController(SettingViewController(), animated: true)
       
        
    }
    
    @IBAction func messageAction(_ sender: UIButton) {
        self.responderViewController()?.navigationController?.pushViewController(MessageViewController(), animated: true)
        
    }
    
    @IBAction func signInAction(_ sender: UIButton) {
        NetworkingHandle.fetchNetworkData(url: "signInterfaces.api?insertSign", at: self, success: { (response) in
            sender.setTitle("已签到", for: .normal)
            sender.isEnabled = false
            sender.backgroundColor = UIColor(hexString: "#dbdbdb")
            NotificationCenter.default.post(name: NSNotification.Name(ReloadSignStateNotification), object: nil)
        })
        
    }
}
