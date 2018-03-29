<template>
  <div class="editor-root">
    <toolbar title="工种信息详情" />
    <detail :baseData="baseData" :dataSource="typeBean" @change="change" :toolData="toolData" @toolClick="toolClick"/>
  </div>
</template>
<script type="text/ecmascript-6">

  export default {

    data() {
      return {
        baseData: [],
        typeBean:this.isNull(this.$route.params.typeBean)?{}:JSON.parse(decodeURIComponent(this.$route.params.typeBean)),
        toolData:[{name:'保存'}],
      }
    },
    beforeMount() {
      this.baseData = [
        {key: 'type_name', name: '名称',validate:'null'},
        {key: 'sort', name: '权重',validate:'null,int'},
        {key: 'create_time', name: '创建时间',disable:true}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      change(key, value) {
        this.typeBean[key]=value;
      },
      toolClick(index) {
        switch(index){
          case 0:
            if(!this.validate(this.baseData,this.typeBean)){
              return;
            }
            if(this.isNull(this.typeBean.type_id)){
              this.post(1, 'memberController.api?insertWorkType', this.typeBean);
            }else{
              this.post(1, 'memberController.api?updateWorkType', this.typeBean);
            }
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
