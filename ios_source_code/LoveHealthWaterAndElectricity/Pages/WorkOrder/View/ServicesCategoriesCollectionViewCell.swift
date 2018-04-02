//
//  ServicesCategoriesCollectionViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/27.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class ServicesCategoriesCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var title: UILabel!
    
    var model : ServicesCategoriesModel!{
        willSet(m){
            if m.isSelected{
                self.title.layer.borderColor = UIColor.init(hexString: "EE784E").cgColor
                self.title.textColor = UIColor.init(hexString: "EE784E")
            }else{
                self.title.layer.borderColor = UIColor.init(hexString: "c1c1c1").cgColor
                self.title.textColor = UIColor.init(hexString: "646464")
            }
            title.text = m.class_name
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        self.title.layer.cornerRadius = kCornerRadius
        self.title.layer.borderWidth = 1
        
    }

}
