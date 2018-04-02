//
//  MineViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/16.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class VCModel: NSObject {
    var itemImg: String
    var vc: UIViewController.Type
    var title : String
    init(itemImg: String, title: String, vc: UIViewController.Type) {
        self.title = title
        self.itemImg = itemImg
        self.vc = vc
    }
}
class MineViewController: UIViewController,UICollectionViewDelegate,UICollectionViewDataSource, UICollectionViewDelegateFlowLayout,UINavigationControllerDelegate{

    @IBOutlet weak var collectionView: UICollectionView!
    
    @IBOutlet weak var layout: UICollectionViewFlowLayout!
    
    var vcArr: [VCModel] = []
    
    
    var model : LHWEUserInfoModel!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationController?.delegate = self
        
        self.collectionView.showsHorizontalScrollIndicator = false
        self.collectionView.showsVerticalScrollIndicator = false
        
        

        
        self.vcArr = [VCModel.init(itemImg: "lishigongdan", title: "历史工单", vc: HistoryWorkFormViewController.self),VCModel.init(itemImg: "fwdz", title: "我的地址", vc: ServiceAddressViewController.self),VCModel.init(itemImg: "qianbao", title: "我的钱包", vc: MyWalletViewController.self),VCModel.init(itemImg: "wdbb", title: "我的报备", vc: MyReportViewController.self),VCModel.init(itemImg: "jinengrenzheng", title: "技能认定", vc: SkillsCertificateViewController.self),VCModel.init(itemImg: "peixunxueyuan", title: "培训学院", vc: TrainCollegeViewController.self),VCModel.init(itemImg: "zhengshu", title: "我的证书", vc: CommanWebViewController.self),VCModel.init(itemImg: "feiyong", title: "费用标准", vc: CommanWebViewController.self), VCModel.init(itemImg: "kefu", title: "联系客服", vc: UIViewController.self),VCModel.init(itemImg: "share", title: "软件分享", vc: UIViewController.self),VCModel.init(itemImg: "ruanjian", title: "软件相关", vc: AboutUSViewController.self),VCModel.init(itemImg: "", title: "", vc: UIViewController.self)]
        
        
        self.collectionView.register(UINib.init(nibName: "VCKindsCollectionViewCell", bundle: nil), forCellWithReuseIdentifier: VCKindsCollectionViewCell.description())
        self.collectionView.register(UINib.init(nibName: "MineCollectionReusableView", bundle: nil), forSupplementaryViewOfKind: UICollectionElementKindSectionHeader, withReuseIdentifier: MineCollectionReusableView.description())
        self.collectionView.register(UINib.init(nibName: "ReportCollectionReusableView", bundle: nil), forSupplementaryViewOfKind: UICollectionElementKindSectionHeader, withReuseIdentifier: ReportCollectionReusableView.description())
        self.collectionView.bounces = false
        self.layout.minimumLineSpacing = 0
        self.layout.minimumInteritemSpacing = 0
        // Do any additional setup after loading the view.
    }
    func getUserInfo() {
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getMemberDetail", at: self,isAuthHide: false, isShowHUD: false, success: { (response) in
            let data = response["data"] as! NSDictionary
            self.model = LHWEUserInfoModel.deserialize(from: data)!
            self.collectionView.reloadData()
        })
    }
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if indexPath.section == 0{
            
            let cell = collectionView.dequeueReusableCell(withReuseIdentifier: VCKindsCollectionViewCell.description(), for: indexPath) as! VCKindsCollectionViewCell
            let model = self.vcArr[indexPath.row]
            cell.itemImg.image = UIImage.init(named: model.itemImg)
            cell.title.text = model.title
//            if indexPath.row > self.vcArr.count - 5{
//                cell.line.isHidden = true
//            }
            return cell
        }
        return UICollectionViewCell.init()
        
        
    }
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.vcArr.count
    }
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 2
    }
    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        if indexPath.section == 0{
            
            let header = collectionView.dequeueReusableSupplementaryView(ofKind: UICollectionElementKindSectionHeader, withReuseIdentifier: MineCollectionReusableView.description(), for: indexPath) as! MineCollectionReusableView
            if self.model != nil{
                 header.model = self.model
            }
            if self.model != nil, self.model.is_sign == "1"{
               
                header.signInBtn.isEnabled = false
                header.signInBtn.setTitle("已签到", for: .normal)
                header.signInBtn.backgroundColor = UIColor(hexString: "#dbdbdb")
            }
            return header
        }
        let sHeader = collectionView.dequeueReusableSupplementaryView(ofKind: UICollectionElementKindSectionHeader, withReuseIdentifier: ReportCollectionReusableView.description(), for: indexPath)
        return sHeader
        
    }
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, referenceSizeForHeaderInSection section: Int) -> CGSize {
        if section == 0{
           return CGSize.init(width: kScreenWidth, height: kScreenWidth * 292/375)
        }
        return CGSize.init(width: kScreenWidth, height: 54)
    }
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        if indexPath.section == 0{
            return CGSize(width: kScreenWidth/4.0, height: kScreenWidth/4.0)
        }
        return CGSize.zero
    }
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        
        
        if self.vcArr[indexPath.row].vc == MyOrderViewController.self{
            let vcc = MyOrderViewController()
            vcc.type = 0
            self.navigationController?.pushViewController(vcc, animated: true)
            return
        }
        if self.vcArr[indexPath.row].title == "软件分享"{

            _ = CommanShareView.show(atView: UIApplication.shared.keyWindow!, url: APPDownLoadURL, avatar: "logo", username: (LHWEUserInfoHandler.getUserInfo()?.member_real_name)!, desc: "", isMineVC: true)
            return
        }
        if self.vcArr[indexPath.row].title == "我的地址"{
            let vcc = ServiceAddressViewController()
            if self.model != nil, self.model.member_service_name != ""{
                vcc.isHaveServiceAddress = true
                vcc.saModel = self.model
            }
            self.navigationController?.pushViewController(vcc, animated: true)
            return
        }
        if self.vcArr[indexPath.row].title == "联系客服"{
            callCustomerServices(atView: self.view, phoneNum: CustomerServicesPhone)
            return
        }
        if self.vcArr[indexPath.row].title == "我的证书"{
            let vcc = CommanWebViewController()
            vcc.title = self.vcArr[indexPath.row].title
            vcc.url = NetworkingHandle.mainHost + self.model.member_certificate!
            
            
            self.navigationController?.pushViewController(vcc, animated: true)
            return
        }
        if self.vcArr[indexPath.row].title == "费用标准"{
            let vcc = CommanWebViewController()
            vcc.title = self.vcArr[indexPath.row].title
            vcc.url = NetworkingHandle.mainHost + FeeRuleURL
            self.navigationController?.pushViewController(vcc, animated: true)
            return
        }
        if self.vcArr[indexPath.row].title == ""{
            return
        }
        let vc = self.vcArr[indexPath.row].vc.init()
        vc.title = self.vcArr[indexPath.row].title
        self.navigationController?.pushViewController(vc, animated: true)
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func navigationController(_ navigationController: UINavigationController, willShow viewController: UIViewController, animated: Bool) {
        
        let isShow = viewController.isKind(of: MineViewController.self)
        let statusBarBG = UIApplication.shared.value(forKey: "statusBarWindow") as! UIView
        let statusBarView = statusBarBG.value(forKey: "statusBar") as! UIView
        if isShow{
           
            statusBarView.backgroundColor = UIColor.clear
        }else{
            statusBarView.backgroundColor = themeColor
        }
        self.navigationController?.setNavigationBarHidden(isShow, animated: true)
        
    }
    override func viewWillAppear(_ animated: Bool) {
     //   super.viewWillAppear(animated)
        self.collectionView.reloadData()
        self.getUserInfo()
        setStatusBarBGColor(color: UIColor.clear)
        if #available(iOS 11.0, *) {
            
            self.collectionView.contentInsetAdjustmentBehavior = .never
            
        } else {
            self.automaticallyAdjustsScrollViewInsets = false
            // Fallback on earlier versions
            
        }
        
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        setStatusBarBGColor(color: themeColor)
        if #available(iOS 11.0, *) {
            self.collectionView.contentInsetAdjustmentBehavior = .always
        }else{
            self.automaticallyAdjustsScrollViewInsets = true
        }
    }
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
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
