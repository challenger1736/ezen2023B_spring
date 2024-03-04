package ezenweb.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class BoardDto {
    long bno;           // 번호
    String btitle;      // 제목
    String bcontent;    // 내용
    String bfile;       // 첨부파일
    int bview;          // 조회수
    String bdate;       // 작성일
    long mno;           // 작성자 번호
    long bcno;          // 카테고리
}

/*
    글쓰기 용
        - 입력 받기 : btitle, bcontent, bfile, bcno
        - 입력 안 받기 : bno - 자동
                        bview - 기본값 0
                        bdate - 기본값 현재 날짜
                        mno - 로그인 세션에서 쓸려고(*세션)


    글출력 용
 */