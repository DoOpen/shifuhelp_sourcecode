//
//  ReceivingAddressViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh
import IQKeyboardManagerSwift

class ReceivingAddressViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    
    @IBOutlet weak var addNewAddressBtn: UIButton!
    var addressEditView = AddressEditView()
    var addressArr : [AddressModel] = []
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = "收货地址"
        self.tableView.mj_header = MJRefreshNormalHeader(refreshingBlock: { [unowned self] in
            self.requestAddress()
        })
        self.tableView.register(UINib.init(nibName: "AddressTableViewCell", bundle: nil), forCellReuseIdentifier: AddressTableViewCell.description())
        self.tableView.mj_header.beginRefreshing()
        // Do any additional setup after loading the view.
    }
    func requestAddress(){
        
        NetworkingHandle.fetchNetworkData(url: "addressInterfaces.api?getOwnerAddress", at: self, success: { (response) in
            self.tableView.mj_header.endRefreshing()
            let data = response["data"] as! NSArray
            self.addressArr.removeAll()
            self.addressArr = [AddressModel].deserialize(from: data)! as! [AddressModel]
            self.tableView.reloadData()
            
        })
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: AddressTableViewCell.description()) as! AddressTableViewCell
        cell.cellTag = indexPath.section
        cell.model = self.addressArr[indexPath.section]
        cell.setDefault = { [unowned self] in
            self.tableView.mj_header.beginRefreshing()
        }
        cell.delete = { [unowned self] in
            self.tableView.mj_header.beginRefreshing()
        }
        cell.edit = { [unowned self] index in
            self.tableView.mj_header.beginRefreshing()
        }
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return self.addressArr.count
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 10
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 130
    }
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 0.1
    }
    @IBAction func addNewAddressAction(_ sender: UIButton) {
        self.addressEditView = AddressEditView.show(atView: self.view, addressModel: AddressModel(), isEdit: false)
        self.addressEditView.selectAddress = { [unowned self] in
            let v = ProvinceCityDistrictChoice(frame: self.view.bounds)
            v.show(choiced: { [unowned self] (p, c, d) in
                
                self.addressEditView.province = p
                self.addressEditView.city = c
                self.addressEditView.district = d
                self.addressEditView.addressBtn.setTitle(p+c+d, for: .normal)
            })
            self.view.addSubview(v)
        }
        self.addressEditView.updateAddress = { [unowned self] in
            self.tableView.mj_header.beginRefreshing()
        }
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    override func viewWillAppear(_ animated: Bool) {
        
        super.viewWillAppear(animated)
        setKeyBoardManagerIsEnable()
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        setKeyBoardManagerDisable()
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
