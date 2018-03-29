<template>
  <div class="picker-root" v-if="!hide">
    <div :class="isNull(title)?'':'picker-title'">
      <p>{{title}}</p>
    </div>
    <div class="picker-content">
      <vue-date-picker :date="initTime" :disabled="disable" :option="option" @change="change"></vue-date-picker>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 日期时间组件
   * type:组件类型 date：日期选择器 datetime：日期时间选择器
   * title:组件左边的文字
   * initValue:初始值
   * filed：表单的字段名
   * disable:为true的时候会被禁用
   * hide:为true的时候隐藏组件
   */
  import VueDatePicker from './vue-datepicker.vue'
  export default {
    components:{
      VueDatePicker
    },
    props: [
      'type','title','initValue','filed','disable','hide'
    ],
    watch:{
      initValue(){
        this.initTime.time=this.initValue;
      }
    },
    methods:{
      change(value){
        this.$emit('change',this.filed,value);
      }
    },
    data () {
      return {
        initTime:{
          time:this.isNull(this.initValue)?'':this.initValue
        },
        startTime: {
          time: ''
        },
        endtime: {
          time: ''
        },
        option: {
          type: this.isNull(this.type)?'min':this.type=='date'?'day':'min',
          week: ['一', '二', '三', '四', '五', '六', '日'],
          month: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
          format: this.isNull(this.type)?'YYYY-MM-DD HH:mm:ss':this.type=='date'?'YYYY-MM-DD':'YYYY-MM-DD HH:mm:ss',
          placeholder: '',
          inputStyle: {
            'display': 'inline-block',
            'height': '25px',
            'width': '200px',
            'border': '1px #2d6da3 solid',
            'border-radius': '5px',
            'color': '#666',
            'text-indent':'5px'
          },
          color: {
            header: '#2d6da3',
            headerText: '#fff'
          },
          buttons: {
            ok: '确定',
            clear:'清空',
            cancel: '取消'
          },
          overlayOpacity: 0.5, // 0.5 as default
          dismissible: true // as true as default
        },
        timeoption: {
          type: 'min',
          week: ['一', '二', '三', '四', '五', '六', '日'],
          month: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
          format: 'YYYY-MM-DD HH:mm'
        },
        multiOption: {
          type: 'multi-day',
          week: ['一', '二', '三', '四', '五', '六', '日'],
          month: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
          format:"YYYY-MM-DD HH:mm"
        },
        limit: [{
          type: 'weekday',
          available: [1, 2, 3, 4, 5,6,7,8]
        }]
      }
    }
  }
</script>
<style scoped>
  .picker-root{
    display: flex;
    align-content: center;
    align-items: center;
    justify-content: flex-start;
  }
  .picker-title{
    width:150px;
    text-align: right;
    margin-right:10px;
  }
  .picker-content{
  }
</style>
