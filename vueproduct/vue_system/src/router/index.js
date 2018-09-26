import Vue from 'vue'
import Router from 'vue-router'
import index from '@/components/index'
// import leftbar from '@/components/leftBar'
import login from '@/components/login'
import home from '@/components/home'
import userManagement from '@/components/user/userManagement'
import roleManagement from '@/components/role/roleManagement'
import resourceManagement from '@/components/resource/resourceManagement'
import addUser from '@/components/user/addUser'
import addRole from '@/components/role/addRole'

import bookList from '@/components/book/bookList'
import bookClassify from '@/components/book/bookClassify'
import changePwd from '@/components/user/changePwd'

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
            // name: 'index',
            // meta: {
            //     requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录的
            //     ,meta:{requireAuth: true}
            // },
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
        {
            path: '/',
            redirect: '/login'
        }

    ]
})