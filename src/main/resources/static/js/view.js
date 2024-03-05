// * 경로(URL)상의 쿼리스트링(매개변수) 호출하기. js 에서!
    // 1. new URL(location.href) : 현재 페이지의 경로 호출
    console.log(new URL(location.href));
    // 2. 경로상의( 쿼리스트링 ) 매개변수들
    console.log(new URL(location.href).searchParams);
    // 3. (쿼리스트링) 매개변수들 에서 특정 매개변수 호출
    console.log(new URL(location.href).searchParams.get('bno'));
let bno = new URL(location.href).searchParams.get('bno')

// 1. 게시물 개별 조회
onView();
function onView(){
    console.log("onView()");

    $.ajax({
        url : "/board/view.do", // 쿼리스트링 방식
        method : "get",
        data : {"bno": bno }, // 이것도 쿼리스트링 방식으로 침.
        success : (r) => {
            console.log(r);

            document.querySelector('.btitle').innerHTML = r.btitle;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bcontent').innerHTML = r.bcontent;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bcno').innerHTML = r.bcno;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.mno').innerHTML = r.mno;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bdate').innerHTML = r.bdate;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bview').innerHTML = r.bview;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bfile').innerHTML = r.bfile;// div 이기 떄문에, 그 사이! innerHTML


        }
    })
}