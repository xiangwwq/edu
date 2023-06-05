<template>
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>视频播放</el-breadcrumb-item>
      </el-breadcrumb>

      <el-card class="box-card">

              <div class="box">

                <div class="right">
                  <div class="title">{{course.title}}</div>
                  <div class="video">
                    <img :src="course.cover" alt="" width="100%">
                  </div>
                  <div class="msg">课程介绍:{{course.msg}}</div>
                </div>
                     <div class="left">
                       <div class="chapter" v-for="item in chapterList">{{item.title}}
                        <div v-for="v in item.videoList" @click="changeVideo(v.videoSourcePath)">{{v.title}}</div>
                       </div>
                     </div>

              </div>
      </el-card>
    </div>
</template>

<script>

export default {
  name: "Study",
  data(){
    return{
      course: {
        title: '',
        lessonNum: 0,
        cover: '',
        price: 10,
        msg:""
      },
      chapterList:[],
      cover:"",
      path:"",
      courseId:"",
    }
  },
  methods:{
    changeVideo(id){
      var vm=this
      vm.$router.push({path:"/video/"+id})
    },
    getChapter(){
      var vm=this
      this.$http.get("chapter/getall/"+this.courseId).then(function (res){
           vm.chapterList=res.data.data.allChapterVideo
           // console.log(res.data.data.allChapterVideo)
      })
    },
    getCourseById() {
      var vm=this
      this.$http.get("course/"+this.courseId).then(function (res){
        vm.course=res.data.data.item
      })
    },
    init(){
       if (this.$route.params && this.$route.params.id) {

         this.courseId = this.$route.params.id
         // 根据id获取课程基本信息
       }
     },
  },

  created() {
    this.init()
    this.getChapter()
    this.getCourseById()
  },

}
</script>

<style scoped>
.box-card{
  margin-top: 20px;
  height: 800px;
   display: flex;
  justify-content: center;
  align-items: center;
  background-color: #DDDDDD;
}
.box{
  width: 1400px;
  height: 750px;
  background-color: azure;
  display: flex;
  flex-direction: row;
  box-shadow: 0 15px 30px rgba(0,0,0,.3);

  border-radius: 55px;
}
.left{
  width: 30%;
  height: 100%;
  background-color: #2D3A4B;
  overflow-y:auto;
  overflow-x: hidden;
  border-radius: 0 0 55px;
}
.right{
  width: 70%;
  height: 100%;
  background-color: white;
  display: flex;
  flex-direction: column;
  border-radius: 25% 10%;
}
.title{
  height: 10%;
  color: black;
  width: 100%;
  font-size: 25px;
  line-height: 70px;
  background-color: whitesmoke;
  /*border-radius: 25% 10%;*/
}
.video{
  height: 60%;
  width: 100%;
  background-color: #42b983;
  overflow: hidden;
}
.msg{
  margin-top: 80px;
  width: 100%;
  height: 30%;
  color: black;
  font-size: 20px;
  line-height: 20px;
}
.chapter{
  width: 100%;
  /*height: 30px;*/
  background-color: #DDDDD1;
  font-size: 20px;
}
.chapter div{
  background-color: #DDDDDD;
  font-size: 18px;
  width: 100%;
  border-radius: 3px;
}
.chapter div:hover{
  background-color: gray;
  cursor: pointer;
}

</style>
