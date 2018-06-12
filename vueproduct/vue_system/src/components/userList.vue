<template>
    <div class="userList">
        <el-row class="search">
            <el-input placeholder="用户名/姓名/昵称" v-model="inputValue" clearable></el-input>
            <el-button type="primary" class="query" @click="query()">查询</el-button>
            <el-table :data="tableData" style="width: 100%" align="center" :default-sort = "{prop: 'id', order: 'ascending'}">
                <el-table-column width="50" type="selection">
                </el-table-column>
                <el-table-column prop="id" label="#" min-width="50">
                </el-table-column>
                <el-table-column prop="userName" label="姓名" sortable min-width="80">
                </el-table-column>
                <el-table-column prop="loginAccount" label="账号" sortable min-width="90">
                </el-table-column>
                <el-table-column prop="role" label="角色" sortable min-width="80">
                </el-table-column>
                <el-table-column prop="sex" label="性别" sortable min-width="80">
                  <template slot-scope="scope">
                    {{scope.row.sex==false?'男':'女'}}
                  </template>
                </el-table-column>
                <el-table-column prop="email" label="邮箱" sortable min-width="160">
                </el-table-column>
                <el-table-column prop="createTime" label="注册时间" sortable min-width="200">
                </el-table-column>
                <el-table-column label="操作" min-width="200">
                <template slot-scope="scope">
                  <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
              </el-table-column>
                <!-- <el-table-column prop="address" label="地址"  sortable min-width="300" :formatter="formatter">
                </el-table-column> -->
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
      tableData:[]
    };
  },
  methods: {
    formatter(row, column) {
      return row.address;
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
      this.$axios.delete('http://120.78.161.183:8080/api/user/'+row.id)
      .then(response=>{
        // console.log(response);
        // window.location.reload(); //这个方法会刷新整个页面，不太好
        let NewPage = '_empty' + '?time=' + new Date().getTime()/1000;
        // 将上面的空页面push进去
        this.$router.push(NewPage);
        // 再次返回上一页即可
        this.$router.go(-1);
      })
      .catch(error=>{
        console.log(error)
      })
    },
    getUserList(){
      this.$axios.post('http://120.78.161.183:8080/api/user/list',{
        pager:"{}"
      })
      .then(response=>{
        // console.log(response);
        this.tableData = response.data.result.result;
        // console.log(this.tableData);
      })
      .catch(error=>{
        console.log(error)
      })
    },
    query(){
      this.$axios.post('http://120.78.161.183:8080/api/user/list',{
        condition:{
          loginAccount:this.inputValue
        }
      })
      .then(response=>{
        console.log(response);
        
        // console.log(this.tableData);
      })
      .catch(error=>{
        console.log(error)
      })
    }
  },
  mounted(){
    this.getUserList()
  }
};
</script>


<style scoped>
.userList {
    width: 100%;
    /* height: 100%; */
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
.query {
  position: absolute;
  left: 260px;
  top: 20px;
}
.el-table{
    margin-top: 100px;
    margin-bottom: 100px;
}
.el-table--scrollable-x .el-table__body-wrapper{
  overflow-x:hidden;
}
</style>
