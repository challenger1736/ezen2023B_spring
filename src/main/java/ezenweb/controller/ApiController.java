package ezenweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api") // api용 api url의 컨트롤러 선언
public class ApiController {

    @GetMapping("")
    public String getApi(){
        return "ezenweb/api/api";
    }
}
