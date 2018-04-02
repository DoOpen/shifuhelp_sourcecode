//
//  ApplyRefundViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/23.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import IQKeyboardManagerSwift

class ApplyRefundViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    
    var model : OrderGoodsBeans!
    var refundGoodsNum : String!
   
    var refundModel : RefundReasonModel!
    var refundDes : String?
    var refundType : String?
    var imgArr : [String]! = []
    var refundReasonView = RefundReasonView()
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setKeyBoardManagerIsEnable()
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        setKeyBoardManagerDisable()
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "申请退款"
        
        IQKeyboardManager.sharedManager().enable = true
        IQKeyboardManager.sharedManager().enableAutoToolbar = true
        
        self.refundGoodsNum = self.model.goods_num
        
        self.tableView.bounces = false
        self.tableView.showsHorizontalScrollIndicator = false
        self.tableView.showsVerticalScrollIndicator = false
        
        self.tableView.register(UINib.init(nibName: "GoodsRefundTableViewCell", bundle: nil), forCellReuseIdentifier: GoodsRefundTableViewCell.description())
        self.tableView.register(UINib.init(nibName: "RefundOptionsTableViewCell", bundle: nil), forCellReuseIdentifier: RefundOptionsTableViewCell.description())
        self.tableView.register(UINib.init(nibName: "RefundReasonTableViewCell", bundle: nil), forCellReuseIdentifier: RefundReasonTableViewCell.description())
        // Do any additional setup after loading the view.
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if indexPath.row == 0{
            let cell = tableView.dequeueReusableCell(withIdentifier: GoodsRefundTableViewCell.description()) as! GoodsRefundTableViewCell
            cell.model = self.model
            cell.refundNum = { num in
                self.refundGoodsNum = num
            }
            return cell
        }else if indexPath.row == 1{
            let cell = tableView.dequeueReusableCell(withIdentifier: RefundOptionsTableViewCell.description()) as! RefundOptionsTableViewCell
            cell.model = self.model
            cell.refundType = { type in
                if type == 1{
                    self.refundType = "not_goods"
                }else{
                    self.refundType = "with_goods"
                }
                
            }
            cell.showReason = { [unowned self] in
                
                self.showReasonView()
            }
            return cell
        }else{
            let cell = tableView.dequeueReusableCell(withIdentifier: RefundReasonTableViewCell.description()) as! RefundReasonTableViewCell
            cell.textDidChange = { reason in
                
                self.refundDes = reason
            }
            cell.imageArrChange = { imageArr in
                
                self.imgArr = imageArr
            }
            return cell
        }
        
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 3
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        switch indexPath.row {
        case 0:
            return 155
        case 1:
            return 343
        case 2:
            return 340
        default:
            return 0
        }
       
    }
    func showReasonView(){
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?getRefundsReasons", at: self, isAuthHide: false, isShowHUD: false,success: { (response) in
            self.refundReasonView = RefundReasonView.showReasonView(atView: self.view, reasonArr: [RefundReasonModel].deserialize(from: response["data"] as? NSArray) as! [RefundReasonModel])
            self.refundReasonView.selectModel = { m in
                NotificationCenter.default.post(name: NSNotification.Name(rawValue:ShowRefundReasonNotification), object: nil, userInfo: ["text":m.reason_name!])
                self.refundModel = m
            }
        })
    }
    
    @IBAction func submitAction(_ sender: UIButton) {
        if self.refundDes == nil{
            ProgressHUD.showMessage(message: "请输入退款原因")
            return
        }
        if self.refundType == nil{
            ProgressHUD.showMessage(message: "请选择退款类型")
            return
        }
        if self.refundModel == nil{
            ProgressHUD.showMessage(message: "请选择退款原因")
            return
        }
        var params : Dictionary<String,String> = ["order_id":self.model.order_id!,"order_goods_id":self.model.order_goods_id!,"refund_count":self.refundGoodsNum,"refund_desc":self.refundDes!,"refund_reason_id":self.refundModel.refund_reason_id!,"reason_name":self.refundModel.reason_name!]
        for i in 0..<3{
            if i < self.imgArr.count{
                params["refund_img\(i + 1)"] = self.imgArr[i]
            }else{
                params["refund_img\(i + 1)"] = ""
            }
        }
        print(self.imgArr.count)
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?refundOrderNoFile", at: self, params: params, success: { (response) in
            ProgressHUD.showMessage(message: "成功提交申请")
            let vcArrs = self.navigationController?.viewControllers
            for vc in vcArrs!{
                if vc.isKind(of: MyOrderViewController.self){
                    self.navigationController?.popToViewController(vc, animated: true)
                }
            }
        }) { 
            
        }
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
