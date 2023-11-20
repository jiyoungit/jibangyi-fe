<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
// import { useMenuStore } from "@/stores/menu";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
// const { changeMenuState } = useMenuStore();

const loginUser = ref({
  userId: "",
  userPwd: "",
});

const login = async () => {
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
