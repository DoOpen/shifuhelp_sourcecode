//
//  RefundGoodsRemarkTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/11.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class RefundGoodsRemarkTableViewCell: UITableViewCell {

    @IBOutlet weak var refundImg1: UIImageView!
    
    @IBOutlet weak var refundImg2: UIImageView!
    
    @IBOutlet weak var refundImg3: UIImageView!
    
    @IBOutlet weak var LogisticsView: UIView!
    
    @IBOutlet weak var LogisticsViewHeight: NSLayoutConstraint!
    
    @IBOutlet weak var logistics: UILabel!
    
    @IBOutlet weak var refundNum: UILabel!
    
    @IBOutlet weak var phone: UILabel!
    
    @IBOutlet weak var name: UILabel!
    
    @IBOutlet weak var submitTime: UILabel!
    
    var model : RefundGoodsModel!{
        willSet(m){
            if m.refund_state == "end"{
                LogisticsView.isHidden = false
                LogisticsViewHeight.constant = 140
                logistics.text = "退货物流：" + m.logistics_company!
                refundNum.text = "退货单号：" + m.logistics_no!
                phone.text = "联系电话：" + m.logistics_phone!
                name.text = "退货人姓名：" + m.logistics_name!
                submitTime.text = "提交时间：" + m.logistics_time!
            }else{
                LogisticsView.isHidden = true
                LogisticsViewHeight.constant = 0
            }
           refundImg1.kf.setImage(with: URL.init(string: m.refund_img1!), placeholder: #imageLiteral(resourceName: "upload"), options: nil, progressBlock: nil, completionHandler: nil)
            refundImg2.kf.setImage(with: URL.init(string: m.refund_img2!), placeholder: #imageLiteral(resourceName: "upload"), options: nil, progressBlock: nil, completionHandler: nil)
            refundImg3.kf.setImage(with: URL.init(string: m.refund_img3!), placeholder: #imageLiteral(resourceName: "upload"), options: nil, progressBlock: nil, completionHandler: nil)
            
            
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
