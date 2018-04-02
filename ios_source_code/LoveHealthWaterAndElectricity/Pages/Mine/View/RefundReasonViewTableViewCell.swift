//
//  RefundReasonViewTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/25.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class RefundReasonViewTableViewCell: UITableViewCell {

    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var checkImg: UIImageView!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        if selected{
            self.checkImg.image = UIImage.init(named: "frame-selected")
        }else{
            self.checkImg.image = UIImage.init(named: "frame-normal")
        }
        // Configure the view for the selected state
    }
    
}
