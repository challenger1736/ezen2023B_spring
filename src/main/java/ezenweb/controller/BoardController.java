package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import ezenweb.service.BoardService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board") // 공통 URL
public class BoardController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;

    @Autowired
    private BoardService boardService;


    // 1. 글쓰기 처리            /board/write.do POST            // Dto 필요
    @PostMapping("/write.do")
    @ResponseBody
    public long doPostBoardWrite(BoardDto boardDto){
        System.out.println("BoardController.doPostBoardWrite");
        // 1. 현재 로그인된 세션(브라우저 마다 톰캣서버내(자바, JVM) 저장소) 호출
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){return -2;} // 세션없다 비로그인,
        // 2.
        String mid = (String) object;
        // 3. mid를 mno로 바꾸기
        long mno = memberService.doGetLoginInfo(mid).getNo();

        // 4. 작성자 번호 대입
        boardDto.setMno(mno);
        return boardService.doPostBoardWrite(boardDto);
    }
    // 2. 전체 글 출력 호출       /board.do   GET -- 호출 이니까    페이징처리, 검색 기능
    @GetMapping("/do")
    @ResponseBody
    public BoardPageDto doGetBoardViewList(@RequestParam int page,@RequestParam int pageBoardSize,
                                           @RequestParam int bcno,@RequestParam("key") String keyString,
                                           @RequestParam("keyword") String keywordString){ // 매개변수 : 현재 페이지
        System.out.println("BoardController.doGetBoardViewList");
        return boardService.doGetBoardViewList(page, pageBoardSize, bcno, keyString, keywordString);
    }
    // 3. 개별 글 출력 호출        /board/view.do GET -- 호출이니까 // 게시물 번호 필요
    @GetMapping("/view.do")
    @ResponseBody
    public BoardDto doGetBoardView(@RequestParam int bno){ // 쿼리스트링, JSON을 받을때는 @RequestBody
        System.out.println("BoardController.doGetBoardView");
        return boardService.doGetBoardView(bno);
    }

    // 4. 글 수정 처리           /board/update.do   PUT          // Dto 필요

    // 5. 글 삭제 처리           /board/delete.do    DELETE      // 게시물 번호 필요

    // =======================머스테치는 컨트롤에서 뷰 템플렛을 반환======================== //

    // 1. 글쓰기 페이지 이동        /board/write GET -- 메소드가 달라야 같은 URL로 실행 가능함.
    @GetMapping("/write")
    public String getBoardWrite(){
        return "ezenweb/board/write";
    }

    // 2. 게시판 페이지 이동        /board GET      -- 페이지 전환 (A 태그) GET 방식 페이지는 GET라고 보면 됨,
    @GetMapping("")
    public String getBoard(){
        return "ezenweb/board/board";
    }

    // 3. 게시판 상세 페이지 이동     /board/view   GET
    @GetMapping("/view")
    public String getBoardView(int bno){
        return "ezenweb/board/view";
    }

    // 4. 글 수정 페이지 이동       /board/update GET
}
