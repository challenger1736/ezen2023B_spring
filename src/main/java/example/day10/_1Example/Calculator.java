package example.day10._1Example;

public class Calculator {
    private int memory;

    public  int getMemory(){//게터
        return memory;
    }

    public synchronized void setMemory1(int memory){//세터
        // 동기화 synchronized : 여러 스레드가 해당 메소드/블록 호출 했을 때 순서 매기기(락을 건다.) // 동기화 안하면 값 둘 다 50으로 나옴
            // 컬렉션 프레임 워크 : List(Vector) , map(hashTable)
        this.memory = memory;
        try{Thread.sleep(2000);}catch (InterruptedException e){}
        System.out.println(Thread.currentThread().getName()+ " : "+ this.memory);
    }

}
