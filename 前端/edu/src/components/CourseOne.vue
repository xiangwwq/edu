<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{path:'/list'}">课程管理</el-breadcrumb-item>
      <el-breadcrumb-item>章节管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card" >
      <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
        <el-step title="填写课程基本信息"/>
        <el-step title="创建课程大纲"/>
        <el-step title="提交审核"/>
      </el-steps>

      <div class="block">
        <p>使用 scoped slot</p>
        <el-tree :data="data" node-key="id" :expand-on-click-node="false" highlight-current >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
<!--          <el-button type="primary" size="mini" @click="() => append(data)">添加节点</el-button>-->
<!--          <el-button type="warning" size="mini" @click="() => remove(node, data)">删除节点</el-button>-->
        </span>
      </span>
        </el-tree>
      </div>


      <el-upload
          class="upload-demo"
          action="http://localhost:3000/video/update"
          :show-file-list="true"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :on-error="updateError"
          list-type="picture">
        <el-button size="medium" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>




    </el-card>

  </div>
</template>

<script>
export default {
  name: "CourseOne",
  data() {
    return {
      data: [{
        label: '一级 1',
        children: [{
          label: '二级 1-1',
        }]
      }, {
        label: '一级 2',
        children: [{
          label: '二级 2-1',
        }, {
          label: '二级 2-2',
        }]
      }, {
        label: '一级 3',
        children: [{
          label: '二级 3-1',
        }, {
          label: '二级 3-2',
        }]
      }],
      defaultProps: {
        children: 'children',
        label: 'label'
      },

    }

  },

  methods: {
    updateError(res,file){
      this.$message.error("上传文件失败,服务器异常")
    },
    //上传成功后调用的函数
    handleAvatarSuccess(res, file) {
      this.$message.success("上传图片成功")
      console.log(res);
      this.imageUrl = res.data.url;
      console.log("图片预览的url:"+this.imageUrl);
    },
    //上传成功前调用函数
    beforeAvatarUpload(file) {
      const isJPG =( file.type === "image/jpeg"||file.type==="image/png");
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG ,png 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },

    append(data) {
      const newChild = {id: id++, label: 'testtest', children: []};
      if (!data.children) {
        this.$set(data, 'children', []);
      }
      data.children.push(newChild);
    },

    remove(node, data) {
      const parent = node.parent;
      const children = parent.data.children || parent.data;
      const index = children.findIndex(d => d.id === data.id);
      children.splice(index, 1);
    },


    handleChange(file, fileList) {
      //限制条件可以通过file判断，比如图片格式
      console.log(file, "file");
      console.log(fileList, "fileList");
      let formData = new FormData();
      formData.append("file", file.raw);
      //上传接口
      // addupload(formData,1).then(res => {
      //   //拿到返回的路径
      //   let index = res.data.data.lastIndexOf("=");
      //   let path = res.data.data.substring(index + 1);
      //   this.$message.success("上传成功");
      //   //回显接口
      //   getEventpath(path).then(res => {
      //     //拿到图片路径回显
      //     let url = res.request.responseURL;
      //     this.urlist.push(url);
      //   });
      // });
    },
    Visibleimg(val) {
      this.url = val;
      this.dialogVisibleimg = !this.dialogVisibleimg;
    },


  }



}
</script>

<style scoped>
.box-card{
  margin-top: 20px;
}

.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 25px;
  padding-right: 8px;
  /*padding-left: 50px;;*/



}
.el-tree{
  height: 200px;
  color: black;
  /*font-weight: bold;*/
  font-size: 50px
}
</style>
