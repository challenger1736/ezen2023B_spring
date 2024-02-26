package ezenweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}

// ============================================================================= //

// 홈페이지 favicon(아이콘)
// static에다가 favicon이라는 파일로 넣어주면 됨. 확장자 .ico