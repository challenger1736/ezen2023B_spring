package test.testmvc1.test1controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.testmvc1.test1model.test1dao.Test1Dao;
import test.testmvc1.test1model.test1dto.Test1Dto;

import java.util.List;

@RestController
@RequestMapping("/SoloTest/test1/html")
public class Test1Controller {
    @Autowired
    Test1Dao test1Dao;

    @GetMapping("/contentsList") // 전체 글 출력되긴하는데 이거면 되나?
    public List<Test1Dto> contentsList(){
        return test1Dao.contentsList();
    }

    // 이제 글쓰기 해보자.
    @PostMapping("/contentcreate") // 글 쓰기.
    public void contentcreate(@RequestBody Test1Dto dto){ // 뭘 받아와서 뭘 넣을것인가? // 지금 html 에서 ajax로 받고 있으므로 json으로 받자.
        // json으로 매개변수 받으려고 RequestBody를 씀.
        test1Dao.contentcreate(dto);
        return ;
    }



}
