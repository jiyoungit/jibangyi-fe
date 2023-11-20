<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
// import { useMenuStore } from "@/stores/menu";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin, isLoginError } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
// const { changeMenuState } = useMenuStore();

const loginUser = ref({
  userId: "",
  userPwd: "",
});

const checkFuncs = {
  async isValidId() {
    const validateId = /^[A-Za-z][A-Za-z0-9]*$/;
    return validateId.test(loginUser.value.userId) && loginUser.value.userId.length > 4; // 조건 실패시
  },
  async isValidPw() {
    const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,24}$/;
    return validatePassword.test(loginUser.value.userPwd) && loginUser.value.userPwd.length >= 8;
  },
};

const inputValidator = {
  validId: { check: checkFuncs.isValidId, failed: ref(false), msg: "유효한 사용자 정보를 입력해주세요." },
  validPw: { check: checkFuncs.isValidPw, failed: ref(false), msg: "유효한 사용자 정보를 입력해주세요." },
};

const login = async () => {
  isLoginError.value = false;
  let failed = false;
  for (const [key, iv] of Object.entries(inputValidator)) {
    console.log(key, iv)
    if (await iv.check()) {
      iv.failed.value = false;
    } else {
      iv.failed.value = true && !failed;
      failed = true;
    }
  }
  if (failed) return;
  
  await userLogin(loginUser.value);
  let token = sessionStorage.getItem("accessToken");
  if (isLogin.value) {
    getUserInfo(token);
    // changeMenuState();
    router.push("/");
  } else {
    alert("로그인 실패");
  }
};

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
                <input type="text" name="id" id="id" placeholder="아이디 입력" v-model="loginUser.userId"/>
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
                <input type="text" name="pw" id="pw" placeholder="비밀번호 입력" v-model="loginUser.userPwd"/>
              </div>
            </div>
          </div>
          <div v-if="inputValidator.validId.failed.value"><span class="text-warn">{{ inputValidator.validId.msg }}</span></div>
          <div v-if="inputValidator.validPw.failed.value"><span class="text-warn">{{ inputValidator.validPw.msg }}</span></div>
          <div v-if="isLoginError"><span class="text-warn">사용자 정보가 잘못 입력되었습니다.</span></div>
        </div>
        <button type="button" @click="login">
          <span>로그인</span>
        </button>
      </form>
      <div class="btnWrap">
        <button>비밀번호 변경</button>
        <button @click="router.push('join')">신규 회원가입</button>
      </div>
    </div>
  </section>
</template>

<style scoped lang="scss">
@import '@/assets/scss/user/login.scss';
</style>
