<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="提交审核"/>
    </el-steps>

    <el-button type="text" @click="openDialogChapter">添加章节</el-button>
    <!-- 章节 -->
    <ul class="chanpterList">
      <li
          v-for="chapter in chapterNestedList"
          :key="chapter.id">
        <p>
          {{ chapter.title }}

          <span class="acts">
                <el-button type="text" @click="openVideo(chapter.id)">添加课时</el-button>
                <el-button style="" type="text" @click="openEditChapter(chapter.id)">编辑</el-button>
                <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
            </span>
        </p>

        <!-- 视频 -->
        <ul class="chanpterList videoList">
          <li
              v-for="video in chapter.videoList"
              :key="video.id">
            <p>{{ video.title }}
              <span class="acts">

                        <el-button type="text">编辑</el-button>
                        <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
                    </span>
            </p>
          </li>
        </ul>
      </li>
    </ul>
    <div>
      <el-button @click="previous">上一步</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
    </div>


    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title"/>
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
      </div>
    </el-dialog>



    <!-- 添加和修改课时表单 -->

    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title"/>
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
        </el-form-item>
        <el-form-item label="上传视频">
          <el-upload
              :on-success="handleVodUploadSuccess"
              :on-remove="handleVodRemove"
              :before-remove="beforeVodRemove"
              :on-exceed="handleUploadExceed"
              :file-list="fileList"
              action="http://localhost:3000/aliyun/upload"
              :limit="1"
              class="upload-demo">
            <el-button size="small" type="primary">上传视频</el-button>
            <el-tooltip placement="right-end">
              <div slot="content">最大支持500MB，<br>
                支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
                GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
                MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
                SWF、TS、VOB、WMV、WEBM 等视频格式上传</div>
              <i class="el-icon-question"/>
            </el-tooltip>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

export default {
  name:'Chapter',
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      courseId: '', // 所属课程
      chapterNestedList: [] ,// 章节嵌套课时列表
      chapter:{
        courserId:"",
        title: '',
        sort: 0
      },

      video: {// 课时对象
        title: '',
        sort: 0,
        videoSourcePath:"",
      },
      saveVideoBtnDisabled:false,
      dialogChapterFormVisible:false,
      dialogVideoFormVisible:false,
      fileList: [],//上传文件列表
      BASE_API: process.env.BASE_API // 接口API地址
    }
  },

  created() {
    console.log('chapter created')
    this.init()

  },

  methods: {
    // 点×调用的方法  提示是否删除
    beforeVodRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    //点确定删除调用的方法
    handleVodRemove(file, fileList) {
      console.log("将要删除")
      console.log(file)
      if(this.video.videoSourcePath&&this.video.videoSourcePath.length>0){
        var vm=this
        this.$http.delete("aliyun/"+this.video.videoSourcePath).then(function (res){
          vm.$message({
            type: 'success',
            message: "删除成功"
          })
          //文件列表清空
          vm.fileList=[];
        })
      }else {

      }
    },

    //===================================================小节操作=================================================
    //成功回调
    handleVodUploadSuccess(response, file, fileList) {
      this.video.videoSourcePath=response.data.videoId
      console.log("url"+response.data.videoId)
      console.log(file.name)
      if(response.data.videoId&&response.data.videoId.length>0){
        this.$message.success("上传视频成功")
      }else {
        this.$message.error("上传视频失败")
      }

    },
    //视图上传多于一个视频
    handleUploadExceed(files, fileList) {
      this.$message.warning('想要重新上传视频，请先删除已上传的视频')
    },


    //删除小节
    removeVideo(VideoId){
      this.$confirm('此操作将永久删除该小节, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var vm=this
        this.$http.get("video/del/"+VideoId).then(function (res){
            //删除成功提示信息
            vm.$message({
              type: 'success',
              message: '删除小节成功!'
            });
            //再次查询
            vm.getAllChapterVideo();
        })
      })

    },
    //打开弹框添加小节
    openVideo(id){
      //清空表单
      this.video={}
      this.fileList=[]
      this.dialogVideoFormVisible=true
      //设置章节id
      this.video.chapterId=id
    },
    //添加小节
    addVideo(){
      //设置课程id
      if (this.$route.params && this.$route.params.id) {
        this.video.courseId = this.$route.params.id
      }
      var vm=this
      this.$http.post("video/add",this.video).then(function (res){
          //关闭弹框
          vm.dialogVideoFormVisible=false
          //提示信息
          vm.$message({
            type: 'success',
            message: '添加小节成功!'
          })
          //刷新页面
          vm.getAllChapterVideo();
      })
    },

    //小节确认
    saveOrUpdateVideo(){
      //判断小节id是否存在
      if(!this.video.id){
        this.addVideo()
      }
    },



    //===================================================章节操作==================================================
    //删除章节
    removeChapter(chapterid){

      this.$confirm('此操作将永久删除该章节, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var vm=this
        this.$http.get("chapter/del/"+chapterid).then(function (res){
          vm.$message.success("删除成功！")
          vm.getAllChapterVideo();
        })
      }).catch(()=>{
        // this.$message.success("取消删除")
      })
    },
    //打开编辑窗口进行数据回显
    openEditChapter(chapterid){
      this.dialogChapterFormVisible=true
      var vm=this;
      this.$http.get("chapter/getone/"+chapterid).then(function (res){
        vm.chapter=res.data.data.chapter
        console.log(vm.chapter)
      })
      // chapter.getById(chapterid).then(response=>{
      //   this.chapter=response.data.chapter
      // })
    },
    //打开弹框
    openDialogChapter(){
      //清空表单
      this.chapter.title='',
          this.chapter.sort=0,
          this.dialogChapterFormVisible=true
    },
    addChapter(){
      var vm=this;
      this.chapter.courserId=this.courseId;
      this.$http.post("chapter/add",this.chapter).then(function (res){
          //关闭弹框
          vm.dialogChapterFormVisible=false
          //提示信息
          vm.$message({
            type: 'success',
            message: '添加章节成功!'
          })
          //刷新页面
          vm.getAllChapterVideo();
      })
    },
    updateChapter(){
      var vm=this
      this.$http.post("chapter/update",this.chapter).then(function (res){
          //关闭弹框
          vm.dialogChapterFormVisible=false
           vm.$message.success("修改成功")
          //刷新页面
           vm.getAllChapterVideo();
      })
    },
    //添加章节
    saveOrUpdate(){
      //没有id就进行添加
      if(!this.chapter.id){
        this.addChapter();
      }else {
        //有id就就行修改
        this.updateChapter()
      }
    },

    init() {

      if (this.$route.params && this.$route.params.id) {

        this.courseId = this.$route.params.id
        // 根据id获取课程基本信息
        this.getAllChapterVideo()
      }
    },

    //获得章节与小节信息
    getAllChapterVideo() {
      var vm=this
      this.$http.get("chapter/getall/"+this.courseId).then(function (res){
        vm.chapterNestedList=res.data.data.allChapterVideo;
        // console.log(vm.chapterNestedList)
      })
    },
    previous() {
      console.log('previous')
      this.$router.push({ path: '/info/'+ this.courseId  })
    },

    next() {
      console.log('next')
      this.$router.push({ path: '/publish/'+this.courseId })
    }
  }
}
</script>
<style scoped>
.chanpterList{
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}
.chanpterList li{
  position: relative;
}
.chanpterList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}
.chanpterList .acts {
  float: right;
  font-size: 14px;
}

.videoList{
  padding-left: 50px;
}
.videoList p{
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}

</style>
