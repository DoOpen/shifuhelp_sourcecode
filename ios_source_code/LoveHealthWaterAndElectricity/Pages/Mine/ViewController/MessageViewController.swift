//
//  MessageViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/21.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class MessageViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    var titleArr: [[SetVCModel]]!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "我的信息"
        
        self.titleArr = [[SetVCModel.init(img: #imageLiteral(resourceName: "xtxx"), title: "系统消息")],[SetVCModel.init(img: #imageLiteral(resourceName: "ddxx"), title: "订单消息")]]
        self.tableView.register(UINib.init(nibName: "SettingTableViewCell", bundle: nil), forCellReuseIdentifier: SettingTableViewCell.description())
        // Do any additional setup after loading the view.
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: SettingTableViewCell.description()) as! SettingTableViewCell
        cell.img.image = self.titleArr[indexPath.section][indexPath.row].img
        cell.title.text = self.titleArr[indexPath.section][indexPath.row].title
        return cell
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return self.titleArr.count
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.titleArr[section].count
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 10
    }
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        return 0.1
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if indexPath.section == 0{
            self.navigationController?.pushViewController(SystemMessageViewController(), animated: true)
        }
        if indexPath.section == 1{
            self.navigationController?.pushViewController(OrderMessageViewController(), animated: true)
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
