//
//  MyCouponViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class MyCouponViewController: PageViewController {
    
    @IBOutlet weak var unuseBtn: UIButton!
    
    @IBOutlet weak var usedBtn: UIButton!
    
    @IBOutlet weak var outDateBtn: UIButton!
    
    var sliderLine : UILabel!
    var selectedBtn: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = "我的优惠券"
        let unuseVC = MyCouponDetailViewController()
        unuseVC.index = 0
        
        let usedVC = MyCouponDetailViewController()
        usedVC.index = 1
        
        let outDateVC = MyCouponDetailViewController()
        outDateVC.index = 2
        
        self.viewControllerArray = [unuseVC, usedVC, outDateVC]
        
        self.buttonArray = [unuseBtn, usedBtn, outDateBtn]
        
        var frame = self.view.bounds
        frame.origin.y = 50
        frame.size.height -= 50
        pageViewController.view.frame = frame
        pageViewController.setViewControllers([self.viewControllerArray[0]], direction: .forward, animated: true, completion: nil)
        self.unuseBtn.isSelected = true
        selectedBtn = self.unuseBtn
        self.sliderLine = UILabel()
        
        self.sliderLine.frame = CGRect(x: kScreenWidth/3 * CGFloat(0) + kScreenWidth * 1/18, y: 47, width: kScreenWidth/3 - kScreenWidth * 1/9, height: 3)
        self.sliderLine.backgroundColor = UIColor(hexString: "EB6900")
        self.view.addSubview(self.sliderLine)

        // Do any additional setup after loading the view.
    }

    @IBAction func btnClicked(_ sender: UIButton) {
        
        UIView.animate(withDuration: 0.1) {
            self.sliderLine.frame = CGRect(x: kScreenWidth/3 * CGFloat(sender.tag) + kScreenWidth/18, y: 47, width: kScreenWidth/3 - kScreenWidth * 1/9, height: 3)
        }
        
        self.selectedViewController(atButton: sender )
    }
    // 重写父类刷新按钮方法
    override func viewControllerTransition(toIndex index: Int) {
        let btn = self.buttonArray[index]
        UIView.animate(withDuration: 0.1) {
            self.sliderLine.frame = CGRect(x: kScreenWidth/3 * CGFloat(index) + kScreenWidth/18, y: 47, width: kScreenWidth/3 - kScreenWidth/9, height: 3)
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
