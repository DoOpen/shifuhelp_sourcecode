//
//  MyReportDetailViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/27.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class MyReportDetailViewController: UIViewController{

    @IBOutlet weak var nameTF: UITextField!
    
    @IBOutlet weak var phoneTF: UITextField!
    
    @IBOutlet weak var addressLab: UILabel!
    
    
    @IBOutlet weak var photoView: UIView!
    
    @IBOutlet weak var image1: UIImageView!
    
    @IBOutlet weak var image2: UIImageView!
    
    @IBOutlet weak var image3: UIImageView!
    
    @IBOutlet weak var deleteBtn: UIButton!
    
    @IBOutlet weak var deleteBtnHeight: NSLayoutConstraint!
    
    var deleteSuccess : (()->())?
    
    var imageStrArr : [String?] = []
    var addPhotoView: BBSAddPhotoView!
    var model : ReportedListModel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "我的报备"
        self.deleteBtn.isHidden = true
        self.deleteBtnHeight.constant = 0
        if self.model.reported_state == "success"{
            self.deleteBtn.isHidden = false
            self.deleteBtnHeight.constant = 50
        }
        self.nameTF.text = self.model.reported_name
        self.phoneTF.text = self.model.reported_phone
        self.addressLab.text = self.model.province! + self.model.city! + self.model.district! + self.model.detail!
        if self.model.reported_img1 != ""{
            self.image1.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + self.model.reported_img1!)!)
        }
        if self.model.reported_img2 != ""{
            self.image2.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + self.model.reported_img2!)!)
        }
        if self.model.reported_img3 != ""{
            self.image3.kf.setImage(with: URL.init(string: NetworkingHandle.mainHost + self.model.reported_img3!)!)
        }

        // Do any additional setup after loading the view.
    }
    
    @IBAction func deleteReportAction(_ sender: UIButton) {
        let vc = UIAlertController.init(title: "提示", message: "是否删除此条报备信息", preferredStyle: .alert)
        let okAction = UIAlertAction.init(title: "确定", style: .default) { (alert) in
            NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?deleteReported", at: self, params: ["reported_id":self.model.reported_id!], success: { (response) in
                ProgressHUD.showSuccess(message: "删除陈功")
                self.deleteSuccess?()
                self.navigationController?.popViewController(animated: true)
            }, failure: { 
                
            })
        }
        let cancelAction = UIAlertAction.init(title: "取消", style: .cancel, handler: nil)
        vc.addAction(okAction)
        vc.addAction(cancelAction)
        self.present(vc, animated: true, completion: nil)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
