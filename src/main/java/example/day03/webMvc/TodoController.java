package example.day03.webMvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// 스프링에게 컨트롤이란걸 알리기 // 스프링 시작하면
@RestController // 스프링 컨테이너[스프링이 관리하는 메모리공간](JVM안에 있음)에 빈(객체) 등록 , IOC라고 함
                // IOC 제어역전 기법 : 개발자가 객체 관리X, 스프링이 대신
public class TodoController { // java 거의 그대론데 컨트롤러 선언과 매핑 차이 정도만 있다.

//    private TodoDao todoDao = new TodoDao(); //스프링에선 이렇게 안쓴다.
// 밑의 것과 같다.
    @Autowired // 스프링 컨테이너의 빈 찾아서 주입. // 전체 조건 : 빈 등록을 미리 해야한다 // @Component
    private TodoDao todoDao;
    
    // 자원의 경로를 만들때 Mapping을 쓴다.
    @PostMapping("todo/post.do") // HTTP 랑 통신하기 위한 Mapping
    // 할 일 등록 함수
    public boolean doPost(TodoDto todoDto){
        return todoDao.doPost(todoDto);
    }

    @GetMapping("todo/get.do")
    // 할 일 목록 출력 함수
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }

    @PutMapping("todo/put.do")
    public boolean doPut(TodoDto todoDto){
        return todoDao.doPut(todoDto);
    }

    @DeleteMapping("todo/delete.do")
    public boolean doDelete(int id){
        return todoDao.doDelete(id);
    }

}
