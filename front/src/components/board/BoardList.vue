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



//////////////// 조회 버튼
// let titles = document.querySelectorAll(".article-title");
// titles.forEach(function (title) {
//   title.addEventListener("click", function () {
//     console.log(this.getAttribute("data-no"));
//     location.href =
//       "${root}/article?action=view&articleno=" + this.getAttribute("data-no");
//   });
// });

//////////////// 새 글 등록 버튼
// document
//   .querySelector("#btn-mv-register")
//   .addEventListener("click", function () {
//     location.href = "${root}/article?action=mvwrite";
//   });

//////////////// 검색 버튼
// document.querySelector("#btn-search").addEventListener("click", function () {
//   let form = document.querySelector("#form-search");
//   form.setAttribute("action", "${root}/article");
//   form.submit();submit
// });

//////////////// 뭔지 모름
// let pages = document.querySelectorAll(".page-link");
// pages.forEach(function (page) {
//   page.addEventListener("click", function () {
//     console.log(this.parentNode.getAttribute("data-pg"));
//     document.querySelector("#p-action").value = "list";
//     document.querySelector("#p-pgno").value =
//       this.parentNode.getAttribute("data-pg");
//     document.querySelector("#p-key").value = "${param.key}";
//     document.querySelector("#p-word").value = "${param.word}";
//     document.querySelector("#form-param").submit();
//   });
// });

</script>

<template>
  <section>
    <div>
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
