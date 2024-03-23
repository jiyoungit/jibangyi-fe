<script setup>
import MyHouseList from '@/components/myhouse/MyHouseList.vue';
import MyHouseCompare from '@/components/myhouse/MyHouseCompare.vue';
</script>

<template>
  <div>
    <header>
      <div>
        <h1>매물 비교</h1>
      </div>
    </header>
    <section>
      <MyHouseList />
      <MyHouseCompare />
    </section>
  </div>
</template>

<style scoped>
div {
  width: 1020px;
  margin: 0 auto;
}

section {
  display: flex;
  justify-content: space-evenly;
  align-items: flex-start;
  margin-bottom: 200px;
}

header {
  width: 100%;
}

header>div {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  height: 278px;
  position: relative;
  margin: 0 auto;
  padding: 0 10px;
}

header>div>h1 {
  margin: 80px 0px 64px;
  font-size: 2.5rem;
  font-weight: 700;
}
</style>