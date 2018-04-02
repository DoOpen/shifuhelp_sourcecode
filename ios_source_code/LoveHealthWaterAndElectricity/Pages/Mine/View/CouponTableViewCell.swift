//
//  CouponTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class CouponTableViewCell: UITableViewCell {

    @IBOutlet weak var name: UILabel!
    
    @IBOutlet weak var price: UILabel!
    
    @IBOutlet weak var tips: UILabel!
    
    @IBOutlet weak var time: UILabel!
    
    @IBOutlet weak var des: UILabel!
    
    @IBOutlet weak var stateImg: UIImageView!
    
    
    var model : CouponsModel!{
        willSet(m){
            name.text = m.coupon_name
            price.text = "¥" + m.coupon_price!
            tips.text = m.coupon_desc!
            time.text = "有效期" + m.start_time! + "~" + m.end_time!
            des.text = "满" + m.coupon_full_price! + "减" + m.coupon_price!
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
