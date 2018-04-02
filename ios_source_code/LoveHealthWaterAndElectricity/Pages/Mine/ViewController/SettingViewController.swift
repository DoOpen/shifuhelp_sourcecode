//
//  SettingViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/21.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import Kingfisher

class SetVCModel: NSObject {
    var img : UIImage
    var title : String
    init(img: UIImage, title: String) {
        self.img = img
        self.title = title
    }
}
class SettingViewController: UIViewController, UITableViewDelegate,UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    var footer = SetTableFooterView.setView()
    var titleArr: [[SetVCModel]]!
    var cacheLab : UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "个人资料"
        self.titleArr = [[SetVCModel.init(img: #imageLiteral(resourceName: "grzl"), title: "个人资料")],[SetVCModel.init(img: #imageLiteral(resourceName: "xgmm"), title: "修改密码")], [SetVCModel.init(img: #imageLiteral(resourceName: "qchc"), title: "清除缓存")], [SetVCModel.init(img: #imageLiteral(resourceName: "about_us"), title: "关于我们")], [SetVCModel.init(img: #imageLiteral(resourceName: "xgxy"), title: "相关协议")]]
        self.tableView.register(UINib.init(nibName: "SettingTableViewCell", bundle: nil), forCellReuseIdentifier: SettingTableViewCell.description())
        
        self.tableView.tableFooterView = self.footer
        footer.quitBlock = { [unowned self] in
            let alertController = UIAlertController(title: "提示", message: "是否确定退出登录", preferredStyle: .alert)
            let cancelAction = UIAlertAction(title: "取消", style: .cancel, handler: nil)
            let okAction = UIAlertAction(title: "确定", style: .default) { (_) in
                DispatchQueue.main.async {
                    LHWEUserInfoHandler.deleteUserInfo()
                    UIApplication.shared.keyWindow?.rootViewController = LoginNavigationController.setup()
                }
            }
            alertController.addAction(cancelAction)
            alertController.addAction(okAction)
            self.present(alertController, animated: true, completion: nil)

            
        }
        // Do any additional setup after loading the view.
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: SettingTableViewCell.description()) as! SettingTableViewCell
        cell.img.image = self.titleArr[indexPath.section][indexPath.row].img
        cell.title.text = self.titleArr[indexPath.section][indexPath.row].title
        if indexPath.section == 2{
            cell.detailImg.isHidden = true
            if self.cacheLab == nil{
                self.cacheLab = UILabel()
                self.cacheLab.text = CacheTool.cacheSize
                self.cacheLab.textColor = UIColor.init(hexString: "808080")
                self.cacheLab.font = defaultFont(size: 15)
                cell.contentView.addSubview(self.cacheLab)
                self.cacheLab.snp.makeConstraints({ (make) in
                    make.right.equalTo(-25)
                    make.centerY.equalTo(cell.snp.centerY)
                })
            }else{
                self.cacheLab.text = CacheTool.cacheSize
            }
            
        }
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.titleArr[section].count
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return self.titleArr.count
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        if section == 2{
            return 1
        }
        return 10
    }
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 0.1
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if indexPath.section == 0{
            self.navigationController?.pushViewController(PersonInfoEditViewController(), animated: true)
        }
        if indexPath.section == 1{
            if indexPath.row == 0{
                let vc = ForgetPasswordViewController()
                vc.title = "修改密码"
                self.navigationController?.pushViewController(vc, animated: true)
            }else{
                
            }
        }
        if indexPath.section == 2{
            
            KingfisherManager.shared.cache.clearDiskCache()
            KingfisherManager.shared.cache.clearMemoryCache()
            KingfisherManager.shared.cache.cleanExpiredDiskCache()
            //webview的缓存
            URLCache.shared.removeAllCachedResponses()
            CacheTool.clearCache(success: {
                 self.tableView.reloadData()
            })
            
            
            
            
        }
        if indexPath.section == 3{
            let vc = CommanWebViewController()
            vc.url = NetworkingHandle.mainHost + AboutUsURL
            vc.title = "关于我们"
            self.navigationController?.pushViewController(vc, animated: true)
        }
        if indexPath.section == 4 {
            let vc = CommanWebViewController()
            vc.url = NetworkingHandle.mainHost + AboutAgreementURL
            vc.title = "协议"
            self.navigationController?.pushViewController(vc, animated: true)
        }
        
    }
    func computeCache() -> String {
        
        let acache: CGFloat = CGFloat(EMSDImageCache.shared().getSize())
        return String(format: "%.1f", acache / 1024 / 1024)  + "MB"
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
