package ezenweb.service;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MemberService {
    @Autowired
    private FileService fileService; // 외부 서비스
    @Autowired
    private MemberDao memberDao; // 외부 리포지토리

    @Autowired
    private EmailService emailService;

    // 1. 회원가입 서비스
    public boolean doPostSignup(MemberDto memberDto){
        // 1. 파일 처리
            // 만약에 첨부파일이 존재하면
        String fileName = "defaultImg.jpg";
        System.out.println("memberDto = " + memberDto);
        System.out.println("memberDto.getImg() = " + memberDto.getImg()); //비어있는 객체가 무조건 있다 / 프로필 사진 등록안하면
        System.out.println("memberDto.getImg().isEmpty() = " + memberDto.getImg().isEmpty());
        if(!memberDto.getImg().isEmpty()) {
            fileName = fileService.fileUpload(memberDto.getImg());
            if(fileName != null){// 업로드 성공했으면
                // 2. DB 처리
                // dto에 업로드 성공한 파일명을 대입한다.
//                memberDto.setUuidFile(fileName);
                // dao
            }else{ // 실패하면,
                return false;
            }
        }
        memberDto.setUuidFile(fileName); // img자체가 아닌 경로를 저장.
        boolean result = memberDao.doPostSignup(memberDto);
        // * 이메일 테스트
//        if(result){ emailService.send(); }
        return result; // 사진이 있든 없든 DB 처리는 해야함,

    }

    // 2. 로그인 서비스

    // 3. 회원정보 요청 서비스
    public MemberDto doGetLoginInfo(String id){
        // 1. DAO 호출
        return memberDao.doGetLoginInfo(id);
    }

    // 4.  ============= 아이디 중복 체크 요청 =============
    public boolean doGetFindIdCheck( String id){
        return memberDao.doGetFindIdCheck(id);
    }

}
