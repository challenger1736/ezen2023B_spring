console.log("Create 글쓰기 JS 실행");

function doCreate(){ // 글쓰기 js
    console.log('doCreate js 함수 실행');
    let btitle = document.getElementsByName("btitle")[0].value;
    let bcontent = document.getElementsByName("bcontent")[0].value;
    let bwriter = document.getElementsByName("bwriter")[0].value;
    let bpw = document.getElementsByName("bpw")[0].value;

    let info = {
            btitle : btitle ,
            bcontent : bcontent ,
            bwriter : bwriter ,
            bpw : bpw
    }

   $.ajax({
        url: "/SoloTest/test1/html/contentcreate",
        method: "POST",
        contentType : "application/json",
        data : JSON.stringify(info),
        success : (r) => { //{}안에 실행문
            location.href = "/SoloTest/test1/html/TestMVC1.html";
        }


   })
}