//
//  CheckInformationHeaderView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/28.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class CheckInformationHeaderView: UITableViewHeaderFooterView {

    @IBOutlet weak var firstImg: UIImageView!
    
    @IBOutlet weak var middleImg: UIImageView!
    
    @IBOutlet weak var rightImg: UIImageView!
    
    @IBOutlet weak var leftLine: UILabel!
    
    @IBOutlet weak var rightLine: UILabel!
    
    @IBOutlet weak var middleLab: UILabel!
    
    @IBOutlet weak var rightLab: UILabel!
    

    
    var payDepositAction : (()->())?
    
    override func awakeFromNib() {
        
        super.awakeFromNib()
        
        
    }
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    

}
