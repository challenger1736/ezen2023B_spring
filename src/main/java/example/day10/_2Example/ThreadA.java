package example.day10._2Example;

public class ThreadA extends Thread{
    private WorkObject workObject;

    public ThreadA(WorkObject workObject){
        setName("ThreadA");
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for(int i=0; i<1000 ; i++){workObject.methodA();}

    }
}
