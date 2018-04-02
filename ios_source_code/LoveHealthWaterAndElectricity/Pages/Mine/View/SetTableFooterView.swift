//
//  SetTableFooterView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/21.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class SetTableFooterView: UIView {

    @IBOutlet weak var quitBtn: UIButton!
    var quitBlock : (()->())?
    
    override func awakeFromNib() {
        if kScreenHeight == CGFloat(IPHONE5_5S_SEHeight) {
            self.frame = CGRect(x: 0, y: 0, width: kScreenWidth, height: 320)
        }else{
            self.frame = CGRect(x: 0, y: 0, width: kScreenWidth, height: 220)
        }
        self.quitBtn.layer.cornerRadius = kCornerRadius
        self.quitBtn.layer.masksToBounds = true
    }
    class func setView() -> SetTableFooterView{
        let view = Bundle.main.loadNibNamed("SetTableFooterView", owner: nil, options: nil)?.first as! SetTableFooterView
        return view
    }
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    @IBAction func quitAction(_ sender: UIButton) {
        self.quitBlock?()
    }

}
