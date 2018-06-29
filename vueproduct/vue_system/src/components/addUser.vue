<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="80px" class="demo-ruleForm">
    <el-form-item label="账号" prop="loginAccount">
        <el-input v-model="ruleForm.loginAccount"></el-input>
    </el-form-item>
    <el-form-item label="姓名" prop="userName">
        <el-input v-model="ruleForm.userName"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password"></el-input>
    </el-form-item>
    <el-form-item label="性别">
      <div class="radiogroup">
        <el-radio  v-model="ruleForm.sex" label="0">男</el-radio>
        <el-radio  v-model="ruleForm.sex" label="1">女</el-radio>
      </div>
    </el-form-item>
    <el-form-item label="手机" prop="phone">
        <el-input v-model="ruleForm.phone"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
        <el-input v-model="ruleForm.email"></el-input>
    </el-form-item>
    <el-form-item label="生日" prop="birthDate">
        <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.birthDate" style="width: 100%;"></el-date-picker>
    </el-form-item>
    <el-form-item label="是否禁用">
        <el-switch v-model="ruleForm.disable"></el-switch>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
        <el-button>取消</el-button>
    </el-form-item>
    </el-form>
</template>
<script>
  export default {
    data() {
      return {
        ruleForm: {
          loginAccount:"",
          userName: '',
          password:'',
          sex: "0",
          phone:'',
          email: '',
          birthDate:'',
          disable: "0" //1 禁用  0启用
        },
        rules: {
          loginAccount:[
            {required:true, message: '请输入账号'}
          ],
          userName: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'change' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('submit!');
            console.log(this.ruleForm);
            var params = JSON.stringify(this.ruleForm);
            this.$axios.post('https://xuelanlan.cn:8080/api/user',{
              birthDate:this.ruleForm.birthDate,
              disable:Number(this.ruleForm.disable),
              email:this.ruleForm.email,
              loginAccount:this.ruleForm.loginAccount,
              password:this.ruleForm.password,
              phone:this.ruleForm.phone,
              sex:Number(this.ruleForm.sex),
              userName:this.ruleForm.userName
            })
            .then(response=>{
              // console.log(response);
              this.resetForm(formName);
              // alert('新建用户成功')
              this.$router.go(-1);
            })
            .catch(error=>{
              console.log("出错了~~")
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    },
    mounted(){
      // this.addUserInfo()
    }
  }
</script>
<style scoped>
.demo-ruleForm{
  margin-top: 20px;
  margin-bottom: 80px;
}
.radiogroup,.el-switch{
  position: absolute;
  left: 20px;
  top: 12px;
}
.radiogroup{
  top: 0;
}
</style>

