import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import { findDongCode, listAptDealsByAptCode, getAptInfoByAptCode } from '@/api/apt';

export const useDealStore = defineStore(
  'deal',
  () => {
    const dongCode = ref('');
    const dongInfo = ref({});
    const aptDealList = ref([]);
    const aptInfo = ref({});
    const dongInfos = ref([]);

    const allDongs = computed(() => dongInfos.value);
    const dongCoord = computed(() => dongInfo.value);
    const allDeals = computed(() => aptDealList.value);
    const oneApt = computed(() => aptInfo.value);
    
    const findDong = (searchWord) => {
      findDongCode(searchWord, ({ data }) => {
        dongInfos.value = [];
        data.forEach((e) => {
          dongInfos.value.push(e);
        });
      },
      (err) => {
        console.log(err);
      },
      )
    }

    const searchDeal = (dong) => { // dongCode
      dongInfo.value = dong;
    }

    const aptDetail = (aptCode) => {
      listAptDealsByAptCode({ 'aptCode' : aptCode }, ({ data }) => {
        aptDealList.value = [];
        data.forEach((e) => {
          aptDealList.value.push(e);
        })
      })
    }

    const getAptInfo = (aptCode) => {
      getAptInfoByAptCode(aptCode, ({ data }) => {
        aptInfo.value = data;
      })
    }

    return {
      dongCode, dongInfo, dongInfos, aptInfo, aptDealList, allDongs, dongCoord, allDeals, oneApt, searchDeal,findDong, aptDetail, getAptInfo,
    }
  }
)