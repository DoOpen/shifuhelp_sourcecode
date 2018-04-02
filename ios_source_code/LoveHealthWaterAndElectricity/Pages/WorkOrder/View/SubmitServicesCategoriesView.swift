//
//  SubmitServicesCategoriesView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/28.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class SubmitServicesCategoriesView: UIView, UITextViewDelegate, BBSAddPhotoViewDelegate{

   
    @IBOutlet weak var bgBtn: UIButton!
    
    @IBOutlet weak var serviceCategory: UILabel!
    
    @IBOutlet weak var photoView: UIView!
    
    @IBOutlet weak var textView: UITextView!
    
    @IBOutlet weak var tip: UILabel!
    
    @IBOutlet weak var backView: UIView!
    
    @IBOutlet weak var sumServicePriceTF: UITextField!
    
    var serviceStr : String!
    var servicePrice : String!
    var orderID : String!
    var imageStrArr : [String?] = []
    var addPhotoView: BBSAddPhotoView!

    class func show(atView:UIView, serviceStr: String, servicePrice: String, order_id: String) -> SubmitServicesCategoriesView{
        
        let view = Bundle.main.loadNibNamed("SubmitServicesCategoriesView", owner: nil, options: nil)?.first as! SubmitServicesCategoriesView
        atView.addSubview(view)
        let attributedStr = NSMutableAttributedString.init(string: serviceStr + "(" + servicePrice + ")")
        attributedStr.addAttributes([NSForegroundColorAttributeName : UIColor.init(hexString: "#EB6900")], range: NSRange.init(location: serviceStr.characters.count, length: servicePrice.characters.count + 2))
        view.serviceCategory.attributedText = attributedStr
        view.orderID = order_id
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
        self.textView.delegate = self
        
        
        addPhotoView = BBSAddPhotoView()
        addPhotoView.myDelegate = self
        addPhotoView.imageReduceBlock = { imageArray in
            if imageArray.count == 0 {
                self.imageStrArr.removeAll()
                self.addPhotoView.updateLayout()
            }else{
                NetworkingHandle.uploadOneMorePicture(url: "memberInterfaces.api?uploadReportedImg", atVC: self.responderViewController()!, images: imageArray, uploadSuccess: { (response) in
                    
                    self.imageStrArr = response["data"] as! [String]
                    self.addPhotoView.updateLayout()
                })
            }
        }
        self.photoView.addSubview(addPhotoView)
        
        addPhotoView.snp.makeConstraints { (make) in
            make.left.equalTo(16)
            make.width.equalTo(kScreenWidth - 32)
            make.bottom.equalTo(self.photoView.snp.bottom)
            make.height.equalTo(81)
        }


    }
    //MARK: - delegate
    
    func bbsAddPhotoView(addPhotoView: BBSAddPhotoView, clickButton index:Int)
    {
        if index == 100 {
            
            let actionSheet = ZLPhotoActionSheet()
            actionSheet.maxSelectCount = 3 - addPhotoView.imagesData.count
            
            actionSheet.showPhotoLibrary(withSender: self.responderViewController()!, last: [], completion: {(selectPhotos, selectPhotoModels) in
                self.addPhotoView.imagesData = self.addPhotoView.imagesData + selectPhotos
                NetworkingHandle.uploadOneMorePicture(url: "memberInterfaces.api?uploadReportedImg", atVC: self.responderViewController()!, images: self.addPhotoView.imagesData, uploadSuccess: { (response) in
                    self.imageStrArr = (response["data"] as? [String])!
                    self.addPhotoView.updateLayout()
                })
                
            })
            
        }
    }

    func textViewDidChange(_ textView: UITextView) {
        if textView.text.characters.count == 0{
            self.tip.isHidden = false
        }else{
            self.tip.isHidden = true
        }
    }
    
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    
    @IBAction func dismissAction(_ sender: UIButton) {
        switch sender.tag {
        case 0:
            print("直接退出")
            self.remove()
        case 1:
            print("展示服务类别选项")
            NotificationCenter.default.post(name: NSNotification.Name.init(rawValue:ShowServicesCategoriesNotification), object: nil)
            self.remove()
        case 2:
            print("直接退出")
            self.remove()
        case 3:
            self.submitOrderFinishState()
            print("提交订单更改状态")
            
        default:
            return
        }
        
    }
    func submitOrderFinishState(){
//        if self.textView.text == nil {
//            ProgressHUD.showMessage(message: "请填写备注")
//            return
//        }
        if self.sumServicePriceTF.text?.characters.count == 0{
            ProgressHUD.showMessage(message: "请填写此次服务费用")
            return
        }
        if self.imageStrArr.count == 0 {
            ProgressHUD.showMessage(message: "请上传完工图片")
            return
        }
       
        var params : Dictionary<String,String> = ["type":"worker_complete","order_id":self.orderID,"order_complete_note":self.textView.text ?? "", "order_price":self.sumServicePriceTF.text!]
        for i in 0..<3{
            if i < self.imageStrArr.count{
                params["order_complete_img\(i + 1)"] = self.imageStrArr[i]
            }else{
                params["order_complete_img\(i + 1)"] = ""
            }
        }
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?updateOrderState", at: self, params: params, isAuthHide: true, isShowHUD: true, isShowError: true, hasHeaderRefresh: nil, success: { (response) in
            ProgressHUD.showSuccess(message: "提交成功")
            self.remove()
            let vcArr = self.responderViewController()?.navigationController?.viewControllers
            NotificationCenter.default.post(name: NSNotification.Name(rawValue:ChangeWorkerOrderStateNotification), object: nil, userInfo: ["reloadState":0])
            for vc in vcArr!{
                if vc.isKind(of: WorkOrderViewController.self){
                    self.responderViewController()?.navigationController?.popToViewController(vc, animated: true)
                }
            }
        }) { 
            
        }
    }
    deinit {
        NotificationCenter.default.removeObserver(self)
    }
    
}
