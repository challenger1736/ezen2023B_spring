console.log('js실행');

// 테스트용 변수들
let id = 9;
let content = "AJAX테스트중";

//1.
function ajax1(){
    console.log("ajax1() 실행") // 확인



   $.ajax( { // $.ajax 함수안에 {} 객체를 넣는 방식
        url: "/day11/ajax1",
        method: "get",
//        content-Type : 생략하면 form.
        success : (result) => {console.log(result);}, // 자바는(forEach) -> //(매개변수) => { 실행문 }
        error : (error) => {console.log(error);}


   } ) //ajax end
}


// 2. PathVariable로 해보기
function ajax2(){
    console.log("ajax2() 실행");

    $.ajax({
            url : `/day11/ajax2/${id}/${content}`, method:"get",
            success : (r)=>{console.log(r);}
    })
}

// 3. 경로상에 쿼리스트링 포함하기
function ajax3(){
    console.log("ajax3() 실행")

    $.ajax({
        url :  `/day11/ajax3?id=${id}&content=${content}` , method:"get",
        success : (r)=>{console.log(r);}
    })
}

function ajax4(){
    console.log("ajax4() 실행")

    $.ajax({
        url: "day11/ajax4",
        method : "get",
        data : {id: id, content : content},
        success : (r) => {console.log(r);}
    })
}

function ajax5(){
    console.log("ajax5() 실행")

    $.ajax({
        url: "day11/ajax5",
        method : "post",
        data : {id: id, content : content},
        success : (r) => {console.log(r);}
    })
}

function ajax6(){
    console.log("ajax6() 실행")

    $.ajax({
        url: "day11/ajax6",
        method : "post",
        contentType : "application/json",
        data : JSON.stringify({id: id, content : content}),
        success : (r) => {console.log(r);
//            JSON.parse(r); // ResponseBody쓰면 컨트롤에서 넘어올 때 자동으로 해준다.
        }
    })
}