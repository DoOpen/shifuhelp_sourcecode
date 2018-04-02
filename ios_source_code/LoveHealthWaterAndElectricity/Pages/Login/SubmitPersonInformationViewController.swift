//
//  SubmitPersonInformationViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/28.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import IQKeyboardManagerSwift

class SubmitPersonInformationViewController: UIViewController, UINavigationControllerDelegate, UIImagePickerControllerDelegate {

    
    
    @IBOutlet weak var headImg: UIImageView!
    
    @IBOutlet weak var nameTF: UITextField!
    
    @IBOutlet weak var sexLab: UILabel!
    
    
    @IBOutlet weak var ageTF: UITextField!
    
    @IBOutlet weak var pcd: UILabel!
    
    
    
    @IBOutlet weak var workKindsLab: UILabel!
    @IBOutlet weak var workYearsTF: UITextField!
    
    @IBOutlet weak var submitBtn: UIButton!
    
    @IBOutlet weak var detailAddressTF: UITextField!
    
    @IBOutlet weak var phoneTF: UITextField!
    
    var province : String!
    var city : String!
    var district : String!
    var workTypeArr = [WorkTypeModel]()
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = "完善个人资料"
        
        
        
        
        self.submitBtn.layer.cornerRadius = kCornerRadius
        self.submitBtn.layer.masksToBounds = true
        
