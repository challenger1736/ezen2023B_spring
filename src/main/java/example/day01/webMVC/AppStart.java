package example.day01.webMVC;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 스프링부트 주입.
public class AppStart { // 얘네는 웹으로 바뀌는 애들
    public static void main(String[] args) {
        // 스프링 시작
        SpringApplication.run(AppStart.class);
        //http://localhost:8080


    }
}
