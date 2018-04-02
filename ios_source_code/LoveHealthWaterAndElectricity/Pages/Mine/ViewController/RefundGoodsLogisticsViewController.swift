//
//  RefundGoodsLogisticsViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/12.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import IQKeyboardManagerSwift

class RefundGoodsLogisticsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

   
    @IBOutlet weak var selectCompany: UILabel!
    
    @IBOutlet weak var arrow: UIImageView!
    
    @IBOutlet weak var showBtn: UIButton!
    
    
    @IBOutlet weak var logisticsCompanyView: UIView!
    
    @IBOutlet weak var logisticsNum: UITextField!
    
    @IBOutlet weak var phone: UITextField!
    
    @IBOutlet weak var name: UITextField!
    
    var refundID : String!
    var submitSuccess : (()->())?
    var tableView: UITableView!
    var titleArr : [LogisticsCompanyModel] = []
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setKeyBoardManagerIsEnable()
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        setKeyBoardManagerDisable()
    }
    override func viewDidLoad() {
        
        self.title = "订单详情"
        super.viewDidLoad()
        
        self.tableView = UITableView()
        self.tableView.frame = CGRect.init(x: 16, y: self.logisticsCompanyView.frame.size.height + self.logisticsCompanyView.frame.origin.y, width: kScreenWidth - 32, height: 0)
        self.tableView.separatorInset = UIEdgeInsetsMake(0, 0, 0, 0)
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.layer.cornerRadius = kCornerRadius
        self.tableView.layer.masksToBounds = true
        self.tableView.layer.borderColor = UIColor.init(hexString: "aeaeae").cgColor
        self.tableView.layer.borderWidth = 0.5
        self.tableView.tableFooterView = UIView()
        self.view.addSubview(self.tableView)
        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "topicFormCell")
        self.showBtn.isSelected = true
        
        
        
        // Do any additional setup after loading the view.
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.titleArr.count
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "topicFormCell")!
        cell.textLabel?.text = self.titleArr[indexPath.row].logistics_name
        cell.textLabel?.textAlignment = .center
        cell.textLabel?.font = defaultFont(size: 15)
        cell.textLabel?.textColor = UIColor.init(hexString: "242424")
        cell.selectionStyle = .none
        return cell
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let str = self.titleArr[indexPath.row].logistics_name
        self.selectCompany.text = "  " + str!
        self.selectCompany.textColor = UIColor.init(hexString: "242424")
        self.showBtn.isSelected = true
        
        remove()
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 50
    }
    
    @IBAction func selectLogisticsCompanyAction(_ sender: UIButton) {
        
        if self.showBtn.isSelected {
            setListView()
            self.showBtn.isSelected = false
        }else{
            remove()
            self.showBtn.isSelected = true
        }
    }
    func setListView() {
        
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?getLogisticsCompanyList", at: self,isAuthHide: false, isShowHUD: false, success: { (response) in
            let data = response["data"] as! NSArray
            
            self.titleArr = [LogisticsCompanyModel].deserialize(from: data) as! [LogisticsCompanyModel]
            self.tableView.reloadData()
            UIView.animate(withDuration: 0.25) {
                var height : CGFloat = 0.0
                if self.titleArr.count > 6{
                    height = 300.0
                }else{
                    height = CGFloat(self.titleArr.count * 50)
                }
                self.tableView.frame = CGRect.init(x: 16.0, y: self.logisticsCompanyView.frame.origin.y + self.logisticsCompanyView.frame.size.height + 1, width: kScreenWidth - 32, height: height)
                self.arrow.transform = CGAffineTransform(rotationAngle: CGFloat(Double.pi))
                
            }
        })
        
        
    }
    func remove() {
        UIView.animate(withDuration: 0.25) {
            self.tableView.frame = CGRect.init(x: 16.0, y: self.logisticsCompanyView.frame.origin.y + self.logisticsCompanyView.frame.size.height, width: kScreenWidth - 32, height: CGFloat(0))
            self.arrow.transform = CGAffineTransform.identity
            
        }

    }
    @IBAction func submitAction(_ sender: UIButton) {
        if self.selectCompany.text == nil {
            ProgressHUD.showMessage(message: "物流公司不为空")
            return
        }
        if self.logisticsNum.text == nil{
            ProgressHUD.showMessage(message: "物流单号不能为空")
            return
        }
        if !((self.phone.text?.isPhoneNumber)!) && (self.phone.text?.isTelephone)!{
            ProgressHUD.showMessage(message: "联系电话格式不正确")
            return
        }
        if self.name.text == nil {
            ProgressHUD.showMessage(message: "退货人姓名不能为空")
            return
        }
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?updateRefundOrderLogistics", at: self, params: ["refund_id":self.refundID,"logistics_company":self.selectCompany.text!,"logistics_no":self.logisticsNum.text!,"logistics_phone":self.phone.text!,"logistics_name":self.name.text!], success: { (response) in
            ProgressHUD.showSuccess(message: "提交成功")
            self.submitSuccess?()
            self.navigationController?.popViewController(animated: true)
        }) { 
            
        }
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        var point = touches.first?.location(in: self.view)
        if self.tableView != nil {
            point = self.tableView.layer.convert(point!, from: self.view.layer)
            if !self.tableView.layer.contains(point!){
                self.showBtn.isSelected = true
                remove()
            }
        }
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
//extension UIScrollView{
//    open override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
//        self.next?.touchesBegan(touches, with: event)
//        super.touchesBegan(touches, with: event)
//    }
//}

