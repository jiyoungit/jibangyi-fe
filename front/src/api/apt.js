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

function listAptDealsByAptCode(param, success, fail) {
  // const param = {
  //   "aptCode": "",
  //   "dealMonth": 0,
  //   "dealYear": 0,
  //   "limit": 0,
  //   "offset": 0
  // }
  local.get(`${PREFIX}/apt-deals`, { params: param }).then(success).catch(fail)
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

export {
  findDongCode,
  listAptInfos,
  listAptDealsByAptCode,
  listAptInfosByCoold,
}
