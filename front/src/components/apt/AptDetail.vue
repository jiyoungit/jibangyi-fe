<script setup>
import { useDealStore } from '@/stores/deal';
import { useMemberStore } from '@/stores/member';
import { storeToRefs } from 'pinia';
import { ref, watch, onMounted } from 'vue';
import { checkMyHouse, registMyHouse, deleteMyHouse } from '@/api/user';

const memberStore = useMemberStore();
const { user, loginCheck } = storeToRefs(memberStore);

const dealStore = useDealStore();
const { getAptDealList, oneApt, getTotalPage } = storeToRefs(dealStore);

const currentPage = ref(1);
const liked = ref();
const likeInfo = ref({});

onMounted(() => {
  if (loginCheck.value) {
    likeInfo.value = {
      aptCode: oneApt.value.aptCode,
      userId: user.value.userId,
    }
    checkMyHouse(likeInfo.value,
      ({ data }) => {
        liked.value = data.isUserHouse;
      },
      (error) => {
        console.log(error);
      })
  }
}),

  watch(currentPage, () => {
    console.log('apt list' + JSON.stringify(getAptDealList.value));
    dealStore.aptDetail(currentPage.value);
  })

watch(oneApt, () => {
  currentPage.value = 1;
})

const closeDealList = () => {
  dealStore.clearAptInfo();
}

const likeCliked = () => {
  if (loginCheck.value) {
    registMyHouse(likeInfo.value, (res) => {
      if (res.status === 201) {
        console.log('좋아요!');
        liked.value = true;
      }
    }, (error) => {
      console.log(error);
    })
  }
}

const unlikeCliked = () => {
  if (loginCheck.value) {
    deleteMyHouse(likeInfo.value, (res) => {
      if (res.status === 204) {
        console.log('안좋아요!');
        liked.value = false;
      }
    }, (error) => {
      console.log(error);
    })
  }
}

</script>

<template>
  <aside>
    <div class="aptNameBox">
      <div>
        <div>
          <v-icon class="like" v-show="liked" @click="unlikeCliked">mdi-heart</v-icon>
          <v-icon class="like" v-show="!liked" @click="likeCliked">mdi-heart-outline</v-icon>
          <h1>{{ oneApt.aptName }}</h1>
        </div>
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