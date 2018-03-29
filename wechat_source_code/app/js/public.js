// /*返回上次浏览位置*/
//     $(function () {
//       var str = window.location.href;
//       str = str.substring(str.lastIndexOf("/") + 1);
//       console.log(str)
//       console.log($.cookie(str))
//       if ($.cookie(str)) {
//         $(window).scrollTop($.cookie(str));
//       }
//       else {
//       }
//     })
//     $(window).scroll(function () {
//       var str = window.location.href;
//       str = str.substring(str.lastIndexOf("/") + 1);
//       var top = $(document).scrollTop();
//       $.cookie(str, top, { path: '/' });
//       return $.cookie(str);
//     })
//     /*返回上次浏览位置*/