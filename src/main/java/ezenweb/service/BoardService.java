package ezenweb.service;

import ezenweb.model.dao.BoardDao;
import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Service
public class BoardService { // Controller 에서 매핑이랑 어노테이션 몇개 지우고 Ctrl C, Ctrl V

    @Autowired
    private BoardDao boardDao;
    @Autowired
    private  FileService fileService;

    public long doPostBoardWrite(BoardDto boardDto){
        System.out.println("BoardService.doPostBoardWrite");

        //1. 첨부파일 처리
            // 첨부파일이 존재하면
        if(!boardDto.getUploadfile().isEmpty()){
            String fileName = fileService.fileUpload(boardDto.getUploadfile()); // null이나 파일명을 준다.
            if(fileName != null){ // 업로드 성공했으면
                boardDto.setBfile(fileName); // db 저장할 첨부파일 명 대입
            }else{ // 업로드 문제 발생하면 글쓰기 취소
                return -1;
            }
        }
        return boardDao.doPostBoardWrite(boardDto);
    }
    // 2. 전체 글 출력 호출       /board.do   GET -- 호출 이니까    페이징처리, 검색 기능
    public BoardPageDto doGetBoardViewList(int page, int pageBoardSize, int bcno, String keyString, String keywordString){
        System.out.println("BoardService.doGetBoardViewList");
        // 페이지처리시 사용할 SQL 구문 : limit 시작레코드 번호(0부터), 출력개수
        // 1. 페이지당 게시물을 출력할 개수(3개)
//        int pageBoardSize = 3;

        // 2. 페이지당 게시물을 출력할 시작 레코드 번호.
        int startRow = (page-1)*pageBoardSize;
        // 3. 총 페이지 수
            // 1. 전체 게시물 수
        int totalBoardSize = boardDao.getBoardSize(bcno, keyString, keywordString);
            // 2. 총 페이지수 계산 (나머지 값 없으면 그대로, 나머지 있으면 +1)
        int totalPage = totalBoardSize % pageBoardSize == 0 ?
                totalBoardSize/pageBoardSize : totalBoardSize/pageBoardSize+1;

        // 4. 게시물 정보 요청
        List<BoardDto> list = boardDao.doGetBoardViewList(startRow, pageBoardSize, bcno, keyString, keywordString);

        // 5. 페이징 버튼 개수
            // 1. 페이지버튼 최대 개수
        int btnSize = 3; // 3개씩
            // 2. 페이지 버튼 시작번호 1페이지일때 1~3 1%3 = 1 > 1~3이 나와야함
                                // 2페이지일때 1~3  2%3 = 2 > 1~3
                                // 3페이지 일떄 1~3 3%3 = 0 > 1~3
//        int startBtn = (1+(page%btnSize==0? page/btnSize-1 : page/btnSize)*btnSize); // 1이면 1 2이면 1 3이면 1 4이면 4
            int startBtn = ((page-1)/btnSize*btnSize)+1; // 간단하게 하면
            // 3. 페이지 버튼 끝번호
        int endBtn =(btnSize+(page%btnSize==0? page/btnSize-1 : page/btnSize)*btnSize);
            // 간단하게 하면 int endBtn = startbtn+btnsize-1;
            // 페이지버튼의 끝 번호가 총페이지수 보다는 커질수 없다.
        if(endBtn>=totalPage){endBtn=totalPage;}

        // pageDto 구성 (page 값 넘기려고 추가로 작업하는 일)
//        BoardPageDto boardPageDto = new BoardPageDto(page, totalPage, startBtn, endBtn, list); // 빌더 패턴 쓴다고 이거 지움
        // pageDto 구성 * 빌더패턴 : 생성자의 단점( 매개변수에 따른 유연성 부족)을 보완
        // 사용방법 : 클래스명.builder().필드명(대입값).필드명(대입값).build(); = 빈 깡통(new)에 넣는 세터와 유사함
        // + 생성자보단 유연성 : 매개변수의 순서와 개수 자유롭다
            // 빌더패턴 vs 유연성 vs setter
        BoardPageDto boardPageDto =
                BoardPageDto.builder()
                        .page(page)
                        .totalPage(totalPage)
                        .totalBoardSize(totalBoardSize)
                        .list(list)
                        .startBtn(startBtn)
                        .endBtn(endBtn)
                        .build();
        // 빌더 패턴은 순서 안맞춰도 됨.

        return boardPageDto;

    }
    // 3. 개별 글 출력 호출        /board/view.do GET -- 호출이니까 // 게시물 번호 필요

