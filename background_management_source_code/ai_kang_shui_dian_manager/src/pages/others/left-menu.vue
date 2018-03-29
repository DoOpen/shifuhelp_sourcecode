<template>
  <div class="menu-root">
    <div v-for="parent in systemModuleBeans" class="parent-div" :key="Math.random()">
      <p @click="menuClick" type="parent" :moduleId="parent.module_id">{{parent.module_name}}</p>
      <div class="child-div" v-show="selectParent==parent.module_id">
        <p @click="menuClick" v-for="child in parent.systemModuleBeans" type="child" :key="Math.random()"
           :style="selectChild==child.module_id?{'background':'#5CB85C'}:{}" :href="child.module_url" :moduleId="child.module_id">{{child.module_name}}</p>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
export default{
  data(){
    return{
     systemModuleBeans:[],
      selectParent:0,
      selectChild:0
    }
  },
  mounted(){
    this.post(1,'systemController.api?getSystemModuleListBySystemAccount');
  },
  methods:{
    doSuccess(index,data){
      switch(index){
        case 1:
          this.systemModuleBeans=data;
          if(!this.isNull(data)&&!this.isNull(data[0].systemModuleBeans)){
            this.selectParent=data[0].module_id;
            this.selectChild=data[0].systemModuleBeans[0].module_id;
            this.$router.push(data[0].systemModuleBeans[0].module_url);
          }
          break;
      }
    },
    menuClick(e){
      if(e.target.getAttribute('type')==='parent'){
        this.selectParent=e.target.getAttribute('moduleId');
        this.systemModuleBeans.forEach((item,index)=>{
          if(item.module_id==this.selectParent){
            if(item.systemModuleBeans.length>0){
              this.selectChild=item.systemModuleBeans[0].module_id;
              this.$router.push(item.systemModuleBeans[0].module_url);
              //清空工具栏的历史信息
              sessionStorage.removeItem('titles');
            }
          }
        });
      }else{
        this.selectChild=e.target.getAttribute('moduleId');
        this.$router.push(e.target.getAttribute('href'));
        //清空工具栏的历史信息
        sessionStorage.removeItem('titles');
      }
    }
  }
}
</script>
<style scoped>
.menu-root{
  background:#2d6da3;
  height:100%;
}
.parent-div>p{
  font-size:16px;
  padding:5px 0 5px 10px;
  background: #2d6da3;
  color:#fff;
  cursor: pointer;
}
  .child-div>p{
    font-size:14px;
    padding:5px 0 5px 20px;
    background: #2d6da3;
    color:#fff;
    cursor: pointer;
  }
  .child-div>p:hover{
    background:#5BC0DE;
  }
</style>
