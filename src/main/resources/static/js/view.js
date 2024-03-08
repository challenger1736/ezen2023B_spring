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
            document.querySelector('.mid').innerHTML = r.mid;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bdate').innerHTML = r.bdate;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bview').innerHTML = r.bview;// div 이기 떄문에, 그 사이! innerHTML
            //* 다운로드 링크
                // 유효성 검사
            if(r.bfile != null){
             document.querySelector('.bfile').innerHTML = `<a href="/board/file/download?bfile=${r.bfile}">${r.bfile}</a>`;// div 이기 떄문에, 그 사이! innerHTML
            }


            // * 삭제 / 수정 버튼 활성화 ( 해당 보고 있는 클라이언트가 게시물 작성자의 아이디와 동일하면 )
                // 유효성 검사.
                // 현재 로그인된 아이디
//                let loginId = document.querySelector('.top_menu>#login_menu');// 이런식으로 안으로 들어가서 결국 id만 뽑아내는 방법
//                console(loginId);
                $.ajax({
                    url : "/member/login/check",
                    method : 'get',
                    success : (loginId) => {
                        if(loginId == r.id){
                            document.querySelector('.btnBox').innerHTML += ` <button class="boardBtn" type="button" onclick="onDelete(${r.bno})">삭제</button>
                                                                                        <button class="boardBtn" type="button" onclick="location.href='/board/update?bno=${r.bno}'">수정</button>`;
                        }
                    }
                })





        }
    })
}

function onDelete(bno){
    $.ajax({
        url: "/board/delete.do",
        method: "delete",
        data: {'bno' : bno},
        success:(r)=>{
            console.log(r);
            if(r){alert('삭제 성공'); location.href ="/board"}
            else{ alert('삭제실패')}
        }
    })
}


