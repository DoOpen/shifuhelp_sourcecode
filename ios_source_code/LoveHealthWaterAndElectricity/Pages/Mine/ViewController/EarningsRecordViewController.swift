//
//  EarningsRecordViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class EarningsRecordViewController: PageViewController {

    @IBOutlet weak var earningsBtn: UIButton!
    
    @IBOutlet weak var employBtn: UIButton!
    
    var sliderLine : UILabel!
    var selectedBtn: UIButton!
    var type: Int!

    override func viewDidLoad() {
        super.viewDidLoad()
        let vcEarn = EarningsRecordDetailViewController()
        let vcEmploy = EarningsRecordDetailViewController()
        vcEmploy.isEarned = false
        
        self.viewControllerArray = [vcEarn, vcEmploy]
        
        self.buttonArray = [earningsBtn, employBtn]
        
        var frame = self.view.bounds
        frame.origin.y = 50
        frame.size.height -= 50
        pageViewController.view.frame = frame
        pageViewController.setViewControllers([self.viewControllerArray[type]], direction: .forward, animated: true, completion: nil)
        // Do any additional setup after loading the view.
        if self.type == 0{
            
            self.earningsBtn.isSelected = true
            selectedBtn = self.earningsBtn
            self.title = "提现记录"
            
        } else if self.type == 1 {
            
            self.employBtn.isSelected = true
            selectedBtn = self.employBtn
            self.title = "反佣记录"
            
        }
        self.sliderLine = UILabel()
        self.sliderLine.frame = CGRect(x: kScreenWidth/2 * CGFloat(self.type) + kScreenWidth/6, y: 47, width: kScreenWidth/2 - kScreenWidth/3, height: 3)
        self.sliderLine.backgroundColor = UIColor(hexString: "EC6B1A")
        self.view.addSubview(self.sliderLine)

    }

    @IBAction func btnClicked(_ sender: UIButton) {
        if sender.tag == 0 {
            self.title = "提现记录"
        }
        else if sender.tag == 1 {
            self.title = "反佣记录"
        }
        UIView.animate(withDuration: 0.1) {
            self.sliderLine.frame = CGRect(x: kScreenWidth/2 * CGFloat(sender.tag) + kScreenWidth/6, y: 47, width: kScreenWidth/2 - kScreenWidth/3, height: 3)
        }
        
        self.selectedViewController(atButton: sender )
    }
    // 重写父类刷新按钮方法
    override func viewControllerTransition(toIndex index: Int) {
        
        let btn = self.buttonArray[index]
        UIView.animate(withDuration: 0.1) {
            self.sliderLine.frame = CGRect(x: kScreenWidth/2 * CGFloat(btn.tag) + kScreenWidth/6, y: 47, width: kScreenWidth/2 - kScreenWidth/3, height: 3)
        }
        self.title = btn.titleLabel?.text
        
        if selectedBtn === btn {
            
            return
        }
        selectedBtn.isSelected = false
        btn.isSelected = true
        selectedBtn = btn
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
