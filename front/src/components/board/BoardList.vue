<script setup>
import { ref, onMounted } from "vue";
import { listArticle, } from "@/api/board.js"
import { RouterLink } from "vue-router";

const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: 1,
  spp: VITE_ARTICLE_LIST_SIZE,
  key: "",
  word: "",
});

const articles = ref([]);
const currentPage = ref(1);
const totalPageCount = ref();

onMounted(() => {
  listArticle(param.value,
    ({ data }) => {
      console.log(data)
      articles.value = data.articles;
      currentPage.value = data.currentPage;
      totalPageCount.value = data.totalPageCount;
    },
    (error) => {
      console.log(error);
    })
})
</script>

<template>
  <section>
    <div>
      <div class="registBtn">
        <RouterLink :to="{ name: 'regist' }"><v-btn rounded="lg">문의하기</v-btn></RouterLink>
      </div>
      <table>
        <thead></thead>
        <tbody>
          <!-- "qnaNo": 25,
                          "userId": "ssafy",
                          "userName": "김싸피",
                          "subject": "string1",
                          "content": null,
                          "hit": 0,
                          "registerTime": "2023-11-21 12:30:19" -->
          <tr v-for="article in articles" :key="article.qnaNo">
            <td class="articleSubject">
              <RouterLink :to="{ name: 'detail', params: { 'id': article.qnaNo } }">
                <strong>Q.</strong> {{ article.subject }}
              </RouterLink>
            </td>
            <td class="userName">{{ article.userName.substring(0, 1) }}**</td>
            <td>{{ article.registerTime.substring(0, 4) }}.{{ article.registerTime.substring(5, 7) }}.{{
              article.registerTime.substring(8, 10) }}</td>
            <td>{{ article.hit }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
  <v-pagination v-model="currentPage" :length="totalPageCount" :total-visible="5" prev-icon="mdi-menu-left"
    next-icon="mdi-menu-right" class="mt-8">
  </v-pagination>
</template>

<style scoped lang="scss">
@import '@/assets/scss/board/qna.scss'
</style>
