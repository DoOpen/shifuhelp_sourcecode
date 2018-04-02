//
//  MyOrderViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/22.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class MyOrderViewController: PageViewController {

    @IBOutlet weak var allBtn: UIButton!
    
    @IBOutlet weak var waitPayBtn: UIButton!
    
    @IBOutlet weak var waitSendBtn: UIButton!
    
    @IBOutlet weak var waitReceiveBtn: UIButton!
    
    @IBOutlet weak var waitCommentBtn: UIButton!
    
    var sliderLine : UILabel!
    var selectedBtn: UIButton!
    var type: Int!

    override func viewDidLoad() {
        super.viewDidLoad()
        let allVC = MyOrderDetailViewController()
        
        let waitPayVC = MyOrderDetailViewController()
        waitPayVC.orderState = "wait_pay"
        
        let waitSendVC = MyOrderDetailViewController()
        waitSendVC.orderState = "wait_send"
        
        let waitReceiveVC = MyOrderDetailViewController()
        waitReceiveVC.orderState = "wait_receive"
        
        let waitCommentVC = MyOrderDetailViewController()
        waitCommentVC.orderState = "wait_assessment"
        
        self.viewControllerArray = [allVC, waitPayVC, waitSendVC, waitReceiveVC, waitCommentVC]
        
        self.buttonArray = [allBtn, waitPayBtn, waitSendBtn,  waitReceiveBtn, waitCommentBtn]
        
        
        var frame = self.view.bounds
        frame.origin.y = 49
        frame.size.height -= 49
        pageViewController.view.frame = frame
        pageViewController.setViewControllers([self.viewControllerArray[type]], direction: .forward, animated: true, completion: nil)
        
        if self.type == 0{
            self.allBtn.isSelected = true
            selectedBtn = self.allBtn
            self.title = "全部"
        } else if self.type == 1 {
            self.waitPayBtn.isSelected = true
            selectedBtn = self.waitPayBtn
            self.title = "待付款"
            
        } else if self.type == 2 {
            self.waitSendBtn.isSelected = true
            selectedBtn = self.waitSendBtn
            self.title = "待发货"
            
        } else if self.type == 3{
            self.waitReceiveBtn.isSelected = true
            selectedBtn = self.waitReceiveBtn
            self.title = "待收货"
            
        } else if self.type == 4{
            self.waitCommentBtn.isSelected = true
            selectedBtn = self.waitCommentBtn
            self.title = "待评价"
        }
        self.sliderLine = UILabel()
        self.sliderLine.frame = CGRect(x: kScreenWidth/5 * CGFloat(self.type), y: 45, width: kScreenWidth/5, height: 3)
        self.sliderLine.backgroundColor = UIColor(hexString: "EC6B1A")
        self.view.addSubview(self.sliderLine)
        // Do any additional setup after loading the view.
    }

    @IBAction func btnClicked(_ sender: UIButton) {
        if sender.tag == 0 {
            self.title = "全部"
        }
        else if sender.tag == 1 {
            self.title = "待付款"
        }
        else if sender.tag == 2 {
            self.title = "待发货"
        }
        else if sender.tag == 3 {
            self.title = "待收货"
        }else if sender.tag == 4{
            
            self.title = "待评价"
        }
        UIView.animate(withDuration: 0.1) {
            self.sliderLine.frame = CGRect(x: sender.frame.origin.x, y: 45, width: sender.frame.size.width, height: 3)
        }
        
        self.selectedViewController(atButton: sender )

    }
    // 重写父类刷新按钮方法
    override func viewControllerTransition(toIndex index: Int) {
        
        let btn = self.buttonArray[index]
        UIView.animate(withDuration: 0.1) {
            self.sliderLine.frame = CGRect(x: btn.frame.origin.x, y: 45, width: btn.frame.size.width, height: 3)
        }
        self.title = btn.titleLabel?.text
        
        if selectedBtn === btn {
            
            return
        }
        selectedBtn.isSelected = false
        btn.isSelected = true
        selectedBtn = btn
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.navigationController?.navigationBar.isHidden = false
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
