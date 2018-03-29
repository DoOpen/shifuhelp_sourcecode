

app1.controller("mainCtrl", ['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce','$window', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce,$window) {
	console.log("主控制器");
  // $cookieStore.put('member_id',$cookieStore.get('member_id'));
  // $cookieStore.put('member_token',$cookieStore.get('member_token'));
  // $cookieStore.put('member_id',48);
  // $cookieStore.put('member_token','9c9ab4f1-e47f-43ea-af47-8b75c40c021d');
  console.log($cookieStore.get("member_id"))
  console.log($cookieStore.get("member_token"))
	/*获取用户资料*/
 //  $scope.userInfoFun=function(){
	// 	$http.post(url+"memberInterfaces.api?getMemberDetail",$.param({
 //      	member_id: $cookieStore.get('member_id'),
 //      	member_token: $cookieStore.get('member_token')
 //    }), 
 //    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
	// ).success(function(data) {
 //    	console.log(data)
 //    	if(data['status']=="ok"){
 //       		$scope.userInfo=data['data'];
 //    	}
 //  	});
	// }
 //  $scope.getCookie = function(name){
 //    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 //    if(arr=document.cookie.match(reg)){
 //      return unescape(arr[2]);
 //    }else {
 //      return null;
 //    }
 //  } 
 //  if ($cookies.get("user_info")) {
 //    $scope.member =JSON.parse($scope.getCookie("user_info"));
 //    //console.log($scope.member);
 //    $cookieStore.put('member_id', $scope.member.member_id);
 //    $cookieStore.put('member_token', $scope.member.member_token);
 //    $scope.userInfoFun();
 //  } else {
 //    window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx76adf3e3a6e3e471&redirect_uri=http%3a%2f%2faksd.tstweiguanjia.com%2fwx_login.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
 //  }
  
	/*判断是否授权登入*/
	// if($cookieStore.get('member_id')&&$cookieStore.get('member_token')){
	// 	/*获取用户资料*/
 //   		$scope.userInfoFun();
 //   		$scope.code=null;
	// }else{

	// 	//获取code值	
	// 	$scope.hrefArr = location.href.split("?");
	//     $scope.hrefArr.length>1&&($scope.code = $scope.hrefArr[1].split("&")[0].split("=")[1]);
	//     //调取接口，把code的值传给接口，获取微信用户的id和token
	//     if ($scope.code) {
	//         $http.post(
	//             url + "memberInterfaces.api?wxPubMemberLogin",$.param({
	//                 code: $scope.code
	//             }),
	//             {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
	//         ).success(function (data) {
	//         	console.log("微信登入用户信息");
	//             console.log(data);
	//             if (data['status'] == 'ok') {
	//                 $scope.userInfo = data['data'];
	//                 $cookieStore.put('member_id',$scope.userInfo.member_id);//封装
	//                 $cookieStore.put('member_token',$scope.userInfo.member_token);
	//                 $cookieStore.put('wxmember_head_image',$scope.userInfo.member_head_image);//用户微信昵称头像
	//             	$cookieStore.put('wxmember_name',$scope.userInfo.member_nick_name);//用户微信昵称
	//             }else if (data['status'] == 'error'){
	//                 console.log(data['error']);
	//             }
	//         });
	//    }else{
	//  window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx76adf3e3a6e3e471&redirect_uri=http%3a%2f%2faksd.tstweiguanjia.com&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	//    }    
	// }
  //后退页面 	
	$scope.back=function(){
	  window.history.go(-1);
	};

  //刷新页面
  $scope.reloadRoute = function () {
    $window.location.reload();
  };
  //带刷新的小提示
  $scope.tips1 = function(arr){
      $(".Prompt1 .tips").html("");
      $(".Prompt1  .tips").append(arr);
      $(".Prompt1 ").fadeIn();
      setTimeout("$('.Prompt-back').fadeOut(window.location.reload());",1000);
  }
  //不带刷新的小提示
  $scope.tips2 = function(arr,time){
      $(".Prompt1  .tips").html("");
      $(".Prompt1  .tips").append(arr);
      $(".Prompt1 ").fadeIn();
      setTimeout("$('.Prompt-back').fadeOut();",time);
  }
  //带确认的提示信息
  $scope.tips3 = function(arr){
      $(".tips2").html("");
      $(".tips2").append(arr)
      $(".Prompt-back").fadeIn();
  }
	//控制遮罩 0关闭 1开启
  $scope.mask=0;
  $scope.maskFun = function(id){
    $scope.mask=id;
  }


  //删除数组指定下标或指定对象  
  // Array.prototype.remove=function(obj){ 
  //   for(var i =0;i <this.length;i++){ 
  //     var temp = this[i]; 
  //     if(!isNaN(obj)){ 
  //     temp=i; 
  //   } 
  //   if(temp == obj){ 
  //     for(var j = i;j <this.length;j++){ 
  //       this[j]=this[j+1]; 
  //     } 
  //     this.length = this.length-1; 
  //     } 
  //   } 
  // }
  /* 具体用法 */
  // var str ="vvvvvvv"; 
  // arr.remove(3);//删除下标为3的对象 
  // arr.remove(str);//删除对象值为“vvvvvvv”

}])
   /*售后*/
.controller("shList",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("售后列表");
  //获得收货列表
  $scope.shListFun = function(){
  $http.post(url+"orderInterfaces.api?getMemberRefunds",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          refund_state: 'wait_review,accept,refuse,end' 
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.listInfo=data["data"];
        }
      });
  }
  $scope.shListFun();
}])
.controller("shInfo",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("售后详情");
  $scope.rid=$location.search().rid;//获取地址栏的id（退款订单id）
  $scope.oid=$location.search().oid;//获取地址栏的id（订单id）
  $scope.refundCode=$scope.userInfo.member_bank_code;//获取用户付款账号
  console.log($scope.rid);
  //获得售后订单详情
  $http.post(url+"orderInterfaces.api?getRefundDetail",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          refund_id: $scope.rid
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.infoHTML=data["data"];
        }
      });
  //获取整个订单详情
  $http.post(url+"orderInterfaces.api?getOneOrderDetail",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          order_id: $scope.oid
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.order=data["data"];
        }
      });
  //取消退款遮罩
  $scope.no=0; //控制取消退款遮罩
  $scope.noFun = function(id){
    $scope.no=id;
  }
  //等待遮罩
  $scope.loading=0; //控制遮罩显示 0 隐藏，1 显示 
  $scope.loadings=0; //控制等待成功 0 等待，1 成功
  $scope.loadingfun = function(id){
    $scope.loading=id;
  }
  //取消退款方法
  $scope.noRefundFun = function(){
    $scope.noFun(0);
    $scope.loadingfun(1); 
    $http.post(url+"orderInterfaces.api?cancleRefundOrder",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          refund_id: $scope.rid
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
          $scope.loadings=1;
          $scope.loadingfun(1);
          $timeout(function(){
            $location.path('shList');
          },1500);
        }
      });
  }


}])

