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
         { 최소길이, 최대길이 } : 문자 길이 규칙
         [허용할 문자]  : 허용 문자 규칙
            [a-z]       : 소문자 a~z 허용
            [A-Z]       : 대문자 A~Z 허용
            [!@#$]      : 특정 특수문자 허용
            [a-zA-Z]    : 대소문자 a~z 허용
            [a-zA-Z0-9] : 대소문자 a~z 허용, 숫자허용
            [a-zA-Z0-9가-힣] : 대소문자 a~z 허용, 숫자허용, 한글허용
            [ac]            : a또는 c 허용
            (?=.*[1개이상 문자 패턴])[]{개수}
            +               : 얘 앞에 있는 패턴 1개이상 반복// = 뜻 : 앞의 문구는 한 개이상 넣어라.
            ?               : 얘 앞에 있는 패턴 0개 혹은 1개 이상 반복
            *               : 얘 앞에 있는 패턴 0개 반복
            .               : 1개의 문자를 뜻함 // 이래서 이런애들을 쓰려면 \ 백슬래시를 써야한다.
            예) 1개 이상 필수
                (?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,30}

            예) 000-0000-0000 또는 00-000-0000
            let 폰규칙 = /^ ([0-9]{2,3})+[-]+([0,9]{3,4})+[-]+[0-9]{4}$/ [-]?면 들어가도되고 안들어가도되고

            예) 문자열@문자.문자
            qwe@naver.com
            asdas@kakao.net
                /^[a-zA-Z0-9]+@+[a-zA-Z0-9]+\.[a-zA-Z]+$/

            ()              : 패턴의 그룹
            ?=.             : 문자열 패턴 안에 어딘가에 존재하면 가능,
*/
// ******* 현재 유효성 검사 체크 현황

// 1. 인증 구역
let authbox = document.querySelector('.authbox');

let authreqbtn = document.querySelector('.authreqbtn'); // 인증요청 버튼 이렇게 전역으로 변수를 저장
// 해놨기에 저 클래스 버튼이 authreqbtn.disabled = true ; or false; 이렇게 해도 먹힘.

let checkArray = [false,false,false,false,false]; // 아이디, 비밀번호, 이름 , 전화번호 , 이메일
//8. 이메일 유효성 검사, 문자열@문자.문자
function emailcheck(){
    let email = document.querySelector('#email').value;
    let 이메일규칙 =  /^[a-zA-Z0-9]+@+[a-zA-Z0-9_-]+\.[a-zA-Z]+$/
    let msg = '아이디@도메인 입력해주세요.'
    checkArray[4] = false; // 이메일 인증까지 해야 true로 바뀌게
    authreqbtn.disabled = true;
    if(이메일규칙.test(email)){
        authreqbtn.disabled = false; // 유효성 검사 맞으면 disable 안되게
        msg = '인증요청 가능';
    }
    document.querySelector('.emailcheckbox').innerHTML = msg;
}


//7. 전화번호 유효성 검사 000-0000-0000 또는 00-000-0000
function phonecheck(){
    let phone = document.querySelector('#phone').value;
    let 전화번호규칙 = /^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]+([0-9]{4})$/ // [-]?면 들어가도되고 안들어가도되고
    let msg = '000-0000-0000 또는 00-000-0000';
    checkArray[3] = false;
    if(전화번호규칙.test(phone)){
        msg = '통과';
        checkArray[3] = true;
    }
    document.querySelector('.phonecheckbox').innerHTML = msg;

}


//6. 이름 유효성 검사 (입력 시 마다)
function namecheck(){
    let name = document.querySelector('#name').value; // 1. 입력값 가져온다
    let 이름규칙 = /^[가-힣]{5,20}$/            // 2. 정규표현식을 작성한다.
    let msg = ''; checkArray[2] = false;
    if(이름규칙.test(name)){                    // 3. 정규표현식을 검사한다.
        msg ='통과';                            // 4. 정규 표션식 검사가 일치했을 때,
        checkArray[2] = true;
    }else{
        msg = '한글 5~20 글자'
    }
    document.querySelector('.namecheckbox').innerHTML = msg;

}



//5. pwconfirm 유효성 검사 (입력 시 마다)
function pwcheck(){
    console.log('pwcheck() 실행')
    // 1. 입력값 가져온다
    let pw = document.querySelector('#pw').value;
    let pwconfirm = document.querySelector('#pwconfirm').value;

    // 2. 유효성 검사
    let msg = "통과";
        // 1. 비밀번호 에 대한 정규 표현식
    let 비밀번호규칙 = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,30}$/ // 영대소문자 1개 필수와 숫자 1개 필수의 조합 <필수포함해서 5~30글자
        // 2.
        if(비밀번호규칙.test(pw)){ //비밀번호 검사
            msg = "표현식 일치" // 있으나 마나
            // 3.
                // 4.
                if(pw==pwconfirm){ 
                    msg="통과";
                    checkArray[1]=true;
                }else{
                    msg="패스워드 불일치"
                    checkArray[1]=false;
                }
           
        }else{
            msg = "영대소문자 1개 필수와 숫자 1개 필수의 조합 5~30글자로 넣어주세요";
            checkArray[1]=false;
        }

    //
    document.querySelector('.pwcheckbox').innerHTML = msg;

}
let timer = 180; // 인증 시간

