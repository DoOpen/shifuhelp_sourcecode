//
//  HomePageViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/16.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class HomePageViewController: UIViewController,UITableViewDelegate,UITableViewDataSource, UITextFieldDelegate{

    @IBOutlet weak var tableView: UITableView!
    var masterArr : [StarMasterModel] = []
    var currentFormModel : WorkFormModel!
    var model : LHWEUserInfoModel!
    
    let signBtn = UIButton.init(type: .custom)
    let locationBtn = UIButton.init(type: .custom)
    
    var isFirstReload = true
    
    fileprivate lazy var searchTF : UITextField = {
        let tf = UITextField()
        tf.delegate = self
        tf.returnKeyType = .search
        tf.backgroundColor = UIColor.white
        tf.clearButtonMode = .whileEditing
        let imageLeft = UIImageView.init(image: #imageLiteral(resourceName: "sousuo"))
        imageLeft.frame = CGRect.init(x: 0, y: 0, width: 25, height: 30)
        imageLeft.contentMode = .scaleAspectFill
        tf.leftView = imageLeft
        tf.leftViewMode = .always
        tf.layer.cornerRadius = kCornerRadius
        tf.layer.masksToBounds = true
        tf.placeholder = "搜索您想要的商品"
        tf.font = defaultFont(size: 14)
        tf.clearsOnBeginEditing = true
        tf.isUserInteractionEnabled = false
        return tf
    }()
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        LocationManager.shared.updateLocation()
        self.tabBarController?.tabBar.isHidden = false
        setStatusBarBackgroundColor()
    }
    func setStatusBarBackgroundColor() {
        
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        NotificationCenter.default.addObserver(self, selector: #selector(HomePageViewController.paySuccess), name: NSNotification.Name(rawValue: PaySuccessNotification), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(HomePageViewController.payFail), name: NSNotification.Name(rawValue: PayFailNotification), object: nil)
        
        NotificationCenter.default.addObserver(self, selector: #selector(HomePageViewController.jumpToWorkOrderVC(noti:)), name: NSNotification.Name(rawValue:JumpToWorkOrderVCNotification), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(HomePageViewController.reloadSignState(noti:)), name: NSNotification.Name(rawValue: ReloadSignStateNotification), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(HomePageViewController.reloadServiceAddressState(noti:)), name: NSNotification.Name(rawValue:ReloadHomeServiceAddressNotification), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(HomePageViewController.reloadLoaction(noti:)), name: NSNotification.Name(rawValue:"locationUpdate"), object: nil)
        
        self.tableView.separatorStyle = .none
        self.tableView.keyboardDismissMode = .onDrag
        self.tableView.register(UINib.init(nibName: "StarMasterShowTableViewCell", bundle: nil), forCellReuseIdentifier: StarMasterShowTableViewCell.description())
        self.tableView.register(UINib.init(nibName: "HomePageSectionView", bundle: nil), forHeaderFooterViewReuseIdentifier: HomePageSectionView.description())
        
        self.tableView.register(UINib.init(nibName: "CurrentWorkFormTableViewCell", bundle: nil), forCellReuseIdentifier: CurrentWorkFormTableViewCell.description())
        self.tableView.register(UINib.init(nibName: "InformationTableViewCell", bundle: nil), forCellReuseIdentifier: InformationTableViewCell.description())
        self.tableView.mj_header = MJRefreshNormalHeader(refreshingBlock: { [unowned self] in
            self.requestBannerData()
            self.getUserInfo()
        })
        self.tableView.mj_header.beginRefreshing()
       
        
        // Do any additional setup after loading the view.
    }
   
    func reloadLoaction(noti:NSNotification) {
        let locationDistrict = noti.userInfo!["district"] as? String
        locationBtn.setTitle(locationDistrict, for: .normal)
        
    }
    func paySuccess() {
        ProgressHUD.showMessage(message: "支付成功")
        self.tableView.mj_header.beginRefreshing()
    }
    func payFail(){
        ProgressHUD.showMessage(message: "支付失败")
    }
    func jumpToWorkOrderVC(noti:NSNotification) {
        self.navigationController?.pushViewController(WorkOrderViewController(), animated: true)
    }
    func getUserInfo() {
        
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getMemberDetail", at: self,isAuthHide: false, isShowHUD: false, success: { (response) in
            let data = response["data"] as! NSDictionary
            self.model = LHWEUserInfoModel.deserialize(from: data)!
            LHWEUserInfoHandler.saveUserInfo(model: self.model)
            NotificationCenter.default.post(name: NSNotification.Name(rawValue:UserInfoUploadNotification), object: nil, userInfo: ["model":self.model])
            if self.isFirstReload{
                self.setNavigationBar()
                self.isFirstReload = false
            }else{
                if self.model.is_sign == "0" {
                    self.signBtn.setTitle("签到", for: .normal)
                }else{
                    self.signBtn.setTitle("已签到", for: .normal)
                    self.signBtn.isEnabled = false
                    self.signBtn.isUserInteractionEnabled = false
                }
            }
            
        })
    }
    func reloadServiceAddressState(noti:NSNotification){
        let address = noti.userInfo!["address"] as? String
        
        
        locationBtn.setTitle(address, for: .normal)
        
    }
    func reloadSignState(noti:NSNotification) {
        self.signBtn.setTitle("已签到", for: .normal)
        self.signBtn.isEnabled = false
    }
    func setNavigationBar() {
        
        
        let view = UIView.init(frame: CGRect.init(x: 0, y: 0, width: 45, height: 45))
      
        self.navigationItem.leftBarButtonItem = UIBarButtonItem.init(customView: view)
        
        let imageBG = UIImageView.init(image: #imageLiteral(resourceName: "dingwei"))
        imageBG.frame = CGRect.init(x: 12.5, y: 5, width: 20, height: 20)
        view.addSubview(imageBG)
        
        
        locationBtn.frame = CGRect.init(x: 0, y: 25, width: 45, height: 15)
        locationBtn.setTitle(LocationManager.shared.district , for: .normal)
        locationBtn.titleLabel?.font = defaultFont(size: 11.0)
        locationBtn.titleLabel?.textColor = UIColor.white
        locationBtn.titleLabel?.textAlignment = .center
        view.addSubview(locationBtn)

        
        if self.isFirstReload {
            if self.model.is_sign == "0" {
                signBtn.setTitle("签到", for: .normal)
            }else{
                signBtn.setTitle("已签到", for: .normal)
                signBtn.isEnabled = false
                signBtn.isUserInteractionEnabled = false
            }
        }
        
        
        signBtn.setImage(#imageLiteral(resourceName: "qiandao"), for: .normal)
        
        signBtn.addTarget(self, action: #selector(HomePageViewController.sign(sender:)), for: .touchUpInside)
        signBtn.frame = CGRect.init(x: 0, y: 0, width: 45, height: 45)
        signBtn.setImageForwardLayout(btnType: .BottomForward)
        signBtn.titleLabel?.font = defaultFont(size: 11.0)
        signBtn.titleLabel?.textColor = UIColor.white
        self.navigationItem.rightBarButtonItem = UIBarButtonItem.init(customView: signBtn)
        
        let views = UIView(frame: CGRect(x: 0, y: 0, width: kScreenWidth - 180, height: 44))
        views.backgroundColor = UIColor.clear
        self.searchTF.frame = CGRect.init(x: 0, y: 7, width: kScreenWidth - 180, height: 30)
        views.addSubview(self.searchTF)
        let btn = UIButton.init(type: .custom)
        btn.addTarget(self, action: #selector(HomePageViewController.jumpToSearchVC), for: .touchUpInside)
        btn.frame = views.frame
        views.addSubview(btn)
        self.navigationItem.titleView = views
    }
    func sign(sender:UIButton) {
        NetworkingHandle.fetchNetworkData(url: "signInterfaces.api?insertSign", at: self, success: { (response) in
            sender.setTitle("已签到", for: .normal)
            sender.isEnabled = false
            
        })
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        if textField.text?.characters.count == 0 {
            ProgressHUD.showMessage(message: "请输入您想要的商品")
        }else{
            let vc = ShoppingMallNextViewController()
            vc.vcTitle = "商品分类"
            let str1 = NetworkingHandle.mainHost + "app-mall1/index.html#/search_goods?goodsName="
            let str2 = "&member_id=" + (LHWEUserInfoHandler.getUserIDAndToken()?.id)! + "&member_token=" + (LHWEUserInfoHandler.getUserIDAndToken()?.token)!
            let str3 = "&add=" + (LHWEUserInfoHandler.getUserInfo()?.member_service_district)!
            let url = str1 + textField.text!.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)! + str2 + str3.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
            //中文字符转码
            vc.url = url
            print(vc.url)
            self.navigationController?.pushViewController(vc, animated: true)
        }
        return true
    }
    func jumpToSearchVC(){
        let vc = SearchGoodsViewController()
        let str1 = NetworkingHandle.mainHost + "app-mall1/index.html#/search"
        let str2 = "?member_id=" + (LHWEUserInfoHandler.getUserIDAndToken()?.id)! + "&member_token=" + (LHWEUserInfoHandler.getUserIDAndToken()?.token)!
        let str3 = "&add=" + (self.model.member_service_district?.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!)!
        vc.url = str1 + str2 + str3
        vc.address = self.model.member_service_district!
        self.navigationController?.pushViewController(vc, animated: true)
    }

    func requestBannerData() {
        NetworkingHandle.fetchNetworkData(url: "settingInterfaces.api?getBannerList", at: self, params: ["banner_position":"app_home"], isAuthHide: true, isShowHUD: false, isShowError: false, hasHeaderRefresh: nil, success: { (response) in
            let data = response["data"] as! NSArray
            self.tableView.tableHeaderView = HomePageBannerView.set(modelArr: [HomePageBannerModel].deserialize(from: data)! as! [HomePageBannerModel])
            self.tableView.reloadData()
            self.requestStarMaster()
        }) {
             self.requestStarMaster()

        }
//        NetworkingHandle.fetchNetworkData(url: "bannerInterfaces.api?getAllBanners", at: self, success: { (response) in
//            let data = response["data"] as! NSArray
//            self.tableView.tableHeaderView = HomePageBannerView.set(modelArr: [HomePageBannerModel].deserialize(from: data)! as! [HomePageBannerModel])
//            self.tableView.reloadData()
//            self.requestStarMaster()
//        }) {
//            self.requestStarMaster()
//        }
        
    }
    func requestStarMaster() {
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getStarMemberList", at: self,isShowHUD:false, success: { (response) in
            let data = response["data"] as? NSArray
            self.masterArr = [StarMasterModel].deserialize(from: data)! as! [StarMasterModel]
            self.tableView.reloadData()
            self.requestCurrentWorkForm()
        }) {
           self.requestCurrentWorkForm()
        }
    }
    func requestCurrentWorkForm(){
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?getWorkOrderByDistance", at: self, success: { (response) in
            if let data = response["data"] as? NSDictionary{
                self.currentFormModel = WorkFormModel.deserialize(from: data)
            }
            self.tableView.reloadData()
            self.tableView.mj_header.endRefreshing()
            
        }) { 
            self.tableView.mj_header.endRefreshing()
        }
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if indexPath.section == 0 {
            let cell = tableView.dequeueReusableCell(withIdentifier: StarMasterShowTableViewCell.description()) as! StarMasterShowTableViewCell
            cell.dataArr = self.masterArr
            return cell
        }else if indexPath.section == 1{
            let cell = tableView.dequeueReusableCell(withIdentifier: CurrentWorkFormTableViewCell.description()) as! CurrentWorkFormTableViewCell
                cell.type = "1"
            if self.currentFormModel == nil || self.currentFormModel.order_id == nil {
                cell.isOrderEmpty = true
            }else{
                cell.isOrderEmpty = false
                cell.model = self.currentFormModel
                cell.successGetOrder = { [unowned self] in
                    self.currentFormModel = nil
                    self.tableView.mj_header.beginRefreshing()
                }
            }
            
            return cell
        }else{
            let cell = tableView.dequeueReusableCell(withIdentifier: InformationTableViewCell.description()) as! InformationTableViewCell
            return cell
        }
        
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch section {
        case 0:
            return 1
        case 1:
            return 1
            
        case 2:
            return 1
        default:
            return 0
        }
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let header = tableView.dequeueReusableHeaderFooterView(withIdentifier: HomePageSectionView.description()) as! HomePageSectionView
        if section == 0 {
            header.title.text = "明星师傅展示"
        }else if section == 1{
            header.title.text = "当前可抢单"
        }else{
            header.title.text = "信息报备"
        }
        return header
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        
        return 50
    }
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 0.1
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        switch indexPath.section {
        case 0:
            return 160
        case 1:
            return 129
        case 2:
            return 91
        default:
            return 0
        }
        
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if indexPath.section == 0 {
            return
        }
        if indexPath.section == 1 {
            
        }
        if indexPath.section == 2 {
            print("信息报备")
            self.navigationController?.pushViewController(InformationReportViewController(), animated: true)
            
        }
    }
    deinit {
        NotificationCenter.default.removeObserver(self)
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