.controller("shLogistics",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("提交退货物流");
  $scope.rid=$location.search().rid;
  //获得售后订单详情
  $http.post(url+"orderInterfaces.api?getRefundDetail",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          refund_id: $scope.rid
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.infoHTML=data["data"];
            $scope.rtimeFun();
        }
      });
  //计算商家同意退款时间
  $scope.rtimeFun = function(){
    var date = new Date();
    // var time=date.toLocaleTimeString();
    // var auditTime=$scope.infoHTML.audit_time;
    // auditTime=auditTime.replace(/-/g,"/");
    // var date = new Date(auditTime);
    // $scope.rtime=date.getTime() - auditTime.getTime();
    var time=stringToDate($scope.infoHTML.audit_time); //把商家同意退款时间赋值给变量
    $scope.rtime=date.getTime() - time.getTime(); //计算当前时间和商家同意退款时间
    $scope.rday=6 - Math.floor($scope.rtime/(24*3600*1000)); //计算天数

    //计算出小时数
    var leave1=$scope.rtime%(24*3600*1000)    //计算天数后剩余的毫秒数
    $scope.rhour=24 - Math.floor(leave1/(3600*1000)) //计算时间
    console.log($scope.rtime);
    console.log($scope.rday);
    console.log($scope.rhour); 
     
    /*'yyyy-MM-dd HH:mm:ss'格式的字符串转日期*/
  function stringToDate(str){
    var tempStrs = str.split(" ");
    var dateStrs = tempStrs[0].split("-");
    var year = parseInt(dateStrs[0], 10);
    var month = parseInt(dateStrs[1], 10) - 1;
    var day = parseInt(dateStrs[2], 10);
    var timeStrs = tempStrs[1].split(":");
    var hour = parseInt(timeStrs [0], 10);
    var minute = parseInt(timeStrs[1], 10);
    var second = parseInt(timeStrs[2], 10);
    var date = new Date(year, month, day, hour, minute, second);
    return date;
  } 
  }
  
  $scope.logistics={
    company:'',//物流名称
    no:'',//物流编号
    phone:'',//电话
    name:''//退货人姓名
  }
  $scope.logisticsNames=['顺丰快递','申通快递','韵达快递','圆通快递','中通快递','天天快递','百世汇通','邮政快递'];
  //把select的值赋值到input
  $scope.lname='';
  var s=document.getElementById('selects');
  //s.selectedIndex = -2;
  $scope.lnameFun = function(){
      $scope.lname=s.value;
  }
  $scope.of=0;
  $scope.ofs = function(id){
    $scope.of=id;
  }
  $scope.ofFun = function(){
    if($scope.lname==''||$scope.logistics.no==''||$scope.logistics.phone==''){
      $scope.ofs(1);
    }else{
      $scope.shLogisticsFun();
    }
  }
  //提交退货物流信息
  $scope.shLogisticsFun = function(){
    $http.post(url+"orderInterfaces.api?updateRefundOrderLogistics",$.param({
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token'),
        refund_id: $scope.rid,
        logistics_company: $scope.lname,
        logistics_no: $scope.logistics.no,
        logistics_phone: $scope.logistics.phone,
        logistics_name: $scope.logistics.name
        }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
      ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
          
        }
      });
    }
}])
/*退单物流追踪*/
.controller("shLogisticsInfo",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单退货物流");
  $scope.rid=$location.search().rid;
  //获得售后订单详情
  $http.post(url+"orderInterfaces.api?getRefundDetail",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          refund_id: $scope.rid
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.rinfoHTML=data["data"];
        }
      });
    $scope.arr1;
  //获取退货物流信息
    $http.post(url+"orderInterfaces.api?getOrderLogisticsDetails",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          logistics_no: 427069144961,
          type: 'refund'
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
          $scope.infoHTML=data['data'];
          $scope.listInfo=data['data']['logisticsDetailBeans'];
          $scope.arr1=$scope.listInfo[0];
        }
      });
  

}])
.controller("orderEvaluate",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单评价");
  $scope.oid=$location.search().oid;
  //获取订单详情
  $http.post(url+"orderInterfaces.api?getOneOrderDetail",$.param({
      order_id: $scope.oid,
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token')

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.infoHTML=data['data'];
        $scope.rinfoHTML=data['data']['orderGoodsBeans'];
        $scope.goodsNum=data['data']['orderGoodsBeans'].length;
        $scope.fileArr=[];
        $scope.list=[];
        for(var i=0;i<$scope.goodsNum;i++){
          $scope.fileArr[i]=[];
          $scope.list[i]=[];
        }
    }
  });
  // //存放评论数组
  // $scope.list= [{
  //   member_id:'',
  //   order_id:'',
  //   assessment_desc:'',
  //   assessment_type:'goods',
  //   assessment_star1:'5',
  //   assessment_star2:'',
  //   assessment_star3:'',
  //   relation_id:'',
  //   assessmentImgBeans:[
  //   {assessment_img:' '},
  //   {assessment_img:' '},
  //   {assessment_img:' '}
  //   ]
  // }];
  $scope.g = function(){
    for(var i=0;i<$scope.goodsNum;i++){
      console.log($scope.list);
        $scope.list[i].assessment_desc=$scope.evaluate.text[i];
        $scope.list[i].member_id=$scope.infoHTML.member_id;
        $scope.list[i].order_id=$scope.oid;
        $scope.list[i].relation_id=$scope.rinfoHTML[i].goods_id;
        $scope.list[i].assessmentImgBeans=$scope.fileArr[i];
        $scope.list[i].assessment_star2=$scope.evaluate.logistics;
        $scope.list[i].assessment_star3=$scope.evaluate.quality;
        $scope.list[i].assessment_type='goods';
      
    }  
      console.log($scope.list);
  }
  //评价
  $scope.evaluate = {
    goods:5, //商品
    logistics:5, //物流
    quality:5,  //服务质量
    text:''  //文字评价
  }
  //评价星星
  $scope.xxFun = function($event){
    angular.element($event.target).addClass("pjxx1");
    angular.element($event.target).prevAll().addClass("pjxx1");
    angular.element($event.target).nextAll().removeClass("pjxx1").addClass("npjxx1");
    // if(type=='goods'){
    //   for(var i=0;i<$scope.list.length;i++){
    //     if(gs==$scope.list[i]){
    //       $scope.evaluate.goods=id;
    //     }
    //   }
    // }
  }
  //商品评分（商品分开评）
  $scope.pfFun1 = function(list,id){
    for(var i=0;i<$scope.goodsNum;i++){
      if(list==i){
        $scope.list[i].assessment_star1=id;
      }
    }
  }
  //物流和服务评分
  $scope.pfFun = function(id,type){
    if(type=='logistics'){
      $scope.evaluate.logistics=id;
      console.log('物流'+id);
    }else if(type=='quality'){
      $scope.evaluate.quality=id;
      console.log('服务'+id);
    }
  }
  
  //图片上传
    
    $scope.fileUrl = {
      'fileImgSrcArr':[] //用来放置要显示原图片的Src
    }

    $scope.ftfilefn = function (e) {
        var formDOM = angular.element(e).parents("form"),//找到file所在form(祖先)
            $formIndex = formDOM.index() - 1,//file祖先form元素的下标(index)
            ftFileImgBox = angular.element(e).parent().siblings(".ftFileImgBox");//找到同一个form下放置评价图片的盒子(元素)
        console.log($formIndex)
        
        var len = parseInt(ftFileImgBox.find(".img").length);//盒子中放置图片的数量
        console.log(len);
        var fileLen =parseInt(e.files.length);//每次change后file的数量
        if(len+fileLen>3) {
            console.log("不能超过3张");
            return false;
        }
        if(len+fileLen>=3){
          angular.element(e).parents(".fileUrl").css("display",'none');
        }
        $scope.laoding=1;
        $http({
            method: 'POST',
            url: url + "orderInterfaces.api?uploadAssessmentImg",
            data: {},
            headers: {
              'Content-Type': undefined
            },

            //将jq对象转换成原生js对象
            transformRequest: function (data) {
                var formData = new FormData(formDOM[0]);
                formData.append("img[]", e);//在数组后面添加元素
                //实际上传
                return formData;
            }
        }).success(function (d) {
            console.log(d);
            $scope.laoding=0;
            if (d.status == "ok") {
                $scope.ofImg = true;
                var imgUrlLen = d["data"].length;
                var html= "";
                for(var i=0;i<imgUrlLen;i++){
                    // $scope.fileArr[].unshift(e.files[i]);
                    // $scope.fileUrl.fileImgSrcArr.unshift(d["info"]["img"][i]);//用来放置要显示原图片的Src
                    // $scope.fileUrl.fileThumbSrcArr.unshift(d["info"]["thumb"][i]);//用来放置要显示缩略图片的Src
                    // html += '<div class="img">' +
                    //     '< img src="' + d["info"]["thumb"][i] + '">' +
                    //     '<span class="x1"></span>' +
                    //     '</div>';
                    
                    // $scope.fileArr[].unshift(e.files[i]);
                    $scope.fileArr[$formIndex].unshift(e.files[i]);

                    html += '<div class="img h70 w70 m_l10  p_r  bgc_d9 dis_l fl_l flex-center b_r5">'+
                              '<div class="d_red_x b_r_b50 x1"></div>'+
                              '<div class="w62 h62 m_t3 m0a ">'+
                                '<img src="' + d["data"][i] + ' " alt="">'+
                              '</div>'+
                            '</div>'
                }
                console.log($scope.fileArr[$formIndex]);
                angular.element(e).parent().siblings(".ftFileImgBox").append(html);
                console.log($formIndex);
                angular.element(".x1").click(function(){
                    angular.element(e).parents(".fileUrl").css("display",'block');//控制上传按钮显示
                    var $formIndex1 = angular.element(this).parents("form").index(),//找到元素祖先form的下标
                         index = angular.element(this).parent().index()-1;//每个评价图片的下标
                         // file = angular.element(this).parents().find("input[type=file]");//找到同一个form下的file
                    // file.splice(index,1)//删除files下下标为index的file(似乎没效果放弃)
                    console.log(index);
                    $scope.fileArr[$formIndex].splice(index,1);
                    angular.element(this).parent().remove();//删除自己
                    // console.log($scope.fileArr[$formIndex]);
                    // $scope.fileArr[$formIndex].splice(index,1);//删除下标下的file元素
                })

            } else {
                $scope.diType = false;
            }
        }).error(function (err, status) {
            $scope.diType = false;
            //console.log(err);
        });
      }
    

// 遮罩控制
  $scope.laoding=0;//等待遮罩变量  默认0隐藏，1显示
  $scope.laoding1=0;//等待还是成功变量 默认0等待，1成功
  $scope.laodingFun = function(number,number1){
    $scope.laoding=number;
    $scope.laoding1=number1;
  }
  //上传订单评价详情
  $scope.orderEvaluateFun = function(){
    $scope.g();//调用方法，把评论详情存放进$scope.list
    $scope.laodingFun(1,0);
    $http.post(url+"orderInterfaces.api?assessmentOrder",$.param({
        json: JSON.stringify($scope.list),
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token')

        }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
      ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
          $scope.laodingFun(1,1);
          $timeout(function(){
            $location.path('order');
          },1500);
        }
      });
  }

}])
/*订单物流追踪*/
.controller("orderLogistics",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单物流");
  $scope.oid=$location.search().oid;
  //获取订单详情
  $http.post(url+"orderInterfaces.api?getOneOrderDetail",$.param({
      order_id: $scope.oid,
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token')

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log('物流信息：'+data)
      if(data['status']=="ok"){
        $scope.rinfoHTML=data['data'];
      }
    });
  //获取物流信息
    $http.post(url+"orderInterfaces.api?getOrderLogisticsDetails",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          order_id: $scope.oid,
          logistics_no: 427069144961,
          type: 'order'
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log('订单信息：'+data)
        if(data['status']=="ok"){
          $scope.infoHTML=data['data'];
          $scope.listInfo=data['data']['logisticsDetailBeans'];
        }
      });
}])
/*我的订单列表*/
.controller("order",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单列表");

  $scope.listInfo = [];//定义一个空列表'[]'
  $scope.orderFun1 = function(type,page){
      $scope.page = page || 1;//如果page为空，就赋值1给他
      console.log($scope.page);
      $scope.type = type;
      $http.post(url+"orderInterfaces.api?getOrders",$.param({
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token'),
        order_state: type,
        order_type: 'goods',
        page: $scope.page
      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
          //$scope.listInfo = [];//初始化，把列表的值为空
          $scope.allPage = Math.ceil(data["total"] /10) == 0 ? '1' : Math.ceil(data["total"] / 10);
          $scope.listInfo.push.apply($scope.listInfo, data["data"])
          /*for (var i = 0, len = data["data"].length; i < len; i++) {
                        $scope.listInfo.push(data["data"][i]);
                        --js原生写法--利用push方法把list数组添加数据
                    }*/
        } else if (data['status'] == 'error') {
            console.log(data['status']);
          } else if (data['data'] == 'token failed') {
      }
      });
  }
  $scope.orderFun = function(type){ 
    $scope.listInfo = [];
    $scope.orderFun1(type);
  }
  //从个人中心进来是打开哪个列表
  $scope.rtFun1 = function(){
    $scope.oid=$location.search().id;//获取地址栏的id属性，angular专用，或者$location.search()['name'];
      if($scope.oid==1){
        $scope.orderFun('');
      }else if($scope.oid==2){
              $scope.orderFun('wait_pay');
            }else if($scope.oid==3){
                    $scope.orderFun('wait_receive');
                  }else if($scope.oid==4){
                          $scope.orderFun('wait_assessment');
                        }else if($scope.oid==5){
                          $scope.orderFun('wait_send');
                        }  
  }
  //控制从订单详情退回来打开哪个列表
  $scope.rtFun2 = function(){
  $scope.type1=$location.search().type;
    if($scope.type1=='wait_pay'){
      $scope.orderFun('wait_pay');
    }else if($scope.type1=='wait_receive'){
      $scope.orderFun('wait_receive');
    }else if($scope.type1=='wait_assessment'){
      $scope.orderFun('wait_assessment');
    }else if($scope.type1=='wait_send'){
      $scope.orderFun('wait_send');
    }else{
      $scope.orderFun('');
    }
  }

  $scope.rt=$location.search().rt;
  if($scope.rt==1){
    $scope.rtFun1();
  }else{
    $scope.rtFun2();
  }
  // $(function () {
  //     console.log($cookieStore.get("order"))
  //     if ($cookieStore.get("order")!=0) {
  //       //$("body").scrollTop($cookieStore.get("order"));
  //     }
  //   })
   /*滚动加载*/
    $(window).scroll(function() {
        var wTop = null,
          bTop = null,
          dTop = null;
          wTop = $(window).scrollTop();
          bTop = $("body").height();
          dTop = $(document).height();
          if (wTop + bTop >= dTop) { //下拉到底部加载
            if ($scope.allPage > $scope.page) {
              $scope.orderFun1($scope.type,++$scope.page);
          }
        }
        //$cookieStore.put('order', wTop);
        //console.log($cookieStore.get("order"))
        //return $.cookie(str);
      })
}])


