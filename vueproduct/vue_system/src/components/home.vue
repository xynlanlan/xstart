<template>
  <div>
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
        <el-row>
            <el-col :span="12">
            <div class="grid-content bg-purple">讨厌鬼</div>
            <img src="../assets/logo.png" alt="">
            </el-col>
            <el-col :span="12">
            <div class="grid-content bg-purple-light">
                <img src="../assets/logo.png" alt="">
            </div>
            </el-col>
        </el-row>
        </section>
  </div>
</template>

<script>
export default {
  name: 'home',
  data(){
      return {
          currentDate: new Date()
      }
  }
}
</script>


<style scoped>
.card .el-col{
  padding: 30px 20px;
}
</style>
