package book.controller;

//@Controller에 해당하는 컨트롤러
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // * 이 클래스가 컨트롤러임을 선언
public class FirstController {

    @GetMapping("/hi") // 컨트롤러임을 선언해야만 GetMapping, // 통신방법 http 이므로  http://localhost:8080/hi
    public String niceToMeetYou(Model model){//ui Model 인터페이스(맵 같은거지만 그냥 스프링에서는 이런 느낌으로 쓴다.) Model에서 {{}}값들을 들고와서 addAttribute로  greetings 머스테치에 넣어주는 방식인가봄.
        // return "머스테치파일명"을 넣어주기
        model.addAttribute("유저name","홍팍");
        return "greetings"; // greetings.mustache 파일 반환 but 스프링에서 html으로 변환후 넘어감.
    }

    //http://localhost:8080/greetings.mustache  [X] resource/templates
    //http://localhost:8080/greetings.html  [X] resource/templates
    //http://localhost:8080/hello.html  [O] resource/static
    //http://localhost:8080/hi  [O]

    /*
    HTTP : 하이퍼 텍스트 통신 규약, 이동식문서 통신 규약
        1. IP 주소:PORT번호         , 스프링아~             , localhost:80
        2. /문서(자원)의경로         , 도서지급대장문서줘      , /hi , 그걸 정하는 애가 @GetMapping("/hi")

    브라우저[클라이언트]                                                                      스프링(지금 예제는 Controller)[서버]
    강호동                                                                                          신동엽 localhost:8080
                       말: 강호동이 신동엽에게 도서지급대장문서를 줘.                              서랍(=hi) [return 도서지급대장문서 = greetings.mustache]
                       HTTP: http://localhost:8080/hi
                        -------------------------------------->
    브라우저             신동엽이 강호동에게 도서지급대장문서를 응답
    html렌더링가능        greetings.html(mustache를 html로 변환해서 보내줌, 스프링이)    강호동은 뷰 템플릿을 모르니까 뷰 템플릿을 HTML 랜더링 하고 HTML로 반환
                        html은 문자[String으로 return 전송]
                        <--------------------------------------

     */

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("xx","홍길동");
        // return 머스테치파일명
        return "goodbye";
    }
}
