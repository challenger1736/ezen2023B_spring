// 1. 전체 출력용 : 함수 : 매개변수x , 반환x , 언제 실행할껀지? : 페이지 열릴 때(JS)
doViewList(1); // 처음엔 첫 페이지 출력
function doViewList(page){
    console.log("doViewList() 실행");
    $.ajax({
        url:"/board/do",
        method: "get",
        data : { 'page' : page }, // 또는 url 에 ?page=1 이런식
        success: (r) => {
            console.log(r);
            // 어디에
            let boardTableBody = document.querySelector("#boardTableBody");
            // 무엇을
            let html = "";
                // 서버가 보내준 데이터를 출력
                // 1. 방식 forEach
                r.list.forEach(board=>{console.log(board);
                html += `<tr>
                             <th >${board.bno}</th>
                             <td >${board.btitle}</td>
                             <td ><img src="/img/${board.img}" style="width:20px; border-radius:50%;"/>${board.id}</td>
                             <td >${board.bdate}</td>
                             <td >${board.bview}</td>
                         </tr>`;}) // board는 그냥 반복문의 반복변수 임의로 설정
                // 2. 일반 for문 으로하면 이런식이겟죠
//                for(let i = 0 ; i<r.list.length ; i++){
//                    console.log(r[i]);
//                }
            // 출력
            boardTableBody.innerHTML = html;

            // 페이지네이션 출력

            // 어디에
            let pagination = document.querySelector(".pagination");
            // 무엇을
            let pagehtml = "";
                // 서버가 보내준 데이터를 출력
                // 일반 for문 혼자 해보기
                // 이전 페이지
                pagehtml +=`<li class="page-item"><a class="page-link" href="javascript:doViewList(${page-1 < 1 ? 1 : page-1})">이전</a></li>`
                // 페이지 번호 버튼
                for(let i = r.startBtn ; i<= r.endBtn ; i++){
                    pagehtml += `<li class="page-item ${i == page ? 'active': ''}" ><a class="page-link" onclick="doViewList(${i})">${i}</a></li>`
                }
                // 다음 페이지
                pagehtml +=`<li class="page-item"><a class="page-link" onclick="doViewList(${page+1 > r.totalPage ? r.totalPage : page+1})">다음</a></li>`

            // 3. 출력
            pagination.innerHTML = pagehtml;
        }
    })

    return;
};
