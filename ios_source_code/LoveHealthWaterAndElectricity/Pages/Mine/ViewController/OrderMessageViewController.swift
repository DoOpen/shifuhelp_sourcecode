//
//  OrderMessageViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/22.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class OrderMessageViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    var orderMessageArr : [SystemMessageModel] = []
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "订单消息"
        self.tableView.mj_header = MJRefreshNormalHeader(refreshingBlock: { [unowned self] in
            self.requestOrderMessage()
        })
        self.tableView.register(UINib.init(nibName: "OrderMessageTableViewCell", bundle: nil), forCellReuseIdentifier: OrderMessageTableViewCell.description())
        self.tableView.mj_header.beginRefreshing()
        // Do any additional setup after loading the view.
    }
    func requestOrderMessage(){
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getMemberMsgs", at: self, params: ["msg_type":"order"], isAuthHide: false, isShowHUD: false, isShowError: true, hasHeaderRefresh: self.tableView, success: { (response) in
            self.orderMessageArr = [SystemMessageModel].deserialize(from: response["data"] as? NSArray) as! [SystemMessageModel]
            self.tableView.reloadData()
        }) { 
            
        }
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: OrderMessageTableViewCell.description()) as! OrderMessageTableViewCell
        cell.model = self.orderMessageArr[indexPath.row]
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.orderMessageArr.count
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
