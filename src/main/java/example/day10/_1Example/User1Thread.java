package example.day10._1Example;

public class User1Thread extends Thread{ // extends Thread : 작업스레드 생성하기 위해

    // 1. 필드, 유저1객체가 가지고 있는 계산기
    private Calculator calculator;

    public User1Thread(){
        // setName : Thread클래스로부터 상속받은 함수( 작업스레드 이름 정해주기 )
        setName("User1Thread");
    }

    public void setCalculator(Calculator calculator){ // 세터, 외부로 부터 계산기를 받아서 저장해주겠다.
        this.calculator = calculator;
    }

    @Override
    public void run() {
        calculator.setMemory1(100);
    } // 여기서 calculator의 값 100으로 바꿔주는 함수 실행
}
