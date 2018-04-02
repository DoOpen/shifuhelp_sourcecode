//
//  MyReportHeaderView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/27.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class MyReportHeaderView: UITableViewHeaderFooterView {

    @IBOutlet weak var num: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        self.frame = CGRect(x: 0, y: 0, width: kScreenWidth, height: 33)
        self.contentView.backgroundColor = UIColor.init(hexString: "EE784E")
    }
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */

}
