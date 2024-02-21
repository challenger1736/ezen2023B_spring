package example.day09;

public class Example2 {
    public static void main(String[] args) {

        // 1. 자식 스레드 객체 생성
        SumThread sumThread = new SumThread();
            // sum = 0; 왜?? 객체 생성시 필드 초기값
        // 2. 자식 스레드 실행
        sumThread.start();

        // 3. 작업스레드가 run() 메소드를 처리하기 전에 아래 실행문 처리.
        System.out.println("1 sumThread.getSum() : " + sumThread.getSum());
//        System.out.println("1 sumThread.getSum() : " + sumThread.getSum());

        try {
            sumThread.join(); // main스레드와 sumThread스레드가 JOIN
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("2 sumThread.getSum() : " + sumThread.getSum());


        WorkThread workThreadA = new WorkThread("workThreadA");
        WorkThread workThreadB = new WorkThread("workThreadB");
        workThreadA.start();
        workThreadB.start();

        try{Thread.sleep(5000);}catch (InterruptedException e){}
        workThreadA.work = false;

        try{Thread.sleep(10000);}catch (InterruptedException e){}
        workThreadA.work = true;


    }
}
