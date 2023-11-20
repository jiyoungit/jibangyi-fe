<script setup>
import Header from './components/common/Header.vue';
import Footer from './components/common/Footer.vue';
import MainView from './views/MainView.vue';
import userTest from './components/test/userTest.vue';
</script>

<template>
  <div id="app">
    <div class="container">
      <Header />
      <router-view />
      <Footer />
    </div>
  </div>
</template>

<style scoped>
html {
  scroll-behavior: smooth;
}

.container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 100%;
  min-width: 1200px;
  position: relative;
}

@font-face {
  font-family: 'Noto Sans KR';
  src: url('assets/fonts/NotoSansKR-Regular.otf');
  font-weight: 400;
}

@font-face {
  font-family: 'Noto Sans KR';
  src: url('assets/fonts/NotoSansKR-Medium.otf');
  font-weight: 500;
}

@font-face {
  font-family: 'Noto Sans KR';
  src: url('assets/fonts/NotoSansKR-Light.otf');
  font-weight: 300;
}

@font-face {
  font-family: 'Noto Sans KR';
  src: url('assets/fonts/NotoSansKR-Bold.otf');
  font-weight: 600;
}
</style>
