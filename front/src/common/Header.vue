<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <nav>
    <RouterLink :to="{ name: 'home' }"><img class="logo" src="../assets/imgs/logo.jpg" alt="" /></RouterLink>
    <div class="menuwrap">
      <RouterLink :to="{ name: 'deal' }"><span class="menu">집구하기</span></RouterLink>
      <RouterLink :to="{ name: 'qna' }"><span class="menu">Q&A 게시판</span></RouterLink>
      <RouterLink :to="{ name: 'notice' }"><span class="menu">공지사항</span></RouterLink>
      <RouterLink :to="{ name: 'login' }"><v-btn class="menu loginBtn">로그인 | 회원가입</v-btn></RouterLink>
    </div>
  </nav>
</template>

<style scoped>
nav {
  display: flex;
  flex: 0 0 auto;
  width: 100%;
  align-items: center;
  height: 80px;
  padding: 0 20px;
  background: transparent;
}
.logo {
  width: 10rem;
}
.menuwrap {
  display: flex;
  flex: 0 0 auto;
  align-items: center;
  margin-left: auto;
  font-family: 'Noto Sans KR';
}
.menu {
  font-size: 16px;
  line-height: 26px;
  position: relative;
  margin-left: 40px;
  background: transparent;
  color: #ddd;
  /* mix-blend-mode: difference; */
}
.menu:hover {
  color: #fff;
}
.loginBtn {
  border: 1px solid #fff;
  border-radius: 10px;
}
</style>
