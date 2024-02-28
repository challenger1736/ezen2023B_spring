console.log('member.js')

/*

    onclick : 클릭 할 때마다
    onchange : 값이 변경될 때 마다
    onkeyup : 키보드 키를 떼었을 때

    ---- 정규 표현식 ----
    정규표현식이란 : 특별한 규칙을 가진 문자열의 집합을 표현할 때 사용하는 형식 언어 // !!규칙!!
        - 주로 문자열 데이터 검사할 때 사용 - 유효성 검사.
        - 메소드
            정규표현식.test(검사할 대상)
        - 형식 규칙
         /^ : 시작, $/ : 끝
         {최소길이,최대길이 } : 문자 길이 규칙
         [허용할 문자]  : 허용 문자 규칙
            [a-z]       : 소문자 a~z 허용
            [a-zA-z]    : 대소문자 a~z 허용
            [a-zA-z0-9] : 대소문자 a~z 허용, 숫자허용
            [a-zA-z0-9가-힣] : 대소문자 a~z 허용, 숫자허용, 한글허용
*/
// ******* 현재 유효성 검사 체크 현황
let checkArray = [false,false,false,false,false]; // 아이디, 비밀번호, 이름 , 전화번호 , 이메일



//4. 아이디 유효성 검사 (아이디 입력할 때 마다.) onkeyup
function idcheck(){
    console.log('idcheck() 함수 실행');

    // 1. 입력된 데이터 가져오기
    let id = document.querySelector('#id').value;
    console.log(id);

    // 2. 정규표현식 /^ : 시작, $/ : 끝 // 영소문자+숫자 조합의 5~30 글자 사이 규칙
    let 아이디규칙 = /^[a-z0-9]{5,30}$/

    // 3. 정규표현식에 따른 검사
    console.log(아이디규칙.test(id));

    // 간단한 유효성 검사 결과 출력
    if(아이디규칙.test(id)){
        // 아이디 중복 체크 (DB 찍기,ajax)
        $.ajax({ // 비동기 vs 동기
            // type: "",
            url: "/member/find/idcheck", 
            method: "get", //어떤 변화를 주는게 아니다보니까 get // get은 폼형식으로 보내면 http body 없다. 쿼리 스트링으로 간다.
            data: {'id':id}, // "/member/find/idcheck?id=${id}" 이랑 같다 url이
            success: (r)=>{
                // let result = false; // DB 찍고 왔다 치고,
                if(r){ // true 중복있다. false 중복없다
                    document.querySelector('.idcheckbox').innerHTML = `사용중인 아이디`;
                    checkArray[0]=false;
                }else{
                    document.querySelector('.idcheckbox').innerHTML = `PASS`;
                    checkArray[0]=true;
                }
            }
        });
       
        
    }else{
        document.querySelector('.idcheckbox').innerHTML = `영소문자+숫자 조합의 5~30 글자 사이로 입력해주세요`;
        checkArray[0]=false;

    }
    
   
}


// 1. 회원가입
function signup(){

    // * 유효성 검사 체크 현황중에 하나라도 false이면 회원가입 금지
    for(let i =0; i<checkArray.length;i++){
        if(checkArray[i]){  // false 가 있으면 나가버렸!
            alert('입력사항들을 모두 정확히 입력해주세요.')
            return;
        }
    }
    // 위의 for문 이런 방식으로도 찾을수 있음
    // checkArray.indexOf 
    // checkArray.includes()

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