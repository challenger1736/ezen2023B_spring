package example.day11._2Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/day11")
public class RestController3 {

    // HTTP 이용한 매개변수 보내는 방법
        // 1. 경로상의 변수       http://localhost:8080/day11/black/value
        // 2. 쿼리스트링 변수      http://localhost:8080/day11/black?key=value

    // 1. Get : get방식 localhost:8080/day11/red
//    @RequestMapping( value = "/day11/white", method = RequestMethod.GET)
//    @ResponseBody // 응답 컨텐츠 타입을 application/json으로 하겠다.
//    @RequestMapping( value = "URL", method = RequestMethod.XXX)
    @GetMapping("/red")
    public String getRed(HttpServletRequest rsq){
        System.out.println("RestController1.getRed");
        //요청 http://localhost:8080/day11/black?sendMsg=안녕
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        //응답
//        resp.setContentType("text/html");
//        resp.getWriter().println("안녕[클라이언트]");
        return "안녕[클라이언트]";
    }

    // 2. Post : post방식 localhost:8080/day11/red
//    @RequestMapping( value = "/day11/white", method = RequestMethod.POST)
//    @ResponseBody   // 응답 contentType 컬렉션프레임워크 또는 배열 ---> application/json 으로 변환
    @PostMapping("/red")
    public Map<String,String> postRed(HttpServletRequest rsq){
        System.out.println("RestController1.postRed");
        //요청 http://localhost:8080/day11/black?sendMsg=안녕
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        //응답
//        resp.setContentType("text/html");
//        resp.getWriter().println("안녕[클라이언트]");
//        String[] strArray = new String[2];
//        strArray[0] = "안녕";
//        strArray[1] = "클라이언트";
//        List<String> strArray = new ArrayList<>();
//        strArray.add("안녕");
//        strArray.add("클라이언트");
        Map<String,String> strArray = new HashMap<>();
        strArray.put("안녕","클라이언트");
        return strArray;
    }
    // 3. Put : put방식 localhost:8080/day11/red
//    @RequestMapping( value = "/day11/white", method = RequestMethod.PUT)
//    @ResponseBody
    @PutMapping("/red")
    public int putRed(HttpServletRequest rsq){
        System.out.println("RestController1.putRed");
        //요청 http://localhost:8080/day11/black?sendMsg=안녕
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        //응답
//        resp.setContentType("text/html");
//        resp.getWriter().println("안녕[클라이언트]");

        return 10;
    }

    // 4. Delete : delete방식 localhost:8080/day11/red
//    @RequestMapping( value = "/day11/white", method = RequestMethod.DELETE)
//    @ResponseBody
    @DeleteMapping("/red")
    public boolean deleteRed(HttpServletRequest rsq){
        System.out.println("RestController1.deleteRed");
        //요청 http://localhost:8080/day11/black?sendMsg=안녕
        String sendMsg = rsq.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        //응답
//        resp.setContentType("text/html");
//        resp.getWriter().println("안녕[클라이언트]");
        return true;
    }
}
