<template>
    <div class="login">
        <el-form ref="form" class="box">
            <h2>管理员登录</h2>
            <el-form-item>
                <el-input v-model="loginAccount" placeholder="请输入账号"></el-input>
            </el-form-item>
            <el-form-item>
                <el-input v-model="password" placeholder="请输入密码"></el-input>
            </el-form-item>

            <el-button type="primary" @click="login()">登录</el-button>
        </el-form>
    </div>
</template>


<script>
export default {
    name: 'login',
    data(){
        return {
            loginAccount:"",
            password:""
        }
    },
    methods:{
        login(){
            this.$axios.post('http://120.78.161.183:8080/alogin',{
                loginAccount:this.loginAccount,
                password:this.password
            }).then(response=>{
                console.log(response);
                if(response.data.status==200){
                    this.$router.push({ path: 'index' });
                }else{
                    alert("账号或密码输入错误！")
                }
            }).catch(error=>{
                console.log(error);
                alert(error.data.message);
            })
        }
    },
    beforeCreate(){
        document.getElementsByTagName("body")[0].style.background="#dfe9fb";
    },
    beforeDestroy(){
        document.getElementsByTagName("body")[0].style.background="#fff";
    }
    
}
</script>

<style>
.login{
    width: 100%;
    height: 100%;
}
.box{
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 160px auto;
    width: 350px;
    padding: 35px 35px 15px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
}
h2{
    margin: 0 auto 40px;
    text-align: center;
    color: #505458;
}
</style>

