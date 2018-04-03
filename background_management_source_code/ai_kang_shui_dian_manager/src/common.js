import Toast from './components/common/toast.vue'
import Dialog from './components/common/dialog.vue'
import Axios from 'axios'
import Qs from 'qs'
const Common = {
  install: function (Vue) {
    Vue.prototype.homeUrl='http://wx.shifuhelp.com/';
    // Vue.prototype.homeUrl='http://aksd.qubaotang.cn/';
    //项目访问前缀
    // Vue.prototype.homeUrl = '/ai_kang_shui_dian/';
    //默认图片上传接口
    Vue.prototype.uploadPath = "settingController.api?uploadImgs";
    //默认显示图片路径
    Vue.prototype.defaultImg = '/images/common/404.png';
    //非父子组件通信中转站
    Vue.prototype.bus = new Vue();
    //判断参数是否为空或者undefined
    Vue.prototype.isNull = isNull;
    //Axios的post请求的再封装
    Vue.prototype.post = post;
    //ajax请求封装
    Vue.prototype.ajaxFileUpload = ajaxFileUpload;
    //请求成功的回调
    Vue.prototype.doSuccess = doSuccess;
    //请求失败的回调
    Vue.prototype.doFiled = doFiled;
    //token验证失败的回调
    Vue.prototype.doPending = doPending;
    //弹出提示信息
    Vue.prototype.showTip = showTip;
    //弹出选择框
    Vue.prototype.showdDialog = showDialog;
    //弹出确认框
    Vue.prototype.showConfirm=showConfirm;
    //随机生成指定长度的字符串
    Vue.prototype.randomString=randomString;
    //获取当前时间
    Vue.prototype.getCurrentDate=getCurrentDate;
    //表单验证
    Vue.prototype.validate=validate;
    //图片压缩
    Vue.prototype.photoCompress=photoCompress;

    //配置Axios的参数
    //响应时间
    Axios.defaults.timeout = 10000;
    //配置请求前缀
    // Axios.defaults.baseURL = '/ai_kang_shui_dian/';
    //返回状态判断(添加响应拦截器)
    Axios.interceptors.response.use((res) => {
      //若响应状态码不是200，就抛出异常
      if (isNull(res)||res.status != '200') {
        return Promise.reject(res);
      }else{
        return res;
      }
    }, (error) => {
      return Promise.reject(error);
    });
    /**
     * 自定义指令自动获得焦点
     */
    Vue.directive('focus', {
      inserted: (el, object) => {
        if (object.value) {
          el.focus();
        }
      }
    })

    /**
     * 判断参数是否为空
     * @param data
     * @returns {boolean}
     */
    function isNull(data) {
      if (data == '' || data == undefined) {
        return true;
      }else if(typeof data=='object'&&Object.keys(data).length == 0){
        return true;
      }else if( typeof data ==Array&&data.length==0){
        return true;
      }else{
        return false;
      }
    }

    /**
     * 随机生成指定长度的字符加数字组合(商品规格SKU)
     * @param len
     * @param charSet
     * @returns {string}
     */
    function randomString(length) {
      let charSet= 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
      var randomString = '';
      for (var i = 0; i < length; i++) {
        var randomPoz = Math.floor(Math.random() * charSet.length);
        randomString += charSet.substring(randomPoz,randomPoz+1);
      }
      return randomString;
    }
    function getCurrentDate() {
      var date = new Date();
      var month = date.getMonth() + 1;
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      var day = date.getDate();
      if (day >= 0 && day <= 9) {
        day = "0" + day;
      }
      let hours=date.getHours();
      if (hours >= 0 && hours <= 9) {
        hours = "0" + hours;
      }
      let minutes=date.getMinutes();
      if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
      }
      let seconds=date.getSeconds();
      if (seconds >= 0 && seconds <= 9) {
        seconds = "0" + seconds;
      }
      var currentdate = date.getFullYear() + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
      return currentdate;
    }
    /**
     * post方式请求数据
     * @param index请求成功后，回调doSuccess(index,data)方法的下标
     * @param url请求的url，与全局属性的homeUrl拼接成完整的访问url
     * @param params请求的额外参数
     * @param type当type为true时duSuccess(index,data)方法传递完整json否则只传递json的数据部分
     */
    function post(index, url, params, type) {
      if (isNull(params)) {
        params = {};
      }
      if(!isNull(params['sort'])){
        try{
          params['sort']=parseInt(params['sort']);
        }catch(error){
          showTip('权重非法!','danger');
          return;
        }
      }else{
        params['sort']=1;
      }

      if (!isNull(sessionStorage.systemAccountBean)) {
        let systemAccountBean = JSON.parse(sessionStorage.systemAccountBean);
        params['account_login_id'] = systemAccountBean.account_login_id;
        params['system_token'] = systemAccountBean.system_token;
        if(!isNull(systemAccountBean.district)){
          params['send_range'] = systemAccountBean.district;
        }
      }
      let config = {
        headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}
      };
      Axios.post(url,Qs.stringify(params),config).then((response) => {
        let data = response.data;
        if (data.status == 'ok') {
          if (!isNull(type) && type == true) {
            this.doSuccess(index, data);
          } else {
            this.doSuccess(index, data.data);
          }
        } else if (data.status === 'pending') {
          this.doPending(index);
        } else {
          this.doFiled(index, data.error);
        }
      }).catch((error) => {
        if(isNull(error.response)){
          this.showTip('程序错误,请查看控制台','danger');
        }else if(error.response.status==500){
          this.showTip('服务器错误','danger');
        }else if(error.response.status==400){
          this.showTip('请求异常','danger');
        }else if(error.response.status==404){
          this.showTip('未知的请求','danger');
        }else if(error.response.status==504){
          this.showTip('服务器没有响应','danger');
        }else{
          this.showTip('未知的错误','danger');
        }
      });
    }

    /**
     * ajax文件上传函数，参数同上
     * @param index
     * @param url
     * @param params
     */
    function ajaxFileUpload(index,params) {
      if (!isNull(sessionStorage.systemAccountBean)) {
        let systemAccountBean = JSON.parse(sessionStorage.systemAccountBean);
        params.append('account_login_id', systemAccountBean.account_login_id);
        params.append('system_token', systemAccountBean.system_token);
      }
      let config = {
        headers:{'Content-Type':'multipart/form-data; boundary=----WebKitFormBoundaryA6FNOAaDnOdIigs4'}
      };
      Axios.post(this.uploadPath, params,config).then((response) => {
        let data = response.data;
        if (data.status === 'ok') {
          this.doSuccess(index, data.data);
        } else if (data.status === 'pending') {
          this.doPending(index);
        } else {
          this.doFiled(index, data.error);
        }
      }).catch((error) => {
        this.showTip('上传失败','danger');
      });
    }

    /**
     * 绑定ajax请求处理函数到vue全局对象中，实际会使用子组件中的doSuccess方法覆盖掉全局的方法
     * @param index
     * @param data
     */

    function doSuccess(index, data) {
    }

    /**
     * 请求失败处理
     * @param index
     * @param error
     */
    function doFiled(index, error) {
      if (!isNull(error)) {
        showTip(error, 'warning');
      } else {
        showTip('未知的请求错误!', 'danger');
      }
    }

    /**
     * token验证失败处理
     * @param index
     */
    function doPending(index) {
      showTip('token验证失败!', 'danger');
      this.$router.push('/');
    }

    /**
     * 消息提示框
     * @param message提示的消息内容
     * @param type有五种不同的提示框颜色背景，primary默认、success成功，info一般，warning警告，danger危险
     */
    function showTip(message, type) {
      let options = {};
      options.message = message;
      let toastBox = Vue.extend(Toast);
      if (!isNull(type)) {
        options.type = type;
      }
      //生成组件
      let instance = new toastBox({
        data: options
      });
      //组件需要挂载在dom元素上
      instance.vm = instance.$mount();
      document.body.appendChild(instance.vm.$el);
      return instance.vm;
    }

    /**
     * 提示框
     * @param message
     * @param type
     * @returns {*}
     */
    function showDialog(type, message,callBack) {
      let options = {};
      options.type=type;
      options.message = message;
      options.callBack=callBack;
      let dialogBox = Vue.extend(Dialog);
      //生成组件
      let instance = new dialogBox({
        data: options
      });
      //组件需要挂载在dom元素上
      instance.vm = instance.$mount();
      document.body.appendChild(instance.vm.$el);
      this.bus.$on('dialogClick',(select)=>{
        callBack(select);
        //监听一次以后就移除这个事件，否则下次会重复收到事件通知
        this.bus.$off('dialogClick');
      });
      return instance.vm;
    }
    /**
     * 确认框
     * @param message
     * @param type
     * @returns {*}
     */
    function showConfirm(title,message,callBack){
      let options = {};
      options.title=title;
      options.message = message;
      options.callBack=callBack;
      let dialogBox = Vue.extend(Dialog);
      //生成组件
      let instance = new dialogBox({
        data: options
      });
      //组件需要挂载在dom元素上
      instance.vm = instance.$mount();
      document.body.appendChild(instance.vm.$el);
      this.bus.$on('dialogClick',(select)=>{
        callBack(select);
        //监听一次以后就移除这个事件，否则下次会重复收到事件通知
        this.bus.$off('dialogClick');
      });
      return instance.vm;
    }

    /**
     * 表单验证
     * @param base
     * @param data
     * @returns {boolean}
     */
    function validate(base,data){
      try{
        for(let i=0;i<base.length;i++){
          let item=base[i];
          if(!this.isNull(item.validate)){
            if(item.validate.indexOf('null')!=-1&&this.isNull(data[item.key])){
              this.showTip(item.name+'不允许为空','info');
              return false;
            }
            if(item.validate.indexOf('int')!=-1&&!/^[0-9]*$/.test(data[item.key])){
              this.showTip(item.name+'只能是正整数','info');
              return false;
            }
            if(item.validate.indexOf('length')!=-1){
              let validate;
              item.validate.split(',').forEach((child)=>{
                if(child.indexOf('length')!=-1){
                  validate=child;
                }
              })
              let length=validate.substring(validate.indexOf('(')+1,validate.indexOf(')'))
              let start,end=0;
              if(length.indexOf('-')!=-1){
                start=length.split('-')[0];
                end=length.split('-')[1];
              }else{
                start=end=length;
              }
              let test=new RegExp('^.{'+start+','+end+'}$');
              if(!test.test(data[item.key])){
                if(start==end){
                  this.showTip(item.name+'长度为【'+start+'】','info');
                }else{
                  this.showTip(item.name+'长度为【'+start+'-'+end+'】','info');
                }
                return false;
              }
            }
            if(item.validate.indexOf('number')!=-1&&!/^[0-9.]*$/.test(data[item.key])){
              this.showTip(item.name+'只能是数字','info');
              return false;
            }
          }
        }
      }catch(error){
        this.showTip('正则格式错误','danger');
        return false;
      }
      return true;
    }

    /**
     * 图片压缩
     * @param file
     * @param w
     * @param objDiv
     */
    function photoCompress(file,w,objDiv){
      var ready=new FileReader();
      /*开始读取指定的Blob对象或File对象中的内容. 当读取操作完成时,readyState属性的值会成为DONE,如果设置了onloadend事件处理程序,则调用之.同时,result属性中将包含一个data: URL格式的字符串以表示所读取文件的内容.*/
      ready.readAsDataURL(file);
      ready.onload=function(){
        var re=this.result;
        canvasDataURL(re,w,objDiv)
      }
    }

    /**
     * 将图片绘制成convas进行压缩
     * @param path
     * @param obj
     * @param callback
     */
    function canvasDataURL(path, obj, callback){
      var img = new Image();
      img.src = path;
      img.onload = function(){
        var that = this;
        // 默认按比例压缩
        var w = that.width,
          h = that.height,
          scale = w / h;
        w = obj.width || w;
        h = obj.height || (w / scale);
        var quality = 0.7;  // 默认图片质量为0.7
        //生成canvas
        var canvas = document.createElement('canvas');
        var ctx = canvas.getContext('2d');
        // 创建属性节点
        var anw = document.createAttribute("width");
        anw.nodeValue = w;
        var anh = document.createAttribute("height");
        anh.nodeValue = h;
        canvas.setAttributeNode(anw);
        canvas.setAttributeNode(anh);
        ctx.drawImage(that, 0, 0, w, h);
        // 图像质量
        if(obj.quality && obj.quality <= 1 && obj.quality > 0){
          quality = obj.quality;
        }
        // quality值越小，所绘制出的图像越模糊
        var base64 = canvas.toDataURL('image/jpeg', quality);
        // 回调函数返回base64的值
        callback(convertBase64UrlToBlob(base64));
      }
    }

    /**
     * 将convas图像转成blob对象
     * @param urlData
     * @returns {Blob}
     */
    function convertBase64UrlToBlob(urlData){
      var arr = urlData.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
      while(n--){
        u8arr[n] = bstr.charCodeAt(n);
      }
      return new Blob([u8arr], {type:mime});
    }
  }

}
export default Common
