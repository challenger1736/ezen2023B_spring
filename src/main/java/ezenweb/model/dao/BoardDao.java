package ezenweb.model.dao;

import ezenweb.model.dto.BoardDto;
import ezenweb.service.BoardService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardDao {




    public boolean doPostBoardWrite(BoardDto boardDto){
        System.out.println("BoardDao.doPostBoardWrite");

        return true;
    }
    // 2. 전체 글 출력 호출       /board.do   GET -- 호출 이니까    페이징처리, 검색 기능

    // 3. 개별 글 출력 호출        /board/view.do GET -- 호출이니까 // 게시물 번호 필요

    public BoardDto doGetBoardView( int bno){
        System.out.println("BoardDao.doGetBoardView");
        return null;
    }

    // 4. 글 수정 처리           /board/update.do   PUT          // Dto 필요

    // 5. 글 삭제 처리           /board/delete.do    DELETE      // 게시물 번호 필요

    // =======================머스테치는 컨트롤에서 뷰 템플렛을 반환======================== //

    // 1. 글쓰기 페이지 이동        /board/write GET -- 메소드가 달라야 같은 URL로 실행 가능함.

    // 2. 게시판 페이지 이동        /board GET      -- 페이지 전환 (A 태그) GET 방식 페이지는 GET라고 보면 됨,

    // 3. 게시판 상세 페이지 이동     /board/view   GET

    // 4. 글 수정 페이지 이동       /board/update GET

}
