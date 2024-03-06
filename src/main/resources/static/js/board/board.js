// ========== 페이지 관련 객체 만들기(여러개의 변수를 묶겠다.) =========== //
let pageObject = {
    page : 1,           // 현재 페이지
    pageBoardSize : 5,  // 현재 페이지당 표시할 게시물 수
    bcno : 0,            // 현재 카테고리
    key : 'b.btitle',   // 현재 검색 키
    keyword : ''        // 현재 검색
}

// 오늘 날짜를 가져오기
let currentDate = new Date();

// 1. 전체 출력용 : 함수 : 매개변수x , 반환x , 언제 실행할껀지? : 페이지 열릴 때(JS)
doViewList(1); // 처음엔 첫 페이지 출력
function doViewList(page){
    console.log("doViewList() 실행");

    pageObject.page = page; // 매개변수로 들어온 페이지를 현재페이지로 설정해주고,

    $.ajax({
        url:"/board/do",
        method: "get",
        data : pageObject , // 또는 url 에 ?page=1 이런식
        success: (r) => {
            console.log(r);
            // 어디에
            let boardTableBody = document.querySelector("#boardTableBody");
            // 무엇을
            let html = "";
                // 서버가 보내준 데이터를 출력
                // 1. 방식 forEach
                r.list.forEach(board=>{console.log(board);
                    let dateFromMySQL = new Date(board.bdate); // MySQL datetime 값을 js 객체로 변환
                    console.log(board.bdate);
                    console.log(dateFromMySQL.toDateString());
                    console.log(currentDate.toDateString());
                    let todaytime = board.bdate.split(" ");
                    html += `<tr>
                                 <th >${board.bno}</th>
                                 <td ><a href="/board/view?bno=${board.bno}">${board.btitle}</a></td>
                                 <td ><img src="/img/${board.img}" style="width:20px; border-radius:50%;"/>${board.id}</td>
                                 <td >${dateFromMySQL.toDateString()!=currentDate.toDateString() ? board.bdate : todaytime[1]}</td>
                                 <td >${board.bview}</td>
                         </tr>`;}) // board는 그냥 반복문의 반복변수 임의로 설정
                // 2. 일반 for문 으로하면 이런식이겟죠
//                for(let i = 0 ; i<r.list.length ; i++){
//                    console.log(r[i]);
//                }
            // 출력
            boardTableBody.innerHTML = html;

            // 페이지네이션 출력

            //1 어디에
            let pagination = document.querySelector(".pagination");
            //2 무엇을
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

            // 부가 출력
            document.querySelector('.totalPage').innerHTML = r.totalPage;
            document.querySelector('.totalBoardSize').innerHTML = r.totalBoardSize;
        }
    })

    return;
}; // end


// 2. 페이지당 게시물 수
function onPageBoardSize(object){
    console.log(object);
    console.log(object.value); // 이렇게 선택한 옵션의 밸류 받아올 수 있음.

    pageObject.pageBoardSize = object.value;
    doViewList(1);
}

// 3. 카테고리 변경 함수
function onBcno(bcno){
    // bcno : 카테고리 식별번호 [ 0 : 전체, 1~ : 식별번호 pk ]
    pageObject.bcno = bcno;
    doViewList(1);
    // 검색 제거 ( 검색이 없다는 기준 데이터 )
    pageObject.key = 'b.btitle';
    pageObject.keyword = '';
    document.querySelector('.key').value = 'b.btitle';
    document.querySelector('.keyword').value = '';

    // 카테고리 활성화 css 적용 (해당 버튼에 categoryActive 클래스 대입)
    // 1. 모든 카테고리 버튼(querySelectorAll) 호출
    let categoryBtns = document.querySelectorAll(".boardCategoryBox>button");
    // .querySelectorAll은 list로 가져옴
    console.log(categoryBtns);
    // 2. 선택된 카테고리 번호(매개변수bcno) 에 class 대입하고
        //dom객체.classList.add("추가할 클래스명")
        //dom객체.classList.remove("삭제할 클래스명")
        // 1. 활성화 초기화
        for(let i = 0 ; i<categoryBtns.length ; i++){
            categoryBtns[i].classList.remove("categoryActive");
        }
        // 2. 해당 bcno만 활성화
        categoryBtns[bcno].classList.add("categoryActive")

    // 재출력
    doViewList(1);


}

// 4. 검색 함수
function onSearch(){
    // 1. 입력받은 값 가져오기
    let key = document.querySelector('.key').value;
    let keyword = document.querySelector('.keyword').value;
    // 2.
    pageObject.key = key;
    pageObject.keyword = keyword;
    // 3.
    doViewList(1);
}
