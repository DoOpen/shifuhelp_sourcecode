//
//  MyReportViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/27.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class MyReportViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    
    var reportedArr : [ReportedListModel] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        self.tableView.mj_header = MJRefreshNormalHeader(refreshingBlock: { [unowned self] in
           
            self.requestData()
        })
        
        
        
        self.tableView.register(UINib.init(nibName: "MyReportTableViewCell", bundle: nil), forCellReuseIdentifier: MyReportTableViewCell.description())
        self.tableView.register(UINib.init(nibName: "MyReportHeaderView", bundle: nil), forHeaderFooterViewReuseIdentifier: MyReportHeaderView.description())
        self.tableView.mj_header.beginRefreshing()
        // Do any additional setup after loading the view.
    }
    func requestData() {
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getReportedListByMemberId", at: self,hasHeaderRefresh: self.tableView, success: { (response) in
            self.reportedArr.removeAll()
            self.reportedArr = [ReportedListModel].deserialize(from: response["data"] as? NSArray) as! [ReportedListModel]
            self.tableView.reloadData()
        })
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: MyReportTableViewCell.description()) as! MyReportTableViewCell
        cell.model = self.reportedArr[indexPath.row]
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.reportedArr.count
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 33
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let header = tableView.dequeueReusableHeaderFooterView(withIdentifier: MyReportHeaderView.description()) as! MyReportHeaderView
        header.num.text = "共报备\(self.reportedArr.count)条"
        return header
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        let vc = MyReportDetailViewController()
        vc.model = self.reportedArr[indexPath.row]
        vc.deleteSuccess = { [unowned self] in
            self.tableView.mj_header.beginRefreshing()
        }
        self.navigationController?.pushViewController(vc, animated: true)
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
