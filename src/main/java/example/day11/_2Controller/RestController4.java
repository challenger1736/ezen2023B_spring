package example.day11._2Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // @Controller + @ResponseBody
@RequestMapping("/day11") // 해당 클래스내 매핑함수들의 공통URL
public class RestController4 {

    @GetMapping("/ajax1")
    public String ajax1(){
        System.out.println("RestController4.ajax1");
        return "응답1"; // text/plain
    }

    @GetMapping("/ajax2/{id}/{content}")
    public String ajax2(@PathVariable int id,@PathVariable String content){
        System.out.println("RestController4.ajax2");
        System.out.println("id = " + id + ", content = " + content);
        return "응답2";
    }

    // 3. 쿼리스트링 1~4

//   1. @RequestParam

//    @GetMapping("/ajax3")
//    public String ajax3( int id, @RequestParam("content") String content2){
//        System.out.println("RestController4.ajax3");
//        System.out.println("id = " + id + ", content = " + content2);
//        return "응답3";
//    }

//  2. HttpServletRequest 객체

//        @GetMapping("/ajax3")
//    public String ajax3(HttpServletRequest req){
//        System.out.println("RestController4.ajax3");
//        System.out.println("req = " + req.toString());
//        return "응답3";
//    }

//  3. @RequestParam map
//    @GetMapping("/ajax3")
//    public String ajax3(@RequestParam Map<String,String> map){
//        System.out.println("RestController4.ajax3");
//        System.out.println("map = " + map);
//        return "응답3";
//    }

//  4. DTO
    @GetMapping("/ajax3") // Get 방식은 본문이 없다. body를 일반적으로 사용하지 않는다.(.ajax의 data를 사용하지 않는다.)
        public String ajax3(AjaxDto dto){
        System.out.println("RestController4.ajax3");
        System.out.println("dto = " + dto);
        return "응답3";
    }

// 5. HTTP BODY 본문
//    @GetMapping("/ajax4")
//        public String ajax4(int id, @RequestParam("content") String content2){
//        System.out.println("RestController4.ajax4");
//        System.out.println("id = " + id + ", content = " + content2);
//        return "응답4";
//
//    }

//     5. HTTP BODY 본문
    @GetMapping("/ajax4")
        public String ajax4(@RequestParam Map<String,String> map){
        System.out.println("RestController4.ajax4");
        System.out.println("map = " + map);
        return "응답4";
    }



}
