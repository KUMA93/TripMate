import Vue from 'vue'
import VueRouter from 'vue-router'
import AppMain from '@/views/AppMain'
import AppAttraction from '@/views/AppAttraction'
import AppTripPlan from '@/views/AppTripPlan'
import AppHotplace from '@/views/AppHotplace'
import AppBoard from '@/views/AppBoard'
import AppUser from '@/views/AppUser'
import AppNotice from '@/views/AppNotice'

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "AppMain",
    component: AppMain,
  },
  {
    path: "/attraction",
    name: "AppAttraction",
    component: AppAttraction,
  },
  {
    path: "/user",
    name: "AppUser",
    component: AppUser,
  },
  {
    path: "/board",
    name: "AppBoard",
    component: AppBoard,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "BoardList",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "BoardWrite",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardWrite"),
      },
      {
        path: "view/:articleNo",
        name: "BoardView",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardView"),
      },
      {
        path: "modify",
        name: "BoardModify",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardModify"),
      },
      {
        path: "delete",
        name: "BoardDelete",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardDelete"),
      },
    ]
  },
  {
    path: "/notice",
    name: "AppNotice",
    component: AppNotice,
  },
  {
    path: "/hotplace",
    name: "AppHotplace",
    component: AppHotplace,
  },
  {
    path: "/tripplan",
    name: "AppTripPlan",
    component: AppTripPlan,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