.controller("orderInfo",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单详情");
  $scope.id=$location.search().id;//获取url订单id
  //获取订单详情
  $scope.orderInfoFun = function(){
    $http.post(url+"orderInterfaces.api?getOneOrderDetail",$.param({
      order_id: $scope.id,
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token')

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.infoHTML=data['data'];
        $scope.goodsList=data['data']['orderGoodsBeans'];
      }
    });
  }
  $scope.orderInfoFun();
  //确认退款按钮
  $scope.refund=0; //确认退款遮罩控制变量
  $scope.oid=''; //存储订单id变量
  $scope.gid=''; //存储订单商品id变量
  $scope.type=0; //存储是退货还是退款变量
  $scope.refundFun = function(id,oid,gid,type){
    $scope.refund=id;
    $scope.oid=oid;
    $scope.gid=gid;
    $scope.type=type;
  }
  //取消订单
  $scope.cancel=0;
  $scope.cancelFun = function(id){
    $scope.cancel=id;
  }
  $scope.cancelOrderFun = function(){
    $http.post(url+"orderInterfaces.api?cancelOrder",$.param({
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token'),
      order_id: $scope.id

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data);
      if(data['status']=="ok"){ 
        $scope.back();
      }
    });
  }

  //确认收货遮罩控制
  $scope.confirm=0;
  $scope.confirmFun = function(id) {
    $scope.confirm=id;
  }
  //确认收货
  $scope.confirmReceiptFun = function(){
    $http.post(url+"orderInterfaces.api?confirmOrder",$.param({
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token'),
      order_id: $scope.id

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data);
      if(data['status']=="ok"){
        $scope.confirmFun(0);
        $scope.orderInfoFun();
      }
    });
  }
  //app付款
  $scope.appGoPay = function(order_id){
    $scope.order_id1=order_id;
      var u = navigator.userAgent;
      if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
          JavaScript:android.appGoPay($scope.order_id1);
      } else
      if (u.indexOf('iPhone') > -1) {//苹果手机
          appGoPay($scope.order_id1);
      }
    
    
  }
  // $scope.paymentFun = function(order_id){
  //   window.location.href='http://aksd.tstweiguanjia.com//app-mall2/index.html#/pay_finish';
  //   $.ajax({
  //     url: url +  "orderInterfaces.api?payRealOrders", //接口
  //     method: 'post',
  //     data: {  //读取内容所需条件
  //       member_id : $cookieStore.get("member_id"),
  //       member_token : $cookieStore.get("member_token"),
  //       order_ids: order_id, //订单ID
  //       channel: 'wx_pub'    //微信公众号支付
  //     },
  //     dataType : "json",
  //     success: function (data) {
  //       var charge = data['data'];
  //       pingpp.createPayment(charge, function (result, error) {
  //         if (result == "success") { // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的 wap 支付结果都是在 extra 中对应的 URL 跳转。
  //           tips2("支付成功",1300);
  //             window.location.href="#/pay_finish?&order_id="+ order_id;
  //         } else if (result == "fail") {
  //           tips2("支付取消",1300);
  //           window.location.href="#/orderInfo?&id="+ order_id;
  //           // charge 不正确或者微信公众账号支付失败时会在此处返回
  //         } else if (result == "cancel") {
  //           tips2("支付失败",1300);
  //           window.location.href="#/orderInfo?&id="+ order_id;
  //         }
  //       })
  //     }
  //   });
  // }
}])
.controller("orderRefund",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单退款");
  $scope.oid=$location.search().id; //订单id
  $scope.gid=$location.search().gid;  //订单中的订单商品id
  $scope.type=$location.search().type; //判断是退款还是退货
  
  $scope.g; //存放订单中商品的数量

  //退款数据对象
  $scope.refund={
    reason:'',//退款原因
    desc:''//退款说明
  }
  $scope.laoding=0;//等待遮罩变量 0隐藏遮罩 1显示遮罩
  $scope.laoding1=0;  //0等待 1成功
  $scope.laodingFun = function(number,number1){
    $scope.laoding=number;
    $scope.laoding1=number1; 
  }
  //获取订单中要退款商品详情
  $http.post(url+"orderInterfaces.api?getOneOrderDetail",$.param({
      order_id: $scope.oid,
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token')

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.infoHTML=data['data'];
        $scope.goodsList=data['data']['orderGoodsBeans'];
        $scope.g=data['data']['orderGoodsBeans'].length;
        $scope.goodsNumFun();
      }
    });
  //获取退单原因列表
  $scope.r1=0;//储存退单原因列表长度的变量
  $http.post(url+"orderInterfaces.api?getRefundsReasons",$.param({

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.reasonList=data['data'];
        $scope.r1=data['data'].length;
      }
    });
  //控制退款原因遮罩
  $scope.r=0;
  $scope.rFun = function(id){
    $scope.r=id;
  }
  //控制选中退款原因
  $scope.n=0;
  $scope.nFun = function(id){
    $scope.n=id;
    console.log($scope.n);
    for(var i=0;i<$scope.r1;i++)
      if($scope.reasonList[i].refund_reason_id==id){
        $scope.refund.reason=$scope.reasonList[i].reason_name;
      }
    
  }
  //控制选中退款类型
  $scope.s=0;
  $scope.sFun = function(id){
    $scope.s=id;
  }
  //控制refund_type方法
  $scope.refund_type='';//退款类型 退款：money 已收货退款：with_goods 未收货退款：not_goods
  $scope.refundTypeFun = function(){
    if($scope.type==1){
      $scope.refund_type='money';
    }else if(s==3){
            $scope.refund_type='not_goods';
          }else if(s==4){
                  $scope.refund_type='with_goods';
                }
  }
  
  //退货数量逻辑
  $scope.goods={
    Num:0,  //需要退款商品购买数量
    Num1:1  //需要退款的商品数量
  }
  $scope.refundMoney=0;//需要退款的商品单价
  $scope.refundMoney1=0;//需要退款的总价（最大）
  $scope.goodsNumFun = function(){
    for(var i=0;i<$scope.g;i++){
      if($scope.goodsList[i].order_goods_id==$scope.gid){
        $scope.goods.Num=$scope.goodsList[i].goods_num;
        $scope.refundMoney=$scope.goodsList[i].goods_price;
        $scope.refundMoney1=$scope.goods.Num1*$scope.refundMoney;//最大退款金额
        console.log($scope.refundMoney);
      }
    }
  }
  //控制退货商品数量
  $scope.goodsNum2Fun = function(){
    if($scope.goods.Num1<1){
      $scope.goods.num1=1;
    }else if($scope.goods.Num1>$scope.goods.Num){
      $scope.goods.Num1=$scope.goods.Num;
    }
  }
  
  $scope.goodsNum1Fun = function(type){
    if(type=1){
      $scope.goods.Num1++;
      if($scope.goods.Num1>$scope.goods.Num){
        $scope.goods.Num1=$scope.goods.Num;
      }
    }else{
      $scope.goods.Num1--;
      if($scope.goods.Num1<1){
        $scope.goods.Num1=1;
      }
    }
    $scope.refundMoney1=$scope.goods.Num1*$scope.refundMoney;//最大退款金额
  }
  //图片上传
    $scope.fileUrl = {
      'fileImgSrcArr':[] //用来放置要显示原图片的Src
    }
    $scope.ftfilefn = function (e) {
        var formDOM = angular.element(e).parents("form"),//找到file所在form(祖先)
           $formIndex = formDOM.index(),//file祖先form元素的下标(index)
            ftFileImgBox = angular.element(e).parent().siblings("#ftFileImgBox");//找到同一个form下放置评价图片的盒子(元素)
        var len = parseInt(ftFileImgBox.find(".img").length);//盒子中放置图片的数量
        console.log(len);
        var fileLen =parseInt(e.files.length);//每次change后file的数量
        if(len+fileLen>3) {
            console.log("不能超过3张");
            return false;
        }
        if(len+fileLen>=3){
          angular.element("#fileUrl").css("display",'none');
        }
        $scope.laoding=1;
        $http({
            method: 'POST',
            url: url + "orderInterfaces.api?uploadAssessmentImg",
            data: {},
            headers: {
              'Content-Type': undefined
            },

            //将jq对象转换成原生js对象
            transformRequest: function (data) {
                var formData = new FormData(formDOM[0]);
                formData.append("img[]", e);//在数组后面添加元素
                //实际上传
                return formData;
            }
        }).success(function (d) {
            console.log(d);
            $scope.laoding=0;
            if (d.status == "ok") {
                $scope.ofImg = true;
                var imgUrlLen = d["data"].length;
                var html= "";
                for(var i=0;i<imgUrlLen;i++){
                    // $scope.fileArr[].unshift(e.files[i]);
                    // $scope.fileUrl.fileImgSrcArr.unshift(d["info"]["img"][i]);//用来放置要显示原图片的Src
                    // $scope.fileUrl.fileThumbSrcArr.unshift(d["info"]["thumb"][i]);//用来放置要显示缩略图片的Src
                    // html += '<div class="img">' +
                    //     '< img src="' + d["info"]["thumb"][i] + '">' +
                    //     '<span class="x1"></span>' +
                    //     '</div>';
                    
                    // $scope.fileArr[].unshift(e.files[i]);
                    $scope.fileUrl.fileImgSrcArr.unshift(d["data"][i]);

                    html += '<div class="img h70 w70 m_l10  p_r  bgc_d9 dis_l fl_l flex-center b_r5">'+
                              '<div class="d_red_x b_r_b50 x1"></div>'+
                              '<div class="w62 h62 m_t3 m0a ">'+
                                '<img src="' + d["data"][i] + ' " alt="">'+
                              '</div>'+
                            '</div>'
                }
                console.log($scope.fileUrl.fileImgSrcArr);

                angular.element("#ftFileImgBox").append(html);

                angular.element(".x1").click(function(){
                    angular.element("#fileUrl").css("display",'block');//控制上传图片按钮显示，消失
                    var $formIndex = angular.element(this).parents("form").index(),//找到元素祖先form的下标
                         index = angular.element(this).parent().index()-1;//每个评价图片的下标
                         // file = angular.element(this).parents().find("input[type=file]");//找到同一个form下的file
                    // file.splice(index,1)//删除files下下标为index的file(似乎没效果放弃)
                    console.log(index);
                    $scope.fileUrl.fileImgSrcArr.splice(index,1);
                    angular.element(this).parent().remove();//删除自己
                    console.log($scope.fileUrl.fileImgSrcArr);
                    // $scope.fileArr[$formIndex].splice(index,1);//删除下标下的file元素
                    $scope.tsFun(1); 
                })
            } else {
                $scope.diType = false;
            }
        }).error(function (err, status) {
            $scope.diType = false;
            //console.log(err);
        });
    }
  //检索信息是否填写完整
  $scope.of=0;
  $scope.ofs = function(id){
    $scope.of=id;
  }
  $scope.ofFun = function(){
    if($scope.type==1){
      if($scope.refund.reason=='') {
        $scope.of=1;
      }else {
        $scope.orderRefundFun();
      }
    }else{
      if($scope.refund.reason==''||$scope.s==0){
        $scope.of=1;
      }else{
        $scope.orderRefundFun();
      }
    }
  }
  //上传退款信息
  $scope.orderRefundFun = function(){
    $scope.laoding=1;
    $scope.refundTypeFun();//判断退款类型方法
    $http.post(url+"orderInterfaces.api?refundOrderNoFile",$.param({
        order_id: $scope.oid,
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token'),
        order_goods_id: $scope.gid,
        refund_count: $scope.goods.Num1,
        refund_desc: $scope.refund.desc,
        refund_reason_id: $scope.n,
        reason_name: $scope.refund.reason,
        refund_img1:  $scope.fileUrl.fileImgSrcArr[0],
        refund_img2:  $scope.fileUrl.fileImgSrcArr[1],
        refund_img3:  $scope.fileUrl.fileImgSrcArr[2],
        refund_type:  $scope.refund_type
        }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
      ).success(function(data) {
        console.log(data);
        if (data['status']=="ok") {
          $scope.laodingFun(1,1);
          $timeout(function(){
            $location.path('shList');
          },1500);
        }else if (data['status'] == 'error') {
          console.log(data['status']);
        } else if (data['data'] == 'token failed') {

        }
    });
  }
}])

