<script setup>

import { ref, onMounted } from "vue";
import { detailArticle, modifyArticle } from "@/api/board.js"

// const article = {
//   articleNo: `${1}`,
//   subject: "제목",
//   userId: "아이디",
//   hit: "1",
//   registerTime: "2023",
//   content: "내용 1",
// };

const article = ref({})
onMounted(() => {
  const pageNo = 1;
  detailArticle(pageNo, 
  ({data}) => {
    article.value = data;
  }, 
  (error) => {
    console.log(error);
  })
})

function btnModify() {
  console.log(article);
  modifyArticle(article, 
  ({data}) => {
    console.log('글 수정 성공', data);
  }, 
  (error) => {
    console.log(error);
  })
}
/////////////////// 예외 처리
// document.querySelector("#btn-modify").addEventListener("click", function () {
//   if (!document.querySelector("#subject").value) {
//     alert("제목 입력!!");
//     return;
//   } else if (!document.querySelector("#content").value) {
//     alert("내용 입력!!");
//     return;
//   } else {
//     let form = document.querySelector("#form-modify");
//     form.setAttribute("action", "${root}/article");
//     form.submit();
//   }
// });
// document.querySelector("#btn-list").addEventListener("click", function () {
//   location.href = "${root}/article?action=list&pgno=1&key=&word=";
// });
</script>

<template>
  <div class="">
    <div class="">
      <h2 class="">
        <mark class="">글수정</mark>
      </h2>
    </div>
    <div class="">
      <form id="form-modify" method="POST" action="">
        <input type="hidden" name="articleno" v-model="article.articleNo" />
        <div class="">
          <label for="subject" class="">제목 : </label>
          <input
            type="text"
            class=""
            id="subject"
            name="subject"
            v-model="article.subject"
          />
        </div>
        <div class="">
          <label for="content" class="">내용 : </label>
          <textarea
            class=""
            id="content"
            name="content"
            rows="7"
            v-model="article.content"
          ></textarea>
        </div>
        <div class="">
          <button type="button" id="btn-modify" class="" @click="btnModify">
            글수정
          </button>
          <button type="button" id="btn-list" class="">목록으로이동...</button>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped></style>
