<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="提交审核"/>
    </el-steps>

    <el-form label-width="120px" label-position="left" >

      <el-form-item label="课程标题">
        <el-input v-model="course.title" placeholder="示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"></el-input>
      </el-form-item>


      <el-form-item label="总课时">
        <el-input-number :min="0" v-model="course.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
      </el-form-item>


      <!-- 课程封面-->
      <el-form-item label="课程封面">

        <el-upload
          :show-file-list="false"
          :auto-upload="true"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          action="http://localhost:3000/video/updateImg"
          class="avatar-uploader">
          <img :src="course.cover===''?'../img.png':course.cover" width="200px">
        </el-upload>
      </el-form-item>

      <el-form-item label="课程详情">
        <el-input  rows="5" type="textarea" placeholder="请输入内容" v-model="course.msg"></el-input>
      </el-form-item>

<!--      <el-form-item label="课程价格">-->
<!--        <el-input-number :min="0" v-model="course.price" controls-position="right" placeholder="免费课程请设置为0元"/> 元-->
<!--      </el-form-item>-->

      <el-form-item>

      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">保存并下一步</el-button>
      </el-form-item>

    </el-form>


  </div>
</template>
<script>


export default {
  name:"Info",
  data() {
    return {
      course: {
        title: '',
        lessonNum: 0,
        cover: '',
        price: 10,
        msg:""
      },
      saveBtnDisabled: false,// 保存按钮是否禁用
      courseId:"",
    }
  },

  created() {
    //数据回显

    if (this.$route.params && this.$route.params.id) {
      this.courseId = this.$route.params.id
      console.log("参数" + this.$route.params.id + "|")
      console.log("课程id:" + this.courseId)
      // 根据id获取课程基本信息
      this.getCourseById()
    }
  },
  methods: {
    //根据id查询课程详情

    //根据id回显数据
    getCourseById() {
      var vm=this
      this.$http.get("course/"+this.courseId).then(function (res){
            vm.course=res.data.data.item
      })
    },

    //上传之前
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || 'image/png'

      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG,PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    //上传之后
    handleAvatarSuccess(res, file) {
      console.log(res)// 上传响应
      console.log(URL.createObjectURL(file.raw))// base64编码
      this.course.cover = res.data.url
      console.log("图片路径为:"+this.course.cover)
    },


    addCoures(){
      this.$http.post("course/add",this.course).then(response => {
        let id=response.data.data.id
        this.$message({
          type: 'success',
          message: '保存成功!'
        })
        this.$router.push({ path: '/chapter/'+id } )
      })
    },
    updateCourse(){
      this.$http.post("course/update",this.course).then(response => {

              this.$message({
                  type: 'success',
                  message: '修改成功!'
               })
              this.$router.push({ path: '/chapter/'+this.courseId} )
            })
    },

    //保存并提交
    next() {
      //判断路径中是否有参数
      if (!this.course.id) {
        this.addCoures()
       }else {
        //无参数进行添加
        this.updateCourse()
      }
    }
  }
}
</script>
<style scoped>

/*.app-container {*/
/*  padding: 20px;*/
/*}*/

.el-form{
  width: 30%;
  margin: 0 auto;
}
.el-input-number{
  margin-left: 0px;
}
</style>
