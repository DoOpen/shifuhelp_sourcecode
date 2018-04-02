//
//  EarningsRecordDetailViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class EarningsRecordDetailViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    var page = 1
    var isEarned = true
    var ApplyCashsArr : [ApplyCashsModel] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.mj_header = MJRefreshNormalHeader(refreshingBlock: { [unowned self] in
            self.page = 1
            self.requestData()
        })
        self.tableView.mj_footer = MJRefreshAutoNormalFooter(refreshingBlock: { [unowned self] in
            self.page += 1
            self.requestData()
        })
        
        self.tableView.register(UINib.init(nibName: "EarningsRecordDetailEarningTableViewCell", bundle: nil), forCellReuseIdentifier: EarningsRecordDetailEarningTableViewCell.description())
        self.tableView.register(UINib.init(nibName: "EarningsRecordDetailEmployTableViewCell", bundle: nil), forCellReuseIdentifier: EarningsRecordDetailEmployTableViewCell.description())
        self.tableView.mj_header.beginRefreshing()
        // Do any additional setup after loading the view.
    }
    func requestData() {
        
        if isEarned{
            NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getApplyCashs", at: self, hasHeaderRefresh: self.tableView, success: { (response) in
                if self.page == 1{
                    self.ApplyCashsArr.removeAll()
                }
                
                let data = response["data"] as! NSArray
                if data.count == 0{
                    self.tableView.mj_footer.endRefreshingWithNoMoreData()
                }
                self.ApplyCashsArr += [ApplyCashsModel].deserialize(from: data) as! [ApplyCashsModel]
                self.tableView.reloadData()
                
            }, failure: { 
                
            })
        }else{
            
        }
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if isEarned{
            
            let cell = tableView.dequeueReusableCell(withIdentifier: EarningsRecordDetailEmployTableViewCell.description()) as! EarningsRecordDetailEmployTableViewCell
            cell.model = self.ApplyCashsArr[indexPath.row]
            return cell

        }else{
            
            let cell = tableView.dequeueReusableCell(withIdentifier: EarningsRecordDetailEarningTableViewCell.description()) as! EarningsRecordDetailEarningTableViewCell
            return cell
            
        }
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if isEarned{
            return self.ApplyCashsArr.count
        }else{
            return 3
        }
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if isEarned{
            
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
