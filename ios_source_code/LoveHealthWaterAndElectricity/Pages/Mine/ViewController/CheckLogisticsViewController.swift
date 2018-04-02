//
//  CheckLogisticsViewController.swift
//  BaiShiXueYiLiving
//
//  Created by sh-lx on 2017/6/6.
//  Copyright © 2017年 liangyi. All rights reserved.
//

import UIKit
import Alamofire

class CheckLogisticsViewController: UIViewController,UITableViewDelegate,UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    
    
    
    
    var model: OrderModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "查看物流"
       
        
        self.tableView.register(UINib(nibName:"CheckLogisticsTableViewCell",bundle:nil), forCellReuseIdentifier: "CheckLogisticsTableViewCell")
        self.tableView.register(UINib.init(nibName: "CheckLogisticsSectionHeaderView", bundle: nil), forHeaderFooterViewReuseIdentifier: CheckLogisticsSectionHeaderView.description())
        self.tableView.tableFooterView = UIView()
        
        // Do any additional setup after loading the view.
    }
    

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CheckLogisticsTableViewCell") as! CheckLogisticsTableViewCell
        cell.model = self.model.logisticsDetailBeans?[indexPath.row]
        if indexPath.row == 0{
            cell.stateImg.image = #imageLiteral(resourceName: "zt_h")
            cell.content.textColor = UIColor.init(hexString: "242424")
            cell.time.textColor = UIColor.init(hexString: "242424")
        }
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return (self.model.logisticsDetailBeans?.count)!
    }
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let header = tableView.dequeueReusableHeaderFooterView(withIdentifier: CheckLogisticsSectionHeaderView.description()) as! CheckLogisticsSectionHeaderView
        let attributedStr = NSMutableAttributedString(string: "物流状态：" + (self.model.logisticsDetailBeans?.first?.logistics_state)!)
        attributedStr.addAttributes([NSForegroundColorAttributeName:UIColor.init(hexString: "54BB15")], range: NSRange.init(location: 5, length: (self.model.logisticsDetailBeans?.first?.logistics_state?.characters.count)!))
        header.state.attributedText = attributedStr
        header.company.text = "物流公司：" + self.model.logistics_name!
        header.number.text = "订单编号：" + self.model.order_no!
        header.postNumber.text = "运单编号：" + self.model.logistics_no!
        return header
    }
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 113
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
