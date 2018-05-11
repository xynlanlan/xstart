<template>
  <div class="bookList">
      <div class="search">
          <el-input placeholder="书名" v-model="inputValue" clearable class="searchInput"></el-input>
          <el-button type="primary" class="query">查询</el-button>
          <el-button type="primary" class="add" @click="dialogFormVisible = true">新增</el-button>
          <el-table :data="tableData" style="width: 100%" align="center" :default-sort = "{prop: 'num', order: 'ascending'}">
              <el-table-column width="50" type="selection">
              </el-table-column>
              <el-table-column prop="num" label="#" min-width="50">
              </el-table-column>
              <el-table-column prop="bookname" label="书名" sortable min-width="100">
              </el-table-column>
              <el-table-column prop="author" label="作者" sortable min-width="150">
              </el-table-column>
              <el-table-column prop="date" label="出版时间" sortable min-width="150">
              </el-table-column>
              <el-table-column prop="introduction" label="作品简介" min-width="300">
              </el-table-column>
              <el-table-column prop="email" label="操作" min-width="200" :formatter="formatter">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
              </el-table-column>
          </el-table>
          <el-button type="danger" class="alldel">批量删除</el-button>
      </div>
      <el-dialog title="新增图书" :visible.sync="dialogFormVisible">
        <el-form ref="ruleform" :model="ruleform" label-position="left" :rules="rules">
          <el-form-item label="书名" :label-width="formLabelWidth" prop="bookname">
            <el-input v-model="ruleform.bookname" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="作者" :label-width="formLabelWidth" prop="author">
            <el-input v-model="ruleform.author" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="出版日期" :label-width="formLabelWidth" prop="date">
            <el-date-picker
              v-model="value1"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="简介" :label-width="formLabelWidth" prop="introduction">
            <el-input
              type="textarea"
              :rows="4"
              placeholder="请输入内容"
              v-model="textarea">
            </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">提 交</el-button>
        </div>
      </el-dialog>
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
          bookname: "西游记",
          author:"吴承恩",
          date:"2000-08-09",
          email:"88888888@qq.com",
          introduction: "明代小说家吴承恩同名文学古典名著。"
        },
        {
          num: "2",
          bookname: "红楼梦",
          author:"曹雪芹",
          date:"2000-08-09",
          email:"66666666@qq.com",
          introduction: "清代作家曹雪芹创作的章回体长篇小说。"
        },
        {
          num: "3",
          bookname: "三国演义",
          author:"罗贯中",
          date:"2000-08-09",
          email:"66666666@qq.com",
          introduction: "中国第一部长篇章回体历史演义的小说。"
        },
        {
          num: "4",
          bookname: "水浒传",
          author:"施耐庵",
          date:"2000-08-09",
          email:"66666666@qq.com",
          introduction: "一部以描写古代农民起义为题材的长篇小说。"
        }
      ],
      dialogTableVisible: false,
      dialogFormVisible: false,
      ruleform: {
        bookname: '',
        author: '',
        date:"",
        introduction: ''
      },
      rules:{
        bookname: [
            { required: true, message: '请输入书名', trigger: 'blur' },
            { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
          ],
        author:[
          { required: true, message: '请输入作者', trigger: 'change' }
        ],
        introduction:[
          { required: true, message: '请输入作品简介', trigger: 'change' }
        ]
      },
      formLabelWidth: '80px',
      textarea: '',
      value1: ''
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
    },
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
};
</script>

<style scoped>
.bookList {
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
.alldel{
  position: absolute;
  left: 0;
  margin-top: 20px;
}
.el-table{
    margin-top: 100px;
}
.el-date-editor{
  position: absolute;
  left: 0;
}
</style>

