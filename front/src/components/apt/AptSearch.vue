<script setup>
import { ref, watch } from 'vue';
import { useDealStore } from '@/stores/deal';
import { storeToRefs } from 'pinia';

const dealStore = useDealStore();
const { getDongList } = storeToRefs(dealStore);
const searchWord = ref('');

watch(searchWord, () => {
  dealStore.findDong(searchWord.value);
})

const searchDeal = (dong) => {
  dealStore.searchDeal(dong);
  searchWord.value = '';
}
</script>

<template>
  <v-form class="searchInput mx-auto mt-8" color="grey-lighten-3" @keydown.enter.prevent>
    <v-text-field variant="solo" label="동이름을 입력하세요." prepend-inner-icon="mdi-magnify" single-line hide-details
      v-model="searchWord"></v-text-field>
    <v-card v-show="getDongList.length > 0" class="mx-auto overflow-auto" width="390" style="max-height: 350px; ">
      <v-list-item v-for="dong in getDongList" :key="dong.dongCode" @click="searchDeal(dong)">
        {{ dong.sidoName }} {{ dong.gugunName }} {{ dong.dongName }}
      </v-list-item>
    </v-card>
  </v-form>
</template>

<style scoped>
.searchInput {
  z-index: 3;
  position: absolute;
  left: 20px;
  top: 60px;
  width: 400px;
}
</style>