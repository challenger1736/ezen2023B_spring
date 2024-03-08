

    // document (문서)HTML가 모두 렌더링 되었을 때 실행
    // $( JQUERY 문법 )
    // JS : document.querySelector('#summernote') VS JQUERY : $('#summernote') 같은 것임.
//$(document).ready(function() {
//
//
//
//});


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

            document.querySelector('.btitle').value = r.btitle;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bcontent').innerHTML = r.bcontent;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bcno').value = r.bcno;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bfile').innerHTML = r.bfile;// div 이기 떄문에, 그 사이! innerHTML


            // 썸머노트 옵션 추가
            let option = {
                lang : 'ko-KR', // 한글 패치
                height : 500  // 썸머노트 에디터 세로 크기
              };
              $('#summernote').summernote( option ); // 썸머노트 실행



        }
    })
}

    // 2. 게시물 수정
function onUpdate(){
    // 1. 폼 가져온다.
    let boardUpdateForm = document.querySelector('.boardUpdateForm')
    // 2. 폼 객체화 ( 첨부파일 바이트화 = new FormData(Form객체))
    let boardUpdataFormData = new FormData(boardUpdateForm);


        // + 폼 객체에 데이터 추가. [ HTML 입력 폼 외 데이터 삽입 가능 ]
        // 폼데이터객체명.set(속성명(name), 데이터(value));
        boardUpdataFormData.set('bno', bno);

    // 멀티파트 폼 전송
    $.ajax({
        url: "/board/update.do",
        method: "put",
        data : boardUpdataFormData,
        contentType : false,
        processData : false,
        success : (r) => {
            if(r){alert('수정성공'); location.href = "/board/view?bno="+bno;}
            else{alert('수정실패')}
        }
    })
}

