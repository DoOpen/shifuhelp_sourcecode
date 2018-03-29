<template>
  <div class="editor-root">
    <toolbar title="商品信息详情" :toolData="toolData" @toolClick="toolClick"/>
    <detail :baseData="baseData" :dataSource="goodsBean" header="基本信息" @change="change"/>
    <div class="splitter">
      <p>轮播图</p>
    </div>
    <div class="slider-content">
      <div v-for="(img,index) in goodsImgBeans" class="img-content">
        <my-image :initValue="img.img_url" :not_hover="false" :filed="index" @change="imgChange" :options="{'width':'100px','height':'100px'}" class="slider-img" :disable="index>2" path="/images/goods"/>
        <my-button v-if="index<goodsImgBeans.length-1" name="删除" @click="removeImg(index)"/>
      </div>
    </div>
    <div class="splitter">
      <p>规格编辑</p>
    </div>
    <div class="specification-header">
      <my-button name="移除" @click="removeSpecification" class="insert-specification"/>
      <select ref="parentSpecification" class="specification-select">
        <option v-for="specificationBean in specificationBeans" :value="specificationBean.specification_id">
          {{specificationBean.specification_name}}
        </option>
      </select>
      <my-button name="添加" @click="insertSpecification" class="insert-specification"/>
    </div>
    <div v-for="parent in tempSpecificationBeans" v-if="isShowSpecification(parent)">
      <div class="parent-specification">
        <p>{{parent.specification_name}}</p>
      </div>
      <div class="child-specification">
        <label v-for="(child,index) in parent.specificationBeans" class="specification-label">
          <input type="checkbox" :value="child.specification_id" v-model="checked" @click="checkboxClick"/>
          <p>{{child.specification_name}}</p>
        </label>
      </div>
    </div>
    <list :baseData="specificationBaseData" :dataSource="tempGoodsSpecificationBeans"
          @change="specificationChange"></list>
    <wang-editor :url="goodsBean.goods_url" filed="goods_url_content" header="商品详情" path="/images/goods" @change="change"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        toolData: [{name: '保存'}],
        selectData: [],
        goodsBean: {},
        checked: [],
        initFlag: true,//第一次初始化标识
        selectSpecificationBeans: [],//已选择的规格列表
        specificationBeans: [],//总规格列表
        goodsSpecificationBeans: [],//所有商品规格
        tempSpecificationBeans: [],//规格缓存
        tempGoodsSpecificationBeans: [],
        goodsImgBeans: []
      }
    },
    beforeMount() {
      this.baseData = [
        {key: 'goods_name', name: '名称',validate:'null',},
        {key: 'goods_img', name: '图片', type: 'img', path: '/images/goods'},
        {
          key:'class_id',
          name:'分类',
          type:'level',
          lables:'一级,二级,三级',
          result:'one',
          validate:'null,int',
          childName:'goodsClassBeans',
          selectValue:'class_id',
          showValue:'class_name',
          dataSource:[]
        },
        {key: 'goods_now_price', name: '现价', type: 'text'},
        {key: 'goods_desc', name: '介绍', type: 'textarea'},
        {key: 'goods_stock', name: '库存', type: 'text'},
        {key: 'total_sales', name: '展示销量',type:'text'},
        {key: 'actual_sales', name: '实际销量',type:'text'},
        {
          key: 'goods_state',
          name: '上架状态',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '上架',
              value: '1'
            },
            {
              key: '下架',
              value: '0'
            }
          ]
        },
        {
          key: 'show_type',
          name: '展示位置',
          selectValue: 'value',
          showValue: 'key',
          type: 'check',
          dataSource: [
            {
              key: '师傅端',
              value: 'app'
            },
            {
              key: '微信端',
              value: 'wx'
            }
          ]
        },
        {
          key: 'buy_type',
          name: '购买方式',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '余额',
              value: 'money'
            },
            {
              key: '积分',
              value: 'integral'
            }
          ]
        },
        {key: 'express_price', name: '邮费',tip:'0元包邮'}
      ];
      this.specificationBaseData = [
        {key: 'specification_id', name: 'ID'},
        {key: 'specification_ids', name: '规格组合ID',flex:2},
        {key: 'specification_names', name: '规格组合名称',flex:3},
        {key: 'specification_sales', name: '销量', type: 'input',validate:'int'},
        {key: 'specification_stock', name: '库存', type: 'input',validate:'int'},
        {key: 'specification_price', name: '价格', type: 'input',validate:'number'},
        {
          key: 'specification_state',
          name: '上架状态',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '上架',
              value: '1'
            },
            {
              key: '下架',
              value: '0'
            }
          ]
        },
        {key: 'specification_img', name: '图标', type: 'img', path: '/images/goods'},
      ];
      this.init();
      if (this.$route.params.goods_id != 0) {
        this.post(1, 'goodsController.api?getGoodsDetail', {goods_id: this.$route.params.goods_id});
        this.post(7, 'goodsController.api?getSelectSpecificationList', {goods_id: this.$route.params.goods_id});
        this.post(8, 'goodsController.api?getGoodsSpecificationList', {
          goods_id: this.$route.params.goods_id,
          limit: 2000
        });
      }else{
        this.insertImg();
      }
      this.post(6, 'goodsController.api?getSpecificationList', {parent_id: -1, level: 2, limit: 2000});
      this.post(4, 'goodsController.api?getGoodsClassList', {level:3,parent_id:-1, limit: 2000});
    },
    watch: {
      checked() {
        //当规格复选框改变时，重新生成商品规格列表
        this.flushSpecification();
      }
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.goodsBean = data;
            this.goodsImgBeans=data.goodsImgBeans;
            this.insertImg();
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 3:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 4:
            this.baseData[2].dataSource = data;
            break;
          case 6:
            this.specificationBeans = data;
            if (this.$route.params.goods_id !== 0) {
              this.tempSpecificationBeans = data.slice(0);
            }
            break;
          case 7:
            this.selectSpecificationBeans = data;
            data.forEach((item) => {
              if (this.checked.indexOf(item.specification_id) == -1) {
                this.checked.push(item.specification_id);
              }
            })
            break;
          case 8:
            this.goodsSpecificationBeans = data;
            this.flushSpecification();
            break;
          case 10:
            this.baseData[14].dataSource=data;
            break;
        }
      },
      init(){
        if (this.$route.params.goods_id==0&&!this.isNull(sessionStorage.systemAccountBean)) {
          let systemAccountBean = JSON.parse(sessionStorage.systemAccountBean);
          if(!this.isNull(systemAccountBean.merchantsBean)){
            this.baseData[4].hide=true;
            this.goodsBean.merchants_id=systemAccountBean.merchantsBean.merchants_id;
          }
        }
      },
      change(key, value) {
        this.goodsBean[key] = value;
      },
      specificationChange(rowId, key, value) {
        this.tempGoodsSpecificationBeans[rowId][key] = value;
      },
      imgChange(rowId, value) {
        this.goodsImgBeans[rowId].img_url=value;
        if(rowId==this.goodsImgBeans.length-1){
          this.insertImg();
        }
      },
      insertImg(){
        let img={};
        img.img_url='/images/common/add.jpg';
        this.goodsImgBeans.push(img);
      },
      toolClick(index) {
        switch (index) {
          case 0:
            if(!this.validate(this.baseData,this.goodsBean)){
              return;
            }
            for(let index in this.tempGoodsSpecificationBeans){
              if(!this.validate(this.specificationBaseData,this.tempGoodsSpecificationBeans[index])){
                return;
              }
            }
            let params={};
            params.goods_id=this.goodsBean.goods_id;
            params.goods_name=this.goodsBean.goods_name;
            params.show_type=this.goodsBean.show_type;
            params.buy_type=this.goodsBean.buy_type;
            params.goods_img=this.goodsBean.goods_img;
            params.class_id=this.goodsBean.class_id;
            params.goods_origin_price=this.goodsBean.goods_origin_price;
            params.goods_now_price=this.goodsBean.goods_now_price;
            params.goods_desc=this.goodsBean.goods_desc;
            params.total_sales=this.goodsBean.total_sales;
            params.express_price=this.goodsBean.express_price;
            params.goods_state=this.goodsBean.goods_state;
            params.goods_url=this.goodsBean.goods_url;
            params.goods_url_content=this.goodsBean.goods_url_content;
            params.goods_specifications= JSON.stringify(this.tempGoodsSpecificationBeans);
            params.goods_imgs = JSON.stringify(this.goodsImgBeans.slice(0,this.goodsImgBeans.length-1));
            if (this.isNull(this.goodsBean.goods_id)) {
              this.post(2, 'goodsController.api?insertGoods', params);
            } else {
              this.post(3, 'goodsController.api?updateGoods', params);
            }
            break;
        }
      },
      removeImg(index) {
        let temp=[];
        this.goodsImgBeans.forEach((item,imgIndex)=>{
          if(index!==imgIndex){
            temp.push(item);
          }
        })
        this.goodsImgBeans=temp;
      },
      checkboxClick() {
        this.initFlag = false;
      },
      flushSpecification() {
        this.tempGoodsSpecificationBeans = [];
        //若已选则的规格列表包含商品已有的规格时，将该谷歌加入到商品规格缓存中
        if (this.initFlag) {
          this.goodsSpecificationBeans.forEach((item) => {
            let ids = item.specification_ids.split(',').map((string) => {
              return parseInt(string);
            });
            let have = true;
            for (let id in ids) {
              if (this.checked.indexOf(ids[id]) == -1) {
                have = false;
              }
            }
            if (have) {
              this.tempGoodsSpecificationBeans.push(item);
            }
          })
        }
        //分组得到规格列表，并将未选择的规格去除掉
        let arrays = [];
        this.tempSpecificationBeans.forEach((item) => {
          let parentBeans = [];
          item.specificationBeans.forEach((item1, index) => {
            if (this.checked.indexOf(item1.specification_id) > -1) {
              parentBeans.push(item1);
            }
          })
          if (parentBeans.length > 0) {
            arrays.push(parentBeans);
          }
        })
        //递归求规格的笛卡尔积数组
        let result = [];
        this.toResult(0, [], arrays, result);
        //遍历生成的笛卡尔积数组，生成商品规格
        result.forEach((item) => {
          let params = {};
          params['specification_sku'] = this.randomString(10);
          params['specification_sales'] = 0;
          params['specification_stock'] = 0;
          params['specification_price'] = 0;
          params['specification_state'] = 1;
          params['specification_img'] = '';
          //拼接商品规格id组合，和名称组合
          item.forEach((item1) => {
            if (!params['specification_ids']) {
              params['specification_ids'] = '';
            }
            if (!params['specification_names']) {
              params['specification_names'] = '';
            }
            params['specification_ids'] += (item1.specification_id + ',');
            params['specification_names'] += (item1.specification_name + ',');
          })
          if (params['specification_ids']) {
            params['specification_ids'] = params['specification_ids'].substring(0, params['specification_ids'].length - 1);
          }
          if (params['specification_names']) {
            params['specification_names'] = params['specification_names'].substring(0, params['specification_names'].length - 1);
          }
          //判断商品规格缓存表中是否存在这个规格
          let have = false;
          this.tempGoodsSpecificationBeans.forEach((item) => {
            if (item.specification_ids == params['specification_ids']) {
              have = true;
            }
          })
          //若生成的规格不存在，加入到规格缓存表
          if (params['specification_names'] && !have) {
            this.tempGoodsSpecificationBeans.push(params);
          }
        });
      },
      //递归生成笛卡尔积
      toResult(arrIndex, aresult, arrays, result) {
        if (arrIndex >= arrays.length) {
          result.push(aresult);
          return;
        }
        let aArr = arrays[arrIndex];
        if (!aresult) {
          aresult = new Array();
        }
        for (let i = 0; i < aArr.length; i++) {
          let theResult = aresult.slice(0, aresult.length);
          theResult.push(aArr[i]);
          this.toResult(arrIndex + 1, theResult, arrays, result);
        }
      },
      insertSpecification() {
        this.initFlag = false;
        let insertSpecificationId = this.$refs.parentSpecification.value;
        let have = false;
        this.tempSpecificationBeans.forEach((item, index) => {
          if (item.specification_id == insertSpecificationId) {
            this.showTip('此规格已添加!');
            have = true;
          }
        })
        if (!have) {
          for (let index in this.specificationBeans) {
            if (this.specificationBeans[index].specification_id == insertSpecificationId) {
              this.tempSpecificationBeans.push(this.specificationBeans[index]);
              break;
            }
          }
        }
        this.flushSpecification();
      },
      removeSpecification() {
        this.initFlag = false;
        let removeSpecificationId = this.$refs.parentSpecification.value;
        let have = false;
        let temp = [];
        this.tempSpecificationBeans.forEach((parent) => {
          if (parent.specification_id != removeSpecificationId) {
            temp.push(parent);
          } else {
            have = true;
            parent.specificationBeans.forEach((child, childIndex) => {
              if (this.checked.indexOf(child.specification_id) > -1) {
                this.checked[childIndex] = 0;
              }
            })
          }
        })
        this.tempSpecificationBeans = temp;
        if (!have) {
          this.showTip('不存在此规格!', 'warning');
        }
        this.flushSpecification();
      },
      isShowSpecification(parent) {
        let have = false;
        if (!this.isNull(parent)) {
          parent.specificationBeans.forEach((item) => {
            if (this.checked.indexOf(item.specification_id) > -1) {
              have = true;
            }
          })
        }
        if (have) {
          return true;
        } else {
          if (this.initFlag) {
            let temp = [];
            this.tempSpecificationBeans.forEach((item, index) => {
              if (item.specification_id != parent.specification_id) {
                temp.push(item);
              }
            });
            this.tempSpecificationBeans = temp;
            return false;
          } else {
            return true;
          }
          return false;
        }
      }
    }
  }
</script>
<style scoped>
  .slider-content {
    display: flex;
    justify-content: flex-start;
    align-items: flex-start;
    margin-bottom: 10px;
  }
  .img-content{
    text-align: center;
  }
  .slider-img{
    margin:5px 10px;
  }
  .specification-header {
    padding: 5px 0;
    display: flex;
    justify-content: center;
    align-content: center;
    align-items: center;
  }

  .specification-select {
    height: 26px;
    margin: 0 5px;
    padding-left:5px;
    border-radius: 5px;
    width: 150px;
    line-height: 26px;
    border: 1px #2d6da3 solid;
    display: inline-block;
  }

  .parent-specification {
    background: #5BC0DE;
    border-radius: 5px;
    padding: 2px 0;
    text-align: center;
    color: #fff;
  }

  .child-specification {
    padding: 5px 0px 5px 10px;
  }

  .specification-label {
    display: inline-block;
    margin-left: 15px;
  }

  .specification-label > input {
  }

  .specification-label > p {
    display: inline-block;
  }
</style>
