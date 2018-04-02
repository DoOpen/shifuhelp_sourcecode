//
//  EarningsRecordDetailEmployTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class EarningsRecordDetailEmployTableViewCell: UITableViewCell {
    @IBOutlet weak var content: UILabel!

    @IBOutlet weak var money: UILabel!
    
    @IBOutlet weak var time: UILabel!
    
    @IBOutlet weak var bgView: UIView!
    
    var model : ApplyCashsModel!{
        willSet(m){
            //wait_review：等待审核  accept:通过  refuse：不通过  end:打款成功
            if m.apply_state == "wait_review"{
                content.text = "等待审核"
            }else if m.apply_state == "accept"{
                content.text = "通过"
            }else if m.apply_state == "refuse"{
                content.text = "失败"
            }else{
                content.text = "提现成功"
            }
            money.text = "-" + m.cash_price! + "元"
            time.text = m.cash_time
        }
    }
    var imodel : IntegralRecordModel!{
        willSet(m){
            content.text = m.integral_type_show
            money.textColor = UIColor.init(hexString: "#EB6900")
            money.text = "+" + m.integral_value!
            time.text = m.create_time
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
