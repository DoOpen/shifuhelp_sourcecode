//
//  HistoryWorkFormViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/10/23.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class HistoryWorkFormViewController: UIViewController,UITableViewDelegate,UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    var page = 1
    var historyOrderArr = [WorkOrderModel]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.tableView.mj_header = MJRefreshNormalHeader(refreshingBlock: {[unowned self] in
            self.page = 1
            self.requestHistoryWorkFormData()
        })
        self.tableView.mj_footer = MJRefreshAutoNormalFooter(refreshingBlock: {[unowned self] in
            self.page += 1
            self.requestHistoryWorkFormData()
        })
        self.tableView.register(UINib.init(nibName: "CurrentWorkFormTableViewCell", bundle: nil), forCellReuseIdentifier: CurrentWorkFormTableViewCell.description())
        self.tableView.mj_header.beginRefreshing()
        // Do any additional setup after loading the view.
    }
    func requestHistoryWorkFormData(){
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?getOrderListByState", at: self, params: ["type":"worker_accept_complete","page":self.page], isAuthHide: false, isShowHUD: false, isShowError: false, hasHeaderRefresh: self.tableView, success: { (response) in
            let data = response["data"] as! NSArray
            if data.count == 0{
                self.tableView.mj_footer.endRefreshingWithNoMoreData()
            }
            if self.page == 1{
                self.historyOrderArr.removeAll()
            }
            self.historyOrderArr += [WorkOrderModel].deserialize(from: data) as! [WorkOrderModel]
            self.tableView.reloadData()
        }) {
            if self.page > 1{
                self.page -= 1
            }
        }
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: CurrentWorkFormTableViewCell.description()) as! CurrentWorkFormTableViewCell
        cell.amodel = self.historyOrderArr[indexPath.row]
        cell.getFormBtn.isUserInteractionEnabled = false
        cell.type = "3"
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.historyOrderArr.count
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 129
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let model = self.historyOrderArr[indexPath.row]
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?getWorkOrderDetail", at: self, params: ["order_id":model.order_id!], isAuthHide: false, isShowHUD: true, isShowError: true, hasHeaderRefresh: nil, success: { (response) in
            ProgressHUD.hideLoading(toView: self.view)
            let data = response["data"] as! NSDictionary
            let vc = WorkOrderDetailViewController()
            vc.model = OrderDetailModel.deserialize(from: data)!
            self.navigationController?.pushViewController(vc, animated: true)
        }, failure: {
            
        })
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
