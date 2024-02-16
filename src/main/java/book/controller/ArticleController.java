package book.controller;

import book.dao.ArticleDao;
import book.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Slf4j // 자바에서 간단한 로그 처리를 해주는 어노테이션. (lombok)
@Controller // 스프링이 컨트롤러임을 알게 해주는 것,
// 1. 스프링 컨테이너(스프링 메모리저장소)에 빈(객체/힙) 등록
// 2. 스프링이 해당 클래스를 사용할 수 있다.
// 3. 모든 클라이언트 요청은 컨트롤러로 들어온다.(MVC 패턴맞춰서)
public class ArticleController {

    @Autowired // 스프링 컨테이너에 등록된 빈 주입한다.(DI, 의존성 주입) IOC
    ArticleDao articleDao;

    @GetMapping("/articles/new") // HTTP 요청 경로 : GET 방식 : localhost:port번호/article/new
    public String newArticleForm(){
        return "articles/new"; // 얘는 자원이라고 함.
    }

    // html 창 띄우는건 무조건 get 방식
    // 1. <form action="/articles/create" method="post">
    // 2. 입력 태그 속성의 name과 DTO 필드와 필드명 일치하면 자동 연결된다.
    // 3. public 생성자 필요.
    @PostMapping("/articles/create") // HTTP 요청 경로 : Post 방식 : localhost:port번호/ariticle/create
    public String createArticle(ArticleForm form){
        System.out.println(new Date());
        System.out.println("ArticleController.createArticle");// soutm 중요!
        System.out.println("form = " + form); // 매개변수 출력.
        log.info(form.toString()); // 자동완성 -> 메뉴 -> 설정 -> 플러그인 -> 마켓플레이스 -> lombok
        //DAO에게 요청하고 응답 받기
        ArticleForm result = articleDao.createArticle(form);

//        return "redirect:URL_주소" // URL 재 요청
//        return "articles/index" // 모델이 없다.
//        return "redirect:/articles"; // 전체글 보기로 가려면

        return "redirect:/articles/"+result.getId(); // 개별글 보기로 가려면

    }

    // p 156 조회
        // [개별 조회]
        // 1. 클라이언트가 서버(spring) 요청시 id/식별키/pk 전달.
        // 2. HTTP URL 이용한 요청 : /articles/1 , /articles/2, /articles/3
            // 정해진 값이 아닌 매개변수일 경우에는 : /articles/{매개변수명}
            // 정해진 값이 아닌 매개변수면서 여러가지일 경우에는 : /articles/{매개변수명}/{매개변수명}/{매개변수명}
        // 3. 서버(spring) Controller가 먼저 받아서 URL 매핑(연결) 받기
        // 4. @GetMapping("/articles/{매개변수}")
        // 5. 함수 매개변수에서 URL상의 매개변수와 이름 일치
        // 6. 함수 매개변수 앞에 @PathVariable 어노테이션 주입.
            // @PathVariable : URL 요청으로 들어온 전달값을 컨트롤러함수의 매개변수로 가져오는 어노테이션
    @GetMapping("/articles/{id}") //클라이언트 요청 예시 :  /articles/1 , /articles/2, /articles/3 ...
//    @ResponseBody // 객체를 반환, 화면을 반환할래 객체(값)를 반환할래 선택 2가지 밖에 없다.
    public String show(@PathVariable Long id, Model model){              //id:1        id:2          id:3
        System.out.println("id = " + id);
        // JPA 대신에 DAO p.159
        // 요청된 ID를 가지고 DAO에게 데이터 요청
        ArticleForm form = articleDao.show(id);
        System.out.println("form = "+ form);
        // model.addAttribute("키","값"); // p.160 DAO에게 전달받은 값을 뷰템플릿에게 전달하기
        // model이라는 놈은 : 머스테치(뷰 템플릿)에서 사용할 데이터 전달 객체
        model.addAttribute("article", form);
        model.addAttribute("name", "유재석");
            // 머스테치 문법
            // {{변수명}}
            // {{>파일경로}}

        // p161 3. 해당 함수가 종료될 때 리턴값 1. 화면/뷰 (머스테치,HTML) 2. 값
        return "articles/show"; // 아직 템플렛 안만듬.
    }
    // p. 170 조회
        // [ 전체 조회 ]
    @GetMapping("/articles")
    public String index(Model model){
        // 1. DAO에게 요청해서 데이터를 가져온다.
        List<ArticleForm> result = articleDao.index();
        // 2. 뷰 템플릿에게 (머스테치)에게 전달할 모델을 만들어준다.
        model.addAttribute("articleList", result);
        // 3. 뷰 페이지 설정 175p
        return "articles/index";

    }

