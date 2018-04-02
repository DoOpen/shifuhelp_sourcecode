//
//  SystemMessageTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/22.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class SystemMessageTableViewCell: UITableViewCell {

    var content : UILabel!
    var time : UILabel!
    var model : SystemMessageModel!{
        willSet(m){
            content.text = m.msg_desc
            time.text = m.create_time
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    override init(style: UITableViewCellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        initViews()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    func initViews(){
        content = UILabel()
        content.textColor = UIColor.init(hexString: "242424")
        content.font = defaultFont(size: 16)
        content.numberOfLines = 0
        self.contentView.addSubview(content)
        
        time = UILabel()
        time.font = defaultFont(size: 14)
        time.textColor = UIColor.init(hexString: "808080")
        self.contentView.addSubview(time)
        
    }
    override func layoutSubviews() {
        super.layoutSubviews()
        layoutUI()
    }
    func layoutUI(){
        self.content.snp.makeConstraints { (make) in
            make.top.equalTo(17)
            make.left.equalTo(17)
            make.right.equalTo(-17)
        }
        self.time.snp.makeConstraints { (make) in
            make.top.equalTo(self.content.snp.bottom).offset(16)
            make.left.equalTo(16)
            make.size.equalTo(CGSize.init(width: 143, height: 11))
        }
    }
    func getCellHeight() -> CGFloat {
        layoutUI()
        self.time.superview?.layoutIfNeeded()
        return content.frame.size.height + content.frame.origin.y + 17 * 2
        
    }
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
