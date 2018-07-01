import Vue from 'vue'
import Router from 'vue-router'
import index from '@/components/index'
// import leftbar from '@/components/leftBar'
import login from '@/components/login'
import home from '@/components/home'
import userManagement from '@/components/userManagement'
import roleManagement from '@/components/roleManagement'
import resourceManagement from '@/components/resourceManagement'
import addUser from '@/components/addUser'
import addRole from '@/components/addRole'

import bookList from '@/components/bookList'
import bookClassify from '@/components/bookClassify'
import changePwd from '@/components/changePwd'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/login',
            name: 'login',
            components:{
                login:login
            }
        },
        {
            path: '/index',
            name: 'index',
            components: {
                index:index
            },
            children:[
                {path:'/home',name:'home',components:{content:home}},
                {path:'/userManagement',name:'userManagement',components:{content:userManagement}},
                {path:'/roleManagement',name:'roleManagement',components:{content:roleManagement}},
                {path:'/resourceManagement',name:'resourceManagement',components:{content:resourceManagement}},
                {path:'/bookList',name:'bookList',components:{content:bookList}},
                {path:'/bookClassify',name:'bookClassify',components:{content:bookClassify}},
                {path:'/changePwd',name:'changePwd',components:{content:changePwd}},
                {path:'/addUser',name:'addUser',components:{content:addUser}},
                {path:'/addRole',name:'addRole',components:{content:addRole}},
                {path: '/',redirect: '/home'}
            ]
        },
        // {
        //     path: '/home',
        //     name: 'home',
        //     components: {
        //         content:home
        //     }
        // },
        // {
        //     path: '/userManagement',
        //     name: 'userManagement',
        //     components: {
        //         content:userManagement
        //     }
        // },
        // {
        //     path: '/roleManagement',
        //     name: 'roleManagement',
        //     component: roleManagement
        // },
        // {
        //     path: '/resourceManagement',
        //     name: 'resourceManagement',
        //     component: resourceManagement
        // },
        // {
        //     path: '/addUser',
        //     name: 'addUser',
        //     component: addUser
        // },
        // {
        //     path: '/addRole',
        //     name: 'addRole',
        //     component: addRole
        // },
        // {
        //     path: '/bookList',
        //     name: 'bookList',
        //     component: bookList
        // },
        // {
        //     path: '/bookClassify',
        //     name: 'bookClassify',
        //     component: bookClassify
        // },
        // {
        //     path: '/changePwd',
        //     name: 'changePwd',
        //     component: changePwd
        // },
        {
            path: '/',
            redirect: '/login'
        }

    ]
})