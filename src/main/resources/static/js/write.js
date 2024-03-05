// 썸머노트 실행
$(document).ready(function() {
    // 썸머노트 옵션 추가
  let option = {
    lang : 'ko-KR', // 한글 패치
    height : 500  // 썸머노트 에디터 세로 크기
  }




  $('#summernote').summernote( option );

});



// 1. 글쓰기



function onWrite(){
    console.log("onWrite()");
    // 1. 폼 DOM 가져온다.
    let boardWriteForm = document.querySelector('.boardWriteForm');
    // 2. 폼 바이트(바이너리) 객체 변환 [ 첨부 파일 보낼때는 필수 ]
    let boardWriteFormData = new FormData(boardWriteForm);
    // 3. ajax 첨부파일 폼 전송
    $.ajax({
        url: "/board/write.do",
        method : "POST",
        data : boardWriteFormData,
        contentType : false,
//        contentType : text, 등등..
        processData : false,
//        이게 멀티파트 파일이라고 위의 두 문장으로 선언해주는 것.
        success : (r) => {
            console.log(r);
            if(r == 0){
            alert('글쓰기 실패: 관리자에게 문의 DB오류')

            }else if(r == -1){
            alert('글쓰기 실패: 관리자에게 문의(첨부파일 오류)')

            }else if(r == -2){
            alert('글쓰기 실패: 로그인 세션이 존재하지 않습니다.(잘못된 접근)');
            location.href = "/member/login";
            }else if(r>= 1){
            alert('글쓰기 성공')
            location.href = `/board/view?bno=${r}`;
            }


        }
    })
}