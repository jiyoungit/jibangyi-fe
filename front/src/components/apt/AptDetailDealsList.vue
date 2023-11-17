<script setup>

import { computed, reactive, ref, onMounted, watch } from 'vue';
import { getAptDetail } from "@/api/apt";

// test data
const aptCode = ref('11110000000001');

const dealList = ref([])

const sizePerPage = 5;
const currentPage = ref(1);
const totalPage = ref(1);
onMounted(() => {
  loadAptDealList();
})

const loadAptDealList = () => {
  getAptDetail({
    aptCode: aptCode.value,
    pageNo: currentPage.value,
    spp: sizePerPage,
  }, 
  ({ data }) => { 
    dealList.value = data.dealList;
    totalPage.value = data.lastPageNo;
  }, 
  (err) => { console.log(err); });
}

watch(currentPage, () => {
  loadAptDealList();
})

</script>

<template>
  <div>
    <div>
      <v-table density="compact">
        <thead>
          <tr>
            <th class="text-left">
              거래일시
            </th>
            <th class="text-left">
              면적
            </th>
            <th class="text-left">
              층수
            </th>
            <th class="text-left">
              매매가격(만원)
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in dealList" :key="item.no">
            <td>{{ item.dealYear }}-{{item.dealMonth}}-{{item.dealDay }}</td>
            <td>{{ item.area }}</td>
            <td>{{ item.floor }}</td>
            <td>{{ item.dealAmount }}</td>
          </tr>
        </tbody>
      </v-table>

      <v-pagination v-model="currentPage" :length="totalPage" prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"></v-pagination>

    </div>

  </div>
</template>

<style scoped></style>
