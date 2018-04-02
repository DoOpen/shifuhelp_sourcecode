//
//  WorkOrderViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/16.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class WorkOrderViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    
    @IBOutlet weak var tableView: UITableView!
    
    var segmented : UISegmentedControl!
    var titleArr : [String]! = ["待接单","已接单","已退单"]
    var page = 1
    var vc = AcceptedViewController()
    var orderType = "1"
    var orderArr = [WorkOrderModel]()
    var isFirstLoad = true
    var isLoading = false
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.getAllOrderStateNum(num: 0)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        
        NotificationCenter.default.addObserver(self, selector: #selector(WorkOrderViewController.pushToDetailVC(noti:)), name: NSNotification.Name(rawValue:PushToWorderDetailVC), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(WorkOrderViewController.paySuccess), name: NSNotification.Name(rawValue: PaySuccessNotification), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(WorkOrderViewController.payFail), name: NSNotification.Name(rawValue: PayFailNotification), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(WorkOrderViewController.reloadState(noti:)), name: NSNotification.Name(rawValue:ReloadAcceptWorkOrderNotification), object: nil)
        vc.type = 0
        self.view.insertSubview(vc.view, belowSubview: self.tableView)
        
        
        self.tableView.register(UINib.init(nibName: "CurrentWorkFormTableViewCell", bundle: nil), forCellReuseIdentifier: CurrentWorkFormTableViewCell.description())
        self.tableView.mj_header = MJRefreshNormalHeader(refreshingBlock: { [unowned self] in
            self.page = 1
            self.requestData(page: self.page)
           
        })
        self.tableView.mj_footer = MJRefreshAutoNormalFooter(refreshingBlock: { [unowned self] in
            self.page += 1
            self.requestData(page: self.page)
            
        })
        self.tableView.mj_header.beginRefreshing()
        // Do any additional setup after loading the view.
    }
    func reloadState(noti:NSNotification) {
        self.getAllOrderStateNum(num: 1)
    }
    func paySuccess() {
        self.getUserInfo()
        
    }
    func payFail(){
        ProgressHUD.showMessage(message: "支付失败")
    }
    func getUserInfo() {
        
        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getMemberDetail", at: self,isAuthHide: true, isShowHUD: true, success: { (response) in
            let data = response["data"] as! NSDictionary
            LHWEUserInfoHandler.saveUserInfo(model: LHWEUserInfoModel.deserialize(from: data)!)
            ProgressHUD.showMessage(message: "支付成功")
        })
    }
    func setTitleView(index:Int){
        self.segmented = UISegmentedControl.init(items: self.titleArr)
        self.segmented.frame = CGRect.init(x: 0, y: 0, width: 300, height: 30)
        self.segmented.tintColor = UIColor.white
        self.segmented.setTitleTextAttributes([NSForegroundColorAttributeName:UIColor.white, NSFontAttributeName:UIFont.systemFont(ofSize: 14)], for: .normal)
        self.segmented.setTitleTextAttributes([NSForegroundColorAttributeName:UIColor.init(hexString: "EBB100")], for: .selected)
        
            self.segmented.selectedSegmentIndex = index
        
        self.segmented.addTarget(self, action: #selector(WorkOrderViewController.segmentedSelectedIndex(control:)), for: .valueChanged)
        self.navigationItem.titleView = self.segmented
    }
    func pushToDetailVC(noti:NSNotification){
        if self.isLoading {
            return
        }
        self.isLoading = true
        
        let model = noti.userInfo?["model"] as! WorkOrderModel
        if model.order_state == "13" || model.order_state == "14" {
            NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?getRefundWorkOrderDetail", at: self, params: ["order_id": model.order_id!], isAuthHide: false, isShowHUD: false, isShowError: false, hasHeaderRefresh: nil, success: { (response) in
                self.isLoading = false
                let data = response["data"] as! NSDictionary
                let vc = WorkOrderDetailViewController()
                vc.cancelOrderOrNot = { [unowned self] in
                    self.tableView.mj_header.beginRefreshing()
                    self.getAllOrderStateNum(num: 1)
                    NotificationCenter.default.post(name: NSNotification.Name(rawValue:ChangeWorkerOrderStateNotification), object: nil, userInfo: ["reloadState":1])
                }
                vc.model = OrderDetailModel.deserialize(from: data)!
                self.navigationController?.pushViewController(vc, animated: true)
            }, failure: {
                self.isLoading = false
            })
            
        }else{
            NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?getWorkOrderDetail", at: self, params: ["order_id":model.order_id!], isAuthHide: false, isShowHUD: false, isShowError: false, hasHeaderRefresh: nil, success: { (response) in
                self.isLoading = false
                let data = response["data"] as! NSDictionary
                let vc = WorkOrderDetailViewController()
                vc.cancelOrderOrNot = { [unowned self] in
                    self.tableView.mj_header.beginRefreshing()
                    self.getAllOrderStateNum(num: 1)
                    NotificationCenter.default.post(name: NSNotification.Name(rawValue:ChangeWorkerOrderStateNotification), object: nil, userInfo: ["reloadState":1])
                }
                vc.model = OrderDetailModel.deserialize(from: data)!
                self.navigationController?.pushViewController(vc, animated: true)
            }, failure: {
                self.isLoading = false
                ProgressHUD.showMessage(message: "请重新操作")
            })

        }
        
     }
    func getAllOrderStateNum(num: Int){
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?getWorkOrderStateCount", at: self, params: ["member_type":"1"], isAuthHide: false, isShowHUD: false, isShowError: true, hasHeaderRefresh: nil, success: { (response) in
            let data = response["data"] as! Dictionary<String,Int>
            self.titleArr = ["待接单(" + "\(data["worker_wait_accept"]!)" + ")","已接单(" + "\(data["worker_accept"]!)" + ")","已退单(" + "\(data["worker_cancle"]!)" + ")"]
            self.setTitleView(index: num)
        }) {
            
        }
    }
    func requestData(page: Int) {
        var params : Dictionary<String,Any>!
        if self.orderType == "1"{
            params = ["type":"worker_wait_accept","page":self.page]
        }else{
            params = ["type":"worker_cancle", "page":self.page]
        }
        
        NetworkingHandle.fetchNetworkData(url: "workOrderInterfaces.api?getOrderListByState", at: self, params: params, hasHeaderRefresh: self.tableView, success: { (response) in
            
            let data = response["data"] as! NSArray
            if data.count == 0{
                self.tableView.mj_footer.endRefreshingWithNoMoreData()
            }
            
            if self.page == 1{
                self.orderArr.removeAll()
            }
            self.orderArr += [WorkOrderModel].deserialize(from: data) as! [WorkOrderModel]
            self.tableView.reloadData()
            
            
        }) {
            if self.page > 1{
                self.page -= 1
            }
            
        }
        
    }
    //MARK: -tableviewDelegate
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: CurrentWorkFormTableViewCell.description()) as! CurrentWorkFormTableViewCell
        cell.amodel = self.orderArr[indexPath.row]
        if self.orderType == "1"{
            cell.type = "2"
        }else if self.orderType == "2"{
             cell.type = "3"
        }
        cell.successGetOrder = { [unowned self] in
            self.getAllOrderStateNum(num: 0)
            NotificationCenter.default.post(name: NSNotification.Name(rawValue:ReloadAcceptWorkOrderNotification), object: nil)
            self.tableView.mj_header.beginRefreshing()
        }
        
        return cell
        
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return self.orderArr.count
    }
    
    func segmentedSelectedIndex(control: UISegmentedControl) {
        
        print(control.selectedSegmentIndex)
        if control.selectedSegmentIndex == 1{
            self.tableView.isHidden = true
            self.view.bringSubview(toFront: vc.view)
        }else{
            
            if control.selectedSegmentIndex == 0 {
                
                self.orderType = "1"
                
            }else{
                
                self.orderType = "2"
            }
            self.tableView.mj_header.beginRefreshing()
            self.tableView.isHidden = false
            self.view.bringSubview(toFront: self.tableView)
        }
        
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
