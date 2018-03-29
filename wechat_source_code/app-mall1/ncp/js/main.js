
/*首页*/
app.controller('index',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
    console.log('首页')
    $("#loading").fadeIn();
    $scope.home={
        bannerlist:[],
        class_id_list:[],
        banner: function () {  // banner
            $http.post($scope.urls+"/settingInterfaces.api?getBannerList",$.param({
                banner_position: 'wechat_home'}),
                {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
            ).success(function(data){
                console.log(data)
                if(data["status"]=="ok"){
                    for (var i=0;i<data['data'].length;i++){
                        if(data['data'][i].banner_type=='goods'){
                            $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i]['goods_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                            $(".banners .swiper-wrapper").append('<div class="swiper-slide"><a class="di_bl" href="JavaScript:android.appGoGoods(\''+$scope.APPurl+'\')"  onclick="appGoGoods(\''+$scope.APPurl+'\')"><img class="img1" src="'+data['data'][i]['banner_img']+'" alt=""></a></div>');
                        }else if(data['data'][i].banner_type=='chain'){ //外链
                            //$scope.APPurl=$scope.urls+'app-mall1/index.html#/store_goodslist?md='+data['data'][i]['merchants_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                            $scope.APPurl=data['data'][i]['chain_url'];
                            //console.log("appGoLink(\''+$scope.APPurl+'\',+data['data'][i]['banner_title']+'\')" )
                            $(".banners .swiper-wrapper").append('<div class="swiper-slide"><a class="di_bl" href="JavaScript:android.appGoLink(\''+$scope.APPurl+'\',\''+data['data'][i]['banner_title']+'\')"  onclick="appGoLink(\''+$scope.APPurl+'\',\''+data['data'][i]['banner_title']+'\')"><img class="img1" src="'+data['data'][i]['banner_img']+'" alt=""></a></div>');
                        }else if(data['data'][i].banner_type=='common'){ //内链
                            //$scope.APPurl=$scope.urls+'app-mall1/index.html#/store_goodslist?md='+data['data'][i]['merchants_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                            $scope.APPurl=$scope.urls+data['data'][i]['banner_url'];
                            $(".banners .swiper-wrapper").append('<div class="swiper-slide"><a class="di_bl" href="JavaScript:android.appGoLink(\''+$scope.APPurl+'\',\''+data['data'][i]['banner_title']+'\')"  onclick="appGoLink(\''+$scope.APPurl+'\',\''+data['data'][i]['banner_title']+'\')"><img class="img1" src="'+data['data'][i]['banner_img']+'" alt=""></a></div>');
                        }
                    }
                    var Myswiper = new Swiper('.swiper-container',{
                        pagination:'.swiper-pagination',
                        direction:"horizontal",
                        observer:true,
                        observeParents:true,
                        loop:true,
                        loopAdditionalSlides:1,
                        autoplay:2000,
                        autoplayDisableOnInteraction:false,
                    });
                } else if (data['status'] == 'error') {
                    console.log(data)
                }
            })
        },
        Level: function () {  // 分类
            $http.post($scope.urls+"/goodsInterfaces.api?getGoodsClassListCache",$.param({
                    
                }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
            ).success(function(data){
                console.log(data)
                if(data["status"]=="ok"){
                    $scope.home.classList=data['data'];
                    // for(var i=0;i<$scope.home.classList.length;i++){
                    //     $scope.home.Recommend($scope.home.classList[i].class_id,i);
                    // }
                } else if (data['status'] == 'error') {
                    console.log(data)
                }
            })
        },
        listInfo:[],
        Recommend: function () {  // 分类
            //$scope.class_id=classId || 1 ;
            //console.log($scope.class_id)
            $scope.district1; //区
            if($cookieStore.get('address')=='失败'){ //判断是否定位成功
                $scope.district1='';
            }else{
                $scope.district1=$cookieStore.get('address');
                console.log($cookieStore.get('address'))
            }
            $http.post($scope.urls+"/goodsInterfaces.api?getHomeClassGoodsList",$.param({
                send_range:'',// $scope.district1,
                show_type: 'app'
                }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
            ).success(function(data){
                console.log(data)
                $("#loading").fadeOut();
                if(data["status"]=="ok"){
                    $scope.home.listInfo=data['data'];
                    //console.log($scope.home.listInfo[list])
                } else if (data['status'] == 'error') {
                    console.log(data)
                }
            })
        },
        goLevel: function(classId){
            $scope.APPurl=$scope.urls+'/app-mall1/index.html#/goodsList?uuid='+classId+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token')+'&add='+$scope.addApp;
            //window.location.href=$scope.APPurl;
            var u = navigator.userAgent;
            if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                JavaScript:android.appGoodslist($scope.APPurl)
            } else
            if (u.indexOf('iPhone') > -1) {//苹果手机
                appGoodslist($scope.APPurl);
            }
        },
        goodsListIntegral: function(){
            $scope.APPurl=$scope.urls+'/app-mall1/index.html#/goodsListIntegral?&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token')+'&add='+$scope.addApp;
            //window.location.href=$scope.APPurl;
            var u = navigator.userAgent;
            if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                JavaScript:android.appGoodslistIntegral($scope.APPurl)
            } else
            if (u.indexOf('iPhone') > -1) {//苹果手机
                appGoodslistIntegral($scope.APPurl);
            }
        },
        goGoodsDetail: function(goodsId){
            $scope.APPurl1=$scope.urls+'/app-mall1/index.html#/goods_detail?dd='+goodsId+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
            //window.location.href=$scope.APPurl1;
            var u = navigator.userAgent;
            if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                JavaScript:android.appGoGoods($scope.APPurl1)
            } else
            if (u.indexOf('iPhone') > -1) {//苹果手机
                appGoGoods($scope.APPurl1);
            }
        }
       
    }
    $scope.addAppFun = function(){
        $scope.addApp=encodeURI($scope.city);
        console.log($scope.addApp)
    }
   // 定位
    $scope.city=$cookieStore.get('address') || '失败';
    $scope.addAppFun();
    console.log($scope.city)
    $scope.cityFun = function (d) {
        $scope.city=d;
    }


     /* 调用省市区联动插件 */
    $("#city-picker").cityPicker({
      title: "选择省市区/县",
      onChange: function (picker, values, displayValues) {
        console.log(values, displayValues);
        var addressArr = displayValues;
        //$cookieStore.put("address",addressArr[2],'/'); 
        $cookieStore.put("address",addressArr[2]);

        console.log($cookieStore.get("address"))
        $scope.$apply(function(){
            $scope.city=$cookieStore.get("address");
        });
        $scope.addAppFun();
        $scope.home.Recommend();
        $scope.integralClass();
      }
    });
    // 积分商品
    $scope.integralList = [];
    $scope.integralClass = function () {  // 积分商品
            
        //console.log($scope.class_id)
        $scope.district1 = ''; //区
        if($cookieStore.get('address')=='失败'){ //判断是否定位成功
            $scope.district1='';
        }else{
            $scope.district1=$cookieStore.get('address');
        }
        //alert($cookieStore.get('address'))
        $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
            send_range:'',// $scope.district1,
            show_type: 'app',
            buy_type: 'integral'
            }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
        ).success(function(data){
            console.log(data)
            if(data["status"]=="ok"){
                $scope.integralList = data['data'];
                //console.log($scope.goodsClass.listInfo[list])
            } else if (data['status'] == 'error') {
                console.log(data)
            }
        })
    }
    $scope.integralClass();

    $scope.appSearch=function () {
        $scope.APPurl=$scope.addApp;
        // $(".searchbtn").attr("href","JavaScript:android.appSearch(\""+$scope.APPurl+"\")");
        // $(".searchbtn").attr("onclick","appSearch(\""+$scope.APPurl+"\")");
        console.log($scope.APPurl)
        //判断是否是微信浏览器的函数
            // var ua = window.navigator.userAgent.toLowerCase();
            // if(ua.match(/MicroMessenger/i) == 'micromessenger'){
            //     window.location.href=$scope.APPurl;
            // }
        var u = navigator.userAgent;
        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
            JavaScript:android.appSearch($scope.APPurl)
        } else
        if (u.indexOf('iPhone') > -1) {//苹果手机
            appSearch($scope.APPurl)
        }
    };
    /* dom */
    // <input id="city-picker" />
    $scope.home.banner();
    $scope.home.Level();
    $scope.home.Recommend();
    //滚动加载
    $(window).scroll(function () {
        var winHeight = null, winWidth = null, mTop = null, wTop = null, result = null, oTop = null, bTop = null, dTop = null;
        wTop = $('body').scrollTop();  // 滚动的距离
        bTop = $("body").height();  // 盒子的高度
        dTop = $('#home').height();  // 盒子内容的高度
        if (wTop + bTop >= dTop) {//下拉到底部加载
            if ($scope.pageNum > $scope.pages) {
                // $scope.home.Recommend(++$scope.pages);
            }
        }
    })
})
    // 搜索
    .controller('search',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('搜索');
        //alert($location.search().add)
        $scope.search={
            searchbtn: function () {
                $cookieStore.put('searchInfo','');
                //if($(".search-tag").text()=='商品'){
                    if($(".search-text input").val()!=''){
                        $scope.APPurl=$scope.urls+'/app-mall1/index.html#/search_goods?goodsName='+$(".search-text input").val()+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        location.href='#/search_goods';
                        $cookieStore.put('searchInfo',$(".search-text input").val());
                        $("#loading").fadeIn();
                        // JavaScript:android.appSearchGoods($scope.APPurl);
                        // appGoPay($scope.APPurl);
                        // location.href='#/search_goods?goodsName='+$(".search-text input").val()
                    }else{
                        tips2("请输入搜索内容",800)
                    }
                // }
                // if($(".search-tag").text()=='商家'){
                //     if($(".search-text input").val()!=''){
                //         $scope.APPurl=$scope.urls+'/app-mall1/index.html#/search_store?merchantsName='+$(".search-text input").val()+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                //         JavaScript:android.appSearchstore($scope.APPurl);
                //         appSearchstore($scope.APPurl);
                //         // location.href='#/search_store?merchantsName='+$(".search-text input").val()
                //     }else{
                //         tips2("请输入搜索内容",800)
                //     }
                // }
            },
            searchlist:function () {
                $http.post($scope.urls+"/searchHistoryInterfaces.api?selSearchHistory",$.param({
                        member_id:$cookieStore.get("member_id"),
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data);
                    if(data["status"]=="ok"){
                        for (var i=0;i<data['data'].length;i++){
                            if(data['data'][i].search_type=='goods'){
                                $scope.APPurl=$scope.urls+'app-mall1/index.html#/search_goods?goodsName='+data['data'][i]['search_content']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                                $(".searchlists").append('<li class="b1b_CD"><a class="di_bl" href="JavaScript:android.appSearchgoods(\''+$scope.APPurl+'\')"  onclick="appSearchgoods(\''+$scope.APPurl+'\')"><p>'+data['data'][i]['search_content']+'</p></a></li>');
                            }else if(data['data'][i].search_type=='merchants'){
                                $scope.APPurl=$scope.urls+'app-mall1/index.html#/search_store?merchantsName='+data['data'][i]['search_content']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                                $(".searchlists").append('<li class="b1b_CD"><a class="di_bl" href="JavaScript:android.appSearchstore(\''+$scope.APPurl+'\')"  onclick="appSearchstore(\''+$scope.APPurl+'\')"><p>'+data['data'][i]['search_content']+'</p></a></li>');
                            }
                        }
                        if(data['total']==0){
                            $scope.modelstext='暂无数据';
                        }
                        $scope.search.searchliststotal=data['total'];
                    } else if (data['status'] == 'error') {
                        console.log(data);
                    }
                })
            },
            // Hotsearchlist:function () {
            //     $http.post($scope.urls+"/goodsInterfaces.api?getHotSearchs",$.param({
            //             search_type:'goods',
            //             page:1,
            //             limit:5
            //     }),
            //         {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
            //     ).success(function(data){
            //         console.log(data);
            //         if(data["status"]=="ok"){
            //             for (var i=0;i<data['data'].length;i++){
            //                 if(data['data'][i].search_type=='goods'){
            //                     $scope.APPurl=$scope.urls+'app-mall1/index.html#/search_goods?goodsName='+data['data'][i]['search_name']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
            //                     $(".hot-search").append('<a href="JavaScript:android.appSearchgoods(\''+$scope.APPurl+'\')" onclick="appSearchgoods(\''+$scope.APPurl+'\')" class="hot-search-tag di_bl l f-12 back_ef col_666 ml-15 line_h20 pl-5 pr-5 bor_3">'+data['data'][i]['search_name']+'</a>');
            //                 }else if(data['data'][i].search_type=='merchants'){
            //                     $scope.APPurl=$scope.urls+'app-mall1/index.html#/search_store?merchantsName='+data['data'][i]['search_name']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
            //                     $(".hot-search").append('<a href="JavaScript:android.appSearchstore(\''+$scope.APPurl+'\')" onclick="appSearchstore(\''+$scope.APPurl+'\')" class="hot-search-tag di_bl l f-12 back_ef col_666 ml-15 line_h20 pl-5 pr-5 bor_3">'+data['data'][i]['search_name']+'</a>');
            //                 }
            //             }
            //             $scope.search.searchliststotal=data['total'];
            //         } else if (data['status'] == 'error') {
            //             console.log(data);
            //         }
            //     })
            // },
            hotSearchlist: function () { //查询热搜
                $http.post($scope.urls+"/goodsInterfaces.api?getHotSearchList",$.param({

                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data);
                    if(data["status"]=="ok"){
                        $scope.search.hotList=data['data'];
                    } else if (data['status'] == 'error') {
                        console.log(data);
                    }
                })
            },
            hotSearchlistOn: function (value) {
                $("#loading").fadeIn();
                $cookieStore.put('searchInfo',value);
                $scope.APPurl=$scope.urls+'/app-mall1/index.html#/search_goods?goodsName='+value+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token')+'&add='+$location.search().add;
                location.href='#/search_goods?goodsName='+value+'&add='+$location.search().add;
                //location.href='#/search_goods';
                // var u = navigator.userAgent;
                // if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                //     JavaScript:android.appSearch($scope.APPurl)
                // } else
                // if (u.indexOf('iPhone') > -1) {//苹果手机
                //     appSearch($scope.APPurl)
                // }
            },
            delsearch:function () {
                $http.post($scope.urls+"/searchHistoryInterfaces.api?delSearchHistory",$.param({
                        member_id:$cookieStore.get("member_id"),
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data);
                    if(data["status"]=="ok"){
                        tips2(data['data'],800);
                        $scope.search.searchlist()
                    } else if (data['status'] == 'error') {
                        console.log(data);
                    }
                })
            }
        }
        //$scope.search.searchlist();
        $scope.search.hotSearchlist();
    })
    // 分类
    .controller('goodsClass',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('分类');
        //alert($cookieStore.get('address'));
        $scope.add1=encodeURI($cookieStore.get('address'));
        $scope.goodsClass={
            Level: function () {  // 分类
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsClassListCache",$.param({
                       
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        $scope.goodsClass.classList=data['data'];
                        for(var i=0;i<$scope.goodsClass.classList.length;i++){
                            $scope.goodsClass.Recommend($scope.goodsClass.classList[i].class_id,i);
                        }
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            listInfo:[],
            Recommend: function (classId,list) {  // 分类
                $scope.class_id=classId || 1 ;
                $scope.list=list;
                //console.log($scope.class_id)
                 $scope.district1; //区
                if($cookieStore.get('address')=='失败'){ //判断是否定位成功
                    $scope.district1='';
                }else{
                    $scope.district1=$cookieStore.get('address');
                }
                //alert($cookieStore.get('address'))
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
                    class_id:$scope.class_id,
                    send_range:'',// $scope.district1,
                    show_type: 'app',
                    buy_type: 'money'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        $scope.goodsClass.listInfo[list]=data['data'];
                        //console.log($scope.goodsClass.listInfo[list])
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            goLevel: function(classId){
                $scope.APPurl=$scope.urls+'/app-mall1/index.html#/goodsList?uuid='+classId+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token')+'&add='+$scope.add1;
                //window.location.href=$scope.APPurl;
                var u = navigator.userAgent;
                if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                    JavaScript:android.appGoodslist($scope.APPurl)
                } else
                if (u.indexOf('iPhone') > -1) {//苹果手机
                    appGoodslist($scope.APPurl);
                }
            },  
            goodsListIntegral: function(){
                $scope.APPurl=$scope.urls+'/app-mall1/index.html#/goodsListIntegral?&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token')+'&add='+$scope.addApp;
                //window.location.href=$scope.APPurl;
                var u = navigator.userAgent;
                if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                    JavaScript:android.appGoodslistIntegral($scope.APPurl)
                } else
                if (u.indexOf('iPhone') > -1) {//苹果手机
                    appGoodslistIntegral($scope.APPurl);
                }
            },
            goGoodsDetail: function(goodsId){
                $scope.APPurl1=$scope.urls+'/app-mall1/index.html#/goods_detail?dd='+goodsId+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                //window.location.href=$scope.APPurl1;
                var u = navigator.userAgent;
                if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                    JavaScript:android.appGoGoods($scope.APPurl1)
                } else
                if (u.indexOf('iPhone') > -1) {//苹果手机
                    appGoGoods($scope.APPurl1);
                }
            },
        }
        $scope.goodsClass.Level();
        // 积分商品
        $scope.integralList = [];
        $scope.integralClass = function () {  // 微信商品
                
            //console.log($scope.class_id)
            $scope.district1 = ''; //区
            if($cookieStore.get('address')=='失败'){ //判断是否定位成功
                $scope.district1='';
            }else{
                $scope.district1=$cookieStore.get('address');
            }
            //alert($cookieStore.get('address'))
            $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
                send_range:'',// $scope.district1,
                show_type: 'app',
                buy_type: 'integral'
                }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
            ).success(function(data){
                console.log(data)
                if(data["status"]=="ok"){
                    $scope.integralList = data['data'];
                    //console.log($scope.goodsClass.listInfo[list])
                } else if (data['status'] == 'error') {
                    console.log(data)
                }
            })
        }
        $scope.integralClass();
    })
    // 搜索-商品
    .controller('search_goods',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('搜索-商品');
        //$("#loading").fadeOut();
        $scope.add1=decodeURIComponent($location.search().add);
        //alert($scope.add1)
        $scope.district1; //区
        if($scope.add1==='失败'){ //判断是否定位成功
            $scope.district1='';
        }else{
            $scope.district1=$scope.add1;
           // alert($location.search().add)
        }
        alert($location.search()['goodsName'])
        $scope.searchGoods={
            type:0,
            goods: function (page) {
                $("#loading").fadeIn();
                // $scope.district1; //区
                // if($cookieStore.get('address')=='失败'){ //判断是否定位成功
                //     $scope.district1='';
                // }else{
                //     $scope.district1=$cookieStore.get('address');
                // }
                $scope.searchGoods.type=1;
                // alert($cookieStore.get("searchInfo"));
                $scope.pages=page||1;
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
                    goods_name: $location.search()['goodsName'],
                    page:$scope.pages,
                    send_range:'',// $scope.district1
                    show_type: 'app'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    $("#loading").fadeOut();
                    console.log(data)
                    if(data["status"]=="ok"){
                        $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
                        $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有商品数据了" : $scope.modelstext = "";
                        for (var i = 0; i < data['data'].length; i++) {
                            $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i]['goods_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                            $(".shop_list").append('<li class="shop_listli">' +
                                '<a href="JavaScript:android.appGoGoods(\''+$scope.APPurl+'\')" onclick="appGoGoods(\''+$scope.APPurl+'\')" class="di_bl">' +
                                //'<a href="'+$scope.APPurl+'" class="di_bl">' +
                                '<div class="shop_listImg l mr-10">' +
                                '<img src="'+data['data'][i].goods_img+'" alt="">' +
                                '</div>' +
                                '<div class="shop_listDetail l">' +
                                '<p class="shop_listName text_ellipsis_2 f-14 col_333 hei_40">'+data['data'][i]['goods_name']+'</p>' +
                                '<p class="shop_listCurrentPrice f-15 flex-between">'+
                                '<span class="f-12 col_red"><span class="col_red price" leng="en">&#165</span>'+data['data'][i]['goods_now_price']+'</span>'+
                                '<span class="shop_listPj f-10 col_999 mr-10 text_ellipsis">'+data['data'][i]['total_sales']+'人购买</span>' +
                                '</p>' +
                                '</div></a></li>')
                        }
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            goods1: function(page){ //销量排序
                $("#loading").fadeIn();
                $scope.searchGoods.type=2;
                // alert($cookieStore.get("searchInfo"));
                $scope.pages=page||1;
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
                    goods_name: $location.search()['goodsName'],
                    order:'sales',
                    page: $scope.pages,
                    send_range:'',// $scope.district1
                    show_type: 'app'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    $("#loading").fadeOut();
                    console.log(data)
                    if(data["status"]=="ok"){
                        $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
                        $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有商品数据了" : $scope.modelstext = "";
                        for (var i = 0; i < data['data'].length; i++) {
                            $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i]['goods_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                            $(".shop_list").append('<li class="shop_listli">' +
                                '<a href="JavaScript:android.appGoGoods(\''+$scope.APPurl+'\')" onclick="appGoGoods(\''+$scope.APPurl+'\')" class="di_bl">' +
                                //'<a href="'+$scope.APPurl+'" class="di_bl">' +
                                '<div class="shop_listImg l mr-10">' +
                                '<img src="'+data['data'][i].goods_img+'" alt="">' +
                                '</div>' +
                                '<div class="shop_listDetail l">' +
                                '<p class="shop_listName text_ellipsis_2 f-14 col_333 hei_40">'+data['data'][i]['goods_name']+'</p>' +
                                '<p class="shop_listCurrentPrice f-15 flex-between">'+
                                '<span class="f-12 col_red"><span class="col_red price" leng="en">&#165</span>'+data['data'][i]['goods_now_price']+'</span>'+
                                '<span class="shop_listPj f-10 col_999 mr-10 text_ellipsis">销量：'+data['data'][i]['total_sales']+'</span>' +
                                '</p>' +
                                '</div></a></li>')
                        }
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            goods2: function(page){ //销量排序
                $("#loading").fadeIn();
                $scope.searchGoods.type=3;
                // alert($cookieStore.get("searchInfo"));
                $scope.pages=page||1;
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
                    goods_name: $location.search()['goodsName'],
                    order: 'price',
                    page:$scope.pages,
                    send_range:'',// $scope.district1
                    show_type: 'app'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    $("#loading").fadeOut();
                    console.log(data)
                    if(data["status"]=="ok"){
                        $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
                        $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有商品数据了" : $scope.modelstext = "";
                        for (var i = 0; i < data['data'].length; i++) {
                            $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i]['goods_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                            $(".shop_list").append('<li class="shop_listli">' +
                                '<a href="JavaScript:android.appGoGoods(\''+$scope.APPurl+'\')" onclick="appGoGoods(\''+$scope.APPurl+'\')" class="di_bl">' +
                                //'<a href="'+$scope.APPurl+'" class="di_bl">' +
                                '<div class="shop_listImg l mr-10">' +
                                '<img src="'+data['data'][i].goods_img+'" alt="">' +
                                '</div>' +
                                '<div class="shop_listDetail l">' +
                                '<p class="shop_listName text_ellipsis_2 f-14 col_333 hei_40">'+data['data'][i]['goods_name']+'</p>' +
                                '<p class="shop_listCurrentPrice f-15 flex-between">'+
                                '<span class="f-12 col_red"><span class="col_red price" leng="en">&#165</span>'+data['data'][i]['goods_now_price']+'</span>'+
                                '<span class="shop_listPj f-10 col_999 mr-10 text_ellipsis">'+data['data'][i]['total_sales']+'人购买</span>' +
                                '</p>' +
                                '</div></a></li>')
                        }
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            goodsType: function(type,page){
                if(!page){
                    $(".shop_list").empty();
                }
                if(type==1){
                    $scope.searchGoods.goods(page);
                }else if(type==2){
                    $scope.searchGoods.goods1(page);
                }else if(type==3){
                    $scope.searchGoods.goods2(page);
                }
            }
        };

        $scope.searchGoods.goods()
        //滚动加载
        $(".main").scroll(function () {
            var winHeight = null, winWidth = null, mTop = null, wTop = null, result = null, oTop = null, bTop = null, dTop = null;
            wTop = $('.main').scrollTop();  // 滚动的距离
            bTop = $(".main").height();  // 盒子的高度
            dTop = $('.shop_list').height();  // 盒子内容的高度
            if (wTop + bTop >= dTop) {//下拉到底部加载
                if ($scope.pageNum > $scope.pages) {
                    $scope.searchGoods.goodsType($scope.searchGoods.type,++$scope.pages);
                }
            }
        })
    })
    // 搜索-商家
    .controller('search_store',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('搜索-商家');
        $scope.searchStore={
            store: function (page) {
                $scope.pages=page || 1;
                $http.post($scope.urls+"/merchantsInterfaces.api?searchMerchants",$.param({
                    merchants_name:$location.search()['merchantsName'],
                    page:$scope.pages,
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        for(var i=0;i<data['data'].length;i++){
                            $scope.APPurl=$scope.urls+'/app-mall1/index.html#/store_goodslist?md='+data['data'][i]['merchants_id']+'&uuid='+data['data'][i]['goods_uuid']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                            $(".store_list").append('<li class="store_listli plr-15 back_fff t_c">\
                                <div class="pt-12 pb-12 store-detail o_h">\
                                <img src="'+data['data'][i]['merchants_img']+'" alt="" class="store-img di_bl l">\
                                <p class="ml-15 l">\
                                <span class="di_bl t_l f-16 col_333 text_ellipsis_2 merchants_name">'+data['data'][i]['merchants_name']+'</span>\
                                </p>\
                                <a href="JavaScript:android.appGostore(\''+$scope.APPurl+'\')" onclick="appGostore(\''+$scope.APPurl+'\')" class="di_bl r baguette-btn bor_3 f-12">进店</a>\
                                </div>\
                                </li>')
                        }
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            }
        };
        $scope.searchStore.store()
        //滚动加载
        $(".main").scroll(function () {
            var winHeight = null, winWidth = null, mTop = null, wTop = null, result = null, oTop = null, bTop = null, dTop = null;
            wTop = $('.main').scrollTop();  // 滚动的距离
            bTop = $(".main").height();  // 盒子的高度
            dTop = $('.shop_list').height();  // 盒子内容的高度
            if (wTop + bTop >= dTop) {//下拉到底部加载
                if ($scope.pageNum > $scope.pages) {
                    $scope.searchStore.store(++$scope.pages);
                }
            }
        })
    })
    // 商品详情
    .controller('goods_detail',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore,$sce){
        console.log('商品详情');
        $scope.goods={
            // bannerlist:[],
            pingjialist:[],
            //gg:[],//存放选中规格
            onGGArr:[],//存放可以购买的规格list
            onGGArr1:[],
            Goodsfn: function () {  // 详情
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsDetail",$.param({
                    member_id:$cookieStore.get("member_id"),
                    goods_id: $location.search()['dd']
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    //$scope.goods.goodsDetail=data.data;
                    $scope.goods.is_collection1=data.data.is_collection;
                   
                    //$('.goindex a').attr('href',$scope.urls+'/app-mall1/#/home');
                    if(data["status"]=="ok"){
                        //存放选中的规格
                        $scope.guigeInfo=data['data']['specificationBeans'];
                        for(var i=0;i<$scope.guigeInfo.length;i++){
                            // $scope.goods.gg[i]=$scope.guigeInfo[i].specificationBeans[0].specification_id;
                            // console.log('默认选择 '+[i]+$scope.goods.gg[i]);
                            $scope.goods.specificationFn(i,$scope.guigeInfo[i].specificationBeans[0].specification_id);
                        }
                        // $scope.guige1Info=data['data']['goodsSpecificationBeans'];
                        // for(var i=0;i<$scope.guige1Info.length;i++){
                        //     $scope.goods.onGGArr[i]=$scope.guige1Info[i].specification_list; //把能购买的商品规格list存进去
                        //     $scope.goods.onGGArr1[i]=$scope.goods.onGGArr[i].split(",");
                        // }
                        $scope.gglength=data['data']['specificationBeans'].length;
                        $scope.goods.pinglun();
                        data['data']['goods_total']=data['data']['goods_price'];
                        // console.log(data['data']['goods_price']);
                        // for(var i=0;i<data['data']['goodsParameterBeans'].length;i++){
                        //     console.log(data['data']['goodsParameterBeans'][i]['goodsParameterBeans'][0]['parameter_price'])
                        //     data['data']['goods_total']=parseFloat(data['data']['goods_total'])+parseFloat(data['data']['goodsParameterBeans'][i]['goodsParameterBeans'][0]['parameter_price'])
                        // }
                        $scope.goods.goodsDetail=data['data'];
                        // 分享商品
                        $scope.APPurlb=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+$location.search()['dd'];
                        $("#sharegood").attr("href","JavaScript:android.appShareGood(\""+$scope.APPurlb+"\",\""+data['data'].goods_name+"\",\""+data['data'].goods_desc+"\",\""+data['data'].goods_img+"\")");
                        $("#sharegood").attr("onclick","appShareGood(\""+$scope.APPurlb+"\",\""+data['data'].goods_name+"\",\""+data['data'].goods_desc+"\",\""+data['data'].goods_img+"\")");
                        //商品详情banner 图
                        //console.log($scope.goods.gbimg)
                        $scope.goods.gbimg=data['data']['goodsImgBeans'];
                        $scope.goods.gbimgl=$scope.goods.gbimg.length;
                        //console.log($scope.goods.gbimg)
                        //商品详情banner
                        for (var i=0;i<$scope.goods.gbimg.length;i++){
                            $(".banners .swiper-wrapper").append('<div class="swiper-slide"><a class="di_bl"><img class="img2" src="'+$scope.goods.gbimg[i]['img_url']+'" alt=""></a></div>');
                        }
                        var Myswiper = new Swiper('.swiper-container',{
                            pagination:'.swiper-pagination',
                            direction:"horizontal",
                            observer:true,
                            observeParents:true,
                            loop:true,
                            loopAdditionalSlides:1,
                            autoplay:2000,
                            autoplayDisableOnInteraction:false,
                        });

                        $scope.APPurl=$scope.urls+'app-mall1/index.html#/store_goodslist?md='+$scope.goods.goodsDetail['merchants_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        $(".goshop").attr("href","JavaScript:android.appGostore(\""+$scope.APPurl+"\")");
                        $(".goshop").attr("onclick","appGostore(\""+$scope.APPurl+"\")");
                        //商品图文详情
                        $scope.goods.details=$sce.trustAsHtml(data['data']['goods_url_content']);
                         //商品总评分
                        $scope.goods.zpf=parseFloat(($scope.goods.goodsDetail.goods_star1+$scope.goods.goodsDetail.goods_star2+$scope.goods.goodsDetail.goods_star3)/3).toFixed(2);
                        //console.log($scope.goods.zpf)
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    } else if (data["status"] == "pending" && data["error"] == "token failed") {
                        var u = navigator.userAgent;
                        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                            JavaScript:android.appGOlogin()
                        } else
                        if (u.indexOf('iPhone') > -1) {//苹果手机
                            appGOlogin()
                        }
                    }
                })
            },
            selected1:'',//控制规格详情选中样式变化
            sPrice:'', //选中规格后商品价格变化
            specification: function () { //通过list获得单个规格详情
                // $scope.selected=d;
                // console.log($scope.selected);
                console.log($scope.goods.specIdArr.join(","))
                console.log($location.search()['dd'])
                $http.post($scope.urls+"goodsInterfaces.api?getGoodsSpecificationDetail",$.param({
                    goods_id: $location.search()['dd'],
                    specification_ids: $scope.goods.specIdArr.join(",")
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function (data) {
                    console.log(data)
                    $("#loading").fadeOut();
                    if(data['status']=='ok'){
                        $scope.listInfo=data['data'];
                        $scope.goods.numfn(3,$scope.listInfo.specification_stock);
                        // $scope.goods.sPrice=data['data'].specification_price;
                        // $scope.guigeInfo=data['data']['specificationBeans'];
                        // $scope.guigeInfo1=data['data']['specificationBeans']['specificationBeans'];
                        //$scope.goods.selected1=data['data']['specificationBeans'][0].specification_id;
                    }
                })

            },
            zezao:0, //控制加入购物车，购买遮罩
            zezaoFun: function(d,type1){
                $scope.goods.zezao=d;
                if(type1==1){
                    $scope.goods.type1Fun(1);
                }else if(type1==2){
                    $scope.goods.type1Fun(2);
                }
            },
            type1:1, //控制是加入购物车按钮还是购买按钮 1购物车 2购买
            type1Fun: function(d){
                $scope.goods.type1=d;
            },
            specIdArr:[],
            // onspecificationFn: function(id){
            //     for(var i=0;i<$scope.guige1Info.length;i++){
            //         for(var j=0;j<$scope.guigeInfo.length;j++){
            //             if($scope.goods.onGGArr1[i][j]==id){
            //                 console.log(i+' '+j+':'+$scope.goods.onGGArr1[i]);
            //             }
            //         }
            //     }
            // },
           
            
            pinglun: function () { // 商品详情页 评论
                $http.post($scope.urls+"/goodsInterfaces.api?getAssessmentList",$.param({
                    goods_id:$location.search()['dd']
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function (data) {
                    console.log(data)
                    if(data['status']=='ok'){
                        $scope.APPurl_pinglun=$scope.urls+'app-mall1/index.html#/pinglun_good?md='+$scope.goods.goodsDetail['goods_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        // //$('.chakanpingjia').attr("href",$scope.APPurl_pinglun)
                        $('.chakanpingjia').attr("href","JavaScript:android.appGoodpinglun(\""+$scope.APPurl_pinglun+"\")")
                        $('.chakanpingjia').attr("onclick","appGoodpinglun(\""+$scope.APPurl_pinglun+"\")")
                        $scope.goods.pingjialist=data['data'];
                        //$scope.goods.pingjialist[0].memberBean.member_account=$scope.goods.pingjialist[0].memberBean.member_account.slice(0,3)+"****"+$scope.goods.pingjialist[0].memberBean.member_account.slice(7,11);
                        
                    }
                })
            },
            jumpPinglun:function (d) {
                $scope.APPurl=$scope.urls+'app-mall1/index.html#/pinglun_good?md='+$scope.goods.goodsDetail['goods_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');

            },

            Collectionfn: function (d) {  // 收藏
                $http.post($scope.urls+"/collectionInterfaces.api?insertCollection",$.param({
                    member_id:$cookieStore.get("member_id"),
                    member_token:$cookieStore.get("member_token"),
                    goods_id:d
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        tips2('收藏成功',800);
                        $scope.goods.goodsDetail.collection_id=data['data'];
                        $scope.goods.collectionText='收藏';
                        // $scope.goods.is_collection1 = $scope.goods.goodsDetail.collection_id; //收藏控制变量
                    } else if (data['status'] == 'error') {
                        tips2(data['error'],800)
                    }
                })
            },
            cancelCollectionfn: function (d) {  // 取消收藏
                console.log(d)
                $http.post($scope.urls+"/collectionInterfaces.api?cancelCollection",$.param({
                    member_id:$cookieStore.get("member_id"),
                    member_token:$cookieStore.get("member_token"),
                    collection_ids: $scope.goods.goodsDetail.collection_id
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        tips2('取消成功',800);
                        $scope.goods.collectionText='收藏';
                        $scope.goods.goodsDetail.collection_id = 0; //取消收藏控制变量
                    } else if (data['status'] == 'error') {
                        tips2(data['error'],800)
                        console.log(data)
                    }
                })
            },
            parameters: function () {  //  参数
                $scope.goods.parameterstext=[]
                for(var i=0;i<$(".parameterList").length;i++){
                    $scope.goods.parameterstext.push($(".parameterList").eq(i).find(".parameterTag .act").attr('id'));
                }
                $scope.goods.parameterstext1=$scope.goods.parameterstext.join(",");
                console.log($scope.goods.parameterstext1)
            },
            selectPar:function () {
                $scope.goods.par=0;
                for(var i=0;i<$(".parameterList").length;i++){
                    $scope.goods.par+=parseFloat($(".parameterList").eq(i).find('.act').attr('price'));
                }
                $scope.goods.goodsDetail['goods_total']=parseFloat($scope.goods.par)+parseFloat($scope.goods.goodsDetail['goods_now_price'])
                console.log($scope.goods.goodsDetail['goods_total'])
            },

            specificationFn: function (index,id){ // 外层循环下标，选中规格ID
                $("#loading").fadeIn();
                $scope.goods.specIdArr[index]=id;
                if ($scope.goods.specIdArr.length == $scope.guigeInfo.length){  //判断是否选中了全部规格
                    $scope.goods.specification();
                }else {
                    return false
                }
                console.log($scope.goods.specIdArr)
                // for(var i=0;i<$scope.guigeInfo.length;i++){
                //     if(i==index){
                //         $scope.goods.gg[i]=id;
                        
                //     }
                //     console.log($scope.goods.gg[i]);
                // }

            },
            num:1,//购买的商品数量
            numfny: function (type,max) {
                $timeout(function(){
                    $scope.goods.numfn(type,max);
                },20);
            },
            numfn: function(type,max){
                //$scope.goods.num=parseInt($(".content") .val());
                $scope.goods.max=parseInt(max);
                if(max==0){
                    tips2('库存不足',800);
                    return false;
                }
                if(type==1){
                    if ($scope.goods.num <= 1) {
                        $scope.goods.num=1
                    }else{
                        if ($scope.goods.num >  $scope.goods.max) {
                            $scope.goods.num=$scope.goods.max;
                        }else{
                            $scope.goods.num--;
                        }
                    }
                }else if(type==2){
                    if ($scope.goods.num >=  $scope.goods.max) {
                        $scope.goods.num=$scope.goods.max;
                        tips2('库存不足',800);
                    }else{
                        $scope.goods.num++;
                    }
                }else if(type==3){
                    if ($scope.goods.num < 1 || isNaN($scope.goods.num * 1)) {
                        $scope.goods.num = 1
                    }else if($scope.goods.num > $scope.goods.max){
                        $scope.goods.num = $scope.goods.max;
                    }
                }
                $(".content") .val($scope.goods.num);
            },
            addcart: function () {  //  添加购物车
                //$scope.goods.parameters()
                $http.post($scope.urls+"/shopCarInterfaces.api?insertShopCar",$.param({
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token"),
                        goods_id:$location.search()['dd'],
                        goods_num:$(".content") .val(), 
                        //merchants_id: 1 , //$scope.goods.goodsDetail['merchants_id'],
                        //car_type:'goods',
                        specification_id: $scope.listInfo.specification_id
                        //goods_parameters:$scope.goods.parameterstext1
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        tips2("添加成功",800);
                        $scope.goods.zezaoFun(0);
                    } else if (data['status'] == 'error') {
                        tips2(data['error'],800)
                    } else if (data["status"] == "pending" && data["error"] == "token failed") {
                        $scope.login(); //登入失效
                    }
                })
            },
            goConfirmhtml: function () {  //  跳转下单页
                //添加购物车
                $http.post($scope.urls+"/shopCarInterfaces.api?insertShopCar",$.param({
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token"),
                        goods_id:$location.search()['dd'],
                        goods_num: $(".content") .val(),
                        //merchants_id: 1 , //$scope.goods.goodsDetail['merchants_id'],
                        //car_type:'goods',
                        specification_id: $scope.listInfo.specification_id
                        //goods_parameters:$scope.goods.parameterstext1
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        $scope.goods.carid=parseInt(data['data'].car_id);
                        $scope.goods.gocart();
                        //tips2("添加成功",800);
                        $scope.goods.zezaoFun(0);
                    } else if (data['status'] == 'error') {
                        tips2(data['error'],800)
                    } else if (data["status"] == "pending" && data["error"] == "token failed") {
                        $scope.login(); //登入失效
                    }
                })

                $scope.goods.parameter=[];
                $scope.goods.parameterid=[];
                //$scope.goods.endprice=0;
                //for(var i=0;i<$(".parameterList").length;i++){
                    console.log($scope.listInfo.specification_name);
                    console.log($scope.goods.specIdArr);
                    //$scope.goods.parameter.push($scope.listInfo.specification_name); //规格名称
                    $scope.goods.parameterid.push($scope.goods.specIdArr); //规格id
                    //$scope.goods.endprice+=parseFloat($(".parameterList").eq(i).find(".parameterTag li.act").attr('price')); //总价
                //}
                //$scope.goods.endprice=$scope.goods.endprice+parseFloat($scope.goods.goodsDetail['goods_now_price']);
                // $location.path("/confirm_order").search({dd:$location.search()['dd'],parametername:$scope.goods.parameter,parameterid:$scope.goods.parameterid,num:$(".nums .content").val(),endprice:$scope.goods.endprice,member_id:$cookieStore.get('member_id'),member_token:$cookieStore.get('member_token')})
                
                // APP适配  '&endprice='+$scope.goods.endprice+ 地址传总价  '&parametername='+$scope.goods.parameter+ 地址传规格名称
                // $scope.APPurl=$scope.urls+'app-mall1/index.html#/confirm_order?dd='+$location.search()['dd']+'&parameterid='+$scope.goods.parameterid+'&num='+$(".content").val()+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                
            },
            gocart: function () { //判断跳转
                $scope.APPurl=$scope.urls+'app-mall1/index.html#/confirm_order?carid='+$scope.goods.carid+'&num='+$(".content") .val()+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                //window.location.href=$scope.APPurl;
                //判断是否是微信浏览器的函数
                // var ua = window.navigator.userAgent.toLowerCase();
                // if(ua.match(/MicroMessenger/i) == 'micromessenger'){
                //     //window.location.href=$scope.APPurl;
                // }

                var u = navigator.userAgent;
                if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                    JavaScript:android.appGoConfirm($scope.APPurl)
                } else
                if (u.indexOf('iPhone') > -1) {//苹果手机
                    appGoConfirm($scope.APPurl);
                }
            }
        }
        $scope.goods.Goodsfn();
    })
    // 确认订单
    .controller('confirm_order',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('确认订单')
        //查询地址列表
        $scope.addressList = function(){
            $http.post($scope.urls+"addressInterfaces.api?getMemberAddressList",$.param({
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
        $scope.addressObj={

        }
        $scope.address = function (id,name,mobile,province,city,district,detailed_address) {
            $scope.addressObj.id=id;
            $scope.addressObj.name=name;
            $scope.addressObj.mobile=mobile;
            $scope.addressObj.province=province;
            $scope.addressObj.city=city;
            $scope.addressObj.district=district;
            $scope.addressObj.detailed_address=detailed_address;
        }
        $scope.zezao1=0, //控制地址列表遮罩
        $scope.zezao1Fun = function (d) {
            $scope.zezao1=d;
        }
        $scope.addressList();
        $scope.affirm={
            json: {
                car_ids: $location.search()['carid'],
                // is_deduct_integral:'',
                // member_coupon_id:'',

                orderBeans:[{
                    order_remark:'',
                    // merchants_id:'',
                    invoice_type:'no',
                    orderGoodsBeans:[]
                }] //订单Beans
            },

            carid: $location.search()['carid'],
            //caridL: $scope.affirm.carid,            
            dd: $location.search()['dd'],
            defaultAddress:function () {  //  默认地址
                console.log($scope.affirm.json);
                $http.post($scope.urls+"/addressInterfaces.api?getDefaultAddress",$.param({
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token")
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data);
                    if(data["status"]=="ok"){
                        $scope.affirm.address=data['data'];
                        $scope.addressObj.name=data['data'].address_name;
                        $scope.addressObj.mobile=data['data'].address_mobile;
                        $scope.addressObj.province=data['data'].address_province;
                        $scope.addressObj.city=data['data'].address_city;
                        $scope.addressObj.district=data['data'].address_district;
                        $scope.addressObj.detailed_address=data['data'].address_detail;
                        $scope.affirm.json.address_id=data['data']['address_id']?data['data']['address_id']:0; //json 地址id
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    } else if (data["status"] == "pending" && data["error"] == "token failed") {
                        var u = navigator.userAgent;
                        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                            JavaScript:android.appGOlogin()
                        } else
                        if (u.indexOf('iPhone') > -1) {//苹果手机
                            appGOlogin()
                        }
                    }
                })
            },
            total1:0, //最终订单价格
            point_num:0, //用户积分数量
            point_proportion:0, //积分抵扣比例饿
            point_money:0, //积分可以抵扣的最大金额 用于呈现在界面
            point_money1:0, //选中积分抵扣时抵扣金额 用于计算抵扣的订单价格
            point:function () {  //  积分抵扣额度
                $http.post($scope.urls+"/memberInterfaces.api?getUserIntegral",$.param({ //获取用户积分
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token")
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data);
                    if(data["status"]=="ok"){
                        $scope.affirm.point_num=data['data'];
                        console.log(parseInt($scope.affirm.point_num));
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    } else if (data["status"] == "pending" && data["error"] == "token failed") {
                        
                    }
                });
                // $http.post($scope.urls+"/othersInterfaces.api?getSetting",$.param({ //获取积分抵扣比例
                //         setting_name: 'integral_deduction'
                //     }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                // ).success(function(data){
                //     console.log(data);
                //     if(data["status"]=="ok"){
                //         $scope.affirm.point_proportion=data['data'].setting_value;
                //         console.log(parseFloat($scope.affirm.point_proportion));
                //         $timeout(function(){
                //             $scope.affirm.pointMoney();
                //         },20);
                //     } else if (data['status'] == 'error') {
                //         console.log(data)
                //     } else if (data["status"] == "pending" && data["error"] == "token failed") {
                        
                //     }
                // });
                
            },
            zezao:0, //控制优惠券列表遮罩
            zezaoFun: function(d,type1){
                $scope.affirm.zezao=d;
                
            },
            couponLists:0,
            coupon: function(){ //获取优惠券列表
                $http.post($scope.urls+"couponInterfaces.api?getCouponList",$.param({
                    member_id: $cookieStore.get('member_id'),
                    member_token: $cookieStore.get('member_token'),
                    coupon_state: 'not_used',
                    page: 1
                    
                  }), {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }}
                ).success(function(data) {
                    console.log(data);
                    if(data['status']=="ok"){
                        $scope.affirm.couponList=data["data"];
                        $scope.affirm.couponLists=data["data"].length;
                        for(var i=0;i<$scope.affirm.couponLists;i++){
                            // console.log($scope.affirm.couponList[i].coupon_full_price);
                            // console.log($scope.affirm.total);
                            // console.log(parseFloat($scope.affirm.total)parseFloat($scope.affirm.couponList[i].coupon_full_price))
                        }
                        //$scope.affirm.couponsFun();
                    } else if (data['status'] == 'error') {
                        console.log(data['status']);
                    } else if (data['data'] == 'token failed') {
                    }
                })
            },
            // coupons:[],
            // couponsFun: function () {
            //     for(var i=0;i<$scope.affirm.couponLists;i++){
            //         if($scope.affirm.couponList[i].coupon_full_price <= $scope.affirm.total){
            //             $scope.affirm.coupons.push($scope.affirm.couponList[i]);
            //         }
            //     }
            //     console.log($scope.affirm.coupons);
            // },
            couponId:'', //选中的优惠券ID
            couponIndex:-1,  //控制选中优惠券图标样式变化
            couponMoney:0, //优惠券优惠金额
            couponMoney1:0, //缓存价格
            onCoupon: function (id,index,money) {
                $scope.affirm.couponId=id;
                $scope.affirm.couponIndex=index;
                $scope.affirm.couponMoney1=parseFloat(money).toFixed(2);
                console.log($scope.affirm.couponMoney1)
            },

            onCoupon1: function(){ //点击确认按钮时，控制优惠券优惠金额显示在界面
                $scope.affirm.couponMoney=$scope.affirm.couponMoney1;
                $scope.affirm.totals();
                $scope.affirm.json.member_coupon_id=$scope.affirm.couponId;
            },
            pointMoney: function () { //计算积分能抵扣的最大金额
                $scope.affirm.point_money=(parseInt($scope.affirm.point_num)*parseFloat($scope.affirm.point_proportion)).toFixed(2);
                console.log(parseInt($scope.affirm.point_num)*parseFloat($scope.affirm.point_proportion));
            },
            num:$location.search()['num'],
            listl:' ', //存放订单里有几件商品商品
            cartlist: function () {  //  商品列表
                //$scope.affirm.json.orderBeans=[];
                if($location.search()['carid']){  //购物车
                    $http.post($scope.urls+"/shopCarInterfaces.api?getShopCarList",$.param({
                            member_id:$cookieStore.get("member_id"),
                            member_token:$cookieStore.get("member_token"),
                            car_ids: $location.search()['carid']
                        }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                    ).success(function(data){
                        console.log(data);
                        $("#loading").fadeOut();
                        if(data["status"]=="ok"){
                            $scope.affirm.list=data['data'];
                            $scope.affirm.listl=$scope.affirm.list.length;
                            
                            if($location.search()['num']){ //判断是否直接购买，如果直接购买使商品数量为1，  type = 1 为直接购买,
                                $scope.affirm.numfncart1(data['data'][0].car_id,data['data'][0].specification_price,$location.search()['num']);
                            }
                            $timeout(function(){
                                 $scope.affirm.totals();  //刚进页面时计算价格
                            },50);

                            // console.log($scope.affirm.json);
                            // $scope.affirm.list=data['data']; //店铺
                            // $scope.affirm.listl=$scope.affirm.list.shoppingCarBeans.length; //商品数量
                            // for (var i=0;i<$scope.affirm.list.length;i++){
                            //     var store={};
                            //     store.merchants_id=$scope.affirm.list[i]['merchants_id'];
                            //     store.remark=$(".store_list").eq(i).find('.remark').val();
                            //     store.orderGoodsBeans=[];
                            //     for( var j=0;j<$scope.affirm.list[i].shoppingCarBeans.length;j++){
                            //         goods = {};
                            //         goods.goods_id=$scope.affirm.list[i].shoppingCarBeans[j]['goods_id'];
                            //         goods.goods_num=$scope.affirm.list[i].shoppingCarBeans[j]['goods_num'];
                            //         goods.orderParameterBeans=[];
                            //         store.orderGoodsBeans.push(goods);
                            //         for(var z=0;z<$scope.affirm.list[i].shoppingCarBeans[j].goodsParameterBeans.length;z++){
                            //             Parameter={};
                            //             Parameter.parameter_id=$scope.affirm.list[i].shoppingCarBeans[j].goodsParameterBeans[z]['parameter_id'];
                            //             Parameter.parameter_price=$scope.affirm.list[i].shoppingCarBeans[j].goodsParameterBeans[z]['parameter_price'];
                            //             goods.orderParameterBeans.push(Parameter);
                            //         }
                            //         $scope.affirm.total=$scope.affirm.total+parseInt($scope.affirm.list[i].shoppingCarBeans[j]['goods_num'])*parseFloat($scope.affirm.list[i].shoppingCarBeans[j]['car_totla_price'])
                            //         console.log($scope.affirm.list[i].shoppingCarBeans[j]['car_totla_price'])
                            //         console.log($scope.affirm.total)
                            //     }
                            //     $scope.affirm.json.orderBeans.push(store);
                            // }
                            //$scope.affirm.total=$scope.affirm.total.toFixed(2)
                        } else if (data['status'] == 'error') {
                            console.log(data)
                        }
                    })
                }else if($location.search()['dd']){  //直接购买
                    $http.post($scope.urls+"/goodsInterfaces.api?getOneGoodsDetail",$.param({
                            member_id:$cookieStore.get("member_id"),
                            goods_id: $location.search()['dd']
                        }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                    ).success(function(data){
                        console.log(data);
                        console.log($scope.affirm.json);
                        if(data["status"]=="ok"){
                            $scope.affirm.detail=data['data'];
                            $scope.affirm.totals();  //页面加载时计算价格

                            // $scope.affirm.detail['parameter_name']=$location.search()['parametername'];
                            // $scope.affirm.detail['parameterid']=$location.search()['parameterid'].split(",");
                            // $scope.affirm.detail['end_price']=$location.search()['endprice'];
                            //store={};
                            //store.merchants_id=data['data']['merchants_id'];
                            //.merchants_id=1;
                            //store.orderGoodsBeans=[];
                            //goods={};
                            //goods.goods_id=$location.search()['dd'];
                            //goods.goods_num=$location.search()['num'];
                            // goods.orderParameterBeans=[];
                            // for(var i=0;i<data['data']['goodsParameterBeans'].length;i++){
                            //     Parameter={};
                            //     for(var j=0;j<data['data']['goodsParameterBeans'][i]['goodsParameterBeans'].length;j++){
                            //         for(var z=0;z<data['data']['parameterid'].length;z++){
                            //             if(data['data']['goodsParameterBeans'][i]['goodsParameterBeans'][j]['parameter_id']==data['data']['parameterid'][z]){
                            //                 Parameter.parameter_id=data['data']['goodsParameterBeans'][i]['goodsParameterBeans'][j]['parameter_id']
                            //                 Parameter.parameter_price=data['data']['goodsParameterBeans'][i]['goodsParameterBeans'][j]['parameter_price']
                            //                 goods.orderParameterBeans.push(Parameter);
                            //             }
                            //         }
                            //     }
                            // }
                            //store.orderGoodsBeans.push(goods);
                            //$scope.affirm.json.orderBeans.push(store);
                            //$scope.affirm.total=(parseFloat($location.search()['endprice'])*$location.search()['num']).toFixed(2)
                        } else if (data['status'] == 'error') {
                            console.log(data)
                        }
                    });
                    $http.post($scope.urls+"/goodsInterfaces.api?getGoodsSpecificationByList",$.param({
                            specification_list: $location.search()['parameterid']
                        }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                    ).success(function(data){
                        console.log(data);
                        if(data["status"]=="ok"){
                            $scope.listInfo=data['data'];
                            $scope.affirm.total=(parseFloat($scope.listInfo.specification_price)*$location.search()['num']).toFixed(2)//商品价格
                        } else if (data['status'] == 'error') {
                            console.log(data)
                        }
                    })
                }
            },
            invoices:'',
            onInvoiceI: function (type) {  //选中发票 积分出现选中状态  1 发票  2 积分  
                if(type==1){
                    if($("#fp").hasClass("onInvoiceI")){
                        $("#fp").removeClass("onInvoiceI");
                        $scope.affirm.json.orderBeans.invoice_type='no';
                        $scope.affirm.json.orderBeans.invoice_company_name='';
                    }else{
                        $("#fp").addClass("onInvoiceI");
                        $scope.affirm.json.orderBeans.invoice_type='paper';
                        $scope.affirm.json.orderBeans.invoice_company_name=$scope.affirm.invoices;
                    }
                }else if(type==2){
                    if($("#jf").hasClass("onInvoiceI")){
                        $("#jf").removeClass("onInvoiceI");
                        $scope.affirm.point_money1=0;
                        $scope.affirm.json.is_deduct_integral=0;

                    }else{
                        $("#jf").addClass("onInvoiceI");
                        $scope.affirm.point_money1=$scope.affirm.point_money;
                        $scope.affirm.json.is_deduct_integral=1;
                    }
                    $scope.affirm.totals();
                }
            },
            totals: function () {   //计算选中的商品价格
                $scope.affirm.total=0;
                $scope.affirm.leng=$(".goods_list").length;
                for(var i=0;i<$scope.affirm.listl;i++){
                    //$scope.affirm.GoodsPrice=parseInt($('.goods_list').eq(i).find(".numN").val())*parseFloat($('.goods_list').eq(i).find(".goodsPrice").text());
                    $scope.affirm.total=parseFloat(parseFloat($scope.affirm.total)+parseFloat($('.goods_list').eq(i).find(".goodsPrice").text())).toFixed(2);
                    //console.log($('.goods_list').eq(i).find(".goodsPrice").text())
                }
                console.log($scope.affirm.total)
                $scope.affirm.total1=parseFloat($scope.affirm.total-$scope.affirm.point_money1).toFixed(2);//减去积分后的价格
                //减去优惠券后的价格
                $scope.affirm.total1=parseFloat($scope.affirm.total1-$scope.affirm.couponMoney).toFixed(2);

            },
            numfncart: function(price,max,carId,type){ // 修改购物车数量  type:1减少2增加
                $("#loading").fadeIn();
                $scope.affirm.Num=parseInt($("#CarNum"+carId).val());
                // console.log($("#CarNum"+carId).val())
                // console.log(carId)
                // console.log(type)
                $scope.affirm.MaxNum=parseInt(max);
                $scope.affirm.Price=parseFloat(price);
                if(max==0){
                    tips2("库存不足",800)
                    return false;
                }else if(type==1){
                    if ($scope.affirm.Num <= 1) {
                        $scope.affirm.Num=1;
                    }else{
                        if ($scope.affirm.Num >  $scope.affirm.MaxNum) {
                            $scope.affirm.Num=$scope.affirm.MaxNum;
                        }else{
                            $scope.affirm.Num--;
                        }
                    }
                }else if(type==2){
                    if ($scope.affirm.Num >=  $scope.affirm.MaxNum) {
                        $scope.affirm.Num=$scope.affirm.MaxNum;
                    }else{
                        $scope.affirm.Num++;
                    }
                }else{
                    if ($scope.affirm.Num < 1 || isNaN($scope.affirm.Num * 1)) {
                        $scope.affirm.Num = 1
                    }else if($scope.affirm.Num > $scope.affirm.MaxNum){
                        $scope.affirm.Num = $scope.affirm.MaxNum;
                    }
                }
                $scope.shop_cars = [{
                    car_id:carId,
                    goods_num:$scope.affirm.Num
                }];
                $http.post($scope.urls+"/shopCarInterfaces.api?updateShopCarList",$.param({
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token"),
                        shop_cars: JSON.stringify($scope.shop_cars)
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data);
                    $("#loading").fadeOut();
                    if(data["status"]=="ok"){
                        $("#CarNum"+carId).val($scope.affirm.Num);
                        $("#goodsPrice"+carId).html($scope.affirm.Price*$scope.affirm.Num); //调整数量之后计算单个商品价格
                        //angular.document("#goodsPrice"+carId).innerHTML
                        console.log($scope.affirm.Price*$scope.affirm.Num);
                        //调整数量之后优惠券清除
                        $scope.affirm.couponMoney=0;
                        $scope.affirm.json.member_coupon_id='';
                        $scope.affirm.totals();  //调整数量之后计算总价格
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            numfncart1: function(carId,price,num){ // 立即购买使商品数量为1  
                $scope.shop_cars1 = [{
                    car_id:carId,
                    goods_num:num
                }];  
                price = parseFloat(price);
                $http.post($scope.urls+"/shopCarInterfaces.api?updateShopCarList",$.param({
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token"),
                        shop_cars: JSON.stringify($scope.shop_cars1)
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data);
                    if(data["status"]=="ok"){
                        $("#CarNum"+carId).val(num);
                        $("#goodsPrice"+carId).html(price * num); //调整数量之后计算单个商品价格
                        console.log(price * num);
                        //调整数量之后优惠券清除
                        $scope.affirm.totals();  //调整数量之后计算总价格
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            // numfn: function(type,max){
            //     $scope.affirm.num=parseInt($(".numN") .val());
            //     $scope.affirm.max=parseInt(max);
            //     if(type==1){
            //         if ($scope.affirm.num <= 1) {
            //             $scope.affirm.num=1
            //         }else{
            //             if ($scope.affirm.num >  $scope.affirm.max) {
            //                 $scope.affirm.num=$scope.affirm.max;
            //             }else{
            //                 $scope.affirm.num--;
            //             }
            //         }
            //     }else if(type==2){
            //         if ($scope.affirm.num >=  $scope.affirm.max) {
            //             $scope.affirm.num=$scope.affirm.max;
            //         }else{
            //             $scope.affirm.num++;
            //         }
            //     }else if(type==3){
            //         if ($scope.affirm.num < 1 || isNaN($scope.affirm.num * 1)) {
            //             $scope.affirm.num = 1
            //         }else if($scope.affirm.num > $scope.affirm.max){
            //             $scope.affirm.num = $scope.affirm.max;
            //         }
            //     }
            //     $(".numN") .val($scope.affirm.num);
            //     $scope.affirm.total=($scope.affirm.num*parseFloat($scope.listInfo.specification_price)).toFixed(2);
            //     $scope.affirm.json.orderBeans[0]['orderGoodsBeans'][0]['goods_num']=$scope.affirm.num;
            // },
            state: 1,
            goods:{},
            jsons:{},
            AffirmOrder:function (type) {  //  获取订单json信息
                $("#loading").fadeIn();
                //$scope.affirm.json.orderBeans.merchants_id=1;
                $scope.affirm.json.orderBeans[0].order_remark=$(".remark").val();
                $scope.affirm.json.member_id = $cookieStore.get('member_id');
                // $scope.affirm.json.order_type= "goods";
                $scope.affirm.json.address_id=$scope.addressObj.id?$scope.addressObj.id:$scope.affirm.json.address_id;
                console.log($scope.affirm.json.address_id)
                $http.post($scope.urls+"/shopCarInterfaces.api?getShopCarList",$.param({
                            member_id:$cookieStore.get("member_id"),
                            member_token:$cookieStore.get("member_token"),
                            car_ids: $location.search()['carid']
                        }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                    ).success(function(data){
                        console.log(data);
                        $("#loading").fadeOut();

                        if(data["status"]=="ok"){
                            $scope.affirm.list=data['data'];
                            $scope.affirm.listl=$scope.affirm.list.length;
                            for( var j=0;j<$scope.affirm.list.length;j++){
                                $scope.affirm.goods = {};
                                $scope.affirm.goods.goods_id=$scope.affirm.list[j]['goods_id'];
                                $scope.affirm.goods.goods_num=$scope.affirm.list[j]['goods_num'];
                                $scope.affirm.goods.specification_id=$scope.affirm.list[j]['specification_id'];
                                //goods.orderParameterBeans=[];
                                $scope.affirm.json.orderBeans[0].orderGoodsBeans.push($scope.affirm.goods);

                            }
                            //$scope.affirm.json.orderBeans[0].merchants_id=data['data'][0].merchants_id;
                            //$scope.affirm.jsons=JSON.stringify($scope.affirm.json);
                            console.log($scope.affirm.json)
                            //console.log(JSON.stringify($scope.affirm.json))
                            if(type==1){
                                if ($scope.affirm.json.address_id ) {
                                    $scope.affirm.AffirmOrder1();
                                }else{
                                    //$scope.affirm.AffirmOrder2();
                                    tips2('请添加地址', 1300)
                                }
                            }
                        } else if (data['status'] == 'error') {
                            tips2(data['error'],800);
                        } else if (data["status"] == "pending" && data["error"] == "token failed") {
                            
                        }
                    })
            },
            AffirmOrder2:function () {  //  计算订单价格
                console.log(JSON.stringify($scope.affirm.json))
                $http.post($scope.urls+"/orderInterfaces.api?getOrderPrice",$.param({
                            member_id:$cookieStore.get("member_id"),
                            member_token:$cookieStore.get("member_token"),
                            json: JSON.stringify($scope.affirm.json)
                        }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                    ).success(function(data){
                        console.log(data);
                        if(data["status"]=="ok"){
                            $scope.affirm.list=data['data'];
                        } else if (data['status'] == 'error') {
                            tips2(data['error'],800);
                        } else if (data["status"] == "pending" && data["error"] == "token failed") {
                            
                        }
                    })
            },
            AffirmOrder1: function(){ //提交订单
                //$scope.affirm.state=2;
                $('loading').fadeIn();
                console.log(JSON.stringify($scope.affirm.json))
                $http.post($scope.urls+"/orderInterfaces.api?insertOrder",$.param({
                        member_id: $cookieStore.get("member_id"),
                        member_token: $cookieStore.get("member_token"),
                        json: JSON.stringify($scope.affirm.json)
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data);
                    $('loading').fadeOut();
                    $scope.er=data;
                    // tips2(data['data'],800)
                    if(data["status"]=="ok"){
                        $scope.APPurl=$scope.urls+'/app-mall1/index.html#/pay_order?o='+data['data'].order_ids+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        $(".Submit-order").attr("href","JavaScript:android.appGoPay(\""+$scope.APPurl+"\")");
                        $(".Submit-order").attr("onclick","appGoPay(\""+$scope.APPurl+"\")");
                        ////window.location.href=$scope.APPurl;
                        //判断是否是微信浏览器的函数
                        // var ua = window.navigator.userAgent.toLowerCase();
                        // if(ua.match(/MicroMessenger/i) == 'micromessenger'){
                        //     //window.location.href=$scope.APPurl;
                        // }
                        $("#loading").fadeOut();
                        console.log(data['data'].order_ids)
                        var u = navigator.userAgent;
                        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                            JavaScript:android.appGoPay(data['data'].order_ids)
                        } else
                        if (u.indexOf('iPhone') > -1) {//苹果手机
                            appGoPay(data['data'].order_ids);
                        }
                        console.log($scope.APPurl)
                    } else if (data['status'] == 'error') {
                        console.log(data);
                    } else if (data["status"] == "pending" && data["error"] == "token failed") {
                        var u = navigator.userAgent;
                        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                            JavaScript:android.appGOlogin()
                        } else
                        if (u.indexOf('iPhone') > -1) {//苹果手机
                            appGOlogin()
                        }
                    }
                })
                //tips2(1,800)
            }
        }
        $scope.affirm.cartlist();
        $scope.affirm.defaultAddress();
        $scope.affirm.point();
        //$scope.affirm.coupon();
    })
        // 商品评论列表
    .controller('pinglun_good',function ($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore) {
        console.log('商品评论列表');
        $scope.pingfen={
            pingfenfun:function () {
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsDetail",$.param({
                        member_id:$cookieStore.get("member_id"),
                        goods_id:$location.search()['md'],
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    $scope.pingfen.goodsDetail=data.data;
                    //商品总评分
                    $scope.pingfen.zpf=parseFloat(($scope.pingfen.goodsDetail.goods_star1+$scope.pingfen.goodsDetail.goods_star2+$scope.pingfen.goodsDetail.goods_star3)/3).toFixed(2);
                    console.log($scope.pingfen.zpf)
            })
        }}
        $scope.pingfen.pingfenfun();
        $scope.pinglun={
            md:$location.search()['md'],
            // goods_mail_score:$location.search()['goods_mail_score'],
            pinglunlist:[],
            pinglun:function () {

                $http.post($scope.urls+"/goodsInterfaces.api?getAssessmentList",$.param({
                        goods_id:$location.search()['md']
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function (data) {
                    console.log(data);
                    $scope.pinglun.pinglunlist=data.data;
                    // $scope.goods.pingjialist[0].memberBean.member_account=$scope.goods.pingjialist[0].memberBean.member_account.slice(0,3)+"****"+$scope.goods.pingjialist[0].memberBean.member_account.slice(7,11);
                    // $scope.APPurl_pinglun=$scope.urls+'app-mall1/index.html#/pinglun_good?md='+$scope.goods.goodsDetail['goods_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');

                })
            }
    }
            $scope.pinglun.pinglun();
    })
    // 商品分类
    .controller('goodsList',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('商品列表');
        $scope.pageNum=0;
        $scope.district1; //区
        if($location.search().add=='失败'){ //判断是否定位成功
            $scope.district1='';
        }else{
            $scope.district1 = $location.search().add;
        }
        $scope.obj={
            uuid:$location.search()['uuid'],
            img:$location.search().img,
            Levellist:[],
            bannerlist:[],
            banner: function () {  // banner
                $http.post($scope.urls+"/bannerInterfaces.api?getAllBanners",$.param({
                        banner_position: 'good_class'}),
                    {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        for (var i=0;i<data['data'].length;i++){
                            if(data['data'][i].banner_type=='common'){
                                $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i]['goods_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                                $(".banners .swiper-wrapper").append('<div class="swiper-slide"><a class="di_bl" href="JavaScript:android.appGoodsdetail(\''+$scope.APPurl+'\')"  onclick="appGoodsdetail(\''+$scope.APPurl+'\')"><img src="'+data['data'][i]['banner_img']+'" alt=""></a></div>');
                            }else if(data['data'][i].banner_type=='merchants'){
                                $scope.APPurl=$scope.urls+'app-mall1/index.html#/store_goodslist?md='+data['data'][i]['merchants_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                                $(".banners .swiper-wrapper").append('<div class="swiper-slide"><a class="di_bl" href="JavaScript:android.appGoodsdetail(\''+$scope.APPurl+'\')"  onclick="appGoodsdetail(\''+$scope.APPurl+'\')"><img src="'+data['data'][i]['banner_img']+'" alt=""></a></div>');
                            }
                        }
                        var Myswiper = new Swiper('.swiper-container',{
                            pagination:'.swiper-pagination',
                            direction:"horizontal",
                            observer:true,
                            observeParents:true,
                            loop:true,
                            loopAdditionalSlides:1,
                            autoplay:2000,
                            autoplayDisableOnInteraction:false,
                        });
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            Level: function (type,page) {  // 分类
                
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsClassLevel",$.param({
                    parent_id:-1,
                    level:1,
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        $scope.obj.Levellist=data['data'];
                        for(var i=0;i<$scope.obj.Levellist.length;i++){
                            if($scope.obj.Levellist[i]['goods_uuid']==$scope.obj.uuid){
                                console.log(i)
                                $scope.obj.uuidindex=i
                            }
                        }
                        // $timeout(function () {
                        //     $('#nav').navbarscroll({
                        //         defaultSelect:$scope.obj.uuidindex,
                        //         endClickScroll:function(obj){
                        //             // console.log(obj.text())
                        //         }
                        //     });
                        // },100)
                        if(!page){
                            $(".shop_list").empty();
                        }
                        if(type==1){
                            $scope.obj.Goods($scope.obj.uuid,page);
                        }else if(type==2){
                            $scope.obj.Goods1($scope.obj.uuid,page);
                        }else if(type==3){
                            $scope.obj.Goods2($scope.obj.uuid,page);
                        }
                        console.log($scope.obj.uuid)
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            clicktag: function (uuid) {
                $("#loading").fadeIn();
                console.log(uuid);
                $(".shop_list").html('');
                $scope.obj.Goods(uuid,1);
            },
            // Goods: function (uuid,page) {  // 列表
            //     $scope.pages= page || 1;
            //     $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
            //             class_id:uuid||$scope.obj.uuid,
            //             page:$scope.pages
            //         }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
            //     ).success(function(data){
            //         $("#loading").fadeOut()
            //         console.log(data);
            //         $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
            //         $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有数据了" : $scope.modelstext = "";
            //         for (var i = 0; i < data['data'].length; i++) {

            //             $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i].goods_id+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
            //             $(".shop_list").append('<li class="wntj_goods back_fff">'+
            //                ' <a class="di_bl" href="'+$scope.APPurl+'" >'+
            //                    ' <img src="'+data['data'][i]['goods_img']+'" alt="" class="di_bl m0a">'+
            //                     '<p class="wntj_goodsName text_ellipsis_2 f-12">'+data['data'][i]['goods_name']+'</p>'+
            //                    ' <p class="wntj_goodsPrice f-14">¥'+data['data'][i]['goods_price']+
            //                    '<span class="icon_goods_cart" ng-click=""></span></p></a></li>')
            //         }
            //         console.log($scope.pages)
            //         console.log($scope.pageNum)
            //         if(data["status"]=="ok"){
            //         } else if (data['status'] == 'error') {
            //             console.log(data)
            //         }
            //     })
            // },
            type:0,
            Goods: function (page) {  // 列表
                $scope.obj.type=1;
                $scope.pages= page || 1;
                sessionStorage.setItem('sortType1', 1);
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
                        class_id: $scope.obj.uuid,
                        page:$scope.pages,
                        send_range:'',// $scope.district1
                        show_type: 'app'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    $("#loading").fadeOut()
                    console.log(data);
                    $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
                    $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有数据了" : $scope.modelstext = "";
                    for (var i = 0; i < data['data'].length; i++) {

                        $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i].goods_id+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        $(".shop_list").append('<li class="shop_listli">' +
                                '<a href="JavaScript:android.appGoGoods(\''+$scope.APPurl+'\')" onclick="appGoGoods(\''+$scope.APPurl+'\')" class="di_bl">' +
                                //'<a href="'+$scope.APPurl+'" class="di_bl">' +
                                '<div class="shop_listImg l mr-10">' +
                                '<img src="'+data['data'][i].goods_img+'" alt="">' +
                                '</div>' +
                                '<div class="shop_listDetail l">' +
                                '<p class="shop_listName text_ellipsis_2 f-14 col_333 hei_40 w_b94">'+data['data'][i]['goods_name']+'</p>' +
                                '<p class="shop_listCurrentPrice f-15 flex-between">'+
                                '<span class="f-12 col_red"><span class="col_red price" leng="en">&#165</span>'+data['data'][i]['goods_now_price']+'</span>'+
                                '<span class="shop_listPj f-10 col_999 mr-10 text_ellipsis ">'+data['data'][i]['total_sales']+'人购买</span>' +
                                '</p>' +
                                '</div></a></li>')
                    }
                    console.log($scope.pages)
                    console.log($scope.pageNum)
                    if(data["status"]=="ok"){
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            Goods1: function (page) {  // 列表
                $scope.pages= page || 1;
                $scope.obj.type=2;
                console.log($scope.obj.type)
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
                        class_id:  $scope.obj.uuid,
                        order: 'sales',
                        page: $scope.pages,
                        send_range:'',// $scope.district1
                        show_type: 'app'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    $("#loading").fadeOut()
                    console.log(data);
                    $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
                    $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有数据了" : $scope.modelstext = "";
                    for (var i = 0; i < data['data'].length; i++) {

                        $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i].goods_id+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        $(".shop_list").append('<li class="shop_listli">' +
                                '<a href="JavaScript:android.appGoGoods(\''+$scope.APPurl+'\')" onclick="appGoGoods(\''+$scope.APPurl+'\')" class="di_bl">' +
                                //'<a href="'+$scope.APPurl+'" class="di_bl">' +
                                '<div class="shop_listImg l mr-10">' +
                                '<img src="'+data['data'][i].goods_img+'" alt="">' +
                                '</div>' +
                                '<div class="shop_listDetail l">' +
                                '<p class="shop_listName text_ellipsis_2 f-14 col_333 hei_40 w_b94">'+data['data'][i]['goods_name']+'</p>' +
                                '<p class="shop_listCurrentPrice f-15 flex-between">'+
                                '<span class="f-12 col_red"><span class="col_red price" leng="en">&#165</span>'+data['data'][i]['goods_now_price']+'</span>'+
                                '<span class="shop_listPj f-10 col_999 mr-10 text_ellipsis">'+data['data'][i]['total_sales']+'人购买</span>' +
                                '</p>' +
                                '</div></a></li>')
                    }
                    console.log($scope.pages)
                    console.log($scope.pageNum)
                    if(data["status"]=="ok"){
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            Goods2: function (page) {  // 列表
                $scope.pages= page || 1;
                $scope.obj.type=3;
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
                        class_id: $scope.obj.uuid,
                        order:'price',
                        page:$scope.pages,
                        send_range:'',// $scope.district1
                        show_type: 'app'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    $("#loading").fadeOut()
                    console.log(data);
                    $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
                    $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有数据了" : $scope.modelstext = "";
                    for (var i = 0; i < data['data'].length; i++) {

                        $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i].goods_id+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        $(".shop_list").append('<li class="shop_listli">' +
                                '<a href="JavaScript:android.appGoGoods(\''+$scope.APPurl+'\')" onclick="appGoGoods(\''+$scope.APPurl+'\')" class="di_bl">' +
                                //'<a href="'+$scope.APPurl+'" class="di_bl">' +
                                '<div class="shop_listImg l mr-10">' +
                                '<img src="'+data['data'][i].goods_img+'" alt="">' +
                                '</div>' +
                                '<div class="shop_listDetail l">' +
                                '<p class="shop_listName text_ellipsis_2 f-14 col_333 hei_40 w_b94">'+data['data'][i]['goods_name']+'</p>' +
                                '<p class="shop_listCurrentPrice f-15 flex-between">'+
                                '<span class="f-12 col_red"><span class="col_red price" leng="en">&#165</span>'+data['data'][i]['goods_now_price']+'</span>'+
                                '<span class="shop_listPj f-10 col_999 mr-10 text_ellipsis">'+data['data'][i]['total_sales']+'人购买</span>' +
                                '</p>' +
                                '</div></a></li>')
                    }
                    console.log($scope.pages)
                    console.log($scope.pageNum)
                    if(data["status"]=="ok"){
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            }
        },
        // $scope.obj.banner();
        // $scope.obj.Level(1);  //调用分类列表
        $scope.sortType = function (type, page) {
            if(!page){
                $(".shop_list").empty();
            }
            if(type==1){
                $scope.obj.Goods(page);
            }else if(type==2){
                $scope.obj.Goods1(page);
            }else if(type==3){
                $scope.obj.Goods2(page);
            }
        }
        
        /*判断从详情返回时保持原状态*/
        if (sessionStorage.getItem('sortType1') && sessionStorage.getItem('sortType1') != 'no'){
            $scope.sortType(sessionStorage.getItem('sortType1'));
        } else {
            $scope.sortType(1);
        }
        
        //滚动加载
        $(".main").scroll(function () {
            var winHeight = null, winWidth = null, mTop = null, wTop = null, result = null, oTop = null, bTop = null, dTop = null;
            wTop = $('.main').scrollTop();  // 滚动的距离
            bTop = $(".main").height();  // 盒子的高度
            dTop = $('.shop_list').height();  // 盒子内容的高度
            if (wTop + bTop >= dTop) {//下拉到底部加载
                if ($scope.pageNum > $scope.pages) {
                    $scope.sortType($scope.obj.type, ++$scope.pages);
                }
            }
        })
    })
    // 商品分类(积分商品)
    .controller('goodsListIntegral',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('商品列表');
        $scope.pageNum=0;
        $scope.district1; //区
        if($location.search().add=='失败'){ //判断是否定位成功
            $scope.district1='';
        }else{
            $scope.district1 = $location.search().add;
        }
        $scope.obj={
            uuid:$location.search()['uuid'],
            img:$location.search().img,
            clicktag: function (uuid) {
                $("#loading").fadeIn();
                console.log(uuid);
                $(".shop_list").html('');
                $scope.obj.Goods(uuid,1);
            },
            
            type:0,
            Goods: function (page) {  // 列表
                $scope.obj.type=1;
                $scope.pages= page || 1;
                sessionStorage.setItem('sortType1', 1);
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
                        class_id: $scope.obj.uuid,
                        page:$scope.pages,
                        send_range:'',// $scope.district1
                        show_type: 'app',
                        buy_type: 'integral'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    $("#loading").fadeOut()
                    console.log(data);
                    $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
                    $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有数据了" : $scope.modelstext = "";
                    for (var i = 0; i < data['data'].length; i++) {

                        $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i].goods_id+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        $(".shop_list").append('<li class="shop_listli">' +
                                '<a href="JavaScript:android.appGoGoods(\''+$scope.APPurl+'\')" onclick="appGoGoods(\''+$scope.APPurl+'\')" class="di_bl">' +
                                //'<a href="'+$scope.APPurl+'" class="di_bl">' +
                                '<div class="shop_listImg l mr-10">' +
                                '<img src="'+data['data'][i].goods_img+'" alt="">' +
                                '</div>' +
                                '<div class="shop_listDetail l">' +
                                '<p class="shop_listName text_ellipsis_2 f-14 col_333 hei_40 w_b94">'+data['data'][i]['goods_name']+'</p>' +
                                '<p class="shop_listCurrentPrice f-15 flex-between">'+
                                '<span class="f-12 col_red"><span class="col_red price" leng="en">&#165</span>'+data['data'][i]['goods_now_price']+'</span>'+
                                '<span class="shop_listPj f-10 col_999 mr-10 text_ellipsis ">'+data['data'][i]['total_sales']+'人购买</span>' +
                                '</p>' +
                                '</div></a></li>')
                    }
                    console.log($scope.pages)
                    console.log($scope.pageNum)
                    if(data["status"]=="ok"){
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            Goods1: function (page) {  // 列表
                $scope.pages= page || 1;
                $scope.obj.type=2;
                console.log($scope.obj.type)
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
                        class_id:  $scope.obj.uuid,
                        order: 'sales',
                        page: $scope.pages,
                        send_range:'',// $scope.district1
                        show_type: 'app',
                        buy_type: 'integral'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    $("#loading").fadeOut()
                    console.log(data);
                    $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
                    $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有数据了" : $scope.modelstext = "";
                    for (var i = 0; i < data['data'].length; i++) {

                        $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i].goods_id+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        $(".shop_list").append('<li class="shop_listli">' +
                                '<a href="JavaScript:android.appGoGoods(\''+$scope.APPurl+'\')" onclick="appGoGoods(\''+$scope.APPurl+'\')" class="di_bl">' +
                                //'<a href="'+$scope.APPurl+'" class="di_bl">' +
                                '<div class="shop_listImg l mr-10">' +
                                '<img src="'+data['data'][i].goods_img+'" alt="">' +
                                '</div>' +
                                '<div class="shop_listDetail l">' +
                                '<p class="shop_listName text_ellipsis_2 f-14 col_333 hei_40 w_b94">'+data['data'][i]['goods_name']+'</p>' +
                                '<p class="shop_listCurrentPrice f-15 flex-between">'+
                                '<span class="f-12 col_red"><span class="col_red price" leng="en">&#165</span>'+data['data'][i]['goods_now_price']+'</span>'+
                                '<span class="shop_listPj f-10 col_999 mr-10 text_ellipsis">'+data['data'][i]['total_sales']+'人购买</span>' +
                                '</p>' +
                                '</div></a></li>')
                    }
                    console.log($scope.pages)
                    console.log($scope.pageNum)
                    if(data["status"]=="ok"){
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            Goods2: function (page) {  // 列表
                $scope.pages= page || 1;
                $scope.obj.type=3;
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsList",$.param({
                        class_id: $scope.obj.uuid,
                        order:'price',
                        page:$scope.pages,
                        send_range:'',// $scope.district1
                        show_type: 'app',
                        buy_type: 'integral'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    $("#loading").fadeOut()
                    console.log(data);
                    $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
                    $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有数据了" : $scope.modelstext = "";
                    for (var i = 0; i < data['data'].length; i++) {

                        $scope.APPurl=$scope.urls+'app-mall1/index.html#/goods_detail?dd='+data['data'][i].goods_id+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        $(".shop_list").append('<li class="shop_listli">' +
                                '<a href="JavaScript:android.appGoGoods(\''+$scope.APPurl+'\')" onclick="appGoGoods(\''+$scope.APPurl+'\')" class="di_bl">' +
                                //'<a href="'+$scope.APPurl+'" class="di_bl">' +
                                '<div class="shop_listImg l mr-10">' +
                                '<img src="'+data['data'][i].goods_img+'" alt="">' +
                                '</div>' +
                                '<div class="shop_listDetail l">' +
                                '<p class="shop_listName text_ellipsis_2 f-14 col_333 hei_40 w_b94">'+data['data'][i]['goods_name']+'</p>' +
                                '<p class="shop_listCurrentPrice f-15 flex-between">'+
                                '<span class="f-12 col_red"><span class="col_red price" leng="en">&#165</span>'+data['data'][i]['goods_now_price']+'</span>'+
                                '<span class="shop_listPj f-10 col_999 mr-10 text_ellipsis">'+data['data'][i]['total_sales']+'人购买</span>' +
                                '</p>' +
                                '</div></a></li>')
                    }
                    console.log($scope.pages)
                    console.log($scope.pageNum)
                    if(data["status"]=="ok"){
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            }
        },
        // $scope.obj.banner();
        // $scope.obj.Level(1);  //调用分类列表
        $scope.sortType = function (type, page) {
            if(!page){
                $(".shop_list").empty();
            }
            if(type==1){
                $scope.obj.Goods(page);
            }else if(type==2){
                $scope.obj.Goods1(page);
            }else if(type==3){
                $scope.obj.Goods2(page);
            }
        }
        
        /*判断从详情返回时保持原状态*/
        if (sessionStorage.getItem('sortType1') && sessionStorage.getItem('sortType1') != 'no'){
            $scope.sortType(sessionStorage.getItem('sortType1'));
        } else {
            $scope.sortType(1);
        }
        //滚动加载
        $(".main").scroll(function () {
            var winHeight = null, winWidth = null, mTop = null, wTop = null, result = null, oTop = null, bTop = null, dTop = null;
            wTop = $('.main').scrollTop();  // 滚动的距离
            bTop = $(".main").height();  // 盒子的高度
            dTop = $('.shop_list').height();  // 盒子内容的高度
            if (wTop + bTop >= dTop) {//下拉到底部加载
                if ($scope.pageNum > $scope.pages) {
                    $scope.sortType($scope.obj.type, ++$scope.pages);
                }
            }
        })
    })
    // 店铺-商品列表
    .controller('store_goodslist',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('店铺-商品列表');
        $scope.pageNum=0;
        $scope.store={
            Storefn:function () {  //  店铺详情
                $http.post($scope.urls+"/merchantsInterfaces.api?getOneMerchantsDetail",$.param({
                    member_id:$cookieStore.get("member_id"),
                    merchants_id:$location.search()['md'],
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        $scope.store.storeDetail=data['data'];
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            clicktag: function (page,recommend,news,type,way) {
                $(".shop_list").html('');
                $("#loading").fadeIn();
                $scope.store.Goodsfn(page,recommend,news,type,way);
            },
            Goodsfn:function (page,recommend,news,type,way) {  //  商品列表
                console.log(page)
                console.log(recommend)
                console.log(news)
                console.log(type)
                console.log(way)
                $scope.pages= page || 1;
                $http.post($scope.urls+"/merchantsInterfaces.api?getMerchantsGoodss",$.param({
                    merchants_id:$location.search()['md'],
                    goods_uuid:$location.search()['uuid']||'',
                    page:$scope.pages,
                    is_recommend:recommend,
                    is_new:news,
                    sort_type:type,
                    sort_way:way
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data);
                    if(data["status"]=="ok"){
                        $("#loading").fadeOut();
                        $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
                        $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有数据了" : $scope.modelstext = "";
                        $scope.store.news=news;
                        $scope.store.recommend=recommend;
                        $scope.store.type=type;
                        $scope.store.way=way;
                        console.log($scope.pages+'分页')
                        console.log($location.search()['uuid']||''+'uuid')
                        console.log($location.search()['md']+'店铺id')
                        console.log($scope.store.recommend+'推荐')
                        console.log($scope.store.news+'新品')
                        console.log($scope.store.type+'状态')
                        console.log($scope.store.way+'序')
                        for(var i=0;i<data['data'].length;i++){
                            $scope.APPurl=$scope.urls+'/app-mall1/index.html#/goods_detail?dd='+data['data'][i]['goods_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                            $(".shop_list").append('<li class="shop_listli">' +
                                '<a href="JavaScript:android.appGoodsdetail(\''+$scope.APPurl+'\')" onclick="appGoodsdetail(\''+$scope.APPurl+'\')" class="di_bl">' +
                                '<div class="shop_listImg l mr-10">' +
                                '<img src="{{g.goods_img}}" alt="">' +
                                '</div>' +
                                '<div class="shop_listDetail l">' +
                                '<p class="shop_listName text_ellipsis_2 f-14 col_333 hei_40">'+data['data'][i]['goods_name']+'</p>' +
                                '<p class="shop_listYieldly f-12">'+data['data'][i]['goods_address']+'</p>' +
                                '<p class="shop_listCurrentPrice f-15">¥'+data['data'][i]['goods_now_price']+'</p>' +
                                '<p class="f-10">' +
                                // '<span class="shop_listPj f-10 col_999 mr-10">'+data['data'][i]['assessment_count']+'评价</span>' +
                                '<sapn class="shop_listXl f-10 col_999">'+data['data'][i]['year_sales']+'销量</sapn>' +
                                '</p>' +
                                '</div></a></li>')
                        }
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            Collectionfn: function (d) {  // 收藏
                $http.post($scope.urls+"/collectionInterfaces.api?insertCollection",$.param({
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token"),
                        relation_id:d,
                        collection_type:'merchants'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    $scope.store.storeDetail.is_collection=1;
                    $scope.store.storeDetail.collection_id=data['data'];
                    if(data["status"]=="ok"){
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            cancelCollectionfn: function (d) {  // 取消收藏
                $http.post($scope.urls+"/collectionInterfaces.api?cancelCollection",$.param({
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token"),
                        collection_id:d,
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    $scope.store.storeDetail.is_collection=0;
                    if(data["status"]=="ok"){
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            }
        }
        $scope.store.Storefn();
        $scope.store.Goodsfn(1,1,'','','');
        //滚动加载
        $(".main").scroll(function () {
            var winHeight = null, winWidth = null, mTop = null, wTop = null, result = null, oTop = null, bTop = null, dTop = null;
            wTop = $('.main').scrollTop();  // 滚动的距离
            bTop = $(".main").height();  // 盒子的高度
            dTop = $('.shop_list').height();  // 盒子内容的高度
            if (wTop + bTop >= dTop) {//下拉到底部加载
                if ($scope.pageNum > $scope.pages) {
                    $scope.store.Goodsfn(++$scope.pages,$scope.store.recommend,$scope.store.news,$scope.store.type,$scope.store.way);
                }
            }
        })
        $scope.appstoreDetail=function () {
            $scope.APPurl=$scope.urls+'/app-mall1/index.html#/store_detail?md='+$location.search()['md']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
            console.log($scope.APPurl)
            var u = navigator.userAgent;
            if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                JavaScript:android.appJumpstoreDetail($scope.APPurl)
            } else
            if (u.indexOf('iPhone') > -1) {//苹果手机
                // appJumpstoreDetail($scope.APPurl)
                window.location.href="ios://appJumpstoreDetail?"+$scope.APPurl
            }
        }
        $scope.appgoodslist=function () {
            $scope.APPurl=$scope.urls+'/app-mall1/index.html#/goodsnav?md='+$location.search()['md']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
            var u = navigator.userAgent;
            if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                JavaScript:android.appJumpgoodslist($scope.APPurl)
            } else
            if (u.indexOf('iPhone') > -1) {//苹果手机
                // appJumpgoodslist()
                window.location.href="ios://appJumpgoodslist?"+$scope.APPurl
            }

        }
    })
    // 店铺-所有分类
    .controller('goodsnav',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log("店铺-所有分类")
        $scope.nav={
            md:$location.search()['md'],
            Goodsnav:function () {  // 分类
                $http.post($scope.urls+"/goodsInterfaces.api?getGoodsClassLevel",$.param({
                    parent_id:-1,
                    level:1,
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        $scope.APPurl1=$scope.urls+'/app-mall1/index.html#/store_goodslist?md='+$scope.nav.md+'&uuid='+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        $(".allshop").attr("href","JavaScript:android.appGostore(\""+$scope.APPurl1+"\")")
                        $(".allshop").attr("onclick","appGostore("+$scope.APPurl1+")")
                        for(var i=0;i<data['data'].length;i++){
                            $scope.APPurl=$scope.urls+'/app-mall1/index.html#/store_goodslist?md='+$scope.nav.md+'&uuid='+data['data'][i]['goods_uuid']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                            $(".nav_list").append('<li class="pl-15 b1b_CD">' +
                                '<a href="JavaScript:android.appGostore(\''+$scope.APPurl+'\')" onclick="appGostore(\''+$scope.APPurl+'\')" class="select_spec di_bl">'+data['data'][i]['goods_name']+'</a>' +
                                '</li>')
                        }
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            }
        }
        $scope.nav.Goodsnav()
    })
    // 店铺-详情
    .controller('store_detail',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('店铺-详情');
        $scope.store={
            Storefn:function () {  //  店铺详情
                $http.post($scope.urls+"/merchantsInterfaces.api?getOneMerchantsDetail",$.param({
                        member_id:$cookieStore.get("member_id"),
                        merchants_id:$location.search()['md'],
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    if(data["status"]=="ok"){
                        $scope.store.storeDetail=data['data'];
                        $scope.APPurl=$scope.urls+'/app-mall1/index.html#/store_goodslist?md='+$scope.store.storeDetail['merchants_id']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                        $(".goshop").attr("href","JavaScript:android.appGostore(\""+$scope.APPurl+"\")")
                        $(".goshop").attr("onclick","appGostore("+$scope.APPurl+")")
                        console.log($scope.APPurl);
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            Collectionfn: function (d) {  // 收藏
                $http.post($scope.urls+"/collectionInterfaces.api?insertCollection",$.param({
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token"),
                        relation_id:d,
                        collection_type:'merchants'
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    $scope.store.storeDetail.is_collection=1;
                    $scope.store.storeDetail.collection_id=data['data'];
                    if(data["status"]=="ok"){
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            },
            cancelCollectionfn: function (d) {  // 取消收藏
                $http.post($scope.urls+"/collectionInterfaces.api?cancelCollection",$.param({
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token"),
                        collection_id:d,
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data)
                    $scope.store.storeDetail.is_collection=0;
                    if(data["status"]=="ok"){
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    }
                })
            }
        }
        $scope.store.Storefn();
    })
    // 店铺-执照资质
    .controller('view_aptitude',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('店铺-执照资质');
    })
    
    // 购物车
    .controller('cart',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        $("#loading").fadeIn();
        console.log('购物车');
        $scope.cart={
            // alls: function(){  //  是否全选
            //     if($(".alls").hasClass("che")){
            //         $(".alls").removeClass("che");
            //         $(".store_che").removeClass("che");
            //         $(".goods_che").removeClass("che");
            //     }else{
            //         $(".alls").addClass("che");
            //         $(".store_che").addClass("che");
            //         $(".goods_che").addClass("che");
            //     }
            //     $scope.cart.totals();
            // },
            // gwcact: function(){  //  判断店铺是否全选中了
            //     var index=$(".store_list").length;
            //     var numact=0;
            //     for(var i=0;i<index;i++){
            //         if($(".store_box").find(".store_list").eq(i).find(".store_che").hasClass("che")){
            //             numact++;
            //         }
            //     }
            //     if(numact==index){
            //         $(".alls").addClass("che");
            //     }else{
            //         $(".alls").removeClass("che");
            //     }
            //     $scope.cart.totals();
            // },
            // store: function (m) {  //  店铺全选
            //     if($("#store"+m).hasClass("che")){
            //         $("#store"+m).removeClass("che");
            //         $("#store"+m).parent().next().find(".goods_che").removeClass("che");
            //     }else{
            //         $("#store"+m).addClass("che");
            //         $("#store"+m).parent().next().find(".goods_che").addClass("che");
            //     }
            //     $scope.cart.gwcact()
            // },
            // goods: function (m,index) {  //  商品全选
            //     //console.log($("#store"+m).parent().next().find($("li #goods"+index)))
            //     if($("#store"+m).parent().next().find($("li #goods"+index)).hasClass("che")){
            //         $("#store"+m).parent().next().find($("li #goods"+index)).removeClass("che");
            //     }else{
            //         $("#store"+m).parent().next().find($("li #goods"+index)).addClass("che");
            //     }
            //     /*判断店铺下的商品是否全选中了*/
            //     var indexs=$("#goods"+index).parents(".store_goods").find("li").length;
            //     var numact=0;
            //     for(var i=0;i<indexs;i++){
            //         if($("#store"+m).parent().next().find("li").eq(i).find(".goods_che").hasClass("che")){
            //             numact++;
            //         }
            //     }
            //     if(numact==indexs){
            //         $("#store"+m).addClass("che");
            //     }else{
            //         $("#store"+m).removeClass("che");

            //     }
            //     //$scope.cart.gwcact();
            //     $scope.cart.totals();
            // },
            goods: function (m,index) {  //  选中购物车的商品
                //console.log($("#store"+m).parent().next().find($("li #goods"+index)))
                if($("li #goods"+index).hasClass("che")){  //判断该商品是否已被选中，
                    $("li #goods"+index).removeClass("che");
                }else{
                    $("li #goods"+index).addClass("che");
                }
                /*判断店铺下的商品是否全选中了*/
                // var indexs=$("#goods"+index).parents(".store_goods").find("li").length;
                // var numact=0;
                // for(var i=0;i<indexs;i++){
                //     if($("#store"+m).parent().next().find("li").eq(i).find(".goods_che").hasClass("che")){
                //         numact++;
                //     }
                // } 
                var indexs=$(".store_goods").find("li").length;  //获取购物车所有商品的长度
                var numact=0;  
                for(var i=0;i<indexs;i++){
                    if($("#goods"+[i]).hasClass("che")){  //判断选中的商品数量
                        numact++;
                    }
                }
                if(numact==indexs){  //判断是否全部选中
                    $(".alls").addClass("che");
                }else{
                    $(".alls").removeClass("che");

                }
                //$scope.cart.gwcact();
                $scope.cart.totals();
            },
            carid:[], //存放购物车选中商品的购物车id
            alls: function(){  //  全选商品
                if($(".alls").hasClass("che")){
                    $(".alls").removeClass("che");
                    $(".goods_che").removeClass("che");
                }else{
                    $(".alls").addClass("che");
                    $(".goods_che").addClass("che");
                }
                $scope.cart.totals();
            },
            selected: function () {

            },
            carts:[],
            cartlist: function (page) {  //  购物车列表
                $scope.pages= page || 1;
                $http.post($scope.urls+"/shopCarInterfaces.api?getShopCarList",$.param({
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token"),
                        page: $scope.pages
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data);
                    if(data["status"]=="ok"){
                        $("#loading").fadeOut();
                        $scope.pageNum=Math.ceil(parseInt(data['total'])/10);
                        $scope.pages >= $scope.pageNum ? $scope.modelstext = "没有数据了" : $scope.modelstext = "";
                        for (var i = 0; i < data['data'].length; i++) {
                            $scope.cart.carts.push(data['data'][i])
                        }
                    } else if (data['status'] == 'error') {
                        console.log(data)
                    } else if (data["status"] == "pending" && data["error"] == "token failed") {
                        var u = navigator.userAgent;
                        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                            JavaScript:android.appGOlogin()
                        } else
                        if (u.indexOf('iPhone') > -1) {//苹果手机
                            appGOlogin()
                        }
                    }
                })
            },
            goGoodsDetail: function(goodsId){
                $scope.APPurl1=$scope.urls+'/app-mall1/index.html#/goods_detail?dd='+goodsId+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                //window.location.href=$scope.APPurl1;
                var u = navigator.userAgent;
                if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                    JavaScript:android.appGoGoods($scope.APPurl1)
                } else
                if (u.indexOf('iPhone') > -1) {//苹果手机
                    appGoGoods($scope.APPurl1);
                }
            },
            numfncart: function(price,max,carId,type){ // 修改购物车数量  type:1减少2增加
                $("#loading").fadeIn();
                $scope.cart.Num=parseInt($("#CarNum"+carId).val());
                console.log($("#CarNum"+carId).val())
                console.log(carId)
                console.log(type)
                $scope.cart.MaxNum=parseInt(max);
                $scope.cart.Price=parseFloat(price);
                if(max==0){
                    tips2("库存不足",800)
                    return false;
                }else if(type==1){
                    if ($scope.cart.Num <= 1) {
                        $scope.cart.Num=1;
                    }else{
                        if ($scope.cart.Num >  $scope.cart.MaxNum) {
                            $scope.cart.Num=$scope.cart.MaxNum;
                        }else{
                            $scope.cart.Num--;
                        }
                    }
                }else if(type==2){
                    if ($scope.cart.Num >=  $scope.cart.MaxNum) {
                        $scope.cart.Num=$scope.cart.MaxNum;
                    }else{
                        $scope.cart.Num++;
                    }
                }else{
                    if ($scope.cart.Num < 1 || isNaN($scope.cart.Num * 1)) {
                        $scope.cart.Num = 1
                    }else if($scope.cart.Num > $scope.cart.MaxNum){
                        $scope.cart.Num = $scope.cart.MaxNum;
                    }
                }
                $scope.shop_cars = [{
                    car_id:carId,
                    goods_num:$scope.cart.Num
                }];
                $http.post($scope.urls+"/shopCarInterfaces.api?updateShopCarList",$.param({ // 修改购物车数量
                        member_id:$cookieStore.get("member_id"),
                        member_token:$cookieStore.get("member_token"),
                        shop_cars: JSON.stringify($scope.shop_cars)
                    }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                ).success(function(data){
                    console.log(data);
                    $("#loading").fadeOut();
                    if(data["status"]=="ok"){
                        $("#CarNum"+carId).val($scope.cart.Num);
                        //$("#goodsPrice"+carId).html($scope.cart.Price*$scope.cart.Num);
                        //angular.document("#goodsPrice"+carId).innerHTML
                        console.log($scope.cart.Price*$scope.cart.Num);
                        $scope.cart.totals();
                    } else if (data['status'] == 'error') {
                        tips2(data['error'], 1000)
                    }
                })
            },
            
            totals: function () {   //计算选中的商品价格
                $scope.cart.carid = []; //全选之前清空购物车id
                $scope.cart.buy_type = [];  //储存商品是余额购买还是积分
                $scope.cart.total=0;
                $scope.cart.s=[];
                $scope.cart.leng=$(".goods_list").length;
                for(var i=0;i<$scope.cart.leng;i++){
                    if($(".goods_list").eq(i).find('.goods_che').hasClass("che")){  //选中商品
                        console.log(parseInt($('.goods_list').eq(i).find(".numN").val()));
                        console.log(parseFloat($('.goods_list').eq(i).find(".goodsPrice").text()));

                        //$scope.cart.GoodsPrice=parseInt($('.goods_list').eq(i).find(".numN").val())*parseFloat($('.goods_list').eq(i).find(".goodsPrice").text());
                        $scope.cart.total=parseFloat(parseFloat($scope.cart.total)+parseFloat($('.goods_list').eq(i).find(".goodsPrice").text() * $('.goods_list').eq(i).find(".numN").val())).toFixed(2);
                        $scope.cart.carid.push($('.goods_list').eq(i).find('.goods_che').attr('carid'))
                        $scope.cart.buy_type.push($('.goods_list').eq(i).find('.goods_che').attr('buy_type'))
                    }
                }
                console.log($scope.cart.total)
                console.log($scope.cart.carid)
                console.log($scope.cart.buy_type)
            },
            goConfirmOrder: function () {
                $scope.APPurl=$scope.urls+'app-mall1/index.html#/confirm_order?carid='+$scope.cart.carid.join(",")+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
                console.log($scope.APPurl)
                // $(".go_Clearing").attr("href","JavaScript:android.appGoConfirm(\""+$scope.APPurl+"\")");
                // $(".go_Clearing").attr("onclick","appGoConfirm(\""+$scope.APPurl+"\")");

                //判断是否是微信浏览器的函数
                // var ua = window.navigator.userAgent.toLowerCase();
                // if(ua.match(/MicroMessenger/i) == 'micromessenger'){
                //     //window.location.href=$scope.APPurl;
                // }
                //判断手机
                var u = navigator.userAgent;
                if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                    JavaScript:android.appGoConfirm($scope.APPurl)
                } else
                if (u.indexOf('iPhone') > -1) {//苹果手机
                    appGoConfirm($scope.APPurl);
                }
            },
            goClearing: function () {  //  去结算购物车
                $scope.cart.totals();
                $scope.cart.buy_type1 = 0;
                for (var i = 1; i < $scope.cart.carid.length; i++) {
                    
                    if ($scope.cart.buy_type[0] == $scope.cart.buy_type[i]) {
                        $scope.cart.buy_type1 = 1
                        console.log($scope.cart.buy_type[0] +' '+ $scope.cart.buy_type[i])
                    } else {
                        $scope.cart.buy_type1 = 0
                        console.log($scope.cart.buy_type[0] +' '+ $scope.cart.buy_type[i])
                    }
                }
                console.log('buy_type:' + $scope.cart.buy_type1)
                if($scope.cart.carid.length == 1) {
                    console.log(1)
                    $scope.cart.goConfirmOrder();
                }else if ($scope.cart.carid.length > 1) {
                    if($scope.cart.buy_type1 == 1) {
                        console.log(11)
                        $scope.cart.goConfirmOrder();
                        
                    } else {
                        console.log(12)
                        tips2('积分商品和余额商品请分开购买',1500)
                    }
                }else if($scope.cart.carid.length == 0){
                    tips2('请选择商品',1300)
                }
            },
            delshow: function () {
                $(".total_Column").hide(0);
                $(".del_box").show(0);
            },
            delhide: function () {
                $(".del_box").hide(0);
                $(".total_Column").show(0);
            },
            godel: function () {  //删除购物车商品
                if($scope.cart.carid.length!=0){
                    console.log($scope.cart.carid)
                    $http.post($scope.urls+"/shopCarInterfaces.api?deleteShopCar",$.param({
                            member_id:$cookieStore.get("member_id"),
                            member_token:$cookieStore.get("member_token"),
                            car_ids:$scope.cart.carid.join(","),
                        }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
                    ).success(function(data){
                        console.log(data);
                        if(data["status"]=="ok"){
                            tips2(data['data'],800);
                            $scope.cart.carts=[];
                            $scope.cart.cartlist(1);
                            $timeout(function() {
                                $scope.cart.totals();
                            }, 50);
                            if($(".alls").hasClass('che')){
                                $(".alls").removeClass('che');
                            }
                        } else if (data['status'] == 'error') {
                            console.log(data)
                        }
                    })
                }else{
                    tips2('请选择商品',800)
                }
            }
        }
        $scope.cart.cartlist(1);
    })
    
    // 支付订单
    .controller('pay_order',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('支付订单');
        // $scope.APPurl=$scope.urls+'/app-mall1/index.html#/pay_order?o='+data['data']+'&member_id='+$cookieStore.get('member_id')+'&member_token='+$cookieStore.get('member_token');
        // JavaScript:android.appPay($scope.APPurl);
        // appPay($scope.APPurl);
        $http.post($scope.urls+"/orderInterfaces.api?getOneOrderDetail",$.param({
                member_id:$cookieStore.get("member_id"),
                member_token:$cookieStore.get("member_token"),
                order_id: $location.search()['o']
            }),{headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}}
        ).success(function(data){
            console.log(data);
            $scope.er=data;
            if(data["status"]=="ok"){
                $scope.order_detail=data['data'];
            } else if (data['status'] == 'error') {
                console.log(data);
            } else if (data["status"] == "pending" && data["error"] == "token failed") {
                var u = navigator.userAgent;
                if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
                    JavaScript:android.appGOlogin()
                } else
                if (u.indexOf('iPhone') > -1) {//苹果手机
                    appGOlogin()
                }
            }
        })
        $scope.pays=function () {
            if($(".list-Column p").hasClass("che")==false){
                tips2("请选择支付方式",800)
            }else{
                console.log($location.search()['o'])
                console.log($(".list-Column .che").attr("type"))
                JavaScript:android.appPay($location.search()['o'],$(".list-Column .che").attr("type"));
                appPay($location.search()['o'],$(".list-Column .che").attr("type"));
                // $scope.paymentFun($location.search()['o']);
                // console.log($location.search()['o']);
            }
        }
        // $scope.paymentFun = function(order_id){
        //   $.ajax({
        //     url: $scope.urls +  "orderInterfaces.api?payRealOrders", //接口
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
    })
    // 支付完成
    .controller('pay_finish',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('支付完成');
    })
    // 地址管理
    .controller('address_manage',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('地址管理');
        //查询地址列表
          $scope.addressList = function(){
          $http.post($scope.urls+"addressInterfaces.api?getOwnerAddress",$.param({
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
    })
    // 地址管理
    .controller('share_app',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('分享app');
    })
    // 地址管理
    .controller('share_store',function($scope, $rootScope, $location, $timeout, $http, $cookies, $cookieStore){
        console.log('分享商家');
    })