<template>
  <el-container class="home-container">
<!--    头部区域-->
    <el-header>
      <div class="imgbox">
        <img src="../assets/123.jpg" alt="" id="headimg">
      </div>
      <div class="headtext">课程学习平台</div>
      <div class="closeBtn">{{user.name}}你好 欢迎！<el-button type="info" round @click="close">退出</el-button></div>
    </el-header>
    <el-container>
<!--      侧边栏区域-->
      <el-aside :width="isCollapse?'64px':'200px'">
        <div class="toggle-button" @click="closeToggle">|||</div>
        <el-menu
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#409eff" :unique-opened="true" :collapse="isCollapse" :collapse-transition="false" :router="true"
            :default-active="'/'+activePath">
<!--          一级菜单-->




            <el-menu-item  index="/li" @click="savePath('li')">
              <i class="el-icon-bell"></i>
              <span slot="title">在线学习</span>
            </el-menu-item>


          <el-menu-item v-show="user.auth===1" index="/list" @click="savePath('list')">
            <i class="el-icon-dish"></i>
            <span slot="title">课程管理</span>
          </el-menu-item>

          <el-menu-item  v-show="user.auth===1" index="/user" @click="savePath('user')">
            <i class="el-icon-user"></i>
            <span slot="title">用户管理</span>
          </el-menu-item>



        </el-menu>
      </el-aside>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Home",
  data(){
    return{
      user:{
        uid:"",
        name:"",
        auth:""
      },
       isCollapse:false,
       activePath:"",
    }
  },
  methods: {
    close(){
      window.sessionStorage.clear();
      this.$router.push("/login")
    },
    closeToggle(){
      this.isCollapse=!this.isCollapse
    },
    savePath(path){
      window.sessionStorage.setItem("path",path);
      this.activePath=path;
    },
    searchUserInfo(){
      const uid=window.sessionStorage.getItem("uid")
      var vm=this
      this.$http.get("/user/"+uid).then(function (res){
        // console.log(res.data.data.user.username)
        vm.user.name=res.data.data.user.username
        vm.user.auth=res.data.data.user.auth
        // console.log(vm.user.auth)

        if(vm.user.auth===1){
          vm.$message.success("当前用户状态为管理员")
        }else if(vm.user.auth===0) {
          vm.$message.success("欢迎使用在线学习平台")
        }
      })


    }
  },
  created() {
     this.activePath=window.sessionStorage.getItem("path");
     this.searchUserInfo()
  }
}
</script>

<style scoped>
.home-container{
  height: 100%;
  width: 100%;

}
  .el-header{
    background-color: #2D3A4B;
    display: flex;
    flex-direction: row;
    align-items: center;
    color: white;
    /*font-weight: bold;*/
    font-size: 23px;
    letter-spacing: 5px;
  }
  .el-aside{
    background-color: #2D3A4B;
  }
  .el-main{
    background-color: #eaedf1;
  }

  #headimg{
    /*width: 100%;*/
    height: 90%;
    border-radius: 50%;
    box-shadow: 0 0 10px #eee;
 }
  .imgbox{
    height: 100%;
    margin: 1px;
  }
  #headimg:hover{
    cursor :pointer ;
    box-shadow: 0 0 20px white;
  }
  .headtext{
    margin-left: 25px;
  }
  .closeBtn{
    position: absolute;
    right: 3%;
    font-size: 15px;
    color: antiquewhite;
  }
  .toggle-button{
    background-color: #4a5064;
    width: 100%;
    height: 50px;
    line-height: 50px;
    letter-spacing: 0.2em;
    color: white;
    text-align: center;
    cursor: pointer;
  }
  .toggle-button:hover{
    color: #409eff;
  }

</style>
