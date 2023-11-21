<script setup>
//////////////////////// 예외처리?
// document.querySelector("#btn-list").addEventListener("click", function () {
//   location.href = "${root}/article?action=list";
// });
// document.querySelector("#btn-mv-modify").addEventListener("click", function () {
//   alert("글수정하자!!!");
//   location.href =
//     "${root}/article?action=mvmodify&articleno=${article.articleNo}";
// });
// document.querySelector("#btn-delete").addEventListener("click", function () {
//   alert("글삭제하자!!!");
//   location.href =
//     "${root}/article?action=delete&articleno=${article.articleNo}";
// });

import { ref, onMounted } from "vue";
import { detailArticle, } from "@/api/board.js"
import { useRouter, useRoute } from "vue-router";
import { deleteArticle, getAnswer } from "../../api/board";

const route = useRoute();
const router = useRouter();

const article = ref({});
const answer = ref({});
const alert = ref(false);

onMounted(() => {
  detailArticle(route.params.id, ({ data }) => {
    article.value = data;
    console.log(article.value);
  }, (error) => {
    console.log(error);
  })
  getAnswer(route.params.id, ({ data }) => {
    answer.value = data;
    console.log(answer.value);
  }, (error) => {
    console.log(error);
  })
})

const deleteQuestion = () => {
  deleteArticle(article.value.qnaNo,
    (res) => {
      if (res.status === 200) router.push("/qna");
    }, (error) => {
      console.log(error);
    });

}

// "articleNo": 500,
// "userId": "ssafy",
// "userName": "김싸피",
// "subject": "rqwe.493",
// "content": "4234",
// "hit": 1,
// "registerTime": "2023-09-22 09:54:47",
// "fileInfos": null

</script>

<template>
  <div>
    <div class="question">
      <div class="questionTitle">
        <p>{{ article.subject }}</p>
      </div>
      <ul>
        <li>등록일 {{ article.registerTime?.substring(0, 4) }}.{{ article.registerTime?.substring(5, 7) }}.{{
          article.registerTime?.substring(8, 10) }}</li>
        <li>조회수 {{ article.hit }}</li>
        <li>{{ article.userName?.substring(0, 1) }}**</li>
      </ul>
      <div class="questionContent">
        <div>
          {{ article.content }}
        </div>
      </div>
    </div>
    <div class="answer">
      <div>
        <div class="answerTitle">
          <p>답변내용</p>
        </div>
        <div class="answerContent">
          <p v-if="answer">{{ answer.content }}</p>
          <p v-else class="empty">아직 등록된 답변이 없습니다.</p>
        </div>
      </div>
    </div>
    <div class="btnWrap">
      <!-- <RouterLink :to="{ name: 'detail', params: { 'id': article.qnaNo } }"></RouterLink> -->
      <RouterLink :to="{ name: 'qna' }"><v-btn rounded="lg">목록</v-btn></RouterLink>
      <!-- <RouterLink :to="{ name: 'modifyQna' }"><v-btn rounded="lg">수정</v-btn></RouterLink> -->

      <v-btn rounded="lg" @click="alert = true">삭제</v-btn>

    </div>
    <div class="alert">
      <v-expand-transition>
        <v-alert v-show="alert" close-label="what" class="w-25 mx-auto">
          <div class="alertText mt-2 ml-1">정말 삭제하시겠습니까?</div>
          <div class="mt-4 mb-2 ml-1">
            <v-btn size="small" rounded="lg" @click="deleteQuestion">확인</v-btn>
            <v-btn class="ml-5" size="small" rounded="lg" @click="alert = false">취소</v-btn>
          </div>
        </v-alert>
      </v-expand-transition>

    </div>
  </div>
</template>

<style scoped>
@import url('@/assets/scss/board/detail.scss');
</style>
