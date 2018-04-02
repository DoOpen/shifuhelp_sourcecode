//
//  EvaluateTableViewCell.swift
//  BaiShiXueYiLiving
//
//  Created by sh-lx on 2017/6/23.
//  Copyright © 2017年 liangyi. All rights reserved.
//

import UIKit

class EvaluateTableViewCell: UITableViewCell,UITextViewDelegate{
    
    @IBOutlet weak var goodsImage: UIImageView!

    @IBOutlet weak var starView: UIView!
    
    @IBOutlet weak var textView: UITextView!
    
    @IBOutlet weak var addPhotoView: UIView!
    
    @IBOutlet weak var placeHolderLab: UILabel!
    
    var addBtn:UIButton!
    var stars: TggStarEvaluationView!
    var photo: BBSAddPhotoView!
    var imagesData: [UIImage] = []
    
    private let butWidth:Float = 57
    
    let model = SubcommentModel()
    
    var submitModel: ((SubcommentModel)->())?
    var tmodel:OrderGoodsBeans!{
        willSet(m){
            
            model.order_id = m.order_id
            model.relation_id = m.goods_id
            self.goodsImage.kf.setImage(with: URL(string:m.goods_img!)!)
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        self.textView.delegate = self
    }
    
    func textViewDidChange(_ textView: UITextView) {
        if textView.text.characters.count == 0{
            self.placeHolderLab.isHidden = false
        }else{
            self.placeHolderLab.isHidden = true
        }
    }
    func textViewDidEndEditing(_ textView: UITextView) {
        self.model.assessment_desc = textView.text
        self.model.relation_id = self.tmodel.goods_id
        self.model.order_id = self.tmodel.order_id
        self.submitModel?(model)
    }
    override func layoutSubviews() {
        super.layoutSubviews()
        stars = TggStarEvaluationView()
        stars.starCount = UInt(self.model.assessment_star1)
        stars.frame = CGRect(x: 0, y: (60 - 45)/2, width:240, height: 45)
        stars.backgroundColor = UIColor.white
        stars.isTapEnabled = true
        stars.evaluateViewChooseStarBlock = { count in
            self.model.assessment_star1 = Int(count)
            self.model.order_id = self.tmodel.order_id
            self.model.relation_id = self.tmodel.goods_id
            self.submitModel?(self.model)
        }
        self.starView.addSubview(stars)
        
        addBtn = UIButton(type: .custom)
        addBtn.setImage(#imageLiteral(resourceName: "upload"), for: .normal)
        addBtn.addTarget(self, action: #selector(addPhotoAction), for: .touchUpInside)
        addBtn.frame = CGRect(x: 16, y: 0, width: Int(butWidth), height: Int(butWidth))
        self.addPhotoView.addSubview(addBtn)
    
        
    }
    func addPhotoAction(){
        
        let action = ZLPhotoActionSheet()
        action.maxSelectCount = 3 - imagesData.count
        action.showPhotoLibrary(withSender: self.responderViewController()!, last: []) { (selectPhotos, selectPhotoModels) in
            self.imagesData = self.imagesData + selectPhotos
            if self.imagesData.count == 0{
                self.model.assessmentImgBeans = []
                self.model.order_id = self.tmodel.order_id
                self.model.relation_id = self.tmodel.goods_id
                self.submitModel?(self.model)
                self.updateFrame()
            } else{
                NetworkingHandle.uploadOneMorePicture(url: "orderInterfaces.api?uploadAssessmentImg", atVC: self.responderViewController()!, images: self.imagesData, uploadSuccess: { (response) in
                    let info = response["data"] as! [String]
                    for str in info{
                        let m = assessmentImgModel()
                        m.assessment_img = str
                        self.model.assessmentImgBeans.append(m)
                    }
                    self.model.order_id = self.tmodel.order_id
                    self.model.relation_id = self.tmodel.goods_id
                    self.submitModel?(self.model)
                    self.updateFrame()
                })
            }
            
        }
    }
    func updateFrame(){
        for v in self.addPhotoView.subviews {
            if ((v as? UIImageView) != nil) {
                v.removeFromSuperview()
            }
        }
        
        let butGap: Float = 5
        imagesData.enumerateKeysAndObjects { (obj, idx) in
            let columnIndex = idx % 4
            let rowIndex = idx / 4
            let imageView = UIImageView(frame: CGRect(x: CGFloat(columnIndex) * CGFloat(butWidth + butGap), y: CGFloat(rowIndex) * CGFloat(butWidth + butGap), width: CGFloat(butWidth), height: CGFloat(butWidth)))
            imageView.image = obj
            imageView.tag = idx
            imageView.isUserInteractionEnabled = true
            self.addPhotoView.addSubview(imageView)
            
            let tap = UITapGestureRecognizer(target: self, action: #selector(clickImage(sender:)))
            imageView.addGestureRecognizer(tap)
        }
        if imagesData.count == 3 {
            addBtn.isHidden = true
        } else {
            addBtn.isHidden = false
        }
        let a = imagesData.count % 3
        let b = imagesData.count / 3
        if imagesData.count == 0 {
            
            addBtn.frame = CGRect(x: 16, y: 0, width: Int(butWidth), height: Int(butWidth))

        }else{
            
            addBtn.frame = CGRect(x: CGFloat(a) * CGFloat(butWidth + butGap), y: CGFloat(b) * CGFloat(butWidth + butGap), width: CGFloat(butWidth), height: CGFloat(butWidth))
        }
        
    }
    func clickImage(sender: UITapGestureRecognizer) {
        let v = sender.view
        imagesData.remove(at: (v?.tag)!)
        if imagesData.count == 0 {
            self.model.assessmentImgBeans = []
            self.model.order_id = self.tmodel.order_id
            self.submitModel?(self.model)
            self.updateFrame()
            return
        } else{
            NetworkingHandle.uploadOneMorePicture(url: "orderInterfaces.api?assessmentOrder", atVC: self.responderViewController()!, images: self.imagesData, uploadSuccess: { (response) in
                let info = response["data"] as! [String]
                for str in info{
                    let m = assessmentImgModel()
                    m.assessment_img = str
                    self.model.assessmentImgBeans.append(m)
                }
                self.model.order_id = self.tmodel.order_id
                self.submitModel?(self.model)
                self.updateFrame()
            })
        }
    }
    func getValueForSubmitModel(){
        self.model.order_id = self.tmodel.order_id
        self.model.relation_id = self.tmodel.goods_id
        if self.imagesData.count == 0 {
            self.model.assessmentImgBeans = []
        }
        if self.textView.text.characters.count == 0 {
            self.model.assessment_desc = ""
        }
        
        
    }
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
class SubcommentModel: HandyJSON {
    var order_id : String!
    var relation_id : String!
    var assessment_type  = "goods"
    var assessment_star1 = 5
    var assessment_desc: String!
    var assessmentImgBeans : [assessmentImgModel]! = []
    var member_id = LHWEUserInfoHandler.getUserInfo()?.member_id
    required init(){}
    
}
class assessmentImgModel: HandyJSON {
    var assessment_img : String!
    required init(){}
    
}
