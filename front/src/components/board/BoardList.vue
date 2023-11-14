<script setup>
import { ref, onMounted } from "vue";
import {listArticle,  } from "@/api/board.js"

const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: 1,
  spp: VITE_ARTICLE_LIST_SIZE,
  key: "",
  word: "",
});

const articles = ref([]);

onMounted(() => {
  listArticle(param.value, 
  ({data}) => {
    console.log(data)
    articles.value = data.articles;
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
  <div class="">
    <div class="">
      <h2 class="">
        <mark class="">글목록</mark>
      </h2>
    </div>
    <div class="">
      <div class="">
        <div class="">
          <button type="button" id="btn-mv-register" class="">글쓰기</button>
        </div>
        <div class="">
          <form class="" id="form-search" action="">
            <input type="hidden" name="action" value="list" />
            <input type="hidden" name="pgno" value="1" />
            <select name="key" id="key" class="" aria-label="검색조건">
              <option value="" selected>검색조건</option>
              <option value="article_no">글번호</option>
              <option value="subject">제목</option>
              <option value="user_id">작성자</option>
            </select>
            <div class="">
              <input
                type="text"
                name="word"
                id="word"
                class=""
                placeholder="검색어..."
              />
              <button id="btn-search" class="" type="button">검색</button>
            </div>
          </form>
        </div>
      </div>
      <table class="">
        <thead>
          <tr class="">
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="article in articles" :key="article.articleNo" class="">
            <th scope="row">{{ article.articleNo }}</th>
            <td class="">
              <a
                href="#"
                class=""
                data-no="${article.articleNo}"
                style="text-decoration: none"
              >
                {{ article.subject }}
              </a>
            </td>
            <td>{{ article.userId }}</td>
            <td>{{ article.hit }}</td>
            <td>{{ article.registerTime }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- 페이지 네비게이션 -->
    <!-- <div class="">${navigation.navigator}</div> -->
  </div>
  <form id="form-param" method="get" action="">
    <input type="hidden" id="p-action" name="action" value="" />
    <input type="hidden" id="p-pgno" name="pgno" value="" />
    <input type="hidden" id="p-key" name="key" value="" />
    <input type="hidden" id="p-word" name="word" value="" />
  </form>
</template>

<style scoped></style>
