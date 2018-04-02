//
//  ServicesCategoriesView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/27.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class ServicesCategoriesView: UIView, UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout{

    @IBOutlet weak var bgBtn: UIButton!
    
    @IBOutlet weak var collectionView: UICollectionView!
    
    @IBOutlet weak var layout: UICollectionViewFlowLayout!
    
    @IBOutlet weak var backView: UIView!
    
    var finishSelect : (((String,String))->())?
    
    var lastSelectModel : ServicesCategoriesModel!
    var serviceArr : [ServicesCategoriesModel] = []
    var selectModelArr = [ServicesCategoriesModel]()
    var newAddModel : ServicesCategoriesModel!
    
    class func show(atView:UIView, arr: [ServicesCategoriesModel]) -> ServicesCategoriesView{
        
        let view = Bundle.main.loadNibNamed("ServicesCategoriesView", owner: nil, options: nil)?.first as! ServicesCategoriesView
        atView.addSubview(view)
        view.serviceArr = arr
        view.showView()
        return view
    }
    private func showView(){
        var frame = self.backView.frame
        let old = frame
        self.alpha = 0.1
        frame.origin.y = self.frame.size.height
        self.backView.frame = frame
        UIView.animateKeyframes(withDuration: 0.25, delay: 0.1, options: .calculationModePaced, animations: {
            self.backView.frame = old
            self.bgBtn.alpha = 0.2
            self.alpha = 1
        }, completion: nil)
    }
    func remove(){
        var frame = self.backView.frame
        frame.origin.y += self.frame.size.height
        UIView.animate(withDuration: 0.25, animations: {
            self.backView.frame = frame
            self.backView.alpha = 0.1
            
        }) { (finish) in
            self.removeFromSuperview()
        }
    }

    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.frame = CGRect(x: 0, y: -64, width: kScreenWidth, height: kScreenHeight)
        self.bgBtn.backgroundColor = UIColor(hexString: "#333333")
        self.bgBtn.alpha = 0.2
        
      
        self.layout.sectionInset = UIEdgeInsetsMake(36, 26, 5, 26)
        self.layout.minimumLineSpacing = 10
        self.layout.minimumInteritemSpacing = 25
        let w = (kScreenWidth - 26 * 2 - 25 * 2)/3
        self.layout.itemSize = CGSize(width: w, height: w * 39/88)

        self.collectionView.delegate = self
        self.collectionView.dataSource = self
        self.collectionView.register(UINib.init(nibName: "ServicesCategoriesCollectionViewCell", bundle: nil), forCellWithReuseIdentifier: ServicesCategoriesCollectionViewCell.description())
        

    }
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: ServicesCategoriesCollectionViewCell.description(), for: indexPath) as! ServicesCategoriesCollectionViewCell
        cell.model = self.serviceArr[indexPath.row]
        return cell
    }
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        
        return self.serviceArr.count
    }
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
         let model = self.serviceArr[indexPath.row]
        if self.lastSelectModel == nil{
            model.isSelected = true
            self.lastSelectModel = model
            
            self.showServiceListByClassID(model: self.lastSelectModel, type: "2")
            return
        }
        if model.class_id == self.lastSelectModel.class_id{
            return
        }
        for m in self.serviceArr {
            if m.class_id == self.lastSelectModel.class_id{
                m.isSelected = false
            }
        }
        model.isSelected = true
        self.lastSelectModel = model
        self.showServiceListByClassID(model: self.lastSelectModel, type: "2")
    }
    
    private func showServiceListByClassID(model:ServicesCategoriesModel, type: String){
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?getServiceClasss", at: self.responderViewController()!, params: ["class_parent_id": model.class_id!], isAuthHide: false, isShowHUD: false, isShowError: false, hasHeaderRefresh: nil, success: { (response) in
            let data = response["data"] as! NSArray
            self.serviceArr.removeAll()
            let tempArr = [ServicesCategoriesModel].deserialize(from: data) as! [ServicesCategoriesModel]
            if tempArr.count == 0{
                self.selectModelArr.append(model)
                var str : NSString = ""
                for model in self.selectModelArr{
                    str = str.appending(model.class_name!).appending(",") as NSString
                }
                
                let finish = str.substring(to: str.length - 1) 
                self.finishSelect?((finish,model.class_price!))
                self.remove()
            }else{
                self.serviceArr += [ServicesCategoriesModel].deserialize(from: data) as! [ServicesCategoriesModel]
                if type == "1"{
                    if self.selectModelArr.count > 0{
                        self.selectModelArr.removeLast()
                    }
                }else{
                    self.newAddModel = model
                    self.selectModelArr.append(model)
                }
                self.collectionView.reloadData()
            }
            
        }) { 
            
        }
    }
    
    @IBAction func dismissAction(_ sender: UIButton) {
        
        self.remove()
    }
    
    @IBAction func lastServiceAction(_ sender: UIButton) {
        
        
        for (index, model) in self.selectModelArr.enumerated() {
            if model.class_id == self.lastSelectModel.class_id{
                self.selectModelArr.remove(at: index)
            }
            if newAddModel.class_id != self.lastSelectModel.class_id{
                if model.class_id == newAddModel.class_id{
                    self.selectModelArr.remove(at: index)
                }
            }
        }
        if self.selectModelArr.count == 0{
            self.lastSelectModel = nil
            let model = ServicesCategoriesModel()
            model.class_id = "0"
            self.showServiceListByClassID(model: model, type: "1")
            return
        }
        self.showServiceListByClassID(model: (self.selectModelArr.last)!, type: "1")
    }
    @IBAction func nextServiceAction(_ sender: UIButton) {
        
        if self.lastSelectModel == nil{
            
            ProgressHUD.showMessage(message: "请选择服务类别")
            return
        }
        var index = 0
        
        for m in self.serviceArr {
            
            if m.isSelected{
                
                index += 1
            }
        }
        if index == 0{
            
            ProgressHUD.showMessage(message: "请选择服务类别")
            return
        }
        self.showServiceListByClassID(model: self.lastSelectModel, type: "2")
    }
    
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */

}
