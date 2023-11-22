<script setup>
import { ref } from "vue";
import { registArticle } from "@/api/board.js"
import { useMemberStore } from '@/stores/member';
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";

const memberStore = useMemberStore();
const { user } = storeToRefs(memberStore);
const router = useRouter();
const subject = ref('');
const content = ref('');

const rules = ref({
  required: value => !!value || '내용을 입력하세요.',
})

const regist = () => {
  if (!subject.value || !content.value) {
    alert('내용을 입력하세요.');
    return
  }

  const article = {
    subject: subject.value,
    userId: user.value.userId,
    userName: user.value.userName,
    content: content.value,
  };

  registArticle(article,
    (res) => {
      console.log('글 등록 성공', res);
      if (res.status === 201) router.push("/qna");
    },
    (error) => {
      console.log(error);
    })
}
</script>

<template>
  <div>
    <v-form class="pb-16">
      <v-divider></v-divider>
      <v-text-field v-model="subject" :rules="[rules.required]" hide-details label="제목을 입력하세요."
        single-line></v-text-field>
      <v-divider></v-divider>
      <div style="min-height: 300px;">
        <v-textarea class="h-100" :rules="[rules.required]" v-model="content" label="내용을 입력하세요." counter auto-grow
          maxlength="999" single-line bg-color="#fff"></v-textarea>
      </div>
      <div class="btnWrap">
        <RouterLink :to="{ name: 'qna' }"><v-btn rounded="lg">목록</v-btn></RouterLink>
        <v-btn rounded="lg" @click="regist">등록</v-btn>
      </div>
    </v-form>
  </div>
</template>

<style scoped lang="scss">
@import url('@/assets/scss/board/detail.scss');
</style>
