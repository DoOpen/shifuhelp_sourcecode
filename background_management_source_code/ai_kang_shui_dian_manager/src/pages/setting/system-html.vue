<template>
  <div class="html-root">
    <toolbar title="图文列表" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <list v-bind="this.$data" :dataSource="htmlBeans" @operationClick="operationClick"  @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
export default {
  data(){
    return{
      htmlBeans:[],
      baseData:[],
      operationData:[],
      toolData:[{name:'添加'}],
      page:1,
      total:0,
    }
  },
  beforeMount() {
    this.getHtmlList(1);
    this.baseData = [
      {key: 'html_id', name: 'ID'},
      {key: 'html_name', name: '名称'},
      {key: 'html_url', name: '链接',flex:2},
      {key: 'sort', name: '权重'},
      {key: 'create_time', name: '创建时间'},
      {name: '操作',type:'operation'},
    ];
    this.operationData=[
      {name:'详情'},
      {name:'删除',type:'dialog',message:'确定删除？'}
    ]
  },
  methods: {
    getHtmlList(page){
      this.post(1, 'settingController.api?getHtmlList',{page:page},true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          this.htmlBeans = data.data;
          this.total=data.total;
          break;
        case 2:
          this.showTip('删除成功','success');
          this.getHtmlList(this.page);
          break;
      }
    },
    operationClick(index,rowId){
      switch(index){
        case 0:
          this.$router.push('/system_html_editor/'+this.htmlBeans[rowId].html_id);
          break;
        case 1:
          this.post(2,'settingController.api?deleteHtml',{html_id:this.htmlBeans[rowId].html_id});

          break;
      }
    },
    toolClick(index){
      switch (index){
        case 0:
          this.$router.push('/system_html_editor/'+0);
          break;
      }
    },
    goPage(page){
      this.page=page;
      this.getHtmlList(page);
    }
  }
}
</script>
<style scoped>

</style>
