package ezenweb.controller;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
//@RestController // 이거 쓰면 자동으로 ResponseBody됨(데이터/값)을 보내게 됨.
public class MemberController {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberService memberService;

    // 1단계. V<---------->C 사이의 HTTP 통신 방식 설계
    // 2단계. Controller mapping 체크 (API Tester하고 3단계로 넘어 갈 예정)
    // 3단계. Controller request 매개변수 체크후 매핑(soutp)
            // ----------------------Dao, Service ------------------------//
    // 4단계. 응답 : 1. text/html;  vs 2. Application/JSON

    // 1. =================== 회원가입 처리 요청 ====================
    @PostMapping("/member/signup") // http://localhost:8080/member/signup
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
        // 첨부파일 MulitpartFile 타입
        MultipartFile 첨부파일 = memberDto.getImg();
        System.out.println(첨부파일); // 첨부파일의 객체의 주소
        System.out.println(첨부파일.getSize()); // 첨부파일 용량 : ~~~~바이트
        System.out.println(첨부파일.getContentType()); // image/png : 첨부파일의 확장자
        System.out.println(첨부파일.getOriginalFilename()); // logo.png : 첨부파일의 이름(확장자 포함)
        System.out.println(첨부파일.getName()); // img : form input name

        // 서버에 업로드 했을때 설계
            // 1. 여러 클라이언트가 동일한 파일명으로 서버[1명]에게 업로드 했을 때 ===> 식별 깨짐
                // 식별이름(주로) : 1. 날짜조합 + 식별번호
            // 2. 클라이언트 화면 표시
                // 업로드 경로 : 아파치 톰캣( static )을 해야 됨, 아니면 서버 리빌드(재시작)해야함.
        /*

            클라이언트 ------------> 톰캣(서버) <------build------- 개발자
                         요청                       컴파일         코드
                     <-----------

        */

