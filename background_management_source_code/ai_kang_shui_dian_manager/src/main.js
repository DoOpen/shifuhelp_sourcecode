import Vue from 'vue'
import VueRouter from 'vue-router'
import Common from './common'
import Lin from './components/component'

Vue.use(VueRouter)
Vue.use(Common)
Vue.use(Lin)

//登录界面
import Login from './pages/others/login.vue'
//网页挂载点
import Layout from './pages/others/layout.vue'
//首页
import Home from './pages/others/home.vue'
//个人信息
import UserInfo from './pages/system/user-info.vue'
//404页面
import ErrorPage from './pages/others/error-page.vue'
//工单管理
import WorkOrder from './pages/workOrder/work-order.vue'
import WorkOrderEditor from './pages/workOrder/work-order-editor.vue'
import WorkOrderCreate from './pages/workOrder/work-order-create.vue'
//工单服务分类管理
import ServiceClass from './pages/workOrder/server-class.vue'
import ServiceClassEditor from './pages/workOrder/server-class-editor.vue'
//支付记录
import PingHistory from './pages/workOrder/ping-history'
//工单收益统计
import WorkOrderFinance from './pages/workOrder/work-order-finance'
//报备信息管理
import Reported from './pages/reported/reported'
import ReportedEditor from './pages/reported/reported-editor'
//系统账号
import SystemAccount from './pages/system/system-account.vue'
import SystemAccountEditor from './pages/system/system-account-editor.vue'
//模块管理
import Module from './pages/system/module.vue'
import ModuleEditor from './pages/system/module-editor.vue'
//角色管理
import Role from './pages/system/role.vue'
import RoleEditor from './pages/system/role-editor.vue'
//用户模块
import Member from './pages/member/member.vue'
import MemberEditor from './pages/member/member-editor.vue'
//师傅模块
import Worker from './pages/member/worker.vue'
import WorkerEditor from './pages/member/worker-editor.vue'
//商品
import Goods from './pages/goods/goods.vue'
import GoodsEditor from './pages/goods/goods-editor.vue'
//线上商品分类
import GoodsClass from './pages/goods/goods-class.vue'
import GoodsClassEditor from './pages/goods/goods-class-editor.vue'
//线下商品分类
import InlineGoodsClass from './pages/goods/inline-goods-class.vue'
import InlineGoodsClassEditor from './pages/goods/inline-goods-class-editor.vue'
//商品规格
import Specification from './pages/goods/specification.vue'
import SpecificationEditor from './pages/goods/specification-editor.vue'
//优惠券
import Coupon from './pages/goods/coupon.vue'
import CouponEditor from './pages/goods/coupon-editor.vue'
//订单
import Order from './pages/order/order.vue'
import OrderEditor from './pages/order/order-editor.vue'
//售后
import OrderRefund from './pages/order/order-refund.vue'
import OrderRefundEditor from './pages/order/order-refund-editor.vue'
//退款原因
import RefundReason from './pages/order/refund-reason.vue'
import RefundReasonEditor from './pages/order/refund-reason-editor.vue'
//财务统计
import OrderFinance from './pages/order/order-finance.vue'
//物流公司
import Logistics from './pages/order/logistics.vue'
import LogisticsEditor from './pages/order/logistics-editor.vue'
//提现列表
import Withdrawal from './pages/member/withdrawal.vue'
//提现详情
import WithdrawalEditor from './pages/member/withdrawal-editor.vue'
//提现统计
import WithdrawalOver from './pages/member/withdrawal-over.vue'
//提现详情
import WithdrawalOverEditor from './pages/member/withdrawal-over-editor.vue'
//系统设置
//图文编辑
import SystemHtml from './pages/setting/system-html.vue'
import SystemHtmlEditor from './pages/setting/system-html-editor.vue'
//系统广告
import Banner from './pages/setting/banner.vue'
import BannerEditor from './pages/setting/banner-editor.vue'
//城市列表
import City from './pages/setting/city.vue'
import CityEditor from './pages/setting/city-editor.vue'
//工种管理
import WorkType from './pages/member/work-type'
import WorkTypeEditor from './pages/member/work-type-editor'
//平台设置
import Setting from './pages/setting/setting'
import SettingEditor from './pages/setting/setting-editor'
//派单师傅列表
import SendWorkOrder from './pages/member/send-work-order'
//版本控制
import VersionEditor from './pages/setting/version-editor'
Vue.use(VueRouter)
Vue.use(Common)

