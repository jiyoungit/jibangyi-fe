<script setup>
import Header from '@/components/common/Header.vue';
import Footer from '@/components/common/Footer.vue';
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';
const location = useRoute();

let isDealPage = ref(false);
watch(location, () => {
  if (location.path === '/deal') isDealPage.value = true;
  else isDealPage.value = false;
})
</script>

<template>
  <div id="app">
    <div class="container">
      <Header />
      <router-view />
      <div v-if="location.path === '/deal'"></div>
      <div v-else>
        <Footer />
      </div>
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
  font-family: 'Apple SD Gothic';
  src: url('assets/fonts/AppleSDGothicNeoT.ttf');
  font-weight: 100;
}

@font-face {
  font-family: 'Apple SD Gothic';
  src: url('assets/fonts/AppleSDGothicNeoUL.ttf');
  font-weight: 200;
}

@font-face {
  font-family: 'Apple SD Gothic';
  src: url('assets/fonts/AppleSDGothicNeoL.ttf');
  font-weight: 300;
}

@font-face {
  font-family: 'Apple SD Gothic';
  src: url('assets/fonts/AppleSDGothicNeoR.ttf');
  font-weight: 400;
}

@font-face {
  font-family: 'Apple SD Gothic';
  src: url('assets/fonts/AppleSDGothicNeoM.ttf');
  font-weight: 500;
}

@font-face {
  font-family: 'Apple SD Gothic';
  src: url('assets/fonts/AppleSDGothicNeoSB.ttf');
  font-weight: 600;
}

@font-face {
  font-family: 'Apple SD Gothic';
  src: url('assets/fonts/AppleSDGothicNeoB.ttf');
  font-weight: 700;
}

@font-face {
  font-family: 'Apple SD Gothic';
  src: url('assets/fonts/AppleSDGothicNeoEB.ttf');
  font-weight: 800;
}

@font-face {
  font-family: 'Apple SD Gothic';
  src: url('assets/fonts/AppleSDGothicNeoH.ttf');
  font-weight: 900;
}
</style>
