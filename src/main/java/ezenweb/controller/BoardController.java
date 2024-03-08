package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import ezenweb.service.BoardService;
import ezenweb.service.FileService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board") // 공통 URL
public class BoardController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private FileService fileService;


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
    @PutMapping("/update.do")
    @ResponseBody
    public boolean doUpdateBoard(BoardDto boardDto){
        System.out.println("BoardController.doUpdateBoard");
        System.out.println("boardDto = " + boardDto);
        // 유효성 검사.
            // 1. 현재 로그인된 아이디 (세션)
        Object object = request.getSession().getAttribute("loginDto");
        if(object != null){
            String mid = (String)object;

            // 2. 현재 수정할 게시물의 작성자 아이디 (DB)
            boolean result = boardService.boardWriterAuth(boardDto.getBno(), mid); // 해당 세션 정보가 로그인한 사람이 작성한 글인지 체크
            if(result){
                return boardService.doUpdateBoard(boardDto); // 현재 수정할 게시물의 작성자 아이디
            }

        }
        return false;
    }

    // 5. 글 삭제 처리           /board/delete.do    DELETE      // 게시물 번호 필요
    @DeleteMapping("/delete.do")
    @ResponseBody
    public boolean doDeleteBoard(@RequestParam long bno){ // 얘는 쿼리스트링 입니다요잇 = @RequestParam
        System.out.println("BoardController.doDeleteBoard");
        // 유효성 검사.
        // 1. 현재 로그인된 아이디 (세션)
        Object object = request.getSession().getAttribute("loginDto");
        if(object != null){
            String mid = (String)object;

            boolean result = boardService.boardWriterAuth(bno, mid);
            // 2. 현재 수정할 게시물의 작성자 아이디 (DB)
            if(result) {
                return boardService.doDeleteBoard(bno);
            }
        }
        return false;
    }

    // 6. 올린 첨부파일 다운로드 처리
    // 함수의 3박자
    // ( 뭘 받을건지: 지금은 파일 하나만 받기에 파일명을 바로 받아도된다,
    // 뭘 리턴할건지,
    // 언제 쓸건지:http 요청 )
    @GetMapping("/file/download") // a태그이기 때문에 get매핑
    @ResponseBody // 제이슨으로 하겠다.
    public void getBoardFileDownload(String bfile){//왜 보이드인지?
        System.out.println("BoardController.getBoardFileDownload");
//        System.out.println("bfile = " + bfile);
        fileService.getBoardFileDownload(bfile);
        return;
    }

    // 7. 댓글 작성 ( brcontent, brindex, mno, bno )
    // 받아오는 방법 [ {상위 댓글}, {상위 댓글}, {상위 댓글} ]
    // [ {상위 댓글}, {상위 댓글}, {상위 댓글} ]
    @PostMapping("/reply/write.do")
    @ResponseBody
    public boolean postReplyWrite(@RequestParam Map<String, String> map){
        System.out.println("BoardController.postReplyWrite");
        System.out.println("map = " + map);
        // 1. 현재 로그인된 세셧 (톰캣서버, 자바프로그램 메모리 JVM) 호출
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){return false;} // 세션 없다(로그인 없다.)
        // 2. 형변환
        String mid = (String) object;
        // 3. mid 를 mno 찾아오기
        long mno = memberService.doGetLoginInfo(mid).getNo();
        // 4. map에 mno 넣기
        map.put("mno", mno+"");
        System.out.println("map = " + map);

        return boardService.postReplyWrite(map);
    }

    // 8. 댓글 출력 댓글 ( brno, brcontent , brindex , mno , bno , brindex )
    @GetMapping("/reply/do")
    @ResponseBody
    public List<Map<String,String>> getReplyDo( int bno ){
        System.out.println("BoardController.getReplyDo");
        return boardService.getReplyDo(bno);

    }


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
    @GetMapping("/update")
    public String getBoardUpdate(){
        return "ezenweb/board/update";
    }
}
