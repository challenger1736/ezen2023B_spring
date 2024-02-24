package test.testmvc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 이번 테스트 패키지는 비회원 게시판을 해보자.
@SpringBootApplication // Spring Boot의 자동 구성 활성화 + SpringApplication을 사용하여 Spring Boot 애플리케이션을 실행하는 역할
public class AppStarttest1 {
    public static void main(String[] args) {
        SpringApplication.run(AppStarttest1.class); // 이걸 시작하므로써 이 패키지(testmvc1)의 빈(객체)들을 찾는다. 그걸 localhost:port번호/URL 로 찾는다.
    }
}
