//
//  SelectWorkTypeTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/11/4.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class SelectWorkTypeTableViewCell: UITableViewCell {
    @IBOutlet weak var checkImg: UIImageView!
    
    @IBOutlet weak var title: UILabel!
    var model : WorkTypeModel!{
        willSet(m){
            title.text = m.type_name
            if m.isSelected {
                checkImg.image = #imageLiteral(resourceName: "frame-selected")
            }else{
                checkImg.image = #imageLiteral(resourceName: "frame-normal")
            }
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
