<template>
  <section class="wraper">
    <el-container>
      <!-- <eheader></eheader> -->
      <!-- <el-header>
        <div class="logo">
          <i class="el-icon-upload"></i>
          <h1>LOGO</h1>
        </div>
        <h3 class="title">后台管理系统</h3>
        <div class="userBtn">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              <i class="el-icon-info"></i>
              <span class="userName">MMMM</span>
              <i class="el-icon-caret-bottom el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>修改密码</el-dropdown-item>
              <el-dropdown-item>退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>sss
      </el-header> -->
      <!-- <el-container> -->
        <!-- <el-aside width="170px"> -->
          <!-- <left-bar></left-bar> -->
        <!-- </el-aside> -->
        <el-main>
          
          <!-- <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          </el-breadcrumb> -->
          
          <home></home>
        </el-main>
      <!-- </el-container> -->
    </el-container>
  </section>
</template>

<script>
import leftBar from '@/components/leftBar';
import home from '@/components/home';
import userList from '@/components/userList';
import eheader from '@/components/header';
export default {
  name: 'index',
  data () {
    return {
      // currentDate: new Date()
    }
  },
  methods:{

  },
  components:{
    leftBar,
    home,
    userList,
    eheader
  }
}
</script>

<style scoped>
.wraper{
  width: 100%;
  height: 100%;
}
.clearfix{
  content: "";
  clear: both;
  display: block;
}





</style>
