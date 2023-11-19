<script setup></script>

<template>
  <section>
    <div>
      <header><h1>회원정보 입력</h1></header>
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
          <header style="margin-top: 14px">
            <h1>비밀번호</h1>
          </header>
          <div class="input">
            <div>
              <div>
                <input type="text" name="id" id="id" placeholder="8자리 이상 영문, 숫자, 특수문자 포함" />
              </div>
            </div>
          </div>
        </div>
        <div>
          <div class="input">
            <div>
              <div>
                <input type="text" name="id" id="id" placeholder="비밀번호 확인" />
              </div>
            </div>
          </div>
        </div>
        <button type="submit" disabled>
          <span>회원가입 완료</span>
        </button>
      </form>
    </div>
  </section>
</template>

<style scoped lang="scss">
@import '@/assets/scss/user/login.scss';
</style>
