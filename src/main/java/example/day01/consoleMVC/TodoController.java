package example.day01.consoleMVC;

import java.util.ArrayList;

public class TodoController { // java 그대로라고 하셨지만 아니더라

    private TodoDao todoDao = new TodoDao(); // 만들수가 없으므로 단 하나만 컨트롤에서 생김.

    // 할 일 등록 함수
    public boolean doPost(TodoDto todoDto){
        return todoDao.doPost(todoDto);
    }

    // 할 일 목록 출력 함수
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }

}
