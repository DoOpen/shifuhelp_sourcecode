<template>
  <div class="finance-root">
    <toolbar title="财务统计" :toolData="toolData" @toolClick="toolClick"/>
    <div class="search-content">
      <my-select title="查询范围" filed="search_type" :dataSource="searchTypeData" selectValue="value" showValue="key" initValue="" @change="paramsChange"/>
      <date-picker title="开始时间" filed="start_time" @change="paramsChange" type="date"/>
      <date-picker title="结束时间" filed="end_time" @change="paramsChange" type="date"/>
    </div>
    <div class="splitter">
      <p>总收益:{{totalPrice}}</p>
    </div>
    <div class="finance-content">
      <div class="finance-child">
        <div class="finance-child-content">
          <p>已取消</p>
          <p>{{finance.cancel_count}}单</p>
          <p>预计可收入</p>
          <p>￥{{finance.cancel_price}}</p>
        </div>
      </div>
      <div class="finance-child">
        <div class="finance-child-content">
          <p>待付款</p>
          <p>{{finance.wait_pay_count}}单</p>
          <p>预计可收入</p>
          <p>￥{{finance.wait_pay_price}}</p>
        </div>
      </div>
      <div class="finance-child">
        <div class="finance-child-content">
          <p>待发货</p>
          <p>{{finance.wait_send_count}}单</p>
          <p>收入</p>
          <p>￥{{finance.wait_send_price}}</p>
        </div>
      </div>
      <div class="finance-child">
        <div class="finance-child-content">
          <p>待收货</p>
          <p>{{finance.wait_receive_count}}单</p>
          <p>收入</p>
          <p>￥{{finance.wait_receive_price}}</p>
        </div>
      </div>
      <div class="finance-child">
        <div class="finance-child-content">
          <p>已收货</p>
          <p>{{finance.wait_assessment_count}}单</p>
          <p>收入</p>
          <p>￥{{finance.wait_assessment_price}}</p>
        </div>
      </div>
      <div class="finance-child">
        <div class="finance-child-content">
          <p>已完成</p>
          <p>{{finance.end_count}}单</p>
          <p>收入</p>
          <p>￥{{finance.end_price}}</p>
        </div>
      </div>
      <div class="finance-child">
        <div class="finance-child-content">
          <p>申请退款</p>
          <p>{{finance.apply_refund_count}}单</p>
          <p>预计退款金额</p>
          <p>￥{{finance.apply_refund_price}}</p>
        </div>
      </div>
      <div class="finance-child">
        <div class="finance-child-content">
          <p>已退款</p>
          <p>{{finance.end_refund_count}}单</p>
          <p>退款金额</p>
          <p>￥{{finance.end_refund_price}}</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data(){
      return{
        searchTypeData:[],
        toolData:[{name:'搜索'}],
        finance:{},
        params:{}
      }
    },
    beforeMount(){
      this.getFinanceStatistics();
      this.searchTypeData=[
        {
          key: '全部',
          value: ''
        },
        {
          key: '今日',
          value: 'day'
        },
        {
          key: '本周',
          value: 'week'
        },
        {
          key: '本月',
          value: 'mouth'
        },
        {
          key: '本年',
          value: 'year'
        }
      ]
    },
    methods:{
      getFinanceStatistics(){
        this.post(1,'orderController.api?getFinanceStatistics',this.params);
      },
      doSuccess(index,data){
        switch (index){
          case 1:
            this.finance=data;
            break;
        }
      },
      paramsChange(key,value){
        this.params[key]=value;
      },
      toolClick(index){
        switch (index){
          case 0:
            this.getFinanceStatistics();
            break;
        }
      }
    },
    computed:{
      totalPrice(){
        if(this.isNull(this.finance)){
          return 0;
        }else{
          return this.finance.wait_send_price+this.finance.wait_receive_price+this.finance.wait_assessment_price+this.finance.end_price -this.finance.end_refund_price;
        }
      }
    }
  }
</script>
<style scoped>
.finance-content{
  display: flex;
  flex-flow: row wrap;
  align-content: center;
  align-items: center;
  justify-content: center;
}
  .finance-child{
    text-align: center;
    width:25%;
    margin-top:20px;
  }
  .finance-child-content{
    width:180px;
    height:220px;
    margin:0 auto;
    border-radius: 20px;
    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(28, 91, 155, 0.8)), to(rgba(108, 191, 255, 0.9)));
    box-shadow:5px 5px 30px #2d6da3;
  }
  .finance-child-content>p{
    color:#ffffff;
    margin-top:10px;
    font-size: 20px;
  }
.finance-child-content>p:nth-child(1){
  border-bottom: 1px #ffffff solid;
  height:40px;
  line-height: 40px;
}
.finance-child-content>p:nth-child(2){
  height:80px;
  line-height: 80px;
  width:80px;
  margin:10px auto 0px auto;
  background:#419641;
  border-radius: 80px;
  border: 1px #ffffff solid;
}
</style>
