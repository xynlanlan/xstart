<template>
  <div id="app">

    <eheader></eheader>
    <!-- <el-row> -->
    <el-container>
      <left-bar></left-bar>
      <el-main>
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          </el-breadcrumb>
          <router-view/>
        </el-main>
    </el-container>
    <!-- </el-row> -->
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
.clearfix{
  content: "";
  clear: both;
  display: block;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #fff;
}
.el-container{
  height: 100%;
  width: 100%;
  position: absolute;
  top: 60px;
  bottom: 0;
}
.el-aside{
 background: #333743;
 height: 100%;
 overflow: hidden;
}
.el-main{
  overflow-y: auto;
  flex: 1 1 auto;
  
}
</style>
