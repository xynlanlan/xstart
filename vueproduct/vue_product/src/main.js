// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import FastClick from 'fastclick'
import $ from 'zepto'
import store from './store/store'


Vue.config.productionTip = false
FastClick.attach(document.body);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  // template: '<App/>'  用render函数替换template模板
  render:function(h){
    return h('app')
  }
})
