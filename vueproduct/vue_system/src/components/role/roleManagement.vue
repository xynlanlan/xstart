<template>
    <div class="roleList">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="#">系统管理</a></el-breadcrumb-item>
        <el-breadcrumb-item>角色管理</el-breadcrumb-item>
      </el-breadcrumb>
        <el-row class="search">
            <el-input placeholder="ID" v-model="inputValue" clearable class="searchInput"></el-input>
            <el-button type="primary" class="query" @click="query()">查询</el-button>
            <router-link :to="{ path: '/addRole' }">
              <el-button type="primary" class="add">新增</el-button>
            </router-link>
            <el-table ref="multipleTable" :data="roleTableData" style="width: 100%" align="center" :default-sort = "{prop: 'id', order: 'ascending'}" @selection-change="getIndex">
                <el-table-column width="50" type="selection" class="checkBox" @selection-change="getIndex">
                </el-table-column>
                <el-table-column prop="id" label="#" min-width="50">
                </el-table-column>
                <el-table-column prop="roleName" label="角色" sortable min-width="120">
                </el-table-column>
                <el-table-column prop="alias" label="别名" sortable min-width="130">
                </el-table-column>
                <el-table-column prop="description" label="描述" sortable min-width="240">
                </el-table-column>
                <el-table-column prop="createTime" label="注册时间" sortable min-width="210">
                   <template slot-scope="scope">
                    {{getCreateTime(scope.row.createTime)}}
                  </template>
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
            <el-button type="danger" class="alldel" @click="delAll(tableData)">批量删除</el-button>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10]"
          :page-size="5"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalPage" class="pagination">
        </el-pagination>

        </el-row>
        
        <el-dialog title="编辑用户" :visible.sync="dialogFormVisible">
          <el-form ref="form" :model="dialogForm" size="mini" label-width="80px">
            <el-form-item label="I D">
              <el-input v-model="dialogForm.id" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="角色">
              <el-input v-model="dialogForm.roleName" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="别名">
              <el-input v-model="dialogForm.alias" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="描述" prop="description">
                <el-input v-model="dialogForm.description"></el-input>
            </el-form-item>
            <el-form-item label="创建日期" prop="createTime">
                <el-date-picker type="date" placeholder="选择日期" v-model="dialogForm.createTime" style="width: 100%;"></el-date-picker>
            </el-form-item>
            <el-form-item label="是否禁用">
                <el-switch v-model="dialogForm.disabled"></el-switch>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="confirmBtn()">确 定</el-button>
          </div>
        </el-dialog>
    </div>

</template>

<script>
import {formatDate} from "../../js/formatDate.js"
export default {
  name: "roleManagement",
  data() {
    return {
      inputValue: "",
      roleTableData:[],
      currentPage: 1,
      totalPage:0,
      formLabelWidth:'120px',
      dialogFormVisible: false,
      dialogForm:{},
      multipleSelection:[]
    };
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
      this.dialogFormVisible = true;
      this.dialogForm = row;
      if(this.dialogForm.sex==true){
        this.dialogForm.sex = this.woman;
      }else{
        this.dialogForm.sex = this.man;
      }
    },
    confirmBtn(){
      this.dialogFormVisible = false
      this.$axios.put('/api/role',{
        createTime:this.dialogForm.createTime,
        disabled:Number(this.dialogForm.disabled),
        description:this.dialogForm.description,
        alias:this.dialogForm.alias,
        roleName:this.dialogForm.roleName,
        id:this.dialogForm.id
      }).then(response=>{
        console.log(response);
      }).catch(error=>{
        console.log(error);
      })
    },
    //删除某行
    handleDelete(index, row) {
      console.log(index, row);
      this.$axios.delete('/api/role/'+row.id)
      .then(response=>{
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
    getIndex(val){
       this.multipleSelection = val;
       console.log(this.multipleSelection);
    },
    getCreateTime(date){
      var time = new Date(date);
      return formatDate(time,'yyyy-MM-DD hh:mm:ss');
    },
    //批量删除
    delAll(){
      let idArr = [];
      this.multipleSelection.map((item)=>{
        idArr.push(item.id)
      })
      console.log(idArr)
      this.$axios.delete('/api/role/'+idArr.join())
      .then(response=>{
        let NewPage = '_empty' + '?time=' + new Date().getTime()/1000;
        this.$router.push(NewPage);
        this.$router.go(-1);
      })
      .catch(error=>{
        console.log(error)
      })
    },
    getRoleList(){
      this.$axios.post('/api/role/list',{
        pager:"{}"
      })
      .then(response=>{
        this.roleTableData = response.data.result.result;
        this.totalPage = response.data.result.total;
      })
      .catch(error=>{
        console.log(error)
      })
    },
    //查询
    query(){
      this.$axios.post('/api/role/list',{
        condition:{
          id:this.inputValue
        }
      })
      .then(response=>{
        this.roleTableData = response.data.result.result;
      })
      .catch(error=>{
        console.log(error);
      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.$axios.post('/api/role/list',{
        "pageIndex":val,
        condition:{}
      })
      .then(response=>{
        // console.log(response);
        this.roleTableData = response.data.result.result;
        this.totalPage = response.data.result.total;
      })
      .catch(error=>{
        console.log(error);
      })
    }
  },
  mounted(){
    // this.getRoleList()
    this.handleCurrentChange(1)
  }
};
</script>


<style scoped>
.roleList {
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
.searchInput {
  min-width: 240px;
  max-width: 240px;
  position: absolute;
  left: 0;
  top: 20px;
}
.query,.add {
  position: absolute;
  left: 260px;
  top: 20px;
}
.add{
  left: 340px;
}
.el-table{
    margin-top: 80px;
    margin-bottom: 100px;
}
.el-table--scrollable-x .el-table__body-wrapper{
  overflow-x:hidden;
}
.alldel{
  position: absolute;
  left: 0;
  bottom: 60px;
}
.pagination{
  position: absolute;
  right: 0;
  bottom: 60px;
}
.radiogroup,.el-switch{
  position: absolute;
  left: 20px;
  top: 4px;
}
.radiogroup{
  top: 0;
}
</style>
