//
//  AddressTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class AddressTableViewCell: UITableViewCell {
    
    @IBOutlet weak var userName: UILabel!

    @IBOutlet weak var phone: UILabel!
    
    @IBOutlet weak var address: UILabel!
    
    @IBOutlet weak var deleteBtn: UIButton!
    
    @IBOutlet weak var editBtn: UIButton!
    
    
    @IBOutlet weak var defaultBtn: UIButton!
    
    @IBOutlet weak var deleteBtnWidth: NSLayoutConstraint!
    
    @IBOutlet weak var deleteBtnRightMargin: NSLayoutConstraint!
    var cellTag : Int!
    var edit: (()->())?
    var delete: (()->())?
    var setDefault: (()->())?
    var isService = false
    var addressEditView = AddressEditView()
    var model : AddressModel!{
        willSet(m){
            userName.text = m.name
            phone.text = m.mobile
            address.text = m.province! + m.city! + m.district! + m.detailed_address!
            if m.is_default == "1" {
                self.defaultBtn.isSelected = true
            }else{
                self.defaultBtn.isSelected = false
            }
          
            
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    @IBAction func deleteAction(_ sender: UIButton) {
        NetworkingHandle.fetchNetworkData(url: "addressInterfaces.api?deleteAddress", at: self, params: ["address_id":self.model.address_id!],  success: { (response) in
            self.delete?()
        }) { 
            
        }
    }
    
    @IBAction func editAction(_ sender: UIButton) {
        
        if isService {
            self.edit?()
        }else{
            self.addressEditView = AddressEditView.show(atView: (self.responderViewController()?.view)!, addressModel: self.model, isEdit: true)
            
            self.addressEditView.selectAddress = { [unowned self] in
                let v = ProvinceCityDistrictChoice(frame: (self.responderViewController()?.view.bounds)!)
                v.show(choiced: { [unowned self] (p, c, d) in
                    self.addressEditView.province = p
                    self.addressEditView.city = c
                    self.addressEditView.district = d
                    self.addressEditView.addressBtn.setTitle(p+c+d, for: .normal)
                })
                self.responderViewController()?.view.addSubview(v)
            }
            self.addressEditView.updateAddress = { [unowned self] in
                self.edit?()
            }
        }
        
    }
    
    @IBAction func setDefaultAddressAction(_ sender: UIButton) {
        if self.model.is_default == "1"{
            return
        }
        NetworkingHandle.fetchNetworkData(url: "addressInterfaces.api?setDefaultAddress", at: self, params: ["address_id":self.model.address_id!],  success: { (response) in
            sender.isSelected = true
            self.setDefault?()
        }) { 
            ProgressHUD.showMessage(message: "设置失败")
        }
        //sender.isSelected = !sender.isSelected
    }
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
