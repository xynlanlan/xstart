<template>
  <div class="leftBar" :class="{isshow:isCollapse}">
      <div class="menu_toggle" @click="changeMenu()">
          <i :class="[isCollapse ? 'el-icon-arrow-right' : 'el-icon-arrow-left']"></i>
      </div>
      <el-menu :default-active="$route.path" :router="true" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" 
      background-color="#323644" text-color="#fff" :collapse="isCollapse">
      <!-- <router-link :to="{ path: '/' }"> -->
        <el-menu-item index="/home">
            <i class="el-icon-location"></i>
            <span slot="title">首页</span>
        </el-menu-item>
         <!-- </router-link> -->
        <el-submenu index="2">
            <template slot="title">
                <i class="el-icon-menu"></i>
                <span>系统管理</span>
            </template>
            <el-menu-item index="/userManagement">用户管理</el-menu-item>
            <el-menu-item index="/roleManagement">角色管理</el-menu-item>
            <el-menu-item index="/resourceManagement">资源管理</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
            <template slot="title">
                <i class="el-icon-document"></i>
                <span>图书管理</span>
            </template>
            <el-menu-item index="/bookList">图书列表</el-menu-item>
            <el-menu-item index="/bookClassify">图书分类</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
            <template slot="title">
                <i class="el-icon-setting"></i>
                <span>设置</span>
            </template>
            <el-menu-item index="/addUser">新增用户</el-menu-item>
            <el-menu-item index="/changePwd">修改密码</el-menu-item>
        </el-submenu>   
        </el-menu>
  </div>
</template>

<script>

export default {
  name: 'leftBar',
  data () {
    return {
      isCollapse: false,
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
    changeMenu(){
        this.isCollapse = !this.isCollapse;
    },
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
    max-width: 170px;
    width: 170px;
    height: auto;
    min-height: 100%;
    flex: 0 0 auto;
    background: #323644;
}
.isshow{
    width: 65px;
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
    overflow-y: hidden;
}
/* .el-menu-item:hover{
    background: #588391;
}
.active{
    background: #00c2de;
} */


</style>
