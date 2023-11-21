import axios from 'axios';

const { VITE_ODSAY_API_URL, VITE_ODSAY_API_SERVICE_KEY } = import.meta.env;

// local vue api axios instance
function makeMetroAxios() {
  const instance = axios.create({
    baseURL: VITE_ODSAY_API_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
    },
  });
  return instance;
}
const metroAxiosInstance = makeMetroAxios();

// const PREFIX_POINT_SEARCH = '/pointSearch';
const PREFIX_POINT_SEARCH = '/pointSearch';

function findMetroListInRadius(_param, success, fail) {
  /*
  정류장 타입 - 1: 버스정류장, 2: 지하철역, ...
  https://lab.odsay.com/guide/releaseReference#pointSearch
  request parameter:
    {
      x: "126.933361407195",
      y: "37.3643392278118",
      radius: "250"(m),
      stationClass: "1:2"
    }
  response data:
  {
    "result": {
      "count": 4,
      "station": [
        {
          "nonstopStation": 0,
          "stationClass": 2,
          "stationName": "역삼", - 정류장명
          "stationID": 221,
          "x": 127.036377, - 경도
          "y": 37.500643, - 위도
          "arsID": "221",
          "type": 2,
          "laneName": "수도권 2호선", - 지하철 호선
          "laneCity": "수도권", - 지하철 호선 도시
          "ebid": "221"
        },
        ...
      ]
    }
  }
  */
  metroAxiosInstance
    .get(`${PREFIX_POINT_SEARCH}`, {
      params: { ..._param, apiKey: VITE_ODSAY_API_SERVICE_KEY },
    })
    .then(success)
    .catch(fail);
}

function testMetroAxios() {
  const param = {
    x: '127.0368861',
    y: '37.5007861',
    radius: '1000', // 1km 반경
    stationClass: '1:2', // 버스정류장, 지하철역 정보
  };
  findMetroListInRadius(
    param,
    ({ data }) => {
      console.log('교통정보 테스트 성공:', data);
    },
    (error) => {
      console.error('교통정보 테스트 실패: ', error);
    }
  );
}

export { findMetroListInRadius, testMetroAxios };
