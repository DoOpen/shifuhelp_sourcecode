//
//  AcceptedDetailViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/20.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class AcceptedDetailViewController: UIViewController,UITableViewDelegate,UITableViewDataSource {

    var serviceType = 0
    var page = 1
    var orderArr = [WorkOrderModel]()
    
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        
         NotificationCenter.default.addObserver(self, selector: #selector(AcceptedDetailViewController.reloadWorkOrderState(noti:)), name: NSNotification.Name(rawValue:ChangeWorkerOrderStateNotification), object: nil)
        
        self.tableView.register(UINib.init(nibName: "CurrentWorkFormTableViewCell", bundle: nil), forCellReuseIdentifier: CurrentWorkFormTableViewCell.description())
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
    func reloadWorkOrderState(noti:NSNotification) {
        //self.serviceType = noti.userInfo?["reloadState"] as! Int
        self.tableView.mj_header.beginRefreshing()
    }
    func requestData(){
        
        var params : Dictionary<String, Any>!
        var type = ""
        switch serviceType {
        case 0:
            type = "worker_accept_not_service"
        case 1:
            type = "worker_accept_service"
        case 2:
            type = "worker_accept_wait_audit"
        case 3:
            type = "worker_accept_complete"
        default:
            return
        }
        params = ["type": type, "page":self.page]
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?getOrderListByState", at: self, params: params, hasHeaderRefresh: self.tableView, success: { (response) in
            if self.page == 1{
                
                self.orderArr.removeAll()
            }
            let data = response["data"] as? NSArray
            
            if data?.count == 0{
                
                self.tableView.mj_footer.endRefreshingWithNoMoreData()
            }
            self.orderArr += [WorkOrderModel].deserialize(from: data) as! [WorkOrderModel]
            self.tableView.reloadData()
        }) {
            if self.page > 1{
                self.page -= 1
            }
        }
        
    }

    //MARK: delegate
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: CurrentWorkFormTableViewCell.description()) as! CurrentWorkFormTableViewCell
        cell.amodel = self.orderArr[indexPath.row]
        cell.type = "3"
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return self.orderArr.count
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        
        if self.serviceType == 0{
            let m = self.orderArr[indexPath.row]
            if m.is_today_order == "1" || m.order_is_cancle == "1"{
                
                return 162
            }
            return 129
        }
        return 129
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    deinit {
        NotificationCenter.default.removeObserver(self)
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
