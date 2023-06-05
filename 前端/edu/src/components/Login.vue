<template>
   <div id="login">

       <div class="title">课程学习平台</div>
       <div class="box">
         <el-form class="form">
           <el-form-item>
             <el-input prefix-icon="el-icon-phone" v-model="user.phone"></el-input>
           </el-form-item>
           <el-form-item >
             <el-input prefix-icon="el-icon-lock" v-model="user.password" type="password"></el-input>
           </el-form-item>
           <el-form-item  class="btn">
             <el-button type="primary" round @click="login">登录</el-button>
<!--             <el-button type="success" round @click="register">注册</el-button>-->
           </el-form-item>
         </el-form >
       </div>
   </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data(){
    return{
      user:{
        phone:"",
        password:'',
      }
    }
  },
  methods:{
    login(){
      var vm= this
      this.$http.post("user/login",this.user).then(function (res){
        if(res.data.code===20000){
          //登录成功
          // vm.$message.success(res.data.message)
          //存储uid,实现token单点登录
          window.sessionStorage.setItem("uid",res.data.data.uid)
          //路由跳转
          vm.$router.push("/home")
        }else {
           vm.$message.error(res.data.message)
           vm.init()
        }
      })

    },
    init(){
      this.user.password="";
      this.user.phone="";
    },
    register(){
      this.$message.info("此功能付费解锁！！！")
    }
  },
  //钩子函数初始化

  created(){
    this.init()
  }
}
</script>

<style scoped>
  .el-button{
    width: 300px;
  }

   #login{
     background-color: #2D3A4B;
     height: 100%;
     width: 100%;
   }
   .title{
     position: absolute;
     width: 80%;
     height: 100px;
     line-height: 100px;
     font-size: 40px;
     text-align: center;
     letter-spacing: 50px;
     left: 10%;
     top:10%;
     font-weight: bold;
     color: #EEEEEE;
     /*background-color: yellow;*/
   }
   .box{
     background-color: #2A5792;
     width: 450px;
     height: 300px;
     position: absolute;
     left: 49%;
     top:50%;
     transform: translate(-50%,-50%);
     border-radius: 15px;
     border: 1px solid white;
     /*box-shadow: 10px 10px 1px 10px white;*/
   }
   .btn{
     display: flex;
     justify-content: center;
   }
   .form{
       position: absolute;
       bottom:12%;
       width: 70%;
     left: 15%;
   }
</style>
