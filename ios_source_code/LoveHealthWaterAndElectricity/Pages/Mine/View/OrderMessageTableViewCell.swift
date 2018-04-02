//
//  OrderMessageTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/22.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class OrderMessageTableViewCell: UITableViewCell {

    @IBOutlet weak var time: UILabel!
    
    @IBOutlet weak var state: UILabel!
    
    @IBOutlet weak var goodsImg: UIImageView!
    
    @IBOutlet weak var goodsName: UILabel!
    
    @IBOutlet weak var orderNumber: UILabel!
    
    @IBOutlet weak var bgView: UIView!
    
    var model : SystemMessageModel!{
        willSet(m){
            time.text = "  " + m.create_time! + "  "
            state.text = m.logistics_state
            goodsImg.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + m.goods_img!)!, placeholder: #imageLiteral(resourceName: "logo"), options: nil, progressBlock: nil, completionHandler: nil)
            orderNumber.text = "运单号：" + m.logistics_no!
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        
        self.bgView.layer.cornerRadius = kCornerRadius
        self.bgView.layer.masksToBounds = true
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
