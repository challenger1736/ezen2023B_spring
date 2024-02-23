package example.day11._2Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/day11")
public class RestController5 {

    // 경로상의 변수(패스베어러블, 쿼리스트링) <GET,DELET,POST,PUT> 다 쓸 수 있음 //
        // 글동록, 글조회, 글삭제, 글 수정 => Get 다 가능
        // 쿼리스트링 : URL 상에 데이터/매개변수 가 표시됨.
        // 기록을 남긴다.(캐시값을 남긴다.) 기록된 데이터를 재사용해서 일치하면 또 쓴다.
        // 단점 : 노출이 된다.

    //================= content Type : form <POST, PUT> =================== //
        // URL상에 데이터/매개변수 표시안함. => HTTP body(본문) 이용, POST/PUT 가능
    // 1.
//    @PostMapping("/ajax5")
//    public String ajax5(int id, @RequestParam("content") String content){
//        System.out.println("RestController5.ajax5");
//        System.out.println("id = " + id + ", content = " + content);
//        return "응답5";
//    }
//    // 2. @RequestParam Map
//    @PostMapping("/ajax5")
//    public String ajax5(@RequestParam Map<String,String> map){
//        System.out.println("RestController5.ajax5");
//        System.out.println("map = " + map);
//        return "응답5";
//    }

    // 3. DTO
    @PostMapping("/ajax5")
    public String ajax5(AjaxDto dto){
        System.out.println("RestController5.ajax5");
        System.out.println("dto = " + dto);
        return "응답5";
    }

    //================= content Type : application/json <POST, PUT> =================== //
//    @PostMapping("/ajax6")
//    public String ajax6(@RequestBody AjaxDto dto){ // Body 는 json 을 말하는 것
//        System.out.println("RestController5.ajax6");
//        System.out.println("dto = " + dto);
//        return "응답6";
//    }

    @PostMapping("/ajax6")
    public String ajax6(@RequestBody  Map<String,String> map){
        System.out.println("RestController5.ajax6");
        System.out.println("map = " + map);
        return "응답6";
    }

    // JSON 방식으로는 이렇게 못 받음 !!!!!
//    @PostMapping("/ajax6")
//    public String ajax6(@RequestParam int id,@RequestParam String content){
//        System.out.println("RestController5.ajax6");
//        System.out.println("id = " + id + ", content = " + content);
//        return "응답6";
//    }
}
