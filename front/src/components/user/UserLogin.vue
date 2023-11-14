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
  <div class="row justify-content-center">
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-3 shadow-sm bg-light text-center">
        <mark class="orange">로그인</mark>
      </h2>
    </div>
    <div class="col-lg-8 col-md-10 col-sm-12">
      <form id="form-login" method="POST" action="">
        <div class="form-check mb-3 float-end">
          <input
            class="form-check-input"
            type="checkbox"
            value="ok"
            id="saveid"
            name="saveid"
          />
          <label class="form-check-label" for="saveid"> 아이디저장 </label>
        </div>
        <div class="mb-3">
          <label for="userid" class="form-label">아이디 : </label>
          <input
            type="text"
            class="form-control"
            id="userid"
            name="userid"
            placeholder="아이디..."
            v-model="userid"
          />
        </div>
        <div class="mb-3">
          <label for="userpwd" class="form-label">비밀번호 : </label>
          <input
            type="password"
            class="form-control"
            id="userpwd"
            name="userpwd"
            placeholder="비밀번호..."
            v-model="userpw"
          />
        </div>
        <div class="text-danger mb-2">{{ message }}</div>
        <div class="col-auto text-center">
          <button
            type="button"
            id="btn-login"
            class="btn btn-outline-primary mb-3"
            @click="userLogin"
          >
            로그인
          </button>
          <button
            type="button"
            id="btn-mv-join"
            class="btn btn-outline-success mb-3"
          >
            회원가입
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped></style>
