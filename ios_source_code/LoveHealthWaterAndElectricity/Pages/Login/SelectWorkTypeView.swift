//
//  SelectWorkTypeView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/11/4.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class SelectWorkTypeView: UIView, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var bgBtn: UIButton!
    @IBOutlet weak var backView: UIView!
    
    @IBOutlet weak var tableView: UITableView!
    
    var workTypeArr = [WorkTypeModel]()
    var selectTypeArr = [String]()
    var selectWorkType : ((String)->())!
    class func showWorkTypeView(atView:UIView, workTypeArr: [WorkTypeModel], selectWorkType: @escaping(String)->()) -> SelectWorkTypeView{
        let view = Bundle.main.loadNibNamed("SelectWorkTypeView", owner: nil, options: nil)?.first as! SelectWorkTypeView
        view.workTypeArr = workTypeArr
        view.selectWorkType = selectWorkType
        view.showView()
        atView.addSubview(view)
        return view
    }
    private func showView(){
        var frame = self.backView.frame
        let old = frame
        self.alpha = 0.1
        frame.origin.y = self.frame.size.height
        self.backView.frame = frame
        UIView.animateKeyframes(withDuration: 0.25, delay: 0.1, options: .calculationModePaced, animations: {
            self.backView.frame = old
            self.bgBtn.alpha = 0.2
            self.alpha = 1
        }, completion: nil)
    }
    func remove(){
        var frame = self.backView.frame
        frame.origin.y += self.frame.size.height
        UIView.animate(withDuration: 0.25, animations: {
            self.backView.frame = frame
            self.backView.alpha = 0.1
            
        }) { (finish) in
            self.removeFromSuperview()
        }
    }

    
    @IBAction func dismissAction(_ sender: UIButton) {
        self.remove()
    }
    
    @IBAction func confirmAction(_ sender: UIButton) {
        if self.selectTypeArr.count == 0 {
            ProgressHUD.showMessage(message: "请选择工种")

        }else{
            self.selectWorkType(self.selectTypeArr.joined(separator: "／"))
            self.remove()
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        self.frame = CGRect(x: 0, y: -64, width: kScreenWidth, height: kScreenHeight)
        self.bgBtn.backgroundColor = UIColor(hexString: "#333333")
        self.bgBtn.alpha = 0.2
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.register(UINib.init(nibName: "SelectWorkTypeTableViewCell", bundle: nil), forCellReuseIdentifier: SelectWorkTypeTableViewCell.description())
        self.tableView.tableFooterView = UIView()
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: SelectWorkTypeTableViewCell.description()) as! SelectWorkTypeTableViewCell
        cell.model = self.workTypeArr[indexPath.row]
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return workTypeArr.count
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let model = self.workTypeArr[indexPath.row]
        if self.selectTypeArr.count == 0 {
            model.isSelected = true
            self.selectTypeArr.append(model.type_name!)
            self.tableView.reloadData()
        }else{
            var num = 0
            for (index,str) in selectTypeArr.enumerated(){
                if str == model.type_name{
                    num += 1
                    model.isSelected = false
                    self.selectTypeArr.remove(at: index)
                    self.tableView.reloadData()
                }
            }
            if num == 0{
                model.isSelected = true
                self.selectTypeArr.append(model.type_name!)
                self.tableView.reloadData()
            }
        }
    }
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */

}
