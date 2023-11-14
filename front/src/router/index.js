import { createRouter, createWebHistory } from 'vue-router';
import MainView from '../views/MainView.vue';
import DealView from '../views/DealView.vue';

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
      component: MainView,
    },
  ],
});

export default router;
