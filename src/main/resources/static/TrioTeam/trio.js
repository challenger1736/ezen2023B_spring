console.log("테스트");

function doCreate(){

    //입력받은 데이터 가져오기
//    let mno = document.querySelector('#mno').value; console.log(mno);
    let mname = document.querySelector('#mname').value; console.log(mname);
    let mphone = document.querySelector('#mphone').value; console.log(mphone);

    //객체화
    let info = {
    mname : mname , mphone : mphone

    }; console.log(info);

   $.ajax({
       url : '/members/create',
       method : 'POST',
       data :   info ,
       success : function ( result ) {
        //4. 결과
       console.log(result);
       if(result){
        alert('글쓰기 성공'); doRead(); //안내후 게시물목록 새로고침

        }else{alert('글쓰기 실패'); }
       }
    })
}

doRead(); //JS 열릴때 최초 실행


function doRead(){

    $.ajax({
       url : '/members',
       method : 'GET',
       success : function ( result ){console.log(result);

    let tbody = document.querySelector('table tbody');

    let html = "";

 for(let i = 0; i<result.length; i++){
     console.log( result[i] );
        html +=` <tr>
                        <th> ${result[i].mno} </th> <th> ${result[i].mname}  </th> <th> ${result[i].mphone}  </th>
                        <th>
                        <button onclick="doDelete( ${ result[i].mno }  )" >삭제</button>
                        <button onclick="doUpdate( ${ result[i].mno }  )" >수정</button>
                        <button onclick="dopaypage( ${ result[i].mno }  )">급여</button>
                        </th>
                    </tr>`
     };

     tbody.innerHTML = html;
     }
    })
}
function doUpdate(mno){
    console.log("doUPpdate()");


    let mphone = prompt('변경할 전화번호');

    //객체화
    let info = {
    mno : mno , mphone : mphone
    }
    //-----------------AJAX------
     $.ajax({
           url : `/members/update/${mno}/${mphone}`,
           method : 'POST',
           data :   info ,
           success : function ( result ){

           if(result){alert('폰번호 변경 성공'); doRead(); }
           else{ alert('폰번호 변경 실패');}

           }
     })
        //----------------------

}

function doDelete(mno){
console.log("doDelete()");

$.ajax({
       url : `/members/delete/${mno}`,
       method : 'GET',

       success : function ( result ){

       if(result){alert('사원 삭제 성공'); doRead(); }
       else{alert(' 사원 삭제 실패');}

       }
    })


}


function payin(){

     let payreason = document.querySelector('#payreason').value; console.log(payreason);
     let pay = document.querySelector('#pay').value;  console.log(pay);

     let info = {
     payreason : payreason , pay : pay
    }

     $.ajax({
           url : `/members/pay/${mno}`,
           method : 'POST',
           data :   info ,
           success : function ( result ) {
            //4. 결과
           console.log(result);

           if(result){
            alert('급여 등록 성공'); dopayread(); // 급여 목록 새로고침
           }else{alert('급여 등룍 실패');}
           }
     })
}
function dopaypage0(mno){
    location.href = 'http://localhost:80/TrioTeam/paypage.html';
    dopaypage(mno);
}


function dopaypage(mno){
 console.log('dopaypage() js실행');
 console.log(mno);
$.ajax({
       url : `/members/pay/${mno}`,
       method : 'GET',
       success : function ( result ){console.log("result "+result);


    //1. 어디에
    let tbody = document.querySelector('table tbody');
    //2. 무엇을
    let html = "";

    for(let i = 0; i<result.length; i++){
     console.log( result[i] );
        html +=   ` <tr>
                        <th> ${result[i].mno} </th> <th> ${result[i].payreason}  </th> <th> ${result[i].pay}  </th>
                   </tr>`
     };

     //3.출력
     tbody.innerHTML = html;

     }
    })
}


