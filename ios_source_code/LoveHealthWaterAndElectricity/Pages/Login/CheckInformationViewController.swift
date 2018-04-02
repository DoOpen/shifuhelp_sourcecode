//
//  CheckInformationViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/28.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class CheckInformationViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    var header = CheckInformationHeaderView()
    var titleArr = [[String]]()
    var detailArr = [[String]]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "完善个人资料"
        
        let m = LHWEUserInfoHandler.getUserInfo()
        self.titleArr = [["姓名","性别","年龄"],["工种","工龄"],["信息有误？"]]
        self.detailArr = [[m!.member_real_name!,m!.member_sex!,m!.member_age!],[m!.member_work_type!,m!
            .member_work_age! + "年"],[""]]
        self.tableView.register(UINib.init(nibName: "CheckInformationHeaderView", bundle: nil), forHeaderFooterViewReuseIdentifier: CheckInformationHeaderView.description())
        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
        // Do any additional setup after loading the view.
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell.init(style: .value1, reuseIdentifier: "cell")
        cell.textLabel?.text = self.titleArr[indexPath.section][indexPath.row]
        cell.textLabel?.font = defaultFont(size: 16)
        cell.textLabel?.textColor = UIColor.init(hexString: "#808080")
        cell.detailTextLabel?.text = self.detailArr[indexPath.section][indexPath.row]
        cell.detailTextLabel?.textColor = UIColor.init(hexString: "#242424")
        cell.detailTextLabel?.font = defaultFont(size: 16)
        cell.selectionStyle = .none
        if indexPath.section == 2{
            cell.contentView.addSubview(cellBtn)
        }
        return cell
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return self.titleArr.count
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.titleArr[section].count
    }
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        if section == 0{
            header = tableView.dequeueReusableHeaderFooterView(withIdentifier: CheckInformationHeaderView.description()) as! CheckInformationHeaderView
            return header
        }
        return UIView()
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        if section == 0{
            return 138
        }
        return 10
    }
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 0.1
    }
    fileprivate lazy var cellBtn:UIButton = {
        let btn = UIButton.init(type: .custom)
        btn.setTitle("联系客服", for: .normal)
        btn.frame = CGRect(x: kScreenWidth - 74 - 16, y: 12, width: 74, height: 33)
        btn.layer.cornerRadius = 16
        btn.layer.borderColor = UIColor.init(hexString: "#AEAEAE").cgColor
        btn.layer.borderWidth = 1
        btn.layer.masksToBounds = true
        btn.setTitleColor(UIColor.init(hexString: "242424"), for: .normal)
        btn.titleLabel?.font = defaultFont(size: 14)
        btn.addTarget(self, action: #selector(CheckInformationViewController.connectService(sender:)), for: .touchUpInside)
        return btn
    }()
    func payDeposit(type: String) {
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?payDeposit", at: self, params: ["channel":type], isAuthHide: false, isShowHUD: true, isShowError: true, hasHeaderRefresh: nil, success: { (response) in
            ProgressHUD.hideLoading(toView: self.view)
            let data = response["data"] as! String
            
            let payDic = getDictionaryFromJSONString(jsonString: data)
            
            if type == "wx"{
                Pingpp.createPayment(payDic, appURLScheme: WXAppID, withCompletion: { (result, error) in
                    if error != nil{
                        print(error?.description ?? "")
                    }else{
                        
                    }
                })
            }else{
                Pingpp.createPayment(payDic, appURLScheme: SonAppID, withCompletion: { (result, error) in
                    if error != nil{
                        print(error?.description ?? "")

                    }else{
                    }

                })
            }
        }) {
            
        }
    }
    
    func connectService(sender:UIButton){
        callCustomerServices(atView: self.view, phoneNum: CustomerServicesPhone)
    }
    override func viewWillAppear(_ animated: Bool) {
        setStatusBarBGColor(color: themeColor)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
    }
    override func viewWillDisappear(_ animated: Bool) {
        self.navigationController?.setNavigationBarHidden(true, animated: true)
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
