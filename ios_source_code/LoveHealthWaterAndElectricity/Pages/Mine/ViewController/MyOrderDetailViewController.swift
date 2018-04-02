//
//  MyOrderDetailViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/22.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class MyOrderDetailViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{
    
    @IBOutlet weak var tableView: UITableView!
    var page = 1
    var orderState : String!
    var orderArr : [OrderModel] = []
    override func viewDidLoad() {
        super.viewDidLoad()
        NotificationCenter.default.addObserver(self, selector: #selector(MyOrderDetailViewController.reloadOrderList(noti:)), name: NSNotification.Name(rawValue:EvaluateGoodsSuccessNotification), object: nil)
        self.tableView.register(UINib.init(nibName: "OrderTableHeaderView", bundle: nil), forHeaderFooterViewReuseIdentifier: OrderTableHeaderView.description())
        self.tableView.register(UINib.init(nibName: "OrderTableFooterView", bundle: nil), forHeaderFooterViewReuseIdentifier: OrderTableFooterView.description())
        self.tableView.register(UINib.init(nibName: "OrderTableViewCell", bundle: nil), forCellReuseIdentifier: OrderTableViewCell.description())
        
        self.tableView.mj_header = MJRefreshNormalHeader(refreshingBlock: { [unowned self] in
            self.loadData()
        })
        self.tableView.mj_header.beginRefreshing()
        // Do any additional setup after loading the view.
    }
    @objc func reloadOrderList(noti:NSNotification){
        self.tableView.mj_header.beginRefreshing()
    }
    func loadData() {
        var param = [String:String]()
        if orderState == nil{
            param = ["order_type" : "goods"]
        }else{
            param = ["order_state": orderState, "order_type":"goods"]
        }
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?getOrders", at: self, params: param, hasHeaderRefresh: self.tableView, success: { (response) in
            let data = response["data"] as! NSArray
            self.orderArr.removeAll()
            self.orderArr += [OrderModel].deserialize(from: data) as! [OrderModel]
            self.tableView.reloadData()
        }) { 
            
        }
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: OrderTableViewCell.description()) as! OrderTableViewCell
        cell.model = self.orderArr[indexPath.section].orderGoodsBeans?[indexPath.row]
        return cell
    }
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        
        let header = tableView.dequeueReusableHeaderFooterView(withIdentifier: OrderTableHeaderView.description()) as! OrderTableHeaderView
            header.model = self.orderArr[section]
        
        return header
    }
//    func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
//        let footer = tableView.dequeueReusableHeaderFooterView(withIdentifier: OrderTableFooterView.description()) as! OrderTableFooterView
//        footer.model = self.orderArr[section]
//        footer.operationBlock = { (type,orderID) in
//            if type == "1"{
//                
//                print("确认订单")
//                
//            }else{
//                
//                print("取消订单")
//            }
//            for (index,model) in self.orderArr.enumerated() {
//                if orderID == model.order_id{
//                    self.orderArr.remove(at: index)
//                }
//            }
//            self.tableView.reloadData()
//        }
//        return footer
//    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 60
    }
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 0.1
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        
        return 114
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return self.orderArr[section].orderGoodsBeans!.count
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        
        return self.orderArr.count
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let vc = OrderDetailViewController()
        vc.model = self.orderArr[indexPath.section]
        vc.confirmOrCancelOrder = { [unowned self] in
            self.tableView.mj_header.beginRefreshing()
        }
        self.navigationController?.pushViewController(vc, animated: true)
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
