<template>
    <div class="userList">
        <el-row class="search">
            <el-input placeholder="用户名/姓名/昵称" v-model="inputValue" clearable></el-input>
            <el-button type="primary">查询</el-button>
            <el-table :data="tableData" style="width: 100%" align="center" :default-sort = "{prop: 'num', order: 'ascending'}">
                <el-table-column prop="num" label="#" width="100">
                </el-table-column>
                <el-table-column prop="name" label="姓名" sortable width="150">
                </el-table-column>
                <el-table-column prop="role" label="角色" sortable width="150">
                </el-table-column>
                <el-table-column prop="sex" label="性别" sortable width="150">
                </el-table-column>
                <el-table-column prop="email" label="邮箱" sortable width="200">
                </el-table-column>
                <el-table-column prop="address" label="地址"  sortable width="300" :formatter="formatter">
                </el-table-column>
            </el-table>
        </el-row>
    </div>

</template>

<script>
export default {
  name: "userList",
  data() {
    return {
      inputValue: "",
      tableData: [
        {
          num: "1",
          name: "admin",
          role:"管理员",
          sex:"男",
          email:"88888888@qq.com",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          num: "2",
          name: "MMMM",
          role:"用户",
          sex:"女",
          email:"66666666@qq.com",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          num: "3",
          name: "MMMM",
          role:"用户",
          sex:"男",
          email:"66666666@qq.com",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          num: "4",
          name: "MMMM",
          role:"用户",
          sex:"男",
          email:"66666666@qq.com",
          address: "上海市普陀区金沙江路 1518 弄"
        }
      ]
    };
  },
  methods: {
    formatter(row, column) {
      return row.address;
    }
  }
};
</script>


<style scoped>
.userList {
    width: 100%;
    height: 100%;
    position: relative;
}
.search {
    width: 100%;
    position: absolute;
    left: 0;
    top: 0;
}
.el-input {
  min-width: 240px;
  max-width: 240px;
  position: absolute;
  left: 0;
  top: 20px;
}
.el-button {
  position: absolute;
  left: 260px;
  top: 20px;
}
.el-table{
    margin-top: 100px;
}
</style>
