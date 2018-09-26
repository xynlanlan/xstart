<template>
  <div id="app">
    <!-- <router-view/> -->
    <router-view name="login"/>
    <router-view name="index"/>
    <!-- <eheader></eheader>
    <el-container>
      <left-bar></left-bar>
      <el-main>
          <router-view/>
        </el-main>
    </el-container> -->
  </div>
</template>

<script>
import eheader from '@/components/common/header';
import leftBar from '@/components/common/leftBar';
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
html,body{
  width: 100%;
  height: 100%;
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
  width: 100%;
  height: 100%;
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
.el-breadcrumb{
 margin-bottom: 10px;
}
</style>
