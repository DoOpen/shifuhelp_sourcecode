

app.controller("mainCtrl", ['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce','$window', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce,$window) {
	console.log("主控制器");

	/*获取用户资料*/
  $scope.userInfoFun=function(){
		$http.post(url+"memberInterfaces.api?getMemberDetail",$.param({
      	member_id: $cookieStore.get('member_id'),
      	member_token: $cookieStore.get('member_token')
    }), 
    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
	).success(function(data) {
    	console.log(data)
    	if(data['status']=="ok"){
       		$scope.userInfo=data['data'];
          $scope.uphone=$scope.userInfo.member_phone; //存放用户手机号码
    	} else if (data['status'] == 'error') {
          console.log(data);
      } else if (data["status"] == "pending") {
          $scope.login(); //登入失效
      }
  	});
	}
  $scope.userInfoFun();

  /*获取cookie*/
  $scope.getCookie = function(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
      return unescape(arr[2]);
    }else {
      return null;
    }
  } 
  /*登入失效执行的方法*/
  $scope.login = function(){ 
    $cookieStore.put('a_url',window.location.href,{path: "/"});
    window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx76adf3e3a6e3e471&redirect_uri=http%3a%2f%2fwx.shifuhelp.com%2fwx_login.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
  
  }
  if ($cookies.get("user_info")) {
    $scope.member =JSON.parse($scope.getCookie("user_info"));
    //console.log($scope.member);
    $cookieStore.put('member_id', $scope.member.member_id);
    $cookieStore.put('member_token', $scope.member.member_token);
    $scope.userInfoFun();
  } else {
    $cookieStore.put('a_url',window.location.href,{path: "/"});
    window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx76adf3e3a6e3e471&redirect_uri=http%3a%2f%2fwx.shifuhelp.com%2fwx_login.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
  
  }
  
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
	//             	   $cookieStore.put('wxmember_name',$scope.userInfo.member_nick_name);//用户微信昵称
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
  $http.post(url+"orderInterfaces.api?getRefundOrderList",$.param({
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
  $scope.userInfoFun=function(){
    $http.post(url+"memberInterfaces.api?getMemberDetail",$.param({
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token')
    }), 
    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
          $scope.userInfo=data['data'];
          $scope.refundCode=$scope.userInfo.member_bank_code;//获取用户付款账号
      } else if (data['status'] == 'error') {
          console.log(data);
      } else if (data["status"] == "pending") {
          $scope.login(); //登入失效
      }
    });
  }
  $scope.userInfoFun();
  console.log($scope.rid);
  //获得售后订单详情
  $http.post(url+"orderInterfaces.api?getRefundOrderDetail",$.param({
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
  // $http.post(url+"orderInterfaces.api?getOrderDetail",$.param({
  //         member_id: $cookieStore.get('member_id'),
  //         member_token: $cookieStore.get('member_token'),
  //         order_id: $scope.oid
  //     }), 
  //     {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  //   ).success(function(data) {
  //       console.log(data)
  //       if(data['status']=="ok"){
  //           $scope.order=data["data"];
  //       }
  //     });
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
    $http.post(url+"orderInterfaces.api?cancelRefundOrder",$.param({
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
        } else if (data['status'] == 'error') {
          tips2(data['error'], 1500);
        }
      });
  }


}])

.controller("shLogistics",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("提交退货物流");
  $scope.rid=$location.search().rid;
  //获得售后订单详情
  $http.post(url+"memberInterfaces.api?getMemberDetail",$.param({
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
    var time=stringToDate($scope.infoHTML.create_time); //把商家同意退款时间赋值给变量
    $scope.rtime=date.getTime() - time.getTime(); //计算当前时间和商家同意退款时间
    $scope.rday=6 - Math.floor($scope.rtime/(24*3600*1000)); //计算天数

    //计算出小时数
    var leave1=$scope.rtime%(24*3600*1000);   //计算天数后剩余的毫秒数
    $scope.rhour=24 - Math.floor(leave1/(3600*1000)) - 1; //计算时间
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
          $scope.back();
        }
      });
    }
}])
/*退单物流追踪*/
.controller("shLogisticsInfo",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单退货物流");
  $scope.rid=$location.search().rid;
  //获得售后订单详情
  $http.post(url+"memberInterfaces.api?getMemberDetail",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          refund_id: $scope.rid
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.rinfoHTML=data["data"];
            $scope.lFun();
        }
      });
    $scope.arr1;
  //获取退货物流信息
  $scope.lFun = function () {
    $http.post(url+"orderInterfaces.api?getOrderLogisticsDetails",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          logistics_no: $scope.rinfoHTML.logistics_no,
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
  }

}])
.controller("orderEvaluate",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单评价");
  $scope.oid=$location.search().oid;
  //获取订单详情
  $http.post(url+"orderInterfaces.api?getOrderDetail",$.param({
      order_id: $scope.oid,
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token')

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.infoHTML=data['data'];
        $scope.rinfoHTML=data['data']['orderGoodsBeans'];
        $scope.goodsNum=data['data']['orderGoodsBeans'].length; //商品数量
        $scope.fileArr=[];
        $scope.list=[]; //评论数据
        // $scope.pfFun2();
        for(var i=0;i<$scope.goodsNum;i++){
          $scope.fileArr[i]=[];
          $scope.list[i]={};
          $scope.pfFun1([i],5);
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
  //   goods_id:'',
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
        $scope.list[i].goods_id=$scope.rinfoHTML[i].goods_id;
        $scope.list[i].assessment_img1 = $scope.fileArr[i][0];
        $scope.list[i].assessment_img2 = $scope.fileArr[i][1];
        $scope.list[i].assessment_img3 = $scope.fileArr[i][2];
        // for (var j = 0; j < $scope.fileArr[i].length ; j++) {
        //   $scope.imgs = {};
        //   $scope.imgs.assessment_img = $scope.fileArr[i][j];
        //   $scope.list[i].assessmentImgBeans.push($scope.imgs);
        // }
        $scope.list[i].assessment_star2=$scope.evaluate.logistics;
        $scope.list[i].assessment_star3=$scope.evaluate.quality;
      
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
    $scope.pf1=id ? id : $scope.evaluate.goods;
    for(var i=0;i<$scope.goodsNum;i++){
      if(list==i){
        $scope.list[i].assessment_star1=$scope.pf1;
      }
    }
  }
  $scope.pfFun2 = function () {
    for(var i=0;i<$scope.goodsNum;i++){
      $scope.pfFun1(i,5);
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
    $scope.fileArr = {};
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
            url: url + "settingInterfaces.api?uploadImgs",
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
                    $scope.fileArr[$formIndex].unshift(d["data"][i]);

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
  $scope.listObj = {
    list:{}
  };
  $scope.orderEvaluateFun = function(){
    $scope.g();//调用方法，把评论详情存放进$scope.list
    $scope.laodingFun(1,0);
    console.log($scope.list);
    $scope.listObj.list = $scope.list;
    console.log($scope.listObj);
    console.log(JSON.stringify($scope.listObj.list))
    $http.post(url+"orderInterfaces.api?assessmentOrder",$.param({
        json: JSON.stringify($scope.list),
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token')

        }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
      ).success(function(data) {
        console.log(data)
        $scope.laodingFun(0);
        if(data['status']=="ok"){
          $scope.tips2(data['data'], 1300);
          $timeout(function(){
            $location.path('order');
          },1500);
        } else if (data['status'] == 'error') {
          $scope.tips2(data['error'], 1300)
        }
      });
  }

}])

/*订单物流追踪*/
.controller("orderLogistics",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单物流");
  $scope.oid=$location.search().oid;
  //获取订单详情
  $http.post(url+"orderInterfaces.api?receiveOrder",$.param({
      order_id: $scope.oid,
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token')

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
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
        console.log(data)
        if(data['status']=="ok"){
          $scope.infoHTML=data['data'];
          $scope.listInfo=data['data']['logisticsDetailBeans'];
        }
      });
}])

/*我的订单列表*/
.controller("order",['$scope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单列表");
  $scope.tips='';
  $scope.listInfo = [];//定义一个空列表'[]'
  $scope.orderFun1 = function(type,page){
      $("#loading").fadeIn();
      $scope.page = page || 1;//如果page为空，就赋值1给他
      console.log($scope.page);
      $scope.type = type;
      $http.post(url+"orderInterfaces.api?getOrderList",$.param({
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token'),
        order_state: type,
        page: $scope.page
      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
          $("#loading").fadeOut();
          //$scope.listInfo = [];//初始化，把列表的值为空
          $scope.allPage = Math.ceil(data["total"] /10) == 0 ? '1' : Math.ceil(data["total"] / 10);
          $scope.listInfo.push.apply($scope.listInfo, data["data"])
          /*for (var i = 0, len = data["data"].length; i < len; i++) {
                        $scope.listInfo.push(data["data"][i]);
                        --js原生写法--利用push方法把list数组添加数据
                    }*/
          if($scope.allPage==$scope.page){
            $scope.tips='没有数据了';
          }else{
            $scope.tips='下拉刷新'
          }
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
          //console.log(wTop);
          bTop = $("body").height();
          dTop = $(document).height();
          if (wTop + bTop >= dTop) { //下拉到底部加载
            if ($scope.allPage > $scope.page) {
              $scope.orderFun1($scope.type,++$scope.page);
          }
        }

        if($(window).scrollTop()!=0){
          sessionStorage.setItem('order',$(window).scrollTop());
          //$scope.scroll = $(document).scrollTop();
          //console.log(sessionStorage.getItem('order'));
        }
     })
    $scope.scrFun = function(){
      var orders = sessionStorage.getItem('order');
      console.log(orders);
        document.documentElement.scrollTop = orders;  
        //window.pageYOffset = orders;  
        document.body.scrollTop = orders;  
    }
    $scope.scrFun();
}])

.controller("orderInfo",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单详情");
  $scope.id=$location.search().id;//获取url订单id
  //$scope.scr1=$location.search().scr 
  //console.log($scope.scr1)
  //获取订单详情
  $scope.orderInfoFun = function(){
    $http.post(url+"orderInterfaces.api?getOrderDetail",$.param({
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token'),
      order_id: $scope.id
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
        $scope.tips2(data['data'],800);
        $scope.back();
      }else{
        $scope.tips2(data['data'],800);
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
    $http.post(url+"orderInterfaces.api?receiveOrder",$.param({
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token'),
      order_id: $scope.id

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data);
      if(data['status']=="ok"){
        $scope.tips2(data['data'],800);
        $scope.confirmFun(0);
        $scope.orderInfoFun();
      }else{
        $scope.tips2(data['data'],800);
      }
    });
  }
  // 支付订单
  $scope.paymentFun = function(order_id){
    //console.log(window.location.href);
    console.log(order_id);
    //window.location.href='http://aksd.tstweiguanjia.com//app-mall/index.html#/pay_finish';
    $.ajax({
      url: url +  "orderInterfaces.api?payRealOrderList", //接口
      method: 'post',
      data: {  //读取内容所需条件
        member_id : $cookieStore.get("member_id"),
        member_token : $cookieStore.get("member_token"),
        order_ids: order_id, //订单ID
        channel: 'wx_pub'    //微信公众号支付
      },
      dataType : "json",
      success: function (data) {
        var charge = data['data'];
        pingpp.createPayment(charge, function (result, error) {
          if (result == "success") { // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的 wap 支付结果都是在 extra 中对应的 URL 跳转。
            $scope.tips2("支付成功",1300);
            $scope.orderInfoFun();
          } else if (result == "fail") {
            $scope.tips2("支付取消",1300);
            $scope.orderInfoFun();
            //charge 不正确或者微信公众账号支付失败时会在此处返回
          } else if (result == "cancel") {
            $scope.tips2("支付失败",1300);
            $scope.orderInfoFun();
          }
        })
      }
    });
  }
  //获取物流信息
  $scope.orderLogisticsFun = function () {
    $http.post("https://m.kuaidi100.com/index_all.html",$.param({
          type: $scope.infoHTML.logistics_pinyin,
          postid: 71262302252905
          //postid: $scope.infoHTML.logistics_no
          //callbackurl: 'order'
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
          //$scope.infoHTML=data['data'];
          //$scope.listInfo=data['data']['logisticsDetailBeans'];
        }
      });
  }
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
  $http.post(url+"orderInterfaces.api?getOrderDetail",$.param({
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
  $http.post(url+"orderInterfaces.api?getRefundReasonList",$.param({
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token')
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
      if($scope.reasonList[i].reason_id==id){
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
    }else if($scope.type==3){
            $scope.refund_type='not_goods';
          }else if($scope.type==4){
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
        $scope.refundMoney=$scope.goodsList[i].specification_price;
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
    }else if(!(/(^[1-9]\d*$)/.test($scope.goods.Num1))){
      $scope.goods.Num1 = 1;
    }
    console.log($scope.goods.Num1)
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
            url: url + "settingInterfaces.api?uploadImgs",
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
    $http.post(url+"orderInterfaces.api?refundOrder",$.param({
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
        $scope.laodingFun(0);
        if (data['status']=="ok") {
          $timeout(function(){
            $location.path('shList');
          },1500);
        }else if (data['status'] == 'error') {
          $scope.tips2(data['error'],1000)
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
  $http.post(url+"collectionInterfaces.api?getCollectionList",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          page: $scope.page
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.listInfo=data["data"];
            $scope.maxLength = data['data'].length;
            console.log($scope.maxLength)
        }
      });
  }
  
  $scope.collectionListFun();
  $scope.onclick = function(index){
    if($("#cid"+index).hasClass("scxz")){
      $("#cid"+index).removeClass("scxz");
    }else{
      $("#cid"+index).addClass("scxz");
    }
    //console.log($scope.maxLength)
    $scope.xznum=0; 
    for(var i=0;i<$scope.maxLength;i++){  //选中加1，用来判断是否全选
      if($("#cid"+[i]).hasClass("scxz")){
        $scope.xznum++;

      }
    }
    if($scope.maxLength==$scope.xznum){ //是否全选
      $(".alls").addClass("scxz");
    }else{
      $(".alls").removeClass("scxz");
    }
    $scope.cidFun();
  }
  
  $scope.qOnclick = function () { //全选
    if($(".alls").hasClass("scxz")){
      $(".alls").removeClass("scxz");
      $(".scList").removeClass("scxz");
    }else{
      $(".alls").addClass("scxz");
      $(".scList").addClass("scxz");
    }
    $scope.cidFun();
  }
  $scope.cids=[]; //全选id
  $scope.cidFun = function(){
    $scope.cids=[];
    $scope.cidLeng=$(".scList").length;
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
      $http.post(url+"collectionInterfaces.api?cancelCollection",$.param({
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
  $http.post(url+"addressInterfaces.api?getMemberAddressList",$.param({
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
    if($scope.adds.name==''||$scope.adds.detailed_address==''||addre1==''){
      // $scope.infoFun(1);
      // $timeout(function(){
      //   $scope.infoFun(0);
      // },2000);
      $scope.tips2('请输入完整地址', 1500)
    } else if (!(/^1[34578]\d{9}$/.test($scope.adds.mobile))) {
      $scope.tips2('请输入正确的手机号码', 1500)
    } else {
      $scope.addressNew();
    }
  }
  //修改地址时判断 
  $scope.infoOf1=function(id){
    $scope.id=id; //存放要修改地址的地址的id
    var addre1=document.getElementById('aaddress').value.split(',');
    if($scope.adds.name==''||$scope.adds.detailed_address==''||addre1==''){
      // $scope.infoFun(1);
      // $timeout(function(){
      //   $scope.infoFun(0);
      // },2000);
      $scope.tips2('请输入完整地址', 1500)
    } else if (!(/^1[34578]\d{9}$/.test($scope.adds.mobile))) {
      $scope.tips2('请输入正确的手机号码', 1500)
    } else {
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
    console.log(addre)
    if(addre.length==2){
      addre[2]=addre[1];
      addre[1]=addre[0];
      // addre[0]="";
    }
    $http.post(url+"addressInterfaces.api?insertUpdateAddress",$.param({
            member_id: $cookieStore.get('member_id'),
            member_token: $cookieStore.get('member_token'),
            address_name: $scope.adds.name,//姓名
            address_mobile: $scope.adds.mobile,//手机
            address_province: addre[0],//地址省份
            address_city: addre[1],//市
            address_district: addre[2]&&addre[2],//区 县
            address_detail: $scope.adds.detailed_address,//详细地址
            is_default: $scope.lens,//是否默认地址
            //address_id: type,//地址标识（1）
            address_zip_code: $scope.adds.zip_code //邮编
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
  $scope.updataAddressFun = function(a_id){
    
    //判断地址直辖市
    var addre=document.getElementById('aaddress').value.split(',');
    if(addre.length==2){
      addre[2]=addre[1];
      addre[1]=addre[0];
      //addre[0]="";
    }
     console.log(addre)
      console.log(addre[2])
      console.log(addre[1])
      console.log(addre[0])
    $http.post(url+"addressInterfaces.api?insertUpdateAddress",$.param({
            member_id: $cookieStore.get('member_id'),
            member_token: $cookieStore.get('member_token'),
            address_name: $scope.adds.name,//姓名
            address_mobile: $scope.adds.mobile,//手机
            address_province: addre[0],//地址省份
            address_city: addre[1],//市
            address_district: addre[2]&&addre[2],//区 县
            address_detail: $scope.adds.detailed_address,//详细地址
            is_default: $scope.lens,//是否默认地址
            address_id: a_id,//地址标识（1）
            address_zip_code: $scope.adds.zip_code //邮编
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
.controller("addressNew",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  
}])
/*我的优惠券*/
.controller("couponList",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("我的优惠券");
    $scope.listInfo = [];//定义一个空列表'[]'
  $scope.couponFun=function(type,page){
      $scope.page = page || 1;//如果page为空，就赋值1给他
      $scope.type = type;
      console.log(type);
      $http.post(url+"couponInterfaces.api?getCouponList",$.param({
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
  $scope.listInfo=[];
  $scope.pointsListFun = function(type,page){  //积分获取记录
    $scope.type=type;
      if($scope.type=='add'){
        $scope.p=1;
      }else if($scope.type=='use'){
        $scope.p=2;
      } 
    $scope.page=page || 1;
    $http.post(url+"memberInterfaces.api?getIntegralGetRecord",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          state: type,
          page: $scope.page
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            // $scope.listInfo = [];//初始化，把列表的值为空
            $scope.allPage = Math.ceil(data["total"] /10) == 0 ? '1' : Math.ceil(data["total"] / 10);
            $scope.listInfo.push.apply($scope.listInfo, data["data"])
            /*for (var i = 0, len = data["data"].length; i < len; i++) {
                          $scope.listInfo.push(data["data"][i]);
                          --js原生写法--利用push方法把list数组添加数据
                      }*/
        }
      });
    }
  
  $scope.pointsListFun1 = function(type){
    $scope.listInfo=[];
    $scope.pointsListFun(type);
  }
  $scope.pointsListFun1('add');
  // 积分使用规则
  $scope.pointsRuleFun = function(){
    $http.post(url+"settingInterfaces.api?getHtmlDetail",$.param({
        html_name: '积分使用规则'
    }), 
    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
          $scope.urlDetail=data['data'];
      }
    });
  }
  $scope.pointsRuleFun();
    /*滚动加载*/
    $(window).scroll(function() {
        var wTop = null,
          bTop = null,
          dTop = null;
          wTop = $(window).scrollTop();
          //$scope.windowLeng = $(window).scrollTop();
          bTop = $("body").height();
          dTop = $(document).height();
          if (wTop + bTop >= dTop) { //下拉到底部加载
            if ($scope.allPage > $scope.page) {
              $scope.pointsListFun($scope.type,++$scope.page);
          }
        }
      })
}])

/*订单消息列表*/
.controller("ddNews",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("订单消息");
  $http.post(url+"memberInterfaces.api?getMemberMsgList",$.param({
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token'),
      msg_type: 'order'
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  ).success(function(data) {
      console.log(data);
        if(data['status']=="ok"){
          $scope.listInfo=data['data'];      
          
        }  
  });  
  // $http.post(url+"memberInterfaces.api?getMemberMsgs",$.param({
  //     member_id: $cookieStore.get('member_id'),
  //     member_token: $cookieStore.get('member_token')
  //     }), 
  //     {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  // ).success(function(data) {
  //     console.log(data);
  //       if(data['status']=="ok"){
  //         var dd=new Array();
  //         var xt=new Array();
  //         $scope.listInfo=data['data'];      
  //         //$scope.infoHTML=$scope.listInfo[data['total']-1];
  //         for(var i=0;i<$scope.listInfo.length;i++){   //分离订单消息和系统消息
  //            if($scope.listInfo[i]['msg_type']=='order'){
  //               dd.push($scope.listInfo[i]);
  //            }else{
  //               xt.push($scope.listInfo[i]);
  //            }
  //         }
  //         $scope.ddHtml=dd;
  //         console.log($scope.ddHtml);
  //       }  
  //   });  
}])
/*系统消息列表*/
.controller("xtNews",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("系统消息");
  $http.post(url+"memberInterfaces.api?getMemberMsgList",$.param({
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token'),
      msg_type: 'system'
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  ).success(function(data) {
      console.log(data);
        if(data['status']=="ok"){
          $scope.listInfo=data['data'];      
        }  
  });  
  // $http.post(url+"memberInterfaces.api?getMemberMsgs",$.param({
  //     member_id: $cookieStore.get('member_id'),
  //     member_token: $cookieStore.get('member_token')
  //     }), 
  //     {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  // ).success(function(data) {
  //     console.log(data);
  //       if(data['status']=="ok"){
  //         var dd=new Array();
  //         var xt=new Array();
  //         $scope.listInfo=data['data'];
  //         console.log($scope.listInfo[0]['msg_type']);          
  //         //$scope.infoHTML=$scope.listInfo[data['total']-1];
  //         for(var i=0;i<$scope.listInfo.length;i++){
  //            if($scope.listInfo[i]['msg_type']=='order'){
  //               dd.push($scope.listInfo[i]);
  //            }else{
  //               xt.push($scope.listInfo[i]);
  //            }
  //         }
  //         $scope.xtHtml=xt;
  //         console.log($scope.xtHtml);
  //       }  
  //   });  
}])
/*消息*/
.controller("newsList",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("消息");
  //消息
  $http.post(url+"memberInterfaces.api?getMemberMsgList",$.param({   //系统消息
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token'),
      msg_type: 'system'
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  ).success(function(data) {
      console.log(data);
        if(data['status']=="ok"){
          $scope.xtInfo=data['data'][0];      
        }  
  });  

  $http.post(url+"memberInterfaces.api?getMemberMsgList",$.param({   //订单消息
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token'),
      msg_type: 'order'
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  ).success(function(data) {
      console.log(data);
        if(data['status']=="ok"){
          $scope.ddInfo=data['data'][0];      
          
        }  
  });  
  // $http.post(url+"memberInterfaces.api?getMemberMsgs",$.param({
  //     member_id: $cookieStore.get('member_id'),
  //     member_token: $cookieStore.get('member_token')
  //     }), 
  //     {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  // ).success(function(data) {
  //     console.log(data);
  //       if(data['status']=="ok"){
  //         var dd=new Array();
  //         var xt=new Array();
  //         $scope.listInfo=data['data'];
  //         console.log($scope.listInfo[0]['msg_type']);          
  //         //$scope.infoHTML=$scope.listInfo[data['total']-1];
  //         for(var i=0;i<$scope.listInfo.length;i++){
  //            if($scope.listInfo[i]['msg_type']=='order'){
  //               dd.push($scope.listInfo[i]);
  //            }else{
  //               xt.push($scope.listInfo[i]);
  //            }
  //         }
  //         console.log(dd);
  //         console.log(xt);
  //         $scope.ddInfo=dd[dd.length-1];
  //         $scope.xtInfo=xt[xt.length-1];
  //       }  
  //   });
  
}])

/*首页*/
.controller("main",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
    
    $scope.flexsliders=function(dom) { //页面传过来的dom节点
      angular.element(dom).flexslider({
            slideshowSpeed: 3000, //展示时间间隔ms
            animationSpeed: 300, //滚动时间ms
            pauseOnAction: true,
            touch: true //是否支持触屏滑动(比如可用在手机触屏焦点图)
        });
    }
  /*轮播图*/
    $scope.banner=function(){
      $http.post(url+"settingInterfaces.api?getBannerList",$.param({
          banner_position: 'wechat_home',
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token')
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log("首页界面");
          console.log("轮播图");
        console.log(data)
        if(data['status']=="ok"){
            $scope.bannerInfo = data['data'];
        }else if (data["status"] == "pending") {
          $scope.tips2(data['error'],1000);
          $scope.login(); //登入失效
      }
      });
  }
  $timeout(function() {
    $scope.banner();
  }, 20)    
    
   //明星师傅展示
    $scope.starMasterWorker = function(){
      $http.post(url+"memberInterfaces.api?getStarMemberList",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          limit: 30
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log("明星师傅展示")
        console.log(data)
        if(data['status']=="ok"){
            $scope.starInfo=data['data'];
            
        }else if (data["status"] == "pending") {
           $scope.tips2(data['error'],1000);
          $scope.login(); //登入失效
      }
      });
  }
  $timeout(function(){
    $scope.starMasterWorker();
  },20)

    //服务选项
    $http.post(url+"workOrderInterfaces.api?getServiceClassList",$.param({
          class_parent_id: -1
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log("服务选项")
        console.log(data)
        if(data['status']=="ok"){
            $scope.serviceList=data['data'];  
        }
      });
    
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
	    	} else if (data['status'] == 'error') {
          $scope.tips2(data['error'],800);
          console.log(data);
        } else if (data["status"] == "pending") {
            $scope.login(); //登入失效
        }
	  	});
	}
  

  
}])
/*设置*/
.controller("setUp",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  	console.log("设置");
  //获取用户信息
  $http.post(url+"memberInterfaces.api?getMemberDetail",$.param({
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token')
    }), 
    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
          $scope.userInfo1=data['data'];
          /*截取电话,中间四位显示为* */
          $scope.phone=$scope.userInfo1.member_phone.slice(0,3)+"****"+$scope.userInfo1.member_phone.slice(7,11);          
      } else if (data['status'] == 'error') {
          console.log(data);
      } else if (data["status"] == "pending") {
          $scope.login(); //登入失效
      }
    });
  	console.log($scope.userInfo1);
  // html内容
  $scope.pointsRuleFun = function(){
    $http.post(url+"settingInterfaces.api?getHtmlDetail",$.param({
        html_name: '关于我们'
    }), 
    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
          $scope.urlDetail=data['data'];
      }
    });
  }
  $scope.pointsRuleFun();

}])
 /*绑定手机*/
.controller("bindMobile",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce','$interval', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce,$interval) {
  console.log("绑定手机");
  $scope.code3;//存放系统发送的验证码
  $scope.codeOf=2;//用户输入的验证码错误提示的变量，0为错误，1为正确，2不做操作
  //设置时间过期
  var date=new Date(); 
  date.setTime(date.getTime()+10*60*60*1000);  
 	$scope.phone={
    p1:'',//存放用户输入要绑定的手机号
    code:'',//存放用户输入的验证码
    p2:0,//判断用户输入的手机号码是否规则
    of:0 //判断是否绑定成功
  }
  //延迟加载方法，是错误提示3秒后消失
  $scope.a=function(){
    $scope.phone.p2=0;  //手机输入框提示
  }
  $scope.c=function(){
    $scope.codeOf=2;    //验证码输入框提示
  }
  //手机号码重复绑定提示，短息验证码已发送提示
  $scope.pho=0; //0 关闭
  $scope.phoFun = function(id){
    $scope.pho=id; //1 为重复绑定提示 2为验证码已发送提示
  }
  /*发送手机验证码*/
  $scope.fcodefun=function(){

    $http.post(url+"settingInterfaces.api?sendCode",$.param({
        mobile: $scope.phone.p1,
        code_type: 'bind_mobile'
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data);
        if(data['status']=="ok"){
            $scope.djsfun();  //调用60秒倒计时
            $scope.phoFun(2);  //验证码已发送提示
            $scope.code3=data['data'];
           // $cookieStore.put('code1',$scope.infoHTML);//封装系统发送的验证码
        } else if (data['status']=="error") {
          $scope.tips2(data['error'], 1300)
        }
      });

  }
  /*  $scope.code3=$cookieStore.get('code1');//存放系统发送的验证码
  $.cookie('$scope.code3',{expires:date});//设置过期*/

  /*验证码60s倒计时*/
  var second=60;
      timePromise = undefined;
  $scope.paracont = "获取验证码";  
  $scope.paraclass = "getcode";  
  var code4=document.getElementById("codebtn1");
  code4.disabled=false;
  $scope.djsfun = function(){  
  timePromise = $interval(function(){  
          if(second==0){  
            $interval.cancel(timePromise);  
            timePromise = undefined;  
  
            second = 60;  
            $scope.paracont = "重发验证码";  
            $scope.paraclass = "getcode";  
            code4.disabled=false;
          }else{  
            $scope.paracont = second + "秒后可重发";  
            $scope.paraclass = "nocode";
            code4.disabled=true; 
            second--;  
             
          }  
        },1000,70); 
  }
  /*验证手机号*/
  $scope.checkPhone=function(){

    //验证手机号正则表达式
    var phone = document.getElementById('phone').value;
    if(!(/^1[34578]\d{9}$/.test(phone))){ 
      $scope.phone.p2=1;
      $timeout(function(){
        $scope.a();
      },2000);
      //console.log($scope.phone.p2);
    }else{
      $scope.fcodefun(); //手机号码符合规则，发送验证码给用户
      
      $timeout(function(){
        $scope.phoFun(0);  //两秒后关闭提示
      },2000);
    } 
  }
  /*判断验证码是否输入正确*/
  $scope.codefun=function(){
    console.log('用户输入的验证码：'+$scope.phone.code);
    console.log('系统发送的验证码：'+$scope.code3);
    //验证手机号正则表达式
    var phone = document.getElementById('phone').value;
    if(!(/^1[34578]\d{9}$/.test(phone))){ 
      $scope.phone.p2=1;
      $timeout(function(){
        $scope.a();
      },3000);
    
    }else if($scope.phone.code!=$scope.code3||$scope.phone.code==''){
        $scope.codeOf=0;
        $timeout(function(){$scope.c();}, 3000);
    }

      if($scope.phone.code==$scope.code3){
        console.log("OL");
        $scope.codeOf=1;
        $scope.bindMobilefun();
      }
    }
 
  /*绑定手机号码*/
 	$scope.bindMobilefun=function(){
 		$http.post(url+"memberInterfaces.api?updateMemberMobile",$.param({
  		  member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token'),
        code: $scope.phone.code,
        code_type: 'bind_mobile',
        member_phone:$scope.phone.p1

	    }), 
	    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
		).success(function(data) {
        console.log(data);
        if(data['status']=="ok"){
	       		$scope.phone.of=1;
            $scope.tips2("绑定成功",1300);
            $timeout(function(){
              window.location.href="#/setUp";
            },1300)
	    	}else if(data['status']=="error"){
          $scope.tips2(data['error'], 1500)
        }
	  	});
 	}
}])
.controller("updataMobile",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce','$interval', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce,$interval) {
  console.log("更换绑定的手机号码");
  /*获取用户资料*/
    $http.post(url+"memberInterfaces.api?getMemberDetail",$.param({
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token')
    }), 
    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
          $scope.uphone=data['data'].member_phone; //存放用户手机号码
          $scope.phone1=$scope.uphone.slice(0,3)+"****"+$scope.uphone.slice(7,11);
      } else if (data['status'] == 'error') {
          console.log(data);
      } else if (data["status"] == "pending") {
          $scope.login(); //登入失效
      }
    });
  $scope.codeOf=2;//控制错误提示信息的变量，0为错误，1为正确，2不做操作
  $scope.pho=0; //控制短息验证码已发送提示变量
  $scope.prompt; //提示变量  
  $scope.code3;//存放系统发送的验证码
  //设置时间过期
  var date=new Date(); 
  date.setTime(date.getTime()+10*60*60*1000);
  $scope.code2={
    phone:'' //存放用户输入的验证码
  }
  /*验证码60s倒计时*/
  var second=60;
      timePromise = undefined;
  $scope.paracont = "获取验证码";  
  $scope.paraclass = "getcode";  
  var code4=document.getElementById("codebtn");
  code4.disabled=false;
  $scope.djsfun = function(){  
  timePromise = $interval(function(){  
          if(second==0){  
            $interval.cancel(timePromise);  
            timePromise = undefined;  
  
            second = 60;  
            $scope.paracont = "重发验证码";  
            $scope.paraclass = "getcode";  
            code4.disabled=false;
          }else{  
            $scope.paracont = second + "秒后可重发";  
            $scope.paraclass = "nocode";
            code4.disabled=true; 
            second--;  
             
          }  
        },1000,70); 
  }
  /*$scope.djsfun=function(){
    if(date<=0){
      $scope.paracont = "重发验证码";  
      $scope.paraclass = "getcode";  
      $scope.paraevent = true;
    }
    
    if(date==0){
      val.removeAttribute("disabled");
      val.setAttribute('class','getcode');
      date = 60; 
    }else{
      val.setAttribute('disabled',true);
      val.setAttribute('class','nocode');
      val.innerHTML=date+'S 可再获取';
      date--;
    }
    $timeout(function(){$scope.djsfun()},1000);
  }*/
 	/*发送手机验证码*/
 	$scope.updateMobilefun=function(){
 		$http.post(url+"settingInterfaces.api?sendCode",$.param({
  		 	mobile: $scope.uphone,
	      code_type: 'update_mobile'
	    }), 
	    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
		).success(function(data) {
      console.log(data);
	    	if(data['status']=="ok"){
	       		$scope.code3=data['data']; //把系统发送的验证码存进去，进行和用户输入的做判断
            $scope.pho=1; //显示验证码已经发送的提示
            $scope.djsfun(); //倒计时
            $timeout(function(){
              $scope.pho=0;  // 两秒后关闭提示
            },2000); 
            /*$cookieStore.put('code1',$scope.infoHTML);//封装
            $.cookie('$scope.infoHTML',{expires:date});//设置过期*/
	    	}else if(data['status']=="error"){
            $scope.prompt=data['error']; 
            //console.log($scope.prompt);
            $scope.pho=2; //显示错误信息
            //console.log($scope.pho);
            $timeout(function(){
              $scope.pho=0;
            },2000);
        }
	  	});
 	}
  //$scope.code3=$cookieStore.get('code1');
  
  $scope.codefun=function(){

    console.log($scope.code2.phone);
    console.log($scope.code3);
    $scope.c=function(){
      $scope.codeOf=3;
    }
      if($scope.code2.phone!=$scope.code3||$scope.code2.phone==''){
        $scope.codeOf=0;
        $timeout(function(){$scope.c();}, 2000);
    }
      
      if($scope.code2.phone==$scope.code3){
        $scope.codeOf=1;
        $location.path('bindMobile');
      }
    }
}])
.controller("userData",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("个人资料");
  
}])


/*轮播图指令*/
.directive('repeatFinish',function(){
	return {
	    restrict: 'A',
	    repeatFinish : '@',
	    link: function(scope,element,attr){
	        if(scope.$last == true){
	            console.log('ng-repeat文本执行完毕');
	            scope.$eval(attr.repeatFinish);
	        }
	    }
	}
})
.controller("agreement",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("用户协议");
  $http.post(url+"/app/teacher/teacher_details",$.param({
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token')
    }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  )
  .success(function(data) {
    console.log(data)
    if(data['status']=="ok"){

    }
  });

}])
.controller("hydropower",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("服务选项");
  //服务选项
    //获取url的id属性值，angular专用，或者$location.search()['name'];
    $scope.title=$location.search().title;
    var infoh=new Array();
    infoh.push($location.search().title);//储存用户选择的服务信息

	  $http.post(url+"workOrderInterfaces.api?getServiceClasss",$.param({
	      class_parent_id: $location.search().id
	    }), 
	    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
		).success(function(data) {
    	console.log(data)
    	if(data['status']=="ok"){
     		$scope.listInfo1=data['data'];
        $scope.hydropower2($scope.listInfo1[0].class_id);
        infoh.push(data['data'].class_name);//储存用户选择的服务信息
    	}
  	});
    $scope.hydropower2=function(type){
      $scope.type=type;
      $http.post(url+"workOrderInterfaces.api?getServiceClasss",$.param({
          class_parent_id: type,
          level:2
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data)
        if(data['status']=="ok"){
            $scope.listInfo2=data['data'];
            infoh.push($scope.listInfo2.class_name);//储存用户选择的服务信息
        }
      });
    }
    $scope.hied=function(serviceClassBeans){
      $scope.serviceClassBeans = serviceClassBeans;
      console.log($scope.serviceClassBeans);
      infoh.push($scope.serviceClassBeans.class_name);//储存用户选择的服务信息
      $scope.modelBox = 1;
    }
    $scope.hied1=function(){
      $scope.modelBox = 0;
    }
}])
.controller("hydropowerInfo",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("服务选项详情");
    $scope.id=$location.search().id;
    $http.post(url+"workOrderInterfaces.api?getServiceCLassDetail",$.param({
        class_id: $scope.id
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data);
      if(data['status']=="ok"){
        $scope.infoHTML=data['data'];
        $scope.infoHTML1=data['data']['serviceUnit'];
      }
    });

}])

.controller("package",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("套餐详情");
  $http.post(url+"/app/teacher/teacher_details",$.param({
      uid: $cookieStore.get('uid'),
      token: $cookieStore.get('token')
    }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  )
  .success(function(data) {
    console.log(data)
    if(data['status']=="ok"){

    }
  });

}])
.controller("pre",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("填写预约信息");
  $scope.order_class_id = ''; // 服务类别id
  $scope.laoding=0;//等待遮罩变量  默认0隐藏，1显示
  $scope.laoding1=0;//等待还是成功变量 默认0等待，1成功
  $scope.laodingFun = function(number,number1){
    $scope.laoding=number;
    $scope.laoding1=number1;
  }
  //预约信息储存对象
  $scope.obj={
    order_name: "",  //预约姓名
    order_phone: "",  //电话
    order_address_detail: "",   //详细地址
    order_subscribe_note: "",   //备注  可不填
    work_area: "",  //施工面积 
    work_requirements: "",   //施工要求 
    recommend_phone: "",   //推荐人手机号
    order_subscribe_content: "",  //服务信息
    order_hope_service_time: "",   // 期望服务时间
    hope_complete_time: "",   // 期望完工时间
    service_class_price: ''   //单价

  }
  $scope.serviceListFun = function (d) {  //根据服务类别id获取类别下面的分类
    $http.post(url+"workOrderInterfaces.api?getServiceClassList",$.param({
        class_parent_id: d
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.listInfo = data['data'];
        $scope.serviceListFun_1(data['data'][0].class_id);
        $scope.myValue = data['data'][0].class_id;
      }
    });
  }
  $scope.serviceListFun($location.search().id);

  $scope.serviceListFun_1 = function (d) {  //根据服务类别id获取类别下面的分类
    $http.post(url+"workOrderInterfaces.api?getServiceClassList",$.param({
        class_parent_id: d
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.listInfo_1 = data['data'];
        $scope.order_class_id = data['data'][0].class_id;
        $scope.serviceInfoFun($scope.order_class_id);
        console.log($scope.order_class_id)
      }
    });
  }

  //获取服务信息
  $scope.serviceInfoFun = function (d) {
    $http.post(url+"workOrderInterfaces.api?getContentByServiceClass",$.param({
        class_id: d
      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
      ).success(function(data) {
      console.log("服务信息")
      console.log(data)
      if(data['status']=="ok"){
        $scope.obj.order_subscribe_content=data['data']; // 服务信息
      }
    });
  }
  

  $scope.modeFun = function(){
    angular.element("#phone").css("ime-mode","disabled");
  }
  //选择地址
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
  area1.value=[27,10,6];//控制初始位置，注意：该方法并不会影响到input的value
  //获取选中地址信息
  
  
  //选择期望开工时间
  var calendar = new datePicker();
  $scope.Dates = new Date();  //当前时间
  $scope.intMonth = parseInt($scope.Dates.getMonth())+1

  $scope.completeTime = $scope.Dates.getFullYear() +'-'+ $scope.intMonth +'-'+ $scope.Dates.getDate();  //当前时间

  console.log($scope.completeTime)
  calendar.init({
    'trigger': '#time_select1', /*按钮选择器，用于触发弹出插件*/
    'type': 'date',/*模式：date日期；datetime日期时间；time时间；ym年月；*/
    'minDate': $scope.completeTime,/*最小日期*/
    'maxDate':'2100-12-31',/*最大日期*/
    'onSubmit':function(){/*确认时触发事件*/
      $scope.obj.order_hope_service_time=calendar.value; // 预约服务时间
      $scope.calendar2Fun();   
    },
    'onClose':function(){/*取消时触发事件*/

    }
  });
  $scope.calendar2Fun = function () {  //传入上门时间
    var calendar2 = new datePicker();
    calendar2.init({
      'trigger': '#time_select2', /*按钮选择器，用于触发弹出插件*/
      'type': 'date',/*模式：date日期；datetime日期时间；time时间；ym年月；*/
      'minDate': $scope.obj.order_hope_service_time,/*最小日期*/
      'maxDate':'2100-12-31',/*最大日期*/
      'onSubmit':function(){/*确认时触发事件*/
        $scope.obj.hope_complete_time = calendar2.value;
        return
      },
      'onClose':function(){/*取消时触发事件*/
        return
      }
    });
  }
  

  $scope.f=1;//控制上传图片按钮消失 0不消失 1消失
  $scope.fFun = function(id){
    $scope.f=id;
  }

  //图片上传
    $scope.fileUrl = {
      'fileImgSrcArr':[] //用来放置要显示原图片的Src
    }
    // if(fileLen==3){
    //       $scope.fFun(0);
    //     }else{
    //       $scope.fFun(1);
    // }
    
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
        if(len+fileLen==3){
          angular.element("#fileUrl").css("display",'none');
        }
        $scope.laoding=1;
        $http({
            method: 'POST',
            url: url + "settingInterfaces.api?uploadImgs",
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
                    angular.element("#fileUrl").css("display",'block');
                    var $formIndex = angular.element(this).parents("form").index(),//找到元素祖先form的下标
                         index = angular.element(this).parent().index()-1;//每个评价图片的下标
                         // file = angular.element(this).parents().find("input[type=file]");//找到同一个form下的file
                    // file.splice(index,1)//删除files下下标为index的file(似乎没效果放弃)
                    console.log(index);
                    $scope.fileUrl.fileImgSrcArr.splice(index,1);
                    angular.element(this).parent().remove();//删除自己
                    console.log($scope.fileUrl.fileImgSrcArr);
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

  //提交预约信息
  $scope.prefun = function(type){  //type为0时保存，其他下单
    $scope.laodingFun(1,0);
    var addre=document.getElementById('aaddress').value.split(',');
    //判断地址直辖市
    var addre=document.getElementById('aaddress').value.split(',');
    if(addre.length==2){
      addre[2]=addre[1];
      addre[1]=addre[0];
      addre[0]=addre[0];
    }
    $http.post(url+"workOrderInterfaces.api?createWorkOrder",$.param({
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token'),
        order_name: $scope.obj.order_name,  //预约姓名
        order_phone: $scope.obj.order_phone, //电话
        order_subscribe_note: $scope.obj.order_subscribe_note, //备注 可不填
        order_subscribe_img1: $scope.fileUrl.fileImgSrcArr[0], //图片1 可不填
        order_subscribe_img2: $scope.fileUrl.fileImgSrcArr[1], //
        order_subscribe_img3: $scope.fileUrl.fileImgSrcArr[2], //
        order_address_detail: $scope.obj.order_address_detail, //详细地址
        order_type: 0,
        order_address_province: addre[0], //省
        order_address_city: addre[1], //市
        order_address_district: addre[2], //区
        order_subscribe_content: $scope.obj.order_subscribe_content, //服务内容
        order_class_id: $scope.order_class_id, //服务类型id
        work_way: $scope.myValueText, //施工方式（文字形式）
        order_hope_service_time: $scope.obj.order_hope_service_time,  // 服务时间
        hope_complete_time: $scope.obj.hope_complete_time,  //期望完工时间
        work_area: $scope.obj.work_area,  //施工面积 
        work_requirements: $scope.obj.work_requirements,   //施工要求 
        recommend_phone: $scope.obj.recommend_phone,   //推荐人手机号
        service_class_price: $scope.obj.service_class_price  //单价
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data);
      $scope.laodingFun(0); //关闭等待遮罩
      if(data['status']=="ok"){
        $scope.gid=data['data']; //预约成功后返回工单号
        console.log(data['data'])
        if(type != 0) {
          $scope.payFun(1); // 打开支付定金确认弹窗
        }else {
          $scope.tips2('保存成功，在我的预约-待付款中查看', 2500)
        }
        // $timeout(function(){
        //   $location.path('pendingAudit');
        // },1500);
        $scope.preobj1=1;
      }else if (data['status'] == 'error'){
        $scope.tips2(data['error'],2000);
      }
    });
    console.log(addre[2]);
  }
  $scope.pay=0;
  $scope.payFun = function(d){
    $scope.pay=d;
  }
  $scope.payFun1 = function(){ //取消支付押金事件 >跳到待支付界面
    $location.path('pendingAudit');
  }
  //缴纳30定金
  $scope.paymentFun = function(){
    console.log($scope.gid);
    //window.location.href='http://aksd.tstweiguanjia.com//app-mall/index.html#/pay_finish';
    $.ajax({
      url: url +  "memberInterfaces.api?payWorkOrderDeposit", //接口
      method: 'post',
      data: {  //读取内容所需条件
        member_id : $cookieStore.get("member_id"),
        member_token : $cookieStore.get("member_token"),
        order_id : $scope.gid,
        channel: 'wx_pub'    //微信公众号支付
      },
      dataType : "json",
      success: function (data) {
        var charge = data['data'];
        $scope.payFun(0);
        console.log(charge);
        pingpp.createPayment(charge, function (result, error) {
          console.log(result);
          console.log(error);

          if (result == "success") { // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的 wap 支付结果都是在 extra 中对应的 URL 跳转。
            $scope.tips2("预约成功",800);
            $timeout(function () {
              $location.path('pendingAudit');
            },600);
            //  window.location.href="app-mall/#/pay_finish?&order_id="+ order_id;
          } else if (result == "fail") {
            $scope.tips2("支付取消",1300);
            $scope.payFun(0);
            //window.location.href="#/orderInfo?&id="+ order_id;
            // charge 不正确或者微信公众账号支付失败时会在此处返回
          } else if (result == "cancel") {
            $scope.tips2("支付失败",1300);
            $scope.payFun(0);
            //window.location.href="#/orderInfo?&id="+ order_id;
          }
        })
      }
    });
  }
  // var xxdz=/([\u4e00-\u9fa5a-zA-Z]+[0-9]*)/;
  // var xxdzzz=xxdz.test($scope.obj.order_address_detail);
  //判断预约信息是否填入完整
  $scope.preobj=0;
  $scope.preobj1=0;
  $scope.preof=function(type){

    var addre1=document.getElementById('aaddress').value.split(',');
    $scope.myValueText = ''; //存储是清工还是包工包料
    for (var i = 0; i < $scope.listInfo.length; i++) {
      if ($scope.listInfo[i].class_id == $scope.myValue) {
        $scope.myValueText = $scope.listInfo[i].class_name;
      }
    }
    for (var i = 0; i < $scope.listInfo_1.length; i++) {  //判断选中类别的价格
      if ($scope.listInfo_1[i].class_id == $scope.order_class_id) {
        $scope.obj.service_class_price = $scope.listInfo_1[i].class_price;
      }
    }
    console.log($scope.obj.service_class_price)
    console.log($scope.obj.order_name+' '+$scope.obj.order_phone+' '+addre1+' '+$scope.obj.order_address_detail+' '
    +$scope.myValueText+' '+$scope.obj.work_area+' '+$scope.obj.work_requirements+' '+$scope.obj.order_subscribe_note+' '+$scope.obj.recommend_phone+' '
    +$scope.obj.order_hope_service_time+' '+$scope.obj.hope_complete_time);
    

    if($scope.obj.order_name=='' || $scope.obj.order_phone=='' || $scope.obj.order_address_detail=='' || addre1=='' 
      || $scope.obj.order_hope_service_time=='' || $scope.obj.work_area=='' || $scope.obj.hope_complete_time==''){
      $scope.preobj=1;
    }else if (!(/^[\u4e00-\u9fa5]+$/.test($scope.obj.order_name))) {
      $scope.tips2('请输入纯中文姓名', 1500)
    }else if (!(/^1[34578]\d{9}$/.test($scope.obj.order_phone))) {
      $scope.tips2('请输入正确的手机号码', 1500)
    }else if (!(/(^[1-9]\d*$)/.test($scope.obj.work_area))) {
      $scope.tips2('请输入整数平方', 1500)
    }else{
      //$scope.payFun(1);
      $scope.prefun(type);
    }
  }
  $scope.none=function(){
    $scope.preobj=0;
    $scope.preobj1=0;
  }
}])

.controller("preRepair",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("填写预约信息（其它）");
  $scope.order_class_id = ""; // 服务类别id
  $scope.order_class_id1 = "";   //服务类别id1，没有四级就用这个
  $scope.laoding=0;//等待遮罩变量  默认0隐藏，1显示
  $scope.laoding1=0;//等待还是成功变量 默认0等待，1成功
  $scope.laodingFun = function(number,number1){
    $scope.laoding=number;
    $scope.laoding1=number1;
  }
  //预约信息储存对象
  $scope.obj={
    order_name: "",  //预约姓名
    order_phone: "",  //电话
    order_address_detail: "",   //详细地址
    order_subscribe_note: "",   //备注  可不填
    order_subscribe_content: "",  //服务信息
    order_hope_service_time: "",   // 期望服务时间
    service_class_price: '',  //单价

  }
  $scope.serviceListFun = function (d) {  //根据服务类别id获取类别下面的分类
    $http.post(url+"workOrderInterfaces.api?getServiceClassList",$.param({
        class_parent_id: d
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.listInfo = data['data'];
        $scope.serviceListFun_1(data['data'][0].class_id);
        $scope.serviceListText = data['data'][0].class_id;
      }
    });
  }
  $scope.serviceListFun($location.search().id);

  $scope.serviceListFun_1 = function (d) {  //根据服务类别id获取类别下面的分类
    $http.post(url+"workOrderInterfaces.api?getServiceClassList",$.param({
        class_parent_id: d
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.listInfo_1 = data['data'];
        $scope.serviceListText_1 = data['data'][0].class_id;
        $scope.listPrice = 
        $scope.serviceListFun_2(data['data'][0].class_id);
      }
    });
  }
  $scope.serviceListFun_2 = function (d) {  //根据服务类别id获取类别下面的分类
    $http.post(url+"workOrderInterfaces.api?getServiceClassList",$.param({
        class_parent_id: d
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.listInfo_2 = data['data'];
        if (data['data'].length) {
          $scope.order_class_id = data['data'][0].class_id;
        } else {
          $scope.order_class_id = $scope.serviceListText_1;
        }
        $scope.serviceInfoFun($scope.order_class_id);
      }
    });
  }
  //获取服务信息
  $scope.serviceInfoFun = function (d) {
    $http.post(url+"workOrderInterfaces.api?getContentByServiceClass",$.param({
        class_id: d
      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
      ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.obj.order_subscribe_content=data['data']; // 服务信息
      }
    });
  }

  $scope.modeFun = function(){
    angular.element("#phone").css("ime-mode","disabled");
  }
  //选择地址
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
  area1.value=[27,10,6];//控制初始位置，注意：该方法并不会影响到input的value
  //获取选中地址信息
  
  
  //选择期望上门时间
  var calendar = new datePicker();
  $scope.Dates = new Date();  //当前时间
  $scope.intMonth = parseInt($scope.Dates.getMonth())+1

  $scope.completeTime = $scope.Dates.getFullYear() +'-'+ $scope.intMonth +'-'+ $scope.Dates.getDate();  //当前时间

  console.log($scope.completeTime)
  calendar.init({
    'trigger': '#time_select1', /*按钮选择器，用于触发弹出插件*/
    'type': 'date',/*模式：date日期；datetime日期时间；time时间；ym年月；*/
    'minDate': $scope.completeTime,/*最小日期*/
    'maxDate':'2100-12-31',/*最大日期*/
    'onSubmit':function(){/*确认时触发事件*/
      $scope.obj.order_hope_service_time=calendar.value; // 预约服务时间

    },
    'onClose':function(){/*取消时触发事件*/
    }
  });

  $scope.f=1;//控制上传图片按钮消失 0不消失 1消失
  $scope.fFun = function(id){
    $scope.f=id;
  }

 //图片上传
  $scope.fileUrl = {
    'fileImgSrcArr':[] //用来放置要显示原图片的Src
  }
  // if(fileLen==3){
  //       $scope.fFun(0);
  //     }else{
  //       $scope.fFun(1);
  // }
  
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
      if(len+fileLen==3){
        angular.element("#fileUrl").css("display",'none');
      }
      $scope.laoding=1;
      $http({
          method: 'POST',
          url: url + "settingInterfaces.api?uploadImgs",
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
                  angular.element("#fileUrl").css("display",'block');
                  var $formIndex = angular.element(this).parents("form").index(),//找到元素祖先form的下标
                       index = angular.element(this).parent().index()-1;//每个评价图片的下标
                       // file = angular.element(this).parents().find("input[type=file]");//找到同一个form下的file
                  // file.splice(index,1)//删除files下下标为index的file(似乎没效果放弃)
                  console.log(index);
                  $scope.fileUrl.fileImgSrcArr.splice(index,1);
                  angular.element(this).parent().remove();//删除自己
                  console.log($scope.fileUrl.fileImgSrcArr);
                  // $scope.fileArr[$formIndex].splice(index,1);//删除下标下的file元素
              })

          } else {
            $scope.laoding=0;
            $scope.tips2('图片不能大于2M', 1200)
            $scope.diType = false;
          }
      }).error(function (err, status) {
          $scope.laoding=0;
          $scope.tips2('图片不能大于2M', 1200)
          $scope.diType = false;
          //console.log(err);
      });
  }
   
  //提交预约信息
  $scope.prefun=function(){
    $scope.laodingFun(1,0);
    var addre=document.getElementById('aaddress').value.split(',');
    //判断地址直辖市
    var addre=document.getElementById('aaddress').value.split(',');
    if(addre.length==2){
      addre[2]=addre[1];
      addre[1]=addre[0];
      addre[0]=addre[0];
    }
    $http.post(url+"workOrderInterfaces.api?createWorkOrder",$.param({
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token'),
        order_name: $scope.obj.order_name,  //预约姓名
        order_phone: $scope.obj.order_phone, //电话
        order_subscribe_note: $scope.obj.order_subscribe_note, //备注 可不填
        order_subscribe_img1: $scope.fileUrl.fileImgSrcArr[0], //图片1 可不填
        order_subscribe_img2: $scope.fileUrl.fileImgSrcArr[1], //
        order_subscribe_img3: $scope.fileUrl.fileImgSrcArr[2], //
        order_address_detail: $scope.obj.order_address_detail, //详细地址
        order_address_province: addre[0], //省
        order_address_city: addre[1], //市
        order_address_district: addre[2], //区
        order_subscribe_content: $scope.obj.order_subscribe_content, //服务内容
        order_class_id: $scope.order_class_id, //服务类型id
        order_hope_service_time: $scope.obj.order_hope_service_time,  // 服务时间
        order_type: 1,  //类型
        service_class_price: $scope.obj.service_class_price  //单价
      }), 
      {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data);
      $scope.laodingFun(0); //关闭等待遮罩
      if(data['status']=="ok"){
        $scope.gid=data['data']; //预约成功后返回工单号
        console.log(data['data'])
        $scope.payFun(1); // 打开支付定金确认弹窗
        // $timeout(function(){
        //   $location.path('pendingAudit');
        // },1500);
        $scope.preobj1=1;
      }else if (data['status'] == 'error'){
        $scope.tips2(data['error'], 1500);
      }
    });
    console.log(addre[2]);
  }
  $scope.pay=0;
  $scope.payFun = function(d){
    $scope.pay=d;
  }
  $scope.payFun1 = function(){ //取消支付押金事件 >跳到待支付界面
    $location.path('pendingAudit');
  }
  //缴纳30定金
  $scope.paymentFun = function(){
    console.log($scope.gid);
    //window.location.href='http://aksd.tstweiguanjia.com//app-mall/index.html#/pay_finish';
    $.ajax({
      url: url +  "memberInterfaces.api?payWorkOrderDeposit", //接口
      method: 'post',
      data: {  //读取内容所需条件
        member_id : $cookieStore.get("member_id"),
        member_token : $cookieStore.get("member_token"),
        order_id : $scope.gid,
        channel: 'wx_pub'    //微信公众号支付
      },
      dataType : "json",
      success: function (data) {
        var charge = data['data'];
        console.log(charge);
        $scope.payFun(0);
        pingpp.createPayment(charge, function (result, error) {
          console.log(result);
          console.log(error);
          if (result == "success") { // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的 wap 支付结果都是在 extra 中对应的 URL 跳转。
            $scope.tips2("预约成功",800);
            $timeout(function () {
              $location.path('pendingAudit');
            },600);
          } else if (result == "fail") {
            $scope.tips2("支付取消",1300);
            $scope.payFun(0);
          } else if (result == "cancel") {
            $scope.tips2("支付失败",1300);
            $scope.payFun(0);
          }
        })
      }
    });
  }
  // var xxdz=/([\u4e00-\u9fa5a-zA-Z]+[0-9]*)/;
  // var xxdzzz=xxdz.test($scope.obj.order_address_detail);
  //判断预约信息是否填入完整
  $scope.preobj=0;
  $scope.preobj1=0;
  $scope.preof=function(){
    var addre1=document.getElementById('aaddress').value.split(',');
    if ($scope.listInfo_2 != '') {
      for (var i = 0; i < $scope.listInfo_2.length; i++) {  //判断选中类别的价格
        if ($scope.listInfo_2[i].class_id == $scope.order_class_id) {
          $scope.obj.service_class_price = $scope.listInfo_2[i].class_price;
        }
      }
    } else {
      for (var i = 0; i < $scope.listInfo_1.length; i++) {  //判断选中类别的价格
        if ($scope.listInfo_1[i].class_id == $scope.order_class_id) {
          $scope.obj.service_class_price = $scope.listInfo_1[i].class_price;
        }
      }
    }
    console.log($scope.obj.service_class_price)
    if($scope.obj.order_name==''||$scope.obj.order_phone==''||$scope.obj.order_address_detail==''||addre1==''||$scope.obj.order_hope_service_time==''){
      $scope.preobj=1;
      
      console.log($scope.obj.order_name+' '+$scope.obj.order_phone+' '+$scope.obj.order_address_detail+' '+addre1+' '
      +$scope.obj.order_hope_service_time+' '+$scope.order_class_id+' '+$scope.obj.order_subscribe_content);
    }else if (!(/^1[34578]\d{9}$/.test($scope.obj.order_phone))) {
      $scope.tips2('请输入正确的手机号码', 1500)
    }else{
      //$scope.payFun(1);
      $scope.prefun();
    }
  }
  $scope.none=function(){
    $scope.preobj=0;
    $scope.preobj1=0;
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
      $scope.tips2('请输入投诉建议内容', 1500)
    }else{
      $scope.feedbackFun();
    }
  }
  $scope.feedbackFun = function(){
    $('loading').fadeIn();
    $http.post(url+"settingInterfaces.api?insertAdvice",$.param({
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token'),
      advice_type: 'advice',
      advice_desc: $scope.content.text,
      advice_mobile: $scope.content.phone
      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data);
        $('loading').fadeOut();
        if(data['status']=="ok"){
          $scope.tips2(data['data'], 1200);
          $timeout(function(){
            $location.path('setUp');
          },1000);
        } else if (data['status']=="error") {
          $scope.tips2(data['error'], 1200)
        }
    }); 
  }

}])


