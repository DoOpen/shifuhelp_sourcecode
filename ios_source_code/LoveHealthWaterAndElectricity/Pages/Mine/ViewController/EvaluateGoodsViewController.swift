//
//  EvaluateGoodsViewController.swift
//  BaiShiXueYiLiving
//
//  Created by sh-lx on 2017/6/23.
//  Copyright © 2017年 liangyi. All rights reserved.
//

import UIKit
import Alamofire
import IQKeyboardManagerSwift


class EvaluateGoodsViewController: UIViewController,UITableViewDelegate,UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    
    var footer: EvaluateTableFooterView!
    var goodsArr: [OrderGoodsBeans] = []
    var order_id: String?
    var submitArr: [SubcommentModel] = []
    var contentArr: [[String:Any]] = [] 
    var evaluateSuccess: (()->())?
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setKeyBoardManagerIsEnable()
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        setKeyBoardManagerDisable()
    }
    override func viewDidLoad() {
        super.viewDidLoad()
       
        self.title = "评价"
        self.tableView.register(UINib(nibName:"EvaluateTableViewCell", bundle:nil), forCellReuseIdentifier: "EvaluateTableViewCell")
        self.tableView.endEditing(true)
        footer = EvaluateTableFooterView.set()
        footer.confirmSuccess = { [unowned self] in
            self.confirm()
        }
        self.tableView.tableFooterView = footer
        // Do any additional setup after loading the view.
    }
    func confirm(){

        if self.submitArr.count == 0{
            ProgressHUD.showNoticeOnStatusBar(message: "麻烦填写下评论啊亲")
            return
        }
        
        for (index,m) in self.submitArr.enumerated() {
            
            if m.assessment_desc?.characters.count == 0 || m.assessment_desc == nil{
                m.assessment_desc = ""
            }
            if index == self.submitArr.count - 1 {
                self.submitForm()
            }
    }
}
    func submitForm(){
        
        for m in self.submitArr{
            self.contentArr.append(m.toJSON()!)
        }
        
        let data = try? JSONSerialization.data(withJSONObject: self.contentArr, options:[])
        let str = String.init(data: data!, encoding: String.Encoding(rawValue: String.Encoding.utf8.rawValue))
        
        NetworkingHandle.fetchNetworkData(url: "orderInterfaces.api?assessmentOrder", at: self, params: ["json":str!], isAuthHide: true, isShowHUD: true, isShowError: true, hasHeaderRefresh: nil, success: { (reponse) in
            ProgressHUD.showSuccess(message: "评论成功")
            self.evaluateSuccess?()
            NotificationCenter.default.post(name: NSNotification.Name.init(rawValue:EvaluateGoodsSuccessNotification), object: nil, userInfo: nil)
            self.navigationController?.popViewController(animated: true)
        }) {

        }
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "EvaluateTableViewCell") as! EvaluateTableViewCell
        cell.tmodel = self.goodsArr[indexPath.section]
        cell.submitModel = { model in
            if self.submitArr.count == 0{
                self.submitArr.append(model)
            } else{
                for (index,m) in self.submitArr.enumerated() {
                    if model.relation_id == m.relation_id{
                        self.submitArr[index] = model
                        return
                    }
                }
                self.submitArr.append(model)
            }
            
        }
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 233/375 * kScreenWidth
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return self.goodsArr.count
    }
    func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
        let view = UIView()
        view.backgroundColor = UIColor(hexString: "#f1f5f6")
        return view
    }
    func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        if section == self.goodsArr.count - 1{
            return 0
        }else{
            return 5
 
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
