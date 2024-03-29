console.log('todo.js실행')

// JS 함수 정의 : function 함수명(매개변수){실행문}

//1.
function doPost(){
    console.log("doPost실행")
    // 1. 입력받은 값 가져오기
    let content =  document.querySelector('#content').value;
    let deadline =  document.querySelector('#deadline').value;
    console.log(content);
    console.log(deadline);
    // 2. 객체화
    let info = {
        content : content,
        deadline : deadline
    }; console.log(info)
    // 3. 컨트롤에게 요청/ 응답
        // HTTP 통신 : 어디에(url) /form에선 action , 어떻게(method) /form 에선 method 응답 데이터(x/success)
         $.ajax({ // 아작스가 어떤 url을 어떤 메소드로 실행할지 하게 해줌. / 들어오면 success로 자바에서 받은 리턴값을 매개변수에 자동으로 넣음.
                 url : 'todo/post.do',
                 method : 'post',
                 data : info,
                 success : function ( result ){ // 익명 함수
                 console.log(result)
                 if(result==true){
                 //화면 갱신
                    doGet();
                 }
                 //통신 응답 결과를  HTML 형식으로 출력해주기
                 }
})
}
    // 4. 출력


    // - 스프링(자바)와 통신(주고 받고)
    // jquery의 Ajax를 쓸 것.
    // $.ajax(JSON 형식의 통신정보)
    // {key : value , key :value} : 객체 , [] : 배열 이엇음

/*
    HTTP method : post , get, put, delete 등등

     $.ajax({})
     $.ajax({
      url : spring controller url / 통신 대상 식별(누구와)
      method : 'HTTP method'    / 통신 방법(어떻게)
      data : 'HTTP request value / 통신 요청으로 보낼 데이터(뭘 주고)
      success : HTTP response function / 통신 응답 함수(뭘 응답 받을 건지)
    })


*/
/*
    $.ajax({
        url : 'spring controller mapping 주소'
        method : 'mappin 방법'
        data : '매개변수/보낼 데이터'
        success : function result('받을 데이터'){}

    })

*/



//2.

doGet();
function doGet(){
 $.ajax({
        url : '/todo/get.do',
        method : 'get',
        success : function result( resultValue ){
        console.log(resultValue)
        //통신 응답 결과를  HTML 형식으로 출력해주기
        //1. 어디에
        let tbody = document.querySelector("table tbody")
        //2. 무엇을
        let html = "";
        for(let i =0 ; i<resultValue.length ; i++){
            html += `
                    <tr>
                        <th>${resultValue[i].id}</th>
                        <th>${resultValue[i].content}</th>
                        <th>${resultValue[i].deadline}</th>
                        <th>${resultValue[i].state}</th>
                    </tr>
            `
        }// for end
        //3. 대입
        tbody.innerHTML = html;


        }

 })}
//3.
function doPut(){}
//4.
function doDelete(){}