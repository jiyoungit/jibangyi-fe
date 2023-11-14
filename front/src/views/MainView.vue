<script setup>
import { ref } from 'vue';
const searchWord = ref(['어쩌고', '저쩌고', '어쩔 TV', '우짤래미']);
</script>

<template>
  <p></p>
  <div class="searchWrapper">
    <div class="searchInner">
      <h1 class="searchText">홍박사님을 아세요?</h1>
      <v-form class="mx-auto mt-8" color="grey-lighten-3">
        <v-text-field variant="solo" label="지역명을 입력하세요." prepend-inner-icon="mdi-magnify" single-line hide-details @click:append-inner="onClick"></v-text-field>
      </v-form>
    </div>
  </div>
  <div class="guideCard__Wrapper">
    <div class="guideCard__CardWrapper">
      <div>
        <a href="#guide1"
          ><div class="guideCard__Card">
            <div class="guideCard__CardTitle">완전 편리</div>
            <div class="guideCard__CardContext">최근 거래 내역을 한 눈에</div>
          </div>
        </a>
      </div>
      <div>
        <a href="#guide2"
          ><div class="guideCard__Card">
            <div class="guideCard__CardTitle">짱짱 간편</div>
            <div class="guideCard__CardContext">인기 거래 지역 알아보기</div>
          </div>
        </a>
      </div>
      <div>
        <a href="#guide3"
          ><div class="guideCard__Card">
            <div class="guideCard__CardTitle">박박 유용</div>
            <div class="guideCard__CardContext">관심있는 인기검색어</div>
          </div></a
        >
      </div>
    </div>
  </div>
  <section class="guideFeature" id="guide1">
    <div class="guideFeature__Header">
      <h2 class="guideFeature__Title">오나전 편리한 서비스</h2>
      <v-icon class="arrow">mdi-chevron-down</v-icon>
    </div>
    <div class="guideFeature__Wrapper card">
      <div class="guideFeature__Card">
        <div class="guideFeature__CardTitle">이런 서비스</div>
        <div class="guideFeature__CardContext">이런 기능도 있답니다?</div>
      </div>
      <img class="guideFeature__Img" src="../assets/imgs/fubaoHello.jpg" alt="" />
    </div>
  </section>
  <section class="guideFeature" id="guide2">
    <div class="guideFeature__Header secondaryBg">
      <h2 class="guideFeature__Title gray">인기있는 동네, 인기없는 동네</h2>
      <v-icon class="arrow gray">mdi-chevron-down</v-icon>
    </div>
    <div class="guideFeature__Wrapper">
      <div class="guideFeature__Card">
        <div class="guideFeature__CardTitle">행운동</div>
        <div class="guideFeature__CardContext">행운동이 가장 인기가 많아요.<br />서울대입구로 이사오세요^_^</div>
      </div>
      <img class="guideFeature__Img" src="../assets/imgs/populargirl.jpg" alt="" />
    </div>
  </section>
  <section class="guideFeature" id="guide3">
    <div class="guideFeature__Header PrimaryBg">
      <h2 class="guideFeature__Title white">이런 거 검색해보삼!</h2>
      <v-icon class="arrow white">mdi-chevron-down</v-icon>
    </div>
    <div class="guideFeature__Wrapper card">
      <div class="guideFeature__Card searchWord">
        <div class="guideFeature__CardTitle">인기검색어</div>
        <div class="guideFeature__CardContext" v-for="(word, index) in searchWord" :key="word">{{ index + 1 }}. {{ word }}</div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.searchWrapper {
  margin: 0;
  width: 100%;
  height: 465px;
  margin-top: -85px;
  margin-bottom: 80px;
  background: rgb(209, 244, 250);
  background: linear-gradient(180deg, #005792 0%, #d1f4fa 100%);
}
.searchInner {
  width: 1200px;
  height: 100%;
  margin: 0 auto;
  padding: 0 10px;
}
.searchText {
  margin-top: 165px;
  line-break: 60px;
  font-family: 'Noto Sans KR';
  font-size: 40px;
  font-weight: 500;
  color: #fff;
}
.guideCard__CardWrapper {
  width: 1020px;
  margin: 0 auto 9.3rem;
  gap: 15px;
  display: flex;
  justify-content: space-between;
}
.guideCard__Card {
  display: flex;
  flex-direction: column;
  width: 320px;
  height: 300px;
  padding: 3.5rem 0 4.35rem 2.5rem;
  position: relative;
  cursor: pointer;
  background-color: rgb(244, 245, 245);
  border-radius: 1.4rem;
}
.guideCard__CardTitle {
  color: rgb(51, 51, 51);
  font-family: 'Noto Sans KR';
  font-size: 2.2rem;
  font-weight: 600;
}
.guideCard__CardContext {
  color: rgb(102, 102, 102);
  font-family: 'Noto Sans KR';
  font-size: 1.5rem;
  line-height: 2.4rem;
  margin-top: 2.5rem;
}
.guideFeature {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-bottom: 12rem;
  background-color: rgb(244, 245, 245);
}
.guideFeature__Header {
  text-align: center;
  padding-top: 6rem;
}
.PrimaryBg {
  width: 100%;
  background-color: #1e7ef9;
}
.secondaryBg {
  width: 100%;
  background-color: #d1f4fa;
}
.guideFeature__Title {
  color: rgb(51, 51, 51);
  font-family: 'Noto Sans KR';
  font-weight: 600;
  font-size: 2.7rem;
  margin-bottom: 5px;
}
.arrow {
  color: rgb(102, 102, 102);
  font-size: 2.5rem;
  margin-top: 20px;
  margin-bottom: 4rem;
}
.guideFeature__Wrapper {
  display: flex;
  justify-content: space-between;
  margin-top: 5.6rem;
  width: 60rem;
}
.card {
  background: rgb(255, 255, 255);
  border-radius: 1.6rem;
}
.guideFeature__Card {
  padding: 4.5rem 0 4.35rem 4.5rem;
  font-family: 'Noto Sans KR';
}
.guideFeature__CardTitle {
  color: rgb(17, 17, 17);
  font-size: 2.5rem;
  font-weight: 600;
  line-height: 3.8rem;
  margin-bottom: 2.4rem;
}
.guideFeature__CardContext {
  color: rgb(102, 102, 102);
  font-size: 1.5rem;
  font-weight: 400;
  line-height: 2.5rem;
  margin-bottom: 2.4rem;
  /* letter-spacing: -0.72px; */
}
.guideFeature__Img {
  width: 20rem;
  height: 20rem;
  margin: 4.5rem;
  border-radius: 1.6rem;
  object-fit: cover;
}
.gray {
  color: rgb(130, 130, 130);
}
.white {
  color: #fff;
}
.searchWord {
  padding-left: 0;
  flex-direction: column;
  text-align: center;
  margin: 0 auto;
}
.searchWord > div {
  margin-bottom: 1.5rem;
}
.infoSection {
  width: 100%;
  padding: 40px 0 27px;
}
.infoSection__Wrapper {
  display: flex;
  width: 1020px;
  margin: 0 auto;
}
.infoSection__Card {
  width: 300px;
}
</style>
