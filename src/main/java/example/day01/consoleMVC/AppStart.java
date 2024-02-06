package example.day01.consoleMVC;

public class AppStart { // 얘네는 웹으로 바뀌는 애들
    public static void main(String[] args) {
//        TodoDao todoDao = new TodoDao();
        new MainView().home();
        //MainView.home(); // 메소드가 스태틱이면
        //MainView.getInstance().home();// 싱긑톤

        // IOC 제어역전, DI 의존성 주입 중요내용 면접때도 많이 나옴!!
        // 직접 객체를 안만들고 스프링이 알아서 해줌.

    }
}