/*我的收藏*/
.controller("collectionList",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("我的收藏");
  //获得收藏列表
  $scope.collectionListFun = function(type,page){
    $scope.type=type;
    $scope.page=page || 1;
  $http.post(url+"collectionInterfaces.api?getCollection",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          collection_type: type,
          page: $scope.page
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.listInfo=data["data"];
        }
      });
  }
  $scope.maxLength=$(".goods_list").find(".scList").length+1;

  $scope.collectionListFun();
  $scope.onclick = function(index){
    if($("#cid"+index).hasClass("scxz")){
      $("#cid"+index).removeClass("scxz");
    }else{
      $("#cid"+index).addClass("scxz");
    }
    //console.log($scope.maxLength)
    $scope.xznum=0;
    for(var i=0;i<$scope.maxLength;i++){
      if($("#cid"+[i]).hasClass("scxz")){
        $scope.xznum++;

      }
    }
    if($scope.maxLength==$scope.xznum){
      $(".alls").addClass("scxz");
    }else{
      $(".alls").removeClass("scxz");
    }
    $scope.cidFun();
  }
  
  $scope.qOnclick = function () {
    if($(".alls").hasClass("scxz")){
      $(".alls").removeClass("scxz");
      $(".scList").removeClass("scxz");
    }else{
      $(".alls").addClass("scxz");
      $(".scList").addClass("scxz");
    }
    $scope.cidFun();
  }
  $scope.cids=[];
  $scope.cidFun = function(){
    $scope.cids=[];
    $scope.cidLeng=$("scList").length;
    console.log($scope.maxLength);
    for(var i=0;i<$scope.maxLength;i++){
      if($("#cid"+[i]).hasClass("scxz")){
        $scope.cids.push($("#cid"+[i]).attr("cid"))
        console.log($("#cid"+[i]).attr("cid"))
      }
    }
    console.log($scope.cids)
  }
  //取消收藏
  $scope.delete = function(){
    if($scope.cids.length!=0){
      $http.post(url+"collectionInterfaces.api?cancelAllCollection",$.param({
            member_id: $cookieStore.get('member_id'),
            member_token: $cookieStore.get('member_token'),
            collection_ids: $scope.cids.join(",")
        }), 
        {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
      ).success(function(data) {
          console.log(data)
          if(data['status']=="ok"){
            $scope.tips2(data['data'],1000);
            $scope.collectionListFun();
            if($(".alls").hasClass('scxz')){
                $(".alls").removeClass('scxz');
            }
          }else if(data['status']=="error"){
            $scope.tips2(data['data'],1000);
          }
      });
    }else{
      $scope.tips2("请选择要删除的商品",1000);
    }
  }
  
  /*滚动加载*/
    $(window).scroll(function() {
        var wTop = null,
          bTop = null,
          dTop = null;
          wTop = $(window).scrollTop();
          bTop = $("body").height();
          dTop = $(document).height();
          if (wTop + bTop >= dTop) { //下拉到底部加载
            if ($scope.allPage > $scope.page) {
              $scope.collectionListFun($scope.type,++$scope.page);
          }
        }
      })
}])

