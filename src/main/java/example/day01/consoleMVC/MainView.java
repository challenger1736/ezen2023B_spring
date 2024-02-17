package example.day01.consoleMVC;

import java.util.ArrayList;
import java.util.Scanner;

public class MainView { // 얘네는 웹으로 바뀌는 애들

    Scanner scanner = new Scanner(System.in);

    private TodoController todoController = new TodoController();
    public void home(){
        while(true){
            doGet(); // 할 일 목록 출력 함수
            System.out.println("1. 할일 등록 : ");
            int ch = scanner.nextInt();
            if(ch==1){
                doPost();
            }
        }
    }

    // 할 일 등록 함수
    public void doPost(){
        System.out.print("할일 내용 : "); String content = scanner.next();
        System.out.print("마감일[yyyy-mm-dd] : "); String deadline = scanner.next();

        TodoDto todoDto = new TodoDto();
        todoDto.setContent(content);
        todoDto.setDeadline(deadline); // 받은 Dto를 넘기기.

        boolean result = todoController.doPost(todoDto);
        System.out.println(result); // true false 확인용 큰 의미 없음.
    }

    // 할 일 목록 출력 함수
    public void doGet(){
        // 1. 입력받기 - 전체 출력이라 조건이 없음. / 조건이 없다는 말은 뭐 넣을 데이터, 매개변수가 없다는 뜻.
        // 2. 객체화 할 것도 없음.
        // 3. 컨트롤에게 요청 응답 받기
        ArrayList<TodoDto> result = todoController.doGet();
        // 4. 응답결과 출력하기
        for(int i =0 ; i<result.size(); i++){
            TodoDto todoDto = result.get(i); // i번쨰 dto를 호출
            System.out.printf("%2s %10s %10s %10s \n",
                    todoDto.getId(),
                    todoDto.getDeadline(),
                    todoDto.isState(), // boolean 은 is
                    todoDto.getContent()
                    );
        }
    }

}
