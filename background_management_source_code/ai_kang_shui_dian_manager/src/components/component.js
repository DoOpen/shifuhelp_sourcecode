/**
 *作者：彭方林
 */
import MyButton from './common/button.vue'
import DatePicker from './common/date-picker.vue'
import Detail from './common/detail.vue'
import Dialog from './common/dialog.vue'
import Editor from './common/editor.vue'
import Level from './common/level.vue'
import List from './common/list.vue'
import MyImage from './common/my-image.vue'
import Page from './common/page.vue'
import SearchBar from './common/search-bar.vue'
import MySelect from './common/select.vue'
import Slider from './common/slider.vue'
import Toast from './common/toast.vue'
import Toolbar from './common/toolbar.vue'
import WangEditor from './common/wang-editor.vue'
import MyVideo from './common/my-video.vue'
import CheckBox from './common/checkbox.vue'
import MyImages from './common/my-images.vue'
const lin={
  install:function(Vue){
    Vue.component('MyButton',MyButton);
    Vue.component('DatePicker',DatePicker);
    Vue.component('Detail',Detail);
    Vue.component('Dialog',Dialog);
    Vue.component('Editor',Editor);
    Vue.component('Level',Level);
    Vue.component('List',List);
    Vue.component('MyImage',MyImage);
    Vue.component('Page',Page);
    Vue.component('SearchBar',SearchBar);
    Vue.component('MySelect',MySelect);
    Vue.component('Slider',Slider);
    Vue.component('Toast',Toast);
    Vue.component('Toolbar',Toolbar);
    Vue.component('WangEditor',WangEditor);
    Vue.component('MyVideo',MyVideo);
    Vue.component('CheckBox',CheckBox);
    Vue.component('MyImages',MyImages);
  }
}
export default lin;