    public BoardDto doGetBoardView( int bno){
        System.out.println("BoardService.doGetBoardView");

        //조회수 처리
        boardDao.boardViewIncrease(bno);

        return boardDao.doGetBoardView(bno);
    }

    // 4. 글 수정 처리           /board/update.do   PUT          // Dto 필요
    public boolean doUpdateBoard(BoardDto boardDto){
        System.out.println("BoardService.doUpdateBoard");

    // 기존 첨부 파일 명 구하기
        String bfile = boardDao.doGetBoardView((int)boardDto.getBno()).getBfile();

        // 새로운 첨부 파일이 있다 없다.
        if(!boardDto.getUploadfile().isEmpty()){//수정시 새로운 첨부파일이 있으면

            // 새로운 첨부파일을 업로드하고 기존 첨부파일 삭제
            String fileName = fileService.fileUpload(boardDto.getUploadfile());
            if(fileName != null){ // 업로드 성공
                boardDto.setBfile(fileName); // 새로운 첨부파일의 이름 dto 대입
                // 기존 첨부파일 삭제
                fileService.fileDelete(bfile);
            }else{
                return false; // 업로드 실패
            }

        }else{ // 새로운 첨부 파일 없으면
            // 업로드 할 필요 없다.
            // 기존 첨부파일명을 그대로 대입.
            boardDto.setBfile(bfile);
        }
        return boardDao.doUpdateBoard(boardDto);
    }


    // 5. 글 삭제 처리           /board/delete.do    DELETE      // 게시물 번호 필요
    public boolean doDeleteBoard(long bno){ // 얘는 쿼리스트링 입니다요잇 = @RequestParam
        System.out.println("BoardService.doDeleteBoard");
        // 레코드 삭제 전에 삭제할 첨부파일명을 임시로 꺼내둔다.
        String bfile = boardDao.doGetBoardView(bno).getBfile();
        // 1. 글 삭제 처리(DAO)
        boolean result = boardDao.doDeleteBoard(bno);
        // 2. DAO처리 성공시 첨부파일도 삭제
        if(result){
            // 기존에 첨부파일이 있었으면, 삭제
            System.out.println("bfile = " + bfile);
            if(bfile!=null){
                return fileService.fileDelete(bfile);
            }

        }
        return result;
    }
    // 6. 게시물 작성자 인증
    public boolean boardWriterAuth(long bno, String mid){
        return boardDao.boardWriterAuth(bno,mid);
    }

    // 7. 댓글 등록
    public boolean postReplyWrite( Map<String, String> map){
        System.out.println("BoardController.postReplyWrite");
        return boardDao.postReplyWrite(map);
    }

    // 8. 댓글 출력
    public List<Map<String,String>> getReplyDo( int bno ){
        System.out.println("BoardController.getReplyDo");
        return boardDao.getReplyDo(bno);
    }
    // =======================머스테치는 컨트롤에서 뷰 템플렛을 반환======================== //

    // 1. 글쓰기 페이지 이동        /board/write GET -- 메소드가 달라야 같은 URL로 실행 가능함.

    // 2. 게시판 페이지 이동        /board GET      -- 페이지 전환 (A 태그) GET 방식 페이지는 GET라고 보면 됨,

    // 3. 게시판 상세 페이지 이동     /board/view   GET

    // 4. 글 수정 페이지 이동       /board/update GET

}
