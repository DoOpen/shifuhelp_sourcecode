//
//  PayResultViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/23.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class PayResultViewController: UIViewController {

    var isSuccess = true
    
    @IBOutlet weak var stateImg: UIImageView!
    
    @IBOutlet weak var state: UILabel!
    
    @IBOutlet weak var firstBtn: UIButton!
    
    @IBOutlet weak var secondBtn: UIButton!
   
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.firstBtn.layer.cornerRadius = kCornerRadius
        self.firstBtn.layer.masksToBounds = true
        self.secondBtn.layer.cornerRadius = kCornerRadius
        self.secondBtn.layer.masksToBounds = true
       
        if !isSuccess{
            self.title = "支付失败"
            self.stateImg.image = #imageLiteral(resourceName: "zfsb")
            self.state.text = "订单支付失败!"
            self.firstBtn.setTitle("再次支付", for: .normal)
            self.secondBtn.setTitle("重新下单", for: .normal)
        }else{
            self.title = "支付成功"
        }
        // Do any additional setup after loading the view.
    }

    @IBAction func firstBtnAction(_ sender: UIButton) {
        if !isSuccess {
           let vc = MyOrderViewController()
           vc.type = 1
           self.navigationController?.pushViewController(vc, animated: true)
        }else{
            var nums = 0
            let vcArr = self.navigationController?.viewControllers
            for vc in vcArr!{
                if vc.isKind(of: ShoppingMallNextViewController.self){
                    nums += 1
                    self.navigationController?.popToViewController(vc, animated: true)
                }
            }
            if nums == 0{
                self.tabBarController?.selectedIndex = 2
                self.navigationController?.popToRootViewController(animated: false)
            }
            
        }
    }
    @IBAction func secondBtnAction(_ sender: UIButton) {
        if !isSuccess {
            var nums = 0
            let vcArr = self.navigationController?.viewControllers
            for vc in vcArr!{
                if vc.isKind(of: ShoppingMallNextViewController.self){
                    nums += 1
                    self.navigationController?.popToViewController(vc, animated: true)
                }
            }
            if nums == 0{
                self.tabBarController?.selectedIndex = 2
                self.navigationController?.popToRootViewController(animated: false)
            }
        }else{
            self.tabBarController?.selectedIndex = 2
            self.navigationController?.popToRootViewController(animated: false)
        }
    }
    class open func jumpPayResultVC(fromVC:UIViewController, isSuccess: Bool){
        let vc = PayResultViewController()
        vc.isSuccess = isSuccess
        fromVC.navigationController?.pushViewController(vc, animated: true)
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

