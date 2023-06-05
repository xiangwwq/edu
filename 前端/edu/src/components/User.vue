<template>

  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>活动管理</el-breadcrumb-item>
      <el-breadcrumb-item>活动列表</el-breadcrumb-item>

    </el-breadcrumb>

    <el-card class="box-card" v-loading="loading">
      <el-row :gutter="20">
          <el-col :span="6">
            <el-input placeholder="请输入内容" v-model="pageinfo.name" >
              <el-button slot="append" icon="el-icon-search" @click="QueryList"></el-button>
            </el-input>
          </el-col>

          <el-col :span="6">
            <el-button type="primary" @click="dialogVisible=true">添加用户</el-button>
          </el-col>
      </el-row>

      <el-table
          :data="tableData"
          stripe
          style="width: 100%">
        <el-table-column label="#" type="index"></el-table-column>

        <el-table-column
            prop="uid"
            label="编号"
            >
        </el-table-column>
        <el-table-column
            prop="username"
            label="姓名"
            >
        </el-table-column>
        <el-table-column
            prop="phone"
            label="手机号">
        </el-table-column>
        <el-table-column
            prop="password"
            label="密码">
        </el-table-column>
        <el-table-column
            prop="auth"
            label="权限">
          <template v-slot="scope">
            <el-tag  :type="scope.row.auth===1?'danger':'primary'">{{scope.row.auth===1?'管理员':'普通用户'}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template v-slot="scope">
               <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
               <el-button type="danger" icon="el-icon-delete" size="mini" @click="delUser(scope.row.uid)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageinfo.currentPage"
          :page-sizes="[1, 2, 5, 10]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pageTotal">
      </el-pagination>
    </el-card>

    <el-dialog
        title="添加用户"
        :visible.sync="dialogVisible"
        width="50%"
        >
      <el-form  label-width="80px"  >
      <el-form-item label="用户id">
        <el-input v-model="addform.uid"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="addform.username"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="addform.phone"></el-input>
      </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="addform.password"></el-input>
        </el-form-item>
        <el-form-item label="权限">
          <el-input v-model="addform.auth"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary"  @click="addUser()">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "User",
  data(){
    return{
      tableData:[],
      pageinfo:{
        name:"",
        currentPage:1,
        pageSize:5,
      },
      addform:{
        uid:"",
        username:"",
        phone:"",
        password:"",
        auth:""
      },
      pageTotal:0,
      dialogVisible:false,
      loading:true
    }
  },

  methods:{
    QueryList(){
      var vm=this;
      let param = new URLSearchParams()
      param.append('name', this.pageinfo.name)
      param.append('currentPage',this.pageinfo.currentPage)
      param.append('pageSize',this.pageinfo.pageSize)

      this.$http.post("/user/list",param).then(function (res){
        vm.tableData=res.data.data.page.list;
        vm.pageTotal=res.data.data.page.total
        setTimeout(function () {
          // 这里就是处理的事件
          vm.loading=false
        }, 700);
      })
    },
    handleSizeChange(newPagesize){
        this.pageinfo.pageSize=newPagesize
        this.QueryList();
    },
    handleCurrentChange(newPage){
       this.pageinfo.currentPage=newPage
      this.QueryList();
    },
    addUser(){
      this.dialogVisible = false
      var vm=this
      this.$http.post("/user/add",this.addform).then(function (res){
        vm.$message.success("添加成功")
        vm.QueryList();
      },function (res){
        vm.$message.error("添加失败")
      })
    },
    delUser(uid){
      var vm=this
      this.$http.get("/user/del/"+uid).then(function (res){
        vm.$message.success("删除成功")
        vm.QueryList();
      },function (res){
        vm.$message.error("删除失败")
      })
    }


  },
  created() {
    this.QueryList();
  }
}
</script>

<style scoped>
.box-card{
  margin-top: 20px;
}

</style>
