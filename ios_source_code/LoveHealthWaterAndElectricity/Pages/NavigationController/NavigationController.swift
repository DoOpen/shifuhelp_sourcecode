//
//  NavigationController.swift
//  LoveHealthWaterAndElectricity
//
//  Created by wzg on 2017/9/16.
//  Copyright © 2017年 tts. All rights reserved.
//

import UIKit
class LoginNavigationController: UINavigationController {
    override func viewDidLoad() {
        super.viewDidLoad()
//        self.navigationBar.setBackgroundImage(UIImage(), for: .default)
//        self.navigationBar.shadowImage = UIImage()
//        self.navigationBar.tintColor = themeColor
//        self.navigationBar.isTranslucent = false
//        self.navigationBar.titleTextAttributes = [NSForegroundColorAttributeName: themeColor, NSFontAttributeName: UIFont.systemFont(ofSize: 19)]
        self.navigationBar.titleTextAttributes = [NSForegroundColorAttributeName: UIColor.white, NSFontAttributeName: defaultFont(size: 19)]
        self.navigationBar.tintColor = UIColor.white
        self.navigationBar.barTintColor = themeColor
        self.navigationBar.isTranslucent = false
        
    }
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    class func setup() -> LoginNavigationController {
        let n = LoginNavigationController(rootViewController: LoginViewController())
        return n
    }
    
    override func pushViewController(_ viewController: UIViewController, animated: Bool) {
        if self.childViewControllers.count > 0 {
            viewController.hidesBottomBarWhenPushed = true
        }
        let backItem = UIBarButtonItem()
        backItem.title = ""
        viewController.navigationItem.backBarButtonItem = backItem
        super.pushViewController(viewController, animated: animated)
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
func defaultFont(size: CGFloat) -> UIFont {
    guard let font = UIFont(name: "PingFang SC", size: size) else {
        return UIFont.systemFont(ofSize: size)
    }
    return font
}
class NavigationController: UINavigationController {

    override var preferredStatusBarStyle: UIStatusBarStyle{
        return .lightContent
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationBar.titleTextAttributes = [NSForegroundColorAttributeName: UIColor.white, NSFontAttributeName: defaultFont(size: 19)]
        self.navigationBar.tintColor = UIColor.white
        self.navigationBar.barTintColor = themeColor
        self.navigationBar.isTranslucent = false
        setStatusBarBGColor(color: themeColor)
        // Do any additional setup after loading the view.
    }
    
    override func pushViewController(_ viewController: UIViewController, animated: Bool) {
        if self.childViewControllers.count > 0 {
            viewController.hidesBottomBarWhenPushed = true
            
        }
        let backItem = UIBarButtonItem()
        backItem.title = ""
        viewController.navigationItem.backBarButtonItem = backItem
        super.pushViewController(viewController, animated: animated)
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
class BaseTabBarController: UITabBarController, UITabBarControllerDelegate {
    private var adImageView: UIImageView?
    var lastViewController = UIViewController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupAllViewController()
        
        setupAllTabBarButton()
        
        
        
        self.delegate = self
        lastViewController = self.childViewControllers.first!
        
        self.tabBar.barTintColor = UIColor.white
        self.tabBar.isTranslucent = false
        
        //        self.tabBar.shadowImage = UIImage()
        //        self.tabBar.backgroundImage = #imageLiteral(resourceName: "tabbarbg")
        
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
}
extension BaseTabBarController {
    func addAllChildViewController() {
        
    }
    
    func setupAllViewController() {
        //首页
        let homeVC = HomePageViewController()
        let homeNav = NavigationController(rootViewController: homeVC)
        self.addChildViewController(homeNav)
        
        //我的工单
        let workerOrderVC = WorkOrderViewController()
        let workerOrderNav = NavigationController(rootViewController: workerOrderVC)
        self.addChildViewController(workerOrderNav)
        
        //商城
        let shoppingMallVC = ShoppingMallViewController()
//        let shoppingMallVC = TestViewController()
        let shoppingMallNav = NavigationController(rootViewController: shoppingMallVC)
        self.addChildViewController(shoppingMallNav)
        
        //我的中心
        let mineVC = MineViewController()
        let mineNav = NavigationController(rootViewController: mineVC)
        self.addChildViewController(mineNav)
    }
    func setupAllTabBarButton() {
        //设置TabBar按钮的内容
        let homeVC = self.childViewControllers[0]
        homeVC.tabBarItem.image = UIImage(named: "shouye")
        homeVC.tabBarItem.selectedImage = UIImage(named: "shouye_h")?.withRenderingMode(.alwaysOriginal)
        homeVC.tabBarItem.title = "首页"
        homeVC.tabBarItem.setTitleTextAttributes([NSForegroundColorAttributeName : UIColor.lightGray], for: .normal)
        homeVC.tabBarItem.setTitleTextAttributes([NSForegroundColorAttributeName : themeColor], for: .selected)
        
        let workerOrderVC = self.childViewControllers[1]
        workerOrderVC.tabBarItem.image = UIImage(named: "wdgd")
        workerOrderVC.tabBarItem.selectedImage = UIImage(named: "wdgd_h")?.withRenderingMode(.alwaysOriginal)
        workerOrderVC.tabBarItem.title = "我的工单"
        workerOrderVC.tabBarItem.setTitleTextAttributes([NSForegroundColorAttributeName : UIColor.lightGray], for: .normal)
        workerOrderVC.tabBarItem.setTitleTextAttributes([NSForegroundColorAttributeName : themeColor], for: .selected)
        
        let shoppingMallVC = self.childViewControllers[2]
        shoppingMallVC.tabBarItem.image = UIImage(named: "shangcheng")
        shoppingMallVC.tabBarItem.selectedImage = UIImage(named: "shangcheng_h")?.withRenderingMode(.alwaysOriginal)
        shoppingMallVC.tabBarItem.title = "我的商城"
        shoppingMallVC.tabBarItem.setTitleTextAttributes([NSForegroundColorAttributeName : UIColor.lightGray], for: .normal)
        shoppingMallVC.tabBarItem.setTitleTextAttributes([NSForegroundColorAttributeName : themeColor], for: .selected)
        
        let mineVC = self.childViewControllers[3]
        mineVC.tabBarItem.image = UIImage(named: "geren")
        mineVC.tabBarItem.selectedImage = UIImage(named: "geren_h")?.withRenderingMode(.alwaysOriginal)
        mineVC.tabBarItem.title = "个人中心"
        mineVC.tabBarItem.setTitleTextAttributes([NSForegroundColorAttributeName : UIColor.lightGray], for: .normal)
        mineVC.tabBarItem.setTitleTextAttributes([NSForegroundColorAttributeName : themeColor], for: .selected)
        let insets = UIEdgeInsetsMake(0, 0, 0, 0)
      
        
        homeVC.tabBarItem.imageInsets = insets
        workerOrderVC.tabBarItem.imageInsets = insets
        shoppingMallVC.tabBarItem.imageInsets = insets
        mineVC.tabBarItem.imageInsets = insets
        
    }
    
    
    func tabBarController(tabBarController: UITabBarController, didSelectViewController viewController: UIViewController) {
        if lastViewController == viewController {
            
        }
        lastViewController = viewController
        
    }
    func tabBarController(_ tabBarController: UITabBarController, shouldSelect viewController: UIViewController) -> Bool {
        if  viewController.isKind(of: ShoppingMallViewController.self) {
            tabBarController.tabBar.isHidden = true
        }
        return true
    }
    
    
    
}

