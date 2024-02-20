package example.day08;

import java.awt.*;

public class 작업스레드 implements Runnable{ // implements 는 여러개(여러 인터페이스) 가능
    // Ctrl + space 로 Override 할 놈 찾기
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
