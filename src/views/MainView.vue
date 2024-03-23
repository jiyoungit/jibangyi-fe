<script setup>
import { ref } from 'vue';
import MainChart from '../components/main/MainChart.vue';
import MainNews from '../components/main/MainNews.vue';

const searchWord = ref("");

const moveToSearchDeal = () => {

}
</script>

<template>
  <div class="searchWrapper" id="top">
    <div class="searchInner">
      <h1 class="searchText">아파트 정보를 찾아보세요!</h1>
      <router-link to="/deal">
        <v-form class="mx-auto mt-8" color="grey-lighten-3" @click="moveToSearchDeal">
          <v-text-field variant="solo" label="동이름을 입력하세요." prepend-inner-icon="mdi-magnify" single-line hide-details
            v-model="searchWord"></v-text-field>
        </v-form>
      </router-link>
    </div>
  </div>
  <div class="guideCard__Wrapper">
    <div class="guideCard__CardWrapper">
      <div>
        <a href="#guide1">
          <div class="guideCard__Card">
            <div class="guideCard__CardTitle">매매가 비교</div>
            <div class="guideCard__CardContext">분기별, 아파트별 매매가를<br> 한 눈에 비교 분석</div>
          </div>
        </a>
      </div>
      <div>
        <a href="#guide2">
          <div class="guideCard__Card">
            <div class="guideCard__CardTitle">가장 인기많은 동네</div>
            <div class="guideCard__CardContext">어디로 이사 갈까?<br>인기 거래 지역 알아보기</div>
          </div>
        </a>
      </div>
      <div>
        <a href="#guide3">
          <div class="guideCard__Card">
            <div class="guideCard__CardTitle">유용한 소식 제공</div>
            <div class="guideCard__CardContext">최근 부동산 관련 뉴스<br>보러가기</div>
          </div>
        </a>
      </div>
    </div>
  </div>
  <section class="guideFeature" id="guide1">
    <div class="guideFeature__Header">
      <h2 class="guideFeature__Title">분기별 평균 매매가</h2>
      <v-icon class="arrow">mdi-chevron-down</v-icon>
    </div>
    <div class="guideFeature__Wrapper">
      <div class="avgDealCard">
        <MainChart></MainChart>
      </div>
    </div>

  </section>
  <section class="guideFeature" id="guide2">
    <div class="guideFeature__Header secondaryBg">
      <h2 class="guideFeature__Title gray">인기 많은 동네 TOP 3</h2>
      <v-icon class="arrow gray">mdi-chevron-down</v-icon>
    </div>
    <div class="guideFeature__Wrapper">
      <div class="guideFeature__Card">
        <div class="guideFeature__CardContext">
          <span>1. 인천광역시 남동구 만수동</span>
          <img class="guideFeature__Img" src="../assets/imgs/mansudong.png">
        </div>
        <div class="guideFeature__CardContext">
          <img class="guideFeature__Img" src="../assets/imgs/ssangyongdong.png">
          <span>2. 충청남도 천안시 서북구 쌍용동</span>
        </div>
        <div class="guideFeature__CardContext">
          <span>3. 경기도 수원시 영통구 영통동</span>
          <img class="guideFeature__Img" src="../assets/imgs/youngtongdong.png" alt="">
        </div>
      </div>
    </div>
  </section>
  <section class="guideFeature" id="guide3">
    <div class="guideFeature__Header PrimaryBg">
      <h2 class="guideFeature__Title white">최근 부동산 소식</h2>
      <v-icon class="arrow white">mdi-chevron-down</v-icon>
    </div>
    <div class="guideFeature__Wrapper card">
      <div class="guideFeature__Card">
        <div class="guideFeature__CardTitle">뉴스</div>
        <div>
          <MainNews></MainNews>
        </div>
      </div>
    </div>
  </section>
  <aside>
    <a href="#top">
      <v-icon size="x-large">mdi-chevron-up-circle-outline</v-icon>
    </a>
  </aside>
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
  z-index: 0;
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
  padding: 3.5rem 1.5rem 4.35rem 2.5rem;
  position: relative;
  cursor: pointer;
  background-color: rgb(244, 245, 245);
  border-radius: 1.4rem;
}

.guideCard__CardTitle {
  color: rgb(51, 51, 51);
  font-size: 2rem;
  font-weight: 600;
}

.guideCard__CardContext {
  color: rgb(102, 102, 102);
  font-size: 1.5rem;
  line-height: 2.4rem;
  margin-top: 2.5rem;
}

.guideFeature {
  padding-bottom: 12rem;
  background-color: rgb(244, 245, 245);
}

.guideFeature__Header {
  text-align: center;
  padding-top: 6rem;
}

.PrimaryBg {
  width: 100%;
  background-color: #005792;
}

.secondaryBg {
  width: 100%;
  background-color: #d1f4fa;
}

.guideFeature__Title {
  color: rgb(51, 51, 51);
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
  margin: 5.6rem auto;
  width: 100%;
  display: flex;
  justify-content: center;
}

.guideFeature__Card {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.guideFeature__CardTitle {
  color: rgb(17, 17, 17);
  font-size: 2.5rem;
  font-weight: 600;
  line-height: 3.8rem;
  margin-bottom: 2.4rem;
}

.guideFeature__CardContext {
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: rgb(102, 102, 102);
  font-size: 1.5rem;
  font-weight: 400;
  line-height: 2.5rem;
  margin: 2.4rem;
  width: 100%;
}

.guideFeature__Img {
  width: 20rem;
  height: auto;
  border-radius: 1.6rem;
  border: 3px solid rgb(200, 200, 200);
  object-fit: cover;
}

.gray {
  color: rgb(130, 130, 130);
}

.white {
  color: #fff;
}

.avgDealCard {
  padding: 10rem 4.5rem;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 1020px;
  background: rgb(255, 255, 255);
  border-radius: 1.6rem;
}

aside {
  position: fixed;
  bottom: 50px;
  right: 70px;
}

aside>a {
  color: #6d6d6d;
}
</style>
