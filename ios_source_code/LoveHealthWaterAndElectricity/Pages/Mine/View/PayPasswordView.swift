//
//  PayPasswordView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/9.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class PayPasswordView: UIView {

    @IBOutlet weak var bgBtn: UIButton!
    
    @IBOutlet weak var backView: UIView!
    
    var passwordTF : JJCPayCodeTextField!
   
    var finishEdit : ((String)->())?
    
    class func show(atView:UIView) -> PayPasswordView{
        let view = Bundle.main.loadNibNamed("PayPasswordView", owner: nil, options: nil)?.first as! PayPasswordView
        atView.addSubview(view)
        view.showView()
        return view
    }
    private func showView(){
        var frame = self.backView.frame
        let old = frame
        self.alpha = 0.1
        frame.origin.y = self.frame.size.height
        self.backView.frame = frame
        UIView.animateKeyframes(withDuration: 0.25, delay: 0.1, options: .calculationModePaced, animations: {
            self.backView.frame = old
            self.bgBtn.alpha = 0.2
            self.alpha = 1
        }, completion: nil)
    }
    func remove(){
        var frame = self.backView.frame
        frame.origin.y += self.frame.size.height
        UIView.animate(withDuration: 0.25, animations: {
            self.backView.frame = frame
            self.backView.alpha = 0.1
            
        }) { (finish) in
            self.removeFromSuperview()
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.frame = CGRect(x: 0, y: -64, width: kScreenWidth, height: kScreenHeight)
        self.bgBtn.backgroundColor = UIColor(hexString: "#333333")
        self.bgBtn.alpha = 0.2
        
        self.passwordTF = JJCPayCodeTextField(frame: CGRect.init(x: 0, y: 0, width: kScreenWidth - 100, height: self.backView.frame.size.height), textFieldType: .wholeBorder)
        self.passwordTF.borderColor = themeColor
        self.passwordTF.finishedBlock = { password in
            self.finishEdit?(password!)
        }
        self.backView.addSubview(self.passwordTF)
    }
    
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    
    @IBAction func dismissAction(_ sender: UIButton) {
        self.remove()
    }

}
