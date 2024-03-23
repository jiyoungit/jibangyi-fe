<script setup>
import { listMyHouse } from '../../api/user';
import { ref, watch, onMounted } from 'vue';
import { useMemberStore } from '@/stores/member';
import { useCompareStore } from '@/stores/compare'
import { storeToRefs } from 'pinia';

const memberStore = useMemberStore();
const { user } = storeToRefs(memberStore);
const myHouseList = ref([]);

const compareStore = useCompareStore();
const checkedApt = ref([]);

const check = () => {
  if (checkedApt.value.length > 2) {
    checkedApt.value = checkedApt.value.splice(1, 2);
  }
  compareStore.getAptInfo(checkedApt.value);
}

onMounted(() => {
  listMyHouse({ userId: user.value.userId }, ({ data }) => {
    myHouseList.value = data.userHouseList;
  }, (error) => {
    console.log(error);
  });
});
</script>

<!-- "userId": "ssafy123",
"houseInfoDetail": {
  "aptCode": 11110000000002,
  "recentDealAmount": "165,000",
  "minDealAmount": "90000",
  "maxDealAmount": "209000",
  "avgDealAmount": "163150.000000000",
  "aptName": "광화문스페이스본(106동)",
  "address": "서울특별시 종로구 사직동 9-1",
  "buildYear": "2008",
  "firstDealDate": "2021-09-14"
} -->

<template>
  <div class="listWrap">
    <table>
      <thead></thead>
      <tbody>
        <tr v-for="house in myHouseList" :key="house.aptCode">
          <td>
            <div>
              <span>{{ house.houseInfoDetail.aptName }}</span>
              <span class="checkbox"><v-checkbox v-model="checkedApt" :value="house.houseInfoDetail.aptCode"
                  @change="check"></v-checkbox></span>
            </div>
            <div class="address">
              <span>{{ house.houseInfoDetail.address }}</span>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped lang="scss">
@import url('@/assets/scss/myhouse/myhouse.scss');
</style>
