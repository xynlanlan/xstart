<template>
  <el-header>
        <div class="logo">
          <i class="el-icon-upload"></i>
          <h1>LOGO</h1>
        </div>
        <h3 class="title">后台管理系统</h3>
        <div class="userBtn">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              <i class="el-icon-info"></i>
              <span class="userName">{{loginAccount}}</span>
              <!-- <span class="userName">{{$store.state.account}}</span> -->
              <i class="el-icon-caret-bottom el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item><router-link :to="{ path: '/changePwd' }">修改密码</router-link></el-dropdown-item>
              <el-dropdown-item  @click.native ="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
</template>

<script>
import { mapState,mapMutations, mapActions } from 'vuex'
export default {
  data () {
    return {
      loginAccount:""
    }
  },
  computed: {
    
  },
  methods:{
    ...mapMutations(
      ['getLoginAccount']
    ),
    userName(){
      this.loginAccount =  sessionStorage.getItem('userName');
      console.log(this.loginAccount)
    },
    logout(){
            this.$axios.get('/logout').then(response=>{
                console.log(response);
                if(response.status==200){
                    localStorage.removeItem('token');
                    // debugger;
                    this.$router.push({ path: 'login' });
                }else{
                    alert("系统错误！")
                }
            }).catch(error=>{
                console.log(error);
                // alert(error.data.message);
            })
        },

  },
  mounted(){
    this.userName();
  }
}
</script>

<style scoped>
header{
  width: 100%;
  height: 60px;
  background: #383d41;
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
  height: 60px;
  line-height: 60px;
  cursor: pointer;
}
.userName{
  color: #fff;
}
</style>