/*收货地址*/
.controller("addressList",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("收货地址");
  $scope.add=0;//新增地址控制
  $scope.del=0;//删除地址遮罩
  $scope.of=0;//控制是新增按钮还是修改按钮
  $scope.address='';//拼接地址变量
  $scope.adds={
    name:'',//姓名
    mobile:'',//手机
    province:'',//地址省份
    city:'',//市
    district:'',//区 县
    detailed_address:'',//详细地址
    is_default:'',//是否默认地址
    address_id:'',//地址标识
    zip_code:'' //邮编
    
  }
  
  //控制新增地址遮罩方法
  $scope.addHide=function(type){
    $scope.add=type;
  }
  //控制新增按钮还是修改方法
  $scope.ofFun = function(type){
    $scope.of=type;
  }
  //查询地址列表
  $scope.addressList = function(){
  $http.post(url+"addressInterfaces.api?getOwnerAddress",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token')
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.listInfo=data['data'];
            $scope.len=data['data'].length;
        }
      });
  }
  $scope.addressList();
  //选择地址插件
  var area1 = new LArea();
  document.getElementById("aaddress")&&area1.init({
    'trigger': '#aaddress', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
    'valueTo': '#huadong', //选择完毕后id属性输出到该位置
    'keys': {
        id: 'id',
        name: 'name'
    }, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
    'type': 1, //数据源类型
    'data': LAreaData //数据源
  });
  area1.value=[25,16,0];//控制初始位置，注意：该方法并不会影响到input的value
  //获取选中地址信息
  //var addre=document.getElementById('aaddress').value.split(',');
  
