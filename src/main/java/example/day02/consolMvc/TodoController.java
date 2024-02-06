package example.day02.consolMvc;

import java.util.ArrayList;

public class TodoController { // java 그대로

    private TodoDao todoDao = new TodoDao();

    // 할 일 등록 함수
    public boolean doPost(TodoDto todoDto){
        return todoDao.doPost(todoDto);
    }

    // 할 일 목록 출력 함수
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }

    public boolean doPut(TodoDto todoDto){
        return todoDao.doPut(todoDto);
    }

    public boolean doDelete(int id){
        return todoDao.doDelete(id);
    }

}
