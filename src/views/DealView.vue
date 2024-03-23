<script setup>
import AptMap from '../components/apt/AptMap.vue';
import AptSearch from "../components/apt/AptSearch.vue";
import AptDetail from '../components/apt/AptDetail.vue';
import { useDealStore } from '@/stores/deal';
import { storeToRefs } from 'pinia';

const dealStore = useDealStore();
const { oneApt } = storeToRefs(dealStore);
</script>
<template>
  <AptSearch />
  <div v-if="Object.keys(oneApt).length > 0">
    <AptDetail />
  </div>

  <AptMap />
</template>

<style scoped></style>
