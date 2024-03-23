import { localAxios } from '@/util/http-commons'

const local = localAxios()

async function userConfirm(param, success, fail) {
  await local.post(`/user/login`, param).then(success).catch(fail)
}

async function findById(userid, success, fail) {
  local.defaults.headers['Authorization'] =
    sessionStorage.getItem('accessToken')
  await local.get(`/user/info/${userid}`).then(success).catch(fail)
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers['refreshToken'] =
    sessionStorage.getItem('refreshToken') //axios header에 refresh-token 셋팅
  await local.post(`/user/refresh`, user).then(success).catch(fail)
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail)
}

async function join(user, success, fail) {
  await local.post(`/user/register`, user).then(success).catch(fail)
}

async function checkDuplicate(userid, success, fail) {
  await local.post(`/user/checkDuplicate`, { userId: userid }).then(success).catch(fail)
}

function checkMyHouse(param, success, fail) {
  local.get(`user/myhouse/checkmyhouse`, { params: param }).then(success).catch(fail)
}

function registMyHouse(param, success, fail) {
  local.post(`/user/myhouse`, JSON.stringify(param)).then(success).catch(fail);
}

function deleteMyHouse(param, success, fail) {
  local.delete(`/user/myhouse/userid/${param.userId}/apt/${param.aptCode}`).then(success).catch(fail);
}

function listMyHouse(param, success, fail) {
  local.get(`/user/myhouse`, {params: param}).then(success).catch(fail);
}

export { userConfirm, findById, tokenRegeneration, logout, join, checkDuplicate, checkMyHouse, registMyHouse, deleteMyHouse, listMyHouse }
