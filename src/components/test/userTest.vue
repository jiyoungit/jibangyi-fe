<script setup>
import { ref } from 'vue';
import { storeToRefs } from 'pinia';
import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();
const { isLogin,
  isLoginError,
  userInfo,
  isValidToken, } = storeToRefs(memberStore);

const { userLogin,
  getUserInfo,
  tokenRegenerate,
  userLogout, } = memberStore;

// 로그인 유저 객체
const loginUser = ref({
  userId: "ssafy",
  userPwd: "1234",
});

// 로그인 함수
const login = async () => {
  console.log('로그인 클릭');
  await userLogin(loginUser.value); // 로그인 함수 실행, isLogin 업데이트
  let token = sessionStorage.getItem("accessToken");
  console.log('액세스 토큰: [', token, ']');
  if (isLogin) {
    getUserInfo(token);
    // changeMenuState();
    window.setTimeout(() => {
      alert('20초로 설정한 액세스 토큰 파기');
    },
      20 * 1000);
    window.setTimeout(() => {
      alert('40초로 설정한 리프레쉬 토큰 파기');
    },
      40 * 1000);
    alert("로그인 성공: ", userInfo.value);
  } else {
    alert("로그인 실패");
  }
  // router.push("/");
};

// Vue 네비게이터 가드에 사용하는 함수: 로그인 상태를 체크해서 해당 페이지 진입 가능 여부를 판단
// 액세스 토큰을 먼저 보내보고 로그인
// 리프레시 토큰을 그 다음으로 보내보고 로그인
// 최종적으로 유효한 로그인 상태가 아니면 로그인 페이지로 보냄
const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = sessionStorage.getItem("accessToken");

  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }
  if (!isValidToken.value || userInfo.value === null) {
    next({ name: "user-login" });
  } else {
    next();
  }
};

// Vue 네비게이터 가드 드라이버
const checkUserSession = () => {
  onlyAuthUser('이동할 페이지', '현재 페이지',
    (path) => {
      if (path) {
        alert('세션이 유효하지 않음');
      } else {
        alert('세션이 유효함');
      }
    });
};

// 로그아웃 함수
const logout = async () => {
  await userLogout(loginUser.value.userId);
  alert('로그아웃 완료');
}

</script>

<template>
  <div>
    <button type="button" @click="login">로그인</button>
    <br>
    <br>
    <button type="button" @click="checkUserSession">세션 상태 유효성 확인</button>
    <br>
    <br>
    <button type="button" @click="logout">로그아웃</button>
  </div>
</template>

<style lang="scss" scoped>
button {
  background-color: aqua;
}
</style>
