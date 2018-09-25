<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="80px" class="demo-ruleForm">
    <el-form-item label="角色" prop="roleName">
        <el-input v-model="ruleForm.roleName"></el-input>
    </el-form-item>
    <el-form-item label="别名" prop="alias">
        <el-input v-model="ruleForm.alias"></el-input>
    </el-form-item>
    <el-form-item label="描述" prop="description">
        <el-input v-model="ruleForm.description"></el-input>
    </el-form-item>
    <el-form-item label="创建日期" prop="createTime">
        <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.createTime" style="width: 100%;"></el-date-picker>
    </el-form-item>
    <el-form-item label="是否禁用">
        <el-switch v-model="ruleForm.disabled"></el-switch>
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
          roleName:"",
          alias: '',
          description:'',
          createTime:'',
          disabled: "0" //1 禁用  0启用
        },
        rules: {
          roleName:[
            {required:true, message: '请输入角色名称'}
          ],
          alias: [
            { required: true, message: '请输入别名', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1 到 10 个字符', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '请描述角色', trigger: 'change' }
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
            this.$axios.post('/api/role',{
              createTime:this.ruleForm.createTime,
              disabled:Number(this.ruleForm.disabled),
              roleName:this.ruleForm.roleName,
              alias:this.ruleForm.alias,
              description:this.ruleForm.description,
            })
            .then(response=>{
              // console.log(response);
              this.resetForm(formName);
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

