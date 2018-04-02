//
//  MyCollectionViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/26.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MJRefresh

class MyCollectionViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    var canEdit = false
    var rightTitle = "编辑"
    var page = 1
    var collectionArr : [CollectionGoodsModel] = []
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "我的收藏"
        
        self.navigationItem.rightBarButtonItem = UIBarButtonItem.init(title: rightTitle, target: self, action: #selector(MyCollectionViewController.editCell))
        self.tableView.register(UINib.init(nibName: "OrderTableViewCell", bundle: nil), forCellReuseIdentifier: OrderTableViewCell.description())
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
    func requestData(){
        NetworkingHandle.fetchNetworkData(url: "collectionInterfaces.api?getCollection", at: self, params: ["page" :self.page], success : { (response) in
            self.tableView.mj_header.endRefreshing()
            if self.page == 1{
                self.collectionArr.removeAll()
            }
            let data = response["data"] as! NSArray
            if data.count == 0{
                self.tableView.mj_footer.endRefreshingWithNoMoreData()
            }
            self.collectionArr += [CollectionGoodsModel].deserialize(from: data) as! [CollectionGoodsModel]
            self.tableView.reloadData()
            
        })
    }
    func editCell() {
        self.tableView.isEditing = canEdit
        canEdit = !canEdit
        rightTitle = canEdit ? "完成" : "编辑"
        if canEdit{
            if self.collectionArr.count > 0{
                ProgressHUD.showMessage(message: "左侧滑动删除选项")
            }
        }
        self.navigationItem.rightBarButtonItem = UIBarButtonItem.init(title: rightTitle, target: self, action: #selector(MyCollectionViewController.editCell))
        self.tableView.reloadData()
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: OrderTableViewCell.description()) as! OrderTableViewCell
        cell.cmodel = self.collectionArr[indexPath.row]
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.collectionArr.count
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 10
    }
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 0.1
    }
    func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return canEdit
    }
    func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        return canEdit
    }
    
    func tableView(_ tableView: UITableView, titleForDeleteConfirmationButtonForRowAt indexPath: IndexPath) -> String? {
        return "删除"
    }
    func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCellEditingStyle {
        return .delete
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print(".....................")
    }
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        NetworkingHandle.fetchNetworkData(url: "collectionInterfaces.api?cancelCollection", at: self, params: ["relation_id":self.collectionArr[indexPath.row].goodsBean!.goods_id!,"collection_type":"goods"], success: { (response) in
            self.collectionArr.remove(at: indexPath.row)
            self.tableView.reloadData()
        }) { 
            ProgressHUD.showMessage(message: "操作失败")
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