/****************************我的预约start*********************************/
/*我的预约列表*/
.controller("pendingAudit",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  	console.log("预约列表");
  	$scope.listInfo = [];//定义一个空列表'[]'
 	$scope.pendingAudit2=function(type,page){
     $("#loading1").fadeIn();
	  	$scope.page = page || 1;//如果page为空，就赋值1给他
	  	$scope.type = type;
      sessionStorage.setItem('workType', type);
	  	$http.post(url+"workOrderInterfaces.api?getOrderListByState",$.param({
	  		member_id: $cookieStore.get('member_id'),
	      member_token: $cookieStore.get('member_token'),
  	 		type: type,
  	 		page: $scope.page,
  	 		limit:10
	    }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
		).success(function(data) {
	    	console.log(data);
        console.log(type);
	    	if(data['status']=="ok"){
           $("#loading1").fadeOut();
	    		// $scope.listInfo = [];//初始化，把列表的值为空
          $scope.allPage = Math.ceil(data["total"] /10) == 0 ? '1' : Math.ceil(data["total"] / 10);
	    		$scope.listInfo.push.apply($scope.listInfo, data["data"])
	    		/*for (var i = 0, len = data["data"].length; i < len; i++) {
                        $scope.listInfo.push(data["data"][i]);
                        --js原生写法--利用push方法把list数组添加数据
                    }*/
          if($scope.allPage==$scope.page){
            $scope.tips='没有数据啦';
          }else{
            $scope.tips='下拉刷新';
          }
	    	} else if (data['status'] == 'error') {
	          console.log(data['status']);
	        } else if (data['data'] == 'token failed') {
			}
	  	});
	}
  //过度方法，点击其他状态主要是清空
  $scope.pendingAudit = function(type){
    $scope.listInfo = [];
    $scope.pendingAudit2(type);
    sessionStorage.setItem('workType',type)
  }
  $scope.pas1=$location.search().pas1;//获取url的pas属性值，angular专用，或者$location.search()['name'];
  //控制返回列表时是打开那个列表
  if(sessionStorage.getItem('workType')) {
    $scope.pendingAudit(sessionStorage.getItem('workType'))
  } else {
     $scope.pendingAudit('member_subscribe_wait_audit');
  }

  //   if($scope.pas1==1){
  //       console.log(1);
  //     $scope.pendingAudit('member_subscribe_wait_audit');
  //   }else if($scope.pas1==2){
  //     console.log(2);
  //     $scope.pendingAudit('member_release');
  //   }else if($scope.pas1==3){
  //     console.log(3);
  //     $scope.pendingAudit('member_wait_service');
  //   }else if($scope.pas1==4){
  //     console.log(4);
  //     $scope.pendingAudit('member_complete');
  //   }else if($scope.pas1==0){
  //     console.log(0);
  //     $scope.pendingAudit('member_subscribe_wait_pay');
  //   }
  // }else{
  //     $scope.pendingAudit2('member_subscribe_wait_audit');
  //   }
  
  //获取工单各状态的工单数量
  
  $scope.OrderCountFun = function(){
    $http.post(url+"workOrderInterfaces.api?getWorkOrderStateCount",$.param({
      member_type: 0,
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token')

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.orderNum=data['data'];
        
      }
    });
  }
  $scope.OrderCountFun();
  //$scope.windowLeng=0;
	 /*滚动加载*/
    $(window).scroll(function() {
        var wTop = null,
          bTop = null,
          dTop = null;
          wTop = $(window).scrollTop();
          //$scope.windowLeng = $(window).scrollTop();
          bTop = $("body").height();
          dTop = $(document).height();
          if (wTop + bTop >= dTop) { //下拉到底部加载
            if ($scope.allPage > $scope.page) {
              $scope.pendingAudit2($scope.type,++$scope.page);
          }
        }
      })
    
}])



