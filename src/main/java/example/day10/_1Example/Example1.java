package example.day10._1Example;

public class Example1 {
    public static void main(String[] args) {
        // 1. 계산기 객체 생성
        Calculator calculator = new Calculator();

        User1Thread user1Thread = new User1Thread();
        user1Thread.setCalculator(calculator);
        user1Thread.start(); // 작업스레드 run  실행 계산기 100 저장

        User2Thread user2Thread = new User2Thread();
        user2Thread.setCalculator(calculator);
        user2Thread.start(); // 작업스레드 run  실행 계산기 100 저장
    }
}
