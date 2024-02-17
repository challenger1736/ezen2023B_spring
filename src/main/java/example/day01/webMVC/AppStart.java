package example.day01.webMVC;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 스프링부트 주입. - 스프링 컨테이너에 애들 생김
public class AppStart { // 얘네는 웹으로 바뀌는 애들
    public static void main(String[] args) {
        // 스프링 시작
        SpringApplication.run(AppStart.class); // SpringApplication에 있는 정적 메소드 run()이 실행되어 그 해당 클래스를 실행하게 해줌
        //http://localhost:8080


    }
}