//判断地址信息填写完整
  $scope.info=0;
  $scope.infoFun = function(id){
    $scope.info=id;
  }
  //增加地址时判断 
  $scope.infoOf=function(){
    var addre1=document.getElementById('aaddress').value.split(',');
    if($scope.adds.name==''||$scope.adds.mobile==''||$scope.adds.detailed_address==''||addre1==''){
      $scope.infoFun(1);
      $timeout(function(){
        $scope.infoFun(0);
      },2000);
    }else{
      $scope.addressNew();
    }
  }
  //修改地址时判断 
  $scope.infoOf1=function(id){
    $scope.id=id; //存放要修改地址的地址的id
    var addre1=document.getElementById('aaddress').value.split(',');
    if($scope.adds.name==''||$scope.adds.mobile==''||$scope.adds.detailed_address==''||addre1==''){
      $scope.infoFun(1);
      $timeout(function(){
        $scope.infoFun(0);
      },2000);
    }else{
      $scope.updataAddressFun(id);
    }
  }

  //新增地址
  $scope.addressNew = function(type){
    //判断是否是第一个地址，如果是设为默认
    $scope.lens=0;
    if($scope.len==0){
      $scope.lens=1;
    }else{
      $scope.lens=0;
    }
    //判断地址直辖市
    var addre=document.getElementById('aaddress').value.split(',');
    if(addre.length==2){
      addre[2]=addre[1];
      addre[1]=addre[0];
      addre[0]="";
    }
    $http.post(url+"addressInterfaces.api?insertAddress",$.param({
            member_id: $cookieStore.get('member_id'),
            member_token: $cookieStore.get('member_token'),
            name: $scope.adds.name,//姓名
            mobile: $scope.adds.mobile,//手机
            province: addre[0],//地址省份
            city: addre[1],//市
            district: addre[2]&&addre[2],//区 县
            detailed_address: $scope.adds.detailed_address,//详细地址
            is_default: $scope.lens,//是否默认地址
            //address_id: type,//地址标识（1）
            zip_code: $scope.adds.zip_code //邮编
        }), 
        {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
      ).success(function(data) {
          console.log(data)
          if(data['status']=="ok"){
                $scope.addressList();
                $scope.addHide(0);
                $scope.addsNull();
          }
        });
    }
  //设置默认地址
  $scope.defaultAddressfun = function(type){
    $scope.type=type;
    $http.post(url+"addressInterfaces.api?setDefaultAddress",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          address_id: type
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.addressList();
        }
      });
  }
  $scope.id='';//删除地址id存放
  //删除地址
  $scope.deleteAddressfun = function(){
    $scope.type=$scope.id;
    console.log($scope.type);
    $http.post(url+"addressInterfaces.api?deleteAddress",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          address_id: $scope.type
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
          $scope.delFun(0);
            $scope.addressList();
        }
      });
  }
  //删除地址遮罩
  $scope.delFun = function(type,id){
    $scope.del=type;
    $scope.id=id;   
  }
  //清空存放地址数据
  $scope.addsNull = function(){
    $scope.address='';
    $scope.adds={
    name:'',//姓名
    mobile:'',//手机
    province:'',//地址省份
    city:'',//市
    district:'',//区 县
    detailed_address:'',//详细地址
    is_default:'',//是否默认地址
    address_id:'',//地址标识
    zip_code:'' //邮编
    }
  }
  //修改地址-传值到修改界面
  $scope.edtaddressFun = function(id,name,mobile,province,city,district,detailed_address,zip_code){
    $scope.adds.address_id=id;
    $scope.adds.name=name;
    $scope.adds.mobile=mobile;
    $scope.adds.province=province;
    $scope.adds.city=city;
    $scope.adds.district=district;
    $scope.address=province+city+district;
    $scope.adds.detailed_address=detailed_address;
    $scope.adds.zip_code=zip_code;
  }
  //修改地址
  $scope.updataAddressFun = function(type){
    
    //判断地址直辖市
    var addre=document.getElementById('aaddress').value.split(',');
    if(addre.length==2){
      addre[2]=addre[1];
      addre[1]=addre[0];
      addre[0]="";
    }
    $http.post(url+"addressInterfaces.api?insertAddress",$.param({
            member_id: $cookieStore.get('member_id'),
            member_token: $cookieStore.get('member_token'),
            name: $scope.adds.name,//姓名
            mobile: $scope.adds.mobile,//手机
            province: addre[0],//地址省份
            city: addre[1],//市
            district: addre[2]&&addre[2],//区 县
            detailed_address: $scope.adds.detailed_address,//详细地址
            is_default: $scope.lens,//是否默认地址
            address_id: type,//地址标识（1）
            zip_code: $scope.adds.zip_code //邮编
        }), 
        {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
      ).success(function(data) {
          console.log(data)
          if(data['status']=="ok"){
                $scope.addressList();
                $scope.addHide(0);
                $scope.addsNull();
          }
        });
    }
}])

