//
//  MyReportTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/27.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class MyReportTableViewCell: UITableViewCell {

    @IBOutlet weak var name: UILabel!
    
    @IBOutlet weak var time: UILabel!
    
    @IBOutlet weak var state: UILabel!
    
    @IBOutlet weak var bgView: UIView!
    
    var model : ReportedListModel!{
        willSet(m){
            name.text = m.reported_name
            let str : NSString = m.reported_name! as NSString
            time.text = str.substring(to: str.length - 2)
            state.text = m.reported_state_show
//            switch m.state! {
//                
//            case "wait_audit":
//                state.text = "待审核"
//            case "accept":
//                state.text = "已受理"
//            case "success":
//                state.text = "成功"
//            case "refuse":
//                state.text = "拒绝"
//            default:
//                state.text = ""
//            }
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
