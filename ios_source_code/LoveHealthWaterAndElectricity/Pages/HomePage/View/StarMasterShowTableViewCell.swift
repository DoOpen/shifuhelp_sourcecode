//
//  StarMasterShowTableViewCell.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/18.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit

class StarMasterShowTableViewCell: UITableViewCell,UICollectionViewDelegate,UICollectionViewDataSource {

    @IBOutlet weak var collectionView: UICollectionView!
    
    @IBOutlet weak var layout: UICollectionViewFlowLayout!
    
    var dataArr : [StarMasterModel]!{
        willSet(m){
            self.collectionView.reloadData()
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        
        self.layout.minimumLineSpacing = 15
        self.layout.minimumInteritemSpacing = 1
        self.layout.scrollDirection = .horizontal
        
        let w = (kScreenWidth - 60)/3
        self.layout.itemSize = CGSize(width: w, height: w * 160/110)
        self.layout.headerReferenceSize = CGSize(width: 0, height: 0)
        self.layout.sectionInset = UIEdgeInsetsMake(0, 15, 0, 15)
        
        
        collectionView.showsVerticalScrollIndicator = false
        collectionView.showsHorizontalScrollIndicator = false
        collectionView.delegate = self
        collectionView.dataSource = self
        
        collectionView.register(UINib.init(nibName: "StarMasterShowCollectionViewCell", bundle: nil), forCellWithReuseIdentifier: StarMasterShowCollectionViewCell.description())
        
        
        
    }
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.dataArr.count
    }
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: StarMasterShowCollectionViewCell.description(), for: indexPath) as! StarMasterShowCollectionViewCell
        cell.model = self.dataArr[indexPath.row]
        return cell
    }
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let vc = CommanWebViewController()
        vc.url = NetworkingHandle.mainHost + self.dataArr[indexPath.row].star_worker_info!
        vc.title = self.dataArr[indexPath.row].member_real_name
        self.responderViewController()?.navigationController?.pushViewController(vc, animated: true)
    }
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
