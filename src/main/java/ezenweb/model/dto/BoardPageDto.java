package ezenweb.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder // 생성자의 단점을 보완한 라이브러리 함수 제공.
public class BoardPageDto {
    // 1. 현재 페이지
    private int page;
    // 2. 총 게시물 수
    private int totalPage;
    // 3. 페이지 버튼의 시작번호
    private int startBtn;
    // 4. 페이지 버튼의 끝번호
    private int endBtn;

    // 5. 총 게시물 수
    private int totalBoardSize;

    // 실제 내용 //
    private List<BoardDto> list;

}
