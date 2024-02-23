package example.day11._1Servlet;

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
// 3. HttpServlet

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

@WebServlet("/servlet")
public class TestServlet extends HttpServlet {
    @Override // get 방식 http://192.168.17.85:8080/servlet
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doGet");
        //요청 HttpServletRequest(요청객체)

        String id  = req.getParameter("id"); //  QUERY PARAMETERS 에 id 입력
        System.out.println("id ="+ id); // 서버에게 보내는 메세지

        int type = Integer.parseInt(req.getParameter("type"));
        System.out.println("type = " + type);

        //응답 HttpServletResponse(응답객체)
//        resp.setContentType("text/html; chracter = utf-8"); // 데이터의 타입 ( 받는 입장에서의 데이터의 처리방법 )
        resp.setContentType("application/json"); // 데이터의 타입 ( 받는 입장에서의 데이터의 처리방법 )
//        resp.getWriter().println("안녕하세요.");
        resp.getWriter().println("{\"msg\" : \"안녕하세요.\"}");
         // 자바 데이터를 JSON 데이터로 변환하는 작업이 필요함.
            // 1. 직접한다.
            // 2. 라이브러리를 사용한다.
            // 3. 스프링MVC에서는 @ResponseBoby하면 알아서 해준다.
    }

    @Override // post 방식 http://192.168.17.85:8080/servlet
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doPost");
    }

    @Override // put 방식 http://192.168.17.85:8080/servlet
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doPut");
    }

    @Override // delete 방식 http://192.168.17.85:8080/servlet
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doDelete");
    }
}
