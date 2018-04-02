//
//  EvaluateTableFooterView.swift
//  BaiShiXueYiLiving
//
//  Created by sh-lx on 2017/6/23.
//  Copyright © 2017年 liangyi. All rights reserved.
//

import UIKit

class EvaluateTableFooterView: UIView {

    @IBOutlet weak var confirmBtn: UIButton!
    var confirmSuccess: (()->())?
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    @IBAction func confirmAction(_ sender: UIButton) {
        self.confirmSuccess?()
    }
    class func set()->EvaluateTableFooterView{
        return Bundle.main.loadNibNamed("EvaluateTableFooterView", owner: self, options: nil)?.first as! EvaluateTableFooterView
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        self.confirmBtn.layer.cornerRadius = 6
        self.confirmBtn.layer.masksToBounds = true
        self.confirmSuccess?()
    }

}
