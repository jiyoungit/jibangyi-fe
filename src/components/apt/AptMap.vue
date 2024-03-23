<script setup>
import { ref, watch, onMounted } from "vue";
import { listAptInfosByCoold } from "@/api/apt";
import { useDealStore } from '@/stores/deal';
import { storeToRefs } from 'pinia';
import { findMetroListInRadius } from '@/api/ext/metro';

var map;
var overlays = [];
var clusterer = {};

const dealStore = useDealStore();
const { getDongInfo } = storeToRefs(dealStore);

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
      }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(37.501477, 127.039724),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);

  window.map = map;

  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

  // 마커 클러스터러를 생성합니다 
  clusterer = new kakao.maps.MarkerClusterer({
    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
    minLevel: 5 // 클러스터 할 최소 지도 레벨 
  });

  kakao.maps.event.addListener(map, 'dragend', () => {
    traceCenterCoolds();
  });

  kakao.maps.event.addListener(map, 'zoom_changed', () => {
    traceCenterCoolds();
    // 지도의 현재 레벨을 얻어옵니다
    const level = map.getLevel();

    const message = '현재 지도 레벨은 ' + level + ' 입니다';

    console.log(message);
  });

  traceCenterCoolds();
};

const aptClicked = async (aptCode) => {
  await dealStore.getAptInfo(aptCode);
  await dealStore.aptDetail(1);
};

const deleteOverlays = () => {
  if (overlays.length > 0) {
    overlays.forEach((overlay) => overlay.setMap(null));
    clusterer.clear();
  }
  overlays = [];
};

watch(
  () => getDongInfo.value,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(getDongInfo.value.clat, getDongInfo.value.clng);

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);
    traceCenterCoolds();
  },
  { deep: true }
);

const loadAptMarkers = (data) => {

  const setMarker = (position, index) => {
    const content = document.createElement('div');
    content.innerHTML = // 평수, 시세, 평당가격
      '<div id="overlaybox' + index + '" class="overlaybox">' +
      '  <div class="boxtitle">' +
      '    <div>' + Math.round(position.avgArea / 3.3) + '평</div>' +
      '  </div>' +
      '  <div class="boxcontent">' +
      '    <div class="price">' + Math.round(position.avgDealAmount / 1000) / 10 + '억</div>' +
      '    <div class="pricePerFeet">' + Math.floor(position.avgDealAmount / position.avgArea) + '만/평</div>' +
      '  </div>' +
      '</div>';

    content.addEventListener('click', () => aptClicked(position.aptCode));

    const customOverlay = new kakao.maps.CustomOverlay({
      map: map,
      position: new kakao.maps.LatLng(position.lat, position.lng),
      content: content,
    });

    overlays.push(customOverlay);
  };

  const promises = data.map(setMarker);
  Promise.all(promises)
    .then(() => {
      try {
        clusterer.addMarkers(overlays);
        clusterer.redraw();
      } catch (error) {
        console.error('에러!!!!!!!!!!!!!!!!!!!!!!!!!!!', error);

      }
    })
    .catch((error) => {
      console.error(error);
    });

};

const loadMetroMarkers = (data) => {

  const { result } = data;
  const { station: stations } = result;

  const cntTotal = result.count;
  let cntMetro = 0;
  let cntBusStation = 0;

  console.log('주변 교통시설 개수:', cntTotal);

  const setMarker = (station, index) => {

    let className = "";
    if (station.stationClass == 2) {
      className = "metro";
      ++cntMetro;
    }
    else if (station.stationClass == 1) {
      className = "bus";
      ++cntBusStation;
      return;
    }

    // 마커에 표시할 인포윈도우를 생성합니다 
    const content = document.createElement('div');
    content.innerHTML =
      '<div class="' + className + '">' +
      '  <div class="hover-message">' + station.stationName + '</div>' +
      '</div>';
    const marker = new kakao.maps.CustomOverlay({
      map: map,
      position: new kakao.maps.LatLng(station.y, station.x),
      content: content,
    });

    const ele = content.querySelector('.element');

    addEventHandle(content, 'mouseenter', () => { console.log('enter:', station.stationName); });
    addEventHandle(content, 'mouseleave', () => { console.log('leave:', station.stationName); });

    overlays.push(marker);

    // target node에 이벤트 핸들러를 등록하는 함수힙니다  
    function addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent('on' + type, callback);
      }
    }
  };

  const promises = stations.map(setMarker);
  Promise.all(promises)
    .then(() => { })
    .catch((error) => {
      console.error(error);
    });
};

const traceCenterCoolds = () => {
  deleteOverlays();

  const latlng = map.getCenter();

  listAptInfosByCoold(
    { lat: latlng.getLat(), lng: latlng.getLng(), range: 2, limit: 1000 },
    ({ data }) => {
      loadAptMarkers(data);
    },
    (error) => {
      console.error(error);
    }
  );

  // findMetroListInRadius(
  //   { y: latlng.getLat(), x: latlng.getLng(), radius: "1500", stationClass: "1:2" },
  //   ({ data }) => {
  //     loadMetroMarkers(data);
  //   },
  //   (error) => {
  //     console.error(error);
  //   });
}

</script>



<template>
  <div id="map"></div>
</template>

<style lang="scss">
@import '@/assets/scss/apt/map.scss';
</style>
