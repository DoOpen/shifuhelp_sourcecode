// pulice
app.controller('home',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
    $scope.urls = 'http://wx.shifuhelp.com/';
    // $scope.urls = 'http://aksd.qubaotang.cn/';
    $scope.htmllaa='';
    // if($cookieStore.get('member_id')||$cookieStore.get('member_token')){

    // }else{
    //     $cookieStore.put('member_id',$location.search()['member_id']);
    //     $cookieStore.put('member_token',$location.search()['member_token']);
    // }
    
    // $cookieStore.put('member_id', $scope.member_id);
    // $cookieStore.put('member_token', $scope.member_token);
    console.log($cookieStore.get('member_id'));
    console.log($cookieStore.get('member_token'));
     /*获取cookie*/
      // $scope.getCookie = function(name){
      //   var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
      //   if(arr=document.cookie.match(reg)){
      //     return unescape(arr[2]);
      //   }else {
      //     return null;
      //   }
      // } 
      // /*登入失效执行的方法*/
      // $scope.login = function(){ 
      //   $cookieStore.put('a_url',window.location.href,{path: "/"});
      //   window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx76adf3e3a6e3e471&redirect_uri=http%3a%2f%2fwx.shifuhelp.com%2fwx_login.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
      // }
      // if ($cookies.get("user_info")) {
      //   $scope.member =JSON.parse($scope.getCookie("user_info"));
      //   //console.log($scope.member);
      //   $cookieStore.put('member_id', $scope.member.member_id);
      //   $cookieStore.put('member_token', $scope.member.member_token);
      // } else {
      //   $cookieStore.put('a_url',window.location.href,{path: "/"});
      //   window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx76adf3e3a6e3e471&redirect_uri=http%3a%2f%2fwx.shifuhelp.com%2fwx_login.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
      // }
    $scope.ofAddress;
    // tips2($cookieStore.get("member_id"),800)
    $scope.appCart=function () {
        if($cookieStore.get('member_token')){
            $scope.APPurl=$scope.urls+'/app-mall/index.html#/cart?&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
            // $(".cartbtn").attr("href","JavaScript:android.appCart(\""+$scope.APPurl+"\")");
            // $(".cartbtn").attr("onclick","appCart(\""+$scope.APPurl+"\")");

            var u = navigator.userAgent;
            if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                JavaScript:android.appCart($scope.APPurl)
            } else
            if (u.indexOf('iPhone') > -1) {//苹果手机
                appCart($scope.APPurl)
            }
        }else{
            if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                JavaScript:android.appGOlogin()
            } else
            if (u.indexOf('iPhone') > -1) {//苹果手机
                appGOlogin()
            }
        }
    };
    $scope.appSearch=function () {
        $scope.APPurl=$scope.urls+'app-mall/index.html#/search';
        // $(".searchbtn").attr("href","JavaScript:android.appSearch(\""+$scope.APPurl+"\")");
        // $(".searchbtn").attr("onclick","appSearch(\""+$scope.APPurl+"\")");
        console.log($scope.APPurl)
        window.location.href=$scope.APPurl;
        
    };
     //后退页面     
    $scope.back=function(){
      window.history.go(-1);
    };

})
.directive("searchselect", [function () {  //  搜索选择
    return {
        link: function (scope, element, attributes) {
            element.click(function (index, typesId) {
                if($(".search-tag").text()=='商品'){
                    if($(".search-text input").val()!=''){
                        location.href='#/search_goods?goodsName='+$(".search-text input").val()
                    }else{
                        tips2("请输入搜索内容",800)
                    }
                }
                if($(".search-tag").text()=='商家'){
                    if($(".search-text input").val()!=''){
                        location.href='#/search_store?merchantsName='+$(".search-text input").val()
                    }else{
                        tips2("请输入搜索内容",800)
                    }
                }
            });
        }
    }
}])
.directive("switch", [function () {  //  搜索选择
    return {
        link: function (scope, element, attributes) {
            element.click(function (index, typesId) {
                element.addClass('act').siblings().removeClass('act');
            });
        }
    }
}])
.directive("parametershow", [function () {  //  显示参数
    return {
        link: function (scope, element, attributes) {
            element.click(function (index, typesId) {
                $(".parameterback").fadeIn(100);
                $(".parameterbox").removeClass("tform110");
                $(".goods_detailFooter").hide(0)
            });
        }
    }
}])
.directive("parameterhide", [function () {  //  隐藏参数
    return {
        link: function (scope, element, attributes) {
            element.click(function (index, typesId) {
                $(".parameterback").fadeOut(100);
                $(".parameterbox").addClass("tform110");
                $(".goods_detailFooter").show(0)
            });
        }
    }
}])
.directive("listswitch", [function () {  //  列表切换
    return {
        link: function (scope, element, attributes) {
            element.click(function (index, typesId) {
                element.addClass('act').siblings().removeClass('act');
                if($(".filter-bar .act").hasClass("selectback")){
                    $(".shop_list").html('');
                    if($(".selectback.act").hasClass("selectback_down")){
                        scope.store.Goodsfn(1,'','','price','asc');
                        $(".selectback.act").addClass("selectback_up").removeClass("selectback_down")
                    }else{
                        scope.store.Goodsfn(1,'','','price','desc');
                        $(".selectback.act").addClass("selectback_down").removeClass("selectback_up")
                    }   
                }
            });
        }
    }
}]).directive("payswitch", [function () {  //  支付切换
        return {
            link: function (scope, element, attributes) {
                element.click(function (index, typesId) {
                    element.find('p').addClass('che').parents('li').siblings().find('p').removeClass('che');
                });
            }
        }
    }])