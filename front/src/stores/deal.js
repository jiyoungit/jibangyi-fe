import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import { findDongCode, listAptInfos } from '@/api/apt';

export const useDealStore = defineStore(
  'deal',
  () => {
    const dongCode = ref('');
    const dongInfo = ref({});
    const dongInfos = ref([]);
    const dealInfos = ref([]);

    const allDongs = computed(() => dongInfos.value);
    const allDeals = computed(() => dealInfos.value);
    const dongCoord = computed(() => dongInfo.value);
    
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
      listAptInfos(dong.dongCode, ({ data }) => {
        dealInfos.value = [];
        data.forEach((e) => {
          dealInfos.value.push(e);
        })
      },
      (err) => {
        console.log(err);
      })
    }

    return {
      dongCode, dongInfo, dongInfos, dealInfos, allDongs, allDeals, dongCoord, searchDeal,findDong
    }
  }
)