console.log('member.js')

// 1. 회원가입
function signup(){
    console.log("signup() 함수 실행")
    // 1. HTML 입력값 호출 [ document.querySelector() ]
    let id = document.querySelector('#id').value ; console.log(id)
    let pw = document.querySelector('#pw').value; console.log(pw)
    let name = document.querySelector('#name').value; console.log(name)
    let phone = document.querySelector('#phone').value;   console.log(phone)
    let email = document.querySelector('#email').value;   console.log(email)
    let img = document.querySelector('#img').value ;   console.log(img)
    // -- 유효성 검사 할 거면 하고,
    // 2. 객체화 [ let info = {} ]
    let info = {
        id : id, pw: pw, name : name, phone : phone, email : email, img : img
    }; console.log(info);

    // 3. 원래 배열에 저장--> Spring Controller 서버와 통신[ JQUERY AJAX ]
//    $.ajax({
//            url : 'spring controller mapping 주소'
//            method : 'mapping 방법'
//            data : : '보낼 데이터'
//            success : function result( '받을 데이터' ){}
//        })
    $.ajax({ // ajax 쓸려면 html에 jQuery 포함 되어야함.
            url : '/member/signup',
            method : 'POST',
            data : info,
            // contentType : application/json 이런식으로 변환도 가능함. 지금은 폼과 동일하게 전송되는 일.(기본값이 form)
            success : function ( result ){ //함수명은 생략가능. Responsebody의 return값이 success의 result 로 들어온다.
                console.log(result);
                // 4. 결과
                if(result){
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
                    }else{
                        alert('로그인 실패');
                    }
                }
            });
    // 4. 결과

}