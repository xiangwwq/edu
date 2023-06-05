<template>
<div class="box" v-loading="loading">
      <div id="J_prismPlayer"></div>
</div>



</template>

<script>
export default {
  name: "Video",
  data(){
    return{
      path:"",
      videoId:"",
      cover:"",
      loading:true
    }
  },
  methods:{
    init(){
      if (this.$route.params && this.$route.params.id) {
        this.videoId = this.$route.params.id
        // 根据id获取课程基本信息
      }
      var vm=this
      this.$http.get("aliyun/getPath/"+this.videoId).then(function (res){
        vm.path=res.data.data.path
         if(vm.path&&vm.path.length>0){
           new Aliplayer({
             id: 'J_prismPlayer',
             height:"100%",
             width:"100%",
             autoplay:false,
             cover:"",
             source: vm.path,//播放地址，可以是第三方点播地址，或阿里云点播服务中的播放地址。
           },function(player){
             console.log('The player is created.')
           });
           vm.loading=false
         }else {
           vm.$message.error("该小节暂无视频")
           vm.$router.push({path:"/li"})
         }
      })
    },
  },
  created() {
    this.init()
  },
  mounted() {

  }
}
</script>

<style scoped>
.box{
  width: 100%;
  height: 100%;
  background-color: #42b983;
}
</style>
