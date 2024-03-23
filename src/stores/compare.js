import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import { getAptInfoByAptCode } from '@/api/apt';

export const useCompareStore = defineStore('compare', () => {
  const compareApts = ref([]);
  
  const apts = computed(() => compareApts.value);

  const getAptInfo = (aptCodes) => {
    compareApts.value = [];
    aptCodes.forEach((aptCode) => {
      getAptInfoByAptCode(aptCode, ({ data }) => {
        compareApts.value = [...compareApts.value, data];
      })
    })
  }

  return {
    compareApts,

    apts,

    getAptInfo,
  }
})