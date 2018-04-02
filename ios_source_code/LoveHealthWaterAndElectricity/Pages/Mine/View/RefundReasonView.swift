//
//  RefundReasonView.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/25.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class RefundReasonView: UIView, UITableViewDelegate, UITableViewDataSource{

    @IBOutlet weak var tableView: UITableView!
    
    @IBOutlet weak var bgBtn: UIButton!
    
    @IBOutlet weak var backView: UIView!
    var model : RefundReasonModel!
    var selectModel: ((RefundReasonModel)->())?
    var reasonData: [RefundReasonModel] = []
    class func showReasonView(atView:UIView, reasonArr: [RefundReasonModel]) -> RefundReasonView{
        let view = Bundle.main.loadNibNamed("RefundReasonView", owner: nil, options: nil)?.first as! RefundReasonView
        view.reasonData = reasonArr
        atView.addSubview(view)
        return view
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        self.frame = CGRect(x: 0, y: -64, width: kScreenWidth, height: kScreenHeight)
        self.bgBtn.backgroundColor = UIColor(hexString: "#333333")
        self.bgBtn.alpha = 0.2
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.register(UINib.init(nibName: "RefundReasonViewTableViewCell", bundle: nil), forCellReuseIdentifier: RefundReasonViewTableViewCell.description())
        
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

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: RefundReasonViewTableViewCell.description()) as! RefundReasonViewTableViewCell
        cell.title.text = self.reasonData[indexPath.row].reason_name
        return cell
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return reasonData.count
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        self.model = self.reasonData[indexPath.row]
    }
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    
    @IBAction func confirmAction(_ sender: UIButton) {
        self.selectModel?(self.model)
        self.remove()
    }
    
    @IBAction func dismissAction(_ sender: UIButton) {
        self.remove()
    }

}
