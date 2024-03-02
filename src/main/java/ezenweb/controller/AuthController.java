package ezenweb.controller;

import ezenweb.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController // @Controller + @ResponseBody
@RequestMapping("/auth") // 해당 클래스의 매핑 ( 주로 해당 클래스내 함수들의 매핑주소중에 공통 )
public class AuthController {
    // 세션이란
    // 아파치 톰캣 안에서 지원하는 어떠한 저장소 : 브라우저 마다의 저장소 , 보안이 좀 낫다
    // 세션 객체 호출
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private EmailService emailService;

    // 1. email 인증 요청
    @GetMapping("/email/req")
    public boolean getEmailReq(@RequestParam String email){
        System.out.println("AuthController.getEmailReq");
        //인증요청을 하면 1.난수를 이용한 6자리 인증코드 발급
//        Math.random() 또는 Random 클래스
        Random random = new Random();
        String ecode = "";
        for(int i = 1 ; i <= 6; i++ ){
            ecode += random.nextInt(10); //정수 난수 생성 // bound 10까지(미만) // .nextInt(~) : ~미만 + 시작번호
            //문자로 할꺼면 (char)random.nextInt(~~까지)+65 // 유니코드
        }
        System.out.println("ecode = " + ecode);
        // 2. 인증코드를 http 세션에 특정시간만큼 인증코드 보관
            // 2-1. 세션에 속성 추가해서 발급된 인증코드 대입하기
        request.getSession().setAttribute("ecode", ecode);
            // 2-2. 세선에 생명주기 추가하기 .setMaxInactiveInterval
        request.getSession().setMaxInactiveInterval(10); // 초 기준 // test 라서 10초 동안 세션유지하고 10초후 삭제
        // 3. 발급된 인증코드를 인증할 이메일로 전송
        emailService.send(email,"EZEN웹 WEB 인증코드", "인증코드 : ["+ecode+"]입니다");
        return true;
    }

    // 2. email 인증 확인
    @GetMapping("/email/check")
    public boolean getEmailCheck(@RequestParam String ecodeinput){
        System.out.println("AuthController.getEmailCheck");
        
        // 1. http 세션에 보관했던 인증코드를 꺼내서
            // 1-1. 세션 속성 호출 // (이게 null)일수도 있는데 null이면 (String)null이 안되므로
        Object object = request.getSession().getAttribute("ecode");
            // 1-2. 만약에 세션 속성이 존재하면
        if(object !=null){
           String ecode = (String)object;
            if(ecode.equals(ecodeinput)){
                return true;
            }
        }

//        String ecode = (String) request.getSession().getAttribute("edode");

//        instanceof 내가 만든게 아니면 String인지 모르니까 instanceof 로 확인하기
        // 2. 입력받은 인증코드와 생성된 인증코드와 일치여부
            // 1. 발급된 인증코드와 입력받은 인증코드와 같으면

            return false;


    }
}