let router=new VueRouter({
  routes:[
    //未匹配路由重定向到error_page
    {
      path:'*',
      redirect:'/error_page'
    },
    {
     path:'/',
      component:Login
    },
    {
      path:'/home',
      component:Home,
      children:[
        //404页面
        {
          path:'/error_page',
          component:ErrorPage
        },
        //个人信息
        {
          path:'/user_info',
          component:UserInfo
        },
        //个人信息
        {
          path:'/version/:version_type',
          component:VersionEditor
        },
        //平台设置
        {
          path:'/setting',
          component:Setting
        },
        {
          path:'/setting_editor/:settingBean',
          component:SettingEditor
        },
        //派单师傅列表
        {
          path:'/send_work_order/:order_id',
          component:SendWorkOrder
        },
        //工单管理
        {
          path:'/work_order/:type?',
          component:WorkOrder
        },
        //工单详情
        {
          path:'/work_order_editor/:orderBean',
          component:WorkOrderEditor
        },
        //创建工单
        {
          path:'/work_order_create',
          component:WorkOrderCreate
        },
        //工单服务分类
        {
          path:'/service_class/:class_id/:level/:title',
          component:ServiceClass
        },
        {
          path:'/service_class_editor/:class_id/:class_parent_id',
          component:ServiceClassEditor
        },
        //工单收益统计
        {
          path:'/work_order_finance',
          component:WorkOrderFinance
        },
        //报备列表
        {
          path:'/reported/:reported_state?',
          component:Reported
        },
        //工单详情
        {
          path:'/reported_editor/:reportedBean',
          component:ReportedEditor
        },
        //系统账号
        {
          path:'/system_account',
          component:SystemAccount
        },
        {
          path:'/system_account_editor/:account_id',
          component:SystemAccountEditor
        },
       //模块管理
        {
          path:'/module/:module_id/:level/:title',
          component:Module
        },
        {
          path:'/module_editor/:module_id/:parent_id',
          component:ModuleEditor
        },
        //角色管理
        {
          path:'/role',
          component:Role
        },
        {
          path:'/role_editor/:role_id',
          component:RoleEditor
        },
        //商品
        {
          path:'/goods',
          component:Goods
        },
        {
          path:'/goods_editor/:goods_id',
          component:GoodsEditor
        },
        //商品分类
        {
          path:'/goods_class/:class_id/:level/:title',
          component:GoodsClass
        },
        {
          path:'/goods_class_editor/:class_id/:parent_id',
          component:GoodsClassEditor
        },
        //商品规格管理
        {
          path:'/specification/:specification_id/:level/:title',
          component:Specification
        },
        //优惠券
        {
          path:'/coupon',
          component:Coupon
        },
        {
          path:'/coupon_editor/:coupon_id',
          component:CouponEditor
        },
        {
          path:'/specification_editor/:specification_id/:parent_id',
          component:SpecificationEditor
        },
        //订单
        {
          path:'/order/:order_state?',
          component:Order
        },
        {
          path:'/order_editor/:order_id',
          component:OrderEditor
        },
        //售后
        {
          path:"/order_refund",
          component:OrderRefund
        },
        {
          path:"/order_refund_editor/:refund_id",
          component:OrderRefundEditor
        },
        //退款原因
        {
          path:"/refund_reason",
          component:RefundReason
        },
        {
          path:"/refund_reason_editor/:reason_id",
          component:RefundReasonEditor
        },
        //财务统计
        {
          path:"/order_finance",
          component:OrderFinance
        },
        //提现申请
        {
          path:"/withdrawal/:withdrawal_state?",
          component:Withdrawal
        },
        {
          path:"/withdrawal_editor/:withdrawalBean",
          component:WithdrawalEditor
        },
        //提现统计
        {
          path:"/withdrawal_over",
          component:WithdrawalOver
        },
        {
          path:"/withdrawal_over_editor/:withdrawalBean",
          component:WithdrawalOverEditor
        },
        //押金支付记录
        {
          path:"/ping_history/:ping_type?",
          component:PingHistory
        },
        //物流公司
        {
          path:"/logistics",
          component:Logistics
        },
        {
          path:"/logistics_editor/:logistics_id",
          component:LogisticsEditor
        },
        //图文编辑
        {
          path:"/system_html",
          component:SystemHtml
        },
        {
          path:"/system_html_editor/:html_id",
          component:SystemHtmlEditor
        },
        //系统广告
        {
          path:"/banner",
          component:Banner
        },
        {
          path:"/banner_editor/:banner_id",
          component:BannerEditor
        },
        //城市列表
        {
          path:"/city/:id/:level/:title",
          component:City
        },
        {
          path:"/city_editor/:id/:parent_id",
          component:CityEditor
        },
       //用户模块
        {
          path:'/member',
          component:Member
        },
        {
          path:'/member_editor/:member_id',
          component:MemberEditor
        },
        //师傅模块
        {
          path:'/worker/:member_state?',
          component:Worker
        },
        {
          path:'/worker_editor/:member_id',
          component:WorkerEditor
        },
        //工种管理
        {
          path:'/work_type',
          component:WorkType
        },
        {
          path:'/work_type_editor/:typeBean?',
          component:WorkTypeEditor
        }
      ]
    }
  ]
})
new Vue({
  el: '#app',
  router,
  template: '<layout/>',
  components: { Layout}
})
