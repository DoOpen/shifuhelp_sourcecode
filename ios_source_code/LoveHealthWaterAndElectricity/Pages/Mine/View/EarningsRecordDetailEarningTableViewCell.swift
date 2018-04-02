//
//  EarningsRecordDetailEarningTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class EarningsRecordDetailEarningTableViewCell: UITableViewCell {
    @IBOutlet weak var account: UILabel!

    @IBOutlet weak var money: UILabel!
    
    @IBOutlet weak var time: UILabel!
    
    @IBOutlet weak var profit: UILabel!
    
    @IBOutlet weak var bgView: UIView!
    
    var model : IntegralRecordModel!{
        willSet(m){
            account.text = m.integral_type_show
            money.text = "-" + m.integral_value!
            time.text = m.create_time
            profit.text = m.deduction
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        self.bgView.layer.cornerRadius = kCornerRadius
        self.bgView.layer.masksToBounds = true
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
