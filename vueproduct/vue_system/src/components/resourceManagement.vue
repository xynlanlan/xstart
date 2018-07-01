<template>
    <div class="resourceList">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="#">系统管理</a></el-breadcrumb-item>
            <el-breadcrumb-item>资源管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-tree :data="dataList" show-checkbox node-key="data.id" default-expand-all :expand-on-click-node="false">
            <span class="custom-tree-node" slot-scope="{node, data}">
                <span class="menuName">{{ data.menuName }}</span>
                <span>
                    <el-button type="primary" size="mini" @click="() => append(data)">新增</el-button>
                    <el-button type="primary" size="mini" @click="() => edit(data)">编辑</el-button>
                    <el-button type="danger" size="mini" @click="() => remove(node, data)">删除</el-button>
                </span>
            </span>
        </el-tree>

        
    </div>
</template>

<script>
import {formatDate} from "../js/formatDate.js"
let id = 1000;
export default {
  name: "resourceManagement",
  data() {
      const data = [{
        id: 1,
        label: '一级 1',
        children: [{
          id: 4,
          label: '二级 1-1',
          children: [{
            id: 9,
            label: '三级 1-1-1'
          }, {
            id: 10,
            label: '三级 1-1-2'
          }]
        }]
      }, {
        id: 2,
        label: '一级 2',
        children: [{
          id: 5,
          label: '二级 2-1'
        }, {
          id: 6,
          label: '二级 2-2'
        }]
      }, {
        id: 3,
        label: '一级 3',
        children: [{
          id: 7,
          label: '二级 3-1'
        }, {
          id: 8,
          label: '二级 3-2'
        }]
      }];
      return {
        data4: JSON.parse(JSON.stringify(data)),
        data5: JSON.parse(JSON.stringify(data)),
        dataList:[]
      }
    },

    methods: {
      append(data) {
        const newChild = { id: id++, menuName: 'testtest', children: [] };
        if (!data.children) {
          this.$set(data, 'children', []);
        }
        data.children.push(newChild);
      },
      edit(data){

      },
      remove(node, data) {
        const parent = node.parent;
        const children = parent.data.children || parent.data;
        const index = children.findIndex(d => d.id === data.id);
        children.splice(index, 1);
      },

      renderContent(h, { node, data, store }) {
        return (
          <span class="custom-tree-node">
            <span>{node.label}</span>
            <span>
              <el-button size="mini" type="text" on-click={ () => this.append(data) }>Append</el-button>
              <el-button size="mini" type="text" on-click={ () => this.remove(node, data) }>Delete</el-button>
            </span>
          </span>);
      },
        getDataList(){
            this.$axios.post('https://xuelanlan.cn:8080/api/resources/list',{
                entity:"{}"
            }).then(response=>{
                this.dataList = response.data.result;
                console.log(this.dataList)
            }).catch(error=>{
                console.log(error)
            })
        }
    },
    mounted(){
        this.getDataList();
    }
  };
</script>


<style scoped>
.resourceList {
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

.menuName{
  width: 300px;
  display: inline-block;
  text-align: left;
}
/* .resourceList .el-tree-node__content{
  height: 30px !important;
}
.el-tree-node,.is-expanded,.is-focusable{
  height: 30px;
} */
.el-button--mini{
  height: 24px;
}

</style>
