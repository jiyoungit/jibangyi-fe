<script setup>
import { ref, onMounted } from "vue";
import { detailArticle, } from "@/api/board.js"
import { useRouter, useRoute } from "vue-router";
import { deleteArticle, getAnswer, registAnswer, modifyAnswer } from "../../api/board";
import { useMemberStore } from '@/stores/member';
import { storeToRefs } from "pinia";

const memberStore = useMemberStore();
const { user, adminCheck } = storeToRefs(memberStore);

const route = useRoute();
const router = useRouter();

const article = ref({});
const answer = ref({});
const answering = ref(false);
const alert = ref(false);

onMounted(() => {
  detailArticle(route.params.id, ({ data }) => {
    article.value = data;
  }, (error) => {
    console.log(error);
  })
  getAnswer(route.params.id, ({ data }) => {
    if (data === '') answer.value = { content: '' }
    else answer.value = data;
  }, (error) => {
    console.log(error);
  })
})

const deleteQuestion = () => {
  deleteArticle(article.value.qnaNo,
    (res) => {
      if (res.status === 200) router.push("/qna");
    }, (error) => {
      console.log(error);
    });
}

const startWriteAnswer = () => {
  answering.value = true;
}

const writeAnswer = () => {
  const param = {
    content: answer.value.content,
    qnaNo: article.value.qnaNo,
    userId: user.value.userId,
    userName: user.value.userName,
  }

  registAnswer(param, (res) => {
    if (res.status === 201) {
      answering.value = false;
    }
  }, (error) => {
    console.log(error);
  })
}

// "articleNo": 500,
// "userId": "ssafy",
// "userName": "김싸피",
// "subject": "rqwe.493",
// "content": "4234",
// "hit": 1,
// "registerTime": "2023-09-22 09:54:47",
// "fileInfos": null

</script>

<template>
  <div>
    <div class="question">
      <div class="questionTitle">
        <p>{{ article.subject }}</p>
      </div>
      <ul>
        <li>등록일 {{ article.registerTime?.substring(0, 4) }}.{{ article.registerTime?.substring(5, 7) }}.{{
          article.registerTime?.substring(8, 10) }}</li>
        <li>조회수 {{ article.hit }}</li>
        <li>{{ article.userName?.substring(0, 1) }}**</li>
      </ul>
      <div class="questionContent">
        <div>
          {{ article.content }}
        </div>
      </div>
    </div>
    <div class="answer">
      <div>
        <div class="answerTitle">
          <p>답변내용</p>
        </div>
        <div class="answerContent">
          <p v-if="!!answer.content && !answering">{{ answer.content }}</p>
          <p v-else-if="answering">
            <v-textarea v-model="answer.content" variant="underlined"></v-textarea>
          </p>
          <p v-else class="empty">아직 등록된 답변이 없습니다.</p>
        </div>
      </div>
    </div>
    <div class="btnWrap">
      <RouterLink :to="{ name: 'qna' }"><v-btn rounded="lg">목록</v-btn></RouterLink>
      <v-btn rounded="lg" @click="alert = true">삭제</v-btn>
      <v-btn v-if="adminCheck && !answering" rounded="lg" @click="startWriteAnswer">답변</v-btn>
      <v-btn v-if="adminCheck && answering" rounded="lg" @click="writeAnswer">등록</v-btn>
    </div>
    <div class="alert">
      <v-expand-transition>
        <v-alert v-show="alert" close-label="what" class="w-25 mx-auto">
          <div class="alertText mt-2 ml-1 pr-3 d-flex justify-space-between w-100">정말
            삭제하시겠습니까?
            <v-btn icon="mdi-close" size="x-small" variant="plain" density="compact" @click="alert = false"></v-btn>
          </div>
          <div class="mt-4 mb-2 ml-1">
            <v-btn size="small" rounded="lg" @click="deleteQuestion">확인</v-btn>
            <v-btn class="ml-5" size="small" rounded="lg" @click="alert = false">취소</v-btn>
          </div>
        </v-alert>
      </v-expand-transition>

    </div>
  </div>
</template>

<style scoped>
@import url('@/assets/scss/board/detail.scss');
</style>
