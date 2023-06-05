<template>


  <div class="box">
<!--    <h3>欢迎</h3>-->
    <e-charts class="chart" :option="option"></e-charts>
  </div>
</template>

<script>
import * as echarts from "echarts";
export default {
  name: "Welcome",
  data(){
    return{
       ROOT_PATH :'https://echarts.apache.org/examples',
       option: {
         graphic: {
           elements: [
             {
               type: 'text',
               left: 'center',
               top: 'center',
               style: {
                 text: '课程学习平台',
                 fontSize: 80,
                 fontWeight: 'bold',
                 lineDash: [0, 200],
                 lineDashOffset: 0,
                 fill: 'transparent',
                 stroke: '#000',
                 lineWidth: 1
               },
               keyframeAnimation: {
                 duration: 3000,
                 loop: true,
                 keyframes: [
                   {
                     percent: 0.7,
                     style: {
                       fill: 'transparent',
                       lineDashOffset: 200,
                       lineDash: [200, 0]
                     }
                   },
                   {
                     // Stop for a while.
                     percent: 0.8,
                     style: {
                       fill: 'transparent'
                     }
                   },
                   {
                     percent: 1,
                     style: {
                       fill: 'black'
                     }
                   }
                 ]
               }
             }
           ]
         }
       }
    }
  }
}
</script>

<style scoped>
.box{
  height: 800px;
}

.chart{
  height: 800px;
  /*background-color: #409eff;*/
}
</style>
