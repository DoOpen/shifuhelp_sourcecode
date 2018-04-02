//
//  StarMasterShowCollectionViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/18.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class StarMasterShowCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var headImg: UIImageView!
    
    @IBOutlet weak var name: UILabel!
    
    @IBOutlet weak var kinds: UILabel!
    
    @IBOutlet weak var text: UILabel!
    
    @IBOutlet weak var bgView: UIView!
    
    @IBOutlet weak var KindsTopMargin: NSLayoutConstraint!
    
    var model: StarMasterModel!{
        willSet(m){
            headImg.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + m.member_head_image!))
            name.text = m.member_real_name
            kinds.text = m.member_work_type
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        if kScreenHeight == CGFloat(IPHONE5_5S_SEHeight) {
            name.font = defaultFont(size: 11)
            kinds.font = defaultFont(size: 9)
            KindsTopMargin.constant = -3
        }
        self.bgView.layer.cornerRadius = kCornerRadius
        self.bgView.layer.masksToBounds = true
//        self.bgView.clipsToBounds = true
        
        self.headImg.layer.cornerRadius = 44/2
        self.headImg.contentMode = .scaleAspectFill
        self.headImg.clipsToBounds = true
        self.text.layer.cornerRadius = kCornerRadius
        self.text.layer.masksToBounds = true
        
    }

}
