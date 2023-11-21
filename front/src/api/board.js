import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listArticle(param, success, fail) {
  local.get(`/qna`, { params: param }).then(success).catch(fail);
}

function detailArticle(articleno, success, fail) {
  local.get(`/qna/${articleno}`).then(success).catch(fail);
}

function registArticle(article, success, fail) {
  console.log("qnajs article", article);
  local.post(`/qna`, JSON.stringify(article)).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  local.put(`/qna`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  local.delete(`/qna/${articleno}`).then(success).catch(fail);
}

function getAnswer(answerNo, success, fail) {
  local.get(`/qna/answer/${answerNo}`).then(success).catch(fail);
}

function registAnswer(param, success, fail) {
  local.post(`/qna/answer/`, {params : param}).then(success).catch(fail);
}

function modifyAnswer(param, success, fail) {
  local.put(`/qna/answer/`, {params : param}).then(success).catch(fail);
}

function deleteAnswer(answerNo, success, fail) {
  local.post(`/qna/answer/${answerNo}`).then(success).catch(fail);
}


export {
  listArticle,
  detailArticle,
  registArticle,
  modifyArticle,
  deleteArticle,
  getAnswer,
  registAnswer,
  modifyAnswer,
  deleteAnswer,
};
