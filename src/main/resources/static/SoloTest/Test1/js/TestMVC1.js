console.log("TestMVC1 JS 실행"); // 절대 경로인지 아닌지 알기 힘드므로 항상 이거 해주기.

//전체 글 출력하는 함수 만들기.

doList();

function doList(){ // 전체 글 출력 js
    console.log('doList js 함수 실행');

   $.ajax({
        url: "/SoloTest/test1/html/contentsList",
        method: "GET",
        success : (r) => { //{}안에 실행문
            let list = document.querySelector('#contentsList'); // 위치 정하기. 안해도 됨
            let html = ""; // 넣을 html
            for(let i=0; i<r.length; i++){
            console.log(r[i]); // 콘솔 찍어보기.
            html += `
                            <tr>
                                <th>${r[i].bno}</th>
                                <th>${r[i].btitle}</th>
                                <th>${r[i].bwriter}</th>
                                <th>${r[i].bview}</th>
                            </tr>
            `
            }
             list.innerHTML = html;
        }


   })
}

