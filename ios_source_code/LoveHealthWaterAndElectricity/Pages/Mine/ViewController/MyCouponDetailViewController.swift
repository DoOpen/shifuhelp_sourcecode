//
//  MyCouponDetailViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class MyCouponDetailViewController: UIViewController,UITableViewDelegate, UITableViewDataSource{

    var index : Int!
    var page = 1
    var type: String!
    var couponsArr : [CouponsModel] = []
    @IBOutlet weak var tableView: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.tableView.register(UINib.init(nibName: "CouponTableViewCell", bundle: nil), forCellReuseIdentifier: CouponTableViewCell.description())
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
        
        if index == 0 {
            type = "not_used"
        }else if index == 1{
            type = "already_used"
        }else{
            type = "expired"
        }
        NetworkingHandle.fetchNetworkData(url: "couponInterfaces.api?getCoupons", at: self, params: ["coupon_state":type,"page":self.page],hasHeaderRefresh: self.tableView, success: { (response) in
            if self.page == 1{
                self.couponsArr.removeAll()
            }
            let data = response["data"] as! NSArray
            if data.count == 0{
                self.tableView.mj_footer.endRefreshingWithNoMoreData()
            }
            self.couponsArr += [CouponsModel].deserialize(from: data) as! [CouponsModel]
            self.tableView.reloadData()
        }) { 
            
        }
        
        
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: CouponTableViewCell.description()) as! CouponTableViewCell
        cell.model = self.couponsArr[indexPath.row]
        if index == 0 {
            cell.stateImg.isHidden = true
        }else if index == 2{
            cell.stateImg.image = #imageLiteral(resourceName: "yiguoqi")
        }
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return self.couponsArr.count
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
