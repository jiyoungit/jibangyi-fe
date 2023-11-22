import { localAxios } from '@/util/http-commons'

const local = localAxios()
const PREFIX = '/house'

function findDongCode(dongName, success, fail) {
  local.get(`${PREFIX}/dongcodes/${dongName}`).then(success).catch(fail)
}

function listAptInfos(dongCode, success, fail) {
  local
    .get(`${PREFIX}/apt-infos`, { params: { dongCode } })
    .then(success)
    .catch(fail)
}

async function getAptInfoByAptCode(aptCode, success, fail) {
  await local.get(`${PREFIX}/apt-deals/info/${aptCode}`).then(success).catch(fail)
}

function listAptInfosByCoold(param, success, fail) {
  // const param = {
  //   "lat": "",
  //   "lng": "",
  //   "range": 0,
  //   "limit": 0,
  //   "offset": 0
  // }
  local
    .get(`${PREFIX}/apt-infos/coold`, { params: param })
    .then(success)
    .catch(fail)
}

function getAptDealListByAptCode(param, success, fail) {
  // const param = {
  //   "aptCode": "",
  //   "pageNo": 0,
  //   "spp": 0
  // }
  local
    .get(`${PREFIX}/apt-deals`, { params: param })
    .then(success)
    .catch(fail)
}

export {
  findDongCode,
  listAptInfos,
  getAptInfoByAptCode,
  listAptInfosByCoold,
  getAptDealListByAptCode,
}
