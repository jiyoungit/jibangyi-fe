<script setup>
import axios from 'axios';
import { ref, watch, onMounted } from 'vue';

const data = ref([]);

onMounted(() => {
  data.value = [];

  // RSS 피드 URL
  const rssFeedURL = 'https://www.fnnews.com/rss/r20/fn_realnews_realestate.xml'; // 여기에 실제 RSS 피드 URL을 넣어주세요.

  // Axios를 이용하여 RSS 데이터 요청
  axios.get(rssFeedURL)
    .then(response => {
      // 요청이 성공한 경우
      const xmlData = new DOMParser().parseFromString(response.data, 'text/xml');

      // channel 요소 가져오기
      const channel = xmlData.querySelector('channel');

      // 모든 아이템 요소 가져오기
      const items = Array.from(channel.querySelectorAll('item'));



      // 각 아이템에서 필요한 데이터 추출하기 예시
      items.forEach((item, index) => {

        const title = item.querySelector('title').textContent;
        const link = item.querySelector('link').textContent;
        const description = item.querySelector('description').textContent;
        const category = item.querySelector('category').textContent;
        const author = item.querySelector('author').textContent;
        const pubDate = item.querySelector('pubDate').textContent;
        const guid = item.querySelector('guid').textContent;

        // 각 아이템에 대한 처리 (예시로 title 출력)
        // console.log(`Item ${index + 1} Title:`, title);
        if (index < 6) {
          data.value.push({ index, title, author, pubDate, link });
        }
      });
    })
    .catch(error => {
      // 요청이 실패한 경우
      console.error('RSS 데이터를 불러오는데 실패했습니다.', error);
    });
})

</script>

<template>
  <table>
    <thead>
    </thead>
    <tbody>
      <tr v-for="d in data" :key="d.index">
        <td><a :href="d.link" class="news-title">{{ d.title }}</a></td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped>
table {
  width: 1020px;
  background-color: #fff;
  border-radius: 1.6rem;
}

tbody {
  padding: 3rem 10rem;
  /* padding-left: 10rem; */
  display: flex;
  flex-direction: column;
  align-items: left;
}

tr {
  border-bottom: 1px solid rgb(229, 229, 229);
}

tr:last-child {
  border-bottom: none;
}

td {
  padding: 1rem;
  text-align: left;
  width: 100%;
}

a {
  font-size: 1rem;
}
</style>