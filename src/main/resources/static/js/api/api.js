console.log('api.js');
// 3. 카카오 지도를 사용하는 API


//var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
//var options = { //지도를 생성할 때 필요한 기본 옵션
//	center: new kakao.maps.LatLng(37.3218778 , 126.8308848), //지도의 중심좌표.
//	level: 3 //지도의 레벨(확대, 축소 정도)
//}; // 클러스터러(마커 합치기) 하기 전 코드.
//var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴


 var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div (id가 map인 놈)
        center : new kakao.maps.LatLng(37.3218778, 126.8308848), // 지도의 중심좌표
        // 안산 좌표 126.8308848, 37.3218778
        level : 6 // 지도의 확대 레벨
    });

    // 마커 클러스터러를 생성합니다 (마커가 여러개 일때 합쳐지는 효과) 를 생성합니다.
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 8 // 클러스터 할 최소 지도 레벨
    });

    $.ajax({
        url : "https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=100&serviceKey=psc3IR%2Bk%2FlcGljcI8vw8mJLos6KAZ%2FCVYiYuKFDMAttZkvOX3iuyKknhxH0AoR%2BmsoB57EWOGcXVgsimV8F2KQ%3D%3D",
        method : "get",
        success : (response) => { // 이 response 어디서 실행되서 어디서 나오는거지? 아 저 api주소에서 get해서 값을 가져오는 것이네.
        var markers = response.data.map((object) => {
        // 마커 1개 만들어서 리턴해서 markers에 대입.
            return new kakao.maps.Marker({
            position : new kakao.maps.LatLng(object.식당위도, object.식당경도)
        })
        })
        // 클러스터러에 마커*들을 추가합니다
        clusterer.addMarkers(markers);
        }
    })
    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다

    // 다르게 아작스 쓰는 법

    /*
        $.ajax({})
            간소화
        $.HTTP메소드(url , success, )
        $.get(url , success, )
        $.post(url , success, )
        $.put(url , success, )
        $.delete(url , success, )
    */


//    $.get("https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=100&serviceKey=psc3IR%2Bk%2FlcGljcI8vw8mJLos6KAZ%2FCVYiYuKFDMAttZkvOX3iuyKknhxH0AoR%2BmsoB57EWOGcXVgsimV8F2KQ%3D%3D",
//        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
//        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
//        function(r){
//        var markers = r.data.map((object) => {
//            return new kakao.maps.Marker({
//                position : new kakao.maps.LatLng(object.식당위도, object.식당경도)
//            })
//            }
//        };
//
//        // 클러스터러에 마커들을 추가합니다
//        clusterer.addMarkers(markers);
//    );




// 2. 안산시 원곡동 일반음식점 현황
$.ajax({
    url: "https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=100&serviceKey=psc3IR%2Bk%2FlcGljcI8vw8mJLos6KAZ%2FCVYiYuKFDMAttZkvOX3iuyKknhxH0AoR%2BmsoB57EWOGcXVgsimV8F2KQ%3D%3D",
    method : "get",
    success : (r) => {
        console.log(r);
        let apiTable2 = document.querySelector('.apiTable2');
        let html = ``;
            r.data.forEach((object)=>{
                html += `<tr>
                             <th>${object.사업장명}</th>
                             <th>${object.도로명전체주소}</th>
                             <th>${object.대표메뉴1}</th>
                             <th>${object.메뉴가격1.toLocaleString()}</th>
                             <th>${object['대표전화']}</th>
                             <th>${object['주차 가능']}</th>
                         </tr>
                `           // 객체명. 속성명 === 객체명['속성명']
            })
        apiTable2.innerHTML = html;
    }
})

// 1. 안산시 강우량 공공데이터
$.ajax({
    url : "https://api.odcloud.kr/api/15111852/v1/uddi:71ee8321-fea5-4818-ade4-9425e0439096?page=1&perPage=100&serviceKey=psc3IR%2Bk%2FlcGljcI8vw8mJLos6KAZ%2FCVYiYuKFDMAttZkvOX3iuyKknhxH0AoR%2BmsoB57EWOGcXVgsimV8F2KQ%3D%3D",
    method : "get",
    success : (r)=> {
        console.log(r);
        let apiTable1 = document.querySelector(".apiTable1");
        let html = ``;
            r.data.forEach((object)=>{
                html += `   <tr>
                                             <th>${object.관리기관명}</th>
                                             <th>${object.날짜}날짜</th>
                                             <th>${object.시도명} ${object.시군구명} ${object.읍면동}</th>
                                             <th>${object['우량(mm)']}</th>
                                         </tr>`
                                         // 객체명. 속성명 === 객체명['속성명']
            })

        apiTable1.innerHTML = html;
    }
})

