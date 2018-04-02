//
//  CheckLogisticsTableViewCell.swift
//  BaiShiXueYiLiving
//
//  Created by sh-lx on 2017/6/22.
//  Copyright © 2017年 liangyi. All rights reserved.
//

import UIKit

class CheckLogisticsTableViewCell: UITableViewCell {
    @IBOutlet weak var stateImg: UIImageView!

    @IBOutlet weak var content: UILabel!
    
    @IBOutlet weak var time: UILabel!
    
    var model: logisticsDetailModel!{
        willSet(m){
            content.text = m.logistics_context
            time.text = m.logistics_time
            stateImg.image = #imageLiteral(resourceName: "zt")
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
