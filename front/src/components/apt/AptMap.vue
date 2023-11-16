<script setup>
import { ref, watch, onMounted } from "vue";
import { listAptInfosByCoold } from "@/api/apt";
import { useDealStore } from '@/stores/deal';
import { storeToRefs } from 'pinia';

var map;
const markers = ref([]);

const dealStore = useDealStore();
const { allDeals, dongCoord } = storeToRefs(dealStore);

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

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
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

// watch(
//   () => props.apts.value,
//   () => {
//     positions.value = [];
//     props.apts.forEach((station) => {
//       let obj = {};
//       obj.latlng = new kakao.maps.LatLng(station.lat, station.lng);
//       obj.title = station.statNm;

//       positions.value.push(obj);
//     });
//     loadMarkers(positions.value);
//   },
//   { deep: true }
// );

const loadMarkers = (data) => { // data: positions.value, listAptInfosByCoord -> data
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  deleteMarkers();

  // 마커 이미지를 생성합니다
  //   const imgSrc = require("@/assets/map/markerStar.png");
  // 마커 이미지의 이미지 크기 입니다
  //   const imgSize = new kakao.maps.Size(24, 35);
  //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

  // 마커를 생성합니다
  markers.value = [];
  data.forEach((position) => {
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      // position: position.latlng, // 마커를 표시할 위치
      position: new kakao.maps.LatLng(position.lat, position.lng),
      title: position.apartmentName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      // image: markerImage, // 마커의 이미지
    });
    markers.value.push(marker);
  });

  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  // const bounds = positions.value.reduce(
  //   (bounds, position) => bounds.extend(position.latlng),
  //   new kakao.maps.LatLngBounds()
  // );

  // map.setBounds(bounds);
};

function traceCenterCoolds() {
  // 마우스 드래그로 지도 이동이 완료되었을 때 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
  kakao.maps.event.addListener(map, 'dragend', function () {
    // 지도 중심좌표를 얻어옵니다 
    const latlng = map.getCenter();

    // let message = '변경된 지도 중심좌표는 ' + latlng.getLat() + ' 이고, ';
    // message += '경도는 ' + latlng.getLng() + ' 입니다';
    // console.log(message);

    listAptInfosByCoold(
      { lat: latlng.getLat(), lng: latlng.getLng(), range: 5, limit: 200 },
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

<style>
#map {
  width: 100vw;
  height: 100vh;
  z-index: 0;
}
</style>
