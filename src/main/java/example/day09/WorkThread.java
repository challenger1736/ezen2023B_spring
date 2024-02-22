package example.day09;

public class WorkThread extends Thread{
    //필드
    public boolean work = true;

    //생성자
    public WorkThread(String name){
        setName(name); // 매개변수로 스레드 이름 변경
    }

    //메소드
    @Override
    public void run(){
        while(true){
            try{Thread.sleep(1000);}catch (Exception e){}

            if(work){
                System.out.println(getName() + " : 작업처리");
            } else {
                System.out.println("1"); // 점유 -> 처리 -> 대기
                Thread.yield(); // 점유 -> 양보 -> 대기
                System.out.println("2");
            }
        }
    }
}
