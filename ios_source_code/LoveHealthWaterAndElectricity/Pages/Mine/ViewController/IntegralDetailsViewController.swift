//
//  IntegralDetailsViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class IntegralDetailsViewController: UIViewController,UITableViewDelegate, UITableViewDataSource{

    var index : Int!
    var rule : UILabel!
    var page = 1
    var integralState : String!
    var integralArr : [IntegralRecordModel] = []
    
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.register(UINib.init(nibName: "EarningsRecordDetailEmployTableViewCell", bundle: nil), forCellReuseIdentifier: EarningsRecordDetailEmployTableViewCell.description())
        
        self.tableView.register(UINib.init(nibName: "EarningsRecordDetailEarningTableViewCell", bundle: nil), forCellReuseIdentifier: EarningsRecordDetailEarningTableViewCell.description())
        
        if index == 2{
            self.tableView.isHidden = true
            self.rule = UILabel()
            self.rule.textColor = UIColor(hexString: "242424")
            self.rule.font = defaultFont(size: 14)
            self.rule.numberOfLines = 0
            self.rule.text = " 1、用户注册：新用户注册账户，并首次登录系统获得20积分\n 2、用户登录：用户每次登录系统获得1积分\n 3、用户邀请：用户邀请朋友注册并登录系统，每邀请一位获得20积分；邀请10位及以上用户可额外获得500积分奖励\n 4、用户完善个人信息：用户完善所有个人信息获得5积分\n（示例，实际已后台编辑为准)"
            self.view.addSubview(self.rule)
            self.rule.snp.makeConstraints({ (make) in
                make.top.left.equalTo(0)
                make.width.equalTo(kScreenWidth)
            })
        }else{
            self.tableView.isHidden = false
            
        }
        self.tableView.mj_header = MJRefreshNormalHeader(refreshingBlock: { [unowned self] in
            self.page = 1
            self.requestData()
        })
        self.tableView.mj_footer = MJRefreshAutoNormalFooter(refreshingBlock: { [unowned self] in
            self.page += 1
            self.requestData()
        })
        self.tableView.mj_header.beginRefreshing()
        // Do any additional setup after loading the view.
    }
    func requestData() {
        
        if index == 0{
            integralState = "add"
        }else{
            integralState = "use"
        }
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getIntegralGetRecord", at: self, params: ["state":integralState], hasHeaderRefresh:self.tableView,  success: { (response) in
            
            if self.page == 1{
                self.integralArr.removeAll()
            }
            let data = response["data"] as! NSArray
            if data.count == 0{
                self.tableView.mj_footer.endRefreshingWithNoMoreData()
            }
            self.integralArr += [IntegralRecordModel].deserialize(from: data) as! [IntegralRecordModel]
            self.tableView.reloadData()
        }) { 
            
        }
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if index == 0{
            let cell = tableView.dequeueReusableCell(withIdentifier: EarningsRecordDetailEmployTableViewCell.description()) as! EarningsRecordDetailEmployTableViewCell
            cell.imodel = self.integralArr[indexPath.row]
            return cell
        }else  {
            let cell = tableView.dequeueReusableCell(withIdentifier: EarningsRecordDetailEarningTableViewCell.description()) as! EarningsRecordDetailEarningTableViewCell
            cell.model = self.integralArr[indexPath.row]
            return cell
        }
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.integralArr.count
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if index == 0{
            return 89
        }else{
            return 115
        }
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
