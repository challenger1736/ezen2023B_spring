package example.day01.webMVC;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController // 해당 클래스가 스프링 MVC를 하겠다고 등록
public class TodoController { // java 그대로

    private TodoDao todoDao = new TodoDao();



    @GetMapping("/todo/post.do")
    // 할 일 등록 함수
    public boolean doPost(TodoDto todoDto){
        return todoDao.doPost(todoDto);
    }

    // 넣는 함수
    // http://localhost:8080/todo/post.do // 얜 에러뜸.
    // GetMapping으로 바꾸고
    // http://localhost:8080/todo/post.do?content=안녕하세요&deadline=2024-02-05



    @GetMapping("/todo/get.do")
    // 할 일 목록 출력 함수
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }

    // http://localhost:8080/todo/get.do

}
