import { computed, ref } from 'vue';
import { useRouter } from 'vue-router';
import { defineStore } from 'pinia';
import { jwtDecode } from 'jwt-decode';

import { userConfirm, findById, tokenRegeneration, logout, join, checkDuplicate } from '@/api/user';
import { httpStatusCode } from '@/util/http-status';

export const useMemberStore = defineStore('memberStore', () => {
  const router = useRouter();

  const isLogin = ref(false);
  const isLoginError = ref(false);
  const userInfo = ref(null);
  const isValidToken = ref(false);

  const isJoinError = ref(false);
  const isDuplicatedId = ref(false);

  const isAdmin = ref(true);

  const user = computed(() => userInfo.value);
  const loginCheck = computed(() => isLogin.value);
  const adminCheck = computed(() => isAdmin.value);

  const userLogin = async (loginUser) => {
    await userConfirm(
      loginUser,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          let { data } = response;
          let accessToken = data['access-token'];
          let refreshToken = data['refresh-token'];
          isLogin.value = true;
          isLoginError.value = false;
          isValidToken.value = true;
          sessionStorage.setItem('accessToken', accessToken);
          sessionStorage.setItem('refreshToken', refreshToken);
          console.log('a 액세스 토큰: [', accessToken, ']');
        } else {
          isLogin.value = false;
          isLoginError.value = true;
          isValidToken.value = false;
        }
              },
      (error) => {
        
        isLogin.value = false;
        isLoginError.value = true;
        isValidToken.value = false;
        console.error(error);
      }
    );
  };

  const getUserInfo = async (token) => {
    let decodeToken = jwtDecode(token);
    await findById(
      decodeToken.userId,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          userInfo.value = response.data.userInfo;
          isValidToken.value = true;
        } else {
          console.log('유저 정보 없음!!!!');
        }
      },
      async (error) => {
        // console.error(
        //   'getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ',
        //   error.response.status
        // );
        isValidToken.value = false;

        await tokenRegenerate();
      }
    );
  };

  const tokenRegenerate = async () => {
    await tokenRegeneration(
      JSON.stringify(userInfo.value),
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          let accessToken = response.data['access-token'];
          sessionStorage.setItem('accessToken', accessToken);
          isValidToken.value = true;
        }
      },
      async (error) => {
        // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
        if (error.response.status === httpStatusCode.UNAUTHORIZED) {
          // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
          await logout(
            userInfo.value.userid,
            (response) => {
              if (response.status === httpStatusCode.OK) {
                console.log('리프레시 토큰 제거 성공');
              } else {
                console.log('리프레시 토큰 제거 실패');
              }
              alert('RefreshToken 기간 만료!!! 다시 로그인해 주세요.');
              isLogin.value = false;
              userInfo.value = null;
              isValidToken.value = false;
              router.push({ name: 'login' });
            },
            (error) => {
              console.error(error);
              isLogin.value = false;
              userInfo.value = null;
            }
          );
        }
      }
    );
  };

  const userLogout = async (userid) => {
    sessionStorage.removeItem("accessToken");
    sessionStorage.removeItem("refreshToken");
    isValidToken.value = false;
    await logout( // db에 있는 리프레쉬 토큰을 null값으로 바꿈
      userid,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          isLogin.value = false;
          userInfo.value = null;
        } else {
          console.error('유저 정보 없음!!!!');
        }
      },
      (error) => {
        console.log(error);
      }
    );
  };

  const userJoin = async (joinUser) => {
    await join(
      joinUser,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          isJoinError.value = false;
        } else {
          isJoinError.value = true;
        }
      },
      (error) => {
        console.log(error);
      })
  }

  const userIdCheck = async (userid) => {
    await checkDuplicate(
      userid,
      (response) => {
        if (response.status === httpStatusCode.OK && !response.data.isDuplicated) {
          isDuplicatedId.value = false;
        } else {
          isDuplicatedId.value = true;
        }
      },
      (error) => {
        console.log(error);
      })
  }

  const updateUserState = (flag) => {
    isLogin.value = flag;
  }

  return {
    isLogin,
    isLoginError,
    userInfo,
    isValidToken,
    isDuplicatedId,
    user,
    isAdmin,
    adminCheck,
    loginCheck,

    userLogin,
    getUserInfo,
    tokenRegenerate,
    userLogout,
    userJoin,
    userIdCheck,
    updateUserState,
  };
});
