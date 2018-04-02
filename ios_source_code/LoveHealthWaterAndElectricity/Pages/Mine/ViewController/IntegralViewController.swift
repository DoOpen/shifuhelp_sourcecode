//
//  IntegralViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class IntegralViewController: PageViewController {

    @IBOutlet weak var integral: UILabel!
    
    @IBOutlet weak var getRecordBtn: UIButton!
    
    @IBOutlet weak var useRecordBtn: UIButton!
    
    @IBOutlet weak var userRulesBtn: UIButton!
    
    var sliderLine : UILabel!
    var selectedBtn: UIButton!
    var type: Int!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        let getRecordVC = IntegralDetailsViewController()
        getRecordVC.index = 0
        
        let useRecordVC = IntegralDetailsViewController()
        useRecordVC.index = 1
        
        let userRulesVC = CommanWebViewController()
        userRulesVC.url = NetworkingHandle.mainHost + IntegralUseRuleURL

        self.viewControllerArray = [getRecordVC, useRecordVC, userRulesVC]
        
        self.buttonArray = [getRecordBtn, useRecordBtn, userRulesBtn]
        
        var frame = self.view.bounds
        frame.origin.y = 180
        frame.size.height -= 180
        pageViewController.view.frame = frame
        pageViewController.setViewControllers([self.viewControllerArray[0]], direction: .forward, animated: true, completion: nil)
        self.getRecordBtn.isSelected = true
        selectedBtn = self.getRecordBtn
        self.title = "获取记录"
//        if self.type == 0{
//            
//            self.getRecordBtn.isSelected = true
//            selectedBtn = self.getRecordBtn
//            self.title = "获取记录"
//            
//        } else if self.type == 1 {
//            
//            self.useRecordBtn.isSelected = true
//            selectedBtn = self.useRecordBtn
//            self.title = "使用记录"
//            
//        } else if self.type == 2 {
//            self.userRulesBtn.isSelected = true
//            selectedBtn = self.userRulesBtn
//            self.title = "使用规则"
//            
//        }
        self.sliderLine = UILabel()
        self.sliderLine.frame = CGRect(x: kScreenWidth/3 * CGFloat(0) + kScreenWidth * 1/18, y: 177, width: kScreenWidth/3 - kScreenWidth * 1/9, height: 3)
        self.sliderLine.backgroundColor = UIColor(hexString: "EB6900")
        self.view.addSubview(self.sliderLine)
        self.getIntegral()
        // Do any additional setup after loading the view.
    }
    func getIntegral() {
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getUserIntegral", at: self, success: { (response) in
            let attributedStr = NSMutableAttributedString(string: response["data"] as! String  + "积分")
            attributedStr.addAttributes([NSFontAttributeName : defaultFont(size: 26)], range: NSRange.init(location: 0, length: attributedStr.length - 2))
            self.integral.attributedText = attributedStr
            
        })
    }
    @IBAction func btnClicked(_ sender: UIButton) {
        
        if sender.tag == 0 {
            self.title = "获取记录"
        }
        else if sender.tag == 1 {
            self.title = "使用记录"
        }
        else if sender.tag == 2 {
            self.title = "使用规则"
        }
        
        UIView.animate(withDuration: 0.1) {
            self.sliderLine.frame = CGRect(x: kScreenWidth/3 * CGFloat(sender.tag) + kScreenWidth/18, y: 177, width: kScreenWidth/3 - kScreenWidth * 1/9, height: 3)
        }
        
        self.selectedViewController(atButton: sender )
    }
    // 重写父类刷新按钮方法
    override func viewControllerTransition(toIndex index: Int) {
        let btn = self.buttonArray[index]
        UIView.animate(withDuration: 0.1) {
            self.sliderLine.frame = CGRect(x: kScreenWidth/3 * CGFloat(btn.tag) + kScreenWidth/18, y: 177, width: kScreenWidth/3 - kScreenWidth/9, height: 3)
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
