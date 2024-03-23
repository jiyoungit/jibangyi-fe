<script setup>
import { ref } from "vue";

// document.querySelector("#btn-edit").addEventListener("click", function () {
//   if (!document.querySelector("#userpwd").value) {
//     alert("비밀번호 입력!!");
//     return;
//   } else if (
//     document.querySelector("#userpwd").value !=
//     document.querySelector("#pwdcheck").value
//   ) {
//     alert("비밀번호 확인!!");
//     return;
//   } else {
//     alert("회원정보가 수정되었습니다.");
//     let form = document.querySelector("#form-edit");
//     form.setAttribute("action", "${root}/user");
//     form.submit();
//   }
// });
// document.querySelector("#btn-delete").addEventListener("click", function () {
//   let returnValue = confirm("회원탈퇴하시겠습니까?");
//   if (returnValue) {
//     alert("회원탈퇴되었습니다.");
//     let form = document.querySelector("#form-delete");
//     form.setAttribute("action", "${root}/user");
//     form.submit();
//   }
// });
const member = ref({
  userName: "",
  userId: "",
  userPwd: "",
  userPwdConfirm: "",
  emailId: "",
  emailDomain: "",
  joinDate: "",
});
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-8 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">회원정보 조회</mark>
        </h2>
      </div>
      <div class="col-lg-8 col-md-10 col-sm-12">
        <form id="form-edit" method="POST" action="">
          <input type="hidden" name="action" value="edit" />
          <div class="mb-3">
            <label for="username" class="form-label">이름 : </label>
            <input
              type="text"
              class="form-control"
              id="username"
              name="username"
              v-model="member.userName"
            />
          </div>
          <div class="mb-3">
            <label for="userid" class="form-label">아이디 : </label>
            <input
              type="text"
              class="form-control"
              id="userid"
              name="userid"
              v-model="member.userId"
              readonly
            />
          </div>
          <div id="result-view" class="mb-3"></div>
          <div class="mb-3">
            <label for="userpwd" class="form-label">비밀번호 : </label>
            <input
              type="password"
              class="form-control"
              id="userpwd"
              name="userpwd"
              v-model="member.userPwd"
            />
          </div>
          <div class="mb-3">
            <label for="pwdcheck" class="form-label">비밀번호확인 : </label>
            <input
              type="password"
              class="form-control"
              id="pwdcheck"
              v-model="member.userPwdConfirm"
            />
          </div>
          <div class="mb-3">
            <label for="emailid" class="form-label">이메일 : </label>
            <div class="input-group">
              <input
                type="text"
                class="form-control"
                id="emailid"
                name="emailid"
                v-model="member.emailId"
              />
              <span class="input-group-text">@</span>
              <select
                class="form-select"
                id="emaildomain"
                name="emaildomain"
                aria-label="이메일 도메인 선택"
                v-model="member.emailDomain"
              >
                <option selected :value="member.emailDomain">
                  {{ member.emailDomain }}
                </option>
                <option value="ssafy.com">싸피</option>
                <option value="google.com">구글</option>
                <option value="naver.com">네이버</option>
                <option value="kakao.com">카카오</option>
              </select>
            </div>
          </div>
          <div class="mb-3">
            <label for="joinDate" class="form-label">가입 일자 : </label>
            <input
              type="text"
              class="form-control"
              id="userid"
              name="userid"
              v-model="member.joinDate"
              readonly
            />
          </div>
        </form>
        <div class="col-auto text-center">
          <button
            type="button"
            id="btn-edit"
            class="btn btn-outline-primary mb-3"
          >
            회원정보수정
          </button>

          <form id="form-delete" method="post">
            <input type="hidden" name="action" value="delete" />
            <button
              type="button"
              id="btn-delete"
              class="btn btn-outline-success mb-3"
            >
              회원탈퇴
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
