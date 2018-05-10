import Vue from 'vue'
import Router from 'vue-router'
import index from '@/components/index'
import leftbar from '@/components/leftBar'
import home from '@/components/home'
import userList from '@/components/userList'
import sss from '@/components/sss'

Vue.use(Router)

export default new Router({
    routes: [{
            path: '/',
            name: 'home',
            component: home,
            // redirect: '/home'
        },
        {
            path: '/userList',
            name: 'userList',
            component: userList
        }
    ]
})