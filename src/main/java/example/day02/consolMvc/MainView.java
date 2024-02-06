package example.day02.consolMvc;

import java.util.ArrayList;
import java.util.Scanner;

public class MainView { // 얘네는 웹으로 바뀌는 애들

    Scanner scanner = new Scanner(System.in);

    private TodoController todoController = new TodoController();
    public void home(){
        while(true){
            doGet(); // 할 일 목록 출력 함수
            System.out.println("1. 할일 등록 2. 할일 상태 변경 3. 할일 삭제");
            int ch = scanner.nextInt();
            if(ch==1){
                doPost();           //할일 등록
            }if(ch==2){
                doPut();            // 할일 상태 변경
            }if(ch==3){
                doDelete();         // 할일 삭제
            }

        }
    }

    // 할 일 등록 함수
    public void doPost(){
        System.out.print("할일 내용 : "); String content = scanner.next();
        System.out.print("마감일[yyyy-mm-dd] : "); String deadline = scanner.next();

        TodoDto todoDto = new TodoDto();
        todoDto.setContent(content);
        todoDto.setDeadline(deadline);

        boolean result = todoController.doPost(todoDto);
        System.out.println(result);
    }

    // 할 일 목록 출력 함수
    public void doGet(){
        // 1. 입력받기 - 전체 출력이라 조건이 없음.
        // 2. 객체화 할 것도 없음.
        // 3. 컨트롤에게 요청 응답 받기
        ArrayList<TodoDto> result = todoController.doGet();
        // 4. 응답결과 출력하기
        for(int i =0 ; i<result.size(); i++){
            TodoDto todoDto = result.get(i); // i번쨰 dto를 호출
            System.out.printf("%2s %10s %10s %10s \n",
                    todoDto.getId(),
                    todoDto.getDeadline(),
                    todoDto.isState(),
                    todoDto.getContent()
                    );
        }
    }
    // 4. 할일 상태 수정
    public void doPut(){
        // 1. 입력받기
        System.out.print("수정할 Todo id : ");
        int id = scanner.nextInt();
        System.out.print("수정할 상태 : ");
        boolean state = scanner.nextBoolean();
        // 2. 객체화
        TodoDto todoDto = new TodoDto();
        todoDto.setId(id);
        todoDto.setState(state);
        // 3. 컨트롤에게 보내기
        boolean result = todoController.doPut(todoDto);
        // 4. 응답결과 출력하기
        System.out.println(result);
    }

    // 5. 할일 삭제
    public void doDelete(){
        // 1. 입력받기
        System.out.print("삭제할 Todo id : ");
        int id = scanner.nextInt();

        boolean result = todoController.doDelete(id);
        System.out.println(result);
    }

}
