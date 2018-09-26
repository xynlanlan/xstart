<template>
    <div class="userList">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>
                <a href="#">系统管理</a>
            </el-breadcrumb-item>
            <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-row class="search">
            <el-input placeholder="账号/用户名/手机号" v-model="inputValue" clearable class="searchInput"></el-input>
            <el-button type="primary" class="query" @click="query()">查询</el-button>
            <router-link :to="{ path: '/addUser' }">
                <el-button type="primary" class="add">新增</el-button>
            </router-link>
            <el-table ref="multipleTable" v-loading="loading" :data="tableData" style="width: 100%" align="center" :default-sort="{prop: 'id', order: 'ascending'}" @selection-change="getIndex">
                <el-table-column width="50" type="selection" class="checkBox" @selection-change="getIndex">
                </el-table-column>
                <el-table-column type="index" label="序号" min-width="50">
                </el-table-column>
                <el-table-column prop="userName" label="用户名" sortable min-width="80">
                </el-table-column>
                <el-table-column prop="loginAccount" label="账号" sortable min-width="90">
                </el-table-column>
                <el-table-column prop="role.roleName" label="角色" sortable min-width="80">
                    <template slot-scope="scope">
                        {{scope.row.role==null?'--':scope.row.role.roleName}}
                    </template>
                </el-table-column>
                <el-table-column prop="sex" label="性别" sortable min-width="80">
                    <template slot-scope="scope">
                        {{scope.row.sex==false?'男':'女'}}
                    </template>
                </el-table-column>
                <el-table-column prop="email" label="邮箱" sortable min-width="160">
                </el-table-column>
                <el-table-column prop="createTime" label="注册时间" sortable min-width="210">
                    <template slot-scope="scope">
                        {{getCreateTime(scope.row.createTime)}}
                    </template>
                </el-table-column>
                <el-table-column prop="disabled" label="状态" sortable min-width="80">
                    <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{scope.row.disabled?'禁用':'启用'}}</el-tag>
                        </div>
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
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" :page-sizes="[10]" :page-size="5" layout="total, sizes, prev, pager, next, jumper" :total="totalPage" class="pagination">
            </el-pagination>

        </el-row>

        <el-dialog title="编辑用户" :visible.sync="dialogFormVisible">
            <el-form ref="form" :model="dialogForm" size="mini" label-width="80px">
                <el-form-item label="I D">
                    <el-input v-model="dialogForm.id" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="dialogForm.userName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="账号">
                    <el-input v-model="dialogForm.loginAccount" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <div class="radiogroup">
                        <el-radio v-model="dialogForm.sex" :label="man">男</el-radio>
                        <el-radio v-model="dialogForm.sex" :label="woman">女</el-radio>
                    </div>
                </el-form-item>
                <el-form-item label="手机" prop="phone">
                    <el-input v-model="dialogForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="dialogForm.email"></el-input>
                </el-form-item>
                <el-form-item label="生日" prop="birthDate">
                    <el-date-picker type="date" placeholder="选择日期" v-model="dialogForm.birthDate" style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="是否禁用">
                    <el-switch v-model="dialogForm.disable"></el-switch>
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
import { formatDate } from "../js/formatDate.js";
export default {
  name: "userManagement",
  data() {
    return {
      inputValue: "",
      loading: true,
      tableData: [],
      currentPage: 1,
      totalPage: 0,
      formLabelWidth: "120px",
      dialogFormVisible: false,
      dialogForm: {},
      man: "0",
      woman: "1",
      multipleSelection: []
    };
  },
  methods: {
    formatter(row, column) {
      return row.address;
    },
    handleEdit(index, row) {
      console.log(index, row);
      this.dialogFormVisible = true;
      this.dialogForm = row;
      if (this.dialogForm.sex == true) {
        this.dialogForm.sex = this.woman;
      } else {
        this.dialogForm.sex = this.man;
      }
    },
    confirmBtn() {
      this.dialogFormVisible = false;
      this.$axios
        .put("/api/user", {
          birthDate: this.dialogForm.birthDate,
          disable: Number(this.dialogForm.disable),
          email: this.dialogForm.email,
          loginAccount: this.dialogForm.loginAccount,
          password: this.dialogForm.password,
          phone: this.dialogForm.phone,
          sex: Number(this.dialogForm.sex),
          userName: this.dialogForm.userName,
          id: this.dialogForm.id
        })
        .then(response => {
          console.log(response);
        })
        .catch(error => {
          console.log(error);
        });
    },
    handleDelete(index, row) {
      console.log(index, row);
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$axios
            .delete("/api/user/" + row.id)
            .then(response => {
              // window.location.reload(); //这个方法会刷新整个页面，不太好
              let NewPage = "_empty" + "?time=" + new Date().getTime() / 1000;
              // 将上面的空页面push进去
              this.$router.push(NewPage);
              // 再次返回上一页即可
              this.$router.go(-1);
            })
            .catch(error => {
              console.log(error);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    getIndex(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
    },
    getCreateTime(date) {
      var time = new Date(date);
      return formatDate(time, "yyyy-MM-DD hh:mm:ss");
    },
    delAll() {
      let idArr = [];
      this.multipleSelection.map(item => {
        idArr.push(item.id);
      });
      if (idArr.length == 0) {
        this.$message({
          showClose: true,
          message: "没有选中要删除的项！",
          type: "warning"
        });
        return;
      }
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$axios
            .delete("/api/user/" + idArr.join())
            .then(response => {
              this.$message({
                type: "success",
                message: "删除成功!"
              });
              let NewPage = "_empty" + "?time=" + new Date().getTime() / 1000;
              this.$router.push(NewPage);
              this.$router.go(-1);
            })
            .catch(error => {
              console.log(error);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    getUserList() {
      this.$axios
        .post("/api/user/list", {
          pager: "{}"
        })
        .then(response => {
          this.tableData = response.data.result.result;
          this.totalPage = response.data.result.total;
        })
        .catch(error => {
          console.log(error);
        });
    },
    query() {
      this.$axios
        .post("/api/user/list", {
          condition: {
            keyword: this.inputValue
          }
        })
        .then(response => {
          this.tableData = response.data.result.result;
        })
        .catch(error => {
          console.log(error);
        });
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.$axios
        .post("/api/user/list", {
          pageIndex: val,
          condition: {}
        })
        .then(response => {
          this.tableData = response.data.result.result;
          this.totalPage = response.data.result.total;
          this.loading = false;
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
  mounted() {
    // this.getUserList()
    this.handleCurrentChange(1);
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
.searchInput {
  min-width: 240px;
  max-width: 240px;
  position: absolute;
  left: 0;
  top: 20px;
}
.query,
.add {
  position: absolute;
  left: 260px;
  top: 20px;
}
.add {
  left: 340px;
}
.el-table {
  margin-top: 80px;
  margin-bottom: 100px;
}
.el-table--scrollable-x .el-table__body-wrapper {
  overflow-x: hidden;
}
.alldel {
  position: absolute;
  left: 0;
  bottom: 60px;
}
.pagination {
  position: absolute;
  right: 0;
  bottom: 60px;
}
.radiogroup,
.el-switch {
  position: absolute;
  left: 20px;
  top: 4px;
}
.radiogroup {
  top: 0;
}
</style>
