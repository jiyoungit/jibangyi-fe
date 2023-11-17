<script setup>
import { ref } from "vue";

let message = ref("");
let userid = ref("");
let userpw = ref("");

////////////////////// 예외 처리
// document.querySelector("#btn-mv-join").addEventListener("click", function () {
// location.href = "${root}/user?action=mvjoin";
// });
// document.querySelector("#btn-login").addEventListener("click", function () {
//   if (!document.querySelector("#userid").value) {
//     alert("아이디 입력!!");
//     return;
//   } else if (!document.querySelector("#userpwd").value) {
//     alert("비밀번호 입력!!");
//     return;
//   } else {
//     let form = document.querySelector("#form-login");
//     form.setAttribute("action", "${root}/user");
//     form.submit();
//   }
// });

function userLogin() {
  console.log("사용자 정보: ", userid, userpw);
}
</script>

<template>
  <section>

  </section>
</template>

<style scoped></style>
