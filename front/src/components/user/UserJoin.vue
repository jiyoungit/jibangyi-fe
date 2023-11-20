<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";

const router = useRouter();
const memberStore = useMemberStore();

const { isDuplicatedId } = storeToRefs(memberStore);
const { userJoin, userIdCheck } = memberStore;

const joinUser = ref({
  "userId": "",
  "userPwd": "",
  "userName": "",
  "emailId": "",
  "emailDomain": "",
});
const userPwdCheck = ref("");

const checkFuncs = {
  async isValidId() {
    const validateId = /^[A-Za-z0-9][A-Za-z0-9]*$/;
    return validateId.test(joinUser.value.userId); // 조건 실패시
  },
  async isDupId() {
    await userIdCheck(joinUser.value.userId);
    return !isDuplicatedId.value; // 아이디 중복 시
  },
  async isSamePw() {
    return joinUser.value.userPwd === userPwdCheck.value;
  },
  async isValidPw() {
    const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,24}$/;
    return validatePassword.test(joinUser.value.userPwd);
  },
  async isValidName() {
    //영어, 한글인지 확인
    const checkKorEng = (value) => {
      var count = 0;

      for (var i = 0; i < value.length; i++) {
        if ((value.charCodeAt(i) >= 65 && value.charCodeAt(i) <= 90) || (value.charCodeAt(i) >= 97 && value.charCodeAt(i) <= 122) || (value.charCodeAt(i) >= 44032 && value.charCodeAt(i) <= 55203)) {
          count += 1;
        }
      }

      if (count === (value.length)) {
        return true;
      } else {
        return false;
      }
    };
    return checkKorEng(joinUser.value.userName) && joinUser.value.userName.length >= 2;
  },
  async isValidEmail() {
    const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
    return validateEmail.test(`${joinUser.value.emailId}@${joinUser.value.emailDomain}`);
  }
};

const inputValidator = {
  validId: { check: checkFuncs.isValidId, failed: ref(false), msg: "아이디는 영문과 숫자의 조합으로만 이루어져야 합니다." },
  dupId: { check: checkFuncs.isDupId, failed: ref(false), msg: "아이디가 중복되었습니다." },
  samePw: { check: checkFuncs.isSamePw, failed: ref(false), msg: "동일한 비밀번호를 입력해주세요." },
  validPw: { check: checkFuncs.isValidPw, failed: ref(false), msg: "비밀번호는 8자 이상으로 적어도 하나의 영문, 숫자, 특수기호를 포함해야 합니다." },
  validName: { check: checkFuncs.isValidName, failed: ref(false), msg: "이름은 2자 이상의 영문이나 한글이어야 합니다." },
  validEmail: { check: checkFuncs.isValidEmail, failed: ref(false), msg: "이메일 아이디가 영문, 숫자 이외의 문자이거나 도메인 형식이 잘못되었습니다." },
};

const join = async () => {
  // - 입력 유효성 체크 -
  let failed = false;
  for (const [key, iv] of Object.entries(inputValidator)) {
    console.log(key, iv);
    console.log(await iv.check());
    if (await iv.check()) {
      iv.failed.value = false;
    } else {
      iv.failed.value = true && !failed;
      failed = true;
    }
  }

  if (!failed) {
    await userJoin(joinUser.value);
    alert("회원가입 성공!!!");
    router.push("login");
  }
};

</script>

<template>
  <section>
    <div>
      <header>
        <h1>회원정보 입력</h1>
      </header>
      <form action="">
        <div>
          <header>
            <h1>아이디</h1>
          </header>
          <div class="input">
            <div>
              <div>
                <input type="text" v-model="joinUser.userId" name="id" id="id" placeholder="아이디 입력" />
              </div>
            </div>
          </div>
          <div v-if="inputValidator.validId.failed.value"><span class="text-warn">{{ inputValidator.validId.msg }}</span>
          </div>
          <div v-if="inputValidator.dupId.failed.value"><span class="text-warn">{{ inputValidator.dupId.msg }}</span>
          </div>
        </div>
        <div>
          <header style="margin-top: 14px">
            <h1>비밀번호</h1>
          </header>
          <div class="input">
            <div>
              <div>
                <input type="text" v-model="joinUser.userPwd" name="pw" id="pw" placeholder="8자리 이상 영문, 숫자, 특수문자 포함" />
              </div>
            </div>
          </div>
        </div>
        <div>
          <div class="input">
            <div>
              <div>
                <input type="text" v-model="userPwdCheck" name="pwc" id="pwc" placeholder="비밀번호 확인" />
              </div>
            </div>
          </div>
          <div v-if="inputValidator.samePw.failed.value"><span class="text-warn">{{ inputValidator.samePw.msg }}</span>
          </div>
          <div v-if="inputValidator.validPw.failed.value"><span class="text-warn">{{ inputValidator.validPw.msg }}</span>
          </div>
        </div>
        <div>
          <header>
            <h1>이름</h1>
          </header>
          <div class="input">
            <div>
              <div>
                <input type="text" v-model="joinUser.userName" name="name" id="name" placeholder="이름 입력" />
              </div>
            </div>
          </div>
          <div v-if="inputValidator.validName.failed.value"><span class="text-warn">{{ inputValidator.validName.msg }}</span></div>
        </div>
        <div>
          <header>
            <h1>이메일</h1>
          </header>
          <div class="input">
            <div>
              <div>
                <input type="text" v-model="joinUser.emailId" name="emailid" id="emailid" placeholder="이메일 입력" />
              </div>
            </div>
          </div>
        </div>
        <div>
          <div class="input">
            <div>
              <div>
                <input type="text" v-model="joinUser.emailDomain" name="emaildomain" id="emaildomain"
                  placeholder="이메일 도메인" />
              </div>
            </div>
          </div>
          <div v-if="inputValidator.validEmail.failed.value"><span class="text-warn">{{ inputValidator.validEmail.msg }}</span></div>
        </div>
        <button type="button" @click="join">
          <span>회원가입</span>
        </button>
      </form>
    </div>
  </section>
</template>

<style scoped lang="scss">
@import '@/assets/scss/user/login.scss';
</style>
