import Vue from 'vue'
import Router from 'vue-router'
// import index from '@/components/index'
// import leftbar from '@/components/leftBar'
import home from '@/components/home'
import userList from '@/components/userList'
import bookList from '@/components/bookList'
import bookClassify from '@/components/bookClassify'
import addUser from '@/components/addUser'
import changePwd from '@/components/changePwd'

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
        },
        {
            path: '/bookList',
            name: 'bookList',
            component: bookList
        },
        {
            path: '/bookClassify',
            name: 'bookClassify',
            component: bookClassify
        },
        {
            path: '/addUser',
            name: 'addUser',
            component: addUser
        },
        {
            path: '/changePwd',
            name: 'changePwd',
            component: changePwd
        }
    ]
})