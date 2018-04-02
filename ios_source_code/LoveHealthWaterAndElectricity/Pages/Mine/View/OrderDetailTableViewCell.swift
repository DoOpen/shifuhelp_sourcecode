//
//  OrderDetailTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/25.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class OrderDetailTableViewCell: UITableViewCell {

    @IBOutlet weak var goodsImg: UIImageView!
    @IBOutlet weak var goodsName: UILabel!
    
    @IBOutlet weak var kinds: UILabel!
    
    @IBOutlet weak var price: UILabel!
    
    @IBOutlet weak var num: UILabel!
    
    @IBOutlet weak var bottomView: UIView!
    
    @IBOutlet weak var refundBtn: UIButton!
    
    var model : OrderGoodsBeans!{
        willSet(m){
            self.goodsImg.kf.setImage(with: URL.init(string:NetworkingHandle.mainHost + m.goods_img!)!, placeholder: #imageLiteral(resourceName: "logo"), options: nil, progressBlock: nil, completionHandler: nil)
            self.goodsName.text = m.goods_name
            self.kinds.text = m.specification_name
            self.price.text = "¥" + m.goods_price!
            self.num.text = "x" + m.goods_num!
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        self.refundBtn.layer.cornerRadius = 16
        self.refundBtn.layer.borderColor = UIColor.init(hexString: "AEAEAE").cgColor
        self.refundBtn.layer.borderWidth = 1
        self.refundBtn.layer.masksToBounds = true
    }

    @IBAction func refundGooodsAction(_ sender: UIButton) {
        let vc = ApplyRefundViewController()
        vc.model = self.model
        self.responderViewController()?.navigationController?.pushViewController(vc, animated: true)
    }
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
