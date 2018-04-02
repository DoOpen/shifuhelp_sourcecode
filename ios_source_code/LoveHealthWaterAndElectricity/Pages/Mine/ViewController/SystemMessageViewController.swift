//
//  SystemMessageViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/22.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class SystemMessageViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    var tempCell: SystemMessageTableViewCell!
    var systemMsgArr : [SystemMessageModel] = []
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "系统消息"
        self.tableView.tableFooterView = UIView()
        
        self.tableView.mj_header = MJRefreshNormalHeader(refreshingBlock: { [unowned self] in
            self.requestSystemMessage()
        })
        
        tempCell = SystemMessageTableViewCell(style: .default, reuseIdentifier: SystemMessageTableViewCell.description())
        
        self.tableView.register(SystemMessageTableViewCell.self, forCellReuseIdentifier: SystemMessageTableViewCell.description())
        self.tableView.mj_header.beginRefreshing()
        // Do any additional setup after loading the view.
    }
    func requestSystemMessage() {

        NetworkingHandle.fetchNetworkData(url: "memberInterfaces.api?getMemberMsgs", at: self, params: ["msg_type":"system"], isAuthHide: false, isShowHUD: false, isShowError: false, hasHeaderRefresh: self.tableView, success: { (response) in
            self.systemMsgArr.removeAll()
            self.systemMsgArr = [SystemMessageModel].deserialize(from: response["data"] as? NSArray) as! [SystemMessageModel]
            self.tableView.reloadData()
        }) {
            
        }
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: SystemMessageTableViewCell.description()) as! SystemMessageTableViewCell
        cell.model = self.systemMsgArr[indexPath.row]
        cell.selectionStyle = .none
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.systemMsgArr.count
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        let model = self.systemMsgArr[indexPath.row]
        if model.cellHeight == nil {
            tempCell.model = model
            model.cellHeight = tempCell.getCellHeight()
        }
        return model.cellHeight!
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
