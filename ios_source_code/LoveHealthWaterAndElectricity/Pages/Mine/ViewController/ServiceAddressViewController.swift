//
//  ServiceAddressViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh
import IQKeyboardManagerSwift

class ServiceAddressViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var bottomBtn: UIButton!
    @IBOutlet weak var bottomHeight: NSLayoutConstraint!
    @IBOutlet weak var tableView: UITableView!
    
    var addressEditView = ServiceAddressView()
    var isHaveServiceAddress = false
    var saModel : LHWEUserInfoModel!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "我的地址"
        
        self.getUserInfo()
        self.bottomBtn.isHidden = true
        self.bottomHeight.constant = 0
        
        
        
        
        self.tableView.register(UINib.init(nibName: "AddressTableViewCell", bundle: nil), forCellReuseIdentifier: AddressTableViewCell.description())
        
        // Do any additional setup after loading the view.
    }
    func getUserInfo() {
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getMemberDetail", at: self,isAuthHide: false, isShowHUD: false, success: { (response) in
            let data = response["data"] as! NSDictionary
            self.saModel = LHWEUserInfoModel.deserialize(from: data)!
            self.isHaveServiceAddress = true
            self.bottomBtn.isHidden = true
            self.bottomHeight.constant = 0
            self.tableView.reloadData()
        })
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: AddressTableViewCell.description()) as! AddressTableViewCell
        cell.defaultBtn.isHidden = true
        cell.deleteBtn.isHidden = true
        cell.deleteBtnWidth.constant = 0
        cell.deleteBtnRightMargin.constant = 0
        if self.saModel != nil{
            cell.address.text = self.saModel.member_service_province! + self.saModel.member_service_city! + self.saModel.member_service_district! + self.saModel.member_service_detail!
            cell.phone.text = self.saModel.member_phone
            cell.userName.text = self.saModel.member_real_name
        }
        
        cell.isService = true
        cell.edit = { [unowned self] in
            self.addressEditView = ServiceAddressView.show(atView: self.view)
            self.addressEditView.address.text = self.saModel.member_service_province! + self.saModel.member_service_city! + self.saModel.member_service_district!
            
            self.addressEditView.detailAddressTF.text = self.saModel.member_service_detail!
            self.addressEditView.province = self.saModel.member_service_province
            self.addressEditView.city = self.saModel.member_service_city
            self.addressEditView.district = self.saModel.member_service_district
            
            self.addressEditView.selectAddress = { [unowned self] in
                let v = ProvinceCityDistrictChoice(frame: self.view.bounds)
                v.show(choiced: { [unowned self] (p, c, d) in
                    
                    self.addressEditView.address.text = p+c+d
                    self.addressEditView.province = p
                    self.addressEditView.city = c
                    self.addressEditView.district = d
                })
                self.view.addSubview(v)
            }
            self.addressEditView.updateAddress = { [unowned self] in
                self.getUserInfo()
            }

            
        }
        
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
       return 1
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 10
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    @IBAction func addAddressAction(_ sender: UIButton) {
        self.addressEditView = ServiceAddressView.show(atView: self.view)
        self.addressEditView.selectAddress = { [unowned self] in
            let v = ProvinceCityDistrictChoice(frame: self.view.bounds)
            v.show(choiced: { [unowned self] (p, c, d) in
                self.addressEditView.address.text = p+c+d
                self.addressEditView.province = p
                self.addressEditView.city = c
                self.addressEditView.district = d
            })
            self.view.addSubview(v)
        }
        self.addressEditView.updateAddress = { [unowned self] in
            self.getUserInfo()
        }
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setKeyBoardManagerIsEnable()
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        setKeyBoardManagerDisable()
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