.controller("reservationDetails1",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
	console.log("预约订单详情");
  $scope.laoding=0;//等待遮罩变量

  //获取接单师傅信息
  $scope.worker=function(id){
    $http.post(url+"memberInterfaces.api?getMemberDetail",$.param({
      member_id: id,//把接单师傅的id传进接口

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log('接单师傅信息');
        console.log(data);
        if(data['status']=="ok"){
          $scope.worker=data['data'];
          $scope.goodRateFun();
        }
    }); 
  }
  //判断师傅好评率
  $scope.goodRate=5;
  $scope.goodRateFun = function(){
    if($scope.member_good_rate>=0&&$scope.member_good_rate<='20%'){
      $scope.goodRate=0.5;
    }else if($scope.member_good_rate>'20%'&&$scope.member_good_rateo<='40%'){
      $scope.goodRate=1.5;
    }else if($scope.member_good_rate>'40%'&&$scope.member_good_rate<='60%'){
      $scope.goodRate=2.5;
    }else if($scope.member_good_rate>'60%'&&$scope.member_good_rate<='80%'){
      $scope.goodRate=3.5;
    }else if($scope.member_good_rate>'80%'&&$scope.member_good_rate<'100%'){
      $scope.goodRate=4.5;
    }else{
      $scope.goodRate=5;
    }
  }

	//获取订单预约详情
	$scope.oid=$location.search().id;//获取url的id属性值，angular专用，或者$location.search()['name'];
  $scope.OrderDetailsFun = function(){
    $http.post(url+"workOrderInterfaces.api?getWorkOrderDetail",$.param({
      order_id:$scope.oid,
      member_id: $cookieStore.get('member_id'),
      member_token: $cookieStore.get('member_token')

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
      console.log(data)
      if(data['status']=="ok"){
        $scope.infoHTML=data['data'];
        $scope.order_accept_id=$scope.infoHTML.order_accept_id;//获取接单师傅的id
        $timeout(function() {
          $scope.worker($scope.order_accept_id);//调用方法获取信息
        }, 50);
        $scope.stamp($scope.infoHTML.order_state);//订单状态
      }
    });
  }
  $scope.OrderDetailsFun();
  //订单四个状态控制方法
  $scope.states=0;
  $scope.stamp = function(id){
    if(id==0){
      $scope.states=1;
    }else if(id==2||id==10||id==11){
      $scope.states=2;
    }else if(id==3||id==4||id==5||id==6||id==7||id==12){
      $scope.states=3;
    }else if(id==8||id==9){
      $scope.states=4;
    }else if(id==16){
      $scope.states=0;
    }
}

   //缴纳30定金
  $scope.paymentFun = function(gid){
    $scope.gid=gid; //工单id
    console.log(window.location.href);
    //window.location.href='http://aksd.tstweiguanjia.com//app-mall/index.html#/pay_finish';
    $.ajax({
      url: url +  "memberInterfaces.api?payWorkOrderDeposit", //接口
      method: 'post',
      data: {  //读取内容所需条件
        member_id : $cookieStore.get("member_id"),
        member_token : $cookieStore.get("member_token"),
        order_id : $scope.gid,
        channel: 'wx_pub'    //微信公众号支付
      },
      dataType : "json",
      success: function (data) {
        var charge = data['data'];
        pingpp.createPayment(charge, function (result, error) {
          if (result == "success") { // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的 wap 支付结果都是在 extra 中对应的 URL 跳转。
            $scope.tips2("预约成功",800);
            $timeout(function () {
              //$scope.reloadRoute();
              $location.path('pendingAudit');
            },800);
            //  window.location.href="app-mall/#/pay_finish?&order_id="+ order_id;
          } else if (result == "fail") {
            $scope.tips2("支付取消",1300);
            //window.location.href="#/orderInfo?&id="+ order_id;
            // charge 不正确或者微信公众账号支付失败时会在此处返回
          } else if (result == "cancel") {
            $scope.tips2("支付失败",1300);
            //window.location.href="#/orderInfo?&id="+ order_id;
          }
        })
      }
    });
  }

   //支付费用
  $scope.payOrderFun = function(gid,order_price,deposit_price){
    $scope.deposit_price=deposit_price || 30;
    $scope.payPrice = order_price - $scope.deposit_price;
    $scope.gid=gid; //工单id
    console.log($scope.gid,$scope.payPrice,order_price,$scope.deposit_price);
    //window.location.href='http://aksd.tstweiguanjia.com//app-mall/index.html#/pay_finish';
   
    $.ajax({
      url: url +  "memberInterfaces.api?payWorkOrderComplete", //接口
      method: 'post',
      data: {  //读取内容所需条件
        member_id : $cookieStore.get("member_id"),
        member_token : $cookieStore.get("member_token"),
        order_id : $scope.gid,
        // price : $scope.payPrice,
        channel: 'wx_pub'    //微信公众号支付
      },
      dataType : "json",
      success: function (data) {
        var charge = data['data'];
        pingpp.createPayment(charge, function (result, error) {
          if (result == "success") { // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的 wap 支付结果都是在 extra 中对应的 URL 跳转。
            $scope.tips2("支付成功",800);
            $timeout(function () {
              $scope.reloadRoute();
            },800);
            //  window.location.href="app-mall/#/pay_finish?&order_id="+ order_id;
          } else if (result == "fail") {
            $scope.tips2("支付取消",1300);
            $timeout(function () {
              $scope.reloadRoute();
            },800);
            //window.location.href="#/orderInfo?&id="+ order_id;
            // charge 不正确或者微信公众账号支付失败时会在此处返回
          } else if (result == "cancel") {
            $scope.tips2("支付失败",1300);
            $timeout(function () {
              $scope.reloadRoute();
            },800);
            //window.location.href="#/orderInfo?&id="+ order_id;
          }
        })
      }
    });
  }
  //用户确认订单完成payWorkOrderComplete
  // $scope.memberEvaluateFun = function(id){
  //   $scope.id=id;
  //   $http.post(url+"workOrderInterfaces.api?updateOrderState",$.param({
  //       member_id: $cookieStore.get('member_id'),
  //       member_token: $cookieStore.get('member_token'),
  //       type: 'member_complete',
  //       order_id: $scope.id

  //     }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
  //   ).success(function(data) {
  //       console.log(data)
  //       if(data['status']=="ok"){
  //           $scope.OrderDetailsFun();
  //       }
  //   });
  // }
  //用户确认订单完成
  
  //用户投诉建议
  $scope.com=0;
  $scope.comFun = function(d){
    $scope.com=d;
  }
  $scope.content={
    text:""
  }
  $scope.complaints = function(id){
    if($scope.content.text!=''){
      $("#loading").fadeIn();
      $scope.id1=id;
      $http.post(url+"workOrderInterfaces.api?workOrderComplaints",$.param({
          member_id: $cookieStore.get('member_id'),
          member_token: $cookieStore.get('member_token'),
          complaints_content: $scope.content.text,
          order_id: $scope.id1

        }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
      ).success(function(data) {
          console.log(data)
          $("#loading").fadeOut();
          if(data['status']=="ok"){
            $scope.comFun(0);
            $scope.OrderDetailsFun();
            $scope.tips2("提交成功",1000);
          }else if(data['status']=="error"){
            $scope.tips2(data['data'],1000);
          }
      });
    }else {
      $scope.tips2('请输入投诉建议内容',1000);
    }
  }
  //评价遮罩控制
  $scope.e=0;
  $scope.eFun = function(id){
    $scope.e=id;
  }
  //评价
  $scope.evaluate = {
    attitudeL:5, //服务态度
    aging:5, //服务时效
    quality:5,  //服务质量
    text:''  //文字评价
  }
  //评价初始化
  $scope.evaluateFun = function(){
    $scope.evaluate = {
      attitudeL:5, //服务态度
      aging:5, //服务时效
      quality:5,  //服务质量
      text:''  //文字评价
    }
  }
  //评价星级
  $scope.evaluateFun = function(type,id){
    $scope.type=type;
    if($scope.type=='attitudeL'){
      $scope.evaluate.attitudeL=id;
      console.log('态度'+$scope.evaluate.attitudeL);
    }else if($scope.type=='aging'){
            $scope.evaluate.aging=id;
            console.log('时效'+$scope.evaluate.aging);
          }else if($scope.type=='quality'){
                  $scope.evaluate.quality=id;
                  console.log('质量'+$scope.evaluate.quality);
                }
  }
  //用户评价订单
  $scope.memberCompleteFun = function(){
    $scope.laoding=1;//等待遮罩变量
    $scope.id=$scope.infoHTML.order_id;
    $http.post(url+"workOrderInterfaces.api?updateOrderState",$.param({
        member_id: $cookieStore.get('member_id'),
        member_token: $cookieStore.get('member_token'),
        type: 'member_evaluate', 
        order_id: $scope.id, 
        order_service_attitude: $scope.evaluate.attitudeL,  //服务态度
        order_service_aging: $scope.evaluate.aging,  //服务时效
        order_service_quality: $scope.evaluate.quality,  //服务质量
        order_evaluate_content: $scope.evaluate.text  //文字评价

      }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
    ).success(function(data) {
        console.log(data);
        $scope.laoding=0;//等待遮罩变量
        if(data['status']=="ok"){
          console.log('用户评价成功');
          $scope.eFun(0);
          $scope.OrderDetailsFun();
        }
    });
  }

}])
/*****************************我的预约end*********************************/

/*
	商城
 */
.controller("shopping",['$scope', '$rootScope', '$location', '$timeout', '$http', '$cookies', '$cookieStore', '$sce', function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore, $sce) {
  console.log("商城");

}])

