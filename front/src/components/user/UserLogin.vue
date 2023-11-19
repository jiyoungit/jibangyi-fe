<script setup>
import { ref } from 'vue';

let message = ref('');
let userid = ref('');
let userpw = ref('');

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
  console.log('사용자 정보: ', userid, userpw);
}
</script>

<template>
  <section>
    <div>
      <header><h1>로그인</h1></header>
      <form action="">
        <div>
          <header>
            <h1>아이디</h1>
          </header>
          <div class="input">
            <div>
              <div>
                <input type="text" name="id" id="id" placeholder="아이디 입력" />
              </div>
            </div>
          </div>
        </div>
        <div>
          <header>
            <h1>비밀번호</h1>
          </header>
          <div class="input">
            <div>
              <div>
                <input type="text" name="id" id="id" placeholder="비밀번호 입력" />
              </div>
            </div>
          </div>
        </div>
        <button type="submit">
          <span>로그인</span>
        </button>
      </form>
      <div class="btnWrap">
        <button>비밀번호 변경</button>
        <button>신규 회원가입</button>
      </div>
    </div>
  </section>
</template>

<style scoped lang="scss">
@import '@/assets/scss/user/login.scss';
</style>
