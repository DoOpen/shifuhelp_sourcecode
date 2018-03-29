<!--自适应-->
(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            var clientHeight = docEl.clientHeight;
            if (!clientWidth) return;
            if(clientWidth>=640){
                docEl.style.fontSize = '100px';
            }else{
                docEl.style.fontSize = 100 * (clientWidth / 750) + 'px';
            }
        };
    if(!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);
//带刷新的小提示
function tips1(arr){
    $(".Prompt1 .tips").html("");
    $(".Prompt1  .tips").append(arr);
    $(".Prompt1 ").fadeIn();
    setTimeout("$('.Prompt-back').fadeOut(window.location.reload());",1000);
}
//不带刷新的小提示
function tips2(arr,time){
    $(".Prompt1  .tips").html("");
    $(".Prompt1  .tips").append(arr);
    $(".Prompt1 ").fadeIn();
    setTimeout("$('.Prompt-back').fadeOut();",time);
}
//带确认的提示信息
function tips3(arr){
    $(".tips2").html("");
    $(".tips2").append(arr);
    $(".Prompt-back").fadeIn();
}
// function appSearch(i) {  //  跳转-搜索
//     // window.location.href='http://nongye.tstweiguanjia.com/index.html#/cart'
//     window.location.href=i
// }
// function appGoPay(i) {  //  去支付
//     window.location.href=i
// }
// function appGoConfirm(i) {  // 确认订单
//     window.location.href=i
// }
// function appCart(i) {  //  跳转-购物车
//     // window.location.href='http://nongye.tstweiguanjia.com/index.html#/cart'
//     window.location.href=i
// }
// function appGoodslist(i) {  //  跳转分类
//     // window.location.href='http://nongye.tstweiguanjia.com/index.html#/goodsList?uuid='+i
//     window.location.href=i
// }
// function appGoodsdetail(i) {  //  跳转商品详情
//     // window.location.href='http://nongye.tstweiguanjia.com/index.html#/goods_detail?dd='+i
//     window.location.href=i
// }
// function appGostore(i) {  // 跳转-进入店铺
//     // window.location.href='http://nongye.tstweiguanjia.com/index.html#/store_goodslist?md='+i
//     window.location.href=i
// }
// function appJumpstoreDetail(i) {  //  跳转-店铺详情
//     // window.location.href='http://nongye.tstweiguanjia.com/index.html#/store_detail?md='+i
//     window.location.href=i
// }
// function appGoodsnav(i) {  //  跳转-店铺详情
//     // window.location.href='http://nongye.tstweiguanjia.com/index.html#/store_goodslist?md='+i+'&uuid='+uuid
//     window.location.href=i
// }
// function appJumpgoodslist(i) {  //  跳转-所有分类
//     // window.location.href='http://nongye.tstweiguanjia.com/index.html#/goodsnav?md='+i
//     window.location.href=i
// }