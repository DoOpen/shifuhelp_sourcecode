//
//  GoodsRefundTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/24.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class GoodsRefundTableViewCell: UITableViewCell {

    @IBOutlet weak var goodsImg: UIImageView!
   
    @IBOutlet weak var goodsName: UILabel!
    
    @IBOutlet weak var kinds: UILabel!
    
    @IBOutlet weak var price: UILabel!
    
    @IBOutlet weak var num: UILabel!
    var refundNum : ((String)->())?
    var model : OrderGoodsBeans!{
        willSet(m){
            self.goodsImg.kf.setImage(with: URL.init(string: m.goods_img!), placeholder: #imageLiteral(resourceName: "logo"), options: nil, progressBlock: nil, completionHandler: nil)
            self.goodsName.text = m.goods_name
            self.kinds.text = m.specification_name
            self.price.text =  "¥" + m.goods_price!
            self.num.text = m.goods_num
            
        }
    }
    var rmodel : RefundGoodsDetailModel!{
        willSet(m){
            self.goodsImg.kf.setImage(with: URL.init(string: m.goods_img!), placeholder: #imageLiteral(resourceName: "logo"), options: nil, progressBlock: nil, completionHandler: nil)
            self.goodsName.text = m.goods_name
            self.kinds.text = m.specification_name
            self.price.text =  "¥" + m.goods_price!
            self.num.text = m.goods_num
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        self.num.layer.borderColor = UIColor.init(hexString: "aeaeae").cgColor
        self.num.layer.borderWidth = 0.5
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    @IBAction func changeNum(_ sender: UIButton) {
        if sender.tag == 1001{
            if Int(self.num.text!)! < Int(self.model.goods_num!)!{
                self.num.text = "\(Int(self.num.text!)! + 1)"
            }
            
        }else{
            if Int(self.num.text!)! > 1{
                self.num.text = "\(Int(self.num.text!)! - 1)"
            }
        }
        self.refundNum?(self.num.text!)
        NotificationCenter.default.post(name: NSNotification.Name.init(rawValue:ChangeRefundPriceNotification), object: nil, userInfo: ["num":Double(self.num.text!)!])
    }
}
