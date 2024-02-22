package ezenweb.controller;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController // 이거 쓰면 자동으로 ResponseBody됨(데이터/값)을 보내게 됨.
public class MemberController {
    // 1단계. V<---------->C 사이의 HTTP 통신 방식 설계
    // 2단계. Controller mapping 체크 (API Tester하고 3단계로 넘어 갈 예정)
    // 3단계. Controller request 매개변수 체크후 매핑(soutp)
            // ----------------------Dao, Service ------------------------//
    // 4단계. 응답 : 1. text/html;  vs 2. Application/JSON

    // 1. =================== 회원가입 처리 요청 ====================
    @PostMapping("member/signup") // http://localhost:8080/member/signup
    @ResponseBody // 응답 방식 // Content-Type:	application/json vs 아니면 뷰 반환(html 혹은 뷰 템플렛이 만들어낸 html)
    public boolean doPostSignup(MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        /*
        params(매개변수)
        {
        id="아이디", pw="비밀번호", name="이름", email="이메일",
        phone="전화번호", img="프로필사진"
        }

         */
        MemberDao.getInstance().doPostSignup(memberDto);

        System.out.println("MemberController.signup"); // API로 통신하고, 되는지 안되는지 확인용. 404 오류 뜨더라도 이건 나옴

        boolean result = true;
        return result;
    }
    // 2. =================== 로그인 처리 요청 =====================
    @PostMapping("member/login")
    @ResponseBody // 응답 방식 // Content-Type:	application/json vs 아니면 뷰 반환(html 혹은 뷰 템플렛이 만들어낸 html)
    public boolean doPostLogin(LoginDto loginDto){
        System.out.println("loginDto = " + loginDto);
        /*
        {
        id="아이디", pw="비밀번호"
        }
         */
        System.out.println("MemberController.login"); // API로 통신하고, 되는지 안되는지 확인용. 404 오류 뜨더라도 이건 나옴
        boolean result = true;
        return result;
    }

    // 3. =================== 회원가입 페이지 요청 =====================
    @GetMapping("member/signup") // 같은 주소라도 메소드가 Get이냐 Post냐 Put이냐 Delete냐에 따라 다르게, 쓰는 것이 됨!! 신기하네,
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "ezenweb/signup";
    }
    // 4. =================== 로그인 페이지 요청 =====================
    @GetMapping("member/login")
    public String viewLogin(){
        System.out.println("MemberController.viewLogin");
        return "ezenweb/login";
    }

    // 5. 회원 탈퇴 - 혼자 해보기
    @PostMapping("member/delete")
    @ResponseBody
    public boolean doPostDelete(int no){
        System.out.println("MemberController.doPostDelete");
        boolean result = true; // 다오처리 + 로그인중인 것 null로 만들기 구현해야함,
        return result;
    }

    // 6. 회원 정보 수정 - 혼자 해보기 JSON 으로 해보려고하기



    @GetMapping("member/update")
    public String viewUpdate(MemberDto memberDto, Model model){
        System.out.println("MemberController.viewUpdate");
        MemberDto result = new MemberDto(); // 다오처리된 놈 받아오기.
        model.addAttribute("Myinfo", result);
        return "ezenweb/update";
    }

    @PostMapping("member/update")
    public boolean doPostUpdate(){ // 로그인 중인 no 변수로 받기.
        System.out.println("MemberController.doPostUpdate");
        boolean result = true; // 다오 처리 되고 update된 값.
        return result;
    }

}
