<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/welcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>在线学习</el-breadcrumb-item>




    </el-breadcrumb>

    <el-card class="box-card">

      <div class="li">

        <div class="one" v-for="item in courseList" @click="openCourse(item.id)">
          <div class="imgbox"><img :src="item.cover" alt="" width="100%" ></div>
          <div class="title1">{{ item.title }}</div>
          <div class="title2">{{ item.lessonNum }}</div>
        </div>




      </div>





    </el-card>
  </div>
</template>

<script>
export default {
  name: "List",
  data(){
    return{
      courseList:[]
    }
  },
  methods:{
    getAll(){
      var vm=this
      this.$http.get("course/getAll").then(function (res){
        vm.courseList=res.data.data.list
      })
    },
    openCourse(id){
      this.$router.push({path:"/study/"+id})
    }
  },
  created() {
    this.getAll();
  }
}
</script>

<style scoped>
.box-card{
  margin-top: 20px;
  background-color: #EEEEEE;
}
.li{

  display: flex;
  /*flex-direction: row;*/
  flex-flow:row wrap
}

.one{
  width: 290px;
  height: 220px;
  background-color: white;
  transition: all 0.3s;
  margin: 30px  10px  30px 10px;
}
.one:hover{
  box-shadow: 0 15px 30px rgba(0,0,0,.3);
  transition: all 0.8s;
}
.imgbox{
  width: 290px;
  height: 150px;
  /*background-color: #42b983;*/
  overflow: hidden;
}
.title1{
  width: 100%;
  height: 40px;
  line-height: 40px;
  color: black;
  font-size: 20px;
  text-align: center;
}
.title2{
  width: 100%;
  height: 30px;
  line-height: 30px;
  color: gray;
  font-size: 17px;
  text-align: center;
}
.one img{
  transform: scale(1);
  transition:  all 0.3s;
}
.one img:hover{
  cursor: pointer;
  transform: scale(1.1);
  transition:  all 0.8s;
}

</style>
