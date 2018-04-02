//
//  OrderTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/22.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class OrderTableViewCell: UITableViewCell {

    
    
    @IBOutlet weak var goodsImg: UIImageView!
    
    @IBOutlet weak var goodsName: UILabel!
    
    @IBOutlet weak var kinds: UILabel!
    
    @IBOutlet weak var price: UILabel!
    
    
    @IBOutlet weak var num: UILabel!
    
    var model : OrderGoodsBeans!{
        willSet(m){
            goodsImg.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + m.goods_img!), placeholder: #imageLiteral(resourceName: "logo"), options: nil, progressBlock: nil, completionHandler: nil)
            goodsName.text = m.goods_name
            kinds.text = "规格：" + m.specification_name!
            price.text = "¥" + m.goods_price!
            num.text = "x" + m.goods_num!
        }
    }
    var cmodel : CollectionGoodsModel!{
        willSet(m){
            goodsImg.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + (m.goodsBean?.goods_img!)!), placeholder: #imageLiteral(resourceName: "logo"), options: nil, progressBlock: nil, completionHandler: nil)
            goodsName.text = m.goodsBean?.goods_name
            price.text = "¥" + (m.goodsBean?.goods_price)!
            kinds.isHidden = true
            num.text = (m.goodsBean?.assessment_count!)! + "人购买"
        }
    }
    var rgModel : RefundGoodsModel!{
        willSet(m){
            goodsImg.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + (m.orderGoodsBean!.goods_img!)), placeholder: #imageLiteral(resourceName: "logo"), options: nil, progressBlock: nil, completionHandler: nil)
            goodsName.text = m.orderGoodsBean?.goods_name
            price.text = "¥" + (m.orderGoodsBean?.goods_price)!
            kinds.text = "规格：" + (m.orderGoodsBean?.specification_name!)!
            num.text = "x" + (m.orderGoodsBean?.goods_num!)!
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