        // * 업로드 할 경로 설정 (내장 톰캣의 경로)
//        String uploadPath =  "C:\\Users\\504\\Desktop\\ezen2023B_spring\\build\\resources\\main\\static\\img";
//        String uuid = UUID.randomUUID().toString(); // 산수생성 UUID!!
//        System.out.println("uuid = "+ uuid);
//        // * 파일 이름 조합하기 : 새로운 식별이름과 실제 파일 이름
//            // 식별키와 실제 이름 구분 : 왜? 나중에 쪼개서 구분하려고 [ 다운로드시 식별키 빼고 제공하려고 ]
//            // 혹시나 파일 이름이 구분문자 가 있을 경우 기준이 깨짐.
//                // .replaceAll() : 문자열 치환/교체
//        String filename = uuid+"_"+memberDto.getImg().getOriginalFilename().replaceAll("_","-"); //이걸 다 바꾸게.
//        memberDto.setUuidFile(filename); // 어차피 파일은 대개 고정이므로 filename만 있으면 됨,
//
//        // 1. 첨부파일 업로드 하기. [업로드란: 클라이언트의 바이트(대용량/파일)을 서버로 복사]
//            // 1. 첨부파일을 저장할 경로
//                // File 클래스 : 파일 관련된 메소드 제공
//                // new File(파일경로)
////        File file = new File("c:\\java\\"+첨부파일.getOriginalFilename());
//
//        File file = new File(uploadPath+filename);
//        System.out.println("file ="+file); // c:\java\file명
//        System.out.println("file.exists() ="+file.exists()); // 파일 존재 안하고 true/false로 나옴.
//
//            // 2. [무엇을] 첨부파일 객체
//                // transferTo(경로) 경로: Path나 File 클래스로 줘야한다고 자바가 정해놓음.
//        try{ memberDto.getImg().transferTo(file);}
//        catch (Exception e){
//            System.out.println(e);
//        }
        // 이 부분 Service로 넘기기( 자주 쓸 예정이라 )

//        System.out.println("MemberController.signup"); // API로 통신하고, 되는지 안되는지 확인용. 404 오류 뜨더라도 이건 나옴
//
//        boolean result = MemberDao.getInstance().doPostSignup(memberDto);
//        System.out.println(result);
        return memberService.doPostSignup(memberDto);
    }

    // * Http 요청 객체
    @Autowired
    private HttpServletRequest request;

    // 2. =================== 로그인 처리 요청 =====================
    @PostMapping("/member/login")
    @ResponseBody // 응답 방식 // Content-Type:	application/json vs 아니면 뷰 반환(html 혹은 뷰 템플렛이 만들어낸 html)
    public boolean doPostLogin(LoginDto loginDto){
        System.out.println("loginDto = " + loginDto);
        /*
        {
        id="아이디", pw="비밀번호"
        }
         */
        System.out.println("MemberController.login"); // API로 통신하고, 되는지 안되는지 확인용. 404 오류 뜨더라도 이건 나옴
        boolean result = MemberDao.getInstance().doPostLogin(loginDto); // Dao 처리
        if(result){ // 로그인 성공이면,
            // 세션에 저장할 내용물들을 구성( 식별키 만 넣어도된다. )
            request.getSession().setAttribute("loginDto",loginDto.getId()); // loginDto id 넣기

        }
        
        //  * 로그인 성공시
            // 세션 저장소 : 톰캣 서버에 *브라우저 마다*의 메모리 할당
            // 세션 객체 타입 : Object(여러가지의 타입들을 저장하려고)
            // 1. HTTP 요청 객체 호출 HttpServletRequest
            // 2. HTTP 세션 객체 호출 .getSession()
            // 3. Http 세션 객체에 데이터 저장 .setAttribute("세션속성명", 데이터); //  -- 자동 형변환 (자-->부)
            // 4. Http 세션 객체에 데이터 호출 .getAttribute("세션속성명"); //         -- 나올 때는 Object라서 강제 형변환이 필요함 (부-->자)
            // 5. Http 세션 객체 초기화 .invalidate()


        return result;
    } // f end

    // 2-2. =================== 로그인 여부 확인 요청 =====================
    @GetMapping("/member/login/check")
    @ResponseBody
    public String doGetLoginCheck(){
        //* 로그인 여부 확인 = HTTP 세션 객체가 있다 없다 확인하는것과 같다.
            // 1-> http 요청 객체 호출, 2->http세션 객체 호출, 3-> http 세션 데이터 호출
        // null 형변환이 불가능하기 때문에 유효성 검사
        String loginDto = "";
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if(sessionObj!=null){ loginDto = (String)sessionObj;}
        // 만약에 로그인했으면(세션에 데이터가 있으면) 강제형변환을 통해 데이터 호출 아니면 0
        return loginDto;
    }

    // 2-3. =================== 로그아웃 요청 =====================
    @GetMapping("/member/logout")
    @ResponseBody
    public boolean doGetLoginOut(){
        // 1. 로그인 관련 세션 호출 (초기화)
            // 1. 모든 세션 초기화(모든 세션의 속성이 초기화 -> 로그인 세션외 다른 세션도 초기화되므로 고려를 해야함.)
        request.getSession().invalidate(); // 현재 요청 보낸 브라우저의 모든 세션 초기화.
            // 2. 특정 세션속성 초기화
//        request.getSession().setAttribute("loginDto", null); // 얘는 세션의 해당 속성명만 지우는 것.
        return true; // 로그아웃 성공시 대다수가 로그인 페이지 이동

    }

    // 3. =================== 회원 정보 요청 (로그인 된 회원 요청) ==================== // 패스워드 제외한 개인정보 불러오기
    @GetMapping("/member/login/info")
    @ResponseBody
    public MemberDto doGetLoginInfo(String id){
        return memberService.doGetLoginInfo(id);

    }


    // 3. =================== 회원가입 페이지 요청 =====================
    @GetMapping("/member/signup") // 같은 주소라도 메소드가 Get이냐 Post냐 Put이냐 Delete냐에 따라 다르게, 쓰는 것이 됨!! 신기하네,
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "ezenweb/signup";
    }
    // 4. =================== 로그인 페이지 요청 =====================
    @GetMapping("/member/login")
    public String viewLogin(){
        System.out.println("MemberController.viewLogin");
        return "ezenweb/login";
    }

    // 5. 회원 탈퇴 - 혼자 해보기
    @PostMapping("/member/delete")
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

    @PostMapping("/member/update")
    public boolean doPostUpdate(){ // 로그인 중인 no 변수로 받기.
        System.out.println("MemberController.doPostUpdate");
        boolean result = true; // 다오 처리 되고 update된 값.
        return result;
    }

}
