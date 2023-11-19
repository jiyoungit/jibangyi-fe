import { createRouter, createWebHistory } from 'vue-router';
import MainView from '@/views/MainView.vue';
import DealView from '@/views/DealView.vue';
import LoginView from '@/views/LoginView.vue';
import JoinView from '@/views/JoinView.vue';

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
      component: MainView,
    },
    {
      path: '/notice',
      name: 'notice',
      component: MainView,
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

export default router;
