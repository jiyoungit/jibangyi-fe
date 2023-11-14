<script setup>

import { ref } from "vue";
import { registArticle } from "@/api/board.js"

const subject = ref('')
const userId = ref('ssafy')
const content = ref('')

function regist() {
  const article = {
    subject: subject.value,
    userId: userId.value,
    content: content.value,
  };
  registArticle(article,
  ({data}) => {
    console.log('글 등록 성공', data);
  },
  (error) => {
    console.log(error);
  })
}

// const article = {
//   articleNo: `${1}`,
//   subject: "제목",
//   userId: "아이디",
//   hit: "1",
//   registerTime: "2023",
//   content: "내용 1",
// };

/////////////////////////////// 예외 처리
// document.querySelector("#btn-register").addEventListener("click", function () {
//   if (!document.querySelector("#subject").value) {
//     alert("제목 입력!!");
//     return;
//   } else if (!document.querySelector("#content").value) {
//     alert("내용 입력!!");
//     return;
//   } else {
//     let form = document.querySelector("#form-register");
//     form.setAttribute("action", "${root}/article");
//     form.submit();
//   }
// });
</script>

<template>
  <div class="">
    <div class="">
      <h2 class="">
        <mark class="">글쓰기</mark>
      </h2>
    </div>
    <div class="">
      <form id="form-register" method="POST" action="">
        <input type="hidden" name="action" value="write" />
        <div class="">
          <label for="subject" class="">제목 : </label>
          <input
            type="text"
            class=""
            id="subject"
            name="subject"
            placeholder="제목..."
            v-model="subject"
          />
        </div>
        <div class="">
          <label for="content" class="">내용 : </label>
          <textarea
            class=""
            id="content"
            name="content"
            rows="7"
            v-model="content"
          ></textarea>
        </div>
        <div class="">
          <button
            type="button"
            id="btn-register"
            class=""
            @click="regist"
          >
            글작성
          </button>
          <button type="reset" class="">
            초기화
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped></style>
