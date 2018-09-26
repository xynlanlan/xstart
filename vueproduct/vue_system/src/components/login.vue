<template>
    <div class="login">
        <el-form ref="form" class="box">
            <h2>管理员登录</h2>
            <el-form-item>
                <el-input v-model="loginAccount" placeholder="请输入账号" prefix-icon="el-icon-message"></el-input>
            </el-form-item>
            <!-- <el-form-item>
                <el-input type="password" v-model="password" placeholder="请输入密码" prefix-icon="el-icon-view"></el-input>
            </el-form-item> -->
            
            <el-form-item v-if="visible" >
                <el-input type="password" v-model="password" placeholder="请输入密码">
                    <i slot="prefix" title="显示密码" @click="changePass('show')" style="cursor:pointer;"
                    class="el-input__icon el-icon-view"></i>
                </el-input>
            </el-form-item>
            <el-form-item v-else>
                <el-input type="text" v-model="password" placeholder="请输入密码">
                    <i slot="prefix" title="隐藏密码" @click="changePass('hide')" style="cursor:pointer;"
                    class="el-input__icon el-icon-view"></i>
                </el-input>
            </el-form-item>

            <el-button native-type="submit" class="loginBtn" type="primary" @click="login()">登录</el-button>
        </el-form>
    </div>
</template>


<script>
import { mapState, mapActions } from 'vuex'
export default {
    name: 'login',
    data(){
        return {
            // loginAccount:"",
            password:"",
            visible: true 
        }
    },
    watch:{
        // "$store.getters.account":function(oldval,newval){
        //     this.$store.commit('account',newval)
        // }
    },
    computed: {
        loginAccount:{
            get(){
                return this.$store.state.account
            },
            set(val){
                this.$store.commit('setLoginAccount', val)
                sessionStorage.setItem('userName',val)
            }
        }
    },
    methods:{
        login(){
            this.$axios.post('/login',{
                loginAccount:this.loginAccount,
                password:this.password
            }).then(response=>{
                console.log(response);
                if(response.status==200){
                    this.$router.push({ path: 'index' });
                    localStorage.setItem('token',response.data.Authorization);
                    // debugger;
                }else{
                    alert("账号或密码输入错误！")
                }
            }).catch(error=>{
                console.log(error);
                // alert(error.data.message);
            })
        },
        changePass(value) {
            this.visible = !(value === 'show');
        }    //判断渲染，true:暗文显示，false:明文显示
    },
    beforeCreate(){
        document.getElementsByTagName("body")[0].style.background="#2c394a";
        // document.getElementsByTagName("body")[0].style.background="#dfe9fb";
    },
    beforeDestroy(){
        document.getElementsByTagName("body")[0].style.background="#fff";
    },
    mounted(){
        // this.$axios.head('/login',{
        //         loginAccount:this.loginAccount,
        //         password:this.password
        //     }).then(response=>{
        //         console.log(response);
        //         if(response.status==200){
        //             this.$router.push({ path: 'index' });
        //             localStorage.setItem('token',response.headers.authorization);
        //         }else{
        //             alert("账号或密码输入错误！")
        //         }
        //     }).catch(error=>{
        //         console.log(error);
        //     })
        // }
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
    background: #2c394a;
    /* border: 1px solid #374353; */
    /* box-shadow: 0 0 25px #cac6c6; */
}
h2{
    margin: 0 auto 40px;
    text-align: center;
    color: #fff;
}
.loginBtn{
    width: 350px;
}
</style>

