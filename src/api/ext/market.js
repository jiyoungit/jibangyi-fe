import axios from 'axios';

const { VITE_OPEN_API_STORE_URL } = import.meta.env;

// local vue api axios instance
function makeStoreAxios() {
  const instance = axios.create({
    baseURL: VITE_OPEN_API_STORE_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
    },
  });
  return instance;
}
const storeAxiosInstance = makeStoreAxios();

const PREFIX_STORE_ZONE = '/storeZoneInRadius';
const PREFIX_STORE_LIST = '/storeListInArea';

function findStoreZoneInRadius(param, success, fail) {
  /*
  req:
    {
    
    }
  res:
    {
    
    }
  */
  storeAxiosInstance
    .get(`${PREFIX_STORE_ZONE}`, { params: { param } })
    .then(success)
    .catch(fail);
}

function findStoreListByStoreCode(param, success, fail) {
  /*
  req:
    {
    
    }
  res:
    {
    
    }
  */
  storeAxiosInstance
    .get(`${PREFIX_STORE_LIST}`, { params: { param } })
    .then(success)
    .catch(fail);
}
