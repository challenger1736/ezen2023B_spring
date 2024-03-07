package example.day08;

import java.awt.*;

public class Example1 {
    /*
        운영체제는 실행중인 프로그램을 프로세스로 관리한다.
            - 프로그램 1개당 프로세스 존재한다는 이야기
            - 멀티 태스킹 : 두가지 이상을 동시에 처리한다는 걸 이야기하는 것.
                - 프로그램 1개당 멀티 프로세스 존재 할 수 있다.
                - 프로세스 1개당 멀티 스레드 존재 할 수 있다.
            - 하나의 프로세스가 두 가지 이상의 작업을 처리
                - 멀티 스레드 : 하나의 프로세스가 두가지 이상의 작업을 처리
            스레드 : 코드의 실행 흐름
                - 스레드마다 스택 할당
                - 스레드끼리 자원 공유 안됨.
                - 하나의 스레드가 예외 발생시 전체 스레드가 예외 발생

            멀티 스레드 : 여러개의 코드 실행 흐름
                - 생성 : main 스레드가 추가 작업 스레드 생성
                - 자바는 무조건 하나이상의 스레드를 갖는다.(main 스레드)

                cpu 코어 1개당
                    ------------------------------------------------------------
                    멀티 스레드

                        작업 요청
                        멀티 : 안됨x
                        하나의 코어가,
                        여러개 스레드들의 작업 순서는 하나씩 처리( 컴퓨터는 빠르게 동시처리처럼 보일 뿐 )
                        작업순서 : 운영체제가 스케줄링에 의해서 자동으로 순서를 부여해서 처리함.
                        자바에서 서로다른 스레드끼리의 작업순서 정하기 불가능.
                        프로그램(소프트웨어)은 자원(하드웨어) 제어권이 없다. ( 그래서 운영체제가 자원을 할당해줌 )
                        운영체제 스케줄링 공부해 보기.
                    |
                    |   ------------------------
                    |           |               | 3
                    |           |               |
                    |     1     |               |
                    |           |        2      |
                    |      5    |               | 6
                    ------------------------------------------------------------
            JVM :
                메소드              스택                  힙영역
            - 클래스 정보      - 스레드마다 할당        - 객체의 인스턴스
                             - 함수가 실행되는 곳
            - static         - 지역변수



     */

    // 1. 메인 스레드 (메인 함수) 선언
    public static void main(String[] args) {

        // --------------------------- 단일 스레드 일때 ---------------------------------- // for 문 두 개를 같이 출력할 수 없다.
        // * java.aws :  java.ui
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        toolkit.beep(); // 띵하는 소리 발생시키는 함수.
        for(int i=1; i<=5; i++){
            toolkit.beep(); // 얘가 멀티라서 한번에 다 남.
            // for 문 5번 회전하는 것보다 띵소리가 느리기 때문에
            // 메인 스레드 잠시 멈추기
            try{
            Thread.sleep(500); // main 스레드를 0.5초간 일시정지
            }catch (InterruptedException e){
                System.out.println(e);
            }
                // Thread.sleep(밀리초) :(1/1000)초 만큼 일시정지
            // * 예외처리 : 혹시나 일시정지 도중에 다른쪽 스레드가 예외 발생시킬수 있을수 있으니까.
        }

        for(int i =1 ; i<=5 ; i++){
            System.out.print("띵");
            try{Thread.sleep(500);}catch (InterruptedException e){ }

        }
        // --------------------------- 단일 스레드 일때 end---------------------------------- //

        // --------------------------- 멀티 스레드 일때 ---------------------------------- //
        // 1. Runnable 인터페이스 구현객체 필요
            // 1. 구현 클래스
            // 2. 익명 구현 : 인터페이스가 new를 이용한 직접 추상메소드 재정의
                    // new 인터페이스명() { Override함수 }
        // 2. 구현객체를 Thread 생성자에 대입.
        Thread thread = new Thread(new Runnable() {
            // ============ 작업 스레드 구현 ============= //
            @Override
            public void run() { // 1. 작업 스레드가 실행하는 메소드.
                 Toolkit toolkit1 = Toolkit.getDefaultToolkit();
                 for(int i = 1 ; i <=5 ; i ++){
                     try{
                     Thread.sleep(500);
                     }catch (InterruptedException e){
                         System.out.println(e);
                     }
                 }
            }

        });
        thread.start();

        for(int i = 1; i <=5; i++){ // main 스레드가 코드를 실행]
            System.out.print("띵");
            try{
                Thread.sleep(500);}
            catch (InterruptedException e){

            }

        }

        // ========================== 멀티 스레드 2 구현클래스 일 떄 ======================== //
        // 1. Runnable 객체
        Runnable runnable = new 작업스레드();
        // 2. Thread 객체
        Thread thread1 = new Thread(runnable);
        // 작업스레드 실행
        thread1.start();

        for(int i = 1; i <=5; i++){ // main 스레드가 코드를 실행]
            System.out.print("띵");
            try{
                Thread.sleep(500);}
            catch (InterruptedException e){

            }

        }
        // ========================== 멀티 스레드 2 구현클래스 일 떄 end ======================== //
        // ========================== 멀티 스레드 2 Thread 상속 일 떄 ======================== //
        작업스레드2 작업스레드2 = new 작업스레드2();
        작업스레드2.start(); // 상속은 한 번 땡,
        // vs
        Thread thread2 = new Thread(){
            public void run(){
                for(int i = 1 ; i <=5 ; i ++){
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    toolkit.beep();
                    try{ Thread.sleep(500);}
                    catch (InterruptedException e){}
                }
            }
        };
        thread2.start();
        // ========================== 멀티 스레드 2 Thread 상속 end ======================== //

    }
}

/*
    [인터페이스 : 하나의 클래스에 여러개 인터페이스 구현도 가능.]

    구현객체(구현체) 란 : 추상메소드를 구현한 클래스의 객체
    - 클래스 implements 인터페이스{}
        1. 구현클래스 정의 : 클래스 implements 인터페이스{}
        2. 인터페이스명 변수명 = 구현클래스 new
    - 익명 구현객체
        1. 인터페이스명 변수명 = new 인터페이스명(){추상메소드 재정의}

        + Runnable 인터페이스
            1. run 메소드를 재정의한다. run()메소드는 생성된 새로운 작업 스레드가 처리할 행위/ 메소드/함수 하는 곳.
            2. Runnable runnable = new runnable(){재정의};
               Thread thread = new Thread(runnable);
               thread.start();

========================================================================================

    [상속  : 하나의 클래스에 하나의 클래스만 상속 가능. ]

    자식객체 : 부모클래스로부터 (필드/메소드) 상속/물려받은 클래스의 객체
    - 자식 객체
        1. 자식클래스 정의 : 클래스 extends 부모클래스{ 오버라이딩 재정의할거면 재정의}
        2. 부모/자식클래스 변수명 = new 자식클래스();
    - 익명 자식객체
        1. 부모/자식클래스 변수명 = new 부모클래스(){재정의}

      + Thread 클래스
           1. run 메소드를 재정의한다. run()메소드는 생성된 새로운 작업 스레드가 처리할 행위/ 메소드/함수 하는 곳.
           2. Thread thread = new 자식클래스()// 이거 안해도되나? >> {재정의};
              thread.start();

 */