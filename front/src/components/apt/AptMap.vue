<script setup>
import { ref, watch, onMounted } from "vue";
import { listAptInfosByCoold } from "@/api/apt";
import { useDealStore } from '@/stores/deal';
import { storeToRefs } from 'pinia';

var map;
const overlays = ref([]);
const clusterer = ref();

const dealStore = useDealStore();
const { dongCoord } = storeToRefs(dealStore);

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

  traceCenterCoolds();
};

const aptClicked = (aptCode) => {
  console.log(aptCode);
  dealStore.getAptInfo(aptCode);
  dealStore.aptDetail(aptCode);
}

const deleteOverlays = () => {
  if (overlays.value.length > 0) {
    overlays.value.forEach((overlay) => overlay.setMap(null));
  }
};

watch(
  () => dongCoord.value,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(dongCoord.value.clat, dongCoord.value.clng);

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);
  },
  { deep: true }
);

const loadMarkers = (data) => {
  deleteOverlays();

  overlays.value = [];
  data.forEach((position, index) => {

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

    overlays.value = [...overlays.value, customOverlay];
  })

  // clusterer.value = new kakao.maps.MarkerClusterer({
  //   map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
  //   averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
  //   minLevel: 10 // 클러스터 할 최소 지도 레벨 
  // });

  // // 데이터에서 좌표 값을 가지고 커스텀 오버레이를 표시합니다
  // // 마커 클러스터러로 관리할 커스텀 오버레이 객체는 생성할 때 지도 객체를 설정하지 않습니다
  // overlays.value = data.map((i, position) => {
  //   return new kakao.maps.CustomOverlay({
  //     position: new kakao.maps.LatLng(position.lat, position.lng),
  //     content: content,
  //   });
  // });

  // console.log(overlays.value);

  // // 클러스터러에 커스텀 오버래이들을 추가합니다
  // clusterer.value.addMarkers(overlays.value);
};

function traceCenterCoolds() {
  // 마우스 드래그로 지도 이동이 완료되었을 때 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
  kakao.maps.event.addListener(map, 'dragend', function () {
    // 지도 중심좌표를 얻어옵니다 
    const latlng = map.getCenter();

    listAptInfosByCoold(
      { lat: latlng.getLat(), lng: latlng.getLng(), range: 5, limit: 2000 },
      ({ data }) => {
        loadMarkers(data);
      },
      (error) => {
        console.log(error);
      }
    )
  });
}
</script>

<template>
  <div id="map"></div>
</template>

<style lang="scss">
@import '@/assets/scss/apt/map.scss';
</style>