// 9. 인증요청
function authreq( object ){


    // 2. 인증 구역 구성(html에서 실험)
    let html = `<span class="timebox"> 00분00초 </span>
                            <input type="text" class="ecodeinput"/>
                            <button onclick="auth()" type="button">인증</button>`;
    // 3. 인증 구역 출력
    authbox.innerHTML = html;

    // ======== 자바에게 인증 요청 ===============
    $.ajax({
        url : "/auth/email/req", // 쿼리스트링으로 가겠구만
        method : "get",
        data : {"email": document.querySelector('#email').value},
        success : (r) => {
            if(r){
                // 4. 타이머 함수 실행
                      timer = 180;
                      ontimer(); // 타이머 함수 실행
                      // 해당 버튼 사용 금지
                  //    console.log(object) //html에서 전달한 js의 this로 object 확인 (this로 >> js에서 object 매개변수를 쓸 때)
                      authreqbtn.disabled = true;
            }else{
                alert('관리자에게 문의')
            }
        }
    })

    // ==============

}

let timerInter = null; // ontimer 안에 있다가 전역으로 빼줌. 딴데서도 쓰려고,
// 10. 타이머 함수
function ontimer(){
// 테스트
    // 정의 : setInterval(함수, 밀리초) : 특정 밀리초마다 함수 실행.
    // 종료 : clearInterval(종료할Interval변수) : 종료할 Interval의 변수 대입.
//let time = 0;
    timerInter = setInterval(()=>{
    // setInterval() 함수는 JavaScript에서 주어진 시간 간격마다
    // 함수를 반복적으로 실행하는 역할을 합니다.
    // 예시 >  setInterval(function() { console.log('반복 실행되는 함수');}, 1000)
    // 멈추기 = clearInterval(setInterval변수명)

    // 1. 현재 날짜/시간
    //    let today = new Date();
    // 2. 타이머 리미트

    // 2-1 .이 타이머를 분 초로 나누기,
    let m = parseInt(timer/60); //분
    let s = parseInt(timer%60); //분을 제외한 초
    // 2-2. 시간을 두 자릿수로 표현
    m = m < 10? "0"+m : m; // 8분 -> 08분
    s = s < 10? "0"+s : s; // 3초 -> 03초
    // 2-3. 출력
    document.querySelector('.timebox').innerHTML = `${m}분${s}초`;
    // 2-4. 초 감소
    timer--;
    // 2-5. 만약에 초가 0보다 작아지면
    if(timer<0){    //
        clearInterval(timerInter); // 이거 멈추려고 함수를 timerInter라는 변수에 저장해 둠
        authbox.innerHTML = `인증 시간 경과`;
        authreqbtn.disabled = false;

    }

},1000);


}
// 11. 인증함수
function auth(){
    // 1. 내가 입력한 인증번호
    let ecodeinput = document.querySelector('.ecodeinput').value;

    // ===========내가 입력한 인증번호를 자바에게 보내기============== //

    $.ajax({
        url : "/auth/email/check",
        method : "get", // 보안이 필요한 건 없으니까
        data : {'ecodeinput':  ecodeinput},
        success : (r) => {
        // 3. 성공시 / 실패시
         if(r){
            checkArray[4] = true;
            document.querySelector('.emailcheckbox').innerHTML = '통과';
            clearInterval(timerInter); // 음?
            authbox.innerHTML = ``; // 인증 구역 없애기
            authreqbtn.disabled = false; // 해당 버튼 사용
         }else{
                alert('인증번호가 다릅니다. 또는 인증세션 시간 초과');
         }

        }

    })
    let result = true;
    // ========================= //




}



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
    if(아이디규칙.test(id)){ // 정규표현식에 맞으면 실행되는 아작스
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
    fileReader.onload = event =>{ // => 이렇게 쓰는거 자체가 함수의 선언
        console.log(event);     // ProgressEvent
        console.log(event.target);
        console.log(event.target.result); // 여기에 읽어온 첨부파일의 바이트가 있음.
        document.querySelector('#preimg').src = event.target.result; // 이렇게 src를 바꿔줘서 미리보기를 해줄수도 있구나
    }
}

// 1. 회원가입
function signup(){
    console.log(checkArray);
    // * 유효성 검사 체크 현황중에 하나라도 false이면 회원가입 금지
    for(let i =0; i<checkArray.length;i++){
        if(!checkArray[i]){  // false 가 있으면 나가버렸!
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
        // form 으로는 이렇게 보내나봄.

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
                data : info, // 이렇게 객체화로 보내도 Dto로 들어감. 컨트롤러에서 받는 매개변수 (LoginDto loginDto) 임
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


/*
    함수 정의 방법
        1. function 함수명(매개변수){}
        2. function (매개변수){} // 익명함수
        3. (매개변수) => {} // 람다, 화살표 함수
*/