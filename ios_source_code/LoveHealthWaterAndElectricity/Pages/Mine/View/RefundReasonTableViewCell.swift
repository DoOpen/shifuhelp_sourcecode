//
//  RefundReasonTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/25.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class RefundReasonTableViewCell: UITableViewCell, UITextViewDelegate, BBSAddPhotoViewDelegate{

    @IBOutlet weak var textView: UITextView!
    
    @IBOutlet weak var photoView: UIView!
    
    @IBOutlet weak var tvPlaceholder: UILabel!
    
    @IBOutlet weak var limitNum: UILabel!
    
    var imageStrArr : [String?] = []
    var addPhotoView: BBSAddPhotoView!
    var textDidChange : ((String)->())?
    var imageArrChange : (([String])->())?
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        
        self.textView.delegate = self
        
        addPhotoView = BBSAddPhotoView()
        addPhotoView.myDelegate = self
        addPhotoView.imageReduceBlock = { imageArray in
            if imageArray.count == 0 {
                self.imageStrArr.removeAll()
                self.addPhotoView.updateLayout()
            }else{
                NetworkingHandle.uploadOneMorePicture(url: "orderInterfaces.api?uploadAssessmentImg", atVC: self.responderViewController()!, images: imageArray, uploadSuccess: { (response) in
                    
                    self.imageStrArr = response["data"] as! [String]
                    self.imageArrChange?(self.imageStrArr as! [String])
                    self.addPhotoView.updateLayout()
                })
            }
        }
        self.photoView.addSubview(addPhotoView)
        
        addPhotoView.snp.makeConstraints { (make) in
            make.left.equalTo(16)
            make.width.equalTo(kScreenWidth - 32)
            make.bottom.equalTo(self.limitNum.snp.top).offset(-20)
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
                NetworkingHandle.uploadOneMorePicture(url: "orderInterfaces.api?uploadAssessmentImg", atVC: self.responderViewController()!, images: self.addPhotoView.imagesData, uploadSuccess: { (response) in
                    self.imageStrArr = (response["data"] as? [String])!
                    self.imageArrChange?(self.imageStrArr as! [String])
                    self.addPhotoView.updateLayout()
                })
                
            })
            
        }
    }

    func textViewDidChange(_ textView: UITextView) {
        if textView.text.characters.count == 0{
            self.tvPlaceholder.isHidden = false
        }else{
            self.tvPlaceholder.isHidden = true
        }
        self.textDidChange?(textView.text)
    }
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
