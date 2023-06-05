
import Vue from 'vue'   //引入Vue
import VueRouter from 'vue-router'  //引入vue-router
import Login from "@/components/Login";  //引入根目录下的Hello.vue组件
import Home from "@/components/Home";
import Welcome from "@/components/Welcome";
import User from "@/components/User";
import Course from "@/components/Course";
import Info from "@/components/Info";
import Chapter from "@/components/Chapter";
import Publish from "@/components/Publish";
import List from "@/components/List";
import Study from "@/components/Study";
import Video from "@/components/Video";


Vue.use(VueRouter)  //Vue全局使用Router

const router=new VueRouter({
  routes: [              //配置路由，这里是个数组

    {                    //每一个链接都是一个对象
      path: '/login',
      component: Login   //对应的组件模板
    },
    {                    //每一个链接都是一个对象
      path: '/',         //链接路径
      redirect: '/login'   //对应的组件模板
    },
    {
      path:'/home',
      component:Home,
      redirect:'/welcome',
      children:[{
        path:'/welcome',
        component:Welcome,
      },
        {
          path:"/list",
          component: Course,
        },
        {
          path:"/li",
          component:List
        },
        // {
        //   path:'/setting',
        //   component:Setting,
        // },
        {
          path:'/user',
          component:User,
        },

        {
          path:'/study/:id',
          component:Study,
        },
        {
          path:'/video/:id',
          component:Video,
        },
        {
          path:'/info/:id',
          component:Info,
        },
        {
          path:'/info',
          component:Info,
        },
        {
          path:'/chapter/:id',
          component:Chapter,
        },
        {
          path:'/publish/:id',
          component:Publish,
        },
        // {
        //   path:'/warn',
        //   component: Warn,
        // }
      ]
    }
  ]

})
//挂载路由守卫
router.beforeEach((to, from, next)=>{
//to 将要访问哪个路径
  //from 从哪个路径来
  //next表示放行  next()放行  next("/login")强制跳转

  if(to.path==="/login") return next()

  const uidStr= window.sessionStorage.getItem("uid")

  if(!uidStr) return next("/login")

  return next()
})

export default router
