<template>
  <div>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      </el-breadcrumb>
      <section class="card">
        <el-row>
            <el-col :span="8" v-for="(o, $index) in 3" :key="$index">
                <el-card :body-style="{ padding: '0px' }">
                    <img src="../assets/logo.png" class="image">
                    <div style="padding: 14px;">
                    <span>好吃的汉堡</span>
                    <div class="bottom clearfix">
                        <time class="time">{{ currentDate }}</time>
                        <el-button type="text" class="button">操作按钮</el-button>
                    </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <!-- <el-row>
            <el-col :span="12">
                <div class="grid-content bg-purple">
                    <img src="../assets/logo.png" alt="">
                </div>
            </el-col>
            <el-col :span="12">
                <div class="grid-content bg-purple-light">
                    <img src="../assets/logo.png" alt="">
                </div>
            </el-col>
        </el-row> -->
        <el-row>
            <el-col :span="12">
                <div id="chart1" style="width:450px;height:400px"></div>
            </el-col>
            <el-col :span="12">
                <div id="chart2" style="width:500px;height:400px"></div>
            </el-col>

        </el-row>
    </section>
  </div>
</template>

<script>
import echarts from 'echarts'
export default {
  name: 'home',
  data(){
      return {
          currentDate: new Date(),
          
      }
  },
  mounted(){
      let myChart1 = echarts.init(document.getElementById('chart1'));
      let myChart2 = echarts.init(document.getElementById('chart2'));
      myChart1.setOption({
            title: { text: '在Vue中使用echarts' },
            tooltip: {},
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        });
        myChart2.setOption({
            title: {
                text: '折线图堆叠',
                // left: 'center',
                // height:'200px'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                top:'25',
                data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['周一','周二','周三','周四','周五','周六','周日']
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name:'邮件营销',
                    type:'line',
                    stack: '总量',
                    data:[120, 132, 101, 134, 90, 230, 210]
                },
                {
                    name:'联盟广告',
                    type:'line',
                    stack: '总量',
                    data:[220, 182, 191, 234, 290, 330, 310]
                },
                {
                    name:'视频广告',
                    type:'line',
                    stack: '总量',
                    data:[150, 232, 201, 154, 190, 330, 410]
                },
                {
                    name:'直接访问',
                    type:'line',
                    stack: '总量',
                    data:[320, 332, 301, 334, 390, 330, 320]
                },
                {
                    name:'搜索引擎',
                    type:'line',
                    stack: '总量',
                    data:[820, 932, 901, 934, 1290, 1330, 1320]
                }
            ]
        })
  }
}
</script>


<style scoped>
/* .image{
    width: 100%;
    height: 200px;;
} */
.card .el-col{
  padding: 30px 20px;
}
.grid-content{
    color: black;
}
</style>
