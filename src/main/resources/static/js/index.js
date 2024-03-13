// 모든 페이지에서 적용할 공통 JS파일

// 1. 로그인 여부 확인요청 [js가 열릴 때마다 항상 체크할 놈]

$.ajax({
    url : '/member/login/check',
    method : 'get',
    success : (r)=>{
        console.log(r); // 일단 확인 먼저
        //1.어디에
        let login_menu = document.querySelector('#login_menu'); // 여기에 로그인하면 뭘 넣을거다. ( 사용자 정보를 넣을거다 )
        //2. 무엇을
        let html = ``;
        if(r!=''){ // 로그인 했을 때 , 세션에 있을 때

            $.ajax({url:"/member/login/info",
            method:'get',
            data : {id:r},
            async: false , // 이건 동기식으로 만들어주는 느낌 , 기다려!
            success:(r2)=>{console.log(r2); console.log(r2.uuidFile);
            html +=        `<li class="nav-item">
                                    <a class="nav-link" onclick="logout()">로그아웃</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/member/signup">내 정보</a>
                                </li>
                                <li class="nav-item">
                                    <img src="/img/${r2.uuidFile}" width="80px" />${r} 님
                                </li>`
                                }
                                })
        }
        else{ //로그인 안했을 때
            html += `<li class="nav-item">
                                             <a class="nav-link" href="/member/login">로그인</a>
                                         </li>
                                         <li class="nav-item">
                                         <a class="nav-link" href="/member/signup">회원가입</a>
                                         </li>`;
        }

        login_menu.innerHTML = html;
    }

})// ajax end

// 2. 로그아웃 함수 만들기
function logout(){
    $.ajax({
        url: '/member/logout',
        method:'get',
        success : (r)=>{
            if(r){
                alert('로그아웃 했습니다.');
                location.href='/'; //로그아웃 성공시 메인페이지로
            }else{
            alert('로그아웃 실패.(관리자에게 문의)');}

        }
    })
}