        self.headImg.layer.cornerRadius = 60/2
        self.headImg.layer.masksToBounds = true
        self.headImg.contentMode = .scaleAspectFill
        self.headImg.clipsToBounds = true
        // Do any additional setup after loading the view.
    }

    @IBAction func updateImg(_ sender: UIButton) {
        let alert = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
        let action1 = UIAlertAction(title: "从相册上传", style: .default, handler: { (acion) in
            self.openImagePickerController(sourceType: .photoLibrary)
        })
        let action2 = UIAlertAction(title: "拍照", style: .default, handler: { (acion) in
            self.openImagePickerController(sourceType: .camera)
        })
        let cancelAction = UIAlertAction(title: "取消", style: .cancel, handler:nil)
        alert.addAction(action1)
        alert.addAction(action2)
        alert.addAction(cancelAction)
        self.present(alert, animated: true, completion: nil)
    }
    func openImagePickerController(sourceType :UIImagePickerControllerSourceType) {
        let pickerVC = UIImagePickerController()
        pickerVC.view.backgroundColor = UIColor.white
        pickerVC.delegate = self
        pickerVC.allowsEditing = false
        if UIImagePickerController.isSourceTypeAvailable(sourceType) {
            pickerVC.sourceType = sourceType
        }
        self.present(pickerVC, animated: true, completion: nil)
    }
    //MARK: UIImagePickerControllerDelegate
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
        dismiss(animated: true, completion: nil)
        if let image = info[UIImagePickerControllerOriginalImage] as? UIImage {
            
           self.headImg.image = image
        }
    }
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        dismiss(animated: true, completion: nil)
    }
    // 设置 imagePicker 导航栏
    func navigationController(_ navigationController: UINavigationController, willShow viewController: UIViewController, animated: Bool) {
        navigationController.navigationBar.tintColor = UIColor.white
        navigationController.navigationBar.titleTextAttributes = [NSForegroundColorAttributeName: UIColor.white, NSFontAttributeName:defaultFont(size: 16)]
        navigationController.navigationBar.barTintColor = themeColor
    }
    
    @IBAction func selectSexAction(_ sender: UIButton) {
        let vc = UIAlertController.init(title: "选择性别", message: "", preferredStyle: .actionSheet)
        let manActionSheet = UIAlertAction.init(title: "男", style: .default) { (alert) in
            self.sexLab.text = "男"
        }
        let womanActionSheet = UIAlertAction.init(title: "女", style: .default) { (alert) in
            self.sexLab.text = "女"
        }
        let cancelActionSheet = UIAlertAction.init(title: "取消", style: .cancel, handler: nil)
        vc.addAction(manActionSheet)
        vc.addAction(womanActionSheet)
        vc.addAction(cancelActionSheet)
        self.present(vc, animated: true, completion: nil)
        
    }
    
    @IBAction func selectProvinceCityDistrictAction(_ sender: UIButton) {
        self.view.endEditing(true)
        let v = ProvinceCityDistrictChoice(frame: self.view.bounds)
        v.show(choiced: { [unowned self] (p, c, d) in
            self.province = p
            self.city = c
            self.district = d
            if p == c{
                self.pcd.text = p + "-" + d
            }else{
                self.pcd.text = p + "-" + c + "-" + d
            }
        })
        self.view.addSubview(v)
    }
    
    
    @IBAction func selectWorkKindsAction(_ sender: UIButton) {
        
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getWorkTypeList", at: self, success: { (response) in
            let data = response["data"] as! NSArray
            self.workTypeArr.removeAll()
            self.workTypeArr = [WorkTypeModel].deserialize(from: data) as! [WorkTypeModel]
            self.reportAlert(arr: self.workTypeArr)
        })
    }
    // 举报
    func reportAlert(arr: [WorkTypeModel]) {
       _ = SelectWorkTypeView.showWorkTypeView(atView: self.view, workTypeArr: arr, selectWorkType: { (str) in
            self.workKindsLab.text = str
        })
        
//        //        UIImageWriteToSavedPhotosAlbum(img.image!, self, nil, nil)
//        let alert = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
//        for model in arr {
//            let alertAction = UIAlertAction(title: model.type_name!, style: .default) { (action) in
//               self.workKindsLab.text = model.type_name
//            }
//            alert.addAction(alertAction)
//        }
//        let cancel = UIAlertAction(title: "取消", style: .cancel, handler: nil)
//        alert.addAction(cancel)
//        self.present(alert, animated: true, completion: nil)
    }
    
    @IBAction func submitAction(_ sender: UIButton) {
       
        if !(self.phoneTF.text?.isPhoneNumber)!{
            ProgressHUD.showMessage(message: "手机号格式不正确")
            return
        }
        if self.nameTF.text!.characters.count == 0  || self.ageTF.text!.isEmpty || self.workKindsLab.text!.characters.count == 0 || self.workYearsTF.text!.characters.count == 0 || self.headImg.image == nil || self.detailAddressTF.text?.characters.count == 0 || self.pcd.text?.characters.count == 0{
            
            ProgressHUD.showMessage(message: "请完善信息")
            return
        }
        let params = ["member_real_name":self.nameTF.text,"member_sex":self.sexLab.text,"member_age":self.ageTF.text, "member_work_type":self.workKindsLab.text, "member_work_age":self.workYearsTF.text, "member_service_phone":self.phoneTF.text, "member_service_province":self.province, "member_service_city":self.city, "member_service_district":self.district,"member_service_detail":self.detailAddressTF.text] as [String:AnyObject]
        
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?updateMemberDetail", at: self, params: params, isAuthHide: false, isShowHUD: true, isShowError: false, hasHeaderRefresh: nil, success: { (response) in
            ProgressHUD.showMessage(message: "提交成功")
            let model = LHWEUserInfoModel()
            model.member_real_name = self.nameTF.text
            model.member_sex = self.sexLab.text
            model.member_age = self.ageTF.text
            model.member_work_age = self.workYearsTF.text
            model.member_work_type = self.workKindsLab.text
            model.member_service_province = self.province
            model.member_service_city = self.city
            model.member_service_district = self.district
    
            LHWEUserInfoHandler.saveBaseUserInfo(model: model)
            self.navigationController?.pushViewController(CheckInformationViewController(), animated: true)
        }) { 
            ProgressHUD.showMessage(message: "请重新提交")
        }
        NetworkingHandle.uploadPicture(url: "memberInterfaces.api?uploadMemberImg", atVC: self, image: self.headImg.image!, uploadSuccess: { (response) in
            if let data = response["data"] as? String{
                LHWEUserInfoHandler.updateImg(img: NetworkingHandle.mainHost + data)
            }
        })
        
        
    }
    override func viewWillAppear(_ animated: Bool) {
        setStatusBarBGColor(color: themeColor)
        setKeyBoardManagerIsEnable()
        self.navigationController?.setNavigationBarHidden(false, animated: false)
    }
    override func viewWillDisappear(_ animated: Bool) {
        
        setKeyBoardManagerDisable()
        self.navigationController?.setNavigationBarHidden(true, animated: true)
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
