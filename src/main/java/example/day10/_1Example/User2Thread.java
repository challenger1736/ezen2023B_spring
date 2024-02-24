package example.day10._1Example;

public class User2Thread extends Thread{

    // 1. 필드, 유저1객체가 가지고 있는 계산기
    private Calculator calculator;

    public User2Thread(){
        // setName : Thread클래스로부터 상속받은 함수( 작업스레드 이름 정해주기 )
        setName("User2Thread");
    }

    public void setCalculator(Calculator calculator){ // 세터, 외부로 부터 계산기를 받아서 저장해주겠다.
        this.calculator = calculator;
    }

    @Override
    public void run() {
        calculator.setMemory1(50);
    }
}
