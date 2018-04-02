//
//  SubmitQuestionView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/4.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class SubmitQuestionView: UIView,UITextViewDelegate{

    @IBOutlet weak var bgBtn: UIButton!
    @IBOutlet weak var backView: UIView!
    
    @IBOutlet weak var textView: UITextView!
    
    @IBOutlet weak var placeHolder: UILabel!
    
    var submitQuestion : ((String)->())?
    class func show(atView: UIView) -> SubmitQuestionView {
        let view = Bundle.main.loadNibNamed("SubmitQuestionView", owner: nil, options: nil)?.first as! SubmitQuestionView
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
        self.textView.delegate = self
        
        self.textView.layer.cornerRadius = kCornerRadius
        self.textView.layer.masksToBounds = true
        
        self.frame = CGRect(x: 0, y: -64, width: kScreenWidth, height: kScreenHeight)
        self.bgBtn.backgroundColor = UIColor(hexString: "#333333")
        self.bgBtn.alpha = 0.2
    }
    func textViewDidChange(_ textView: UITextView) {
        
        if textView.text.characters.count == 0{
            
            self.placeHolder.isHidden = false
            
        }else{
            
            self.placeHolder.isHidden = true
        }
    }
    @IBAction func dismiss(_ sender: UIButton) {
        
        self.remove()
        
        if sender.tag == 0{
            
            print("立即提交")
            if !textView.text.isEmpty {
                submitQuestion?(textView.text)
            }
            
        }else{
            
            print("销毁")
        }
    }
   
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */

}
