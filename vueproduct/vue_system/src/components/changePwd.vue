<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="95px" class="demo-ruleForm">
    <el-form-item label="原密码" prop="oldpwd">
        <el-input v-model="ruleForm.oldpwd"></el-input>
    </el-form-item>
    <el-form-item label="新密码" prop="newpwd">
        <el-input v-model="ruleForm.newpwd"></el-input>
    </el-form-item>
    <el-form-item label="确认新密码" prop="reconfirm">
        <el-input v-model="ruleForm.reconfirm"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
        <el-input v-model="ruleForm.email"></el-input>
    </el-form-item>
    
    <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
    </el-form-item>
    </el-form>
</template>
<script>
  export default {
    data() {
      return {
        ruleForm: {
          oldpwd:"",
          newpwd: '',
          reconfirm: '',
          email: ''
        },
        rules: {
          oldpwd: [
            { required: true, message: '请输入昵称', trigger: 'blur' },
            { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
          ],
          newpwd: [
            { required: true, message: '请输入邮箱', trigger: 'change' }
          ],
          reconfirm: [
            { required: true, message: '请输入邮箱', trigger: 'change' }
          ],
          email: [
            { required: true, message: '请输入邮箱', trigger: 'change' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>
<style scoped>
.demo-ruleForm{
    margin-top: 20px;
}
</style>

