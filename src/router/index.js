import { createRouter, createWebHistory } from 'vue-router';
import MainView from '@/views/MainView.vue';
import DealView from '@/views/DealView.vue';
import LoginView from '@/views/LoginView.vue';
import JoinView from '@/views/JoinView.vue';
import BoardView from '@/views/BoardView.vue';
import BoardDetailView from '@/views/BoardDetailView.vue';
import BoardRegistView from '@/views/BoardRegistView.vue';
import DealCompareView from '@/views/DealCompareView.vue';

import { storeToRefs } from 'pinia';
import { useMemberStore } from '@/stores/member';
import { useMenuStore } from '@/stores/menu';

// 로그인 된 유저만 허용하는 가드
const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const menuStore = useMenuStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;
  const { changeMenuState } = menuStore;

  let token = sessionStorage.getItem('accessToken');
  if (token) {
    await getUserInfo(token);
  }
  if (!isValidToken.value || userInfo.value === null) {
    changeMenuState(false);
    next({ name: 'login' });
  } else {
    changeMenuState(true);
    next();
  }
};

// 토큰의 유효성을 확인하고 유효성을 갱신하는 메서드
const checkUserStatus = async (to, from) => {
  const memberStore = useMemberStore();
  const menuStore = useMenuStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo, updateUserState } = memberStore;
  const { changeMenuState } = menuStore;

  let token = sessionStorage.getItem('accessToken');
  if (token) {
    await getUserInfo(token);
  }
  if (!isValidToken.value || userInfo.value === null) {
    updateUserState(false);
    changeMenuState(false);
    // console.log('로그인 체크', false)
    // next({ name: "login" });
  } else {
    updateUserState(true);
    changeMenuState(true);
  }
  // console.log('로그인 체크', loginCheck.value)
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: MainView,
    },
    {
      path: '/deal',
      name: 'deal',
      component: DealView,
    },
    {
      path: '/qna',
      name: 'qna',
      component: BoardView,
    },
    {
      path: '/qna/detail/:id',
      name: 'detail',
      component: BoardDetailView,
    },
    {
      path: '/qna/regist',
      name: 'regist',
      component: BoardRegistView,
      beforeEnter: onlyAuthUser,
    },
    {
      path: '/compare',
      name: 'compare',
      component: DealCompareView,
      beforeEnter: onlyAuthUser,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
    {
      path: '/join',
      name: 'join',
      component: JoinView,
    },
  ],
});

router.afterEach(checkUserStatus);

export default router;
