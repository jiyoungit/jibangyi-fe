import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import { findDongCode, getAptDealListByAptCode, getAptInfoByAptCode } from '@/api/apt';

export const useDealStore = defineStore(
  'deal',
  () => {
    const dongCode = ref('');
    const dongInfo = ref({});
    const aptDealList = ref([]);
    const aptInfo = ref({});
    const dongList = ref([]);

    // pagination
    const sizePerPage = 7;
    const totalPage = ref(1);

    // getter
    const getDongList = computed(() => dongList.value);
    const getDongInfo = computed(() => dongInfo.value);
    const getAptDealList = computed(() => aptDealList.value);
    const oneApt = computed(() => aptInfo.value);
    const getTotalPage = computed(() => totalPage.value);
    
    // action
    const findDong = (searchWord) => {
      findDongCode(searchWord, ({ data }) => {
        dongList.value = [];
        data.forEach((e) => {
          dongList.value.push(e);
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

    const aptDetail = async (currentPage) => {
      await getAptDealListByAptCode({
        aptCode: aptInfo.value.aptCode,
        pageNo: currentPage,
        spp: sizePerPage,
      },
        ({ data }) => {
          aptDealList.value = data.dealList;
          totalPage.value = data.lastPageNo;
        },
        (err) => { console.log(err); });
    }

    const getAptInfo = async (aptCode) => {
      await getAptInfoByAptCode(aptCode, ({ data }) => {
        aptInfo.value = data;
      })
    }

    const clearAptInfo = () => {
      aptInfo.value = {};
    }

    return {
      dongCode, dongInfo, dongList, aptInfo, aptDealList, getDongList, getDongInfo, getAptDealList, oneApt, getTotalPage, searchDeal,findDong, aptDetail, getAptInfo, clearAptInfo,
    }
  }
)