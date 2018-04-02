//
//  RefundOptionsTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/25.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class RefundOptionsTableViewCell: UITableViewCell {

    @IBOutlet weak var price: UILabel!
    
    @IBOutlet weak var notReceivedBtn: UIButton!
    
    @IBOutlet weak var receivedBtn: UIButton!
    
    @IBOutlet weak var refundResonLab: UILabel!
    
    var refundType: ((Int)->())?
    var showReason: (()->())?
    
    var model : OrderGoodsBeans!{
        willSet(m){
            self.price.text = "    ¥" +  "\(Double(m.goods_price!)! * Double(m.goods_num!)!)"
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        NotificationCenter.default.addObserver(self, selector: #selector(RefundOptionsTableViewCell.changeRefundPrice(noti:)), name: NSNotification.Name(rawValue:ChangeRefundPriceNotification), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(RefundOptionsTableViewCell.showReason(noti:)), name: NSNotification.Name(rawValue:ShowRefundReasonNotification), object: nil)
    }
    func showReason(noti:NSNotification) {
        self.refundResonLab.text = noti.userInfo?["text"] as? String
    }
    func changeRefundPrice(noti:NSNotification){
        let num = noti.userInfo?["num"] as! Double
        self.price.text = "    ¥" +  "\(Double(model.goods_price!)! * num)"
    }

    @IBAction func selectKinds(_ sender: UIButton) {
        sender.isSelected = !sender.isSelected
        if sender.tag == 1001{
            if self.receivedBtn.isSelected {
                self.receivedBtn.isSelected = false
            }
        }else{
            if  self.notReceivedBtn.isSelected {
                self.notReceivedBtn.isSelected = false

            }
        }
        self.refundType?(sender.tag - 1000)
    }
    
    @IBAction func selectReasonAction(_ sender: UIButton) {
        self.showReason?()
    }
    deinit {
        NotificationCenter.default.removeObserver(self)
    }
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    
    
}
