package book.controller;

import book.dao.ArticleDao;
import book.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;


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
    public boolean createArticle(ArticleForm form){
        System.out.println(new Date());
        System.out.println("ArticleController.createArticle");// soutm 중요!
        System.out.println("form = " + form); // 매개변수 출력.
        log.info(form.toString()); // 자동완성 -> 메뉴 -> 설정 -> 플러그인 -> 마켓플레이스 -> lombok
        //DAO에게 요청하고 응답 받기
        return articleDao.createArticle(form);

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