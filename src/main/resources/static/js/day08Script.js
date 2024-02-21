console.log('day08Board')

// 1. 저장 메소드 : 실행 조건 : 등록 버튼 클릭 시 / 매개변수x, 리턴x
 function doCreate(){
 // 0. 함수 켜지는지
  console.log('doCreate() 실행')
  // 1. 입력받은 데이터 가져오기
  let bcontent = document.querySelector("#bcontent").value;
  console.log(bcontent);
  let bwriter = document.querySelector("#bwriter").value;
  console.log(bwriter);
  let bpw = document.querySelector("#bpw").value;
  console.log(bpw);

  // * 유효성 검사 // 생략
  // 2. 객체화
  let info = {bcontent: bcontent , bwriter : bwriter, bpw : bpw};
  console.log(info);
  // 3. controller 전송 후 응답

    // ------------ AJAX -------------- //
            // 1. jquery 라이브러리 호출 해야함 : 안하면 $ is not defined 이라고 함.
            // $를 사용하는 문법 라이브러리가 jquery!
    //        $.ajax({
    //           url : '서버 매핑 주소 ',
    //           method : '서버 매핑 방법',
    //           data :  {서버 요청 보낼 매개변수}  ,
    //           success : function ( 서버 응답 받은 매개변수 ){ }
    //        })
       $.ajax({
               url : '/board/create',
               method : 'POST',
               data : info ,
               success : function (result){
                 // 4. 결과
                console.log(result); // 보니까 T/F임.
                if(result){alert('글쓰기 성공'); doRead();} // 안내후 게시물 목록 새로고침
                else{alert('글쓰기 실패');}
                }
            })
    // -------------------------------- //

 }

doRead(); // JS 열릴때 최초 실행
// 2. 전체 호출 메소드 : 실행 조건 : 페이지 열릴 때, 변화(저장, 수정, 삭제)가 있을때(새로고침) / 매개변수 x  , 리턴 x
 function doRead(){


  console.log('doRead() 실행')
    // ------------ AJAX -------------- //
    //        $.ajax({
    //           url : '서버 매핑 주소 ',
    //           method : '서버 매핑 방법',
    //           data :  {서버 요청 보낼 매개변수}  ,
    //           success : function ( 서버 응답 받은 매개변수 ){ } // Ajax가 자동 변환도 해줌
    //        })
       $.ajax({
               url : '/board/read',
               method : 'GET',
//               data :  {}  ,
               success : function (result){
               console.log(result); // 응답 뭘로 받는지 보는 것.

                   // 1. 어디에
                   let tbody = document.querySelector('table tbody');
                   // 2. 무엇을
                   let html = "";
                   for(let i = 0; i<result.length; i++){
                   console.log(result[i]);
                   // 백틱 ` : 키보드 tab 키 위에 있는 키
                   //       `` 백틱 문자열 사이에 ${JS코드} 대입하는 템플릿
                    html += `   <tr>
                                         <th>${result[i].bno}</th>
                                         <th>${result[i].bcontent}</th>
                                         <th>${result[i].bwriter}</th>
                                         <th><button onclick="doDelete(${result[i].bno})">삭제</button>
                                             <button onclick="doUpdate(${result[i].bno})">수정</button></th>
                                </tr>
                    `;
                   }
                   // 3. 출력
                   tbody.innerHTML = html;

               }
            })

    // -------------------------------- //
 }

// 3. 수정 메소드 : 실행 조건 : 등록 버튼 클릭 시 / 매개 변수 : 수정할 식별키 bno , 리턴 x
 function doUpdate(bno){
  console.log('doUpdate() 실행'+bno);
  // 1. 수정할 내용 가져온다. 오늘은 간단하게 prompt에서 예시로 받겠다.
  let bcontent = prompt('수정 할 내용 :');
  let bpw = prompt('게시물 비밀번호 :');

  // ------------ AJAX -------------- //
   $.ajax({
                 url : '/board/update',
                 method : 'POST',
                 data :  {bno: bno , bcontent : bcontent, bpw : bpw}  ,
                 success : function (result){
                 console.log(result); // 응답 뭘로 받는지 보는 것.
                 if(result){
                 alert('글 수정 성공'); doRead();}
                 else{alert('글 수정 실패');}
                 }
              })
  // -------------------------------- //
 }

// 4. 삭제 메소드 : 실행 조건 : 등록 버튼 클릭 시 / 매개 변수 : 삭제할 식별키 bno , 리턴 x
 function doDelete(bno){
  console.log('doDelete() 실행'+bno)
  let bpw = prompt('게시물 비밀번호 :'); // 검증할 방금 입력한 비밀번호
  // 2. 객체화
  let info = {bpw:bpw}
    // ------------ AJAX -------------- //
      $.ajax({
                     url : `/board/delete/${bno}/${bpw}`,
                     method : 'GET',
//                     data :  {}  ,
                     success : function (result){
                     console.log(result); // 응답 뭘로 받는지 보는 것.
                     if(result){alert('삭제 성공'); doRead();}
                     else{alert('삭제 실패')}
                     }
                  })
    // -------------------------------- //
 }