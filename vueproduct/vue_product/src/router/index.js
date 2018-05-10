import Vue from 'vue'
import Router from 'vue-router'
import Steak from '@/components/Steak'
import test from '@/components/test'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Steak',
      component: Steak,
      // children:[
      //   {
      //     path: '/test',
      //     name: 'test',
      //     component: test
      //   }
      // ]
    },
    {
      path: '/test',
      name: 'test',
      component: test
    }
    
  ]
})
