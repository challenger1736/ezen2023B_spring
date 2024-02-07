package example.day03.webMvc; // Controller(얜 HTTP 와 통신해야되서 조금 바뀜) 와 Dao,Dto 는 그대로기에 뷰는 복사 안함.
                                    // V[브라우저] <--HTTP--> C <--메소드--> D
                                    // * 브라우저는 HTTP 이용한 문서 통신/ 랜더링 프로그램
                                    // JAVA는 HTTP 못써서 그래서 SPRING Rest API 지원
                                    // @RestController
                                    // @Controller
                                    // @GetMapping          : 호출 [C]
                                    // @PostMapping         : 저장 [R]
                                    // @PutMapping          : 수정 [U]
                                    // @DeleteMapping       : 삭제 [D]

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// ----------------- 스프링부트 실행에 관련된 기능 주입 ------------------------
@SpringBootApplication // 1. 내장 서버, 톰캣이 실행됨
                        // 2. 동등한 패키지 혹은 하위 패키지내 @Controller 또는 @RestController가 스캔을 해줌 (중요!!)
public class AppStart { // 얘네는 웹으로 바뀌는 애들
    public static void main(String[] args) {
        // 스프링 자체를 시작.
        SpringApplication.run(AppStart.class);

    }
}
