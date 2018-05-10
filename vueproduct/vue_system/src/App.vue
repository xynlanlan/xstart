<template>
  <div id="app">

    <eheader></eheader>
    <left-bar></left-bar>
    <el-container>
      <el-main>
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          </el-breadcrumb>
          <router-view/>
        </el-main>
    </el-container>
  </div>
</template>

<script>
import eheader from '@/components/header';
import leftBar from '@/components/leftBar';
export default {
  
  name: 'App',
  data () {
    return {
      // currentDate: new Date()
    }
  },
  methods:{

  },
  components:{
    eheader,
    leftBar
  }
}
</script>

<style>
body{
  overflow-y: hidden;
}
*{
  padding: 0;
  margin: 0;
}
a,.router-link-active {
  text-decoration: none;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #fff;
}
header{
  width: 100%;
  height: 60px;
  background: #383d41;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 999;
}
.logo{
  float: left;
  width: 149px;
  height: 100%;
  border-right: 1px solid black;
}
.logo h1{
  color: aquamarine;
}
.title{
  float: left;
  line-height: 60px;
  margin-left: 15px;
}
.userBtn{
  float: right;
  width: auto;
  line-height: 60px;
  cursor: pointer;
}
.userName{
  color: #fff;
}
.el-container{
  height: 100%;
  position: absolute;
  top: 60px;
  left: 170px;
  right: 0;
}
.el-aside{
 background: #333743;
 height: 100%;
 overflow: hidden;
}
.el-main{
  
}
</style>
