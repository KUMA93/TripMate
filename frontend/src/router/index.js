import Vue from 'vue'
import VueRouter from 'vue-router'
import AppMain from '@/views/AppMain'
import AppAttraction from '@/views/AppAttraction'
import AppTripPlan from '@/views/AppTripPlan'
import AppHotplace from '@/views/AppHotplace'
import AppBoard from '@/views/AppBoard'
import AppUser from '@/views/AppUser'
import AppNotice from '@/views/AppNotice'

import store from "@/store";


Vue.use(VueRouter)

// sessionStorage의 토큰을 비교해서 로그인 상태를 판별해줄 수 있는 변수 onlyAuthUser
// 라우터 객체의 로그인 상태가 필요한 기능의 프로퍼티 beforeEnter: onlyAuthUser 로 처리한다.
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["UserStore/checkUserInfo"];
  const checkToken = store.getters["UserStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("UserStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
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
    children: [
      {
        path: "join",
        name: "join",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserRegister"),
      },
      {
        path: "login",
        name: "login",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserLogin"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserMyPage"),
      },
      {
        path: "update",
        name: "update",
        beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserUpdate"),
      },
      {
        path: "findPw",
        name: "findPw",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserFindPw"),
      },
      {
        path: "changePw/:id",
        name: "changePw",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserChangePw"),
      },
    ]
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
        beforeEnter: onlyAuthUser,
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
    name: "notice",
    component: AppNotice,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "NoticeList",
        component: () => import(/* webpackChunkName: "notice" */ "@/components/notice/NoticeList"),
      },
      {
        path: "write",
        name: "NoticeWrite",
        component: () => import(/* webpackChunkName: "notice" */ "@/components/notice/NoticeWrite"),
      },
      {
        path: "view/:articleNo",
        name: "NoticeView",
        component: () => import(/* webpackChunkName: "notice" */ "@/components/notice/NoticeView"),
      },
      {
        path: "modify",
        name: "NoticeModify",
        component: () => import(/* webpackChunkName: "notice" */ "@/components/notice/NoticeModify"),
      },
      {
        path: "delete",
        name: "NoticeDelete",
        component: () => import(/* webpackChunkName: "notice" */ "@/components/notice/NoticeDelete"),
      },
    ]
  },
  {
    path: "/hotplace",
    name: "AppHotplace",
    component: AppHotplace,
    redirect: "/hotplace/list",
    children: [
      {
        path: "list",
        name: "HotPlaceList",
        component: () => import("@/components/hotplace/HotPlaceList")
      },
      {
        path: "view/:articleNo",
        name: "HotPlaceView",
        component: () => import("@/components/hotplace/HotPlaceView")
      },
      {
        path: "write",
        name: "HotPlaceWrite",
        component: () => import("@/components/hotplace/HotPlaceWrite")
      }
    ]
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
