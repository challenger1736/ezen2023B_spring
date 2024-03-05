package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
//@Builder // 이런게 있다. 생성자의 단점보완용 ( 유연성 )

public class BoardDto {
    long bno;           // 번호
    String btitle;      // 제목
    String bcontent;    // 내용
    String bfile;       // 첨부파일
    int bview;          // 조회수
    String bdate;       // 작성일
    long mno;           // 작성자 번호
    long bcno;          // 카테고리

    MultipartFile uploadfile;
    
    // + 전체 출력시 필요한 필드
    String id;
    String img;
}

/*

    -용도에 따라 다양한 DTO가 존재할 수 있다.
    -하나의 DTO에 서로 다른 용도로 사용.

    글쓰기 용
        - 입력 받기 : btitle, bcontent, bfile>> uploadfile 멀티파트 써야해서,, bcno
        - 입력 안 받기 : bno - 자동
                        bview - 기본값 0
                        bdate - 기본값 현재 날짜
                        mno - 로그인 세션에서 쓸려고(*세션)


    개별 글출력 용
        - 출력용 : bno btitle bcontent bfile bview bdate mno bcno null(업로드파일)
        
    개별 글출력 용
        - 출력용 : bno btitle bcontent bfile bview bdate mno bcno null(업로드파일) + id + img

    - 생성자 단점/규칙 :  매개변수의 순서, 개수를 맞춰야함 ( 유연성이 떨어진다. )
    - 빌더 패턴 : @Builder
 */