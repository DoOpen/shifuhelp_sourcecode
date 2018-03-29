<template>
  <div class="page-root" v-if="total>=10">
    <ul>
      <li>
        <a class="page-cant">{{pageCount}}</a>
      </li>
      <li v-if="page!=1">
        <a @click="goPage(page-1)" class="page-ok">上</a>
      </li>
      <li v-else="">
        <a class="page-disable">上</a>
      </li>
      <li v-for="index in pages">
        <a v-if="page==index" class="page-now" @click="goPage(index)">{{index}}</a>
        <a v-else="" @click="goPage(index)" class="page-ok">{{index}}</a>
      </li>
      <li v-if="page!=pageCount">
        <a @click="goPage(page+1)" class="page-ok">下</a>
      </li>
      <li v-else="">
        <a class="page-disable">下</a>
      </li>
      <li>
        <a class="page-cant">{{total}}</a>
      </li>
    </ul>
    <div class="page-go">
      <editor filed="page" @change="change" type="input" :options="{'width':'80px','height':'25px'}"></editor>
      <my-button name="跳转" @click="goPage(pageGo)"></my-button>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 分页组件
   * page：当前第几页默认1
   * total:共多少条结果默认0
   * limit：每页显示多少条记录：默认10
   */
  import Editor from './editor.vue'
  import MyButton from './button.vue'
  export default {
    components:{
      Editor,MyButton
    },
    props: {
      page: {
        default: 1
      },
      total: {
        default: 0
      },
      limit:{
        default:10
      }
    },
    data() {
      return {
        pageCount: 0,
        pageNum: 11,
        pageGo:''
      }
    },
    methods: {
      goPage(go) {
        if(!this.isNull(go)){
          if(isNaN(go)){
            this.showTip('页数格式非法','danger');
            return;
          }
          if(go<1||go>this.pageCount){
            this.showTip('页数超出范围','danger');
            return;
          }
          if(go==this.page){
            return;
          }
          this.$emit('goPage',go);
        }
      },
      change(filed,value){
          this.pageGo=value;
        }
    },
    computed: {
      pages() {
        let pages = [];
        this.pageCount = Math.ceil(this.total / this.limit);
        for (let i = 1; i <= this.pageCount; i++) {
          if (i >= this.page - (this.pageNum / 2) && this.page <= this.pageCount - this.pageNum) {
            pages.push(i);
          } else if (i > this.pageCount - this.pageNum) {
            pages.push(i);
          }
          if (pages.length >= this.pageNum) {
            break;
          }
        }
        return pages;
      }
    }
  }
</script>
<style scoped>
  .page-root {
    margin: 10px 0;
    text-align: center;
  }

  .page-root ul {
    display: inline-block;
  }

  .page-root ul li {
    border-radius: 10px;
    float: left;
    text-align: center;
    height: 30px;
    margin-left: 5px;
    box-shadow: 10px 5px 30px #2d6da3;
  }

  .page-root ul li:hover {
    background: #ff00ff;
  }

  .page-root ul li a {
    min-width: 50px;
    height: 30px;
    color: white;
    line-height: 30px;
    display: block;
    cursor: pointer;
    border-radius: 10px;
  }

  .page-ok {
    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(28, 91, 155, 0.8)), to(rgba(108, 191, 255, 0.9)));
  }

  .page-now {
    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(217, 83, 79, 0.8)), to(rgba(196, 50, 46, 0.9)));
  }
  .page-ok:hover{

    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(92,184,92, 0.8)), to(rgba(65,150,65, 0.9)));
  }

  .page-cant {
    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(125,38,205, 0.8)), to(rgba(132,112,255, 0.9)));
  }

  .page-disable {
    background: gray;
  }
  .page-go{
    display: flex;
    align-items: center;
    align-content: center;
    justify-content: center;
    margin-top:10px;
  }
</style>
