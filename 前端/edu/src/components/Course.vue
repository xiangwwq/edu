<template>

  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>课程列表</el-breadcrumb-item>


    </el-breadcrumb>

    <el-card class="box-card" v-loading="loading">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入内容" v-model="pageinfo.name" >
            <el-button slot="append" icon="el-icon-search" @click="QueryList"></el-button>
          </el-input>
        </el-col>

        <el-col :span="3">
          <el-button type="primary" @click="addCourse">添加课程</el-button>
        </el-col>
      </el-row>

      <el-table
          :data="tableData"
          stripe
          style="width: 100%">
        <el-table-column label="#" type="index"></el-table-column>

        <el-table-column
            prop="id"
            label="课程编号"
        >
          <template v-slot="scope">
              00{{scope.row.id}}
          </template>

        </el-table-column>
        <el-table-column
            prop="title"
            label="课程名称"
        >
        </el-table-column>

        <el-table-column
            prop="lessonNum"
            label="课时数">
          <template v-slot="scope">
            {{scope.row.lessonNum}} <el-tag type="info">时</el-tag>
          </template>
        </el-table-column>
        <el-table-column

            label="封面">
          <template v-slot="scope">
          <img :src="scope.row.cover" alt="" width="100px">
          </template>
        </el-table-column>
        <el-table-column
            prop="updateTime"
            label="最近更新时间">

          <template v-slot="scope">
            {{scope.row.updateTime.substring(0,10)}}
<!--            {{scope.row.sendTime.substring(11,19)}}-->
          </template>

        </el-table-column>
        <el-table-column label="操作">
          <template v-slot="scope">
            <el-button type="primary" icon="el-icon-view" size="mini" @click="openChapter(scope.row.id)">编辑课程</el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="del(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageinfo.currentPage"
          :page-sizes="[1, 2, 5, 10]"
          :page-size="pageinfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pageTotal">
      </el-pagination>
    </el-card>


  </div>
</template>

<script>
export default {
  name: "Course",
  data(){
    return{
      tableData:[],
      pageinfo:{
        name:"",
        currentPage:1,
        pageSize:10
      },
      addform:{
        uid:"",
        username:"",
        phone:"",
        password:"",
        auth:""
      },
      pageTotal:0,
      loading:true
    }
  },

  methods:{
    addCourse(){
      this.$router.push({
        path:'/info/',
      })
    },
    del(id){
      var vm=this
      this.$confirm("确定要删除该课程吗").then(function (){
        vm.$http.delete('course/del/'+id).then(function (res){
               vm.$message.success("删除成功！")
          vm.QueryList();
        })
        console.log("删除课程")
      }).catch(function (){
        console.log("取消删除")
      })
    },
    QueryList(){
      var vm=this;
      let param = new URLSearchParams()
      param.append('name', this.pageinfo.name)
      param.append('currentPage',this.pageinfo.currentPage)
      param.append('pageSize',this.pageinfo.pageSize)

      this.$http.post("/course/list",param).then(function (res){
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
    // addUser(){
    //   this.dialogVisible = false
    //   var vm=this
    //   this.$http.post("/course/add",this.addform).then(function (res){
    //     vm.$message.success("添加成功")
    //     vm.QueryList();
    //   },function (res){
    //     vm.$message.error("添加失败")
    //   })
    // },
    // delUser(uid){
    //   var vm=this
    //   this.$http.get("/course/del/"+uid).then(function (res){
    //     vm.$message.success("删除成功")
    //     vm.QueryList();
    //   },function (res){
    //     vm.$message.error("删除失败")
    //   })
    // },
    openChapter(id){

      this.$router.push({
        path:'/info/'+id,
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
