import { ref } from "vue";
import { defineStore } from "pinia";

export const useMenuStore = defineStore("menuStore", () => {
  const menuList = ref([
    { name: "로그인 | 회원가입", show: true, default: true, routeName: "login" },
    // { name: "로그인", show: true, routeName: "user-login" },
    // { name: "오늘할일", show: false, routeName: "todos" },
    // { name: "내정보", show: false, routeName: "user-mypage" },
    { name: "로그아웃", show: false, default: false, routeName: "logout" },
  ]);

  const changeMenuState = (isLoggedIn) => {
    menuList.value = menuList.value.map(
      (item) => ({ ...item, show: (isLoggedIn ? !item.default : item.default) }));
  };
  return {
    menuList,
    changeMenuState,
  };
});
