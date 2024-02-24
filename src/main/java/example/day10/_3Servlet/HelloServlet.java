package example.day10._3Servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 자바 회사에서 웹 개발을 위한 HTTP 통신 클래스 : HttpServlet
    // : MVC패턴에서는 주로 controller 역할

    // 서블릿 선언하는 방법
    // 1. 해당 클래스에 HttpServlet 상속받는다.
    // 2. 해당 클래스에 @WebServlet("HTTP식별주소") 어노테이션 주입해서 web.xml 에 등록한다.

    // 서블릿 실행 구동 순서
    // 1. 클라이언트(브라우저/WAS외부) HTTP 요청이 톰캣 서버(WAS)로 들어온다.
    // 2. 서블릿 컨테이너에 요청받은 서블릿이 있는지 없는지 판단.
        //3-1. 없으면 init() 메소드 실행한 서블릿 생성,
        //3-2. 있으면, 생성했으면 스레드(작업스레드) 할당
    // 4.  service() 실행하고 Get, Post등 method 요청에 따른 메소드로 이동
    // 5. doXXX 메소드 실행될 때, 요청(HttpServletRequest req)
        // - HTTP 관련된 정보를 요청할 수 있는 기능 가지고 있다.
    // 6. doXXX 메소드 종료될 때, 응답(HttpServletResponse resp)
        // - HTTP 관련된 정보를 응답할 수 있는 기능 가지고 있다.
    // ------------------- 다음 요청이 올 때까지.
    // 다시 요청이 들어오면 1 > 2 > 3-2 > 4 > 5
    // ------------------- 서버가 종료되면 destroy() 실행되면서 안전하게 서블릿 제거



@WebServlet("/hello-servlet") // web.xml에 해당 서블릿 등록
public class HelloServlet extends HttpServlet {
    // HttpServlet 클래스로부터 상속받으면 다양한 HTTP 관련 메소드 사용

    @Override// 1. [최초 요청 1번 실행] 해당 서블릿 객체가 생성 되었을 때 실행되는 메소드
    public void init(ServletConfig config) throws ServletException {
        System.out.println("HelloServlet.init");
        //DB 연동 미리 해놓거나 해놓을 수 있음,, Dao로 가서 DB연동만 해놓기정도.?
        super.init(config);
    }

    @Override// 2. [요청마다 실행] 해당 서블릿으로부터 HTTP 서비스가 실행되었을때 실행되는 메소드
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        super.service(req, resp);
    }

    @Override// 3. [HTTP method에 따라] HTTP 서비스 요청중에 HTTP method 방식이 get이면 실행되는 메소드
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.doGet");
        super.doGet(req, resp);
    }

    @Override// 4. [서버가 종료될 때 1번 실행] 해당 서블릿 객체가 삭제 되었을 때,사라졌을 때 실행되는 메소드.
    public void destroy() {
        System.out.println("HelloServlet.destroy");
        super.destroy();
    }
}
