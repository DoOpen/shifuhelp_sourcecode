//
//  ShowLocationViewController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by 王志刚 on 2017/9/29.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
import MapKit


class ShowLocationViewController: UIViewController {

    var mainMapView : MKMapView!
    var log: String!
    var lat: String!
    var city : String!
    var dis : String!
    var detailAddress : String!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "具体位置"
        
        self.mainMapView = MKMapView.init(frame: self.view.frame)
        self.view.addSubview(self.mainMapView)
        
        self.mainMapView.mapType = .standard
        //创建一个MKCoordinateSpan对象，设置地图的范围（越小越精确）
        let latDelta = 0.05
        let longDelta = 0.05
        let currentLocationSpan:MKCoordinateSpan = MKCoordinateSpanMake(latDelta, longDelta)
        //定义地图区域和中心坐标（
        //使用当前位置
        //var center:CLLocation = locationManager.location.coordinate
        //使用自定义位置
        let center:CLLocation = CLLocation(latitude: Double(lat)!, longitude: Double(log)!)
        
        let currentRegion:MKCoordinateRegion = MKCoordinateRegion(center: center.coordinate,
                                                                  span: currentLocationSpan)
        
        //设置显示区域
        self.mainMapView.setRegion(currentRegion, animated: true)
        //创建一个大头针对象
        let objectAnnotation = MKPointAnnotation()
        //设置大头针的显示位置
        objectAnnotation.coordinate = CLLocation(latitude: Double(lat)!,
                                                 longitude: Double(log)!).coordinate
        //设置点击大头针之后显示的标题
        objectAnnotation.title = city
        //设置点击大头针之后显示的描述
        objectAnnotation.subtitle = dis + detailAddress
        //添加大头针
        self.mainMapView.addAnnotation(objectAnnotation)
        // Do any additional setup after loading the view.
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
