package ezenweb.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service // 이게 어차피 컴포넌트와 같다
// 해당 클래스를 스프링 컨테이너에 빈 등록한다.
public class FileService {
    // Controller : 중계자 역할 (HTTP 매핑, HTTP요청/응답, 데이터 유효성검사) 등등
    // Service : Controller <-- Service(비즈니스로직) -->  Dao , Controller <---> Service(비즈니스로직)


    // 어디에(PATH) 누구를(파일객체)
    String uploadPath =  "C:\\Users\\504\\Desktop\\ezen2023B_spring\\build\\resources\\main\\static\\img\\";

    // 1. 업로드 메소드
    public String fileUpload(MultipartFile multipartFile){
        String uuid = UUID.randomUUID().toString(); // 산수생성 UUID!!
        System.out.println("uuid = "+ uuid);
        // * 파일 이름 조합하기 : 새로운 식별이름과 실제 파일 이름
        // 식별키와 실제 이름 구분 : 왜? 나중에 쪼개서 구분하려고 [ 다운로드시 식별키 빼고 제공하려고 ]
        // 혹시나 파일 이름이 구분문자 가 있을 경우 기준이 깨짐.
        // .replaceAll() : 문자열 치환/교체
        String filename = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_","-"); //이걸 다 바꾸게.
//        memberDto.setUuidFile(filename); // 어차피 파일은 대개 고정이므로 filename만 있으면 됨,
// 얘는 할 수가 없음!! multipartFile로 바꿀수 없음


        // 1. 첨부파일 업로드 하기. [업로드란: 클라이언트의 바이트(대용량/파일)을 서버로 복사]
        // 1. 첨부파일을 저장할 경로
        // File 클래스 : 파일 관련된 메소드 제공
        // new File(파일경로)
//        File file = new File("c:\\java\\"+첨부파일.getOriginalFilename());

        File file = new File(uploadPath+filename);
        System.out.println("file ="+file); // c:\java\file명
        System.out.println("file.exists() ="+file.exists()); // 파일 존재 안하고 true/false로 나옴.

        // 2. [무엇을] 첨부파일 객체
        // transferTo(경로) 경로: Path나 File 클래스로 줘야한다고 자바가 정해놓음.
        try{ multipartFile.transferTo(file);}
        catch (Exception e){
            System.out.println(e);
            return null;
        }

        return filename;
    }

    // 2. 다운로드 메소드
}
