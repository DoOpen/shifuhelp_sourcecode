//
//  AfterSaleViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/25.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh


class AfterSaleViewController: UIViewController,UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
   
    var refundGoodsArr : [RefundGoodsModel]! = []
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "售后"
        
        
        NotificationCenter.default.addObserver(self, selector: #selector(AfterSaleViewController.reloadList(noti:)), name: NSNotification.Name(rawValue:ReloadRefundGoodsListNotification), object: nil)
        
        
        self.tableView.mj_header = MJRefreshNormalHeader(refreshingBlock: { [unowned self] in
            self.requestData()
        })
        
        self.tableView.register(UINib.init(nibName: "OrderTableViewCell", bundle: nil), forCellReuseIdentifier: OrderTableViewCell.description())
        self.tableView.register(UINib.init(nibName: "OrderTableHeaderView", bundle: nil), forHeaderFooterViewReuseIdentifier: OrderTableHeaderView.description())
        self.tableView.register(UINib.init(nibName: "OrderTableFooterView", bundle: nil), forHeaderFooterViewReuseIdentifier: OrderTableFooterView.description())
        self.tableView.mj_header.beginRefreshing()
        // Do any additional setup after loading the view.
    }
    func reloadList(noti:NSNotification){
        self.tableView.mj_header.beginRefreshing()
    }
    func requestData() {
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?getMemberRefunds", at: self, hasHeaderRefresh: self.tableView, success: { (response) in
            
            self.refundGoodsArr.removeAll()
            self.refundGoodsArr = [RefundGoodsModel].deserialize(from: response["data"] as? NSArray) as! [RefundGoodsModel]
            self.tableView.reloadData()
            
        }) { 
            
        }
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: OrderTableViewCell.description()) as! OrderTableViewCell
        cell.rgModel = self.refundGoodsArr[indexPath.section]
        return cell
    }
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let header = tableView.dequeueReusableHeaderFooterView(withIdentifier: OrderTableHeaderView.description()) as! OrderTableHeaderView
        header.rgModel = self.refundGoodsArr[section]
        return header
    }
    func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
        let footer = tableView.dequeueReusableHeaderFooterView(withIdentifier: OrderTableFooterView.description()) as! OrderTableFooterView
        footer.rgModel = self.refundGoodsArr[section]
        footer.isRefundGoods = true
        return footer
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 60
    }
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 85
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        
        return self.refundGoodsArr.count
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return 1
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
