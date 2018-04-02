//
//  PersonInfoEditViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/21.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class PersonInfoEditViewController: UIViewController,UITableViewDelegate, UITableViewDataSource, UINavigationControllerDelegate, UIImagePickerControllerDelegate{

    @IBOutlet weak var tableView: UITableView!
    var model : LHWEUserInfoModel!
    var titleArr : [[String]] = []
    var detailArr : [[String]] = []
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = "个人资料"
        
        self.tableView.tableFooterView = UIView()
        self.getUserInfo()
        self.titleArr = [["本人头像"],["姓名","性别","年龄","地址", "手机号"],["工种","工龄"],["证书"]]
        self.getUserInfo()
        
        // Do any additional setup after loading the view.
    }
    func getUserInfo() {
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getMemberDetail", at: self,isAuthHide: false, isShowHUD: false, success: { (response) in
            let data = response["data"] as! NSDictionary
            self.model = LHWEUserInfoModel.deserialize(from: data)!
            let str = (self.model?.member_service_province!)! + (self.model?.member_service_city!)! + (self.model?.member_service_district!)!
            let str2 = str + (self.model?.member_service_detail!)!
            self.detailArr = [[(self.model?.member_head_image)!],[(self.model?.member_real_name)!,(self.model?.member_sex)!,(self.model?.member_age)!,  str2, (self.model?.member_phone)!],[(self.model?.member_work_type)!, (self.model?.member_work_age ?? "3")!],[""]]
            self.tableView.reloadData()
        })
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
         let cell = UITableViewCell(style: .value1, reuseIdentifier: "cell")
        cell.selectionStyle = .none
        cell.textLabel?.font = UIFont.systemFont(ofSize: 16)
        cell.textLabel?.textColor = UIColor(hexString: "#808080")
        cell.detailTextLabel?.font = UIFont.systemFont(ofSize: 16)
        cell.detailTextLabel?.textColor = UIColor.init(hexString: "#242424")
        cell.textLabel?.text = self.titleArr[indexPath.section][indexPath.row]
        if indexPath.section == 0 {
            cell.contentView.addSubview(userImage)
            userImage.snp.makeConstraints({ (make) in
                make.centerY.equalTo(cell.snp.centerY)
                make.right.equalTo(-16)
                make.size.equalTo(CGSize(width: 60, height: 60))
            })
        } else if indexPath.section == 3{
            
            cell.accessoryType = .disclosureIndicator
            
        }else{
            
            cell.detailTextLabel?.text = self.detailArr[indexPath.section][indexPath.row]
            
        }
        return cell
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return self.detailArr.count
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.detailArr[section].count
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.section == 0{
            return 81
        }
        return 58
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 10
    }
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 0.1
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if indexPath.section == 0{
            clickAvatar()
        }
        if indexPath.section == 3{
            let vc = CommanWebViewController()
            vc.url = NetworkingHandle.mainHost + (self.model?.member_certificate)!
            vc.title = "我的证书"
            self.navigationController?.pushViewController(vc, animated: true)
        }
    }
    //MARK: 点击事件
    func clickAvatar() {//点击头像
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
            updateAvatarImage(img: image)
        }
    }
    //MARK: 上传图片
    func updateAvatarImage(img: UIImage) {
        NetworkingHandle.uploadPicture(url: "memberInterfaces.api?uploadMemberImg", atVC: self, image: img, params: ["key" : "1"], uploadSuccess: { (response) in
            self.userImage.image = img
            let data = response["data"] as! String
            LHWEUserInfoHandler.updateImg(img: NetworkingHandle.mainHost + data)
            NotificationCenter.default.post(name: NSNotification.Name.init(rawValue:"image"), object: nil)
            
        })
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

    fileprivate lazy var userImage : UIImageView = {
        
        let imageView = UIImageView()
        imageView.layer.cornerRadius = 30;
        imageView.layer.masksToBounds = true
        imageView.kf.setImage(with: URL(string: (LHWEUserInfoHandler.getUserInfo()?.member_head_image)!))
        return imageView
        
    }()
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
