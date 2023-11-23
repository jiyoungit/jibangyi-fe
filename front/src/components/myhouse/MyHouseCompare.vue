<script setup>
import { ref, watch } from 'vue';
import { useCompareStore } from '@/stores/compare'
import { storeToRefs } from 'pinia';

const compareStore = useCompareStore();
const { apts } = storeToRefs(compareStore);

const buildFlag0 = ref();
const buildFlag1 = ref();
const avgPriceFlag0 = ref();
const avgPriceFlag1 = ref();
const recentPriceFlag0 = ref();
const recentPriceFlag1 = ref();

const getPrice = (price) => {
  price = price.replace(/,/gi, '');
  console.log(price);
  return `${(Math.floor(price / 10000)).toLocaleString()}억 ${(price % 10000).toLocaleString()}만원`;
}

watch(apts, () => {
  if (apts.value.length == 2) {
    if (apts.value[0].buildYear < apts.value[1].buildYear) {
      buildFlag0.value = 'worse';
      buildFlag1.value = 'better';
    } else {
      buildFlag0.value = 'better';
      buildFlag1.value = 'worse';
    }

    if (apts.value[0].avgDealAmount < apts.value[1].avgDealAmount) {
      avgPriceFlag0.value = 'better';
      avgPriceFlag1.value = 'worse';
    } else {
      avgPriceFlag0.value = 'worse';
      avgPriceFlag1.value = 'better';
    }

    if (apts.value[0].recentDealAmount.replace(/,/, '') < apts.value[1].recentDealAmount.replace(/,/, '')) {
      recentPriceFlag0.value = 'better';
      recentPriceFlag1.value = 'worse';
    } {
      recentPriceFlag0.value = 'worse';
      recentPriceFlag1.value = 'better';
    }
  }
})

</script>

<template>
  <div class="compareWrap">
    <table v-if="apts.length === 2">
      <thead>
        <th></th>
        <th>{{ apts[0].aptName }}</th>
        <th>{{ apts[1].aptName }}</th>
      </thead>
      <tbody>
        <tr>
          <td class="list">완공 년도</td>
          <td :class="buildFlag0">{{ apts[0].buildYear }}</td>
          <td :class="buildFlag1">{{ apts[1].buildYear }}</td>
        </tr>
        <tr>
          <td class="list">평균 거래가</td>
          <td :class="avgPriceFlag0">{{ getPrice(apts[0].avgDealAmount) }}</td>
          <td :class="avgPriceFlag1">{{ getPrice(apts[1].avgDealAmount) }}</td>
        </tr>
        <tr>
          <td class="list">최근 실거래가</td>
          <td :class="recentPriceFlag0">{{ getPrice(apts[0].recentDealAmount) }}</td>
          <td :class="recentPriceFlag1">{{ getPrice(apts[1].recentDealAmount) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped lang="scss">
@import url('@/assets/scss/myhouse/myhouse.scss');
</style>