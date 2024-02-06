package book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 컨트롤러를 자동으로 스캔해주는 역할(핸들 컨트롤러) -> 힙 할당해서 컨트롤러를 알아서 켜준다.
public class FirstProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstProjectApplication.class);
    }
}
