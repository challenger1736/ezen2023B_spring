package example.day07._2스택큐컬렉션;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Example1 {
    public static void main(String[] args) {

        // 1. 스택 컬렉션 생성 [Vector 상속 받은 컬렉션]
        Stack<Integer> coinBox = new Stack<>();

        // 2. 동전 넣기 = 스택 삽입 = push(add도 상속 받아서 사용가능한데, push로 그냥 자주 사용하는 용어의 차이)
        coinBox.push(100);
        coinBox.push(50);
        coinBox.push(500);
        coinBox.push(10);
        coinBox.add(0,13); // 엥 이렇게도 들어가네,?????

        // 3. 동전 빼기 = pop() (삭제되는거긴함.)
        while (!coinBox.isEmpty()){
            System.out.println("빼내기 전 : "+coinBox);
            System.out.println("빠질 돈 : "+coinBox.pop()); // 꺼내기
            System.out.println("빼내기 후 : "+coinBox);
            System.out.println();
        }

        Queue<String> messageQueue = new LinkedList<>(); // LinkedList에는 add 인덱스가 없음....? 뭐지?

        messageQueue.offer("안녕 홍길동");
        messageQueue.offer("안녕 김자바");
        messageQueue.offer("안녕 신용권");
        messageQueue.offer("안녕 감자");


        while (!messageQueue.isEmpty()){
            System.out.println(messageQueue);
            System.out.println(messageQueue.poll()); // 꺼내기
            System.out.println(messageQueue);
            System.out.println();
        }




    }
}
