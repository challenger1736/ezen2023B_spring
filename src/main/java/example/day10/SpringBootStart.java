package example.day10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication // 아파치 톰캣
@ServletComponentScan // 서블릿 검색
public class SpringBootStart {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStart.class);
    }
}

/*

클라이언트 : 데이터/서비스 요청하고 제공받는 입장
서버 : 데이터/서비스 요청받고 제공하는 입장
------------------------------------------------
정적 : HTML, CSS, JS, IMAGE (아파치 서버)
동적 : Servlet, JSP (톰캣 서버)
정적 동적 서버 : HTML, CSS, JS, IMAGE, SERVLET, JSP ( 아파치 톰캣 서버)
------------------------------------------------

 */
