<template>
  <div id="index">

    <eheader></eheader>
    <el-container>
      <left-bar></left-bar>
      <el-main>
          <router-view name="content"/>
        </el-main>
    </el-container>
  </div>
</template>

<script>
import eheader from '@/components/common/header';
import leftBar from '@/components/common/leftBar';
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
    eheader,
    leftBar
  }
}
</script>

<style>
body{
  overflow-y: hidden;
  background: #fff;
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
#index {
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
