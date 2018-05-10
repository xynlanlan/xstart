<template>
  <div class="leftBar">
      <div class="menu_toggle">
          <i class="el-icon-arrow-left"></i>
      </div>
      <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" 
      background-color="#323644" text-color="#fff">
      <router-link :to="{ path: '/' }">
        <el-menu-item index="1">
            <i class="el-icon-location"></i>
            <span slot="title">首页</span>
        </el-menu-item>
         </router-link>
        <router-link :to="{ path: '/userList' }">
            <el-menu-item index="2">
                <i class="el-icon-menu"></i>
                <span slot="title">用户列表</span>
            </el-menu-item>
        </router-link>
        <el-submenu index="3">
            <template slot="title">
                <i class="el-icon-document"></i>
                <span>图书管理</span>
            </template>
            <el-menu-item index="1-1">图书列表</el-menu-item>
            <el-menu-item index="1-2">图书分类</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
            <template slot="title">
                <i class="el-icon-setting"></i>
                <span>设置</span>
            </template>
            <el-menu-item index="1-1">个人信息</el-menu-item>
            <el-menu-item index="1-2">修改密码</el-menu-item>
        </el-submenu>   
        </el-menu>
      <!-- <ul class="list">
        <li class="item"  v-for="(item,$index) in lists" :key="$index">
            <i :class="item.class"></i>
            <span>{{item.text}}</span>
            <i :class="item.icon"></i>
            <ul class="dropdown" id="bookMenu" v-for="(d,$index) in item.dropdown" :key="$index">
                <li class="item">{{d.text}}</li>
            </ul>
        </li>
    </ul> -->
  </div>
</template>

<script>

export default {
  name: 'leftBar',
  data () {
    return {
      toggleFlag: false,
      lists:[
          {text:"首页",class:"el-icon-location"},
          {text:"用户列表",class:"el-icon-menu"},
          {text:"图书管理",class:"el-icon-document",icon:"el-icon-arrow-down",dropdown:[
              {text:"图书列表"},{text:"图书分类"}
          ]},
          {text:"设置",class:"el-icon-setting",icon:"el-icon-arrow-down",dropdown:[
              {text:"个人信息"},{text:"修改密码"}
          ]},
      ]
    }
  },
  methods:{
    handleOpen(key, keyPath) {
        console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
        console.log(key, keyPath);
    },

  }
}
</script>

<style scoped>

.leftBar{
    width: 170px;
    position: fixed;
    bottom: 0;
    top: 60px;
    left: 0;
    right: 170px;
    z-index: 999;
}
.el-aside,.el-menu,.el-menu--inline{
    overflow-x: hidden;
}
.el-menu{
    height: 100%;
}
.menu_toggle{
    height: 30px;
    line-height: 30px;
    background: #4a4f63;
}
ul,li{
    list-style: none;
    text-align: left;
}
.el-menu-vertical-demo{
    border-color: #323644;
}
/* .el-menu-item:hover{
    background: #588391;
}
.active{
    background: #00c2de;
} */


</style>