/*我的优惠券*/
.controller("couponList",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("我的优惠券");
    $scope.listInfo = [];//定义一个空列表'[]'
  $scope.couponFun=function(type,page){
      $scope.page = page || 1;//如果page为空，就赋值1给他
      $scope.type = type;
      console.log(type);
      $http.post(url+"couponInterfaces.api?getCoupons",$.param({
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token'),
        coupon_state: type,
        page: $scope.page
        
      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data);
        if(data['status']=="ok"){
          $scope.listInfo=data["data"];
        } else if (data['status'] == 'error') {
            console.log(data['status']);
          } else if (data['data'] == 'token failed') {
      }
      });
  }
  $scope.couponFun('not_used');
  
}])

/*我的积分*/
.controller("pointsList",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("我的积分");
  //获取积分数量
  $http.post(url+"memberInterfaces.api?getUserIntegral",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token')
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.infoHTML=data['data'];
        }
      });
    $scope.p=0;
    //积分记录
    $scope.pointsFun=function(type){
      $scope.type=type;
      if($scope.type=='add'){
        $scope.p=1;
      }else if($scope.type=='use'){
        $scope.p=2;
      }
    $http.post(url+"memberInterfaces.api?getIntegralGetRecord",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          state: type
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.listInfo=data['data'];
        }
      });
    }
    $scope.pointsFun('add');
    //积分使用规则
    $scope.pointsRuleFun=function(){
      $scope.p=3;
      $http.post(url+"memberInterfaces.api?getIntegralGetRecord",$.param({
          url: '/html/others/integral_rule.html'
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.listInfo=data['data'];
        }
      });
    }
}])


