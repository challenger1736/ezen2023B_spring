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
            document.querySelector('.mid').innerHTML = r.id;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bdate').innerHTML = r.bdate;// div 이기 떄문에, 그 사이! innerHTML
            document.querySelector('.bview').innerHTML = r.bview;// div 이기 떄문에, 그 사이! innerHTML
            //* 다운로드 링크
                // 유효성 검사
            if(r.bfile != null){
             document.querySelector('.bfile').innerHTML = `<a href="/board/file/download?bfile=${r.bfile}">${r.bfile}</a>`;// div 이기 떄문에, 그 사이! innerHTML
             document.querySelector('.bcontent').innerHTML = `<img src="\\img\\${r.bfile}" style="width:80%;" /> </br></br>`+ r.bcontent
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
             onReplyList(); // 비동기할거면 이런식으로 성공의 성공(success)에 넣기
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

// 3. 댓글쓰기
function onReplyWrite( brindex ){
    $.ajax({
        url : "/board/reply/write.do",
        method : "post",
        data : {
            "bno" : bno, // 현재 보고 있는 게시물 번호
            "brcontent" : document.querySelector(`.brcontent${brindex}`).value, // 작성된 댓글 내용
            "brindex" : brindex // 댓글 위치 번호 [ 0: 상위, 1+: 하위 ]
        },
        success : (r) => {console.log(r);
            if(r){alert('댓글 작성 성공'); onReplyList();}
            else{alert('댓글 작성 실패');}
        }
    })

}


//4. 댓글출력 [ 1. 게시물 출력된 후 2. 댓글 작성시 3. 댓글 삭제 5. 댓글 수정 ]
function onReplyList(){
    $.ajax({
        url:"/board/reply/do",
        method:"get",
        data : { "bno" : bno},
        success:(r)=>{
            console.log(r);
            let replyListBox = document.querySelector('.replyListBox');
            let html = ``;
                r.forEach((reply)=>{
                    html += `<div>
                                <span>${reply.brcontent}</span>
                                <span>${reply.mno}</span>
                                <span>${reply.brdate}</span>
                                <button type="button" onclick="subReplyView(${reply.brno})">답변작성</button>
                                <div class="subReplyBox${reply.brno}">
                                </div>
                                ${onSubReplyList(reply.subReply)}
                            </div>
                    `
//                    reply.subReply.forEach((subReply1)=>{
//                              html+=    `
//                                        <div style="margin-left : 50px;">└
//                                        <span>${subReply1.brcontent}</span>
//                                        <span>${subReply1.mno}</span>
//                                        <span>${subReply1.brdate}</span>
//                                        </div>
//                                        `});

// map 알려주심. (forEach대신)
//                        let subReplys = reply.subReply.map((subReply1)=>{
//                                 return    `<div style="margin-left : 50px;">└
//                                           <span>${subReply1.brcontent}</span>
//                                           <span>${subReply1.mno}</span>
//                                           <span>${subReply1.brdate}</span>
//                                           </div>
//                                           `});
                    // 여기에 대 댓글 작성 // 클래스명 뒤에 ${}로 식별키로 구분하기.
                 })
                 replyListBox.innerHTML = html;
                }
    })
}

// 5. 대댓글 작성 칸 생성 함수
function subReplyView(brno){
    let html = `
                <textarea class="brcontent${brno}"></textarea>
                <button type="button" onclick="onReplyWrite(${brno})">답변작성</button>
    `
    document.querySelector(`.subReplyBox${brno}`).innerHTML = html;
}

// 6. 대댓글 함수 출력 함수
function onSubReplyList(subReply){
let subHTML =``;
subReply.forEach((reply)=>{
                    subHTML += `<div style="margin-left : 50px;">└
                                <span>${reply.brcontent}</span>
                                <span>${reply.mno}</span>
                                <span>${reply.brdate}</span>
                            </div>
                    `
})
    return subHTML;
}



