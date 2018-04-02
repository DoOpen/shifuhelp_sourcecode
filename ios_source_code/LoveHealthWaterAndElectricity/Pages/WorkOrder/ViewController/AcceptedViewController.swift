//
//  AcceptedViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/20.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class AcceptedViewController: PageViewController {
    
    @IBOutlet weak var noneServiceBtn: UIButton!

    @IBOutlet weak var inServiceBtn: UIButton!
    
    
    @IBOutlet weak var inAuditBtn: UIButton!
    
    
    @IBOutlet weak var finishedBtn: UIButton!
    
    
    
    
    var sliderLine : UILabel!
    var selectedBtn: UIButton!
    var type: Int!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        NotificationCenter.default.addObserver(self, selector: #selector(AcceptedViewController.getOrderStateNum), name: NSNotification.Name(rawValue:ReloadAcceptWorkOrderNotification), object: nil)
        let noneServiceVC = AcceptedDetailViewController()
        noneServiceVC.serviceType = 0
        
        let inServiceVC = AcceptedDetailViewController()
        inServiceVC.serviceType = 1
        
        let inAuditVC = AcceptedDetailViewController()
        inAuditVC.serviceType = 2
        
        let finishedVC = AcceptedDetailViewController()
        finishedVC.serviceType = 3
        
        self.viewControllerArray = [noneServiceVC, inServiceVC, inAuditVC, finishedVC]
        self.buttonArray = [noneServiceBtn, inServiceBtn, inAuditBtn, finishedBtn]
        
        var frame = self.view.bounds
        frame.origin.y = 49
        frame.size.height -= 49
        pageViewController.view.frame = frame
        pageViewController.setViewControllers([self.viewControllerArray[type]], direction: .forward, animated: true, completion: nil)
        
        
        if self.type == 0{
            self.noneServiceBtn.isSelected = true
            selectedBtn = self.noneServiceBtn
            self.title = "未服务"
        } else if self.type == 1 {
            self.inServiceBtn.isSelected = true
            selectedBtn = self.inServiceBtn
            self.title = "服务中"
            
        } else if self.type == 2 {
            self.inAuditBtn.isSelected = true
            selectedBtn = self.inAuditBtn
            self.title = "待审核"
            
        } else if self.type == 3{
            self.finishedBtn.isSelected = true
            selectedBtn = self.finishedBtn
            self.title = "已完成"
            
        }
        self.sliderLine = UILabel()
        self.sliderLine.frame = CGRect(x: kScreenWidth/4 * CGFloat(self.type), y: 47, width: kScreenWidth/4, height: 3)
        self.sliderLine.backgroundColor = UIColor(hexString: "EC6B1A")
        self.view.addSubview(self.sliderLine)
        self.getOrderStateNum()
        
        // Do any additional setup after loading the view.
    }
    func getOrderStateNum() {
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?getWorkOrderStateCount", at: self, params: ["member_type":"1"], isAuthHide: false, isShowHUD: false, isShowError: true, hasHeaderRefresh: nil, success: { (response) in
            let data = response["data"] as! Dictionary<String,Int>
            self.noneServiceBtn.setTitle("未服务(\(data["worker_not_service"]!))", for: .normal)
            self.inServiceBtn.setTitle("服务中(\(data["worker_servicing"]!))", for: .normal)
            self.inAuditBtn.setTitle("待审核(\(data["worker_wait_audit"]!))", for: .normal)
            self.finishedBtn.setTitle("已完成(\(data["worker_complete"]!))", for: .normal)
        }) {
            
        }
    }
    @IBAction func btnCilckedAction(_ sender: UIButton) {
        if sender.tag == 0 {
           self.title = "未服务"
        }
        else if sender.tag == 1 {
             self.title = "服务中"
        }
        else if sender.tag == 2 {
            self.title = "待审核"
        }
        else if sender.tag == 3 {
            self.title = "已完成"
        }
        UIView.animate(withDuration: 0.1) {
            
            self.sliderLine.frame = CGRect(x: sender.frame.origin.x, y: 47, width: sender.frame.size.width, height: 3)
        }
        
        self.selectedViewController(atButton: sender )

    }
    // 重写父类刷新按钮方法
    override func viewControllerTransition(toIndex index: Int) {
        
        let btn = self.buttonArray[index]
        UIView.animate(withDuration: 0.1) {
            self.sliderLine.frame = CGRect(x: btn.frame.origin.x, y: 47, width: btn.frame.size.width, height: 3)
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
        super.viewWillAppear(animated)
        
        self.navigationController?.navigationBar.isHidden = false
        
    }
    deinit {
        NotificationCenter.default.removeObserver(self)
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