/*个人中心*/
.controller("personalCenter",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
    console.log("个人中心");
  /*获取用户资料*/
    $http.post(url+"memberInterfaces.api?getMemberDetail",$.param({
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token')
    }), 
    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
          $scope.userInfo1=data['data'];
          $scope.sign=$scope.userInfo1.is_sign;
      }
    });
  
    $scope.type=0; //控制签到成功遮罩变量
  $scope.none1=function(d){
    $scope.type=d;
  };
  //控制签到按钮样式
  $scope.signs = function(){
    $scope.sign='1';
  };
    /*签到*/
    $scope.signFun=function(){
        $http.post(url+"signInterfaces.api?insertSign",$.param({
            member_id: $cookieStore.get('member_id'),
            member_token: $cookieStore.get('member_token')
        }), 
        {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
        ).success(function(data) {
            console.log(data);
            if(data['status']=="ok"){
                $scope.signInfo=data['data'];
                console.log($scope.signInfo);
                $scope.none1(1); //控制签到成功遮罩
            $scope.signs();
            }
            else{
                console.log($scope.signInfo)
            }
        });
    };
  //暴露给app的方法
  $scope.appGoPoints = function () { //我的积分
    $scope.APPurl=url+'app-mall1/index1.html#/pointsList?&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
    var u = navigator.userAgent;
    if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
        JavaScript:android.appGoPoints();
    } else
    if (u.indexOf('iPhone') > -1) {//苹果手机
        appGoPoints();
    }
  }

  $scope.appGoCoupon = function () { //我的优惠券
    $scope.APPurl1=url+'app-mall1/index1.html#/couponList?&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
    var u = navigator.userAgent;
    if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
        JavaScript:android.appGoCoupon();
    } else
    if (u.indexOf('iPhone') > -1) {//苹果手机
        appGoCoupon();
    }
  }
  
  $scope.appGoAddress = function () { //我的地址
    $scope.APPurl2=url+'app-mall1/index1.html#/addressList?&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
    var u = navigator.userAgent;
    if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
        JavaScript:android.appGoAddress();
    } else
    if (u.indexOf('iPhone') > -1) {//苹果手机
        appGoAddress();
    }
  }
  
  $scope.appGoCollection = function () { //我的收藏
    $scope.APPurl3=url+'app-mall1/index1.html#/collectionList?&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
    var u = navigator.userAgent;
    if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
        JavaScript:android.appGoCollection();
    } else
    if (u.indexOf('iPhone') > -1) {//苹果手机
        appGoCollection();
    }
  }
  
  $scope.appJumpCall = function () { //我的客服
    //$scope.APPurl=url+'app-mall1/index1.html#/pointsList?&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
    var u = navigator.userAgent;
    if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
        JavaScript:android.appJumpCall();
    } else
    if (u.indexOf('iPhone') > -1) {//苹果手机
        appJumpCall();
    }
  }
  
  $scope.appGoFeedback = function () { //我的反馈
    $scope.APPurl4=url+'app-mall1/index1.html#/feedback?&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
    var u = navigator.userAgent;
    if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
        JavaScript:android.appGoFeedback($scope.APPurl4);
    } else
    if (u.indexOf('iPhone') > -1) {//苹果手机
        appGoFeedback($scope.APPurl4);
    }
  }
  
  
  


}])

.controller("feedback",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("意见反馈");
  $scope.content={
    text:'',
    phone:''
  }
  $scope.laoding=0;//等待遮罩变量  默认0隐藏，1显示
  $scope.laoding1=0;//等待还是成功变量 默认0等待，1成功
  $scope.laodingFun = function(number,number1){
    $scope.laoding=number;
    $scope.laoding1=number1;
  }
  $scope.ofFun = function(){
    if($scope.content.text==''){

    }else{
      $scope.feedbackFun();
    }
  }
  $scope.feedbackFun = function(){
    $scope.laodingFun(1,0);
    $http.post(url+"memberInterfaces.api?addAdvice",$.param({
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token'),
      advice_content: $scope.content.text+$scope.content.phone
      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data);
        if(data['status']=="ok"){
          $scope.laodingFun(1,1);
          $timeout(function(){
          $location.path('personalCenter');
        },1500);
        }
    }); 
  }

}])

