package example.day09;

import java.awt.*;

public class Example1 {

    //* main 함수 안에는 main 스레드 포함
    public static void main(String[] args) {

        // 1. 현재 코드의 스레드 객체 호출
        // Thread.currentThread(); // 현재 스레드 호출 하는 코드
        Thread thread = Thread.currentThread();
        // 2. 현재 코드를 실행하는 스레드객체의 이름
        System.out.println("1 thread.getName() = " + thread.getName());

        // 3. 작업스레드 생성 4가지 방법
            // 자식 익명 객체 : 자식 타입이 없고 익명으로 만드는 객체
        for(int i = 0 ; i<3 ; i++){
            int finalI = i;
            Thread threadA = new Thread(){
                @Override // 물려받은거 재정의 하면 됨.
                public void run() { // - 작업스레드가 실행할 때 최초로 실행되는 함수.
                    Thread thread = Thread.currentThread();
                    thread.setName("내가 만든 작업스레드 " + finalI ); // + i가 안되는 이유 다른 스레드라서 : 다른 스레드의 지역변수 호출 불가능하다.
                    System.out.println("2 thread.getName() : " + thread.getName());
                }
            };
            threadA.start();

        }



        System.out.println("3 thread.getName() : " + thread.getName());

        // -- p.603 주어진 시간동안 스레드 일시정지 .sleep() : 정적메소드(static) : 정적메소드 호출하는 방법 : 클래스명.정적메소드();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for(int i =0; i<10 ; i ++){
            toolkit.beep();
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }


    }
}
