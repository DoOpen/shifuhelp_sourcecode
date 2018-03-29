<template>
  <div class="role-root">
    <toolbar title="角色详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="baseData" :dataSource="roleBean" @change="change" class="detail"></detail>
    <div v-for="(parentModule,parentIndex) in allModule" class="role-content">
      <div class="parent">
        <label>
        <input type="checkbox" :value="parentModule.module_id" @change="parentChange(parentModule)" v-model="myModule"/>
        <h1>{{parentModule.module_name}}</h1>
        </label>
      </div>
      <div class="child-root">
        <div v-for="(childModule,childIndex) in parentModule.systemModuleBeans" class="child">
          <label>
          <input type="checkbox"  :value="childModule.module_id" @change="childChange(parentModule,childModule)" v-model="myModule"/>
          <h1>{{childModule.module_name}}</h1>
          </label>
        </div>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">

  export default {

    data() {
      return {
        baseData: [],
        roleBean:{},
        myModule:[],
        allModule:[],
        selectModule:[],
        toolData:[{name:'保存'}]
      }
    },
    beforeMount() {
      if(this.$route.params.role_id!=0){
        this.post(1, 'systemController.api?getSystemRoleDetail', {role_id: this.$route.params.role_id});
      }
      this.post(2, 'systemController.api?getAllModule');
      this.baseData = [
        {key: 'role_name', name: '角色名称'}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.roleBean = data;
            if(!this.isNull(data.module_ids)){
              this.myModule=data.module_ids.split(',').map ((string)=>{
                return parseInt (string);
                });
            }
            break;
          case 2:
            this.allModule = data;
            //若父模块的任意一个子模块不存在于模块数组中，则将该父模块从数组中移除
            if(!this.isNull(data)){
              data.forEach((parentModule)=>{
                let temp=[];
                let flag=false;
                for(let index in parentModule.systemModuleBeans){
                  let childModuleId=parentModule.systemModuleBeans[index].module_id;
                  if(this.myModule.indexOf(childModuleId)===-1){
                    flag=true;
                  }
                }
                if(flag){
                  this.myModule.forEach((item)=>{
                    if(parentModule.module_id!==item&&temp.indexOf(item)===-1){
                      temp.push(item);
                    }
                  });
                  this.myModule=temp;
                }
              })
            }
            break;
          case 3:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 4:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      parentChange(parentModule){
        //若模块数组中存在这个父模块id则将其所有的子模块id移除，否则将其所有的子模块id添加到模块数组中
        if(this.myModule.indexOf(parentModule.module_id)==-1){
          parentModule.systemModuleBeans.forEach((item)=>{
            if(this.myModule.indexOf(item.module_id)>-1){
              this.myModule.splice(this.myModule.indexOf(item.module_id),1);
            }
          })
        }else{
          parentModule.systemModuleBeans.forEach((item)=>{
            if(this.myModule.indexOf(item.module_id)==-1){
              this.myModule.push(item.module_id);
            }
          })
        }
      },
      childChange(parentModule,childModule){
        //如果模块数组中不存在这个子模块id，则把对应的父模块id从数组中移除
        if(this.myModule.indexOf(childModule.module_id)==-1){
          if(this.myModule.indexOf(parentModule.module_id)>-1){
            this.myModule.splice(this.myModule.indexOf(parentModule.module_id),1);
          }
        }else{
          //判断父模块的所有子模块是否都存在于模块数组中
          let allHave=true;
          parentModule.systemModuleBeans.forEach((item)=>{
            if(this.myModule.indexOf(item.module_id)==-1){
              allHave=false;
            }
          })
          //如果当前父模块的所有子模块都存在数组中，则将父模块选中
          if(allHave){
            if(this.myModule.indexOf(parentModule.module_id)==-1){
              this.myModule.push(parentModule.module_id);
            }
          }else{
            if(this.myModule.indexOf(parentModule.module_id)>-1){
              this.myModule.splice(this.myModule.indexOf(parentModule.module_id),1);
            }
          }
        }
      },
      change(key, value) {
        this.roleBean[key]=value;
      },
      toolClick(index) {
        switch(index){
          case 0:
            //只要父模块中的任意一个子模块存在于数组中，则将该父模块添加到数组中
            this.allModule.forEach((parentModule)=>{
              let childModules=parentModule.systemModuleBeans;
              for(let childIndex in childModules){
                if(this.myModule.indexOf(childModules[childIndex].module_id)>-1){
                  if(this.myModule.indexOf(parentModule.module_id)==-1){
                    this.myModule.push(parentModule.module_id);
                    break;
                  }
                }
              }
            })
            //将模块id数组,先升序排序，之后拼接成字符串
            this.roleBean.module_ids=this.myModule.sort((a,b)=>{
              return a-b;
            }).join(',');
            if (this.isNull(this.roleBean.role_id)) {
              this.post(3, 'systemController.api?insertSystemRole', this.roleBean);
            } else {
              this.post(4, 'systemController.api?updateSystemRole', this.roleBean);
            }
            break;
        }
      },
    }
  }
</script>
<style scoped>
  .detail {
  }

  .parent{
    border-radius: 10px;
    text-align: center;
    font-size: 20px;
    padding:2px;
    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(28, 91, 155, 0.8)), to(rgba(108, 191, 255, 0.9)));
  }
  .child{
    display: inline-block;
    margin:10px 0;
  }
  .parent h1{
    color:#FFF;
  }
  input{
    margin-left: 20px;
    cursor: pointer;
  }
  h1{
    display: inline-block;
    cursor: pointer;
  }
</style>
