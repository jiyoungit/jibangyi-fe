<script setup>
import { useDealStore } from '@/stores/deal';
import { storeToRefs } from 'pinia';
import { ref, watch } from 'vue';

const dealStore = useDealStore();
const { getAptDealList, oneApt, getTotalPage } = storeToRefs(dealStore);

const currentPage = ref(1);
watch(currentPage, () => {
  console.log(getAptDealList);
  dealStore.aptDetail(currentPage.value);
})

watch(oneApt, () => {
  currentPage.value = 1;
})

const closeDealList = () => {
  dealStore.clearAptInfo();
}
</script>

<template>
  <aside>
    <div class="aptNameBox">
      <div>
        <h1>{{ oneApt.aptName }}</h1>
        <v-icon class="closeBtn" @click="closeDealList">mdi-close</v-icon>
      </div>
    </div>
    <div class="detail">
      <div class="priceSection">
        <div>
          <div>
            <p>최근 실거래가</p>
            <p>{{ oneApt.recentDealAmount?.substring(0, 1) }}억 {{ oneApt.recentDealAmount?.substring(1) }}만원</p>
          </div>
          <div>
            <p>매물 시세</p>
            <p>{{ Math.floor(oneApt.minDealAmount / 10000) }}억 {{ Math.round(oneApt.minDealAmount %
              10000).toLocaleString()
            }}만원 ~ {{ Math.floor(oneApt.maxDealAmount / 10000) }}억 {{ Math.round(oneApt.maxDealAmount %
  10000).toLocaleString()
}}만원
            </p>
          </div>
        </div>
      </div>
      <hr>
      <div class="basicInfo">
        <div class="location">
          <span>주소</span>
          <p>{{ oneApt.address }}</p>
        </div>
        <div class="homeInfo">
          <ul>
            <li>
              {{ oneApt.buildYear }}년 입주
            </li>
            <li>
              평균 거래가 {{ Math.floor(oneApt.avgDealAmount / 10000) }}억 {{ Math.round(oneApt.avgDealAmount %
                10000).toLocaleString()
              }}만원
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="dealList">
      <table>
        <thead>
          <th>계약일</th>
          <th>실거래 정보</th>
        </thead>
        <tbody>
          <tr v-for="deal in getAptDealList" :key="deal.no">
            <td>
              {{ deal.dealYear }}.{{ deal.dealMonth }}.{{ deal.dealDay }}
            </td>
            <td>
              <div>
                <span>{{ deal.dealAmount?.substring(0, 1) }}억 {{ deal.dealAmount?.substring(1) }}만원</span>
              </div>
              <div>
                <span>{{ deal.area }}㎡</span> &middot;
                <span>{{ deal.floor }}층</span>
              </div>
            </td>

          </tr>
        </tbody>

      </table>
    </div>
    <v-pagination v-model="currentPage" :length="getTotalPage" prev-icon="mdi-menu-left" next-icon="mdi-menu-right">
    </v-pagination>
  </aside>
</template>

<style scoped lang="scss">
@import '@/assets/scss/apt/detail.scss';
</style>