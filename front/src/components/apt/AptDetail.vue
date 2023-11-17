<script setup>
import { useDealStore } from '@/stores/deal';
import { storeToRefs } from 'pinia';

const dealStore = useDealStore();
const { allDeals, oneApt } = storeToRefs(dealStore);
</script>

<template>
  <div>
    <div>
      oneApt Info:
      {{ oneApt.aptName }}
    </div>
    allDeals:
    <div v-for="deal in allDeals">
      {{ deal }}
    </div>
  </div>
</template>

<style scoped lang="scss">
@import '@/assets/scss/apt/detail.scss';
</style>