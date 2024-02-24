package example.day10._2Example;

public class ThreadB extends Thread{
    private WorkObject workObject;

    public ThreadB(WorkObject workObject){
        setName("ThreadB");
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for(int i=0; i<1000 ; i++){workObject.methodB();}
    }
}
