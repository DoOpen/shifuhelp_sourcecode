//
//  HomePageBannerView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/18.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class HomePageBannerView: UIView {

    var pageScrollView: CarouselView?
    
    var modelArr : [HomePageBannerModel]!{
        willSet(m){
            pageScrollView?.fetchImageUrl = { index in
                if m[index].banner_img != ""{
                     return NetworkingHandle.mainHost + m[index].banner_img!
                }else{
                    return NetworkingHandle.mainHost
                }
                
                
            }
            pageScrollView?.totalPages = m.count
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        self.frame = CGRect(x: 0, y: 0, width: kScreenWidth, height: 131/375 * kScreenWidth)
        self.pageScrollView = CarouselView(frame: CGRect(x:0, y:0, width:kScreenWidth, height: 131/375 * kScreenWidth), animationDuration: 3.5, didSelect: { [unowned self] index in
            let model = self.modelArr[index]
            
            DispatchQueue.main.async { [unowned self] in
                let vc = CommanWebViewController()
              
                if model.banner_url != ""{
                    vc.url = NetworkingHandle.mainHost + model.banner_url!
                }else{
                    vc.url = "https://www.baidu.com"
                }
                vc.title = self.modelArr[index].banner_title
                self.responderViewController()?.navigationController?.pushViewController(vc, animated: true)
            }
        })
        self.addSubview(self.pageScrollView!)
       
    }
    class func set(modelArr: [HomePageBannerModel]) -> HomePageBannerView{
        let view = Bundle.main.loadNibNamed("HomePageBannerView", owner: nil, options: nil)?.first as! HomePageBannerView
        view.modelArr = modelArr
        return view
    }

    
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */

}