    // p 202 수정 1단계 : 기존 데이터 불러오기
    @GetMapping("articles/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model){ // ("id") 는 URL 변수명 을 넣어서 여러군데 PathVariable을 쓸 수 있다. (예> @PathVariable ~~~, @PathVariable ~~
        // JAVA 함수( @PathVariable 타입 매개변수명): 요청한 URL 주소 경로상의 매개변수 대입용 , 타입 자동변환도 해줌.
        // URL : /articles/{매개변수명}/edit , 예시 : /articles/1/edit, /articles/2/edit
        System.out.println("id = " + id);
        // 1. 다오에게 요청하고 응답받기
        ArticleForm form = articleDao.findById(id);
        // 2. 응답 결과를 뷰 템플릿에 보낼 준비(Model 써라는거야)
        model.addAttribute("article", form);
        // 3. return
        //뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        // * form에 입력 데이터를 Dto 매개변수로 받을때
            // 1. form 입력상자의 name과 Dto의 필드명 동일
            // 2. Dto의 필드 값을 저장할 생성자 필요
        System.out.println("form = " + form);
        ArticleForm updated = articleDao.update(form);

        return "redirect:/articles/"+updated.getId();
    }

    @GetMapping("/articles/{id}/delete") // a 태그이므로 GetMapping임, DeleteMapping 아님
    public String delete(@PathVariable long id){
        System.out.println("id = " + id);
        log.info("삭제 요청이 들어왔습니다!");
        // 1. 삭제할 대상 @PathVariable
        // 2. Dao 삭제 요청하고 응답받기
        boolean delete = articleDao.delete(id);
        // 3. 결과 페이지로 리다이렉트 하기.

        return "redirect:/articles";
    }

    //DAO에게 요청하고 응답 받기.
    // 1.
//    ArticleDao articleDao = new ArticleDao();
//    articleDao.createArticle();
    //2.
//    new ArticleDao().createArticle();
    //3. 해당 함수가 정적멤버면.
//    ArticleDao.createArticle();
    //4. 해당 클래스가 싱글톤
//    ArticleDao.getInstance().createArticle();
    //5. 스프링 컨테이너(JVM만들어진 메모리 저장소)에 있는 빈
//    @Autowired ArticleDao articleDao;
}
/*

    @어노테이션
        1. 표준 어노테이션 : 자바에서 기본적으로 제공
            @Override : 메소드 재정의
            등등
        2. 메타 어노테이션 : p 64
            - 소스코드에 추가해서 사용하는 메타 데이터
            - 메타 데이터 : 구조화된 데이터
            - 컴파일 또는 실행 했을때 코드를 어떻게 처리 해야 할지알려주는 코드
            @SpringBootApplication
                - 1. 내장 톰캣을 지원(내장 서버)
                - 2. 컴포넌트/module 스캔 : MVC 클래스 애들 스캔
                    동일 패키지또는 하위 패키지만 스캔하게 해놨음.
                    @Controller @RestController @Service
                - 3. 스프링 MVC 패턴 내장
                    view : resources
                    controller : @Controller, @RestController
                    service : @Service
                    model : Dao (@Repository)
                    entity(db table) : @Entity
                    그외 별도로 내가 등록 하려면 : @ Component
                    설정 클래스 : @Configuration
            @Controller
            @GetMapping

*/