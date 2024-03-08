package ezenweb.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.UUID;

@Service // 이게 어차피 컴포넌트와 같다
// 해당 클래스를 스프링 컨테이너에 빈 등록한다.
public class FileService {
    // Controller : 중계자 역할 (HTTP 매핑, HTTP요청/응답, 데이터 유효성검사) 등등
    // Service : Controller <-- Service(비즈니스로직) -->  Dao , Controller <---> Service(비즈니스로직)

    @Autowired
    private HttpServletRequest request; // HTTP로 요청을 보낸 정보가 담긴 객체 ( 매개변수와 브라우저 정보 -> 세션 )
    @Autowired
    private HttpServletResponse response; // HTTP로 응답을 보낼 정보와 기능/메소드를 가지고 있는 객체



    // 어디에(PATH) 누구를(파일객체)
    String uploadPath =  "C:\\Users\\504\\Desktop\\ezen2023B_spring\\build\\resources\\main\\static\\img\\";

    // 1. 업로드 메소드
    public String fileUpload(MultipartFile multipartFile){ // MultipartFile은 업로드(매핑)잡을때 지원해주는 느낌.
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
        try{ multipartFile.transferTo(file);} //실제로 서버에 저장하는 일.
        catch (Exception e){
            System.out.println(e);
            return null;
        }

        return filename;
    }

    // 2. 다운로드 메소드

    public void getBoardFileDownload(String bfile){
        System.out.println("FileService.getBoardFileDownload");
        System.out.println("bfile = " + bfile);
        // 2-1. 다운로드 할 파일의 경로와 파일명 연결
        String downloadPath = uploadPath+bfile;
        System.out.println(downloadPath);
        // 2-2. 해당 파일을 객체로 가져오기 [File 클래스는 해당 경로의 파일을 객체로 가져와서 다양한 메소드/기능 제공]
        File file = new File(downloadPath);
        System.out.println(file);

        // 2-3. exists : 해당 경로의 파일이 있다 없다
        if(file.exists()){
            System.out.println("첨부파일 있다.");
            // HTTP가 파일 전송하는 방법 : 파일을 바이트 전송해야함
            try{
                // HTTP로 응답시 응답방법(다운로드 모양)에 대한 정보(HTTP Header)를 추가
                    // url은 한글 지원안한다 URLEncoder.encode(받을것 ,"utf-8"));
                    // 첨부파일 다운로드 형식 : 브라우저 마다 형식이 다르다(웨일, 크롬 등등 다 다운로드 받는 프론트는 다 브라우저에서 알아서 제공)
                response.setHeader("Content-Disposition",
                        "attachement;filename="+ URLEncoder.encode(bfile.split("_")[1],"utf-8"));
            // 1. 해당파일을 바이트로 불러온다. // 파일 바이트 불러오기
                // 1-1 파일 입력 스트림(바이트가 다니는 통로) 객체 생성
            BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file)); // 제일 고전적인 방법 Buffered
                // 1-2 바이트 배열(고정) vs 리스트
                // 고정이라 배열을 쓴다.
                    // 1. 파일의 사이즈/크기/용량 (파일의 크기만큼 바이트 배열 선언하기 위해)
                    long fileSize = file.length();
                    // 2. 해당 파일의 사이즈 만큼 바이트 배열 선언
                    byte[] bytes = new byte[(int)fileSize];

                // 1-3 입력(불러오기) 메소드
                    // 입력스트림.read(내보내기할바이트배열) == 바이트 하나씩 읽어오면서 바이트 배열 저장 => 바이트 배열 필요함.
                fin.read(bytes);

                // 1-4. (확인용) 읽어온 파인의 바이트가 들어있다.
                System.out.println("Arrays.toString(bytes) = "+ Arrays.toString(bytes));
                System.out.println("bytes = " + bytes);
//
            // 2. 불러온 바이트를 HTTP response를 이용한 바이트로 응답한다.
                // 2-1. HTTP 응답 스트림 객체 생성
            BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());
                // 2-2. 응답스트림.write(내보내기할바이트배열) 하나씩 바이트를 읽어와서 해당 바이트 배열에 저장해주는 함수.
                fout.write(bytes);


                // ------------- 버퍼 초기화(안전하게)
                fin.close();    // 스트림 닫기
                fout.close();   // 스트림 닫기
            }catch(Exception e){
                System.out.println(e);
            }
        }else{
            System.out.println("첨부파일 없다.");
        }
        return;
    }

    // 3. 파일 삭제 [ 게시물 삭제시 만약에 첨부파일 있으면 첨부파일도 같이 삭제, 게시물 수정시 첨부파일 변경하면 기존 첨부파일 삭제 ]
    public boolean fileDelete(String bfile){
        // 1. 경로와 파일을 합쳐서 파일 위치 찾기
        String filePath = uploadPath+bfile;
        // 2. File클래스의 메소드 활용
            // .exists()    : 해당 파일이 존재 여부
            // .length()    : 해당 파일의 크기/용량 바이트 단위
            // .delete()    : 해당 파일 삭제

        File file = new File(filePath);
        if(file.exists()){// 만약에 해당 경로에 파일이 존재하면 삭제
            file.delete();
            return true;
        }
        return false;
    }
}

/*
    비트 : 0 또는 1
    바이트 : 8비트 01010101 비트가 8개 모임
        문자, 이미지
            <파일에서는 바이트가 최소 단위>


 */