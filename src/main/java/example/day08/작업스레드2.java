package example.day08;

import java.awt.*;

public class 작업스레드2 extends Thread{ // 상속은 한 개(하나의 클래스)만 가능.(부모가 하나)
    @Override
    public void run() {
        for(int i = 1 ; i <=5 ; i ++){
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.beep();
            try{ Thread.sleep(500);}
            catch (InterruptedException e){}
        }

    }
}
