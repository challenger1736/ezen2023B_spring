console.log('member.js')

// 1. 회원가입
function signup(){
    console.log("signup() 함수 실행")
    // 1. HTML 입력값 호출 [ document.querySelector() ]
//    let id = document.querySelector('#id').value ; console.log(id)
//    let pw = document.querySelector('#pw').value; console.log(pw)
//    let name = document.querySelector('#name').value; console.log(name)
//    let phone = document.querySelector('#phone').value;   console.log(phone)
//    let email = document.querySelector('#email').value;   console.log(email)
//    let img = document.querySelector('#img').value ;   console.log(img)
    // -- 유효성 검사 할 거면 하고,
    // 2. 객체화 [ let info = {} ]
//    let info = {
//        id : id, pw: pw, name : name, phone : phone, email : email, img : img
//    }; console.log(info);

// div 객체 하나하나로 보내지말고 form으로 바꿔서 전송 받아보기 !! 2/27 배움
        // 2. 폼 가져오기
    let signUpForm = document.querySelector('.signUpForm');
        console.log( signUpForm ); //
    let signUpFormData = new FormData( signUpForm );
        console.log( signUpFormData );  // new FormData : 문자데이터가 아닌 바이트 데이터로 변환. ( 첨부파일 필수 )

    // 3. 원래 배열에 저장--> Spring Controller 서버와 통신[ JQUERY AJAX ]
//    $.ajax({
//            url : 'spring controller mapping 주소'
//            method : 'mapping 방법'
//            data : : '보낼 데이터'
//            success : function result( '받을 데이터' ){}
//        })
//    $.ajax({ // ajax 쓸려면 html에 jQuery 포함 되어야함.
//            url : '/member/signup',
//            method : 'POST',
//            data : info,
//            // contentType : application/json 이런식으로 변환도 가능함. 지금은 폼과 동일하게 전송되는 일.(기본값이 form)
//            success : function ( result ){ //함수명은 생략가능. Responsebody의 return값이 success의 result 로 들어온다.
//                console.log(result);
//                // 4. 결과
//                if(result){
//                    alert('회원가입 성공');
//                    location.href = '/member/login';
//                }else{
//                    alert('회원가입 실패');
//                }
//            }
//        });

    $.ajax({
            url : '/member/signup',         // controller 매핑 주소
            method : 'POST',                // controller 매핑 방법
//            data :  info ,                  // controller 요청 보낼 매개변수
            data : signUpFormData ,
            contentType  : false ,  // 기본이 form 전송인데, false를 하면 multipart로 대용량으로 보낼 수 있다.
            processData  : false ,  // 문자형식이 아닌 바이트 형식으로 보내는 방법( 첨부파일)
            success : (r) => {  // controller 응답 받은 매개변수
                console.log( r);
                // 4. 결과
                if( r ) {
                    alert('회원가입 성공');
                    location.href = '/member/login';
                }else{
                    alert('회원가입 실패');
                }
            }
        });

    // 4. 결과

}





//2. 로그인
function login(){
    // 1. HTML 입력값 호출 [ document.querySelector() ]
    let id = document.querySelector("#id").value; console.log(id);
    let pw = document.querySelector("#pw").value; console.log(pw);
    // -- 유효성 검사 할 거면 하고,
    // 2. 객체화 [ let info = {} ]
    let info = {
           id : id, pw: pw
       }; console.log(info);
    // 3. 서버와 통신
    $.ajax({
                url : '/member/login',
                method : 'POST',
                data : info,
                success : function ( result ){ //함수명은 생략가능.
                    console.log(result);
                    // 4. 결과
                    if(result){
                        alert('로그인 성공');
                        // JS 페이지 전환
                        location.href = "/"; // 로그인 성공시 메인페이지
                    }else{
                        alert('로그인 실패');
                    }
                }
            });
    // 4. 결과

}

function onpreimg(e){
    console.log('preimg');
    console.log(e); // 해당 함수를 실행한 input
    console.log(e.files); //  현재 인풋의 파일들.
    console.log(e.files[0]); //  현재 인풋의 파일들.중에 첫번째,
    // - input에 업로드 된 파일을 바이트로 가져오기
    // 1. 파일 읽기 객체 생성
    let fileReader = new FileReader();
    // 2. 파일 읽기 메소드
    fileReader.readAsDataURL(e.files[0]);
    console.log(fileReader);
    console.log(fileReader.result); // 확인해보면 null 이러면 안나옴 onload로 불러와야함.
    // 3. 파일Reader에 on load 정의
    fileReader.onload = event =>{
        console.log(event);     // ProgressEvent
        console.log(event.target);
        console.log(event.target.result); // 여기에 읽어온 첨부파일의 바이트가 있음.
        document.querySelector('#preimg').src = event.target.result;
    }
}
/*
    함수 정의 방법
        1. function 함수명(매개변수){}
        2. function (매개변수){} // 익명함수
        3. (매개변수) => {} // 람다, 화살표 함수
*/